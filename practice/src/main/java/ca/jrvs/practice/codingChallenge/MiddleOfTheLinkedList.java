package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Middle-of-the-Linked-List-816deac9baad4b3e94ee5e234610f5fd
 */
public class MiddleOfTheLinkedList {

  /**
   * Big-O: O(n) Justification: go through the linkedList
   */
  public ListNode middleNode(ListNode head) {

    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
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
