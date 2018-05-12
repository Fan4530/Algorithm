package PracticeInterview.Google.medium;





//
//      1   1   1   h = 1   i = 0    i < h - 1
//      -----------------
//      1   1   1   h = 2   i = 1    i == h - 1
//      1   1   1   h = 3
//
//
//
//      0   0   1   3   9
//      0   1   2   6   16
//      1   1   3   7   19
//      -----------------
//      0   1   2   6   16
//      0   0   1   3   9
//
//      0   0   0   0   0
//      0   0   0   0   0
//      0   0   0   0   X
//      -----------------
//      0   1   0   0   0
//      0   0   0   0   0
//
//
//
public class Ways {
    public static void main(String [] agrs) {
        System.out.println(sol(6, 5,2,0,2,4,3));
    }
    public static int sol(int n, int m, int si, int sj, int ei, int ej, int h) {
        if(n <= 0 || m <= 0) {
            return 0;
        }
        if(n == 1 && h <= 1) {
            return h <= 1 ? 1 : 0;
        }
        int [][] dp1 = new int[n][m];
        int [][] dp2 = new int[n][m];
        dp1[si][sj] = 1;
        for(int j = 1; j < m; j ++) {
            //j is jth line, no 0, so actually  i = h - 1 - j + 1
            for(int i = 0; i < n; i ++) {
                if(i == 0) {
                    dp1[i][j] += dp1[i + 1][j - 1] + dp1[i][j - 1];
                } else if(i == n - 1) {
                    dp1[i][j] += dp1[i - 1][j - 1] + dp1[i][j - 1];
                } else {
                    dp1[i][j] += dp1[i + 1][j - 1] + dp1[i][j - 1] + dp1[i - 1][j - 1];
                }
            }
        }
        for(int j = 1; j < m; j ++) {
            for(int i = 0; i < n; i ++) {
                if(i == 0) {
                    dp2[i][j] += getVal(dp1, dp2, i + 1, j - 1, h) + getVal(dp1, dp2, i, j - 1, h);
                } else if(i == n - 1) {
                    dp2[i][j] += getVal(dp1, dp2, i - 1, j - 1, h) + getVal(dp1, dp2, i, j - 1, h);
                } else {
                    dp2[i][j] += getVal(dp1, dp2, i + 1, j - 1, h) + getVal(dp1, dp2, i, j - 1, h) + getVal(dp1, dp2, i - 1, j - 1, h);
                }
            }
        }
        return dp2[ei][ej];
    }
    private static int getVal(int [][] dp1, int [][]dp2,  int i, int j, int h) {
        //h = 1, 2, 3, 4,   not start from 0
        if(i < h) {
            return dp2[i][j];
        } else {
            return dp1[i][j];
        }
    }
//    public static int sol(int n, int m, int si, int sj, int ei, int ej, int h) {
//        if(n <= 0 || m <= 0) {
//            return 0;
//        }
//        if(n == 1 && h <= 1) {
//            return h <= 1 ? 1 : 0;
//        }
//        if(si == ei && sj == ej) {
//            return 1;
//        }
//        int [][] dp = new int[n][m];
//        dp[si][sj] = 1;
//
//        dp(dp, n, m);
//        for(int i = 0; i < h; i ++) {
//            for(int j = 0; j < m; j ++) {
//                dp[i][j] = 0;
//            }
//        }
//        dp(dp, n, m);
//        return dp[ei][ej];
//
//
//    }
//    public static void dp(int [][] dp, int n, int m) {
//        for(int j = 1; j < m; j ++) {
//            //j is jth line, no 0, so actually  i = h - 1 - j + 1
//            for(int i = 0; i < n; i ++) {
//                if(i == 0) {
//                    dp[i][j] += dp[i + 1][j - 1] + dp[i][j - 1];
//                } else if(i == n - 1) {
//                    dp[i][j] += dp[i - 1][j - 1] + dp[i][j - 1];
//                } else {
//                    dp[i][j] += dp[i + 1][j - 1] + dp[i][j - 1] + dp[i - 1][j - 1];
//                }
//            }
//        }
//    }
}
