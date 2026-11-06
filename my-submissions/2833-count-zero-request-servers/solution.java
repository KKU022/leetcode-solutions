class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        // Sort queries and logs by time
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int[] ans = new int[m];
        int l = 0, r = 0;
        int activeServersCount = 0;
        int[] serverFreq = new int[n + 1];

        for (int i = 0; i < m; i++) {
            int qTime = sortedQueries[i][0];
            int originalIdx = sortedQueries[i][1];
            int startTime = qTime - x;

            // Add logs that fall within or before the right window boundary (qTime)
            while (r < logs.length && logs[r][1] <= qTime) {
                int serverId = logs[r][0];
                if (serverFreq[serverId] == 0) {
                    activeServersCount++;
                }
                serverFreq[serverId]++;
                r++;
            }

            // Remove logs that fall outside the left window boundary (startTime)
            while (l < logs.length && logs[l][1] < startTime) {
                int serverId = logs[l][0];
                serverFreq[serverId]--;
                if (serverFreq[serverId] == 0) {
                    activeServersCount--;
                }
                l++;
            }

            // Zero-request servers = Total servers (n) - Active servers in window
            ans[originalIdx] = n - activeServersCount;
        }

        return ans;
    }
}
