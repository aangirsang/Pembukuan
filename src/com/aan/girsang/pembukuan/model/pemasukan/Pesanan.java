package com.aan.girsang.pembukuan.model.pemasukan;

import com.aan.girsang.pembukuan.model.master.Pelanggan;
import com.aan.girsang.pembukuan.model.pengeluaran.Belanja;
import com.aan.girsang.pembukuan.model.report.JurnalUmumModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Pesanan implements Serializable {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    
    private String faktur;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="tanggal_pesan")
    private Date tanggalPesan;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="tanggal_kirim")
    private Date tanggalKirim;

    @ManyToOne
    @JoinColumn(name = "pelanggan")
    private Pelanggan pelanggan;

    @Column(name="alamat_kirim")
    private String alamatKirim;

    @Column(nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal ongkos = BigDecimal.ZERO;
    
    @OneToOne(mappedBy = "pesanan", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Belanja belanja;

    @OneToMany(mappedBy = "pesanan", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<PesananDetail> daftarDetail = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getFaktur() {
        return faktur;
    }
    
    public void setFaktur(String faktur) {
        this.faktur = faktur;
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
    
    public Pelanggan getPelanggan() {
        return pelanggan;
    }
    
    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }
    
    public String getAlamatKirim() {
        return alamatKirim;
    }
    
    public void setAlamatKirim(String alamatKirim) {
        this.alamatKirim = alamatKirim;
    }
    
    public BigDecimal getTotal() {
        return total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public BigDecimal getOngkos() {
        return ongkos;
    }
    
    public void setOngkos(BigDecimal ongkos) {
        this.ongkos = ongkos;
    }

    public Belanja getBelanja() {
        return belanja;
    }

    public void setBelanja(Belanja belanja) {
        this.belanja = belanja;
        if(belanja!=null){
            JurnalUmumModel jurnal = new JurnalUmumModel();
            belanja.setPesanan(this);
            belanja.setFaktur(faktur);
            belanja.setKeterangan("ONGKOS KIRIM KE " + this.alamatKirim);
            belanja.setTanggal(this.tanggalKirim);
            belanja.setTotal(this.ongkos);
            belanja.setJurnalUmumModel(jurnal);
        }
    }
    
    public List<PesananDetail> getDaftarDetail() {
        return daftarDetail;
    }
    
    public void setDaftarDetail(List<PesananDetail> detail) {
        this.daftarDetail = detail;
        if(detail !=null && !detail.isEmpty()){
            for(PesananDetail details : detail){
                JurnalUmumModel jurnal = new JurnalUmumModel();
                details.setPesanan(this);
                details.setTanggal(this.tanggalKirim);
                details.setJurnalUmumModel(jurnal);
            }
        }
    }
    public void addDetail(PesananDetail detail){
        if(daftarDetail==null){
            daftarDetail = new ArrayList<>();
        }
        daftarDetail.add(detail);
        detail.setPesanan(this);
    }
    public void removeDetail(PesananDetail detail){
        if(daftarDetail==null){
            daftarDetail = new ArrayList<>();
        }
        daftarDetail.remove(detail);
        detail.setPesanan(null);
    }
//</editor-fold>
}
