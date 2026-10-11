class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length ;
        for(int i =0;i<n;i++){
            for(int j =i;j<n-i-1;j++){
                int del = j-i;
                int curr = matrix[i][j];
                int next = matrix[i+del][n-i-1];
                matrix[i+del][n-i-1] = curr;
                curr = next;
                next=matrix[n-i-1][n-i-1-del];
                matrix[n-i-1][n-i-1-del]=curr;
                curr=next;
                next=matrix[n-i-1-del][i];
                matrix[n-i-1-del][i]=curr;
                curr=next;
                matrix[i][j]=curr;
            }
        }
    }
}
