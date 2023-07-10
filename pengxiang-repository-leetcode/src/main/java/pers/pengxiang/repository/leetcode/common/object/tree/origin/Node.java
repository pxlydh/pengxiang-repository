package pers.pengxiang.repository.leetcode.common.object.tree.origin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Leetcode 二叉树节点
 *
 * @author pengxiang
 * Create on 2023-07-10 23:31:16
 */
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    public int val;

    public Node left;

    public Node right;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}