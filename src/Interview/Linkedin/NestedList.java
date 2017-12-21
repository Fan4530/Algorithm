package Interview.Linkedin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by program on 11/14/2017.
 */

class NestedInteger {

    Object  i;
    public NestedInteger (Object i) {
        this.i = i;
    }
    public boolean isInteger() {
        return i instanceof Integer;
    }
    public Integer getInteger() {
        if(isInteger()) {
            return (Integer) i;
        }
        return null;
    }
    public List<NestedInteger> getList() {
        if(isInteger()) {
            return null;
        }
        return (List<NestedInteger>) i;
    }

}
public class NestedList {
    public static void main(String [] args) {
        NestedList sol = new NestedList();
        List<NestedInteger> l = new ArrayList<>();
        l.add(new NestedInteger(1));
        l.add(new NestedInteger(2));
        List<NestedInteger> l2 = new ArrayList<>();

        l2.add(new NestedInteger(l));
        l2.add(new NestedInteger(1));
        l2.add(new NestedInteger(1));

        System.out.println(sol.depthSum(l2));

    }

    //                      node
    //                  /   |   \
    //                node  2    node           1 * 3  = 3
    //              /  |           |  \
    //              1  1          1   node      1 * 1 + 1 * 1 + 1 * 2  = 4          dfs -->  up to down increase the depth
    //                                 |   |
    //                                 1   1    1 * 3 + 1 * 3 = 6
    // bottom to up

    //Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
    //Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
    //                      node
    //                  /   |   \
    //                node  2    node           2 * 3  = 6
    //              /  |           |  \
    //              1  1          1   node      1 * 2 + 1 * 2 + 1 * 2  = 6          dfs -->  up to down increase the depth
    //                                 |   |
    //                                 1   1    1 * 1 + 1 * 1 = 2
    public int depthSumII(List<NestedInteger> nestedList) {
        // draw the triangle, then done!
        //
        //
        // curLayer =                             2    3             2
        // sum = sum + preLayerSum + curLayer     2    7             14
        // preLayerSum += curLayer                2   2 + 3 = 5    2 + 3 + 2
        int preLayerSum = 0;
        int curLayer = 0;
        int sum = 0;
        Queue<NestedInteger> q = new LinkedList<>();
        for(NestedInteger e : nestedList) {
            q.offer(e);
        }

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i ++) {
                NestedInteger node = q.poll();
                if(node.isInteger()) {
                    curLayer += node.getInteger();
                } else {
                    for(NestedInteger e : node.getList()) {//be careful
                        q.offer(e);
                    }

                }
            }
            sum += preLayerSum + curLayer;
            preLayerSum += curLayer;
            curLayer = 0;
        }
        return sum;
    }


    //Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
    //Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)

    //
    //                      node
    //                  /   |   \
    //                node  2    node           2 * 1  = 2
    //              /  |           |  \
    //              1  1          1   node      1 * 2 + 1 * 2 + 1 * 2  = 6          dfs -->  up to down increase the depth
    //                                 |   |
    //                                 1   1    1 * 3 + 1 * 3 = 6
    //
    private int sum = 0;
    public int depthSum(List<NestedInteger> nestedList) {
        dfs(1, nestedList);
        return sum;
    }
    private void dfs(int index, List<NestedInteger> list) {
        //base case
        for(NestedInteger e : list) {
            if(e.isInteger()) {
                sum += index * e.getInteger();
            } else {
                dfs(index + 1, e.getList());
            }
        }
    }
}
