package Algorithm.Interview.Linkedin;

/**
 * Created by program on 11/15/2017.
 */
public class Flower {
    // next and prev, if next and prev are both 0, then count ++, and set this position to 1
    //
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for(int i = 0; i < flowerbed.length; i ++) {
            if(flowerbed[i] == 0) {
                // corner case, the first position and the final position
                // both should be set to 0
                int next = (i + 1 == flowerbed.length) ? 0 : flowerbed[i + 1];
                int prev = (i == 0) ? 0 : flowerbed[i - 1];
                if(next == 0 && prev == 0) {
                    count += 1;
                    flowerbed[i] = 1;
                }
            }
        }
        return count >= n;
    }
}
