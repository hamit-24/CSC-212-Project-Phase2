
public class ContactBST {
	private BSTNode  root,current;
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
		BSTNode p = root;
		if (empty())
			return false;
		while(p != null) {
			current =p;
			if(name.compareToIgnoreCase(p.key)==0) {
				return true;
			}else if(name.compareToIgnoreCase(p.key)<0)
				p =p.left;
			else 
				p =p.right;				
		}
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
		if(root == null) {
			root = current =  new BSTNode(c.getName(),c);
			return true;
		}
		BSTNode p = current;
		if(findkey(c.getName())) {
			current = p;
			return false;
		}
		BSTNode tmp = new BSTNode(c.getName(),c);
			if(c.getName().compareTo(current.key)<0)
				current.left = tmp;
			else
				current.right = tmp;
			
			current = tmp;
			return true;
	}
	
	public boolean remove(String name) {
		BSTNode p =root, q = null; // q is parent of p
		while (p != null) {
			if(name.compareToIgnoreCase(p.key)<0) {
				q = p;
				p = p.left;
			}else if(name.compareToIgnoreCase(p.key)>0) {
				q = p;
				p = p.right;
			}else {// Found the key & start checking for 3 cases
				
				if(p.right != null && p.left != null){// has 2 children
					BSTNode min = p.right;
					q = p;
					while(min.left != null) {
						q = min;
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					name = min.key;
					p = min;
				} 
				//changing subtree of p
				if (p.left != null) {//has no child or one child
					p = p.left;
				}
				else {
					p = p.right; // so if he has no child it'll be null
				}

				if(q == null)//P hasn't parent, so root must change
					root = p;
				else {
					if(name.compareToIgnoreCase(q.key)<0)
						q.left = p;
					else
						q.right = p;
				}
				current = root;
				return true;
			}
		}
		return false;//Not found
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
	
	public LinkedList<Contact> searchByFname(String fname) {
		if(root == null)
			return null;
		LinkedList<Contact> sameFName = new LinkedList<Contact>();
		fname = fname.toLowerCase();
		SBF(root,fname,sameFName);
		return sameFName;
	}
	private void SBF(BSTNode node,String fname,LinkedList<Contact> resultLL) {//SBF = search by first name
		if(node==null)
			return;
		SBF(node.left,fname,resultLL);
		if(node.data.getFirstname().equals(fname))
			resultLL.addSorted(node.data);
		SBF(node.right,fname,resultLL);
	}
	
	public LinkedList<Contact> searchByEmail(String email) {
		if(root == null)
			return null;
		email = email.toLowerCase();
		LinkedList<Contact> sameEmail = new LinkedList<Contact>();
		SBE(root,email,sameEmail);
		return sameEmail;
		
	}
	private void SBE(BSTNode node, String email,LinkedList<Contact> resultLL) {//SBE -> search by email
		if(node == null)
			return;
		SBE(node.left,email,resultLL);
		if(node.data.getFirstname().equals(email))
			resultLL.addSorted(node.data);
		SBE(node.right,email,resultLL);
	}
	
	public LinkedList<Contact> searchByAddress(String address) {
		if(root == null)
			return null;
		address = address.toLowerCase();
		LinkedList<Contact> sameAddress = new LinkedList<Contact>();
		SBA(root,address,sameAddress);
		return sameAddress;
	}
	private void SBA(BSTNode node,String address,LinkedList<Contact> resultLL) {//SBA-> search by address
		if(node == null)
			return;
		SBA(node.left,address,resultLL);
		if(node.data.getAddress().equals(address))
			resultLL.addSorted(node.data);
		SBA(node.right,address,resultLL);
	}
	
	public LinkedList<Contact> searchByBD(String BD) {
		if(root == null)
			return null;
		LinkedList<Contact> sameBD = new LinkedList<Contact>();
		SBBD(root,BD,sameBD);
		return sameBD;
	}
	private void SBBD(BSTNode node,String BD,LinkedList<Contact> resultLL) {//SBBD-> search by birthday
		if(node == null)
			return;
		SBBD(node.left,BD,resultLL);
		if(node.data.getBD().equals(BD))
			resultLL.addSorted(node.data);
		SBBD(node.right,BD,resultLL);
	}	
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
	public boolean findPhone(String PB) {
		if(root == null)
			return false;
		else
			return FP(root,PB);
	}
	private boolean FP(BSTNode node,String PB) {
		if(node == null)
			return false;
		boolean foundLeft = FP(node.left,PB);
		if(foundLeft)
			return true;
		if(node.data.getpNumber().equals(PB))
			return true;
		
		return FP(node.right,PB);
	}
}
	
