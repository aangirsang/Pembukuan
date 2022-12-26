package com.aan.girsang.pembukuan.UI.pengeluaran.belanja;

import com.aan.girsang.pembukuan.model.pengeluaran.BelanjaDetail;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelModelBelanjaDetail extends AbstractTableModel{

    List<BelanjaDetail> daftarDetail;
    public TabelModelBelanjaDetail(List<BelanjaDetail> list){
        this.daftarDetail = list;
    }
    @Override
    public int getRowCount() {
        return daftarDetail.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    @Override
    public String getColumnName(int col){
        switch(col){
            case 0 : return "ID";
            case 1 : return "Belanja";
            case 2 : return "Item";
            case 3 : return "Sub Total";
            default:return"";
        }
    }
    @Override
    public Object getValueAt(int row, int col) {
        BelanjaDetail b = daftarDetail.get(row);
         switch(col){
            case 0 : return b.getId();
            case 1 : return b.getBelanja().getId();
            case 2 : return b.getItem();
            case 3 : return b.getSubTotal();
            default:return"";
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 3 : return BigDecimal.class;
            default:return String.class;
        }
    }
    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        if (columnIndex == 3) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        BelanjaDetail detail = daftarDetail.get(rowIndex);
        switch(columnIndex){
            case 3:
                BigDecimal subTotal = (BigDecimal) aValue;
                detail.setSubTotal(subTotal);
                fireTableCellUpdated(rowIndex, columnIndex); // Total may also have changed
                break;
            
        }
    }
    
}
