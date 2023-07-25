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
    private String modeloEquipo;
    private String cedulaUsuario;
    private String nombreUsuario;
    private String cedulaOperadorPrestamo;
    private String nombreOperadorPrestamo;
    private String fechaDevolicion;
    private String cedulaOperadorDevolucion;
    private String nombreOperadorDevolucion;
    private int idEstado;
    private String estadoDevolicion;
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

    public Prestamo(int idPrestamo, String fechaSolicitud, int idEquipo, String modeloEquipo, String cedulaUsuario, String nombreUsuario, String cedulaOperadorPrestamo, String nombreOperadorPrestamo, String fechaDevolicion, String cedulaOperadorDevolucion, String nombreOperadorDevolucion, int idEstado, String estadoDevolicion, String observacion) {
        this.idPrestamo = idPrestamo;
        this.fechaSolicitud = fechaSolicitud;
        this.idEquipo = idEquipo;
        this.modeloEquipo = modeloEquipo;
        this.cedulaUsuario = cedulaUsuario;
        this.nombreUsuario = nombreUsuario;
        this.cedulaOperadorPrestamo = cedulaOperadorPrestamo;
        this.nombreOperadorPrestamo = nombreOperadorPrestamo;
        this.fechaDevolicion = fechaDevolicion;
        this.cedulaOperadorDevolucion = cedulaOperadorDevolucion;
        this.nombreOperadorDevolucion = nombreOperadorDevolucion;
        this.idEstado = idEstado;
        this.estadoDevolicion = estadoDevolicion;
        this.observacion = observacion;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getModeloEquipo() {
        return modeloEquipo;
    }

    public void setModeloEquipo(String modeloEquipo) {
        this.modeloEquipo = modeloEquipo;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCedulaOperadorPrestamo() {
        return cedulaOperadorPrestamo;
    }

    public void setCedulaOperadorPrestamo(String cedulaOperadorPrestamo) {
        this.cedulaOperadorPrestamo = cedulaOperadorPrestamo;
    }

    public String getNombreOperadorPrestamo() {
        return nombreOperadorPrestamo;
    }

    public void setNombreOperadorPrestamo(String nombreOperadorPrestamo) {
        this.nombreOperadorPrestamo = nombreOperadorPrestamo;
    }

    public String getFechaDevolicion() {
        return fechaDevolicion;
    }

    public void setFechaDevolicion(String fechaDevolicion) {
        this.fechaDevolicion = fechaDevolicion;
    }

    public String getCedulaOperadorDevolucion() {
        return cedulaOperadorDevolucion;
    }

    public void setCedulaOperadorDevolucion(String cedulaOperadorDevolucion) {
        this.cedulaOperadorDevolucion = cedulaOperadorDevolucion;
    }

    public String getNombreOperadorDevolucion() {
        return nombreOperadorDevolucion;
    }

    public void setNombreOperadorDevolucion(String nombreOperadorDevolucion) {
        this.nombreOperadorDevolucion = nombreOperadorDevolucion;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstadoDevolicion() {
        return estadoDevolicion;
    }

    public void setEstadoDevolicion(String estadoDevolicion) {
        this.estadoDevolicion = estadoDevolicion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public Prestamo save() {
        Conexion myConnection = new Conexion();
        PrestamoDAO prestamoDAO = new PrestamoDAO(this.idPrestamo, this.fechaSolicitud, this.idEquipo, 
        this.cedulaUsuario, this.cedulaOperadorPrestamo, this.fechaDevolicion, this.cedulaOperadorDevolucion,
        this.idEstado, this.observacion);
        
        ResultSet rs = myConnection.executeQuery(prestamoDAO.save());
        Prestamo currentPrestamo = null;
        try {
            while ( rs.next() ) {
                currentPrestamo = new Prestamo(rs.getInt("idprestamo"), rs.getString("fecha_solicitud"), 
                        rs.getInt("idequipo"), rs.getString("modelo"), rs.getString("cedula_usuario"),
                        rs.getString("Solicitante"), rs.getString("cedula_operador_prestamo"),
                        rs.getString("OperadorS"), rs.getString("fecha_devolucion"),
                        rs.getString("cedula_operador_devolucion"), rs.getString("OperadorD"), 
                        rs.getInt("idestado_devolucion"), rs.getString("estado_devolucion"),
                        rs.getString("observacion"));
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return currentPrestamo;
    }
    
    public String delete() {
        Conexion conn = new Conexion();
        PrestamoDAO prestamoDAO = new PrestamoDAO(this.idPrestamo);
        return conn.executeUpdate(prestamoDAO.delete());
    }
    
     public ArrayList<Prestamo> list(int skip, int sort) {
        ArrayList<Prestamo> data = new ArrayList<>();
        
        Conexion myConnection = new Conexion();
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        
        ResultSet rs = myConnection.executeQuery(prestamoDAO.list(skip, sort));
        
        try {
            while ( rs.next() ) {
                Prestamo currentPrestamo = new Prestamo(rs.getInt("idprestamo"), rs.getString("fecha_solicitud"), 
                        rs.getInt("idequipo"), rs.getString("modelo"), rs.getString("cedula_usuario"),
                        rs.getString("Solicitante"), rs.getString("cedula_operador_prestamo"),
                        rs.getString("OperadorS"), rs.getString("fecha_devolucion"),
                        rs.getString("cedula_operador_devolucion"), rs.getString("OperadorD"), 
                        rs.getInt("idestado_devolucion"), rs.getString("estado_devolucion"),
                        rs.getString("observacion"));
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
        return "Prestamo{" + "idPrestamo=" + idPrestamo + ", fechaSolicitud=" + fechaSolicitud + ", idEquipo=" + idEquipo + ", modeloEquipo=" + modeloEquipo + ", cedulaUsuario=" + cedulaUsuario + ", nombreUsuario=" + nombreUsuario + ", cedulaOperadorPrestamo=" + cedulaOperadorPrestamo + ", nombreOperadorPrestamo=" + nombreOperadorPrestamo + ", fechaDevolicion=" + fechaDevolicion + ", cedulaOperadorDevolucion=" + cedulaOperadorDevolucion + ", nombreOperadorDevolucion=" + nombreOperadorDevolucion + ", idEstado=" + idEstado + ", estadoDevolicion=" + estadoDevolicion + ", observacion=" + observacion + '}';
    }

    
     
    
}
