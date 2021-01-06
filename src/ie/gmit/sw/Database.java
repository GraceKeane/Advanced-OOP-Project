package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;

public class Database {	

	private EmbeddedStorageManager db = null;
	private Collection<String> root = new LinkedList<>();
	
	public Database() {
		super();
	}
		
	public void go(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
		// Reading in the jar file & extracting all class names 
		JarInputStream in = new JarInputStream(new FileInputStream(new File(fileName)));
		JarEntry next = in.getNextJarEntry();
		db = EmbeddedStorage.start(root, Paths.get("./dta"));
					
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
			    System.out.println("Class name: " + cls.getClasses());
			    System.out.println("Class discription: " + cls.descriptorString());
			    System.out.println("Member (bool): " + cls.isMemberClass());
				    
			    root.add(cls.getClasses().toString());
			    root.add(cls.descriptorString().toString());
			    root.add(cls.descriptorString().toString());
			    
			    db.storeRoot();    
				    
			}
		}
		db.shutdown();
	}
		
		public void showContents() {
			System.out.println("Print out all data");
			root.stream()
			.forEach(System.out::println);
		}
		
		public void emptyDatabase() {
			root.clear();
			db.storeRoot();
		
		}
	}