package Practical.liyi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Interval{

	int end;
	int start;
	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
}
public class MeetingRoom {

	//
  	public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (Interval i1, Interval i2) -> Integer.compare(i1.start, i2.start));
        for(int i = 0; i < intervals.length - 1; i ++) {// Be careful
            if(intervals[i].end > intervals[i + 1].start ) {
                return false;
            }
        }
        return true;
    }
    // meeting room 2,  【max overlap】, minimum rooms required
    // time complexity: O(nlogn), sort

    public int minMeetingRooms(Interval [] intervals ) {
    	int n = intervals.length;
    	int [] start = new int[n];
    	int [] end = new int[n];
    	for(int i = 0; i < n; i ++) {
    		start[i] = intervals[i].start;
    		end[i] = intervals[i].end;
    	}
    	Arrays.sort(start);
    	Arrays.sort(end);
    	int res = 0;
    	int endIdx = 0;
    	// one start means need one more room, 
    	// one end mearns need one less room
		//  s     e    			s    e
		//             empty
    	//  s     s    e   e
		//     1     2   1     0
		//  s     s          s
		//             e          e
		//                   | the contribution of 3th start will cancel the previous minus 1, so just do nothing and move on
		//                     so just left index ++
		for(int i = 0; i < n; i ++) {
    		if(start[i] < end[endIdx]) {//be careful, it is endIdx, not i
    			res ++;
    		} else {// start[i] >= end[endIdx] means meet one end, so let endIdx, the room number minus 1; 
    			    //after that, start ++(i++), room number plus 1. So they will cancel 
    			endIdx ++;
    		}
    	}
    	return res;
  	}

  	// first while loop
  	// input.start <=  cur[i].end  terminated    ==> while(input.start > cur[i].end )
	//
	// s  e     s  e    |     s   e
	//                 s  e

	// s  e  |   s       e         s      e
	//           s  <-s     e


	// s  e       s    e  |     s     e
	//                     s     e

	//s   e    |  s       e     s      e

	//            s <- s          e

	//
	//
	// while(input.end >= cur[i].start)   merge,  input.start = min(cur[i].start, input.start);
	//
	// after loop: merge end,  input.end = max(cur[i].end, input.end),  add this to the list
	//
	// while add the other



  	//insert the new Interval
  	//time complexity O(n)
  	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int i = 0;
        List<Interval> res = new ArrayList<>();
        while(i < intervals.size() && newInterval.start > intervals.get(i).end) {
            res.add(intervals.get(i++));
        }
        //  here, first overlap, so in the next while, merge them 
        //     ----- 
        //         ------ 
        while(i < intervals.size() && newInterval.end >= intervals.get(i).start) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i++).end);
        }// here, -----   ---
        //        new      i
        res.add(newInterval);
        
        while(i < intervals.size()) {
            res.add(intervals.get(i++));
        }
        return res;
    } 

    // merge 
    public List<Interval> merge(List<Interval> intervals) {
    	if(intervals == null || intervals.size() == 0) {
    		return new ArrayList<>();
    	}
    	int n = intervals.size();
    	int [] starts = new int[n];
    	int [] ends = new int[n];
    	for(int i = 0; i < n ; i ++) {
    		starts[i] = intervals.get(i).start;
    		ends[i] = intervals.get(i).end;
    	}
    	Arrays.sort(starts);
    	Arrays.sort(ends);
    	List<Interval> res = new ArrayList<>();// be careful about the Interval, not integer
    	for (int i = 0, j = 0; i < n; i ++) {// be careful to initialize the
			// s               e
			//       s            e
			//                           s   e
    		if(i == n - 1 || starts[i + 1] > ends[i]) {
    			res.add(new Interval(starts[j], ends[i]));// j : the start of the new one, initialize as 0
    			j = i + 1;// the start of the the new one 
    		}
    	}
    	return res;
    }
  } 
