package org.example.models;

import lombok.Setter;

import javax.persistence.*;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;
    private int valoracion;

    @Setter
    @ManyToOne
    @JoinColumn(name = "usuario_correo", nullable = false)
    private Usuario usuario;

    public Comentario() {
    }

    public Comentario(String contenido, int valoracion) {
        if (valoracion < 0 || valoracion > 10) {
            throw new IllegalArgumentException("La valoración debe estar entre 0 y 10.");
        }
        this.contenido = contenido;
        this.valoracion = valoracion;
    }

    public Long getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    public int getValoracion() {
        return valoracion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

