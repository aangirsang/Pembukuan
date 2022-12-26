package com.aan.girsang.pembukuan.service;

import com.aan.girsang.pembukuan.model.master.ItemLain;
import com.aan.girsang.pembukuan.model.master.Menu;
import com.aan.girsang.pembukuan.model.master.Pelanggan;
import com.aan.girsang.pembukuan.model.master.RunningNumber;
import com.aan.girsang.pembukuan.model.master.Pengguna;
import com.aan.girsang.pembukuan.model.runingnumber.RunningNumberEnum;
import java.util.List;

public interface MasterService {
    //<editor-fold defaultstate="collapsed" desc="Running Number">
    public void simpan(RunningNumber r);
    public List<RunningNumber> semuaRunningNumber();
    public String ambilBerikutnya(RunningNumberEnum id);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Menu">
    public void simpan(Menu menu);
    public void hapus(Menu menu);
    public Menu cariMenu(String kodeMenu);
    public List<Menu> semuaMenu();
    public List<Menu> cariNamaMenu(String namaMenu);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Item">
    public void simpan(ItemLain itemLain);
    public void hapus(ItemLain itemLain);
    public ItemLain cariItemLain(String idItemLain);
    public List<ItemLain> semuaItem();
    public List<ItemLain> cariNamaItem(String namaItem);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Pelanggan">
    public void simpan(Pelanggan pelanggan);
    public void hapus(Pelanggan pelanggan);
    public Pelanggan cariIdPelanggan(String idPelanggan);
    public List<Pelanggan> semuaPelanggan();
    public List<Pelanggan> cariNamaPelanggan(String namaLengkap);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="User">
    public void simpan(Pengguna user);
    public void hapus(Pengguna user);
    public Pengguna cariIdUser(String id);
    public Pengguna cariUsername(String username);
    public List<Pengguna> cariUser(String username);
    public List<Pengguna> semuaUser();
//</editor-fold>
}
