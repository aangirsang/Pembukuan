package com.aan.girsang.pembukuan.pribadi.report.service;

import com.aan.girsang.pembukuan.pribadi.report.model.ReportPribadiModel;
import com.aan.girsang.pembukuan.report.model.ReportJurnalUmum;
import com.aan.girsang.pembukuan.report.service.impl.KeuanganReportServiceImpl;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.QueryException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("KeuanganPribadiReportService")
@Transactional(readOnly = true)
public class ReportPribadiServiceImpl implements ReportPribadiService{

    @Autowired SessionFactory sessionFactory;
    @Override
    public JasperPrint reportKeuanganPribadi() {
        try {
            List<ReportPribadiModel> reportPribadiModel = 
                    sessionFactory.getCurrentSession().createQuery(
                            "select j.tanggal as tanggal, "
                                    + "j.tanggal as waktu, "
                                    + "j.keterangan as keterangan, "
                                    + "j.debit as debit, "
                                    + "j.kredit as kredit "
                                    + "from JurnalUmumPribadi j "
                                    + "order by j.tanggal Asc")
                    .setResultTransformer(Transformers.aliasToBean(ReportPribadiModel.class))
                    .list();
            
            InputStream is = ReportPribadiServiceImpl.class
                    .getResourceAsStream("/com/aan/girsang/pembukuan/pribadi/report/jasper/LaporanKeuanganPribadi.jasper");
            Map<String, Object> parameters = new HashMap<String, Object>();
            System.out.println("Report Tampil");
            return JasperFillManager.fillReport(is, parameters,
                    new JRBeanCollectionDataSource(reportPribadiModel));
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (QueryException ex){
            ex.printStackTrace();
        }
        
        return null;
    }

    @Override
    public JasperPrint reportKeuanganPribadiFilter(Date mulai, Date akhir) {
        try {
            List<ReportPribadiModel> reportPribadiModel = 
                    sessionFactory.getCurrentSession().createQuery(
                            "select j.tanggal as tanggal, "
                                    + "j.tanggal as waktu, "
                                    + "j.keterangan as keterangan, "
                                    + "j.debit as debit, "
                                    + "j.kredit as kredit "
                                    + "from JurnalUmumPribadi j "
                                    + "where j.tanggal>=:mulai and j.tanggal<=:akhir "
                                    + "order by j.tanggal Asc")
                            .setParameter("mulai", mulai)
                            .setParameter("akhir", akhir)
                    .setResultTransformer(Transformers.aliasToBean(ReportPribadiModel.class))
                    .list();
            
            InputStream is = ReportPribadiServiceImpl.class
                    .getResourceAsStream("/com/aan/girsang/pembukuan/pribadi/report/jasper/LaporanKeuanganPribadiFilter.jasper");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("mulai", mulai);
            parameters.put("akhir", akhir);
            System.out.println("Report Tampil");
            return JasperFillManager.fillReport(is, parameters,
                    new JRBeanCollectionDataSource(reportPribadiModel));
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (QueryException ex){
            ex.printStackTrace();
        }
        
        return null;
    }

    @Override
    public JasperPrint reportRekapPengeluaranPribadi(int bulan, int tahun) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, bulan);
        calendar.set(Calendar.YEAR, tahun);
        Date date = calendar.getTime();
        
        String tgl = new SimpleDateFormat("MM yyyy").format(date);
        String tgl1 = new SimpleDateFormat("MMMM yyyy").format(date);
        try {
            List<ReportPribadiModel> reportPribadiModel = 
                    sessionFactory.getCurrentSession().createQuery(
                            "select j.tanggal as tanggal, "
                                    + "j.tanggal as waktu, "
                                    + "j.keterangan as keterangan, "
                                    + "j.total as debit, "
                                    + "j.total as kredit "
                                    + "from PengeluaranPribadi j "
                                    + "where TO_CHAR(j.tanggal, 'MM yyyy') LIKE :bulan "
                                    + "order by j.tanggal Asc")
                            .setParameter("bulan","%"+tgl+"%")
                    .setResultTransformer(Transformers.aliasToBean(ReportPribadiModel.class))
                    .list();
            
            InputStream is = ReportPribadiServiceImpl.class
                    .getResourceAsStream("/com/aan/girsang/pembukuan/pribadi/report/jasper/LaporanPengeluaranPribadi.jasper");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("date",tgl1);
            System.out.println("Report Tampil");
            return JasperFillManager.fillReport(is, parameters,
                    new JRBeanCollectionDataSource(reportPribadiModel));
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (QueryException ex){
            ex.printStackTrace();
        }
        
        return null;
    }

    @Override
    public JasperPrint reportRekapPemasukanPribadi(int bulan, int tahun) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, bulan);
        calendar.set(Calendar.YEAR, tahun);
        Date date = calendar.getTime();
        
        String tgl = new SimpleDateFormat("MM yyyy").format(date);
        String tgl1 = new SimpleDateFormat("MMMM yyyy").format(date);
        try {
            List<ReportPribadiModel> reportPribadiModel = 
                    sessionFactory.getCurrentSession().createQuery(
                            "select j.tanggal as tanggal, "
                                    + "j.tanggal as waktu, "
                                    + "j.keterangan as keterangan, "
                                    + "j.total as debit, "
                                    + "j.total as kredit "
                                    + "from PemasukanPribadi j "
                                    + "where TO_CHAR(j.tanggal, 'MM yyyy') LIKE :bulan "
                                    + "order by j.tanggal Asc")
                            .setParameter("bulan","%"+tgl+"%")
                    .setResultTransformer(Transformers.aliasToBean(ReportPribadiModel.class))
                    .list();
            
            InputStream is = ReportPribadiServiceImpl.class
                    .getResourceAsStream("/com/aan/girsang/pembukuan/pribadi/report/jasper/LaporanPemasukanPribadi.jasper");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("date",tgl1);
            System.out.println("Report Tampil");
            return JasperFillManager.fillReport(is, parameters,
                    new JRBeanCollectionDataSource(reportPribadiModel));
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (QueryException ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    
}
