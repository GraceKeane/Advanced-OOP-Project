package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * Runner class reads in a jar file and prints out the data.
 * 
 * @author Grace Keane
 * @version Java 15
 * @since 25/12/2020
 * 
 */
public class ReadJar {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		// Launching GUI
	    //Application.launch(AppWindow.class, args);

		// Reading in the jar file & extracting all class names 
		JarInputStream in = new JarInputStream(new FileInputStream(new File("./commons-text-1.9.jar")));
		JarEntry next = in.getNextJarEntry();
				
		while (next != null) {
			if (next.getName().endsWith(".class")) {
				// Strip out from the name
				String name = next.getName().replaceAll("/", "\\.");
				name = name.replaceAll(".class", "");
				if (!name.contains("$"))
					name.substring(0, name.length() - ".class".length());
				// Print out the name of the class
				System.out.println(name);
				
				// Loading the class
			    Class cls = Class.forName("ie.gmit.sw.Runner");  
			    cls.getClass();
			    visit(cls);
			    
			    // Stream
			    //List<Class> list = null;
			    //list.stream().filter(predicate);
			    
			    Method[] methods = cls.getMethods(); //Get the set of methods
			
			   
			}
			next = in.getNextJarEntry();
		}
	}

	private static void visit(Class node) {
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