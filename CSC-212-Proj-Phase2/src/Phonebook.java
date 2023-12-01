import java.util.*;
public class Phonebook {
	private ContactBST contacts;
    private LinkedList<Event> events;
    
    
	public Phonebook() {
		contacts = new ContactBST();
		events = new LinkedList<Event>();
	}
	
	public boolean searchContact(Contact c) {
		
		if(contacts.empty())
			return false;
		return contacts.findkey(c.getName());
		
		
	}
	
	public void addContact() {
		Contact c = new Contact();
		c.readContact();
		if(!contacts.findkey(c.getName())) {
			contacts.insertSorted(c);
			System.out.println("-Contact added successfully");
			System.out.println("--------------------");
			return;	
		}
		else {
		System.out.println("-There is a contact with tha same name or phone number");
		System.out.println("--------------------");
		   }
		}
	
	public void deleteContact(String name) {
		
		if(contacts.empty()) {
			System.out.println("-The linked list is empty! you can't delete");				
			System.out.println("--------------------");
			return;
		}
			if(contacts.remove(name)) {
				deleteEvent(name);
				System.out.println("-Contact "+name+" deleted");
				System.out.println("--------------------");
			
		}
		else {
			System.out.println("-Contact doesn't exist, you can't delete");
			System.out.println("--------------------");
		}
	}
	
	public void deleteEvent(String name) {
		
		if(events.isEmpty())
			return;
		events.findFirst();
		while(!events.last()) {
			if(events.retrive().getContactName().equalsIgnoreCase(name)) {
				if(!events.retrive().getIsEvent()||events.retrive().getContactInEvent().isEmpty()) {
					events.remove();
					return;	
				}
			}
			events.findNext();
		}
		if(!events.retrive().getIsEvent()||events.retrive().getContactInEvent().isEmpty()) {
			events.remove();
			return;	
		}
		
	}
	
	public Contact searchByName(String name) {
		if(contacts.empty())
			return null;
		if(contacts.findkey(name))
			return contacts.retrieve();
		return null;
	}
	
	public Contact searchByPhone(String phone) {
		if(contacts.empty())
			return null;
		return contacts.searchByPB(phone);
	}
	
	public ContactBST searchByFirstName(String fname){
		if(contacts.empty())
			return null;
		ContactBST samefname = contacts.searchByFname(fname);;
		return samefname; 		
	}
	
	public ContactBST searchByEmail(String e){		//e--->email
		if(contacts.empty()) 
			return null;
		ContactBST sameEmail =contacts.searchByEmail(e);
		return sameEmail;
	}
	
	public ContactBST searchByAddress(String ad){		//ad--->address
		if(contacts.empty())
			return null;
		ContactBST sameAddress = contacts.searchByAddress(ad);
		return sameAddress;	
	}
	
	public ContactBST searchByBirthday(String bd){		//bd--->birthday
		if(contacts.empty())
			return null;
		ContactBST sameBD = contacts.searchByBD(bd);  
		return sameBD;
	}
		
	//searches for event by title and adds it to a linked list.
	public LinkedList<Event> searchEventByTitle(String ti) {  //ti--->title.
		LinkedList<Event> t= new LinkedList<Event>();
		
		if(events.isEmpty())
			return null;
		
		events.findFirst();
		while(!events.last()) {
			if(events.retrive().getTitle().equalsIgnoreCase(ti))
				t.addSorted(events.retrive());
				
			events.findNext();
		}
		if(events.retrive().getTitle().equalsIgnoreCase(ti))
			t.addSorted(events.retrive());
		
		return t;
	}	

	// this method adds event to contact.
	public void addEvent(boolean ev) {
		Event e = new Event(ev);
	    e.readEvent();	
	    if(!ev) {
	    	Contact c = searchByName(e.getContactName());
	    	if(c==null) {
	    		System.out.println("-There is no contact with this name");
				System.out.println("--------------------");
	    	}else if(!isConflict(e)) {
	    		e.setConInEvent(c);	// sets Event's contact. 
	    		contacts.retrieve().contactEvents.addSorted(e);	    		
	    		events.addSorted(e);
	    	}
	    }else {
	    	String [] names = e.getContactsnames();
	    	for(int i = 0; i <= names.length;i++) {
	    		Contact c =  searchByName(names[i]);
	    		if(c==null) {
	    			continue;
	    		}else if(!isConflict(e)) {
	    			e.contactInEvent.addSorted(c);
	    			c.contactEvents.addSorted(e);
	    		}
	    	}
	    	if(!e.getContactInEvent().isEmpty()) {
	    		events.addSorted(e);
	    		System.out.println("-Event schedule with: ");
	    	}
	    	else {
	    		System.out.println("- All Contacts' names you entred are not exist");
	    		System.out.println("--------------------");
	    	}
	    	
	    }
		
			
	}
	

