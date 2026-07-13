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
    public boolean eregister(String nama, String role, String email, String password){
        try {
            User u = new User(nama, role,email, password);
            u.insertData();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("REGISTER ERROR: " + e.getMessage());
        }
    }
    
    @WebMethod
    public User egetData(String email, String password)
    {
        try
        {
            User u = new User();
            u.getData(email, password);
            return u;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new RuntimeException("REGISTER ERROR: " + ex.getMessage());
        }
    }
}
