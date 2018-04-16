package com.sizzle.LeetCode.FindNoReaptMaxLengthSubStr;

/**
 * Created by sizzle_carl on 2018/4/15.
 *
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。

 示例：

 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。

 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。

 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 *
 */
public class Solution {

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int repeatIndex = 0;
        for(int i = 0;i < s.length(); i++){
            //1,2   1,3
            if(maxLength > repeatIndex - i && i < repeatIndex){
                continue;
            }
            //1.切出所有可能的子串
            for(int k = i + 1; k < s.length() + 1; k++ ){
                String subStr;
                if(k == s.length()){
                    subStr = s.substring(i);
                }else {
                    if(k - i < maxLength){
                        continue;
                    }
                    subStr = s.substring(i,k);
                }
                //2.判断字串是否有重复的字符，记录长度
                boolean isRepeat = false;
                int currentMaxLength = 1;
                for (int j = 0;j < subStr.length();j++){
                    String curChar = subStr.charAt(j) + "";
                    int index = subStr.lastIndexOf(curChar);
                    if(index != j){
                        isRepeat = true;
                        //记录重复字符的索引
                        //repeatIndex = h;
                        break;
                    }
                }
                //已经发现重复的就无需再进行切分

                if(!isRepeat){
                    currentMaxLength = subStr.length();
                    if(currentMaxLength > maxLength){
                        maxLength = currentMaxLength;
                    }
                }else{
                    break;
                }
            }

        }

        //3。返回最大的字串长度
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String str = "nakjzewjudthlenlflontbumdimcopxbrhmrlkahqwqdafphrfumgrakzmmpclttshmgsnpilgxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        Long start = System.currentTimeMillis();
        int maxLength = solution.lengthOfLongestSubstring(str);
        Long end = System.currentTimeMillis();
        System.out.println((end - start));
        System.out.println(maxLength);
    }
}
