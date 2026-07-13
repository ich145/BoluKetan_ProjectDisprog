/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Model.Meja;
import Model.Reservasi;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;

import javax.jws.WebService;
/**
 *
 * @author michael
 */
@WebService
public class ReservasiWS {
    @WebMethod
    public String tambahReservasi(String tanggal, int jumlah, String status, int idUser, int idMeja, String jam, double totalHarga) {
        Reservasi r = new Reservasi(
                Timestamp.valueOf(tanggal),
                jumlah,
                status,
                idUser,
                idMeja,
                Timestamp.valueOf(jam));

        r.setTotal_harga(totalHarga);
        r.insertData();

        return "Insert Success";
    }
    
    @WebMethod
    public List<Reservasi> lihatReservasi() {
        
        ArrayList<Object> data = new Reservasi().viewListData();
        List<Reservasi> hasil = new ArrayList<>();
        for (Object o : data) {
            hasil.add((Reservasi) o);
        }
        return hasil;

    }
    @WebMethod
    public String updateReservasi(String tanggal, int jumlah, String status, int idUser, int idMeja, String jam){
        
        Reservasi r = new Reservasi(Timestamp.valueOf(tanggal), jumlah, status, idUser, idMeja, Timestamp.valueOf(jam));
        r.updateData();
        return "Update Success";
    }
    
    @WebMethod
    public String batalkanReservasi(int id){
        
        Reservasi r = Reservasi.getById(id);
        if (r == null) {
        return "Gagal: Reservasi tidak ditemukan";
        }
        r.setStatus_reservasi("cancel");
        r.updateData();
        return "Reservasi Dibatalkan";
    }
    
    @WebMethod
    public List<Meja> cariMejaTersedia(String jamReservasi, int jumlahTamu){
        
        Timestamp ts = Timestamp.valueOf(jamReservasi);
        return Reservasi.cariMejaTersedia(ts, jumlahTamu);
    }

            
       
}
