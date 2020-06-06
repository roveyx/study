package net.xiaosaguo.study.se.io.nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * description: 修改文件名
 *
 * @author xiaosaguo
 * @date 2020/06/06
 */
public class RenameFiles {

    public static final String NAME = ".avi";
    public static final String REGEX = "^[0-9]{1,2}、[\\s\\S]*$";
    public static final String DIR_PATH = "E:\\学习E\\谷粒商城\\1.分布\\";

    public static void main(String[] args) throws IOException {
        // 获取文件夹路径
        Path dirPath = Paths.get(DIR_PATH);
        // 遍历文件夹中的文件及目录，不递归
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
            for (Path filePath : stream) {
                // 获取文件名，不带路径
                String fileName = filePath.getFileName().toString();
                /// System.out.println(filePath.getFileName());
                // 如果匹配正则和文件拓展名，就改名
                if (fileName.matches(REGEX) && fileName.endsWith(NAME)) {
                    // 改名后的文件，需要带上路径，注意文件夹路径最后需要 “\”
                    fileName = DIR_PATH + 0 + fileName;
                    /// fileName = fileName.replace("0", "");
                    Path renamePath = Paths.get(fileName);
                    System.out.println(renamePath);
                    Files.move(filePath, renamePath);
                }
            }
        }
    }
}
