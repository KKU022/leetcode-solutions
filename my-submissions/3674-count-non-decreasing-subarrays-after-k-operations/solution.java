class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        int r = n - 1;
        
        // Deque stores pairs of {blockValue, countOfElementsInBlock}
        Deque<long[]> dq = new ArrayDeque<>();
        
        for (int l = n - 1; l >= 0; l--) {
            long val = nums[l];
            long count = 1;
            
            // Merge all smaller or equal blocks to the right into the new peak
            while (!dq.isEmpty() && dq.peekFirst()[0] <= val) {
                long[] front = dq.pollFirst();
                cost += (val - front[0]) * front[1];
                count += front[1];
            }
            dq.addFirst(new long[]{val, count});
            
            // Shrink window from the right until cost <= k
            while (cost > k) {
                long[] back = dq.peekLast();
                cost -= (back[0] - nums[r]);
                back[1]--;
                if (back[1] == 0) {
                    dq.pollLast();
                }
                r--;
            }
            
            // All subarrays nums[l..j] for l <= j <= r are valid
            ans += (r - l + 1);
        }
        
        return ans;
    }
}
