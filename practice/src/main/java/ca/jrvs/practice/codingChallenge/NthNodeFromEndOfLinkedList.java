package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.codingChallenge.LinkedListCycle.ListNode;

/**
 * ticket: https://www.notion.so/jarvisdev/Nth-Node-From-End-of-LinkedList-02322cef05324fae8bf406cefd29b905
 */
public class NthNodeFromEndOfLinkedList {

  /**
   * Big-O: O(n) Justification: go through the linkedList
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {

    ListNode dummy = new ListNode(0, head);
    ListNode fast = dummy, slow = dummy;
    while (n > 0 && fast != null) {
      fast = fast.next;
      n--;
    }

    while (fast.next != null) {
      slow = slow.next;
      fast = fast.next;
    }

    slow.next = slow.next.next;
    return dummy.next;

  }


  public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

}
