package Assignment4;
// Name: Fan Zhang
// USC NetID: 1417685115
// CS 455 PA4
// Fall 2017
/**
 * Created by program on 11/14/2017.
 */
public class StringRating {

    // the original string
    private String rawString = "";
    private int score;

    public StringRating(String input) {
        this.rawString = input;
        // get score immediately when the instance is created
        this.score = ScoreTable.getScore(input);
    }

    /**
     * This method is used for return original stirng
     * @return original string
     */
    public String getString () {
            return rawString;
    }

    /**
     * This method is for returning the score of the string
     * @return the score of the string
     */
    public int getScore() {
        return score;
    }

    /**
     * This method is used for returning the format in print
     * @return the format of printing
     */
    public String toString() {
        return  score + ": " + rawString;
    }
}
