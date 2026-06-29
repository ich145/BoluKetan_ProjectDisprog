/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boluketan_projectdisprog;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author michael
 */
public class Koneksi {
    private static Connection conn;

    public static Connection getConnection() {

        try {

            if (conn == null || conn.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/uasdisprog"
                        + "?useSSL=false"
                        + "&allowPublicKeyRetrieval=true"
                        + "&serverTimezone=Asia/Jakarta",
                        "root",
                        ""
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return conn;

    }
}
