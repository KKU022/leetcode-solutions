class Solution {
    public void duplicateZeros(int[] arr) {
        int possZeroDups =  0;
        int lastIdx = arr.length -1 ;
        for(int i =0;i<=lastIdx-possZeroDups;i++){
            if(arr[i]==0){
                if(i == lastIdx-possZeroDups){
                    arr[lastIdx]=0;
                    lastIdx--;
                    break;
                }
                possZeroDups++;
            }
        }
        int newLastIdx = lastIdx - possZeroDups;
        for(int i =newLastIdx;i>=0;i--){
            if(arr[i]==0){
                arr[i+possZeroDups] = 0;
                possZeroDups--;
                arr[i+possZeroDups] = 0;
            } else{
                arr[i+possZeroDups] = arr[i];
            }
        }
    }
}
