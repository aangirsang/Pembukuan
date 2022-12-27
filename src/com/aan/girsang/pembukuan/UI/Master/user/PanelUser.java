package com.aan.girsang.pembukuan.UI.Master.user;

import com.aan.girsang.pembukuan.UI.Master.item.*;
import com.aan.girsang.pembukuan.Pembukuan;
import com.aan.girsang.pembukuan.model.master.ItemLain;
import com.aan.girsang.pembukuan.model.master.Akun;
import com.aan.girsang.pembukuan.util.Notifikasi;
import com.aan.girsang.pembukuan.util.UkuranTabel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelUser extends javax.swing.JPanel {

    Akun user;
    List<Akun> daftarUser;
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
    public PanelUser() {
        initComponents();
        initListener();
        tampilData();
        addPopupmenu();
    }
    private void cariItem(){
        if (!idSelect.equals("")) {
            user = new Akun();
            user = Pembukuan.getMasterService().cariIdUser(idSelect);
        } else {
            user = null;
        }
    }
    private void tampilData(){
        toolbar.getTxtCari().setText("");
        daftarUser = Pembukuan.getMasterService().semuaUser();
        tabel.setModel(new TabelModelUser(daftarUser));
        tabel = new UkuranTabel().UkuranTabelUser(tabel);
        tabel.clearSelection();
        
        user = null;
        idSelect = "";
        
        lblJumlah.setText(daftarUser.size() +" Data Item   ");
    }
    private void baruItem(){
        user = null;
        Akun u = new DialogUser().showDialog(user);
        if(u!=null){
            boolean data = false;
            user = new Akun();
            user = u;
            List<Akun> is = Pembukuan.getMasterService().semuaUser();
            for(int j=0;j<is.size();j++){
                if(is.get(j).getUsername()
                        .equals(user.getUsername())&&
                        is.get(j).getId()!=user.getId()){
                    tabel.setRowSelectionInterval(j, j);
                    Notifikasi.pesanDataSudahAda();
                    data = true;
                }
            }
            if(data==false){
                Pembukuan.getMasterService().simpan(user);
                tampilData();
            }
        }
    }
    private void editItem(){
        cariItem();
        if(user==null){
                Notifikasi.pesanTidakAdaData();
            }else{
                Akun u = new DialogUser().showDialog(user);
                if(u!=null){
                    boolean data = false;
                    user = u;
                    List<Akun> is = Pembukuan.getMasterService().semuaUser();
                    for(int j=0;j<is.size();j++){
                        if(is.get(j).getUsername()
                                .equals(user.getUsername())&&
                                is.get(j).getId()!=user.getId()){
                            tabel.setRowSelectionInterval(j, j);
                            Notifikasi.pesanDataSudahAda();
                            data = true;
                        }
                    }
                    if(data==false){
                        Pembukuan.getMasterService().simpan(user);
                        tampilData();
                    }
                }
            }
    }
    private void hapusItem(){
        cariItem();
        if(user==null){
                Notifikasi.pesanTidakAdaData();
            }else{
                if(Notifikasi.pesanValidasiHapus()){
                    Pembukuan.getMasterService().hapus(user);
                    tampilData();
                }
            }
    }
    private void addPopupmenu(){
        popup.add(new JMenuItem(new AbstractAction("Tambah User") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                baruItem();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Edit User") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                editItem();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Hapus User") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hapusItem();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Refresh Tabel") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tampilData();
            }
        }));
    }
    private void initListener(){
        tabel.getSelectionModel().addListSelectionListener((ListSelectionEvent lse) -> {
            if(tabel.getSelectedRow()>=0){
                idSelect = tabel.getValueAt(tabel.getSelectedRow(), 0).toString();
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
                    editItem();
                }
            }
        });
        toolbar.getTxtCari().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if ("".equals(toolbar.getTxtCari().getText())) {
                    tampilData();
                } else {
                    daftarUser = Pembukuan.getMasterService()
                            .cariUser(toolbar.getTxtCari().getText());
                    tabel.setModel(new TabelModelUser(daftarUser));
                    tabel = new UkuranTabel().UkuranTabelUser(tabel);
                }
            }
        });
        toolbar.getBtnBaru().addActionListener((al) ->{
            baruItem();
        });
        toolbar.getBtnEdit().addActionListener((ae)->{
            editItem();
        });
        toolbar.getBtnHapus().addActionListener((al)->{
            hapusItem();
        });
        toolbar.getBtnRefresh().addActionListener((al)->{
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/GolonganBarang 63X63.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setText("Daftar Item");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        jLabel3.setText("Disini anda bisa menambah, mengedit atau menghapus data item");

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
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(lblJumlah)))
                        .addGap(0, 477, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
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
