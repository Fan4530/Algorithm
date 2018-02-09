package Practical.fineb;

/**
 * Created by program on 1/31/2018.
 */
public class UnionAndIntersection {
    class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public Interval union(Interval [] intervals) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for(int i = 0; i < intervals.length; i ++) {
            min = Math.min(min, intervals[i].start);
            max = Math.max(max, intervals[i].end);
        }
        if(min <= max) {
            return new Interval(min, max);
        } else {
            return null;
        }
    }

}
