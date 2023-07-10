package pers.pengxiang.repository.leetcode.common.object.list.origin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Leetcode 链表节点
 *
 * @author pengxiang
 * Create on 2023-07-10 23:34:10
 */
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    public int val;

    public Node next;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}