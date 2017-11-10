package Interview.PocketGem;
import java.util.*;
public class RPG_bag {
    public static void main(String[] args) {
        ItemInfo[] infors = new ItemInfo[3];
        infors[0] = new ItemInfo("diamond", 10, 5);
        infors[1] = new ItemInfo("ruby", 5, 5);
        infors[2] = new ItemInfo("armor", 25, 1);
        String[] items = {"diamond", "ruby", "armor", "diamond", "diamond", "ruby", "diamond", "diamond", "diamond", "diamond",
        "diamond", "armor"};
        System.out.println(solve(3, items, infors));
    }
    public static int solve(int n, String[] items, ItemInfo[] infos) {
        if (infos == null || infos.length == 0 || items == null || items.length == 0) return 0;
        Map<String, Integer> values = new HashMap<>();
        Map<String, Integer> size = new HashMap<>();
        Map<String, Integer> frequency = new HashMap<>();
        for (ItemInfo info : infos) {
            values.put(info.name, info.value);
            size.put(info.name, info.max_stack_size);
        }
        for (String item : items) {
            int count = frequency.getOrDefault(item, 0);
            frequency.put(item, count + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            int f = entry.getValue();
            int stackSize = size.get(entry.getKey());
            int value = values.get(entry.getKey());
            while (f != 0) {
            	int cur_value = 0;
                if (f >= stackSize) {
                	cur_value = stackSize * value;
                    f -= size.get(entry.getKey());
                } else {
                	cur_value = f * value; 
                    f = 0;
                }
                if (pq.size() == n) {
                	if(cur_value > pq.peek()) {
                		pq.poll();
                		pq.offer(cur_value);
                	}
                } else {
                	pq.offer(cur_value);
                }
            }
        }
        int max = 0;
        while (!pq.isEmpty()) max += pq.poll();
        return max;
    }
    static class ItemInfo {
        String name;
        int value;
        int max_stack_size;
        public ItemInfo(String name, int value, int max) {
            this.name = name;
            this.value = value;
            this.max_stack_size = max;
        }
    }
}