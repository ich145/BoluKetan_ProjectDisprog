/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michael
 */
public class User extends MyModel{

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private int idUser;
    private String nama;
    private String role;
    private String email;
    private String password;
    
    public User(){
        super();
    }
    
    public User(
            String nama,
            String role,
            String email,
            String password
    ) {
        super();
        this.nama = nama;
        this.role = role;
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
            System.out.println("CONNECT DB: " + conn.getCatalog());

            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO `user` (nama, role, email, password) VALUES (?, ?, ?, ?)"
            );

            sql.setString(1, this.nama);
            sql.setString(2, this.role);
            sql.setString(3, this.email);
            sql.setString(4, this.password);
            
            int hasil = sql.executeUpdate();
            System.out.println("insert berhasil: " + hasil);
            
            
            //sql.executeUpdate();
            sql.close();

        } catch (Exception e) {
            e.printStackTrace(); // ganti ini

            throw new RuntimeException(e);
            //System.out.println("Error insertData " + e);
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
        ArrayList<Object> collections = new ArrayList<Object>();
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery("Select * from user");
            while (this.result.next()){
                User temp = new User(
                    this.result.getString("nama"),
                    this.result.getString("role"),
                    this.result.getString("email"),
                    this.result.getString("password")
                );
                collections.add(temp);
            }
        } catch (Exception e){
            System.out.println("Error viewListData " + e);
        }
        return collections;
    }
    
    public User getData(String email, String password)
    {
        User user = new User();
        try {
            PreparedStatement sql = conn.prepareStatement("Select * from user where email = ? and password = ?");
            sql.setString(1, email);
            sql.setString(2, password);
            result = sql.executeQuery();
            if(result.next())
            {
                user = new User(
                this.result.getString("nama"),
                this.result.getString("role"),
                this.result.getString("email"),
                this.result.getString("password"));
            }
        } catch (Exception ex) {
            System.out.println("Email atau Password Salah");
        }
        return user;
    }

}
