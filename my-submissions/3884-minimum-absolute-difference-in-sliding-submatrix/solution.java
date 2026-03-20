class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m-k+1][n-k+1];
        for(int i=0;i<=m-k;i++){
          TreeMap<Integer,Integer>map=new TreeMap<>();
          for(int x=i;x<i+k;x++){
            for(int y=0;y<k;y++){
                add(map,grid[x][y]);
            }
          }
          for(int j=0;j<=n-k;j++){
            ans[i][j]=getMinDiff(map);
            if(j+k<n){
                for(int x=i;x<i+k;x++){
                    remove(map,grid[x][j]);
                }
                for(int x=i;x<i+k;x++){
                    add(map,grid[x][j+k]);
                }
            }
          }
         }
         return ans;
}
private void add(TreeMap<Integer, Integer> map, int val) {
    map.put(val, map.getOrDefault(val, 0) + 1);
}

private void remove(TreeMap<Integer, Integer> map, int val) {
    if (map.get(val) == 1) map.remove(val);
    else map.put(val, map.get(val) - 1);
}

private int getMinDiff(TreeMap<Integer, Integer> map) {
    Integer prev = null;
    int minDiff = Integer.MAX_VALUE;

    for (int num : map.keySet()) {
        if (prev != null) {
            minDiff = Math.min(minDiff, num - prev);
        }
        prev = num;
    }

    return minDiff == Integer.MAX_VALUE ? 0 : minDiff;
}}
