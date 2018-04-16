package com.sizzle.LeetCode.SumOfTwoNums;

/**
 * Created by sizzle_carl on 2018/4/16.
 */
public class Solution {

    public int[] twoSum(int[] nums, int target) {
        for(int i = 0;i < nums.length;i++){
            for(int j = i + 1; j < nums.length;j++){
                if(nums[i] + nums[j] == target){
                    int[] result = new int[2];
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return new int[2];
    }
}
