import java.util.Scanner;
public class Event implements Comparable <Event>{
	
	private String title;
	private String location;
	private String date;
	private String time;
	private String contactName;//for appointment
	private boolean isEvent;
	private String contactsInEvent;
	private String[] ContactsNames;
	
	public Event(boolean ev) { //ev --> Event
		title = location = date = time = contactName = contactsInEvent = null;
		isEvent = ev;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	@Override
	public String toString() {
		if(!isEvent) {
			System.out.println("Event title: "+ title);
			System.out.println("Contact name: "+ contactName);
			System.out.println("Event date and time(dd/mm/yyyy hh:mm): "+ date + " "+ time);
			System.out.println("Event location: "+ location);
			System.out.println("--------------------");
		}else {
			System.out.println("Event title: "+ title);
			System.out.println("Contacts names: "+ contactsInEvent);
			System.out.println("Event date and time(dd/mm/yyyy hh:mm): "+ date + " "+ time);
			System.out.println("Event location: "+ location);
			System.out.println("--------------------");
		}
		return "--------------------";
	}
	
	//reads event info from user.
	public void readEvent() {
		int day=40,month=40,year=0,hour=25,minute=61;
		String names = null;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter event title: ");
		this.title = input.nextLine();
		this.title=title.toLowerCase();
		if(!isEvent) {
			System.out.println("Enter Contact name ");
			this.contactName= input.nextLine();	
		}else {
			System.out.println("Enter Contacts' name seperated by a comma(,) ");
			System.out.print("NOTE! please don't put a space after the comma: ");
			names = input.nextLine();
			names = names.toLowerCase();
			ContactsNames = names.split(",");
			contactsInEvent=names;
			while(ContactsNames.length<2) {
				System.out.println("Enter Contacts' name seperated by a comma(,) ");
				System.out.print("NOTE! please don't put a space after the comma: ");
				names = input.nextLine();
				names = names.toLowerCase();
				ContactsNames = names.split(",");
				contactsInEvent=names;
			}
				
		}
		System.out.println("Enter the event's date: ");
		do {
			try {
				System.out.print("Enter the day: ");
				day=input.nextInt();
				System.out.print("Enter the month: ");
				month=input.nextInt();
				System.out.print("Enter the year: ");
				year=input.nextInt();
				System.out.print("Enter the hour: ");
				hour=input.nextInt();
				System.out.print("Enter the minute: ");
				minute=input.nextInt();
			}catch (Exception e) {
				System.out.println("-Invalid input. Please enter a valid minute/hour/day/month/year.");
				System.out.println("--------------------");
				input.nextLine();
				continue;
			}
			if(day>31||month>12||hour>24||minute>60)
				System.out.println("-Please enter day<32,month<13,hour<24,minute<60 ");
		}while(day>31||month>12||hour>24||minute>60);
		date = day+"/"+month+"/"+year;
		time= hour+":"+minute;
		System.out.print("Enter event location: ");
		input.nextLine();//garbage
		this.location = input.nextLine();
		}

	public void setContactsInEvent(String contactsInEvent) {
		this.contactsInEvent = contactsInEvent;
	}

	public boolean getIsEvent() {
		return isEvent;
	}

	@Override
	public int compareTo(Event o) {
		return this.date.compareTo(o.date);
	}
	public int compareTo(String time) {
		return this.time.compareTo(time);
	}

	public String[] getContactsnames() {
		return ContactsNames;
	}	
	public void deleteContact(String name) {
		contactsInEvent = null;
		for(int i =0;i<ContactsNames.length;i++) {
			if(ContactsNames[i].equalsIgnoreCase(name)) 
				continue;
			if(contactsInEvent==null)
				contactsInEvent = ContactsNames[i];
			else
				contactsInEvent = contactsInEvent+","+ContactsNames[i];
		}
		if(contactsInEvent != null)
			ContactsNames= contactsInEvent.split(",");
		
	}

	public String getContactsInEvent() {
	return contactsInEvent;
	}


	
}
	