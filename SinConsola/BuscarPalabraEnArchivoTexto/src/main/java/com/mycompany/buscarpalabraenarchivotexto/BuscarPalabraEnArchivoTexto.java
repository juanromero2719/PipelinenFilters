/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.buscarpalabraenarchivotexto;

import com.mycompany.filtro.Filtro;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author juanr
 */
public class BuscarPalabraEnArchivoTexto implements Filtro{

    @Override
    public String procesar(String entrada) {
        
        File archivoOriginal = new File(entrada);
        if (!archivoOriginal.exists() || !archivoOriginal.isFile()) {
            System.err.println("Error: El archivo especificado no existe o no es un archivo válido.");
            return null;
        }
        
        try {

            String contenido = new String(Files.readAllBytes(Paths.get(entrada)));        
            String palabraBuscada = "texto";
            String resultadoBusqueda;
            
            if (contenido.toLowerCase().contains(palabraBuscada.toLowerCase())) {
                resultadoBusqueda = "si esta la palabra";
            } else {
                resultadoBusqueda = "no esta la palabra";
            }
            
            String rutaSalida = System.getProperty("user.dir")
                    + File.separator + "archivos";
            
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
