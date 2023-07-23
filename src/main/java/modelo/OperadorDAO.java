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
    
    public String save() {
        return String.format("call OperatorCreateOrUpdate('%s', '%s', '%s')", 
                this.cedula, this.primerNombre, this.primerApellido);
    }
    
//    public String insert() {
//        return "insert into operadores(cedula, primer_nombre, primer_apellido)values('"
//                +this.cedula+"', '"+this.primerNombre+"', '"+this.primerApellido+"');";
//    }
//    
//    public String update() {
//        return "UPDATE operadores set primer_nombre = '"+ this.primerNombre+"', primer_apellido = '"
//                + this.primerApellido+"' where cedula = '"+ this.cedula+"';";
//    }
    
    public String search( String value ) {
        return String.format("call OperatorSearch('%s')", value);
    }
    
    public String get() {
        return "SELECT * FROM operadores where cedula = '"+this.cedula+"';";
    }
    
     public String delete() {
        return "DELETE FROM operadores WHERE cedula = '"+ this.cedula+"';";
    }
    
    public String list(int skip, int sort) {
        return sort == 0 
                ? String.format("call OperatorListASC(%s);", skip)
                : String.format("call OperatorListDesc(%s);", skip);
    }
}
