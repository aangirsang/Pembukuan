package com.aan.girsang.pembukuan.report.UI;

import com.aan.girsang.pembukuan.Pembukuan;
import com.aan.girsang.pembukuan.util.Notifikasi;
import java.awt.BorderLayout;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class PanelLaporanPesanan extends javax.swing.JPanel {

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
        return btnTutup;
    }
//</editor-fold>
    public PanelLaporanPesanan() {
        initComponents();
        initListener();
        clear();
    }

    private void tampilSemuaReport(){
        JasperPrint print = Pembukuan.getKeuanganReportService().reportPesanan();
        JRViewer viewer = new JRViewer(print);
        viewer.setPreferredSize(getPreferredSize());
        panelView.removeAll();
        panelView.setLayout(new BorderLayout());
        panelView.add(viewer, BorderLayout.CENTER);
        panelView.revalidate();
        panelView.repaint();
    }
    private void clear(){
        jdcMulai.setDateFormatString("dd/MM/yyyy  HH:mm");
        jdcAkhir.setDateFormatString("dd/MM/yyyy  HH:mm");
        
        jdcMulai.setDate(null);
        jdcAkhir.setDate(null);
    }
    private void tampilReport(){
        JasperPrint print = Pembukuan.getKeuanganReportService().reportPesanan(jdcMulai.getDate(), jdcAkhir.getDate());
        JRViewer viewer = new JRViewer(print);
        Locale local = new Locale("id","ID");
        viewer.setLocale(local);
        viewer.setPreferredSize(getPreferredSize());
        panelView.removeAll();
        panelView.setLayout(new BorderLayout());
        panelView.add(viewer, BorderLayout.CENTER);
        panelView.revalidate();
        panelView.repaint();
    }
    private boolean validasiTanggal(){
        if(jdcMulai.getDate()==null ||
                jdcAkhir.getDate()==null ||
                jdcMulai.getDate().before(jdcAkhir.getDate())==false
                ){
            JOptionPane.showMessageDialog(this, "Tanggal Seleksi Salah");
            return false;
        }
        return true;
    }
    private void initListener(){
        btnTampilSemua.addActionListener((ae)->{
            tampilSemuaReport();
        });
        btnTampil.addActionListener((ae)->{
            if(validasiTanggal()){
                tampilReport();
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnTutup = new javax.swing.JButton();
        panelView = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jdcMulai = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jdcAkhir = new com.toedter.calendar.JDateChooser();
        btnTampil = new javax.swing.JButton();
        btnTampilSemua = new javax.swing.JButton();

        btnTutup.setText("Tutup");

        panelView.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelViewLayout = new javax.swing.GroupLayout(panelView);
        panelView.setLayout(panelViewLayout);
        panelViewLayout.setHorizontalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );
        panelViewLayout.setVerticalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setText("Tanggal");

        jLabel2.setText("Sampai");

        btnTampil.setText("Tampil");

        btnTampilSemua.setText("Tampilkan Semua Pesanan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jdcMulai, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(btnTampil))
                    .addComponent(btnTampilSemua, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTutup, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdcMulai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdcAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(btnTampil)
                .addGap(18, 18, 18)
                .addComponent(btnTampilSemua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 357, Short.MAX_VALUE)
                .addComponent(btnTutup)
                .addContainerGap())
            .addComponent(panelView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTampil;
    private javax.swing.JButton btnTampilSemua;
    private javax.swing.JButton btnTutup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser jdcAkhir;
    private com.toedter.calendar.JDateChooser jdcMulai;
    private javax.swing.JPanel panelView;
    // End of variables declaration//GEN-END:variables
}
