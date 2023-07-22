/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Conexion;
import modelo.TipoUsuarioDAO;

/**
 *
 * @author BlackOnyxs
 */
public class TipoUsuario {
    private int idTipoUsuario;
    private String descripcion;

    public TipoUsuario(int idTipoUsuario, String descripcion) {
        this.idTipoUsuario = idTipoUsuario;
        this.descripcion = descripcion;
    }

    public TipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }
    

    public TipoUsuario(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCarrera() {
        return idTipoUsuario;
    }

    public void setIdCarrera(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombreCarrera() {
        return descripcion;
    }

    public void setNombreCarrera(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String insert(){
        Conexion conn = new Conexion();
        TipoUsuarioDAO career = new TipoUsuarioDAO(this.descripcion);
        return conn.executeUpdate(career.insert());
    }
    
     public String update(){
        Conexion conn = new Conexion();
        TipoUsuarioDAO career = new TipoUsuarioDAO(this.idTipoUsuario, this.descripcion);
        return conn.executeUpdate(career.update());
    }
     
    public boolean getById () {
        Conexion myConnection = new Conexion();
        TipoUsuarioDAO career = new TipoUsuarioDAO(this.idTipoUsuario);
        
        ResultSet rs = myConnection.executeQuery(career.getById());
        try {
            if ( rs.next() ) {
                this.descripcion = rs.getString("descripcion");
                return true;
            }else return false;
        }catch( SQLException e ) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<TipoUsuario> getByDescription() {
        ArrayList<TipoUsuario> data = new ArrayList<>();
        
        Conexion myConnection = new Conexion();
        TipoUsuarioDAO tipoUsuarioDao = new TipoUsuarioDAO();
        
        ResultSet rs = myConnection.executeQuery(tipoUsuarioDao.getByDescription());
        
        try {
            while ( rs.next() ) {
                TipoUsuario currentEquipo = new TipoUsuario(rs.getInt("tipousuario"), rs.getString("descripcion"));
                data.add(currentEquipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public String delete(){
        Conexion conn = new Conexion();
        TipoUsuarioDAO tipoUsuario = new TipoUsuarioDAO(this.idTipoUsuario);
        return conn.executeUpdate(tipoUsuario.delete());
    }
    
    public ArrayList<TipoUsuario> list() {
        ArrayList<TipoUsuario> data = new ArrayList<>();
        
        Conexion myConnection = new Conexion();
        TipoUsuarioDAO tipoUsuario = new TipoUsuarioDAO();
        
        ResultSet rs = myConnection.executeQuery(tipoUsuario.list());
        
        try {
            while ( rs.next() ) {
                System.out.println("Si");
                TipoUsuario currentEquipo = new TipoUsuario(rs.getInt("tipousuario"), rs.getString("descripcion"));
                data.add(currentEquipo);
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String toString() {
        return "TipoUsuario{" + "idTipoUsuario=" + idTipoUsuario + ", descripcion=" + descripcion + '}';
    }

    
    
    
}
