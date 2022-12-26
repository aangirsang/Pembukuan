package com.aan.girsang.pembukuan.UI.pemasukan;

import com.aan.girsang.pembukuan.model.pemasukan.PesananDetail;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelModelPemasukan extends AbstractTableModel{

    List<PesananDetail> daftarDetail;
    public TabelModelPemasukan(List<PesananDetail> list){
        this.daftarDetail = list;
    }
    @Override
    public int getRowCount() {
        return daftarDetail.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }
    @Override
    public String getColumnName(int col){
        switch(col){
            case 0:return "ID";
            case 1:return "Tanggal";
            case 2:return "Item";
            case 3:return "Harga";
            case 4:return "kuantiti";
            case 5:return "Sub Total";
            default:return"";
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        PesananDetail p = daftarDetail.get(row);
        switch(col){
            case 0:return p.getId();
            case 1:return p.getTanggal();
            case 2:
                if(p.getMenu()!=null){
                    return p.getMenu().getNamaMenu();
                }else{
                    return p.getItem();
                }
            case 3:return p.getHarga();
            case 4:return p.getKuantitas();
            case 5:return p.getSubTotal();
            default:return"";
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 1 : return Date.class;
            case 4 : return Integer.class;
            case 3 : return BigDecimal.class;
            case 5 : return BigDecimal.class;
            default:return String.class;
        }
    }
    
}
