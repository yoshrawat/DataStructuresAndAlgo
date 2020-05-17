package com.yogesh.leetcode.hard;

import java.util.*;

public class Leetcode85 {
    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        System.out.println(maximalRectangle(matrix));
    }

    static public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int result = 0;

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] preSum = new int[row][col];

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (i == 0) {
                    if (matrix[j][i] - '0' == 0) {
                        preSum[j][i] = 0;
                    } else {
                        preSum[j][i] = matrix[j][i] - '0';
                    }

                } else {
                    if (matrix[j][i] - '0' == 0) {
                        preSum[j][i] = 0;
                    } else {
                        preSum[j][i] = (matrix[j][i] - '0') + preSum[j][i - 1];
                    }

                }
            }
        }

        for (int[] rows : preSum) {
            System.out.println(Arrays.toString(rows));
        }

        for (int right = 0; right < col; right++) {
            int sum = helper(preSum, 0, right);
            result = Math.max(result, sum);
        }
        return result;
    }

    private static int helper(int[][] preSum, int left, int right) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        int i =0;
        while( i < preSum.length) {
            if( stack.isEmpty() || preSum[i][right] >= preSum[stack.peek()][right] ) {
                stack.push(i);
                i++;
            }else {
                int candiate = stack.pop();
                int height = preSum[candiate][right];
                int width = stack.isEmpty() ? i : i - stack.peek() -1;
                max = Math.max( max, height * width);
            }
        }
        while( !stack.isEmpty()) {
            int candiate = stack.pop();
            int height = preSum[candiate][right];
            int width = stack.isEmpty() ? i : i - stack.peek() -1;
            max = Math.max( max, height * width);
        }
        return max;
    }
}
