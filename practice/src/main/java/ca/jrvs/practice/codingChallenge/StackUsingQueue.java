package ca.jrvs.practice.codingChallenge;


import java.util.LinkedList;
import java.util.Queue;

/**
 * ticket: https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-28835a1d3cca4a5397c93bb9bf49af1c
 */
public class StackUsingQueue {


  /**
   * Using two queues
   */
  class MyStack1 {

    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack1() {
      q1 = new LinkedList<>();
      q2 = new LinkedList<>();

    }

    public void push(int x) {
      q1.add(x);
    }

    public int pop() {
      while (q1.size() > 1) {
        q2.add(q1.remove());
      }
      int last = q1.remove();
      while (q2.size() > 0) {
        q1.add(q2.remove());
      }
      return last;
    }

    public int top() {
      while (q1.size() > 1) {
        q2.add(q1.remove());
      }
      int last = q1.peek();
      q2.add(q1.remove());
      while (q2.size() > 0) {
        q1.add(q2.remove());
      }
      return last;
    }

    public boolean empty() {
      return q1.size() == 0;
    }
  }


  /**
   * using one queue
   */
  class MyStack2 {

    Queue<Integer> q;

    public MyStack2() {
      q = new LinkedList<>();
    }

    public void push(int x) {
      q.add(x);
    }

    public int pop() {
      for (int i = q.size(); i > 1; i--) {
        q.add(q.remove());
      }
      return q.remove();
    }

    public int top() {
      for (int i = q.size(); i > 1; i--) {
        q.add(q.remove());
      }
      int last = q.peek();
      q.add(q.remove());
      return last;
    }

    public boolean empty() {
      return q.size() == 0;
    }
  }

}
