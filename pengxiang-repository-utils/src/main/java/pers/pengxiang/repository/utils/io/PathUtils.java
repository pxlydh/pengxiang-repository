package pers.pengxiang.repository.utils.io;

import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

import lombok.SneakyThrows;

/**
 * java.nio.file.Path 工具类
 *
 * @author pengxiang
 * Create on 2023-06-30 00:37:00
 */
public class PathUtils {
    private PathUtils() {
    }

    /**
     * 获取 '路径' 对应文件的扩展名
     *
     * @param path 路径
     * @return path 对应文件的扩展名
     */
    public static String getExtension(Path path) {
        if (path == null) {
            throw new NullPointerException();
        }
        if (Files.isDirectory(path)) {
            return null;
        }
        String fileName = path.toFile().getName();
        int index = fileName.lastIndexOf('.');
        return index == -1 ? "" : fileName.substring(index + 1);
    }

    /**
     * 将 '内容' 写入 '目标文件'
     *
     * @param target      目标文件
     * @param content     内容
     * @param openOptions 文件打开方式的选项
     */
    @SneakyThrows
    public static void writeString(Path target, CharSequence content, OpenOption... openOptions) {
        Path parent = target.getParent();
        if (Files.notExists(parent)) {
            Files.createDirectories(parent);
        }
        Files.writeString(target, content, openOptions);
    }

    /**
     * 将 '源文件' 复制到 '目标文件'
     *
     * @param source      源文件
     * @param target      目标文件
     * @param copyOptions 如何进行移动的选项
     */
    @SneakyThrows
    public static void copy(Path source, Path target, CopyOption... copyOptions) {
        Path parent = target.getParent();
        if (Files.notExists(parent)) {
            Files.createDirectories(parent);
        }
        Files.copy(source, target, copyOptions);
    }

    /**
     * 将 '源文件' 移动到 '目标文件'
     *
     * @param source      源文件
     * @param target      目标文件
     * @param copyOptions 如何进行移动的选项
     */
    @SneakyThrows
    public static void move(Path source, Path target, CopyOption... copyOptions) {
        Path parent = target.getParent();
        if (Files.notExists(parent)) {
            Files.createDirectories(parent);
        }
        Files.move(source, target, copyOptions);
    }
}