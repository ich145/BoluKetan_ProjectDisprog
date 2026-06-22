/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class User extends MyModel{
    private int idUser;
    private String nama;
    private String role;
    private Date tanggal_lahir;
    private String email;
    private String password;
    
    public User(){
        super();
    }
    
    public User(
            String nama,
            String role,
            Date tanggal_lahir,
            String email,
            String password
    ) {
        super();
        this.nama = nama;
        this.role = role;
        this.tanggal_lahir = tanggal_lahir;
        this.email = email;
        this.password = password;
    }
    
    
    

    public boolean checkLogin(String email, String password){
        boolean found = false;
        try {
            PreparedStatement sql = conn.prepareStatement(
                            "SELECT * "
                            + "FROM user "
                            + "WHERE email=? "
                            + "AND password=?"
                    );
            sql.setString(1, email);
            sql.setString(2, password);
            result = sql.executeQuery();
            if (result.next()) {
                found = true;
            }
            sql.close();
        } 
        catch (Exception e) {
            System.out.println("Error checkLogin " + e);
        }

        return found;

    }

    @Override
    public void insertData() {
        try {

            PreparedStatement sql = conn.prepareStatement(
                            "INSERT INTO user"
                            + "(nama,role,tanggal_lahir,email,password)"
                            + " VALUES(?,?,?,?,?)"
                    );

            sql.setString(1, this.nama);
            sql.setString(2, this.role);
            sql.setDate(3, this.tanggal_lahir);
            sql.setString(4, this.email);
            sql.setString(5, this.password);
            
            sql.executeUpdate();
            sql.close();

        } catch (Exception e) {
            System.out.println("Error insertData " + e);
        }
    }

    @Override
    public void updateData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Object> viewListData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
