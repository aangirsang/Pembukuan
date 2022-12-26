package com.aan.girsang.pembukuan.pribadi.service;

import com.aan.girsang.pembukuan.pribadi.model.PemasukanPribadi;
import com.aan.girsang.pembukuan.pribadi.model.PengeluaranPribadi;
import java.util.List;

public interface PribadiService {
    public void simpan(PemasukanPribadi pemasukanPribadi);
    public void hapus(PemasukanPribadi pemasukanPribadi);
    public PemasukanPribadi cariidPemasukan(String id);
    public List<PemasukanPribadi> semuaPemasukanPribadi();
    
    public void simpan(PengeluaranPribadi pengeluaranPribadi);
    public void hapus(PengeluaranPribadi pengeluaranPribadi);
    public PengeluaranPribadi cariidPengeluaran(String id);
    public List<PengeluaranPribadi> semuaPengeluaranPribadi();
}
