
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;


public class AutorServicio {
    
    private final AutorDAO DAO;

    public AutorServicio() {
        this.DAO = new AutorDAO();
    }
    
    
    // este método persiste un registro de tipo Autor en la base de datos
    // a través del método guardar() de la clase DAO.
    public Autor crearAutor(String nombre) {

        Autor autor = new Autor();
        try {
            
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del Autor");
            }
                       
            autor.setNombre(nombre);
            autor.setAlta(Boolean.TRUE);
       
            DAO.guardar(autor);
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Autor buscarPorId(Integer id) {
        try {
            return DAO.buscarPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Autor buscarPorNombre(String nombre) {
        try {
            return DAO.buscarPorNombre(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    public boolean eliminarPorId(Integer id) {
        try {
            Autor autor = DAO.buscarPorId(id);
            DAO.eliminar(autor);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public List<Autor> listarAutores() {
        try {
            return DAO.listarTodosAutores();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void modificarAutor(Integer id, String nombre) {
        try {
            Autor autor = DAO.buscarPorId(id);
            
            autor.setNombre(nombre);
            
            DAO.editar(autor);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
    }
    
    ////////////////
    
    public Autor crearAutorPorDatoIngresado() {

        Autor autor = new Autor();
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        
        try {
            
            System.out.println("Ingrese el nombre del Autor");
            System.out.print("Nombre: ");
            String nombreAutorIngresado = leer.next();           
            autor.setNombre(nombreAutorIngresado);
            
            if (autor.getNombre() == null || autor.getNombre().trim().isEmpty()) {
                throw new Exception("Error: Debe indicar el nombre del Autor");
            }

            //Cuando sale la Exception, no setea el Alta, ni guarda el Autor mediante DAO.                                  
            autor.setAlta(Boolean.TRUE);
       
            DAO.guardar(autor);
            
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
            System.out.println("Ingrese NUEVAMENTE el nombre del Autor");
            System.out.print("Nombre: ");            
            autor.setNombre(leer.next());
            
            autor.setAlta(Boolean.TRUE);      
            DAO.guardar(autor);
            
            return autor;
            
            //Ver esto, el null creo que va
            //crearAutorPorDatoIngresado();
            //return null;
        } 
        
    }
    
    
    
}
