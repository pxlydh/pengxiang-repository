package pers.pengxiang.repository.leetcode.common.program;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import lombok.SneakyThrows;
import pers.pengxiang.repository.leetcode.common.filevisitor.LeetcodeFileVisitor;

/**
 * @author pengxiang
 * Create on 2022-11-15 23:20:53
 */
public class MoveFile {
    @SneakyThrows
    public static void main(String[] args) {
        InputStream in = ClassLoader.getSystemResourceAsStream("leetcode.properties");
        Properties properties = new Properties();
        properties.load(in);
        LeetcodeFileVisitor fileVisitor = new LeetcodeFileVisitor();
        String programPath = System.getProperty("user.dir");
        String leetcodePath = properties.getProperty("origin-path");
        Files.walkFileTree(Paths.get(programPath + leetcodePath), fileVisitor);
    }
}