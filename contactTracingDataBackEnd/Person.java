// Definition of a Person Object which allows for the creation and modification
package contactTracingDataBackEnd;

import java.util.ArrayList;

public class Person //implements Comparable<Person> 
{
	private String id;
	private String firstName;
	private String lastName;
	private String status;
	private String role;
	private String number;
	private String email;
	private ArrayList<String> contacts;
	
	// Default Contructor
	public Person()
	{
		id = "00000";
		firstName = "unknown";
		lastName = "unknown";
		status = "unknown";
		role = "unknown";
		number = "unknown";
		email = "unknown";
		contacts = new ArrayList<String>();
	}
	
	// Constructor
	public Person (String i, String fn, String ln, String s, String r, String n, String e) 
	{
		id = i;
		firstName = fn;
		lastName = ln;
		status = s;
		role = r;
		number = n;
		email = e;
		contacts = new ArrayList<String>();
	}
	
	// Id getter
	public String getId() 
	{
		return id;
	}
	
	// Id setter
	public void setId(String i) 
	{
		id = i;
	}
	
	// First name getter
	public String getFirstName() 
	{
		return firstName;
	}
	
	// First name setter
	public void setFirstName(String fn) 
	{
		firstName = fn;
	}
	
	// Last name getter
	public String getLastName() 
	{
		return lastName;
	}
	
	// Last name setter
	public void setLastName(String ln) 
	{
		lastName = ln;
	}

	// Status getter
	public String getStatus()
	{
		return status;
	}
	
	// Status setter
	public void setStatus(String s)
	{
		status = s;
	}
	
	// Role getter
	public String getRole()
	{
		return role;
	}
	
	// Role setter
	public void setRole(String r)
	{
		role = r;
	}
	
	// Number getter
	public String getNumber()
	{
		return number;
	}
	
	// Number setter
	public void setNumber(String n)
	{
		number = n;
	}
	
	// Email getter
	public String getEmail()
	{
		return email;
	}
	
	// Email setter
	public void setEmail(String e)
	{
		email = e;
	}
	
	// Contacts getter
	public ArrayList<String> getContacts() 
	{
		return contacts;
	}
	
	// Add a new contact to the contact collection of each person
	public void addContact(String i) 
	{
		if (!contacts.contains(i)) 
		{
			contacts.add(i);
		}
	}
	
	// Compare 2 people using ids
	public boolean equals(Person rhs) 
	{
		return id.equals(rhs.getId());
	}
	
	// Display the contact collection
	public String contactToString() 
	{
		String toReturn = new String();
		if (contacts.size() > 0) 
		{
			for (int i = 0; i < contacts.size(); i++) 
			{
				toReturn += "," + contacts.get(i);
			}
		}
		return toReturn;
	}
	
	// Display each person's information
	public String toString() 
	{
		return id + "," + firstName + "," + lastName + "," + status + "," + role + "," + number + "," + email + "," + contactToString();
	}
}