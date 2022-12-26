package com.aan.girsang.pembukuan.util.popup;

import com.aan.girsang.pembukuan.pribadi.UI.report.PanelLaporanKeuanganPribadi;
import com.aan.girsang.pembukuan.pribadi.UI.report.PanelLaporanPemasukanPribadi;
import com.aan.girsang.pembukuan.pribadi.UI.report.PanelLaporanPengeluaranPribadi;
import com.aan.girsang.pembukuan.report.UI.PanelLaporanPesanan;
import com.aan.girsang.pembukuan.report.UI.PanelLaporanRekapPengeluaran;
import com.aan.girsang.pembukuan.report.UI.PanelLaporanRekapPesananMenu;
import com.aan.girsang.pembukuan.report.UI.PanelReportJurnalUmum;
import com.aan.girsang.pembukuan.report.UI.PanelReportMenu;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

public class ReportPopUp {
    JButton actionButton;
    JPopupMenu popupMenu;
    JMenu menuItem;
    JTabbedPane TP;
    
    PanelReportMenu panelReportMenu = new PanelReportMenu();
    PanelReportJurnalUmum jurnalUmum = new PanelReportJurnalUmum();
    PanelLaporanPesanan laporanPesanan = new PanelLaporanPesanan();
    PanelLaporanRekapPesananMenu laporanRekapPesananMenu = new PanelLaporanRekapPesananMenu();
    PanelLaporanRekapPengeluaran laporanRekapPengeluaran = new PanelLaporanRekapPengeluaran();
    PanelLaporanKeuanganPribadi keuanganPribadi = new PanelLaporanKeuanganPribadi();
    PanelLaporanPemasukanPribadi laporanPemasukanPribadi = new PanelLaporanPemasukanPribadi();
    PanelLaporanPengeluaranPribadi laporanPengeluaranPribadi = new PanelLaporanPengeluaranPribadi();
    
