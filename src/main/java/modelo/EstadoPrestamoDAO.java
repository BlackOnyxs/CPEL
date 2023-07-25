/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author BlackOnyxs
 */
public class EstadoPrestamoDAO {
    private int idEstado;
    private String estadoDevolucion;

    public EstadoPrestamoDAO() {
        this.estadoDevolucion = "";
    }

    public EstadoPrestamoDAO(String descripcion) {
        this.estadoDevolucion = descripcion;
    }

    public EstadoPrestamoDAO(int idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoPrestamoDAO(int idEstado, String descripcion) {
        this.idEstado = idEstado;
        this.estadoDevolucion = descripcion;
    }
    
    
    public String insert() {
        return "insert into estado_devolucion(idestado_devolucion, estado_devolucion)values(null,'"+ this.estadoDevolucion+"');";
    }
    
    public String update() {
        return "UPDATE estado_devolucion set estado_devolucion = '"+ this.estadoDevolucion+
                "' where idestado_devolucion = "+ this.idEstado+";";
    }
    
    
    public String delete() {
        return "DELETE FROM estado_devolucion WHERE idestado_devolucion = "+ this.idEstado+";";
    }
    
    public String list() {
        return "SELECT * FROM estado_devolucion order by idestado_devolucion asc limit 10;";
    }
}
