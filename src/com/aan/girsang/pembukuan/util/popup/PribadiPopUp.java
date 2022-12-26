package com.aan.girsang.pembukuan.util.popup;

import com.aan.girsang.pembukuan.UI.Master.menu.PanelMenu;
import com.aan.girsang.pembukuan.pribadi.UI.PanelPemasukanPribadi;
import com.aan.girsang.pembukuan.pribadi.UI.PanelPengeluaranPribadi;
import com.aan.girsang.pembukuan.util.PesanJO;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;


public class PribadiPopUp {
    
    JButton actionButton;
    JPopupMenu popupMenu;
    
    PanelPemasukanPribadi pemasukanPribadi = new PanelPemasukanPribadi();
    PanelPengeluaranPribadi pengeluaranPribadi = new PanelPengeluaranPribadi();
    public PribadiPopUp(JTabbedPane TP, JPopupMenu popupMenuMaster, JButton btnMaster) {
        popupMenuMaster.add(new JMenuItem(new AbstractAction("Pemasukan Pribadi") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (pemasukanPribadi.getAktifPanel() == 1) {
                    TP.setSelectedIndex(pemasukanPribadi.getIndexTab());
                } else {
                    pemasukanPribadi = new PanelPemasukanPribadi();
                    pemasukanPribadi.setName("Data Pemasukan Pribadi");
                    pemasukanPribadi.setAktifPanel(pemasukanPribadi.getAktifPanel() + 1);
                    TP.addTab(pemasukanPribadi.getName(), pemasukanPribadi);
                    pemasukanPribadi.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(pemasukanPribadi.getIndexTab());
                    
                    pemasukanPribadi.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(pemasukanPribadi);
                        pemasukanPribadi.setAktifPanel(pemasukanPribadi.getAktifPanel() - 1);
                        TP.setSelectedIndex(pemasukanPribadi.getIndexTab() -1);
                    });
                }
            }
        }));
        popupMenuMaster.add(new JMenuItem(new AbstractAction("Pengeluaran Pribadi") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (pengeluaranPribadi.getAktifPanel() == 1) {
                    TP.setSelectedIndex(pengeluaranPribadi.getIndexTab());
                } else {
                    pengeluaranPribadi = new PanelPengeluaranPribadi();
                    pengeluaranPribadi.setName("Data Pengeluaran Pribadi");
                    pengeluaranPribadi.setAktifPanel(pengeluaranPribadi.getAktifPanel() + 1);
                    TP.addTab(pengeluaranPribadi.getName(), pengeluaranPribadi);
                    pengeluaranPribadi.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(pengeluaranPribadi.getIndexTab());
                    
                    pengeluaranPribadi.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(pengeluaranPribadi);
                        pengeluaranPribadi.setAktifPanel(pengeluaranPribadi.getAktifPanel() - 1);
                        TP.setSelectedIndex(pengeluaranPribadi.getIndexTab() -1);
                    });
                }
            }
        }));
        btnMaster.addActionListener((ae) -> {
            popupMenuMaster.show(btnMaster, 0, btnMaster.getSize().height);
        });
    }
}
