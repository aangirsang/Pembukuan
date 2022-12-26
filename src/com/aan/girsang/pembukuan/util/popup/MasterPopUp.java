package com.aan.girsang.pembukuan.util.popup;

import com.aan.girsang.pembukuan.Pembukuan;
import com.aan.girsang.pembukuan.UI.FrameUtama;
import com.aan.girsang.pembukuan.UI.Master.item.PanelItem;
import com.aan.girsang.pembukuan.UI.Master.menu.PanelMenu;
import com.aan.girsang.pembukuan.UI.Master.pelanggan.PanelPelanggan;
import com.aan.girsang.pembukuan.UI.Master.user.PanelUser;
import com.aan.girsang.pembukuan.report.UI.PanelReportMenu;
import com.aan.girsang.pembukuan.util.PesanJO;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import org.h2.tools.RunScript;
import org.springframework.beans.factory.annotation.Autowired;

public class MasterPopUp extends AbstractButton{
    
    @Autowired
    private DataSource dS;
    
    JButton actionButton;
    JPopupMenu popupMenu;
    
    PanelMenu menuPanel = new PanelMenu();
    PanelPelanggan pelanggan = new PanelPelanggan();
    PanelItem item = new PanelItem();
    PanelReportMenu panelReportMenu = new PanelReportMenu();
    PanelUser panelUser = new PanelUser();
    
    public MasterPopUp(JTabbedPane TP, JPopupMenu popupMenuMaster, JButton btnMaster) {
        popupMenuMaster.add(new JMenuItem(new AbstractAction(PesanJO.namaMenu.MENU) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (menuPanel.getAktifPanel() == 1) {
                    TP.setSelectedIndex(menuPanel.getIndexTab());
                } else {
                    menuPanel = new PanelMenu();
                    menuPanel.setName(PesanJO.namaTab.MENU);
                    menuPanel.setAktifPanel(menuPanel.getAktifPanel() + 1);
                    TP.addTab(menuPanel.getName(), menuPanel);
                    menuPanel.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(menuPanel.getIndexTab());
                    
                    menuPanel.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(menuPanel);
                        menuPanel.setAktifPanel(menuPanel.getAktifPanel() - 1);
                        TP.setSelectedIndex(menuPanel.getIndexTab() -1);
                    });
                }
            }
        }));
        popupMenuMaster.add(new JMenuItem(new AbstractAction(PesanJO.namaMenu.PELANGGAN) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (pelanggan.getAktifPanel() == 1) {
                    TP.setSelectedIndex(pelanggan.getIndexTab());
                } else {
                    pelanggan = new PanelPelanggan();
                    pelanggan.setName(PesanJO.namaTab.PELANGGAN);
                    pelanggan.setAktifPanel(pelanggan.getAktifPanel() + 1);
                    TP.addTab(pelanggan.getName(), pelanggan);
                    pelanggan.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(pelanggan.getIndexTab());
                    
                    pelanggan.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(pelanggan);
                        pelanggan.setAktifPanel(pelanggan.getAktifPanel() - 1);
                        TP.setSelectedIndex(pelanggan.getIndexTab() -1);
                    });
                }
            }
        }));
        popupMenuMaster.add(new JMenuItem(new AbstractAction(PesanJO.namaMenu.ITEM) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (item.getAktifPanel() == 1) {
                    TP.setSelectedIndex(item.getIndexTab());
                } else {
                    item = new PanelItem();
                    item.setName(PesanJO.namaTab.ITEM);
                    item.setAktifPanel(item.getAktifPanel() + 1);
                    TP.addTab(item.getName(), item);
                    item.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(item.getIndexTab());
                    
                    item.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(item);
                        item.setAktifPanel(item.getAktifPanel() - 1);
                        TP.setSelectedIndex(item.getIndexTab() -1);
                    });
                }
            }
        }));
        popupMenuMaster.add(new JMenuItem(new AbstractAction("User") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (panelUser.getAktifPanel() == 1) {
                    TP.setSelectedIndex(panelUser.getIndexTab());
                } else {
                    panelUser = new PanelUser();
                    panelUser.setName("Data User");
                    panelUser.setAktifPanel(panelUser.getAktifPanel() + 1);
                    TP.addTab(panelUser.getName(), panelUser);
                    panelUser.setIndexTab(TP.getTabCount() - 1);
                    TP.setSelectedIndex(panelUser.getIndexTab());
                    
                    panelUser.getBtnTutup().addActionListener((ae1) -> {
                        TP.remove(panelUser);
                        panelUser.setAktifPanel(panelUser.getAktifPanel() - 1);
                        TP.setSelectedIndex(panelUser.getIndexTab() -1);
                    });
                }
            }
        }));
        
        popupMenuMaster.add(new JMenuItem(new AbstractAction("BackUp Database") {
            SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
            String Judul = "Back Up Data "+ formater.format(new Date())+".zip";
            @Override
            public void actionPerformed(ActionEvent ae) {
                try (Connection c = Pembukuan.getDataSource().getConnection() ;
                        Statement stmt = c.createStatement()) {
                    JFileChooser chooser = new JFileChooser(Judul);
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooser.setSelectedFile(new File(Judul));
                    int returnValue = chooser.showSaveDialog(FrameUtama.getInstance());
                    if(returnValue==JFileChooser.APPROVE_OPTION){
                        String url = chooser.getSelectedFile().getPath();
                        stmt.execute(String.format("BACKUP TO '%s'", url));
                    }
                    
                    
                    c.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MasterPopUp.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }));
        /*popupMenuMaster.add(new JMenuItem(new AbstractAction("Restore Database") {
        @Override
        public void actionPerformed(ActionEvent ae) {
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(null);
        File file = jfc.getSelectedFile();
        String dir = file.getAbsolutePath();
        try (Connection c = Pembukuan.getDataSource().getConnection() ;
        Statement stmt = c.createStatement()) {
        RunScript.execute(c, new FileReader(dir));
        c.close();
        } catch (SQLException ex) {
        Logger.getLogger(MasterPopUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
        Logger.getLogger(MasterPopUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Direktori file yang di pilih "+ dir);
        }
        }));*/
         btnMaster.addActionListener((ae) -> {
            popupMenuMaster.show(btnMaster, 0, btnMaster.getSize().height);
        });
    }
}
