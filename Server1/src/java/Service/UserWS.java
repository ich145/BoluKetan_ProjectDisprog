/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.User;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author michael
 */
@WebService
public class UserWS {
    @WebMethod

    public boolean elogin(String email, String password) {
        User u = new User();
        return u.checkLogin(email,password);
    }
    
    @WebMethod
    public boolean eregister(String nama, String role, String tanggal_lahir, String email, String password){
        try {
            java.sql.Date tglLahir = java.sql.Date.valueOf(tanggal_lahir);
            User u = new User(nama, role, tglLahir, email, password);
            u.insertData();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("REGISTER ERROR: " + e.getMessage());
        }
    }
}
