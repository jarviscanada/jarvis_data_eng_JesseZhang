package ca.jrvs.practice.codingChallenge;

import java.util.Deque;
import java.util.LinkedList;


/**
 * ticket: https://www.notion.so/jarvisdev/Implement-Queue-using-Stacks-d049f868f92840f9a0703fdd1239508b
 */
public class QueueUsingStacks {

  /**
   * Push - O(n)O(n) per operation, Pop - O(1)O(1) per operation
   */
  class MyQueue1 {

    Deque<Integer> s1;
    Deque<Integer> s2;

    public MyQueue1() {
      s1 = new LinkedList<>();
      s2 = new LinkedList<>();
    }

    public void push(int x) {
      while (!s2.isEmpty()) {
        s1.push(s2.pop());
      }
      s1.push(x);
      while (!s1.isEmpty()) {
        s2.push(s1.pop());
      }
    }

    public int pop() {
      return s2.pop();
    }

    public int peek() {
      return s2.peek();
    }

    public boolean empty() {
      return s2.size() == 0;
    }
  }


  /**
   * Push - O(1)O(1) per operation, Pop - Amortized O(1)O(1)
   */
  class MyQueue2{
    Deque<Integer> s1;
    Deque<Integer> s2;
    int front;

    public MyQueue2() {
      s1 = new LinkedList<>();
      s2 = new LinkedList<>();
    }

    public void push(int x) {
      if (s1.isEmpty()) {
        front = x;
      }
      s1.push(x);
    }

    public int pop() {
      if (s2.isEmpty()) {
        while (s1.size() > 0) {
          s2.push(s1.pop());
        }
      }
      return s2.pop();
    }

    public int peek() {
      if (!s2.isEmpty()) {
        return s2.peek();
      }
      return front;
    }
    
    public boolean empty() {
      return s1.size() == 0 && s2.size() == 0;
    }
  }

}
