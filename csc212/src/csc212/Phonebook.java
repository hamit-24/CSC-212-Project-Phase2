package csc212;

import java.util.Scanner;

public class Phonebook {
	private ContactBST contacts;
	private LinkedList<Event> events;

	public Phonebook() {
		contacts = new ContactBST();
		events = new LinkedList<Event>();
	}

	public boolean searchContact(Contact c) {

		if (contacts.empty())
			return false;
		return contacts.findkey(c.getName());

	}

	public void addContact() {
		Contact c = new Contact();
		c.readContact();
		if (!contacts.findkey(c.getName())) {
			contacts.insertSorted(c);
			System.out.println("-Contact added successfully");
			System.out.println("--------------------");
			return;
		} else {
			System.out.println("-There is a contact with tha same name or phone number");
			System.out.println("--------------------");
		}
	}

	public void deleteContact(String name) {

		if (contacts.empty()) {
			System.out.println("-The linked list is empty! you can't delete");
			System.out.println("--------------------");
			return;
		}
		if (contacts.remove(name)) {
			deleteInEvent(name);
			System.out.println("-Contact " + name + " deleted");
			System.out.println("--------------------");

		} else {
			System.out.println("-Contact doesn't exist, you can't delete");
			System.out.println("--------------------");
		}
	}

	public void deleteInEvent(String name) {

		if (events.isEmpty())
			return;
		events.findFirst();
		while (!events.last()) {
			if (events.retrive().getIsEvent()) {// Event
				if (events.retrive().getContactsInEvent().indexOf(name) != -1) {
					events.retrive().deleteContact(name);
					if (events.retrive().getContactsInEvent() == null)
						events.remove();
				}
				events.findNext();
			} else {// appointment
				if (events.retrive().getContactName().equalsIgnoreCase(name)) {
					events.remove();
				}
				events.findNext();
			}
		} // for the last
		if (events.retrive().getIsEvent()) {// Event
			if (events.retrive().getContactsInEvent().indexOf(name) != -1) {
				events.retrive().deleteContact(name);
				if (events.retrive().getContactsInEvent() == null)
					events.remove();
			}
		} else {// appointment
			if (events.retrive().getContactName().equalsIgnoreCase(name))
				events.remove();
		}
	}

	public Contact searchByName(String name) {
		if (contacts.empty())
			return null;
		if (contacts.findkey(name))
			return contacts.retrieve();
		return null;
	}

	public Contact searchByPhone(String phone) {
		if (contacts.empty())
			return null;
		return contacts.searchByPB(phone);
	}

	public LinkedList<Contact> searchByFirstName(String fname) {
		if (contacts.empty())
			return null;
		LinkedList<Contact> samefname = contacts.searchByFname(fname);
		;
		return samefname;
	}

	public LinkedList<Contact> searchByEmail(String e) { // e--->email
		if (contacts.empty())
			return null;
		LinkedList<Contact> sameEmail = contacts.searchByEmail(e);
		return sameEmail;
	}

	public LinkedList<Contact> searchByAddress(String ad) { // ad--->address
		if (contacts.empty())
			return null;
		LinkedList<Contact> sameAddress = contacts.searchByAddress(ad);
		return sameAddress;
	}

	public LinkedList<Contact> searchByBirthday(String bd) { // bd--->birthday
		if (contacts.empty())
			return null;
		LinkedList<Contact> sameBD = contacts.searchByBD(bd);
		return sameBD;
	}

	// searches for event by title and adds it to a linked list.
	public LinkedList<Event> searchEventByTitle(String ti) { // ti--->title.
		LinkedList<Event> sameTitle = new LinkedList<Event>();

		if (events.isEmpty())
			return null;

		events.findFirst();
		while (!events.last()) {
			if (events.retrive().getTitle().equalsIgnoreCase(ti))
				sameTitle.addSorted(events.retrive());

			events.findNext();
		}
		if (events.retrive().getTitle().equalsIgnoreCase(ti))
			sameTitle.addSorted(events.retrive());

		return sameTitle;
	}

