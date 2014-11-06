package list.view;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import list.model.ITask;
import list.model.ICategory;

/**
 * Handle all GUI of the Categories
 * There are buttons and labels
 * 
 * @author Shotaro
 */
public class CategoriesController {
	
	// fixed value for the size and number of buttons
    private static final double BUTTON_X = 0.0d;
    private static final double BUTTON_WIDTH = 140.0d;
    private static final double BUTTON_HEIGHT = 26.0d;
    private static final double NUMBER_OF_DEFALUT_BUTTON = 3.0d;
    
    // fixed value for the size and number of labels
    private static final double LABEL_X = 0.0d;
    private static final double LABEL_Y = 0.0d;
    private static final double LABEL_WIDTH = 140.0d;
    private static final double LABEL_HEIGHT = 13.0d;
    private static final double NUMBER_OF_DEFAULT_LABEL = 2.0d;
    
    // fixed value for the size of pane
    private static final double PANE_X = 0.0d;
    private static final double PANE_Y = 0.0d;
    private static final double PANE_WIDTH = 140.0d;
    private double PANE_HEIGHT;
    
    // fixed value for the string of labels and buttons
    private static final String stringForLabelAllCategory = "All Task";
    private static final String stringForLabelOtherCategory = "Others";
    private static final String stringForButtonCurrentTask = "Current Task";
    private static final String stringForButtonFloatingTask = "Floating Task";
    private static final String stringForButtonOverDueTask = "Overdue Task";
    
    // lists to hold the value of tasks and categories to keep on track them
    private List<ITask> tasks;
    private List<ICategory> categoryListCalculated = new ArrayList<ICategory>();
    private List<ICategory> categoryListAppeared = new ArrayList<ICategory>();
    
    // position to start displaying the categories made by user
    private double positonToDisplayOthers = LABEL_HEIGHT * NUMBER_OF_DEFAULT_LABEL + BUTTON_HEIGHT * NUMBER_OF_DEFALUT_BUTTON;
    
    // ScrollPane to make the categories to be able to scroll
    @FXML
    ScrollPane paneContainer;
    
    // Pane to hold the buttons and labels
    Pane categoriesContainer = new Pane();
    
    public CategoriesController(List<ITask> tasks) {
    	
    	// assign the value of list of the tasks
    	this.tasks = tasks;
    	
    	// calculate the height of the pane according to the number of other categories
    	PANE_HEIGHT = calculatePaneHeight();
    }
    
    public double calculatePaneHeight() {
    	
    	// number of other categories to be set to zero
    	double numberOfOtherCategories = 0.0d;
    	
    	// plus one the number of other categories if the new category was presented
    	for(int i = 0; i < tasks.size(); i++) {
			if(!(categoryListCalculated.contains(tasks.get(i).getCategory()))) {
				numberOfOtherCategories++;
				categoryListCalculated.add(tasks.get(i).getCategory());
			}
    	}
    	
    	// return the height of the Pane
    	return LABEL_HEIGHT * NUMBER_OF_DEFAULT_LABEL + BUTTON_HEIGHT * (NUMBER_OF_DEFALUT_BUTTON + numberOfOtherCategories);
    }
    
    public void setUpView() {
    	
    	// set the style, layout and the size of the Pane
    	categoriesContainer.setLayoutX(PANE_X);
        categoriesContainer.setLayoutY(PANE_Y);
        categoriesContainer.setPrefWidth(PANE_WIDTH);
        categoriesContainer.setPrefHeight(PANE_HEIGHT);
        categoriesContainer.setStyle("-fx-background-color: #333333;");
        
        // create the default label to be displayed
    	Label labelForAllCategory = setUpLabel(stringForLabelAllCategory, LABEL_X, LABEL_Y);
    	Label labelForOtherCategory = setUpLabel(stringForLabelOtherCategory, LABEL_X, LABEL_Y + LABEL_HEIGHT+BUTTON_HEIGHT * NUMBER_OF_DEFALUT_BUTTON);
    	
    	// create the default button to be displayed
    	Button buttonForCurrentTask = setUpButton(stringForButtonCurrentTask, BUTTON_X, LABEL_Y + LABEL_HEIGHT);
    	Button buttonForFloatingTask = setUpButton(stringForButtonFloatingTask, BUTTON_X, LABEL_Y + LABEL_HEIGHT+BUTTON_HEIGHT * 1);
    	Button buttonForOverDueTask = setUpButton(stringForButtonOverDueTask, BUTTON_X, LABEL_Y + LABEL_HEIGHT+BUTTON_HEIGHT * 2);
    
        // add the labels and buttons into the Pane
        categoriesContainer.getChildren().add(labelForAllCategory);
        categoriesContainer.getChildren().add(labelForOtherCategory);
        categoriesContainer.getChildren().add(buttonForCurrentTask);
        categoriesContainer.getChildren().add(buttonForFloatingTask);
        categoriesContainer.getChildren().add(buttonForOverDueTask);
        
        // display other categories on to the screen
        displayOtherCategory();
        
        // set the Pane on the ScrollPane
        paneContainer.setContent(categoriesContainer);
    }
    
    private Label setUpLabel(String title, double xPos, double yPos) {
    	
    	// set up the label according to the title, layout, and size
    	Label label = new Label();
    	label.setText(title);
        label.setLayoutX(xPos);
        label.setLayoutY(yPos);
        label.setPrefWidth(LABEL_WIDTH);
        label.setPrefHeight(LABEL_HEIGHT);
        label.setStyle("-fx-background-color: white;");
        label.setFont(Font.font("Helvetica", 10.0d));
        
        // return the label created
        return label;
    }
    
    private Button setUpButton(String title, double xPos, double yPos) {
    	
    	// set up the button according to the title, layout, and size
    	Button button = new Button();
    	button.setText(title);
        button.setLayoutX(xPos);
        button.setLayoutY(yPos);
        button.setPrefWidth(BUTTON_WIDTH);
        button.setPrefHeight(BUTTON_HEIGHT);
        button.setStyle("-fx-background-color: #333333;");
        button.setFont(Font.font("Helvetica", 14.0d));
        
        // return the button created
        return button;
    }
    
    public void clearAll() {
    	
    	// clear all the content in the ScrollPane
    	paneContainer.setContent(null);
    }
	
	private void displayOtherCategory() {
		
		// keep on track the current position 
		int currentPosition = 0;
		
		// check whether the category is already added
		for(int i = 0; i < tasks.size(); i++) {
			if(!(categoryListAppeared.contains(tasks.get(i).getCategory()))) {
				
				// set up the button according to the title, layout, and size
				Button button = new Button();
		        button.setText(tasks.get(i).getCategory().getName());
		        button.setLayoutX(BUTTON_X);
		        button.setLayoutY(positonToDisplayOthers + BUTTON_HEIGHT * currentPosition);
		        button.setPrefHeight(BUTTON_HEIGHT);
		        button.setPrefWidth(BUTTON_WIDTH);
		        button.setStyle("-fx-background-color: #333333;");
		        button.setFont(Font.font("Helvetica", 18.0d));
		        
		        // put the category to the list to keep on track
		        categoryListAppeared.add(tasks.get(i).getCategory());
		        
		        // add the button on the Pane
		        categoriesContainer.getChildren().add(button);
			}
		}
    }
}