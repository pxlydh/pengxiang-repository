package pers.pengxiang.repository.leetcode.common.filevisitor.model;


import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * Leetcode 题目文件基本信息
 *
 * @author pengxiang
 * Create on 2022-07-05 09:28:52
 */
@Builder
@Getter
public class LeetcodeFileInfo {
    /**
     * Leetcode 题目文件前缀
     */
    private String prefix;
    /**
     * Leetcode 题目文件扩展名
     */
    private String extension;
    /**
     * Leetcode 题目编号
     */
    private String questionId;
    /**
     * Leetcode 题目名称
     */
    private String questionName;
    /**
     * Leetcode 题目的 java 接口名称
     */
    private String interfaceName;
    /**
     * Leetcode 题目的 java 导包语句
     */
    private String imports;
    /**
     * Leetcode 题目的 java 方法
     */
    private String methods;
    /**
     * Leetcode 题目的 java 注释
     */
    private String description;
    /**
     * Leetcode 题目的 java 包名
     */
    private String packageName;
    /**
     * Leetcode 题目的 java package 语句
     */
    private String packageStatement;
    /**
     * Leetcode 题目的 java 实现代码
     */
    private List<List<String>> classes;
}