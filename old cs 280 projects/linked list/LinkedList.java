//Matthew Butler
//Cs 114 Lab
public class LinkedList {
	private Node front;

	public LinkedList() {
		front = null;
	}

	public void addToFront(String val) {
		front = new Node(val, front);
	}

	public void addToEnd(String val) {
		Node newnode = new Node(val, null);

		if (front == null)
			front = newnode;
		else {

			Node temp = front;
			while (temp.next != null)
				temp = temp.next;

			temp.next = newnode;
		}
	}

	public void removeFirst() {
		if (front != null)
			front = front.next;
	}

	public String head(){
		return front.val;
	
}
	/*public int length() {
		int length = 0;

		while (front != null) {
			length++;
			front = front.next;
		}
		return length;

	}

	public void removeLast() {
		Node current = front;

		if (length() > 1) {
			while (current.next.next != null) {
				current = current.next;
			}
			current.next = null;
		} else {
			front = null;
		}
	}*/

	public void print() {
		System.out.println("---------------------");
		System.out.print("List elements: ");

		Node temp = front;

		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
		System.out.println("\n---------------------\n");
	}

	private class Node {
		public String val;
		public Node next;

		public Node(String val, Node next) {
			this.val = val;
			this.next = next;
		}
	}
}