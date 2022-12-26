package com.aan.girsang.pembukuan.dao.master;

import com.aan.girsang.pembukuan.dao.BaseDaoHibernate;
import com.aan.girsang.pembukuan.model.master.Menu;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MenuDao extends BaseDaoHibernate<Menu>{
    public Menu cariMenu(String idMenu){
        return (Menu) sessionFactory.getCurrentSession().get(Menu.class, idMenu);
    }
    @Override
    public List<Menu> semuaPelanggan(){
        return sessionFactory.getCurrentSession()
                .createQuery("from Menu m order by m.namaMenu Asc")
                .list();
    }
    public List<Menu> cariNamaMenu(String namaMenu){
        return sessionFactory.getCurrentSession()
                .createQuery("from Menu m where m.namaMenu LIKE :namaMenu "
                        + "order by m.namaMenu asc")
                .setParameter("namaMenu", "%" + namaMenu.toUpperCase() + "%")
                .list();
    }
}
