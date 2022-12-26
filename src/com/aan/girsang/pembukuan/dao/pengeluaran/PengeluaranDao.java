package com.aan.girsang.pembukuan.dao.pengeluaran;

import com.aan.girsang.pembukuan.dao.BaseDaoHibernate;
import com.aan.girsang.pembukuan.model.pengeluaran.Belanja;
import com.aan.girsang.pembukuan.model.pengeluaran.Pengeluaran;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PengeluaranDao extends BaseDaoHibernate<Pengeluaran>{
    public Pengeluaran cariIdPengeluaran(String id){
        return (Pengeluaran) sessionFactory.getCurrentSession()
                .get(Pengeluaran.class, id);
    }
    public Pengeluaran cariBelanja(Belanja belanja){
        return (Pengeluaran) sessionFactory.getCurrentSession().createQuery(
                "from Pengeluaran p where p.belanja =:belanja")
                .setParameter("belanja", belanja.getId());
    }
    public List<Pengeluaran> semuaPengeluaran(){
        return sessionFactory.getCurrentSession().createQuery(
                "from Pengeluaran p order by p.tanggal Desc")
                .list();
    }
    public List<Pengeluaran> cariTanggalPengeluaran(Date mulai, Date akhir){
        return sessionFactory.getCurrentSession()
                .createQuery("from Pengeluaran b where b.tanggal >=:mulai and "
                        + "b.tanngal<=:akhir order by b.tanggal Desc")
                .setParameter("mulai", mulai)
                .setParameter("akhir", akhir)
                .list();
    }
}
