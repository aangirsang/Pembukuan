package com.aan.girsang.pembukuan.UI.Master.item;

import com.aan.girsang.pembukuan.model.master.ItemLain;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelModelItem extends AbstractTableModel{

    List<ItemLain> daftarItem;
    public TabelModelItem(List<ItemLain> list){
        this.daftarItem=list;
    }
    @Override
    public int getRowCount() {
        return daftarItem.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int col){
        switch(col){
            case (0):return "ID";
            case (1):return "Nama Item";
            default:return"";
        }
    }
    @Override
    public Object getValueAt(int row, int col) {
        ItemLain i = daftarItem.get(row);
        switch(col){
            case (0):return i.getId();
            case (1):return i.getNamaItem();
            default:return"";
        }
    }
    
}
