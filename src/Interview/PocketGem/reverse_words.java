package Interview.PocketGem;

public class reverse_words {
    public void swap(char[] s, int x, int y) {
        char temp = s[x];
        s[x] = s[y];
        s[y] = temp;
    }
    
    public void reverse(char[] s, int left, int right) {
        while (left < right)
            swap(s, left++, right--);
    }
    
    public String reverseWords(char[] s) {
        if (s == null || s.length == 0)
            return "";
        reverse(s, 0, s.length-1);
        int index = 0;
        while(index < s.length) {
        	while(index < s.length && s[index] == ' ')
        		index++;
        	int start = index;
        	while(index < s.length && s[index] != ' ')
        		index++;
        	reverse(s, start, index-1);
        }
        return new String(s);
    }
    
    public static void main(String[] args) {
    	String input = "  I love    yahoo";
    	reverse_words r = new reverse_words();
    	char[] in = input.toCharArray();
    	System.out.println(r.reverseWords(in));
    }
}
