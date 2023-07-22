/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author BlackOnyxs
 */
public class EstadoEquipoDAO {
    private int idEstado;
    private String descripcion;

    public EstadoEquipoDAO() {
        this.descripcion = "";
    }

    public EstadoEquipoDAO(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoEquipoDAO(int idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoEquipoDAO(int idEstado, String descripcion) {
        this.idEstado = idEstado;
        this.descripcion = descripcion;
    }
    
    
    public String insert() {
        return "insert into estado_equipos(idestado_equipo, descripcion)values(null,'"+ this.descripcion+"');";
    }
    
    public String update() {
        return "UPDATE estado_equipos set descripcion = '"+ this.descripcion+
                "' where idestado_equipo = "+ this.idEstado+";";
    }
    
    
    public String getById() {
        return "SELECT * FROM estado_equipos where idestado_equipo = "+this.idEstado+";";
    }
    
    //TODO: handle spaces
    public String getByDescription() {
        return "select * from estado_equipos where descripcion like '"+this.descripcion+"';";
    }
    
    public String delete() {
        return "DELETE FROM estado_equipos WHERE idestado_equipo = "+ this.idEstado+";";
    }
    
    public String list() {
        return "SELECT * FROM estado_equipos";
    }
}
