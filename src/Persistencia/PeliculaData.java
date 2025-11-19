/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import Modelo.Conexion;
import Modelo.Pelicula;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;


/**
 *
 * @author elias
 */
public class PeliculaData {
    private Connection con = null;
    
    public PeliculaData(){
        this.con = Conexion.conectar();
    }
    
    public void agregarPelicula(Pelicula pelicula) {
        String sql = "INSERT INTO pelicula (titulo, director, origen, genero, estreno, enCartelera) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getDirector());
            ps.setString(3, pelicula.getOrigen());
            ps.setString(4, pelicula.getGenero());
            ps.setDate(5, new java.sql.Date(pelicula.getEstreno().getTime())); 
            ps.setBoolean(6, pelicula.isEnCartelera());

            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                 JOptionPane.showMessageDialog(null, "Pelicula '" + pelicula.getTitulo() + "' agregada exitosamente.");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Error: La pelicula '" + pelicula.getTitulo() + 
                                   "' del director '" + pelicula.getDirector() + "' ya existe.");
            } else {
                JOptionPane.showMessageDialog(null,"Error al guardar la pelicula: " + e.getMessage());
            }
        }
    }

    

    public void modificarPelicula(Pelicula pelicula) {
        // El WHERE usa la clave primaria compuesta
        String sql = "UPDATE pelicula SET origen = ?, genero = ?, estreno = ?, enCartelera = ? " +
                     "WHERE titulo = ? AND director = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            
            ps.setString(1, pelicula.getOrigen());
            ps.setString(2, pelicula.getGenero());
            ps.setDate(3, new java.sql.Date(pelicula.getEstreno().getTime()));
            ps.setBoolean(4, pelicula.isEnCartelera());
            
          
            ps.setString(5, pelicula.getTitulo()); 
            ps.setString(6, pelicula.getDirector());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null,"Pelicula '" + pelicula.getTitulo() + "' modificada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null,"No se encontro la pelicula para modificar.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al modificar la pelicula: " + e.getMessage());
        }
    }

    
    public void eliminarPelicula(String titulo, String director) {
        
    String sql = "DELETE FROM pelicula WHERE titulo = ? AND director = ?";
    
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, titulo);
        ps.setString(2, director);
        
        int filas = ps.executeUpdate();
        
        if (filas > 0) {
            JOptionPane.showMessageDialog(null, "Pelicula eliminada.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro la pelicula.");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
    }
        
        
    }

   
    public Pelicula buscarPelicula(String titulo, String director) {
        Pelicula pelicula = null;
        String sql = "SELECT * FROM pelicula WHERE titulo = ? AND director = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, titulo);
            ps.setString(2, director);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pelicula = new Pelicula();

                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setDirector(rs.getString("director"));
                    pelicula.setOrigen(rs.getString("origen"));
                    pelicula.setGenero(rs.getString("genero"));
                    pelicula.setEstreno(rs.getDate("estreno"));
                    pelicula.setEnCartelera(rs.getBoolean("enCartelera"));
                } else {
                    JOptionPane.showMessageDialog(null,"No se encontro la pelicula.");
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al buscar la pelicula: " + e.getMessage());
        }
        return pelicula;
    }

  
    public List<Pelicula> listarTodasLasPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT * FROM pelicula";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
               
                Pelicula pelicula = new Pelicula(
                    rs.getString("titulo"),
                    rs.getString("director"),
                    rs.getString("origen"),
                    rs.getString("genero"),
                    rs.getDate("estreno"),
                    rs.getBoolean("enCartelera")
                );
                peliculas.add(pelicula);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al listar las peliculas: " + e.getMessage());
        }
        return peliculas;
    }
    

    public List<Pelicula> listarPeliculasEnCartelera() {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT * FROM pelicula WHERE enCartelera = ?"; 

        try (PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setBoolean(1, true);
            try(ResultSet rs = ps.executeQuery()){

            while (rs.next()) {
                Pelicula pelicula = new Pelicula(
                    rs.getString("titulo"),
                    rs.getString("director"),
                    rs.getString("origen"),
                    rs.getString("genero"),
                    rs.getDate("estreno"),
                    rs.getBoolean("enCartelera")
                );
                peliculas.add(pelicula);
            }
        }

        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al listar peliculas en cartelera: " + e.getMessage());
        }
        return peliculas;
    }
    
    public List<Pelicula> listarPeliculasEstrenos() {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT * FROM pelicula WHERE enCartelera = ?"; 

        try (PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setBoolean(1, false);
            try(ResultSet rs = ps.executeQuery()){

            while (rs.next()) {
                Pelicula pelicula = new Pelicula(
                    rs.getString("titulo"),
                    rs.getString("director"),
                    rs.getString("origen"),
                    rs.getString("genero"),
                    rs.getDate("estreno"),
                    rs.getBoolean("enCartelera")
                );
                peliculas.add(pelicula);
            }
        }

        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al listar proximos estrenos: " + e.getMessage());
        }
        return peliculas;
    }
}
 
