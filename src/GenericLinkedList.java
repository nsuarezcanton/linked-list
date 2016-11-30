/**
 * Implementation of a general purpose GenericLinkedList class.
 *
 * @author Nick Suarez-Canton Trueba
 * @version 02/10/2015
 */
public class GenericLinkedList<T extends Comparable<T>> {
    // *************************************************************************
    // Invariant of GenericLinkedList:
    // (1) If the list is empty (size = 0), head.data refers to null.
    // (2) If the list is not empty, head.data refers to a null node, and
    // head.link refers to the first non-empty node in the list.
    // (3) manyStrings always contains the current number of strings stored in
    // the list.
    // *************************************************************************

    private GenericListNode<T> head;
    private int size;

    /**
     * Default constructor for the GenericLinkedList class. It creates an empty
     * list.
     */
    public GenericLinkedList() {
        head = new GenericListNode<T>();
        size = 0;
    }


    /**
     * Obtains the number of elements on the list.
     *
     * @return the number of items in the GenericLinkedList
     */
    public int size() {
        return size;
    }


    /**
     * Retrieves the element at a given index position.
     *
     * @param index location of the desired item
     * @return the string at the desired index position
     * @throws IllegalArgumentException indicates that the index is not valid.
     * @pre the GenericLinkedList is not empty
     */
    public T get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("invalid index: " + index);
        GenericListNode<T> cursor;
        cursor = head;
        for (int count = -1; count < index; count++)
            cursor = cursor.link;
        return cursor.data;
    }


    /**
     * Changes the value of the element at a given index position.
     *
     * @param value the string to substitute the current one
     * @param index location of the desired item
     * @throws IllegalArgumentException indicates that the index is not valid.
     * @pre the GenericLinkedList is not empty
     * @post the given index refers to the new value
     */
    public void set(T value, int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("invalid index: " + index);
        GenericListNode<T> cursor = head;
        for (int count = -1; count < index; count++)
            cursor = cursor.link;
        cursor.data = value;
    }


    /**
     * Adds an element at the given index position.
     *
     * @param toAdd the string to be stored in the GenericLinkedList
     * @param index desired location
     * @throws IllegalArgumentException indicates that the index is not valid.
     * @post value is stored a the given index
     * @post size has decreased by one element
     */
    public void add(int index, T toAdd) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("invalid index: " + index);

        GenericListNode<T> cursor = head;
        for (int count = -1; count < index - 1; count++) {
            cursor = cursor.link;
        }
        cursor.link = new GenericListNode<T>(toAdd, cursor.link);

        size++;
    }


    /**
     * Removes the element at the given index position.
     *
     * @param index integer that represents the position of the item to be removed
     * @pre the GenericLinkedList is not empty
     * @post size has decreased by one element
     */
    public T remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("invalid index: " + index);
        GenericListNode<T> cursor = head;
        for (int count = -1; count < index - 1; count++) {
            cursor = cursor.link;
        }
        T removed = cursor.link.data;
        cursor.link = cursor.link.link;
        size--;
        return removed;

    }


    /**
     * Converts the GenericLinkedList into a string.
     *
     * @return a string that represents the items inside the linked list in the
     * following format, e.g. "{A,B,C,}".
     */
    public String toString() {

        String contents = "";
        if (size == 0)
            return contents;
        GenericListNode<T> currentNode = head.link;

        for (int count = 0; count < size; count++) {
            contents = contents.concat(currentNode.toString()) + "\n";
            currentNode = currentNode.link;
        }

        contents = contents.substring(0, contents.length() - 1);

        return contents;
    }


    /**
     * Inserts a new node in the correct spot such that all elements before
     * toAdd are "smaller or equal" according to the compareTo method and all
     * elements after toAdd are "greater or equal" according to the compareTo
     * method defined for type T.
     *
     * @param toAdd data to be added to the list
     */
    public void insertSorted(T toAdd) {
        GenericListNode<T> nodeBefore = findNodeBefore(toAdd);
        nodeBefore.link = new GenericListNode<T>(toAdd, nodeBefore.link);
        size++;

    }

    // Helper method that given a T object finds the right place
    // for the item in the list using the compareTo method.
    private GenericListNode<T> findNodeBefore(T toAdd) {
        GenericListNode<T> cursor = head;
        if (size == 0)
            return cursor;
        else {
            cursor = head.link;
            GenericListNode<T> previous = head;
            while (cursor != null) {
                int compare = cursor.data.compareTo(toAdd);
                if (compare >= 0) {
                    return previous;
                } else {
                    previous = cursor;
                    cursor = cursor.link;
                    if (cursor == null)
                        return previous;
                }
            }
        }
        return null;
    }


    /**
     * Finds the first node whose data match toRemove (according to the
     * compareTo method), and returns the data. If the data was not found,
     * method returns null.
     *
     * @param toFind data to find in the list
     * @return a reference to the data
     */
    public T find(T toFind) {

        GenericListNode<T> cursor = head.link;
        for (int index = 0; index < size(); index++) {
            T toCompare = cursor.data;
            if (toCompare.compareTo(toFind) == 0)
                return toCompare;
            cursor = cursor.link;
        }
        return null;
    }


    /**
     * Finds the first node whose data match toRemove(according to the
     * compareTo method), removes that node, and returns the data. If
     * toRemove is not in the list, the method returns null.
     *
     * @param toRemove node to be removed
     * @return reference to the data that was in the node that has been removed
     */
    public T remove(T toRemove) {
        GenericListNode<T> cursor = head.link;

        for (int index = 0; index < size(); index++) {


            if (cursor.data.compareTo(toRemove) == 0) {
                return remove(index);
            }

            cursor = cursor.link;
        }
        return null;
    }
}