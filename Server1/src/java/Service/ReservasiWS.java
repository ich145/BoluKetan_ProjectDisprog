/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Model.Reservasi;

import java.sql.Timestamp;

import javax.jws.WebMethod;

import javax.jws.WebService;
/**
 *
 * @author michael
 */
@WebService
public class ReservasiWS {
    @WebMethod

    public String tambahReservasi(
            String tanggal,
            int jumlah,
            String status,
            int idUser,
            int idMeja,
            String jam
    ) {
        Reservasi r = new Reservasi(
                        Timestamp.valueOf(tanggal),
                        jumlah,
                        status,
                        idUser,
                        idMeja,
                        Timestamp.valueOf(jam)
                );

        r.insertData();
        return "Insert Success";
    }
    
    @WebMethod
    public String lihatReservasi() {
        Reservasi r= new Reservasi();
        String hasil = "";
        for (Object o : r.viewListData()) {
            Reservasi temp = (Reservasi) o;
            hasil += temp.getIdreservasi()
                    + " | "
                    + temp.getTanggal_reservasi()
                    + " | "
                    + temp.getJumlah_tamu()
                    + " | "
                    + temp.getStatus_reservasi()
                    + " | "
                    + temp.getUser_iduser()
                    + " | "
                    + temp.getMeja_idmeja()
                    + "\n";
        }
        return hasil;
    }
}
