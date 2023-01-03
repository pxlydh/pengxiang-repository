package pers.pengxiang.repository.leetcode.original08._0733_;

/**
 * <a href = "https://leetcode-cn.com/problems/flood-fill/">733. 图像渲染</a>
 * 解题思路：深度优先搜索
 * <p>*******************************************************</p>
 * <p>执行结果：通过</p>
 * <p>执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户</p>
 * <p>内存消耗：42.1 MB, 在所有 Java 提交中击败了79.28%的用户</p>
 * <p>*******************************************************</p>
 *
 * @author pengxiang
 * Create on 2023-01-04 01:10:49
 */
public class DepthFirstSearchImpl implements FloodFill {
    @Override
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) {
            return image;
        }
        update(image, sr, sc, image[sr][sc], color);
        return image;
    }

    // 从矩阵 grid 的 (i, j) 位置开始进行深度优先搜索，将搜索到的值为 oldColor 的网格更新为 newColor。
    private void update(int[][] image, int i, int j, int oldColor, int newColor) {
        int m = image.length, n = image[0].length;
        image[i][j] = newColor;
        if (i + 1 < m && image[i + 1][j] == oldColor) {
            update(image, i + 1, j, oldColor, newColor);
        }
        if (j + 1 < n && image[i][j + 1] == oldColor) {
            update(image, i, j + 1, oldColor, newColor);
        }
        if (i - 1 >= 0 && image[i - 1][j] == oldColor) {
            update(image, i - 1, j, oldColor, newColor);
        }
        if (j - 1 >= 0 && image[i][j - 1] == oldColor) {
            update(image, i, j - 1, oldColor, newColor);
        }
    }
}