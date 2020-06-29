/*
 * Demo.java
 *
 * Created on 29 de diciembre de 2007, 12:21 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jpositionrating;

/**
 *
 * @author Jose Francisco
 */

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Demo
{
	static int numero = 0;
	static DataInputStream leer;
	static DataOutputStream escribir;
	static boolean resp = true; // El programa expira cuando sea falso
	static File Fichero;

	public static boolean Demostrar()
	{
		Fichero = new File("C:\\Expiracion.txt");
		
		leerFichero();
		escribirFichero();
		
		return condicionar();
	}
	
	public static void leerFichero()
	{
		// abrir archivo
		try
		{
			leer = new DataInputStream(new FileInputStream(Fichero));
			int num;
		
			num = leer.readInt();
			numero = num;
			leer.close();
		}
			
		// procesa la excepcion al abrir el archivo
		catch(Exception e)
		{
			//JOptionPane.showMessageDialog(this,"Error al abrir el archivo","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
		
	public static void escribirFichero()
	{
		try
		{
			escribir = new DataOutputStream(new FileOutputStream(Fichero));
			int num = numero;
			
			num = num + 1;
			
			escribir.writeInt(num);
			escribir.close();
		}
		// mostrar mensaje de error si no se puede abrir el archivo
		catch(IOException ioException)
		{
			//JOptionPane.showMessageDialog(this,"Error al abrir el archivo","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static boolean condicionar()
	{
		if(numero >= 3)
			return false;
		else
			return true;
	}
}


				
		