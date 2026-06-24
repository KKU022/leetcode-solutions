class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int answer = nums[0];
        for(int i =1;i< nums.length;i++){
            int num = nums[i];
            int tempMax= max;
            max = Math.max(num,Math.max(num*max,num*min));
            min = Math.min(num,Math.min(num*tempMax,num*min));
            answer = Math.max(answer,max);
        }
        return answer;
    }
}
