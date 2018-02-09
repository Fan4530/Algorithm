package Practical.Jingchi;

/**
 * Created by program on 11/27/2017.
 */
public class coins {
    public static double naiv(double [] coins, int n) {
        //res[0][0]  0 coins, 0 header
        //res[i][j]  i coins, j header, i <= coins.length
        double [][] res = new double[coins.length + 1][n + 1];
        res[0][0]  = 1;
        for(int i = 1; i <= coins.length; i ++) {
            for(int j = 0; j <= n; j ++) {
                res[i][j] = res[i - 1][j] * (1 - coins[i - 1]) + (j > 0 ? res[i - 1][j - 1] * coins[i - 1] : 0);
            }
        }
        return res[coins.length][n];
    }
    public static double coins(double [] coins, int n) {
        //i: ith coin
        //j:
        if(n > coins.length) {
            return 0;
        }
        int header = Math.min(n, coins.length);
        double [] res = new double[header + 1];
        res[0] = 1;
        for(int i = 0; i < coins.length; i ++) {
            for(int j = header; j >= 0; j --) {
                res[j] = res[j] * (1 - coins[i]) + (j > 0 ? res[j - 1] * coins[i] : 0);
            }
        }
        return res[header];
    }
    public static void main(String [] args) {
        System.out.println(coins(new double[]{0.4, 0.5, 0.4}, 0));
    }
}
