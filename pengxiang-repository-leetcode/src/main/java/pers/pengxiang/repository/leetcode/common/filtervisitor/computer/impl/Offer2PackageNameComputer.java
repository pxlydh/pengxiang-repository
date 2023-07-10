package pers.pengxiang.repository.leetcode.common.filtervisitor.computer.impl;

import org.springframework.stereotype.Service;

import pers.pengxiang.repository.leetcode.common.filtervisitor.computer.ComputerTypeEnum;
import pers.pengxiang.repository.leetcode.common.filtervisitor.computer.PackageNameComputer;

/**
 * Leetcode《剑指 Offer (专项突击版)》题目包名计算器
 *
 * @author pengxiang
 * Create on 2023-06-30 00:24:23
 */
@Service
public class Offer2PackageNameComputer implements PackageNameComputer {
    @Override
    public ComputerTypeEnum getComputerType() {
        return ComputerTypeEnum.OFFER2;
    }

    @Override
    public String getPackageName(String questionId) {
        return "lc_offer02._0" + questionId.replace("剑指 Offer II ", "").replace("_", "") + "_";
    }
}