class Solution {

    private int dist(int a, int b) {
        int d = Math.abs(a - b);
        return Math.min(d, 10 - d);
    }

    public int minRotations(int n, String s) {

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i) - '0';
        }

        // prefix[i] = cost to dial a[0] ... a[i]
        int[] prefix = new int[n];

        prefix[0] = dist(0, a[0]);

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + dist(a[i - 1], a[i]);
        }

        // Original answer (don't reverse)
        int answer = prefix[n - 1];

        // suffixCost = cost inside a[k] ... a[n-1]
        int suffixCost = 0;

        for (int k = n - 1; k >= 0; k--) {

            if (k < n - 1) {
                suffixCost += dist(a[k], a[k + 1]);
            }

            int cost;

            if (k == 0) {
                // Reverse entire string
                cost = dist(0, a[n - 1]) + suffixCost;
            } else {
                // Prefix: 0 -> a[0] -> ... -> a[k-1]
                int prefixCost = prefix[k - 1];

                // Then jump to the old last element
                cost = prefixCost
                        + dist(a[k - 1], a[n - 1])
                        + suffixCost;
            }

            answer = Math.min(answer, cost);
        }

        return answer;
    }
}
