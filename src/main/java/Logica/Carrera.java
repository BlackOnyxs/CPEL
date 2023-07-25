/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.CarreraDAO;
import modelo.Conexion;
import modelo.SearchTypesDAO;

/**
 *
 * @author BlackOnyxs
 */
public class Carrera {
    private int idCarrera;
    private String nombreCarrera;

    public Carrera() {
    }
    
    

    public Carrera(int idCarrera, String nombreCarrera) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
    }

    public Carrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }   
    
     public Carrera save(){
        Conexion conn = new Conexion();
        CarreraDAO carrerDao = new CarreraDAO(this.idCarrera, this.nombreCarrera);
        
        ResultSet rs = conn.executeQuery(carrerDao.save());
        Carrera currentCarrer = null;
        try{
         while ( rs.next() ) {
                currentCarrer = new Carrera(rs.getInt("idCarrera"), rs.getString("nombre_carrera"));
               
            }
          } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentCarrer;
    }
     
    public ArrayList<Carrera> search(String value) {
        ArrayList<Carrera> data = new ArrayList<>();
        
        Conexion myConnection = new Conexion();
        CarreraDAO carrerDao = new CarreraDAO();
        
        ResultSet rs = myConnection.executeQuery(carrerDao.search(value));
        
        try {
            while ( rs.next() ) {
                Carrera currentCarrer = new Carrera(rs.getInt("idCarrera"), rs.getString("nombre_carrera"));
                data.add(currentCarrer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public String delete(){
        Conexion conn = new Conexion();
        CarreraDAO career = new CarreraDAO(this.idCarrera);
        return conn.executeUpdate(career.delete());
    }
    
    public ArrayList<Carrera> list(int skip, int sort) {
        ArrayList<Carrera> data = new ArrayList<>();
        
        Conexion myConnection = new Conexion();
        CarreraDAO careerDao = new CarreraDAO();
        
        ResultSet rs = myConnection.executeQuery(careerDao.list( skip, sort ));
        
        try {
            while ( rs.next() ) {
                Carrera currentCarrer = new Carrera(rs.getInt("idCarrera"), rs.getString("nombre_carrera"));
                data.add(currentCarrer);
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String toString() {
        return "Carrera{" + "idCarrera=" + idCarrera + ", nombreCarrera=" + nombreCarrera + '}';
    }
    
    
}
