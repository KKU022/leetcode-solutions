class Solution {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;
        int[] first = new int[k];
        int[] last = new int[k];
        java.util.Arrays.fill(first, -1);
        java.util.Arrays.fill(last, -1);

        @SuppressWarnings("unchecked")
        java.util.ArrayList<Integer>[] pos = new java.util.ArrayList[k];
        for (int i = 0; i < k; i++)
            pos[i] = new java.util.ArrayList<>();

        int[] order = new int[k];
        int cnt = 0, sum = 0;

        first[0] = 0;
        order[cnt++] = 0;

        for (int i = 0; i < n; i++) {
            int x = ((nums[i] % k) + k) % k;
            pos[x].add(i);

            sum = (sum + x) % k;

            if (first[sum] == -1) {
                first[sum] = i + 1;
                order[cnt++] = sum;
            }
            last[sum] = i + 1;
        }

        int ans = 0;

        // No negation
        for (int r = 0; r < k; r++) {
            if (first[r] != -1)
                ans = Math.max(ans, last[r] - first[r]);
        }

        // Negate one element
        for (int x = 0; x < k; x++) {
            int p = 0;

            for (int z = 0; z < cnt; z++) {
                int a = order[z];

                while (p < pos[x].size() && pos[x].get(p) < first[a])
                    p++;

                if (p == pos[x].size())
                    break;

                int b = (a + 2 * x) % k;
                int right = last[b];

                if (right > pos[x].get(p))
                    ans = Math.max(ans, right - first[a]);
            }
        }

        return ans;
    }
}