    public ReportPopUp(JTabbedPane TP, JPopupMenu popupReport, JButton button) {
        this.TP = TP;
        popupReport.add(addmenu());
        popupReport.add(new JMenuItem(new AbstractAction("Laporan Menu") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (panelReportMenu.getAktifPanel() == 1) {
                    TP.setSelectedIndex(panelReportMenu.getIndexTab());
                } else {
                    panelReportMenu = new PanelReportMenu();
                    panelReportMenu.setName("Laporan Menu");
                    panelReportMenu.setAktifPanel(panelReportMenu.getAktifPanel() + 1);
                    TP.addTab(panelReportMenu.getName(), panelReportMenu);
                    panelReportMenu.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(panelReportMenu.getIndexTab());
                    
                    panelReportMenu.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(panelReportMenu);
                        panelReportMenu.setAktifPanel(panelReportMenu.getAktifPanel() - 1);
                        TP.setSelectedIndex(panelReportMenu.getIndexTab() -1);
                    });
                }
            }
        }));
        popupReport.add(new JMenuItem(new AbstractAction("Laporan Keuangan") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (jurnalUmum.getAktifPanel() == 1) {
                    TP.setSelectedIndex(jurnalUmum.getIndexTab());
                } else {
                    jurnalUmum = new PanelReportJurnalUmum();
                    jurnalUmum.setName("Laporan Keuangan");
                    jurnalUmum.setAktifPanel(jurnalUmum.getAktifPanel() + 1);
                    TP.addTab(jurnalUmum.getName(), jurnalUmum);
                    jurnalUmum.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(jurnalUmum.getIndexTab());
                    
                    jurnalUmum.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(jurnalUmum);
                        jurnalUmum.setAktifPanel(jurnalUmum.getAktifPanel() - 1);
                        TP.setSelectedIndex(jurnalUmum.getIndexTab() -1);
                    });
                }
            }
        }));
        popupReport.add(new JMenuItem(new AbstractAction("Laporan Pesanan") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (laporanPesanan.getAktifPanel() == 1) {
                    TP.setSelectedIndex(laporanPesanan.getIndexTab());
                } else {
                    laporanPesanan = new PanelLaporanPesanan();
                    laporanPesanan.setName("Laporan Pesanan");
                    laporanPesanan.setAktifPanel(laporanPesanan.getAktifPanel() + 1);
                    TP.addTab(laporanPesanan.getName(), laporanPesanan);
                    laporanPesanan.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(laporanPesanan.getIndexTab());
                    
                    laporanPesanan.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(laporanPesanan);
                        laporanPesanan.setAktifPanel(laporanPesanan.getAktifPanel() - 1);
                        TP.setSelectedIndex(laporanPesanan.getIndexTab() -1);
                    });
                }
            }
        }));
        popupReport.add(new JMenuItem(new AbstractAction("Laporan Rekap Pesanan Menu") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (laporanRekapPesananMenu.getAktifPanel() == 1) {
                    TP.setSelectedIndex(laporanRekapPesananMenu.getIndexTab());
                } else {
                    laporanRekapPesananMenu = new PanelLaporanRekapPesananMenu();
                    laporanRekapPesananMenu.setName("Rekap Pesanan Menu");
                    laporanRekapPesananMenu.setAktifPanel(laporanRekapPesananMenu.getAktifPanel() + 1);
                    TP.addTab(laporanRekapPesananMenu.getName(), laporanRekapPesananMenu);
                    laporanRekapPesananMenu.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(laporanRekapPesananMenu.getIndexTab());
                    
                    laporanRekapPesananMenu.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(laporanRekapPesananMenu);
                        laporanRekapPesananMenu.setAktifPanel(laporanRekapPesananMenu.getAktifPanel() - 1);
                        TP.setSelectedIndex(laporanRekapPesananMenu.getIndexTab() -1);
                    });
                }
            }
        }));
        popupReport.add(new JMenuItem(new AbstractAction("Laporan Rekap Pengeluaran") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (laporanRekapPengeluaran.getAktifPanel() == 1) {
                    TP.setSelectedIndex(laporanRekapPengeluaran.getIndexTab());
                } else {
                    laporanRekapPengeluaran = new PanelLaporanRekapPengeluaran();
                    laporanRekapPengeluaran.setName("Rekap Pengeluaran");
                    laporanRekapPengeluaran.setAktifPanel(laporanRekapPengeluaran.getAktifPanel() + 1);
                    TP.addTab(laporanRekapPengeluaran.getName(), laporanRekapPengeluaran);
                    laporanRekapPengeluaran.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(laporanRekapPengeluaran.getIndexTab());
                    
                    laporanRekapPengeluaran.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(laporanRekapPengeluaran);
                        laporanRekapPengeluaran.setAktifPanel(laporanRekapPengeluaran.getAktifPanel() - 1);
                        TP.setSelectedIndex(laporanRekapPengeluaran.getIndexTab() -1);
                    });
                }
            }
        }));
        
        button.addActionListener((ae) -> {
            popupReport.show(button, 0, button.getSize().height);
        });
    }
    public JMenuItem addmenu(){
        menuItem = new JMenu("Laporan Pribadi");
        menuItem.add(new JMenuItem(new AbstractAction("Laporan Keuangan Pribadi") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (keuanganPribadi.getAktifPanel() == 1) {
                    TP.setSelectedIndex(keuanganPribadi.getIndexTab());
                } else {
                    keuanganPribadi = new PanelLaporanKeuanganPribadi();
                    keuanganPribadi.setName("Laporan Keuangan Pribadi");
                    keuanganPribadi.setAktifPanel(keuanganPribadi.getAktifPanel() + 1);
                    TP.addTab(keuanganPribadi.getName(), keuanganPribadi);
                    keuanganPribadi.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(keuanganPribadi.getIndexTab());
                    
                    keuanganPribadi.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(keuanganPribadi);
                        keuanganPribadi.setAktifPanel(keuanganPribadi.getAktifPanel() - 1);
                        TP.setSelectedIndex(keuanganPribadi.getIndexTab() -1);
                    });
                }
            }
        }));
        menuItem.add(new JMenuItem(new AbstractAction("Laporan Pemasukan Pribadi") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (laporanPemasukanPribadi.getAktifPanel() == 1) {
                    TP.setSelectedIndex(laporanPemasukanPribadi.getIndexTab());
                } else {
                    laporanPemasukanPribadi = new PanelLaporanPemasukanPribadi();
                    laporanPemasukanPribadi.setName("Laporan Pemasukan Pribadi");
                    laporanPemasukanPribadi.setAktifPanel(laporanPemasukanPribadi.getAktifPanel() + 1);
                    TP.addTab(laporanPemasukanPribadi.getName(), laporanPemasukanPribadi);
                    laporanPemasukanPribadi.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(laporanPemasukanPribadi.getIndexTab());
                    
                    laporanPemasukanPribadi.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(laporanPemasukanPribadi);
                        laporanPemasukanPribadi.setAktifPanel(laporanPemasukanPribadi.getAktifPanel() - 1);
                        TP.setSelectedIndex(laporanPemasukanPribadi.getIndexTab() -1);
                    });
                }
            }
        }));
        menuItem.add(new JMenuItem(new AbstractAction("Laporan Pengeluaran Pribadi") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (laporanPengeluaranPribadi.getAktifPanel() == 1) {
                    TP.setSelectedIndex(laporanPengeluaranPribadi.getIndexTab());
                } else {
                    laporanPengeluaranPribadi = new PanelLaporanPengeluaranPribadi();
                    laporanPengeluaranPribadi.setName("Laporan Pengeluaran Pribadi");
                    laporanPengeluaranPribadi.setAktifPanel(laporanPengeluaranPribadi.getAktifPanel() + 1);
                    TP.addTab(laporanPengeluaranPribadi.getName(), laporanPengeluaranPribadi);
                    laporanPengeluaranPribadi.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(laporanPengeluaranPribadi.getIndexTab());
                    
                    laporanPengeluaranPribadi.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(laporanPengeluaranPribadi);
                        laporanPengeluaranPribadi.setAktifPanel(laporanPengeluaranPribadi.getAktifPanel() - 1);
                        TP.setSelectedIndex(laporanPengeluaranPribadi.getIndexTab() -1);
                    });
                }
            }
        }));
        return menuItem;
    }
}
