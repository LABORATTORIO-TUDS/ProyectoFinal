
package Modelo;

import Modelo.*;
/**
 *
 * @author victor
 */
public class Asiento {

    private int codAsiento;
    private String fila;    
    private int numero;
    private EstadoAsiento estado;                           
    private Proyeccion proyeccion; 

   
    public Asiento() {
    }

    // Constructor completo sin el ID
    public Asiento(String fila, int numero, EstadoAsiento estado, Proyeccion proyeccion) {
        this.fila = fila;
        this.numero = numero;
        this.estado = estado;
        this.proyeccion = proyeccion;
    }

    // Constructor completo con ID
    public Asiento(int codAsiento, String fila, int numero, EstadoAsiento estado, Proyeccion proyeccion) {
        this.codAsiento = codAsiento;
        this.fila = fila;
        this.numero = numero;
        this.estado = estado;
        this.proyeccion = proyeccion;
    }

    public int getCodAsiento() {
        return codAsiento;
    }

    public void setCodAsiento(int codAsiento) {
        this.codAsiento = codAsiento;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public EstadoAsiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsiento estado) {
        this.estado = estado;
    }

    public Proyeccion getProyeccion() {
        return proyeccion;
    }

    public void setProyeccion(Proyeccion proyeccion) {
        this.proyeccion = proyeccion;
    }

    
    
    @Override
    public String toString() {
        return "Asiento{" +
                "codAsiento=" + codAsiento +
                ", fila='" + fila + '\'' +
                ", numero=" + numero +
                ", estado='" + estado + '\'' +
                ", proyeccion=" + (proyeccion != null ? proyeccion.getCodProyeccion() : "null") +
                '}';
    }
}
