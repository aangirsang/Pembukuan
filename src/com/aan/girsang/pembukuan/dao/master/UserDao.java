package com.aan.girsang.pembukuan.dao.master;

import com.aan.girsang.pembukuan.dao.BaseDaoHibernate;
import com.aan.girsang.pembukuan.model.master.Pengguna;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDaoHibernate<Pengguna>{
    
    public Pengguna cariIdUser(String id){
        return (Pengguna) sessionFactory.getCurrentSession().get(Pengguna.class, id);
    }
    public Pengguna cariUsername(String username){
        return (Pengguna) sessionFactory.getCurrentSession().createQuery(
                "from Pengguna u where u.username=:username")
                .setParameter("username", username)
                .uniqueResult();
    }
    public List<Pengguna> cariUser(String username){
        return sessionFactory.getCurrentSession()
                .createQuery("from Pengguna i where i.username LIKE :username "
                        + "order by i.username asc")
                .setParameter("username", "%" + username.toUpperCase() + "%")
                .list();
    }
    public List<Pengguna> semuaUser(){
        return sessionFactory.getCurrentSession().createQuery(
                "from Pengguna u order by u.username Asc")
                .list();
    }
    
}
