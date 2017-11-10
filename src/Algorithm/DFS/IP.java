package Algorithm.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by program on 10/24/2017.
 */
public class IP {
    public List<String> restoreIpAddresses(String s) {
        if(s.length() < 4 || s.length() > 12) {
            return new ArrayList<String>();
        }
        List<String> res = new ArrayList<>();
        char [] array = s.toCharArray();

        helper(res, array, 0, "", 0);
        return res;

    }

    private void helper(List<String> res, char [] array, int count, String cur, int l) {
        if(count == 3) {
            if(isValid(array, l, array.length - 1)) {
                res.add(cur);
            }
            return;
        }
        for(int i = 1; i < 3; i ++) {
            if(isValid(array, l, l + i)) {
                int len = cur.length();
                String substr = new String(array, l, i);
                helper(res, array, count + 1, cur + '.' + substr, l + i + 1);
                cur = cur.substring(0, len);
            }
        }
    }
    private boolean isValid(char [] array, int left, int right) {
        int sum = 0;
        for(int i = right; i >= left; i --) {
            sum = sum * 10 + array[i] - '0';
        }
        return sum <= 255;
    }
    public static void main(String [] args) {
        IP sol = new IP();
        System.out.println(sol.restoreIpAddresses("0000"));
    }
}
