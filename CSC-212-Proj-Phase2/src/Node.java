
public class Node<T> {
	
	public Event data;
	public Node<T> next;

	public Node(Event d) {
		data = d;
		next = null;
	}
}
