package com.mycompany.buscarpalabraenarchivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BuscarPalabraEnArchivoTexto {

    // Atributo para la palabra a buscar
    private String palabraBusqueda = "texto";

    // Setter para establecer la palabra a buscar
    public void setPalabraBusqueda(String palabraBusqueda) {
        this.palabraBusqueda = palabraBusqueda;
    }

    // Lógica de búsqueda
    public String procesar(String rutaArchivo) {

        File archivoOriginal = new File(rutaArchivo);
        if (!archivoOriginal.exists() || !archivoOriginal.isFile()) {
            System.err.println("Error: El archivo especificado no existe o no es un archivo válido.");
            return null;
        }

        try {
            // Lee todo el contenido del archivo
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));

            // Realiza la búsqueda
            String resultadoBusqueda;
            if (contenido.toLowerCase().contains(palabraBusqueda.toLowerCase())) {
                resultadoBusqueda = "si esta la palabra";
                System.out.println("si esta la palabra");
            } else {
                resultadoBusqueda = "no esta la palabra";
                System.out.println("no esta la palabra");
            }

            // Prepara la carpeta de salida
            String rutaSalida = System.getProperty("user.dir") + File.separator + "archivos";
            File directorioSalida = new File(rutaSalida);
            if (!directorioSalida.exists()) {
                directorioSalida.mkdirs();
            }

            // Prepara el nombre del archivo de salida
            String nombreArchivo = archivoOriginal.getName();
            int indice = nombreArchivo.lastIndexOf('.');
            if (indice > 0) {
                nombreArchivo = nombreArchivo.substring(0, indice);
            }
            String rutaDestino = rutaSalida + File.separator + nombreArchivo + ".txt";

            // Escribe el resultado de la búsqueda en un archivo .txt
            Files.write(Paths.get(rutaDestino), resultadoBusqueda.getBytes());

            // Retorna la ruta del archivo con el resultado
            return rutaDestino;

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            // 1) Leer la ruta del archivo desde la tubería (stdin).
            //    Esta ruta es la salida del primer jar (CargarArchivoDeTexto.jar)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String rutaArchivo = br.readLine(); // ruta del archivo copiado

            // 2) Leer la palabra a buscar desde los argumentos.
            //    Si no se proporciona argumento, se usará "texto" por defecto
            String palabra = (args.length > 0) ? args[0] : "texto";

            // 3) Crear instancia de la clase, configurar la palabra y procesar
            BuscarPalabraEnArchivoTexto buscador = new BuscarPalabraEnArchivoTexto();
            buscador.setPalabraBusqueda(palabra);

            String salida = buscador.procesar(rutaArchivo);

            // Opcional: si deseas encadenar más procesos, imprime la ruta de salida
            // (o algún dato) por stdout. Si no, simplemente terminas aquí.
            if (salida != null) {
                // En este ejemplo, solo se imprime para saber cuál fue el archivo de resultado
                System.out.print(salida);
            }

        } catch (IOException e) {
            System.err.println("Error al leer la entrada desde la tubería: " + e.getMessage());
        }
    }
}
