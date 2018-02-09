package Practical.pg;

/**
 * Created by program on 11/21/2017.
 */
public class triangle {
    // no overflow
    // double or int
    // diff, when in the
    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String [] args) {
        Point a = new Point(0,0);
        Point b = new Point(200,0);
        Point c = new Point(0,200);
        Point p = new Point(100, 100);
        triangle sol = new triangle();
        System.out.println(sol.triangle(a, b, c, p));
    }
    public boolean triangle(Point a, Point b, Point c, Point p) {
        double area = getArea(a, b, c);
        double area1 = getArea(p, b, c);
        double area2 = getArea(a, p, c);
        double area3 = getArea(a, b, p);
        double diff = Math.abs(area - area1 - area2 - area3);
        return diff < 0.01;
    }
    public double getArea(Point a, Point b, Point c) {
        double len1 =  getLength(a, b);
        double len2 =  getLength(c, b);
        double len3 =  getLength(a, c);
        double s = (len1 + len2 + len3) / 2;
        return Math.sqrt(s * (s - len1) * (s - len2) * (s - len3));
    }
    public double getLength(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}
