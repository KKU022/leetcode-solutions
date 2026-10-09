class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i =0;i<n/2;i++){
            for(int j = i;j<n-i-1;j++){
                //cycle
                //T:O(n^2) , S:O(1)
                int del = j-i;
                int curr = matrix[i][j];
                int next = matrix[i+del][n-i-1];
                
                matrix[i+del][n-i-1]=curr;
                curr = next;
                
                next=matrix[n-i-1][n-i-1-del];
                matrix[n-i-1][n-i-1-del]=curr;
                curr=next;
                
                next=matrix[n-i-1-del][i];
                matrix[n-i-1-del][i]=curr;
                curr=next;

                matrix[i][j] = curr;
            }
        }
    }
}
