/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Conexion;
import modelo.EstadoPrestamoDAO;

/**
 *
 * @author BlackOnyxs
 */
public class EstadoPrestamo {
    private int idEstado;
    private String estadoDevolucion;

    public EstadoPrestamo() {
    }

    public EstadoPrestamo(int idEstado, String estadoDevolucion) {
        this.idEstado = idEstado;
        this.estadoDevolucion = estadoDevolucion;
    }

    public EstadoPrestamo(int idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoPrestamo(String estadoDevolucion) {
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstadoDevolucion() {
        return estadoDevolucion;
    }

    public void setEstadoDevolucion(String estadoDevolucion) {
        this.estadoDevolucion = estadoDevolucion;
    }
        
    public String insert(){
        Conexion conn = new Conexion();
        EstadoPrestamoDAO prestamoEstado = new EstadoPrestamoDAO(this.estadoDevolucion);
        return conn.executeUpdate(prestamoEstado.insert());
    }
    
     public String update(){
        Conexion conn = new Conexion();
        EstadoPrestamoDAO prestamoEstado = new EstadoPrestamoDAO(this.idEstado, this.estadoDevolucion);
        return conn.executeUpdate(prestamoEstado.update());
    }
     
    
    public String delete(){
        Conexion conn = new Conexion();
        EstadoPrestamoDAO prestamoEstado = new EstadoPrestamoDAO(this.idEstado);
        return conn.executeUpdate(prestamoEstado.delete());
    }
    
    public ArrayList<EstadoPrestamo> list() {
        ArrayList<EstadoPrestamo> data = new ArrayList<>();
        
        Conexion myConnection = new Conexion();
        EstadoPrestamoDAO prestamoEstado = new EstadoPrestamoDAO();
        
        ResultSet rs = myConnection.executeQuery(prestamoEstado.list());
        
        try {
            while ( rs.next() ) {
                EstadoPrestamo currentEstadoPrestamo = new EstadoPrestamo(rs.getInt("idestado_devolucion"), rs.getString("estado_devolucion"));
                data.add(currentEstadoPrestamo);
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String toString() {
        return "EstadoPrestamo{" + "idEstado=" + idEstado + ", estadoDevolucion=" + estadoDevolucion + '}';
    }
    
}
