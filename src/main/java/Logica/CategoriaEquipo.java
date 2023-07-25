/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.CategoriaEquiposDAO;
import modelo.Conexion;

/**
 *
 * @author BlackOnyxs
 */
public class CategoriaEquipo {
    private int idCategoria;
    private String nombreCategoria;

    public CategoriaEquipo() {
    }

    public CategoriaEquipo(int idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

    public CategoriaEquipo(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CategoriaEquipo(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }        
    
    
    public String insert(){
        Conexion conn = new Conexion();
        CategoriaEquiposDAO categoriaEquipoDAO = new CategoriaEquiposDAO(this.nombreCategoria);
        return conn.executeUpdate(categoriaEquipoDAO.insert());
    }
    
     public String update(){
        Conexion conn = new Conexion();
        CategoriaEquiposDAO categoriaEquipoDAO = new CategoriaEquiposDAO(this.idCategoria, this.nombreCategoria);
        return conn.executeUpdate(categoriaEquipoDAO.update());
    }
     
    public boolean getById () {
        Conexion myConnection = new Conexion();
        CategoriaEquiposDAO categoriaEquipoDAO = new CategoriaEquiposDAO(this.idCategoria);
        
        ResultSet rs = myConnection.executeQuery(categoriaEquipoDAO.getById());
        try {
            if ( rs.next() ) {
                this.nombreCategoria = rs.getString("descripcion");
                return true;
            }else return false;
        }catch( SQLException e ) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<CategoriaEquipo> getByDescription() {
        ArrayList<CategoriaEquipo> data = new ArrayList<>();
        
        Conexion myConnection = new Conexion();
        CategoriaEquiposDAO categoriaEquipoDAO = new CategoriaEquiposDAO();
        
        ResultSet rs = myConnection.executeQuery(categoriaEquipoDAO.getByDescription());
        
        try {
            while ( rs.next() ) {
                CategoriaEquipo currentCategoria = new CategoriaEquipo(rs.getInt("idcategoria"), rs.getString("nombre_categoria"));
                data.add(currentCategoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public String delete(){
        Conexion conn = new Conexion();
        CategoriaEquiposDAO categoriaEquipoDAO = new CategoriaEquiposDAO(this.idCategoria);
        return conn.executeUpdate(categoriaEquipoDAO.delete());
    }
    
    public ArrayList<CategoriaEquipo> list() {
        ArrayList<CategoriaEquipo> data = new ArrayList<>();
        
        Conexion myConnection = new Conexion();
        CategoriaEquiposDAO categoriaEquipoDAO = new CategoriaEquiposDAO();
        
        ResultSet rs = myConnection.executeQuery(categoriaEquipoDAO.list());
        
        try {
            while ( rs.next() ) {
                CategoriaEquipo currentCategoria = new CategoriaEquipo(rs.getInt("idcategoria"), rs.getString("nombre_categoria"));     
                data.add(currentCategoria);
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String toString() {
        return "CategoriaEquipo{" + "idCategoria=" + idCategoria + ", nombreCategoria=" + nombreCategoria + '}';
    }

    

    
    
    
}
