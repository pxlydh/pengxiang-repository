package pers.pengxiang.repository.leetcode.common.filtervisitor.computer.impl;

import org.springframework.stereotype.Service;

import pers.pengxiang.repository.leetcode.common.filtervisitor.computer.ComputerTypeEnum;
import pers.pengxiang.repository.leetcode.common.filtervisitor.computer.PackageNameComputer;

/**
 * Leetcode 比赛题目包名计算器
 *
 * @author pengxiang
 * Create on 2023-06-30 00:23:47
 */
@Service
public class LcpLcsPackageNameComputer implements PackageNameComputer {
    @Override
    public ComputerTypeEnum getComputerType() {
        return ComputerTypeEnum.LCP_AND_LCS;
    }

    @Override
    public String getPackageName(String questionId) {
        return "lcp_lcs." + questionId.replace(" ", "").toLowerCase();
    }
}