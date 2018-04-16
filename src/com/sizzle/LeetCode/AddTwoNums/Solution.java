package com.sizzle.LeetCode.AddTwoNums;

/**
 * Created by sizzle_carl on 2018/4/16.
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
