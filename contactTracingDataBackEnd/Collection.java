// A collection of Person data
package contactTracingDataBackEnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

import contactTracingDataBackEnd.Person;

public class Collection
{
	private HashMap<String, Person> people;
	private String filename;
	
	// Default Constructor
	public Collection()
	{
		people = new HashMap<String, Person>();
		filename = null;
	}
	
	// Constructor
	public Collection(String fn) 
	{
		this();
		filename = fn;
		readFile();
	}
	
	// Add new person to the whole dataset
	public void add(Person p)
	{
		String newId = p.getId();
		people.put(newId, p);
	}
	
	// Remove person from the whole dataset
	public Person remove(String i)
	{
		return people.remove(i);
	}
	
	// Add new contact to the contact collection of each person
	public void addContact(Person p1, Person p2)
	{
		p1.addContact(p2.getId());
	}
	
	// Iterate through the whole dataset
	public Iterator<String> getIterator() 
	{
		return people.keySet().iterator();
	}
	
	// Find a person using her/his id
	public Person findPerson(String i) 
	{
		return people.get(i);
	}
	
	// Return the size of the dataset which is the number of key-value pairs in the hash map
	public int size() 
	{
		return people.size();
	} 
	
	// Check if the dataset is empty
	public boolean isEmpty() 
	{
		return people.isEmpty();
	}
	
	// Return true if the id (key) is in the dataset
	public boolean contains(String i) 
	{
		return people.containsKey(i);
	}
	
	// String representation of the Collection class
	public String toString() 
	{
		Iterator<String> iter = this.getIterator();
		String toReturn = "";
		while(iter.hasNext()) 
		{
			toReturn += this.findPerson(iter.next()).toString() + "\n";
		}
		return toReturn;
	}
	
	// Read data from input file
	private void readFile() 
	{
		BufferedReader lineReader = null;
		try 
		{
			FileReader fr = new FileReader(filename);
			lineReader = new BufferedReader(fr);
			String line = null;
			while((line = lineReader.readLine()) != null) 
			{
				String[] tokens = line.split(",");
				// Pass tokens as arguments to the Person constructor
				Person temp = new Person(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
				// Any remaining tokens are the id's of the Person's contacts
				for(int i = 7; i < tokens.length; i++) 
				{
					temp.addContact(tokens[i]);
				}
				// Add Person to collection of people
				people.put(temp.getId(), temp);
			}
		} 
		catch (Exception e) 
		{
			//System.err.println("there was a problem with the file reader, try different read type.");
			try 
			{
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)));
				String line = null;
				while((line = lineReader.readLine()) != null) 
				{
					String[] tokens = line.split(",");
					// Pass tokens as arguments to the Person constructor
					Person temp = new Person(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
					// Remaining tokens are the id's of the Person's contacts
					for(int i = 7; i < tokens.length; i++) 
					{
						temp.addContact(tokens[i]);
					}
					// Add Person to collection of people
					people.put(temp.getId(), temp);
				}
			} 
			catch (Exception e2) 
			{
				e2.fillInStackTrace();
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} 
			finally 
			{
				if (lineReader != null)
					try 
					{
						lineReader.close();
					} 
					catch (IOException e2) 
					{
						System.err.println("could not close BufferedReader");
					}
			}
		} 
		finally 
		{
			if (lineReader != null)
				try 
				{
					lineReader.close();
				} 
				catch (IOException e) 
				{
					System.err.println("could not close BufferedReader");
				}
		}
	} 
	
	// Save data between runs
	public void writeFile() 
	{
		doWrite(filename);
	}

	// Test the doWrite function
	public void writeFile(String altFileName) 
	{
		doWrite(altFileName);
	} 

	// Write the data from Collection into a file
	private void doWrite(String fn) 
	{
		try 
		{
			FileWriter fw = new FileWriter(fn);
			BufferedWriter outputFile = new BufferedWriter(fw);
			outputFile.write(this.toString());
			outputFile.flush();
			outputFile.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Didn't save to " + fn);
		}
	} 
}