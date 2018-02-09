package Practical.liyi;

/**
 * Created by program on 12/8/2017.
 */
public class Serilize {
    public static TreeNode deserialize(String data) {
        String [] input = data.split(",");
        return helperDecode(input);
    }
    static int index = 0;
    private static TreeNode helperDecode(String [] input) {
        if(input[index].equals("NULL")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(input[index]));
        index ++;
        root.left = helperDecode(input);
        index++;
        root.right = helperDecode(input);
        return root;

    }
    public static void main(String [] args) {
        deserialize("1,NULL,2");
    }
}
