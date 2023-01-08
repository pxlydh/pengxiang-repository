package pers.pengxiang.repository.leetcode.original13._1254_;

import java.util.Arrays;

/**
 * <a href = "https://leetcode-cn.com/problems/number-of-closed-islands/">1254. 统计封闭岛屿的数目</a>
 * 解题思路：并查集
 * <p>*******************************************************</p>
 * <p>执行结果：通过</p>
 * <p>执行用时：2 ms, 在所有 Java 提交中击败了50.59%的用户</p>
 * <p>内存消耗：41.5 MB, 在所有 Java 提交中击败了93.26%的用户</p>
 * <p>*******************************************************</p>
 *
 * @author pengxiang
 * Create on 2023-01-09 03:22:08
 */
public class DisjointSetsImpl implements NumberOfClosedIslands {
    private int[] parent; // parent[i] 记录并查集中节点 i 的父节点。
    private int components; // 记录并查集中连通分量的数量。

    @Override
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int dummy = components = m * n;
        parent = new int[dummy + 1];
        Arrays.fill(parent, -1);
        for (int i = 0; i < m; i++) {
            union(dummy, i * n);
            union(dummy, i * n + n - 1);
        }
        for (int j = 0; j < n; j++) {
            union(dummy, j);
            union(dummy, (m - 1) * n + j);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int idx = i * n + j;
                if (grid[i][j] == 1) {
                    union(dummy, idx);
                    continue;
                }
                if (grid[i - 1][j] == grid[i][j]) {
                    union(idx, idx - n);
                }
                if (grid[i][j - 1] == grid[i][j]) {
                    union(idx, idx - 1);
                }
            }
        }
        return components;
    }

    // 获取并查集中节点 i 所在集合的代表元。
    private int find(int i) {
        if (parent[i] < 0) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    // 合并并查集中节点 i1 与节点 i2 所在的集合。
    private void union(int i1, int i2) {
        int root1 = find(i1);
        int root2 = find(i2);
        if (root1 == root2) {
            return;
        }
        int h1 = -parent[root1];
        int h2 = -parent[root2];
        if (h1 < h2) {
            parent[root1] = root2;
        } else if (h1 > h2) {
            parent[root2] = root1;
        } else {
            parent[root2]--;
            parent[root1] = root2;
        }
        components--; // 合并成功则并查集中连通分量的数量 -1。
    }
}