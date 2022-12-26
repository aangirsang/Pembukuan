package com.aan.girsang.pembukuan.pribadi.report.service;

import java.util.Date;
import net.sf.jasperreports.engine.JasperPrint;

public interface ReportPribadiService {
    JasperPrint reportKeuanganPribadi();
    JasperPrint reportKeuanganPribadiFilter(Date mulai, Date akhir);
    JasperPrint reportRekapPengeluaranPribadi(int bulan, int tahun);
    JasperPrint reportRekapPemasukanPribadi(int bulan, int tahun);
}
