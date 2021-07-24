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
        boolean negative = x < 0;
        if(negative) x = -x;
        int limit = negative ? Integer.MIN_VALUE : -Integer.MAX_VALUE;
        int subLimit = limit / 10;
        while (x != 0){
            int currentInt = x % 10;
            if(result < subLimit) return 0;
            result *= 10;
            if(result < currentInt + limit) return 0;
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
