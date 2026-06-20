class Solution {
    public int divide(int dividend, int divisor) {
        // Special case: overflow handling
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the result
        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        // Convert both to negative numbers to prevent overflow
        int negDividend = dividend < 0 ? dividend : -dividend;
        int negDivisor = divisor < 0 ? divisor : -divisor;

        int quotient = 0;

        // Since both are negative, we check if negDividend <= negDivisor
        // (e.g., -10 <= -3 is true, meaning we can still divide)
        while (negDividend <= negDivisor) {
            int tempDivisor = negDivisor;
            int count = 1;

            // Double the divisor until it exceeds the dividend
            // Check tempDivisor >= Integer.MIN_VALUE >> 1 to prevent bit overflow
            while (tempDivisor >= (Integer.MIN_VALUE >> 1) && negDividend <= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                count <<= 1;
            }

            // Subtract the accumulated divisor from dividend
            negDividend -= tempDivisor;
            // Add the accumulated count to the quotient
            quotient += count;
        }

        return isNegative ? -quotient : quotient;
    }
}
