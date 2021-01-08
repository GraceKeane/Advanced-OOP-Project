package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import javafx.application.*;

/**
 * Runner class that reads a hard coded jar file and applies one metric to it (Count SLOC).
 * Starts and runs a GUI.
 * 
 * @author Grace Keane
 * @version Java 15
 * 
 */

public class Runner {

	// Printing out jar contents to the cammand line
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Reading in the jar file & extracting all class names
		JarInputStream in = new JarInputStream(new FileInputStream(new File("./commons-text-1.9.jar")));
		JarEntry next = in.getNextJarEntry();

		// Iterating through the jar file
		while (next != null) {
			if (next.getName().endsWith(".class")) {
				// Strip out "/" "\\" from the name
				String name = next.getName().replaceAll("/", "\\.");
				name = name.replaceAll(".class", "");
				if (!name.contains("$"))
					name.substring(0, name.length() - ".class".length());
				// Print out the name of the class
				System.out.println(name);

				// Loading the class
				Class cls = Class.forName("ie.gmit.sw.Runner");
				cls.getClass();
				visit.getVisit(cls);

				// Get the set of methods
				Method[] methods = cls.getMethods(); 

			}
			// Continuing through the jar
			next = in.getNextJarEntry();
		}
		
		// Run GUI
		System.out.println("[INFO] Launching GUI...");
		Application.launch(AppWindow.class, args);

	}
}
