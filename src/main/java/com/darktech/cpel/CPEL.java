/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.darktech.cpel;

import Logica.Equipo;
import Logica.TipoUsuario;
import Logica.Usuario;
import static modelo.SearchTypesDAO.*;

/**
 *
 * @author BlackOnyxs
 */
public class CPEL {

    public static void main(String[] args) {
        System.out.println("\n******************************\n");
        System.out.println("Insertando un escritor en la tabla");
        TipoUsuario user = new TipoUsuario(4, "Conserge 2");
        System.out.println(user.delete());
    }
}
