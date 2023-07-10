package pers.pengxiang.repository.leetcode.common.filtervisitor.computer;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;

/**
 * @author pengxiang
 * Create on 2023-06-30 00:18:16
 */
@AllArgsConstructor
public enum ComputerTypeEnum {
    /**
     * Leetcode 比赛题目
     */
    LCP_AND_LCS("LCP,LCS"),

    /**
     * Leetcode《剑指 Offer (专项突击版)》题目
     */
    OFFER2("剑指 Offer II"),

    /**
     * Leetcode《剑指 Offer (第2版)》题目
     */
    OFFER("剑指 Offer"),

    /**
     * Leetcode《程序员面试金典 (第6版)》题目
     */
    INTERVIEW("面试题"),

    /**
     * Leetcode 原创题目
     */
    ORIGIN("");

    private final String patterns;

    public boolean match(String str) {
        return Stream.of(patterns.split(",")).anyMatch(str::contains);
    }
}