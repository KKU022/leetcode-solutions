class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i =0;i<nums.length;i++){
            int current = nums[i];
            map.put(current,map.getOrDefault(current,0)+1);
            if(map.get(current)>n/2){
               return current;
            }
        }
        return -1;
    }
}
