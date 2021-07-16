package com.sizzle.leet.code.longest_palindromic_substring;

import java.util.Arrays;
import java.util.List;

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
        //双指针遍历所有子串，判断是否是回文，找出最大的
        int head, tail;
        String maxStr = "";
        for(int i = 0; i < s.length(); i++){
            head = i;
            tail = s.length();
            while(tail > 0 && tail > head && (tail - head + 1) > maxStr.length()){
                String subStr = s.substring(head, tail);
                //判断是否是回文
                boolean flag = true;
                for(int j = 0; j < subStr.length(); j++){
                    char charStr1 = subStr.charAt(subStr.length() - j - 1);
                    char charStr2 = subStr.charAt(j);
                    if(charStr1 != charStr2){
                        flag = false;
                        break;
                    }
                }
                maxStr = flag && subStr.length() > maxStr.length() ?  subStr : maxStr;
                tail--;
            }

        }
        return maxStr;
    }


    public static void main(String[] args) {
        List<String> caseList = Arrays.asList("babad", "cbbd", "a", "ac","dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        Solution solution = new Solution();
        caseList.forEach(str -> {
            Long start = System.currentTimeMillis();
            String s = solution.longestPalindrome(str);
            Long end = System.currentTimeMillis();
            System.out.println(str + "最大回文子串：" + s + ", 花费时间：" + (end - start) + "ms");
        });

    }
}
