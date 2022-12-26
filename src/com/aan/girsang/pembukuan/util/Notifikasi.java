/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aan.girsang.pembukuan.util;

import javax.swing.JOptionPane;

/**
 *
 * @author Girsang
 */
public class Notifikasi {
    public static void pesanInformasi(String pesan){
        JOptionPane.showMessageDialog(null, 
                pesan,
                PesanJO.pesan.INFORMASI_TITLE,
                JOptionPane.INFORMATION_MESSAGE);
    }
    public static void pesanTidakAdaData(){
        JOptionPane.showMessageDialog(null, 
                PesanJO.pesan.TIDAK_ADA_DATA, 
                PesanJO.pesan.PERHATIAN_TITLE, 
                JOptionPane.ERROR_MESSAGE);
    }
    public static void pesanDataSudahAda(){
        JOptionPane.showMessageDialog(null, 
                PesanJO.pesan.DATA_SUDAH_ADA, 
                PesanJO.pesan.PERHATIAN_TITLE, 
                JOptionPane.ERROR_MESSAGE);
    }
    public static void pesanHapusError(){
        JOptionPane.showMessageDialog(null, 
                PesanJO.pesan.HAPUS_ERROR, 
                PesanJO.pesan.PERHATIAN_TITLE ,
                JOptionPane.ERROR_MESSAGE);
    }
    public static void pesanValidasiSimpan(){
        JOptionPane.showMessageDialog(null, 
                PesanJO.pesan.DATA_TIDAK_LENGKAP, 
                PesanJO.pesan.PERHATIAN_TITLE ,
                JOptionPane.ERROR_MESSAGE);
    }
    public static Boolean pesanValidasiHapus(){
        String ObjButtons[] = {"Ya", "Tidak"};
        int option = JOptionPane.showOptionDialog(null, 
                PesanJO.pesan.HAPUS_DATA, 
                PesanJO.pesan.KONFIRMASI_TITLE,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,ObjButtons,ObjButtons[1]);
        if(option==JOptionPane.YES_OPTION){
            return true;
        }
        return false;
    }
}
