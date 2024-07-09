package com.alura.literalura.model;

import com.alura.literalura.dto.DatosLibro;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libro")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Autor> autores;

    private String idiomas;

    private Double descargas;

    public Libro() {
    }

    public Libro(String titulo, List<Autor> autores, String idiomas, Double descargas) {
        this.titulo = titulo;
        this.autores = autores;
        this.idiomas = idiomas;
        this.descargas = descargas;
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.descargas = datosLibro.numeroDeDescargas();
        this.idiomas = datosLibro.idiomas().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }
}
