package com.aan.girsang.pembukuan.pribadi.service.impl;

import com.aan.girsang.pembukuan.pribadi.dao.PemasukanPribadiDao;
import com.aan.girsang.pembukuan.pribadi.dao.PengeluaranPribadiDao;
import com.aan.girsang.pembukuan.pribadi.model.JurnalUmumPribadi;
import com.aan.girsang.pembukuan.pribadi.model.PemasukanPribadi;
import com.aan.girsang.pembukuan.pribadi.model.PengeluaranPribadi;
import com.aan.girsang.pembukuan.pribadi.service.PribadiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service("PribadiService")
@Transactional(readOnly = true)
public class PribadiServiceImpl implements PribadiService{
    
    @Autowired PemasukanPribadiDao pemasukanPribadiDao;
    @Autowired PengeluaranPribadiDao pengeluaranPribadiDao;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void simpan(PemasukanPribadi pemasukanPribadi) {
        JurnalUmumPribadi jurnal = pemasukanPribadi.getJurnalUmumPribadi();
        if(jurnal==null){
            jurnal = new JurnalUmumPribadi();
        }
        pemasukanPribadi.setJurnalUmumPribadi(jurnal);
        
        pemasukanPribadiDao.simpan(pemasukanPribadi);
    }

    @Override
    @Transactional
    public void hapus(PemasukanPribadi pemasukanPribadi) {
        pemasukanPribadiDao.hapus(pemasukanPribadi);
    }

    @Override
    public PemasukanPribadi cariidPemasukan(String id) {
        return pemasukanPribadiDao.cariid(id);
    }
    @Override
    public List<PemasukanPribadi> semuaPemasukanPribadi(){
        return pemasukanPribadiDao.semuaPemasukanPribadi();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void simpan(PengeluaranPribadi pengeluaranPribadi) {
        JurnalUmumPribadi jurnal = pengeluaranPribadi.getJurnalUmumPribadi();
        if(jurnal==null){
            jurnal = new JurnalUmumPribadi();
        }
        
        pengeluaranPribadi.setJurnalUmumPribadi(jurnal);
        
        pengeluaranPribadiDao.simpan(pengeluaranPribadi);
    }

    @Override
    @Transactional
    public void hapus(PengeluaranPribadi pengeluaranPribadi) {
        pengeluaranPribadiDao.hapus(pengeluaranPribadi);
    }

    @Override
    public PengeluaranPribadi cariidPengeluaran(String id) {
        return pengeluaranPribadiDao.cariId(id);
    }
    @Override
    public List<PengeluaranPribadi> semuaPengeluaranPribadi(){
        return pengeluaranPribadiDao.semuaPengeluaranPribadi();
    }
    
}
