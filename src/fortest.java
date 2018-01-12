import java.util.*;

/**
 * Created by program on 12/18/2017.
 */
public class fortest {
        public static void main(String [] args) {
                fortest sol = new fortest();
             //   System.out.println(sol.longestPalindrome(("babad")));
              //  System.out.println(sol.longestCommonPrefix((new String[]{"aa", "a"})));
            //    System.out.println(sol.divide(-2147483648, 1));
             //   System.out.println(sol.combinationSum2(new int[]{2, 6, 1 , 5}, 8));
               // System.out.println(sol.threeSum(new int[]{-1, -1, 2}));
              //  System.out.println(sol.findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"}));
               // System.out.println(sol.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
               // System.out.println(sol.canJump(new int[]{2, 3, 1, 1, 4}));
           // sol.setZeroes(new int[][]{{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}});
            //System.out.println(sol.canIWin(5,4));
            for(int i = 0; i < 30; i ++) {
                int n = i;

                System.out.print(((n ^= n/4) & n-1) == 0);
                System.out.println(" " + Integer.toBinaryString(i));
            }
            System.out.println(null == "a");
        }



    List<Integer> path = new ArrayList<>();
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {

        boolean [] notChose = new boolean[maxChoosableInteger + 1];


        canWin(notChose, desiredTotal);
        for(int i : path) {
            System.out.println(i);
        }
        return false;
    }
    private boolean canWin(boolean [] notChose, int desiredTotal) {
        if(desiredTotal <= 0) {
            System.out.println("??");
            return false;
        }
        for(int i = 1; i < notChose.length; i ++) {
            if(!notChose[i]) {
                path.add(i);
                System.out.println("add" + i);
                notChose[i] = true;
                if(!canWin(notChose, desiredTotal - i)) {
                    return true;
                }
                System.out.println("remove" + path.get(path.size() - 1));
                path.remove(path.size() - 1);
                notChose[i] = false;
            }


        }
        return false;
    }
    public void setZeroes(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        for(int i = 0; i < matrix.length; i ++) {
            for(int j = 0; j < matrix[0].length; j ++) {
                if(matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for(int i = 0; i < matrix.length; i ++) {
            if(matrix[i][0] == 0) {
                for(int j = 1; j < matrix[0].length; j ++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for(int j = 0; j < matrix[0].length; j ++) {
            if(matrix[0][j] == 0) {
                for(int i = 0; i < matrix.length; i ++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

        public boolean canJump(int[] nums) {
                if(nums == null || nums.length <= 1) {
                        return true;
                }

                int last = nums.length - 1;
                for(int i = nums.length - 2; i >= 0; i --) {
                        if(i + nums[i] >= last) {
                                i = last;
                        }
                }
                return last <= 0;
        }


        public List<Integer> findSubstring(String s, String[] words) {
                // step 1: hashmap to record the words
                // step 2: for loop each, to see the counter if it is larger than 0

                HashMap<String, Integer> map = new HashMap<>();
                int n = words.length; // number of the words
                int m = words[0].length(); // len of the one word
                for(String w : words) {
                        Integer frq = map.get(w);
                        if(frq == null) {
                                map.put(w, 1);
                        } else {
                                map.put(w, 1 + frq);
                        }
                }
                List<Integer> res = new ArrayList<>();
                for(int i = 0; i <= s.length() - n * m; i ++) {
                        HashMap<String, Integer> copy = new HashMap<>(map);
                        int j = i;
                        int k = n;
                        while(k > 0) {
                                String cur = s.substring(j, j + m);
                                if(!map.containsKey(cur) || map.get(cur) < 1) {
                                        break;
                                } else {
                                        copy.put(cur, copy.get(cur) - 1);
                                }
                                k--;
                                j += m;
                        }
                        if(k == 0) {
                                res.add(i);
                        }

                }
                return res;
        }


        public List<List<Integer>> threeSum(int[] nums) {
                if(nums == null || nums.length < 3) {
                        return new ArrayList<>();
                }
                Arrays.sort(nums);
                List<List<Integer>> res = new ArrayList<>();
                for(int i = 0; i < nums.length; i ++) {
                        if(i != 0 && nums[i] == nums[i - 1]) {
                                continue;
                        }
                        int l = i + 1;
                        int r = nums.length - 1;
                        while(l < r) {
                                if(l != i && nums[l] == nums[l - 1]) {
                                        l++;
                                        continue;
                                }
                                int sum = nums[l] + nums[r] + nums[i];
                                System.out.println(nums[l]);
                                System.out.println(nums[r]);
                                System.out.println(nums[i]);
                                if(sum == 0) {
                                        res.add(Arrays.asList(nums[l++], nums[r--], nums[i]));
                                        System.out.println("Makr");
                                } else if(sum < 0) {
                                        l ++;
                                } else {
                                        r --;
                                }
                        }
                }
                return res;
        }
        public List<List<Integer>> combinationSum2(int[] cand, int target) {
                Arrays.sort(cand);
                List<List<Integer>> res = new ArrayList<List<Integer>>();
                List<Integer> path = new ArrayList<Integer>();
                dfs_com(cand, 0, target, path, res);
                return res;
        }
        void dfs_com(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
                if (target == 0) {
                        res.add(new ArrayList(path));
                        return ;
                }
                if (target < 0) return;
                for (int i = cur; i < cand.length; i++){
                        if (i > cur && cand[i] == cand[i-1]) continue;
                        path.add(cand[i]);
                        dfs_com(cand, i+1, target - cand[i], path, res);
                        path.remove(path.size()-1);
                }
        }


}
