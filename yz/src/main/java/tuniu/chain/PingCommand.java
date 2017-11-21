package tuniu.chain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class PingCommand implements Command {
 
	private String message;
 
	public PingCommand( String message ) {
		this.message = message;
	}
 
	public boolean execute( Context context ) throws Exception {
		context.put("PingCommand", "PingCommand");
		System.err.println( message );
		return false;
	}
}