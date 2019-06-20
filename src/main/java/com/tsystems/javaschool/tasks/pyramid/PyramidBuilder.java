package com.tsystems.javaschool.tasks.pyramid;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


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
        try {
            sortedInputNumbers = inputNumbers.stream().sorted().collect(Collectors.toList());
        } catch (Throwable t) {                         // catches errors (like OutOfMemoryError)
            throw new CannotBuildPyramidException();    // caused by very large input lists and NullPointerException
        }                                               // caused by the presence of null in the input list
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
        if (i > numberOfElements + 1) {
            throw new CannotBuildPyramidException();
        }
        return height;
    }

    private static int[][] createPyramid(int height, int width, List<Integer> numbers) {
        int[][] pyramid = new int[ height][width];
        Iterator<Integer> sortNumIter = numbers.iterator();
        for (int i = 0; i <  height; i++) {
            int startPos =  height - (i + 1);
            int stopPos =  height + i;
            for (int j = startPos; j <= stopPos; j += 2) {
                pyramid[i][j] = sortNumIter.next();
            }
        }
        return pyramid;
    }
}
