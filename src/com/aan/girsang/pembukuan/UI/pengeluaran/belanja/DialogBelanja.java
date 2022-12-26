package com.aan.girsang.pembukuan.UI.pengeluaran.belanja;

import com.aan.girsang.pembukuan.Pembukuan;
import com.aan.girsang.pembukuan.UI.FrameUtama;
import com.aan.girsang.pembukuan.UI.Master.item.DialogPilihItem;
import com.aan.girsang.pembukuan.model.pengeluaran.Belanja;
import com.aan.girsang.pembukuan.model.pengeluaran.BelanjaDetail;
import com.aan.girsang.pembukuan.model.runingnumber.RunningNumberEnum;
import com.aan.girsang.pembukuan.util.BigDecimalRenderer;
import com.aan.girsang.pembukuan.util.IntegerRenderer;
import com.aan.girsang.pembukuan.util.Notifikasi;
import com.aan.girsang.pembukuan.util.TextComponentUtils;
import com.aan.girsang.pembukuan.util.UkuranTabel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

public class DialogBelanja extends javax.swing.JDialog {

    Belanja belanja = new Belanja();
    BelanjaDetail detail = new BelanjaDetail();
    List<BelanjaDetail> daftarDetail = new ArrayList<>();
    private JPopupMenu popup = new JPopupMenu();
    public DialogBelanja() {
        super(FrameUtama.getInstance(), true);
        initComponents();
        initListener();
        addPopupmenu();
        tabel.setDefaultRenderer(BigDecimal.class, new BigDecimalRenderer());
        tabel.setDefaultRenderer(Integer.class, new IntegerRenderer());
        TextComponentUtils.setAutoUpperCaseText(txtItem);
        TextComponentUtils.setCurrency(txtTotal);
        TextComponentUtils.setCurrency(txtSubTotal);
    }
    public Belanja showDialog(Belanja b){
        if(b!=null){
            clearAll();
            belanja = new Belanja();
            belanja = b;
            txtFaktur.setText(belanja.getFaktur());
            jdcTanggal.setDate(belanja.getTanggal());
            txtTotal.setText(TextComponentUtils.formatNumber(belanja.getTotal()));
            daftarDetail = belanja.getDaftarDetail();
        }else{
            clearAll();
            daftarDetail = new ArrayList<>();
        }
        tampilData();
        
        setLocationRelativeTo(null);
        setVisible(true);
        return belanja;
    }
    private void clearItem(){
        txtItem.setText("");
        txtSubTotal.setText("0");
    }
    private void clearAll(){
        clearItem();
        txtFaktur.setText(Pembukuan.getMasterService().ambilBerikutnya(RunningNumberEnum.BELANJA));
        txtTotal.setText("0");
        jdcTanggal.setDate(null);
        jdcTanggal.setDateFormatString("dd-MM-yy  HH:mm");
    }
    private void tampilData(){
        tabel.setModel(new TabelModelBelanjaDetail(daftarDetail));
        tabel = new UkuranTabel().UkuranTabelBelanjaDetail(tabel);
        
        detail = null;
        txtTotal.setText(TextComponentUtils.formatNumber(totalDetail(daftarDetail)));
    }
    private void addItem(){
        if(txtItem.getText().equals("")){
            Notifikasi.pesanValidasiSimpan();
            txtItem.requestFocus();
        }else{
            boolean data = false;
            detail = new BelanjaDetail();
            detail.setBelanja(belanja);
            detail.setItem(txtItem.getText());
            detail.setSubTotal(TextComponentUtils.parseNumberToBigDecimal(txtSubTotal.getText()));
            if(daftarDetail!=null){
                for(int i=0;i<daftarDetail.size();i++){
                    if(daftarDetail.get(i).getItem()
                            .equals(detail.getItem())){
                        data = true;
                        Notifikasi.pesanDataSudahAda();
                        tabel.setRowSelectionInterval(i, i);
                    }
                }
            }
            if(data==false){
                daftarDetail.add(detail);
                tabel.setModel(new TabelModelBelanjaDetail(daftarDetail));
                tabel = new UkuranTabel().UkuranTabelBelanjaDetail(tabel);
                clearItem();
            }
            detail = null;
            txtTotal.setText(TextComponentUtils.formatNumber(totalDetail(daftarDetail)));
        }
    }
    private void addPopupmenu(){
        popup.add(new JMenuItem(new AbstractAction("Hapus Data") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(detail!=null){
                    daftarDetail.remove(detail);
                    tampilData();
                }
            }
        }));
    }
    
    private void loadFormToModel(Belanja b){
        b.setId(b.getId());
        b.setFaktur(txtFaktur.getText());
        b.setTanggal(jdcTanggal.getDate());
        b.setTotal(TextComponentUtils.parseNumberToBigDecimal(txtTotal.getText()));
        b.setDaftarDetail(daftarDetail);
    }
    private boolean validasiSimpan(){
        if(jdcTanggal.getDate()==null){
            Notifikasi.pesanValidasiSimpan();
            jdcTanggal.requestFocus();
            return false;
        }
        if(txtTotal.getText().equals("0")){
            Notifikasi.pesanValidasiSimpan();
            txtSubTotal.requestFocus();
            return false;
        }
        return true;
    }
    private BigDecimal totalDetail(List<BelanjaDetail> list){
        BigDecimal total = new BigDecimal(0);
        
        if(!list.isEmpty()){
            for(int i=0;i<list.size();i++){
                total = total.add(list.get(i).getSubTotal());
            }
        }
        return total;
    }
    private void initListener(){
        //<editor-fold defaultstate="collapsed" desc="close Windows">
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                belanja = null;
                dispose();
            }
        });//</editor-fold>
        tabel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                txtTotal.setText("Rp. "+TextComponentUtils.formatNumber(totalDetail(daftarDetail)));
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                txtTotal.setText("Rp. "+TextComponentUtils.formatNumber(totalDetail(daftarDetail)));
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                txtTotal.setText("Rp. "+TextComponentUtils.formatNumber(totalDetail(daftarDetail)));
            }
        });
        tabel.getSelectionModel().addListSelectionListener((lse) -> {
            if(tabel.getSelectedRow() >=0){
                detail = new BelanjaDetail();
                detail = daftarDetail.get(tabel.getSelectedRow());
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
        });
        btnCariItem.addActionListener((ae)->{
            String item = new DialogPilihItem().showDialog();
            txtItem.setText(item);
            txtItem.requestFocus();
        });
        btnInputMenu.addActionListener((ae)-> {
            addItem();
        });
        btnBatal.addActionListener((ae)-> {
            belanja = null;
            dispose();
        });
        btnSimpan.addActionListener((ae)-> {
            if(validasiSimpan()){
                if(belanja==null){
                    belanja = new Belanja();
                }
                loadFormToModel(belanja);
                dispose();
            }
        });
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jdcTanggal = new com.toedter.calendar.JDateChooser();
        txtFaktur = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtItem = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnCariItem = new javax.swing.JButton();
        btnInputMenu = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Faktur");

        jLabel2.setText("Tanggal");

        txtFaktur.setText("jTextField1");

        txtTotal.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setText("jTextField2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(txtFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(jdcTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Nama Item");

        txtItem.setText("jTextField3");

        txtSubTotal.setText("jTextField5");

        jLabel9.setText("Sub Total");

        btnCariItem.setText("Cari Menu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(btnCariItem, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(btnCariItem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnInputMenu.setText("Input Menu");

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabel);

        btnSimpan.setText("Simpan");

        btnBatal.setText("Batal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatal))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnInputMenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBatal, btnSimpan});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInputMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatal)
                    .addComponent(btnSimpan))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCariItem;
    private javax.swing.JButton btnInputMenu;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcTanggal;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField txtFaktur;
    private javax.swing.JTextField txtItem;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
