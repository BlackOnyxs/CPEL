/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.darktech.cpel;

import Logica.Equipo;
import Logica.Operador;
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
        
        Operador operador = new Operador("9-157-0000", "Robin - 2", "Avila");
        System.out.print(operador.list());
    }
}
