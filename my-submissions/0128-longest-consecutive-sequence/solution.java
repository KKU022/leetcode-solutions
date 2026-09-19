class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int longest =0 ;
        for(int num:nums){
            set.add(num);
        }
        for(int num:set){
            if(!(set.contains(num-1))){
                int currentLength =1 ;
                int current = num;
                while(set.contains(current+1)){
                    current++;
                    currentLength++;
                }
                longest = Math.max(longest, currentLength);
            }
        }  
        return longest;
    }
}
