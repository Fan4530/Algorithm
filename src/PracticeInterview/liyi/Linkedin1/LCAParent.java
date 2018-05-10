package Practical.liyi.Linkedin1;

/**
 * Created by program on 1/11/2018.
 */
public class LCAParent {
    static class TreeNodeP {
        TreeNodeP left;
        TreeNodeP right;
        TreeNodeP parent;
        int val;
        public TreeNodeP (int num){
            val = num;
        }
    }
    public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
        int h1 = getH(one);
        int h2 = getH(two);
        if(h1 > h2) {
            one = move(one, h1 - h2);
        } else {
            two = move(two, h2 - h1);
        }
        return getLCA(one, two);
    }
    private TreeNodeP getLCA(TreeNodeP one, TreeNodeP two) {
        while(one != two) {
            one = one.parent;
            two = two.parent;
        }
        return one;
    }
    private TreeNodeP move(TreeNodeP node, int diff) {
        while(diff -- > 0) {
            node = node.parent;
        }
        return node;
    }
    private int getH(TreeNodeP root) {
        int count = 0;
        while(root != null) {
            root = root.parent;
            count++;
        }
        return count;
    }

}
