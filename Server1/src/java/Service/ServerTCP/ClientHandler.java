/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.ServerTCP;
import java.io.*;
import java.net.Socket;
/**
 *
 * @author michael
 */
public class ClientHandler extends Thread{
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public ClientHandler(Socket socket) 
    {
        this.socket = socket;
    }

    @Override

    public void run() {
        try {
            in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String pesan = in.readLine();
                System.out.println(
                        "Dari Client : "
                        + pesan
                );

                out.println(
                        "Server menerima : "
                        + pesan
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
