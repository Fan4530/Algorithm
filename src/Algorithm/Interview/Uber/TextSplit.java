package Algorithm.Interview.Uber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by program on 12/18/2017.
 */
public class TextSplit {
    public List<String> textSplit(String s, int limit) {
        //assume (1/4) need 5 characters
        List<String> res = new ArrayList<>();
        int pageNumber = s.length() / (limit - 5);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int left = 0;
        while(count < pageNumber) {
            sb.setLength(0);
            count++;
            res.add(sb.append(s.substring(left, count *(limit - 5))).append("(").
                    append(count).append("/").append(pageNumber).append(")").toString());
            left = count * (limit - 5);
        }
        for(int i = 0; i < res.size(); i ++) {
            System.out.println(res.get(i));
        }
        return res;
    }
    public static void main(String [] args) {
        TextSplit sol = new TextSplit();
        sol.textSplit("I am looking forward to seeing you. Dear professor", 15);

    }
}
