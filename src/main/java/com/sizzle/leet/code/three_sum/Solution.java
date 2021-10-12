package com.sizzle.leet.code.three_sum;

import java.util.*;
import java.util.stream.Collectors;

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
        quickSort(nums);
        //全正数组，全负数组没有解答
        if (nums[0] > 0 || nums[nums.length - 1] < 0) return Collections.emptyList();
        //找出正负临界索引
        int criticalIndex = nums.length / 2;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < 0 && nums[i + 1] >= 0) {
                criticalIndex = i + 1;
                break;
            }
        }
        List<List<Integer>> resultList = new ArrayList<>();
        //从正数区间找出所有的2个元素的元组，分辨遍历负数区间判断是否等于0
        for (int i = criticalIndex; i < nums.length; i++) {
            int iVal = nums[i];
            for (int j = i + 1; j < nums.length - 1; j++) {
                int jVal = nums[j];
                int val = -(iVal + jVal);
                int searchIndex = Arrays.binarySearch(nums, 0, criticalIndex, val);
                if(searchIndex > -1) {
                    resultList.add(Arrays.asList(iVal, jVal, nums[searchIndex]));
                }
            }
        }

        //从负数区间找出所有的2个元素的元组，分辨遍历正数区间判断是否等于0
        for (int i = 0; i < criticalIndex; i++) {
            int iVal = nums[i];
            for (int j = i + 1; j < criticalIndex - 1; j++) {
                int jVal = nums[j];
                int val = -(iVal + jVal);
                int searchIndex = Arrays.binarySearch(nums, criticalIndex, nums.length, val);
                if(searchIndex > -1) {
                    resultList.add(Arrays.asList(iVal, jVal, nums[searchIndex]));
                }
            }
        }

        //整数区间3个0,
        int zeroCount = 0;
        for (int i = criticalIndex; i < nums.length; i++) {
            if (zeroCount >= 3) break;
            if (nums[i] == 0) {
                zeroCount++;
            }
        }
        if (zeroCount >= 3) resultList.add(Arrays.asList(0, 0, 0));
        return resultList;
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
    private int[] quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int hi, int ti) {
        //尾指针与头指针重叠，直接返回
        if (hi >= ti) return;
        //寻找分区点,将区间内元素大于分区点的元素放右边，小于分区点的元素放左边，分区点放中间
        int pivotIndex = partition(nums,  hi,  ti);
        //递归左区间
        quickSort(nums, hi, pivotIndex - 1);
        //递归右区间
        quickSort(nums, pivotIndex + 1, ti);
    }

    /**
     * 寻找分区点将区间内元素大于分区点的元素放右边，小于分区点的元素放左边，分区点放中间
     * @param nums 待排序的数组
     * @param hi 头指针
     * @param ti 尾指针
     * @return 分区点的索引
     */
    private int partition(int[] nums, int hi, int ti){
        //默认用区间最后一个元素作为分区点
        int pivot = nums[ti];
        int i = hi;
        int j = hi;
        for (; i < ti ; i++) {
            int iVal = nums[i];
            if (iVal < pivot) {
                //交互i,j的值
                nums[i] = nums[j];
                nums[j++] = iVal;
            }
        }
        //将分区元素移动至中间的位置
        nums[ti] = nums[j];
        nums[j] = pivot;
        return j;
    }


    public static void main(String[] args) {
        Solution quickSort = new Solution();
        int num[] = new int[]{-1,0,1,2,-1,-4};
        Long start = System.currentTimeMillis();
        List<List<Integer>> lists = quickSort.threeSum(num);
        Long end = System.currentTimeMillis();
        System.out.println("花费时间：" + (end - start) + "ms");
        lists.stream().map(list -> list.stream().map(Objects::toString).collect(Collectors.joining(","))).peek(System.out::println).collect(Collectors.toList());
    }



}
