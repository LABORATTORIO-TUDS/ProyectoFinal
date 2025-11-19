/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import Modelo.Pelicula;
import Modelo.Proyeccion;
import Modelo.Sala;
import Persistencia.PeliculaData;
import Persistencia.ProyeccionData;
import Persistencia.SalaData;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andre
 */
public class VistaProyeccion extends javax.swing.JPanel {

   private PeliculaData pD;
   private SalaData sD;
   private ProyeccionData proyD;
    public VistaProyeccion() {
        initComponents();
         pD = new PeliculaData();
         sD = new SalaData();      
         proyD = new ProyeccionData();
         
         SpinnerDateModel modelInicio = new SpinnerDateModel();
        jspHoraInicio.setModel(modelInicio);
        JSpinner.DateEditor editorInicio = new JSpinner.DateEditor(jspHoraInicio, "HH:mm");
        jspHoraInicio.setEditor(editorInicio);

        // Configurar jspHoraFin
        SpinnerDateModel modelFin = new SpinnerDateModel();
        jspHoraFin.setModel(modelFin);
        JSpinner.DateEditor editorFin = new JSpinner.DateEditor(jspHoraFin, "HH:mm");
        jspHoraFin.setEditor(editorFin);
        
         jBEditar.setEnabled(false);
         jBModificar.setEnabled(false);
         jBEliminar.setEnabled(false);
        
        
        jTabla.getSelectionModel().addListSelectionListener(e -> {
        boolean hayFilaSeleccionada = jTabla.getSelectedRow() != -1;
            jBEditar.setEnabled(hayFilaSeleccionada);
            jBModificar.setEnabled(hayFilaSeleccionada);
            jBEliminar.setEnabled(hayFilaSeleccionada);
        }); 
         
         
         cargarPeliculas();
         cargarSalas();
         cargarTablaProyecciones();
    }
    
    public void cargarSalas(){
        List<Sala> salitas = sD.listarSalas();
        jCBSalas.removeAllItems();
        
        for(Sala a : salitas){
           if(a.getEstado().equals("LIBRE")){
               jCBSalas.addItem(a);
           }
        }          
    }
    public void cargarPeliculas(){
        List<Pelicula> peliculitas = pD.listarPeliculasEnCartelera();
        jCBPeliculas.removeAllItems();
        for (Pelicula peli : peliculitas){
            jCBPeliculas.addItem(peli);
        }
        
<<<<<<< HEAD
=======
        jCBSalas.setModel(modeloSalas);
        if (!salitas.isEmpty()){
            jCBSalas.setSelectedIndex(0);                           
        }
>>>>>>> d015a31c0cbbd2a2c039e146bb11731b85361ec7
    }
    public void cargarTablaProyecciones(){
        String[] columnas = { "Código", "Idioma", "Es 3D", "Subtitulada", 
                     "Hora Inicio", "Hora Fin", "Precio", 
                     "Título", "Director", "Sala"};    
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        
        
        List<Proyeccion> proy = proyD.listaProyecciones();
        for(Proyeccion p : proy){
           Object[] fila = new Object[10];
           fila[0] = p.getCodProyeccion();
           fila[1] = p.getIdioma();
           fila[2] = p.isEs3D();          // booleano (true/false)
           fila[3] = p.isSubtitulada();   // booleano
           fila[4] = p.getHoraInicio();   // tipo LocalTime
           fila[5] = p.getHoraFin();      // tipo LocalTime
           fila[6] = p.getPrecio();
           fila[7] = p.getTitulo();
           fila[8] = p.getDirector();
           fila[9] = p.getNroSala();
           model.addRow(fila);
         }
        jTabla.setModel(model);             
       }     
    
<<<<<<< HEAD
       private void eliminarProyeccion(){
           
         try{  
           int filaSel = jTabla.getSelectedRow();
           int codigo = (int) jTabla.getValueAt(filaSel, 0);
           
           Proyeccion proy = new Proyeccion();
           proy.setCodProyeccion(codigo);
           
           proyD.bajarProyeccion(proy);
           cargarTablaProyecciones();
         }catch(Exception ex){
             JOptionPane.showMessageDialog(this, "No se pudo eliminar la proyeccion: " + ex);
         } 
       }
       
