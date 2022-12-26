package com.aan.girsang.pembukuan.UI.pemasukan.pesanan;

import com.aan.girsang.pembukuan.model.pemasukan.PesananDetail;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelModelPesananDetail extends AbstractTableModel{

    DialogPesanan dp = new DialogPesanan();
    List<PesananDetail> daftarDetail;
    public TabelModelPesananDetail(List<PesananDetail> list){
        this.daftarDetail=list;
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
            case 1:return "Pesanan";
            case 2:return "Menu";
            case 3:return "Harga";
            case 4:return "Kuantiti";
            case 5:return "Sub Total";
            default:return "";
        }
    }
    @Override
    public Object getValueAt(int row, int col) {
        PesananDetail p = daftarDetail.get(row);
        switch (col){
            case 0:return p.getId();
            case 1:return p.getPesanan();
            case 2:return p.getMenu().getNamaMenu();
            case 3:return p.getHarga();
            case 4:return p.getKuantitas();
            case 5:return p.getSubTotal();
            default:return "";
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 3 : return BigDecimal.class;
            case 4 : return Integer.class;
            case 5 : return BigDecimal.class;
            default:return String.class;
        }
    }
    @Override
        public boolean isCellEditable(int row, int columnIndex) {
            if (columnIndex == 3 ||
                    columnIndex == 4) {
                return true;
            } else {
                return false;
            }
        }
         @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            PesananDetail detail = daftarDetail.get(rowIndex);
            switch(columnIndex){
                case 3:
                    BigDecimal harga = (BigDecimal) aValue;
                    detail.setHarga(harga);
                    detail.setSubTotal(harga.multiply(
                            new BigDecimal(detail.getKuantitas())));
                    fireTableCellUpdated(rowIndex, columnIndex); // Total may also have changed
                    fireTableCellUpdated(rowIndex, 5);
                    break;
                case 4:
                    Integer kuantiti = (Integer) aValue;
                    detail.setKuantitas(kuantiti);
                    detail.setSubTotal(detail.getHarga().multiply(
                            new BigDecimal(kuantiti)));
                    fireTableCellUpdated(rowIndex, columnIndex); // Total may also have changed
                    fireTableCellUpdated(rowIndex, 5);
                    break;
            }
        }
}
