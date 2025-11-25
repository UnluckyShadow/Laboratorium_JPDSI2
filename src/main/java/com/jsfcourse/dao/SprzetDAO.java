package com.jsfcourse.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsfcourse.entities.Sprzet;

@Stateless
public class SprzetDAO {

    private final static String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Sprzet sprzet) {
        em.persist(sprzet);
    }

    public Sprzet merge(Sprzet sprzet) {
        return em.merge(sprzet);
    }

    public void remove(Sprzet sprzet) {
        em.remove(em.merge(sprzet));
    }

    public Sprzet find(Object id) {
        return em.find(Sprzet.class, id);
    }

    public List<Sprzet> getFullList() {
        List<Sprzet> list = null;
        Query query = em.createQuery("select s from Sprzet s");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Sprzet> getList(Map<String, Object> searchParams) {
        List<Sprzet> list = null;

        String select = "select s ";
        String from = "from Sprzet s ";
        String where = "";
        String orderby = "order by s.nazwa asc";

        String nazwa = (String) searchParams.get("nazwa");
        if (nazwa != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "s.nazwa like :nazwa ";
        }

        String kategoria = (String) searchParams.get("kategoria");
        if (kategoria != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "s.kategoria like :kategoria ";
        }

        Query query = em.createQuery(select + from + where + orderby);

        if (nazwa != null) {
            query.setParameter("nazwa", nazwa + "%");
        }
        if (kategoria != null) {
            query.setParameter("kategoria", kategoria + "%");
        }

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}