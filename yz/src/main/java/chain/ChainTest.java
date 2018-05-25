package chain;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations={ "spring.xml"} )
public class ChainTest {
 
	@Autowired
	ChainRunner chainRunner;
 
	@Test()
	public void driveTheChain() {
		System.out.println("Starting up...      [Ok]");
		chainRunner.runChain( "pingPongChain" );
		System.out.println("Finised...          [Ok]");
	}
}