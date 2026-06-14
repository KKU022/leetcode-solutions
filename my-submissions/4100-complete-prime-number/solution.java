class Solution {
    public boolean completePrime(int num) {
        String s = Integer.toString(num);
        int n = s.length();

        for(int i=1;i<=n;i++){
            int prefix = Integer.parseInt(s.substring(0,i));
            if(!isPrime(prefix)){
                return false;
            }
        }
        for(int i=0;i<n;i++){
            int suffix = Integer.parseInt(s.substring(i));
            if(!isPrime(suffix)){
                return false;
            }
        }
        return true;
    }
    private boolean isPrime(int n){
        if(n<=1) return false;
        if(n==2 || n==3) return true;
        if(n%2 == 0 || n%3 ==0) return false;
        for(int i=5;i*i<=n;i+=6){
            if(n%i == 0 || n%(i+2) == 0){
                return false;
            }
        }

        return true ;
    }
}