       private void crearProyeccion(){
           try {
               // Aca obtengo los valores que seleccione en la vista
               Pelicula peli = (Pelicula) jCBPeliculas.getSelectedItem();
               Sala salita = (Sala) jCBSalas.getSelectedItem();
               String idioma = (String) jCBIdiomas.getSelectedItem();
               boolean es3D = jCheckBox_Es3D.isSelected();
               boolean subtitulada  = jCheckBox_EsSubtitulada.isSelected();
               
               //Aca valido si la sala es apta para 3D
               if(es3D && !salita.isApta3D()){
                   JOptionPane.showMessageDialog(this, "La sala seleccionada no es apta para 3D");
                   return;
               }
               
               // Aca Obtenes las horas desde los jSpinner
               Date horaInicioDate = (Date) jspHoraInicio.getValue();
               Date horaFinDate = (Date) jspHoraFin.getValue();
               LocalTime horaInicio = horaInicioDate.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
               LocalTime horaFin = horaFinDate.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
               
               // Hay que validar si es que la hora de inicio es antes de la hora final, sino no tiene sentido.
               if (!horaFin.isAfter(horaInicio)){
                   JOptionPane.showMessageDialog(this, "La hora de inicio seleccionada tiene que ser anterior a la hora del final de la proyeccion");
                   return;
               }
               
               //Obtener el precio jeje
               double precio;
               //Convertimos el precio del text field a un double
               try { 
                   precio = Double.parseDouble(jTFPrecio.getText());
                   if(precio <= 0){
                       JOptionPane.showMessageDialog(this, "El  precio debe ser mayor que cero");
                       return;
                   }
                   
                   
                  }catch(NumberFormatException e) {
                      JOptionPane.showMessageDialog(this, "Formato de precio incorrecto" + e);
                      return;
                  }
               //Creamos el objeto Proyeccion asi lo guardamos
               Proyeccion proy = new Proyeccion(idioma, es3D, subtitulada, horaInicio, horaFin, precio, peli.getTitulo(), peli.getDirector(), salita.getNroSala());
               
               //Ahora le metemos el objeto que creamos bien adentro de la base de datoss
               proyD.crearProyeccion(proy);
               
               //Refrescamos la vista papus
               limpiarFormulario();
               cargarTablaProyecciones();
               
           }catch(Exception ex){
               JOptionPane.showMessageDialog(this, "Error al guardar proyeccion:" + ex);
           }
           
           
       }
       
