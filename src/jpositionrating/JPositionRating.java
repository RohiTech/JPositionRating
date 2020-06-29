/*
 * JPositionRating.java
 *
 * Created on 17 de noviembre de 2007, 07:22 PM
 */

package jpositionrating;

import java.awt.ScrollPane;
import javax.swing.event.*;

import java.io.*;
import java.util.*;

import javax.swing.table.*;

import java.awt.print.*;
import java.text.*;

import java.lang.Object;
import java.awt.Component;
import java.awt.Container;
import javax.swing.text.JTextComponent;

import javax.swing.*;

import jxl.write.WritableWorkbook;
import jxl.write.WritableSheet;
import jxl.write.Label;
import jxl.Workbook;
import jxl.write.WriteException;
import jxl.write.biff.WritableWorkbookImpl;
import org.omg.SendingContext.RunTimeOperations;

import java.awt.*;

/**
 *
 * @author  José Francisco
 */
public class JPositionRating extends javax.swing.JFrame {
    
    String nombre = null;
    double minimo = 0;
    int puesto = 0;
    
    Vector nuevafila;
    DefaultTableModel modelotabla;
    
    int max = 0, min = 0, g = 0;
    
    int Habilidad[] = new int[5];
    int Esfuerzo[] = new int[5];
    int Responsabilidad[] = new int[5];
    int Condicion[] = new int[5];
        
    // Habilidad
    int Educacion[] = new int[5];
    int Experiencia[] = new int[5];
    int Iniciativa[] = new int[5];
    
    // Esfuerzo
    int Fisico[] = new int[5];
    int Mental[] = new int[5];
    
    // Responsabilidad
    int Maquinaria[] = new int[5];
    int Materiales[] = new int[5];
    int Otros[] = new int[5];
    
    // Condiciones de trabajo
    int Ambiente[] = new int[5];
    int Riesgo[] = new int[5];
    
    int P[] = new int[10];
    int puntaje = 0;
    
    static String noborrar = null;
    
    // Demo
    
    static int numero = 0;
    static DataInputStream leer3;
    static DataOutputStream escribir3;
    static boolean resp = true; // El programa expira cuando sea falso
    static File Fichero;
    
    // Ficheros
    File name, modelo;
    DataOutputStream escribir;
    DataInputStream leer;
    File f;
    RandomAccessFile output, input;
    
    // Enviar a excel
    static WritableWorkbook  workbook = null;
    static WritableSheet sheet = null;
    static Label label = null;
    
    // Generar Operaciones
    JDescripcion d = new JDescripcion();
    String G = "", C = "", S = "", L = "", R = "", Rang = "";
    
    /** Creates new form JPositionRating */
    public JPositionRating() 
    {
        initComponents();
        jLabel28.setText("Managua, Nicaragua");
        jRadioButtonMenuItem1.setSelected(true);
        inicio();
        setVisible(true);
    }
    
    public void inicio()
    {
        // Evaluación de la Conserje
        jTextField1.setText("Conserje");
        jTextField2.setText("1200.00");
        jTextField3.setText("10");
        
        // Habilidad
        jTextField5.setText("15");
        jTextField6.setText("15");
        jTextField7.setText("10");
        
        // Esfuerzo
        jTextField9.setText("15");
        jTextField10.setText("10");
        
        // Responsabilidad
        jTextField12.setText("5");
        jTextField13.setText("5");
        jTextField14.setText("5");
        
        // Condiciones de trabajo
        jTextField16.setText("8");
        jTextField17.setText("12");
        
        jButton5.setText("2");
        jButton6.setText("4");
        jButton8.setText("4");
        jButton9.setText("4");
        jButton10.setText("3");
        jButton11.setText("4");
        jButton12.setText("5");
        
        jTextField22.setText("");
        
        this.Verificar_Radio1();
        
        Aritmetico();
    }
    
    public void Limpiar_Tabla()
    {
        Limpiar_Tabla2();
        Limpiar_Peso();
        Limpiar_Grado();
        
        jTextField19.setText("");
        jTextField20.setText("");
        jTextField21.setText("");
                
        jTabbedPane1.setSelectedIndex(0);
        jTextField1.requestFocus();
    }
    
