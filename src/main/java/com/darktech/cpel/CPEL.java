/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.darktech.cpel;

import Logica.Carrera;

/**
 *
 * @author BlackOnyxs
 */
public class CPEL {

    public static void main(String[] args) {
         System.out.println("\n******************************\n");
        System.out.println("Insertando un escritor en la tabla");
        Carrera newCarrera = new Carrera(7, "Sistemas - Uppdated");
        System.out.println(newCarrera.list());
    }
}
