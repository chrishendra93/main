package list.view;

import java.util.List;

import javafx.collections.ObservableList;
import list.model.ICategory;
import list.model.ITask;

/**
 * 
 * 
 * @author andhieka, michael, shotaro
 */
public interface IUserInterface { 
    
    void displayTaskDetail(ITask task);
    
    void hideTaskDetail();
        
    void display(String pageTitle, ObservableList<ITask> tasks);
        
    void clearDisplay();
        
    void displayMessageToUser(String message);
    
    void displayCategories(List<ICategory> categories);
    
    void hideCategories();
    
    boolean back();
    
    boolean next();
}