       public void editarProyeccion(){
           int filaSel = jTabla.getSelectedRow();
            if (filaSel == -1) {
                JOptionPane.showMessageDialog(this, "Seleccioná una proyección para editar.");
                return;
            }

            try {
                // Titulo y director vienen como String desde la tabla
                String titulo = (String) jTabla.getValueAt(filaSel, 7);
                String director = (String) jTabla.getValueAt(filaSel, 8);
                int nroSala = (int) jTabla.getValueAt(filaSel, 9);

                // Buscar la Pelicula correspondiente en el combo
                for (int i = 0; i < jCBPeliculas.getItemCount(); i++) {
                    Pelicula peli = jCBPeliculas.getItemAt(i);
                    if (peli.getTitulo().equals(titulo) && peli.getDirector().equals(director)) {
                        jCBPeliculas.setSelectedIndex(i);
                        break;
                    }
                }

                // Buscar la Sala correspondiente en el combo
                for (int i = 0; i < jCBSalas.getItemCount(); i++) {
                    Sala sala = jCBSalas.getItemAt(i);
                    if (sala.getNroSala() == nroSala) {
                        jCBSalas.setSelectedIndex(i);
                        break;
                    }
                }

                //Le metemos el codigo de proyeccion exacto al JTF

                int codigo = (int) jTabla.getValueAt(filaSel, 0);        
                jTFCodProyeccion.setText(String.valueOf(codigo));



                // Idioma
                String idioma = (String) jTabla.getValueAt(filaSel, 1);
                jCBIdiomas.setSelectedItem(idioma);

                // 3D y Subtitulada
                jCheckBox_Es3D.setSelected((boolean) jTabla.getValueAt(filaSel, 2));
                jCheckBox_EsSubtitulada.setSelected((boolean) jTabla.getValueAt(filaSel, 3));

                // Horarios
                LocalTime horaInicio = (LocalTime) jTabla.getValueAt(filaSel, 4);
                LocalTime horaFin = (LocalTime) jTabla.getValueAt(filaSel, 5);

                Date dateInicio = Date.from(horaInicio.atDate(LocalDate.now())
                    .atZone(ZoneId.systemDefault()).toInstant());
                Date dateFin = Date.from(horaFin.atDate(LocalDate.now())
                    .atZone(ZoneId.systemDefault()).toInstant());

                jspHoraInicio.setValue(dateInicio);
                jspHoraFin.setValue(dateFin);

                // Precio
                double precio = (double) jTabla.getValueAt(filaSel, 6);
                jTFPrecio.setText(String.valueOf(precio));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar datos de la tabla: " + e.getMessage());
            }
           
       }
       
       public void modificarProyeccion(){
         try {
             int filaSel = jTabla.getSelectedRow();
             if (filaSel == -1) {
                 JOptionPane.showMessageDialog(this, "Debes seleccionar una fila para modificar.");
                 return;
             }

             int codProyeccion = (int) jTabla.getValueAt(filaSel, 0);

             Pelicula peli = (Pelicula) jCBPeliculas.getSelectedItem();
             Sala salita = (Sala) jCBSalas.getSelectedItem();
             String idioma = (String) jCBIdiomas.getSelectedItem();
             boolean es3D = jCheckBox_Es3D.isSelected();
             boolean subtitulada = jCheckBox_EsSubtitulada.isSelected();

             if (es3D && !salita.isApta3D()) {
                 JOptionPane.showMessageDialog(this, "La sala seleccionada no es apta para 3D");
                 return;
             }

             Date horaInicioDate = (Date) jspHoraInicio.getValue();
             Date horaFinDate = (Date) jspHoraFin.getValue();
             LocalTime horaInicio = horaInicioDate.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
             LocalTime horaFin = horaFinDate.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

             if (!horaFin.isAfter(horaInicio)) {
                 JOptionPane.showMessageDialog(this, "La hora de inicio debe ser anterior a la de fin.");
                 return;
             }

             double precio;
             try {
                 precio = Double.parseDouble(jTFPrecio.getText());
                 if (precio <= 0) {
                     JOptionPane.showMessageDialog(this, "El precio debe ser mayor que cero.");
                     return;
                 }
             } catch (NumberFormatException e) {
                 JOptionPane.showMessageDialog(this, "Formato de precio incorrecto.");
                 return;
             }

             Proyeccion proyModificada = new Proyeccion(codProyeccion, idioma, es3D, subtitulada,
                     horaInicio, horaFin, precio, peli.getTitulo(), peli.getDirector(), salita.getNroSala());

             proyD.modificarProyeccion(proyModificada);
             cargarTablaProyecciones();
             limpiarFormulario();

         } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, "Error al modificar proyección: " + ex.getMessage());
         }
       }
       
       
       
       public void limpiarFormulario(){
           //Limpio los jComboBox
           jCBPeliculas.setSelectedIndex(-1);
           jCBSalas.setSelectedIndex(-1);
           jCBIdiomas.setSelectedIndex(-1);
           //Limpio los check box
           jCheckBox_Es3D.setSelected(false);
           jCheckBox_EsSubtitulada.setSelected(false);
           //Limpio el text field
           jTFPrecio.setText("");
           //Renicio los jSpinner
           LocalTime ahora = LocalTime.now();
            Date horaActual = Date.from(ahora.atDate(LocalDate.now())
            .atZone(ZoneId.systemDefault()).toInstant());
            jspHoraInicio.setValue(horaActual);
            jspHoraFin.setValue(horaActual); 
            jTFCodProyeccion.setText("");
       }
    
