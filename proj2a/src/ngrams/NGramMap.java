package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.*;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;
import static utils.Utils.SHORT_WORDS_FILE;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 * <p>
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    private class generalSeries extends HashMap<String, TimeSeries> {
        public generalSeries() {
            super();
        }
    }

    private generalSeries root; //存储top部分的内容
    private TimeSeries page; //存储total_counts部分内容

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.

        //处理total_count
        page = new TimeSeries();
        In in = new In(countsFilename);
        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] spiltLine = nextLine.split(",");
            int index = Integer.parseInt(spiltLine[0]);
            double res = Long.parseLong(spiltLine[1]);
            page.put(index, res);
        }

        //处理top文件，采用封装后的数据结构，文件名-TimeSeries的形式；
        root = new generalSeries();
        In inWord = new In(wordsFilename);
        while (inWord.hasNextLine()) {
            String nextLine = inWord.readLine();
            String[] spiltLine = nextLine.split("\t");
            String name = spiltLine[0];
            int year = Integer.parseInt(spiltLine[1]);
            double count = Long.parseLong(spiltLine[2]);
            if (!root.containsKey(name)) {
                root.put(name, new TimeSeries());
                root.get(name).put(year, count);
            } else {
                root.get(name).put(year, count);
            }
        }


    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries n = new TimeSeries();
        if (root.containsKey(word)) {
            n = new TimeSeries(root.get(word), startYear, endYear);
        }
        return n;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries n = new TimeSeries();
        if (root.containsKey(word)) {
            n.putAll(root.get(word));
        }
        return n;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        return page;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries n = new TimeSeries();
        n = new TimeSeries(root.get(word), startYear, endYear);
        Set<Integer> sets = n.keySet();
        for (int set : sets) {
            n.put(set, n.get(set) / page.get(set));
        }
        return n;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries n = new TimeSeries();
        n.putAll(root.get(word));
        Set<Integer> sets = n.keySet();
        for (int set : sets) {
            n.put(set, n.get(set) / page.get(set));
        }
        return n;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries n = new TimeSeries();
        for (String word : words) {
            //存储单个word的数据结构
            TimeSeries w = new TimeSeries();
            w = new TimeSeries(root.get(word), startYear, endYear);
            Set<Integer> sets = w.keySet();
            for (int set : sets) {
                w.put(set, w.get(set) / page.get(set));
            }
            TimeSeries tempt = w.plus(n);
            n = tempt;
        }
        return n;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        TimeSeries n = new TimeSeries();
        for (String word : words) {
            //存储单个word的数据结构
            TimeSeries w = new TimeSeries();
            w.putAll(root.get(word));
            Set<Integer> sets = w.keySet();
            for (int set : sets) {
                w.put(set, w.get(set) / page.get(set));
            }
            TimeSeries tempt = w.plus(n);
            n = tempt;
        }
        return n;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
