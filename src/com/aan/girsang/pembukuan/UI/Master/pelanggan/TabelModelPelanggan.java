package com.aan.girsang.pembukuan.UI.Master.pelanggan;

import com.aan.girsang.pembukuan.model.master.Pelanggan;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelModelPelanggan extends AbstractTableModel{

    List<Pelanggan> daftarPelanggan;
    public TabelModelPelanggan(List<Pelanggan> list){
        this.daftarPelanggan = list;
    }
    @Override
    public int getRowCount() {
        return daftarPelanggan.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
     @Override
    public String getColumnName(int column){
        switch (column){
            case 0:return "ID";
            case 1:return "Nama Pelanggan";
            case 2:return "Alamat";
            case 3:return "No. HP";
            case 4:return "Sosmed";
            default:return"";
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        Pelanggan p = daftarPelanggan.get(row);
        switch (col){
            case 0:return p.getId();
            case 1:return p.getNamaLengkap();
            case 2:return p.getAlamat();
            case 3:return p.getHp();
            case 4:return p.getSosmed();
            default:return"";
        }
    }
    
}
