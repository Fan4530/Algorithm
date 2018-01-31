package Interview.Uber;

import java.util.*;

/**
 * Created by program on 1/21/2018.
 */
public class WaterPool {
    // assumption: if ajacent has the same value, both will be traversed
    // dfs to the lowest points
    // 可以用point代替array， 然后重写hashcode 和equals
    public static void main(String [] args) {
        WaterPool sol = new WaterPool();
        int [][] grid = new int[][]{
                {0, 1, 4},
                {1, 7, 1},
                {1, 1, 0}};
        sol.waterPoll(grid, 1, 1);
        for(int [] e : set) {
            System.out.println(Arrays.toString(e));
        }
    }
    public void waterPoll(int [][] grid, int i, int j){
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return;
        }
        dfs(i, j, grid);

    }
    static Set<int []>  set = new HashSet<>();
    private int [][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    private void dfs(int i, int j, int [][] grid) {
        int min = grid[i][j];
        for(int k = 0; k < 4; k ++) {
            int newi = i + dirs[k][0];
            int newj = j + dirs[k][1];
            if(newi < grid.length && newi >= 0 && newj >= 0 && newj < grid[0].length) {
                min = Math.min(min, grid[newi][newj]);
            }
        }
        boolean flag = false;
        for(int k = 0; k < 4; k ++) {
            int newi = i + dirs[k][0];
            int newj = j + dirs[k][1];
            if(newi < grid.length && newi >= 0 && newj >= 0 && newj < grid[0].length) {
                if(grid[newi][newj] == min) {
                    flag = true;
                    dfs(newi, newj, grid);
                }
            }
        }
        if(!flag) {
            set.add(new int[]{i, j});
        }

    }
}
