/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.convertirarchivobinarioatexto;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author juanr
 */
public class ConvertirArchivoBinarioATexto {

    public static String procesar(String entrada) {
        File archivoOriginal = new File(entrada);
        
        if (!archivoOriginal.exists() || !archivoOriginal.isFile()) {
            System.err.println("Error: El archivo especificado no existe o no es un archivo válido.");
            return null;
        }

        // Definir la carpeta de salida ("./archivos")
        String rutaTexto = System.getProperty("user.dir") + File.separator + "archivos";
        File directorioTexto = new File(rutaTexto);
        if (!directorioTexto.exists()) {
            directorioTexto.mkdirs();
        }

        // Extraer el nombre del archivo sin extensión
        String nombreArchivo = archivoOriginal.getName();
        int indice = nombreArchivo.lastIndexOf('.');
        if (indice > 0) {
            nombreArchivo = nombreArchivo.substring(0, indice);
        }

        // Armar la ruta de salida con extensión ".txt"
        String rutaDestino = rutaTexto + File.separator + nombreArchivo + ".txt";
        
        try {
            String contenidoBinario = new String(Files.readAllBytes(Paths.get(entrada)));
            StringBuilder contenidoTexto = new StringBuilder();
            
            // Se asume que cada grupo binario está separado por espacios
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
    
    // Main para ejecutar este proceso de forma independiente o mediante tubería.
    public static void main(String[] args) {
        String rutaArchivo;
        if (args.length > 0) {
            rutaArchivo = args[0];
        } else {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                rutaArchivo = br.readLine();
            } catch (IOException e) {
                System.err.println("Error al leer la entrada: " + e.getMessage());
                return;
            }
        }
        
        String salida = procesar(rutaArchivo);
        if (salida != null) {
            // Imprime únicamente la ruta del archivo generado para encadenarlo en una tubería
            System.out.print(salida);
        }
    }
}