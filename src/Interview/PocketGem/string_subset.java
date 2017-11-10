package Interview.PocketGem;
import java.util.*;
public class string_subset {
		
    public static boolean strStr(String source, String target) {
        if(target.length() == 0)
            return false;
        for(int i = 0; i <= source.length() - target.length(); i++) {
            int j = 0;
            while(i+j < source.length() && j < target.length() && source.charAt(i+j) == target.charAt(j)) {
                j++;
            }
            if(j == target.length())
                return true;
        }
        return false;
    }
	public static List<List<String>> partion(String[] input) {
		string_subset s = new string_subset();
		Arrays.sort(input, new Comparator<String>() {
			public int compare(String e1, String e2) {
				return Integer.compare(e1.length(), e2.length());
			}
		});
		List<List<String>> res = new LinkedList<>();
		int[][] matrix = new int[input.length][input.length];
		for(int i = 0; i < input.length; i++) {
			for(int j = i+1; j < input.length; j++) {
				if(strStr(input[j], input[i])) {
					matrix[i][j] = 1;
					matrix[j][i] = 1;
				}
			}
		}
		boolean[] visited = new boolean[input.length];
		for(int i = 0; i < matrix.length; i++) {
			if(!visited[i]) {
				List<String> path = new LinkedList<>();
				dfs(matrix, i, visited, path, input);
				res.add(path);
			}
		}
		return res;
	}
	
	public static void dfs(int[][] matrix, int index, boolean[] visited, List<String> path, String[] input) {
		visited[index] = true;
		path.add(input[index]);
		for(int i = 0; i < matrix.length; i++) {
			if(matrix[index][i] == 1 && !visited[i]) {
				dfs(matrix, i, visited, path, input);
			}
		}
	}
	//union found
	
    class UnionFind {
        private int count = 0;
        private int[] parent, rank;
        
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int p) {
        	while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }
        
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (rank[rootQ] > rank[rootP]) {
                parent[rootP] = rootQ;
            }
            else {
                parent[rootQ] = rootP;
                if (rank[rootP] == rank[rootQ]) {
                    rank[rootP]++;
                }
            }
            count--;
        }
        
        public int count() {
            return count;
        }
    }
    
    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) uf.union(i, j);
            }
        }
        return uf.count();
    }
	
	public static void main(String[] args) {
		String[] input = {"a", "aaa", "bz", "bba"};
		string_subset s = new string_subset();
		List<List<String>> res = s.partion(input);
		System.out.println(res);
	}
}
