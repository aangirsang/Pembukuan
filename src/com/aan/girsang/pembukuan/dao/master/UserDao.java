package com.aan.girsang.pembukuan.dao.master;

import com.aan.girsang.pembukuan.dao.BaseDaoHibernate;
import com.aan.girsang.pembukuan.model.master.Akun;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDaoHibernate<Akun>{
    
    public Akun cariIdUser(String id){
        return (Akun) sessionFactory.getCurrentSession().get(Akun.class, id);
    }
    public Akun cariUsername(String username){
        return (Akun) sessionFactory.getCurrentSession().createQuery(
                "from Akun u where u.username=:username")
                .setParameter("username", username)
                .uniqueResult();
    }
    public List<Akun> cariUser(String username){
        return sessionFactory.getCurrentSession()
                .createQuery("from Akun i where i.username LIKE :username "
                        + "order by i.username asc")
                .setParameter("username", "%" + username.toUpperCase() + "%")
                .list();
    }
    public List<Akun> semuaUser(){
        return sessionFactory.getCurrentSession().createQuery(
                "from Akun u order by u.username Asc")
                .list();
    }
    
}
