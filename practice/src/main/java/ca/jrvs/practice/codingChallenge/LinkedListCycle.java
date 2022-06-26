package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/LinkedList-Cycle-0eecb5b5985f4318b86d876ab268fe0f
 */
public class LinkedListCycle {

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

  /**
   * Big-O: O(n) Justification: go through the linkedList
   */
  public boolean hasCycle(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    while(fast!=null && fast.next!=null){
      slow = slow.next;
      fast = fast.next.next;
      if(slow.next.equals(fast.next)){
        return true;
      }
    }
    return false;
  }
}
