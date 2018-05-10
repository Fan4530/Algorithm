package PracticeInterview.Google;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// leetcode 没有：
// 判断一个矩阵里面的true能否构成一个矩形
// {true, false, false, true},
// {true, false, false, false},
// {true, false, false, true}
// 会return true， 因为四个顶点是true。
//
public class FourPointsRectangle {
    public static void main(String [] agrs) {
        boolean [][] matrix = new boolean[][]{{true, false, false, true}, {true, false, false, false}, {true, false, false, true}};
        System.out.println(isRectangle(matrix));
    }
    public static boolean isRectangle(boolean [][] matrix) {
        Set<List<Integer>> set = new HashSet<>();
        int rows = matrix.length;
        int cols = matrix[0].length;

        for(int i = 0; i < rows; i ++) {
            for(int j = 0; j < cols; j ++) {
                for(int k = j + 1; k < cols; k ++) {
                    if(matrix[i][j] && matrix[i][k]) {
                        if(!set.add(Arrays.asList(j, k))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

}
