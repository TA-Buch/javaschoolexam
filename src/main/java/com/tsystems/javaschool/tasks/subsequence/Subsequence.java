package com.tsystems.javaschool.tasks.subsequence;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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
        if (x.size() > y.size()) {
            return false;
        }
        int foundXs = 0;
        Iterator<Object> xIterator = x.iterator();
        Object currXElem = xIterator.next();
        for (int i = 0; i < y.size() && foundXs < x.size(); i++) {
            if (Objects.equals(currXElem, y.get(i))) {
                foundXs++;
                if (xIterator.hasNext()) {
                    currXElem = xIterator.next();
                } else {
                    break;
                }
            }
        }
        return foundXs == x.size();
    }
}
