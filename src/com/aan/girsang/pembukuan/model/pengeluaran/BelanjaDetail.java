package com.aan.girsang.pembukuan.model.pengeluaran;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class BelanjaDetail implements Serializable {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    
    @ManyToOne
    @JoinColumn(name="belanja")
    private Belanja belanja;
    
    @Column(nullable = false)
    private String item;
    
    @Column(nullable = false)
    private BigDecimal subTotal=BigDecimal.ZERO;

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Belanja getBelanja() {
        return belanja;
    }
    
    public void setBelanja(Belanja belanja) {
        this.belanja = belanja;
    }
    
    public String getItem() {
        return item;
    }
    
    public void setItem(String item) {
        this.item = item;
    }
    
    public BigDecimal getSubTotal() {
        return subTotal;
    }
    
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BelanjaDetail other = (BelanjaDetail) obj;
        return !((this.id == null) ? (other.id != null) : !this.id.equals(other.id));
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
//</editor-fold>
}
