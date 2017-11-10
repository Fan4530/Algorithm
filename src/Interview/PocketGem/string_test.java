package Interview.PocketGem;
import java.util.*;
public class string_test {
	public static void main(String[] args) {
		String[] nums = {"daniel", "summer","daniel","summer"};
		Arrays.sort(nums, new Comparator<String>() {
			public int compare(String e1, String e2) {
				return e1.compareTo(e2);
			}
		});
		System.out.println(Arrays.toString(nums));
	}
}
