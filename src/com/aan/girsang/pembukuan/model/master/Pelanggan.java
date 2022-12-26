package com.aan.girsang.pembukuan.model.master;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Pelanggan implements Serializable {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    
    @Column(nullable = false)
    private String namaLengkap;
    private String alamat;
    private String hp;
    private String sosmed;

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNamaLengkap() {
        return namaLengkap;
    }
    
    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }
    
    public String getAlamat() {
        return alamat;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public String getHp() {
        return hp;
    }
    
    public void setHp(String hp) {
        this.hp = hp;
    }
    
    public String getSosmed() {
        return sosmed;
    }
    
    public void setSosmed(String sosmed) {
        this.sosmed = sosmed;
    }
//</editor-fold>
}
