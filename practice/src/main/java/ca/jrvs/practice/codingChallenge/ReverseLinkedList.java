package ca.jrvs.practice.codingChallenge;


import ca.jrvs.practice.codingChallenge.DuplicateLinkedListNode.ListNode;

/**
 * ticket: https://www.notion.so/jarvisdev/Reverse-Linked-List-eacd7c7b56f7491abd520aca6cc96be0
 */
public class ReverseLinkedList {


  /**
   * Big-O: O(n) Justification: go through the list
   */
  public ListNode reverseList1(ListNode head) {

    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    return prev;
  }







  /**
   * Big-O: O(n) Justification: linearly go through the nodes by calling functions
   */
  public ListNode reverseList2(ListNode head) {
    return reverse(head, null);

  }

  private ListNode reverse(ListNode head, ListNode newHead) {
    if (head == null) {
      return newHead;
    }

    ListNode next = head.next;
    head.next = newHead;
    newHead = head;
    head = next;

    return reverse(head, newHead);

  }

}
