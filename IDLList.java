package hw3;

import java.util.ArrayList;

public class IDLList<E> {
	//I pledge my honor that I have abided by the Stevens Honor System -ppate785,
	private class Node<E>{
		//data fields
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		Node (E elem){
			this.data = elem;
			this.next = null;
			this.prev = null;
		}
		
		Node(E elem, Node<E> prev, Node<E> next){
			this.data = elem;
			this.next = next;
			this.prev = prev;
		}
	}
	//data fields
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices = new ArrayList<Node<E>>();
	
	public IDLList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.indices = new ArrayList<Node<E>>();
	}
	
	//adds an element at a specific index
	public boolean add(int index, E elem) {
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else if(index == 0){
			this.add(elem);
		} else {
			Node<E> temp = head;
			for(int i = 0; i < index; i++) {
				temp = temp.next;
				i++;
			}
			Node<E> current = new Node(elem, temp, temp.next);
			temp.next = current;
			indices.add(index, current);
			size++;
			Node<E> loop = current;
			while(loop.next != null) {
				loop = loop.next;
			}
			tail = loop;
		}
		return true;
	}
	//adds an element at the head
	public boolean add(E elem) {
		if(head == null) {
			head = new Node(elem, null, null);
			tail = head;
			size++;
			indices.add(0, head);
			return true;
		} else {
			Node<E> heading = new Node(elem, null, head);
			head = heading;
			size++;
			indices.add(0, head);
			Node<E> temp = head;
			while(temp.next != null) {
				temp = temp.next;
			}
			tail = temp;
			return true;
		}
	}
	//adds an element at the end
	public boolean append(E elem) {
		if(head == null) {
			head = new Node(elem, null, null);
			tail = head;
			size++;
			indices.add(0, tail);
			return true;
		} else {
			Node<E> current = new Node(elem, tail, null);
			if (tail != null) {
				tail.next=current;
			}
			
			tail = current;
			size++;
			indices.add(size-1, tail);
			return true;
		}
	}
	//returns the object at the index
	public E get(int index) {
		if(index >= size || index < 0 || head == null) {
			throw new IllegalArgumentException();
		}
		int i = 0;
		while(i < index) {
			i++;
		}
		return indices.get(i).data;
		
	}
	//returns the head
	public E getHead() {
		if(head==null) {
			throw new IllegalArgumentException();
		} else {
			return indices.get(0).data;
		}
	}
	//returns the tail
	public E getLast() {
		if (head == null) {
			throw new IllegalArgumentException();
		} else {
			return indices.get(size-1).data;
		}
	}
	//returns the size
	public int size() {
		return this.size;
	}
	//removes the head
	public E remove() {
		if (head == null) {
			throw new IllegalArgumentException();
		} else {
			Node<E> temp = head;
			head = head.next;
			size--;
			indices.remove(0);
			return temp.data;
		}
	}
	//removes the element at the tail
	public E removeLast() {
		if (head == null) {
			throw new IllegalArgumentException();
		} else {
			Node<E> temp = tail;
			tail = tail.prev;
			indices.remove(size-1);
			size--;
			return temp.data;
		}
	}
	//removes the element at the index
	public E removeAt(int index) {
		if(head==null || index >= size || index < 0) {
			throw new IllegalArgumentException();
		} else if(index == 0) {
			return this.remove();
		} else if(index == size) {
			return this.removeLast();
		} else {
			int i = 0;
			Node<E> temp = head;
			while(i < index-1) {
				i++;
				temp = temp.next;
			}
			temp.next = temp.next.next;
			E hold = indices.get(i+1).data;
			indices.remove(i+1);
			size--;
			return hold;
		}
	}
	//removes the first occurence of the element
	public boolean remove(E elem) {
		if(head==null) {
			throw new IllegalArgumentException();
		} else {
			Node<E> temp = head;
			int i = 0;
			while(temp!=null) {
				if(temp.data == elem) {
					this.removeAt(i);
					return true;
				}
				temp = temp.next;
				i++;
			}
			return false;
		}
	}
	
	public String toString() {
		if(head == null) {
			throw new IllegalArgumentException();
		} else {
			String s = "";
			Node<E> current = head;
			while(current.next!= null) {
				s+="[" + current.data + "] ";
						
				current= current.next;
			}
			return s;
		}
	}
	
	public static void main(String[] args) {
		IDLList test = new IDLList();
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		System.out.println(test.removeLast());
		
	}
}
