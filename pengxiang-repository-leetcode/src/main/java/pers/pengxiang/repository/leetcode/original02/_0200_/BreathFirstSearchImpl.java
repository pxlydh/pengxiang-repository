package pers.pengxiang.repository.leetcode.original02._0200_;

/**
 * <a href = "https://leetcode-cn.com/problems/number-of-islands/">200. 岛屿数量</a>
 * 解题思路：广度优先搜索
 * <p>*******************************************************</p>
 * <p>执行结果：通过</p>
 * <p>执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户</p>
 * <p>内存消耗：50 MB, 在所有 Java 提交中击败了30.66%的用户</p>
 * <p>*******************************************************</p>
 *
 * @author pengxiang
 * Create on 2023-01-04 01:20:13
 */
public class BreathFirstSearchImpl implements NumberOfIslands {
    @Override
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] queue = new int[m * n];
        int islands = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    update(grid, i, j, queue);
                }
            }
        }
        return islands;
    }

    // 从矩阵 grid 的 (i0, j0) 位置开始进行广度优先搜索，将搜索到的值为 1 的网格更新为 0。
    private void update(char[][] grid, int i0, int j0, int[] queue) {
        int m = grid.length, n = grid[0].length;
        grid[i0][j0] = '0';
        queue[0] = i0 * n + j0;
        for (int head = 0, tail = 1; head < tail; head++) {
            int i = queue[head] / n, j = queue[head] % n;
            if (i + 1 < m && grid[i + 1][j] == '1') {
                grid[i + 1][j] = '0';
                queue[tail++] = queue[head] + n;
            }
            if (j + 1 < n && grid[i][j + 1] == '1') {
                grid[i][j + 1] = '0';
                queue[tail++] = queue[head] + 1;
            }
            if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                grid[i - 1][j] = '0';
                queue[tail++] = queue[head] - n;
            }
            if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                grid[i][j - 1] = '0';
                queue[tail++] = queue[head] - 1;
            }
        }
    }
}