class Solution {
    public int[] productExceptSelf(int[] nums) {
        int answer[] = new int[nums.length];
        int product =1 ;
        // Left Index fetch 
        int left[] = new int[nums.length];
        left[0]=1;
        for(int i =1;i<nums.length;i++){
            left[i]= left[i-1] * nums[i-1];
        } 
        // Right Index fetch 
        int right[]= new int[nums.length];
        right[nums.length-1] =1 ;
        for(int j = nums.length -2 ; j>=0;j--){
            right[j]=right[j+1]*nums[j+1];
        }
        for(int z = 0 ; z< nums.length ; z++){
             answer[z] = left[z]*right[z];
        }
        return answer ; 
    }
}
