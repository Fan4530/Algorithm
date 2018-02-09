package Practical.fineb;

import java.util.*;

/**
 * Created by program on 1/21/2018.
 */
public class WaterPool {
    //输入是一个二维 int 矩阵和一个起点坐标，矩阵值代表柱子高度，然后从起始点往下浇水，
    //水会流向它四周最低的那个方向，问水最后会停在哪里。
    //我用的BFS，交流过程中面试官不断要求我给出自己的 assumption。
    // assumption:
    // 问题1: 四周都是相同的高度， 往哪里流？
    // 问题2: 如果有两个停留点，怎么办？

    public static void main(String [] args) {
        String str = "abcd";

        WaterPool sol = new WaterPool();
        int [][] grid = new int[][]{
                {0, 1, 4},
                {1, 7, 1},
                {1, 1, 0}};
            //是不是同时输出两个零的位置
        int [][] grid2 = new int[][]{
                {1, 3, 1},
                {3, 7, 4},
                {1, 4, 0}
        }; //  是输出 三个1的位置呢，还是输出0的位置呢？ 我是输出三个1的位置
        int [][] grid3 = new int[][]{
                {0, 3, 2},
                {3, 7, 3},
                {3, 3, 1}
        }; //  这个是不是只输出0？ 我的是
        sol.waterPoll(grid3, 1, 1);

    }
    class Point {
        int i;
        int j;
        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    private int [][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public void waterPoll(int [][] grid, int i, int j){
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return;
        }
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(i, j));

        boolean [][] visited = new boolean[grid.length][grid[0].length];
        visited[i][j] = true;// deduplicate, 免得一个点被访问两次，而输出两个一样的结果

        int curMin = Integer.MAX_VALUE;//cur min value, prunning

        List<Point> res = new ArrayList<>();// result, it should be the last level
        while(!q.isEmpty()) {
            int size = q.size();
            List<Point> cur = new ArrayList<>();// current level
            for(int k = 0; k < size; k++) {
                Point p = q.poll();
                cur.add(p);

                for(int [] dir : dirs) {
                    int newi = p.i + dir[0];
                    int newj = p.j + dir[1];
                    //1. 不能NPE  valid来决定
                    //2. 不能重复
                    //3: 要访问的点要同时小于本次这个点和curMin
                    if(valid(newi, newj, grid) && !visited[newi][newj] && grid[newi][newj] <= grid[p.i][p.j] && grid[newi][newj] <= curMin ) {
                        curMin = grid[newi][newj];
                        visited[newi][newj] = true;
                        q.offer(new Point(newi, newj));
                    }
                }
            }
            //防止比如有一层是 4 3 3 3, 先访问了4， 所以要除去比curMin大的点
            while(!q.isEmpty() && grid[q.peek().i][q.peek().j] > curMin) {
                q.poll();
            }
            //update the result
            res = cur;
        }
        for(Point p : res) {
            System.out.println(p.i + " " + p.j);
        }

    }
    private boolean valid(int i, int j, int [][] grid) {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length;

    }

}
