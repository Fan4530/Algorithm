package Algorithm.DFS;

/**
 * Created by program on 10/13/2017.
 */
public class FormARingWithWords {
    public boolean formRing(String[] input) {
        // Write your solution here.
        return helper(input, 0);
    }
    private boolean helper(String[] input, int index) {
        if(index == input.length - 1) {
            String last = input[input.length - 1];
            if(input[0].charAt(0) == last.charAt(last.length() - 1)) {
                return true;
            } else {
                return false;
            }
        }
        for(int i = index + 1; i < input.length; i ++) {
            String next = input[i];
            String cur = input[index];
            if(cur.charAt(cur.length() - 1) == next.charAt(0)) {
                swap(input, index + 1, i);
                if(helper(input, index + 1)) {
                    return true;
                }
                swap(input, index + 1, i);
            }
        }
        return false;
    }
    private void swap(String [] input, int left, int right) {
        String tmp = input[left];
        input[left] = input[right];
        input[right] = tmp;
    }
}
