class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m =grid.length;
        int n =grid[0].length;
        int[][] prefixSum =new int[m][n];
        int[][] prefixX=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int val=0;
                if(grid[i][j]=='X') val = 1;
                else if(grid[i][j]=='Y') val = -1;
                else if(grid[i][j]=='.') val = 0;
                prefixSum[i][j]=val;
                if(i>0) prefixSum[i][j] += prefixSum[i-1][j];
                if(j>0) prefixSum[i][j] +=  prefixSum[i][j-1];
                if(i>0 && j>0) prefixSum[i][j] -=  prefixSum[i-1][j-1];
    
                prefixX[i][j] = (grid[i][j] == 'X') ? 1 : 0;
                if(i > 0) prefixX[i][j] += prefixX[i-1][j];
                if(j > 0) prefixX[i][j] += prefixX[i][j-1];
                if(i > 0 && j > 0) prefixX[i][j] -= prefixX[i-1][j-1];
            }
        }

        int count = 0;

            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(prefixSum[i][j] == 0 && prefixX[i][j] > 0){
                        count++;
                    }
                }
            }
            return count;
    }
}
