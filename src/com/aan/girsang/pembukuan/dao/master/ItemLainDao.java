package com.aan.girsang.pembukuan.dao.master;

import com.aan.girsang.pembukuan.dao.BaseDaoHibernate;
import com.aan.girsang.pembukuan.model.master.ItemLain;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ItemLainDao extends BaseDaoHibernate<ItemLain>{
    public ItemLain cariItem(String idItemLain){
        ItemLain i = (ItemLain) sessionFactory.getCurrentSession().get(ItemLain.class, idItemLain);
        return i;
    }
    @Override
    public List<ItemLain> semuaPelanggan(){
        return sessionFactory.getCurrentSession()
                .createQuery("from ItemLain i order By i.namaItem asc")
                .list();
    }
    public List<ItemLain> cariNamaItem(String namaItem){
        return sessionFactory.getCurrentSession()
                .createQuery("from ItemLain i where i.namaItem LIKE :namaItem "
                        + "order by i.namaItem asc")
                .setParameter("namaItem", "%" + namaItem.toUpperCase() + "%")
                .list();
    }
}