    public void Limpiar_Tabla2()
    {
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nivel Salarial", "Rango", "Coeficiente Salarial N", "Salario Mensual N"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    public void Limpiar_Peso()
    {
        jTable1.setValueAt("",0,1);
        jTable1.setValueAt("",1,1);
        jTable1.setValueAt("",2,1);
        jTable1.setValueAt("",3,1);
        jTable1.setValueAt("",4,1);
        jTable1.setValueAt("",5,1);
        jTable1.setValueAt("",6,1);
        jTable1.setValueAt("",7,1);
        jTable1.setValueAt("",8,1);
        jTable1.setValueAt("",9,1);
        jTable1.setValueAt("",10,1);
        jTable1.setValueAt("",11,1);
        jTable1.setValueAt("",12,1);
        jTable1.setValueAt("",13,1);
        jTable1.setValueAt("",14,1);
        
        jTable1.setValueAt("",0,2);
        jTable1.setValueAt("",0,3);
        jTable1.setValueAt("",0,4);
        jTable1.setValueAt("",0,5);
        
        jTable1.setValueAt("",4,2);
        jTable1.setValueAt("",4,3);
        jTable1.setValueAt("",4,4);
        jTable1.setValueAt("",4,5);
        
        jTable1.setValueAt("",7,2);
        jTable1.setValueAt("",7,3);
        jTable1.setValueAt("",7,4);
        jTable1.setValueAt("",7,5);
        
        jTable1.setValueAt("",11,2);
        jTable1.setValueAt("",11,3);
        jTable1.setValueAt("",11,4);
        jTable1.setValueAt("",11,5);
    }
    
    public void Limpiar_Grado()
    {
        int col = 1;
        int grado = 1;
        
        for(grado = 1; grado <= 5; grado++)
        {
            col++;
            //jTable1.setValueAt(Integer.parseInt(jTextField4.getText()) * grado,0,col);
            jTable1.setValueAt("",1,col);
            jTable1.setValueAt("",2,col);
            jTable1.setValueAt("",3,col);
            //jTable1.setValueAt(Integer.parseInt(jTextField8.getText()) * grado,4,col);
            jTable1.setValueAt("",5,col);
            jTable1.setValueAt("",6,col);
            //jTable1.setValueAt(Integer.parseInt(jTextField11.getText()) * grado,7,col);
            jTable1.setValueAt("",8,col);
            jTable1.setValueAt("",9,col);
            jTable1.setValueAt("",10,col);
            //jTable1.setValueAt(Integer.parseInt(jTextField15.getText()) * grado,11,col);
            jTable1.setValueAt("",12,col);
            jTable1.setValueAt("",13,col);
            jTable1.setValueAt("",14,col);
        }
    }
        
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JPositionRating 2008");
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del puesto a evaluar"));
        jPanel4.setLayout(null);

        jLabel1.setText("Nombre del puesto:");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(30, 34, 110, 20);

        jTextField1.setToolTipText("Digite el nombre del puesto");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jPanel4.add(jTextField1);
        jTextField1.setBounds(150, 30, 150, 20);

        jLabel2.setText("Salario mínimo:");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(30, 80, 110, 10);

        jTextField2.setToolTipText("Digite el salario minimo segun el puesto");
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        jPanel4.add(jTextField2);
        jTextField2.setBounds(150, 70, 80, 20);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(20, 20, 330, 110);

        jLabel3.setText("Cantidad de puestos en la empresa:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(50, 140, 220, 30);

        jTextField3.setToolTipText("Digite la cantidad de puestos que contiene la empresa");
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField3);
        jTextField3.setBounds(280, 140, 70, 20);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignación de puntos"));
        jPanel5.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 12));
        jLabel4.setText("Factores");
        jPanel5.add(jLabel4);
        jLabel4.setBounds(30, 15, 70, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12));
        jLabel5.setText("Peso Porcentual");
        jPanel5.add(jLabel5);
        jLabel5.setBounds(190, 15, 100, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 12));
        jLabel6.setText("Grados");
        jPanel5.add(jLabel6);
        jLabel6.setBounds(320, 15, 42, 30);

        jLabel7.setText("Habilidad =");
        jPanel5.add(jLabel7);
        jLabel7.setBounds(30, 60, 70, 14);

        jLabel8.setText("Educación =");
        jPanel5.add(jLabel8);
        jLabel8.setBounds(50, 90, 70, 14);

        jLabel9.setText("Experiencia =");
        jPanel5.add(jLabel9);
        jLabel9.setBounds(50, 120, 80, 14);

        jLabel10.setText("Iniciativa e Ingenio =");
        jPanel5.add(jLabel10);
        jLabel10.setBounds(50, 150, 150, 14);

        jLabel11.setText("Esfuerzo =");
        jPanel5.add(jLabel11);
        jLabel11.setBounds(30, 180, 60, 14);

        jLabel12.setText("Físico =");
        jPanel5.add(jLabel12);
        jLabel12.setBounds(50, 210, 60, 14);

        jLabel13.setText("Mental =");
        jPanel5.add(jLabel13);
        jLabel13.setBounds(50, 240, 60, 14);

        jLabel14.setText("Responsabilidad =");
        jPanel5.add(jLabel14);
        jLabel14.setBounds(30, 270, 140, 14);

        jLabel15.setText("Materiales =");
        jPanel5.add(jLabel15);
        jLabel15.setBounds(50, 300, 120, 14);

        jLabel16.setText("Maquinarias y equipos =");
        jPanel5.add(jLabel16);
        jLabel16.setBounds(50, 330, 160, 14);

        jLabel17.setText("Otros =");
        jPanel5.add(jLabel17);
        jLabel17.setBounds(50, 360, 110, 14);

        jLabel18.setText("Condiciones de trabajo =");
        jPanel5.add(jLabel18);
        jLabel18.setBounds(30, 390, 150, 14);

        jLabel19.setText("Ambiente de trabajo =");
        jPanel5.add(jLabel19);
        jLabel19.setBounds(50, 420, 150, 14);

        jLabel20.setText("Riesgo =");
        jPanel5.add(jLabel20);
        jLabel20.setBounds(50, 450, 60, 14);

        jTextField4.setEditable(false);
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField4);
        jTextField4.setBounds(190, 50, 60, 20);

        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField5);
        jTextField5.setBounds(240, 80, 60, 20);

        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField6);
        jTextField6.setBounds(240, 110, 60, 20);

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField7KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField7);
        jTextField7.setBounds(240, 140, 60, 20);

        jTextField8.setEditable(false);
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField8KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField8);
        jTextField8.setBounds(190, 170, 60, 20);

        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField9KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField9);
        jTextField9.setBounds(240, 200, 60, 20);

        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField10KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField10KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField10);
        jTextField10.setBounds(240, 230, 60, 20);

        jTextField11.setEditable(false);
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField11KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField11KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField11);
        jTextField11.setBounds(190, 260, 60, 20);

        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField12KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField12KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField12);
        jTextField12.setBounds(240, 290, 60, 20);

        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField13KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField13KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField13);
        jTextField13.setBounds(240, 320, 60, 20);

        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField14KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField14KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField14);
        jTextField14.setBounds(240, 350, 60, 20);

        jTextField15.setEditable(false);
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField15KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField15KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField15);
        jTextField15.setBounds(190, 380, 60, 20);

        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField16KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField16KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField16);
        jTextField16.setBounds(240, 410, 60, 20);

        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField17KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField17KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField17);
        jTextField17.setBounds(240, 440, 60, 20);

        jButton3.setText("1");
        jButton3.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jButton3MouseWheelMoved(evt);
            }
        });
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton3MouseReleased(evt);
            }
        });
        jButton3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jButton3MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton3MouseMoved(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3);
        jButton3.setBounds(320, 80, 50, 23);

        jButton4.setText("1");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4);
        jButton4.setBounds(320, 110, 50, 23);

        jButton5.setText("1");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5);
        jButton5.setBounds(320, 140, 50, 23);

        jButton6.setText("1");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6);
        jButton6.setBounds(320, 200, 50, 23);

        jButton7.setText("1");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7);
        jButton7.setBounds(320, 230, 50, 23);

        jButton8.setText("1");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8);
        jButton8.setBounds(320, 290, 50, 23);

        jButton9.setText("1");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton9MouseEntered(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton9);
        jButton9.setBounds(320, 320, 50, 23);

        jButton10.setText("1");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton10MouseEntered(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton10);
        jButton10.setBounds(320, 350, 50, 23);

        jButton11.setText("1");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton11MouseEntered(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton11);
        jButton11.setBounds(320, 410, 50, 23);

        jButton12.setText("1");
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton12MouseEntered(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton12);
        jButton12.setBounds(320, 440, 50, 23);

        jButton13.setText("1");
        jButton13.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jButton13MouseWheelMoved(evt);
            }
        });
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton13MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton13MouseReleased(evt);
            }
        });
        jButton13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jButton13MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton13MouseMoved(evt);
            }
        });
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton13);
        jButton13.setBounds(280, 50, 50, 23);

        jButton14.setText("1");
        jButton14.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jButton14MouseWheelMoved(evt);
            }
        });
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton14MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton14MouseReleased(evt);
            }
        });
        jButton14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jButton14MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton14MouseMoved(evt);
            }
        });
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton14);
        jButton14.setBounds(280, 170, 50, 23);

        jButton15.setText("1");
        jButton15.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jButton15MouseWheelMoved(evt);
            }
        });
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton15MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton15MouseReleased(evt);
            }
        });
        jButton15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jButton15MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton15MouseMoved(evt);
            }
        });
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton15);
        jButton15.setBounds(280, 260, 50, 23);

        jButton16.setText("1");
        jButton16.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jButton16MouseWheelMoved(evt);
            }
        });
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton16MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton16MouseReleased(evt);
            }
        });
        jButton16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jButton16MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton16MouseMoved(evt);
            }
        });
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton16);
        jButton16.setBounds(280, 380, 50, 23);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(390, 20, 380, 480);

        jLabel21.setText("Total =");
        jPanel1.add(jLabel21);
        jLabel21.setBounds(550, 510, 80, 14);

        jTextField18.setEditable(false);
        jPanel1.add(jTextField18);
        jTextField18.setBounds(710, 510, 60, 20);

        jButton1.setText("Aceptar");
        jButton1.setToolTipText("Clic para calcular el salario justo del puesto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(240, 180, 110, 23);

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Puesto.gif"))); // NOI18N
        jPanel1.add(jLabel27);
        jLabel27.setBounds(90, 250, 210, 200);

        jLabel28.setFont(new java.awt.Font("Old English Text MT", 1, 18));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel28);
        jLabel28.setBounds(40, 470, 310, 40);

        jTabbedPane1.addTab("Obtener Datos", jPanel1);

        jPanel2.setLayout(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Habilidad", null, null, null, null, null, null},
                {"- Educacion", null, null, null, null, null, null},
                {"- Experiencia", null, null, null, null, null, null},
                {"- Iniciativa e Ingenio", null, null, null, null, null, null},
                {"Esfuerzo", null, null, null, null, null, null},
                {"- Físico", null, null, null, null, null, null},
                {"- Mental", null, null, null, null, null, null},
                {"Responsabilidad", null, null, null, null, null, null},
                {"- Materiales", null, null, null, null, null, null},
                {"- Maquinarias y Equipos", null, null, null, null, null, null},
                {"- Otros", null, null, null, null, null, null},
                {"Condiciones de trabajo", null, null, null, null, null, null},
                {"- Ambiente de trabajo", null, null, null, null, null, null},
                {"- Riesgo", null, null, null, null, null, null},
                {"Total", null, null, null, null, null, null}
            },
            new String [] {
                "Factores / Grados", "Peso Porcentual", "1er", "2do", "3er", "4to", "5to"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 10, 830, 330);

        jLabel22.setText("Gradiente de crecimiento:");
        jPanel2.add(jLabel22);
        jLabel22.setBounds(570, 370, 180, 14);

        jTextField19.setEditable(false);
        jPanel2.add(jTextField19);
        jTextField19.setBounds(710, 370, 120, 20);

        jLabel23.setText("Puntaje:");
        jPanel2.add(jLabel23);
        jLabel23.setBounds(650, 410, 130, 14);

        jTextField20.setEditable(false);
        jPanel2.add(jTextField20);
        jTextField20.setBounds(710, 410, 120, 20);

        jLabel26.setText("     Razón de crecimiento:");
        jPanel2.add(jLabel26);
        jLabel26.setBounds(570, 450, 180, 14);

        jTextField22.setEditable(false);
        jPanel2.add(jTextField22);
        jTextField22.setBounds(710, 450, 120, 20);

        jButton2.setText("Imprimir Tabla");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(580, 490, 120, 23);

        jButton18.setText("Exportar a Excel");
        jButton18.setEnabled(false);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton18);
        jButton18.setBounds(709, 490, 120, 23);

        jTabbedPane1.addTab("Tabla de asignación de punto", jPanel2);

        jPanel3.setLayout(null);

        jScrollPane2.setBorder(null);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nivel Salarial", "Rango", "Coeficiente Salarial N", "Salario Mensual N"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setEnabled(false);
        jScrollPane2.setViewportView(jTable2);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(20, 20, 810, 480);

        jLabel24.setText("Salario asignado al puesto de trabajo:");
        jPanel3.add(jLabel24);
        jLabel24.setBounds(460, 510, 240, 40);

        jTextField21.setEditable(false);
        jPanel3.add(jTextField21);
        jTextField21.setBounds(710, 520, 120, 20);

        jButton17.setText("Imprimir Tabla");
        jButton17.setEnabled(false);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton17);
        jButton17.setBounds(20, 520, 130, 23);

        jButton19.setText("Exportar a Excel");
        jButton19.setEnabled(false);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton19);
        jButton19.setBounds(160, 520, 120, 23);

        jTabbedPane1.addTab("Tabla Salarial", jPanel3);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 60, 860, 580);

        jProgressBar1.setIndeterminate(true);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(0, 640, 860, 14);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Factores / Grados", "Peso Porcentual", "1er", "2do", "3er", "4to", "5to"}
            },
            new String [] {
                "Factores / Grados", "Peso Porcentual", "1er", "2do", "3er", "4to", "5to"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(10, 710, 20, 20);

        jScrollPane4.setBorder(null);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Nivel Salarial", "Rango", "Coeficiente Salarial N", "Salario Mensual N"}
            },
            new String [] {
                "Nivel Salarial", "Rango", "Coeficiente Salarial N", "Salario Mensual N"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(40, 710, 20, 20);

        jToolBar1.setRollover(true);

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/New.png"))); // NOI18N
        jButton20.setToolTipText("Nuevo");
        jButton20.setFocusable(false);
        jButton20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton20.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton20);

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Open.png"))); // NOI18N
        jButton21.setToolTipText("Abrir");
        jButton21.setFocusable(false);
        jButton21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton21);

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Save.png"))); // NOI18N
        jButton22.setToolTipText("Guardar");
        jButton22.setFocusable(false);
        jButton22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton22.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton22);

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Thunder_Bolt.png"))); // NOI18N
        jButton23.setToolTipText("Ejecutar");
        jButton23.setFocusable(false);
        jButton23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton23.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton23);

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Aritmetica.png"))); // NOI18N
        jButton29.setToolTipText("Regresión Aritmética");
        jButton29.setFocusable(false);
        jButton29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton29.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton29);

        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Geometrica.png"))); // NOI18N
        jButton30.setToolTipText("Regresión Geométrica");
        jButton30.setFocusable(false);
        jButton30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton30.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton30);

        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Excel.png"))); // NOI18N
        jButton31.setToolTipText("Enviar a excel");
        jButton31.setEnabled(false);
        jButton31.setFocusable(false);
        jButton31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton31.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton31);

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Report.png"))); // NOI18N
        jButton24.setToolTipText("Generar operaciones");
        jButton24.setFocusable(false);
        jButton24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton24.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton24);

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Printer.png"))); // NOI18N
        jButton25.setToolTipText("Imprimir");
        jButton25.setEnabled(false);
        jButton25.setFocusable(false);
        jButton25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton25.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton25);

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Calculator.png"))); // NOI18N
        jButton26.setToolTipText("Abrir calculadora");
        jButton26.setFocusable(false);
        jButton26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton26.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton26);

        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/PDF.png"))); // NOI18N
        jButton32.setToolTipText("Abrir PDF de las leyes de salario minimo en Nicaragua");
        jButton32.setFocusable(false);
        jButton32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton32.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton32);

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Help.png"))); // NOI18N
        jButton27.setToolTipText("Contenido de ayuda");
        jButton27.setFocusable(false);
        jButton27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton27.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton27);

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancel.png"))); // NOI18N
        jButton28.setToolTipText("Salir");
        jButton28.setFocusable(false);
        jButton28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton28.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton28);

        getContentPane().add(jToolBar1);
        jToolBar1.setBounds(0, 0, 840, 60);

        jMenu1.setText("Archivo");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Abrir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Guardar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Cerrar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Regresión");

        jRadioButtonMenuItem1.setText("Aritmética");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jRadioButtonMenuItem1);

        jRadioButtonMenuItem2.setText("Geométrica");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jRadioButtonMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Generar");
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });

        jMenuItem9.setText("Operaciones");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);

        jMenuBar1.add(jMenu5);

        jMenu3.setText("Herramientas");

        jMenuItem5.setText("Calculadora");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem11.setText("Foxit Reader");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Ayuda");

        jMenuItem10.setText("Salario Minimo");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem6.setText("Contenido");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem7.setText("Autor");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem8.setText("Acerca de...");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-858)/2, (screenSize.height-705)/2, 858, 705);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
// TODO: Agrege su codigo aqui:
        Exportar_Accion2();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
