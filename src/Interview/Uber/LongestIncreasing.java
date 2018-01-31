package Interview.Uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by program on 1/30/2018.
 */


//329. Longest Increasing Path in a Matrix
//这种题目的套路就是:
// memorization就是用hashmap或者int [] [] 做hash，自己看看哪个方便
//Follow up : 如果要输出path
    // 那就用hashmap记录下每个点的路径， 很简单，全是一样的套路
public class LongestIncreasing {
    class Point {
        int i;
        int j;
        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    public List<Point> longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        List<Point> res = new ArrayList<>();
        HashMap<Point, List<Point>> map = new HashMap<>();
        for(int i = 0; i < matrix.length; i ++) {
            for(int j = 0; j < matrix[0].length; j ++) {
                List<Point> tmp = dfs(map, matrix, i, j);
                if(tmp.size() > res.size()) {
                    res = tmp;
                }
            }
        }
        return res;
    }

    private int [][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private List<Point> dfs(HashMap<Point, List<Point>> map, int [][] matrix, int i, int j) {
        Point p = new Point(i, j);
        if(map.containsKey(p)) {
            return map.get(p);
        }

        List<Point> nextRes = new ArrayList<>();
        for(int [] d : dirs) {
            int a = i + d[0];
            int b = j + d[1];
            if(a < matrix.length && a >= 0 && b < matrix[0].length && b >= 0 && matrix[i][j] < matrix[a][b]) {
                List<Point> tmp = dfs(map, matrix, a, b);
                if(tmp.size() > nextRes.size()) {
                    nextRes = tmp;
                }
            }
        }
        nextRes.add(p);
        map.put(p, nextRes);
        return nextRes;
    }
    public static void main(String [] agrs) {
        LongestIncreasing sol = new LongestIncreasing();
        int [][] input = new int[][]{
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        };
        List<Point> res = sol.longestIncreasingPath(input);
        for(Point p : res) {
            System.out.println(input[p.i][p.j]);
        }
    }
}
