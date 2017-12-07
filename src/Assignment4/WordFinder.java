package Assignment4;
// Name: Fan Zhang
// USC NetID: 1417685115
// CS 455 PA4
// Fall 2017
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by program on 11/14/2017.
 */
public class WordFinder {
    public static void main (String[] args) {
        String fileName = "src\\Assignment4\\sowpods.txt";
        try {
            if(args != null && args.length != 0) {
                fileName = args[0];
            }
            // build dictionary
            AnagramDictionary dict = new AnagramDictionary(fileName);
            System.out.println ("Type . to quit.");
            // fetch the target strings that need to be matched
            Scanner in = new Scanner (System.in);
            while (true) {
                System.out.print("Rack? ");
                String input = in.next();
                if (input.equals(".")) {
                    break;
                }

                List<String> res = getFinalRes(input, dict);
                printHeader(input, res);
                List<StringRating> finalRes = sort(res);
                printRes(finalRes);
            }
        } catch (FileNotFoundException e) {
            System.out.println ("Error: File Not Found: " + fileName);
        }
    }


    /**
     * This method is for printing the summary of matched results
     * @param s: input string
     * @param res: final results
     */
    private static void printHeader(String s, List<String> res) {
        System.out.println ("We can make " + res.size() + " words from \"" + s + "\"");
        System.out.println ("All of the words with their scores (sorted by score):");
    }


    /**
     * This method is used for getting all anagram of the dic for the word
     * @param word the target word
     * @param dict built dictionary
     * @return the matched string res
     */
    private static List<String> getFinalRes(String word, AnagramDictionary dict) {
        //all subsets of the word
        List<String> rack = Rack.allSubsets(word);
        //the final results: the Anagram of all of the word in rack
        List<String> res = new ArrayList<String> ();// to store the final result of the match words
        for(int i = 0; i < rack.size(); i ++) {
            List<String> cur = dict.getAnagramsOf(rack.get(i));
            if(cur != null) {
                res.addAll(cur);
            }
        }
        return res;
    }


    /**
     * This method is used for sorting the res according to score in descending order and alphbet aescending order
     * @param res : input the res
     * @return return StringRating.
     */
    private static List<StringRating> sort(List<String> res) {
        //create the list of StringRating instances
        List<StringRating> finalRes = new ArrayList<>();
        for(int i = 0; i < res.size(); i ++) {
            finalRes.add(new StringRating(res.get(i)));
        }
        //sort according to score and alphbeit
        Collections.sort(finalRes, new Comparator<StringRating>() {
            @Override
            public int compare(StringRating o1, StringRating o2) {
                if(o1.getScore() == o2.getScore()) {
                    return o1.getString().compareTo(o2.getString());
                } else {
                    return Integer.compare(o2.getScore(), o1.getScore());
                }
            }
        });
        return finalRes;
    }

    /**
     * Print all results: including score and matched string
     * @param res: StringRating res arraylist
     */
    private static void printRes(List<StringRating> res) {
        for(StringRating s: res) {
            System.out.println(s.toString());
        }
    }
}



