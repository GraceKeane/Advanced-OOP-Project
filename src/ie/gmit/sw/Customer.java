package ie.gmit.sw;

/**
 *  Customer is a record or a read-only bean class with a constructor that 
 *  matches the set of parameters in the record signature and a suite of 
 *  accessor methods. The default implementation of the methods equals() and 
 *  hashCode() aggregate all the attributes of the record.
 *  
 *  Bean class.
 *  
 *  @author Grace Keane
 *  @version Java 15
 */

public class Customer{
	private String name;
	private double numOfLines;
	private String jarfile;
	
	public Customer(String name, double numOfLines, String jarfile) {
		super();
		this.name = name;
		this.numOfLines = numOfLines;
		this.jarfile = jarfile;
	}

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
