/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author BlackOnyxs
 */
public class TipoUsuarioDAO {
    private int idTipoUsuario;
    private String descripcion;

    public TipoUsuarioDAO() {
        this.descripcion = "";
    }

    public TipoUsuarioDAO(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoUsuarioDAO(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public TipoUsuarioDAO(int idTipoUsuario, String descripcion) {
        this.idTipoUsuario = idTipoUsuario;
        this.descripcion = descripcion;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    
    public String insert() {
        return "insert into tipos_usuarios(tipousuario, descripcion)values(null,'"+ this.descripcion+"');";
    }
    
    public String update() {
        return "UPDATE tipos_usuarios set descripcion = '"+ this.descripcion+
                "' where tipousuario = "+ this.idTipoUsuario+";";
    }
    
    
    public String getById() {
        return "SELECT * FROM tipos_usuarios where tipousuario = "+this.idTipoUsuario+";";
    }
    
    //TODO: handle spaces
    public String getByDescription() {
        return "select * from tipos_usuarios where descripcion like '"+this.descripcion+"';";
    }
    
    public String delete() {
        return "DELETE FROM tipos_usuarios WHERE tipousuario = "+ this.idTipoUsuario+";";
    }
    
    public String list() {
        return "SELECT * FROM tipos_usuarios";
    }
}
