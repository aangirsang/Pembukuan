package com.aan.girsang.pembukuan.model.master;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class ItemLain implements Serializable {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    
    @Column(nullable = false, unique = true)
    private String namaItem;

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNamaItem() {
        return namaItem;
    }
    
    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }
//</editor-fold>
}
