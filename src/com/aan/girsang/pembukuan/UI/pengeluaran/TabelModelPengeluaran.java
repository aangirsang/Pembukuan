package com.aan.girsang.pembukuan.UI.pengeluaran;

import com.aan.girsang.pembukuan.model.pengeluaran.Pengeluaran;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelModelPengeluaran extends AbstractTableModel{

    List<Pengeluaran> daftarPengeluaran;
    public TabelModelPengeluaran(List<Pengeluaran> list){
        this.daftarPengeluaran = list;
    }
    @Override
    public int getRowCount() {
        return daftarPengeluaran.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }
    @Override
    public String getColumnName(int col){
        switch(col){
            case 1:return"ID";
            case 2:return"Tanggal";
            case 3:return"Item";
            case 4:return"Harga";
            case 5:return"Kuantiti";
            case 6:return"Subtotal";
            case 7:return"Keterangan";
            default:return"";
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        Pengeluaran p = daftarPengeluaran.get(row);
        switch(col){
            default:return"";
        }
    }
    
}
