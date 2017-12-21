package Interview.twitter;

import java.util.*;

/**
 * Created by program on 11/19/2017.
 */
public class twitter {
    public int maxLnegth(int [] a, int k) {
        if(a == null || a.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;// not include itself
        int cur = 0;
        int max = 0;
        while(left <= right && right < a.length) {
            if(cur < k) {
                cur += a[right];
                right ++;
            } else if(cur >= k) {
                cur -= a[left];
                left ++;
            }
            if(cur <= k) {
                max = Math.max(max, right - left);
            }

        }
        return max;
    }
    public static void main(String [] args) {
        twitter sol = new twitter();
        System.out.println(sol.maxLnegth(new int[]{1,2,3}, 4));
    }

    public int findMutationDistance(String start, String end, String [] bank) {
        HashSet<String> set = new HashSet<>();
            for(String s : bank) {
            set.add(s);
        }
        HashSet<String> visited = new HashSet<>();
            visited.add(start);
        Queue<String> q = new LinkedList<>();
            q.offer(start);
        int count = 1;
            while(!q.isEmpty()) {
            int size = q.size();

            count ++;
            for(int j = 0; j < size; j ++) {

                String cur = q.poll();
                for(int i = 0; i < cur.length(); i ++) {
                    char [] array = cur.toCharArray();
                    for(char c = 'a'; c <= 'z'; c ++) {
                        array[i] = c;
                        String newStr = new String(array);
                        if(newStr.equals(end) && set.contains(newStr)) {
                            return count;
                        }
                        if(set.contains(newStr) && visited.add(newStr)) {
                            q.offer(newStr);
                        }
                    }
                }
            }


        }
        return 0;
    }





    }

