package tuniu.chain;

import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.impl.ContextBase;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class ChainRunner implements BeanFactoryAware {
 
	private BeanFactory beanFactory;
 
	public void runChain( String chainName ) {
		try {
			ContextBase cb = new ContextBase();
			cb.put("begin", "begin");
			createChain ( chainName ).execute( cb );
			
			System.out.println(cb.toString());
			
		}
		catch ( Exception exc ) {
			throw new RuntimeException("Chain \"" + chainName + "\": Execution failed.", exc );
		}
	}
 
	public void setBeanFactory( BeanFactory beanFactory ) throws BeansException {
		this.beanFactory = beanFactory;
	}
 
	protected ChainBase createChain( String chainName ) {
		return ( ChainBase ) this.beanFactory.getBean( chainName );
	}
}