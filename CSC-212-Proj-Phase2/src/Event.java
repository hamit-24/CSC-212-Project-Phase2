import java.util.Scanner;
public class Event implements Comparable <Event>{
	
	private String title;
	private String location;
	private String date;
	private String time;
	private String contactName;
	private Contact conInEvent;
	private boolean isEvent;
	public LinkedList<Contact> contactInEvent = new LinkedList<Contact>();
	private String[] ContactsNames;
	
	public Event(boolean ev) { //app --> appointment
		title = location = date =time = contactName =null;
		conInEvent = null;
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

	public void addConInEvent() {
		
		
	}

	public void setConInEvent(Contact conInEvent) {
		this.conInEvent = conInEvent;
	}

	@Override
	public String toString() {
		System.out.println("Event title: "+ title);
		System.out.println("Contact name: "+ contactName);
		System.out.println("Event date and time(dd/mm/yyyy hh:mm): "+ date + " "+ time);
		System.out.println("Event location: "+ location);
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
			System.out.println("Enter Contacts' name ");
			this.contactName= input.nextLine();	
		}else {
			names = input.nextLine();
			names = names.toLowerCase();
			ContactsNames = names.split(",");
			while(ContactsNames.length<2) {
				System.out.println("Enter Contacts' name seperated by a comma(,) ");
				System.out.print("NOTE! please don't put a space after the comma: ");
				names = input.nextLine();
				names = names.toLowerCase();
				ContactsNames = names.split(",");
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
	public void addContactInApp(Contact c) {
		if(conInEvent == null) {
			setConInEvent(c);
			setContactName(c.getName());
		}
	}

	public String[] getContactsnames() {
		return ContactsNames;
	}

	public LinkedList<Contact> getContactInEvent() {
		return contactInEvent;
	}
	
}
	