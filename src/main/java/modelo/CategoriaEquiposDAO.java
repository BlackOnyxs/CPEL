/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author BlackOnyxs
 */
public class CategoriaEquiposDAO {
    private int idCategory;
    private String nombreCategoria;

    public CategoriaEquiposDAO() {
        this.nombreCategoria = "";
    }

    public CategoriaEquiposDAO(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public CategoriaEquiposDAO(int idCategory) {
        this.idCategory = idCategory;
    }

    public CategoriaEquiposDAO(int idCategory, String nombreCategoria) {
        this.idCategory = idCategory;
        this.nombreCategoria = nombreCategoria;
    }
    
    
    public String insert() {
        return "insert into categoria_equipos(idcategoria, nombre_categoria)values(null,'"+ this.nombreCategoria+"');";
    }
    
    public String update() {
        return "UPDATE categoria_equipos set nombre_categoria = '"+ this.nombreCategoria+
                "' where idcategoria = "+ this.idCategory+";";
    }
    
    
    public String getById() {
        return "SELECT * FROM tipos_usuarios where idcategoria = "+this.idCategory+";";
    }
    
    //TODO: handle spaces
    public String getByDescription() {
        return "select * from tipos_usuarios where nombre_categoria like '"+this.nombreCategoria+"';";
    }
    
    public String delete() {
        return "DELETE FROM tipos_usuarios WHERE idcategoria = "+ this.idCategory+";";
    }
    
    public String list() {
        return "SELECT * FROM tipos_usuarios";
    }
}
