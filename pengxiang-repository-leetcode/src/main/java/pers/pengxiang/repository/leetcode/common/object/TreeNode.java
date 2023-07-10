package pers.pengxiang.repository.leetcode.common.object;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Leetcode 二叉树节点
 *
 * @author pengxiang
 * Create on 2023-06-30 00:47:07
 */
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}