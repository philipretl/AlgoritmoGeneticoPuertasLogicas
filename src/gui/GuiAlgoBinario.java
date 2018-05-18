/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import logica.CompuertasGeneticas;

/**
 *
 * @author philipretl
 */
public class GuiAlgoBinario extends javax.swing.JFrame implements Serializable {
	CompuertasGeneticas compGen;
        ArrayList<String> letras;
        int n,m;
        int[][] matriz;
	/**
	 * Creates new form GuiAlgoBinario
	 */
	public GuiAlgoBinario() {
		initComponents();
                n=0;
                m=0;
                letras=new ArrayList<>();
                txtATabla.setEditable(false);
		txtAExpresion.setEditable(false);
                iniciarLetras();
	}
	
	public void iniciarLetras(){
            letras.add("A");
            letras.add("B");
            letras.add("C");
            letras.add("D");
            letras.add("F");
            letras.add("G");
            letras.add("H");
            letras.add("I");
            letras.add("J");
        }
	public void ensayo(){
		compGen.arrancar();
	
	}
        
        public int[][] cargarMatriz(File fichero){
            ArrayList<String[]> lineas= new ArrayList<>();
            try {
                FileReader fr = new FileReader(fichero);
                BufferedReader br = new BufferedReader(fr);
 
                String linea;
                while((linea = br.readLine()) != null){
                    String[] split = linea.split(";");
                    lineas.add(split);
                    //m=m+1;
                    //n=split.length;
                }
                fr.close();
            }
            catch(IOException e) {
                System.out.println("Excepcion leyendo fichero "+ fichero + ": " + e);
            }
            
            n=lineas.size();
            m=lineas.get(0).length;
            
            int [][] matriz = new int[n][m];
           
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matriz[i][j]=Integer.parseInt(lineas.get(i)[j]);
                }
            }
           
            
           return matriz;
        }
        
        public void mostrarMatriz(int[][] matriz){
            String cadena="";
            int n;
            n=matriz[0].length;
            for (int i = 0; i < n; i++) {
                cadena=cadena+"  |  "+letras.get(i);
            }
            cadena=cadena+"\n";
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[j].length; j++) {
                    cadena=cadena+"  |  "+Integer.toString(matriz[i][j]);
                }
                cadena=cadena+"\n";
            }
            txtATabla.setText(cadena);
        }
	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDatos = new javax.swing.JPanel();
        txtRuta = new javax.swing.JTextField();
        btnExaminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNumIndividuos = new javax.swing.JTextField();
        pnlOpciones = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAExpresion = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtATabla = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 153));

        pnlDatos.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatos.setBorder(new javax.swing.border.MatteBorder(null));

        txtRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutaActionPerformed(evt);
            }
        });

        btnExaminar.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        btnExaminar.setText("Examinar");
        btnExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExaminarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri Light", 2, 14)); // NOI18N
        jLabel1.setText("Seleccionar archivo");

        btnIniciar.setBackground(new java.awt.Color(255, 255, 255));
        btnIniciar.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        btnIniciar.setText("Iniciar");
        btnIniciar.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 204)));
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("DATOS");

        jLabel7.setFont(new java.awt.Font("Calibri Light", 2, 14)); // NOI18N
        jLabel7.setText("Numero de individuos:");

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(txtRuta)
                .addGap(18, 18, 18)
                .addComponent(btnExaminar)
                .addGap(70, 70, 70))
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(pnlDatosLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNumIndividuos, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNumIndividuos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExaminar))
                .addGap(30, 30, 30)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        pnlOpciones.setBackground(new java.awt.Color(255, 255, 255));
        pnlOpciones.setBorder(new javax.swing.border.MatteBorder(null));

        txtAExpresion.setColumns(20);
        txtAExpresion.setRows(5);
        jScrollPane2.setViewportView(txtAExpresion);

        jLabel5.setFont(new java.awt.Font("Calibri Light", 1, 16)); // NOI18N
        jLabel5.setText("Resultados");

        jLabel3.setFont(new java.awt.Font("Calibri Light", 2, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Error:");

        lblError.setFont(new java.awt.Font("Calibri Light", 2, 14)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 51, 51));
        lblError.setText("-");

        javax.swing.GroupLayout pnlOpcionesLayout = new javax.swing.GroupLayout(pnlOpciones);
        pnlOpciones.setLayout(pnlOpcionesLayout);
        pnlOpcionesLayout.setHorizontalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesLayout.createSequentialGroup()
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOpcionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(pnlOpcionesLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3)
                        .addGap(86, 86, 86)
                        .addComponent(lblError))
                    .addGroup(pnlOpcionesLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pnlOpcionesLayout.setVerticalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblError)
                    .addComponent(jLabel3))
                .addGap(39, 39, 39))
        );

        txtATabla.setColumns(20);
        txtATabla.setRows(5);
        jScrollPane1.setViewportView(txtATabla);

        jLabel4.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel4.setText("Tabla de verdad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarActionPerformed
        // TODO add your handling code here:
        //Creamos el objeto JFileChooser
        
        if(txtNumIndividuos.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Por favor ingrese el numero de inviduos a generar");
        }else{
            JFileChooser fc=new JFileChooser();
 
            //Creamos el filtro
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.txt", "txt");
 
            //Le indicamos el filtro
            fc.setFileFilter(filtro);
            //Abrimos la ventana, guardamos la opcion seleccionada por el usuario
            int seleccion=fc.showOpenDialog(pnlDatos);
 
            //Si el usuario, pincha en aceptar
            if(seleccion==JFileChooser.APPROVE_OPTION){
 
                //Seleccionamos el fichero
                File fichero=fc.getSelectedFile();
 
                //Ecribe la ruta del fichero seleccionado en el campo de texto
                txtRuta.setText(fichero.getAbsolutePath());
            
                 matriz = cargarMatriz(fichero);
                
                //compGen= new CompuertasGeneticas(n,m,Integer.parseInt(txtNumIndividuos.getText()));
                mostrarMatriz(matriz);
                //compGen.setTabla(matriz);    
            }
        }
    }//GEN-LAST:event_btnExaminarActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
        if((txtRuta.getText().equals("")) || (txtNumIndividuos.getText().equals(""))){
            JOptionPane.showMessageDialog(null, "Por favor llene los campos");
        
        }else{
            // solucionado pequeño problema a la hora de que se carguen nuevos archivos y se modifiquen los 
            //individuos
            
            compGen= new CompuertasGeneticas(n,m,Integer.parseInt(txtNumIndividuos.getText()));
            //mostrarMatriz(matriz);
            compGen.setTabla(matriz);    
            
            compGen.arrancar();
            JOptionPane.showMessageDialog(null, "Proceso Terminado");
            double error=compGen.getArboles().get(0).getErrorTotal();
            error=error*100;
            
            if(error>=100){
                error=100;
            }
            lblError.setText(String.valueOf(error)+" % ");
            String recorridoPreorden = compGen.getArboles().get(0).recorridoPreorden();
            txtAExpresion.setText(recorridoPreorden);
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void txtRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutaActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GuiAlgoBinario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GuiAlgoBinario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GuiAlgoBinario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GuiAlgoBinario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GuiAlgoBinario().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExaminar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblError;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlOpciones;
    private javax.swing.JTextArea txtAExpresion;
    private javax.swing.JTextArea txtATabla;
    private javax.swing.JTextField txtNumIndividuos;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}
