import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import computation.Automation;

public class TestFunction {
    @BeforeTest public void initiate() { Automation.initialize(); }
    @BeforeTest public void printoutFunction() { Automation.printFunction(); }
    @Test public void testFunctionOutput() { Automation.functionValue(); }
    @Test public void testFunctionDerivative() { Automation.functionDerivative(); }
    @Test public void testFunctionIntegral() { Automation.functionIntegral(); }
    @Test public void testPrintDerivative() { Automation.printDerivative(); }
    @Test public void testPrintIntegral() { Automation.printIntegral(); }
    @Test public void testDerivativeValue() { Automation.derivativeValue(); } // Precondition: testPrintDerivative();
    @Test public void testIntegralValue() { Automation.integralValue(); } // Precondition: testPrintIntegral();
    @AfterTest public void terminate() { Automation.closeEverything(); }
    @AfterTest public void thankYou() { Automation.thanks(); }
}
