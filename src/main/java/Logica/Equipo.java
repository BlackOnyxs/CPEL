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
    private String descripcion;
    private int idEstado;
    private String modelo;
    private String placaInventario;
    private String fechaCompra;
    private String foto;

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
    
    public String insert(){
        Conexion conn = new Conexion();
        EquiposDAO equipo = new EquiposDAO(this.idCategoria,this.descripcion, this.idEstado,
                        this.modelo, this.placaInventario, this.fechaCompra, this.foto);
        return conn.executeUpdate(equipo.insert());
    }
    
    public String update(){
        Conexion conn = new Conexion();
        EquiposDAO equipo = new EquiposDAO(this.idEquipo, this.idCategoria,this.descripcion, this.idEstado,
                        this.modelo, this.placaInventario, this.fechaCompra, this.foto);
        return conn.executeUpdate(equipo.update());
    }
    
    public String delete(){
        Conexion conn = new Conexion();
        EquiposDAO equipo = new EquiposDAO(this.idEquipo);
        return conn.executeUpdate(equipo.delete());
    }
   
    public ArrayList<Equipo> search(SearchTypesDAO st, String filter) {
        ArrayList<Equipo> data = new ArrayList<>();
        Conexion conn = new Conexion();
        EquiposDAO equipo = new EquiposDAO();
        ResultSet rs = conn.executeQuery(equipo.search(st, filter));
        
        try {
            while ( rs.next() ) {
                Equipo currentEquipo = new Equipo(rs.getInt("idequipo"), rs.getInt("idcategoria"), 
                        rs.getString("descripcion"), rs.getInt("idestado_equipo"), rs.getString("modelo"),
                        rs.getString("placa_inventario"), rs.getString("fecha_compra"), rs.getString("foto"));
                data.add(currentEquipo);
            }
        } catch (SQLException e) {
//            Logger.getLogger(Writer.class.getName()).log(Level., null, e);
            e.printStackTrace();
        }
        return data;
    }
    
    public ArrayList<Equipo> list() {
        ArrayList<Equipo> data = new ArrayList<>();
        Conexion conn = new Conexion();
        EquiposDAO equipo = new EquiposDAO();
        ResultSet rs = conn.executeQuery(equipo.list());
        
        try {
            while ( rs.next() ) {
                Equipo currentEquipo = new Equipo(rs.getInt("idequipo"), rs.getInt("idcategoria"), 
                        rs.getString("descripcion"), rs.getInt("idestado_equipo"), rs.getString("modelo"),
                        rs.getString("placa_inventario"), rs.getString("fecha_compra"), rs.getString("foto"));
                data.add(currentEquipo);
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
