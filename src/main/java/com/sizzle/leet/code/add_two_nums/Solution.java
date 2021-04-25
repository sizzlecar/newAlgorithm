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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newNode = new ListNode(0);
        ListNode headPointer = newNode;
        while(l1 != null || l2 != null){
            int carry = 0;
            int addResult = 0;
            int tmp = 0;
            if(l1 != null){
                addResult += l1.val;
            }
            if(l2 != null){
                addResult += l2.val;
            }
            addResult += newNode.val;
            //保留个位数
            tmp = addResult % 10;
            //记录进位
            if(addResult / 10 > 0){
                carry = 1;
            }
            addResult = tmp;
            newNode.val = addResult;
            if(carry > 0){
                newNode.next = new ListNode(carry);
            }
            if(l1 != null){
                l1 = l1.next;
            }else{
                l1 = null;
            }
            if(l2 != null){
                l2 = l2.next;
            }else{
                l2 = null;
            }
            ListNode tmpNode = newNode.next;
            if(tmpNode == null && (l1 != null || l2 != null)){
                tmpNode = new ListNode(0);
                newNode.next = tmpNode;
                newNode = tmpNode;
            }else {
                newNode = newNode.next;
            }

        }
        return headPointer;
    }
}
