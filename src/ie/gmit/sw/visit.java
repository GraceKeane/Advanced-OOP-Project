package ie.gmit.sw;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * visit class implements my matric. It counts the number of lines in the hard
 * coded jar file.
 * 
 * @author Grace Keane
 * @version Java 15
 * 
 */
public class visit {

	public static void getVisit(Class node) {
		int count = 0;

		Method[] m = node.getMethods();
		for (Method n : m) {
			n.getName();
		}

		try {
			// create a new file object
			File file = new File("commons-text-1.9.jar");

			// create an object of Scanner
			// associated with the file
			Scanner sc = new Scanner(file);

			// read each line and
			// count number of lines
			while (sc.hasNextLine()) {
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
