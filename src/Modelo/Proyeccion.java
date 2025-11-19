/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author elias
 */
public class Proyeccion {

    private int codProyeccion;
    private String idioma;
    private boolean es3D;
    private boolean subtitulada;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private double precio;
    private String titulo;
    private String director;
    private Date Fecha;
    private int nroSala;

    public Proyeccion() {

    }

    public Proyeccion(int codProyeccion, String idioma, boolean es3D, boolean subtitulada, LocalTime horaInicio, LocalTime horaFin, double precio, String titulo, String director, int nroSala, Date fecha) {
        this.codProyeccion = codProyeccion;
        this.idioma = idioma;
        this.es3D = es3D;
        this.subtitulada = subtitulada;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.precio = precio;
        this.titulo = titulo;
        this.director = director;
        this.nroSala = nroSala;
                this.Fecha = (fecha);

    }

    public Proyeccion(String idioma, boolean es3D, boolean subtitulada, LocalTime horaInicio, LocalTime horaFin, double precio, String titulo, String director, int nroSala, Date fecha) {
        this.idioma = idioma;
        this.es3D = es3D;
        this.subtitulada = subtitulada;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.precio = precio;
        this.titulo = titulo;
        this.director = director;
        this.nroSala = nroSala;
        this.Fecha = fecha;
    }

    public int getCodProyeccion() {
        return codProyeccion;
    }

    public void setCodProyeccion(int codProyeccion) {
        this.codProyeccion = codProyeccion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isEs3D() {
        return es3D;
    }

    public void setEs3D(boolean es3D) {
        this.es3D = es3D;
    }

    public boolean isSubtitulada() {
        return subtitulada;
    }

    public void setSubtitulada(boolean subtitulada) {
        this.subtitulada = subtitulada;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getNroSala() {
        return nroSala;
    }

    public void setNroSala(int nroSala) {
        this.nroSala = nroSala;
    }

    @Override
    public String toString() {
        String formato3D = this.isEs3D() ? " (3D)" : "";
        String formatoSub = this.isSubtitulada() ? " (SUB)" : "";
        String formatoIdioma = this.getIdioma().isEmpty() ? "" : " (" + this.getIdioma() + ")";

        
        return this.getTitulo()
                + formato3D
                + formatoSub
                + formatoIdioma
                + " | " + this.getHoraInicio() + " hs"
                + " | $" + String.format("%.2f", this.getPrecio());
    }

}
