public class Search2D {
        // order 
        //[
        //   [1,   3,  5,  7],
        //   [10, 11, 16, 20],
        //   [23, 30, 34, 50]
        // ]
    //time : O(2 log n)
    public boolean searchMatrix1D(int[][] matrix, int target) {
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    int n = matrix.length;
    int m = matrix[0].length;
    int l = 0;
    int r = n * m - 1;
    //mid / col
    //mid % col
    while(l <= r) {
        int mid = l + (r - l) / 2;
        int row = mid / m;
        int col = mid % m;
        if(matrix[row][col] == target) {
            return true;
        } else if(matrix[row][col] < target) {
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    return false;
    }

//    [
//      [1,   4,  7, 11, 15],
//      [2,   5,  8, 12, 19],
//      [3,   6,  9, 16, 22],
//      [10, 13, 14, 17, 24],
//      [18, 21, 23, 26, 30]
//    ]
    // time : O(n)
    public boolean searchMatrix2D(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int j = matrix[0].length - 1;
        int i = 0;
        
        while(i < matrix.length && j >= 0) {
            if(matrix[i][j] == target) {
             return true;   
            }else if(matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    
    }
}     

