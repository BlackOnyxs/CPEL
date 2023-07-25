/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author BlackOnyxs
 */
public class CarreraDAO {
    private int idCarrera;
    private String nombreCarrera;

    public CarreraDAO() {
        this.nombreCarrera = "";
    }

    public CarreraDAO(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public CarreraDAO(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public CarreraDAO(int idCarrera, String nombreCarrera) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
    }
    
    
    public String save() {
        return String.format("call CarrerSave(%d, '%s')", this.idCarrera, this.nombreCarrera);
    }
    
   
    public String search( String value ) {
        return String.format("call CareerSearch('%s')", value);
    }
    
     public String delete() {
        return "DELETE FROM carreras WHERE idcarrera = "+ this.idCarrera+";";
    }
    
    public String list(int skip, int sort) {
        return String.format("SELECT * FROM carreras order by nombre_carrera %s limit 10 offset %d;",
                (sort == 0 ? "asc" : "desc"), skip);
    }
}
