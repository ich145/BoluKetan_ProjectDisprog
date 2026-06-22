/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author michael
 */
public class TestMenui {
    public static void main(String[] args) {

        Menu m = new Menu(

                "Nasi Goreng",

                "Makanan",

                25000,

                "Nasi goreng spesial"

        );

        m.insertData();

        System.out.println("Insert Success");

    }
}
