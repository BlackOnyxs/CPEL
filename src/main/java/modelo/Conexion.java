/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author BlackOnyxs
 */
public class Conexion {
    
    private Connection myConnection;
    private Statement myStatement;
    private String jdbc;
    private String url;
    private String username;
    private String password;

    public Conexion() {
        this.myConnection = null;
        this.myStatement = null;
        this.jdbc = "org.mariadb.jdbc.Driver";
        this.url = "jdbc:mariadb://localhost:3306/cpel";
        this.username = "admincpel";
        this.password = "cpel2023";
    }
    
    public void openConecction() {
          try{
            Class.forName(this.jdbc);
            this.myConnection = (Connection) DriverManager.getConnection(this.url, this.username, this.password);
            this.myStatement = this.myConnection.createStatement();
              System.out.println("Conectados");
        }catch( SQLException e ) {
             e.printStackTrace();
        }catch( ClassNotFoundException e ) {
            e.printStackTrace();
        }
    } 
    
    public void closeConnection() {
        try {
            this.myConnection.close();
            System.out.println("Nos desconectamos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String executeUpdate( String sqlStatement ) {
        try{
            this.openConecction();
            this.myStatement.executeUpdate(sqlStatement);
            this.closeConnection();
            return "executed.";
        }catch( SQLException e ) {
            this.closeConnection();
            return e.toString();
        }
    }
    
    public ResultSet executeQuery( String query ) {
        ResultSet rs = null;
        try {
            this.openConecction();
            rs = this.myStatement.executeQuery(query);
            this.closeConnection();
        } catch (SQLException e) {
            this.closeConnection();
            e.printStackTrace();
        }
        return rs;
    }
    
    
}


