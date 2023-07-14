
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Libro;


public class LibroDAO extends DAO<Libro>{
    
    
    @Override
    public void guardar(Libro libro) {
        super.guardar(libro); 
    }
    
    /*
    public void guardar(Libro libro) {
        conectar();
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
        desconectar();
    }*/
    
    
    public Libro buscarPorIsbn(Long isbn) {
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn")
                .setParameter("isbn", isbn).getSingleResult();
        desconectar();
        return libro;
    }
    
    public Libro buscarPorTitulo(String titulo) {
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                .setParameter("titulo", titulo).getSingleResult();
        desconectar();
        return libro;
    }
    

    @Override
    public void eliminar(Libro libro) {
        super.eliminar(libro); 
    }
    
    public List<Libro> listarTodosLibros() {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l")
                .getResultList();
        desconectar();
        return libros;
    }

    @Override
    public void editar(Libro libro) {
        super.editar(libro); 
    }
    
    
    public List<Libro> buscarLibroNombreAutor(String nombre) {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre LIKE :nombre")
                .setParameter("nombre", nombre).getResultList();
        desconectar();
        return libros;
    }
    
    public List<Libro> buscarLibroNombreEditorial(String nombre) {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :nombre")
                .setParameter("nombre", nombre).getResultList();
        desconectar();
        return libros;
    }
 
}
