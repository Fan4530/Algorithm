package Algorithm.Interview.PocketGem;
import java.util.*;
public class class_test {

	class Node {
		List<Integer> neighbors;
		Node() {
			this.neighbors = new LinkedList<>();
		}
	}
	
	public static void main(String[] args) {
		class_test c = new class_test();
		Node d = c.new Node();
		d.neighbors.add(1);
		System.out.println(d.neighbors);
	}
}
