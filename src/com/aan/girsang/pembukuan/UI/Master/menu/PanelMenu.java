package com.aan.girsang.pembukuan.UI.Master.menu;

import com.aan.girsang.pembukuan.Pembukuan;
import com.aan.girsang.pembukuan.model.master.Menu;
import com.aan.girsang.pembukuan.util.BigDecimalRenderer;
import com.aan.girsang.pembukuan.util.IntegerRenderer;
import com.aan.girsang.pembukuan.util.Notifikasi;
import com.aan.girsang.pembukuan.util.UkuranTabel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

public class PanelMenu extends javax.swing.JPanel {

    private Menu menu = new Menu();
    private List<Menu> daftarMenu;
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

    public JButton getBtnTutup() {
        return toolbar.getBtnKeluar();
    }
//</editor-fold>

    public PanelMenu() {
        initComponents();
        initListener();
        tabel.setDefaultRenderer(BigDecimal.class, new BigDecimalRenderer());
        tabel.setDefaultRenderer(Integer.class, new IntegerRenderer());
        tampilMenu();
        toolbar.getTxtCari().setToolTipText("Cari Nama Menu");
        addPopupmenu();
    }

    private void tampilMenu() {
        toolbar.getTxtCari().setText("");
        daftarMenu = Pembukuan.getMasterService().semuaMenu();
        tabel.setModel(new TabelModelMenu(daftarMenu));

        tabel = new UkuranTabel().UkuranTabelMenu(tabel);

        tabel.clearSelection();
        idSelect = "";
        jLabel4.setText(daftarMenu.size() + " Data Menu  ");
    }
    private void cariMenu() {
        if (!idSelect.equals("")) {
            menu = new Menu();
            menu = Pembukuan.getMasterService().cariMenu(idSelect);
        } else {
            menu = null;
        }
    }
    private void loadFormToModel(Menu m) {
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
    private void initListener() {
        tabel.getSelectionModel().addListSelectionListener((lse) -> {
            if (tabel.getSelectedRow() >= 0) {
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
                    menuEdit();
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
                if ("".equals(toolbar.getTxtCari().getText())) {
                    tampilMenu();
                } else {
                    daftarMenu = Pembukuan.getMasterService()
                            .cariNamaMenu(toolbar.getTxtCari().getText());
                    tabel.setModel(new TabelModelMenu(daftarMenu));
                    tabel = new UkuranTabel().UkuranTabelMenu(tabel);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        toolbar = new com.aangirsang.girsang.toko.toolbar.ToolbarTanpaFilter();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/GolonganBarang 63X63.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setText("Daftar Menu");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        jLabel3.setText("Disini anda bisa menambah, mengedit atau menghapus data menu");

        toolbar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabel);

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel;
    private com.aangirsang.girsang.toko.toolbar.ToolbarTanpaFilter toolbar;
    // End of variables declaration//GEN-END:variables
}
