/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michael
 */
public class Reservasi extends MyModel{
    private int idreservasi;
    private String tanggal_reservasi;
    private int jumlah_tamu;
    private String status_reservasi;
    private int user_iduser;
    private int meja_idmeja;
    private String jam_reservasi;
    private double total_harga;
    private String tanggal;
    private int jumlah;
    private String status;
    private int idUser;
    private int idMeja;
    private String jam;
    
    public int getIdreservasi() {
        return idreservasi;
    }

    public void setIdreservasi(int idreservasi) {
        this.idreservasi = idreservasi;
    }

    public String getTanggal_reservasi() {
        return tanggal_reservasi;
    }

    public void setTanggal_reservasi(String tanggal_reservasi) {
        this.tanggal_reservasi = tanggal_reservasi;
    }

    public int getJumlah_tamu() {
        return jumlah_tamu;
    }

    public void setJumlah_tamu(int jumlah_tamu) {
        this.jumlah_tamu = jumlah_tamu;
    }

    public String getStatus_reservasi() {
        return status_reservasi;
    }

    public void setStatus_reservasi(String status_reservasi) {
        this.status_reservasi = status_reservasi;
    }

    public int getUser_iduser() {
        return user_iduser;
    }

    public void setUser_iduser(int user_iduser) {
        this.user_iduser = user_iduser;
    }

    public int getMeja_idmeja() {
        return meja_idmeja;
    }

    public void setMeja_idmeja(int meja_idmeja) {
        this.meja_idmeja = meja_idmeja;
    }

    public String getJam_reservasi() {
        return jam_reservasi;
    }

    public void setJam_reservasi(String jam_reservasi) {
        this.jam_reservasi = jam_reservasi;
    }
    
    public double getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(double total_harga) {
        this.total_harga = total_harga;
    }
    
    public Reservasi() {
        super();
    }
    public Reservasi(
            String tanggal_reservasi,
            int jumlah_tamu,
            String status_reservasi,
            int user_iduser,
            int meja_idmeja,
            String jam_reservasi
    ) {
        super();
        this.tanggal_reservasi = tanggal_reservasi;
        this.jumlah_tamu = jumlah_tamu;
        this.status_reservasi = status_reservasi;
        this.user_iduser = user_iduser;
        this.meja_idmeja = meja_idmeja;
        this.jam_reservasi = jam_reservasi;
    }