// TODO: Agrege su codigo aqui:
        Exportar_Accion1();
    }//GEN-LAST:event_jButton18ActionPerformed

    public void Exportar_Accion1()
    {
        try
        {  
            CrearExcel("Libro1.xls","Hoja1",jTable1.getModel(),jTable3.getModel());
            Runtime.getRuntime().exec("AbrirExcel.bat");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error al exportar","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void Exportar_Accion2()
    {
        try
        {  
            CrearExcel("Libro2.xls","Hoja1",jTable2.getModel(),jTable4.getModel());
            Runtime.getRuntime().exec("AbrirExcel2.bat");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error al exportar","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public static void CrearExcel(String ruta,String nombreHoja,TableModel modeloTabla,TableModel modeloTabla2){
        
    try
    {
        workbook = Workbook.createWorkbook(new File(ruta));
        sheet = workbook.createSheet(nombreHoja,0);
        
        for(int i=0;i<modeloTabla2.getRowCount();i++)
        {
            for(int j=0;j<modeloTabla2.getColumnCount();j++)
            {
                Object x = modeloTabla2.getValueAt(i,j);
                    
                if(x!=null)
                    label = new Label(j,i,x.toString());
                else
                    label = new Label(j,i,"");

                sheet.addCell(label);
            }
        }
        
        for(int i=0;i<modeloTabla.getRowCount();i++)
        {
            for(int j=0;j<modeloTabla.getColumnCount();j++)
            {
                Object x = modeloTabla.getValueAt(i,j);
                    
                if(x!=null)
                    label = new Label(j,i + 1,x.toString());
                else
                    label = new Label(j,i + 1,"");

                sheet.addCell(label);
            }
        }
        
        workbook.write(); 
        workbook.close();
    }
    catch(IOException e){
        JOptionPane.showMessageDialog(null,e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    catch(WriteException e){
        JOptionPane.showMessageDialog(null,e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }
    
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
// TODO: Agrege su codigo aqui:
        Imprimir_Tabla_Salarial();
    }//GEN-LAST:event_jButton17ActionPerformed

    public void Imprimir_Tabla_Salarial()
    {
        try
        {
            MessageFormat headerFormat = new MessageFormat("Page {0}");
            MessageFormat footerFormat = new MessageFormat("- {0} -");
            jTable2.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
        }
        catch(PrinterException pe)
	{
            System.err.println("Error printing: " + pe.getMessage());
	}
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO: Agrege su codigo aqui:
        Imprimir_Asignacion_Punto();
    }//GEN-LAST:event_jButton2ActionPerformed

    public void Imprimir_Asignacion_Punto()
    {
        try
        {
            MessageFormat headerFormat = new MessageFormat("Page {0}");
            MessageFormat footerFormat = new MessageFormat("- {0} -");
            jTable1.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
        }
        catch(PrinterException pe)
	{
            System.err.println("Error printing: " + pe.getMessage());
	}
    }
    
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
// TODO: Agrege su codigo aqui:
        Acerca acerca = new Acerca();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
// TODO: Agrege su codigo aqui:
        Autor autor = new Autor();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
// TODO: Agrege su codigo aqui:
        Contenido contenido = new Contenido();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
// TODO: Agrege su codigo aqui:
        Llamar_Calculadora();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    public void Llamar_Calculadora()
    {
        try
        {
    		Runtime rt = Runtime.getRuntime();
    		Process p = rt.exec("Calc.exe");
    		p.waitFor();
    	}
    	catch(Exception e)
    	{
    	}
    }
    
    public void Llamar_Salario_Minimo()
    {
        try
        {
    		Runtime rt = Runtime.getRuntime();
    		Process p = rt.exec("sal_min.bat");
    		p.waitFor();
    	}
    	catch(Exception e)
    	{
    	}
    }
    
    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            if(jRadioButtonMenuItem1.isSelected() == true)
                Aritmetico();
            else
                Geometrico();
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void jTextField15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jButton1.requestFocus();
        }
    }//GEN-LAST:event_jTextField15KeyPressed

    private void jTextField11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField15.requestFocus();
        }
    }//GEN-LAST:event_jTextField11KeyPressed

    private void jTextField8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField11.requestFocus();
        }
    }//GEN-LAST:event_jTextField8KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField8.requestFocus();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField17KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyTyped
// TODO: Agrege su codigo aqui:
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField17KeyTyped

    private void jTextField16KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyTyped
// TODO: Agrege su codigo aqui:
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField16KeyTyped

    private void jTextField15KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyTyped
// TODO: Agrege su codigo aqui:
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField15KeyTyped

    private void jTextField14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyTyped
// TODO: Agrege su codigo aqui:
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField14KeyTyped

    private void jTextField13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyTyped
// TODO: Agrege su codigo aqui:
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField13KeyTyped

    private void jTextField12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyTyped
// TODO: Agrege su codigo aqui:
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField12KeyTyped

    private void jTextField11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyTyped
// TODO: Agrege su codigo aqui:
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField11KeyTyped

    private void jTextField10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyTyped
// TODO: Agrege su codigo aqui:
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField10KeyTyped

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
// TODO: Agrege su codigo aqui:
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField9KeyTyped

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
// TODO: Agrege su codigo aqui:
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
// TODO: Agrege su codigo aqui:
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
// TODO: Agrege su codigo aqui:
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
// TODO: Agrege su codigo aqui:
        
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
// TODO: Agrege su codigo aqui:
        
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
// TODO: Agrege su codigo aqui:
        
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número enteros.

	if(!(Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
// TODO: Agrege su codigo aqui:
        
        char c= evt.getKeyChar();
        
        // Validación para que solo entren número decimales.
        
        if(!((Character.isDigit(c)) || (c==evt.VK_BACK_SPACE) || (c == evt.VK_DELETE) || (c==evt.VK_PERIOD)))
            evt.consume();
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
// TODO: Agrege su codigo aqui:
        
        char c= evt.getKeyChar(); 

        // Validación para que solo entren caracteres.
        
	if((Character.isDigit(c) || c==evt.VK_BACK_SPACE)|| (c == evt.VK_DELETE)|| (c==evt.VK_PERIOD))
	evt.consume();
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton16.setText(String.valueOf(Integer.parseInt(jButton16.getText()) + 1));
        
        if(Integer.parseInt(jButton16.getText()) > 5)
            jButton16.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton16.getText()) > 4)
                jButton16.setText("1");
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton16MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseMoved
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton16MouseMoved

    private void jButton16MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseDragged
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton16MouseDragged

    private void jButton16MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseReleased
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton16MouseReleased

    private void jButton16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseExited
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton16MouseExited

    private void jButton16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton16.getText());
        
        switch(grado)
        {
            case 1:
                    jButton16.setToolTipText("Ambiente de trabajo agradable con excelente ventilación, excelente ilumminación adecuado al tipo de puesto, sin ruido violentos debajo de 70 db, \n Ambiente de trabajo normal. La posibilidad de que ocurra un accidente es eventual");
                    break;
            case 2:
                    jButton16.setToolTipText("Ambiente de trabajo regular, un poco caluroso (30°C), con buena iluminación y ruido un poco molesto (ligeramente superior a los 70 db), \n Pocas veces se expone a accidentes o molestias de menor importancia, que pueden producir incapacidades temporales no mayor de tres días");
                    break;
            case 3:
                    jButton16.setToolTipText("Ambiente de trabajo regular, un poco caluroso (30°C), con abanicos, buena iluminación, pero ruidos intensos pero eventual (85 db), \n Expuestos a accidentes que pueden producir incapacidades temporales mayores de tres días");
                    break;
            case 4:
                    jButton16.setToolTipText("Ambiente de trabajo intensamente caluroso, con ventilación, iluminación adecuada al tipo de trabajo, ruidos intensos hasta de 90 db, \n Expuestos a riesgos que pueden incapacitarlo por un período no mayor de treinta días");
                    break;
            case 5:
                    jButton16.setToolTipText("Ambiente de trabajo excesivamente caluroso, sin abanico, poca ventilación, ruido intensos e intermitentes hasta de 90 db. E iluminación aceptable, \n Expuestos a riesgos que pueden ocasionar daños permanentes en el organismo o la muerte incluso");
                    break;
        }
    }//GEN-LAST:event_jButton16MouseEntered

    private void jButton16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseClicked
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton16MouseClicked

    private void jButton16MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jButton16MouseWheelMoved
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton16MouseWheelMoved

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton15.setText(String.valueOf(Integer.parseInt(jButton15.getText()) + 1));
        
        if(Integer.parseInt(jButton15.getText()) > 5)
            jButton15.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton15.getText()) > 4)
                jButton15.setText("1");
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton15MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseMoved
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton15MouseMoved

    private void jButton15MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseDragged
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton15MouseDragged

    private void jButton15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseReleased
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton15MouseReleased

    private void jButton15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseExited
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton15MouseExited

    private void jButton15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton15.getText());
        
        switch(grado)
        {
            case 1:
                    jButton15.setToolTipText("El valor de los materiales con que se trabaja tiene un precio hasta de US $ 10,\n El daño a la maquinaria, instrumental y equipos es muy probable igual que la pérdida de los mismos,\n Responsable únicamente de su propio trabajo");
                    break;
            case 2:
                    jButton15.setToolTipText("El valor del material con que se trabaja está entre US $ 10 y US $ 100,\n El daño a la maquinaria, instrumental y equipos es poco probable, igual que la pérdida de los mismos,\n Influye de manera indirecta en el trabajo de otras personas de un departamento o de otros departamentos");
                    break;
            case 3:
                    jButton15.setToolTipText("El valor del material con que se trabaja está entre US $ 100 y US $ 1000,\n El daño a la maquinaria, instrumental y equipos es fácil de causarse,\n Dirige o supervisa el trabajo de una o dos personas");
                    break;
            case 4:
                    jButton15.setToolTipText("El valor del material con que se trabaja está entre US $ 1000 y US $ 5000,\n,Muy fácilmente puede causarse daños a la maquinaria, instrumental y equipos,\n Dirige o supervisa el trabajo de tres o seis personas");
                    break;
            case 5:
                    jButton15.setToolTipText("El valor del material con que se trabaja tiene un precio mayor de US $ 5000,\n,Muy frecuentemente puede causarse daño a la maquinaria, instrumental y equipos,\n Dirige o supervisa el trabajo de más de seis personas");
                    break;
        } 
    }//GEN-LAST:event_jButton15MouseEntered

    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseClicked
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton15MouseClicked

    private void jButton15MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jButton15MouseWheelMoved
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton15MouseWheelMoved

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton14.setText(String.valueOf(Integer.parseInt(jButton14.getText()) + 1));
        
        if(Integer.parseInt(jButton14.getText()) > 5)
            jButton14.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton14.getText()) > 4)
                jButton14.setText("1");
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton14MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseMoved
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton14MouseMoved

    private void jButton14MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseDragged
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton14MouseDragged

    private void jButton14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseReleased
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton14MouseReleased

    private void jButton14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseExited
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton14MouseExited

    private void jButton14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton14.getText());
        
        switch(grado)
        {
            case 1:
                    jButton14.setToolTipText("Esfuerzo físico requerido para realizar exclusivamente trabajos manuales de oficina, Atención normal que debe ponerse en todo trabajo");
                    break;
            case 2:
                    jButton14.setToolTipText("Esfuerzo físico mínimo desarrollado por trabajadores especializados que operan máquina totalmente automática y trabajos de oficina ligeros en su puesto de trabajo, Atención intensa y sostenida sólo durante período cortos; el resto del tiempo sólo exige atención refleja normal");
                    break;
            case 3:
                    jButton14.setToolTipText("Esfuerzo físico requerido para manejar manualmente sus equipos y trabajos constantes de oficinas, Requiere atención intensa en forma regular, pero intermitente");
                    break;
            case 4:
                    jButton14.setToolTipText("Constante esfuerzo físico mediano como el requerido para cargar y descargar, Atención intensa constante y sostenida");
                    break;
            case 5:
                    jButton14.setToolTipText("Constante aplicación de gran esfuerzo físico, Requiere análisis y mucha concentración mental para tomar decisiones sumamente importantes en su puesto de trabajo");
                    break;
        }         
    }//GEN-LAST:event_jButton14MouseEntered

    private void jButton14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseClicked
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton14MouseClicked

    private void jButton14MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jButton14MouseWheelMoved
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton14MouseWheelMoved

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton13.setText(String.valueOf(Integer.parseInt(jButton13.getText()) + 1));
        
        if(Integer.parseInt(jButton13.getText()) > 5)
            jButton13.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton13.getText()) > 4)
                jButton13.setText("1");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton13MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseMoved
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton13MouseMoved

    private void jButton13MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseDragged
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton13MouseDragged

    private void jButton13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseReleased
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton13MouseReleased

    private void jButton13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseExited
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton13MouseExited

    private void jButton13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton13.getText());
        
        switch(grado)
        {
            case 1:
                    jButton13.setToolTipText("Haber cursado la primaria, Experiencia hasta de tres meses, Requiere solamente habilidad para ejecutar las órdenes recibidas");
                    break;
            case 2:
                    jButton13.setToolTipText("Haber cursado ciclo básico y estudios técnicos medios, Experiencia hasta de seis meses, Requiere ciertas interpretación de las órdenes recibidas al aplicarla e iniciativa para resolverla");
                    break;
            case 3:
                    jButton13.setToolTipText("Haber cursado la secundaria y estudios técnicos medios, Experiencia hasta de un año, Requiere criterio e iniciativa para resolverlo constantemente (el 50%) o más, los problemas sencillos que se presentan en el puesto");
                    break;
            case 4:
                    jButton13.setToolTipText("Haber realizado estudios Universitarios, Experiencia hasta de tres años, Requiere criterio e iniciativa para resolverlo eventualmente los problemas díficiles que se presentan en el puesto de trabajo");
                    break;
            case 5:
                    jButton13.setToolTipText("Haber realizado estudios de especializaciones: Postgrado, maestría o estudios superiores a estos, Experiencia hasta de más de tres años, Requiere criterio e iniciativa para resolver constantemente problemas díficiles y de gran trascendencia en su trabajo");
                    break;
        }
    }//GEN-LAST:event_jButton13MouseEntered

    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseClicked
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton13MouseClicked

    private void jButton13MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jButton13MouseWheelMoved
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton13MouseWheelMoved

    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem2ActionPerformed
// TODO: Agrege su codigo aqui:
        Radio2();
    }//GEN-LAST:event_jRadioButtonMenuItem2ActionPerformed

    public void Radio2()
    {
        Verificar_Radio2();
        Limpiar();
        Completar();
    }
    
    public void Verificar_Radio2()
    {
        if(jRadioButtonMenuItem1.isSelected() == false && jRadioButtonMenuItem2.isSelected() == false)
            jRadioButtonMenuItem2.setSelected(true);
                
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            jRadioButtonMenuItem1.setSelected(false);
            Verificar();
            Modificar1();
        }
        else
        {
            Modificar2();
        }
    }
    
    public void Completar()
    {
        jTextField1.setText("Mensajero interno de oficina");
        jTextField2.setText("331.15");
        jTextField3.setText("10");
        
        jTextField4.setText("45");
        jTextField8.setText("30");
        jTextField11.setText("15");
        jTextField15.setText("10");
        
        jButton13.setText("1");
        jButton14.setText("2");
        jButton15.setText("4");
        jButton16.setText("4");
        
        Geometrico();
    }
    
    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
