package com.tsystems.javaschool.tasks.subsequence;

import java.util.Iterator;
import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List<Object> x, List<Object> y) {
        if (x == null || y == null) throw new IllegalArgumentException();
        if (x.isEmpty()) {
            return true;
        }
        int foundXs = 0;
        Iterator<Object> xIterator = x.iterator();
        Object currXElem = xIterator.next();
        for (int i = 0; i < y.size() && foundXs < x.size(); i++) {
            if (currXElem.equals(y.get(i))) {
                foundXs++;
                if (xIterator.hasNext()) {
                    currXElem = xIterator.next();
                }
            }
        }
        return foundXs == x.size();
    }

//    public boolean find(List<Object> x, List<Object> y) {
//        if (x == null || y == null) {
//          throw new IllegalArgumentException();
//    }
//        List<Object> second = y.stream()
//                .filter(x::contains)
//                .distinct()
//                .collect(toList());
//        return x.equals(second);
//    }
}
