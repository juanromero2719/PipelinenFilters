/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filtrotuberias;

import com.mycompany.buscarpalabraenarchivotexto.BuscarPalabraEnArchivoTexto;
import com.mycompany.cargararchivodetexto.CargarArchivoDeTexto;
import com.mycompany.convertirarchivobase64abinario.ConvertirArchivoBase64ABinario;
import com.mycompany.convertirarchivobinarioabase64.ConvertirArchivoBinarioABase64;
import com.mycompany.convertirarchivobinarioatexto.ConvertirArchivoBinarioATexto;
import com.mycompany.convertirarchivotextoabinario.ConvertirArchivoTextoABinario;
import com.mycompany.encriptararchivotextosha256.EncriptarArchivoTextoSHA256;
import com.mycompany.listarpalabrasfrecuenciadeocurrencia.ListarPalabrasFrecuenciaDeOcurrencia;
import java.io.File;

/**
 *
 * @author juanr
 */
public class Main {
    
    public static String ejemplouno(String rutaArchivo){
       
        Tuberia tuberia = new Tuberia();
        tuberia.agregarFiltro(new CargarArchivoDeTexto());
        tuberia.agregarFiltro(new ConvertirArchivoTextoABinario());
        String resultado = tuberia.ejecutar(rutaArchivo);
        return resultado;
    }
    
    public static String ejemplodos(String rutaArchivo){
       
        Tuberia tuberia = new Tuberia();
        tuberia.agregarFiltro(new CargarArchivoDeTexto());
        tuberia.agregarFiltro(new ConvertirArchivoBinarioATexto());
        String resultado = tuberia.ejecutar(rutaArchivo);
        return resultado;
    }
    
    public static String ejemplotres(String rutaArchivo){
       
        Tuberia tuberia = new Tuberia();
        tuberia.agregarFiltro(new CargarArchivoDeTexto());
        tuberia.agregarFiltro(new ConvertirArchivoTextoABinario());
        tuberia.agregarFiltro(new ConvertirArchivoBinarioABase64());
        tuberia.agregarFiltro(new ConvertirArchivoBase64ABinario());
        String resultado = tuberia.ejecutar(rutaArchivo);
        return resultado;
    }
    
    public static String ejemplocuatro(String rutaArchivo){
       
        Tuberia tuberia = new Tuberia();
        tuberia.agregarFiltro(new CargarArchivoDeTexto());
        tuberia.agregarFiltro(new EncriptarArchivoTextoSHA256());
        String resultado = tuberia.ejecutar(rutaArchivo);
        return resultado;
    }
    
    public static String ejemplocinco(String rutaArchivo){
       
        Tuberia tuberia = new Tuberia();
        tuberia.agregarFiltro(new CargarArchivoDeTexto());
        tuberia.agregarFiltro(new BuscarPalabraEnArchivoTexto());
        String resultado = tuberia.ejecutar(rutaArchivo);
        return resultado;
    }
    
    public static String ejemploseis(String rutaArchivo){
        
        Tuberia tuberia = new Tuberia();
        tuberia.agregarFiltro(new CargarArchivoDeTexto());
        tuberia.agregarFiltro(new ListarPalabrasFrecuenciaDeOcurrencia());
        String resultado = tuberia.ejecutar(rutaArchivo);
        return resultado;
    }
    
    
    public static void main(String[] args) {
               
        //Paso 1
        //String  rutaArchivoEjemploUno = System.getProperty("user.dir") + File.separator + "ejemplouno.txt";
        //ejemplouno(rutaArchivoEjemploUno);
        
        // Paso 2
        //String rutaArchivoEjemploDos = System.getProperty("user.dir") + File.separator + "ejemplodos.bin";
        //ejemplodos(rutaArchivoEjemploDos);
        
        // Paso 3
        //String rutaArchivoEjemploTres = System.getProperty("user.dir") + File.separator + "ejemplotres.txt";
        //ejemplotres(rutaArchivoEjemploTres);
        
        // Paso 4
        //String rutaArchivoEjemploCuatro = System.getProperty("user.dir") + File.separator + "ejemplocuatro.txt";
        //ejemplocuatro(rutaArchivoEjemploCuatro);
        
        // Paso 5
        //String rutaArchivoEjemploCinco = System.getProperty("user.dir") + File.separator + "ejemplocinco.txt";
        //ejemplocinco(rutaArchivoEjemploCinco);
        
        // Paso 6
        String rutaArchivoEjemploSeis = System.getProperty("user.dir") + File.separator + "ejemploseis.txt";
        ejemploseis(rutaArchivoEjemploSeis);
        
    }
}
