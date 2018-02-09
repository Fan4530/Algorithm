package Practical.fineb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by program on 12/18/2017.
 */
public class TextSplit {
//    public List<String> textSplit(String s, int limit) {
//        //assume (1/4) need 5 characters
//        List<String> res = new ArrayList<>();
//        int pageNumber = s.length() / (limit - 5);
//        StringBuilder sb = new StringBuilder();
//        int count = 0;
//        int left = 0;
//        while(count < pageNumber) {
//            sb.setLength(0);
//            count++;
//            res.add(sb.append(s.substring(left, count *(limit - 5))).append("(").
//                    append(count).append("/").append(pageNumber).append(")").toString());
//            left = count * (limit - 5);
//        }
//        for(int i = 0; i < res.size(); i ++) {
//            System.out.println(res.get(i));
//        }
//        return res;
//    }
    public List<String> textSplit(String s, int limit) {
        //假设不超过10页？
        //假设真正的limitation比每个str的长度都大？
        // start: the first word of a line
        // last: the next first word of a line
        // count: the len of a current line
        // page: page number, it is the iteration number
        int l = limit - 5;
        String [] str = s.split(" ");
        int start = 0;
        List<String> res = new ArrayList<>();
        while(start < str.length) {
            int last = start + 1;
            int count = str[start].length();
            while(last < str.length && str[last].length() + 1 + count <= l) {
                count += str[last++].length() + 1;
            }
            StringBuilder sb = new StringBuilder();
            for(int i = start; i < last; i ++) {
                sb.append(str[i]).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            res.add(sb.toString());
            start = last;
        }
        for(int i = 0; i < res.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(res.get(i)).append("(").append(i + 1).append("/").append(res.size()).append(")");
            res.set(i, sb.toString());
            System.out.println(res.get(i));
        }


        return res;
    }
    public static void main(String [] args) {
        TextSplit sol = new TextSplit();
        sol.textSplit("aaaaaa aaaaaaaa Hi helloterrana your uber is arrivlloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!Hi helloterran your uber is arriving soon!", 20);

    }
}