// TODO: Agrege su codigo aqui:
        Radio1();
    }//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed
    
    public void Radio1()
    {
        Verificar_Radio1();
        
        Limpiar_Campo();
        inicio();
    }
    
    public void Verificar_Radio1()
    {
        if(jRadioButtonMenuItem1.isSelected() == false && jRadioButtonMenuItem2.isSelected() == false)
            jRadioButtonMenuItem1.setSelected(true);
        
        if(jRadioButtonMenuItem1.isSelected() == true)
        {
            jRadioButtonMenuItem2.setSelected(false);
            Modificar2();
        }
        else
        {
            Modificar1();
        }
    }
    
    public void Verificar()
    {
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
           if(jButton3.getText().equals("5"))
               jButton3.setText("4");
           
           if(jButton4.getText().equals("5"))
               jButton4.setText("4");
           
           if(jButton5.getText().equals("5"))
               jButton5.setText("4");
           
           if(jButton6.getText().equals("5"))
               jButton6.setText("4");
           
           if(jButton7.getText().equals("5"))
               jButton7.setText("4");
           
           if(jButton8.getText().equals("5"))
               jButton8.setText("4");
           
           if(jButton9.getText().equals("5"))
               jButton9.setText("4");
           
           if(jButton10.getText().equals("5"))
               jButton10.setText("4");
           
           if(jButton11.getText().equals("5"))
               jButton11.setText("4");
           
           if(jButton12.getText().equals("5"))
               jButton12.setText("4");
           
           if(jButton13.getText().equals("5"))
               jButton13.setText("4");
           
           if(jButton14.getText().equals("5"))
               jButton14.setText("4");
           
           if(jButton15.getText().equals("5"))
               jButton15.setText("4");
           
           if(jButton16.getText().equals("5"))
               jButton16.setText("4");
        }
    }
    
    public void Modificar1()
    {
        // Cuando la regresión es geométrica
        
        Deshabilitar();
        
        this.jTextField4.setEditable(true);
        this.jTextField8.setEditable(true);
        this.jTextField11.setEditable(true);
        this.jTextField15.setEditable(true);
        
        jLabel7.setVisible(true);
        jLabel11.setVisible(true);
        jLabel14.setVisible(true);
        jLabel18.setVisible(true);
                
        jTextField4.setVisible(true);
        jTextField8.setVisible(true);
        jTextField11.setVisible(true);
        jTextField15.setVisible(true);
        
        jButton13.setVisible(true);
        jButton14.setVisible(true);
        jButton15.setVisible(true);
        jButton16.setVisible(true);
        
        this.jLabel26.setVisible(true);
        this.jTextField22.setVisible(true);
    }
    
    public void Modificar2()
    {
        // Cuando la regresión es aritmética
        
        // En la regresión aritmética ningun componente se deshabilita porque se ocupan todos.
        // Excepto ocultar la razon de crecimiento porque esta regresion no la lleva.
        
        Habilitar();
        
        this.jTextField4.setEditable(false);
        this.jTextField8.setEditable(false);
        this.jTextField11.setEditable(false);
        this.jTextField15.setEditable(false);
                
        this.jLabel26.setVisible(false);
        this.jTextField22.setVisible(false);
        
        this.jButton13.setVisible(false);
        this.jButton14.setVisible(false);
        this.jButton15.setVisible(false);
        this.jButton16.setVisible(false);
    }
    
    public void Habilitar()
    {
        jLabel7.setVisible(true);
        jLabel8.setVisible(true);
        jLabel9.setVisible(true);
        jLabel10.setVisible(true);
        jLabel11.setVisible(true);
        jLabel12.setVisible(true);
        jLabel13.setVisible(true);
        jLabel14.setVisible(true);
        jLabel15.setVisible(true);
        jLabel16.setVisible(true);
        jLabel17.setVisible(true);
        jLabel18.setVisible(true);
        jLabel19.setVisible(true);
        jLabel20.setVisible(true);
        
        jTextField4.setVisible(true);
        jTextField5.setVisible(true);
        jTextField6.setVisible(true);
        jTextField7.setVisible(true);
        jTextField8.setVisible(true);
        jTextField9.setVisible(true);
        jTextField10.setVisible(true);
        jTextField11.setVisible(true);
        jTextField12.setVisible(true);
        jTextField13.setVisible(true);
        jTextField14.setVisible(true);
        jTextField15.setVisible(true);
        jTextField16.setVisible(true);
        jTextField17.setVisible(true);
        
        jButton3.setVisible(true);
        jButton4.setVisible(true);
        jButton5.setVisible(true);
        jButton6.setVisible(true);
        jButton7.setVisible(true);
        jButton8.setVisible(true);
        jButton9.setVisible(true);
        jButton10.setVisible(true);
        jButton11.setVisible(true);
        jButton12.setVisible(true);
        jButton13.setVisible(true);
        jButton14.setVisible(true);
        jButton15.setVisible(true);
        jButton16.setVisible(true);
    }
    
    public void Deshabilitar()
    {
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);
        jLabel12.setVisible(false);
        jLabel13.setVisible(false);
        jLabel14.setVisible(false);
        jLabel15.setVisible(false);
        jLabel16.setVisible(false);
        jLabel17.setVisible(false);
        jLabel18.setVisible(false);
        jLabel19.setVisible(false);
        jLabel20.setVisible(false);
        
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jTextField6.setVisible(false);
        jTextField7.setVisible(false);
        jTextField8.setVisible(false);
        jTextField9.setVisible(false);
        jTextField10.setVisible(false);
        jTextField11.setVisible(false);
        jTextField12.setVisible(false);
        jTextField13.setVisible(false);
        jTextField14.setVisible(false);
        jTextField15.setVisible(false);
        jTextField16.setVisible(false);
        jTextField17.setVisible(false);
        
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        jButton5.setVisible(false);
        jButton6.setVisible(false);
        jButton7.setVisible(false);
        jButton8.setVisible(false);
        jButton9.setVisible(false);
        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jButton12.setVisible(false);
        jButton13.setVisible(false);
        jButton14.setVisible(false);
        jButton15.setVisible(false);
        jButton16.setVisible(false);
    }
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
// TODO: Agrege su codigo aqui:
        Abrir();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    public void Abrir()
    {
        // Abrir los datos
        
        JFileChooser x = new JFileChooser();              
        
        x.setFileSelectionMode( JFileChooser.FILES_ONLY );
    
        int result = x.showOpenDialog( this );
     
        // si el usuario hace click en cancel se retorna a la ventana del programa
        if ( result == JFileChooser.CANCEL_OPTION )
           return;

        // obtener el archivo seleccionado
        File name = x.getSelectedFile();
        modelo = name;
        
        // mostrar error en caso de nombre invalido
        if ( name == null || name.getName().equals(""))
           JOptionPane.showMessageDialog( this, "Nombre de archivo invalido","Nombre del Archivo Invalido", JOptionPane.ERROR_MESSAGE );
       else 
       {
          // abrir archivo
          try 
          {
              leer = new DataInputStream(new FileInputStream(name));
              Limpiar();
              leerFichero();
          }
  
          // procesa la excepcion al abrir el archivo
           catch(Exception e ) 
           {
                JOptionPane.showMessageDialog( this,"Error al abrir el archivo","Error", JOptionPane.ERROR_MESSAGE );
           }      
      }
    }
    
    public void leerFichero()
    {
        // Datos
        String nomb, puest, num;
        
        // Habilidad
        String edu, exp, ini;
        String b3, b4, b5;
        
        // Esfuerzo
        String ment, fuer;
        String b6, b7;
        
        // Responsabilidad
        String mat, maq, otr;
        String b8, b9, b10;
        
        // Condiciones
        String amb, riesg;
        String b11, b12;
        
        // Geometrico
        String h, e, r, c;
        String bh, be, br, bc;
        
        String reg;
        
        try
        {
            nomb = leer.readUTF();
            puest = leer.readUTF();
            num = leer.readUTF();
            
            System.out.println(nomb);
            System.out.println(puest);
            System.out.println(num);
            
            jTextField1.setText(nomb);
            jTextField2.setText(puest);
            jTextField3.setText(num);
            
            reg = leer.readUTF();
            
            if(reg.equals("arit"))
            {
                jRadioButtonMenuItem1.setSelected(true);
                
                Verificar_Radio1();
                
                // Peso Porcentual

                edu = leer.readUTF();
                exp = leer.readUTF();
                ini = leer.readUTF();

                ment = leer.readUTF();
                fuer = leer.readUTF();

                mat = leer.readUTF();
                maq = leer.readUTF();
                otr = leer.readUTF();

                amb = leer.readUTF();
                riesg = leer.readUTF();

                // Grados

                b3 = leer.readUTF();
                b4 = leer.readUTF();
                b5 = leer.readUTF();

                b6 = leer.readUTF();
                b7 = leer.readUTF();

                b8 = leer.readUTF();
                b9 = leer.readUTF();
                b10 = leer.readUTF();

                b11 = leer.readUTF();
                b12 = leer.readUTF();
                     
                jTextField5.setText(edu);
                jTextField6.setText(exp);
                jTextField7.setText(ini);

                jTextField9.setText(ment);
                jTextField10.setText(fuer);

                jTextField12.setText(mat);
                jTextField13.setText(maq);
                jTextField14.setText(otr);

                jTextField16.setText(amb);
                jTextField17.setText(riesg);
                     
                jButton3.setText(b3);
                jButton4.setText(b4);
                jButton5.setText(b5);

                jButton6.setText(b6);
                jButton7.setText(b7);

                jButton8.setText(b8);
                jButton9.setText(b9);
                jButton10.setText(b10);

                jButton11.setText(b11);
                jButton12.setText(b12);
                
                Aritmetico();
            }
            else
            {
                jRadioButtonMenuItem2.setSelected(true);
                
                Verificar_Radio2();
                
                // Peso Porcentual

                h = leer.readUTF();
                e = leer.readUTF();
                r = leer.readUTF();
                c = leer.readUTF();

                jTextField4.setText(h);
                jTextField8.setText(e);
                jTextField11.setText(r);
                jTextField15.setText(c);

                // Grados

                bh = leer.readUTF();
                be = leer.readUTF();
                br = leer.readUTF();
                bc = leer.readUTF();

                jButton13.setText(bh);
                jButton14.setText(be);
                jButton15.setText(br);
                jButton16.setText(bc);
                                
                Geometrico();
            }
        }
        catch(IOException ioException)
        {
            JOptionPane.showMessageDialog(null,"Error al abrir archivo","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
// TODO: Agrege su codigo aqui:
        Guardar();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    public void Guardar()
    {
        // Guardar los datos
        JFileChooser x = new JFileChooser();
        
   	x.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
   	int result = x.showSaveDialog(this);
        
   	if(result==JFileChooser.CANCEL_OPTION)
            return;
        
   	File name = x.getSelectedFile();
   	modelo = name;
        
   	if(name==null || name.getName().equals(""))
   		JOptionPane.showMessageDialog(this,"Nombre del archivo invalido","Nombre del archivo invalido",JOptionPane.ERROR_MESSAGE);
   	else
   	{
   		try
                {
                    escribir = new DataOutputStream(new FileOutputStream(modelo + ".pr"));      
                    escribirFichero();
   		}
   		// mostrar mensaje de error si no se puede abrir el archivo
                catch ( IOException ioException ) 
                {
                    JOptionPane.showMessageDialog( this, "Error al abrir el archivo","Error", JOptionPane.ERROR_MESSAGE );
                }    
   	} // fin else
    }
    
    public void escribirFichero()
    {
        // Datos
        String nomb, puest, num;
        
        // Habilidad
        String edu, exp, ini;
        String b3, b4, b5;
        
        // Esfuerzo
        String ment, fuer;
        String b6, b7;
        
        // Responsabilidad
        String mat, maq, otr;
        String b8, b9, b10;
        
        // Condiciones
        String amb, riesg;
        String b11, b12;
        
        // Geometrico
        String h, e, r, c;
        String bh, be, br, bc;
        
        String reg;
                
        try
        {           
            nomb = jTextField1.getText();
            puest = jTextField2.getText();
            num = jTextField3.getText();
            
            System.out.println(nomb);
            System.out.println(puest);
            System.out.println(num);
            
            escribir.writeUTF(nomb);
            escribir.writeUTF(puest);
            escribir.writeUTF(num);
            
            if(jRadioButtonMenuItem1.isSelected() == true)
            {
                try
                {
                    reg = "arit";
                    escribir.writeUTF(reg);

                    // Peso Porcentual

                    edu = jTextField5.getText();
                    exp = jTextField6.getText();
                    ini = jTextField7.getText();

                    ment = jTextField9.getText();
                    fuer = jTextField10.getText();

                    mat = jTextField12.getText();
                    maq = jTextField13.getText();
                    otr = jTextField14.getText();

                    amb = jTextField16.getText();
                    riesg = jTextField17.getText();
                    
                    escribir.writeUTF(edu);
                    escribir.writeUTF(exp);
                    escribir.writeUTF(ini);

                    escribir.writeUTF(ment);
                    escribir.writeUTF(fuer);

                    escribir.writeUTF(mat);
                    escribir.writeUTF(maq);
                    escribir.writeUTF(otr);

                    escribir.writeUTF(amb);
                    escribir.writeUTF(riesg);

                    // Grados

                    b3 = jButton3.getText();
                    b4 = jButton4.getText();
                    b5 = jButton5.getText();
                    b6 = jButton6.getText();
                    b7 = jButton7.getText();
                    b8 = jButton8.getText();
                    b9 = jButton9.getText();
                    b10 = jButton10.getText();
                    b11 = jButton11.getText();
                    b12 = jButton12.getText();

                    escribir.writeUTF(b3);
                    escribir.writeUTF(b4);
                    escribir.writeUTF(b5);

                    escribir.writeUTF(b6);
                    escribir.writeUTF(b7);

                    escribir.writeUTF(b8);
                    escribir.writeUTF(b9);
                    escribir.writeUTF(b10);

                    escribir.writeUTF(b11);
                    escribir.writeUTF(b12);
                }
                catch(IOException ioException)
                {
                    
                }
            }
            else
            {
                reg = "geo";
                escribir.writeUTF(reg);
                
                // Peso Porcentual

                h = jTextField4.getText();
                e = jTextField8.getText();
                r = jTextField11.getText();
                c = jTextField15.getText();

                escribir.writeUTF(h);
                escribir.writeUTF(e);
                escribir.writeUTF(r);
                escribir.writeUTF(c);

                // Grados
                
                bh = jButton13.getText();
                be = jButton14.getText();
                br = jButton15.getText();
                bc = jButton16.getText();

                escribir.writeUTF(bh);
                escribir.writeUTF(be);
                escribir.writeUTF(br);
                escribir.writeUTF(bc);
            }
        }
        catch(IOException ioException)
        {
            JOptionPane.showMessageDialog(null,"Error al guardar archivo","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            if(jRadioButtonMenuItem1.isSelected() == true)
                jTextField5.requestFocus();
            else
                jTextField4.requestFocus();
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField3.requestFocus();
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField2.requestFocus();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jButton1.requestFocus();
        }
    }//GEN-LAST:event_jTextField17KeyPressed

    private void jTextField16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField17.requestFocus();
        }
    }//GEN-LAST:event_jTextField16KeyPressed

    private void jTextField14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField16.requestFocus();
        }
    }//GEN-LAST:event_jTextField14KeyPressed

    private void jTextField13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField14.requestFocus();
        }
    }//GEN-LAST:event_jTextField13KeyPressed

    private void jTextField12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField13.requestFocus();
        }
    }//GEN-LAST:event_jTextField12KeyPressed

    private void jTextField10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField12.requestFocus();
        }
    }//GEN-LAST:event_jTextField10KeyPressed

    private void jTextField9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField10.requestFocus();
        }
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField9.requestFocus();
        }
    }//GEN-LAST:event_jTextField7KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField7.requestFocus();
        }
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
// TODO: Agrege su codigo aqui:
        int KeyCode = evt.getKeyCode();
        
        if(KeyCode == 10)
        {
            jTextField6.requestFocus();
        }
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseReleased
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton3MouseReleased

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton12.getText());
        
        switch(grado)
        {
            case 1:
                    jButton12.setToolTipText("Ambiente de trabajo normal. La posibilidad de que ocurra un accidente es eventual");
                    break;
            case 2:
                    jButton12.setToolTipText("Pocas veces se expone a accidentes o molestias de menor importancia, que pueden producir incapacidades temporales no mayor de tres días");
                    break;
            case 3:
                    jButton12.setToolTipText("Expuestos a accidentes que pueden producir incapacidades temporales mayores de tres días");
                    break;
            case 4:
                    jButton12.setToolTipText("Expuestos a riesgos que pueden incapacitarlo por un período no mayor de treinta días");
                    break;
            case 5:
                    jButton12.setToolTipText("Expuestos a riesgos que pueden ocasionar daños permanentes en el organismo o la muerte incluso");
                    break;
        }
    }//GEN-LAST:event_jButton12MouseEntered

    private void jButton11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton11.getText());
        
        switch(grado)
        {
            case 1:
                    jButton11.setToolTipText("Ambiente de trabajo agradable con excelente ventilación, excelente ilumminación adecuado al tipo de puesto, sin ruido violentos debajo de 70 db");
                    break;
            case 2:
                    jButton11.setToolTipText("Ambiente de trabajo regular, un poco caluroso (30°C), con buena iluminación y ruido un poco molesto (ligeramente superior a los 70 db)");
                    break;
            case 3:
                    jButton11.setToolTipText("Ambiente de trabajo regular, un poco caluroso (30°C), con abanicos, buena iluminación, pero ruidos intensos pero eventual (85 db)");
                    break;
            case 4:
                    jButton11.setToolTipText("Ambiente de trabajo intensamente caluroso, con ventilación, iluminación adecuada al tipo de trabajo, ruidos intensos hasta de 90 db");
                    break;
            case 5:
                    jButton11.setToolTipText("Ambiente de trabajo excesivamente caluroso, sin abanico, poca ventilación, ruido intensos e intermitentes hasta de 90 db. E iluminación aceptable");
                    break;
        }
    }//GEN-LAST:event_jButton11MouseEntered

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton10.getText());
        
        switch(grado)
        {
            case 1:
                    jButton10.setToolTipText("Responsable únicamente de su propio trabajo");
                    break;
            case 2:
                    jButton10.setToolTipText("Influye de manera indirecta en el trabajo de otras personas de un departamento o de otros departamentos");
                    break;
            case 3:
                    jButton10.setToolTipText("Dirige o supervisa el trabajo de una o dos personas");
                    break;
            case 4:
                    jButton10.setToolTipText("Dirige o supervisa el trabajo de tres o seis personas");
                    break;
            case 5:
                    jButton10.setToolTipText("Dirige o supervisa el trabajo de más de seis personas");
                    break;
        }
    }//GEN-LAST:event_jButton10MouseEntered

    private void jButton9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton9.getText());
        
        switch(grado)
        {
            case 1:
                    jButton9.setToolTipText("El daño a la maquinaria, instrumental y equipos es muy probable igual que la pérdida de los mismos");
                    break;
            case 2:
                    jButton9.setToolTipText("El daño a la maquinaria, instrumental y equipos es poco probable, igual que la pérdida de los mismos");
                    break;
            case 3:
                    jButton9.setToolTipText("El daño a la maquinaria, instrumental y equipos es fácil de causarse");
                    break;
            case 4:
                    jButton9.setToolTipText("Muy fácilmente puede causarse daños a la maquinaria, instrumental y equipos");
                    break;
            case 5:
                    jButton9.setToolTipText("Muy frecuentemente puede causarse daño a la maquinaria, instrumental y equipos");
                    break;
        }
    }//GEN-LAST:event_jButton9MouseEntered

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton8.getText());
        
        switch(grado)
        {
            case 1:
                    jButton8.setToolTipText("El valor de los materiales con que se trabaja tiene un precio hasta de US $ 10");
                    break;
            case 2:
                    jButton8.setToolTipText("El valor del material con que se trabaja está entre US $ 10 y US $ 100");
                    break;
            case 3:
                    jButton8.setToolTipText("El valor del material con que se trabaja está entre US $ 100 y US $ 1000");
                    break;
            case 4:
                    jButton8.setToolTipText("El valor del material con que se trabaja está entre US $ 1000 y US $ 5000");
                    break;
            case 5:
                    jButton8.setToolTipText("El valor del material con que se trabaja tiene un precio mayor de US $ 5000");
                    break;
        }
    }//GEN-LAST:event_jButton8MouseEntered

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton7.getText());
        
        switch(grado)
        {
            case 1:
                    jButton7.setToolTipText("Atención normal que debe ponerse en todo trabajo");
                    break;
            case 2:
                    jButton7.setToolTipText("Atención intensa y sostenida sólo durante período cortos; el resto del tiempo sólo exige atención refleja normal");
                    break;
            case 3:
                    jButton7.setToolTipText("Requiere atención intensa en forma regular, pero intermitente");
                    break;
            case 4:
                    jButton7.setToolTipText("Atención intensa constante y sostenida");
                    break;
            case 5:
                    jButton7.setToolTipText("Requiere análisis y mucha concentración mental para tomar decisiones sumamente importantes en su puesto de trabajo");
                    break;
        }
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton6.getText());
        
        switch(grado)
        {
            case 1:
                    jButton6.setToolTipText("Esfuerzo físico requerido para realizar exclusivamente trabajos manuales de oficina");
                    break;
            case 2:
                    jButton6.setToolTipText("Esfuerzo físico mínimo desarrollado por trabajadores especializados que operan máquina totalmente automática y trabajos de oficina ligeros en su puesto de trabajo");
                    break;
            case 3:
                    jButton6.setToolTipText("Esfuerzo físico requerido para manejar manualmente sus equipos y trabajos constantes de oficinas");
                    break;
            case 4:
                    jButton6.setToolTipText("Constante esfuerzo físico mediano como el requerido para cargar y descargar");
                    break;
            case 5:
                    jButton6.setToolTipText("Constante aplicación de gran esfuerzo físico");
                    break;
        }
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton5.getText());
        
        switch(grado)
        {
            case 1:
                    jButton5.setToolTipText("Requiere solamente habilidad para ejecutar las órdenes recibidas");
                    break;
            case 2:
                    jButton5.setToolTipText("Requiere ciertas interpretación de las órdenes recibidas al aplicarla e iniciativa para resolverla");
                    break;
            case 3:
                    jButton5.setToolTipText("Requiere criterio e iniciativa para resolverlo constantemente (el 50%) o más, los problemas sencillos que se presentan en el puesto");
                    break;
            case 4:
                    jButton5.setToolTipText("Requiere criterio e iniciativa para resolverlo eventualmente los problemas díficiles que se presentan en el puesto de trabajo");
                    break;
            case 5:
                    jButton5.setToolTipText("Requiere criterio e iniciativa para resolver constantemente problemas díficiles y de gran trascendencia en su trabajo");
                    break;
        }
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton4.getText());
        
        switch(grado)
        {
            case 1:
                    jButton4.setToolTipText("Experiencia hasta de tres meses");
                    break;
            case 2:
                    jButton4.setToolTipText("Experiencia hasta de seis meses");
                    break;
            case 3:
                    jButton4.setToolTipText("Experiencia hasta de un año");
                    break;
            case 4:
                    jButton4.setToolTipText("Experiencia hasta de tres años");
                    break;
            case 5:
                    jButton4.setToolTipText("Experiencia hasta de más de tres años");
                    break;
        }
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
// TODO: Agrege su codigo aqui:
        int grado = Integer.parseInt(jButton3.getText());
        
        switch(grado)
        {
            case 1:
                    jButton3.setToolTipText("Haber cursado la primaria");
                    break;
            case 2:
                    jButton3.setToolTipText("Haber cursado ciclo básico y estudios técnicos medios");
                    break;
            case 3:
                    jButton3.setToolTipText("Haber cursado la secundaria y estudios técnicos medios");
                    break;
            case 4:
                    jButton3.setToolTipText("Haber realizado estudios Universitarios");
                    break;
            case 5:
                    jButton3.setToolTipText("Haber realizado estudios de especializaciones: Postgrado, maestría o estudios superiores a estos");
                    break;
        }
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jButton3MouseWheelMoved
// TODO: Agrege su codigo aqui:
        
    }//GEN-LAST:event_jButton3MouseWheelMoved

    private void jButton3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseMoved
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton3MouseMoved

    private void jButton3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseDragged
