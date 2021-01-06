package ie.gmit.sw;

import java.io.*;
import javafx.application.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * Contains all functions relating to the GUI and also holds GUI design code.
 * 
 * @author Grace Keane
 * @version Java 15 
 */
public class AppWindow extends Application {
	//The Model - a list of observers.
	private ObservableList<Customer> customers; 
	//The View - a composite of GUI components
	private TableView<Customer> tv; 
	//A control, part of the View and a leaf node.
	private TextField txtFile; 
	
	// Object of database
	Database db = new Database();
	
	/**
	 * The GUI is based on the composite pattern and is a tree of nodes, some
	 * of which are composite nodes (containers) and some are leaf nodes (controls).
	 * A stage contains scenes, each of which is a container window for other
	 * containers or controls.
	 * 
	 * @param statge
	 * @throws Exception
	 * 
	 */
	@Override
	public void start(Stage stage) throws Exception { 
		// Setting title, width & height of window
		stage.setTitle("GMIT - B.Sc. in Computing (Software Development)");
		stage.setWidth(700);
		stage.setHeight(400);
		
		stage.setOnCloseRequest((e) -> System.exit(0));
		
		// The root container is a VBox - Strategy Pattern
		VBox box = new VBox();
		box.setPadding(new Insets(10));
		box.setSpacing(9);

		// Configure the Context with a Concrete Strategy
		Scene scene = new Scene(box); 
		stage.setScene(scene);

		// Creating a new object called toolBar.
		// A ToolBar is a composite node for Buttons (leaf nodes)
		ToolBar toolBar = new ToolBar(); 

		/*
		 * Observer Pattern.
		 * 
		 * Using a lambda expression to plant an EventHandler<WindowEvent> 
		 * observer on the stage close button.
		 * 
		 */
		Button btnQuit = new Button("Quit"); 
		btnQuit.setOnAction(e -> System.exit(0)); 
		toolBar.getItems().add(btnQuit); //Add to the parent node and build the tree
		
		/*
		 * Observer Pattern.
		 * 
		 * Using a lambda expression to plant an EventHandler<WindowEvent> 
		 * observer on the stage show all button. Database prints 
		 * out information contained when "Show all" is clicked.
		 * 
		 */
		Button btnShow = new Button("Show all"); 
		btnShow.setOnAction(e -> { 
		db.showContents(); // Show contents of the database
		});
		toolBar.getItems().add(btnShow);
		
		/*
		 * Add all the sub trees of nodes to the parent node and build the tree
		 */
		box.getChildren().add(getFileChooserPane(stage)); //Add the sub tree to the main tree
		//box.getChildren().add(getTableView()); //Add the sub tree to the main tree
		box.getChildren().add(toolBar); //Add the sub tree to the main tree
		
		// Display the window
		stage.show();
		stage.centerOnScreen();
	}

	/*
	 *  This method builds a TitledPane containing the controls for the file chooser 
	 *  part of the application. 
	 */
	private TitledPane getFileChooserPane(Stage stage) {
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

		TitledPane tp = new TitledPane("Select File to Process", panel); //Add to the parent node and build a sub tree
		tp.setCollapsible(false);
		return tp;
	}
}