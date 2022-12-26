package com.aan.girsang.pembukuan.service.impl;

import com.aan.girsang.pembukuan.dao.pemasukan.PemasukanDao;
import com.aan.girsang.pembukuan.dao.pemasukan.PesananDao;
import com.aan.girsang.pembukuan.dao.master.RunningNumberDao;
import com.aan.girsang.pembukuan.dao.pengeluaran.BelanjaDao;
import com.aan.girsang.pembukuan.model.master.Menu;
import com.aan.girsang.pembukuan.model.master.Pelanggan;
import com.aan.girsang.pembukuan.model.pemasukan.Pesanan;
import com.aan.girsang.pembukuan.model.pemasukan.PesananDetail;
import com.aan.girsang.pembukuan.model.pengeluaran.Belanja;
import com.aan.girsang.pembukuan.model.report.JurnalUmumModel;
import com.aan.girsang.pembukuan.model.runingnumber.RunningNumberEnum;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.aan.girsang.pembukuan.service.PemasukanService;
import com.aan.girsang.pembukuan.util.TextComponentUtils;
import org.hibernate.SessionFactory;

@Service("PemasukanService")
@Transactional(readOnly = true)
public class PemasukanServiceImpl implements PemasukanService{

    @Autowired private SessionFactory sessionFactory;
    @Autowired RunningNumberDao runningNumberDao;
    @Autowired PesananDao pesananDao;
    @Autowired PemasukanDao pemasukanDao;
    @Autowired BelanjaDao belanjaDao;
    
    
    //<editor-fold defaultstate="collapsed" desc="Pesanan">
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void simpan(Pesanan pesanan) {
        if("".equals(pesanan.getFaktur())){
            pesanan.setFaktur(runningNumberDao.ambilBerikutnyaDanSimpan(RunningNumberEnum.PESANAN));
            pesanan.getBelanja().setFaktur(pesanan.getFaktur());
        }
        
        pesananDao.merge(pesanan);
    }
    
    @Override
    @Transactional
    public void hapus(Pesanan pesanan) {
        pesananDao.hapus(pesanan);
    }
    
    @Override
    public Pesanan cariPesananID(String id) {
        return pesananDao.cariPesananID(id);
    }
    
    @Override
    public Pesanan cariPesananFaktur(String faktur) {
        return pesananDao.cariPesananFaktur(faktur);
    }
    
    @Override
    public List<Pesanan> semuaPesanan() {
        return pesananDao.semuaPesanan();
    }
    
    @Override
    public List<Pesanan> pesananPelanggan(Pelanggan pelanggan) {
        return pesananDao.pesananPelanggan(pelanggan);
    }
    
    @Override
    public List<Pesanan> pesananTanggal(Date mulai, Date akhir) {
        return pesananDao.pesananTanggal(mulai, akhir);
    }
    @Override
    public List<Pesanan> cariNamaPelanggan(String nama){
        return pesananDao.cariNamaPelanggan(nama);
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="PesananDetail">
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void simpan(PesananDetail p) {
        JurnalUmumModel jurnal = new JurnalUmumModel();
        if(p.getJurnalUmumModel()==null){
            jurnal = new JurnalUmumModel();
        }
        p.setJurnalUmumModel(jurnal);
        sessionFactory.getCurrentSession().saveOrUpdate(p);
    }
    
    @Override
    @Transactional
    public void hapus(PesananDetail p) {
        sessionFactory.getCurrentSession().delete(p);
    }
    
    
    @Override
    public PesananDetail cariIDPesananDetail(String id) {
        return pesananDao.cariIDPesananDetail(id);
    }
    
    @Override
    public List<PesananDetail> semuaPesananDetail() {
        return pesananDao.semuaPesananDetail();
    }
    
    @Override
    public List<PesananDetail> cariMenu(Menu menu) {
        return pesananDao.cariMenu(menu);
    }
    @Override
    public List<PesananDetail> cariItem(String item){
        return pesananDao.cariItem(item);
    }
//</editor-fold>
    
}
