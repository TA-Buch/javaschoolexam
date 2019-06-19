package com.tsystems.javaschool.tasks.pyramid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        // TODO : Implement your solution here
        LinkedList<Integer> sortedNumbers = inputNumbers.stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedList::new));
        int level = getLevel(sortedNumbers.size());
        int base = 2 * level - 1;
        int[][] pyramid = new int[level][base];
        Iterator<Integer> sortNumIter = sortedNumbers.iterator();
        for (int i = 0; i < level; i++) {
            int startPos = level - (i + 1);
            int stopPos = level + i;
            for (int j = startPos; j <= stopPos; j += 2) {
                pyramid[i][j] = sortNumIter.next();
            }
        }
        return pyramid;
    }

    private int getLevel(int sizeList) {
        int levelPyramid = 0;
        for(int i =1; i <= sizeList; i += levelPyramid) {
            levelPyramid++;
        }
        return levelPyramid;
    }

}
