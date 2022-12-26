package com.aan.girsang.pembukuan.UI.Master.user;

import com.aan.girsang.pembukuan.model.master.Pengguna;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelModelUser extends AbstractTableModel{

    List<Pengguna> daftarUser;
    public TabelModelUser(List<Pengguna> list){
        this.daftarUser = list;
    }
    @Override
    public int getRowCount() {
        return daftarUser.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    @Override
    public String getColumnName(int col){
        switch(col){
            case (0):return "ID";
            case (1):return "Username";
            case (2):return "Password";
            default:return"";
        }
    }
    @Override
    public Object getValueAt(int row, int col) {
        Pengguna u = daftarUser.get(row);
        switch(col){
            case (0):return u.getId();
            case (1):return u.getUsername();
            case (2):return u.getPass();
            default:return"";
        }
    }
    
}
