class Solution {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length, ans = 0;
        int[] p = new int[n + 1];

        for (int i = 0; i < n; i++)
            p[i + 1] = (int)(((long)p[i] + nums[i]) % k + k) % k;

        int[] first = new int[k];
        java.util.Arrays.fill(first, -1);
        first[0] = 0;

        for (int i = 1; i <= n; i++) {
            if (first[p[i]] >= 0)
                ans = Math.max(ans, i - first[p[i]]);
            else
                first[p[i]] = i;
        }

        int[] next = new int[n + 1];
        int[] last = new int[k];
        java.util.Arrays.fill(last, -1);

        for (int i = n; i >= 0; i--) {
            next[i] = last[p[i]];
            last[p[i]] = i;
        }

        int[] left = new int[k];
        int[] right = new int[k];
        java.util.Arrays.fill(left, -1);
        java.util.Arrays.fill(right, -1);

        left[p[0]] = 0;

        for (int i = 0; i <= n; i++)
            right[p[i]] = i;

        for (int t = 0; t < n; t++) {
            int d = (int)(((2L * nums[t]) % k + k) % k);

            for (int a = 0; a < k; a++) {
                if (left[a] != -1) {
                    int b = (a + d) % k;

                    if (right[b] != -1)
                        ans = Math.max(ans, right[b] - left[a]);
                }
            }

            int q = t + 1;

            if (right[p[q]] == q)
                right[p[q]] = next[q];

            if (left[p[q]] == -1)
                left[p[q]] = q;
        }

        return ans;
    }
}
