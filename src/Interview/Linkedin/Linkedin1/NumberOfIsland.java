package Interview.Linkedin.Linkedin1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by program on 12/9/2017.
 */
public class NumberOfIsland {
    public static void main(String [] args) {
        NumberOfIsland sol = new NumberOfIsland();
        System.out.println(sol.numIslands(new int[][]{{1,1,1}, {0,0,0}, {1,1,1}}));
    }
    public  int numIslands(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for(int i = 0; i < grid.length; i ++) {
            for(int j = 0; j < grid[0].length; j ++) {
                if(grid[i][j] == 1) {
                    dfs1(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    private void dfs1(int [][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 ||  j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        for(int idx = 0; idx < directions.length; idx ++) {
            int [] dir = directions[idx];
            dfs1(grid, i + dir[0], j + dir[1]);
        }
    }

    //不同的island个数
    public int numDistinctIslands(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        HashSet<List<Integer>> set = new HashSet<>();
        for(int i = 0; i < grid.length; i ++) {
            for(int j = 0; j < grid[0].length; j ++) {
                if(grid[i][j] == 1) {
                    List<Integer> cur = new ArrayList<>();
                    dfs(grid, i, j, i, j, cur);
                    set.add(cur);
                }
            }
        }
        return set.size();
    }
    private int [][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private void dfs(int [][] grid, int i, int j, int marki, int markj, List<Integer> cur) {
        if(i < 0 || i >= grid.length || j < 0 ||  j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        cur.add(i - marki, j - markj);
        grid[i][j] = 0;
        for(int idx = 0; idx < directions.length; idx ++) {
            int [] dir = directions[idx];
            dfs(grid, i + dir[0], j + dir[1], marki, markj, cur);
        }
    }
}
