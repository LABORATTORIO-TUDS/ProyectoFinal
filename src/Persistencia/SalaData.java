/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Conexion;
import Modelo.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class SalaData {
    
    private Connection con;

    public SalaData() {
        this.con = Conexion.conectar();
    }
    
    public void guardarSala(Sala sala) {
        String sql = "INSERT INTO salas (apta3D, capacidad, estado) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, sala.isApta3D());
            ps.setInt(2, sala.getCapacidad());
            ps.setString(3, sala.getEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                sala.setNroSala(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Sala añadida con éxito.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla salas: " + ex.getMessage());
        }
    }
    
    public void modificarSala(Sala sala) {
        String sql = "UPDATE salas SET apta3D = ?, capacidad = ?, estado = ? WHERE nroSala = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, sala.isApta3D());
            ps.setInt(2, sala.getCapacidad());
            ps.setString(3, sala.getEstado());
            ps.setInt(4, sala.getNroSala());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Sala modificada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La sala no existe.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla salas: " + ex.getMessage());
        }
    }
    
    public void eliminarSala(int nroSala) {
        String sql = "DELETE FROM salas WHERE nroSala = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nroSala);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Sala eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La sala no existe.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla salas: " + ex.getMessage());
        }
    }
    
    public Sala buscarSala(int nroSala) {
        Sala sala = null;
        String sql = "SELECT * FROM salas WHERE nroSala = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nroSala);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sala = new Sala();
                sala.setNroSala(rs.getInt("nroSala"));
                sala.setApta3D(rs.getBoolean("apta3D"));
                sala.setCapacidad(rs.getInt("capacidad"));
                sala.setEstado(rs.getString("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe la sala.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla salas: " + ex.getMessage());
        }
        return sala;
    }
    
    public List<Sala> listarSalas() {
        List<Sala> salas = new ArrayList<>();
        String sql = "SELECT * FROM salas";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sala sala = new Sala();
                sala.setNroSala(rs.getInt("nroSala"));
                sala.setApta3D(rs.getBoolean("apta3D"));
                sala.setCapacidad(rs.getInt("capacidad"));
                sala.setEstado(rs.getString("estado"));
                salas.add(sala);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla salas: " + ex.getMessage());
        }
        return salas;
    }
}