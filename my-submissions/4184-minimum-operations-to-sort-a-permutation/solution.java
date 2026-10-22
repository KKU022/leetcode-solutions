class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int count = 0;
        for(int i = 0;i<n;i++){
            if(nums[i]==0){
                count = i;
                break;
            }
        }
        int ans = Integer.MAX_VALUE;
        boolean inc = true,
                dec = true;
        for(int i = 0; i<n;i++){
            int curr = (count+i)%n;
            if(nums[curr]!= i){
                inc = false;
            }
            curr = (count-i+n)%n;
            if(nums[curr]!=i){
                dec = false;
            }
        }
        if(!inc && !dec){
            return -1;
        } 
        if(inc){
            ans = Math.min(ans,count);
            ans = Math.min(ans, n-count+2);
        }
        if(dec){
            ans = Math.min(ans,count+2);
            ans = Math.min(ans,n-count);
        }
        return ans == Integer.MAX_VALUE ? -1 :ans;
    }
}
