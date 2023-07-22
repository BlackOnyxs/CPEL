/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author BlackOnyxs
 */
public class EquiposDAO {
    private int idEquipo;
    private int idCategoria;
    private String descripcion;
    private int idEstado;
    private String modelo;
    private String placaInventario;
    private String fechaCompra;
    private String foto;

    public EquiposDAO() {
    }
    
    

    public EquiposDAO(int idEquipo, int idCategoria, String descripcion, int idEstado, String modelo, String placaInventario, String fechaCompra, String foto) {
        this.idEquipo = idEquipo;
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.idEstado = idEstado;
        this.modelo = modelo;
        this.placaInventario = placaInventario;
        this.fechaCompra = fechaCompra;
        this.foto = foto;
    }

    public EquiposDAO(int idCategoria, String descripcion, int idEstado, String modelo, String placaInventario, String fechaCompra, String foto) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.idEstado = idEstado;
        this.modelo = modelo;
        this.placaInventario = placaInventario;
        this.fechaCompra = fechaCompra;
        this.foto = foto;
    }
    
    

    public EquiposDAO(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    
    public String insert() {
        return "insert into equipos(idequipo, idcategoria, descripcion, "
                + "idestado_equipo, modelo, placa_inventario, fecha_compra, foto)values(null, "+this.idCategoria+", '"+this.descripcion+"', "+this.idEstado+", '"+this.modelo+"', '"+this.placaInventario+"', '"+this.fechaCompra+"', '"+this.foto+"');";
    }
    
    public String update() {
        return "UPDATE equipos set idcategoria = "+ this.idCategoria+", descripcion = '"
                + this.descripcion+"', idestado_equipo = "+this.idEstado+", modelo = '"+this.modelo+"', "
                + "placa_inventario = '"+this.placaInventario +"', fecha_compra = '"+this.fechaCompra +"', foto = '"+this.foto+"' where idequipo = '"+ this.idEquipo+"';";
    }
    
    public String search( SearchTypesDAO filter, String value ) {
        String sqlStatement =  "";
        
        switch( filter ) {
            case ID:
                sqlStatement = "select * from equipos where idequipo = "+value+";";
               break;
            case CATEGORY:
                sqlStatement = "select * from equipos where idcategoria = "+value+";";
               break;
            case STATE:
                sqlStatement = "select * from equipos where idestado_equipo = "+value+";";
               break;
            case BOARD:
                sqlStatement = "select * from equipos where placa_inventario like '"+value+"';";
               break;
            default:
                return "Not implement";
        }
        return sqlStatement;
    }
    
     public String get() {
        return "SELECT * FROM equipos where idequipo = "+this.idEquipo+";";
    }
    
     public String delete() {
        return "DELETE FROM equipos WHERE idequipo = "+ this.idEquipo+";";
    }
    
    public String list() {
        return "SELECT * FROM equipos";
    }
}
