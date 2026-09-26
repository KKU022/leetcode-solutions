class Solution {
    public boolean canTransform(int[] source, int[] target) {
        long sumSource=0;
        long sumTarget =0 ;
        for(int x:source){
            sumSource +=x;
        }
        for(int x:target){
            sumTarget+=x;
        }
        return sumSource == sumTarget;
    }
}
