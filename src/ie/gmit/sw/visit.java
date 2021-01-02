package ie.gmit.sw;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * visit class implements my matric. 
 * 
 */
public class visit {
	
	public static void getVisit(Class node) {
		// Store the name of class or something else in object store -> object.store
		// compute some metric on the class (1 or more)
		
		Method [] m = node.getMethods();
		for(Method n : m){
			n.getName();	
		}	
		
		int count = 0;

	    try {
	      // create a new file object
	      File file = new File("commons-text-1.9.jar");

	      // create an object of Scanner
	      // associated with the file
	      Scanner sc = new Scanner(file);

	      // read each line and
	      // count number of lines
	      while(sc.hasNextLine()) {
	        sc.nextLine();
	        count++;
	      }
	      
	      System.out.println("Total Number of Lines: " + count);

	      // close scanner
	      sc.close();
	    } catch (Exception e) {
	      e.getStackTrace();
	    }
	}
}
