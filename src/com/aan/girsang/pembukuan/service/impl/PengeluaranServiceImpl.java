package com.aan.girsang.pembukuan.service.impl;

import com.aan.girsang.pembukuan.dao.master.RunningNumberDao;
import com.aan.girsang.pembukuan.dao.pengeluaran.BelanjaDao;
import com.aan.girsang.pembukuan.dao.pengeluaran.PengeluaranDao;
import com.aan.girsang.pembukuan.model.pengeluaran.Belanja;
import com.aan.girsang.pembukuan.model.pengeluaran.BelanjaDetail;
import com.aan.girsang.pembukuan.model.pengeluaran.Pengeluaran;
import com.aan.girsang.pembukuan.model.report.JurnalUmumModel;
import com.aan.girsang.pembukuan.model.runingnumber.RunningNumberEnum;
import com.aan.girsang.pembukuan.service.PengeluaranService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service("PengeluaranService")
@Transactional(readOnly = true)
public class PengeluaranServiceImpl implements PengeluaranService{

    @Autowired BelanjaDao belanjaDao;
    @Autowired PengeluaranDao pengeluaranDao;
    @Autowired RunningNumberDao runningNumberDao;
    
    //<editor-fold defaultstate="collapsed" desc="Belanja">
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void simpan(Belanja b) {
        if("".equals(b.getFaktur())){
            b.setFaktur(runningNumberDao.ambilBerikutnyaDanSimpan(RunningNumberEnum.BELANJA));
        }
        String keterangan = "";
        if(!b.getDaftarDetail().isEmpty()){
            for(BelanjaDetail detail : b.getDaftarDetail()){
                if(keterangan.equals("")){
                    keterangan = "BELANJA ("+detail.getItem();
                }else{
                    keterangan = keterangan + ", " + detail.getItem();
                }
            }
            keterangan = keterangan + ")";
        }else{
            keterangan = b.getKeterangan();
        }
        
        JurnalUmumModel jurnal = b.getJurnalUmumModel();
        if(jurnal==null){
            jurnal = new JurnalUmumModel();
        }
        b.setKeterangan(keterangan);
        b.setJurnalUmumModel(jurnal);
        belanjaDao.simpan(b);
    }
    
    @Override
    @Transactional
    public void hapus(Belanja b) {
        belanjaDao.hapus(b);
    }
    
    @Override
    public Belanja cariBelanjaId(String id) {
        return belanjaDao.cariBelanjaId(id);
    }
    
    @Override
    public Belanja cariFaktur(String faktur) {
        return belanjaDao.cariFaktur(faktur);
    }
    
    @Override
    public List<Belanja> semuaBelanja() {
        return belanjaDao.semuaBelanja();
    }
    
    @Override
    public List<Belanja> cariTanggalBelanja(Date mulai, Date akhir) {
        return belanjaDao.cariTanggalBelanja(mulai, akhir);
    }
    @Override
    public List<Belanja> cariKeterangan(String keterangan){
        return belanjaDao.cariKeterangan(keterangan);
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Pengeluaran">
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void simpan(Pengeluaran p) {
        pengeluaranDao.simpan(p);
    }
    
    @Override
    @Transactional
    public void hapus(Pengeluaran p) {
        pengeluaranDao.hapus(p);
    }
    
    @Override
    public Pengeluaran cariIdPengeluaran(String id) {
        return pengeluaranDao.cariIdPengeluaran(id);
    }
    
    @Override
    public List<Pengeluaran> semuaPengeluaran() {
        return pengeluaranDao.semuaPengeluaran();
    }
    
    @Override
    public List<Pengeluaran> cariTanggalPengeluaran(Date mulai, Date akhir) {
        return pengeluaranDao.cariTanggalPengeluaran(mulai, akhir);
    }
//</editor-fold>
    
}
