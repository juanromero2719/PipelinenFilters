/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cargararchivodetexto;

import com.mycompany.filtro.Filtro;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author juanr
 */

public class CargarArchivoDeTexto implements Filtro {

    @Override
    public String procesar(String entrada) {
        
        String rutaRaiz = System.getProperty("user.dir") + File.separator + "archivos";

        File archivoOriginal = new File(entrada);
        if (!archivoOriginal.exists() || !archivoOriginal.isFile()) {
            System.out.println("Error: El archivo especificado no existe o no es un archivo válido.");
            return null;
        }
        
        String nombreArchivo = archivoOriginal.getName();
        int indice = nombreArchivo.lastIndexOf('.');
        if (indice > 0) {
            nombreArchivo = nombreArchivo.substring(0, indice);
        }
        
        File directorioDestino = new File(rutaRaiz);
        if (!directorioDestino.exists()) {
            directorioDestino.mkdirs();
        }

        String rutaDestino = rutaRaiz + File.separator + nombreArchivo;

        try {
            Files.copy(Paths.get(entrada), Paths.get(rutaDestino), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado con éxito a: " + rutaDestino);
            return rutaDestino;
        } catch (IOException e) {
            System.err.println("Error al copiar el archivo: " + e.getMessage());
        }
        
        return rutaDestino;
        
    }

}