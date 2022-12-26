package com.aan.girsang.pembukuan.dao.master;

import com.aan.girsang.pembukuan.dao.BaseDaoHibernate;
import com.aan.girsang.pembukuan.model.master.Pelanggan;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PelangganDao extends BaseDaoHibernate<Pelanggan>{
    public Pelanggan cariPelangganId(String idPelanggan){
        return (Pelanggan) sessionFactory.getCurrentSession().get(Pelanggan.class, idPelanggan);
    }
    @Override
    public List<Pelanggan> semuaPelanggan(){
        return sessionFactory.getCurrentSession()
                .createQuery("from Pelanggan p order by p.namaLengkap asc")
                .list();
    }
    public List<Pelanggan> cariNamaPelanggan(String namaLengkap){
        return sessionFactory.getCurrentSession()
                .createQuery("from Pelanggan p where "
                        + "p.namaLengkap Like :namaLengkap "
                        + "order by p.namaLengkap asc")
                .setParameter("namaLengkap", "%" + namaLengkap.toUpperCase() + "%")
                .list();
    }
}
