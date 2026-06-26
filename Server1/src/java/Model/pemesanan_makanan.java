/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import static Model.MyModel.conn;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author ZKFZ
 */
public class pemesanan_makanan extends MyModel{
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
    
    public pemesanan_makanan()
    {
        super();
    }
    
    public pemesanan_makanan(int idReservasi, int idMenu, int jumlah)
    {
        this.setIdMenu(idMenu);
        this.setIdReservasi(idReservasi);
        this.setJumlah(jumlah);
    }
    public pemesanan_makanan(int idReservasi, int idMenu, int jumlah, String status)
    {
        this.setIdMenu(idMenu);
        this.setIdReservasi(idReservasi);
        this.setJumlah(jumlah);
        this.setStatus_pemesanan(status);
    }

    @Override
    public void insertData() {
        try
        {
            PreparedStatement sql;
            if(this.status_pemesanan != null)
            {
                sql =conn.prepareStatement("insert into pemesanan_makanan(reservasi_idreservasi, Menu_idMenu,jumlah,status_pemesanan) values ?,?,?,?");
                sql.setInt(1, this.idReservasi);
                sql.setInt(2, this.idMenu);
                sql.setInt(3, this.jumlah);
                sql.setString(4, this.status_pemesanan);
            }
            else
            {
                sql =conn.prepareStatement("insert into pemesanan_makanan(reservasi_idreservasi, Menu_idMenu,jumlah) values ?,?,?");
                sql.setInt(1, this.idReservasi);
                sql.setInt(2, this.idMenu);
                sql.setInt(3, this.jumlah); 
            }
            sql.executeUpdate();
            sql.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error di insert" + ex);
        }
    }

    @Override
    public void updateData() {
        try
        {
            PreparedStatement sql = conn.prepareStatement("update pemesanan_makanan set jumlah = ?, status_pemesanan = ? " +
                    "where reservasi_idReservasi = ? and Menu_idMenu = ?");
            sql.setInt(1, this.jumlah);
            sql.setString(2,this.status_pemesanan);
            sql.setInt(3, this.idReservasi);
            sql.setInt(4, this.idMenu);
            sql.executeUpdate();
            sql.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error di Update " + ex);
        }
    }

    @Override
    public void deleteData() {
        try
        {
            PreparedStatement sql = conn.prepareStatement("Delete from pemesanan_makanan where reservasi_idReservasi = ? and Menu_idMenu = ?");
            sql.setInt(1, idReservasi);
            sql.setInt(2, idMenu);
            sql.executeUpdate();
            sql.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error di delete " + ex);
        }
    }

    @Override
    public ArrayList<Object> viewListData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
