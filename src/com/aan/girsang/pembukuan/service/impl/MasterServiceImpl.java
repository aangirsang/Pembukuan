package com.aan.girsang.pembukuan.service.impl;

import com.aan.girsang.pembukuan.dao.master.ItemLainDao;
import com.aan.girsang.pembukuan.dao.master.MenuDao;
import com.aan.girsang.pembukuan.dao.master.PelangganDao;
import com.aan.girsang.pembukuan.dao.master.RunningNumberDao;
import com.aan.girsang.pembukuan.dao.master.UserDao;
import com.aan.girsang.pembukuan.model.master.ItemLain;
import com.aan.girsang.pembukuan.model.master.Menu;
import com.aan.girsang.pembukuan.model.master.Pelanggan;
import com.aan.girsang.pembukuan.model.master.RunningNumber;
import com.aan.girsang.pembukuan.model.master.Pengguna;
import com.aan.girsang.pembukuan.model.runingnumber.RunningNumberEnum;
import com.aan.girsang.pembukuan.service.MasterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service("MasterService")
@Transactional(readOnly = true)
public class MasterServiceImpl implements MasterService{

    @Autowired RunningNumberDao runningNumberTransaksiDao;
    @Autowired MenuDao menuDao;
    @Autowired ItemLainDao itemDao;
    @Autowired PelangganDao pelangganDao;
    @Autowired UserDao userDao;
    
    //<editor-fold defaultstate="collapsed" desc="Running Number">
    @Override
    @Transactional
    public void simpan(RunningNumber r) {
        runningNumberTransaksiDao.simpan(r);
    }
    
    @Override
    public List<RunningNumber> semuaRunningNumber() {
        return runningNumberTransaksiDao.semua();
    }
    
    @Override
    @Transactional
    public String ambilBerikutnya(RunningNumberEnum id) {
        return runningNumberTransaksiDao.ambilBerikutnya(id);
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Menu">
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void simpan(Menu menu) {
        menuDao.simpan(menu);
    }
    
    @Override
    @Transactional
    public void hapus(Menu menu) {
        menuDao.hapus(menu);
    }
    
    @Override
    public Menu cariMenu(String kodeMenu) {
        return menuDao.cariMenu(kodeMenu);
    }
    
    @Override
    public List<Menu> semuaMenu() {
        return menuDao.semuaPelanggan();
    }
    
    @Override
    public List<Menu> cariNamaMenu(String namaMenu){
        return menuDao.cariNamaMenu(namaMenu);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="ItemLain">
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void simpan(ItemLain itemLain) {
        itemDao.simpan(itemLain);
    }
    
    @Override
    @Transactional
    public void hapus(ItemLain itemLain) {
        itemDao.hapus(itemLain);
    }
    
    @Override
    public ItemLain cariItemLain(String idItemLain) {
        return itemDao.cariItem(idItemLain);
    }
    
    @Override
    public List<ItemLain> semuaItem() {
        return itemDao.semuaPelanggan();
    }
    @Override
    public List<ItemLain> cariNamaItem(String namaItem){
        return itemDao.cariNamaItem(namaItem);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Pelanggan">
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void simpan(Pelanggan pelanggan) {
        pelangganDao.simpan(pelanggan);
    }
    
    @Override
    @Transactional
    public void hapus(Pelanggan pelanggan) {
        pelangganDao.hapus(pelanggan);
    }
    
    @Override
    public Pelanggan cariIdPelanggan(String idPelanggan) {
        return pelangganDao.cariPelangganId(idPelanggan);
    }
    
    @Override
    public List<Pelanggan> semuaPelanggan() {
        return pelangganDao.semuaPelanggan();
    }
    @Override
    public List<Pelanggan> cariNamaPelanggan(String namaLengkap){
        return pelangganDao.cariNamaPelanggan(namaLengkap);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="User">
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void simpan(Pengguna user) {
        userDao.simpan(user);
    }
    
    @Override
    @Transactional
    public void hapus(Pengguna user) {
        userDao.hapus(user);
    }
    
    @Override
    public Pengguna cariIdUser(String id) {
        return userDao.cariIdUser(id);
    }
    
    @Override
    public Pengguna cariUsername(String username) {
        return userDao.cariUsername(username);
    }
    @Override
    public List<Pengguna> cariUser(String username){
        return userDao.cariUser(username);
    }
    
    @Override
    public List<Pengguna> semuaUser() {
        return userDao.semuaUser();
    }
//</editor-fold>
    
}
