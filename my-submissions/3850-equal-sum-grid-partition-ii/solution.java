import java.util.*;

class Solution {

    private boolean valid(int rows, int cols, int r, int c, int r1, int c1, int r2, int c2){
        if(rows > 1 && cols > 1) return true;

        if(rows == 1){
            return c == c1 || c == c2;
        }

        if(cols == 1){
            return r == r1 || r == r2;
        }

        return false;
    }

    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long total = 0;
        for(int[] row : grid){
            for(int val : row) total += val;
        }

        // ---------- Horizontal ----------
        long topSum = 0;
        Map<Integer,Integer> topMap = new HashMap<>();
        Map<Integer,Integer> bottomMap = new HashMap<>();

        for(int[] row : grid){
            for(int val : row){
                bottomMap.put(val, bottomMap.getOrDefault(val,0)+1);
            }
        }

        for(int i=0;i<m-1;i++){

            for(int j=0;j<n;j++){
                int val = grid[i][j];
                topSum += val;

                topMap.put(val, topMap.getOrDefault(val,0)+1);
                bottomMap.put(val, bottomMap.get(val)-1);
                if(bottomMap.get(val)==0) bottomMap.remove(val);
            }

            long bottomSum = total - topSum;

            if(topSum == bottomSum) return true;

            long diff = Math.abs(topSum - bottomSum);

            if(topSum > bottomSum){
                if(topMap.containsKey((int)diff) && topSum - diff == bottomSum){

                    int rows = i+1, cols = n;

                    for(int r=0;r<=i;r++){
                        for(int c=0;c<n;c++){
                            if(grid[r][c]==diff &&
                               valid(rows, cols, r, c, 0, 0, i, n-1)){
                                return true;
                            }
                        }
                    }
                }
            } else {
                if(bottomMap.containsKey((int)diff) && bottomSum - diff == topSum){

                    int rows = m-(i+1), cols = n;

                    for(int r=i+1;r<m;r++){
                        for(int c=0;c<n;c++){
                            if(grid[r][c]==diff &&
                               valid(rows, cols, r, c, i+1, 0, m-1, n-1)){
                                return true;
                            }
                        }
                    }
                }
            }
        }

        // ---------- Vertical ----------
        long leftSum = 0;
        Map<Integer,Integer> leftMap = new HashMap<>();
        Map<Integer,Integer> rightMap = new HashMap<>();

        for(int[] row : grid){
            for(int val : row){
                rightMap.put(val, rightMap.getOrDefault(val,0)+1);
            }
        }

        for(int j=0;j<n-1;j++){

            for(int i=0;i<m;i++){
                int val = grid[i][j];
                leftSum += val;

                leftMap.put(val, leftMap.getOrDefault(val,0)+1);
                rightMap.put(val, rightMap.get(val)-1);
                if(rightMap.get(val)==0) rightMap.remove(val);
            }

            long rightSum = total - leftSum;

            if(leftSum == rightSum) return true;

            long diff = Math.abs(leftSum - rightSum);

            if(leftSum > rightSum){
                if(leftMap.containsKey((int)diff) && leftSum - diff == rightSum){

                    int rows = m, cols = j+1;

                    for(int r=0;r<m;r++){
                        for(int c=0;c<=j;c++){
                            if(grid[r][c]==diff &&
                               valid(rows, cols, r, c, 0, 0, m-1, j)){
                                return true;
                            }
                        }
                    }
                }
            } else {
                if(rightMap.containsKey((int)diff) && rightSum - diff == leftSum){

                    int rows = m, cols = n-(j+1);

                    for(int r=0;r<m;r++){
                        for(int c=j+1;c<n;c++){
                            if(grid[r][c]==diff &&
                               valid(rows, cols, r, c, 0, j+1, m-1, n-1)){
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
}
