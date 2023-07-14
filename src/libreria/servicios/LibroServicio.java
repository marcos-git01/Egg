
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;


public class LibroServicio {
       
    
    private final LibroDAO DAO;

    public LibroServicio() {
        this.DAO = new LibroDAO();
    }
    
    private final AutorServicio autorServicio = new AutorServicio();
    private final EditorialServicio editorialServicio = new EditorialServicio();
    
    
    // este método persiste un registro de tipo Libro en la base de datos
    // a través del método guardar() de la clase DAO.
    public Libro crearLibro(String titulo, Integer anio, Autor autor, Editorial editorial) {

        Libro libro = new Libro();
        try {
            
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new Exception("Debe indicar el titulo del libro");
            }
            if (anio == null || anio < 0) {
                throw new Exception("Debe indicar el año");
            }
            if (autor == null) {
                throw new Exception("Autor nulo");
            }
            if (editorial == null) {
                throw new Exception("Editorial nulo");
            }
            
            libro.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            libro.setIsbn((long) (int) (Math.random() * 100000 + 1));
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            
            int ejemplares = (int) (Math.random() * 100 + 1);
            int ejemplaresPrestados = (int) (Math.random() * 10 + 1);
            
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);
            
            
            libro.setAlta(Boolean.TRUE);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            DAO.guardar(libro);
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    public Libro buscarPorIsbn(Long isbn) {
        try {
            return DAO.buscarPorIsbn(isbn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Libro buscarPorTitulo(String titulo) {
        try {
            return DAO.buscarPorTitulo(titulo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public boolean eliminarPorIsbn(Long isbn) {
        try {
            Libro libro = DAO.buscarPorIsbn(isbn);
            DAO.eliminar(libro);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    public List<Libro> listarLibros() {
        try {
            return DAO.listarTodosLibros();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void modificarLibro(Long isbn, String titulo) {
        try {
            Libro libro = DAO.buscarPorIsbn(isbn);
            
            libro.setTitulo(titulo);
            
            DAO.editar(libro);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
    }
    
    public List<Libro> buscarLibroPorNombreAutor(String nombre) {
        try {
            return DAO.buscarLibroNombreAutor(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public List<Libro> buscarLibroPorNombreEditorial(String nombre) {
        try {
            return DAO.buscarLibroNombreEditorial(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    //////////////
    
    public Libro crearLibroPorDatoIngresado() {

        Libro libro = new Libro();
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        try {

            libro.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            libro.setIsbn((long) (int) (Math.random() * 100000 + 1));

            System.out.println("Ingrese el titulo del libro");
            System.out.print("Titulo: ");
            String tituloLibro = leer.next();
            libro.setTitulo(tituloLibro);

            System.out.println("Ingrese el año del libro");
            System.out.print("Año: ");
            Integer anioLibro = leer.nextInt();
            libro.setAnio(anioLibro);

            int ejemplares = (int) (Math.random() * 100 + 1);
            int ejemplaresPrestados = (int) (Math.random() * 10 + 1);

            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);

            libro.setAlta(Boolean.TRUE);

            System.out.println("Informacion del Autor del libro");

            System.out.println("Ingrese el nombre del Autor");
            System.out.print("Nombre: ");
            String nombreAutorIngresado = leer.next();
            boolean AutorEsta = false;

            for (Autor aux : autorServicio.listarAutores()) {
                if (aux.getNombre().equals(nombreAutorIngresado)) {
                    System.out.println("El Autor ya existe");
                    autorServicio.buscarPorNombre(aux.getNombre());
                    libro.setAutor(aux);
                    System.out.println("Autor ingresado correctamente");
                    AutorEsta = true;
                }

            }

            if (!AutorEsta) {
                System.out.println("Autor nuevo");
                libro.setAutor(autorServicio.crearAutorPorDatoIngresado());
            }

            System.out.println("Informacion de la Editorial del libro");

            System.out.println("Ingrese el nombre de la Editorial");
            System.out.print("Nombre: ");
            String nombreEditorialIngresado = leer.next();
            boolean EditorialEsta = false;

            for (Editorial aux : editorialServicio.listarEditoriales()) {
                if (aux.getNombre().equals(nombreEditorialIngresado)) {
                    System.out.println("La Editorial ya existe");
                    editorialServicio.buscarPorNombre(aux.getNombre());
                    libro.setEditorial(aux);
                    System.out.println("Editorial ingresada correctamente");
                    EditorialEsta = true;
                }

            }

            if (!EditorialEsta) {
                System.out.println("Editorial nueva");
                libro.setEditorial(editorialServicio.crearEditorialPorDatoIngresado());
            }
            
            
            if (validarLibroNuevo(tituloLibro,anioLibro, nombreAutorIngresado, nombreEditorialIngresado)) {
                System.out.println("");
                System.out.println("El libro ya se encuentra en la lista");
                System.out.println("No se puede registrar");                
            }else {
                //ls.crearLibro(null, tituloLibro, anioLibro, ejemplaresLibro, 0, ejemplaresLibro, true, autor, editorial);
                DAO.guardar(libro);
            }
        
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    //////
    //Ver esto
    public Boolean validarLibroNuevo(String tituloL, Integer anioL, String autorL, String editorialL){
        Boolean esta = false;
        for (Libro libroAux : listarLibros()) {
            int anioN = anioL;
            if(libroAux.getTitulo().equalsIgnoreCase(tituloL) && libroAux.getAnio()==anioN && libroAux.getAutor().getNombre().equalsIgnoreCase(autorL) && libroAux.getEditorial().getNombre().equalsIgnoreCase(editorialL)){
                esta = true;
                break;
            }
        }
        return esta;
    }
    /*
    if (ld.validarLibroNuevo(tituloLibro,anioLibro, autor.getNombre(), editorial.getNombre())) {
                System.out.println("Este libro ya se encuentra en la lista.");
            }else {
                ls.crearLibro(null, tituloLibro, anioLibro, ejemplaresLibro, 0, ejemplaresLibro, true, autor, editorial);
            }*/


}
