package com.sizzle.sort;


/**
 * 快排
 * @author carl.che
 */
public class QuickSort {


    /**
     * 快排
     *  思路：
     *  时间复杂度:
     *  空间复杂度:
     *  递推公式：quick_sort(p...r) = quick_sort(p...q-1) + quick_sort(q+1...r)
     *  终止条件：p >= r
     * @param numArray 待排序的数组
     * @return 已经排好序的数组
     */
    public int[] quickSort(int[] numArray) {
        quickSort(numArray, 0, numArray.length);
        return numArray;
    }

    public void quickSort(int[] numArray, int headIndex, int tailIndex) {
        if (headIndex >= tailIndex) return;
        //获取分区点
        int partition = partition(numArray, headIndex, tailIndex);
        quickSort(numArray, headIndex, partition - 1);
        quickSort(numArray, partition + 1, tailIndex);
    }

    public int partition(int[] numArray, int headIndex, int rightIndex) {

        return 0;
    }



}
