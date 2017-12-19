package Algorithm.Interview.Uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by program on 12/18/2017.
 */
public class CombinationOf10Digits {
    public static List<List<Integer>> combination(List<Integer> input) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(input, res, 0);
        print(res);
        return res;
    }
    public static void print(List<List<Integer>> in) {
        for(int i = 0; i < in.size(); i ++) {
            List<Integer> print = in.get(i);
            for(int j = 0; j < print.size(); j ++) {
                System.out.print(print.get(j) + " ");
            }
            System.out.println();
        }
    }
    private static void dfs(List<Integer> input, List<List<Integer>> res, int idx) {
        if(idx == input.size()) {
            res.add(new ArrayList<>(input));
            return;
        }
        for(int i = idx; i < input.size(); i ++) {
            swap(input, i, idx);
            dfs(input, res, idx + 1);
            swap(input, i, idx);
        }
    }
    private static void swap(List<Integer> input, int l, int r) {
        int tmp = input.get(l);
        input.set(l, input.get(r));
        input.set(r, tmp);
    }

    public static void main(String [] agrs) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        combination(numbers);
    }
}
