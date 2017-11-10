package Algorithm.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by program on 10/13/2017.
 */

  public class GraphNode {
    public int key;
    public List<GraphNode> neighbors;
    public GraphNode(int key) {
      this.key = key;
      this.neighbors = new ArrayList<GraphNode>();
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GraphNode graphNode = (GraphNode) o;

    if (key != graphNode.key) return false;
    return neighbors != null ? neighbors.equals(graphNode.neighbors) : graphNode.neighbors == null;
  }

  @Override
  public int hashCode() {
    int result = key;
    result = 31 * result + (neighbors != null ? neighbors.hashCode() : 0);
    return result;
  }
}
