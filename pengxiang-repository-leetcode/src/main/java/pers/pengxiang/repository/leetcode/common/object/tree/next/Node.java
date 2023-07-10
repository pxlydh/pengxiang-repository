package pers.pengxiang.repository.leetcode.common.object.tree.next;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Leetcode 带 next 指针的二叉树节点
 *
 * @author pengxiang
 * Create on 2023-07-10 23:32:30
 */
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    public int val;

    public Node left;

    public Node right;

    public Node next;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}