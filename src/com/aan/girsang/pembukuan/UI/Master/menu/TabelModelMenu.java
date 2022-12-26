package com.aan.girsang.pembukuan.UI.Master.menu;

import com.aan.girsang.pembukuan.model.master.Menu;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelModelMenu extends AbstractTableModel{

    List<Menu> daftarMenu;
    public TabelModelMenu(List<Menu> menus){
        this.daftarMenu = menus;
    }
    @Override
    public int getRowCount() {
        return daftarMenu.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column){
        switch (column){
            case 0 : return "ID";
            case 1 : return "Nama Menu";
            case 2 : return "Harga";
            default:return"";
        }
    }
    @Override
    public Object getValueAt(int row, int col) {
        Menu m = daftarMenu.get(row);
        switch (col){
            case 0 : return m.getId();
            case 1 : return m.getNamaMenu();
            case 2 : return m.getHarga();
            default:return"";
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 2 : return BigDecimal.class;
            default:return String.class;
        }
    }
    
}
