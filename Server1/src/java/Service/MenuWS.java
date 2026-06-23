/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Menu;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author michael
 */
@WebService
public class MenuWS {
    @WebMethod

    public String tambahMenu(
            String nama,
            String kategori,
            double harga,
            String informasi
    ) {

        Menu m= new Menu(
                        nama,
                        kategori,
                        harga,
                        informasi
                );
        m.insertData();
        return "Insert Success";
    }
    
    @WebMethod
    public String lihatMenu() {
        Menu m = new Menu();
        String hasil = "";
        for (Object o : m.viewListData()) {
            Menu temp = (Menu) o;
            hasil
                    += temp.getIdMenu()
                    + " | "
                    + temp.getNama()
                    + " | "
                    + temp.getKategori()
                    + " | "
                    + temp.getHarga()
                    + "\n";

        }
        return hasil;
    }
}
