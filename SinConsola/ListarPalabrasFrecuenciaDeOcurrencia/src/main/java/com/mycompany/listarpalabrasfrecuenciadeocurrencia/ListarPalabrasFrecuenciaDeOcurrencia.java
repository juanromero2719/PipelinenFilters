/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.listarpalabrasfrecuenciadeocurrencia;

import com.mycompany.filtro.Filtro;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juanr
 */
public class ListarPalabrasFrecuenciaDeOcurrencia implements Filtro{

    @Override
    public String procesar(String entrada) {
        
        File archivoOriginal = new File(entrada);
        if (!archivoOriginal.exists() || !archivoOriginal.isFile()) {
            System.err.println("Error: El archivo especificado no existe o no es un archivo válido.");
            return null;
        }
        
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(entrada)));
            
            String[] palabras = contenido.toLowerCase().split("\\W+");
            
            Map<String, Integer> frecuencia = new HashMap<>();
            for (String palabra : palabras) {
                if (!palabra.isEmpty()) {
                    frecuencia.put(palabra, frecuencia.getOrDefault(palabra, 0) + 1);
                }
            }

            List<String> listaPalabras = new ArrayList<>(frecuencia.keySet());
            Collections.sort(listaPalabras);
            
            StringBuilder salidaBuilder = new StringBuilder();
            for (String palabra : listaPalabras) {
                salidaBuilder.append(palabra)
                             .append(": ")
                             .append(frecuencia.get(palabra))
                             .append(System.lineSeparator());
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
            
            Files.write(Paths.get(rutaDestino), salidaBuilder.toString().getBytes());

            return rutaDestino;
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
            return null;
        }
        
    }


}
