/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aan.girsang.pembukuan.report.service.impl;

import com.aan.girsang.pembukuan.report.service.MasterReportService;
import com.aan.girsang.pembukuan.report.model.ReportMenu;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ITSUSAHBRO
 */
@Service("MasterReportService")
@Transactional(readOnly = true)
public class MasterReportServiceImpl implements MasterReportService{

    @Autowired SessionFactory sessionFactory;
    @Override
    public JasperPrint menuReport() {
        try {
            List<ReportMenu> reportMenu = 
                    sessionFactory.getCurrentSession().createQuery(
                            "select m.namaMenu as namaMenu, "
                                    + "m.harga as hargaMenu "
                                    + "from "
                                    + "Menu m "
                                    + "order by "
                                    + "m.namaMenu")
                    .setResultTransformer(Transformers.aliasToBean(ReportMenu.class))
                    .list();
            
            InputStream is = MasterReportServiceImpl.class
                    .getResourceAsStream("/com/aan/girsang/pembukuan/report/menu/MenuReport.jasper");
            Map<String, Object> parameters = new HashMap<String, Object>();
            System.out.println("Report Tampil");
            return JasperFillManager.fillReport(is, parameters,
                    new JRBeanCollectionDataSource(reportMenu));
        } catch (JRException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
