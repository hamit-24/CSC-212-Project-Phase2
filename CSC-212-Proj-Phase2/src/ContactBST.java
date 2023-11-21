
public class ContactBST {
	private BSTNode  root,current;
	private ContactBST sameX;// x mean any thing,EX: first name, email, address, birthday
	private boolean exist;
	private Contact c;
	public ContactBST() {
		root = current = null;
	}
	public boolean empty() {
		return root == null;
	}
	public boolean full() {
		return false;
	}
	public Contact retrieve() {
		return current.data;
	}
	public boolean findkey(String name) {
		BSTNode p = root, q = root;
		if (empty())
			return false;
		while(p != null) {
			q=p;
			if(p.key.equals(name)) {
				current = p;
				return true;
			}else if(p.key.compareTo(name)<0)
				p =p.left;
			else 
				p =p.right;				
		}
		current = q;
		return false;
	}
	public boolean findExistence(String name,String PB) {
		BSTNode p = root, q = current;
		if (empty()||searchByPB(PB)!=null||findkey(name))//alredyExist(name, PB)
			return false;
		while(p != null) {
			p=q;
			if(p.key.equals(name)) {
				current = p;
				return true;
			}else if(p.key.compareTo(name)<0)
				p =p.left;
			else 
				p =p.right;				
		}
		current = q;
		return false;
	}
	
	public boolean insertSorted(Contact c) {
		BSTNode p, q = current;
		if(findExistence(c.getName(),c.getpNumber())) {
			current = q;
			return false;
		}
		p = new BSTNode(c.getName(),c);
		if(empty()) {
			root = current = p;
			return true;
		}else {
			if(current.key.compareTo(c.getName())<0)
				current.left = p;
			else
				current.right = p;
			
			current = p;
			return true;
		}	
	}
	
	public boolean remove(String name) {
		BSTNode p =root, q = null; // q is parent of p
		while (p != null) {
			if(p.key.compareTo(name)<0) {
				q = p;
				p = p.left;
			}else if(p.key.compareTo(name)>0) {
				q = p;
				p = p.right;
			}else {// Found the key & start checking for 3 cases
				if(p.right != null && p.left != null){// has 2 children
					BSTNode  min = p.right;
					while(min.left != null) {
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					min = null;
				}
		
				if (p.right == null) {//has no child or one child
					p = p.left;
					current = root = p;
					return true;
				}
				else {
					p = p.right; // so if he has no child it'll be null
					current = root = p;
					return true;
				}		
			}
		}
		return false;
	}		
	
	private BSTNode findMin(BSTNode node) {
		if(node == null)
			return null;
		while(node.left != null)
			node = node.left;
		return node;
	}
	
	public boolean update(String name,Contact c) {
		remove(name);
		return insertSorted(c);
	}
	
	public void traverse() {
		if(root == null) {
			System.out.println("There is no Contacts in Phonebook");
			return;
		}
		inOrder(root);
	}
	private void inOrder(BSTNode node) {
		if(node.left!=null)
			inOrder(node.left);
		System.out.print(node.data+", ");
		if(node.right!=null)
			inOrder(node.right);
	}
	
	public ContactBST searchByFname(String fname) {
		if(root == null)
			return null;
		fname = fname.toLowerCase();
		sameX = new ContactBST();
		SBF(root,fname);
		return sameX;
	}
	private void SBF(BSTNode node,String fname) {//SBF = search by first name
		if(node.left!=null)
			SBF(node.left,fname);
		if(node.data.getFirstname().equals(fname))
			sameX.insertSorted(node.data);
		if(node.right!=null)
			SBF(node.right,fname);
	}
	
	public ContactBST searchByEmail(String email) {
		if(root == null)
			return null;
		email = email.toLowerCase();
		sameX = new ContactBST();
		SBE(root,email);
		return sameX;
		
	}
	private void SBE(BSTNode node, String email) {
		if(node.left!=null)
			SBE(node.left,email);
		if(node.data.getFirstname().equals(email))
			sameX.insertSorted(node.data);
		if(node.right!=null)
			SBE(node.right,email);
	}
	
	public ContactBST searchByAddress(String address) {
		if(root == null)
			return null;
		address = address.toLowerCase();
		sameX = new ContactBST();
		SBA(root,address);
		return sameX;
	}
	private void SBA(BSTNode node,String address) {
		if(node.left!=null)
			SBA(node.left,address);
		if(node.data.getAddress().equals(address))
			sameX.insertSorted(node.data);
		if(node.right!=null)
			SBA(node.right,address);
	}
	
	public ContactBST searchByBD(String BD) {
		if(root == null)
			return null;
		sameX = new ContactBST();
		SBBD(root,BD);
		return sameX;
	}
	private void SBBD(BSTNode node,String BD) {
		if(node.left!=null)
			SBBD(node.left,BD);
		if(node.data.getBD().equals(BD))
			sameX.insertSorted(node.data);
		if(node.right!=null)
			SBBD(node.right,BD);
	}
	
//	public boolean alredyExist(String name, String PB) {
//		exist = false;
//		name = name.toLowerCase();
//		SNorSPB(root,name,PB);
//		return exist;
//	}
//	private void SNorSPB(BSTNode node,String name, String PB) { // method name -> same name or same phone number
//		if(node.left!=null)
//			SNorSPB(node.left, name, PB);
//		if(node.data.getName().equals(name)||node.data.getpNumber().equals(PB)) {
//			exist = true;
//			return;
//		}
//		if(node.right!=null)
//		SNorSPB(node.right, name, PB);
//	}
	
	public Contact searchByPB(String PB) {
		c = new Contact();
		SBP(root,PB);
		return c;
	}
	public void SBP(BSTNode node,String PB) {
		if(node.left!= null)
			SBP(node.left,PB);
		if(node.data.getpNumber().equals(PB)) {
			c = node.data;
			return;
		}
		if(node.right!= null)
			SBP(node.right,PB);
			
	}
	
//	private BSTNode remove_aux(String key, BSTNode p) {
//		BSTNode q, child = null;
//		if(p == null)
//			return null;
//		if(key.compareTo(p.key)<0)
//			p.left = remove_aux(key, p.left); //go left
//		else if(key.compareTo(p.key) > 0)
//			p.right = remove_aux(key, p.right); //go right
//		else {
//			if (p.left != null && p.right != null){ //two children
//				q = findMin(p.right);
//				p.key = q.key;
//				p.data = q.data;
//				p.right = remove_aux(q.key, p.right);
//			}else {
//				if (p.right == null) //one child
//					child = p.left;
//				else if (p.left == null) //one child
//					child = p.right;
//				return child;
//			}
//		}
//		return p;
//	}


	
	
	
	

}
	
