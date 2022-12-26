package com.aan.girsang.pembukuan.report.model;

import java.math.BigDecimal;

public class ReportRekapPesanan {
    private String namaMenu;
    private Long kuantiti;
    private BigDecimal subTotal;

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
    public String getMenu() {
        return namaMenu;
    }
    
    public void setMenu(String menu) {
        this.namaMenu = menu;
    }
    
    public Long getKuantiti() {
        return kuantiti;
    }
    
    public void setKuantiti(Long kuantiti) {
        this.kuantiti = kuantiti;
    }
    
    public BigDecimal getSubTotal() {
        return subTotal;
    }
    
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
//</editor-fold>
    
}
