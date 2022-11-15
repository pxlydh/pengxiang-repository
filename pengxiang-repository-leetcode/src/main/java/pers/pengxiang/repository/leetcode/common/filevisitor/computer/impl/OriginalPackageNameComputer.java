package pers.pengxiang.repository.leetcode.common.filevisitor.computer.impl;

import pers.pengxiang.repository.leetcode.common.filevisitor.computer.PackageNameComputer;

/**
 * Leetcode 原创题目包名计算器
 *
 * @author pengxiang
 * Create on 2022-11-15 22:40:10
 */
public class OriginalPackageNameComputer implements PackageNameComputer {
    @Override
    public String getPackageName(String questionId) {
        String realQuestionId = questionId.replace("_", "");
        int questionNo = Integer.parseInt(realQuestionId);
        int pageNo = questionNo / 100 + 1;
        if (questionNo % 100 == 0) {
            pageNo--;
        }
        return (pageNo < 10 ? "original0" : "original") + pageNo + "." + questionId;
    }
}