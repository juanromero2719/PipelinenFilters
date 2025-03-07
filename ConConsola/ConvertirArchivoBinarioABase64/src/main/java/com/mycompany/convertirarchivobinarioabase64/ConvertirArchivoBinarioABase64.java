package com.mycompany.convertirarchivobinarioabase64;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ConvertirArchivoBinarioABase64 {

    public static String procesar(String entrada) {
        File archivoOriginal = new File(entrada);
        
        if (!archivoOriginal.exists() || !archivoOriginal.isFile()) {
            System.err.println("Error: El archivo especificado no existe o no es un archivo válido.");
            return null;
        }
        
        String rutaBase64 = System.getProperty("user.dir") + File.separator + "archivos";
        
        File directorioBase64 = new File(rutaBase64);
        if (!directorioBase64.exists()) {
            directorioBase64.mkdirs();
        }
        
        String nombreArchivo = archivoOriginal.getName();
        int indice = nombreArchivo.lastIndexOf('.');
        if (indice > 0) {
            nombreArchivo = nombreArchivo.substring(0, indice);
        }

        String rutaDestino = rutaBase64 + File.separator + nombreArchivo + ".base64";
        
        try {
            byte[] datos = Files.readAllBytes(Paths.get(entrada));
            String contenidoBase64 = Base64.getEncoder().encodeToString(datos);
            Files.write(Paths.get(rutaDestino), contenidoBase64.getBytes());
            
            return rutaDestino;
        } catch (IOException e) {
            System.err.println("Error al convertir el archivo a Base64: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        
        String rutaArchivo;

        if (args.length > 0) {
            rutaArchivo = args[0];
            
        }else{
            
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
