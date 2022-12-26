package com.aan.girsang.pembukuan.report.model;

import java.math.BigDecimal;
import java.util.Date;

public class ReportPengeluaran {
    public Date tanggal;
    public String faktur;
    public String keterangan;
    public BigDecimal total;

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
    public Date getTanggal() {
        return tanggal;
    }
    
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    
    public String getFaktur() {
        return faktur;
    }
    
    public void setFaktur(String faktur) {
        this.faktur = faktur;
    }
    
    public String getKeterangan() {
        return keterangan;
    }
    
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
    public BigDecimal getTotal() {
        return total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
//</editor-fold>
    
}
