/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Conexion;
import modelo.PrestamoDAO;

/**
 *
 * @author BlackOnyxs
 */
public class Prestamo {
      private int idPrestamo;
    private String fechaSolicitud;
    private int idEquipo;
    private String cedulaUsuario;
    private String cedulaOperadorPrestamo;
    private String fechaDevolicion;
    private String cedulaOperadorDevolucion;
    private int idEstado;
    private String observacion;

    public Prestamo() {
    }
    
    

    public Prestamo(int idPrestamo, String fechaSolicitud, int idEquipo, String cedulaUsuario, String cedulaOperadorPrestamo, String fechaDevolicion, String cedulaOperadorDevolucion, int idEstado, String observacion) {
        this.idPrestamo = idPrestamo;
        this.fechaSolicitud = fechaSolicitud;
        this.idEquipo = idEquipo;
        this.cedulaUsuario = cedulaUsuario;
        this.cedulaOperadorPrestamo = cedulaOperadorPrestamo;
        this.fechaDevolicion = fechaDevolicion;
        this.cedulaOperadorDevolucion = cedulaOperadorDevolucion;
        this.idEstado = idEstado;
        this.observacion = observacion;
    }

    public Prestamo(String fechaSolicitud, int idEquipo, String cedulaUsuario, String cedulaOperadorPrestamo, String fechaDevolicion, String cedulaOperadorDevolucion, int idEstado, String observacion) {
        this.fechaSolicitud = fechaSolicitud;
        this.idEquipo = idEquipo;
        this.cedulaUsuario = cedulaUsuario;
        this.cedulaOperadorPrestamo = cedulaOperadorPrestamo;
        this.fechaDevolicion = fechaDevolicion;
        this.cedulaOperadorDevolucion = cedulaOperadorDevolucion;
        this.idEstado = idEstado;
        this.observacion = observacion;
    }

    public Prestamo(int idPrestamo, String fechaSolicitud, int idEquipo, String cedulaUsuario, String cedulaOperadorPrestamo, int idEstado, String observacion) {
        this.idPrestamo = idPrestamo;
        this.fechaSolicitud = fechaSolicitud;
        this.idEquipo = idEquipo;
        this.cedulaUsuario = cedulaUsuario;
        this.cedulaOperadorPrestamo = cedulaOperadorPrestamo;
        this.idEstado = idEstado;
        this.observacion = observacion;
    }

    public Prestamo(String fechaSolicitud, int idEquipo, String cedulaUsuario, String cedulaOperadorPrestamo) {
        this.fechaSolicitud = fechaSolicitud;
        this.idEquipo = idEquipo;
        this.cedulaUsuario = cedulaUsuario;
        this.cedulaOperadorPrestamo = cedulaOperadorPrestamo;
    }

    public Prestamo(int idPrestamo, String fechaDevolicion, String cedulaOperadorDevolucion, int idEstado, String observacion) {
        this.idPrestamo = idPrestamo;
        this.fechaDevolicion = fechaDevolicion;
        this.cedulaOperadorDevolucion = cedulaOperadorDevolucion;
        this.idEstado = idEstado;
        this.observacion = observacion;
    }
    
    
    
    

    public String insert() {
        Conexion myConnection = new Conexion();
        PrestamoDAO prestamoDAO = new PrestamoDAO(this.fechaSolicitud, this.idEquipo, this.cedulaUsuario, this.cedulaOperadorPrestamo);
        return myConnection.executeUpdate(prestamoDAO.insert());
    }
    
    
    public String update() {
        Conexion myConnection = new Conexion();
        PrestamoDAO prestamoDAO = new PrestamoDAO(this.idPrestamo, this.fechaDevolicion, this.cedulaOperadorDevolucion, this.idEstado, this.observacion);
        return myConnection.executeUpdate(prestamoDAO.update());
    }
    
    
     public ArrayList<Prestamo> list() {
        ArrayList<Prestamo> data = new ArrayList<>();
        
        Conexion myConnection = new Conexion();
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        
        ResultSet rs = myConnection.executeQuery(prestamoDAO.list(2));
        
        try {
            while ( rs.next() ) {
                System.out.print(rs);
                Prestamo currentPrestamo = new Prestamo(rs.getInt("idprestamo"), rs.getString("fecha_solicitud"), 
                        rs.getInt("idequipo"), rs.getString("Solicitante"), rs.getString("Operador"),
                        rs.getInt("idestado_devolucion"), rs.getString("observacion"));
                data.add(currentPrestamo);
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "idPrestamo=" + idPrestamo + ", fechaSolicitud=" + fechaSolicitud + ", idEquipo=" + idEquipo + ", cedulaUsuario=" + cedulaUsuario + ", cedulaOperadorPrestamo=" + cedulaOperadorPrestamo + ", fechaDevolicion=" + fechaDevolicion + ", cedulaOperadorDevolucion=" + cedulaOperadorDevolucion + ", idEstado=" + idEstado + ", observacion=" + observacion + '}';
    }
     
     
    
}
