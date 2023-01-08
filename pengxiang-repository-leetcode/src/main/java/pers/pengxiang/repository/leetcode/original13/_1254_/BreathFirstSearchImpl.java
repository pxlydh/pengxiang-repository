package pers.pengxiang.repository.leetcode.original13._1254_;

/**
 * <a href = "https://leetcode-cn.com/problems/number-of-closed-islands/">1254. 统计封闭岛屿的数目</a>
 * 解题思路：广度优先搜索
 * <p>*******************************************************</p>
 * <p>执行结果：通过</p>
 * <p>执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户</p>
 * <p>内存消耗：41.5 MB, 在所有 Java 提交中击败了93.26%的用户</p>
 * <p>*******************************************************</p>
 *
 * @author pengxiang
 * Create on 2023-01-09 01:41:45
 */
public class BreathFirstSearchImpl implements NumberOfClosedIslands {
    @Override
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] queue = new int[m * n];
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                update(grid, i, 0, queue);
            }
            if (grid[i][n - 1] == 0) {
                update(grid, i, n - 1, queue);
            }
        }
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 0) {
                update(grid, 0, j, queue);
            }
            if (grid[m - 1][j] == 0) {
                update(grid, m - 1, j, queue);
            }
        }
        int closedIslands = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    closedIslands++;
                    update(grid, i, j, queue);
                }
            }
        }
        return closedIslands;
    }

    // 从矩阵 grid 的 (i0, j0) 位置开始进行广度优先搜索，将搜索到的值为 0 的网格更新为 1。
    private void update(int[][] grid, int i0, int j0, int[] queue) {
        int m = grid.length, n = grid[0].length;
        grid[i0][j0] = 1;
        queue[0] = i0 * n + j0;
        for (int head = 0, tail = 1; head < tail; head++) {
            int i = queue[head] / n, j = queue[head] % n;
            if (i + 1 < m && grid[i + 1][j] == 0) {
                grid[i + 1][j] = 1;
                queue[tail++] = queue[head] + n;
            }
            if (j + 1 < n && grid[i][j + 1] == 0) {
                grid[i][j + 1] = 1;
                queue[tail++] = queue[head] + 1;
            }
            if (i - 1 >= 0 && grid[i - 1][j] == 0) {
                grid[i - 1][j] = 1;
                queue[tail++] = queue[head] - n;
            }
            if (j - 1 >= 0 && grid[i][j - 1] == 0) {
                grid[i][j - 1] = 1;
                queue[tail++] = queue[head] - 1;
            }
        }
    }
}