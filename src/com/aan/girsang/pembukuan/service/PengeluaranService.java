package com.aan.girsang.pembukuan.service;

import com.aan.girsang.pembukuan.model.pengeluaran.Belanja;
import com.aan.girsang.pembukuan.model.pengeluaran.Pengeluaran;
import java.util.Date;
import java.util.List;

public interface PengeluaranService {
    //<editor-fold defaultstate="collapsed" desc="Belanja">
    public void simpan(Belanja b);
    public void hapus(Belanja b);
    public Belanja cariBelanjaId(String id);
    public Belanja cariFaktur(String faktur);
    public List<Belanja> semuaBelanja();
    public List<Belanja> cariTanggalBelanja(Date mulai, Date akhir);
    public List<Belanja> cariKeterangan(String keterangan);
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Pengeluaran">
    public void simpan(Pengeluaran p);
    public void hapus(Pengeluaran p);
    public Pengeluaran cariIdPengeluaran(String id);
    public List<Pengeluaran> semuaPengeluaran();
    public List<Pengeluaran> cariTanggalPengeluaran(Date mulai, Date akhir);
//</editor-fold>
}
