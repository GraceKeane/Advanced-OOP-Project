package ie.gmit.sw;

import java.time.LocalDateTime;

/*
 *  Customer is a record or a read-only bean class with a constructor that 
 *  matches the set of parameters in the record signature and a suite of 
 *  accessor methods. The default implementation of the methods equals() and 
 *  hashCode() aggregate all the attributes of the record.
 *  
 *  Bean class
 */

public record Customer(String name, double numOfLines, String jarfile) {

	
}
