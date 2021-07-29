package com.sizzle.leet.code.reverse_integer;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31, 2^31 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 * <p>
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 * <p>
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 * <p>
 * 输入：x = 0
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * -231 <= x <= 231 - 1
 *
 * @author carl.che
 */
public class Solution {


    public int reverse(int x) {
        int result = 0;
        //判断是否是负数
        boolean negative = x < 0;
        //负数按正数处理
        if(negative) x = -x;
        //32位有符号范围，正数最大值取反
        int limit = negative ? Integer.MIN_VALUE : -Integer.MAX_VALUE;
        //最大值减少一个数量级
        int subLimit = limit / 10;
        while (x != 0){
            //循环取x的个位数
            int currentInt = x % 10;
            //1.如果x是负数, subLimit = -214748364, 如果 result < subLimit 再拼上一位也一定小于 Integer.MIN_VALUE,即超出范围
            //2.如果x是正数, subLimit = -214748364, 如果 result < subLimit -> -result > -subLimit 再拼上一位也一定大于 Integer.MAX_VALUE,即超出范围
            if(result < subLimit) return 0;
            //循环增加数量级
            result *= 10;
            //1.如果x是负数, limit = -2147483648, 如果 result - currentInt < limit 小于 Integer.MIN_VALUE 超出范围
            //2.如果x是正数, limit = -2147483647, 如果 -result + currentInt > limit 大于 Integer.MAX_VALUE 超出范围
            if(result < currentInt + limit) return 0;
            //统一按负数处理
            result -= currentInt;
            x /= 10;
        }
        return negative ? result : -result;
    }

    public static void main(String[] args) {
        int x = -123;
        Solution solution = new Solution();
        int reverse = solution.reverse(x);
        System.out.println(reverse);
    }
}
