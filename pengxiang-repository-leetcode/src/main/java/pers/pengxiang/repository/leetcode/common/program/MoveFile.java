package pers.pengxiang.repository.leetcode.common.program;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.SneakyThrows;
import pers.pengxiang.repository.leetcode.common.filtervisitor.LeetcodeFileVisitor;

/**
 * 处理 leetcode 文件程序
 *
 * @author pengxiang
 * Create on 2023-06-30 00:48:30
 */
public class MoveFile {
    private static final String BASE_PACKAGE = "pers.pengxiang.repository.leetcode.common";

    @SneakyThrows
    public static void main(String[] args) {
        InputStream in = ClassLoader.getSystemResourceAsStream("leetcode.properties");
        Properties properties = new Properties();
        properties.load(in);
        ApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        LeetcodeFileVisitor fileVisitor = context.getBean(LeetcodeFileVisitor.class);
        String programPath = System.getProperty("user.dir");
        String leetcodePath = properties.getProperty("origin-path");
        Files.walkFileTree(Paths.get(programPath + leetcodePath), fileVisitor);
    }
}