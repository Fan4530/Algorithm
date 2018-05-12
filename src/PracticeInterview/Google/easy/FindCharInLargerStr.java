package PracticeInterview.Google.easy;

//第四轮, 两个字符串, 一个字符串比另一个多一个字母, 其余出现顺序相同,返回那个字母,
//        follow up: 出现顺序不一定相同, 返回那个字母,
//        follow up: 如果字符串特别大, 怎么办?
public class FindCharInLargerStr {
    public static void main(String [] agrs) {

        //should return d
        System.out.println(sol2("abacdfaa", "abaacfa"));

    }
    public static char sol1(String str1, String str2) {
        int smallSize = Math.min(str1.length(), str2.length());
        for(int i = 0; i < smallSize; i ++) {
            if(str1.charAt(i) != str2.charAt(i)) {
                return str1.length() > str2.length() ? str1.charAt(i) : str1.charAt(i);
            }
        }
        return str1.length() == smallSize ? str2.charAt(smallSize) : str1.charAt(smallSize);
    }
    public static char sol2(String str1, String str2) {
        char res = 0;
        for(int i = 0; i < str1.length(); i ++) {
            res ^= str1.charAt(i);
        }
        for(int i = 0; i < str2.length(); i ++) {
            res ^= str2.charAt(i);
        }
        return res;
    }
}
