class Solution {
    public int heightChecker(int[] heights) {
        Integer[] expected = new Integer[heights.length];
        int count = 0 ;

        for(int i = 0;i<heights.length;i++){
            expected[i] = heights[i];
        }
        Arrays.sort(expected);
        for(int i=0;i<heights.length;i++){
            if(heights[i]!=expected[i]){
                count ++;
            }
        }
        return count;
    }
}
