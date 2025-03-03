/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.convertirarchivotextoabinario;

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

public class ConvertirArchivoTextoABinario{

    public static String convertirABinario(String rutaArchivo) {
        File archivoOriginal = new File(rutaArchivo);

        if (!archivoOriginal.exists() || !archivoOriginal.isFile()) {
            System.err.println("Error: El archivo especificado no existe o no es un archivo válido.");
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
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            StringBuilder contenidoBinario = new StringBuilder();

            for (int i = 0; i < contenido.length(); i++) {
                char caracter = contenido.charAt(i);
                String binario = String.format("%8s", Integer.toBinaryString(caracter))
                                     .replace(' ', '0');
                contenidoBinario.append(binario).append(" ");
            }

            Files.write(Paths.get(rutaDestino), contenidoBinario.toString().getBytes());
            return rutaDestino;
        } catch (IOException e) {
            System.err.println("Error al convertir el archivo a binario: " + e.getMessage());
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
        String salida = convertirABinario(rutaArchivo);
        if (salida != null) {
            System.out.print(salida);
        }
    }
}
