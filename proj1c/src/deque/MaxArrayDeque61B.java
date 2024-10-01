package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61BB<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque61B(Comparator<T> t) {
        comparator = t;
    }

    public T max() {
        if (size() == 0) {
            return null;
        }

        //initialize the tMax
        T tMax = getFirst();


        for (T item : this){
            if (comparator.compare(tMax,item) < 0){
                tMax = item;
            }
        }

        return tMax;
    }

    public T max(Comparator<T> t){
        if (size() == 0) {
            return null;
        }

        //initialize the tMax
        T tMax = get(1);


        for (T item : this){
            if (t.compare(tMax,item) < 0){
                tMax = item;
            }
        }

        return tMax;
    }


}
