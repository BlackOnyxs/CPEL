/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author BlackOnyxs
 */
public class OperadorDAO {
    private String cedula;
    private String primerNombre;
    private String primerApellido;

    public OperadorDAO() {
    }
    

    public OperadorDAO(String cedula, String primerNombre, String primerApellido) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
    }

    public OperadorDAO(String cedula) {
        this.cedula = cedula;
    }
    
    public String insert() {
        return "insert into operadores(cedula, primer_nombre, primer_apellido)values('"
                +this.cedula+"', '"+this.primerNombre+"', '"+this.primerApellido+"');";
    }
    
    public String update() {
        return "UPDATE operadores set primer_nombre = '"+ this.primerNombre+"', primer_apellido = '"
                + this.primerApellido+"' where cedula = '"+ this.cedula+"';";
    }
    
    public String search( SearchTypesDAO filter, String value ) {
        String sqlStatement =  "";
        
        switch( filter ) {
            case ID:
                sqlStatement = "select * from operadores where cedula like '"+value+"';";
               break;
            case NAME:
                sqlStatement = "select * from operadores where primer_nombre like '"+value+"';";
               break;
            case LASTNAME:
                sqlStatement = "select * from operadores where primer_apellido like '"+value+"';";
               break;
            default:
                return "Not implement";
        }
        return sqlStatement;
    }
    
    public String get() {
        return "SELECT * FROM operadores where cedula = '"+this.cedula+"';";
    }
    
     public String delete() {
        return "DELETE FROM operadores WHERE cedula = '"+ this.cedula+"';";
    }
    
    public String list() {
        return "SELECT * FROM operadores";
    }
}
