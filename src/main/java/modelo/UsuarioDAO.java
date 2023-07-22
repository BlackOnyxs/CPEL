/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author BlackOnyxs
 */
public class UsuarioDAO {
    private String cedula;
    private String primerNombre;
    private String primerApellido;
    private String telefono;
    private String correo;
    private int tipoUsuario;
    private int idCarrera;

    public UsuarioDAO(String cedula, String primerNombre, String primerApellido, String telefono, String correo, int tipoUsuario, int idCarrera) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.telefono = telefono;
        this.correo = correo;
        this.tipoUsuario = tipoUsuario;
        this.idCarrera = idCarrera;
    }
    
    

    public UsuarioDAO(String cedula) {
        this.cedula = cedula;
    }
    
    public UsuarioDAO() {
        this.primerNombre = "";
        this.primerApellido = "";
        this.telefono = "";
        this.correo = "";
        this.tipoUsuario = 0;
        this.idCarrera = 0;
    }
    
    public String insert() {
        return "insert into usuarios(cedula, primer_nombre, primer_apellido, "
                + "telefono, correo, idcarrera, tipousuario)values('"+this.cedula+"', '"+this.primerNombre+"', '"+this.primerApellido+"', '"+this.telefono+"', '"+this.correo+"', "+this.idCarrera+", "+this.tipoUsuario+")";
    }
    
    public String update() {
        return "UPDATE usuarios set primer_nombre = '"+ this.primerNombre+"', primer_apellido = '"
                + this.primerApellido+"', telefono = '"+this.telefono+"', correo = '"+this.correo+"', "
                + "idcarrera = "+this.idCarrera +", tipousuario = "+this.tipoUsuario +" where cedula = '"+ this.cedula+"';";
    }
    
    public String search( SearchTypesDAO filter, String value ) {
        String sqlStatement =  "";
        
        switch( filter ) {
            case ID:
                sqlStatement = "select * from usuarios where cedula like '"+value+"';";
               break;
            case NAME:
                sqlStatement = "select * from usuarios where primer_nombre like '"+value+"';";
               break;
            case LASTNAME:
                sqlStatement = "select * from usuarios where primer_apellido like '"+value+"';";
               break;
            default:
                return "Not implement";
        }
        return sqlStatement;
    }
    
     public String get() {
        return "SELECT * FROM usuarios where cedula = "+this.cedula+";";
    }
    
     public String delete() {
        return "DELETE FROM usuarios WHERE cedula = "+ this.cedula+";";
    }
    
    public String list() {
        return "SELECT * FROM usuarios";
    }
    
}
