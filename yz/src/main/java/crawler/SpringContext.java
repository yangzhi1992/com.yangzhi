package crawler;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
@Service
public class SpringContext implements ApplicationContextAware{

	private static volatile ApplicationContext ac;
	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		SpringContext.ac = ac;
	}
	
	
	
	public static <T> T getBean(Class<T> clazz){
		if(ac==null){
			return null;
		}
		return ac.getBean(clazz);
	}
	
	public static <T> T getBean(String name,Class<T> clazz){
		if(ac==null){
			return null;
		}
		return ac.getBean(name,clazz);
	}
	public static Object getBean(String name){
		if(ac==null){
			return null;
		}
		return ac.getBean(name);
	}
}
