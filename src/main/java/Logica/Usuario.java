/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Conexion;
import modelo.UsuarioDAO;

/**
 *
 * @author BlackOnyxs
 */
public class Usuario {
    private String cedula;
    private String primerNombre;
    private String primerApellido;
    private String telefono;
    private String correo;
    private int idTipoUsuario;
    private int idCarrera;
    private String careerName;
    private String tipoUsuario;

    public Usuario() {
    }

    public Usuario(String cedula, String primerNombre, String primerApellido, String telefono, String correo, int idTipoUsuario, int idCarrera) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.telefono = telefono;
        this.correo = correo;
        this.idTipoUsuario = idTipoUsuario;
        this.idCarrera = idCarrera;
    }

    public Usuario(String cedula, String primerNombre, String primerApellido, String telefono, String correo, int idTipoUsuario, int idCarrera, String careerName, String tipoUsuario) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.telefono = telefono;
        this.correo = correo;
        this.idTipoUsuario = idTipoUsuario;
        this.idCarrera = idCarrera;
        this.careerName = careerName;
        this.tipoUsuario = tipoUsuario;
    }
   

    public Usuario(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }
    
     public Usuario save(){
        Conexion conn = new Conexion();
        UsuarioDAO user = new UsuarioDAO(this.cedula,this.primerNombre, this.primerApellido,
                            this.telefono, this.correo, this.idTipoUsuario, this.idCarrera);
        
        ResultSet rs = conn.executeQuery(user.save());
        Usuario currentUser = null;
        
        try {
            while ( rs.next() ) {
                currentUser = new Usuario(rs.getString("cedula"), rs.getString("primer_nombre"), 
                        rs.getString("primer_apellido"), rs.getString("telefono"), rs.getString("correo"),
                         rs.getInt("tipousuario"), rs.getInt("idcarrera"), rs.getString("career_name"), 
                        rs.getString("tipo_usuario"));
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return currentUser;
    }
    
//    public String update(){
//        Conexion conn = new Conexion();
//        UsuarioDAO user = new UsuarioDAO(this.cedula,this.primerNombre, this.primerApellido,
//                            this.telefono, this.correo, this.idTipoUsuario, this.idCarrera);
//        return conn.executeUpdate(user.update());
//    }
    
    public String delete(){
        Conexion conn = new Conexion();
        UsuarioDAO user = new UsuarioDAO(this.cedula);
        System.out.print(user);
        return conn.executeUpdate(user.delete());
    }
    public ArrayList<Usuario> search(String filter) {
        ArrayList<Usuario> data = new ArrayList<>();
        Conexion conn = new Conexion();
        UsuarioDAO user = new UsuarioDAO();
        ResultSet rs = conn.executeQuery(user.search(filter));
        
        try {
            while ( rs.next() ) {
                Usuario currentUser = new Usuario(rs.getString("cedula"), rs.getString("primer_nombre"), 
                        rs.getString("primer_apellido"), rs.getString("telefono"), rs.getString("correo"),
                         rs.getInt("tipousuario"), rs.getInt("idcarrera"), rs.getString("career_name"), 
                        rs.getString("tipo_usuario"));
                data.add(currentUser);
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return data;
    }
    
    public ArrayList<Usuario> list(int skip, int sort) {
        ArrayList<Usuario> data = new ArrayList<>();
        
        Conexion myConnection = new Conexion();
        UsuarioDAO userDao = new UsuarioDAO();
        
        ResultSet rs = myConnection.executeQuery(userDao.list(skip, sort));
        
        try {
            while ( rs.next() ) {
                Usuario currentUser = new Usuario(rs.getString("cedula"), rs.getString("primer_nombre"), 
                        rs.getString("primer_apellido"), rs.getString("telefono"), rs.getString("correo"),
                         rs.getInt("tipousuario"), rs.getInt("idcarrera"), rs.getString("career_name"), 
                        rs.getString("tipo_usuario"));
                data.add(currentUser);
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return data;
    }

    

    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", primerNombre=" + primerNombre + ", primerApellido=" + primerApellido + ", telefono=" + telefono + ", correo=" + correo + ", tipoUsuario=" + tipoUsuario + ", idCarrera=" + idCarrera + '}';
    }
   
    
    
}
