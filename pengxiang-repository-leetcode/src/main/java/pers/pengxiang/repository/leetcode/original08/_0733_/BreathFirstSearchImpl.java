package pers.pengxiang.repository.leetcode.original08._0733_;

/**
 * <a href = "https://leetcode-cn.com/problems/flood-fill/">733. 图像渲染</a>
 * 解题思路：广度优先搜索
 * <p>*******************************************************</p>
 * <p>执行结果：通过</p>
 * <p>执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户</p>
 * <p>内存消耗：42.4 MB, 在所有 Java 提交中击败了45.57%的用户</p>
 * <p>*******************************************************</p>
 *
 * @author pengxiang
 * Create on 2023-01-04 01:13:22
 */
public class BreathFirstSearchImpl implements FloodFill {
    @Override
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) {
            return image;
        }
        int m = image.length, n = image[0].length;
        int oldColor = image[sr][sc];
        image[sr][sc] = color;
        int[] queue = new int[m * n];
        queue[0] = sr * n + sc;
        for (int head = 0, tail = 1; head < tail; head++) {
            int i = queue[head] / n, j = queue[head] % n;
            if (i + 1 < m && image[i + 1][j] == oldColor) {
                image[i + 1][j] = color;
                queue[tail++] = queue[head] + n;
            }
            if (j + 1 < n && image[i][j + 1] == oldColor) {
                image[i][j + 1] = color;
                queue[tail++] = queue[head] + 1;
            }
            if (i - 1 >= 0 && image[i - 1][j] == oldColor) {
                image[i - 1][j] = color;
                queue[tail++] = queue[head] - n;
            }
            if (j - 1 >= 0 && image[i][j - 1] == oldColor) {
                image[i][j - 1] = color;
                queue[tail++] = queue[head] - 1;
            }
        }
        return image;
    }
}