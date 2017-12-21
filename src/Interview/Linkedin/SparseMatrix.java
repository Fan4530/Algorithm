package Interview.Linkedin;

public class SparseMatrix {

	//assumption: A, B is not empty and null
	public int [][] sparseMatrix(int [][] A, int [][] B) {

		int n = A.length;
		int m = A[0].length;
		int l = B[0].length;
		int [][] res = new int[n][l];
		for(int i = 0; i < n; i ++) {
			for(int j = 0; j < m; j ++) {
				if(A[i][j] != 0) {// we can find that A[i]][j] is none business of the k, so we can prune
					for(int k = 0; k < l; k ++) {
						res[i][k] += A[i][j] * B[j][k];
					}
				}
			}
		}
		return res;

	}
}