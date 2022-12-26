package com.aan.girsang.pembukuan.pribadi.UI.report;

import com.aan.girsang.pembukuan.report.UI.*;
import com.aan.girsang.pembukuan.Pembukuan;
import com.aan.girsang.pembukuan.util.Notifikasi;
import java.awt.BorderLayout;
import java.util.Locale;
import javax.swing.JButton;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class PanelLaporanKeuanganPribadi extends javax.swing.JPanel {

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
    public PanelLaporanKeuanganPribadi() {
        initComponents();
        initListener();
        clear();
    }
    private void tampilSemuaReport(){
        JasperPrint print = Pembukuan.getReportPribadiService()
                .reportKeuanganPribadi();
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
    private void tampilfilter(){
        JasperPrint print = Pembukuan.getReportPribadiService()
                .reportKeuanganPribadiFilter(jdcMulai.getDate(), jdcAkhir.getDate());
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
    private void clear(){
        jdcMulai.setDateFormatString("dd/MM/yyyy  HH:mm");
        jdcAkhir.setDateFormatString("dd/MM/yyyy  HH:mm");
        
        jdcMulai.setDate(null);
        jdcAkhir.setDate(null);
    }
    private boolean validasiTanggal(){
        if(jdcMulai.getDate()==null ||
                jdcAkhir.getDate()==null ||
                jdcMulai.getDate().before(jdcAkhir.getDate())==false
                ){
            Notifikasi.pesanValidasiSimpan();
            return false;
        }
        return true;
    }
    private void initListener(){
        btnTampil.addActionListener((ae) -> {
            if(validasiTanggal()){
                tampilfilter();
            }
        });
        btnTampilSemua.addActionListener((ae) -> {
            tampilSemuaReport();
        });
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTutup = new javax.swing.JButton();
        panelView = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jdcMulai = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jdcAkhir = new com.toedter.calendar.JDateChooser();
        btnTampil = new javax.swing.JButton();
        btnTampilSemua = new javax.swing.JButton();

        btnTutup.setText("Tutup");

        javax.swing.GroupLayout panelViewLayout = new javax.swing.GroupLayout(panelView);
        panelView.setLayout(panelViewLayout);
        panelViewLayout.setHorizontalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelViewLayout.setVerticalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setText("Tanggal");

        jLabel2.setText("Sampai");

        btnTampil.setText("Tampil");

        btnTampilSemua.setText("Tampil Semua");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnTampilSemua, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTampil)
                            .addComponent(btnTutup, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jdcAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcMulai, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(623, 623, 623))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jdcMulai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jdcAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTampil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTampilSemua)
                .addGap(30, 30, 30)
                .addComponent(btnTutup)
                .addGap(0, 285, Short.MAX_VALUE))
            .addComponent(panelView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTampil;
    private javax.swing.JButton btnTampilSemua;
    private javax.swing.JButton btnTutup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.toedter.calendar.JDateChooser jdcAkhir;
    private com.toedter.calendar.JDateChooser jdcMulai;
    private javax.swing.JPanel panelView;
    // End of variables declaration//GEN-END:variables
}
