import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        
        // Fill the entire grid with obstacles
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }
        
        // Form an "L-shaped" path:
        // 1. Move right across the first row: (0, 0) to (0, n - 1)
        for (int j = 0; j < n; j++) {
            grid[0][j] = '.';
        }
        
        // 2. Move down the last column: (0, n - 1) to (m - 1, n - 1)
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }
        
        // Convert rows to String array
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}
