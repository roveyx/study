package net.xiaosaguo.study.se.clazz.string;

import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

import static java.lang.System.out;

/**
 * description: 测试 byte[]
 * <p>
 * Java 采用 unicode来表示字符，Java 中的一个 char 是 2 个字节，一个中文或英文字符的 unicode 编码都占 2个字节，
 * 但如果采用其他编码方式，一个字符占用的字节数则各不相同。
 * 在 GB2312 编码或 GBK 编码中，一个英文字母字符存储需要 1 个字节，一个汉子字符存储需要 2 个字节。
 * 在 UTF-8 编码中，一个英文字母字符存储需要 1 个字节，一个汉字字符储存需要 3 到 4 个字节。
 * 在 UTF-16 编码中，一个英文字母字符存储需要 2 个字节，一个汉字字符储存需要 3 到 4 个字节（Unicode 扩展区的一些汉字存储需要 4 个字节）。
 * 在 UTF-32 编码中，世界上任何字符的存储都需要 4 个字节。
 * <p>
 * 而与getBytes相对的,可以通过new String(byte[], decode)的方式来还原这个"中"字,
 * <p>
 * 有时候,为了让中文字符适应某些特殊要求(如http header要求其内容必须为iso8859-1编码),可能会通过将中文字符按照字节方式来编码的情况,如:
 * String s_iso88591 = new String("中".getBytes("UTF-8"),"ISO8859-1"),
 * 这样得到的s_iso8859-1字符串实际是三个在ISO8859-1中的字符,在将这些字符传递到目的地后,
 * 目的地程序再通过相反的方式String s_utf8 = new String(s_iso88591.getBytes("ISO8859-1"),"UTF-8")来得到正确的中文汉字"中"，
 * 这样就既保证了遵守协议规定、也支持中文。
 *
 * @author xiaosaguo
 * @date 2020/06/03 15:15
 */
public class StringTest {

    @SneakyThrows
    public static void main(String[] args) {

        // String str = "程序员";
        String str = "我a";
        // 默认编码
        byte[] bytes = str.getBytes();
        out.println("\nbytes.length : " + bytes.length);
        for (byte aByte : bytes) {
            out.print(aByte + " ");
        }
        // UTF-8 编码
        byte[] utf8Bytes = str.getBytes(StandardCharsets.UTF_8);
        out.println("\nutf8Bytes.length : " + utf8Bytes.length);
        for (byte aByte : utf8Bytes) {
            out.print(aByte + " ");
        }
        // GBK 编码
        byte[] gbkBytes = str.getBytes(CharsetEnum.GBK.val());
        out.println("\ngbkBytes.length : " + gbkBytes.length);
        for (byte aByte : gbkBytes) {
            out.print(aByte + " ");
        }

        // GBK 编码
        byte[] gb2312Bytes = str.getBytes(CharsetEnum.GB2312.val());
        out.println("\ngb2312Bytes.length : " + gb2312Bytes.length);
        for (byte aByte : gb2312Bytes) {
            out.print(aByte + " ");
        }
    }
}

enum CharsetEnum {
    GBK("GBK"), GB2312("GB2312");

    private String value;

    CharsetEnum(String value) {
        this.value = value;
    }

    public String val() {
        return value;
    }
}
