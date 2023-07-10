package pers.pengxiang.repository.leetcode.common.object.tree.parent;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Leetcode 带 parent 指针的二叉树节点
 *
 * @author pengxiang
 * Create on 2023-07-10 23:31:51
 */
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    public int val;

    public Node left;

    public Node right;

    public Node parent;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}