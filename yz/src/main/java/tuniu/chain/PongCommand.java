package tuniu.chain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class PongCommand implements Command {
 
	private String message;
 
	public PongCommand( String message ) {
		this.message = message;
	}
 
	public boolean execute( Context context ) throws Exception {
		context.put("PongCommand", "PongCommand");
		System.err.println( message );
		return false;
	}
}