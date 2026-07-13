/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author michael
 */
public class TestUser {
    public static void main(String[] args) {

        User u = new User(
                "Michael",
                "konsumen",
                "michael@gmail.com",
                "123"
        );

        u.insertData();

        System.out.println("Selesai");

    }
}
