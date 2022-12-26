package com.aan.girsang.pembukuan.UI.Master.pelanggan;

import com.aan.girsang.pembukuan.UI.FrameUtama;
import com.aan.girsang.pembukuan.model.master.Pelanggan;
import com.aan.girsang.pembukuan.util.Notifikasi;
import com.aan.girsang.pembukuan.util.PesanJO;
import com.aan.girsang.pembukuan.util.TextComponentUtils;
import java.awt.event.ActionEvent;

public class DialogPelanggan extends javax.swing.JDialog {

    Pelanggan pelanggan = new Pelanggan();
    public DialogPelanggan() {
        super(FrameUtama.getInstance(), true);
        initComponents();
        initListener();
        TextComponentUtils.setAutoUpperCaseText(txtNama);
    }
    
    public Pelanggan showDialog(Pelanggan p){
        if(p!=null){
            setTitle(PesanJO.namaDialog.PELANGGAN_UBAH);
            clear();
            pelanggan = new Pelanggan();
            pelanggan = p;
            loadModelToForm(pelanggan);
        }else{
            setTitle(PesanJO.namaDialog.PELANGGAN_TAMBAH);
            clear();
        }
        
        setLocationRelativeTo(null);
        setVisible(true);
        return pelanggan;
    }
    private void clear(){
        txtNama.setText("");
        txtAlamat.setText("");
        txtHP.setText("");
        txtSosmed.setText("");
        txtNama.requestFocus();
    }
    private void loadModelToForm(Pelanggan p){
        txtNama.setText(p.getNamaLengkap());
        txtAlamat.setText(p.getAlamat());
        txtHP.setText(p.getHp());
        txtSosmed.setText(p.getSosmed());
    }
    private void loadFormToModel(){
        pelanggan.setNamaLengkap(txtNama.getText());
        pelanggan.setAlamat(txtAlamat.getText());
        pelanggan.setHp(txtHP.getText());
        pelanggan.setSosmed(txtSosmed.getText());
    }
    private boolean validasiPelanggan(){
        if(txtNama.getText().equals("")){
            Notifikasi.pesanValidasiSimpan();
            return false;
        }
        return true;
    }
    private void initListener(){
        btnBatal.addActionListener((ActionEvent)-> {
            pelanggan = null;
            dispose();
        });
        btnSimpan.addActionListener((ActionEvent)-> {
            if(pelanggan==null){
                pelanggan = new Pelanggan();
            }
            if(validasiPelanggan()){
                loadFormToModel();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        txtHP = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSosmed = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nama Pelanggan");

        jLabel2.setText("Alamat");

        jLabel3.setText("No. HP");

        jLabel4.setText("Kontak Sosmed");

        txtNama.setText("jTextField1");

        txtAlamat.setColumns(20);
        txtAlamat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtAlamat.setLineWrap(true);
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        txtHP.setText("jTextField2");

        txtSosmed.setColumns(20);
        txtSosmed.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtSosmed.setLineWrap(true);
        txtSosmed.setRows(5);
        jScrollPane2.setViewportView(txtSosmed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSimpan.setText("Simpan");

        btnBatal.setText("Batal");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBatal)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBatal, btnSimpan});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtHP;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextArea txtSosmed;
    // End of variables declaration//GEN-END:variables
}
