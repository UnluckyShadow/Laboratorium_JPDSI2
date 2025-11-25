package com.jsfcourse.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsfcourse.entities.Rezerwacje;

@Stateless
public class RezerwacjeDAO {

    private final static String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Rezerwacje rezerwacja) {
        em.persist(rezerwacja);
    }

    public Rezerwacje merge(Rezerwacje rezerwacja) {
        return em.merge(rezerwacja);
    }

    public void remove(Rezerwacje rezerwacja) {
        em.remove(em.merge(rezerwacja));
    }

    public Rezerwacje find(Object id) {
        return em.find(Rezerwacje.class, id);
    }

    public List<Rezerwacje> getFullList() {
        List<Rezerwacje> list = null;
        Query query = em.createQuery("select r from Rezerwacje r");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Rezerwacje> getList(Map<String, Object> searchParams) {
        List<Rezerwacje> list = null;

        String select = "select r ";
        String from = "from Rezerwacje r ";
        String where = "";
        String orderby = "order by r.dataWypozyczenia asc";

        String klient = (String) searchParams.get("klient");
        if (klient != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "r.klient like :klient ";
        }

        String status = (String) searchParams.get("status");
        if (status != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "r.status = :status ";
        }

        Query query = em.createQuery(select + from + where + orderby);

        if (klient != null) {
            query.setParameter("klient", klient + "%");
        }
        if (status != null) {
            query.setParameter("status", status);
        }

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}