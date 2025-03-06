package com.mycompany.buscarpalabraenarchivotexto;

import com.mycompany.filtro.Filtro;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BuscarPalabraEnArchivoTexto implements Filtro {

    private String palabraBusqueda = "texto"; 

    public void setPalabraBusqueda(String palabraBusqueda) {
        this.palabraBusqueda = palabraBusqueda;
    }

    @Override
    public String procesar(String entrada) {

        File archivoOriginal = new File(entrada);
        if (!archivoOriginal.exists() || !archivoOriginal.isFile()) {
            System.err.println("Error: El archivo especificado no existe o no es un archivo válido.");
            return null;
        }
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(entrada)));
            String resultadoBusqueda;
            if (contenido.toLowerCase().contains(palabraBusqueda.toLowerCase())) {
                resultadoBusqueda = "si esta la palabra";
                System.out.println("si esta la palabra");
            } else {
                resultadoBusqueda = "no esta la palabra";
                System.out.println("no esta la palabra");
            }
            
            String rutaSalida = System.getProperty("user.dir") + File.separator + "archivos";
            File directorioSalida = new File(rutaSalida);
            if (!directorioSalida.exists()) {
                directorioSalida.mkdirs();
            }
            
            String nombreArchivo = archivoOriginal.getName();
            int indice = nombreArchivo.lastIndexOf('.');
            if (indice > 0) {
                nombreArchivo = nombreArchivo.substring(0, indice);
            }
            
            String rutaDestino = rutaSalida + File.separator + nombreArchivo + ".txt";
            Files.write(Paths.get(rutaDestino), resultadoBusqueda.getBytes());
            
            return rutaDestino;
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
            return null;
        }
    }
}
