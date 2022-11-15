package pers.pengxiang.repository.leetcode.common.object;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author pengxiang
 * Create on 2022-11-15 23:30:12
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