import junit.framework.*;
/*
 * RootSuite.java
 * JUnit based test
 *
 * Created on 24 de diciembre de 2007, 03:48 PM
 */

/**
 *
 * @author Usuaio
 */
public class RootSuite extends TestCase {
    
    public RootSuite(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }


    /**
     * metodo de conjunto generado automaticamente por el modulo JUnit
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("RootSuite");
        suite.addTest(jpositionrating.JpositionratingSuite.suite());
        return suite;
    }
    
}
