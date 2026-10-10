class Solution {
    public int resilientSubarray(int[] nums, int k) {
        int n = nums.length;
        int ans = 1;
        int i = 0;

        while (i < n) {
            int rem = Math.floorMod(nums[i], k);
            int j = i + 1;

            // Find the longest contiguous run with the same remainder.
            while (j < n && Math.floorMod(nums[j], k) == rem) {
                j++;
            }

            int len = j - i;

            if (rem == 0) {
                // Every element is divisible by k.
                ans = Math.max(ans, len);
            } else {
                // A valid length L must satisfy:
                // (L - 1) * rem % k == 0
                int g = gcd(rem, k);
                int period = k / g;

                // Largest valid length <= len.
                int validLen = 1 + ((len - 1) / period) * period;

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
