package com.aan.girsang.pembukuan.dao.pemasukan;

import com.aan.girsang.pembukuan.dao.BaseDaoHibernate;
import com.aan.girsang.pembukuan.model.master.Menu;
import com.aan.girsang.pembukuan.model.master.Pelanggan;
import com.aan.girsang.pembukuan.model.pemasukan.Pesanan;
import com.aan.girsang.pembukuan.model.pemasukan.PesananDetail;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

@Repository
public class PesananDao extends BaseDaoHibernate<Pesanan>{
    public Pesanan cariPesananID(String id){
       Pesanan p = (Pesanan) sessionFactory.getCurrentSession().get(Pesanan.class, id);
       if(p!=null){
           Hibernate.initialize(p.getDaftarDetail());
           for(PesananDetail detail : p.getDaftarDetail()){
               Hibernate.initialize(detail.getMenu());
           }
       }
       return p;
    }
    public Pesanan cariPesananFaktur(String faktur){
        return (Pesanan) sessionFactory.getCurrentSession().createQuery(
                "from Pesanan p where p.faktur =:faktur")
                .setParameter("faktur", faktur);
    }
    public List<Pesanan> semuaPesanan(){
        return sessionFactory.getCurrentSession().createQuery(
                "from Pesanan p order by p.tanggalPesan Desc")
                .list();
    }
    public List<Pesanan> pesananPelanggan(Pelanggan pelanggan){
        return sessionFactory.getCurrentSession().createQuery(
                "from Pesanan p where p.pelanggan=:pelanggan order by p.tanggalPesan Desc")
                .setParameter("pelanggan", pelanggan)
                .list();
    }
    public List<Pesanan> pesananTanggal(Date mulai, Date akhir){
        return sessionFactory.getCurrentSession().createQuery(
                "from Pesanan p where p.tanggalKirim>=:mulai "
                        + "and p.tanggalKirim<=:akhir "
                        + "order by p.tanggalKirim Desc")
                .setParameter("mulai", mulai)
                .setParameter("akhir", akhir)
                .list();
    }
    public List<Pesanan> cariNamaPelanggan(String nama){
        return sessionFactory.getCurrentSession().createQuery(
                "from Pesanan p where p.pelanggan.namaLengkap LIKE :nama "
                        + "order by p.tanggalPesan Desc")
                .setParameter("nama", "%" + nama + "%")
                .list();
    }
    public PesananDetail cariIDPesananDetail(String id){
        return (PesananDetail) sessionFactory.getCurrentSession()
                .get(PesananDetail.class, id);
    }
    public List<PesananDetail> semuaPesananDetail(){
        return sessionFactory.getCurrentSession().createQuery(
                "from PesananDetail p order by p.tanggal Desc")
                .list();
    }
    public List<PesananDetail> cariMenu(Menu menu){
        return sessionFactory.getCurrentSession().createQuery(
                "from PesananDetail p where p.menu =:menu "
                        + "order by p.pesanan.tanggalPesan Desc")
                .setParameter("menu", menu)
                .list();
    }
    public List<PesananDetail> cariItem(String item){
        return sessionFactory.getCurrentSession().createQuery(
                "from PesananDetail p where p.item LIKE :item "
                        + "order by p.tanggal Desc")
                .setParameter("item","%" + item.toUpperCase()+"%")
                .list();
    }
}