=======
>>>>>>> d015a31c0cbbd2a2c039e146bb11731b85361ec7
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
        jspHoraInicio = new javax.swing.JSpinner();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jspHoraFin = new javax.swing.JSpinner();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTFCodProyeccion = new javax.swing.JTextField();
        jBEditar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(670, 664));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel1.setForeground(java.awt.SystemColor.activeCaptionText);
        jLabel1.setText("PELICULA A PROYECTAR");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, 23));

        jCBPeliculas.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
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
        jCBIdiomas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Español", "Ingles", "Frances", "Mongol", "TuAbuela" }));
        jCBIdiomas.setSelectedIndex(-1);
        add(jCBIdiomas, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 220, -1));

        jCheckBox_Es3D.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jCheckBox_Es3D.setForeground(java.awt.SystemColor.activeCaptionText);
        jCheckBox_Es3D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_Es3DActionPerformed(evt);
            }
        });
        add(jCheckBox_Es3D, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, -1, 20));
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
        add(jspHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 70, -1));
        add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 650, 10));

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel6.setForeground(java.awt.SystemColor.activeCaptionText);
        jLabel6.setText("ES 3D");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, -1, 20));
        add(jspHoraFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 70, -1));
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
        jBLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimpiarActionPerformed(evt);
            }
        });
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
        jBModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModificarActionPerformed(evt);
            }
        });
        add(jBModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 610, 100, 50));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTabla.setPreferredSize(new java.awt.Dimension(675, 100));
        jScrollPane3.setViewportView(jTabla);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 650, 220));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("CODIGO PROYECCION");
        jPanel1.add(jLabel8);

        jTFCodProyeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFCodProyeccionActionPerformed(evt);
            }
        });
        jPanel1.add(jTFCodProyeccion);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 170, 70));

        jBEditar.setText("Editar");
        jBEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditarActionPerformed(evt);
            }
        });
        add(jBEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 610, 100, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox_EsSubtituladaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_EsSubtituladaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_EsSubtituladaActionPerformed

    private void jCheckBox_Es3DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_Es3DActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_Es3DActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        crearProyeccion();
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        eliminarProyeccion();
    }//GEN-LAST:event_jBEliminarActionPerformed

    private void jTFCodProyeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFCodProyeccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFCodProyeccionActionPerformed

    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed
        editarProyeccion();
    }//GEN-LAST:event_jBEditarActionPerformed

    private void jBModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModificarActionPerformed
        modificarProyeccion();
    }//GEN-LAST:event_jBModificarActionPerformed

    private void jBLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimpiarActionPerformed
       limpiarFormulario();
    }//GEN-LAST:event_jBLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBLimpiar;
    private javax.swing.JButton jBModificar;
    private javax.swing.JComboBox<String> jCBIdiomas;
<<<<<<< HEAD
    private javax.swing.JComboBox<Pelicula> jCBPeliculas;
=======
    private javax.swing.JComboBox<String> jCBPeliculas;
>>>>>>> d015a31c0cbbd2a2c039e146bb11731b85361ec7
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextField jTFCodProyeccion;
    private javax.swing.JTextField jTFPrecio;
    private javax.swing.JTable jTabla;
    private javax.swing.JSpinner jspHoraFin;
    private javax.swing.JSpinner jspHoraInicio;
    // End of variables declaration//GEN-END:variables
}