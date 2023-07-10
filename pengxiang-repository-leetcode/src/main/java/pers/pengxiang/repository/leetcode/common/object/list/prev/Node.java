package pers.pengxiang.repository.leetcode.common.object.list.prev;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Leetcode 双向链表节点
 *
 * @author pengxiang
 * Create on 2023-07-10 23:34:59
 */
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    public int val;

    public Node prev;

    public Node next;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}