package PracticeInterview.Google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// 不是leetcode 题
//
//第四轮, 两个字符串, 一个字符串比另一个多一个字母, 其余出现顺序相同,返回那个字母,
//follow up: 出现顺序不一定相同, 返回那个字母,
//follow up: 如果字符串特别大, 怎么办?
public class DifferentSymbol {
    public static void main(String[] agrs) {
        System.out.println(sol(new int[]{2, 1, 1}, 3));
        System.out.println(solFollow(new int[]{2,1,1}, 3));


        System.out.println(sol(new int[]{5, 5, 6}, 3));
        System.out.println(solFollow(new int[]{5,5,6}, 3));

        System.out.println(sol1(new int[]{5, 1}, 3));
    }

    private static List<List<Integer>> sol(int[] array, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, n, array, 0, new ArrayList<>());
        return res;
    }

    // 这个dfs和一般的有一丢丢的不一样。
    // [2, 3, 1] 要取尽某个位置的所有点  所以你的for loop 起始位置应该是i = index, 而不是 i = index + 1
    private static void dfs(List<List<Integer>> res, int n, int[] array, int index, List<Integer> cur) {
        if (cur.size() == n) {
            res.add(new ArrayList<>(cur));
            return;
        }
        if (index >= array.length) {
            return;
        }
        for (int i = index; i < array.length; i++) {
            if (array[i] > 0) {
                array[i]--;
                cur.add(i);
                dfs(res, n, array, i, cur);
                cur.remove(cur.size() - 1);
                array[i]++;
            }
        }

    }
    //dp[i][j]: for the former i - 1 symbol, if n = j, then we have dp[i][j] solutions
    //dp[0][j] = {1, 0, 0, 0} 也就是说 如果一种symbol都没有，让你n = 0, 那么有一种方法，如果n > 0那是没有办法办到的
    //dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1]... dp[i - 1][j - idx], idx = array[i - 1]
    // 比如说 2 3 1, n = 3 那么在访问 1时， 我们有这么几种选择，
    // 分配给[2, 3] n = 0, 分配给[1] n = 3
    //分配给[2, 3] n = 1, 分配给[1] n = 2
    //分配给[2, 3] n = 2, 分配给[1] n = 1
    //分配给[2, 3] n = 3, 分配给[1] n = 0
    //但是显然，[1]最多只能取1个。  所以就是删去前面两种。 后面两种就是 dp[i - 1][j] + dp[i - 1][j - 1]
    //当然，可以优化空间复杂度。

    private static int solFollow(int [] array, int n) {
        int [] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i < array.length + 1; i ++) {
            int [] pre = dp;
            dp = new int[n + 1];
            for(int j = 0; j < n + 1; j ++) {
                for(int idx = 0; idx <= Math.min(array[i - 1], j); idx ++) {
                    dp[j] += pre[j - idx];
                }
            }
        }
        return dp[n];

    }

    static List<List<Integer>> res = new ArrayList<>();
    public static List<List<Integer>> sol1(int [] arr, int n) {
        help(new ArrayList<>(), arr, n, 0);
        return res;
    }
    private static void help(List<Integer> list, int [] arr, int n, int index) {
        if(list.size() == n) {
            res.add(new ArrayList<>(list));
            return;
        }
        if(index >= arr.length) {
            return;
        }
        help(list, arr, n, index + 1);
        if(arr[index] > 0) {
            arr[index] --;
            list.add(index);
            help(list, arr, n, index);
            arr[index] ++;
            list.remove(list.size() - 1);
        }

    }
}
