
package libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DAO<T> {
    
    protected final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("EjApJPA01PU");
    protected EntityManager em = EMF.createEntityManager();
    
    // Este m�todo nos permite conectar con la base de datos
    // se verifica si la conexi�n est� realizada, en caso que
    // no est� realizada, se realiza en la l�nea 24.
    protected void conectar() {
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }

    // Este m�todo nos permite desconectarnos de la base de datos
    // Se verifica si existe una conexi�n, y de ser el caso, se
    // cierra la conexi�n y se desconecta el programa con la base de datos.
    protected void desconectar() {
        if (em.isOpen()) {
            em.close();
        }
    }
    
    // este m�todo nos permite persistir un objeto en la base de datos.
    // Toma como par�metro el objeto a persistir, vemos que es gen�rico, por
    // lo que puede aceptar cualquier tipo de objeto (Direcci�n, Mastoca, Persona)
    protected void guardar(T objeto){
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    // Este m�todo nos permite modificar una tupla de una base de datos.
    // Recibe como par�metro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    protected void editar(T objeto){
        conectar();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    // Este m�todo nos permite eliminar un registro de la base de datos.
    // Como par�metro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma informaci�n
    // que el par�metro recibido, y se elimina.
    protected void eliminar(T objeto){
        conectar();
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
}
