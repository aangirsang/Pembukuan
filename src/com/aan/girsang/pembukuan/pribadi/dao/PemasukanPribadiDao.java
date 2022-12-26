package com.aan.girsang.pembukuan.pribadi.dao;

import com.aan.girsang.pembukuan.dao.BaseDaoHibernate;
import com.aan.girsang.pembukuan.pribadi.model.PemasukanPribadi;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PemasukanPribadiDao extends BaseDaoHibernate<PemasukanPribadi>{
    
    public PemasukanPribadi cariid(String id){
        return (PemasukanPribadi) sessionFactory.getCurrentSession()
                .get(PemasukanPribadi.class, id);
    }
    public List<PemasukanPribadi> semuaPemasukanPribadi(){
        return sessionFactory.getCurrentSession().createQuery(
                "from PemasukanPribadi p order by p.tanggal Desc")
                .list();
    }
    
}
