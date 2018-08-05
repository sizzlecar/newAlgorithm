package com.sizzle.leetCode.MedianOfTwoSortedArrays;

/**
 * Created by sizzle_carl on 2018/5/20.
 * <p>
 * <p>
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * <p>
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 */
public class Solution {


    /**
     * 关键是如何对nums1,和nums2进行整体排序
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] nums = new int[nums1.length + nums2.length];
        //将nums1的值赋给nums
        for (int i = 0;i < nums1.length; i++){
            nums[i] = nums1[i];
        }
        //nums2的值追加至nums
        int nums1Length = nums1.length;
        for(int i = 0 ;i < nums2.length; i++){
            nums[nums1Length++] = nums2[i];
        }
        //希尔排序
        for (int step = nums.length / 2;step > 0;step /=2){//外层循环不断缩小步长
            for (int i  = step;i < nums.length;i++){//取出分组后的一个数
                int val = nums[i];
                //按照步长取出另外一个数，进行比较，并进行交换
                //如果步长变为1则成为插入排序
                for (int j = i - step;j >= 0 && val < nums[j];j -= step){
                    nums[j+step] = nums[j];
                    nums[j] = val;
                }
            }
        }

        double result = 0.0;

        if(nums.length % 2 ==0){
            //长度偶数  1 2 3 4
            result = (nums[nums.length / 2] + nums[nums.length / 2 - 1]) / 2.0;
        }else {
            //长度为奇数
            result = nums[nums.length / 2];
        }

        return result;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        solution.findMedianSortedArrays(nums1,nums2);
    }



}
