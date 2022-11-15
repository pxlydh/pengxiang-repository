package pers.pengxiang.repository.leetcode.common.filevisitor.computer.impl;

import pers.pengxiang.repository.leetcode.common.filevisitor.computer.PackageNameComputer;

/**
 * Leetcode《剑指 Offer (第2版)》题目包名计算器
 *
 * @author pengxiang
 * Create on 2022-11-15 22:38:39
 */
public class Offer2PackageNameComputer implements PackageNameComputer {
    @Override
    public String getPackageName(String questionId) {
        return "lc_offer02._0" + questionId.replace("剑指 Offer II ", "").replace("_", "") + "_";
    }
}