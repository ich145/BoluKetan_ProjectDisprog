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
public class Meja extends MyModel {

    public int getIdMeja() {
        return idMeja;
    }

    public void setIdMeja(int idMeja) {
        this.idMeja = idMeja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getJumlah_konsumen() {
        return jumlah_konsumen;
    }

    public void setJumlah_konsumen(int jumlah_konsumen) {
        this.jumlah_konsumen = jumlah_konsumen;
    }
    private int idMeja;
    private String status;
    private int jumlah_konsumen;

    public Meja() {
        super();
    }

    public Meja(
            String status,
            int jumlah_konsumen
    ) {
        super();
        this.status = status;
        this.jumlah_konsumen = jumlah_konsumen;
    }

    @Override
    public void insertData() {
        try {

            PreparedStatement sql
                    = conn.prepareStatement(
                            "INSERT INTO meja "
                            + "(status,jumlah_konsumen) "
                            + "VALUES (?,?)"
                    );

            sql.setString(1, this.status);
            sql.setInt(2, this.jumlah_konsumen);

            sql.executeUpdate();
            sql.close();

        } catch (Exception e) {
            System.out.println("Error insertData " + e);
        }
    }

    @Override
    public void updateData() {
        try {

            PreparedStatement sql
                    = conn.prepareStatement(
                            "UPDATE meja "
                            + "SET "
                            + "status=?,"
                            + "jumlah_konsumen=? "
                            + "WHERE idmeja=?"
                    );

            sql.setString(1,this.status);
            sql.setInt(2,this.jumlah_konsumen);
            sql.setInt(3,this.idMeja);

            sql.executeUpdate();
            sql.close();

        } catch (Exception e) {

            System.out.println(e);

        }
    }

    @Override
    public void deleteData() {
        try {

            PreparedStatement sql
                    = conn.prepareStatement(
                            "DELETE FROM meja "
                            + "WHERE idmeja=?"
                    );
            sql.setInt(1,this.idMeja);

            sql.executeUpdate();
            sql.close();

        } catch (Exception e) {

            System.out.println(e);

        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Object> viewListData() {
        ArrayList<Object> collections
                = new ArrayList<>();

        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM meja");

            while (result.next()) {
                Meja temp = new Meja();

                temp.setIdMeja(result.getInt("idmeja"));
                temp.setStatus(result.getString("status"));
                temp.setJumlah_konsumen(result.getInt("jumlah_konsumen"));

                collections.add(temp);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return collections;
    }
}
