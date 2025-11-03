/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Comprador;
import java.sql.*;

import Modelo.Conexion;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class CompradorData {
    private Connection con = null;
    
    public CompradorData(){
        this.con = Conexion.conectar();
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
}
