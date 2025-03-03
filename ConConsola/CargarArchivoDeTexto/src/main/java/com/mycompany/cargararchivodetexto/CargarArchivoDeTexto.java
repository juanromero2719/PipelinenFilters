/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cargararchivodetexto;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author juanr
 */

public class CargarArchivoDeTexto {

    public static String copiarArchivo(String rutaArchivo) {
        
        String rutaRaiz = System.getProperty("user.dir") + File.separator + "archivos";

        File archivoOriginal = new File(rutaArchivo);
        if (!archivoOriginal.exists() || !archivoOriginal.isFile()) {
            System.err.println("Error: El archivo especificado no existe o no es un archivo válido.");
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
            Files.copy(Paths.get(rutaArchivo), Paths.get(rutaDestino), StandardCopyOption.REPLACE_EXISTING);
            return rutaDestino;
        } catch (IOException e) {
            System.err.println("Error al copiar el archivo: " + e.getMessage());
            return null;
        }
    }

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
        String salida = copiarArchivo(rutaArchivo);
        if (salida != null) {
            System.out.print(salida);
        }
    }

}