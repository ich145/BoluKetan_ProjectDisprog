/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Model.Meja;
import javax.jws.WebMethod;
import javax.jws.WebService;
/**
 *
 * @author michael
 */
@WebService
public class MejaWS {
    @WebMethod

    public String tambahMeja(
            int nomer_meja,
            String status,
            int jumlah_konsumen
    ) {

        Meja m= new Meja(
                        nomer_meja,
                        status,
                        jumlah_konsumen
                );
        m.insertData();
        return "Insert Success";
    }

    @WebMethod
    public String lihatMeja() {

        Meja m = new Meja();
        String hasil = "";
        for (Object o : m.viewListData()) {

            Meja temp = (Meja) o;

            hasil+= temp.getIdMeja()
                    + " | "
                    + temp.getNomer_meja()
                    + " | "
                    + temp.getStatus()
                    + " | "
                    + temp.getJumlah_konsumen()
                    + "\n";

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
}
