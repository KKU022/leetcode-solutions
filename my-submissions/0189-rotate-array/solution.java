class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int count =0 ;
        for(int i =0;count<n;i++){
            int idx =i;
            int current = nums[idx];
            do{
            int next = nums[(idx+k)%n];
            nums[(idx+k)%n] = current;
            current = next;
            idx=(idx+k)%n;
            count++;

        } while(idx != i);
        }
        
    }
}
