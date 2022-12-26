/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aan.girsang.pembukuan.util;

/**
 *
 * @author Girsang
 */
public interface PesanJO {
    
    interface pesan {
        String PERHATIAN_TITLE = "Perhatian";
        String INFORMASI_TITLE = "Informasi";
        String KONFIRMASI_TITLE = "Konfirmasi";
        String HAPUS_ERROR = "Tidak Bisa Menghapus Data, Data Masih Digunakan Pada Tabel Lain";
        String DATA_SUDAH_ADA = "Data Sudah Ada";
        String TIDAK_ADA_DATA = "Tidak Ada Data Yang Dipilih. Silahkan Pilih Data Terlebih Dahulu";
        String TANYA_HAPUS = "Anda Yakin Ingin Menghapus Data Ini?";
        String TANYA_UBAH ="Anda Yakin Ingin Menyimpan Perubahan Data Ini?";
        String DATA_TIDAK_LENGKAP = "Data Belum Lengkap, Silahkan Lengkapi Data Terlebih Dahulu";
        String HAPUS_DATA = "Apakah Anda Yakin Ingin Menghapus Data Ini?";
    }
    
    interface namaTab{
        String MENU = "Data Menu";
        String ITEM = "Data Item";
        String PELANGGAN = "Data Pelanggan";
        String PESANAN = "Data Pesanan";
        String BELANJA = "Data Pengeluaran";
        String PENGELUARAN = "Data Pengeluaran";
        String PEMASUKAN = "Data Pemasukan";
    }
    interface namaMenu{
        String MENU = "Menu";
        String PELANGGAN = "Pelanggan";
        String ITEM = "Item";
        String PEMASUKAN = "Pemasukan";
        String PESANAN = "Pesanan";
        String PENGELUARAN = "Pengeluaran";
        String BELANJA = "Pengeluaran";
    }
    interface namaDialog{
        String MENU_TAMBAH = "Tambah Data Menu";
        String MENU_UBAH = "Ubah Data Menu";
        String MENU_PILIH = "Pilih Data Menu";
        String PELANGGAN_TAMBAH = "Tambah Data Pelanggan";
        String PELANGGAN_UBAH = "Ubah Data Pelanggan";
        String PELANGGAN_PILIH = "Pilih Data Pelanggan";
        String ITEM_TAMBAH = "Tambah Data Item";
        String ITEM_UBAH = "Ubah Data Item";
        String ITEM_PILIH = "Pilih Data Item";
        String PESANAN_RINCIAN = "Rincian Pesanan";
        String PESANAN_TAMBAH = "Tambah Data Pesanan";
        String PESANAN_EDIT = "Edit Data Pesanan";
        String PILIH_PEMBELI = "Pilih Pembeli";
        String PILIH_ITEM = "Pilih Item";
    }
    
}
