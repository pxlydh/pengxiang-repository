package pers.pengxiang.repository.leetcode.original02._0200_;

/**
 * <a href = "https://leetcode-cn.com/problems/number-of-islands/">200. 岛屿数量</a>
 * 解题思路：深度优先搜索
 * <p>*******************************************************</p>
 * <p>执行结果：通过</p>
 * <p>执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户</p>
 * <p>内存消耗：49.8 MB, 在所有 Java 提交中击败了54.41%的用户</p>
 * <p>*******************************************************</p>
 *
 * @author pengxiang
 * Create on 2023-01-04 01:18:27
 */
public class DepthFirstSearchImpl implements NumberOfIslands {
    @Override
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int islands = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    update(grid, i, j);
                }
            }
        }
        return islands;
    }

    // 从矩阵 grid 的 (i, j) 位置开始进行深度优先搜索，将搜索到的值为 1 的网格更新为 0。
    private void update(char[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        grid[i][j] = '0';
        if (i + 1 < m && grid[i + 1][j] == '1') {
            update(grid, i + 1, j);
        }
        if (j + 1 < n && grid[i][j + 1] == '1') {
            update(grid, i, j + 1);
        }
        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            update(grid, i - 1, j);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            update(grid, i, j - 1);
        }
    }
}