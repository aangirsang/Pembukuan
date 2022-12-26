package com.aan.girsang.pembukuan.report.service.impl;

import com.aan.girsang.pembukuan.report.model.ReportJurnalUmum;
import com.aan.girsang.pembukuan.report.model.ReportPengeluaran;
import com.aan.girsang.pembukuan.report.model.ReportPesanan;
import com.aan.girsang.pembukuan.report.model.ReportRekapPesanan;
import com.aan.girsang.pembukuan.report.service.KeuanganReportService;
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

@Service("KeuanganReportService")
@Transactional(readOnly = true)
public class KeuanganReportServiceImpl implements KeuanganReportService{
    @Autowired SessionFactory sessionFactory;
    @Override
    public JasperPrint reportJurnalUmum() {
        try {
            List<ReportJurnalUmum> reportJurnalUmum = 
                    sessionFactory.getCurrentSession().createQuery(
                            "select j.tanggal as tanggal, "
                                    + "j.tanggal as waktu,  "
                                    + "j.keterangan as keterangan, "
                                    + "j.debit as debit, "
                                    + "j.kredit as kredit "
                                    + "from JurnalUmumModel j "
                                    + "order by j.tanggal")
                    .setResultTransformer(Transformers.aliasToBean(ReportJurnalUmum.class))
                    .list();
            
            InputStream is = KeuanganReportServiceImpl.class
                    .getResourceAsStream("/com/aan/girsang/pembukuan/report/keuangan/jasper/LaporanKeuangan.jasper");
            Map<String, Object> parameters = new HashMap<String, Object>();
            System.out.println("Report Tampil");
            return JasperFillManager.fillReport(is, parameters,
                    new JRBeanCollectionDataSource(reportJurnalUmum));
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (QueryException ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    @Override
    public JasperPrint reportJurnalUmum(Date mulai, Date akhir) {
        try {
            List<ReportJurnalUmum> reportJurnalUmum = 
                    sessionFactory.getCurrentSession().createQuery(
                            "select j.tanggal as tanggal, "
                                    + "j.tanggal as waktu,  "
                                    + "j.keterangan as keterangan, "
                                    + "j.debit as debit, "
                                    + "j.kredit as kredit "
                                    + "from JurnalUmumModel j "
                                    + "where j.tanggal>=:mulai and j.tanggal<=:akhir "
                                    + "order by j.tanggal")
                            .setParameter("mulai", mulai)
                            .setParameter("akhir", akhir)
                    .setResultTransformer(Transformers.aliasToBean(ReportJurnalUmum.class))
                    .list();
            
            InputStream is = KeuanganReportServiceImpl.class
                    .getResourceAsStream("/com/aan/girsang/pembukuan/report/keuangan/jasper/LaporanKeuanganFilter.jasper");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("mulai", mulai);
            parameters.put("akhir", akhir);
            System.out.println("Report Tampil");
            return JasperFillManager.fillReport(is, parameters,
                    new JRBeanCollectionDataSource(reportJurnalUmum));
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (QueryException ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    @Override
    public JasperPrint reportPesanan() {
        try {
            List<ReportPesanan> reportPesanan = 
                    sessionFactory.getCurrentSession().createQuery(
                            "select p.faktur as faktur, "
                                    + "p.pelanggan.namaLengkap as namaPelanggan, "
                                    + "p.alamatKirim as alamatKirim, "
                                    + "p.tanggalKirim as tanggalKirim, "
                                    + "p.tanggalPesan as tanggalPesan, "
                                    + "p.ongkos as ongkos, "
                                    + "d.menu.namaMenu as namaMenu, "
                                    + "d.kuantitas as kuantitas, "
                                    + "d.harga as hargaMenu, "
                                    + "d.subTotal as subTotal "
                                    + "from "
                                    + "Pesanan p "
                                    + "join "
                                    + "p.daftarDetail d "
                                    + "order by p.tanggalKirim asc")
                    .setResultTransformer(Transformers.aliasToBean(ReportPesanan.class))
                    .list();
            
            InputStream is = KeuanganReportServiceImpl.class
                    .getResourceAsStream("/com/aan/girsang/pembukuan/report/keuangan/jasper/LaporanPesanan.jasper");
            Map<String, Object> parameters = new HashMap<String, Object>();
            System.out.println("Report Tampil");
            return JasperFillManager.fillReport(is, parameters,
                    new JRBeanCollectionDataSource(reportPesanan));
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (QueryException ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    @Override
    public JasperPrint reportPesanan(Date mulai, Date akhir) {
        try {
            List<ReportPesanan> reportPesanan = 
                    sessionFactory.getCurrentSession().createQuery(
                            "select p.faktur as faktur, "
                                    + "p.pelanggan.namaLengkap as namaPelanggan, "
                                    + "p.alamatKirim as alamatKirim, "
                                    + "p.tanggalKirim as tanggalKirim, "
                                    + "p.tanggalPesan as tanggalPesan, "
                                    + "p.ongkos as ongkos, "
                                    + "d.menu.namaMenu as namaMenu, "
                                    + "d.kuantitas as kuantitas, "
                                    + "d.harga as hargaMenu, "
                                    + "d.subTotal as subTotal "
                                    + "from "
                                    + "Pesanan p "
                                    + "join "
                                    + "p.daftarDetail d "
                                    + "where p.tanggalKirim>=:mulai and p.tanggalKirim<=:akhir "
                                    + "order by p.tanggalKirim asc")
                            .setParameter("mulai", mulai)
                            .setParameter("akhir", akhir)
                    .setResultTransformer(Transformers.aliasToBean(ReportPesanan.class))
                    .list();
            
            InputStream is = KeuanganReportServiceImpl.class
                    .getResourceAsStream("/com/aan/girsang/pembukuan/report/keuangan/jasper/LaporanPesananFilter.jasper");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("mulai", mulai);
            parameters.put("akhir", akhir);
            System.out.println("Report Tampil");
            return JasperFillManager.fillReport(is, parameters,
                    new JRBeanCollectionDataSource(reportPesanan));
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (QueryException ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    @Override
    public JasperPrint reportRekapPesananMenu(int bulan, int tahun) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, bulan);
        calendar.set(Calendar.YEAR, tahun);
        Date date = calendar.getTime();
        
        String tgl = new SimpleDateFormat("MM yyyy").format(date);
        String tgl1 = new SimpleDateFormat("MMMM yyyy").format(date);
        try {
            List<ReportRekapPesanan> reportPesanan = 
                    sessionFactory.getCurrentSession().createQuery(
                            "select d.menu.namaMenu as namaMenu, "
                                    + "sum(d.kuantitas) as kuantiti, "
                                    + "sum(d.subTotal) as subTotal "
                                    + "from "
                                    + "Pesanan p "
                                    + "join "
                                    + "p.daftarDetail d "
                                    + "where TO_CHAR(p.tanggalKirim, 'MM yyyy') LIKE :bulan "
                                    + "group by d.menu.namaMenu "
                                    + "order by d.menu.namaMenu asc")
                            .setParameter("bulan","%"+tgl+"%")
                    .setResultTransformer(Transformers.aliasToBean(ReportRekapPesanan.class))
                    .list();
            
            InputStream is = KeuanganReportServiceImpl.class
                    .getResourceAsStream("/com/aan/girsang/pembukuan/report/pesanan/jasper/LaporanRekapPesananMenu.jasper");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("bulan",tgl1);
            System.out.println("Report Tampil");
            return JasperFillManager.fillReport(is, parameters,
                    new JRBeanCollectionDataSource(reportPesanan));
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (QueryException ex){
            ex.printStackTrace();
        }
        
        return null;
    }

    @Override
    public JasperPrint reportpengeluaran(int bulan, int tahun) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, bulan);
        calendar.set(Calendar.YEAR, tahun);
        Date date = calendar.getTime();
        
        String tgl = new SimpleDateFormat("MM yyyy").format(date);
        String tgl1 = new SimpleDateFormat("MMMM yyyy").format(date);
        try {
            List<ReportPengeluaran> reportPesanan = 
                    sessionFactory.getCurrentSession().createQuery(
                            "select b.tanggal as tanggal, "
                                    + "b.faktur as faktur, "
                                    + "b.keterangan as keterangan, "
                                    + "b.total as total "
                                    + "from "
                                    + "Belanja b "
                                    + "where TO_CHAR(b.tanggal, 'MM yyyy') LIKE :bulan "
                                    + "order by b.tanggal asc")
                            .setParameter("bulan","%"+tgl+"%")
                    .setResultTransformer(Transformers.aliasToBean(ReportPengeluaran.class))
                    .list();
            
            InputStream is = KeuanganReportServiceImpl.class
                    .getResourceAsStream("/com/aan/girsang/pembukuan/report/pengeluaran/jasper/LaporanRekapPengeluaran.jasper");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("date",tgl1);
            System.out.println("Report Tampil");
            return JasperFillManager.fillReport(is, parameters,
                    new JRBeanCollectionDataSource(reportPesanan));
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (QueryException ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    
}
