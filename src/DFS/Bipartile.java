package DFS;

import java.util.*;

/**
 * Created by program on 10/13/2017.
 */

public class Bipartile {
    public boolean isBipartite(List<GraphNode> graph) {
        HashMap<GraphNode, Integer> map = new HashMap<>();
        for(GraphNode n : graph) {

                if(!BFS(map, n)) {
                    return false;
                }

        }
        return true;
    }


    private boolean BFS(HashMap<GraphNode, Integer> map, GraphNode node) {
        if(map.containsKey(node)) {
            return true;
        }
        Queue<GraphNode> q = new LinkedList<>();
        q.offer(node);
        map.put(node, 0);
        while(!q.isEmpty()) {
            GraphNode n = q.poll();
            Integer curGroup = map.get(n);
            int neiGroup = curGroup == 0 ? 1 : 0;
            for(GraphNode nei : n.neighbors) {
                Integer group = map.get(nei);
                if(group == null) {
                    map.put(n, neiGroup);
                    q.offer(nei);
                } else if(group != neiGroup) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String [] args) {
        Bipartile sol = new Bipartile();
        GraphNode node1 = new GraphNode(1);
        GraphNode node0 = new GraphNode(0);
        node1.neighbors.add(node0);
      //  node0.neighbors.add(node1);
        ArrayList<GraphNode> list = new ArrayList<>();
        list.add(node0);
        list.add(node1);
        System.out.println(sol.isBipartite(list));
    }
}
