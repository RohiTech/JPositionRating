package jpositionrating;
import junit.framework.*;
/*
 * AutorTest.java
 * JUnit based test
 *
 * Created on 24 de diciembre de 2007, 03:48 PM
 */

/**
 *
 * @author Usuaio
 */
public class AutorTest extends TestCase {
    
    public AutorTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(AutorTest.class);
        
        return suite;
    }
    
}
