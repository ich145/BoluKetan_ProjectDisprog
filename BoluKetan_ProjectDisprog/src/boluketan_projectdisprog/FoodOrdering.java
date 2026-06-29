/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package boluketan_projectdisprog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author michael
 */
public class FoodOrdering extends javax.swing.JFrame {

    /**
     * Creates new form FoodOrdering
     */

    
    public FoodOrdering(String namaPemesan, String waktuReservasi) {
        initComponents();
        lblNama.setText(namaPemesan);
        lblWaktu.setText(waktuReservasi);
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Menu", "Harga", "Qty", "Subtotal"},0){
                @Override
                public boolean isCellEditable(int row,int column){
                    return column==2;
                }
            };


        tableKeranjang.setModel(model);
        tambahKeranjang("Nasi Goreng", 25000, 1);
        tambahKeranjang("Mie Ayam", 18000, 2);
        tambahKeranjang("Es Teh", 5000, 1);
        
        model.addTableModelListener(e->{
            if(e.getColumn()==2){
                int row=e.getFirstRow();
                try{
                    String hargaString = model.getValueAt(row, 1).toString();

                    hargaString = hargaString.replace("Rp", "")
                            .replace(".", "")
                            .trim();

                    int harga = Integer.parseInt(hargaString);

                    int qty = Integer.parseInt(model.getValueAt(row, 2).toString());

                    if (qty < 1) {
                        qty = 1;
                        model.setValueAt(1, row, 2);
                    }

                    model.setValueAt(Rupiah(harga * qty), row, 3);
                    hitungTotal();
                }catch(Exception ex){

                }
            }
        });
    };
    
    public void tambahKeranjang(String menu,int harga,int qty){
        DefaultTableModel model=
                (DefaultTableModel)tableKeranjang.getModel();

        model.addRow(new Object[]{
            menu,
            Rupiah(harga),
            qty,
            Rupiah(harga * qty)

        });

        hitungTotal();
    }
    
    private String Rupiah(int angka) {
        return "Rp " + String.format("%,d", angka).replace(',', '.');
    }
    
    private void hitungTotal() {
        DefaultTableModel model = (DefaultTableModel) tableKeranjang.getModel();

        int total = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            String subtotal = model.getValueAt(i, 3).toString();
            subtotal = subtotal.replace("Rp", "")
                    .replace(".", "")
                    .trim();

            total += Integer.parseInt(subtotal);
        }

        lblTotal.setText("Total : " + Rupiah(total));
    }
    private int insertReservasi() {

        int idReservasi = -1;

        try {

            Connection conn = Koneksi.getConnection();

            DefaultTableModel model
                    = (DefaultTableModel) tableKeranjang.getModel();

            int total = 0;

            for (int i = 0; i < model.getRowCount(); i++) {

                String subtotal = model.getValueAt(i, 3).toString();

                subtotal = subtotal.replace("Rp", "")
                        .replace(".", "")
                        .trim();

                total += Integer.parseInt(subtotal);

            }

            String sql = "INSERT INTO reservasi "
                    + "(tanggal_reservasi,jumlah_tamu,status_reservasi,"
                    + "user_iduser,meja_idmeja,jam_reservasi,total_harga)"
                    + " VALUES (NOW(),?,?,?, ?,NOW(),?)";

            PreparedStatement ps
                    = conn.prepareStatement(sql,
                            PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, 1);                 // jumlah tamu
            ps.setString(2, "pending");      // status
            ps.setInt(3, 1);                 // id user
            ps.setInt(4, 1);                 // id meja
            ps.setDouble(5, total);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                idReservasi = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return idReservasi;
    }
    private void insertDetailPesanan(int idReservasi) {

        try {

            Connection conn = Koneksi.getConnection();

            DefaultTableModel model
                    = (DefaultTableModel) tableKeranjang.getModel();

            String cariMenu
                    = "SELECT idMenu FROM menu WHERE nama=?";

            String insert
                    = "INSERT INTO pemesanan_makanan "
                    + "(reservasi_idreservasi,Menu_idMenu,jumlah)"
                    + " VALUES (?,?,?)";

            for (int i = 0; i < model.getRowCount(); i++) {

                String namaMenu
                        = model.getValueAt(i, 0).toString();

                int qty
                        = Integer.parseInt(
                                model.getValueAt(i, 2).toString());

                PreparedStatement psCari
                        = conn.prepareStatement(cariMenu);

                psCari.setString(1, namaMenu);

                ResultSet rs = psCari.executeQuery();

                if (rs.next()) {

                    int idMenu = rs.getInt("idMenu");

                    PreparedStatement psInsert
                            = conn.prepareStatement(insert);

                    psInsert.setInt(1, idReservasi);
                    psInsert.setInt(2, idMenu);
                    psInsert.setInt(3, qty);

                    psInsert.executeUpdate();

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableKeranjang = new javax.swing.JTable();
        btnCheckout = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblWaktu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableKeranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableKeranjang);

        btnCheckout.setText("Checkout");
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });

        lblTotal.setText("Total : 0");

        jLabel1.setText("Nama Pemesan:");

        lblNama.setText("lblNama");

        jLabel3.setText("Waktu:");

        lblWaktu.setText("lblWaktu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCheckout)
                .addGap(35, 35, 35))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(73, 73, 73)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblWaktu)
                            .addComponent(lblNama))))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNama))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblWaktu))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnCheckout))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblTotal)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model
                = (DefaultTableModel) tableKeranjang.getModel();

        if (model.getRowCount() == 0) {

            javax.swing.JOptionPane.showMessageDialog(this,
                    "Keranjang masih kosong!");

            return;

        }

        int konfirmasi = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin melakukan checkout?",
                "Konfirmasi",
                javax.swing.JOptionPane.YES_NO_OPTION);

        if (konfirmasi != javax.swing.JOptionPane.YES_OPTION) {

            return;

        }

        int idReservasi = insertReservasi();

        if (idReservasi != -1) {

            insertDetailPesanan(idReservasi);

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Checkout berhasil!");

            dispose();

        } else {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Checkout gagal!");

        }
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Checkout berhasil!");

        dispose();
    }//GEN-LAST:event_btnCheckoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FoodOrdering.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FoodOrdering.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FoodOrdering.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FoodOrdering.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FoodOrdering("Admin","00:00").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblWaktu;
    private javax.swing.JTable tableKeranjang;
    // End of variables declaration//GEN-END:variables
}
