package ie.gmit.sw;

/**
 * Customer is a record or a read-only bean class. It contains
 * important getter and setter methods that are used in the 
 * AppWindow class to call information.
 *  
 * @author Grace Keane
 * @version Java 15
 */

public class Customer{
	// Encapsulated vars
	private String name;
	private double numOfLines;
	private String jarfile;
	
	private Customer(String name, double numOfLines, String jarfile) {
		super();
		this.name = name;
		this.numOfLines = numOfLines;
		this.jarfile = jarfile;
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getNumOfLines() {
		return numOfLines;
	}

	public void setNumOfLines(double numOfLines) {
		this.numOfLines = numOfLines;
	}

	public String getJarfile() {
		return jarfile;
	}

	public void setJarfile(String jarfile) {
		this.jarfile = jarfile;
	}	
}
