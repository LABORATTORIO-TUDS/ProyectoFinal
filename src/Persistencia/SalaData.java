/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Conexion;
import Modelo.Sala;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SalaData {
    private Connection con = null;

    public SalaData() {
        this.con = Conexion.conectar();
    }

    public void guardarSala(Sala sala) {
        String sql = "INSERT INTO salas (apta3D, capacidad, estado) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setBoolean(1, sala.isApta3D());
            ps.setInt(2, sala.getCapacidad());
            ps.setString(3, sala.getEstado());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                sala.setNroSala(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Sala agregada con éxito. Número: " + sala.getNroSala());
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar la sala: " + ex.getMessage());
        }
    }

    public void modificarSala(Sala sala) {
        String sql = "UPDATE salas SET apta3D = ?, capacidad = ?, estado = ? WHERE nroSala = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
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
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar la sala: " + ex.getMessage());
        }
    }

    public void eliminarSala(int nroSala) {
        String sql = "DELETE FROM salas WHERE nroSala = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nroSala);
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Sala eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la sala para eliminar.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la sala (puede tener proyecciones asociadas): " + ex.getMessage());
        }
    }

    public Sala buscarSala(int nroSala) {
        Sala sala = null;
        String sql = "SELECT * FROM salas WHERE nroSala = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nroSala);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                sala = new Sala();
                sala.setNroSala(rs.getInt("nroSala"));
                sala.setApta3D(rs.getBoolean("apta3D"));
                sala.setCapacidad(rs.getInt("capacidad"));
                sala.setEstado(rs.getString("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe una sala con ese número.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar la sala: " + ex.getMessage());
        }
        return sala;
    }

    public List<Sala> listarSalas() {
        List<Sala> salas = new ArrayList<>();
        String sql = "SELECT * FROM salas";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sala sala = new Sala();
                sala.setNroSala(rs.getInt("nroSala"));
                sala.setApta3D(rs.getBoolean("apta3D"));
                sala.setCapacidad(rs.getInt("capacidad"));
                sala.setEstado(rs.getString("estado"));
                salas.add(sala);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar salas: " + ex.getMessage());
        }
        return salas;
    }
    
    public List<Sala> listarSalasLibres() {
        List<Sala> salas = new ArrayList<>();
        String sql = "SELECT * FROM salas WHERE estado = 'LIBRE'";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sala sala = new Sala();
                sala.setNroSala(rs.getInt("nroSala"));
                sala.setApta3D(rs.getBoolean("apta3D"));
                sala.setCapacidad(rs.getInt("capacidad"));
                sala.setEstado(rs.getString("estado"));
                salas.add(sala);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar salas libres: " + ex.getMessage());
        }
        return salas;
    }
}