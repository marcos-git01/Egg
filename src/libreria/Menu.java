
package libreria;

import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.servicios.AutorServicio;
import libreria.servicios.EditorialServicio;
import libreria.servicios.LibroServicio;


public class Menu {
    
    private final LibroServicio libroServicio = new LibroServicio();
    private final AutorServicio autorServicio = new AutorServicio();
    private final EditorialServicio editorialServicio = new EditorialServicio();
    
    public void ejecucion1() {
        
        //Creacion de autores
        Autor a1 = autorServicio.crearAutor("Autor 1");
        Autor a2 = autorServicio.crearAutor("Autor 2");
        
        //autorServicio.listarAutores().forEach((a) -> System.out.println(a.toString()));
              
        //Creacion de editoriales
        Editorial e1 = editorialServicio.crearEditorial("Editorial 1");
        Editorial e2 = editorialServicio.crearEditorial("Editorial 2");
        
        //editorialServicio.listarEditoriales().forEach((e) -> System.out.println(e.toString()));     
        
        //Creacion de libros
        Libro l1 = libroServicio.crearLibro("Libro 1", 1991, a1, e1);
        Libro l2 = libroServicio.crearLibro("Libro 2", 1992, a2, e2);
        Libro l3 = libroServicio.crearLibro("Libro 3", 1993, a1, e1);
        Libro l4 = libroServicio.crearLibro("Libro 4", 1994, a1, e1);
        Libro l5 = libroServicio.crearLibro("Libro 5", 1995, a1, e1);
        
        System.out.println("");
        System.out.println("Los libros registrados son:");
        libroServicio.listarLibros().forEach((l) -> System.out.println(l.toString()));
        System.out.println("");
        
        
        System.out.println("Autor por nombre:");
        System.out.println(autorServicio.buscarPorNombre("Autor 1"));
        System.out.println(autorServicio.buscarPorNombre("Autor 1").getNombre());
        System.out.println("");
        
        System.out.println("Libro por Isbn:");
        System.out.println(libroServicio.buscarPorIsbn(8712L));
        System.out.println("");
        
        System.out.println("Libro por titulo:");
        System.out.println(libroServicio.buscarPorTitulo("Libro 2"));
        System.out.println(libroServicio.buscarPorTitulo("Libro 2").getTitulo());
        System.out.println("");
        
        
        System.out.println("Libros por nombre de autor:");
        //System.out.println(libroServicio.buscarLibroPorNombreAutor("Autor 1"));
        libroServicio.buscarLibroPorNombreAutor("Autor 1").forEach((l) -> System.out.println(l.toString()));
        System.out.println("");
        
        
        System.out.println("Libros por nombre de editorial:");
        //System.out.println(libroServicio.buscarLibroPorNombreEditorial("Editorial 2")); 
        libroServicio.buscarLibroPorNombreEditorial("Editorial 2").forEach((l) -> System.out.println(l.toString()));
        System.out.println("");
        
    }
    
    
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void ejecucion2() {
        System.out.println("");
        System.out.println("----- MENU LIBRERIA -----");
        System.out.println("Seleccione una opcion: ");
        System.out.println("1. Crear Autores, Libros y Editoriales Preestablecidos");
        System.out.println("2. Crear Libros");
        System.out.println("3. Imprimir los libros registrados");
        System.out.println("4. Buscar Autor por nombre");
        System.out.println("5. Buscar Libro por ISBN");
        System.out.println("6. Buscar Libro por Titulo");
        System.out.println("7. Buscar Libros por nombre de Autor");
        System.out.println("8. Buscar Libros por nombre de Editorial");       
        System.out.println("9. Salir del programa");
        System.out.print("Opcion: ");
        String opc = leer.next();
        System.out.println("");
        switch (opc) {
            case "1":
                //Creacion de autores
                Autor a1 = autorServicio.crearAutor("Autor 1");
                Autor a2 = autorServicio.crearAutor("Autor 2");

                //autorServicio.listarAutores().forEach((a) -> System.out.println(a.toString()));
                //Creacion de editoriales
                Editorial e1 = editorialServicio.crearEditorial("Editorial 1");
                Editorial e2 = editorialServicio.crearEditorial("Editorial 2");

                //editorialServicio.listarEditoriales().forEach((e) -> System.out.println(e.toString()));     
                //Creacion de libros
                Libro l1 = libroServicio.crearLibro("Libro 1", 1991, a1, e1);
                Libro l2 = libroServicio.crearLibro("Libro 2", 1992, a2, e2);
                Libro l3 = libroServicio.crearLibro("Libro 3", 1993, a1, e1);
                Libro l4 = libroServicio.crearLibro("Libro 4", 1994, a1, e1);
                Libro l5 = libroServicio.crearLibro("Libro 5", 1995, a1, e1);
                
                System.out.println("Libros, Autores y Editoriales cargados satisfactoriamente");

                ejecucion2();
                break;
            case "2":

                System.out.println("");
                libroServicio.crearLibroPorDatoIngresado();
                System.out.println("");

                ejecucion2();
                break; 
                
            case "3":

                System.out.println("");
                System.out.println("LIBROS REGISTRADOS SON");
                libroServicio.listarLibros().forEach((l) -> System.out.println(l.toString()));
                System.out.println("");

                ejecucion2();
                break;
            case "4":
                
                System.out.println("Buscar Autor por nombre");
                System.out.print("Nombre: ");
                String nombreAutor = leer.next();

                System.out.println("AUTOR POR NOMBRE");
                System.out.println(autorServicio.buscarPorNombre(nombreAutor));
                System.out.println(autorServicio.buscarPorNombre(nombreAutor).getNombre());
                System.out.println("");

                ejecucion2();
                break;
            case "5":
                
                System.out.println("Ingrese el ISBN del Libro a buscar");
                System.out.print("ISBN: ");
                Long isbnBuscar = leer.nextLong();

                System.out.println("LIBRO POR ISBN");
                System.out.println(libroServicio.buscarPorIsbn(isbnBuscar));
                System.out.println("");

                ejecucion2();
                break;
            case "6":
                
                System.out.println("Buscar Libro por titulo");
                System.out.print("Titulo: ");
                String tituloLibro = leer.next();

                System.out.println("LIBRO POR TITULO");
                System.out.println(libroServicio.buscarPorTitulo(tituloLibro));
                System.out.println(libroServicio.buscarPorTitulo(tituloLibro).getTitulo());
                System.out.println("");

                ejecucion2();
                break;
            case "7":
                
                System.out.println("Buscar Libros por nombre de Autor");
                System.out.print("Nombre Autor: ");
                String autorNombre = leer.next();
                
                System.out.println("LIBROS POR NOMBRE DE AUTOR");
                //System.out.println(libroServicio.buscarLibroPorNombreAutor("Autor 1"));
                libroServicio.buscarLibroPorNombreAutor(autorNombre).forEach((l) -> System.out.println(l.toString()));
                System.out.println("");

                ejecucion2();
                break;
            case "8":
                
                System.out.println("Buscar Libros por nombre de Editorial");
                System.out.print("Nombre Editorial: ");
                String editorialNombre = leer.next();
                
                System.out.println("LIBROS POR NOMBRE DE EDITORIAL");
                //System.out.println(libroServicio.buscarLibroPorNombreEditorial("Editorial 2")); 
                libroServicio.buscarLibroPorNombreEditorial(editorialNombre).forEach((l) -> System.out.println(l.toString()));
                System.out.println("");

                ejecucion2();
                break;
            case "9":
                System.out.println("Ejecución finalizada");
                break;
            default:
                System.out.println("Opcion no válida");               
                ejecucion2();
        } 

    }
    
    
}
