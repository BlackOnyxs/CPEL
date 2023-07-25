/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Logica.Carrera;
import Logica.Equipo;
import Logica.Operador;
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

    public static ArrayList<Usuario> loadUsersFromFile(Component component) throws IOException {
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
                        Usuario newObject = new Usuario(cedula, primer_nombre, primer_apellido, telefono, correo, Integer.parseInt(tipousuario), Integer.parseInt(idcarrera));
                        users.add(newObject);
                    }

                }
                br.close();
            } catch (IOException ex) {
                throw ex;
            }
        }
        return users;
    }

    public static void saveUsersToFile(ArrayList<Usuario> users, Component component) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV", "csv");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showSaveDialog(component);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                for (Usuario user : users) {
                    String line = user.getCedula() + "," + user.getPrimerNombre() + ","
                            + user.getPrimerNombre() + "," + user.getTelefono() + ","
                            + user.getCorreo() + "," + user.getIdTipoUsuario() + ","
                            + user.getIdCarrera();
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
    
    public static ArrayList<Operador> loadOperatorsFromFile(Component component) throws IOException {
        ArrayList<Operador> operators = new ArrayList<>();
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
                    if (data.length == 3) {
                        String cedula = data[0];
                        String primer_nombre = data[1];
                        String primer_apellido = data[2];
                        Operador newObject = new Operador(cedula, primer_nombre, primer_apellido);
                        operators.add(newObject);
                    }

                }
                br.close();
            } catch (IOException ex) {
                throw ex;
            }
        }
        return operators;
    }
    
    public static void saveOperatorsToFile(ArrayList<Operador> operators, Component component) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV", "csv");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showSaveDialog(component);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                for (Operador user : operators) {
                    String line = user.getCedula() + "," + user.getPrimerNombre() + ","
                            + user.getPrimerNombre();
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
   public static ArrayList<Equipo> loadEquipmentsFromFile(Component component) throws IOException {
        ArrayList<Equipo> equipments = new ArrayList<>();
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
                    if (data.length == 4) {
                        String id = data[0];
                        String descripcion = data[1];
                        String placa = data[2];
                        String fecha = data[3];                        
                        Equipo newObject = new Equipo( Integer.parseInt(id), descripcion, placa, fecha);
                        equipments.add(newObject);
                    }
                }
                br.close();
            } catch (IOException ex) {
                throw ex;
            }
        }
        return equipments;
    }

    public static void saveEquipmentsToFile(ArrayList<Equipo> equipments, Component component) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV", "csv");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showSaveDialog(component);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                for (Equipo equipment : equipments) {
                    String line = String.valueOf(equipment.getIdEquipo())+ "," + equipment.getDescripcion() + ","
                            + equipment.getPlacaInventario()+ "," + equipment.getFechaCompra();
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
    
    public static ArrayList<Carrera> loadCareersFromFile(Component component) throws IOException {
        ArrayList<Carrera> careers = new ArrayList<>();
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
                    if (data.length == 2) {
                        String id = data[0];
                        String nombre = data[1];
                        Carrera newObject = new Carrera(Integer.parseInt(id), nombre);
                        careers.add(newObject);
                    }

                }
                br.close();
            } catch (IOException ex) {
                throw ex;
            }
        }
        return careers;
    }
    
    public static void saveCareersToFile(ArrayList<Carrera> careers, Component component) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV", "csv");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showSaveDialog(component);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                for (Carrera career : careers) {
                    String line = career.getIdCarrera()+ "," + career.getNombreCarrera();
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
