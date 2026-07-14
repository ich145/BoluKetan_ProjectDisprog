/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Model.Meja;
import Model.Reservasi;
import Model.pemesanan_makanan;
import java.sql.SQLException;

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
    public int tambahReservasi(String tanggal, int jumlah, String status, int idUser, int idMeja, String jam, double totalHarga) {
        Reservasi r = new Reservasi(
                tanggal,
                jumlah,
                status,
                idUser,
                idMeja,
                jam);

        r.setTotal_harga(totalHarga);
        
        int idBaru = r.insertDataAndGetId();
        
        if(idBaru>0)
        {
            Meja m = new Meja();
            m.setIdMeja(idMeja);
            m.setStatus("reservasi");
            m.setJumlah_konsumen(jumlah);
            m.updateData();
        }

        return idBaru; // Mengembalikan ID (int)
    }
    
    
    @WebMethod
    public List<Reservasi> lihatReservasi(int id) {
        
        ArrayList<Object> data = new Reservasi().viewDataUser(id);
        List<Reservasi> hasil = new ArrayList<>();
        for (Object o : data) {
            hasil.add((Reservasi) o);
        }
        return hasil;

    }
    @WebMethod
    public String updateReservasi(String tanggal, int jumlah, String status, int idUser, int idMeja, String jam){
        
        Reservasi r = new Reservasi(tanggal, jumlah, status, idUser, idMeja, jam);
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
    @WebMethod(operationName = "simpanPesananMakanan")
    public boolean simpanPesananMakanan(int idReservasi, int idMenu, int jumlah, String statusPesanan) {
        try {
            Model.pemesanan_makanan pm = new Model.pemesanan_makanan(idReservasi, idMenu, jumlah, statusPesanan);
            pm.insertData();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @WebMethod
    public List<pemesanan_makanan> lihatPesanan(int idReservasi) {
        return pemesanan_makanan.getByReservasi(idReservasi);
    }   
       
    @WebMethod
    public double hitungTotalHarga(int idReservasi) 
    {
        try
        {
            Reservasi reservasi = Reservasi.getById(idReservasi);
            if(reservasi != null)
            {
                reservasi.hitungTotalHarga();
                return reservasi.getTotal_harga();
            }
        }
        catch(Exception ex)
        {
            System.out.println("Eror: " + ex);
        }
        return 0;
    }
}
