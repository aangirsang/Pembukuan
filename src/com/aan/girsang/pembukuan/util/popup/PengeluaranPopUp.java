package com.aan.girsang.pembukuan.util.popup;

import com.aan.girsang.pembukuan.UI.pengeluaran.belanja.PanelBelanja;
import com.aan.girsang.pembukuan.util.PesanJO;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

public class PengeluaranPopUp {
    JButton actionButton;
    JPopupMenu popupMenu;
    
    PanelBelanja panelBelanja = new PanelBelanja();
    public PengeluaranPopUp(JTabbedPane TP, JPopupMenu popupMenuMaster, JButton btnPemasukan) {
        popupMenuMaster.add(new JMenuItem(new AbstractAction(PesanJO.namaMenu.BELANJA) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (panelBelanja.getAktifPanel() == 1) {
                    TP.setSelectedIndex(panelBelanja.getIndexTab());
                } else {
                    panelBelanja = new PanelBelanja();
                    panelBelanja.setName(PesanJO.namaTab.BELANJA);
                    panelBelanja.setAktifPanel(panelBelanja.getAktifPanel() + 1);
                    TP.addTab(panelBelanja.getName(), panelBelanja);
                    panelBelanja.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(panelBelanja.getIndexTab());
                    
                    panelBelanja.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(panelBelanja);
                        panelBelanja.setAktifPanel(panelBelanja.getAktifPanel() - 1);
                        TP.setSelectedIndex(panelBelanja.getIndexTab() -1);
                    });
                }
            }
        }));
        btnPemasukan.addActionListener((ae) -> {
            popupMenuMaster.show(btnPemasukan, 0, btnPemasukan.getSize().height);
        });
    }
}
