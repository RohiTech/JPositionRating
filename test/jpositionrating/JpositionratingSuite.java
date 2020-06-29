package jpositionrating;
import junit.framework.*;
/*
 * JpositionratingSuite.java
 * JUnit based test
 *
 * Created on 24 de diciembre de 2007, 03:48 PM
 */

/**
 *
 * @author Usuaio
 */
public class JpositionratingSuite extends TestCase {
    
    public JpositionratingSuite(String testName) {
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
        TestSuite suite = new TestSuite("JpositionratingSuite");
        suite.addTest(jpositionrating.ContenidoTest.suite());
        suite.addTest(jpositionrating.AcercaTest.suite());
        suite.addTest(jpositionrating.JPositionRatingTest.suite());
        suite.addTest(jpositionrating.AutorTest.suite());
        return suite;
    }
    
}
