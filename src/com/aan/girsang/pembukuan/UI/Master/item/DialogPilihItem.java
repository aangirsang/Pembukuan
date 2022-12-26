package com.aan.girsang.pembukuan.UI.Master.item;

import com.aan.girsang.pembukuan.Pembukuan;
import com.aan.girsang.pembukuan.UI.FrameUtama;
import com.aan.girsang.pembukuan.model.master.ItemLain;
import com.aan.girsang.pembukuan.util.Notifikasi;
import com.aan.girsang.pembukuan.util.TextComponentUtils;
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
import javax.swing.event.ListSelectionEvent;

public class DialogPilihItem extends javax.swing.JDialog {

    ItemLain itemLain;
    List<ItemLain> daftarItem;
    private String idSelect, itemSelect;
    private JPopupMenu popup = new JPopupMenu();
    public DialogPilihItem() {
        super(FrameUtama.getInstance(), true);
        initComponents();
        initListener();
        addPopupmenu();
        TextComponentUtils.setAutoUpperCaseText(toolbar.getTxtCari());
    }
    public String showDialog(){
        tampilData();
        setLocationRelativeTo(null);
        setVisible(true);
        return itemSelect;
    }

    private void cariItem(){
        if (!idSelect.equals("")) {
            itemLain = new ItemLain();
            itemLain = Pembukuan.getMasterService().cariItemLain(idSelect);
        } else {
            itemLain = null;
        }
    }
    private void tampilData(){
        toolbar.getTxtCari().setText("");
        daftarItem = Pembukuan.getMasterService().semuaItem();
        tabel.setModel(new TabelModelItem(daftarItem));
        tabel = new UkuranTabel().UkuranTabelItem(tabel);
        tabel.clearSelection();
        
        itemLain = null;
        idSelect = "";
        
    }
    private void pilihItem(){
        cariItem();
        if(itemLain==null){
            Notifikasi.pesanTidakAdaData();
        }else{
            itemSelect = itemLain.getNamaItem();
            dispose();
        }
    }
    private void baruItem(){
        itemLain = null;
        ItemLain i = new DialogItem().showDialog(itemLain);
        if(i!=null){
            boolean data = false;
            itemLain = new ItemLain();
            itemLain = i;
            List<ItemLain> is = Pembukuan.getMasterService().semuaItem();
            for(int j=0;j<is.size();j++){
                if(is.get(j).getNamaItem()
                        .equals(itemLain.getNamaItem())&&
                        is.get(j).getId()!=itemLain.getId()){
                    tabel.setRowSelectionInterval(j, j);
                    Notifikasi.pesanDataSudahAda();
                    data = true;
                }
            }
            if(data==false){
                Pembukuan.getMasterService().simpan(itemLain);
                tampilData();
            }
        }
    }
    private void editItem(){
        cariItem();
        if(itemLain==null){
                Notifikasi.pesanTidakAdaData();
            }else{
                ItemLain i = new DialogItem().showDialog(itemLain);
                if(i!=null){
                    boolean data = false;
                    itemLain = i;
                    List<ItemLain> is = Pembukuan.getMasterService().semuaItem();
                    for(int j=0;j<is.size();j++){
                        if(is.get(j).getNamaItem()
                                .equals(itemLain.getNamaItem())&&
                                is.get(j).getId()!=itemLain.getId()){
                            tabel.setRowSelectionInterval(j, j);
                            Notifikasi.pesanDataSudahAda();
                            data = true;
                        }
                    }
                    if(data==false){
                        Pembukuan.getMasterService().simpan(itemLain);
                        tampilData();
                    }
                }
            }
    }
    private void hapusItem(){
        cariItem();
        if(itemLain==null){
                Notifikasi.pesanTidakAdaData();
            }else{
                if(Notifikasi.pesanValidasiHapus()){
                    Pembukuan.getMasterService().hapus(itemLain);
                    tampilData();
                }
            }
    }
    private void addPopupmenu(){
        popup.add(new JMenuItem(new AbstractAction("Pilih Item") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pilihItem();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Tambah Item") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                baruItem();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Edit Item") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                editItem();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Hapus Item") {
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
                    pilihItem();
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
                    daftarItem = Pembukuan.getMasterService()
                            .cariNamaItem(toolbar.getTxtCari().getText());
                    tabel.setModel(new TabelModelItem(daftarItem));
                    tabel = new UkuranTabel().UkuranTabelItem(tabel);
                }
            }
        });
        toolbar.getBtnPilih().addActionListener((al) ->{
            pilihItem();
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
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
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
