package Practical.pg;
import java.io.*;
import java.util.*;
public class TopKFreqElement {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // bucket sort
        // time complexity: O(n) for this problem,  here since all the frqs are Integer
        List<Integer> res = new LinkedList<>();
        if (nums == null || nums.length == 0)
            return res;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i ++) {
            Integer frq = map.get(nums[i]);
            if(frq == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], frq + 1);
            }
        }
        // the index of bucket is the frequence, the content is the List of the numers
        // attention the format of new a instance of list array 
        
        List<Integer>[] bucket = new ArrayList[nums.length + 1];// frqs from 0 to nums.length
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            if (bucket[item.getValue()] == null)
                bucket[item.getValue()] = new LinkedList<Integer>();
            bucket[item.getValue()].add(item.getKey());
        }
        // use of addAll
        for (int i = nums.length; i >= 0 && res.size() < k; i--) {
            if (bucket[i] != null)
                res.addAll(bucket[i]);
        }
        return res;
    }
    //using heap

    public List<Integer> topKFrequentHeap(int[] nums, int k) {
        //step 1 : get hte frq of the number
        //step 2 : since we need top k, so we use a minHeap, the bottom is the largest
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i ++) {
            Integer frq = map.get(nums[i]);
            if(frq == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], frq + 1);
            }
        }
        // minHeap
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(11, (e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));
        // import the data
        int i = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(i < k) {
                minHeap.offer(entry);
            } else {
                if(entry.getValue() > minHeap.peek().getValue()) {
                    minHeap.poll();
                    minHeap.offer(entry);
                }
            }
            i ++;
        }
        //
        List<Integer> list = new ArrayList<>();
        for(i = 0; i < k; i ++) {// be careful!
            list.add(minHeap.poll().getKey());
        }
        Collections.reverse(list);
        return list;
    }


    //----------------------------------




    public static void main(String[] args) throws Exception {
    	int[] nums = {1,1,1,2,2,2,2,3,3,3,3,3,3,3,3};
    	TopKFreqElement t = new TopKFreqElement();
    	System.out.println(t.topKFrequent(nums, 2));
    	
    	System.out.println(t.top1(nums));
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String input;
    	try {
    		while((input = br.readLine()) != null) {
    			System.out.print(input);
    		}
    	} catch (Exception e) {
    		throw e;
    	} finally {
    		br.close();
    	}
    	
    }


    public int top1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max_item = 0;
        int max_value = 0;
        for(int n : nums) {
            map.put(n, map.getOrDefault(n,0)+1);
            if(map.get(n) > max_value) {
                max_value = map.get(n);
                max_item = n;
            }
        }
        return max_item;
    }
}
