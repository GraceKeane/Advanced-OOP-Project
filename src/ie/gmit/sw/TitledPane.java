package ie.gmit.sw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * Created class TitledPane to Adhere to the open closed principle (OCP).
 * This class contains all the processing design and functionality.
 * 
 * @author Grace Keane
 * @version Java 15
 * 
 */
public class TitledPane extends AppWindow{

	public TitledPane(String string, VBox panel) {
		// TODO Auto-generated constructor stub
	}

	/*
	 *  This method builds a TitledPane containing the controls for the file chooser 
	 *  part of the application. 
	 */
	public static Node getFileChooserPane(Stage stage) {
		// A concrete strategy
		VBox panel = new VBox(); 

		// Leaf node
		txtFile = new TextField(); 

		// Leaf node
		FileChooser fc = new FileChooser();
		// Allow to choose jar files
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JAR Files", "*.jar"));

		/*
		 * Observer Pattern.
		 * 
		 * Using a lambda expression to plant an EventHandler<WindowEvent> 
		 * observer on the stage Select file button. Database prints 
		 * out information contained when "Show all" is clicked.
		 * 
		 */
		Button btnOpen = new Button("Select File"); 
		btnOpen.setOnAction(e -> { 
			File f = fc.showOpenDialog(stage);
			txtFile.setText(f.getAbsolutePath());
		});

		/*
		 * Observer Pattern.
		 * 
		 * Using a lambda expression to plant an EventHandler<WindowEvent> 
		 * observer on the stage for Process button. Program then processes
		 * file in the command prompt straight away.
		 * 
		 */
		Button btnProcess = new Button("Process"); //A leaf node
		btnProcess.setOnAction(e -> { //Plant an observer on the button
		File f = new File(txtFile.getText());	
		System.out.println("[INFO] Processing file " + f.getName());	
		ReadJar r = new ReadJar(f.toString());
		
		// Exception handling
		try {
			db.go(f.toString());
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(IOException ex) {
			ex.printStackTrace();
		} catch(NoClassDefFoundError ex) {
			ex.printStackTrace();
		}
			
	});
		
		ToolBar tb = new ToolBar(); //A composite node
		tb.getItems().add(btnOpen); //Add to the parent node and build a sub tree
		tb.getItems().add(btnProcess); //Add to the parent node and build a sub tree

		panel.getChildren().add(txtFile); //Add to the parent node and build a sub tree
		panel.getChildren().add(tb); //Add to the parent node and build a sub tree
		
		TitledPane p = new TitledPane("Select File to Process", panel); //Add to the parent node and build a sub tree
		p.setCollapsible(false);
		
		return tb;

		//TitledPane tp = new TitledPane("Select File to Process", panel); //Add to the parent node and build a sub tree
		//tp.setCollapsible(false);
		//return tp;
	}

	public Object TitledPane;

	private void setCollapsible(boolean b) {
		panel(true);
	}

	private void panel(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
	