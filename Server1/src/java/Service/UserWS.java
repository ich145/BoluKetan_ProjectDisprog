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
    public boolean eregister(String nama, String role, String email, String password) {

        System.out.println("=== REGISTER DIPANGGIL ===");

        try {

            System.out.println("Nama : " + nama);
            System.out.println("Email: " + email);
            System.out.println("Pass : " + password);

            User u = new User(nama, role, email, password);

            System.out.println("USER DIBUAT");

            u.insertData();

            System.out.println("INSERT SELESAI");

            return true;

        } catch (Exception e) {

            System.out.println("ERROR REGISTER");

            return false;
        }
    }
    
    @WebMethod
    public User egetData(String email, String password)
    {
        try
        {
            User u = new User();
            return u.getData(email, password);
        }
        catch(Exception ex)
        {
            throw new RuntimeException("REGISTER ERROR: " + ex.getMessage());
        }
    }
    @WebMethod
    public String getRole(String email, String password) {
        User u = new User();
        User data = u.getData(email, password);
        return data.getRole();
    }

    @WebMethod
    public String getNama(String email, String password) {
        User u = new User();
        User data = u.getData(email, password);
        return data.getNama();
    }
    
    @WebMethod
    public boolean test() {
        System.out.println("SERVICE HIDUP");
        return true;
    }
    
    @WebMethod
    public boolean updateProfile(int idUser, String nama, String email, String password) {
        try {
            User u = new User();
            u.setIdUser(idUser);
            u.setNama(nama);
            u.setEmail(email);
            u.setPassword(password); 
            u.updateData();
            return true;
        } catch (Exception e) {
            System.out.println("Error updateProfile " + e);
            return false;
        }
    }
}
