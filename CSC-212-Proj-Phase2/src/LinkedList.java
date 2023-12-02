
public class LinkedList <T extends Comparable<T>> {
	private Node<T> head;
	private Node<T> current;
	
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
	public void Update(T d) {
		current.data=d;
	}
	public T retrive() {
		if(current==null)
			return null;
		return current.data;
	}
	public void insert(T d) {
		Node<T> temp = new Node<T>(d);
		if(head == null) {
			head = temp;
			current = temp;
		}else {
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
			Node<T> p = head;
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
	public boolean search(T x) {
		Node<T> temp = head;
		while(temp != null) {
			if(temp.data.equals(x))
				return true;
			else 
				temp = temp.next;
		}
		return false;
	}
	public void display() {
		Node<T> temp = head;
		while(temp != null) {
			System.out.print(temp.data+" ");
			System.out.println();
			temp=temp.next;
		}
	}
	public void addSorted(T x) {
		Node<T> temp = new Node<T>(x);
		if(head == null) {
			head = temp;
			current = temp;
		}else {
			if(x instanceof Event) {
				if(((Event)(x)).getTitle().compareTo(((Event)head.data).getTitle())<0){
					temp.next = head;
					head = temp;
					current = temp;
				}else {
					Node<T> front = head, back=null;
					while(front!= null && (((Event)(front.data)).getTitle().compareTo(((Event)x).getTitle())<=0)){
						back = front;
						front = front.next;
					}
					back.next = temp;
					temp.next = front;
				}	
			}else {
				if(((Contact)(x)).getName().compareTo(((Contact)head.data).getName())<0){
					temp.next = head;
					head = temp;
					current = temp;
				}else {
					Node<T> front = head, back=null;
					while(front!= null && ((front.data).compareTo(x)<=0)){
						back = front;
						front = front.next;
					}
					back.next = temp;
					temp.next = front;
			    }
	        }
	    }
	}
}