import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /**
     * Returns the total sum in a list of integers
     */
    public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int sum = 0;
        for (int li : L) {
            sum += li;
        }
        return sum;
    }

    /**
     * Returns a list containing the even numbers of the given list
     */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        return null;
    }

    /**
     * Returns a list containing the common item of the two given lists
     */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        //Create a new list
        List<Integer> list = new ArrayList<>();

        // Based on L1, for-range l2
        for( int L : L1){
            for(int Ll: L2){
                if(L == Ll){
                    list.add(L);
                }
            }
        }


        return list;
    }


    /**
     * Returns the number of occurrences of the given character in a list of strings.
     */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        //Set the base of number of char
        int sum = 0;

        //For range the words, and one by one compare the results
        for(String word : words){
            for (int i = 0; i < word.length(); i++) {
                if(word.charAt(i) == c){
                    sum++;
                }
            }
        }



        return sum;
    }
}
