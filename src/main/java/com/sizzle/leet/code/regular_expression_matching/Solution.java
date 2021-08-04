package com.sizzle.leet.code.regular_expression_matching;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 * <p>
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4：
 * <p>
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5：
 * <p>
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 *
 * @author carl.che
 */
public class Solution {

    public boolean isMatch(String s, String p) {
        //根据正则表达式构建图
        DirectedGraph directedGraph = new DirectedGraph();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            switch (c) {
                //任意单个字符
                case '.':
                    break;
                //前面0个或多个字符
                case '*':
                    break;
                //其他字符
                default:
                    break;
            }
        }

        return false;
    }

    public static int ID = 0;
    //无条件边值
    public static String CLOSURE = "CLOSURE";


    /**
     * 图
     */
    public static class DirectedGraph {
        private final Node root;

        public DirectedGraph() {
            root = new Node();
        }

        public DirectedGraph(Node root) {
            this.root = root;
        }

        /**
         * 根据正则表达式编译为有限不确定自动机
         * @param regex
         * @return
         */
        public static DirectedGraph compile(String regex) {
            DirectedGraph directedGraph = new DirectedGraph();
            Node currentNode = directedGraph.getRoot();
            for (int i = 0; i < regex.length(); i++) {
                char c = regex.charAt(i);
                switch (c) {
                    //任意单个字符
                    case '.':
                        Node pointNode = new Node();
                        directedGraph.addEdge(CLOSURE, currentNode, pointNode);
                        currentNode = pointNode;
                        break;
                    //前面的字符0次或多次
                    case '*':
                        Node anyLastNode = directedGraph.getAnyLastNode(currentNode);
                        if (anyLastNode == null) {
                            throw new RuntimeException("find last node error");
                        }
                        directedGraph.addEdge(CLOSURE, anyLastNode, currentNode);
                        directedGraph.addEdge(CLOSURE, currentNode, anyLastNode);
                        break;
                    //其他字符
                    default:
                        Node strNode = new Node();
                        directedGraph.addEdge(String.valueOf(c), currentNode, strNode);
                        currentNode = strNode;
                        break;
                }
            }
            return directedGraph;
        }

        /**
         * 根据子集构造法将nfa转化为dfa
         * @param nfa
         * @return
         */
        public static DirectedGraph nfa2dfa(DirectedGraph nfa){
            Node nfaRoot = nfa.getRoot();
            Set<String> symbolSet = new HashSet<>();
            nfa.findSymbol(nfaRoot, symbolSet, null);
            //NFA初始节点只经过CLOSURE边可以到达的状态结合
            Set<Node> rootSymbolNodeSet = new HashSet<>();
            DirectedGraph.find(nfaRoot, CLOSURE, rootSymbolNodeSet);
            String combinationId = rootSymbolNodeSet.stream().map(Node::getId).map(Objects::toString).collect(Collectors.joining(","));
            NodeExt nodeExt = new NodeExt(combinationId);
            DirectedGraph dfa = new DirectedGraph(nodeExt);
            int i = 1;
            nfa2dfa(nfaRoot, rootSymbolNodeSet, symbolSet, i, dfa, null);
            return dfa;
        }

        public static void nfa2dfa(Node nfaRoot,
                             Set<Node> rootSymbolNodeSet,
                             Set<String> symbolSet,
                             int i,
                             DirectedGraph dfa,
                             Node currentNode){
            for (String symbol : symbolSet){
                Set<Node> moveSet = DirectedGraph.findMoveSet(rootSymbolNodeSet, symbol);
                Set<Node> resultSet = new HashSet<>();
                moveSet.forEach(moveNode -> {
                    Set<Node> symbolNodeSet = new HashSet<>();
                    DirectedGraph.find(moveNode, CLOSURE, symbolNodeSet);
                    resultSet.addAll(symbolNodeSet);
                });

                if(resultSet.size() > 0){
                    String combinationIdStr = resultSet.stream().map(Node::getId).map(Objects::toString).collect(Collectors.joining(","));
                    NodeExt node = new NodeExt(combinationIdStr);
                    if(currentNode == null) currentNode = dfa.getRoot();
                    dfa.addEdge(symbol, currentNode, node);
                    System.out.println("dfa" + i++ + " ->" + combinationIdStr);
                    nfa2dfa(nfaRoot, resultSet, symbolSet, i, dfa, node);
                }
            }
        }


        public void addEdge(String edgeVal, Node node) {
            node.addEdge(edgeVal, node);
        }


        /**
         * 给当前的图的指定的节点添加一个边
         *
         * @param edgeVal 边
         * @param toNode  -> node
         */
        public void addEdge(String edgeVal, Node fromNode, Node toNode) {
            //遍历图,找到这个节点
            fromNode.addEdge(edgeVal, toNode);
        }

        public Node getRoot() {
            return root;
        }

        /**
         * 找当前节点的上一个节点
         *
         * @param node 需要寻找上一个节点的节点
         * @return 任一上一个节点，没有则返回null
         */
        public Node getAnyLastNode(Node node) {
            return findLast(root, node);
        }


        /**
         * 打印当前图
         */
        public void print() {
            print(root, new HashSet<>());
        }

        private void print(Node node, Set<Edge> cacheEdge) {
            Set<Edge> edges = node.getEdges();
            for (Edge edge : edges) {
                if (cacheEdge.contains(edge)) continue;
                System.out.println(edge.getId());
                cacheEdge.add(edge);
                Node to = edge.getTo();
                print(to, cacheEdge);
            }
        }

        private Node find(Node root, Node node) {
            Set<Edge> edges = root.getEdges();
            for (Edge edge : edges) {
                Node to = edge.getTo();
                if (to.equals(node)) {
                    return node;
                }
                return find(to, node);
            }
            return null;
        }


        public static Set<Node> find(Node root, String edgeVal, Set<Node> nodeSet) {
            Set<Edge> edges = root.getEdges();
            nodeSet.add(root);
            for (Edge edge : edges) {
                String val = edge.getVal();
                if(nodeSet.contains(edge.getTo())) continue;
                if(val.equals(edgeVal)) {
                    nodeSet.add(edge.getTo());
                }else {
                    continue;
                }
                find(edge.getTo(), edgeVal, nodeSet);
            }
            return nodeSet;
        }


        public static Set<Node> findMoveSet(Set<Node> statusSet, String edgeVal) {
            //找出 statusSet中有edgeVal边的下一个状态集合
             return statusSet.stream().map(statusNode -> {
                Set<Edge> edges = statusNode.getEdges();
                 return edges.stream()
                         .filter(edge -> edge.getVal().equals(edgeVal))
                         .map(Edge::getTo)
                         .collect(Collectors.toSet());
            }).flatMap(Set::stream).collect(Collectors.toSet());
        }

        private Node findLast(Node root, Node node) {
            Set<Edge> edges = root.getEdges();
            for (Edge edge : edges) {
                Node to = edge.getTo();
                if (to.equals(node)) {
                    return edge.getFrom();
                }
                return findLast(to, node);
            }
            return null;
        }

        private Set<String> findSymbol(Node root, Set<String> symbolSet, Edge lastEdge) {
            Set<Edge> edges = root.getEdges();
            for (Edge edge : edges) {
                String val = edge.getVal();
                if(lastEdge != null && edge.getFrom().equals(lastEdge.getTo()) && edge.getTo().equals(lastEdge.getFrom())){
                    if(!val.equals(CLOSURE)) symbolSet.add(val);
                    continue;
                }
                if(!val.equals(CLOSURE)) symbolSet.add(val);
                lastEdge = edge;
                findSymbol(edge.getTo(), symbolSet, lastEdge);
            }
            return symbolSet;
        }

        public static class Node {
            //类型 0 普通节点, 1 尾节点
            private int type;

            private final int id;
            //key -> 节点的边， val -> 边指向的节点
            private final Set<Edge> edges = new HashSet<>();

            public Node() {
                this.id = Solution.ID++;
                type = 0;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Node)) return false;
                Node node = (Node) o;
                return id == node.id;
            }

            @Override
            public int hashCode() {
                return Objects.hash(id);
            }


            /**
             * 给当前节点添加一个边
             *
             * @param edgeVal 边
             * @param toNode  -> node
             */
            public void addEdge(String edgeVal, Node toNode) {
                Edge edge = new Edge(this, toNode, edgeVal);
                edges.add(edge);
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getId() {
                return id;
            }

            public Set<Edge> getEdges() {
                return edges;
            }
        }

        public static class NodeExt extends Node{


            private final String combinationId;

            public NodeExt(String combinationId) {
                this.combinationId = combinationId;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof NodeExt)) return false;
                if (!super.equals(o)) return false;
                NodeExt nodeExt = (NodeExt) o;
                return combinationId.equals(nodeExt.combinationId);
            }

            @Override
            public int hashCode() {
                return Objects.hash(super.hashCode(), combinationId);
            }
        }

        static class Edge {
            private final Node from;
            private final Node to;
            private final String val;
            private final String id;

            public Edge(Node from, Node to, String val) {
                this.from = from;
                this.to = to;
                this.val = val;
                this.id = from.id + "->" + to.id + ":" + val;
            }

            public Node getFrom() {
                return from;
            }

            public Node getTo() {
                return to;
            }

            public String getVal() {
                return val;
            }

            public String getId() {
                return id;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Edge)) return false;
                Edge edge = (Edge) o;
                return Objects.equals(id, edge.id);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id);
            }
        }

    }

    public static void main(String[] args) {
        DirectedGraph directedGraph = DirectedGraph.compile("a.aaas*");
        directedGraph.print();
        DirectedGraph.nfa2dfa(directedGraph);

    }


}
