package pers.pengxiang.repository.leetcode.common.filtervisitor.computer;

/**
 * Leetcode 题目包名计算器
 *
 * @author pengxiang
 * Create on 2023-06-30 00:17:36
 */
public interface PackageNameComputer {
    /**
     * 获取包名计算器类型
     *
     * @return 包名计算器类型
     */
    ComputerTypeEnum getComputerType();

    /**
     * 根据 leetcode 题目编号，返回该题目文件期望存放的包名
     *
     * @param questionId leetcode 题目编号
     * @return 期望存放的包名
     */
    String getPackageName(String questionId);
}