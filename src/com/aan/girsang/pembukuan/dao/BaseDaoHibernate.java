/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aan.girsang.pembukuan.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author GIRSANG PC
 */

public class BaseDaoHibernate<T> {

    @SuppressWarnings("unchecked")
    protected Class domainClass;

    @Autowired(required = true)
    protected SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public BaseDaoHibernate() {
        this.domainClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void simpan(T domain) {
        sessionFactory.getCurrentSession().saveOrUpdate(domain);
    }

    public void merge(T domain) {
        sessionFactory.getCurrentSession().merge(domain);
    }

    public void hapus(T domain) {
        sessionFactory.getCurrentSession().delete(domain);
    }

    @SuppressWarnings("unchecked")
    public Long hitung() {
        List list = sessionFactory.getCurrentSession().createQuery(
                "select count(*) from " + domainClass.getName() + " x").list();
        Long count = (Long) list.get(0);
        return count;
    }

    @SuppressWarnings("unchecked")
    public List<T> semuaPelanggan() {
        return sessionFactory.getCurrentSession().createQuery("from " + domainClass.getName()).list();
    }

}
