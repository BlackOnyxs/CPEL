/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Conexion;
import modelo.EquiposDAO;
import modelo.SearchTypesDAO;
import modelo.UsuarioDAO;

/**
 *
 * @author BlackOnyxs
 */
public class Equipo {
     private int idEquipo;
    private int idCategoria;
    private String nombreCategoria;
    private String descripcion;
    private int idEstado;
    private String estadoDescripcion;
    private String modelo;
    private String placaInventario;
    private String fechaCompra;
    private String foto;

    public Equipo() {
    }

    public Equipo(int idEquipo, int idCategoria, String nombreCategoria, String descripcion, int idEstado, String estadoDescripcion, String modelo, String placaInventario, String fechaCompra, String foto) {
        this.idEquipo = idEquipo;
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
        this.idEstado = idEstado;
        this.estadoDescripcion = estadoDescripcion;
        this.modelo = modelo;
        this.placaInventario = placaInventario;
        this.fechaCompra = fechaCompra;
        this.foto = foto;
    }            

    public Equipo(int idEquipo, int idCategoria, String descripcion, int idEstado, String modelo, String placaInventario, String fechaCompra, String foto) {
        this.idEquipo = idEquipo;
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.idEstado = idEstado;
        this.modelo = modelo;
        this.placaInventario = placaInventario;
        this.fechaCompra = fechaCompra;
        this.foto = foto;
    }
    

    public Equipo(int idCategoria, String descripcion, int idEstado, String modelo, String placaInventario, String fechaCompra, String foto) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.idEstado = idEstado;
        this.modelo = modelo;
        this.placaInventario = placaInventario;
        this.fechaCompra = fechaCompra;
        this.foto = foto;
    }

    public Equipo(int idEquipo, String descripcion, String placaInventario, String fechaCompra) {
        this.idEquipo = idEquipo;
        this.descripcion = descripcion;
        this.placaInventario = placaInventario;
        this.fechaCompra = fechaCompra;
    }
    
    

    public Equipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlacaInventario() {
        return placaInventario;
    }

    public void setPlacaInventario(String placaInventario) {
        this.placaInventario = placaInventario;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getEstadoDescripcion() {
        return estadoDescripcion;
    }

    public void setEstadoDescripcion(String estadoDescripcion) {
        this.estadoDescripcion = estadoDescripcion;
    }
    
    
    
    public Equipo save() {
        Conexion conn = new Conexion();
        EquiposDAO equipo = new EquiposDAO(this.idEquipo, this.idCategoria,this.descripcion, this.idEstado,
                        this.modelo, this.placaInventario, this.fechaCompra, this.foto);
        ResultSet rs = conn.executeQuery(equipo.save());
        Equipo currentEquipment = null;
        
        try {
            while ( rs.next() ) {
                currentEquipment = new Equipo(rs.getInt("idequipo"), rs.getInt("idcategoria"), 
                        rs.getString("nombre_categoria"), rs.getString("descripcion"), rs.getInt("idestado_equipo"),
                         rs.getString("estado_descipcion"), rs.getString("modelo"), rs.getString("placa_inventario"), 
                        rs.getString("fecha_compra"), rs.getString("foto"));
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return currentEquipment;
    }
    
//    public String insert(){
//        Conexion conn = new Conexion();
//        EquiposDAO equipo = new EquiposDAO(this.idCategoria,this.descripcion, this.idEstado,
//                        this.modelo, this.placaInventario, this.fechaCompra, this.foto);
//        return conn.executeUpdate(equipo.insert());
//    }
//    
//    public String update(){
//        Conexion conn = new Conexion();
//        EquiposDAO equipo = new EquiposDAO(this.idEquipo, this.idCategoria,this.descripcion, this.idEstado,
//                        this.modelo, this.placaInventario, this.fechaCompra, this.foto);
//        return conn.executeUpdate(equipo.update());
//    }
    
    public String delete(){
        Conexion conn = new Conexion();
        EquiposDAO equipo = new EquiposDAO(this.idEquipo);
        return conn.executeUpdate(equipo.delete());
    }
   
    public ArrayList<Equipo> search(String filter) {
        ArrayList<Equipo> data = new ArrayList<>();
        Conexion conn = new Conexion();
        EquiposDAO equipo = new EquiposDAO();
        ResultSet rs = conn.executeQuery(equipo.search( filter ));
        
        try {
            while ( rs.next() ) {
                Equipo currentEquipment = new Equipo(rs.getInt("idequipo"), rs.getInt("idcategoria"), 
                        rs.getString("nombre_categoria"), rs.getString("descripcion"), rs.getInt("idestado_equipo"),
                         rs.getString("estado_descipcion"), rs.getString("modelo"), rs.getString("placa_inventario"), 
                        rs.getString("fecha_compra"), rs.getString("foto"));
                
                data.add(currentEquipment);
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return data;
    }
    
    public ArrayList<Equipo> list(int skip, int sort) {
        ArrayList<Equipo> data = new ArrayList<>();
        Conexion conn = new Conexion();
        EquiposDAO equipo = new EquiposDAO();
        ResultSet rs = conn.executeQuery(equipo.list(skip, sort));
        
        try {
            while ( rs.next() ) {
                Equipo currentEquipment = new Equipo(rs.getInt("idequipo"), rs.getInt("idcategoria"), 
                        rs.getString("nombre_categoria"), rs.getString("descripcion"), rs.getInt("idestado_equipo"),
                         rs.getString("estado_descipcion"), rs.getString("modelo"), rs.getString("placa_inventario"), 
                        rs.getString("fecha_compra"), rs.getString("foto"));
                data.add(currentEquipment);
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String toString() {
        return "Equipo{" + "idEquipo=" + idEquipo + ", idCategoria=" + idCategoria + ", descripcion=" + descripcion + ", idEstado=" + idEstado + ", modelo=" + modelo + ", placaInventario=" + placaInventario + ", fechaCompra=" + fechaCompra + ", foto=" + foto + '}';
    }

    
}
