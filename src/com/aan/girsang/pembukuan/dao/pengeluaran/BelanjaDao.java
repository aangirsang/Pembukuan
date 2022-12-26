package com.aan.girsang.pembukuan.dao.pengeluaran;

import com.aan.girsang.pembukuan.dao.BaseDaoHibernate;
import com.aan.girsang.pembukuan.model.pengeluaran.Belanja;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

@Repository
public class BelanjaDao extends BaseDaoHibernate<Belanja>{
    public Belanja cariBelanjaId(String id){
        Belanja b = (Belanja) sessionFactory.getCurrentSession().get(Belanja.class, id);
        if(b!=null){
            Hibernate.initialize(b.getDaftarDetail());
        }
        return b;
    }
    public Belanja cariFaktur(String faktur){
        Belanja b =  (Belanja) sessionFactory.getCurrentSession()
                .createQuery("from Belanja b where b.faktur =:faktur")
                .setString("faktur", faktur)
                .uniqueResult();
        if(b!=null){
            Hibernate.initialize(b.getDaftarDetail());
        }
        return b;
    }
    public List<Belanja> semuaBelanja(){
        return sessionFactory.getCurrentSession()
                .createQuery("from Belanja b order by b.tanggal Desc")
                .list();
    }
    public List<Belanja> cariTanggalBelanja(Date mulai, Date akhir){
        return sessionFactory.getCurrentSession()
                .createQuery("from Belanja b where b.tanggal >=:mulai and "
                        + "b.tanngal<=:akhir order by b.tanggal Desc")
                .setParameter("mulai", mulai)
                .setParameter("akhir", akhir)
                .list();
    }
    public List<Belanja> cariKeterangan(String keterangan){
        return sessionFactory.getCurrentSession().createQuery(
                "from Belanja b where b.keterangan LIKE :keterangan "
                        + "order by b.tanggal Desc")
                .setParameter("keterangan", "%" + keterangan.toUpperCase()+ "%")
                .list();
    }
}