// TODO: Agrege su codigo aqui:
    }//GEN-LAST:event_jButton3MouseDragged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO: Agrege su codigo aqui:
        if(jRadioButtonMenuItem1.isSelected() == true)
            Aritmetico();
        else
            Geometrico();
        
        Restriccion_Ejecutar();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public void Restriccion_Ejecutar()
    {
        this.jButton1.setEnabled(false);
        this.jButton23.setEnabled(false);
    }
    
    public void Aritmetico()
    {
        if(Suma_Total() == 100)
        {
            iniciar();
            Tabla_Asignacion();
            Obtener_Datos();
            Tabla_Salarial();
            Poner_Factores();
            jTabbedPane1.setSelectedIndex(2);
        }
    }
    
    public void Geometrico()
    {
        if(Suma_Total2() == 100)
        {
            iniciar();
            Tabla_Asignacion2();
            Obtener_Datos();
            Tabla_Salarial2();
            jTabbedPane1.setSelectedIndex(2);
        }
    }
    
    public void iniciar()
    {
        Limpiar_Tabla();
        puntaje = 0;
        g = 0;
    }
    
    public int Suma_Total2()
    {
        int hab = 0, esf = 0, resp = 0, cond = 0, sum = 0;
        
        hab = Integer.parseInt(jTextField4.getText());
        esf = Integer.parseInt(jTextField8.getText());
        resp = Integer.parseInt(jTextField11.getText());
        cond = Integer.parseInt(jTextField15.getText());
        
        sum = hab + esf + resp + cond;
        jTextField18.setText(String.valueOf(sum));
        return sum;
    }
    
    public void Obtener_Datos()
    {
        nombre = jTextField1.getText();
        minimo = Double.parseDouble(jTextField2.getText());
        puesto = Integer.parseInt(jTextField3.getText());
        
        if(jRadioButtonMenuItem1.isSelected() == true)
        {
            max = 500;
            min = 100;
            
            Obtener_Puntaje();
        }
        else
        {
            max = 400;
            min = 100;
            
            //System.out.println(String.valueOf(jTable1.getValueAt(0,2)));
            
            Obtener_Puntaje2();
        }
        
        g = (max - min) / puesto;
        
        G = d.Gradiente_Crecimiento(g, max, min, puesto);
        
        jTextField19.setText(String.valueOf(g));       
    }
    
    public void Obtener_Puntaje()
    {
        Educacion[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(1,2)));
        Educacion[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(1,3)));
        Educacion[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(1,4)));
        Educacion[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(1,5)));
        Educacion[4] = Integer.parseInt(String.valueOf(jTable1.getValueAt(1,6)));
                
        Experiencia[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(2,2)));
        Experiencia[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(2,3)));
        Experiencia[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(2,4)));
        Experiencia[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(2,5)));
        Experiencia[4] = Integer.parseInt(String.valueOf(jTable1.getValueAt(2,6)));
        
        Iniciativa[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(3,2)));
        Iniciativa[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(3,3)));
        Iniciativa[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(3,4)));
        Iniciativa[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(3,5)));
        Iniciativa[4] = Integer.parseInt(String.valueOf(jTable1.getValueAt(3,6)));
        
        Fisico[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(5,2)));
        Fisico[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(5,3)));
        Fisico[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(5,4)));
        Fisico[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(5,5)));
        Fisico[4] = Integer.parseInt(String.valueOf(jTable1.getValueAt(5,6)));
        
        Mental[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(6,2)));
        Mental[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(6,3)));
        Mental[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(6,4)));
        Mental[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(6,5)));
        Mental[4] = Integer.parseInt(String.valueOf(jTable1.getValueAt(6,6)));
        
        Materiales[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(8,2)));
        Materiales[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(8,3)));
        Materiales[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(8,4)));
        Materiales[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(8,5)));
        Materiales[4] = Integer.parseInt(String.valueOf(jTable1.getValueAt(8,6)));
                
        Maquinaria[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(9,2)));
        Maquinaria[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(9,3)));
        Maquinaria[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(9,4)));
        Maquinaria[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(9,5)));
        Maquinaria[4] = Integer.parseInt(String.valueOf(jTable1.getValueAt(9,6)));
        
        Otros[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(10,2)));
        Otros[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(10,3)));
        Otros[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(10,4)));
        Otros[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(10,5)));
        Otros[4] = Integer.parseInt(String.valueOf(jTable1.getValueAt(10,6)));
        
        Ambiente[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(12,2)));
        Ambiente[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(12,3)));
        Ambiente[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(12,4)));
        Ambiente[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(12,5)));
        Ambiente[4] = Integer.parseInt(String.valueOf(jTable1.getValueAt(12,6)));
        
        Riesgo[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(13,2)));
        Riesgo[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(13,3)));
        Riesgo[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(13,4)));
        Riesgo[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(13,5)));
        Riesgo[4] = Integer.parseInt(String.valueOf(jTable1.getValueAt(13,6)));
        
        P[0] = Integer.parseInt(jButton3.getText());
        P[1] = Integer.parseInt(jButton4.getText());
        P[2] = Integer.parseInt(jButton5.getText());
        P[3] = Integer.parseInt(jButton6.getText());
        P[4] = Integer.parseInt(jButton7.getText());
        P[5] = Integer.parseInt(jButton8.getText());
        P[6] = Integer.parseInt(jButton9.getText());
        P[7] = Integer.parseInt(jButton10.getText());
        P[8] = Integer.parseInt(jButton11.getText());
        P[9] = Integer.parseInt(jButton12.getText());
        
        switch(P[0])
        {
            case 1:
                puntaje = puntaje + Educacion[0];
                break;
            case 2:
                puntaje = puntaje + Educacion[1];
                break;
            case 3:
                puntaje = puntaje + Educacion[2];
                break;
            case 4:
                puntaje = puntaje + Educacion[3];
                break;
            case 5:
                puntaje = puntaje + Educacion[4];
                break;
        }
        
        switch(P[1])
        {
            case 1:
                puntaje = puntaje + Experiencia[0];
                break;
            case 2:
                puntaje = puntaje + Experiencia[1];
                break;
            case 3:
                puntaje = puntaje + Experiencia[2];
                break;
            case 4:
                puntaje = puntaje + Experiencia[3];
                break;
            case 5:
                puntaje = puntaje + Experiencia[4];
                break;
        }
        
        switch(P[2])
        {
            case 1:
                puntaje = puntaje + Iniciativa[0];
                break;
            case 2:
                puntaje = puntaje + Iniciativa[1];
                break;
            case 3:
                puntaje = puntaje + Iniciativa[2];
                break;
            case 4:
                puntaje = puntaje + Iniciativa[3];
                break;
            case 5:
                puntaje = puntaje + Iniciativa[4];
                break;
        }
        
        switch(P[3])
        {
            case 1:
                puntaje = puntaje + Fisico[0];
                break;
            case 2:
                puntaje = puntaje + Fisico[1];
                break;
            case 3:
                puntaje = puntaje + Fisico[2];
                break;
            case 4:
                puntaje = puntaje + Fisico[3];
                break;
            case 5:
                puntaje = puntaje + Fisico[4];
                break;
        }
        
        switch(P[4])
        {
            case 1:
                puntaje = puntaje + Mental[0];
                break;
            case 2:
                puntaje = puntaje + Mental[1];
                break;
            case 3:
                puntaje = puntaje + Mental[2];
                break;
            case 4:
                puntaje = puntaje + Mental[3];
                break;
            case 5:
                puntaje = puntaje + Mental[4];
                break;
        }
        
        switch(P[5])
        {
            case 1:
                puntaje = puntaje + Materiales[0];
                break;
            case 2:
                puntaje = puntaje + Materiales[1];
                break;
            case 3:
                puntaje = puntaje + Materiales[2];
                break;
            case 4:
                puntaje = puntaje + Materiales[3];
                break;
            case 5:
                puntaje = puntaje + Materiales[4];
                break;
        }
        
        switch(P[6])
        {
            case 1:
                puntaje = puntaje + Maquinaria[0];
                break;
            case 2:
                puntaje = puntaje + Maquinaria[1];
                break;
            case 3:
                puntaje = puntaje + Maquinaria[2];
                break;
            case 4:
                puntaje = puntaje + Maquinaria[3];
                break;
            case 5:
                puntaje = puntaje + Maquinaria[4];
                break;
        }
        
        switch(P[7])
        {
            case 1:
                puntaje = puntaje + Otros[0];
                break;
            case 2:
                puntaje = puntaje + Otros[1];
                break;
            case 3:
                puntaje = puntaje + Otros[2];
                break;
            case 4:
                puntaje = puntaje + Otros[3];
                break;
            case 5:
                puntaje = puntaje + Otros[4];
                break;
        }
        
        switch(P[8])
        {
            case 1:
                puntaje = puntaje + Ambiente[0];
                break;
            case 2:
                puntaje = puntaje + Ambiente[1];
                break;
            case 3:
                puntaje = puntaje + Ambiente[2];
                break;
            case 4:
                puntaje = puntaje + Ambiente[3];
                break;
            case 5:
                puntaje = puntaje + Ambiente[4];
                break;
        }
        
        switch(P[9])
        {
            case 1:
                puntaje = puntaje + Riesgo[0];
                break;
            case 2:
                puntaje = puntaje + Riesgo[1];
                break;
            case 3:
                puntaje = puntaje + Riesgo[2];
                break;
            case 4:
                puntaje = puntaje + Riesgo[3];
                break;
            case 5:
                puntaje = puntaje + Riesgo[4];
                break;
        }
        
        jTextField20.setText(String.valueOf(puntaje));
    }
    
    public void Obtener_Puntaje2()
    {
        Habilidad[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(0,2)));
        Habilidad[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(0,3)));
        Habilidad[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(0,4)));
        Habilidad[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(0,5)));
        
        Esfuerzo[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(4,2)));
        Esfuerzo[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(4,3)));
        Esfuerzo[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(4,4)));
        Esfuerzo[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(4,5)));
        
        Responsabilidad[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(7,2)));
        Responsabilidad[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(7,3)));
        Responsabilidad[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(7,4)));
        Responsabilidad[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(7,5)));
        
        Condicion[0] = Integer.parseInt(String.valueOf(jTable1.getValueAt(11,2)));
        Condicion[1] = Integer.parseInt(String.valueOf(jTable1.getValueAt(11,3)));
        Condicion[2] = Integer.parseInt(String.valueOf(jTable1.getValueAt(11,4)));
        Condicion[3] = Integer.parseInt(String.valueOf(jTable1.getValueAt(11,5)));
        
        P[0] = Integer.parseInt(jButton13.getText());
        P[1] = Integer.parseInt(jButton14.getText());
        P[2] = Integer.parseInt(jButton15.getText());
        P[3] = Integer.parseInt(jButton16.getText());
        
        switch(P[0])
        {
            case 1:
                puntaje = puntaje + Habilidad[0];
                break;
            case 2:
                puntaje = puntaje + Habilidad[1];
                break;
            case 3:
                puntaje = puntaje + Habilidad[2];
                break;
            case 4:
                puntaje = puntaje + Habilidad[3];
                break;
        }
        
        switch(P[1])
        {
            case 1:
                puntaje = puntaje + Esfuerzo[0];
                break;
            case 2:
                puntaje = puntaje + Esfuerzo[1];
                break;
            case 3:
                puntaje = puntaje + Esfuerzo[2];
                break;
            case 4:
                puntaje = puntaje + Esfuerzo[3];
                break;
        }
        
        switch(P[2])
        {
            case 1:
                puntaje = puntaje + Responsabilidad[0];
                break;
            case 2:
                puntaje = puntaje + Responsabilidad[1];
                break;
            case 3:
                puntaje = puntaje + Responsabilidad[2];
                break;
            case 4:
                puntaje = puntaje + Responsabilidad[3];
                break;
        }
        
        switch(P[3])
        {
            case 1:
                puntaje = puntaje + Condicion[0];
                break;
            case 2:
                puntaje = puntaje + Condicion[1];
                break;
            case 3:
                puntaje = puntaje + Condicion[2];
                break;
            case 4:
                puntaje = puntaje + Condicion[3];
                break;
        }
                
        jTextField20.setText(String.valueOf(puntaje));
    }
    
    public void Tabla_Asignacion()
    {
        Poner_Factores();
        Peso_Porcentual();
        Determinar_Grados();
    }
    
    public void Tabla_Asignacion2()
    {
        Peso_Porcentual2();
        Determinar_Grados2();
    }
    
    public void Poner_Factores()
    {
        jTable1.setValueAt("Habilidad", 0, 0);
        jTable1.setValueAt("- Educación", 1, 0);
        jTable1.setValueAt("- Experiencia", 2, 0);
        jTable1.setValueAt("- Iniciativa e Ingenio", 3, 0);
        
        jTable1.setValueAt("Esfuerzo", 4, 0);
        jTable1.setValueAt("- Físico", 5, 0);
        jTable1.setValueAt("- Mental", 6, 0);
        
        jTable1.setValueAt("Responsabilidad", 7, 0);
        jTable1.setValueAt("- Materiales", 8, 0);
        jTable1.setValueAt("- Maquinarias y Equipos", 9, 0);
        jTable1.setValueAt("- Otros", 10, 0);
        
        jTable1.setValueAt("Condiciones de trabajo", 11, 0);
        jTable1.setValueAt("- Ambiente de trabajo", 12, 0);
        jTable1.setValueAt("- Riesgo", 13, 0);
        
        jTable1.setValueAt("Total", 14, 0);
    }
    
    public void Peso_Porcentual()
    {
        jTable1.setValueAt(jTextField4.getText(),0,1);
        jTable1.setValueAt(jTextField5.getText(),1,1);
        jTable1.setValueAt(jTextField6.getText(),2,1);
        jTable1.setValueAt(jTextField7.getText(),3,1);
        jTable1.setValueAt(jTextField8.getText(),4,1);
        jTable1.setValueAt(jTextField9.getText(),5,1);
        jTable1.setValueAt(jTextField10.getText(),6,1);
        jTable1.setValueAt(jTextField11.getText(),7,1);
        jTable1.setValueAt(jTextField12.getText(),8,1);
        jTable1.setValueAt(jTextField13.getText(),9,1);
        jTable1.setValueAt(jTextField14.getText(),10,1);
        jTable1.setValueAt(jTextField15.getText(),11,1);
        jTable1.setValueAt(jTextField16.getText(),12,1);
        jTable1.setValueAt(jTextField17.getText(),13,1);
        jTable1.setValueAt(jTextField18.getText(),14,1);
    }
    
    public void Peso_Porcentual2()
    {
        jTable1.setValueAt(jTextField4.getText(),0,1);
        
        jTable1.setValueAt(jTextField8.getText(),4,1);
        
        jTable1.setValueAt(jTextField11.getText(),7,1);
        
        jTable1.setValueAt(jTextField15.getText(),11,1);
        
        jTable1.setValueAt(jTextField18.getText(),14,1);
    }
    
    public void Determinar_Grados()
    {
        int col = 1;
        int grado = 1;
        
        for(grado = 1; grado <= 5; grado++)
        {
            col++;
            
            
            jTable1.setValueAt(Integer.parseInt(jTextField5.getText()) * grado,1,col);
            jTable1.setValueAt(Integer.parseInt(jTextField6.getText()) * grado,2,col);
            jTable1.setValueAt(Integer.parseInt(jTextField7.getText()) * grado,3,col);
            
            jTable1.setValueAt(Integer.parseInt(jTextField9.getText()) * grado,5,col);
            jTable1.setValueAt(Integer.parseInt(jTextField10.getText()) * grado,6,col);
            
            jTable1.setValueAt(Integer.parseInt(jTextField12.getText()) * grado,8,col);
            jTable1.setValueAt(Integer.parseInt(jTextField13.getText()) * grado,9,col);
            jTable1.setValueAt(Integer.parseInt(jTextField14.getText()) * grado,10,col);
            
            jTable1.setValueAt(Integer.parseInt(jTextField16.getText()) * grado,12,col);
            jTable1.setValueAt(Integer.parseInt(jTextField17.getText()) * grado,13,col);
            jTable1.setValueAt(Integer.parseInt(jTextField18.getText()) * grado,14,col);
        }
    }
    
    public void Determinar_Grados2()
    {
        int col = 1;
        int grado = 1;
        int x = 0;
        
        String habi[] = new String[100];
        String esf[] = new String[100];
        String resp[] = new String[100];
        String cond[] = new String[100];
        String total[] = new String[100];
        
        Ajustar_Tabla();
        
        for(grado = 1; grado <= 5; grado++)
        {
            col++;
            
            if(grado == 5)
            {
                    
            }
            else
            {
                 jTable1.setValueAt(Integer.parseInt(jTextField4.getText()) * (int)Math.pow(2,grado - 1),0,col);
                 habi[col - 1] = d.Asignacion_Pto(col - 1, Integer.parseInt(jTextField4.getText()), Integer.parseInt(jTextField4.getText()) * (int)Math.pow(2,grado - 1));
                 
                 jTable1.setValueAt(Integer.parseInt(jTextField8.getText()) * (int)Math.pow(2,grado - 1),4,col);
                 esf[col - 1] = d.Asignacion_Pto(col - 1, Integer.parseInt(jTextField8.getText()), Integer.parseInt(jTextField8.getText()) * (int)Math.pow(2,grado - 1));
                 
                 jTable1.setValueAt(Integer.parseInt(jTextField11.getText()) * (int)Math.pow(2,grado - 1),7,col);
                 resp[col - 1] = d.Asignacion_Pto(col - 1, Integer.parseInt(jTextField11.getText()), Integer.parseInt(jTextField11.getText()) * (int)Math.pow(2,grado - 1));
                 
                 jTable1.setValueAt(Integer.parseInt(jTextField15.getText()) * (int)Math.pow(2,grado - 1),11,col);
                 cond[col - 1] = d.Asignacion_Pto(col - 1, Integer.parseInt(jTextField15.getText()), Integer.parseInt(jTextField15.getText()) * (int)Math.pow(2,grado - 1));
                 
                 jTable1.setValueAt(Integer.parseInt(jTextField18.getText()) * (int)Math.pow(2,grado - 1),14,col);
                 total[col - 1] = d.Asignacion_Pto(col - 1, Integer.parseInt(jTextField18.getText()), Integer.parseInt(jTextField18.getText()) * (int)Math.pow(2,grado - 1));
            }
        }
        
        L+= "Habilidad \n\n";
        
        for(x = 1; x <= 4; x++)
        {
            L += habi[x];
        }
        
        L+= "\nEsfuerzo \n\n";
        
        for(x = 1; x <= 4; x++)
        {
            L += esf[x];
        }
        
        L+= "\nResponsabilidad \n\n";
        
        for(x = 1; x <= 4; x++)
        {
            L += resp[x];
        }
        
        L+= "\nCondiciones de trabajo \n\n";
        
        for(x = 1; x <= 4; x++)
        {
            L += cond[x];
        }
        
        L+= "\nTotal \n\n";
        
        for(x = 1; x <= 4; x++)
        {
            L += total[x];
        }
    }
    
    public void Ajustar_Tabla()
    {
        this.jTable1.setValueAt(" ", 1, 0);
        this.jTable1.setValueAt(" ", 2, 0);
        this.jTable1.setValueAt(" ", 3, 0);
        
        this.jTable1.setValueAt(" ", 5, 0);
        this.jTable1.setValueAt(" ", 6, 0);
        
        this.jTable1.setValueAt(" ", 8, 0);
        this.jTable1.setValueAt(" ", 9, 0);
        this.jTable1.setValueAt(" ", 10, 0);
        
        this.jTable1.setValueAt(" ", 12, 0);
        this.jTable1.setValueAt(" ", 13, 0);
                
    }
    
    public void Tabla_Salarial()
    {
        int Lmin[] = new int[500];
        int Lmax[] = new int[500];
        String Rango = null;
        double coef = 0, sal = 0;
        int i = 0;
        
        NumberFormat nf1 = NumberFormat.getNumberInstance();
        NumberFormat nf2 = NumberFormat.getNumberInstance();
        
        double pmax = 0, pmin = 0, r = 0;
        double L = 0, L2 = 0;
        
        nf1.setMaximumFractionDigits(4);
        nf2.setMaximumFractionDigits(2);
        
        Lmin[0] = 100;
        Lmax[0] = Lmin[0] + g;
        
        Rango = String.valueOf(Lmin[0]) + " - " + String.valueOf(Lmax[0]);

        coef = (double)Lmax[0] / Lmax[0];
        sal = minimo;
        
        C = d.Coeficiente_Salarial_N(1, coef, Lmax[0], Lmax[0]);
        S = d.Salario_Mensual_N(1, minimo, coef, sal);
        
        for(i = 0; i <= (puesto - 1); i++)
        {
            Agregar_Fila();
            
            jTable2.setValueAt(i + 1,i,0);
            jTable2.setValueAt(Rango,i,1);
            jTable2.setValueAt(nf1.format(coef),i,2);
            jTable2.setValueAt(nf2.format(sal),i,3);
            
            if(puntaje >= Lmin[i] && puntaje <= Lmax[i])
                jTextField21.setText(String.valueOf(jTable2.getValueAt(i,3)));
            
            Lmax[i + 1] = Lmax[i] + g;
            Lmin[i + 1] = Lmax[i] + 1;
            
            Rango = String.valueOf(Lmin[i + 1]) + " - " + String.valueOf(Lmax[i + 1]);
            coef = (double)Lmax[i + 1] / Lmax[0];
            sal = (double)coef * minimo;
            
            if(!(i <= (puesto - 2)))
            {
                
            }
            else
            {
                C += d.Coeficiente_Salarial_N(i + 2, coef, Lmax[i + 1], Lmax[0]);
                S += d.Salario_Mensual_N(i + 2, minimo, coef, sal);
            }
        }
    }
    
    public void Tabla_Salarial2()
    {
        int Lmin[] = new int[500];
        int Lmax[] = new int[500];
        String Rango = null;
        double coef = 0, sal = 0;
        int i = 0;
        
        NumberFormat nf1 = NumberFormat.getNumberInstance();
        NumberFormat nf2 = NumberFormat.getNumberInstance();
        NumberFormat nf3 = NumberFormat.getNumberInstance();
        
        double pmax = 0, pmin = 0, r = 0;
        double L = 0, L2 = 0, n = 0, div = 0;
        
        nf1.setMaximumFractionDigits(4);
        nf2.setMaximumFractionDigits(2);
        nf3.setMaximumFractionDigits(8);
        
        pmax = (double)Double.parseDouble(String.valueOf(jTable1.getValueAt(14,5)));
        pmin = (double)Double.parseDouble(String.valueOf(jTable1.getValueAt(14,2)));
        
        n = puesto - 1;
        
        div = (double) pmax / pmin;
        
        n = 1 / n;
        
        r = (double)Math.pow(div,n);
        
        R += d.Razon_Crecimiento(puesto, pmax, pmin, r);
                
        jTextField22.setText(String.valueOf(nf3.format(r)));
        
        L = pmin * Math.pow(r,(i + 1) - 1);
        
        Lmin[0] = 0;
        Lmax[0] = (int)L;
        L2 = L;
        
        Rango = String.valueOf(Lmin[0]) + " - " + String.valueOf(Lmax[0]);

        Rang += d.Determinar_Rango(1, pmin, r, L);
        
        coef = (double)Lmax[0] / Lmax[0];
        sal = minimo;
        
        C = d.Coeficiente_Salarial_N(1, coef, Lmax[0], Lmax[0]);
        S = d.Salario_Mensual_N(1, minimo, coef, sal);
                
        for(i = 0; i <= (puesto - 1); i++)
        {
            Agregar_Fila();
            
            jTable2.setValueAt(i + 1,i,0);
            jTable2.setValueAt(Rango,i,1);
            jTable2.setValueAt(nf1.format(coef),i,2);
            jTable2.setValueAt(nf2.format(sal),i,3);
            
            if(puntaje >= Lmin[i] && puntaje <= Lmax[i])
                jTextField21.setText(String.valueOf(jTable2.getValueAt(i,3)));    
            
            L = pmin * Math.pow(r,(i + 1) - 1);
            
            Lmin[i + 1] = (int)L;
            
            L = pmin * Math.pow(r,(i + 2) - 1);
            
            Lmax[i + 1] = (int)L;
            
            L2 = L;
            
            Rango = String.valueOf(Lmin[i + 1]) + " - " + String.valueOf(Lmax[i + 1]);
            
            coef = (double)Lmax[i + 1] / Lmax[0];
            sal = (double)coef * minimo;
            
            if(!(i <= (puesto - 2)))
            {
                
            }
            else
            {
                Rang += d.Determinar_Rango(i + 1, pmin, r, L);
                C += d.Coeficiente_Salarial_N(i + 2, coef, Lmax[i + 1], Lmax[0]);
                S += d.Salario_Mensual_N(i + 2, minimo, coef, sal);
            }
        }
    }
    
    public double Redondear(double valor, int cantidad)
    {
        return (int)((Math.pow(valor * 10,cantidad)) + 0.5) / Math.pow(10,cantidad);
    }
    
    public void Agregar_Fila()
    {
        modelotabla = (DefaultTableModel) jTable2.getModel();
        nuevafila = new Vector();
        jTable2.setModel(modelotabla);
        nuevafila.add(null);
        modelotabla.addRow(nuevafila);
    }
    
    public int Suma_Total()
    {
        int sum = 0;
        
        sum = Suma_Habilidad() + Suma_Esfuerzo() + Suma_Responsabilidad() + Suma_Condiciones();
        jTextField18.setText(String.valueOf(sum));
        return sum;
    }
    
    public int Suma_Habilidad()
    {
        int sum = 0;
        sum = Integer.parseInt(jTextField5.getText()) + Integer.parseInt(jTextField6.getText()) + Integer.parseInt(jTextField7.getText());
        jTextField4.setText(String.valueOf(sum));
        return sum;
    }
    
    public int Suma_Esfuerzo()
    {
        int sum = 0;
        sum = Integer.parseInt(jTextField9.getText()) + Integer.parseInt(jTextField10.getText());
        jTextField8.setText(String.valueOf(sum));
        return sum;
    }
    
    public int Suma_Responsabilidad()
    {
        int sum = 0;
        sum = Integer.parseInt(jTextField12.getText()) + Integer.parseInt(jTextField13.getText()) + Integer.parseInt(jTextField14.getText());
        jTextField11.setText(String.valueOf(sum));
        return sum;
    }
    
    public int Suma_Condiciones()
    {
        int sum = 0;
        sum = Integer.parseInt(jTextField16.getText()) + Integer.parseInt(jTextField17.getText());
        jTextField15.setText(String.valueOf(sum));
        return sum;
    }
    
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton12.setText(String.valueOf(Integer.parseInt(jButton12.getText()) + 1));
        
        if(Integer.parseInt(jButton12.getText()) > 5)
            jButton12.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton12.getText()) > 4)
                jButton12.setText("1");
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton11.setText(String.valueOf(Integer.parseInt(jButton11.getText()) + 1));
        
        if(Integer.parseInt(jButton11.getText()) > 5)
            jButton11.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton11.getText()) > 4)
                jButton11.setText("1");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton10.setText(String.valueOf(Integer.parseInt(jButton10.getText()) + 1));
        
        if(Integer.parseInt(jButton10.getText()) > 5)
            jButton10.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton10.getText()) > 4)
                jButton10.setText("1");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton9.setText(String.valueOf(Integer.parseInt(jButton9.getText()) + 1));
        
        if(Integer.parseInt(jButton9.getText()) > 5)
            jButton9.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton9.getText()) > 4)
                jButton9.setText("1");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton8.setText(String.valueOf(Integer.parseInt(jButton8.getText()) + 1));
        
        if(Integer.parseInt(jButton8.getText()) > 5)
            jButton8.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton8.getText()) > 4)
                jButton8.setText("1");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton7.setText(String.valueOf(Integer.parseInt(jButton7.getText()) + 1));
        
        if(Integer.parseInt(jButton7.getText()) > 5)
            jButton7.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton7.getText()) > 4)
                jButton7.setText("1");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton6.setText(String.valueOf(Integer.parseInt(jButton6.getText()) + 1));
        
        if(Integer.parseInt(jButton6.getText()) > 5)
            jButton6.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton6.getText()) > 4)
                jButton6.setText("1");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton5.setText(String.valueOf(Integer.parseInt(jButton5.getText()) + 1));
        
        if(Integer.parseInt(jButton5.getText()) > 5)
            jButton5.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton5.getText()) > 4)
                jButton5.setText("1");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton4.setText(String.valueOf(Integer.parseInt(jButton4.getText()) + 1));
        
        if(Integer.parseInt(jButton4.getText()) > 5)
            jButton4.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton4.getText()) > 4)
                jButton4.setText("1");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO: Agrege su codigo aqui:
        jButton3.setText(String.valueOf(Integer.parseInt(jButton3.getText()) + 1));
        
        if(Integer.parseInt(jButton3.getText()) > 5)
            jButton3.setText("1");
        
        if(jRadioButtonMenuItem2.isSelected() == true)
        {
            if(Integer.parseInt(jButton3.getText()) > 4)
                jButton3.setText("1");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
// TODO: Agrege su codigo aqui:
        Cerrar();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    public void Cerrar()
    {
        JOptionPane.showMessageDialog(null,"Gracias por usar JPositionRating 2008","ChicoSoft",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
// TODO: Agrege su codigo aqui:
        puntaje = 0;
        g = 0;
        Limpiar();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jTextField1ActionPerformed

private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
// TODO add your handling code here:
    JGenerar gen = new JGenerar();
    
    gen.jTextArea1.setText(Generar_Operaciones());
}//GEN-LAST:event_jMenuItem9ActionPerformed

public String Generar_Operaciones()
{
    String res = null;
    
    if(jRadioButtonMenuItem1.isSelected() == true)
            res = Operaciones_Aritmeticas();
        else
            res = Operaciones_Geometricas();
    
    return res;
}

public String Operaciones_Aritmeticas()
{
    String res = null;
    
    res = d.Puesto(this.jTextField1.getText(), this.jTextField2.getText(), this.jTextField3.getText());
    res += G;
    res += d.Form_Coef();
    res += C;
    res += d.Form_Sal();
    res += S;
    
    return res;
}

public String Operaciones_Geometricas()
{
    String res = null;
    
    res = d.Puesto(this.jTextField1.getText(), this.jTextField2.getText(), this.jTextField3.getText());
    res += d.Form_Pto();
    res += L;
    res += d.Form_Rango();
    res += Rang;
    res += d.Form_Coef();
    res += C;
    res += d.Form_Sal();
    res += S;
    
    return res;
}

private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jMenu5ActionPerformed

private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
// TODO add your handling code here:
    puntaje = 0;
    g = 0;
    Limpiar();
}//GEN-LAST:event_jButton20ActionPerformed

private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
// TODO add your handling code here:
    Abrir();
}//GEN-LAST:event_jButton21ActionPerformed

