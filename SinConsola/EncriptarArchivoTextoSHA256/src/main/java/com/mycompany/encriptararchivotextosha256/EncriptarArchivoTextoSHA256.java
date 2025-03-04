/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.encriptararchivotextosha256;

import com.mycompany.filtro.Filtro;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author juanr
 */
public class EncriptarArchivoTextoSHA256 implements Filtro{

    @Override
    public String procesar(String entrada) {
        
        File archivoOriginal = new File(entrada);
        if (!archivoOriginal.exists() || !archivoOriginal.isFile()) {
            System.err.println("Error: El archivo especificado no existe o no es un archivo válido.");
            return null;
        }
        

        String rutaEncriptado = System.getProperty("user.dir")
                                + File.separator + "archivos";
        
        File directorioEncriptado = new File(rutaEncriptado);
        if (!directorioEncriptado.exists()) {
            directorioEncriptado.mkdirs();
        }
        
        String nombreArchivo = archivoOriginal.getName();
        int indice = nombreArchivo.lastIndexOf('.');
        if (indice > 0) {
            nombreArchivo = nombreArchivo.substring(0, indice);
        }
        
        String rutaDestino = rutaEncriptado + File.separator + nombreArchivo + ".sha256";
        
        try {

            byte[] contenido = Files.readAllBytes(Paths.get(entrada));
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(contenido);
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }
            
            Files.write(Paths.get(rutaDestino), hexString.toString().getBytes());
            
            return rutaDestino;
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println("Error al encriptar el archivo con SHA-256: " + e.getMessage());
            return null;
        }
        
    }


}
