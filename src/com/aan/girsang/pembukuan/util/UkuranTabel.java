package com.aan.girsang.pembukuan.util;

import javax.swing.JTable;

public class UkuranTabel extends JTable{

    JTable tabel = new JTable();

    public JTable UkuranTabelMenu(JTable tabel) {
        this.tabel = tabel;
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tabel.getColumnModel().getColumn(0).setMaxWidth(0);
        tabel.getColumnModel().getColumn(0).setMinWidth(0);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabel.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabel.getColumnModel().getColumn(2).setPreferredWidth(80);
        return tabel;
    }
    public JTable UkuranTabelPelanggan(JTable tabel){
        this.tabel = tabel;
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabel.getColumnModel().getColumn(0).setMaxWidth(0);
        tabel.getColumnModel().getColumn(0).setMinWidth(0);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabel.getColumnModel().getColumn(1).setPreferredWidth(200);//Nama Pelanggan;
        tabel.getColumnModel().getColumn(2).setPreferredWidth(200);//Alamat Pelanggan
        tabel.getColumnModel().getColumn(3).setPreferredWidth(80);//HP
        tabel.getColumnModel().getColumn(4).setPreferredWidth(200);//Sosmed
        return tabel;
    }
    public JTable UkuranTabelItem(JTable tabel){
        this.tabel = tabel;
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabel.getColumnModel().getColumn(0).setMaxWidth(0);
        tabel.getColumnModel().getColumn(0).setMinWidth(0);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(0);//ID
        tabel.getColumnModel().getColumn(1).setPreferredWidth(500);//Nama Item
        return tabel;
    }
    public JTable UkuranTabelUser(JTable tabel){
        this.tabel = tabel;
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabel.getColumnModel().getColumn(0).setMaxWidth(0);
        tabel.getColumnModel().getColumn(0).setMinWidth(0);
        tabel.getColumnModel().getColumn(2).setMaxWidth(0);
        tabel.getColumnModel().getColumn(2).setMinWidth(0);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(0);//ID
        tabel.getColumnModel().getColumn(1).setPreferredWidth(500);//Nama Item
        tabel.getColumnModel().getColumn(2).setPreferredWidth(0);//Nama Item
        return tabel;
    }
    public JTable UkuranTabelPemasukan(JTable tabel){
        this.tabel = tabel;
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabel.getColumnModel().getColumn(0).setMaxWidth(0);
        tabel.getColumnModel().getColumn(0).setMinWidth(0);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(0);//ID
        tabel.getColumnModel().getColumn(1).setPreferredWidth(150);//Tanggal
        tabel.getColumnModel().getColumn(2).setPreferredWidth(200);//Item
        tabel.getColumnModel().getColumn(3).setPreferredWidth(100);//Harga
        tabel.getColumnModel().getColumn(4).setPreferredWidth(80);//Kuantiti
        tabel.getColumnModel().getColumn(5).setPreferredWidth(100);//Sub Total
        return tabel;
    }
    public JTable UkuranTabelPesanan(JTable tabel){
        this.tabel = tabel;
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabel.getColumnModel().getColumn(0).setMaxWidth(0);
        tabel.getColumnModel().getColumn(0).setMinWidth(0);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(0);//ID
        tabel.getColumnModel().getColumn(1).setPreferredWidth(150);//Tanggal Pesan
        tabel.getColumnModel().getColumn(2).setPreferredWidth(150);//Tanggal kirim
        tabel.getColumnModel().getColumn(3).setPreferredWidth(100);//Faktur
        tabel.getColumnModel().getColumn(4).setPreferredWidth(200);//Pelanggan
        tabel.getColumnModel().getColumn(5).setPreferredWidth(250);//Alamat Kirim
        tabel.getColumnModel().getColumn(6).setPreferredWidth(100);//Ongkos
        tabel.getColumnModel().getColumn(7).setPreferredWidth(100);//Total
        return tabel;
    }
    public JTable UkuranTabelPesananDetail(JTable tabel){
        this.tabel = tabel;
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabel.getColumnModel().getColumn(0).setMaxWidth(0);
        tabel.getColumnModel().getColumn(0).setMinWidth(0);
        tabel.getColumnModel().getColumn(1).setMaxWidth(0);
        tabel.getColumnModel().getColumn(1).setMinWidth(0);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(0);//ID
        tabel.getColumnModel().getColumn(1).setPreferredWidth(0);//Pesanan
        tabel.getColumnModel().getColumn(2).setPreferredWidth(200);//Nama Menu
        tabel.getColumnModel().getColumn(3).setPreferredWidth(80);//Harga
        tabel.getColumnModel().getColumn(4).setPreferredWidth(80);//Kuantitas
        tabel.getColumnModel().getColumn(5).setPreferredWidth(80);//SubTotal
        return tabel;
    }
    public JTable UkuranTabelBelanja(JTable tabel){
        this.tabel = tabel;
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabel.getColumnModel().getColumn(0).setMaxWidth(0);
        tabel.getColumnModel().getColumn(0).setMinWidth(0);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(0);//ID
        tabel.getColumnModel().getColumn(1).setPreferredWidth(150);//Tanggal
        tabel.getColumnModel().getColumn(2).setPreferredWidth(100);//Faktur
        tabel.getColumnModel().getColumn(3).setPreferredWidth(300);//Item
        tabel.getColumnModel().getColumn(4).setPreferredWidth(100);//Harga
        return tabel;
    }
    public JTable UkuranTabelBelanjaDetail(JTable tabel){
        this.tabel = tabel;
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabel.getColumnModel().getColumn(0).setMaxWidth(0);
        tabel.getColumnModel().getColumn(0).setMinWidth(0);
        tabel.getColumnModel().getColumn(1).setMaxWidth(0);
        tabel.getColumnModel().getColumn(1).setMinWidth(0);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(0);//ID
        tabel.getColumnModel().getColumn(1).setPreferredWidth(0);//Belanja
        tabel.getColumnModel().getColumn(2).setPreferredWidth(200);//Item
        tabel.getColumnModel().getColumn(3).setPreferredWidth(80);//SubTotal
        return tabel;
    }
    public JTable UkuranTabelPribadi(JTable tabel){
        this.tabel = tabel;
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabel.getColumnModel().getColumn(0).setMaxWidth(0);
        tabel.getColumnModel().getColumn(0).setMinWidth(0);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(0);//ID
        tabel.getColumnModel().getColumn(1).setPreferredWidth(150);//Tanggal
        tabel.getColumnModel().getColumn(2).setPreferredWidth(200);//Keterangan
        tabel.getColumnModel().getColumn(3).setPreferredWidth(80);//Total
        return tabel;
    }

}
