package com.sizzle.leet.code.regular_expression_matching;

import javax.naming.NameNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 *  
 * 示例 1：
 *
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 *
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4：
 *
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5：
 *
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 * @author carl.che
 */
public class Solution {

    public boolean isMatch(String s, String p) {
        //根据正则表达式构建图
        DirectedGraph directedGraph = new DirectedGraph();
        Node start = new Node();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            switch (c){
                case '.' : break;
                case '*' : break;
                default: break;
            }
        }

        return false;
    }



    /**
     * 图
     */
    public static class DirectedGraph {
        private Node startNode;
        private Node endNode;

    }

    /**
     * 图的节点
     */
    public static class Node{
        //类型 0 正常节点， -1
        private int type;

        private static int id = 0;

        //key -> 节点的边， val -> 边指向的节点
        private final Map<String, List<Node>> next = new HashMap<>();

        public Node(){
            id = id++;
        }

        /**
         * 添加下一个节点
         * @param edge 边
         * @param node 节点
         */
        public void addNext(String edge, Node node){
            List<Node> nodes = next.get(edge);
            if(nodes == null){
                nodes = new ArrayList<>();
            }
            nodes.add(node);
            next.put(edge, nodes);
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }



}
