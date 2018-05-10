package Practical.pg;
import java.util.*;
public class MeetingRoom12 {
    // meeting room1
    // when will it be invalid? there is a cycle
    // use a array to record the pre request for each course
    //   -1     -1     -1       -1     -1  pre request, initial as - 1 means no prereques
    //   0      1       2       3       4  course
    // for loop to all the pre request list
    // [1,2]   [2,3]   [3,1]  this is invalid
    // For[1,2]  the point is: if we want to take course 1,
    // we need to take course 2, is 2 doesnt has pre request, then this is valid
    // and in the meantime, we mark that the prerequest of 1 is 2
    //  -1     2  -1    -1    -1
    //  0     1   2     3     4
    // For [2, 3], the pre of 2 is 3, we find 3 doesnt have pre, so it is valid
    // mark pre of 2 as 3
    // -1    2  3  -1  -1
    // 0    1   2   3  4
    // For [3, 1], the pre of 3 is 1,  we find 1 has a pre, go on to find 1 has a pre of 2, 2 has pre of 3,
    // since 3 need to take 3 first, so it is a loop, so it is not valid

    //time: O(n^2)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int [] pre = new int[numCourses];
        Arrays.fill(pre, -1);
        for(int [] onePre : prerequisites) {
            int from = onePre[1];
            int to = onePre[0];
            //find the pre of from
            while(pre[from] >= 0) {
                from = pre[from];
                if(from == to) {
                    return false;
                }
            }
            //mark
            pre[to] = onePre[1];// be careful, not from, from has changed,
        }
        return true;
    }




    // meeting room1
    public int[] findOrder(int n, int[][] pres) {
        int[][] matrix = new int[n][n];
        int[] indegree = new int[n];
        for (int[] pre : pres) {
            int p = pre[1];
            int r = pre[0];
            if (matrix[p][r] == 0)
                indegree[r]++;
            matrix[p][r] = 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                res.add(i);
            }
        }
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < n; i++) {
                if (matrix[cur][i] == 1) { 
                    if (--indegree[i] == 0) {
                        queue.offer(i);
                        res.add(i);
                    }

                }
            }
        }
        if (res.size() != n)
            return new int[]{};
        int[] ret = new int[n];
        int index = 0;
        for (int item : res) {
            ret[index++] = item;
        }
        return ret;
    }
}
