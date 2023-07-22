/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Conexion;
import modelo.OperadorDAO;
import modelo.SearchTypesDAO;

/**
 *
 * @author BlackOnyxs
 */
public class Operador {
    private String cedula;
    private String primerNombre;
    private String primerApellido;

    public Operador() {
    }
    
    

    public Operador(String cedula, String primerNombre, String primerApellido) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
    }

    public Operador(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    
    
     public String insert(){
        Conexion conn = new Conexion();
        OperadorDAO user = new OperadorDAO(this.cedula,this.primerNombre, this.primerApellido);
        return conn.executeUpdate(user.insert());
    }
    
    public String update(){
        Conexion conn = new Conexion();
        OperadorDAO user = new OperadorDAO(this.cedula,this.primerNombre, this.primerApellido);
        return conn.executeUpdate(user.update());
    }
    
    public String delete(){
        Conexion conn = new Conexion();
        OperadorDAO user = new OperadorDAO(this.cedula);
        return conn.executeUpdate(user.delete());
    }
    public ArrayList<Operador> search(SearchTypesDAO st, String filter) {
         ArrayList<Operador> data = new ArrayList<>();
        Conexion conn = new Conexion();
        OperadorDAO user = new OperadorDAO();
        ResultSet rs = conn.executeQuery(user.search(st, filter));
        
        try {
            while ( rs.next() ) {
                Operador currentOperador = new Operador(rs.getString("cedula"), rs.getString("primer_nombre"), 
                        rs.getString("primer_apellido"));
                data.add(currentOperador);
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return data;
    }
    
    public ArrayList<Operador> list() {
        ArrayList<Operador> data = new ArrayList<>();
        
        Conexion myConnection = new Conexion();
        OperadorDAO userDao = new OperadorDAO();
        
        ResultSet rs = myConnection.executeQuery(userDao.list());
        
        try {
            while ( rs.next() ) {
                 Operador currentOperador = new Operador(rs.getString("cedula"), rs.getString("primer_nombre"), 
                        rs.getString("primer_apellido"));
                data.add(currentOperador);
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String toString() {
        return "Operador{" + "cedula=" + cedula + ", primerNombre=" + primerNombre + ", primerApellido=" + primerApellido + '}';
    }

    
    
}