private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
// TODO add your handling code here:
    Guardar();
}//GEN-LAST:event_jButton22ActionPerformed

private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
// TODO add your handling code here:
    if(jRadioButtonMenuItem1.isSelected() == true)
        Aritmetico();
    else
        Geometrico();
    Restriccion_Ejecutar();
}//GEN-LAST:event_jButton23ActionPerformed

private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
// TODO add your handling code here:
    JGenerar gen = new JGenerar();
    
    gen.jTextArea1.setText(Generar_Operaciones());
}//GEN-LAST:event_jButton24ActionPerformed

private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
// TODO add your handling code here:
    if(this.jTabbedPane1.getSelectedIndex() == 1)
        this.Imprimir_Asignacion_Punto();
    
    if(this.jTabbedPane1.getSelectedIndex() == 2)
        this.Imprimir_Tabla_Salarial();
}//GEN-LAST:event_jButton25ActionPerformed

private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
// TODO add your handling code here:
    this.Llamar_Calculadora();
}//GEN-LAST:event_jButton26ActionPerformed

private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
// TODO add your handling code here:
    Contenido con = new Contenido();
}//GEN-LAST:event_jButton27ActionPerformed

private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
// TODO add your handling code here:
    this.Cerrar();
}//GEN-LAST:event_jButton28ActionPerformed

