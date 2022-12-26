package com.aan.girsang.pembukuan.model.pemasukan;

import com.aan.girsang.pembukuan.model.master.Menu;
import com.aan.girsang.pembukuan.model.report.JurnalUmumModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class PesananDetail implements Serializable {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    
    @ManyToOne
    @JoinColumn(name="pesanan")
    private Pesanan pesanan;
    
    @ManyToOne
    @JoinColumn(name="menu")
    private Menu menu;
    
    private String item="-";
    
    @Column(nullable = false)
    private Integer kuantitas = 0;
    
    @Column(nullable = false)
    private BigDecimal harga=BigDecimal.ZERO;
    
    @Column(nullable = false)
    private BigDecimal subTotal=BigDecimal.ZERO;
    
    @OneToOne(mappedBy = "pesananDetail", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private JurnalUmumModel jurnalUmumModel;

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
    
    public Pesanan getPesanan() {
        return pesanan;
    }
    
    public void setPesanan(Pesanan pesanan) {
        this.pesanan = pesanan;
    }
    
    public Menu getMenu() {
        return menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    
    public Integer getKuantitas() {
        return kuantitas;
    }
    
    public void setKuantitas(Integer kuantitas) {
        this.kuantitas = kuantitas;
    }
    
    public BigDecimal getHarga() {
        return harga;
    }
    
    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }
    
    public BigDecimal getSubTotal() {
        return subTotal;
    }
    
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public JurnalUmumModel getJurnalUmumModel() {
        return jurnalUmumModel;
    }

    public void setJurnalUmumModel(JurnalUmumModel jurnalUmumModel) {
        this.jurnalUmumModel = jurnalUmumModel;
        if(jurnalUmumModel!=null){
            jurnalUmumModel.setPesananDetail(this);
            jurnalUmumModel.setKeterangan(item);
            jurnalUmumModel.setTanggal(tanggal);
            jurnalUmumModel.setDebit(subTotal);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PesananDetail other = (PesananDetail) obj;
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
