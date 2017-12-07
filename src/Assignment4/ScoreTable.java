package Assignment4;
// Name: Fan Zhang
// USC NetID: 1417685115
// CS 455 PA4
// Fall 2017


/**
 * This class is used to give score for a given string.
 */
public class ScoreTable {
    // this is the rule for scoring, it belongs to the class(all the ), so we use static
    //            (1 point)-A, E, I, O, U, L, N, S, T, R
    //            (2 points)-D, G
    //            (3 points)-B, C, M, P
    //            (4 points)-F, H, V, W, Y
    //            (5 points)-K
    //            (8 points)- J, X
    //            (10 points)-Q, Z
    //             'a' and 'A' will have the same score

    private static final int[] table = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
     /**
          This method is used to getScore for a given string.
      @param s given string
      @return

     */
	public static int getScore(String s) {
	    int sum = 0;
        for(int i = 0; i < s.length(); i ++) {
            // change to int value according to the ASIC II
            int c = (int)(s.charAt(i) - 'a' );
            // if the char is upper character, change it to lower character
            c = c < 0 ? c + ('a' - 'A') : c;
            sum += table[c];
        }
        return sum;
    }
}