    @Override
    public void insertData() {
        try {
            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO reservasi "
                    + "(tanggal_reservasi,jumlah_tamu,status_reservasi,user_iduser,meja_idmeja,jam_reservasi,total_harga) "
                    + "VALUES (?,?,?,?,?,?,?)"
            );

            sql.setString(1, this.tanggal_reservasi);
            sql.setInt(2, this.jumlah_tamu);
            sql.setString(3, this.status_reservasi);
            sql.setInt(4, this.user_iduser);
            sql.setInt(5, this.meja_idmeja);
            sql.setString(6, this.jam_reservasi);
            sql.setDouble(7, this.total_harga);

            sql.executeUpdate();
            sql.close();
        } catch (Exception e) {
            System.out.println("Error insertData " + e);
        }
    }

    @Override
    public void updateData() {
        try {
            PreparedStatement sql = conn.prepareStatement(
                            "UPDATE reservasi "
                            + "SET "
                            + "tanggal_reservasi=?,"
                            + "jumlah_tamu=?,"
                            + "status_reservasi=?,"
                            + "user_iduser=?,"
                            + "meja_idmeja=?,"
                            + "jam_reservasi=? "
                            + "WHERE idreservasi=?"
                    );

            sql.setString(1, this.tanggal_reservasi);
            sql.setInt(2, this.jumlah_tamu);
            sql.setString(3, this.status_reservasi);
            sql.setInt(4, this.user_iduser);
            sql.setInt(5, this.meja_idmeja);
            sql.setString(6, this.jam_reservasi);
            sql.setInt(7, this.idreservasi);

            sql.executeUpdate();
            sql.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteData() {
        try {
            PreparedStatement sql = conn.prepareStatement(
                            "DELETE FROM reservasi "
                            + "WHERE idreservasi=?"
                    );
            sql.setInt(1,this.idreservasi);

            sql.executeUpdate();
            sql.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public ArrayList<Object> viewListData() {
       ArrayList<Object> hasil = new ArrayList<>();
        try {
            PreparedStatement sql = conn.prepareStatement("SELECT * FROM reservasi");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Reservasi r = new Reservasi(
                    rs.getString("tanggal_reservasi"),
                    rs.getInt("jumlah_tamu"),
                    rs.getString("status_reservasi"),
                    rs.getInt("user_iduser"),
                    rs.getInt("meja_idmeja"),
                    rs.getString("jam_reservasi")
                );
                r.setIdreservasi(rs.getInt("idreservasi"));
                r.setTotal_harga(rs.getDouble("total_harga"));
                hasil.add(r);
            }
            sql.close();
        } catch (Exception e) {
        System.out.println("Error getAll " + e);
    }
        return hasil;

    }
    
    public ArrayList<Object> viewDataUser(int id) {
       ArrayList<Object> hasil = new ArrayList<>();
        try {
            PreparedStatement sql = conn.prepareStatement("SELECT * FROM reservasi where user_iduser = ?");
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Reservasi r = new Reservasi(
                    rs.getString("tanggal_reservasi"),
                    rs.getInt("jumlah_tamu"),
                    rs.getString("status_reservasi"),
                    rs.getInt("user_iduser"),
                    rs.getInt("meja_idmeja"),
                    rs.getString("jam_reservasi")
                );
                r.setIdreservasi(rs.getInt("idreservasi"));
                r.setTotal_harga(rs.getDouble("total_harga"));
                hasil.add(r);
            }
            sql.close();
        } catch (Exception e) {
        System.out.println("Error getAll " + e);
    }
        return hasil;

    }
    
    public void hitungTotalHarga() throws SQLException
    {
        PreparedStatement sql = conn.prepareStatement("Select Sum(m.harga * pm.jumlah) as total " +
                "from pemesanan_makanan pm inner join Menu m on pm.Menu_idMenu = m.idMenu " +
                "where pm.reservasi_idreservasi = ?");
        
        sql.setInt(1, this.idreservasi);
        
        ResultSet result = sql.executeQuery();
        if(result.next())
        {
            this.total_harga = result.getInt("total");
        }
        sql.close();
        
        PreparedStatement updateTotal = conn.prepareStatement("update reservasi set total_harga = ? where idreservasi = ?" );
        
        updateTotal.setDouble(1, this.total_harga);
        updateTotal.setInt(2,this.idreservasi);
        updateTotal.executeUpdate();
        updateTotal.close();
    }
    
    public boolean cekKetersediaan() {
    boolean tersedia = true;
    try {
        PreparedStatement sql = conn.prepareStatement(
            "SELECT COUNT(*) as jumlah FROM reservasi " +
            "WHERE meja_idmeja = ? AND jam_reservasi = ? AND status_reservasi != 'cancel'");
        sql.setInt(1, this.meja_idmeja);
        sql.setString(2, this.jam_reservasi);

        ResultSet rs = sql.executeQuery();
        if (rs.next() && rs.getInt("jumlah") > 0) {
            tersedia = false; // sudah ada reservasi lain di meja & jam yang sama
        }
        sql.close();
    } catch (Exception e) {
        System.out.println("Error cekKetersediaan " + e);
        tersedia = false; // kalau error, anggap tidak tersedia (safer default)
    }
    return tersedia;
    }
    
    public static Reservasi getById(int idreservasi) {
    Reservasi r = null;
    try {
        PreparedStatement sql = conn.prepareStatement(
            "SELECT * FROM reservasi WHERE idreservasi = ?");
        sql.setInt(1, idreservasi);

        ResultSet rs = sql.executeQuery();
        if (rs.next()) {
            r = new Reservasi(
                rs.getString("tanggal_reservasi"),
                rs.getInt("jumlah_tamu"),
                rs.getString("status_reservasi"),
                rs.getInt("user_iduser"),
                rs.getInt("meja_idmeja"),
                rs.getString("jam_reservasi")
            );
            r.setIdreservasi(idreservasi);
        }
        sql.close();
    } catch (Exception e) {
        System.out.println("Error getById " + e);
    }
        return r;
    }
    
    public static ArrayList<Meja> cariMejaTersedia(Timestamp jamReservasi, int jumlahTamu) {

        ArrayList<Meja> hasil = new ArrayList<>();

        try {
            PreparedStatement sql = conn.prepareStatement(
                    "SELECT * FROM meja m "
                    + "WHERE m.jumlah_konsumen >= ? "
                    + "AND m.status = 'kosong' "
                    + "AND m.idmeja NOT IN ("
                    + "SELECT r.meja_idmeja "
                    + "FROM reservasi r "
                    + "WHERE r.jam_reservasi = ? "
                    + "AND r.status_reservasi <> 'cancel'"
                    + ")"
            );

            sql.setInt(1, jumlahTamu);
            sql.setTimestamp(2, jamReservasi);

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {

                Meja m = new Meja();

                m.setIdMeja(rs.getInt("idmeja"));
                m.setStatus(rs.getString("status"));
                m.setJumlah_konsumen(rs.getInt("jumlah_konsumen"));

                hasil.add(m);
            }

            sql.close();

        } catch (Exception e) {
            System.out.println("Error cari meja tersedia : " + e);
        }

        return hasil;
    }
    public int insertDataAndGetId() {
        int idBaru = 0;
        try {
            // Sesuaikan nama kolom dengan yang ada di database kamu
            String query = "INSERT INTO reservasi (tanggal_reservasi, jumlah_tamu, status_reservasi, user_iduser, meja_idmeja, jam_reservasi, total_harga) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sql = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            sql.setString(1, this.tanggal_reservasi);
            sql.setInt(2, this.jumlah_tamu);
            sql.setString(3, this.status_reservasi);
            sql.setInt(4, this.user_iduser);
            sql.setInt(5, this.meja_idmeja);
            sql.setString(6, this.jam_reservasi);
            sql.setDouble(7, this.total_harga);

            sql.executeUpdate();

            ResultSet rs = sql.getGeneratedKeys();
            if (rs.next()) {
                idBaru = rs.getInt(1);
            }

            sql.close();
        } catch (Exception ex) {
            System.out.println("Error saat insert: " + ex);
        }
        return idBaru;
    }
}   
