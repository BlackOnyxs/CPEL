/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.darktech.cpel;

import Logica.Equipo;
import Logica.Usuario;
import static modelo.SearchTypes.*;

/**
 *
 * @author BlackOnyxs
 */
public class CPEL {

    public static void main(String[] args) {
        System.out.println("\n******************************\n");
        System.out.println("Insertando un escritor en la tabla");
        Equipo equipo = new Equipo(5);
        System.out.println(equipo.search(CATEGORY, "4"));
    }
}
