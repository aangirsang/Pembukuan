package com.aan.girsang.pembukuan.UI.pemasukan.pesanan;

import com.aan.girsang.pembukuan.model.pemasukan.Pesanan;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelModelPesanan extends AbstractTableModel{

    List<Pesanan> daftarPesanan;
    public TabelModelPesanan(List<Pesanan> list){
        this.daftarPesanan = list;
    }
    @Override
    public int getRowCount() {
        return daftarPesanan.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }
    @Override
    public String getColumnName(int col){
        switch(col){
            case 0 : return "ID";
            case 1 : return "Tanggal Pesan";
            case 2 : return "Tanggal Kirim";
            case 3 : return "Faktur";
            case 4 : return "Pelanggan";
            case 5 : return "Alamat Kirim";
            case 6 : return "Ongkos";
            case 7 : return "Total";
            default:return "";
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        Pesanan p = daftarPesanan.get(row);
        switch(col){
            case 0 : return p.getId();
            case 1 : return p.getTanggalPesan();
            case 2 : return p.getTanggalKirim();
            case 3 : return p.getFaktur();
            case 4 : return p.getPelanggan().getNamaLengkap();
            case 5 : return p.getAlamatKirim();
            case 6 : return p.getOngkos();
            case 7 : return p.getTotal();
            default:return "";
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 1 : return Date.class;
            case 2 : return Date.class;
            case 6 : return BigDecimal.class;
            case 7 : return BigDecimal.class;
            default:return String.class;
        }
    }
    
}
