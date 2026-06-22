/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public abstract class MyModel {
    protected static Connection conn;
    protected Statement statement;
    protected ResultSet result;

    public MyModel(){
        conn = getConnection();
    }

    public Connection getConnection(){
        if(conn == null){
            try{
                Class.forName(
                "com.mysql.cj.jdbc.Driver"
                );

                conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/boluketan"
                + "?useSSL=false"
                + "&allowPublicKeyRetrieval=true"
                + "&serverTimezone=Asia/Jakarta",
                "root",
                ""
                );
            }

            catch(Exception e){
                System.out.println(e);
            }
        }
        return conn;
    }

    public abstract void insertData();

    public abstract void updateData();

    public abstract void deleteData();

    public abstract ArrayList<Object>

    viewListData();
}
