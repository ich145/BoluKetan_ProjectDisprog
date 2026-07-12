/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class Menu extends MyModel{
    private int idMenu;
    private String nama;
    private String kategori;
    private double harga;
    private String informasi;

    public Menu(){

        super();

    }
    public Menu(String nama, String kategori, double harga, String informasi) {
        super();
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.informasi = informasi;
    }
    
    public Menu(int id, String nama, String kategori, double harga, String informasi) {
        super();
        this.idMenu = id;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.informasi = informasi;
}
    
    public int getIdMenu() {
    return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getInformasi() {
        return informasi;
    }

    public void setInformasi(String informasi) {
        this.informasi = informasi;
    }

    @Override
    public void insertData() {
        try {
            PreparedStatement sql =conn.prepareStatement(
                "INSERT INTO Menu "
                + "(nama,kategori,harga,informasi) "
                + "VALUES (?,?,?,?)"
            );

            sql.setString(1, this.nama);
            sql.setString(2, this.kategori);
            sql.setDouble(3, this.harga);
            sql.setString(4, this.informasi);
            sql.executeUpdate();
            sql.close();
        }

        catch(Exception e){
            System.out.println("Error insertData " + e);
        }
    }

    @Override
    public void updateData() {
        
        try {
            
                PreparedStatement sql = conn.prepareStatement(
                    "UPDATE Menu SET nama=?, katgori=?, harga=?, Informasi=? WHERE id=?");
                sql.setString(1, this.nama);
                sql.setString(2, this.kategori);
                sql.setDouble(3, this.harga);
                sql.setString(4, this.informasi);
                sql.executeUpdate();
                sql.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteData() {
        try {
            
                PreparedStatement sql = conn.prepareStatement(
                    "DELETE FROM Menu WHERE id=?");
                sql.setInt(1, this.idMenu);
                sql.executeUpdate();
                sql.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public ArrayList<Object> viewListData() {
        ArrayList<Object> list = new ArrayList<>();
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM menu");

            while(result.next()){
                Menu m = new Menu();
                m.setIdMenu(result.getInt("idMenu"));

                m.setNama(result.getString("nama"));

                m.setKategori(result.getString("kategori"));

                m.setHarga(result.getDouble("harga"));

                m.setInformasi(result.getString("informasi"));
                
                list.add(m);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
    
}