private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
// TODO add your handling code here:
    this.jRadioButtonMenuItem1.setSelected(true);
    this.jRadioButtonMenuItem2.setSelected(false);
    Radio1();
}//GEN-LAST:event_jButton29ActionPerformed

private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
// TODO add your handling code here:
    this.jRadioButtonMenuItem1.setSelected(false);
    this.jRadioButtonMenuItem2.setSelected(true);
    Radio2();
}//GEN-LAST:event_jButton30ActionPerformed

private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
// TODO add your handling code here:
    if(this.jTabbedPane1.getSelectedIndex() == 1)
        this.Exportar_Accion1();
    
    if(this.jTabbedPane1.getSelectedIndex() == 2)
        this.Exportar_Accion2();
}//GEN-LAST:event_jButton31ActionPerformed

private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
// TODO add your handling code here:
    this.Llamar_Salario_Minimo();
}//GEN-LAST:event_jButton32ActionPerformed

private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
// TODO add your handling code here:
    this.Llamar_Salario_Minimo();
}//GEN-LAST:event_jMenuItem10ActionPerformed

private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
// TODO add your handling code here:
    Instalar_FoxitReader();
}//GEN-LAST:event_jMenuItem11ActionPerformed

public void Instalar_FoxitReader()
{
    try
    {
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("Instalar_FoxitReader.bat");
        p.waitFor();
    }
    catch(Exception e)
    {
    }
}

    public void Limpiar()
    {
        // Evaluación de la Conserje
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        
        Limpiar_Campo();
        
        jTextField19.setText("");
        jTextField20.setText("");
        jTextField21.setText("");
        jTextField22.setText("");
        
        jButton3.setText("1");
        jButton4.setText("1");
        jButton5.setText("1");
        jButton6.setText("1");
        jButton7.setText("1");
        jButton8.setText("1");
        jButton9.setText("1");
        jButton10.setText("1");
        jButton11.setText("1");
        jButton12.setText("1");
        
        jButton13.setText("1");
        jButton14.setText("1");
        jButton15.setText("1");
        jButton16.setText("1");
        
        Limpiar_Tabla();
    }
    
    public void Limpiar_Campo()
    {
        jTextField4.setText("");
        
        // Habilidad
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        
        jTextField8.setText("");
        
        // Esfuerzo
        jTextField9.setText("");
        jTextField10.setText("");
        
        jTextField11.setText("");
        
        // Responsabilidad
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        
        jTextField15.setText("");
        
        // Condiciones de trabajo
        jTextField16.setText("");
        jTextField17.setText("");
        
        jTextField18.setText("");
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                
                // Aplicar HiFiLookAndFeel a nuestra aplicación
                try
                {
                    UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
                }
                catch(Exception e)
                {
                        try
                        {
                            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                        }
                        catch(Exception err)
                        {
                            System.out.println("Error loading myXPStyleTheme: " + err.toString());
                        }
                }
                
                if(Fecha_Expiracion() == true)
                {
                    Final();
                }
                else
                {
                    Expiracion();
                }
                
                //JExp je = new JExp(); // autorizado a ciertas personas
            }
        });
        //JPositionRating valuacion = new JPositionRating();
        //valuacion.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void Verificar_Entrada()
    {
        if(JExp.b == true)
            new JPositionRating().setVisible(true);
        else
        {
            JOptionPane.showMessageDialog(null,"Contraseña incorrecta","JPositionRating 2008",JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }
              
    public static boolean Fecha_Expiracion()
    {
        Date hoy = new Date();
        Date fecha = new Date();
        
        fecha.setYear(2020); // Esta es la fecha de expiración.
        
        //System.out.println(hoy.getYear() + 1900);
        //System.out.println(fecha.getYear());
        
        if((hoy.getYear() + 1900) >= fecha.getYear())        
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static void Final()
    {
        JOptionPane.showMessageDialog(null,"El programa ha expirado, si desea comprarlo envíe un mensaje a: ingenierojosefrancisco@gmail.com","JConsolidado",JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }
    
    public static void Expiracion()
    {
        Fichero = new File("Config.exe");
                
        if(!Fichero.exists())
        {
             JOptionPane.showMessageDialog(null,"Error, el programa necesita el archivo Config.exe","Error",JOptionPane.ERROR_MESSAGE);
             System.exit(0);
        }
        else
        {
             if(Demostrar() == false)
             {
                  Final();
             }
             else
             {
                  new JPositionRating().setVisible(true);
             }
        }
    }
    
    public static boolean Demostrar()
    {
        Fichero = new File("Config.exe");
        
	leerFichero3();
	escribirFichero3();
		
	return condicionar();
    }
    
    public static void leerFichero3()
    {
	// abrir archivo
	try
	{
		leer3 = new DataInputStream(new FileInputStream(Fichero));
		int num;
                
		num = leer3.readInt();
		numero = num;
                
                System.out.println(String.valueOf(numero));
                
		leer3.close();
	}
			
	// procesa la excepcion al abrir el archivo
	catch(Exception e)
	{
		//JOptionPane.showMessageDialog(this,"Error al abrir el archivo","Error",JOptionPane.ERROR_MESSAGE);
	}
    }
		
    public static void escribirFichero3()
    {
	try
	{
		escribir3 = new DataOutputStream(new FileOutputStream(Fichero));
		int num = numero;
                
		num = num + 1;
		
		escribir3.writeInt(num);
		escribir3.close();
	}
	// mostrar mensaje de error si no se puede abrir el archivo
	catch(IOException ioException)
	{
		//JOptionPane.showMessageDialog(this,"Error al abrir el archivo","Error",JOptionPane.ERROR_MESSAGE);
	}
    }
	
    public static boolean condicionar()
    {
        System.out.println("Limite: 3");
        
	if(numero >= 3)
		return false;
	else
		return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
    
}
