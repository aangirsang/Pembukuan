package com.aan.girsang.pembukuan.UI.pemasukan.pesanan;

import com.aan.girsang.pembukuan.Pembukuan;
import com.aan.girsang.pembukuan.model.pemasukan.Pesanan;
import com.aan.girsang.pembukuan.model.pengeluaran.Belanja;
import com.aan.girsang.pembukuan.util.BigDecimalRenderer;
import com.aan.girsang.pembukuan.util.DateRenderer;
import com.aan.girsang.pembukuan.util.IntegerRenderer;
import com.aan.girsang.pembukuan.util.Notifikasi;
import com.aan.girsang.pembukuan.util.UkuranTabel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

public final class PanelPesanan extends javax.swing.JPanel {

    Pesanan pesanan = new Pesanan();
    List<Pesanan> daftarPesanan;
    private String idSelect;
    private JPopupMenu popup = new JPopupMenu();
    //<editor-fold defaultstate="collapsed" desc="Tabbed">
    int IndexTab = 0;
    int aktifPanel = 0;
    public int getIndexTab() {
        return IndexTab;
    }
    public void setIndexTab(int IndexTab) {
        this.IndexTab = IndexTab;
    }
    public int getAktifPanel() {
        return aktifPanel;
    }
    public void setAktifPanel(int aktifPanel) {
        this.aktifPanel = aktifPanel;
    }
    public JButton getBtnTutup(){
        return toolbar.getBtnKeluar();
    }
//</editor-fold>
    public PanelPesanan() {
        initComponents();
        InitListener();
        tampilData();
        addPopupmenu();
        tabel.setDefaultRenderer(BigDecimal.class, new BigDecimalRenderer());
        tabel.setDefaultRenderer(Integer.class, new IntegerRenderer());
        tabel.setDefaultRenderer(Date.class, new DateRenderer());
        toolbar.getTxtCari().setVisible(false);
    }
    public void cariPesanan(){
        if (!idSelect.equals("")) {
            pesanan = new Pesanan();
            pesanan = Pembukuan.getPemasukanService().cariPesananID(idSelect);
        } else {
            pesanan = null;
        }
    }
    public void tampilData(){
        daftarPesanan = Pembukuan.getPemasukanService().semuaPesanan();
        tabel.setModel(new TabelModelPesanan(daftarPesanan));
        tabel = new UkuranTabel().UkuranTabelPesanan(tabel);
        tabel.clearSelection();
        
        toolbar.getTxtCari().setText("");
        pesanan = null;
        lblJumlah.setText(daftarPesanan.size()+" Data Pesanan     ");
    }
    public void tambahPesanan(){
        pesanan = null;
        Pesanan p = new DialogPesanan().showDialog(pesanan);

        if(p!=null){
            p.setFaktur("");
            Belanja b = new Belanja();
            b.setPesanan(p);
            p.setBelanja(b);
            Pembukuan.getPemasukanService().simpan(p);
            tampilData();
        }
    }
    public void editPesanan(){
        cariPesanan();
        if(pesanan==null){
            Notifikasi.pesanTidakAdaData();
        }else{
            Pesanan p = new DialogPesanan().showDialog(pesanan);
            if(p!=null){
                Belanja b = p.getBelanja();
                if(b==null){
                    b = new Belanja();
                }
                p.setBelanja(b);
                Pembukuan.getPemasukanService().simpan(p);
                tampilData();
            }
        }
    }
    public void hapusPesanan(){
        cariPesanan();
        if(pesanan==null){
            Notifikasi.pesanTidakAdaData();
        }else{
            if(Notifikasi.pesanValidasiHapus()){
                Pembukuan.getPemasukanService().hapus(pesanan);
                tampilData();
            }
        }
    }
    private void addPopupmenu(){
        popup.add(new JMenuItem(new AbstractAction("Tambah Pesanan") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tambahPesanan();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Edit Pesanan") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                editPesanan();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Hapus Pesanan") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hapusPesanan();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Refresh Tabel") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tampilData();
            }
        }));
    }
    private void InitListener(){
        tabel.getSelectionModel().addListSelectionListener((lse) -> {
            if(tabel.getSelectedRow() >=0){
                idSelect = tabel.getValueAt(tabel.getSelectedRow(), 0)
                                .toString();
            }
        });
        tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());

                    if (!source.isRowSelected(row)) {
                        source.changeSelection(row, column, false, false);
                    }

                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    editPesanan();
                }
            }
        });
        toolbar.getBtnBaru().addActionListener((ae)->{
            tambahPesanan();
        });
        toolbar.getBtnEdit().addActionListener((ae) -> {
            editPesanan();
        });
        toolbar.getBtnHapus().addActionListener((ae) -> {
            hapusPesanan();
        });
        toolbar.getBtnRefresh().addActionListener((ae) -> {
            tampilData();
        });
        
    }
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        toolbar = new com.aangirsang.girsang.toko.toolbar.ToolbarTanpaFilter();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        lblJumlah = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Partnership-icon-64.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setText("Daftar Pesanan");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        jLabel3.setText("Disini anda bisa menambah, mengedit atau menghapus data pesanan");

        toolbar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabel);

        lblJumlah.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblJumlah.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJumlah)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblJumlah)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JTable tabel;
    private com.aangirsang.girsang.toko.toolbar.ToolbarTanpaFilter toolbar;
    // End of variables declaration//GEN-END:variables
}
