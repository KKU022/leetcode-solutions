class Solution {
    public int minOperations(String s) {
        int n = s.length();
        // Change of the string to array for the sorting purpose 
        char[] sortedArr = s.toCharArray();
        Arrays.sort(sortedArr);
        String sortedS = new String(sortedArr);
        // If string is already been sorted then no need for operation 
        if(s.equals(sortedS)){
            return 0;
        }
        // If string is less then or equal to 2 characters then it is impossible to handle 
        if(n<=2){
            return -1;
        }
        // like a starting or ending character is already a min or max , then only 1 opertion will be undertaken ACRD -> ACDR (1 operation) or CDAR -> ACDR (1 operation)
        char mn = sortedS.charAt(0);
        char mx = sortedS.charAt(n-1);
        if(s.charAt(0)==mn || s.charAt(n-1)==mx){
            return 1;
        }
        // now checking that if both min and max are at different ends like min at max and vice versa then 3 operations will be required , and we will check by min mid and max mid RCDA -> CDRA -> CADR -> ACDR (3 operations)
        char mm = sortedS.charAt(1);
        char mxm = sortedS.charAt(n-2);
        if(s.charAt(0)>mxm && s.charAt(n-1)<mm){
            return 3;
        }
        // Otherwise 2 
        return 2;

    }
}
