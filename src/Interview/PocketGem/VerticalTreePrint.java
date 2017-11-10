package Interview.PocketGem;
import java.util.*;
public class VerticalTreePrint {
    //                1
//              /  \
//            3     4              queue:    1    3              4
//           /                     qIdx      0    0 - 1 = - 1    4
//          4
//          -2  -1  0  1
//
//
//    -5 -4 -3 -2 -1 0
//    0              5

    //step 1: find the boundary, by traversing the tree  O(n)
    //step 2: use two queues to BFS O(n)
    //           treicy: the idx of root is the -bd[0] th of the arraylist
    //
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        int [] bd = new int[2];
        findBound(bd, root, 0);


        for(int i = bd[0]; i <= bd[1]; i ++) {
            res.add(new ArrayList<>());
        }

        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> qIdx = new LinkedList<>();

        q.offer(root);
        qIdx.offer(-bd[0]);

        while(!q.isEmpty()) {
            TreeNode node = q.poll();
            int idx = qIdx.poll();
            res.get(idx).add(node.val);
            if(node.left != null) {
                q.offer(node.left);
                qIdx.offer(idx - 1);
            }
            if(node.right != null) {
                q.offer(node.right);
                qIdx.offer(idx + 1);
            }
        }
        return res;
    }
    private void findBound(int [] bd, TreeNode root, int col) {
        if(root == null) {
            return;
        }
        //what we should do
        bd[0] = Math.min(bd[0], col);
        bd[1] = Math.max(bd[1], col);
        findBound(bd, root.left, col - 1);
        findBound(bd, root.right, col + 1);
    }


    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null) {
            return res;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> col = new LinkedList<>();
        queue.offer(root);
        col.offer(0);
        int min_col = 0;
        int max_col = 0;
        while(!queue.isEmpty()) {
            TreeNode cur_node = queue.poll();
            int c = col.poll();
            if(!map.containsKey(c)) {
                map.put(c, new LinkedList<Integer>());
            }
            map.get(c).add(cur_node.val);
            if(cur_node.left != null) {
                queue.offer(cur_node.left);
                col.offer(c-1);
                min_col = Math.min(min_col, c-1);
            }
            if(cur_node.right != null) {
                queue.offer(cur_node.right);
                col.offer(c+1);
                max_col = Math.max(max_col, c+1);
            }
        }
        for(int i = min_col; i <= max_col; i++) {
            res.add(new LinkedList<Integer>(map.get(i)));
        }
        return res;
    }


}
