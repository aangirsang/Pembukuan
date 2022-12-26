package com.aan.girsang.pembukuan.model.report;

import com.aan.girsang.pembukuan.model.pemasukan.PesananDetail;
import com.aan.girsang.pembukuan.model.pengeluaran.Belanja;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class JurnalUmumModel implements Serializable {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    
    @OneToOne
    @JoinColumn(name="pesananDetail")
    private PesananDetail pesananDetail;
    
    @OneToOne
    @JoinColumn(name="belanja")
    private Belanja belanja;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    
    private String keterangan;
    
    @Column(nullable = false)
    private BigDecimal debit = BigDecimal.ZERO;
    
    @Column(nullable = false)
    private BigDecimal kredit = BigDecimal.ZERO;

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public PesananDetail getPesananDetail() {
        return pesananDetail;
    }
    
    public void setPesananDetail(PesananDetail pesananDetail) {
        this.pesananDetail = pesananDetail;
    }
    
    public Belanja getBelanja() {
        return belanja;
    }
    
    public void setBelanja(Belanja belanja) {
        this.belanja = belanja;
    }
    
    public Date getTanggal() {
        return tanggal;
    }
    
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
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
