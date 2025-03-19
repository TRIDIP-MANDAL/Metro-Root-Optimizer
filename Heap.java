import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> {
  ArrayList<T> data = new ArrayList<>();
  HashMap<T, Integer> map = new HashMap<>();

  /**
   * Adds an item to the heap.
   * @param item The item to be added.
   */
  public void add(T item) {
    data.add(item);
    map.put(item, this.data.size() - 1);
    upheapify(data.size() - 1);
  }

  /**
   * Restores the heap property by moving the element at index ci up.
   * @param ci The index of the element to be moved up.
   */
  private void upheapify(int ci) {
    int pi = (ci - 1) / 2;
    if (isLarger(data.get(ci), data.get(pi)) > 0) {
      swap(pi, ci);
      upheapify(pi);
    }
  }

  /**
   * Swaps the elements at indices i and j.
   * @param i The index of the first element.
   * @param j The index of the second element.
   */
  private void swap(int i, int j) {
    T ith = data.get(i);
    T jth = data.get(j);

    data.set(i, jth);
    data.set(j, ith);
    map.put(ith, j);
    map.put(jth, i);
  }

  /**
   * Displays the elements of the heap.
   */
  public void display() {
    System.out.println(data);
  }

  /**
   * Returns the number of elements in the heap.
   * @return The size of the heap.
   */
  public int size() {
    return this.data.size();
  }

  /**
   * Checks if the heap is empty.
   * @return True if the heap is empty, false otherwise.
   */
  public boolean isEmpty() {
    return this.size() == 0;
  }

  /**
   * Removes and returns the root element of the heap.
   * @return The root element of the heap.
   * @throws NoSuchElementException if the heap is empty.
   */
  public T remove() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Heap is empty");
    }
    swap(0, this.data.size() - 1);
    T rv = this.data.remove(this.data.size() - 1);
    downheapify(0);

    map.remove(rv);
    return rv;
  }

  /**
   * Restores the heap property by moving the element at index pi down.
   * @param pi The index of the element to be moved down.
   */
  private void downheapify(int pi) {
    int lci = 2 * pi + 1;
    int rci = 2 * pi + 2;
    int mini = pi;

    if (lci < this.data.size() && isLarger(data.get(lci), data.get(mini)) > 0) {
      mini = lci;
    }

    if (rci < this.data.size() && isLarger(data.get(rci), data.get(mini)) > 0) {
      mini = rci;
    }

    if (mini != pi) {
      swap(mini, pi);
      downheapify(mini);
    }
  }

  /**
   * Returns the root element of the heap without removing it.
   * @return The root element of the heap.
   * @throws NoSuchElementException if the heap is empty.
   */
  public T get() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Heap is empty");
    }
    return this.data.get(0);
  }

  /**
   * Compares two elements.
   * @param t The first element.
   * @param o The second element.
   * @return A positive number if t is larger, a negative number if o is larger, and 0 if they are equal.
   */
  public int isLarger(T t, T o) {
    return t.compareTo(o);
  }

  /**
   * Updates the priority of an element in the heap.
   * @param pair The element whose priority is to be updated.
   */
  public void updatePriority(T pair) {
    int index = map.get(pair);
    upheapify(index);
  }
}