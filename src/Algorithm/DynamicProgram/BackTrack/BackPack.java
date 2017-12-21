package Algorithm.DynamicProgram.BackTrack;

/**
 * Created by program on 10/23/2017.
 */
public class BackPack {
    // P1: what is the maximum weight you can get?
    // base case:
    // M[0][0] = true
    // M[i][j] represents get first i items, if you can reach to j weights
    //              select          not select
    // M[i][j] = M[i - 1][j] || M[i][j - w[i]]

    // cannot use one array. if use one array, it is the problem about that we can get multiple items
    public int maxWeight(int [] w, int W) {
        int globalMax = 0;
        boolean [] pre = new boolean[W + 1];
        pre[0] = true;
        for(int i = 0; i < w.length; i ++) {
            //be careful,must has this one
            boolean [] dp = new boolean[W + 1];
            for(int j = 0; j <= W; j ++) {
                dp[j] = (j >= w[i]) ? pre[j - w[i]] || pre[j] : pre[j];// avoid npe
                globalMax = (dp[j] && j > globalMax) ? j : globalMax;
            }
            // be careful, it is a reference!
            pre = dp;  //wrong without new dp array
        }
        return globalMax;
    }
    //P2: how many ways to get W weight
    //M[0][0] = 1
    //M[0][j] = 0  j != 0
    //M[i][j] = M[i][j - w[i]] +            select
    //          M[i - 1][j]            not select
    public int ways(int [] w, int W) {
        int [] pre = new int[W + 1];

        pre[0] = 1;
        // skip the row that has no items, it has been initialized by pre
        for(int i = 0; i < w.length; i ++) {
            int [] dp = new int[W + 1];
            for(int j = 0; j <= W; j ++) {
                dp[j] = (j >= w[i]) ? pre[j] + pre[j - w[i]] : pre[j];
            }
            pre = dp;
        }
        return pre[W];
    }
    //P3: if want to get the minimum items number to get the weight 8
    //M[i][j] the minimum number of items that we can use first i items to get j weight. If we cannot get item j, then -1
    //base case
    //M[0][0] = 0, we can use 0 item to get weight 8
    //M[0][j] = -1, j != 0  we cannot use 0 items to get any weight
    //M[i][j] = min(M[i - 1][j],                 if not select, then the item number remain same
    //              M[i][j - w[i]] + 1);         if select, then items number plus 1.
    public int minimumItemNumber(int [] w, int W) {
        int [] pre = new int[W + 1];
        pre[0] = 0;
        for(int i = 1; i <= W; i ++) {
            pre[i] = -1;
        }
        for(int i = 0; i < w.length; i ++) {
            int [] dp = new int[W + 1];
            for(int j = 0; j <= W; j ++) {
                dp[j] = pre[j];// be careful
                // select       1     -1     1      -1
                // noselect     -1     1     -1     -1
                if(j >= w[i]) {
                    if(pre[j] != -1 && pre[j - w[i]] != -1) {
                        dp[j] = Math.min(pre[j], pre[j - w[i]] + 1);
                    } else {
                        dp[j] = pre[j - w[i]] != -1 ? pre[j - w[i]] + 1 : pre[j];

                    }
                }
            }
            pre = dp;
        }
        return pre[W];
    }
    //P4: if has value
    //M[i][j] represent for first i, get weight j, the max value.
    //you don't have to get W, that is ; e.g    w={1}  value = {3}, W = 5, res = 3
    //M[i][j] = max(M[i - 1][j], M[i][j - w[i]] + v[i])
    //base case: M[0][0] = 0  M[0][j] = -1
    public int maxValue(int [] w, int [] value, int W) {
        int [] pre = new int[W + 1];
        for(int i = 0; i < w.length; i ++) {
            int [] dp = new int[W + 1];
            for(int j = 0; j <= W; j ++) {
                dp[j] = j >= w[i] ? Math.max(pre[j], pre[j - w[i]] + value[i]) : pre[j];
            }
            pre = dp;
        }
        return pre[W];
    }
    //P4.1 if can only reach to W
    public int maxValue2(int [] w, int [] value, int W) {
        int [] pre = new int[W + 1];
        for(int i = 1; i <= W; i ++) {
            pre[i] = -1;
        }
        for(int i = 0; i < w.length; i ++) {
            int [] dp = new int[W + 1];
            for(int j = 0; j <= W; j ++) {
                dp[j] = pre[j];
                if(j >= w[i]) {
                    if(pre[j] != -1 && pre[j - w[i]] != -1) {
                        dp[j] = j >= w[i] ? Math.max(pre[j], pre[j - w[i]] + value[i]) : pre[j];
                    } else {
                        dp[j] = pre[j - w[i]] != -1 ? pre[j - w[i]] + value[i]: pre[j];
                    }
                }
            }
            pre = dp;
        }
        return pre[W];
    }

