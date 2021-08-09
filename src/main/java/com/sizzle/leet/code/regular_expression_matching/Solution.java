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
        return Regex.compile(p).isMatch(s);
    }

    /**
     * 图的基类
     */
    public static abstract class Graph {
        /**
         * 初始节点
         */
        protected final Node root;
        //无条件边值
        public static String CLOSURE = "CLOSURE";
        public static String DOT = "DOT";


        public Graph(Node root) {
            this.root = root;
        }

        /**
         * 给当前的图的指定的节点添加一个边
         *
         * @param edgeVal 边
         * @param toNode  -> node
         */
        public abstract void addEdge(String edgeVal, Node fromNode, Node toNode);


        /**
         * 查询从指定状态开始，指定状态经过指定条件可以达到的状态集合
         *
         * @param root    指定状态(图的节点)
         * @param edgeVal 边值，条件
         * @param nodeSet 指定状态集合
         * @return 从指定状态开始，指定状态经过指定条件可以达到的状态集合
         */
        public static Set<Node> findNext(Node root, String edgeVal, Set<Node> nodeSet, boolean addSelf, boolean dotFlag) {
            Set<? extends Edge> edges = root.getEdges();
            if (addSelf) nodeSet.add(root);
            for (Edge edge : edges) {
                String val = edge.getVal();
                if (nodeSet.contains(edge.getTo())) continue;
                if (val.equals(edgeVal) || (dotFlag && val.equals(DOT))) {
                    nodeSet.add(edge.getTo());
                } else {
                    continue;
                }
                findNext(edge.getTo(), edgeVal, nodeSet, addSelf, dotFlag);
            }
            return nodeSet;
        }

        public static Set<Node> findNext(Node root, String edgeVal, Set<Node> nodeSet) {
            return findNext(root, edgeVal, nodeSet, true, true);
        }


        public Node getRoot() {
            return root;
        }

        /**
         * 查询指定节点下，是否有指定的节点
         *
         * @param root     指定节点（状态）
         * @param findNode 指定的节点（状态）
         * @return 查询指定节点下，是否有指定的节点
         */
        public Node findNode(Node root, Node findNode) {
            if (root.equals(findNode)) return root;
            Set<Edge> edges = root.getEdges();
            for (Edge edge : edges) {
                if (edge.getFrom().equals(edge.getTo())) continue;
                Node to = edge.getTo();
                return findNode(to, findNode);
            }
            return null;
        }


        /**
         * 打印当前图
         */
        protected void print() {
            print(root, new HashSet<>());
        }

        private void print(Node node, Set<Edge> cacheEdge) {
            Set<? extends Edge> edges = node.getEdges();
            for (Edge edge : edges) {
                if (cacheEdge.contains(edge)) continue;
                System.out.println(edge.getId());
                cacheEdge.add(edge);
                Node to = edge.getTo();
                print(to, cacheEdge);
            }
        }


        /**
         * 图的节点，由 类型，id, 由该节点出发的边构成
         */
        public static abstract class Node {
            //类型 0 普通节点, 1 尾节点
            protected int type;

            protected final String id;

            //key -> 节点的边， val -> 边指向的节点
            protected final Set<Edge> edges = new HashSet<>();

            public static int COUNT = 0;


            public Node(String id) {
                this.id = id;
                this.type = 0;
            }

            public Node(int type, String id) {
                this.type = type;
                this.id = id;
            }

            /**
             * 给当前节点添加一个边
             *
             * @param edgeVal 边
             * @param toNode  -> node
             */
            public abstract void addEdge(String edgeVal, Node toNode);

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public Set<Edge> getEdges() {
                return edges;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Node)) return false;
                Node node = (Node) o;
                return Objects.equals(id, node.id);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id);
            }

            @Override
            public String toString() {
                return id;
            }
        }

        /**
         * 图的边，由开始节点，结束节点，边的条件组成
         */
        public static abstract class Edge {
            protected final Node from;
            protected final Node to;
            protected final String val;
            protected final String id;

            public Edge(Node from, Node to, String val, String id) {
                this.from = from;
                this.to = to;
                this.val = val;
                this.id = id;
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


    /**
     * 有限不确定自动机
     */
    public static class Nfa extends Graph {

        //无条件边值
        private Node endNode;

        public Nfa() {
            super(new NfaNode());
        }


        public Nfa(Node root) {
            super(root);
        }

        /**
         * 给当前的图的指定的节点添加一个边
         *
         * @param edgeVal 边
         * @param toNode  -> node
         */
        @Override
        public void addEdge(String edgeVal, Node fromNode, Node toNode) {
            //遍历图,找到这个节点
            fromNode.addEdge(edgeVal, toNode);

        }


        /**
         * 查询从指定状态开始，指定状态经过指定条件可以达到的状态集合
         *
         * @param root    指定状态(图的节点)
         * @param edgeVal 边值，条件
         * @param nodeSet 指定状态集合
         * @return 从指定状态开始，指定状态经过指定条件可以达到的状态集合
         */
        public static Set<Node> find(Node root, String edgeVal, Set<Node> nodeSet, boolean addSelf, boolean dotFlag) {
            Set<Edge> edges = root.getEdges();
            if (addSelf) {
                nodeSet.add(root);
            }
            for (Edge edge : edges) {
                String val = edge.getVal();
                if (nodeSet.contains(edge.getTo())) continue;
                if (val.equals(edgeVal) || (dotFlag && val.equals(DOT))) {
                    nodeSet.add(edge.getTo());
                } else {
                    continue;
                }
                find(edge.getTo(), edgeVal, nodeSet, addSelf, dotFlag);
            }
            return nodeSet;
        }

        public static Set<Node> find(Node root, String edgeVal, Set<Node> nodeSet) {
            return find(root, edgeVal, nodeSet, true, true);
        }


        /**
         * 查询从指定节点（状态）开始，所有经过的边的出现的字符（边的值，条件）集合，不包括 CLOSURE
         *
         * @param root         指定节点（状态）
         * @param symbolSet    边的字符（边的值，条件）集合
         * @param cacheEdgeSet 处理过的边，防止环导致的死循环
         * @return 从指定节点（状态）开始，所有经过的边的出现的字符（边的值，条件）集合，不包括 CLOSURE
         */
        public Set<String> findSymbol(Node root, Set<String> symbolSet, Set<Edge> cacheEdgeSet) {
            Set<Edge> edges = root.getEdges();
            for (Edge edge : edges) {
                String val = edge.getVal();
                if (cacheEdgeSet != null && cacheEdgeSet.contains(edge)) {
                    if (!val.equals(CLOSURE)) symbolSet.add(val);
                    continue;
                }
                if (!val.equals(CLOSURE)) symbolSet.add(val);
                if (cacheEdgeSet == null) cacheEdgeSet = new HashSet<>();
                cacheEdgeSet.add(edge);
                findSymbol(edge.getTo(), symbolSet, cacheEdgeSet);
            }
            return symbolSet;
        }


        /**
         * 找当前节点的任意上一个节点
         *
         * @param node 需要寻找上一个节点的节点
         * @return 任一上一个节点，没有则返回null
         */
        public Node getAnyLastNode(Node node) {
            return findLast(root, node, null);
        }

        public Node getEndNode() {
            return endNode;
        }

        public void setEndNode(Node endNode) {
            this.endNode = endNode;
        }

        private Node findLast(Node root, Node node, Set<Edge> cacheEdgeSet) {
            Set<Edge> edges = root.getEdges();
            for (Edge edge : edges) {
                Node to = edge.getTo();
                if (to.equals(node)) return edge.getFrom();
                if (cacheEdgeSet != null && cacheEdgeSet.contains(edge)) continue;
                if (cacheEdgeSet == null) cacheEdgeSet = new HashSet<>();
                cacheEdgeSet.add(edge);
                return findLast(to, node, cacheEdgeSet);
            }
            return null;
        }


        /**
         * 图的节点，由 类型，id, 由该节点出发的边构成
         */
        public static class NfaNode extends Graph.Node {

            public NfaNode() {
                super(String.valueOf(Node.COUNT++));
            }

            public NfaNode(int type, String id) {
                super(type, id);
            }


            /**
             * 给当前节点添加一个边
             *
             * @param edgeVal 边
             * @param toNode  -> node
             */
            @Override
            public void addEdge(String edgeVal, Node toNode) {
                NfaEdge edge = new NfaEdge(this, toNode, edgeVal);
                edges.add(edge);
            }

        }

        /**
         * 图的边，由开始节点，结束节点，边的条件组成
         */
        public static class NfaEdge extends Edge {

            public NfaEdge(Node from, Node to, String val) {
                super(from, to, val, from.toString() + "->" + to.toString() + ":" + val);
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


    /**
     * 有限确定自动机
     */
    public static class Dfa extends Graph {

        /**
         * DFA 定位表
         * key 节点（状态） + , + 条件
         * val 下个一节点（状态）
         */
        private final Map<String, Graph.Node> table = new HashMap<>();

        private final Map<String, Graph.Node> dotTable = new HashMap<>();

        public Dfa() {
            super(new DfaNode());
        }

        public Dfa(Node root) {
            super(root);
        }

        @Override
        public void addEdge(String edgeVal, Node fromNode, Node toNode) {
            //遍历图,找到这个节点
            fromNode.addEdge(edgeVal, toNode);
        }

        /**
         * 查询从指定状态开始，指定状态经过指定条件可以达到的状态集合
         *
         * @param root    指定状态(图的节点)
         * @param edgeVal 边值，条件
         * @return 从指定状态开始，指定状态经过指定条件可以达到的状态集合
         */
        public static Node find(Node root, String edgeVal, Set<Edge> cacheEdgeSet) {
            Set<Edge> edges = root.getEdges();
            List<Edge> sortedList = edges.stream().sorted(Comparator.comparing(Edge::getVal).reversed()).collect(Collectors.toList());
            for (Edge edge : sortedList) {
                String val = edge.getVal();
                if (val.equals(edgeVal) || val.equals(DOT)) {
                    return edge.getTo();
                }
                if (cacheEdgeSet == null) cacheEdgeSet = new HashSet<>();
                if (cacheEdgeSet.contains(edge)) continue;
                cacheEdgeSet.add(edge);
                find(edge.getTo(), edgeVal, cacheEdgeSet);
            }
            return null;
        }

        /**
         * 找出图中指定节点（状态）出发，经过指定值（边的值，条件）可以到达的状态集合
         *
         * @param statusSet 指定节点（状态）集合
         * @param edgeVal   指定值（边的值，条件）
         * @return 从图中指定节点（状态）出发，经过指定值（边的值，条件）可以到达的状态集合
         */
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


        public Map<String, Node> getTable() {
            return table;
        }

        public Map<String, Node> getDotTable() {
            return dotTable;
        }

        public Node run(Node currentNode, String val) {
            String currentNodeId = currentNode.getId();
            Node node = table.get(currentNodeId + ":" + val);
            if (node == null) {
                node = dotTable.get(currentNodeId + ":" + DOT);
            }
            return node;
        }

        public Node run(Node currentNode, String val, String strategyVal) {
            String currentNodeId = currentNode.getId();
            if(strategyVal.equals(DOT)){
                return dotTable.get(currentNodeId + ":" + DOT);
            }else {
                return table.get(currentNodeId + ":" + val);
            }
        }

        /**
         * 图的节点，由 类型，id, 由该节点出发的边构成
         */
        public static class DfaNode extends Graph.Node {

            public DfaNode() {
                super(String.valueOf(Node.COUNT++));
            }

            public DfaNode(int type, String id) {
                super(type, id);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Node)) return false;
                Node node = (Node) o;
                return Objects.equals(id, node.id);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id);
            }

            @Override
            public String toString() {
                return id;
            }

            /**
             * 给当前节点添加一个边
             *
             * @param edgeVal 边
             * @param toNode  -> node
             */
            @Override
            public void addEdge(String edgeVal, Node toNode) {
                Dfa.DfaEdge edge = new Dfa.DfaEdge(this, toNode, edgeVal);
                edges.add(edge);
            }

        }

        /**
         * 图的边，由开始节点，结束节点，边的条件组成
         */
        public static class DfaEdge extends Edge {

            public DfaEdge(Node from, Node to, String val) {
                super(from, to, val, from.toString() + "->" + to.toString() + ":" + val);
            }


        }


    }


    /**
     * 正则类，实际就是一个有限自动机
     */
    public static class Regex {

        //有限不确定自动机
        private final Nfa nfa;

        //有限确定自动机
        private final Dfa dfa;

        private Regex(Nfa nfa) {
            this.nfa = nfa;
            dfa = nfa2dfa(nfa);
            System.out.println("--------------");
            nfa.print();
            System.out.println("--------------");
            dfa.print();
            System.out.println("--------------");
            System.out.println(dfa.getTable().toString());
            System.out.println("--------------");
            System.out.println(dfa.getDotTable().toString());
            System.out.println("--------------");
        }


        /**
         * 根据正则表达式编译为有限自动机
         *
         * @param regex 正则表达式
         * @return 根据正则表达式创建的有限自动机
         */
        public static Regex compile(String regex) {
            Nfa nfa = new Nfa();
            Graph.Node currentNode = nfa.getRoot();
            for (int i = 0; i < regex.length(); i++) {
                char c = regex.charAt(i);
                switch (c) {
                    //任意单个字符
                    case '.':
                        Nfa.NfaNode pointNode = new Nfa.NfaNode();
                        nfa.addEdge(Graph.DOT, currentNode, pointNode);
                        currentNode = pointNode;
                        break;
                    //前面的字符0次或多次
                    case '*':
                        Graph.Node anyLastNode = nfa.getAnyLastNode(currentNode);
                        if (anyLastNode == null) {
                            throw new RuntimeException("find last node error");
                        }
                        nfa.addEdge(Graph.CLOSURE, anyLastNode, currentNode);
                        nfa.addEdge(Graph.CLOSURE, currentNode, anyLastNode);
                        Nfa.NfaNode cloNode = new Nfa.NfaNode();
                        nfa.addEdge(Graph.CLOSURE, currentNode, cloNode);
                        currentNode = cloNode;
                        break;
                    //其他字符
                    default:
                        Nfa.NfaNode strNode = new Nfa.NfaNode();
                        nfa.addEdge(String.valueOf(c), currentNode, strNode);
                        currentNode = strNode;
                        break;
                }
            }
            currentNode.setType(1);
            nfa.setEndNode(currentNode);
            return new Regex(nfa);
        }

        /**
         * 根据子集构造法将nfa转化为dfa
         * 思路：
         * 1. 找出nfa初始节点出发经过CLOSURE边可以到达的所有的节点（状态）的集合，作为dfa的初始节点
         * 2. 找出nfa所有边的值（条件）的字符集合，不包括CLOSURE
         * 3. 找出dfa上一个节点对应的nfa的状态集合，找出从该集合节点（状态）出发经过某一条件的到达节点（状态）集合,再找出在nfa中任一上一个集合的状态出发经过CLOSURE边可以到达的所有的节点（状态）的集合，作为dfa的下一个节点
         * 4. 重复3，直至找不到节点
         *
         * @param nfa 有限不确定自动机
         * @return 与nfa等价你的dfa
         */
        public static Dfa nfa2dfa(Nfa nfa) {
            Graph.Node nfaRoot = nfa.getRoot();
            Set<String> symbolSet = new HashSet<>();
            nfa.findSymbol(nfaRoot, symbolSet, null);
            //NFA初始节点只经过CLOSURE边可以到达的状态结合
            Set<Graph.Node> rootSymbolNodeSet = new HashSet<>();
            Nfa.find(nfaRoot, Graph.CLOSURE, rootSymbolNodeSet, true, false);
            Dfa.DfaNode nodeExt = new Dfa.DfaNode(0, rootSymbolNodeSet.stream().map(Graph.Node::getId).sorted().collect(Collectors.joining(",")));
            Dfa dfa = new Dfa(nodeExt);
            nfa2dfa(rootSymbolNodeSet, null, symbolSet, dfa, null);
            Graph.Node root = dfa.getRoot();
            setEndNode(root, null, nfa.getEndNode());
            return dfa;
        }

        /**
         * 设置结束节点
         *
         * @param root         图
         * @param cacheNodeSet 防止死循环，缓存处理过的节点
         */
        public static void setEndNode(Graph.Node root, Set<Graph.Node> cacheNodeSet, Graph.Node nfaEndNode) {
            if (cacheNodeSet != null && cacheNodeSet.contains(root)) return;
            Set<Graph.Edge> edges = root.getEdges();
            Set<String> idSet = Arrays.stream(root.getId().split(",")).collect(Collectors.toSet());
            if (idSet.contains(nfaEndNode.getId())) {
                root.setType(1);
            }
            if (cacheNodeSet == null) cacheNodeSet = new HashSet<>();
            cacheNodeSet.add(root);
            for (Graph.Edge edge : edges) {
                Graph.Node to = edge.getTo();
                setEndNode(to, cacheNodeSet, nfaEndNode);
            }
        }


        /**
         * 遍历dfa判断输入的字符串是否被当前自动机接受
         *
         * @param text 待验证的字符串
         * @return 是否被自动机接受，true 接受，false 不接受
         */
        public boolean isMatch(String text) {
            return isMatch(text, -1, null, null);
        }

        public boolean isMatch(String text, int backIndex, Graph.Node backNode, String edgeVal) {
            Graph.Node node = backNode == null ? dfa.getRoot() : backNode;
            int i = backIndex == -1 ? 0 : backIndex;
            for (; i < text.length(); i++) {
                char c = text.charAt(i);
                String str = String.valueOf(c);
                Set<Graph.Edge> edges = node.getEdges();
                List<String> edgeVals = edges.stream().map(Graph.Edge::getVal).collect(Collectors.toList());
                Graph.Node matchNode = dfa.run(node, str, edgeVal == null ? (edgeVals.size() == 0 ? "" :edgeVals.get(0)) : edgeVal);
                if(edges.size() > 1 && edgeVals.contains(Graph.DOT)){
                    edgeVal = edgeVals.get(0);
                    backIndex = i;
                    backNode = node;
                }
                if (matchNode == null){
                    if(backNode != null){
                        Set<Graph.Edge> edgesTmp = backNode.getEdges();
                        List<String> edgeValsTmp = edgesTmp.stream().map(Graph.Edge::getVal).collect(Collectors.toList());
                        edgeValsTmp.remove(edgeVal);
                        if(edgeValsTmp.size() > 0){
                            return isMatch(text, backIndex, backNode, edgeValsTmp.get(0));
                        }
                    }else {
                        return false;
                    }
                }else {
                    edgeVal = null;
                }
                node = matchNode;
                if (i == text.length() - 1 && matchNode.getType() == 1) return true;
            }
            return false;
        }


        public Graph getNfa() {
            return nfa;
        }

        public Graph getDfa() {
            return dfa;
        }

        private static void nfa2dfa(
                Set<Graph.Node> rootSymbolNodeSet,
                Set<Set<Graph.Node>> cacheSetSet,
                Set<String> symbolSet,
                Dfa dfa,
                Graph.Node currentNode) {
            if (cacheSetSet != null && cacheSetSet.contains(rootSymbolNodeSet)) return;
            for (String symbol : symbolSet) {
                Set<Graph.Node> moveSet = Dfa.findMoveSet(rootSymbolNodeSet, symbol);
                Set<Graph.Node> resultSet = new HashSet<>();
                moveSet.forEach(moveNode -> {
                    Set<Graph.Node> symbolNodeSet = new HashSet<>();
                    Dfa.findNext(moveNode, Graph.CLOSURE, symbolNodeSet, true, false);
                    resultSet.addAll(symbolNodeSet);
                });

                if (resultSet.size() > 0) {
                    if (currentNode == null) currentNode = dfa.getRoot();
                    if (cacheSetSet == null) cacheSetSet = new HashSet<>();
                    Graph.Node node = new Dfa.DfaNode(0, resultSet.stream().map(Graph.Node::getId).sorted().collect(Collectors.joining(",")));
                    Graph.Node findNode = dfa.findNode(dfa.getRoot(), node);
                    Map<String, Graph.Node> table = dfa.getTable();
                    Map<String, Graph.Node> dotTable = dfa.getDotTable();
                    if (findNode != null) {
                        dfa.addEdge(symbol, currentNode, findNode);
                        if (Graph.DOT.equals(symbol)) {
                            dotTable.put(currentNode.getId() + ":" + symbol, findNode);
                        } else {
                            table.put(currentNode.getId() + ":" + symbol, findNode);
                        }
                    } else {
                        dfa.addEdge(symbol, currentNode, node);
                        if (Graph.DOT.equals(symbol)) {
                            dotTable.put(currentNode.getId() + ":" + symbol, node);
                        } else {
                            table.put(currentNode.getId() + ":" + symbol, node);
                        }
                    }
                    cacheSetSet.add(rootSymbolNodeSet);
                    nfa2dfa(resultSet, cacheSetSet, symbolSet, dfa, node);
                }
            }
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> caseMap = new HashMap<>();
        /*caseMap.put("a", Collections.singletonList("aa"));
        caseMap.put("aa", Collections.singletonList("aa"));
        caseMap.put("a*", Collections.singletonList("aa"));
        caseMap.put(".*", Arrays.asList("aa", "ab"));
        caseMap.put("c*a*b", Collections.singletonList("aab"));
        caseMap.put("mis*is*p*.", Collections.singletonList("mississippi"));
        caseMap.put("mis*is*ip*.", Collections.singletonList("mississippi"));
        caseMap.put(".*c", Collections.singletonList("ab"));
        caseMap.put("a.a", Collections.singletonList("aaa"));
        caseMap.put("a*a", Collections.singletonList("aaa"));
        caseMap.put("ab*a*c*a", Arrays.asList("aaa", "aaba"));
        caseMap.put(".*..a*", Arrays.asList("a"));*/
        caseMap.put("ab.*de", Arrays.asList("abcdede"));
        //caseMap.put(".", Arrays.asList("a"));
        caseMap.forEach((key, value) -> {
            Long start = System.currentTimeMillis();
            Solution solution = new Solution();
            value.forEach(val -> System.out.println(key + ", " + val + " : " + solution.isMatch(val, key)));
            Long end = System.currentTimeMillis();
            System.out.println("时间:" + (end - start));
        });


    }


}
