package uk.iksp.asmc.exception;

public class AsmcException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1192871573485433073L;
	
	
	private String message = "Exception";
	
    public AsmcException(){
        super();
    }
    
    public AsmcException(String message){
    	this.message = message;
    }
	
    public String getMessage(){
		return this.message;
    }
	
}
