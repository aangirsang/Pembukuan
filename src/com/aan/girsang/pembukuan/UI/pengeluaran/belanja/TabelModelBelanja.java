package com.aan.girsang.pembukuan.UI.pengeluaran.belanja;

import com.aan.girsang.pembukuan.model.pengeluaran.Belanja;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelModelBelanja extends AbstractTableModel{

    List<Belanja> daftarBelanja;
    public TabelModelBelanja(List<Belanja> list){
        this.daftarBelanja=list;
    }
    @Override
    public int getRowCount() {
        return daftarBelanja.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public String getColumnName(int col){
        switch(col){
            case 0 : return "ID";
            case 1 : return "Tanggal";
            case 2 : return "Faktur";
            case 3 : return "Item";
            case 4 : return "Total";
            default:return"";
        }
    }
    @Override
    public Object getValueAt(int row, int col) {
        Belanja b = daftarBelanja.get(row);
        switch(col){
            case 0 : return b.getId();
            case 1 : return b.getTanggal();
            case 2 : return b.getFaktur();
            case 3 : return b.getKeterangan();
            case 4 : return b.getTotal();
            default:return"";
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 1 : return Date.class;
            case 4 : return BigDecimal.class;
            default:return String.class;
        }
    }
    
}
