package com.aan.girsang.pembukuan.UI.Master.menu;

import com.aan.girsang.pembukuan.Pembukuan;
import com.aan.girsang.pembukuan.UI.FrameUtama;
import com.aan.girsang.pembukuan.model.master.Menu;
import com.aan.girsang.pembukuan.util.BigDecimalRenderer;
import com.aan.girsang.pembukuan.util.IntegerRenderer;
import com.aan.girsang.pembukuan.util.Notifikasi;
import com.aan.girsang.pembukuan.util.PesanJO;
import com.aan.girsang.pembukuan.util.UkuranTabel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

public class DialogCariMenu extends javax.swing.JDialog {

    private Menu menu = new Menu();
    private List<Menu> daftarMenu;
    private String idSelect;
    private JPopupMenu popup = new JPopupMenu();

    public DialogCariMenu() {
        super(FrameUtama.getInstance(), true);
        initComponents();
        initListener();
        addPopupmenu();
        tabel.setDefaultRenderer(BigDecimal.class, new BigDecimalRenderer());
        tabel.setDefaultRenderer(Integer.class, new IntegerRenderer());
        tampilMenu();
        toolbar.getTxtCari().setToolTipText("Cari Nama Menu");
    }
    public Menu showDialog(){
        setTitle(PesanJO.namaDialog.MENU_PILIH);
        
        setLocationRelativeTo(null);
        setVisible(true);
        
        return menu;
    }
    private void cariMenu() {
        if (!idSelect.equals("")) {
            menu = new Menu();
            menu = Pembukuan.getMasterService().cariMenu(idSelect);
        } else {
            menu = null;
        }
    }
    
    private void tampilMenu(){
        toolbar.getTxtCari().setText("");
        daftarMenu = Pembukuan.getMasterService().semuaMenu();
        tabel.setModel(new TabelModelMenu(daftarMenu));

        tabel = new UkuranTabel().UkuranTabelMenu(tabel);
        
        tabel.clearSelection();
        menu = null;
        idSelect = "";
    }
    private void loadFormToModel(Menu m){
        menu.setId(m.getId());
        menu.setNamaMenu(m.getNamaMenu());
        menu.setHarga(m.getHarga());
    }
    private void menuBaru(){
        menu = null;
            Menu m = new DialogMenu().showDialog(menu);
            if (m != null) {
                menu = new Menu();
                loadFormToModel(m);
                List<Menu> menus = Pembukuan.getMasterService().semuaMenu();
                boolean data = false;
                for (int i = 0; i < menus.size(); i++) {
                    if (menus.get(i).getNamaMenu()
                            .equals(menu.getNamaMenu())) {
                        data = true;
                        tabel.setRowSelectionInterval(i, i);
                    }
                }
                if (data == true) {
                    Notifikasi.pesanDataSudahAda();
                } else {
                    Pembukuan.getMasterService().simpan(menu);
                    tampilMenu();
                }
            }
            tampilMenu();
    }
    private void menuEdit(){
        cariMenu();
            if (menu != null) {
                Menu m = new DialogMenu().showDialog(menu);
                if (m != null) {
                    menu = new Menu();
                    loadFormToModel(m);
                    List<Menu> menus = Pembukuan.getMasterService().semuaMenu();
                    boolean data = false;
                    for (int i = 0; i < menus.size(); i++) {
                        if (menus.get(i).getNamaMenu()
                                .equals(menu.getNamaMenu())
                                && menus.get(i).getId()
                                != menu.getId()) {
                            data = true;
                            tabel.setRowSelectionInterval(i, i);
                        }
                    }
                    if (data == true) {
                        Notifikasi.pesanDataSudahAda();
                    } else {
                        Pembukuan.getMasterService().simpan(menu);
                        tampilMenu();
                    }
                }
            } else {
                Notifikasi.pesanTidakAdaData();
            }
    }
    private void menuHapus(){
        cariMenu();
            if (menu != null) {
                if (Notifikasi.pesanValidasiHapus()) {
                    Pembukuan.getMasterService().hapus(menu);
                    tampilMenu();
                }
            } else {
                Notifikasi.pesanTidakAdaData();
            }
    }
    private void addPopupmenu(){
        popup.add(new JMenuItem(new AbstractAction("Pilih Menu") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cariMenu();
                dispose();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Tambah Baru") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                menuBaru();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Edit Menu") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                menuEdit();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Hapus Menu") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                menuHapus();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Refresh Tabel") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tampilMenu();
            }
        }));
    }
    private void initListener(){
        tabel.getSelectionModel().addListSelectionListener((lse) -> {
            if(tabel.getSelectedRow() >=0){
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
                    cariMenu();
                    dispose();
                }
            }
        });
        toolbar.getBtnBaru().addActionListener(((ae) -> {
            menuBaru();
        }));
        toolbar.getBtnEdit().addActionListener((ae) -> {
            menuEdit();
        });
        toolbar.getBtnHapus().addActionListener((ae) -> {
            menuHapus();
        });
        toolbar.getBtnRefresh().addActionListener((ae) -> {
            tampilMenu();
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
                    tampilMenu();
                }else{
                    daftarMenu = Pembukuan.getMasterService()
                            .cariNamaMenu(toolbar.getTxtCari().getText());
                    tabel.setModel(new TabelModelMenu(daftarMenu));
                    tabel = new UkuranTabel().UkuranTabelMenu(tabel);
                }
            }
        });
        toolbar.getBtnPilih().addActionListener((al)->{
            cariMenu();
            if(menu==null){
                Notifikasi.pesanTidakAdaData();
            }else{
                dispose();
            }
        });
        toolbar.getBtnKeluar().addActionListener((al)->{
            menu = null;
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
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
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
