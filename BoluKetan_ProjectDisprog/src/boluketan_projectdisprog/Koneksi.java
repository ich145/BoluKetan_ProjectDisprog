/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boluketan_projectdisprog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author michael
 */
public class Koneksi {
    protected static Connection conn;
    protected Statement statement;
    protected ResultSet result;
    
    public Koneksi(){
        this.conn = this.getConnection();
        this.statement = null;
        this.result = null;
        
    }

    public static Connection getConnection() {

        try {

            if (conn == null || conn.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/projectdisprog"               
                        + "?useSSL=false"
                        + "&allowPublicKeyRetrieval=true"
                        + "&serverTimezone=Asia/Jakarta",
                        "root",
                        ""
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return conn;

    }
}
