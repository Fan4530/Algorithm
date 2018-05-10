package Practical.liyi;

import java.util.*;

/**
 * Created by program on 12/8/2017.
 */
public class wordLaddee {
    public static void main(String [] args) {
        String [] str = new String[]{"ait"};
        ladderLength("hit", "ait", new ArrayList<String>(Arrays.asList(str)));
    }
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         ["hot","dot","dog","lot","log","cog"]
//         boolean
//         hit
//             \
//             hot
//             / \
//            dot  lot
//           dog    log  cog  --> stop ,return layer

//             while()  reutnr 0
        if(wordList == null || wordList.size() == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        set.addAll(wordList);
        if(!set.contains(endWord)) {
            return 0;
        }
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int count = 1;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int k = 0; k < size; k ++) {
                char [] cur = q.poll().toCharArray();
                for(int j = 0; j < cur.length; j ++) {
                    char original = cur[j];
                    for(int i = 'a'; i <= 'z'; i ++) {
                        cur[j] = (char)i;
                        String s = new String(cur);
                        if(s.equals(endWord)) {
                            return count;
                        }
                        if(set.contains(s)) {
                            set.remove(s);
                            q.offer(s);
                        }
                    }
                    cur[j] = original;
                }
            }
            count++;
        }
        return 0;

    }
}
