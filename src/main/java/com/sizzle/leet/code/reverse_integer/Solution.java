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
        String numStr = String.valueOf(x);
        boolean flag = numStr.startsWith("-");
        if (flag) {
            numStr = numStr.replace("-", "");
        }
        StringBuilder sb = new StringBuilder(flag ? "-" : "");
        for (int i = numStr.length() - 1; i >= 0; i--) {
            sb.append(numStr.charAt(i));
        }
        String s = sb.toString();
        int result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;
        int multmin;
        int digit;
        char firstChar = s.charAt(0);
        if (firstChar < '0') { // Possible leading "+" or "-"
            if (firstChar == '-') {
                negative = true;
                limit = Integer.MIN_VALUE;
            }
            i++;
        }
        multmin = limit / 10;
        while (i < len) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            digit = Character.digit(s.charAt(i++), 10);
            if (result < multmin) {
                return 0;
            }
            result *= 10;
            if (result < limit + digit) {
                return 0;
            }
            result -= digit;
        }
        return negative ? result : -result;
    }

    public static void main(String[] args) {
        int x = 123;
        Solution solution = new Solution();
        int reverse = solution.reverse(x);
        System.out.println(reverse);
    }
}
