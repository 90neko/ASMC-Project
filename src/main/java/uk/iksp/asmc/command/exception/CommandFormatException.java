package uk.iksp.asmc.command.exception;

import uk.iksp.asmc.exception.AsmcException;

public class CommandFormatException extends AsmcException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4394577658416504566L;

	private String message = "Exception";
	
    public CommandFormatException(){
        super();
    }
    
    public CommandFormatException(String message){
    	this.message = message;
    }
    
    
    public String getMessage(){
		return this.message;
    }
	
	
}
