package Interview.Linkedin;

public class ThreePointers {
//
//    //assumption: sorted
//
//    // A: 1 4 10
//		         a
//    // B: 2 15 20
//            b
//    // C: 10 12
         
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