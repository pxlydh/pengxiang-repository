package pers.pengxiang.repository.leetcode.common.filtervisitor.service;

import java.nio.file.Path;
import java.util.Set;

import pers.pengxiang.repository.leetcode.common.filtervisitor.LeetcodeFileInfo;

/**
 * 创建 Leetcode 题目相关文件服务
 *
 * @author pengxiang
 * Create on 2023-06-30 00:29:04
 */
public interface CreateFileService {
    /**
     * Java 文件扩展名
     */
    Set<String> JAVA = Set.of("java");

    /**
     * SQL、Shell 文件扩展名
     */
    Set<String> SQL_AND_SHELL = Set.of("sql", "sh");

    /**
     * 所有支持文件的扩展名
     */
    Set<String> ALL_EXTENSION = Set.of("java", "sql", "sh");

    /**
     * 返回当前 service 支持的扩展名
     *
     * @return 当前 service 支持的扩展名
     */
    Set<String> getExtension();

    /**
     * 根据 leetcode 文件信息，在新目录中创建文件
     *
     * @param fileInfo  leetcode 文件信息
     * @param file      leetcode 文件路径
     * @param newFolder 新文件的保存目录
     */
    void accept(LeetcodeFileInfo fileInfo, Path file, Path newFolder);
}