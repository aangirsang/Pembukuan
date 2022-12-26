package com.aan.girsang.pembukuan.dao.pemasukan;

import com.aan.girsang.pembukuan.dao.BaseDaoHibernate;
import com.aan.girsang.pembukuan.model.pemasukan.Pemasukan;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PemasukanDao extends BaseDaoHibernate<Pemasukan>{
    public Pemasukan cariIdPemasukan(String id){
        return (Pemasukan) sessionFactory.getCurrentSession().get(Pemasukan.class, id);
    }
    public Pemasukan cariItem(String item){
        return (Pemasukan) sessionFactory.getCurrentSession().createQuery(
                "from Pemasukan p where p.item=:item order by p.tanggal Desc")
                .setParameter("item", item);
    }
    public List<Pemasukan> semuaPemasukan(){
        return sessionFactory.getCurrentSession().createQuery(
                "from Pemasukan p order by p.tanggal Desc")
                .list();
    }
    public List<Pemasukan> cariTanggalPemasukan(Date mulai, Date akhir){
        return sessionFactory.getCurrentSession().createQuery(
                "from pemasukan p where p.tanggal >=: mulai and "
                        + "p.tanggal<=:akhir "
                        + "by p.tanggal Desc")
                .list();
    }
}
