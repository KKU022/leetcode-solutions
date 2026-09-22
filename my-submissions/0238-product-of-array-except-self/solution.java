class Solution {
    public int[] productExceptSelf(int[] nums) {
        int answer[] = new int[nums.length];
        int leftProduct =1 ;
        for(int i = 0;i<nums.length;i++){
            answer[i] = leftProduct;
            leftProduct *= nums[i];
        }
        int rightProduct = 1;
        for(int j=nums.length-1 ; j >=0;j--){
            answer[j] = answer[j] *rightProduct;
            rightProduct *= nums[j];
        }
        return answer;
    }
}
