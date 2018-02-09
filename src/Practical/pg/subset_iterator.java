package Practical.pg;
import java.util.*;
public class subset_iterator {
	public class iterator {
		private Queue<String> queue = new LinkedList<>();
		private HashMap<Character, Integer> map = new HashMap<>();
		private char[] in;
		iterator(char[] input) {
			in = new char[input.length];
			for(int i = 0; i < input.length; i++) {
				queue.offer(""+input[i]);
				map.put(input[i], i);
				this.in[i] = input[i];
			}
		}
		public boolean hasNext() {
			return !queue.isEmpty();
		}
		public String next() {
			String cur_string = queue.poll();
			int cur_index = map.get(cur_string.charAt(cur_string.length()-1));
			for(int i = cur_index+1; i < in.length; i++) {
				queue.offer(cur_string+in[i]);
			}
			return cur_string;
		}
	}
	
	public static void main(String[] args) {
		char[] input = {'a','b','c','d'};
		subset_iterator s = new subset_iterator();
		iterator I = s.new iterator(input);
		while(I.hasNext()) {
			System.out.println(I.next());
		}
	}

}
