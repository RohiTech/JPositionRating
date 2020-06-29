package jpositionrating;
import junit.framework.*;
/*
 * AcercaTest.java
 * JUnit based test
 *
 * Created on 24 de diciembre de 2007, 03:48 PM
 */

/**
 *
 * @author Usuaio
 */
public class AcercaTest extends TestCase {
    
    public AcercaTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(AcercaTest.class);
        
        return suite;
    }
    
}
