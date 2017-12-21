package Interview.PocketGem;

/**
 * Created by program on 11/21/2017.
 */
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        // a  a  c  e  c  a  a | a
        // isValid(0, len - 1);
        char [] array = s.toCharArray();
        int len = array.length;
        int mark = 0;
        for(int i = len - 1; i >= 0; i--) {
            if(isValid(array, 0, i)) {
                mark = i;
                break;
            }
        }
        reverse(array, mark + 1, array.length - 1);
        return new String(array, mark + 1, array.length - mark - 1) + s;
    }
    private void reverse(char [] array, int left, int right) {
        while(left <= right) {
            char tmp = array[left];
            array[left++] = array[right];
            array[right--] = tmp;
        }
    }
    private boolean isValid(char [] array, int left, int right) {
        while(left <= right) {
            if(array[left++] != array[right--]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String [] args) {
        ShortestPalindrome sol = new ShortestPalindrome();
        System.out.println(sol.shortestPalindrome("cecaaa"));
    }
}
