package pers.pengxiang.repository.leetcode.original07._0695_;

/**
 * <a href = "https://leetcode-cn.com/problems/max-area-of-island/">695. 岛屿的最大面积</a>
 * 解题思路：深度优先搜索
 * <p>*******************************************************</p>
 * <p>执行结果：通过</p>
 * <p>执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户</p>
 * <p>内存消耗：41.8 MB, 在所有 Java 提交中击败了18.46%的用户</p>
 * <p>*******************************************************</p>
 *
 * @author pengxiang
 * Create on 2023-01-05 12:40:30
 */
public class DepthFirstSearchImpl implements MaxAreaOfIsland {
    @Override
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = computeArea(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    // 从矩阵 grid 的 (i, j) 位置开始进行深度优先搜索，计算与 (i, j) 相连且值为 1 的网格的数量。
    private int computeArea(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        grid[i][j] = 0;
        int area = 1;
        if (i + 1 < m && grid[i + 1][j] == 1) {
            area += computeArea(grid, i + 1, j);
        }
        if (j + 1 < n && grid[i][j + 1] == 1) {
            area += computeArea(grid, i, j + 1);
        }
        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
            area += computeArea(grid, i - 1, j);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
            area += computeArea(grid, i, j - 1);
        }
        return area;
    }
}