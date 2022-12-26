package com.aan.girsang.pembukuan.util.popup;

import com.aan.girsang.pembukuan.UI.pemasukan.PemasukanPanel;
import com.aan.girsang.pembukuan.UI.pemasukan.pesanan.PanelPesanan;
import com.aan.girsang.pembukuan.util.PesanJO;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

public class PemasukanPopUp extends AbstractButton{
    JButton actionButton;
    JPopupMenu popupMenu;
    
    PemasukanPanel pemasukanPanel = new PemasukanPanel();
    PanelPesanan panelPesanan = new PanelPesanan();
    
    public PemasukanPopUp(JTabbedPane TP, JPopupMenu popupMenuMaster, JButton btnPemasukan) {
        popupMenuMaster.add(new JMenuItem(new AbstractAction(PesanJO.namaMenu.PEMASUKAN) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (pemasukanPanel.getAktifPanel() == 1) {
                    TP.setSelectedIndex(pemasukanPanel.getIndexTab());
                } else {
                    pemasukanPanel = new PemasukanPanel();
                    pemasukanPanel.setName(PesanJO.namaTab.PEMASUKAN);
                    pemasukanPanel.setAktifPanel(pemasukanPanel.getAktifPanel() + 1);
                    TP.addTab(pemasukanPanel.getName(), pemasukanPanel);
                    pemasukanPanel.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(pemasukanPanel.getIndexTab());
                    
                    pemasukanPanel.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(pemasukanPanel);
                        pemasukanPanel.setAktifPanel(pemasukanPanel.getAktifPanel() - 1);
                        TP.setSelectedIndex(pemasukanPanel.getIndexTab() -1);
                    });
                }
            }
        }));
        popupMenuMaster.add(new JMenuItem(new AbstractAction(PesanJO.namaMenu.PESANAN) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (panelPesanan.getAktifPanel() == 1) {
                    TP.setSelectedIndex(panelPesanan.getIndexTab());
                } else {
                    panelPesanan = new PanelPesanan();
                    panelPesanan.setName(PesanJO.namaTab.PESANAN);
                    panelPesanan.setAktifPanel(panelPesanan.getAktifPanel() + 1);
                    TP.addTab(panelPesanan.getName(), panelPesanan);
                    panelPesanan.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(panelPesanan.getIndexTab());
                    
                    panelPesanan.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(panelPesanan);
                        panelPesanan.setAktifPanel(panelPesanan.getAktifPanel() - 1);
                        TP.setSelectedIndex(panelPesanan.getIndexTab() -1);
                    });
                }
            }
        }));
        btnPemasukan.addActionListener((ae) -> {
            popupMenuMaster.show(btnPemasukan, 0, btnPemasukan.getSize().height);
        });
    }
}
