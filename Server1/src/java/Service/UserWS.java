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

    public boolean login(
            String email,
            String password
    ) {

        User u = new User();
        return u.checkLogin(
                email,
                password
        );
    }
}
