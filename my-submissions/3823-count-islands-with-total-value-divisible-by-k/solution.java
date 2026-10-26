import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long islandSum = 0;
                    Queue<int[]> queue = new ArrayDeque<>();

                    queue.offer(new int[]{i, j});
                    islandSum += grid[i][j];
                    grid[i][j] = 0; // Mark visited

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int r = curr[0];
                        int c = curr[1];

                        for (int[] dir : directions) {
                            int nr = r + dir[0];
                            int nc = c + dir[1];

                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                                islandSum += grid[nr][nc];
                                grid[nr][nc] = 0; // Mark visited
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }

                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
