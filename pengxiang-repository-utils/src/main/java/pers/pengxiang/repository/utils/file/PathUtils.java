package pers.pengxiang.repository.utils.file;

import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

import lombok.SneakyThrows;

/**
 * @author pengxiang
 * Create on 2022-11-15 23:03:37
 */
public class PathUtils {
    private PathUtils() {
    }

    /**
     * 获取 '指定路径' 对应文件的扩展名
     *
     * @param path 指定路径
     * @return path 对应文件的扩展名
     */
    public static String getExtension(Path path) {
        if (path == null) {
            throw new NullPointerException();
        }
        if (Files.isDirectory(path)) {
            throw new IllegalArgumentException("该 path 是一个目录");
        }
        String fileName = path.toFile().getName();
        int index = fileName.lastIndexOf('.');
        return index == -1 ? "" : fileName.substring(index + 1);
    }

    /**
     * 将 '指定内容' 写入 '指定目标文件'
     *
     * @param target      指定目标文件
     * @param content     指定内容
     * @param openOptions 指定文件打开方式的选项
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
     * 将 '指定源文件' 复制到 '指定目标文件'
     *
     * @param source      指定源文件
     * @param target      指定目标文件
     * @param copyOptions 指定如何进行移动的选项
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
     * 将 '指定源文件' 移动到 '指定目标文件'
     *
     * @param source      指定源文件
     * @param target      指定目标文件
     * @param copyOptions 指定如何进行移动的选项
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
