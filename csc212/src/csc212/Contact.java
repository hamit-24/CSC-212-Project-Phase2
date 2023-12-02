package csc212;

import java.util.*;

public class Contact implements Comparable<Contact> {

	private String Firstname;
	private String name;
	private String pNumber;
	private String address;
	private String email;
	private String BD;
	private String note;
	public LinkedList<Event> contactEvents;


	public LinkedList getContactEvents() {
		return contactEvents;
	}

	public void setContactEvents(LinkedList contactEvents) {
		this.contactEvents = contactEvents;
	}

	public Contact() {
		Firstname = name = pNumber = address = email = BD = note = null;
		contactEvents = new LinkedList<Event>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contact(Contact con) {
		this.Firstname = con.Firstname;
		this.pNumber = con.pNumber;
		this.address = con.address;
		this.email = con.email;
		BD = con.BD;
		this.note = con.note;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String contactFirstname) {
		this.Firstname = contactFirstname;
	}

	public String getpNumber() {
		return pNumber;
	}

	public void setpNumber(String pNumber) {
		this.pNumber = pNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBD() {
		return BD;
	}

	public void setBD(String bD) {
		BD = bD;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int compareTo(String n) {
		return name.compareTo(n);
	}

	public int compareTo(Contact o) {
		return name.compareTo(o.name);
	}

	public void readContact() {
		int day = 40, month = 40, year = 0;
		;// just for check that the date is correct
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the contact's name: ");
		this.name = input.nextLine();
		this.name = name.toLowerCase();
		String[] parts = name.split(" "); // Split the full name using a space as the delimiter.
		if (parts.length >= 2) // Check if there are at least two parts (first name and last name)
			Firstname = parts[0]; // The first name is the first part (index 0)
		else
			Firstname = name;

		System.out.print("Enter the contact's phone number: ");
		this.pNumber = input.nextLine();
		boolean isDigit = pNumber.matches("\\d+");// to check if number is all digit
		boolean length10 = pNumber.length() == 10;// to check if number is 10 digits
		while (!isDigit || !length10) {
			System.out.print("Enter correct phone number(10 digits without letter): ");
			this.pNumber = input.nextLine();
			isDigit = pNumber.matches("\\d+");
			length10 = pNumber.length() == 10;
		}
		System.out.print("Enter the contact's email address: ");
		this.email = input.nextLine();
		System.out.print("Enter the contact's address: ");
		this.address = input.nextLine();
		System.out.println("Enter the contact's birthday: ");
		do {
			try {
				System.out.print("Enter the day:");
				day = input.nextInt();
				System.out.print("Enter the month:");
				month = input.nextInt();
				System.out.print("Enter the year:");
				year = input.nextInt();
			} catch (Exception e) {
				System.out.println("-Invalid input. Please enter a valid day/month/year.");
				System.out.println("--------------------");
				input.nextLine();
				continue;
			}
			if (day > 31 || month > 12)
				System.out.println("-Please enter day<32 or month<13");
		} while (day > 31 || month > 12);
		BD = day + "/" + month + "/" + year;
		input.nextLine();// garbage

		System.out.print("Enter any notes for the contact: ");
		this.note = input.nextLine();
		System.out.println();

	}

	@Override
	public String toString() {
		System.out.println("name: " + name);
		System.out.println("Phone Number: " + pNumber);
		System.out.println("Email Address: " + email);
		System.out.println("Address: " + address);
		System.out.println("Birthday: " + BD);
		System.out.println("Notes: " + note);

		return "--------------------";

	}

	public void display() {
		System.out.println("name:" + name);
		System.out.println("Phone Number:" + pNumber);
		System.out.println("Email Address:" + email);
		System.out.println("Address:" + address);
		System.out.println("Birthday:" + BD);
		System.out.println("Notes:" + note);
		System.out.println("--------------------");

	}

	public void addEvent(Event e) {
		contactEvents.addSorted(e);
	}
}
