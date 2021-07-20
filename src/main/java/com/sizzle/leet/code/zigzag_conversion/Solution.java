package com.sizzle.leet.code.zigzag_conversion;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 *
 * 示例 1：
 *
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 *
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 * @author carl.che
 */
public class Solution {
    public String convert(String s, int numRows) {
        //按Z型计算s中每个字符的坐标, key index, val Coordinate
        boolean flag = false;
        Map<Integer, Coordinate> coordinateMap = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            int x, y;
            Coordinate coordinate = coordinateMap.get(i - 1);
            y = flag ? coordinate.getY() - 1 : (coordinate == null ? i : coordinate.getY() + 1);
            x = flag ? coordinate.getX() + 1 : (coordinate == null ? i : coordinate.getX());
            coordinateMap.put(i, new Coordinate(x,y));
            if(i != 0 && numRows > 1 && i % (numRows - 1) == 0) flag = !flag;
        }
        //按y,x 排序
        //按顺序拼接字符
        return coordinateMap.values()
                .stream()
                .sorted(Comparator.comparing(Coordinate::getY).thenComparing(Coordinate::getX))
                .map(coo -> {
                    AtomicReference<Integer> index = new AtomicReference<>();
                    coordinateMap.forEach((key, value) -> {
                        if (value.equals(coo)) {
                            index.set(key);
                            return;
                        }
                    });
                    char c = s.charAt(index.get());
                    return Character.toString(c);
                })
                .collect(Collectors.joining(""));
    }

    public static class Coordinate{
        private int x;
        private int y;

        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Long start = System.currentTimeMillis();
        String paypalishiring = solution.convert("AB", 1);
        Long end = System.currentTimeMillis();
        System.out.println(paypalishiring);
        System.out.println("花费时间：" + (end - start) + "ms");

    }

}
