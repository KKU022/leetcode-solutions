class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] represents min cost to reach step i
        // Since costs is 1-indexed in problem description (costs[0] corresponds to step 1)
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            int stepCost = costs[i - 1]; // 0-indexed array access for step i
            int minPrev = dp[i - 1] + 1; // jump from i - 1: k = 1, k^2 = 1

            if (i >= 2) {
                minPrev = Math.min(minPrev, dp[i - 2] + 4); // jump from i - 2: k = 2, k^2 = 4
            }
            if (i >= 3) {
                minPrev = Math.min(minPrev, dp[i - 3] + 9); // jump from i - 3: k = 3, k^2 = 9
            }

            dp[i] = stepCost + minPrev;
        }

        return dp[n];
    }
}
