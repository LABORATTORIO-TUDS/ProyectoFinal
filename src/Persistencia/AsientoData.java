/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
/**
 *
 * @author victor
 */
import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AsientoData {

    private Connection con = null;
    private ProyeccionData proyData; 

    
    public AsientoData() {     
        this.con = Conexion.conectar(); 
        this.proyData = new ProyeccionData(); 
    }

    // --- ALTA (Guardar) ---
    public void guardarAsiento(Asiento asiento) {
        String sql = "INSERT INTO asiento (fila, numero, estado, codProyeccion) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, asiento.getFila());
            ps.setInt(2, asiento.getNumero());
            ps.setString(3, asiento.getEstado().name());
            ps.setInt(4, asiento.getProyeccion().getCodProyeccion());
            
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    asiento.setCodAsiento(rs.getInt(1));
                    // JOptionPane.showMessageDialog(null, "Asiento guardado con éxito.");
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar asiento: " + ex.getMessage());
        }
    }

    // --- MODIFICACIÓN (Actualizar Estado) ---
    public void actualizarEstadoAsiento(int codAsiento, String nuevoEstado) {
        String sql = "UPDATE asiento SET estado = ? WHERE codAsiento = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nuevoEstado);
            ps.setInt(2, codAsiento);
            
            int filas = ps.executeUpdate();
            if (filas == 0) {
                JOptionPane.showMessageDialog(null, "No se encontró el asiento para actualizar.");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar estado: " + ex.getMessage());
        }
    }

    // --- BAJA (Eliminar) ---
    public void eliminarAsiento(int id) {
        String sql = "DELETE FROM asiento WHERE codAsiento = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            
            if (filas > 0) {
                // JOptionPane.showMessageDialog(null, "Asiento eliminado.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar asiento: " + ex.getMessage());
        }
    }

    // --- CONSULTA (Buscar por ID) ---
    public Asiento buscarAsiento(int id) {
        Asiento asiento = null;
        String sql = "SELECT * FROM asiento WHERE codAsiento = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    asiento = new Asiento();
                    asiento.setCodAsiento(id);
                    asiento.setFila(rs.getString("fila"));
                    asiento.setNumero(rs.getInt("numero"));
                    String estadoDB =rs.getString("estado");
                    asiento.setEstado(EstadoAsiento.valueOf(estadoDB));
                    
                                       
                    Proyeccion proy = proyData.buscarProyeccion(rs.getInt("codProyeccion"));
                    asiento.setProyeccion(proy);
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar asiento: " + ex.getMessage());
        }
        return asiento;
    }

    // --- LISTAR (Asientos por Proyección) ---
    public List<Asiento> listarAsientosPorProyeccion(int idProyeccion) {
        List<Asiento> asientos = new ArrayList<>();
        String sql = "SELECT * FROM asiento WHERE codProyeccion = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyeccion);
            
            try (ResultSet rs = ps.executeQuery()) {
                
                Proyeccion proy = proyData.buscarProyeccion(idProyeccion);
                
                while (rs.next()) {
                    Asiento asiento = new Asiento();
                    asiento.setCodAsiento(rs.getInt("codAsiento"));
                    asiento.setFila(rs.getString("fila"));
                    asiento.setNumero(rs.getInt("numero"));
                    String estadoDB =rs.getString("estado");
                    asiento.setEstado(EstadoAsiento.valueOf(estadoDB));
                    asiento.setProyeccion(proy);
                    
                    asientos.add(asiento);
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar asientos: " + ex.getMessage());
        }
        return asientos;
    }
}
