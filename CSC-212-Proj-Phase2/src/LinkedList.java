public class LinkedList {
	private Node head;
	private Node current;
	
	
	public LinkedList() {
		head = current = null;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public boolean isFull() {
		return false;
	}
	
	public boolean last() {
		return current.next==null;
	}
	
	public void findFirst() {
		current= head;
	}
	
	public void findNext() {
		current=current.next;
	}
	
	public void Update(Event d) {
		current.data=d;
	}
	
	public Event retrive() {
		if(current==null)
			return null;
		return current.data;
	}
	
	public void insert(Event d) {
		Node temp = new Node(d);
		if(head == null) {
			head = temp;
			current = temp;
		}
		else {
			temp.next = current.next;
			current.next = temp;
			current = temp;
		}
	}
	
	public void remove() {
		if(current == head) {
			head = head.next;
			current = current.next;
		}else {
			Node p = head;
			while(p.next!= current) {
				p=p.next;
			}
			p.next = current.next;
			if(current.next!= null)
				current = current.next;
			else
				current = head;
		}
	}
	
	public boolean search(Event x) {
		Node temp = head;
		while(temp != null) {
			if(temp.data.equals(x))
				return true;
			else 
				temp = temp.next;
		}
		return false;
	}
	
	public void display() {
		Node temp = head;
		while(temp != null) {
			System.out.print(temp.data+" ");
			System.out.println();
			temp=temp.next;
		}
	}

	//This method inserts an element of type T(generic) to a linked list in sorted way
	public void addSorted( Event x) {
		
		Node temp = new Node(x);
		if(head == null) {
			head = temp;
			current = temp;
		}
		else {

				if(x.getTitle().compareTo(head.data.getTitle())<0){
					temp.next = head;
					head = temp;
					current = temp;
				}
				else {
					Node front = head, back=null;
					while(front!= null && (front.data.getTitle().compareTo(x.getTitle())<=0)){
						back = front;
						front = front.next;
					}
					back.next = temp;
					temp.next = front;
				}	
	    }
	}
}