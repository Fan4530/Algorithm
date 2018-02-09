package Practical.liyi;

/**
 * Created by program on 12/6/2017.
 */
public class printHouse {
    public static int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int[] dp = new int[3];
        int[] pre = new int[3];
        // for(int i = 0; i < 3; i ++) {
        //     pre[i] = costs[0][i];
        // }
        for (int i = 0; i < costs.length; i++) {
            dp[0] = Math.min(pre[1], pre[2]) + costs[i][0];
            dp[1] = Math.min(pre[0], pre[2]) + costs[i][1];
            dp[2] = Math.min(pre[1], pre[0]) + costs[i][2];
            pre[0] = dp[0];
            pre[1] = dp[1];
            pre[2] = dp[2];
        }

        return Math.min(pre[2], Math.min(pre[0], pre[1]));
    }
    public static void main(String [] args) {
        int [][] input = new int[][]{{17,2,17},{16,16,5},{14,3,19}};
         System.out.println(minCost(input));
    }
}
