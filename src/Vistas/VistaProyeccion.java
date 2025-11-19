/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import Modelo.Sala;
import Persistencia.PeliculaData;
import Persistencia.SalaData;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author andre
 */
public class VistaProyeccion extends javax.swing.JPanel {

   private PeliculaData pD;
   private SalaData sD;
   
    public VistaProyeccion() {
        initComponents();
         pD = new PeliculaData();
         sD = new SalaData();      
         
         cargarSalas();
    }
    
    public void cargarSalas(){
        List<Sala> salitas = sD.listarSalas();
        DefaultComboBoxModel<Sala> modeloSalas = new DefaultComboBoxModel<>();
        
        for(Sala a : salitas){
            modeloSalas.addElement(a);
        }
        
        jCBSalas.setModel(modeloSalas);
        if (!salitas.isEmpty()){
            jCBSalas.setSelectedIndex(0);                           
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jCBPeliculas = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jCBSalas = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jCBIdiomas = new javax.swing.JComboBox<>();
        jCheckBox_Es3D = new javax.swing.JCheckBox();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox_EsSubtitulada = new javax.swing.JCheckBox();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSpinner_HoraInicio = new javax.swing.JSpinner();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSpinner_HoraFin = new javax.swing.JSpinner();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jTFPrecio = new javax.swing.JTextField();
        jBGuardar = new javax.swing.JButton();
        jBLimpiar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jBEliminar = new javax.swing.JButton();
        jBModificar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTabla = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(670, 664));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel1.setForeground(java.awt.SystemColor.activeCaptionText);
        jLabel1.setText("PELICULA A PROYECTAR");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, 23));

        jCBPeliculas.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jCBPeliculas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(jCBPeliculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 220, -1));

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel2.setForeground(java.awt.SystemColor.activeCaptionText);
        jLabel2.setText("SALA DONDE SERA LA PROYECCION");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 340, -1));

        jCBSalas.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        add(jCBSalas, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 220, -1));

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel3.setForeground(java.awt.SystemColor.activeCaptionText);
        jLabel3.setText("IDIOMA");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jCBIdiomas.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jCBIdiomas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(jCBIdiomas, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 220, -1));

        jCheckBox_Es3D.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jCheckBox_Es3D.setForeground(java.awt.SystemColor.activeCaptionText);
        jCheckBox_Es3D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_Es3DActionPerformed(evt);
            }
        });
        add(jCheckBox_Es3D, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, -1, 20));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 640, 10));

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel4.setForeground(java.awt.SystemColor.activeCaptionText);
        jLabel4.setText("ES SUBTITULADA");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, 20));

        jCheckBox_EsSubtitulada.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jCheckBox_EsSubtitulada.setForeground(java.awt.SystemColor.activeCaptionText);
        jCheckBox_EsSubtitulada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_EsSubtituladaActionPerformed(evt);
            }
        });
        add(jCheckBox_EsSubtitulada, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 20, 20));
        add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 640, 10));

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jLabel5.setForeground(java.awt.SystemColor.activeCaptionText);
        jLabel5.setText("HORA DE INICIO Y DE FINAL");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));
        add(jSpinner_HoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 70, -1));
        add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 650, 10));

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel6.setForeground(java.awt.SystemColor.activeCaptionText);
        jLabel6.setText("ES 3D");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, -1, 20));
        add(jSpinner_HoraFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 70, -1));
        add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, 60, 10));

        jLabel7.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel7.setForeground(java.awt.SystemColor.activeCaptionText);
        jLabel7.setText("PRECIO");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, 50));

        jTFPrecio.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        add(jTFPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 180, 30));

        jBGuardar.setText("Guardar");
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });
        add(jBGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 610, 100, 50));

        jBLimpiar.setText("Limpiar");
        add(jBLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 610, 100, 50));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 650, 10));

        jBEliminar.setText("Eliminar");
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
            }
        });
        add(jBEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 610, 100, 50));

        jBModificar.setText("Modificar");
        add(jBModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 610, 100, 50));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "codProyeccion", "nroSala", "pelicula", "Idioma", "subtitulada", "es3D", "hora de inicio", "hora de fin", "precio"
            }
        ));
        jTabla.setPreferredSize(new java.awt.Dimension(675, 100));
        jScrollPane3.setViewportView(jTabla);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 650, 220));
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox_EsSubtituladaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_EsSubtituladaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_EsSubtituladaActionPerformed

    private void jCheckBox_Es3DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_Es3DActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_Es3DActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBLimpiar;
    private javax.swing.JButton jBModificar;
    private javax.swing.JComboBox<String> jCBIdiomas;
    private javax.swing.JComboBox<String> jCBPeliculas;
    private javax.swing.JComboBox<Sala> jCBSalas;
    private javax.swing.JCheckBox jCheckBox_Es3D;
    private javax.swing.JCheckBox jCheckBox_EsSubtitulada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSpinner jSpinner_HoraFin;
    private javax.swing.JSpinner jSpinner_HoraInicio;
    private javax.swing.JTextField jTFPrecio;
    private javax.swing.JTable jTabla;
    // End of variables declaration//GEN-END:variables
}