package pers.pengxiang.repository.leetcode.common.filtervisitor.computer.impl;

import org.springframework.stereotype.Service;

import pers.pengxiang.repository.leetcode.common.filtervisitor.computer.ComputerTypeEnum;
import pers.pengxiang.repository.leetcode.common.filtervisitor.computer.PackageNameComputer;

/**
 * Leetcode《程序员面试金典 (第6版)》题目包名计算器
 *
 * @author pengxiang
 * Create on 2023-06-30 00:20:55
 */
@Service
public class InterviewPackageNameComputer implements PackageNameComputer {
    @Override
    public ComputerTypeEnum getComputerType() {
        return ComputerTypeEnum.INTERVIEW;
    }

    @Override
    public String getPackageName(String questionId) {
        return "interview." + questionId.replace("面试题 ", "").replace(".", "");
    }
}