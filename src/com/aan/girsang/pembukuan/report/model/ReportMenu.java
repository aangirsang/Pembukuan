package com.aan.girsang.pembukuan.report.model;

import java.math.BigDecimal;

public class ReportMenu {
    private String namaMenu;
    private BigDecimal hargaMenu;

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public BigDecimal getHargaMenu() {
        return hargaMenu;
    }

    public void setHargaMenu(BigDecimal hargaMenu) {
        this.hargaMenu = hargaMenu;
    }
    
    
}
