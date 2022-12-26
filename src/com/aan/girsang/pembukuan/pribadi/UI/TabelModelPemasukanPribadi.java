package com.aan.girsang.pembukuan.pribadi.UI;

import com.aan.girsang.pembukuan.UI.pemasukan.pesanan.*;
import com.aan.girsang.pembukuan.model.pemasukan.Pesanan;
import com.aan.girsang.pembukuan.pribadi.model.PemasukanPribadi;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelModelPemasukanPribadi extends AbstractTableModel{

    List<PemasukanPribadi> daftarPemasukan;
    public TabelModelPemasukanPribadi(List<PemasukanPribadi> list){
        this.daftarPemasukan = list;
    }
    @Override
    public int getRowCount() {
        return daftarPemasukan.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    @Override
    public String getColumnName(int col){
        switch(col){
            case 0 : return "ID";
            case 1 : return "Tanggal";
            case 2 : return "Keterangan";
            case 3 : return "Total";
            default:return "";
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        PemasukanPribadi p = daftarPemasukan.get(row);
        switch(col){
            case 0 : return p.getId();
            case 1 : return p.getTanggal();
            case 2 : return p.getKeterangan();
            case 3 : return p.getTotal();
            default:return "";
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 1 : return Date.class;
            case 3 : return BigDecimal.class;
            default:return String.class;
        }
    }
    
}
