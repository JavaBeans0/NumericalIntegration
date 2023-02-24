import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import computation.Automation;

public class TestTrapezoidal {
    @BeforeTest public void initiate() { Automation.initialize(); }
    @BeforeTest public void printoutFunction() { Automation.printFunction(); }
    @Test public void testError() { Automation.errorDetermination(); }
//    @Test
//    @Test
    @AfterTest public void terminate() { Automation.closeEverything(); }
    @AfterTest public void thankYou() { Automation.thanks(); }
}

