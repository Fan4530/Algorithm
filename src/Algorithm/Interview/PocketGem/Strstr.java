package Algorithm.Interview.PocketGem;

public class Strstr {
    //assumption: if needle == "", then reutrn 0
    // both are not null
    //
    public int strStr(String large, String small) {
//        if(small.length() == 0) {// by i <= large - small, don't need to check this one
//            return 0;
//        }
        if(small.length() > large.length()) {
            return -1;
        }
        large.indexOf(small);
        // suppose the same length
        for(int i = 0; i <= large.length() - small.length(); i ++) {
            if(checkAt(i, large, small)) {
                return i;
            }
        }
        return -1;
    }
    private  boolean checkAt(int index, String large, String small) {
        for(int i = 0; i < small.length(); i ++) {
            if(large.charAt(i + index) != small.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
}