	// this method adds event to contact.
	public void addEvent(boolean ev) {
		Event e = new Event(ev);
		e.readEvent();
		if (isConflict(e)) {
			System.out.println("-There is already an Event/Appointment with this date & time");
			System.out.println("--------------------");
			return;
		}
		if (!ev) {
			boolean found = contacts.findkey(e.getContactName());
			if (!found) {
				System.out.println("-There is no contact with this name");
				System.out.println("--------------------");
			} else {
				contacts.retrieve().addEvent(e);
				events.addSorted(e);
				System.out.println("-Event added successfully");
				System.out.println("--------------------");
			}
		} else {
			String addName = null;
			String[] names = e.getContactsnames();
			for (int i = 0; i < names.length; i++) {
				boolean found = contacts.findkey(names[i]);
				if (!found) {
					System.out.println("-There is no contact with this name");
					System.out.println("--------------------");
					return;
				}
			}
			for (int i = 0; i < names.length; i++) {

				if (addName == null) {
					addName = contacts.retrieve().getName();
					contacts.retrieve().addEvent(e);
				} else {
					addName = addName + "," + contacts.retrieve().getName();
					contacts.retrieve().addEvent(e);
				}
				e.setContactsInEvent(addName);
				events.addSorted(e);
				System.out.println("-Event schedule with: " + e.getContactsInEvent());
				System.out.println("--------------------");

			}
			if (addName == null) {// there is no name is in the BST
				System.out.println("- All Contacts' names you entred are not exist");
				System.out.println("--------------------");
				return;
			}

		}
	}

	// this method checked if there is an event with same time and date
	public boolean isConflict(Event e) {
		if (events.isEmpty())
			return false;
		events.findFirst();
		while (!events.last()) {
			if (events.retrive().getDate().compareToIgnoreCase(e.getDate()) == 0
					&& events.retrive().getTime().compareTo(e.getTime()) == 0) {
				System.out.println("There is an event with same date & time!");
				System.out.println("--------------------");
				return true;
			}
			events.findNext();
		}
		if (events.retrive().getDate().compareToIgnoreCase(e.getDate()) == 0
				&& events.retrive().getTime().compareTo(e.getTime()) == 0) {
			System.out.println("There is an event with same date & time!");
			System.out.println("--------------------");
			return true;
		}
		return false;
	}

	public void menu() {
		Scanner input = new Scanner(System.in);

		int choice = 0;
		int crit = 0;

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

			case 1: // add Contact

				addContact();
				break;

			case 2: // search

				crit = 0;
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
					if (crit < 1 || crit > 5) {
						System.out.println("-Please choose 1-5");
						System.out.println("--------------------");
					}
				} while (crit < 1 || crit > 5);

				if (crit == 1) {
					System.out.print("Enter the contact's name: ");
					input.nextLine();// garbage
					String name = input.nextLine();
					System.out.println("--------------------");
					Contact c = searchByName(name);
					if (c == null) {
						System.out.println("-Contact not found");
						System.out.println("--------------------");
					} else
						c.display();
				} else if (crit == 2) {
					System.out.print("Enter the contact's phone number: ");
					Contact c = searchByPhone(input.next());
					System.out.println("--------------------");
					if (c == null) {
						System.out.println("-Contact not found");
						System.out.println("--------------------");
					} else
						c.display();
				} else if (crit == 3) {
					System.out.print("Enter the contact's email address: ");
					input.nextLine();// garbage
					LinkedList<Contact> L = searchByEmail(input.nextLine());
					System.out.println("--------------------");
					if (L == null || L.isEmpty()) {
						System.out.println("-Contact not found");
						System.out.println("--------------------");
					} else
						L.display();
				} else if (crit == 4) {
					System.out.print("Enter the contact's address: ");
					input.nextLine();// garbage
					LinkedList<Contact> L = searchByAddress(input.nextLine());
					System.out.println("--------------------");
					if (L == null || L.isEmpty()) {
						System.out.println("-Contact not found");
						System.out.println("--------------------");
					} else
						L.display();
				} else if (crit == 5) {
					int day = 40, month = 40, year = 0;
					System.out.println("Enter the contact's birthday(dd/mm/yyyy): ");
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
					String s = day + "/" + month + "/" + year;
					input.nextLine();// garbage
					LinkedList<Contact> L = searchByBirthday(s);
					System.out.println("--------------------");
					if (L == null || L.isEmpty()) {
						System.out.println("-Contact not found");
						System.out.println("--------------------");
					} else
						L.display();
				}

