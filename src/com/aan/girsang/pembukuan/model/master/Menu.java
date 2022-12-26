package com.aan.girsang.pembukuan.model.master;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Menu implements Serializable {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    
    @Column(nullable = false)
    private String namaMenu;

    @Column(nullable = false)
    private BigDecimal harga;

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNamaMenu() {
        return namaMenu;
    }
    
    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }
    
    public BigDecimal getHarga() {
        return harga;
    }
    
    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }
//</editor-fold>
}
