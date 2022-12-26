package com.aan.girsang.pembukuan.UI.Master.pelanggan;

import com.aan.girsang.pembukuan.Pembukuan;
import com.aan.girsang.pembukuan.UI.FrameUtama;
import com.aan.girsang.pembukuan.model.master.Pelanggan;
import com.aan.girsang.pembukuan.util.Notifikasi;
import com.aan.girsang.pembukuan.util.PesanJO;
import com.aan.girsang.pembukuan.util.UkuranTabel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

public class DialogCariPelanggan extends javax.swing.JDialog {

    private Pelanggan pelanggan = new Pelanggan();
    private List<Pelanggan> daftarPelanggan;
    private String idSelect;
    private JPopupMenu popup = new JPopupMenu();
    
    public DialogCariPelanggan() {
        super(FrameUtama.getInstance(), true);
        initComponents();
        initListener();
        tampilData();
        addPopupmenu();
        toolbar.getTxtCari().setToolTipText("Cari Nama Pelanggan");
    }
    public Pelanggan showDialog(){
        setTitle(PesanJO.namaDialog.PELANGGAN_PILIH);
        
        setLocationRelativeTo(null);
        setVisible(true);
        return pelanggan;
    }
    private void cariPelanggan(){
        if (!idSelect.equals("")) {
            pelanggan = new Pelanggan();
            pelanggan = Pembukuan.getMasterService().cariIdPelanggan(idSelect);
        } else {
            pelanggan = null;
        }
    }
    private void tampilData(){
        toolbar.getTxtCari().setText("");
        daftarPelanggan = Pembukuan.getMasterService().semuaPelanggan();
        tabel.setModel(new TabelModelPelanggan(daftarPelanggan));
        tabel = new UkuranTabel().UkuranTabelPelanggan(tabel);
        tabel.clearSelection();
        
        pelanggan = null;
        
    }
    private void loadFormToModel(Pelanggan p){
        pelanggan.setId(p.getId());
        pelanggan.setNamaLengkap(p.getNamaLengkap());
        pelanggan.setHp(p.getHp());
        pelanggan.setAlamat(p.getAlamat());
        pelanggan.setSosmed(p.getSosmed());
    }
    private void pilihPelanggan(){
        cariPelanggan();
        if(pelanggan==null){
            Notifikasi.pesanTidakAdaData();
        }else{
            dispose();
        }
    }
    private void tambahPelanggan(){
        pelanggan = null;
        Pelanggan p = new DialogPelanggan().showDialog(pelanggan);
        if(p!=null){
            pelanggan = new Pelanggan();
            loadFormToModel(p);
            Pembukuan.getMasterService().simpan(pelanggan);
            tampilData();
        }
        tampilData();
    }
    private void editPelanggan(){
        cariPelanggan();
        if(pelanggan==null){
            Notifikasi.pesanTidakAdaData();
        }else{
            Pelanggan p = new DialogPelanggan().showDialog(pelanggan);
            if(p!=null){
                loadFormToModel(p);
                Pembukuan.getMasterService().simpan(pelanggan);
                tampilData();
            }
        }
    }
    private void hapusPelanggan(){
        cariPelanggan();
        if(pelanggan!=null){
            if(Notifikasi.pesanValidasiHapus()){
                Pembukuan.getMasterService().hapus(pelanggan);
                tampilData();
            }
        }else{
            Notifikasi.pesanTidakAdaData();
        }
    }
    private void addPopupmenu(){
        popup.add(new JMenuItem(new AbstractAction("Pilih Pelanggan") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pilihPelanggan();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Tambah Pelanggan") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tambahPelanggan();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Edit Pelanggan") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                editPelanggan();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Hapus Pelanggan") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hapusPelanggan();
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
                    pilihPelanggan();
                }
            }
        });
        toolbar.getBtnBaru().addActionListener((ae) -> {
            tambahPelanggan();
        });
        toolbar.getBtnEdit().addActionListener((ae) -> {
            editPelanggan();
        });
        toolbar.getBtnHapus().addActionListener((ae) -> {
            hapusPelanggan();
        });
        toolbar.getBtnRefresh().addActionListener((ae) -> {
            tampilData();
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
                if("".equals(toolbar.getTxtCari().getText())){
                    tampilData();
                }else{
                    daftarPelanggan = Pembukuan.getMasterService()
                            .cariNamaPelanggan(toolbar.getTxtCari().getText());
                    tabel.setModel(new TabelModelPelanggan(daftarPelanggan));
                    tabel = new UkuranTabel().UkuranTabelPelanggan(tabel);
                }
            }
        });
        toolbar.getBtnPilih().addActionListener((ae)->{
            pilihPelanggan();
        });
        toolbar.getBtnKeluar().addActionListener((ae)->{
            pelanggan = null;
            dispose();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbar = new com.aangirsang.girsang.toko.toolbar.ToolBarSelect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel;
    private com.aangirsang.girsang.toko.toolbar.ToolBarSelect toolbar;
    // End of variables declaration//GEN-END:variables
}
