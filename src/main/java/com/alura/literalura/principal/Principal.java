package com.alura.literalura.principal;

import com.alura.literalura.model.Datos;
import com.alura.literalura.model.DatosLibros;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    public void muestraElMenu() {

        System.out.println("-------------------------------");
        System.out.println("Elija la opción a través de su número:");
        System.out.println("1- buscar libro por titulo");
        System.out.println("2- listar libros registrados");
        System.out.println("3- listar autores registrados");
        System.out.println("4- listar autores vivos en un determinado año");
        System.out.println("5- listar libros por idioma");
        System.out.println("0- SALIR");

        System.out.println("-------------------------------");
        int opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion) {

            case 1:
                System.out.println("Ingrese el nombre del libro que desea buscar");
                var tituloLibro = teclado.nextLine();

                var json1 = consumoAPI.obtenerDatos(URL_BASE+"?search="+tituloLibro.replace(" ","%20"));
                var datosBusqueda = conversor.obtenerDatos(json1, Datos.class);
                Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                        .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                        .findFirst();

                if (libroBuscado.isPresent()) {
                    System.out.println("Libro buscado encontrado");
                    System.out.println(libroBuscado.get());
                }else {
                    System.out.println("Libro no encontrado");
                }

                muestraElMenu();

                break;

            case 2:

                muestraElMenu();

                break;

            case 3:
                muestraElMenu();

            break;

            case 4:
                System.out.println("Ingrese el año por el que desea buscar a los autores vivos");
                int año = teclado.nextInt();

                var json2 = consumoAPI.obtenerDatos(URL_BASE + "?author_year_start=" + año);

                var datosBusqueda2 = conversor.obtenerDatos(json2, Datos.class);
                List<DatosLibros> librosEncontrados = datosBusqueda2.resultados();

                if (!librosEncontrados.isEmpty()) {
                    System.out.println("Libros encontrados:");
                    for (int i = 0; i < Math.min(3, librosEncontrados.size()); i++) {
                        System.out.println(librosEncontrados.get(i));
                    }
                } else {
                    System.out.println("No se encontraron libros para el año especificado.");
                }
                muestraElMenu();

                break;


            case 5:
                System.out.println("Ingrese el idioma para buscar los libros");
                System.out.println("es- español");
                System.out.println("en- ingles");
                System.out.println("fr- frances");
                System.out.println("pt- portugues");

                var idioma = teclado.nextLine();

                var json3 = consumoAPI.obtenerDatos(URL_BASE + "?languages=" + idioma);

                var datosBusqueda3 = conversor.obtenerDatos(json3, Datos.class);
                List<DatosLibros> librosIdiomas = datosBusqueda3.resultados();

                if (!librosIdiomas.isEmpty()) {
                    System.out.println("Libros encontrados:");
                    for (int i = 0; i < Math.min(3, librosIdiomas.size()); i++) {
                        System.out.println(librosIdiomas.get(i));
                    }
                } else {
                    System.out.println("No se encontraron libros en este idioma");
                }

                muestraElMenu();

                break;

            case 0:
                System.out.println("Hasta luego! Gracias por usar la aplicación");
                System.exit(0);
            break;
        }
    }
}
