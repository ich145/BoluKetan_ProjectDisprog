/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Meja;
import Model.Menu;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author michael
 */
@WebService
public class MejaWS {

    @WebMethod

    public String tambahMeja(String status, int jumlah_konsumen) {
        Meja m = new Meja(status, jumlah_konsumen);
        m.insertData();
        return "Insert Success";
    }

    @WebMethod
    public List<Meja> lihatMeja() {       
        ArrayList<Object> data = new Meja().viewListData();
        List<Meja> hasil = new ArrayList<>();
        for (Object o :data) {
            hasil.add((Meja) o);
        }
        return hasil;
    }

    @WebMethod
    public String updateStatus(
            int idmeja,
            String status,
            int jumlah_konsumen
    ) {
        Meja m = new Meja();
        m.setIdMeja(idmeja);
        m.setStatus(status);
        m.setJumlah_konsumen(jumlah_konsumen);
        m.updateData();
        return "Update Success";
    }
    
    @WebMethod
    public List<Meja> mejaBerdasarkanStatus(String status)
    {
        ArrayList<Object> data = new Meja().listDataTerpilih(status);
        List<Meja> hasil = new ArrayList<>();
        for (Object o :data) {
            hasil.add((Meja) o);
        }
        return hasil;
    }
}
