package com.sizzle.leet.code.reverse_integer;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31, 2^31 − 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 *
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 *
 * 输入：x = 0
 * 输出：0
 *
 * 提示：
 *
 * -231 <= x <= 231 - 1
 *
 * @author carl.che
 */
public class Solution {


    public int reverse(int x) {

        String numStr = x + "";
        boolean flag = numStr.startsWith("-");
        if(flag){
            numStr = numStr.replace("-", "");
        }
        StringBuilder sb = new StringBuilder(flag ? "-" : "");
        for (int i = numStr.length() - 1; i >= 0; i--) {
            sb.append(numStr.charAt(i));
        }
        return Integer.parseInt(sb.toString());
    }

    public static void main(String[] args) {
        int x = 1534236469;
        Solution solution = new Solution();
        int reverse = solution.reverse(x);
        System.out.println(reverse);
    }
}
