package Practical.fineb;

/**
 * Created by program on 1/31/2018.
 */
public class Pi {
    public static double calculatePi() {
        int i = 10000000;
        int total = i;
        long count = 0;
        while(i -- > 0) {
            double x = Math.random();
            double y1 = Math.sqrt(1 - x * x);
            double y2 = Math.random();
            if(y1 > y2) {
                count ++;
            }
        }
        double area = count * 1.0 / total;
        return area * 4;
    }
    public static void main(String [] agrs) {
        System.out.println(calculatePi());
    }
}
