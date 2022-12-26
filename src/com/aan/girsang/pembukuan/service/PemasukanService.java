package com.aan.girsang.pembukuan.service;

import com.aan.girsang.pembukuan.model.master.Menu;
import com.aan.girsang.pembukuan.model.master.Pelanggan;
import com.aan.girsang.pembukuan.model.pemasukan.Pesanan;
import com.aan.girsang.pembukuan.model.pemasukan.PesananDetail;
import java.util.Date;
import java.util.List;

public interface PemasukanService {
    
    //<editor-fold defaultstate="collapsed" desc="Pesanan">
    public void simpan(Pesanan pesanan);
    public void hapus(Pesanan pesanan);
    public Pesanan cariPesananID(String id);
    public Pesanan cariPesananFaktur(String faktur);
    public List<Pesanan> semuaPesanan();
    public List<Pesanan> pesananPelanggan(Pelanggan pelanggan);
    public List<Pesanan> pesananTanggal(Date mulai, Date akhir);
    public List<Pesanan> cariNamaPelanggan(String nama);
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Pesanan Detail">
    public void simpan(PesananDetail p);
    public void hapus(PesananDetail p);
    public PesananDetail cariIDPesananDetail(String id);
    public List<PesananDetail> semuaPesananDetail();
    public List<PesananDetail> cariMenu(Menu menu);
    public List<PesananDetail> cariItem(String item);
//</editor-fold>
}
