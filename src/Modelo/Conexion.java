/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author victor
 */
public class Conexion {
    private static Connection c = null;

    private static final String URL = "jdbc:mysql://localhost:3306/gp13_cinemacentro";
    private static final String USUARIO = "root";
    private static final String CLAVE = "root";

    public static Connection conectar() {
        if (c == null) {
            try {
                c = DriverManager.getConnection(URL, USUARIO, CLAVE);
                System.out.println("✅ Conexion exitosa a la base de datos.");
            } catch (SQLException e) {
                System.out.println("❌ Error en la conexion: " + e.getMessage());
                return null;
            }
        }
         return c;
    }
   

    public static void cerrarConexion() {
        try {
            if (c != null) {
                c.close();
                System.out.println("Conexion cerrada");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion: "+ e);
                  
        }
    }
}