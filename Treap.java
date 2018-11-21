package hw5;
import java.util.Random;
import java.util.Stack;
//I pledge my honor that I have abided by the Stevens Honor System -ppate78

public class Treap<E extends Comparable<E>> {

	private static class Node<E>{
		public E data;  
		public int priority;  
		public Node<E> left;
		public Node<E> right;
		
		//Constructor
		public Node(E data, int priority) {
			if(data == null) {
				throw new NullPointerException("Data cannot be null");
			}
			this.data = data;
			this.priority = priority;
		}
		//rotates left
		Node<E> rotateLeft(){
			 Node<E> one = this.right;
			 Node<E> two = one.left;
			 one.left = this;
			 this.right = two;
			 return one;
		 }
		//rotates right
		Node<E> rotateRight(){
			 Node<E> one = this.left;
			 Node<E> two = one.right;
			 one.right = this;
			 this.left = two;
			 return one;
			 
		 }
		 public String toString() {
	            return "(key="+this.data.toString()+", priority="+this.priority+")";
	        }
	}
	
	private Random priorityGenerator;
	private Node<E> root; 
	
	
	public Treap() {
		this.root = null;
		this.priorityGenerator = new Random();
	}
	

	public Treap(long seed) {
		this.root = null;
		this.priorityGenerator = new Random(seed);
	}
	//adds the item to the treap
	boolean add(E key) {
		if(find(key)) {
			return false;
		} else {
		return add(key, priorityGenerator.nextInt());
		}
	}
	
	boolean add(E key, int priority) {
		if (this.root == null) {
			this.root = new Node<E>(key, priority);
			return true;
		}
		if(find(key)){
			return false;
		}
		Stack<Node<E>> pile = new Stack<Node<E>>();
		Node<E> n = root;
		while (n != null) {
		pile.push(n);
			int comp = n.data.compareTo(key);
			if(comp > 0) {
				if(n.left == null) {
					n.left = new Node<E>(key, priority);
					reheap(pile, n.left);
					return true;
				} else {
					n = n.left;
				}
			} else {
				if(n.right == null) {
					n.right = new Node<E>(key, priority);
					reheap(pile, n.right);
					return true;
				} else {
					n = n.right;
				}
			}
	}
		return false;
	}
	//reheaps
	public void reheap(Stack<Node<E>> pile, Node<E> curr) {
        while (!pile.isEmpty()) {
            Node<E> parent = pile.pop();
            if (parent.priority < curr.priority){
                if (parent.data.compareTo(curr.data) > 0)
                    curr = parent.rotateRight();
                else
                    curr = parent.rotateLeft();
                // fix grandparents link
                if (!pile.isEmpty())
                    if (pile.peek().left == parent)
                        pile.peek().left = curr;
                    else
                        pile.peek().right = curr;
                else
                    this.root = curr;
            } else
                break;
        }
    }
	
	//deletes the item from the treap
	private Node<E> deletehelper(Node<E> current, E key){
        if (current == null)
            return current;
        if(key.compareTo(current.data) < 0){
            current.left = deletehelper(current.left, key);
        }  
        else if (current.left == null){
            Node<E> temp = current.right;
            current = temp;
        }
        
        else if (current.right == null){
            Node<E> temp = current.left;
            current = temp;
        } 
        else if (key.compareTo(current.data) > 0){
            current.right = deletehelper(current.right, key);
        } 
        else if (current.left.priority < current.right.priority){
            current = current.rotateLeft();
            current.left = deletehelper(current.left, key);
        } 
        else {
            current = current.rotateRight();
            current.right = deletehelper(current.right, key);
        }
        return current;
    }
 
   
    public boolean delete(E key){
        if(root == null) {
        	return false;
        }
        if(find(key)==false) {
            return false;
        }
        this.root = deletehelper(this.root, key);
        return true;
    }
	//checks to see if item is in the treap
    private boolean find(Node<E> root, E key) {
		while(root != null) {
			if (key == root.data) {
				return true;
			}
			else if (key.compareTo(root.data) < 0) {
				root = root.left;
			}
			else {
				root = root.right;
			}
		}
		return false;
	}
	public boolean find(E key) {
		return find(root,key);
	}
	
	public void preOrderTraverse(Node<E> n, int depth, StringBuilder s) {
		for (int i = 1; i < depth; i++) {
            s.append("  ");
        }

        if (n == null) {
            s.append("null\n");
        } else {
            s.append(n.toString());
            s.append("\n");
            preOrderTraverse(n.left, depth + 1, s);
            preOrderTraverse(n.right, depth + 1, s);

        }
		
	}
	//toString for treaps
	public String toString() {
		StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();

	}
	public static void main (String[] args) {
		
	}

	
}
