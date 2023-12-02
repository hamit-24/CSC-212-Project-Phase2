package csc212;

public class BSTNode {
	public String key;
	public Contact data;
	public BSTNode left, right;

	public BSTNode(String k, Contact val) {
		key = k;
		data = val;
		left = right = null;
	}

}
