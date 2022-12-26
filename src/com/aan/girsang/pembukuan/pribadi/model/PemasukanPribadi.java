package com.aan.girsang.pembukuan.pribadi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class PemasukanPribadi implements Serializable {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    
     @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    
    @Column(nullable = false)
    private BigDecimal total=BigDecimal.ZERO;
    
    private String keterangan;
    
    @OneToOne(mappedBy = "pemasukanPribadi", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private JurnalUmumPribadi jurnalUmumPribadi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public JurnalUmumPribadi getJurnalUmumPribadi() {
        return jurnalUmumPribadi;
    }

    public void setJurnalUmumPribadi(JurnalUmumPribadi jurnalUmumPribadi) {
        this.jurnalUmumPribadi = jurnalUmumPribadi;
        if(jurnalUmumPribadi!=null){
            jurnalUmumPribadi.setPemasukanPribadi(this);
            jurnalUmumPribadi.setDebit(total);
            jurnalUmumPribadi.setKeterangan(keterangan);
            jurnalUmumPribadi.setTanggal(tanggal);
        }
    }
    
    
}
