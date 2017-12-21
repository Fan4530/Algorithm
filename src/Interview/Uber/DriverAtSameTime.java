package Interview.Uber;

/**
 * Created by program on 12/20/2017.
 */
public class DriverAtSameTime {
    static class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String [] args) {
        Interval i1 = new Interval(1, 2);
        Interval [] input = new Interval[]{new Interval(1, 2), new Interval(1, 3), new Interval(3, 4)};
        System.out.println(driversNumber(input, 3));
    }
    public static int driversNumber(Interval [] intervals, int timestamps) {
        int count =  0;
        for(int i = 0; i < intervals.length; i ++) {
            if(timestamps >= intervals[i].start && timestamps <= intervals[i].end) {
                count++;
            }
        }
        return count;
    }
}
