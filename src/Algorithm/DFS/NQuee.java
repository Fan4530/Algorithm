package Algorithm.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by program on 10/23/2017.
 */
public class NQuee {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        List<String> str = new ArrayList<>();
        helper(res, n, cur, str);
        return res;
    }
    private void helper(List<List<String>> res, int n, List<Integer> cur, List<String> str) {
        //base case
        if(cur.size() == n) {
            res.add(new ArrayList<String>(str));
            return;
        }


        // check if each of the col is valid
        for(int i = 0; i < n; i ++) {
            if(isValid(cur, i)) {
                cur.add(i);
                add(str, i, n);
                helper(res, n, cur, str);
                str.remove(str.size() - 1);
                cur.remove(cur.size() - 1);
            }
        }
    }
    private void add(List<String> str, int i, int n) {
        char [] array = new char[n];
        Arrays.fill(array, '.');
        array[i] = 'Q';
        str.add(new String(array));


    }
    private boolean isValid(List<Integer> list, int curCol) {
        //check each row
        // list.get(i): previous col
        // i: previous row
        // list.size(): cur row
        for(int i = 0; i < list.size(); i ++) {
            if(curCol == list.get(i) || Math.abs(list.size() - i) == Math.abs(list.get(i) - curCol)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String [] args) {
        NQuee sol = new NQuee();
        System.out.println(sol.solveNQueens(4));
    }

}
