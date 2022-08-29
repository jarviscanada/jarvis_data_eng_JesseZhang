package ca.jrvs.practice.dataStructure.stackQueue;

/**
 * This is a simplified version of java.util.Deque
 */
public interface JDeque<E> extends JStack<E>, JQueue<E> {

  void addFirst(E e);

  void addLast(E e);

  E removeFirst();

  E removeLast();

  E peekFirst();

  E peekLast();
}