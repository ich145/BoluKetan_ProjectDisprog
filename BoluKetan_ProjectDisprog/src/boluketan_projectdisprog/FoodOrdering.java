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
import javax.swing.JOptionPane;
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

    private void loadKategoriWS() {
        jComboBoxKategori.removeAllItems();
        jComboBoxKategori.addItem("Semua Kategori");
        jComboBoxKategori.addItem("makanan");
        jComboBoxKategori.addItem("minuman");
    }
    
    public FoodOrdering(String namaPemesan, String waktuReservasi) {

        initComponents();

        loadKategoriWS();

        // 1. Inisialisasi Struktur Kolom Tabel Menu (Tabel Atas)
        DefaultTableModel modelMenu = new DefaultTableModel(
                new Object[]{"Menu", "Harga", "Informasi"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tableKeranjang.setModel(modelMenu); // tableKeranjang bertindak sebagai Katalog Menu

        // 2. Inisialisasi Struktur Kolom Tabel Keranjang (Tabel Bawah)
        DefaultTableModel modelKeranjang = new DefaultTableModel(
                new Object[]{"Menu", "Harga", "Qty", "Subtotal"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tableKeranjang1.setModel(modelKeranjang); // tableKeranjang1 bertindak sebagai Keranjang Belanja

        // 3. Muat Data Menu dari Web Service ke Tabel Menu
        loadMenu();

    }
    
    public void tambahKeranjang(String menu, int harga) {
        DefaultTableModel model = (DefaultTableModel) tableKeranjang1.getModel();
        int barisDitemukan = -1;

        // Cek apakah menu tersebut sudah pernah dimasukkan ke keranjang sebelumnya
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equals(menu)) {
                barisDitemukan = i;
                break;
            }
        }

        if (barisDitemukan != -1) {
            // Jika menu sudah ada di keranjang, akumulasikan Qty (+1)
            int qtyLama = Integer.parseInt(model.getValueAt(barisDitemukan, 2).toString());
            int qtyBaru = qtyLama + 1;
            int subtotalBaru = harga * qtyBaru;

            model.setValueAt(qtyBaru, barisDitemukan, 2);
            model.setValueAt(Rupiah(subtotalBaru), barisDitemukan, 3);
        } else {
            // Jika menu belum ada di keranjang, buat baris baru (Qty awal = 1)
            model.addRow(new Object[]{
                menu,
                Rupiah(harga),
                1,
                Rupiah(harga)
            });
        }

        hitungTotal();
    }
    
    private String Rupiah(int angka) {
        return "Rp " + String.format("%,d", angka).replace(',', '.');
    }
    
    private void hitungTotal() {
        DefaultTableModel model = (DefaultTableModel) tableKeranjang1.getModel();

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

    private void loadMenu() {
        DefaultTableModel model = (DefaultTableModel) tableKeranjang.getModel();
        model.setRowCount(0);

        try {
            MenuWSService service = new MenuWSService();
            MenuWS port = service.getMenuWSPort();
            java.util.List<boluketan_projectdisprog.Menu> daftar = port.lihatMenu();

            if (daftar != null) {
                for (boluketan_projectdisprog.Menu m : daftar) {
                    model.addRow(new Object[]{
                        m.getNama(),
                        (int) m.getHarga(),
                        m.getInformasi() // <-- Tambahkan ini untuk menampilkan data dari database/WS
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data menu: " + e.getMessage());
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
        lblWaktu = new javax.swing.JLabel();
        jComboBoxKategori = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableKeranjang1 = new javax.swing.JTable();
        lblWaktu2 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnSub = new javax.swing.JButton();

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

        lblWaktu.setText("Menu");

        jComboBoxKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tableKeranjang1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableKeranjang1);

        lblWaktu2.setText("Keranjang");

        btnAdd.setText("+");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSub.setText("-");
        btnSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCheckout))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblWaktu2)
                            .addComponent(lblWaktu)
                            .addComponent(jComboBoxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSub)
                            .addComponent(btnAdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jComboBoxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblWaktu)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btnAdd)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblWaktu2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSub)
                        .addGap(81, 81, 81)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(btnCheckout))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model
                = (DefaultTableModel) tableKeranjang1.getModel();

        if (model.getRowCount() == 0) {

            JOptionPane.showMessageDialog(this, "Keranjang kosong!");

            return;

        }

        try {

            // Hitung total
            double total = 0;

            for (int i = 0; i < model.getRowCount(); i++) {

                String subtotal = model.getValueAt(i, 3).toString()
                        .replace("Rp", "")
                        .replace(".", "")
                        .trim();

                total += Double.parseDouble(subtotal);

            }

            ReservasiWSService service = new ReservasiWSService();

            ReservasiWS port = service.getReservasiWSPort();

            // Simpan reservasi
            int idReservasi = port.tambahReservasi(
                    "2026-07-13 18:00:00",
                    1,
                    "pending",
                    1,
                    1,
                    "2026-07-13 18:00:00",
                    total
            );

            if (idReservasi <= 0) {

                JOptionPane.showMessageDialog(this, "Gagal membuat reservasi!");

                return;

            }

            // Simpan semua makanan
            for (int i = 0; i < model.getRowCount(); i++) {

                String namaMenu = model.getValueAt(i, 0).toString();

                int qty = Integer.parseInt(model.getValueAt(i, 2).toString());

                port.simpanPesananMakanan(
                        idReservasi,
                        namaMenu,
                        qty,
                        "pending"
                );

            }

            JOptionPane.showMessageDialog(this,
                    "Checkout berhasil!\nID Reservasi : " + idReservasi);

            dispose();

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(this,
                    "Checkout gagal : " + e.getMessage());

        }
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        int rowTerpilih = tableKeranjang.getSelectedRow();

        if (rowTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih menu dari tabel atas terlebih dahulu!");
            return;
        }

        // Indeks 0 = Menu, Indeks 1 = Harga, Indeks 2 = Informasi
        String namaMenu = tableKeranjang.getValueAt(rowTerpilih, 0).toString();
        int hargaMenu = Integer.parseInt(tableKeranjang.getValueAt(rowTerpilih, 1).toString());

        tambahKeranjang(namaMenu, hargaMenu);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubActionPerformed
        // TODO add your handling code here:
        int rowTerpilih = tableKeranjang1.getSelectedRow();

        if (rowTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih item di keranjang (tabel bawah) yang ingin dikurangi!");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tableKeranjang1.getModel();
        int qtyLama = Integer.parseInt(model.getValueAt(rowTerpilih, 2).toString());

        // Ambil nilai harga mentah dengan membersihkan format Rupiah
        String hargaStr = model.getValueAt(rowTerpilih, 1).toString()
                .replace("Rp", "").replace(".", "").trim();
        int harga = Integer.parseInt(hargaStr);

        if (qtyLama > 1) {
            // Kurangi qty sebanyak 1
            int qtyBaru = qtyLama - 1;
            model.setValueAt(qtyBaru, rowTerpilih, 2);
            model.setValueAt(Rupiah(harga * qtyBaru), rowTerpilih, 3);
        } else {
            // Jika Qty tinggal 1 dan dikurangi lagi, hapus baris dari keranjang
            model.removeRow(rowTerpilih);
        }

        hitungTotal();
    }//GEN-LAST:event_btnSubActionPerformed

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
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnSub;
    private javax.swing.JComboBox<String> jComboBoxKategori;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblWaktu;
    private javax.swing.JLabel lblWaktu2;
    private javax.swing.JTable tableKeranjang;
    private javax.swing.JTable tableKeranjang1;
    // End of variables declaration//GEN-END:variables
}
