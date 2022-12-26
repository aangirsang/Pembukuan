package com.aan.girsang.pembukuan.pribadi.UI;

import com.aan.girsang.pembukuan.UI.FrameUtama;
import com.aan.girsang.pembukuan.pribadi.model.PemasukanPribadi;
import com.aan.girsang.pembukuan.util.TextComponentUtils;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class DialogPemasukanPribadi extends javax.swing.JDialog {

    private PemasukanPribadi pribadi;
    public DialogPemasukanPribadi() {
        super(FrameUtama.getInstance(), true);
        initComponents();
        initListener();
        TextComponentUtils.setAutoUpperCaseText(txtKeterangan);
        TextComponentUtils.setCurrency(txtTotal);
    }
    public PemasukanPribadi showDialog(PemasukanPribadi p){
        clear();
        if(p==null){
            pribadi = new PemasukanPribadi();
            setTitle("Tambah Pemasukan Pribadi");
        }else{
            setTitle("Ubah Pemasukan Pribadi");
            pribadi = p;
            tampilData(pribadi);
        }
        
        
        setLocationRelativeTo(null);
        setVisible(true);
        return pribadi;
    }

    private void clear(){
        txtKeterangan.setText("");
        txtTotal.setText("0");
        jdcTanggal.setDateFormatString("dd/MM/yyyy HH:mm");
        jdcTanggal.setDate(new Date());
    }
    private void tampilData(PemasukanPribadi p){
        jdcTanggal.setDate(p.getTanggal());
        txtKeterangan.setText(p.getKeterangan());
        txtTotal.setText(TextComponentUtils.formatNumber(p.getTotal()));
    }
    private void simpan(){
        if(pribadi==null){
            pribadi = new PemasukanPribadi();
        }
        pribadi.setTanggal(jdcTanggal.getDate());
        pribadi.setKeterangan(txtKeterangan.getText());
        pribadi.setTotal(TextComponentUtils.parseNumberToBigDecimal(txtTotal.getText()));
    }
    private void initListener(){
        //<editor-fold defaultstate="collapsed" desc="close Windows">
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                pribadi = null;
                dispose();
            }
        });//</editor-fold>
        
        btnBatal.addActionListener((ae)->{
            pribadi = null;
            dispose();
        });
        btnSimpan.addActionListener((ae)->{
            simpan();
            dispose();
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jdcTanggal = new com.toedter.calendar.JDateChooser();
        txtKeterangan = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Tanggal");

        jLabel2.setText("Keterangan");

        jLabel3.setText("Total");

        txtKeterangan.setText("jTextField1");

        txtTotal.setText("jTextField2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKeterangan)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 147, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jdcTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnBatal.setText("Batal");

        btnSimpan.setText("Simpan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatal)
                    .addComponent(btnSimpan))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser jdcTanggal;
    private javax.swing.JTextField txtKeterangan;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
