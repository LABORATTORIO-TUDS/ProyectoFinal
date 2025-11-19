/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.*;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.*;

/**
 *
 * @author elias
 */
public class TicketCompraData {
    
    private Connection con = null;
    private final CompradorData compData;
    private ProyeccionData proyData;
    private DetalleTicketData detData;

    public TicketCompraData() {
        this.con = Conexion.conectar();
        this.compData = new CompradorData(); 
        this.proyData = new ProyeccionData();
        this.detData = new DetalleTicketData();
    }

    public boolean guardarTicket(TicketCompra ticket) {
      
        String sqlTicket = "INSERT INTO TICKETCOMPRA (fechaCompra, codProyeccion, montoTotal, dni) VALUES (?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO DETALLETICKET (subtotal, codTicket, codAsiento) VALUES (?, ?, ?)";
        
        try {
            con.setAutoCommit(false);
            
            try ( 
                    PreparedStatement psTicket = con.prepareStatement(sqlTicket, Statement.RETURN_GENERATED_KEYS)) {
                psTicket.setDate(1, new java.sql.Date(ticket.getFechaCompra().getTime()));
                psTicket.setInt(2, ticket.getProyeccion().getCodProyeccion());
                psTicket.setDouble(3, ticket.getMontoTotal());
                psTicket.setInt(4, ticket.getComprador().getDni());
                
                psTicket.executeUpdate();
                
          
                ResultSet rs = psTicket.getGeneratedKeys();
                if (rs.next()) {
                    ticket.setCodTicket(rs.getInt(1)); 
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo obtener el ID del ticket.");
                    con.rollback(); 
                    return false;
                }
            }

            PreparedStatement psDetalle = con.prepareStatement(sqlDetalle);
            for (DetalleTicket detalle : ticket.getDetalles()) {
                psDetalle.setDouble(1, detalle.getSubtotal());
                psDetalle.setInt(2, ticket.getCodTicket()); 
                psDetalle.setInt(3, detalle.getAsiento().getCodAsiento());
                
                psDetalle.executeUpdate();
            }
            psDetalle.close();

            
            con.commit();
            JOptionPane.showMessageDialog(null, "Compra registrada con exito. Codigo de ticket: " + ticket.getCodTicket());
            return true;
            
        } catch (SQLException ex) {
         
            try {
                con.rollback(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al revertir la transaccion: " + e.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Error al guardar el ticket: " + ex.getMessage());
            return false;
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al restaurar auto-commit: " + ex.getMessage());
            }
        }
    }
    
    public List<TicketCompra> listarTicketsPorComprador(int dni) {
        List<TicketCompra> tickets = new ArrayList<>();
        String sql = "SELECT * FROM TICKETCOMPRA WHERE dni = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                TicketCompra ticket = new TicketCompra();
                ticket.setCodTicket(rs.getInt("codTicket"));
                ticket.setFechaCompra(rs.getDate("fechaCompra"));
                ticket.setMontoTotal(rs.getDouble("montoTotal"));
                
             
                ticket.setComprador(compData.buscarCompradorPorDni(dni));
                ticket.setProyeccion(proyData.buscarProyeccion(rs.getInt("codProyeccion")));
                
                
                ticket.setDetalles(detData.buscarDetallesPorTicket(ticket.getCodTicket()));
                
                tickets.add(ticket);
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar tickets: " + ex.getMessage());
        }
        
        return tickets;
    }
    
    public TicketCompra buscarTicketPorId(int codTicket) {
    TicketCompra ticket = null;
    String sql = "SELECT * FROM ticketcompra WHERE codTicket = ?";
    
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, codTicket);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            ticket = new TicketCompra();
            ticket.setCodTicket(rs.getInt("codTicket"));
            ticket.setFechaCompra(rs.getDate("fechaCompra"));
            ticket.setMontoTotal(rs.getDouble("montoTotal")); 
            
            
            int dniComprador = rs.getInt("dni");
            int codProyeccion = rs.getInt("codProyeccion");
            
            ticket.setComprador(compData.buscarCompradorPorDni(dniComprador));
            ticket.setProyeccion(proyData.buscarProyeccion(codProyeccion));
            
           
            ticket.setDetalles(detData.buscarDetallesPorTicket(ticket.getCodTicket()));
            
        } else {
            
        }
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al buscar el ticket: " + ex.getMessage());
    }
    
    return ticket;
}
    
    public void eliminarTicket(int codTicket) {
    String sql = "DELETE FROM ticketcompra WHERE codTicket = ?";
    
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, codTicket);
        
        int exito = ps.executeUpdate();
        
        if (exito == 1) {
           
            JOptionPane.showMessageDialog(null, "El Ticket N° " + codTicket + " ha sido eliminado permanentemente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el Ticket para eliminar.");
        }
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al eliminar el ticket: " + ex.getMessage());
    }
}
    
}
