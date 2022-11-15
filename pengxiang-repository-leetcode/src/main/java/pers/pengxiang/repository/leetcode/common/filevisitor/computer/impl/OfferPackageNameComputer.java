package pers.pengxiang.repository.leetcode.common.filevisitor.computer.impl;

import pers.pengxiang.repository.leetcode.common.filevisitor.computer.PackageNameComputer;

/**
 * Leetcode《剑指 Offer (专项突击版)》题目包名计算器
 *
 * @author pengxiang
 * Create on 2022-11-15 22:39:21
 */
public class OfferPackageNameComputer implements PackageNameComputer {
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
