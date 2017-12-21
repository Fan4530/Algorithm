package Interview.Uber;

/**
 * Created by program on 12/18/2017.
 */
public class Transaction {
    public static String transactionMerge(String s1, String s2) {
        String [] str1 = s1.split(" ");
        String [] str2 = s2.split(" ");

        int i1 = Integer.valueOf(str1[1]);
        int i2 = Integer.valueOf(str2[1]);
        StringBuilder sb = new StringBuilder();
        if(str1[0].equals(str2[0])) {
            return sb.append(str1[0]).append(" ").append(i1 + i2).toString();
        } else if(i1 == i2) {
            return "";
        } else if(i1 > i2) {
            return sb.append(str1[0]).append(" ").append(i1 - i2).toString();
        } else {
            return sb.append(str2[0]).append(" ").append(i2 - i1).toString();
        }
    }
    public static void main(String [] args) {
        System.out.println(transactionMerge("BOA->CHASE 20", "CHASE->BOA 20"));
    }

}
