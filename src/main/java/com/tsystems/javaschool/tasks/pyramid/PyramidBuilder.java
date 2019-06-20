package com.tsystems.javaschool.tasks.pyramid;

import java.util.*;

import static java.util.Comparator.naturalOrder;

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
        try {
            List<Integer> sortedInputNumbers = new ArrayList<>(inputNumbers);
            sortedInputNumbers.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
            int level = getPyramidHeight(sortedInputNumbers.size());
            int width = 2 * level - 1;
            int[][] pyramid = new int[level][width];
            Iterator<Integer> sortNumIter = sortedInputNumbers.iterator();
            for (int i = 0; i < level; i++) {
                int startPos = level - (i + 1);
                int stopPos = level + i;
                for (int j = startPos; j <= stopPos; j += 2) {
                    pyramid[i][j] = sortNumIter.next();
                }
            }
            return pyramid;
        }
        catch (Throwable e) {
            throw new CannotBuildPyramidException();
        }
    }

    private static int getPyramidHeight(int numberOfElements) throws CannotBuildPyramidException {
        int height = 0;
        int i;
        for (i = 1; i <= numberOfElements; i += height) {
            height++;
        }
        System.out.println(i);
        System.out.println(height);
        if (i > numberOfElements+1) {
            throw new CannotBuildPyramidException();
        }
        return height;
    }

}
