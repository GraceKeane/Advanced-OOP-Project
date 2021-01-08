package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;

/**
 * The class Database uses EmbeddedStorage & 
 * EmbeddedStorageManager to save jar information
 * into the folder "dta".
 * 
 * @author Grace Keane
 * @version Java 15
 * 
 */
public class Database {	

	private EmbeddedStorageManager db = null;
	private Collection<String> root = new LinkedList<>();
	
	public Database() {
		super();
	}
		
	/**
	 * Iterates through the selected jar and prints out called reflection
	 * information data based on the jar information. Then stores 
	 * processed data in the database (dta folder)  
	 * 
	 * @param fileName takes in a file name
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 */
	public void go(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
		// Reading in the jar file & extracting all class names 
		JarInputStream in = new JarInputStream(new FileInputStream(new File(fileName)));
		JarEntry next = in.getNextJarEntry();
		db = EmbeddedStorage.start(root, Paths.get("./dta"));
					
		// Iterating through the file
		while (next != null) {
			if (next.getName().endsWith(".class")) {
				// Strip out "/" and "\\" from the name
				String name = next.getName().replaceAll("/", "\\.");
				name = name.replaceAll(".class", "");
				if (!name.contains("$"))
					name.substring(0, name.length() - ".class".length());
				
				// Print out the name of the class
				System.out.println(name);
				// Loading the class
			    Class cls = Class.forName(name);  
			    
			    // Calling information from the jar file using of reflection
			    System.out.println("");
			    System.out.println("Class name: " + cls);
			    System.out.println("");
			    System.out.println("Class Method: " + cls.getMethods());
			    System.out.println("");
			    System.out.println("Class Modifier: " + cls.getModifiers());
			    System.out.println("");
			    System.out.println("Class Hashcode: " + cls.hashCode());
			    System.out.println("");
			    System.out.println("Member?: " + cls.isMemberClass());
			    System.out.println("");
				    
			    // Saving jar info to the database
			    root.add(" ");
			    root.add("Class name: " + cls.toString());
			    root.add(" ");
			    root.add("Descriptor string: " + cls.descriptorString().toString());
			    root.add(" ");
			    root.add("Method: " + cls.getMethods().toString());
			    
			    db.storeRoot();    
			}
			next = in.getNextJarEntry();
			System.out.println();
		}
		db.shutdown();
	}
	
	// Query 1
	public void showContents() {
		//Query 1: Show all data in the database.
		System.out.println("\n[Query] Show all contents of database");
		try {
			System.out.println("\n[Query] Processing...");
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root.stream()
		.forEach(System.out::println);;
	}
}