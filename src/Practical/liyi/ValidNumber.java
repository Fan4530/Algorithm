package Practical.liyi;

/**
 * Created by program on 11/8/2017.
 */
public class ValidNumber {
    public boolean validNumber (String s) {
        boolean seeNumber = false;
        boolean numberAfterPointer = true;
        boolean seePointer = false;
        s = s.trim();
        for(int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if(c == '.') {
                if(seePointer) {
                    return false;
                }
                numberAfterPointer = false;
                seePointer = true;
            } else if(Character.isDigit(c)) {
                seeNumber = true;
                numberAfterPointer = true;
            } else {
                return false;
            }
        }
        return seeNumber && numberAfterPointer;

    }
    public boolean validNumber2(String s) {
        boolean seeNumber = false;
        boolean seeE = false;
        boolean numberAfterE = true;
        boolean numberAfterPointer = true;
        boolean seePointer = false;
        s = s.trim();
        for(int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if(c == '.') {
                if(seePointer) {
                    return false;
                }
                numberAfterPointer = false;
                seePointer = true;
            } else if(c == '+' || c == '-') {
                if(i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else if(c == 'e') {
                if(seeE || !seeNumber) {
                    return false;
                }
                seeE = true;
                numberAfterE = false;
            } else if(Character.isDigit(c)) {
                seeNumber = true;
                numberAfterPointer = true;
            } else {
                return false;
            }
        }
        return seeNumber && numberAfterPointer && numberAfterE;
    }


    public static void main(String [] args) {
        ValidNumber sol = new ValidNumber();
        System.out.println(sol.validNumber(" 333.3.3"));
        System.out.println(sol.validNumber(" 3. 3"));
        System.out.println(sol.validNumber(" a"));
        System.out.println(sol.validNumber(" 0.0"));
    }
}
