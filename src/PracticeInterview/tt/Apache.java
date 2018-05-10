package Practical.tt;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by program on 11/19/2017.
 */
public class Apache {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner in = new Scanner(System.in);
        List<Parse> arraylist = new ArrayList<>();
        while(in.hasNextLine()) {
             arraylist.add(new Parse(in.nextLine()));
        }
        Collections.sort(arraylist, (p1, p2) -> {
            if(p1.time == p2.time) {
                return p1.uir.compareTo(p2.uir);
            } else {
                return p1.time.compareTo(p2.time);
            }
        });

        int cur = 0;
        int total = 1;
        Parse pre = arraylist.get(0);
        for(int i = 0; i < arraylist.size(); i ++) {
            Parse p = arraylist.get(i);
            String curTime = p.time;
            if(p.time.equals(pre.time) && p.uir.equals(pre.uir)) {
                if(p.non500) {
                    cur++;
                }
                total ++;
            } else {
                System.out.print(p.print());
                System.out.println(cur * 1.0 / total);
                cur = p.non500 ? 1 : 0;
                total = 1;
            }
            pre = p;
        }
    }

    static class Parse {
        String original = "";
        String time = "";
        String uir = "";
        boolean non500 = false;


        public String print() {
            return time + " " + uir + " ";
        }
        public Parse(String original) {
            this.original = original;
            String[] s = original.split(" ");
            time = dateparse(s[3] + " " + s[4]);
            uir = getURI(s[6]);
            non500 = isNon500(s[8]);
//            System.out.println(non500);
//            System.out.println(uir);
//            System.out.println(time);

        }
        public boolean isNon500(String s) {
            return s.charAt(0) != '5';
        }
        private String getURI(String s) {
            int mark = 0;
            for(int i = 0; i < s.length(); i ++) {
                if(s.charAt(i) == '?') {
                    mark = i;
                    break;
                }
            }
            return s.substring(0, mark);
        }


        public String dateparse(String s) {
            SimpleDateFormat ForMatter = new SimpleDateFormat("yyyy/MM/dd'T'hh:mm");
            ForMatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            SimpleDateFormat formatter = new SimpleDateFormat("'['dd/MMM/yyyy:HH:mm:ss Z']'", Locale.US);
            Date date = new Date();
            String res = new String();
            try {
                date = formatter.parse(s);
                res = ForMatter.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return res;
        }

    }
}
