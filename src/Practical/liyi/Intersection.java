package Practical.liyi;

import java.util.*;

public class Intersection {
	//assumption: sorted or non sorted?? prefer optimized space or time.
    //assumption: no duplicate
	//method 1: two hashSet  used for non sorted, (if have duplicate use hashmap)
    //time complexity: O(n)
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                intersect.add(nums2[i]);
            }
        }
        int[] result = new int[intersect.size()];
        int i = 0;
        for (Integer num : intersect) {
            result[i++] = num;
        }
        return result;
    }
    //method 2: if sorted this one is better,
    //assumption: for sorted, prefer space optimized, or non-duplicate
        public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);// if sorted ,remove these two lines
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }

    //Follow up: have duplicate
    //can still be solved by two pointers: O(nlogn)
    //or hashmap
     public int[] intersectDuplicate(int[] nums1, int[] nums2) {
        //hashmap: key: value    value: frq
        // step 1: count frq of number in nums1
        // step 2: check key in nums2, if is in the nums2, then frq -- , and add to the res 
        if(nums1 == null || nums2 == null) {
            return new int[]{};
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums1.length; i ++) {
            Integer frq = map.get(nums1[i]);
            if(frq == null) {
                map.put(nums1[i], 1);
            } else {
                map.put(nums1[i], frq + 1);
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums2.length; i ++) {
            Integer frq = map.get(nums2[i]);
            if(frq != null && frq >= 1) {
                map.put(nums2[i], frq - 1);
                res.add(nums2[i]);
            }
        }
        int [] result = new int[res.size()];
        for(int i = 0; i < res.size(); i ++) {
            result[i] = res.get(i);
        }
        return result;
    }


//    总结： 排序好的往two pointers想。  甚至可以binary search。 disk上要用hashmap小的。 俩都在disk上得external sort用two pointers。
//    What if nums1's size is small compared to nums2's size? Which algorithm is better?
//    If has been sorted:
//    if sorted, can binary search klogn
//
//    If has not been sorted,
//    use hashmap: n + m
//    two pointers: nlogn + mlogm + n + m, is slower
//
//
//    One is huge(can only store in disk)
//    If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.
//
//	Both are huge(can only store in disk)太大的话不能一下子sort完
//	If both nums1 and nums2 are so huge that neither fit into the memory, 【sort them individually (external sort)】, then read 2 elements from each array at a time in memory, record intersections.

    //Union: similar to two pointers of intersection, can deal with duplicate
    // time : O(n logn)
    public int[] union(int[] nums1, int[] nums2) {
        List<Integer> set = new ArrayList<>();
        Arrays.sort(nums1);// if sorted ,remove these two lines
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                set.add(nums1[i]);
                i++;
            } else if (nums1[i] > nums2[j]) {
                set.add(nums2[j]);
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        while(i < nums1.length) {
            set.add(nums1[i++]);
        }
        while(j < nums2.length) {
            set.add(nums2[j++]);
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }
    public static void main(String [] args) {
        Intersection sol = new Intersection();
        System.out.println(Arrays.toString(sol.union(new int[]{1,2,2,3,4}, new int[]{1,2,2,2,5})));
    }
}