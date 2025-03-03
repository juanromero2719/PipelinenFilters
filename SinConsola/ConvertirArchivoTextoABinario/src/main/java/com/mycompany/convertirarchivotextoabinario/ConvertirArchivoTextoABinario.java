/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.convertirarchivotextoabinario;

import com.mycompany.filtro.Filtro;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 *
 * @author juanr
 */

public class ConvertirArchivoTextoABinario implements Filtro{

    @Override
    public String procesar(String entrada) {
         File archivoOriginal = new File(entrada);
        
        if (!archivoOriginal.exists() || !archivoOriginal.isFile()) {
            System.out.println("Error: El archivo especificado no existe o no es un archivo válido.");
            return null;
        }
        
        String rutaBinarios = System.getProperty("user.dir") 
                                + File.separator + "archivos" 
                                + File.separator + "binarios";
        File directorioBinarios = new File(rutaBinarios);
        if (!directorioBinarios.exists()) {
            directorioBinarios.mkdirs();
        }
        
        String rutaDestino = rutaBinarios + File.separator + archivoOriginal.getName() + ".bin";
        
        try {
            
            String contenido = new String(Files.readAllBytes(Paths.get(entrada)));
            StringBuilder contenidoBinario = new StringBuilder();
            
            
            for (int i = 0; i < contenido.length(); i++) {
                char caracter = contenido.charAt(i);
                String binario = String.format("%8s", Integer.toBinaryString(caracter))
                                     .replace(' ', '0');
                contenidoBinario.append(binario).append(" ");
            }
                       
            Files.write(Paths.get(rutaDestino), contenidoBinario.toString().getBytes());
            System.out.println("Archivo convertido a binario y guardado en: " + rutaDestino);
            return rutaDestino;
        } catch (IOException e) {
            System.err.println("Error al convertir el archivo a binario: " + e.getMessage());
            return null;
        }
    }
}
