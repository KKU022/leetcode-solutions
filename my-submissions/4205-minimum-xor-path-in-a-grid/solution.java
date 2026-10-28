class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxXor = 1024;

        // dp[i][j][x] indicates whether XOR value x is reachable
        boolean[][][] dp = new boolean[m][n][maxXor];

        // Base case: starting cell
        dp[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];

                // Transition from top cell
                if (i > 0) {
                    for (int x = 0; x < maxXor; x++) {
                        if (dp[i - 1][j][x]) {
                            dp[i][j][x ^ val] = true;
                        }
                    }
                }

                // Transition from left cell
                if (j > 0) {
                    for (int x = 0; x < maxXor; x++) {
                        if (dp[i][j - 1][x]) {
                            dp[i][j][x ^ val] = true;
                        }
                    }
                }
            }
        }

        // Find the minimum reachable XOR at the bottom-right cell
        for (int x = 0; x < maxXor; x++) {
            if (dp[m - 1][n - 1][x]) {
                return x;
            }
        }

        return -1;
    }
}
