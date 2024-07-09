package com.alura.literalura.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String añoDeNacimiento;
    private String añoDeFallecimiento;

    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(String nombre, String añoDeNacimiento, String añoDeFallecimiento) {
        this.nombre = nombre;
        this.añoDeNacimiento = añoDeNacimiento;
        this.añoDeFallecimiento = añoDeFallecimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAñoDeNacimiento() {
        return añoDeNacimiento;
    }

    public void setAñoDeNacimiento(String añoDeNacimiento) {
        this.añoDeNacimiento = añoDeNacimiento;
    }

    public String getAñoDeFallecimiento() {
        return añoDeFallecimiento;
    }

    public void setAñoDeFallecimiento(String añoDeFallecimiento) {
        this.añoDeFallecimiento = añoDeFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
