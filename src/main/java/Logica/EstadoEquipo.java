/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Conexion;
import modelo.EstadoEquipoDAO;

/**
 *
 * @author BlackOnyxs
 */
public class EstadoEquipo {
    private int idEstado;
    private String descripcion;

    public EstadoEquipo(int idEstado, String descripcion) {
        this.idEstado = idEstado;
        this.descripcion = descripcion;
    }

    public EstadoEquipo(int idEstado) {
        this.idEstado = idEstado;
    }
    

    public EstadoEquipo(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCarrera() {
        return idEstado;
    }

    public void setIdCarrera(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreCarrera() {
        return descripcion;
    }

    public void setNombreCarrera(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String insert(){
        Conexion conn = new Conexion();
        EstadoEquipoDAO equipoEstado = new EstadoEquipoDAO(this.descripcion);
        return conn.executeUpdate(equipoEstado.insert());
    }
    
     public String update(){
        Conexion conn = new Conexion();
        EstadoEquipoDAO equipoEstado = new EstadoEquipoDAO(this.idEstado, this.descripcion);
        return conn.executeUpdate(equipoEstado.update());
    }
     
    public boolean getById () {
        Conexion myConnection = new Conexion();
        EstadoEquipoDAO equipoEstado = new EstadoEquipoDAO(this.idEstado);
        
        ResultSet rs = myConnection.executeQuery(equipoEstado.getById());
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
    public ArrayList<EstadoEquipo> getByDescription() {
        ArrayList<EstadoEquipo> data = new ArrayList<>();
        
        Conexion myConnection = new Conexion();
        EstadoEquipoDAO equipoEstado = new EstadoEquipoDAO();
        
        ResultSet rs = myConnection.executeQuery(equipoEstado.getByDescription());
        
        try {
            while ( rs.next() ) {
                EstadoEquipo currentEstadoEquipo = new EstadoEquipo(rs.getInt("idestado_equipo"), rs.getString("descripcion"));
                data.add(currentEstadoEquipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public String delete(){
        Conexion conn = new Conexion();
        EstadoEquipoDAO equipoEstado = new EstadoEquipoDAO(this.idEstado);
        return conn.executeUpdate(equipoEstado.delete());
    }
    
    public ArrayList<EstadoEquipo> list() {
        ArrayList<EstadoEquipo> data = new ArrayList<>();
        
        Conexion myConnection = new Conexion();
        EstadoEquipoDAO tipoUsuario = new EstadoEquipoDAO();
        
        ResultSet rs = myConnection.executeQuery(tipoUsuario.list());
        
        try {
            while ( rs.next() ) {
                System.out.println("Si");
                EstadoEquipo currentEstadoEquipo = new EstadoEquipo(rs.getInt("idestado_equipo"), rs.getString("descripcion"));
                data.add(currentEstadoEquipo);
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String toString() {
        return "TipoUsuario{" + "idTipoUsuario=" + idEstado + ", descripcion=" + descripcion + '}';
    }

    
    
    
}
