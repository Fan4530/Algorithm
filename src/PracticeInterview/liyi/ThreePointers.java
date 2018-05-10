package Practical.liyi;

public class ThreePointers {
//
//    //assumption: sorted
//
//    // A: 1 4 10
//		         a
//    // B: 2 15 20
//            b
//    // C: 10 12
//Given three sorted arrays A[], B[] and C[], find 3 elements i, j and k from A, B and C respectively such that max(abs(A – B[j]), abs(B[j] – C[k]), abs(C[k] – A)) is minimized. Here abs() indicates absolute value.
//    Example :
//    Input: A[] = {1, 4, 10}  B[] = {2, 15, 20} C[] = {10, 12} Output: 10 15 10。 10 from A, 15 from B and 10 from C
//    Input: A[] = {20, 24, 100} B[] = {2, 19, 22, 79, 800} C[] = {10, 12, 23, 24, 119} Output: 24 22 23。24 from A, 22 from B and 23 from C
    public int [] threePointers (int [] A, int [] B, int [] C) {
    	int [] res = new int[3];
        int a = 0;
        int b = 0;
        int c = 0;
        int min = Integer.MAX_VALUE;
        int globalMin = min;
        while(a < A.length && b < B.length && c < C.length) {
            min = Math.abs(Math.abs(A[a] - B[b]) + Math.abs(C[c] - A[a]) +  Math.abs(B[b] - C[c]));
            if(min < globalMin) {
                res[0] = A[a];
                res[1] = B[b];
                res[2] = C[c];
                globalMin = min;
            }
            if(A[a] <= B[b] && A[a] <= C[c]) {
                a ++;
            } else if(B[b] <= A[a] && B[b] <= C[c]) {
                b++;
            } else if(C[c] <= A[a] && C[c] <= B[b]){
                c++;
            }
        }
        return res;
    }

}