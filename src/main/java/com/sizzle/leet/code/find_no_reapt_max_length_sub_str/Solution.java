package com.sizzle.leet.code.find_no_reapt_max_length_sub_str;

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


    /**
     * 1. 从大到小切分该字符串
     * 2. 遍历字串判断是否有重复元素
     * 3. 没有就return
     */
    public int lengthOfLongestSubstring2(String s,
                                         int schedulingFlag,
                                         String lastSubStr,
                                         String lastSubStr2) {
        System.out.println("root str: " + s);
        String subStr = s.substring(0, s.length() - 1);
        System.out.println("去尾str: " + subStr);
        if(subStr.equals("a123cc")){
            System.out.println("break");
        }
        boolean repeatFlag = false;
        for (int j = 0; j < subStr.length() ; j++){
            int indexOf = subStr.indexOf(subStr.charAt(j), j + 1);
            if(indexOf != -1){
                repeatFlag = true;
                break;
            }
        }
        if(!repeatFlag){
            System.out.println(subStr);
            return subStr.length();
        }

        String subStr2 = s.substring(1);
        System.out.println("去头str: " + subStr2);
        boolean repeatFlag2 = false;
        for (int j = 0; j < subStr2.length() ; j++){
            int indexOf = subStr2.indexOf(subStr2.charAt(j), j + 1);
            if(indexOf != -1){
                repeatFlag2 = true;
                break;
            }
        }

        if(!repeatFlag2){
            System.out.println(subStr2);
            return subStr2.length();
        }

        if(schedulingFlag == 0){
            return lengthOfLongestSubstring2(lastSubStr == null ? subStr : lastSubStr, 1, subStr, subStr2);
        }else {
            return lengthOfLongestSubstring2(lastSubStr2 == null ? subStr2 : lastSubStr2, 0, subStr, subStr2);
        }

    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        String str = "aaa123ccc";
        int maxLength = solution.lengthOfLongestSubstring2(str, 0, null, null);
        System.out.println(maxLength);
    }
}
