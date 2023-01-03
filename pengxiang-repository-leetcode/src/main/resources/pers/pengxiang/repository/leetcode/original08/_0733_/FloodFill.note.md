## 733. 图像渲染

### 1. 深度优先搜索

```java
class Solution {
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
```

### 2. 广度优先搜索

```java
class Solution {
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
```

