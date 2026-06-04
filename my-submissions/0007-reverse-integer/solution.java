class Solution {
    public int reverse(int x) {
        int n=0;
        while(x!=0) {
            int np=x%10;
             if (n > Integer.MAX_VALUE / 10 ||(n == Integer.MAX_VALUE / 10 && np> 7)) {
                return 0;
            }
            if (n < Integer.MIN_VALUE / 10 ||(n == Integer.MIN_VALUE / 10 && np < -8)) {
                return 0;
            }
            n=(n*10)+np;
            x=x/10;
        }
        return n;

    }
}
