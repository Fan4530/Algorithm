package Assignment4;
// Name: Fan Zhang
// USC NetID: 1417685115
// CS 455 PA4
// Fall 2017

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {

   // HashMap:
   // Key: the Anagram of string
   // Value: all the Anagram
   HashMap<AnagramFactory, ArrayList<String>> map;
   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
         Scanner in = new Scanner(new File(fileName));
         map = new HashMap<AnagramFactory, ArrayList<String>>();
         while (in.hasNext()) {
            String s = in.next();
            AnagramFactory anagram = new AnagramFactory(s);
            ArrayList<String> anagramPool = map.get(anagram);
            if (anagramPool == null) {
               anagramPool = new ArrayList<String>();
               map.put(anagram, anagramPool);
            }
            anagramPool.add(s);
         }
         int i = 0;
   }
   

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) {
      return map.get(new AnagramFactory(s));
   }
}
