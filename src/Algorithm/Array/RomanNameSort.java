package Algorithm.Array;

import java.util.Arrays;

/**
 * Created by program on 10/21/2017.
 */
public class RomanNameSort {



    public static int romanToInt(String s) {
        if(s == null || s.length() == 0) {
            return -1;
        }
        int res = 0;
        for(int i = s.length() - 1; i >= 0; i --) {
            char c = s.charAt(i);
            switch(c) {
                case 'I': res += res >= 5 ? -1 : 1;
                    break;
                case 'V': res += 5;
                    break;
                case 'X': res += 10 * (res >= 50 ? -1 : 1);
                    break;
                case 'L': res += 50;
                    break;
                case 'C': res += 100 * (res >= 500 ? -1 : 1);
                    break;
                case 'D': res += 500;
                    break;
                case 'M': res += 1000;
                    break;
            }
        }
        return res;
    }

//    public static String[] sort_by(String[] input) {
//        Arrays.sort(input, new Comparator<String>(){
//            @Override
//            public int compare(String s1, String s2) {
//                String [] name1 = s1.split(" ");
//                String [] name2 = s2.split(" ");
//                if(name1[0].equals(name2[0])) {
//                    return Integer.compare(romanToInt(name1[1]), romanToInt(name2[1]));
//                } else {
//                    return name1[0].compareTo(name2[0]);
//                }
//            }
//        });
//        return input;
//
//    }
    public static String[] sort_by(String[] input) {
        Arrays.sort(input, (String s1, String s2) ->{// lambda override comparator
                String [] name1 = s1.split(" ");
                String [] name2 = s2.split(" ");
                if(name1[0].equals(name2[0])) {
                    return Integer.compare(romanToInt(name1[1]), romanToInt(name2[1]));
                } else {
                    return name1[0].compareTo(name2[0]);
                }
        });
        return input;
    }
    public static void main(String [] args) {
        RomanNameSort sol = new RomanNameSort();
        System.out.println(Arrays.toString(sol.sort_by(new String[]{"ab IV", "ab VI"})));
        System.out.println(Arrays.toString(sol.sort_by(new String[]{""})));
    }

}
