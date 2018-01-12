package Interview.Linkedin.Linkedin1;

/**
 * Created by program on 1/11/2018.
 */
public class ReplaceAll {
    private static String replaceAll(String A, String B, String C) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < A.length(); i ++) {
            if(i + B.length() < A.length() && A.substring(i, i + B.length()).equals(B)) {
                sb.append(C);
                i += B.length() - 1;
            } else {
                sb.append(A.charAt(i));
            }
        }
        return sb.toString();
    }
    public static void main(String [] args) {
        System.out.println(replaceAll("I love you my dear, do you love me?", "love", "hate"));
    }
}