	// this method checked if there is an event with same time and date 
	public boolean isConflict(Event e) {
			if(events.isEmpty())
				return false;
			events.findFirst();
			while(!events.last()) {
				if(events.retrive().getDate().compareToIgnoreCase(e.getDate())==0
						&&events.retrive().getTime().compareTo(e.getTime())==0) {
					System.out.println("There is an event with same date & time!");
					System.out.println("--------------------");	
					return true;
				}
				events.findNext();
			}
			if(events.retrive().getDate().compareToIgnoreCase(e.getDate())==0
					&&events.retrive().getTime().compareTo(e.getTime())==0) {
				System.out.println("There is an event with same date & time!");
				System.out.println("--------------------");	
				return true;
			}
			return false;
	}	
	public void menu() {
		Scanner input = new Scanner(System.in);
        
        int choice=0;
        int crit=0;
        
        System.out.println("Welcome to the linked tree phonebook");

        do {
           
            System.out.println("Please choose an option: ");
            System.out.println("1- Add contact");
            System.out.println("2- Search for a contact");
            System.out.println("3- Delete a contact");
            System.out.println("4- Schedule an event");
            System.out.println("5- Print event details");
            System.out.println("6- Print Contacts by first name");
            System.out.println("7- Print all events alphabetically");
            System.out.println("8- Exit");
            System.out.print("Enter your choice: ");
            try {
            	choice = input.nextInt();
            	System.out.println("--------------------");
            } catch (Exception e) {
                System.out.println("-Invalid input. Please enter a valid option.");
                System.out.println("--------------------");
                input.nextLine(); // Consume the invalid input
                continue;
             }

            switch (choice) {
            
                case 1:   //add Contact
                	
                    addContact();                    
                    break;
                    
                case 2:  // search 
                	
                	crit=0;                	
                	do {
                		System.out.println("Choose a criteria:");
                		System.out.println("1- Name");
                    	System.out.println("2- Phone number");
                    	System.out.println("3- Email Address");
                    	System.out.println("4- Address");
                    	System.out.println("5- Birthday");
                    	System.out.print("Enter your choice: ");
                	 try {
                		 crit = input.nextInt();
                		 
                     } catch (Exception e) {
                         System.out.println("-Invalid input. Please enter a valid option.");
                         System.out.println("--------------------");
                         input.nextLine(); // Consume the invalid input
                         continue;
                     }
                	 if(crit<1||crit>5) {
                		 System.out.println("-Please choose 1-5");
                	     System.out.println("--------------------");
                	 }
                	}while(crit<1||crit>5);
                	
                	if(crit==1) {
                		System.out.print("Enter the contact's name: ");
                		input.nextLine();//garbage
                		String name = input.nextLine();
                		System.out.println("--------------------");
                		Contact c = searchByName(name);
                		if(c==null) {
                			System.out.println("-Contact not found");
                		System.out.println("--------------------");
                		}
                		else
                		    c.display();
                	}
                	else if(crit == 2) {
                		System.out.print("Enter the contact's phone number: ");
                		Contact c = searchByPhone(input.next());
                		System.out.println("--------------------");
                		if(c==null) {
                			System.out.println("-Contact not found");
                			System.out.println("--------------------");
                		}
                		else
                		    c.display();
                	}
                	else if(crit ==3) {
                		System.out.print("Enter the contact's email address: "); 
                		input.nextLine();//garbage
                		ContactBST L = searchByEmail(input.nextLine());
                		System.out.println("--------------------");
                		if(L==null||L.empty()) {
                			System.out.println("-Contact not found");
                			System.out.println("--------------------");
                		}
                		else
                		    L.traverse();
                	}
                	else if(crit ==4) {
                		System.out.print("Enter the contact's address: ");
                		input.nextLine();//garbage
                		ContactBST L = searchByAddress(input.nextLine());
                		System.out.println("--------------------");
                		if(L==null||L.empty()) {
                			System.out.println("-Contact not found");
                			System.out.println("--------------------");
                		}
                		else
                		    L.traverse();
                	}
                	else if(crit ==5) {
                		System.out.print("Enter the contact's birthday(dd/mm/yyyy): ");
                		ContactBST L = searchByBirthday(input.next());
                		System.out.println("--------------------");
                		if(L==null||L.empty()) {
                			System.out.println("-Contact not found");
                			System.out.println("--------------------");
                		}else
                		    L.traverse();
                	}
                	
                    
                    break;
                    
                case 3:   //  delete Contact
                	
                	System.out.print("Enter the contact's name: ");
                	input.nextLine();//garbage
                	String name = input.nextLine();
                	deleteContact(name);
                    
                    break;
                    
                case 4:   //  event scheduling
                	crit = 0;
                	do {	
                		System.out.println("Enter type number:");
                  		System.out.println("1- Event");
                  		System.out.println("2- Appointment");
                  		System.out.print("Enter your choice: ");
                		try {
                			crit = input.nextInt();
                			System.out.println("--------------------");
                			
                		}catch(Exception e) {
                			System.out.println("-Invalid input. Please enter a valid option.");
                			System.out.println("Please enter 1 or 2");
                			System.out.println("--------------------");
                			
                			input.nextLine(); // Consume the invalid input
                			continue;	
                		}
                		if(crit!= 1 && crit!= 2) {
                		System.out.println("-Invalid input. Please enter a valid option.");
            			System.out.println("Please enter 1 or 2");
            			System.out.println("--------------------");
            			}
                		
                	}while(crit!= 1 && crit!= 2);
              		if(crit == 1)
              			addEvent(false);
              		else
              			addEvent(true);
                    break;
                    
                case 5:   // event details printing
                	
                	crit=0;
                	do {
                		
                	    System.out.println("Enter search  criteria:");
                		System.out.println("1- Contact name");
                		System.out.println("2- Event title");
                		System.out.print("Enter your choice: ");
                		try {
                			crit = input.nextInt();
                			System.out.println("--------------------");
                			
                		}catch(Exception e) {
                			System.out.println("-Invalid input. Please enter a valid option.");
                			System.out.println("Please enter 1 or 2");
                			System.out.println("--------------------");
                			
                			input.nextLine(); // Consume the invalid input
                			continue;	
                		}
                		if(crit!= 1 && crit!= 2) {
                		System.out.println("-Invalid input. Please enter a valid option.");
            			System.out.println("Please enter 1 or 2");
            			System.out.println("--------------------");
            			}
                		
                	}while(crit!= 1 && crit!= 2);
                	
                	 if(crit == 1) {
                		System.out.print("Enter the contact name: ");
                		input.nextLine();//garbage
                		Contact c = searchByName(input.nextLine());
                		System.out.println("--------------------");
                		
                		if(c == null) {
                			System.out.println("-There is no contact with this name.");
                			System.out.println("--------------------");
                			break;
                		}
                		
                		if(!c.hasEvent()) {
                			System.out.println("-There is no events with this contact");
                			System.out.println("--------------------");
                			break;
                		}
                		else {
                			c.getEventForContact().toString();
                			System.out.println("--------------------");
                		}	
                	}
                	 else{
                		System.out.print("Enter the event title: ");
                		input.nextLine();//garbage
                		LinkedList L = searchEventByTitle(input.nextLine());
                		System.out.println("--------------------");
                		if(L==null) {
                			System.out.println("-There is no event with this title.");
                			System.out.println("--------------------");
                		}
                		else {
                			L.findFirst();
                			while(!L.last()) {
                			L.retrive().toString();
                			System.out.println("--------------------");
                			L.findNext();
                		   }
                			L.retrive().toString();
                			System.out.println("--------------------");
                	   }		
                	}
                	break;
                	
                    
                case 6:   //  printing contacts by first name
                	System.out.print("Enter the first name: ");
                    input.nextLine();//garbage
                	String fName = input.nextLine();
                	ContactBST L = searchByFirstName(fName);
                	System.out.println("--------------------");
                	
                	if(L==null||L.empty()) {
                		System.out.println("-Contact not found");
                		System.out.println("--------------------");
                		break;
 //وقفت هنا
                    }
                	else
            		    L.traverse();
                    
                    break;
                    
                case 7:  //  printing events alphabetically
                	if(events.isEmpty()) {
                		System.out.println("-There are no events");
                		System.out.println("--------------------");
                	}
                	else
                		events.display();                   
                    break;
                    
                case 8:
                    System.out.println("Exiting the phonebook.");
                    break;
                    
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        } while (choice != 8);
	}
	// this method checks if there is an event with same time and date
//	public boolean isConflictapp(Event e, Contact c) {
//		if(c == null) {
//			System.out.println("-there is no contact with this name");
//			System.out.println("--------------------");
//			return true;
//		}
//		if(c.hasEvent()) {
//			System.out.println("-"+c.getName()+" already has an event/appointment");
//			System.out.println("--------------------");
//			return true;
//		}
//		else {
//			if(events.isEmpty())
//				return false;
//			
//			events.findFirst();
//			while(!events.last()) {
//				if(events.retrive().getDate().compareTo(e.getDate())==0
//						&&events.retrive().getTime().compareTo(e.getTime())==0) {
//					System.out.println("-There is an event with same date & time!");
//					System.out.println("--------------------");	
//					return true;
//				}
//				events.findNext();
//			}
//
//			if(events.retrive().getDate().compareTo(e.getDate())==0
//					&&events.retrive().getTime().compareTo(e.getTime())==0) {
//				System.out.println("-There is an event with same date & time!");
//				System.out.println("--------------------");	
//				return true;
//			}
//				 
//			return false;
//		}
//		
//	}
//	public boolean isConflictEve(Event e, Contact c) {
//		if(c == null) {
//			return true;
//		}
//		if(c.hasEvent()) {
//			System.out.println("-"+c.getName()+" already has an event/appointment");
//			System.out.println("--------------------");
//			return true;
//		}
//		else {
//			if(events.isEmpty())
//				return false;
//			
//			events.findFirst();
//			while(!events.last()) {
//				if(events.retrive().getDate().compareTo(e.getDate())==0
//						&&events.retrive().getTime().compareTo(e.getTime())==0) 	
//					return true;
//				
//				events.findNext();
//			}
//
//			if(events.retrive().getDate().compareTo(e.getDate())==0
//					&&events.retrive().getTime().compareTo(e.getTime())==0) 	
//				return true;
//			
//				 
//			return false;
//		}
//	}
	
}