    //P5: if can multiple get on the base of P4
    //M[i][j] = M[j - 1][j] + M[j - w[i]][j]
    public int multipleItemValue(int [] w, int [] value, int W) {
        int [] dp = new int[W + 1];
        for(int i = 0; i < w.length; i ++) {
            for(int j = 0; j <= W; j ++) {
                dp[j] = j >= w[i] ? Math.max(dp[j], value[i] + dp[j - w[i]]) : dp[j];
            }
        }
        return dp[W];
    }
    public static void main(String [] args) {
        BackPack sol = new BackPack();
       // System.out.println(sol.maxWeight(new int[]{2,3,5}, 8));
       // System.out.println(sol.maxWeight(new int[]{}, 8));
        System.out.println(sol.maxWeight(new int[]{2,3,4}, 8));
        System.out.println(sol.maxWeight(new int[]{1,3,6}, 8));
        System.out.println();
        System.out.println(sol.ways(new int[]{2,3,5}, 5));
        System.out.println(sol.ways(new int[]{}, 5));
        System.out.println(sol.ways(new int[]{2,3,4}, 5));
        System.out.println(sol.ways(new int[]{1,3,6}, 5));
        System.out.println();
        //System.out.println(sol.minimumItemNumber(new int[]{2}, 2));
        System.out.println(sol.minimumItemNumber(new int[]{2,3}, 5));
        System.out.println(sol.minimumItemNumber(new int[]{2,3,5}, 5));
        System.out.println(sol.minimumItemNumber(new int[]{}, 5));
        System.out.println(sol.minimumItemNumber(new int[]{2,3,4}, 5));
        System.out.println(sol.minimumItemNumber(new int[]{1,3,6}, 5));
        System.out.println();
        System.out.println(sol.maxValue(new int[]{2}, new int[]{4}, 2));
        System.out.println(sol.maxValue(new int[]{2,3}, new int[]{1,2}, 5));
        System.out.println(sol.maxValue(new int[]{2,3,5}, new int[]{2,3,10}, 5));
        System.out.println(sol.maxValue(new int[]{}, new int[]{}, 5));
        System.out.println(sol.maxValue(new int[]{2,3,4}, new int[]{1,2,3}, 5));
        System.out.println(sol.maxValue(new int[]{1}, new int[]{2}, 5));
        System.out.println();
        System.out.println(sol.maxValue2(new int[]{2}, new int[]{4}, 2));
        System.out.println(sol.maxValue2(new int[]{2,3}, new int[]{1,2}, 5));
        System.out.println(sol.maxValue2(new int[]{2,3,5}, new int[]{2,3,10}, 5));
        System.out.println(sol.maxValue2(new int[]{}, new int[]{}, 5));
        System.out.println(sol.maxValue2(new int[]{2,3,4}, new int[]{1,2,3}, 5));
        System.out.println(sol.maxValue2(new int[]{1}, new int[]{2}, 5));
        System.out.println();
        System.out.println(sol.multipleItemValue(new int[]{2}, new int[]{4}, 2));
        System.out.println(sol.multipleItemValue(new int[]{2,3}, new int[]{1,2}, 5));
        System.out.println(sol.multipleItemValue(new int[]{2,3,5}, new int[]{2,3,10}, 5));
        System.out.println(sol.multipleItemValue(new int[]{}, new int[]{}, 5));
        System.out.println(sol.multipleItemValue(new int[]{2,3,4}, new int[]{3,2,3}, 4));
        System.out.println(sol.multipleItemValue(new int[]{1}, new int[]{2}, 5));



    }

}
