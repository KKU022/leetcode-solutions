class Solution {
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Memoization table for entering a mirror:
        // memo[r][c][dir] stores encoded destination (new_r * n + new_c), or -1 if out of bounds.
        // dir = 0 means entered moving Right
        // dir = 1 means entered moving Down
        int[][][] memo = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j][0] = -2;
                memo[i][j][1] = -2;
            }
        }

        // dp[r][c] = number of valid ways to arrive at empty cell (r, c)
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (dp[r][c] == 0) continue;

                long ways = dp[r][c];

                // 1. Move Right: goes to (r, c + 1)
                int destRight = getDestination(r, c + 1, 0, grid, m, n, memo);
                if (destRight != -1) {
                    int tr = destRight / n;
                    int tc = destRight % n;
                    dp[tr][tc] = (int) ((dp[tr][tc] + ways) % MOD);
                }

                // 2. Move Down: goes to (r + 1, c)
                int destDown = getDestination(r + 1, c, 1, grid, m, n, memo);
                if (destDown != -1) {
                    int tr = destDown / n;
                    int tc = destDown % n;
                    dp[tr][tc] = (int) ((dp[tr][tc] + ways) % MOD);
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    // Resolves where the robot lands when entering cell (r, c) moving in direction `dir`
    private int getDestination(int r, int c, int dir, int[][] grid, int m, int n, int[][][] memo) {
        // Out of bounds
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return -1;
        }

        // Reached an empty cell or target destination
        if (grid[r][c] == 0) {
            return r * n + c;
        }

        if (memo[r][c][dir] != -2) {
            return memo[r][c][dir];
        }

        int nextR = r;
        int nextC = c;
        int nextDir = dir;

        // Reflection rule:
        // - Entering while moving right (dir = 0) -> turn down (dir = 1), move to (r + 1, c)
        // - Entering while moving down (dir = 1)  -> turn right (dir = 0), move to (r, c + 1)
        if (dir == 0) {
            nextR = r + 1;
            nextDir = 1;
        } else {
            nextC = c + 1;
            nextDir = 0;
        }

        int res = getDestination(nextR, nextC, nextDir, grid, m, n, memo);
        memo[r][c][dir] = res;
        return res;
    }
}
