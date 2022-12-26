package com.aan.girsang.pembukuan.model.pengeluaran;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Pengeluaran implements Serializable {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    
    private String item;
    
    @Column(nullable = false)
    private BigDecimal harga = BigDecimal.ZERO;
    
    @Column(nullable = false)
    private BigDecimal subtotal = BigDecimal.ZERO;
    
    private Integer kuantiti=0;
    
    private Belanja belanja;

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
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
    
    public String getItem() {
        return item;
    }
    
    public void setItem(String item) {
        this.item = item;
    }
    
    public BigDecimal getHarga() {
        return harga;
    }
    
    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }
    
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    public Integer getKuantiti() {
        return kuantiti;
    }
    
    public void setKuantiti(Integer kuantiti) {
        this.kuantiti = kuantiti;
    }

    public Belanja getBelanja() {
        return belanja;
    }

    public void setBelanja(Belanja belanja) {
        this.belanja = belanja;
    }
//</editor-fold>
}
