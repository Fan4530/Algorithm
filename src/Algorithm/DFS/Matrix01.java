package Algorithm.DFS;

/**
 * Created by program on 10/17/2017.
 */
public class Matrix01 {
    private int [][] dirs = new int[][]{{0,1}, {0,-1},{-1, 0},   {1, 0}};
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0][0];
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int [][] dp = new int[n][m];
        //dfs bottom to up
        //base case ; not valid
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if(matrix[i][j] != 0) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if(matrix[i][j] != 0) {
                    boolean [][] visited = new boolean[n][m];
                    dfs(dp, i, j, visited);
                }
            }
        }
        return dp;
    }
    private int dfs(int [][] dp, int i, int j, boolean [][] visited) {
        if(i < 0 || j < 0 || i >= dp.length || j >= dp[0].length ) {
            return Integer.MAX_VALUE;
        }
        if(visited[i][j]) {
            return dp[i][j];
        }

        visited[i][j] = true;
        for(int dir = 0; dir < 4; dir ++) {
            int val = dfs(dp, i + dirs[dir][0], j + dirs[dir][1], visited);
            dp[i][j] = val == Integer.MAX_VALUE ?  dp[i][j] : Math.min(dp[i][j], 1 + val );

        }
        return dp[i][j];
    }
    public static void main(String [] args) {
        Matrix01 sol = new Matrix01();
        System.out.println(sol.updateMatrix(new int[][]{{1,0}, {1,1}}));
    }
}
