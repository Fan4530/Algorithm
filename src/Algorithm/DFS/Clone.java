package Algorithm.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by program on 10/16/2017.
 */

  class UndirectedGraphNode {
      int label;
      List<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
  };

public class Clone {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return dfs(node, map);
    }
    private UndirectedGraphNode dfs(UndirectedGraphNode root, HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
        UndirectedGraphNode newRoot = map.get(root);
        if(newRoot != null) {
            return newRoot;
        }
        newRoot = new UndirectedGraphNode(root.label);
        map.put(root, newRoot);
        for(UndirectedGraphNode n : root.neighbors) {
            newRoot.neighbors.add(dfs(n, map));
        }
        return newRoot;
    }
    public static void main(String [] args){
        Clone sol = new Clone();
        UndirectedGraphNode root = new UndirectedGraphNode(0);
        UndirectedGraphNode root2 = new UndirectedGraphNode(1);
        root.neighbors.add(root2);
        root2.neighbors.add(root);
        System.out.println(sol.cloneGraph(root));
    }
}
