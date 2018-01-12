package Interview.Linkedin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by program on 12/8/2017.
 */
public class TextJustification {

    public static void main(String [] args) {

        System.out.println(fullJustify1(new String[]{"what", "must", "be", "shall","be"}, 12));
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<String>();
        // lines: res
        // index: rw
        // last: the next word index
        // count: the character number of current line
        int index = 0;
        while (index < words.length) {
            int count = words[index].length();//initialize the count
            int last = index + 1;//initialize the last
            while (last < words.length) { // still have one line, check if the total count number plus " " is larger than k or not
                if (words[last].length() + count + 1 > maxWidth) break;//not valid
                count += words[last].length() + 1;// valid， + 1是为了满足最低要求，之期间一个空格
                last++;// go on
            }
            StringBuilder builder = new StringBuilder();
            //A         B           C|       D       E
            //index                      last
            int diff = last - index - 1;// how many space area in one line
            // e.g.:  I   am    Fan
            //        idx              last    so the number of space number is: last - idx - 1

            // case 1: last == words.length:
            //      last is the last word, but there may be several words
            // case 2: diff == 0 means a word exactly take up one line
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    builder.append(words[i] + " ");
                    // >> why add " ": For the last line of text,
                    // it should be left justified and no extra space is inserted between words
                }
                //删除多余的一个空格
                builder.deleteCharAt(builder.length() - 1);
                //填满后面的空格
                for (int i = builder.length(); i < maxWidth; i++) {
                    builder.append(" ");
                }
            } else {
                // middle justified
                //还剩多少个空格可以分配
                ////注意这里的count之前算的是加了一个1的，所以这里会多减去一个1，所以要把1加回来

                int spaces = (maxWidth - count) / + 1;
                //本行小于r的(0,1,2,3..r - 1)都是 spaces + 1空格, 后面的是space个空格，
                int r = (maxWidth - count) % diff;
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    if (i < last - 1) {//注意：最后一个空格不用加！
                        for (int j = 0; j < (spaces + ((i - index) < r ? 1 : 0)); j++) {
                            builder.append(" ");
                        }
                    }
                }
            }
            lines.add(builder.toString());
            index = last;
        }
        return lines;
    }


    public static List<String> fullJustify1(String[] words, int maxWidth) {
        //step 1: get how many count can one line hold     I am Fan    l1 + 1 + l2 + 1 + l3 <= L
        //step 2: get the last line:   just append with the word and spaces
        //step 3: general line:    how many spaces: space = (L - count) / diff     r = (L - count) % diff
        List<String> res = new ArrayList<>();
        String [] input = words;
        int idx = 0;// idx of the words

        while(idx < input.length) {
            StringBuilder sb = new StringBuilder();
            int last = idx + 1;// the last word of the line
            int count = input[idx].length();
            while(last < input.length) {
                if(count + 1 + input[last].length() <= maxWidth) {// 1 means the space, space should be at least one between wrods
                    count += 1 + input[last].length();
                } else {
                    break;
                }
                last++;
            }
            //step 2:
            int diff = last - idx - 1;
            //diff == 0表示一行只有一个， start  + 1 = last
            //last == input.length表示剩下的多个单词只有一行！
            if(diff == 0 || last == input.length) {
                for(int i = idx; i < last; i ++) {
                    sb.append(input[idx] + " ");
                }
                sb.deleteCharAt(sb.length() - 1);
                for(int i = sb.length(); i < maxWidth; i ++) {
                    sb.append(" ");
                }
            } else {
                //因为上面的count其实是一个word + 一个空格的长度， 所以最后要+ 1
                int spaceLen = (maxWidth - count) / diff + 1;
                // 除了以后还有余数，补到左边
                int r = (maxWidth - count) % diff;// r means from rth space, we need to add space + 1 spaces
                //开始添加word和空格，从idx开始，一直到last - 1
                for(int i = idx; i < last; i ++) {
                    //先加一个字
                    sb.append(input[i]);
                    //然后看是要加spaceLne 个还是加spaceLne + 1个
                    int upperBound = spaceLen + ((i - idx) < r ? 1 : 0);
                    //最后一个word是不加space的
                    if(i < last - 1) {
                        for(int j = 0; j < upperBound; j ++) {
                            sb.append(" ");
                        }
                    }

                }
            }
            res.add(sb.toString());
            idx = last;
        }
        return res;
    }
}