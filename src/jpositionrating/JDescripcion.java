/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpositionrating;

import java.io.*;
import java.text.*;

/**
 *
 * @author José Francisco
 */
public class JDescripcion 
{
    static NumberFormat nf = NumberFormat.getNumberInstance();
    
    public JDescripcion()
    {
        nf.setMaximumFractionDigits(2);
    }
    
    // Este método sirve para ambas regresiones
    
    public static String Puesto(String nomb, String sal, String cant)
    {
        String TitText = Division() + Espacio() + "Descripción del puesto a evaluar \n" + Espacio();
        
        String NombText = "Nombre del puesto: " + nomb + Espacio();
        String SalText = "Salario Mínimo: " + sal + Espacio();
        String CantText = "Cantidad de puesto en la empresa = " + cant  + Espacio() + Division() + Espacio();
        
        NombText = TitText + NombText + SalText + CantText;
        
        return NombText;
    }
    
    public static String Form_Coef()
    {
        String encabezado = Division() + Espacio() + "Fórmula para calcular el coeficiente salarial n-ésimo" + Espacio();
        
        String form_coef = "CS_N = Lsn / Ls1" + Doble_Espacio();
        String donde = "donde: " + Espacio();
        String CSN = "CS_N: Coeficiente Salarial N-ésimo" + Espacio();
        String Lsn = "Lsn: Límite Superior N-ésimo" + Espacio();
        String Ls1 = "Ls1: Primer Límite Superior" + Espacio() + Division() + Espacio();
        
        String res = encabezado + form_coef + donde + CSN + Lsn + Ls1;
        
        return res;
    }
    
    public static String Coeficiente_Salarial_N(int cont, double coef, int Lmax, int Lmax_1)
    {
        String res = "CS_" + String.valueOf(cont) + " = " + String.valueOf(Lmax) + " / " + String.valueOf(Lmax_1) + " = " + String.valueOf(nf.format(coef)) + Espacio();
        
        return res;
    }
    
    public static String Form_Sal()
    {
        String encabezado = Division() + Espacio() + "Fórmula para calcular el salario mensual n-ésimo" + Espacio();
        
        String form_sal = "Sal_Men_N = Sal_Min * Coef_Sal_N" + Doble_Espacio();
        
        String donde = "donde: " + Espacio();
        String Sal_Men_N = "Sal_Men_N: Salario mensual n-ésimo" + Espacio();
        String Sal_Min = "Sal_Min: Salario mínimo" + Espacio();
        String Coef_Sal_N = "Coef_Sal_N: Coeficiente salarial n-ésimo" + Espacio() + Division() + Espacio();
        
        String res = encabezado + form_sal + donde + Sal_Men_N + Sal_Min + Coef_Sal_N;
        
        return res;
    }
    
    public static String Salario_Mensual_N(int cont, double sal_min, double coef_sal, double sal_n)
    {
        String res = "Sal_Men_" + String.valueOf(cont) + " = " + String.valueOf(nf.format(sal_min)) + " * " + String.valueOf(nf.format(coef_sal)) + " = " + String.valueOf(nf.format(sal_n)) + Espacio();
        
        return res;
    }
    
    // Estos métodos sólo se utilizan en la regresión aritmética
    
    public static String Gradiente_Crecimiento(int G, int Pmax, int Pmin, int CantP)
    {
        String encabezado = "Fórmula para calcular el gradiente de crecimiento" + Espacio();
        
        String formula = "G = (Pmax - Pmin) / CantP\n" + Espacio();
        String donde = "donde: " + Espacio();
        String grad = "G: Gradiente de crecimiento" + Espacio();
        String max = "Pmax: Punto máximo" + Espacio();
        String min = "Pmin: Punto mínimo" + Espacio();
        String cp = "CantP: Cantidad de puestos" + Espacio();
        
        String res = "G = (" + String.valueOf(Pmax) + " - " + String.valueOf(Pmin) + ") / " + String.valueOf(CantP) + " = " + String.valueOf(G) + Espacio();
        
        String resultado = encabezado + formula + donde + grad + max + min + cp + res;
        
        return resultado;
    }
    
    // Estos métodos sólo se utilizan en la regresión geométrica
 
    public static String Form_Pto()
    {
        String encabezado = "Fórmula para la asignación de punto" + Espacio();
        
        String form_p = "L = a * r ^ (n - 1)" + Doble_Espacio();
        
        String donde = "donde: " + Espacio();
        String L = "L: Último término" + Espacio();
        String a = "a: Primer término" + Espacio();
        String r = "r: Razón de crecimiento, cte = 2" + Espacio();
        String n = "n: Número de los grados" + Espacio() + Division() + Espacio();
        
        String res = encabezado + form_p + donde + L + a + r + n;
        
        return res;
    }
    
    public static String Asignacion_Pto(int cont, int a, double L)
    {
        String res = "L = " + String.valueOf(a) + " * 2 ^ ( " + String.valueOf(cont) + " - 1 ) = " + String.valueOf(nf.format(L)) + Espacio();
        
        return res;
    }
    
    public static String Razon_Crecimiento(int n, double L, double a, double r)
    {
        String encabezado = Division() + Espacio() + "Fórmula para la asignación de punto" + Espacio();
        
        String form_r = "(n - 1) ^ sqrt(L / a)";
        
        String res = "(" + String.valueOf(n) + " - 1) ^ sqrt(" + String.valueOf(nf.format(L)) + " / " + String.valueOf(nf.format(a)) + ") = " + String.valueOf(nf.format(r)) + Espacio() + Division();
        
        String resultado = encabezado + form_r + res;
        
        return resultado;
    }
    
    public static String Form_Rango()
    {
        String encabezado = Division() + Espacio() + "Fórmula para determinar el rango" + Espacio();
        
        String form_r = "L = Pmin * r ^ (n - 1)" + Doble_Espacio();
        
        String donde = "donde: " + Espacio();
        String L = "L: Limite superior n-ésimo" + Espacio();
        String Pmin = "Pmin: Puntaje mínimo" + Espacio();
        String r = "r: Razón de crecimiento" + Espacio();
        String n = "n: Niveles salariales" + Espacio();
        
        String res = encabezado + form_r + donde + L + Pmin + r + n + Division() + Espacio();
        
        return res;
    }
    
    public static String Determinar_Rango(int n, double Pmin, double r, double L)
    {
        String res = "L = " + String.valueOf(nf.format(Pmin)) + " * " + String.valueOf(nf.format(r)) + " ^ (" + String.valueOf(n) + " - 1) = " + String.valueOf(nf.format(L)) + Espacio();
        
        return res;
    }
    
    // Métodos que sirven como herramientas de apoyo
    
    private static String Division()
    {
        return "\n---------------------------------------------------------------------------------------------------------\n---------------------------------------------------------------------------------------------------------\n";
    }
    
    private static String Espacio()
    {
        return "\n";
    }
    
    private static String Doble_Espacio()
    {
        return "\n\n";
    }
}
