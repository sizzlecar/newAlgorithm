package com.sizzle.leet.code.find_no_reapt_max_length_sub_str;

import java.util.*;

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


    public int lengthOfLongestSubstring3(String s, List<byte[]> lastBrotherByteArrays, int[] lastResArray, int lastIndex){

        if(lastResArray != null){
            int res = Arrays.stream(lastResArray).max().orElse(-1);
            if(res > -1){
                return res;
            }
        }

        System.out.println("root str: " + s);
        byte[] strBytes = s.getBytes();
        //左指针
        int left = 0;
        //右指针
        int right = strBytes.length;
        List<byte[]> brotherByteArrays = new ArrayList<>();
        //判断自身是否是一个无重复的字符串
        System.out.println("subBytes str: " + s);
        //判断字串是否有重复的元素
        boolean arrayRepeat = verifyArrayRepeat(s.getBytes());
        if(!arrayRepeat){
            System.out.println("subBytes找到无重复字串" + s);
            return s.length();
        }

        //去掉第一个元素
        byte[] subBytes2 = Arrays.copyOfRange(strBytes, left + 1, right);
        System.out.println("subBytes2 str: " + new String(subBytes2));
        //判断字串是否有重复的元素
        boolean arrayRepeat2 = verifyArrayRepeat(subBytes2);
        if(!arrayRepeat2){
            System.out.println("subBytes2找到无重复字串" + new String(subBytes2));
            return subBytes2.length;
        }

        //去掉最后一个元素
        byte[] subBytes3 = Arrays.copyOfRange(strBytes, left, right - 1);
        System.out.println("subBytes3 str: " + new String(subBytes3));
        //判断字串是否有重复的元素
        boolean arrayRepeat3 = verifyArrayRepeat(subBytes3);
        if(!arrayRepeat3){
            System.out.println("subBytes3找到无重复字串" + new String(subBytes3));
            return subBytes3.length;
        }

        if(new String(subBytes2).equals("baab!")){
            System.out.println("1");
        }

        //保存当前根节点的两个最大子字符串节点
        brotherByteArrays.add(subBytes2);
        brotherByteArrays.add(subBytes3);
        int[] finalRes = null;
        if(lastBrotherByteArrays == null || lastBrotherByteArrays.size() == 0 || lastIndex >= lastBrotherByteArrays.size()){
            //当前是根节点
            Integer index = 0;
            int[] resArray = new int[]{-1, -1};
            int length = lengthOfLongestSubstring3(new String(brotherByteArrays.get(index)), brotherByteArrays, resArray, index + 1);
            resArray[index] = length;
            finalRes = resArray;
        }else {
            //当前不是根节点，继续遍历上一层没有遍历到的节点
            int length = lengthOfLongestSubstring3(new String(lastBrotherByteArrays.get(lastIndex)), lastBrotherByteArrays, lastResArray, lastIndex + 1);
            lastResArray[lastIndex] = length;
            finalRes = lastResArray;
        }

        return Arrays.stream(finalRes).max().orElse(-1);
    }

    /**
     * 使用hash表判断数组中是否存在重复的数据
     */
    private boolean verifyArrayRepeat(byte[] array){
        assert array != null;
        Map<Byte, Byte> byteMap = new HashMap<>();
        for (byte b : array) {
            if (byteMap.containsKey(b)) {
                return true;
            }
            byteMap.put(b, null);
        }
        return false;
    }



    public static void main(String[] args) {
        Solution solution = new Solution();

        String str = "aabaab!bb";
        Long s1 = System.currentTimeMillis();
        int maxLength = solution.lengthOfLongestSubstring3(str, null, null, 0);
        Long e1 = System.currentTimeMillis();
        System.out.println("执行时间：" + (e1 - s1) + "ms");
        System.out.println(maxLength);
    }


}
