package pers.pengxiang.repository.leetcode.common.filevisitor.computer.impl;

import pers.pengxiang.repository.leetcode.common.filevisitor.computer.PackageNameComputer;

/**
 * Leetcode 比赛题目包名计算器
 *
 * @author pengxiang
 * Create on 2022-11-15 22:37:29
 */
public class LcpLcsPackageNameComputer implements PackageNameComputer {
    @Override
    public String getPackageName(String questionId) {
        return "lcp_lcs." + questionId.replace(" ", "").toLowerCase();
    }
}