/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.ServerTCP;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author michael
 */
public class ServerTCP {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("Server Running...");

            while (true) {
                Socket s = ss.accept();
                System.out.println("Client Connected");

                ClientHandler ch = new ClientHandler(s);
                ch.start();
            }

        } catch (Exception e) {

            System.out.println(e);

        }

    }
}
