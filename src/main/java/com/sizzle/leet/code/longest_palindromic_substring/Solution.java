package com.sizzle.leet.code.longest_palindromic_substring;

/**
 * Created by test on 2018/6/23.
 * 给定一个字符串 s，找到 s 中最长的回文（正反都一致的字符串）子串。你可以假设 s 的最大长度为1000。

 示例 1：

 输入: "babad"
 输出: "bab"
 注意: "aba"也是一个有效答案。
 示例 2：

 输入: "cbbd"
 输出: "bb"
 */
public class Solution {

    /**
     * 1.切分父串，找出所有可能的子串
     * 2.判断是否是回文字串
     * 3.挑选出长度最大的回文子串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        //转为字节数组处理
        byte[] strBytes = s.getBytes();
        for (byte num : strBytes){
            System.out.println("测试提交");
        }

        return null;
    }
}
