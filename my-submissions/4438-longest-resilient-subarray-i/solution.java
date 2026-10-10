class Solution {
    public int resilientSubarray(int[] nums, int k) {
        int n = nums.length;
        int ans = 1;
        int i = 0;

        while (i < n) {
            int rem = Math.floorMod(nums[i], k);
            int j = i;

            while (j < n && Math.floorMod(nums[j], k) == rem) {
                j++;
            }

            int len = j - i;

            if (rem == 0) {
                ans = Math.max(ans, len);
            } else {
                int period = k / gcd(rem, k);

                // Largest length <= len that is 1 modulo period.
                int validLen = len >= 1
                    ? 1 + ((len - 1) / period) * period
                    : 0;

                ans = Math.max(ans, validLen);
            }

            i = j;
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
