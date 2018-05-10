package PracticeInterview.Google;

//第一轮: 印度大叔 人很好
//定义 n-straight 指连续的n个数字, 输入是一个integer array 和n , 要求返回array 满不满足 n-straight 的要求:
//eg 3-straight [1,2,3,5,6,7]返回true, [1,2,3,4,5] 返回False
//follow up: 如果n-straight 指至少n个连续的数字, 应该怎么写函数
public class NStraight {
    public static void main(String [] agrs) {
        System.out.println(nStraight(new int[]{1,2,3,5,6,7,8}, 3));
        System.out.println(nStraight(new int[]{1,2,3,5,6,7,8}, 1));
        System.out.println(nStraight(new int[]{}, 3));
        System.out.println(nStraight(new int[]{1,3,5}, 1));


        System.out.println(atLeastNStraight(new int[]{1,3,5,6}, 3));

    }
    public static boolean nStraight(int [] array, int n) {
        int count = n - 1;

        for(int i = 0; i < array.length - 1; i ++) {
            if(Math.abs(array[i + 1] - array[i]) == 1) {
                count --;
            } else {
                count = n - 1;
            }
            if (count < 0) {
                return false;
            }
        }
        return true;
    }

    // 【待检验】
    public static boolean atLeastNStraight(int [] array, int n) {
        int count = n - 1;

        for(int i = 0; i < array.length - 1; i ++) {
            if(Math.abs(array[i + 1] - array[i]) == 1) {
                count --;
            } else {
                if(count > 0) {
                    return false;
                } else {
                    count = n - 1;
                }
            }

        }
        return true;
    }


}
