package org.example.services;

import org.example.models.Comentario;
import org.example.models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class Service {

    private static EntityManagerFactory emf;

    public Service(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void saveUsuario(Usuario usuario) {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrarUsuario(String correo, String nombre) {
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setNombre(nombre);
        saveUsuario(usuario);
    }

    public List<Comentario> listarMejoresComentarios(int valoracionMinima) {
        List<Comentario> comentarios = null;
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Comentario> query = em.createQuery(
                    "SELECT c FROM Comentario c WHERE c.valoracion >= :valoracionMinima ORDER BY c.valoracion DESC",
                    Comentario.class
            );
            query.setParameter("valoracionMinima", valoracionMinima);
            comentarios = query.getResultList();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comentarios;
    }

    public void añadirComentarioAUsuario(String correoUsuario, String contenido, int valoracion) {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Usuario usuario = em.find(Usuario.class, correoUsuario);
            if (usuario == null) {
                throw new IllegalArgumentException("Usuario no encontrado");
            }

            Comentario comentario = new Comentario(contenido, valoracion);
            usuario.añadirComentario(comentario);

            em.merge(usuario);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(String correo) {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Usuario usuario = em.find(Usuario.class, correo);
            if (usuario != null) {
                em.remove(usuario);
                System.out.println("Usuario eliminado con éxito.");
            } else {
                System.out.println("Usuario no encontrado.");
            }

            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
