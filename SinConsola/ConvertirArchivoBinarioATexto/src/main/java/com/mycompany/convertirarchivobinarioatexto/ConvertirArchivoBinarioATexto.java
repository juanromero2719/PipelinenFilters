/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.convertirarchivobinarioatexto;

import com.mycompany.filtro.Filtro;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author juanr
 */
public class ConvertirArchivoBinarioATexto implements Filtro{

    @Override
    public String procesar(String entrada) {
        
         File archivoOriginal = new File(entrada);
        
        if (!archivoOriginal.exists() || !archivoOriginal.isFile()) {
            System.err.println("Error: El archivo especificado no existe o no es un archivo válido.");
            return null;
        }

        String rutaTexto = System.getProperty("user.dir")
                            + File.separator + "archivos";
        
        
        File directorioTexto = new File(rutaTexto);
        if (!directorioTexto.exists()) {
            directorioTexto.mkdirs();
        }

        String nombreArchivo = archivoOriginal.getName();
        int indice = nombreArchivo.lastIndexOf('.');
        
        if (indice > 0) {
            nombreArchivo = nombreArchivo.substring(0, indice);
        }

        String rutaDestino = rutaTexto + File.separator + nombreArchivo + ".txt";
        
        try {

            String contenidoBinario = new String(Files.readAllBytes(Paths.get(entrada)));
            StringBuilder contenidoTexto = new StringBuilder();
            
            String[] tokens = contenidoBinario.trim().split("\\s+");
            for (String token : tokens) {
                if (!token.isEmpty()) {
                    int valor = Integer.parseInt(token, 2);
                    contenidoTexto.append((char) valor);
                }
            }
            
            Files.write(Paths.get(rutaDestino), contenidoTexto.toString().getBytes());
            return rutaDestino;
        } catch (IOException e) {
            System.err.println("Error al convertir el archivo a texto: " + e.getMessage());
            return null;
        }
        
    }
}
