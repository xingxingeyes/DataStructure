package com.kai.sparsearray;

/**
 * @description: 稀疏数组学习
 * @author: kai.lv
 * @date: 2021/11/10
 **/
public class SparesArray {

    public static void main(String[] args) {
        // 11*11的五子棋盘，1代表黑棋，2代表白棋，其中有大量无效数字，可转为稀疏数组从而节省空间
        int[][] array = new int[11][11];
        array[1][2] = 1;
        array[2][3] = 2;
        array[2][4] = 5;
        for (int[] arr : array) {
            for (int num : arr) {
                System.out.print("  " + num);
            }
            System.out.println();
        }

        /**
         *
         * 稀疏数组：
         *         行=普通数组有效数组个数+1  sum+1
         *              第一行第一列代表普通数组一共几行，第一行第二列代表普通数组一共几列，第一行第三列代表一共有多少个有效数字
         *              第二行开始 第一列代表第一个有效数字在第几行，第二列代表第一个有效数字在第几列，第三行代表有效数字是多少
         *              第三行以此类推......
         *         列=3列
         */

        // 普通数组转换为稀疏数组
        int sum = 0;
        int row = array.length;
        int col = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i].length > col) {
                    col = array[i].length;
                }
                if (array[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("一共有：[" + row + "]行[" + col + "]列[" + sum + "]个有效数字");

        // 创建稀疏数组
        int sparseArray[][] = new int[sum + 1][3];
        sparseArray[0][0] = row;
        sparseArray[0][1] = col;
        sparseArray[0][2] = sum;

        int count = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                }
            }
        }

        // 打印稀疏数组
        for (int[] arr : sparseArray) {
            for (int num : arr) {
                System.out.print("  " + num);
            }
            System.out.println();
        }

        // 稀疏数组转化为普通数组
        int[][] array1 = new int[sparseArray[0][0]][sparseArray[0][1]];

        for (int i = 1; i < sparseArray.length; i++) {
            array1[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        System.out.println();

        // 再次打印普通数组
        for (int[] arr : array1) {
            for (int num : arr) {
                System.out.print("  " + num);
            }
            System.out.println();
        }


    }
}
