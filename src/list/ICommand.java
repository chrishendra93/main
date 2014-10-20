package list;

import java.io.IOException;

import list.Converter.CorruptedJsonObjectException;
import list.Date.InvalidDateException;

import org.json.JSONException;

/**
 * A command object that is generated by a parser.
 * Objects implementing ICommand can execute itself by the help
 * of task manager.
 * 
 * @author andhieka, michael
 *
 */
interface ICommand {	
	@SuppressWarnings("serial")
    class InvalidTaskNumberException extends Exception { };
		
	@SuppressWarnings("serial")
    class CommandExecutionException extends Exception {
	    public CommandExecutionException(String message) {
	        super(message);
	    }
	};
	
	/**
	 * Executes this command.
	 * 
	 * @return the response to be shown in the console.
	 * @throws CommandExecutionException 
	 * @throws InvalidTaskNumberException 
	 * @throws IOException 
	 * @throws JSONException 
	 */
	String execute() throws CommandExecutionException, JSONException, 
			IOException, InvalidTaskNumberException;
	
}
