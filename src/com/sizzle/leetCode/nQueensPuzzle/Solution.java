package com.sizzle.leetCode.nQueensPuzzle;

import java.util.*;

/**
 * Created by sizzle_carl on 2018/8/5.
 * 皇后规则：横、直、斜都可以走，步数不受限制，但不能越子。
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:

 输入: 4
 输出: [
 [".Q..",  // 解法 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // 解法 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 解释: 4 皇后问题存在两个不同的解法。
 *
 */




public class Solution {
    /**
     * 以棋盘左下角作为坐标轴原点，分为n*2个格子，每个格子以左下角坐标作为当前格子的坐标
     * 先假设某一个格子被皇后占领，根据该点坐标和斜率（横，竖，斜）pass这三条线经过的格子，在剩下的格子中重复上一步步骤
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        //保存皇后位置
        List<List<String>> resultList = new ArrayList<>();

        List<String> chessCoordinateSet = new ArrayList<>();
        for (int i = 0; i < n;i++){
            for (int y = 0;y < n; y++){
                chessCoordinateSet.add(i + "," + y);
            }
        }

        //假设0,0被一个皇后占领
        for (int i = 0; i < n;i++){

            for (int y = 0;y < n; y++){
                Set<String> solutionSet = new HashSet<>();
                Boolean flag = findQueenLocation(i,y,solutionSet,new ArrayList<>(chessCoordinateSet),n);
                if(flag == null){
                    continue;
                }
                if(flag){
                    List<String> list = new ArrayList<>();
                    list.addAll(solutionSet);
                    resultList.add(list);
                }
            }
        }



        return null;
    }

    //pass当前坐标横，竖，斜三条直线上的点
    public Boolean findQueenLocation(int x, int y, Set<String> solutionSet,List<String> chessCoordinateSet,int n){


        //pass横线上的点
        for (int i = 0;i < n; i++){
            chessCoordinateSet.remove(i + "," + y);
        }

        //pass竖线上的点
        for (int i = 0;i < n; i++){
            chessCoordinateSet.remove(x + "," + i);
        }

        //pass斜线上的点(斜率为1)
        for (int i = 0;i < n; i++){
            //点斜式 y = x + b - a
            //假设i = x
            chessCoordinateSet.remove(i + "," + (i + y - x));
        }

        //pass斜线上的点(斜率为-1)
        for (int i = 0;i < n; i++){
            //点斜式 y = -x + b + a
            //假设i = x
            chessCoordinateSet.remove(i + "," + (-i + y + x));
        }

        if(chessCoordinateSet.size() < n - solutionSet.size()){
            return false;
        }

        if(solutionSet.size() == n){
            return true;
        }
        if(x < 8 && y < 8){
            solutionSet.add(x + "," + y);
        }else {
            chessCoordinateSet.remove(x + "," + y);
        }
        Iterator<String> iterable = chessCoordinateSet.iterator();
        while (iterable.hasNext()){
            String str = iterable.next();
            String[] location = str.split(",");
            int otherX = Integer.parseInt(location[0]);
            int otherY = Integer.parseInt(location[1]);
            Boolean flag = findQueenLocation(otherX,otherY,solutionSet,new ArrayList<>(chessCoordinateSet),n);

            if(flag != null && flag){
                return flag;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solveNQueens(4);
        System.out.println("测试提交");
    }




}
