package Interview.PocketGem;
import java.util.*;
class UndirectedGraphNode {
     int label;
     List<UndirectedGraphNode> neighbors;
     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 };
public class graph_clone {
	// BFS
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        UndirectedGraphNode root = new UndirectedGraphNode(node.label);
        map.put(node, root);
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode cur_node = queue.poll();
            for (UndirectedGraphNode n : cur_node.neighbors) {
                if (!map.containsKey(n)) {
                    map.put(n, new UndirectedGraphNode(n.label));
                    queue.add(n);
                }
                map.get(cur_node).neighbors.add(map.get(n));
            }
        }
        return root;
    }
    // DFS
    
    public UndirectedGraphNode helper(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> look_up) {
        if(node == null)
            return null;
        if(look_up.containsKey(node)) {
            return look_up.get(node);
        }
        UndirectedGraphNode new_node = new UndirectedGraphNode(node.label);
        look_up.put(node, new_node);
        for(UndirectedGraphNode neighbor : node.neighbors) {
            new_node.neighbors.add(helper(neighbor, look_up));
        }
        return new_node;
    }
    
    public UndirectedGraphNode cloneGraph_DFS(UndirectedGraphNode node) {
        if(node == null)
            return null;
        HashMap<UndirectedGraphNode, UndirectedGraphNode> look_up = new HashMap<>(); 
        return helper(node, look_up);
    }
}
