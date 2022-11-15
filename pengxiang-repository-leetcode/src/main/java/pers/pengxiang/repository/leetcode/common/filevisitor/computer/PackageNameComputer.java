package pers.pengxiang.repository.leetcode.common.filevisitor.computer;

/**
 * Leetcode 题目包名计算器
 *
 * @author pengxiang
 * Create on 2022-11-15 22:28:30
 */
public interface PackageNameComputer {
    /**
     * 根据 leetcode 题目编号，返回该题目文件期望存放的包名
     *
     * @param questionId leetcode 题目编号
     * @return 期望存放的包名
     */
    String getPackageName(String questionId);
}