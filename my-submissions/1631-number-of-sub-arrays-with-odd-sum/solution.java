class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        long totalOddSubarrays = 0;
        int prefixSum = 0;
        
        // Start with evenCount = 1 to account for prefixSum = 0 before index 0
        int evenCount = 1; 
        int oddCount = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 0) {
                // Current prefix sum is even; pair with previous odd prefix sums
                totalOddSubarrays = (totalOddSubarrays + oddCount) % MOD;
                evenCount++;
            } else {
                // Current prefix sum is odd; pair with previous even prefix sums
                totalOddSubarrays = (totalOddSubarrays + evenCount) % MOD;
                oddCount++;
            }
        }

        return (int) totalOddSubarrays;
    }
}
