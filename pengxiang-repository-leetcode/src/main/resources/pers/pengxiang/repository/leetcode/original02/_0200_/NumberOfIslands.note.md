## 200. 岛屿数量

### 1. 深度优先搜索

```java
class Solution {
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
```

### 2. 广度优先搜索

```java
class Solution {
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
```

### 3. 并查集

```java
class Solution {
    private int[] parent; // parent[i] 记录并查集中节点 i 的父节点。
    private int components; // 记录并查集中连通分量的数量。

    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        parent = new int[m * n];
        Arrays.fill(parent, -1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                components++;
                int idx = i * n + j;
                if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                    union(idx, idx - n);
                }
                if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                    union(idx, idx - 1);
                }
            }
        }
        return components;
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
        int h1 = -parent[root1];
        int h2 = -parent[root2];
        if (h1 < h2) {
            parent[root1] = root2;
        } else if (h1 > h2) {
            parent[root2] = root1;
        } else {
            parent[root2]--;
            parent[root1] = root2;
        }
        components--; // 合并成功则并查集中连通分量的数量 -1。
    }
}
```

