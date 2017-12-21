package Interview.test;

import java.util.*;

/**
 * Created by program on 12/10/2017.
 */
public class fortest {
    public static void main(String [] ars) {
        // evalRPN(new String[]{"0", "3", "/"});
        // System.out.println(firstLargerEqual(new int[]{2,2}, 1));
        fortest sol = new fortest();
        System.out.println(sol.firstLargerEqual(new int[]{2,2}, 1));
    }
















    public String longestCommonPrefix(String [] strs) {
        if(strs == null || strs.length == 0 || strs[0].length() == 0) {
            return "";
        }
        String firstLine = strs[0];

        for(int i = 0; i < strs.length; i ++) {// row
            for(int j = 1; j < firstLine .length(); j ++) {
                if(j >= strs[i].length() || strs[i].charAt(j) != firstLine.charAt(j)) {
                    return firstLine .substring(0, j);
                }
            }
        }
        return firstLine ;
    }

    public static int evalRPN(String [] tokens) {
        if(tokens == null || tokens.length == 0) {
            return 0;
        }
        Deque<Integer> dq = new LinkedList<>();
        for(int i = 0; i < tokens.length; i ++) {
            if(tokens[i].equals("/")) {
                int first = dq.pollFirst();
                int second = dq.pollFirst();
                dq.offerFirst(second / first);
            }  else if(tokens[i].equals('-')){
                int first = dq.pollFirst();
                int second = dq.pollFirst();
                dq.offerFirst(second - first);
            } else if(tokens[i].equals('+')) {
                dq.offerFirst(dq.pollFirst() + dq.pollFirst());
            } else if(tokens[i].equals('*')) {
                dq.offerFirst(dq.pollFirst() * dq.pollFirst());
            } else {
                dq.offerFirst(Integer.parseInt(tokens[i]));
            }
        }
        return dq.pollFirst();
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.size() == 0) {
            return 0;
        }
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int count = 1;
        HashSet<String> set = new HashSet<>();
        set.addAll(wordList);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i ++) {
                char [] array = q.poll().toCharArray();
                for(char j = 'a'; j <= 'z'; j ++) {
                    for(int l = 0; l < array.length; l ++) {
                        char c = array[l];
                        array[l] = j;
                        String tmp = new String(array);
                        if(set.remove(tmp)) {
                            q.offer(tmp);
                            if(tmp.equals(endWord)) {
                                return count + 1;
                            }
                        }

                        array[l] = c;
                    }
                }
            }
            count ++;
        }
        return 0;

    }

    public static int []  searchRange(int [] nums, int target) {
        if(nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = firstLargerEqual(nums, target);
        int right = firstLargerEqual(nums, target + 1);
        if(left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        } else {
            return new int[]{left, right};
        }
    }
    //5 7 7 7 7 8  target 7   mid == target    right = mid
//                   3   mid  > target    right = mid
//                        mid < target     left = mid + 1
    private static int firstLargerEqual(int [] array, int target) {
        int left = 0;
        int right = array.length;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(array[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
//   3  target 5   left = mid + 1
//   3  target 3  right = mid    return left
//   3  target 7

}
