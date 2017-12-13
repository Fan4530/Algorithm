package Algorithm.Interview.PocketGem;

/**
 * Created by program on 11/20/2017.
 */
public class ReverseWordsInString {
    public String reverseWords(String s) {
        if(s == null || s.length() <= 0) {
            return s;
        }
        char [] array = s.toCharArray();
        int len = removeSpace(array);

        reverse(array, 0, len - 1);
        int left = 0;
        for(int i = 0; i <= len; i ++) {// be careful len!!
            if(i == len || array[i] == ' ') {// be careful
                reverse(array, left, i - 1);
                left = i + 1;
            }
        }
        return new String(array, 0, len);// be careful !
    }
    private int removeSpace(char [] array) {
        int slow = 0;
        for(int i = 0; i < array.length; i ++) {
            if(array[i] != ' ' || (slow != 0 && array[slow - 1] != ' ' )) {
                array[slow++] = array[i];
            }
        }
        return (slow > 0 && array[slow - 1] == ' ') ? slow - 1 : slow;// Be careful!
    }
    private void reverse(char [] array, int left, int right) {
        while(left <= right) {
            char tmp = array[left];
            array[left++] = array[right];
            array[right--] = tmp;
        }
    }
}
