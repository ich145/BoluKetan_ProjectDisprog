/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Menu;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author michael
 */
@WebService
public class MenuWS {
    @WebMethod

    public String tambahMenu(String nama,String kategori,double harga,String informasi) {

        Menu m= new Menu(nama,kategori,harga,informasi);
        m.insertData();
        return "Insert Success";
    }
    
    @WebMethod
    public List<Menu> lihatMenu() {
        ArrayList<Object> data = new Menu().viewListData();
        List<Menu> hasil = new ArrayList<>();
        for (Object o : data) {
            hasil.add((Menu) o);
        }
        return hasil;
    }
    
    @WebMethod
    public String updateMenu(int id, String nama, String kategori, double harga, String informasi) {
        
        Menu m = new Menu(id, nama, kategori, harga, informasi);
        m.updateData();
        return "Update Success";
    }
    
    @WebMethod
        public String deleteMenu(int id) {

        Menu m = new Menu();
        m.setIdMenu(id);
        m.deleteData();
        return "Delete Success";
    }
        
        
    public List<Menu> cariMenu(String keyword){
        ArrayList<Object> data = new Menu().cariMenu(keyword);
        List<Menu> hasil = new ArrayList<>();
        for (Object o : data) {
            hasil.add((Menu) o);
        }
        return hasil;
    }
}
