/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.filtrotuberias;


import com.mycompany.filtro.Filtro;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author juanr
 */
public class Tuberia {

    private List<Filtro> filtros = new ArrayList<>();

    public void agregarFiltro(Filtro filtro) {
        filtros.add(filtro);
    }

    public String ejecutar(String entradaInicial) {
        
        String salida = entradaInicial;
        
        for (Filtro filtro : filtros) {
            salida = filtro.procesar(salida);
            if (salida == null) {
                System.out.println("Tuberia interrumpido por error en: " + filtro.getClass().getSimpleName());
                break;
            }
        }
        
        return salida;
    }
}
