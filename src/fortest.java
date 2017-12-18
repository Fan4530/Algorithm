import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * Created by program on 12/14/2017.
 */
public class fortest {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean isMatch(String s, String p) {
        if(s == null || p == null) {
            return true;
        }
        int n = s.length();
        int m = p.length();
        boolean [][] dp = new boolean[m + 1][n +1];
        dp[0][0] = true;
        for(int i = 1; i <= m; i ++) {//pattern
            for(int j = 0; j <= n; j ++) {

                if((j >= 1 && (p.charAt(i - 1) == '.' ||  s.charAt(j - 1) == p.charAt(i - 1)))) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if(p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || (i - 2 >= 0 ? dp[i - 2][j] : false);
                    if(i - 2 >= 0 && j >= 1 && ( p.charAt(i - 2) == s.charAt(j - 1) || p.charAt(i - 2) == '.')) {
                        dp[i][j] |= dp[i - 1][j - 1] || dp[i][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }
    public static List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0|| k <= 0) {
            return new ArrayList<int []>();
        }
        k = Math.min(nums1.length * nums2.length, k);
        List<int []> res = new ArrayList<>();

        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>(11, (e1, e2) -> Integer.compare(nums1[e1[0]] + nums2[e1[1]], nums1[e2[0]] + nums2[e2[1]]));
        for(int i = 0; i < Math.min(k, nums1.length); i ++) {
            minHeap.offer(new int[]{i, 0});
        }
        while(k -- > 0) {
            int [] cur = minHeap.poll();
            res.add(new int[]{nums1[cur[0]], nums2[cur[1]]});
            if(cur[1] + 1 < nums2.length) {
                minHeap.offer(new int[]{cur[0], cur[1] + 1});
            }
        }
        return res;
    }
    public static int myAtoi(String str) {
        if(str == null) {
            return 0;
        }
        str = str.trim();
        if(str.length() == 0) {
            return 0;
        }
        long sum = 0;
        boolean positive = true;

        for(int i = 0; i < str.length(); i ++) {
            char c = str.charAt(i);
            if(Integer.MIN_VALUE >= - sum) {
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            } else if(Character.isDigit(c)) {
                sum = sum * 10 + c - '0';
            } else if(i == 0 && (c == '-' || c == '+')) {
                positive = c == '+' ? true : false;
            } else {
                break;
            }
        }
        if(Integer.MIN_VALUE >= - sum) {
            return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return positive ? (int)sum : (int)(-sum);
    }
    public static int minCostClimbingStairs(int[] cost) {
        if(cost == null || cost.length <= 1) {
            return 0;
        }
        int [] buy = new int[cost.length];
        int [] notBuy = new int[cost.length];
        buy[0] = cost[0];
        for(int i = 1; i < cost.length; i ++) {
            buy[i] = cost[i] + Math.min(buy[i - 1], notBuy[i - 1]);
            notBuy[i] = buy[i - 1];
        }
        return Math.min(buy[cost.length - 1], notBuy[cost.length - 1]);
    }


    public static String shortestCompletingWord(String licensePlate, String[] words) {
        int [] hash = new int[26];
        for(int i = 0; i < licensePlate.length(); i ++) {
            char c = licensePlate.charAt(i);
            if((c <= 'z' && c >= 'a') || (c <= 'Z' && c >= 'A')) {
                hash[Character.toLowerCase(c) - 'a'] ++;
            } else {
                continue;
            }
        }
        String res = "";
        for(int i = 0; i < words.length; i ++) {
            if(isValid(words[i], hash) && (res.equals("") || res.length() > words[i].length())) {
                res = words[i];
            }
        }
        return res;
    }
    private static boolean isValid(String s, int [] hash) {
        int [] compare = new int[26];
        for(int i = 0; i < s.length(); i ++) {
            compare[Character.toLowerCase(s.charAt(i) - 'a')] ++;
        }
        for(int i = 0; i < 26; i ++) {
            if(hash[i] > compare[i]) {
                return false;
            }
        }
        return true;
    }

    public static int countCornerRectangles(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        for(int i = 0; i < n - 1; i ++) {
            for(int j = 0; j < m - 1; j ++) {
                if(grid[i][j] == 1) {
                    for(int b = 1; j + b < m; b ++) {
                        if(grid[i][j + b] == 1) {
                            for(int h = 1; i + h < n; h ++) {
                                if(grid[i + h][j] == 1 && grid[i + h][j + b] == 1) {
                                    count ++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }





    public static boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();  // key: pattern    value: string
        Set<String> set = new HashSet<>();
        // use set to deduplicate
        // like case: aa ab,   the expext will be false
        // but since a --> b,   a --> b, it will return true. So we need to deduplicate use set
        return isMatch(map, set, 0, pattern, 0, str);
    }
    private static boolean isMatch(Map<Character, String> map, Set<String> set, int ip, String pat, int is, String str) {
        //base case
        if(is == str.length() && ip == pat.length()) {
            return true;
        }
        if(is == str.length() || ip == pat.length()) {
            return false;
        }
        //get the current pattern
        char p = pat.charAt(ip);
        //To see if this pattern is in the map
        String s = map.get(p);
        // in the map, check if s is the prefix of the str start with is
        if(s != null) {
            if(str.startsWith(s, is)) {
                return isMatch(map, set, ip + 1, pat, is + s.length(), str);
            }
            return false;
        }
        // not in the map, do for loop to try each of the following char
        for(int i = is; i < str.length(); i ++) {
            s = str.substring(is, i + 1);
            if(set.contains(s)) {
                continue;
            }
            set.add(s);
            map.put(p, s);
            if(isMatch(map, set, ip + 1, pat, is + s.length(), str)) {
                return true;
            }
            map.remove(p);
            set.remove(s);
        }
        return false;

    }
    public static void main(String [] args) {
        System.out.println(wordPatternMatch("aa", "ab"));
        System.out.println("abcde".startsWith("bcde",1));
        HashMap<Character, Integer> map = new HashMap<>();
        System.out.println(map.put('a', 1));
        System.out.println(map.put('a', 1));
        System.out.println(map.put('a', 3));
        System.out.println(Integer.valueOf("1"));
        System.out.println(Character.isAlphabetic('1'));
        String s = "abc";
        kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 10);
        long x = (long)(Integer.MAX_VALUE ) + 'c' - '0';
        System.out.println(x);
        System.out.println(x > Integer.MAX_VALUE);
        System.out.println(myAtoi("9223372036854775809"));
        System.out.println(myAtoi("9223372036854775807"));
        System.out.println(minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        System.out.println(shortestCompletingWord("Ah71752",
                new String []{"suggest","letter","of","husband","easy","education","drug","prevent","writer","old"}));
        System.out.println(containVirus(new int[][]{
                {0,1,0,0,0,0,0,1},
                {0,1,0,0,0,0,0,1},
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,0,0}
        }));

      //  System.out.println(Integer.valueOf("123a"));
      //  s.substring(1, 1);
       // groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
       // System.out.println(isMatch("aab", "c*a*b"));
        //System.out.println(isMatch("aa", ".*"));
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrderEncode(root, sb);
        return sb.toString();

    }
    public static int containVirus(int[][] grid) {
        boolean [][] visited = new boolean[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j ++) {
                if(!visited[i][j] && grid[i][j] == 1) {
                    dfs(grid, i, j, visited);
                }
            }
        }
        for(int i = 0; i < grid.length; i ++) {
            for( int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        return 1;
    }
    private static int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static void dfs(int [][] grid, int i, int j, boolean [][] visited) {
        if(i >= grid.length || i < 0 || j >= grid[0].length ||
                j < 0 ||  visited[i][j]) {
            return;
        }
        if(grid[i][j] != 1) {
            grid[i][j] = 2;
                    return;
        }
        visited[i][j] = true;
        for(int dir = 0; dir < 4; dir ++) {

            dfs(grid, i + dirs[dir][0], j + dirs[dir][1], visited);
        }

    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs == null || strs.length == 0) {
            return res;
        }
        HashMap<char [] , List<String>> map = new HashMap<>();
        for(int i = 0; i < strs.length; i ++) {
            char [] array = strs[i].toCharArray();
            Arrays.sort(array);
            List<String> cur = map.get(array);
            if(cur == null) {
                cur = new ArrayList<>();
                res.add(cur);
                map.put(array, cur);
            }
            cur.add(strs[i]);
        }
        return res;
    }
    private void preOrderEncode(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("null").append(",");
            return;
        }
        sb.append(root.val).append(",");
        preOrderEncode(root.left, sb);
        preOrderEncode(root.right, sb);
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String [] s = data.split(",");
        return preOrderDeCode(s, new int[1]);
    }
    private TreeNode preOrderDeCode(String [] data, int [] idx) {
        if(data[idx[0]].equals(null)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(data[idx[0]]));
        idx[0]++;
        root.left = preOrderDeCode(data, idx);
        idx[0]++;
        root.right = preOrderDeCode(data, idx);
        return root;
    }






    public List<List<String>> groupStrings(String[] strings) {

        List<List<String>> res = new ArrayList<>();
        if(strings == null || strings.length == 0) {
            return res;
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for(int i = 0; i < strings.length; i ++) {
            String mark = convert(strings[i]);
            List<String> list = map.get(mark);
            if(list == null) {
                list = new ArrayList<>();
                map.put(mark, list);
                res.add(list);
            }
            list.add(strings[i]);
        }
        return res;
    }
    private String convert(String s) {
        char first = s.charAt(0);
        int offset = first - 'a';
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i ++) {
            sb.append((char)(s.charAt(i) - offset));
        }
        return sb.toString();
    }





    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();

        int left = 0;
        int right = matrix[0].length - 1;
        int up = 0;
        int bottom = matrix.length - 1;
        int n = (right + 1) * (bottom + 1);
        while(n > 0) {
            for(int i = left; i <= right; i ++ ) {
                res.add(matrix[up][i]);
                n--;
            }
            up++;
            for(int i = up; i <= bottom; i ++) {
                res.add(matrix[i][right]);
                n--;
            }
            right--;
            if(n <= 0){
                return res;
            }
            for(int i = right; i >= left; i --) {
                res.add(matrix[bottom][i]);
                n--;
            }
            bottom --;
            for(int i = bottom; i >= up; i --) {
                res.add(matrix[i][left]);
                n--;
            }
            left++;
        }
        return res;
    }






    public List<String> topKFrequent(String[] words, int k) {
        // step 1: mapping
        // step 2: PriorityQueue
        // step 3: import to priority Queue
        // step 4； output

        // step 1: mapping
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i ++) {
            Integer frq = map.get(words[i]);
            if(frq == null) {
                map.put(words[i], 1);
            } else {
                map.put(words[i], frq + 1);
            }
        }
        // PriorityQueue
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>
                (11, (e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));
        // import
        int i = 0;
        for(Map.Entry<String, Integer> e : map.entrySet()) {
            if(i >= k) {
                if(minHeap.peek().getValue() < e.getValue()) {
                    minHeap.poll();
                }
            }
            minHeap.offer(e);
            i ++;
        }
        // output:
        List<String> res = new ArrayList<>();
        while(!minHeap.isEmpty()) {
            res.add(minHeap.poll().getKey());
        }
        return res;
    }


}
