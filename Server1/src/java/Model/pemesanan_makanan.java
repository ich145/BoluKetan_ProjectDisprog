package Model;

import static Model.MyModel.conn;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author ZKFZ
 */
public class pemesanan_makanan extends MyModel {
    private int idReservasi;
    private int idMenu;
    private int jumlah;
    private String status_pemesanan;
    
    public int getIdReservasi() {
        return idReservasi;
    }

    public void setIdReservasi(int idReservasi) {
        this.idReservasi = idReservasi;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getStatus_pemesanan() {
        return status_pemesanan;
    }

    public void setStatus_pemesanan(String status_pemesanan) {
        this.status_pemesanan = status_pemesanan;
    }
    
    public pemesanan_makanan() {
        super();
    }
    
    public pemesanan_makanan(int idReservasi, int idMenu, int jumlah) {
        super(); // Pastikan super() dipanggil agar koneksi ke database ter-inisialisasi
        this.setIdMenu(idMenu);
        this.setIdReservasi(idReservasi);
        this.setJumlah(jumlah);; // Set jumlah belanjaan
    }
    
    public pemesanan_makanan(int idReservasi, int idMenu, int jumlah, String status) {
        super(); // Pastikan super() dipanggil agar koneksi ke database ter-inisialisasi
        this.setIdMenu(idMenu);
        this.setIdReservasi(idReservasi);
        this.setJumlah(jumlah);
        this.setStatus_pemesanan(status);
    }

    @Override
    public void insertData() {
        try {
            PreparedStatement sql;
            // PERBAIKAN: Menambahkan tanda kurung () pada klausa VALUES
            if (this.status_pemesanan != null) {
                sql = conn.prepareStatement("INSERT INTO pemesan_makanan (reservasi_idreservasi, Menu_idMenu, jumlah, status_pesanan) VALUES (?, ?, ?, ?)");
                sql.setInt(1, this.idReservasi);
                sql.setInt(2, this.idMenu);
                sql.setInt(3, this.jumlah);
                sql.setString(4, this.status_pemesanan);
            } else {
                sql = conn.prepareStatement("INSERT INTO pemesan_makanan (reservasi_idreservasi, Menu_idMenu, jumlah) VALUES (?, ?, ?)");
                sql.setInt(1, this.idReservasi);
                sql.setInt(2, this.idMenu);
                sql.setInt(3, this.jumlah); 
            }
            sql.executeUpdate();
            sql.close();
            System.out.println("Insert data pemesanan makanan berhasil!");
        } catch (Exception ex) {
            System.out.println("Error di insert: " + ex);
        }
    }

    @Override
    public void updateData() {
        try {
            // PERBAIKAN: Menyelaraskan nama tabel menjadi pemesan_makanan & kolom status_pesanan
            PreparedStatement sql = conn.prepareStatement("UPDATE pemesan_makanan SET jumlah = ?, status_pesanan = ? WHERE reservasi_idreservasi = ? AND Menu_idMenu = ?");
            sql.setInt(1, this.jumlah);
            sql.setString(2, this.status_pemesanan);
            sql.setInt(3, this.idReservasi);
            sql.setInt(4, this.idMenu);
            sql.executeUpdate();
            sql.close();
        } catch (Exception ex) {
            System.out.println("Error di Update: " + ex);
        }
    }

    @Override
    public void deleteData() {
        try {
            // PERBAIKAN: Menyelaraskan nama tabel menjadi pemesan_makanan
            PreparedStatement sql = conn.prepareStatement("DELETE FROM pemesan_makanan WHERE reservasi_idreservasi = ? AND Menu_idMenu = ?");
            sql.setInt(1, idReservasi);
            sql.setInt(2, idMenu);
            sql.executeUpdate();
            sql.close();
        } catch (Exception ex) {
            System.out.println("Error di delete: " + ex);
        }
    }

    @Override
    public ArrayList<Object> viewListData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}