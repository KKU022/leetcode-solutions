class Solution {
    public int findMaxLength(int[] nums) {
       HashMap<Integer,Integer> map = new HashMap<>();
       map.put(0,-1);
       int prefixSum = 0;
       int maxLength=0; 
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0){
                nums[i]=-1;
            }
            prefixSum += nums[i];
            if (map.containsKey(prefixSum)) {
                maxLength = Math.max(maxLength,i - map.get(prefixSum));
            } else {
                map.put(prefixSum,i);
            }
        }
        return maxLength;
    }
}
