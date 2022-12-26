package com.aan.girsang.pembukuan.pribadi.dao;

import com.aan.girsang.pembukuan.dao.BaseDaoHibernate;
import com.aan.girsang.pembukuan.pribadi.model.PengeluaranPribadi;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PengeluaranPribadiDao extends BaseDaoHibernate<PengeluaranPribadi>{
    
    public PengeluaranPribadi cariId(String id){
        return (PengeluaranPribadi) sessionFactory.getCurrentSession()
                .get(PengeluaranPribadi.class, id);
    }
    
    public List<PengeluaranPribadi> semuaPengeluaranPribadi(){
        return sessionFactory.getCurrentSession().createQuery(
                "from PengeluaranPribadi p order by p.tanggal Desc")
                .list();
    }
    
}
