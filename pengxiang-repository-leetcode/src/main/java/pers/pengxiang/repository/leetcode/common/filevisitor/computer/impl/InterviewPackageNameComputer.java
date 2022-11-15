package pers.pengxiang.repository.leetcode.common.filevisitor.computer.impl;

import pers.pengxiang.repository.leetcode.common.filevisitor.computer.PackageNameComputer;

/**
 * Leetcode《程序员面试金典 (第6版)》题目包名计算器
 *
 * @author pengxiang
 * Create on 2022-11-15 22:36:26
 */
public class InterviewPackageNameComputer implements PackageNameComputer {
    @Override
    public String getPackageName(String questionId) {
        return "interview." + questionId.replace("面试题 ", "").replace(".", "");
    }
}