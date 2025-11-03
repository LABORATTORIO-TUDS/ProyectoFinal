/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Asiento;
import Modelo.Conexion;
import Modelo.DetalleTicket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author crist
 */
public class DetalleTicketData {

    private Connection con = null;
    private AsientoData asientoData;

    public DetalleTicketData() {
        this.con = Conexion.conectar();
        this.asientoData = new AsientoData();
    }

    public List<DetalleTicket> buscarDetallesPorTicket(int codTicket) {
        List<DetalleTicket> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalleticket WHERE codTicket = ?";
        
    
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, codTicket);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    DetalleTicket detalle = new DetalleTicket();
                    detalle.setSubtotal(rs.getDouble("subtotal"));
                    
                    int codAsiento = rs.getInt("codAsiento");
                    Asiento asiento = asientoData.buscarAsiento(codAsiento);
                    detalle.setAsiento(asiento);
                    
                    detalles.add(detalle);
                }
         
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar detalles: " + ex.getMessage());
        }
        
        return detalles;
    }
}