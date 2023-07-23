/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Logica.Usuario;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author BlackOnyxs
 */
public class FileUtils {
    
    public static ArrayList<Usuario> loadFromFile(Component component )throws IOException{
        ArrayList<Usuario> users = new ArrayList<>();
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV", "csv");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(component);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 7) {
                                String cedula = data[0];
                                String primer_nombre = data[1];
                                String primer_apellido = data[2];
                                String telefono = data[3];
                                String correo = data[4];
                                String tipousuario = data[5];
                                String idcarrera = data[6];
                                Usuario newObject = new Usuario(cedula,primer_nombre, primer_apellido, telefono, correo, Integer.parseInt(tipousuario), Integer.parseInt(idcarrera));
                                users.add( newObject);
                            }
                    
                }
                br.close();
            } catch (IOException ex) {
                throw ex;
            } 
        }
    return users;
    }
   public static void saveContactsToFile(ArrayList<Usuario> users, Component component)throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV", "csv");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showSaveDialog(component);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                for (Usuario user : users) {
                    String line = user.getCedula()+ "," + user.getPrimerNombre()+ "," 
                            + user.getPrimerNombre()+","+ user.getTelefono()+","
                            + user.getCorreo()+","+user.getIdTipoUsuario()+ ","
                            +user.getIdCarrera();
                    bw.write(line);
                    bw.newLine();
                }
                bw.close();
                
            } catch (IOException ex) {
                ex.printStackTrace();
               throw ex;
            }
        }
    }
}
