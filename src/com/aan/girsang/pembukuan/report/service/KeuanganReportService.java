package com.aan.girsang.pembukuan.report.service;

import java.util.Date;
import net.sf.jasperreports.engine.JasperPrint;

public interface KeuanganReportService {
    JasperPrint reportJurnalUmum();
    JasperPrint reportJurnalUmum(Date mulai, Date akhir);
    JasperPrint reportPesanan();
    JasperPrint reportPesanan(Date mulai, Date akhir);
    JasperPrint reportRekapPesananMenu(int bulan, int tahun);
    JasperPrint reportpengeluaran(int bulan, int tahun);
}
