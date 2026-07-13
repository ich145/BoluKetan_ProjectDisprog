/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;

import java.sql.DriverManager;
/**
 *
 * @author michael
 */
public class TestMenui {
    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/uasdisprog"
                    + "?useSSL=false"
                    + "&allowPublicKeyRetrieval=true"
                    + "&serverTimezone=Asia/Jakarta",
                    "root",
                    ""
            );

            System.out.println("Koneksi berhasil!");

            conn.close();

        } catch (Exception e) {

            System.out.println("Koneksi gagal!");

            e.printStackTrace();

        }

    }
}
