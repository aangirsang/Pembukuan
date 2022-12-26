/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aan.girsang.pembukuan.dao.master;

import com.aan.girsang.pembukuan.model.master.RunningNumber;
import com.aan.girsang.pembukuan.model.runingnumber.RunningNumberEnum;
import com.aan.girsang.pembukuan.util.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ifnu
 */
@Repository
public class RunningNumberDao {

    @Autowired private SessionFactory sessionFactory;
    
    public void simpan(RunningNumber p){
        sessionFactory.getCurrentSession()
                .saveOrUpdate(p);
    }

    public String ambilBerikutnya(RunningNumberEnum id){
        Date tanggal = new Date();
        String strTanggal =  new SimpleDateFormat("yyMMdd").format(tanggal);
        String tahun = new SimpleDateFormat("yyyy").format(tanggal);
        RunningNumber r = (RunningNumber) sessionFactory.getCurrentSession().get(RunningNumber.class, id.getId()+tahun);
        if(r==null){
            r = new RunningNumber();
            r.setId(id.getId() + tahun);
            r.setNumber(0);
            simpan(r);
        }else{
            System.out.println("ID RUNNING NUMBER  "+id.getId()+tahun);
        }
        return id.getId()+ strTanggal + StringUtils.padWithZero(r.getNumber() + 1, id.getDigit());
    }
    public String ambilBerikutnyaDanSimpan(RunningNumberEnum id){
        Date tanggal = new Date();
        String strTanggal =  new SimpleDateFormat("yyMMdd").format(tanggal);
        String tahun = new SimpleDateFormat("yyyy").format(tanggal);
        RunningNumber r = (RunningNumber) sessionFactory.getCurrentSession().get(RunningNumber.class, id.getId()+tahun);
        if(r==null){
            r = new RunningNumber();
            r.setId(id.getId() + tahun);
            r.setNumber(1);
        } else {
            r.setNumber(r.getNumber()+1);
            System.out.println("ID RUNNING NUMBER  "+id.getId()+tahun + " SIMPAN");
        }
        sessionFactory.getCurrentSession().saveOrUpdate(r);
        return id.getId()+ strTanggal + StringUtils.padWithZero(r.getNumber(), id.getDigit());
    }
    public List<RunningNumber> semua(){
        return sessionFactory.getCurrentSession()
                .createCriteria(RunningNumber.class)
                .list();
    }
}
