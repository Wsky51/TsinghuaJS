package com.leetcode.onetaskperday;


/**
 * Created on 2020/4/7
 *
 * @author WuYi
 */
public class RotationMatrix {

    public static void main(String[] args) {
        int [][] matrix ={{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate(matrix);
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int half;
        if ((n & 1) == 0) {
            half = n / 2;
        } else {
            half = (n - 1) / 2;
        }

        for (int i = 0; i < half; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i]=temp;
            }
        }
    }

}
