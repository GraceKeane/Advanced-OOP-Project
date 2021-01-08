package ie.gmit.sw;

import javafx.application.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * Contains all button functionality relating to the
 * GUI and also holds some GUI design code such 
 * as width and height of stage.
 * 
 * @author Grace Keane
 * @version Java 15 
 */
public class AppWindow extends Application {
	//The Model - a list of observers
	private ObservableList<Customer> customers; 
	//The View - a composite of GUI components
	private TableView<Customer> tv; 
	//A control, part of the View and a leaf node.
	public static TextField txtFile; 

	// Object of database
	static Database db = new Database();
	
	/**
	 * The GUI is based on the composite pattern and is a tree of nodes, some
	 * of which are composite nodes (containers) and some are leaf nodes (controls).
	 * A stage contains scenes, each of which is a container window for other
	 * containers or controls.
	 * 
	 * @param stage
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
		box.getChildren().add(TitledPane.getFileChooserPane(stage)); 
		box.getChildren().add(toolBar);
		
		// Display the window
		stage.show();
		stage.centerOnScreen();
	}
	
}