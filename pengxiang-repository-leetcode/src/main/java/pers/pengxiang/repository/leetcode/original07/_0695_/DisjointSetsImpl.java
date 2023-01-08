package pers.pengxiang.repository.leetcode.original07._0695_;

import java.util.Arrays;

/**
 * <a href = "https://leetcode-cn.com/problems/max-area-of-island/">695. 岛屿的最大面积</a>
 * 解题思路：并查集
 * <p>*******************************************************</p>
 * <p>执行结果：通过</p>
 * <p>执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户</p>
 * <p>内存消耗：41.9 MB, 在所有 Java 提交中击败了12.42%的用户</p>
 * <p>*******************************************************</p>
 *
 * @author pengxiang
 * Create on 2023-01-05 12:59:17
 */
public class DisjointSetsImpl implements MaxAreaOfIsland {
    private int[] parent; // parent[i] 记录并查集中节点 i 的父节点。

    @Override
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        parent = new int[m * n];
        Arrays.fill(parent, -1);
        boolean hasIsland = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                hasIsland = true;
                int idx = i * n + j;
                if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                    union(idx, idx - n);
                }
                if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                    union(idx, idx - 1);
                }
            }
        }
        if (!hasIsland) {
            return 0;
        }
        int maxArea = 0;
        for (int size : parent) {
            maxArea = Math.max(maxArea, -size);
        }
        return maxArea;
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
        int size1 = -parent[root1];
        int size2 = -parent[root2];
        if (size1 < size2) {
            parent[root1] = root2;
            parent[root2] -= size1;
        } else {
            parent[root2] = root1;
            parent[root1] -= size2;
        }
    }
}