				break;

			case 3: // delete Contact
			{
				System.out.print("Enter the contact's name: ");
				input.nextLine();// garbage
				String name = input.nextLine();
				deleteContact(name);

				break;
			}
			case 4: // event scheduling
				crit = 0;
				do {
					System.out.println("Enter type number:");
					System.out.println("1- Event");
					System.out.println("2- Appointment");
					System.out.print("Enter your choice: ");
					try {
						crit = input.nextInt();
						System.out.println("--------------------");

					} catch (Exception e) {
						System.out.println("-Invalid input. Please enter a valid option.");
						System.out.println("Please enter 1 or 2");
						System.out.println("--------------------");

						input.nextLine(); // Consume the invalid input
						continue;
					}
					if (crit != 1 && crit != 2) {
						System.out.println("-Invalid input. Please enter a valid option.");
						System.out.println("Please enter 1 or 2");
						System.out.println("--------------------");
					}

				} while (crit != 1 && crit != 2);
				if (crit == 1)
					addEvent(true);
				else
					addEvent(false);
				break;

			case 5: // event details printing

				crit = 0;
				do {

					System.out.println("Enter search  criteria:");
					System.out.println("1- Contact name");
					System.out.println("2- Event title");
					System.out.print("Enter your choice: ");
					try {
						crit = input.nextInt();
						System.out.println("--------------------");

					} catch (Exception e) {
						System.out.println("-Invalid input. Please enter a valid option.");
						System.out.println("Please enter 1 or 2");
						System.out.println("--------------------");

						input.nextLine(); // Consume the invalid input
						continue;
					}
					if (crit != 1 && crit != 2) {
						System.out.println("-Invalid input. Please enter a valid option.");
						System.out.println("Please enter 1 or 2");
						System.out.println("--------------------");
					}

				} while (crit != 1 && crit != 2);

				if (crit == 1) {// Events share same contact
					System.out.print("Enter the contact name: ");
					input.nextLine();// garbage
					String name = input.nextLine();
					System.out.println("--------------------");
					if (contacts.findkey(name)) {
						contacts.retrieve().contactEvents.display();
						System.out.println("--------------------");
					} else {
						System.out.println("-There is no contact with this name.");
						System.out.println("--------------------");
					}
				} else {// Events share same title
					System.out.print("Enter the event title: ");
					input.nextLine();// garbage
					LinkedList<Event> L = searchEventByTitle(input.nextLine());
					System.out.println("--------------------");
					if (L == null) {
						System.out.println("-There is no event with this title.");
						System.out.println("--------------------");
					} else
						L.display();
				}
				break;

			case 6: // printing contacts by first name
				System.out.print("Enter the first name: ");
				input.nextLine();// garbage
				String fName = input.nextLine();
				LinkedList<Contact> L = searchByFirstName(fName);
				System.out.println("--------------------");

				if (L == null || L.isEmpty()) {
					System.out.println("-Contact not found");
					System.out.println("--------------------");
					break;
				} else
					L.display();

				break;

			case 7: // printing events alphabetically
				if (events.isEmpty()) {
					System.out.println("-There are no events");
					System.out.println("--------------------");
				} else
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
}
