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
    
    
    
    public String insert() {
        return "insert into carreras(idcarrera, nombre_carrera)values(null,'"+ this.nombreCarrera+"');";
    }
    
    public String update() {
        return "UPDATE carreras set nombre_carrera = '"+ this.nombreCarrera+
                "' where idcarrera = "+ this.idCarrera+";";
    }
    
    public String search( SearchTypes filter, String value ) {
        String sqlStatement =  "";
        
        switch( filter ) {
            case ID:
                sqlStatement = "select * from carreras where idcarrera = "+value+";";
               break;
            case NAME:
                sqlStatement = "select * from carreras where nombre_carrera like '"+value+"';";
               break;
            default:
                return "Not implement";
        }
        return sqlStatement;
    }
    
     public String get() {
        return "SELECT * FROM carreras where idcarrera = "+this.idCarrera+";";
    }
    
     public String delete() {
        return "DELETE FROM carreras WHERE idcarrera = "+ this.idCarrera+";";
    }
    
    public String list() {
        return "SELECT * FROM carreras";
    }
}
