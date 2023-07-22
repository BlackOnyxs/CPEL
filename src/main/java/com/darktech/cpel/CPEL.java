/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.darktech.cpel;


import Logica.Prestamo;

/**
 *
 * @author BlackOnyxs
 */
public class CPEL {

    public static void main(String[] args) {
        System.out.println("\n******************************\n");
        System.out.println("Insertando un escritor en la tabla");
        
        Prestamo p = new Prestamo(5, "2023-07-20", "2-2-2",1, "Cum-2");
        System.out.print(p.update());
    }
}
