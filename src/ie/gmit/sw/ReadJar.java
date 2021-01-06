package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;


import javafx.application.*;

/**
 * 
 * @author Grace Keane
 * @version Java 15
 * 
 * Runner class that reads a jar file and applied one metric to it.
 * Runs a GUI.
 * 
 */

public class ReadJar {
	public String inputFile;
	private List<String> root = new ArrayList<>();
	
	public ReadJar(String inputFile) {
		super();
		this.inputFile = inputFile;
	}
	
	public void read(String inputFile) throws FileNotFoundException, IOException, ClassNotFoundException{

		// Reading in the jar file & extracting all class names 
		JarInputStream in = new JarInputStream(new FileInputStream(new File(inputFile)));
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
			    Class cls = Class.forName(name); 
			    System.out.println("Classes: " + cls.getClass().toString());
			    System.out.println("Classes: " + cls.getMethods().toString());
			    
			    // Class to count the lines
			    visit.getVisit(cls);
			   			   
			}
			next = in.getNextJarEntry();
		}
	}
}

