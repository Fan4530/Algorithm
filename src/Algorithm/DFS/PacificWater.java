package Algorithm.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by program on 10/17/2017.
 */
public class PacificWater {
    private int [][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        boolean [][] pa = new boolean[n][m];
        boolean [][] ta = new boolean[n][m];
        for(int i = 0; i < n ; i ++) {
            for(int j = 0; j < m; j ++) {
                if(i == 0 || j == 0) {
                    dfs(i, j, pa, matrix, 0);
                }
                if(i == n - 1 || j == m - 1) {
                    dfs(i, j, ta, matrix, 0);
                }
            }
        }

        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if(pa[i][j] && ta[i][j]) {

                    res.add(new int[]{i, j});
                }
            }
        }
        return res;


    }
    private void dfs(int i, int j, boolean [][] res, int [][] matrix, int pre) {
        int n = matrix.length, m = matrix[0].length;
        if (i < 0 || i >= n || j < 0 || j >= m || res[i][j] || matrix[i][j] < pre)
            return;
        res[i][j] = true;
        for(int dir = 0; dir < 4; i ++) {
            dfs(i + dirs[dir][0], j + dirs[dir][1], res, matrix, matrix[i][j]);
        }
        // dfs(i + 1, j, matrix, visited, matrix[i][j], );
        // dfs( i - 1, j, matrix, visited, matrix[i][j],);
        // dfs(i, j + 1, matrix, visited, matrix[i][j], );
        // dfs(matrix, visited, matrix[i][j], i, j - 1);
    }
    public static void main(String [] args) {
        PacificWater sol = new PacificWater();
        sol.pacificAtlantic(new int[][]{{1,2,2,3,5}, {3,2,3,4,4}, {2,4,5,3,1}, {6,7,1,4,5}, {5,1,1,2,4}});
    }
}
