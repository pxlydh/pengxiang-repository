package pers.pengxiang.repository.leetcode.original07._0695_;

/**
 * <a href = "https://leetcode-cn.com/problems/max-area-of-island/">695. 岛屿的最大面积</a>
 * 解题思路：广度优先搜索
 * <p>*******************************************************</p>
 * <p>执行结果：通过</p>
 * <p>执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户</p>
 * <p>内存消耗：41.9 MB, 在所有 Java 提交中击败了11.72%的用户</p>
 * <p>*******************************************************</p>
 *
 * @author pengxiang
 * Create on 2023-01-05 12:53:59
 */
public class BreathFirstSearchImpl implements MaxAreaOfIsland {
    @Override
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] queue = new int[m * n];
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = computeArea(grid, i, j, queue);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    // 从矩阵 grid 的 (i0, j0) 位置开始进行广度优先搜索，计算与 (i0, j0) 相连且值为 1 的网格的数量。
    private int computeArea(int[][] grid, int i0, int j0, int[] queue) {
        int m = grid.length, n = grid[0].length;
        int tail = 0;
        grid[i0][j0] = 0;
        queue[tail++] = i0 * n + j0;
        for (int head = 0; head < tail; head++) {
            int i = queue[head] / n, j = queue[head] % n;
            if (i + 1 < m && grid[i + 1][j] == 1) {
                grid[i + 1][j] = 0;
                queue[tail++] = queue[head] + n;
            }
            if (j + 1 < n && grid[i][j + 1] == 1) {
                grid[i][j + 1] = 0;
                queue[tail++] = queue[head] + 1;
            }
            if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                grid[i - 1][j] = 0;
                queue[tail++] = queue[head] - n;
            }
            if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                grid[i][j - 1] = 0;
                queue[tail++] = queue[head] - 1;
            }
        }
        return tail;
    }
}