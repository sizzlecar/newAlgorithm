package com.sizzle.leet.code.add_two_nums;

/**
 * Created by sizzle_carl on 2018/4/16.
 *
 *
 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。

 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807

 */
public class Solution {

    private ListNode calc(ListNode l1, ListNode l2, boolean lastCarry, ListNode parentNode, ListNode rootNode){
        if(l1 == null && l2 == null && !lastCarry){
            //l1,l2都为null直接返回
            return rootNode;
        }
        //分别取出l1,l2的值
        int l1Val = l1 == null ? 0 : l1.val;
        int l2Val = l2 == null ? 0 : l2.val;
        int sum = l1Val + l2Val + (lastCarry ? 1 : 0);
        boolean carryFlag = false;
        if(sum >= 10){
            //如果进位，则设置标志位，重新计算sum的值
            carryFlag = true;
            sum = sum - 10;
        }

        //初始化sum node
        ListNode newSumNode = new ListNode(sum);
        if(parentNode != null){
            parentNode.next = newSumNode;
        }else {
            //保存一下根节点的引用
            rootNode = newSumNode;
        }

        return calc(l1 == null ? null : l1.next, l2 == null ? null : l2.next, carryFlag, newSumNode, rootNode);
    }

    public static void main(String[] args) {
        ListNode l10 = new ListNode(2);
        ListNode l11 = new ListNode(4);
        ListNode l12 = new ListNode(3);
        l10.next = l11;
        l11.next = l12;

        ListNode l20 = new ListNode(5);
        ListNode l21 = new ListNode(6);
        ListNode l22 = new ListNode(9);
        ListNode l23 = new ListNode(9);
        l20.next = l21;
        l21.next = l22;
        l22.next = l23;

        ListNode listNode = new Solution().calc(l10, l20, false, null, null);
        System.out.println(listNode);
    }
}
