package com.huangjin.interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2017-5-19.
 * 输入一个链表，从尾到头打印链表每个节点的值。
 */
public class QuestionC {

    @Test
    public void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        System.out.println(printListFromTailToHead(node1));

        Solution solution = new Solution();
        System.out.println(solution.printListFromTailToHead(node1));
    }

    /**
     * 自己的方法
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();

        if(listNode != null) {
            recursive(list, listNode);
            list.add(listNode.val);
        }
        return list;

    }

    //递归函数
    private void recursive(List<Integer> list, ListNode listNode) {
        if(listNode.next == null) {
            list.add(listNode.val);
        } else {
            listNode = listNode.next;
            recursive(list, listNode);
            if(listNode.next != null) {
                list.add(listNode.val);
            }
        }
    }


}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}


/**
 * 超简洁方法
 */
class Solution {
    ArrayList<Integer> arrayList = new ArrayList<>();

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        if(listNode!=null) {
            this.printListFromTailToHead(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }
}
