/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.darktech.cpel;

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
        Usuario newUser = new Usuario("9-757-0000","Robin", "Avila", "67890090", "robin@email.com", 1, 1);
        System.out.println(newUser.list());
    }
}
