package pers.pengxiang.repository.leetcode.common.object;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Leetcode 链表节点
 *
 * @author pengxiang
 * Create on 2023-06-30 00:45:56
 */
@AllArgsConstructor
@NoArgsConstructor
public class ListNode {
    public int val;

    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}