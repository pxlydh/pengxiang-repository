package pers.pengxiang.repository.leetcode.common.object.tree.nary;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Leetcode N 叉树节点
 *
 * @author pengxiang
 * Create on 2023-07-10 23:33:05
 */
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    public int val;

    public List<Node> children;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}