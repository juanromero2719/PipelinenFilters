/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.convertirarchivobase64abinario;

import com.mycompany.filtro.Filtro;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 *
 * @author juanr
 */
public class ConvertirArchivoBase64ABinario  {

    public static String procesar(String entrada) {
        
        File archivoOriginal = new File(entrada);
        if (!archivoOriginal.exists() || !archivoOriginal.isFile()) {
            System.err.println("Error: El archivo especificado no existe o no es un archivo válido.");
            return null;
        }
        
        String rutaBinarios = System.getProperty("user.dir")
                + File.separator + "archivos";

        File directorioDestino = new File(rutaBinarios);
        if (!directorioDestino.exists()) {
            directorioDestino.mkdirs();
        }
        
        String nombreArchivo = archivoOriginal.getName();
        int indice = nombreArchivo.lastIndexOf('.');
        if (indice > 0) {
            nombreArchivo = nombreArchivo.substring(0, indice);
        }
        
        String rutaDestino = rutaBinarios + File.separator + nombreArchivo + ".bin";
        
        try {

            String contenidoBase64 = new String(Files.readAllBytes(Paths.get(entrada))).trim();
            byte[] datos = Base64.getDecoder().decode(contenidoBase64);
            Files.write(Paths.get(rutaDestino), datos);
            return rutaDestino;
        } catch (IOException e) {
            System.err.println("Error al convertir el archivo de Base64 a binario: " + e.getMessage());
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
        
        String salida = procesar(rutaArchivo);
        if (salida != null) {
            System.out.print(salida);
        }
    }
    
}
