package Algorithm.Interview.PocketGem;
import java.util.*;
public class len2duplicate {
	public static int find(String source, String target) {
		if(source.length() < 2 || target.length() < 2) 
			return 0;
		HashSet<String> set = new HashSet<>();
		for(int i = 0; i < source.length() - 1; i++) {
			String cur = "" + source.charAt(i) + source.charAt(i+1);
			set.add(cur);
		}
		int res = 0;
		for(int i = 0; i < target.length() - 1; i++) {
			String cur = "" + target.charAt(i) + target.charAt(i+1);
			if(set.contains(cur)) {
				res++;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		len2duplicate l = new len2duplicate();
		System.out.println(l.find("appple", "appp"));
	}
}
