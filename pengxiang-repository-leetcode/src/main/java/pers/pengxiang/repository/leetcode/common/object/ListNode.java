package pers.pengxiang.repository.leetcode.common.object;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author pengxiang
 * Create on 2022-11-15 23:31:18
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