package jpositionrating;
import junit.framework.*;
/*
 * ContenidoTest.java
 * JUnit based test
 *
 * Created on 24 de diciembre de 2007, 03:48 PM
 */

/**
 *
 * @author Usuaio
 */
public class ContenidoTest extends TestCase {
    
    public ContenidoTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ContenidoTest.class);
        
        return suite;
    }

    /**
     * Prueba del metodo Combo , de la clase jpositionrating.Contenido.
     */
    public void testCombo() {
        System.out.println("Combo");
        
        jpositionrating.Contenido instance = new jpositionrating.Contenido();
        
        instance.Combo();
        
        // TODO revisar el cadigo de prueba generado y eliminar la llamada predeterminada que falta.
        fail("El caso de prueba es un prototipo.");
    }
    
}
