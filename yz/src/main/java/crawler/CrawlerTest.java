package crawler;

import java.util.concurrent.BlockingQueue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations={ "thread.xml"} )
public class CrawlerTest {
 
	@Autowired
	ThreadPoolTaskExecutor commonExecutor;
	
	BaiduSearchImpl baiduSearchImpl = new BaiduSearchImpl();
 
	@Test()
	public void driveTheChain() {
		baiduSearchImpl.search("代理IP", 15);
		/*commonExecutor.execute(new Runnable() {
			@Override
			public void run() {
				baiduSearchImpl.search("代理IP", 15);
			}
		});*/
		
		PageAnalysisServiceImpl pasi = new PageAnalysisServiceImpl();
		pasi.htmlAnalysis();
	}
}