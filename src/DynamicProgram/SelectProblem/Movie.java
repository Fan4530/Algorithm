package DynamicProgram.SelectProblem;

/**
 * Created by program on 10/20/2017.
 */
public class Movie {
    public static int movie(int[] input) {
        int selected = input[0];
        int netSelected = 0;
        for (int i = 1; i < input.length; i++) {
            int tmp = selected;
            selected = Math.max(selected, netSelected) + input[i];
            netSelected = tmp;
        }
        return Math.max(selected, netSelected);
    }
    public static void main(String [] args) {
        Movie sol = new Movie();
    //    System.out.println(sol.movie(new int[]{}));
        System.out.println(sol.movie(new int[]{1}));
        System.out.println(sol.movie(new int[]{-1}));
        System.out.println(sol.movie(new int[]{1, 2}));
        System.out.println(sol.movie(new int[]{1, -3}));
        System.out.println(sol.movie(new int[]{1, 2, 3}));
        System.out.println(sol.movie(new int[]{1, 2, -1}));
        System.out.println(sol.movie(new int[]{1, 2, -1, -2}));
        System.out.println(sol.movie(new int[]{1, 2, -2, -1}));
        System.out.println(sol.movie(new int[]{1, 2, -1, -2, 2}));
        System.out.println(sol.movie(new int[]{1, 2, -2, -1, 2}));
        System.out.println(sol.movie(new int[]{-4, -2, -1, -6}));
    }
}
