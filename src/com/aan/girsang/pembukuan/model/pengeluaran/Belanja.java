package com.aan.girsang.pembukuan.model.pengeluaran;

import com.aan.girsang.pembukuan.model.pemasukan.Pesanan;
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
public class Belanja implements Serializable {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    
    @Column(unique = true)
    private String faktur;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    
    @Column(nullable = false)
    private BigDecimal total=BigDecimal.ZERO;
    
    private String keterangan;
    
    @OneToOne
    @JoinColumn(name="pesanan")
    private Pesanan pesanan;
    
    @OneToMany(mappedBy = "belanja", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<BelanjaDetail> daftarDetail = new ArrayList<>();
    
    @OneToOne(mappedBy = "belanja", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private JurnalUmumModel jurnalUmumModel;

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
    
    public List<BelanjaDetail> getDaftarDetail() {
        return daftarDetail;
    }
    
    public void setDaftarDetail(List<BelanjaDetail> daftarDetail) {
        this.daftarDetail = daftarDetail;
        if(daftarDetail !=null && !daftarDetail.isEmpty()){
            for (BelanjaDetail detail :daftarDetail){
                detail.setBelanja(this);
            }
        }
    }
    public void addDetail(BelanjaDetail detail){
        if(daftarDetail==null){
            daftarDetail = new ArrayList<>();
        }
        daftarDetail.add(detail);
        detail.setBelanja(this);
    }
    public void removeDetail(BelanjaDetail detail){
        if(daftarDetail==null){
            daftarDetail = new ArrayList<>();
        }
        daftarDetail.remove(detail);
        detail.setBelanja(null);
    }
    
//</editor-fold>

    public Pesanan getPesanan() {
        return pesanan;
    }

    public void setPesanan(Pesanan pesanan) {
        this.pesanan = pesanan;
    }

    public JurnalUmumModel getJurnalUmumModel() {
        return jurnalUmumModel;
    }

    public void setJurnalUmumModel(JurnalUmumModel jurnalUmumModel) {
        this.jurnalUmumModel = jurnalUmumModel;
        if(jurnalUmumModel!=null){
            jurnalUmumModel.setBelanja(this);
            jurnalUmumModel.setKeterangan(keterangan);
            jurnalUmumModel.setTanggal(tanggal);
            jurnalUmumModel.setKredit(total);
        }
    }
    
}
