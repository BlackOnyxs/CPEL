/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author BlackOnyxs
 */
public class PrestamoDAO {
    private int idPrestamo;
    private String fechaSolicitud;
    private int idEquipo;
    private String cedulaUsuario;
    private String cedulaOperadorPrestamo;
    private String fechaDevolicion;
    private String cedulaOperadorDevolucion;
    private int idEstado;
    private String observacion;

    public PrestamoDAO() {
    }
    
    

    public PrestamoDAO(int idPrestamo, String fechaSolicitud, int idEquipo, String cedulaUsuario, String cedulaOperadorPrestamo, String fechaDevolicion, String cedulaOperadorDevolucion, int idEstado, String observacion) {
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

    public PrestamoDAO(String fechaSolicitud, int idEquipo, String cedulaUsuario, String cedulaOperadorPrestamo, String fechaDevolicion, String cedulaOperadorDevolucion, int idEstado, String observacion) {
        this.fechaSolicitud = fechaSolicitud;
        this.idEquipo = idEquipo;
        this.cedulaUsuario = cedulaUsuario;
        this.cedulaOperadorPrestamo = cedulaOperadorPrestamo;
        this.fechaDevolicion = fechaDevolicion;
        this.cedulaOperadorDevolucion = cedulaOperadorDevolucion;
        this.idEstado = idEstado;
        this.observacion = observacion;
    }
    
    public PrestamoDAO(int idPrestamo, String fechaDevolicion, String cedulaOperadorDevolucion, int idEstado, String observacion) {
        this.idPrestamo = idPrestamo;
        this.fechaDevolicion = fechaDevolicion;
        this.cedulaOperadorDevolucion = cedulaOperadorDevolucion;
        this.idEstado = idEstado;
        this.observacion = observacion;
    }

    public PrestamoDAO(String fechaSolicitud, int idEquipo, String cedulaUsuario, String cedulaOperadorPrestamo) {
        this.fechaSolicitud = fechaSolicitud;
        this.idEquipo = idEquipo;
        this.cedulaUsuario = cedulaUsuario;
        this.cedulaOperadorPrestamo = cedulaOperadorPrestamo;
    }
    
    public PrestamoDAO(int idPrestamo) {
        this.idPrestamo = idPrestamo;
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

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getCedulaOperadorPrestamo() {
        return cedulaOperadorPrestamo;
    }

    public void setCedulaOperadorPrestamo(String cedulaOperadorPrestamo) {
        this.cedulaOperadorPrestamo = cedulaOperadorPrestamo;
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

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    

   
    public String save(){
        return String.format("call EquipmentLoanSave(%d,'%s', %d, '%s', '%s', '%s', '%s', %d, '%s')", 
                this.idPrestamo, this.fechaSolicitud, this.idEquipo, this.cedulaUsuario, 
                this.cedulaOperadorPrestamo, this.fechaDevolicion, this.cedulaOperadorDevolucion,
                this.idEstado, this.observacion);
    }
    
    
     public String search( String value ) {
        return String.format("call EquipmentLoanSearch('%s')", value);
    }
     
    public String list( int skip, int sort ) {
        return String.format( sort == 0 ? "call EquipmentLoanListASC(%d)" : "call EquipmentLoanDESC(%d)", skip);
    }
    
    public String delete() {
        return "delete from prestamos where idprestamo = "+this.idPrestamo+";";
    }
}
