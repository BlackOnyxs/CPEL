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

    
    
    public String insert(){
        return String.format("call EquipmentLoanCreate('%s', %s, '%s', '%s')", this.fechaSolicitud, this.idEquipo, this.cedulaUsuario, this.cedulaOperadorPrestamo);
    }
    
    public String update() {
         System.out.println(this.idPrestamo);
        return String.format("call EquipmentLoanUpdate(%s, '%s', '%s', %s, '%s')", this.idPrestamo, this.fechaDevolicion, this.cedulaOperadorDevolucion, this.idEstado, this.observacion);
        /*
        "update prestamos set fecha_solicitud = '"+this.fechaSolicitud+"', idequipo = "+this.idEquipo+", cedula_usuario = '"+this.cedulaUsuario+"', "
                +"cedula_operador_prestamo = '"+this.cedulaOperadorPrestamo+"', fecha_devolucion = '"+this.fechaDevolicion+"', "
                +"cedula_operador_devolucion = '"+this.cedulaOperadorDevolucion+"', idestado_devolucion = "+this.idEstado
                +" , observacion = '"+this.observacion+"' where idprestamo = "+this.idPrestamo;
        */
    }
    
     public String search( SearchTypesDAO filter, String value ) {
        String sqlStatement =  "";
        
         switch( filter ) {
            case ID:
                sqlStatement = String.format("call EquipmentLoanById(%s)", value);
               break;
            case STATE:
                sqlStatement = String.format("call EquipmentLoanByState(%s)", value);
               break;
            case DATE:
                sqlStatement = String.format("call EquipmentLoanByDate(%s)", value);
               break;
            default:
                return "Not implement";
        }
        return sqlStatement;
    }
     
    public String list( int skip ) {
        return String.format("call EquipmentLoanList(%d)", skip);
    }
    
    public String delete() {
        return "delete prestamos where idprestamo = "+this.idPrestamo;
    }
}
