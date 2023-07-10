package pers.pengxiang.repository.leetcode.common.filtervisitor.computer.impl;

import org.springframework.stereotype.Service;

import pers.pengxiang.repository.leetcode.common.filtervisitor.computer.ComputerTypeEnum;
import pers.pengxiang.repository.leetcode.common.filtervisitor.computer.PackageNameComputer;

/**
 * Leetcode《剑指 Offer (第2版)》题目包名计算器
 *
 * @author pengxiang
 * Create on 2023-06-30 00:25:11
 */
@Service
public class OfferPackageNameComputer implements PackageNameComputer {
    @Override
    public ComputerTypeEnum getComputerType() {
        return ComputerTypeEnum.OFFER;
    }

    @Override
    public String getPackageName(String questionId) {
        questionId = questionId.replace("剑指 Offer ", "");
        questionId = questionId.replace(" ", "");
        questionId = questionId.replace("-", "");
        questionId = questionId.replace("I", "");
        questionId = questionId.replace("_", "");
        return "lc_offer01._00" + questionId + "_";
    }
}