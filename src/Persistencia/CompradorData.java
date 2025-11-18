/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Comprador;
import java.sql.*;

import Modelo.Conexion;
import Modelo.TicketCompra;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class CompradorData {
    private Connection con = null;
    private final ProyeccionData proyData;
    private final DetalleTicketData detData;
    
    public CompradorData(){
        this.con = Conexion.conectar();
        this.proyData = new ProyeccionData();
        this.detData = new DetalleTicketData();
    }
    
    public void guardarComprador(Comprador comprador) {
        String sql = "INSERT INTO COMPRADOR (dni, nombre, fechaNac, password) VALUES (?, ?, ?, ?)";
    
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, comprador.getDni());
                ps.setString(2, comprador.getNombre());
                ps.setDate(3, new java.sql.Date(comprador.getFechaNac().getTime())); 
                ps.setString(4, comprador.getPassword());
                ps.executeUpdate();
                
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Comprador registrado con DNI: " + comprador.getDni());
                }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el comprador: " + ex.getMessage());
        }
    }
    
    public void actualizarComprador(Comprador comprador) {
        String sql = "UPDATE comprador SET nombre = ?, fechaNac = ?, password = ? WHERE dni = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, comprador.getNombre());
            ps.setDate(2, new java.sql.Date(comprador.getFechaNac().getTime()));
            ps.setString(3, comprador.getPassword());
            ps.setInt(4, comprador.getDni());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Comprador actualizado con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el comprador con DNI: " + comprador.getDni());
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el comprador: " + ex.getMessage());
            ex.printStackTrace(); 
        }
    }
 
    public Comprador buscarCompradorPorDni(int dni) {
        Comprador comprador = null;
        String sql = "SELECT dni, nombre, fechaNac, password FROM COMPRADOR WHERE dni = ?";
        
      
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, dni);
                
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    comprador = new Comprador();
                    comprador.setDni(rs.getInt("dni"));
                    comprador.setNombre(rs.getString("nombre"));
                    comprador.setFechaNac(rs.getDate("fechaNac"));
                    comprador.setPassword(rs.getString("password"));
                } else {
                    JOptionPane.showMessageDialog(null, "No existe el comprador con ese DNI");
                }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Comprador: " + ex.getMessage());
        }
        
        return comprador;
    }
    
  
    public Comprador validarLogin(int dni, String password) {
        Comprador comprador = null;
        String sql = "SELECT dni, nombre, fechaNac, password FROM COMPRADOR WHERE dni = ? AND password = ?";
        
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, dni);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    comprador = new Comprador();
                    comprador.setDni(rs.getInt("dni"));
                    comprador.setNombre(rs.getString("nombre"));
                    comprador.setFechaNac(rs.getDate("fechaNac"));
                    comprador.setPassword(rs.getString("password"));
                }    
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al validar credenciales: " + ex.getMessage());
        }
        
        return comprador;
    }
    
    public void eliminarComprador(int dni) {
        String sql = "DELETE FROM comprador WHERE dni = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, dni);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Comprador eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro un comprador con ese DNI.");
            }

        } catch (SQLException ex) {
          
            if (ex.getErrorCode() == 1451) { 
                JOptionPane.showMessageDialog(null, "No se puede eliminar el comprador porque tiene tickets asociados.\n"
                        + "Debe eliminar primero sus tickets o historial de compras.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el comprador: " + ex.getMessage());
            }
        }
    }
    
    public List<Comprador> asistenciaDelDia(Date fechaDesde, Date fechaHasta) {
        
        Map<Integer, Comprador> compradoresMap = new HashMap<>();

        String sql = "SELECT "
                   + "c.dni, c.nombre, c.fechaNac, c.password, " 
                   + "tc.codTicket, tc.fechaCompra, tc.montoTotal, tc.codProyeccion " 
                   + "FROM comprador c "
                   + "INNER JOIN ticketcompra tc ON c.dni = tc.dni "
                   + "INNER JOIN proyeccion p ON tc.codProyeccion = p.codProyeccion "
                   + "WHERE p.fechaProyeccion BETWEEN ? and ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setDate(1, new java.sql.Date(fechaDesde.getTime()));
            ps.setDate(2, new java.sql.Date(fechaHasta.getTime()));
            
            try (ResultSet rs = ps.executeQuery()) {
                
                while (rs.next()) {
                    int dni = rs.getInt("dni");
                    
                    Comprador comprador = compradoresMap.get(dni);

                    if (comprador == null) {
                        comprador = new Comprador();
                        comprador.setDni(dni);
                        comprador.setNombre(rs.getString("nombre"));
                        comprador.setFechaNac(rs.getDate("fechaNac"));
                        comprador.setPassword(rs.getString("password"));
                        
                        comprador.setTickets(new ArrayList<>());
                        
                        compradoresMap.put(dni, comprador);
                    }

                    
                    TicketCompra ticket = new TicketCompra();
                    ticket.setCodTicket(rs.getInt("codTicket"));
                    ticket.setFechaCompra(rs.getDate("fechaCompra"));
                    ticket.setMontoTotal(rs.getDouble("montoTotal"));
                    
                    ticket.setComprador(comprador); 
                    ticket.setProyeccion(proyData.buscarProyeccion(rs.getInt("codProyeccion")));
                    ticket.setDetalles(detData.buscarDetallesPorTicket(ticket.getCodTicket()));

                    comprador.getTickets().add(ticket);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar asistencias: " + ex.getMessage());
            ex.printStackTrace(); 
        }

        return new ArrayList<>(compradoresMap.values());
    }
    
}
