package net.xiaosaguo.study.se.io.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * description: 文件类测试
 *
 * @author xiaosaguo
 * @date 2020/06/06
 */
public class FileDemo {

    public static final String NAME = ".avi";
    public static final String REGEX = "^[0-9]{1,3}、[\\s\\S]*$";
    public static final String DIR_PATH = "E:\\test\\test\\test\\";

    public static void main(String[] args) throws IOException, InterruptedException {

        // File.separator

        // 判断一个文件是否存在
        Path path = Paths.get(DIR_PATH);
        boolean exists = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
        System.out.println(exists);


        // 判断文件是否是文件夹
        boolean directory = Files.isDirectory(path);
        System.out.println(directory);


        // 遍历文件，当前目录下，不递归
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(DIR_PATH))) {
            for (Path file : stream) {
                System.out.println(file.getFileName());
            }
        }


        // 自定义遍历目录及其子目录下的文件，递归
        Files.walkFileTree(Paths.get(DIR_PATH), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(NAME)) {
                    System.out.println(file.getFileName());
                }
                return super.visitFile(file, attrs);
            }
        });


        // 删除文件
        Files.delete(path);


        // 创建多级目录
        Files.createDirectories(Paths.get(DIR_PATH));


        // 创建一个新的空文件，如果该文件已经存在，则失败。
        // 检查文件是否存在以及新文件的创建(如果文件不存在)是一个单独的操作，相对于可能影响目录的所有其他文件系统活动而言，这是一个原子操作
        Files.createFile(Paths.get(DIR_PATH));


        // 文件的复制
        Path src = Paths.get("F:\\LZ\\xx\\dd\\1.txt");
        Path target = Paths.get("F:\\LZ\\xx\\dd\\2.txt");
        //REPLACE_EXISTING:文件存在，就替换
        Files.copy(src, target, StandardCopyOption.REPLACE_EXISTING);


        // 一行一行读取文件
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        }


        // 写入字符串
        // 追加
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            writer.write("hello word Path");
            writer.close();
        }


        // 二进制 读写 与字符串类似
        // 字符串
        for (String line : Files.readAllLines(path)) {
            System.out.println(line);
        }
        // 二进制流
        byte[] bytes = Files.readAllBytes(path);


        // 读取文件的 最后 1000 个字符
        FileChannel fileChannel = FileChannel.open(Paths.get(DIR_PATH), StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        fileChannel.read(byteBuffer, fileChannel.size() - 1000);
        System.out.println(byteBuffer);


        // 监测是否有目录下的文件、目录被修改、创建、删除.
        WatchService watchService = FileSystems.getDefault().newWatchService();
        WatchKey watchKey = Paths.get(DIR_PATH).register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        while (true) {
            watchKey = watchService.take();
            for (WatchEvent<?> pollEvent : watchKey.pollEvents()) {
                if (pollEvent.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    System.out.println("这个目录下创建了文件");
                } else if (pollEvent.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.println("这个目录下的文件被删除了");
                } else if (pollEvent.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                    System.out.println("这个目录下的文件被修改了");
                }
            }
            watchKey.reset();
        }
    }

    public static void walkFileTree() throws IOException {
        // 自定义遍历文件
        Path fileTree = Files.walkFileTree(Paths.get(DIR_PATH), new FindVisitor());
        System.out.println(fileTree.toString());
    }

    private static class FindVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toString().endsWith(NAME) && file.toString().matches(REGEX)) {
                System.out.println(file);
            }
            return FileVisitResult.CONTINUE;
        }
    }

}
