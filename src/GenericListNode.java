/**
 * Implementation of a node object for GenericLinkedList class.
 * 
 * @author Nick Suarez-Canton Trueba
 * @version 02/10/2015
 */
public class GenericListNode<T> {
	// *************************************************************************
	// Invariant of GenericListNode:
	// (1) Instance variable data will always contain a reference to a String
	// object.
	// (2) Instance variable link will either contain a reference to the next
	// node in a GenericLinkedList, or, in case that a node is the last one in a
	// GenericLinkedList, it will contain a null reference.
	// *************************************************************************

	public T data;
	public GenericListNode<T> link;

    public GenericListNode(){
        data = null;
        link = null;
    }

	public GenericListNode(T initialData, GenericListNode<T> initialLink) {
		data = initialData;
		link = initialLink;
	}

	public String toString() {
		return data.toString();
	}
}