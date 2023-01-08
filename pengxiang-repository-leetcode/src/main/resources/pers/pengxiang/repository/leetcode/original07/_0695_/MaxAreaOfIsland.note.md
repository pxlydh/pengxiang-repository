## 695. 岛屿的最大面积

### 1. 深度优先搜索

```java
class Solution {
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
```

### 2. 广度优先搜索

```java
class Solution {
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
```

### 3. 并查集

```java
class Solution {
    private int[] parent; // parent[i] 记录并查集中节点 i 的父节点。

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
```

