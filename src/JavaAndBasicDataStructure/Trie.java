package JavaAndBasicDataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by program on 12/20/2017.
 */
//Trie Tree: 第一个node没有用，信息存在边上
// 缺点：
    //1. TreeNode 需要额外的开销
    //2. locality差， 东存一个西存一个
// 优点:
    //1: sapce: O(nm),  time: O(m),  m is the word length, n is the number of the words
    //2. compared with HashMap:
        // map: equals, hashcode都需要O(m), 空间更糟糕
//Implementation:
    //1. hashmap
    //  trie Node is a tree node
    //  *** key: is the edge
    //      value: trie Node
    //2. array
//class TrieNode {
//    boolean isWord;
//    HashMap<Character, TrieNode> map;
//}
//class TrieNode {
//    boolean isWord;
//    int [] childNodes = new int[26];
//}
public class Trie {
    public static void main(String [] agrs) {
        Trie sol = new Trie();
        //一般第一个Node是没有用的
        TrieNode root = new TrieNode();
        sol.insert(root, "zhangfan");
        sol.insert(root, "wang");
        sol.insert(root, "zhou");
        sol.insert(root, "gea");
        sol.insert(root, "geb");
        sol.insert(root, "gec");
        sol.insert(root, "ged");
        sol.insert(root, "laoge");
        System.out.println(sol.findAllWithPrefix(root, "geaa"));
        System.out.println(sol.search(root, "zhangfan"));
        sol.delete(root, "zhangfan");
        System.out.println(sol.search(root, "zhangfan"));
        System.out.println(sol.wildMatch(root, "?ao?e"));
    }
    static class TrieNode {
        boolean isWord;
        HashMap<Character, TrieNode> children;
        int count;
        public TrieNode() {
            children = new HashMap<>();
            count = 0;
        }
    }
    public boolean search(TrieNode root, String target) {
        TrieNode cur = root;
        for(int i = 0; i < target.length(); i ++) {
            char c = target.charAt(i);
            TrieNode next = cur.children.get(c);
            if(next == null) {
                return false;
            }
            cur = next;// why need this
        }
        return cur.isWord;
    }
    public void insert(TrieNode root, String target) {
        TrieNode cur = root;
        cur.count++;
        for(int i = 0; i < target.length(); i ++) {
            char c = target.charAt(i);
            TrieNode next = cur.children.get(c);
            if(next == null) {
                next = new TrieNode();
                cur.children.put(c, next);
            }
            cur = next;// why need this
            cur.count++;
        }
        cur.isWord = true;
    }
    // cutting? or just set false?

    // method 1 : store how many words we store in the subproblem(include itself)
    // case 1: 要删除abc, 那么走到c后，循环结束，标记为isWord = false后结束
    //             N(1)
    //            / a
    //           N (1)
    //          /b
    //         N(true: 1)
    //        /c
    //       N(0)
    //      /d
    //     N(0)

    // case 2: 删除abcd: 其实走到c发现next.count == 1, 直接可以切掉了map.remove
    //             N(2)
    //            / a
    //           N (2)
    //          /b
    //         N(true: 2)
    //        /c
    //       N(1)
    //      /d
    //     N(true: 1)
    public void delete(TrieNode root, String target) {
        if(!search(root, target)) {
            return;
        }
        TrieNode cur = root;
        for(int i = 0; i < target.length(); i ++) {
            char c = target.charAt(i);
            TrieNode next = cur.children.get(c);
            if(next.count == 1) {
                cur.children.remove(c);
                return;
            }
            cur.count --;
            cur = next;// why need this
        }
        cur.isWord = false;
    }
    public List<String> findAllWithPrefix(TrieNode root, String prefix) {
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();
        TrieNode cur = root;
        for(int i = 0; i < prefix.length(); i ++) {
            char c = prefix.charAt(i);
            sb.append(c);
            TrieNode next = cur.children.get(c);
            if(next == null) {
                return res;
            }
            cur = next;
        }
        findAllWithPrefixDFS(res, sb, cur);
        return res;
    }
    private void findAllWithPrefixDFS(List<String> res, StringBuilder sb, TrieNode cur) {
//        not necessary
//        if(cur.children.size() == 0) {
//            return;
//        }
        if(cur.isWord) {
            res.add(sb.toString());
        }
        for(Map.Entry<Character, TrieNode> e : cur.children.entrySet()) {
            sb.append(e.getKey());
            findAllWithPrefixDFS(res, sb, e.getValue());
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> wildMatch(TrieNode root, String target) {
        if(target == null || target.length() == 0) {
            return new ArrayList<>();
        }
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();
        wildMatchDFS(root, target, sb, res, 0);
        return res;
    }

    public void wildMatchDFS(TrieNode root, String match, StringBuilder sb, List<String> res, int idx) {
        if(idx == match.length()) {
            if(root.isWord) {
                res.add(sb.toString());
            }
            return;
        }

        // add to stringBuilder
        char c = match.charAt(idx);
        if(c == '?') {
            for(Map.Entry<Character, TrieNode> e : root.children.entrySet()) {
                sb.append(e.getKey());
                wildMatchDFS(e.getValue(), match, sb, res, idx + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        } else {
            TrieNode next = root.children.get(c);
            if(next != null) {// pay attention !!!
                sb.append(c);
                wildMatchDFS(next, match, sb, res, idx + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

}
