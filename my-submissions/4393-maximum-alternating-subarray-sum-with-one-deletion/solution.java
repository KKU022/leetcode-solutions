class Solution {
    public long maxAlternatingSum(int[] nums) {

        long NEG = Long.MIN_VALUE / 4;

        // No deletion used
        long plus = nums[0];   // last element has '+'
        long minus = NEG;      // last element has '-'

        // One deletion already used, and we have selected an element
        long delPlus = NEG;
        long delMinus = NEG;

        // Current element is deleted.
        // These store the state BEFORE the deleted element.
        long pendingPlus = NEG;
        long pendingMinus = NEG;

        long ans = nums[0];

        for (int i = 1; i < nums.length; i++) {

            long oldPlus = plus;
            long oldMinus = minus;

            long oldDelPlus = delPlus;
            long oldDelMinus = delMinus;

            long oldPendingPlus = pendingPlus;
            long oldPendingMinus = pendingMinus;

            int x = nums[i];

            // No deletion
            plus = Math.max(x, oldMinus + x);
            minus = oldPlus - x;

            // Delete an earlier element, then take x
            delPlus = Math.max(
                    oldDelMinus + x,
                    oldPendingMinus + x
            );

            delMinus = Math.max(
                    oldDelPlus - x,
                    oldPendingPlus - x
            );

            // Delete the current element
            pendingPlus = oldPlus;
            pendingMinus = oldMinus;

            ans = Math.max(ans, plus);
            ans = Math.max(ans, minus);
            ans = Math.max(ans, delPlus);
            ans = Math.max(ans, delMinus);
        }

        return ans;
    }
}
