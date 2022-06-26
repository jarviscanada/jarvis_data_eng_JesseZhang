package ca.jrvs.practice.codingChallenge;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ticket: https://www.notion.so/jarvisdev/Duplicate-LinkedList-Node-76fcaf98d3dd4856b71012dffe7c44ca
 */
public class DuplicateLinkedListNode {

  /**
   * Big-O: O(n) Justification: go through the linkedList
   */
  public static ListNode deleteDuplicates(ListNode head) {
    if(head==null)return null;

    Set<Integer> set = new HashSet<>() ;

    ListNode index = head;
    while(index.next!=null){
      if(set.contains(index.next.val)){
        index.next = index.next.next;
      }else{
        set.add(index.next.val);
        index = index.next;
      }
    }
    return head;
  }




  public static class ListNode {

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

  static void printList(ListNode node)
  {
    while (node != null) {
      System.out.print(node.val + " ");
      node = node.next;
    }
  }

  public static void main(String[] args)
  {

    ListNode head = new ListNode(10);
    head.next = new ListNode(12);
    head.next.next = new ListNode(11);
    head.next.next.next = new ListNode(11);
    head.next.next.next.next = new ListNode(12);
    head.next.next.next.next.next = new ListNode(11);
    head.next.next.next.next.next.next
        = new ListNode(10);

    System.out.println(
        "Linked List before removing duplicates : \n ");
    printList(head);

    deleteDuplicates(head);
    System.out.println("");
    System.out.println(
        "Linked List after removing duplicates : \n ");
    printList(head);
  }


}
