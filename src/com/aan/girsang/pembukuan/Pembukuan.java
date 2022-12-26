package com.aan.girsang.pembukuan;

import com.aan.girsang.pembukuan.UI.FrameUtama;
import com.aan.girsang.pembukuan.pribadi.report.service.ReportPribadiService;
import com.aan.girsang.pembukuan.pribadi.service.PribadiService;
import com.aan.girsang.pembukuan.report.service.KeuanganReportService;
import com.aan.girsang.pembukuan.report.service.MasterReportService;
import com.aan.girsang.pembukuan.service.MasterService;
import com.aan.girsang.pembukuan.service.PemasukanService;
import com.aan.girsang.pembukuan.service.PengeluaranService;
import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.sql.DataSource;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Pembukuan {
    //private static final Logger log = Logger.getLogger(Pembukuan.class);
    private static MasterService masterService;
    private static PemasukanService pemasukanService;
    private static PengeluaranService pengeluaranService;
    private static MasterReportService masterReportService;
    private static KeuanganReportService keuanganReportService;
    private static PribadiService pribadiService;
    private static ReportPribadiService reportPribadiService;
    private static DataSource dataSource;

    //<editor-fold defaultstate="collapsed" desc="Getter">
    public static MasterService getMasterService() {
        return masterService;
    }
    
    public static PemasukanService getPemasukanService() {
        return pemasukanService;
    }
    
    public static PengeluaranService getPengeluaranService() {
        return pengeluaranService;
    }
    
    public static MasterReportService getMasterReportService() {
        return masterReportService;
    }
    
    public static KeuanganReportService getKeuanganReportService() {
        return keuanganReportService;
    }
    
    public static PribadiService getPribadiService() {
        return pribadiService;
    }

    public static ReportPribadiService getReportPribadiService() {
        return reportPribadiService;
    }
    
    public static DataSource getDataSource() {
        return dataSource;
    }
    
//</editor-fold>
    public static void main(String[] args) {
         try {
             
             UIManager.setLookAndFeel(new FlatIntelliJLaf());
             
             /*BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
             org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
             UIManager.put("RootPane.setupButtonVisible", Boolean.FALSE);*/

            
            AbstractApplicationContext ctx
                    = new ClassPathXmlApplicationContext("classpath*:/applicationContext.xml");
            ctx.registerShutdownHook();

            dataSource = (DataSource) ctx.getBean("dataSource");
            masterService = (MasterService) ctx.getBean("MasterService");
            pemasukanService = (PemasukanService) ctx.getBean("PemasukanService");
            pengeluaranService = (PengeluaranService) ctx.getBean("PengeluaranService");
            masterReportService = (MasterReportService) ctx.getBean("MasterReportService");
            keuanganReportService = (KeuanganReportService) ctx.getBean("KeuanganReportService");
            pribadiService = (PribadiService) ctx.getBean("PribadiService");
            reportPribadiService = (ReportPribadiService) ctx.getBean("KeuanganPribadiReportService");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Masalah Pada Database","Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(0);
        }
        java.awt.EventQueue.invokeLater(() -> {
            FrameUtama fu = new FrameUtama();
            fu.setExtendedState(JFrame.MAXIMIZED_BOTH);
            fu.setVisible(true);
            fu.jam();
        });
    }
    
    
    
}
