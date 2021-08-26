// Implementation Tester 
package contactTracingDataBackEnd;

import contactTracingDataBackEnd.Collection;

public class Main 
{
	public static void main(String[] Args) 
	{
		//Define constant variable for the name of the file to store data
		final String DATA_FILE = "data.txt";
		
		/*------------------------Person Class Tests-------------------------*/
		
		// Constructor Tester
		Person person1 = new Person();
		Person person2 = new Person("23589","Dan","Williams","good","instructor","501-24-2379","dw@uca.edu");
		
		//Getter Tester
		System.out.println("Id: " + person2.getId());
		System.out.println("First Name: " + person2.getFirstName());
		System.out.println("Last Name: " + person2.getLastName());
		System.out.println("Status: " + person2.getStatus());
		System.out.println("Role: " + person2.getRole());
		System.out.println("Phone: " + person2.getNumber());
		System.out.println("Email: " + person2.getEmail());
		System.out.println("Contacts: " + person2.getContacts());
		
		// Setters and toString Tester
		person1.setId("70012");
		person1.setFirstName("Connie");
		person1.setLastName("Spencer");
		person1.setStatus("infected");
		person1.setRole("student");
		person1.setNumber("555-34-6000");
		person1.setEmail("cs@uca.edu");
		
		System.out.println("\n" + person1);
		
		// addContact Tester
		person2.addContact(person1.getId());
		
		System.out.println("\n" + person2);
				
		// equals Tester
		System.out.println("\nPerson1 equals Person2: " + person1.equals(person2) + " [Expect False]\n");
				
		
		/*------------------------Collection Class Tests------------------------*/
				
		// Constructor Tester
		Collection data = new Collection(DATA_FILE);
		
		// Getter Tester
		data.add(person1);
		data.add(person2);
		
		// toString Tester
		System.out.println(data);
		
		// remove Tester
		System.out.println("\nPerson: " + data.remove("70012").getFirstName() + " was removed.");
		
		// addContact and findPerson Tester
		data.addContact(data.findPerson("45689"), person2);
		
		System.out.println("\n" + data.findPerson("45689"));
		
		// Test size, isEmpty, and contains
		System.out.println("\nSize of the collection: " + data.size());
		System.out.println("The collection is empty: " + data.isEmpty() + " [Expect false]");
		System.out.println("Collection contains 57890: " + data.contains("57890") + " [Expect true]");
		System.out.println("Collection contains 00012: " + data.contains("00012") + " [Expect false]");
		
		// Test writeFile and doWrite
		data.writeFile("testData.txt");
	}
}