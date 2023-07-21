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

    public UsuarioDAO(String primerNombre, String primerApellido, String telefono, String correo, int tipoUsuario, int idCarrera) {
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
    
}
