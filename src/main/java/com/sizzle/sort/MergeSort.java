package com.sizzle.sort;

/**
 * 归并排序
 * @author carl.che
 */
public class MergeSort {


    /**
     * 归并排序：
     *  思路：
     *      1. 将数组一分为二，重复至不可拆分
     *      2. 将拆分后的进行合并 排序，重复值全部合并完
     *  时间复杂度
     *      1. O(nlogn)
     *  空间复杂度
     *      1. O(n)
     *  本质是分治的思想，将大问题拆分为小问题，小问题解决了，再将结果汇总，大问题也就解决了
     *  递推公式：mergeSort(p...r) = merge(mergeSort(p...q), mergeSort(q+1...r))
     *  终止条件：p >= r (不能再继续分解)
     * @param numArray 待排序的数组
     * @return 排好序的数组
     */
    public int[] mergeSort(int[] numArray) {


        return numArray;
    }


    private void mergeSort(int[] numArray, int headIndex, int tailIndex){

    }

}
