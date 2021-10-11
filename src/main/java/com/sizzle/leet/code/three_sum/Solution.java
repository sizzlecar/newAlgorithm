package com.sizzle.leet.code.three_sum;

import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 *  @author carl.che
 */
public class Solution {

    /**
     *  解法1:
     *      思路：从数组中找出所有的3个元素的组合，分别判断是否等于0
     *      时间复杂度：从n各元素中取k个，根据组合公式 共有 C(n,k) = n! / ((n-k)! * k!) 种取法，时间复杂度为O(C(n,k)),
     *      k=3时,O(f(n)) = O(n * (n - 1) * (n - 2)) = O(n^3)
     *
     *  解法2:
     *      思路：将数组按从小到大排序，记录正负临界的索引。3个元素和为0,元素的正负情况只有2种情况，
     *          1. 两个正数，一个负数
     *          2. 两个负数，一个整数
     *         所以，我们需要做
     *          1.从正数区间找出所有的2个元素的元组，分辨遍历负数区间判断是否等于0
     *          2.从负数区间找出所有的2个元素的元组，分辨遍历正数区间判断是否等于0
     *       时间复杂度：O(f(n)) = O(2 * n/2 * (n/2-1)) + O(2 * 2/n)
     * @param nums 原数组
     * @return 原数组中所有和为0且不重复的三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        //先进行排序



        return null;
    }

    /**
     * 快排
     *      思路：在待排序的数组中，随机找一个分区点，比如最后一个元素, 将数组中小于分区元素的放分区点左边，大于分区元素的元素放分区段右边
     *      然后分别对左边/右边区间递归重复上述过程
     *
     *      递推公式：quick_sort(p...r) = quick_sort(p...q) + quick_sort(q+1...r)
     *      结束条件：p >= r
     * @param nums 待排序的数组
     */
    private void quickSort(int[] nums) {

        quickSort(nums, 0, nums.length - 1, nums.length - 1);
    }

    private void quickSort(int[] nums, int hi, int ti, int pivot){


    }



}
