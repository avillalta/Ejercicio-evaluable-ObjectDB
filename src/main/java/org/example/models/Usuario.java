package org.example.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Id
    private String correo;
    private String nombre;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String correo, String nombre) {
        this.correo = correo;
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void añadirComentario(Comentario c) {
        c.setUsuario(this);
        comentarios.add(c);
    }
}

