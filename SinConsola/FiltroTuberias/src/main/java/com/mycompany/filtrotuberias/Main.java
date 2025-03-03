/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filtrotuberias;

import com.mycompany.cargararchivodetexto.CargarArchivoDeTexto;
import com.mycompany.convertirarchivotextoabinario.ConvertirArchivoTextoABinario;
import java.io.File;

/**
 *
 * @author juanr
 */
public class Main {
    
    public static void main(String[] args) {
        
        String rutaArchivo = System.getProperty("user.dir") + File.separator + "texto.txt";
        
        Tuberia tuberia = new Tuberia();
        tuberia.agregarFiltro(new CargarArchivoDeTexto());
        tuberia.agregarFiltro(new ConvertirArchivoTextoABinario());

        String resultado = tuberia.ejecutar(rutaArchivo);
        
        if (resultado != null) {
            System.out.println("Tubeeria finalizado exitosamente. Resultado: " + resultado);
        } else {
            System.out.println("Tubeeria interrumpido por errores.");
        }
    }
}
