package com.aan.girsang.pembukuan.report.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ReportPesanan {
    private String faktur;
    private String namaPelanggan;
    private String alamatKirim;
    private String namaMenu;
    private BigDecimal ongkos;
    private BigDecimal hargaMenu;
    private BigDecimal subTotal;
    private Integer kuantitas;
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalPesan;
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalKirim;

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
    public String getFaktur() {
        return faktur;
    }
    
    public void setFaktur(String faktur) {
        this.faktur = faktur;
    }
    
    public String getNamaPelanggan() {
        return namaPelanggan;
    }
    
    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }
    
    public String getAlamatKirim() {
        return alamatKirim;
    }
    
    public void setAlamatKirim(String alamatKirim) {
        this.alamatKirim = alamatKirim;
    }
    
    public String getNamaMenu() {
        return namaMenu;
    }
    
    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }
    
    public BigDecimal getOngkos() {
        return ongkos;
    }
    
    public void setOngkos(BigDecimal ongkos) {
        this.ongkos = ongkos;
    }
    
    public BigDecimal getHargaMenu() {
        return hargaMenu;
    }
    
    public void setHargaMenu(BigDecimal hargaMenu) {
        this.hargaMenu = hargaMenu;
    }
    
    public BigDecimal getSubTotal() {
        return subTotal;
    }
    
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
    
    public Integer getKuantitas() {
        return kuantitas;
    }
    
    public void setKuantitas(Integer kuantitas) {
        this.kuantitas = kuantitas;
    }
    
    public Date getTanggalPesan() {
        return tanggalPesan;
    }
    
    public void setTanggalPesan(Date tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }
    
    public Date getTanggalKirim() {
        return tanggalKirim;
    }
    
    public void setTanggalKirim(Date tanggalKirim) {
        this.tanggalKirim = tanggalKirim;
    }
//</editor-fold>
    
    
}
