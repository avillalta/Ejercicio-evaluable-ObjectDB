package org.example;

import org.example.models.Comentario;
import org.example.models.Usuario;
import org.example.services.Service;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Inicializar el servicio
        Service service = new Service(ObjectDBUtil.getEmf());

        // HISTORIA 1: Registrar nuevos usuarios
        System.out.println("Registrando usuarios...");
        service.registrarUsuario("juan@mail.com", "Juan López");
        service.registrarUsuario("maria@mail.com", "María Smith");
        service.registrarUsuario("admin@mail.com", "Admin");

        // HISTORIA 3: Añadir comentarios a usuarios
        System.out.println("Añadiendo comentarios...");
        service.añadirComentarioAUsuario("maria@mail.com", "¡Gran película!", 9);
        service.añadirComentarioAUsuario("juan@mail.com", "Meh, estuvo bien.", 7);
        service.añadirComentarioAUsuario("juan@mail.com", "No me gustó.", 3);
        service.añadirComentarioAUsuario("admin@mail.com", "¡La hostia!", 10);

        // HISTORIA 2: Listar los mejores comentarios
        System.out.println("Listando los mejores comentarios...");
        List<Comentario> mejoresComentarios = service.listarMejoresComentarios(8);
        for (Comentario comentario : mejoresComentarios) {
            System.out.println("Usuario: " + comentario.getUsuario().getCorreo() +
                    " | Comentario: " + comentario.getContenido() +
                    " | Valoración: " + comentario.getValoracion());
        }

        // HISTORIA 4: Eliminar un usuario
        System.out.println("Eliminando usuario juan@mail.com...");
        service.eliminarUsuario("juan@mail.com");

        // Verificar que se eliminó el usuario y sus comentarios
        System.out.println("Comentarios después de eliminar usuario:");
        List<Comentario> comentariosRestantes = service.listarMejoresComentarios(0);
        for (Comentario comentario : comentariosRestantes) {
            System.out.println("Usuario: " + comentario.getUsuario().getCorreo() +
                    " | Comentario: " + comentario.getContenido() +
                    " | Valoración: " + comentario.getValoracion());
        }

        // Cerrar la conexión
        ObjectDBUtil.getEmf().close();
    }
}
