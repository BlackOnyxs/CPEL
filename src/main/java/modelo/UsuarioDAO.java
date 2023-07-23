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
    private int idTipoUsuario;
    private int idCarrera;
    private String careerName;
    private String tipoUsuario;

    public UsuarioDAO() {
    }

    public UsuarioDAO(String cedula) {
        this.cedula = cedula;
    }
    
    

    public UsuarioDAO(String cedula, String primerNombre, String primerApellido, String telefono, String correo, int idTipoUsuario, int idCarrera) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.telefono = telefono;
        this.correo = correo;
        this.idTipoUsuario = idTipoUsuario;
        this.idCarrera = idCarrera;
    }

    public UsuarioDAO(String primerNombre, String primerApellido, String telefono, String correo, int idTipoUsuario, int idCarrera) {
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.telefono = telefono;
        this.correo = correo;
        this.idTipoUsuario = idTipoUsuario;
        this.idCarrera = idCarrera;
    }

    public UsuarioDAO(String cedula, String primerNombre, String primerApellido, String telefono, String correo, String careerName, String tipoUsuario) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.telefono = telefono;
        this.correo = correo;
        this.careerName = careerName;
        this.tipoUsuario = tipoUsuario;
    }

   
    public String save() {
        return String.format("call UserCreateOrUpdate('%s', '%s', '%s', '%s', '%s', %d, %d)", 
                this.cedula, this.primerNombre, this.primerApellido, this.telefono, this.correo, this.idCarrera, this.idTipoUsuario);
//        "insert into usuarios(cedula, primer_nombre, primer_apellido, "
//                + "telefono, correo, idcarrera, tipousuario)values('"+this.cedula+"', '"+this.primerNombre+"', '"+this.primerApellido+"', '"+this.telefono+"', '"+this.correo+"', "+this.idCarrera+", "+this.idTipoUsuario+")";
                
    }
    
    public String update() {
        return "UPDATE usuarios set primer_nombre = '"+ this.primerNombre+"', primer_apellido = '"
                + this.primerApellido+"', telefono = '"+this.telefono+"', correo = '"+this.correo+"', "
                + "idcarrera = "+this.idCarrera +", tipousuario = "+this.idTipoUsuario +" where cedula = '"+ this.cedula+"';";
    }
    
    public String search( String value ) {
        return String.format("call UserSearch('%s')", value);
    }
    
     public String get() {
        return "SELECT * FROM usuarios where cedula = '"+this.cedula+"';";
    }
    
     public String delete() {
        return "DELETE FROM usuarios WHERE cedula like '"+ this.cedula+"';";
    }
    
    public String list(int skip, int sort) {
        return sort == 0 
                ? String.format("call UsersListASC(%s);", skip)
                : String.format("call UsersListDSC(%s);", skip);
    }
    
    
}
