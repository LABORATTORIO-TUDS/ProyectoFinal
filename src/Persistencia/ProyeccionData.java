/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Proyeccion;
import Modelo.Conexion;
import com.sun.java.accessibility.util.EventID;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class ProyeccionData {

    private Connection con = null;

    public ProyeccionData() {
        this.con = Conexion.conectar();
    }

    //Que onda gatos aca hacemos la alta:
    public int crearProyeccion(Proyeccion p) {
        String sql = "INSERT into proyeccion (idioma, es3D, subtitulada, horaInicio, horaFin, precio, titulo, director, nroSala, fechaProyeccion) VALUES (?,?,?,?,?,?,?,?,?,?)";

        int idGenerado = -1;
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getIdioma());
            ps.setBoolean(2, p.isEs3D());
            ps.setBoolean(3, p.isSubtitulada());
            ps.setTime(4, Time.valueOf(p.getHoraInicio()));
            ps.setTime(5, Time.valueOf(p.getHoraFin()));
            ps.setDouble(6, p.getPrecio());
            ps.setString(7, p.getTitulo());
            ps.setString(8, p.getDirector());
            ps.setInt(9, p.getNroSala());
            ps.setDate(10, new java.sql.Date(p.getFecha().getTime()));

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);

                // Lo guardamos también en el objeto por si acaso
                p.setCodProyeccion(idGenerado);
                JOptionPane.showMessageDialog(null, "Proyeccion guardada correctamente."+ idGenerado);
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al crear proyeccion: " + ex.getMessage());

        }
        return idGenerado;
    }

    public Proyeccion buscarProyeccion(int codProyeccion) {
        Proyeccion proy = null;
        String sql = "SELECT * FROM proyeccion WHERE codProyeccion = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codProyeccion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                proy = new Proyeccion();
                proy.setCodProyeccion(rs.getInt("codProyeccion"));
                proy.setIdioma(rs.getString("idioma"));
                proy.setEs3D(rs.getBoolean("es3D"));
                proy.setSubtitulada(rs.getBoolean("subtitulada"));
                proy.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                proy.setHoraFin(rs.getTime("horaFin").toLocalTime());
                proy.setPrecio(rs.getDouble("precio"));
                proy.setTitulo(rs.getString("titulo"));
                proy.setDirector(rs.getString("director"));
                proy.setNroSala(rs.getInt("nroSala"));
                java.sql.Date fechaSql = rs.getDate("fechaProyeccion");
                proy.setFecha(new java.util.Date(fechaSql.getTime()));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar una proyeccion: " + ex.getMessage());
        }

        return proy;
    }

    //Metodo para modificar una proyeccionitis
    public void modificarProyeccion(Proyeccion proy) {
        String sql = "UPDATE proyeccion SET idioma = ?, es3D = ?,"
                + " subtitulada = ?, horaInicio = ?, horaFin = ?,"
                + " precio = ?, titulo = ?, director = ?, nroSala = ?, fechaProyeccion=? WHERE codProyeccion = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, proy.getIdioma());
            ps.setBoolean(2, proy.isEs3D());
            ps.setBoolean(3, proy.isSubtitulada());
            ps.setTime(4, Time.valueOf(proy.getHoraInicio()));
            ps.setTime(5, Time.valueOf(proy.getHoraFin()));
            ps.setDouble(6, proy.getPrecio());
            ps.setString(7, proy.getTitulo());
            ps.setString(8, proy.getDirector());
            ps.setInt(9, proy.getNroSala());
            ps.setInt(11, proy.getCodProyeccion());
            ps.setDate(10, new java.sql.Date(proy.getFecha().getTime()));

            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Proyeccion modificada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Proyeccion a modificar no encontrada.");
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido modificar la proyeccion: " + ex.getMessage());
        }
    }

    //listar todas las proecciones
    public List<Proyeccion> listaProyecciones() {
        List<Proyeccion> proyecciones = new ArrayList<>();
        String sql = "SELECT * FROM proyeccion";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Proyeccion proy = new Proyeccion();
                proy.setCodProyeccion(rs.getInt("codProyeccion"));
                proy.setIdioma(rs.getString("idioma"));
                proy.setEs3D(rs.getBoolean("es3D"));
                proy.setSubtitulada(rs.getBoolean("subtitulada"));
                proy.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                proy.setHoraFin(rs.getTime("horaFin").toLocalTime());
                proy.setPrecio(rs.getDouble("precio"));
                proy.setTitulo(rs.getString("titulo"));
                proy.setDirector(rs.getString("director"));
                proy.setNroSala(rs.getInt("nroSala"));
                java.sql.Date fechaSql = rs.getDate("fechaProyeccion");
                proy.setFecha(new java.util.Date(fechaSql.getTime()));

                proyecciones.add(proy);
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar las proyecciones: " + ex.getMessage());
        }

        return proyecciones;
    }

    //Mteodo para eliminar 
    public void bajarProyeccion(Proyeccion p) {
        String sql = "DELETE FROM proyeccion WHERE codProyeccion = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, p.getCodProyeccion());

            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Proyeccion dada de baja correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro la proyeccion.");
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo dar de baja la proyeccion: " + ex.getMessage());
        }

    }

    public void generarAsientos(int codProyeccion, int capacidadSala) {
        // Definimos cuántas butacas queremos por fila (ej: 10 o 20)
        int butacasPorFila = 10;

        String sql = "INSERT INTO asiento (fila, numero, estado, codProyeccion) VALUES (?, ?, 'LIBRE', ?)";

        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);

            for (int i = 0; i < capacidadSala; i++) {

                char letraFila = (char) ('A' + (i / butacasPorFila));
                int numeroAsiento = (i % butacasPorFila) + 1;

                ps.setString(1, String.valueOf(letraFila));
                ps.setInt(2, numeroAsiento);
                ps.setInt(3, codProyeccion);
                ps.addBatch();
            }

            ps.executeBatch();
            con.commit();
            con.setAutoCommit(true);

            JOptionPane.showMessageDialog(null, "Se crearon " + capacidadSala + " asientos automáticamente.");

        } catch (SQLException ex) {
            try {
                con.rollback(); // Si falla, deshacemos todo para no dejar asientos a medias
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Error al generar asientos: " + ex.getMessage());
        }
    }

}
