package com.tsystems.javaschool.tasks.pyramid;

import java.util.*;
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
    public int[][] buildPyramid(List<Integer> inputNumbers) throws CannotBuildPyramidException {
        List<Integer> sortedInputNumbers;
        /*catches errors (like OutOfMemoryError) caused by very large input lists
        *and NullPointerException caused by the presence of null in the input list*/
        try {
            sortedInputNumbers = inputNumbers.stream()
                    .sorted()
                    .collect(Collectors.toList());
        } catch (Throwable t) {
            throw new CannotBuildPyramidException();
        }
        int height = getPyramidHeight(sortedInputNumbers.size());
        int width = 2 * height - 1;
        return createPyramid(height, width, sortedInputNumbers);
    }

    private static int getPyramidHeight(int numberOfElements) throws CannotBuildPyramidException {
        int height = 0;
        int i = 1;
        for (; i <= numberOfElements; i += height) {
            height++;
        }
        if (i > numberOfElements + 1) {                  // not enough numbers to build pyramid
            throw new CannotBuildPyramidException();
        }
        return height;
    }

    private static int[][] createPyramid(int height, int width, List<Integer> numbers) {
        int[][] pyramid = new int[ height][width];
        // assuming the list is already sorted
        Iterator<Integer> sortIter = numbers.iterator();
        for (int i = 0; i <  height; i++) {
            int startPos =  height - (i + 1);             // the leftmost position of the elements on the i-th level
            int stopPos =  height + i;                    // the rightmost position of the elements of the i-th level
            for (int j = startPos; j <= stopPos; j += 2) {
                pyramid[i][j] = sortIter.next();
            }
        }
        return pyramid;
    }
}
