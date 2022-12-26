package com.aan.girsang.pembukuan.report.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ReportJurnalUmum {
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktu;
    
    private String keterangan;
    private BigDecimal debit = BigDecimal.ZERO;
    private BigDecimal kredit = BigDecimal.ZERO;

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
    public Date getTanggal() {
        Date date = new Date();
        try {
            SimpleDateFormat formater = new SimpleDateFormat("MMMM d, yyyy");
            System.out.println("TANGGAL YG DI KIRIM QUERY  "+formater.format(tanggal));
            String string = formater.format(tanggal);
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy");
            date = format.parse(string);
            System.out.println("TANGGAL YG DI KIRIM KE LAPORAN  "+date);
        } catch (ParseException ex) {
            Logger.getLogger(ReportJurnalUmum.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
    
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Date getWaktu() {
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
        return waktu = new Time(waktu.getTime());
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }
    
    public String getKeterangan() {
        return keterangan;
    }
    
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
    public BigDecimal getDebit() {
        return debit;
    }
    
    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }
    
    public BigDecimal getKredit() {
        return kredit;
    }
    
    public void setKredit(BigDecimal kredit) {
        this.kredit = kredit;
    }
//</editor-fold>
}
