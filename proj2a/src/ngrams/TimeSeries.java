package ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // TODO: Fill in this constructor.
        SortedMap<Integer, Double> map = ts.subMap(startYear, endYear);
        this.putAll(map);
        this.put(endYear,ts.get(endYear));

    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        List<Integer> list = new ArrayList<>();
        Set<Integer> sets = this.keySet();
        for (int set : sets) {
            list.add(set);
        }
        return list;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        List<Double> list = new ArrayList<>();
        Set<Integer> sets = this.keySet();//找出所有键
        for (int set : sets) {
            list.add(get(set));
        }
        return list;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries n = new TimeSeries();

        //把this的键值对传进新对象
        n.putAll(this);

        Set<Integer> sets = ts.keySet();//存储所有的键
        for (int set : sets) {
            if (this.containsKey(set)) {
                n.put(set,this.get(set) + ts.get(set));
            } else {
                n.put(set, ts.get(set));
            }
        }
        return n;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries n = new TimeSeries();
        Set<Integer> set = this.keySet();

        for (int se : set) {
            if (ts.containsKey(se)) {
                n.put(se,this.get(se) / ts.get(se));
            } else {
                throw new IllegalArgumentException("Year " + se + " is missing.");
            }
        }
        return n;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
