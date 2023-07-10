package pers.pengxiang.repository.leetcode.common.filtervisitor.computer.impl;

import org.springframework.stereotype.Service;

import pers.pengxiang.repository.leetcode.common.filtervisitor.computer.ComputerTypeEnum;
import pers.pengxiang.repository.leetcode.common.filtervisitor.computer.PackageNameComputer;

/**
 * Leetcode 原创题目包名计算器
 *
 * @author pengxiang
 * Create on 2023-06-30 00:26:23
 */
@Service
public class OriginalPackageNameComputer implements PackageNameComputer {
    @Override
    public ComputerTypeEnum getComputerType() {
        return ComputerTypeEnum.ORIGIN;
    }

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