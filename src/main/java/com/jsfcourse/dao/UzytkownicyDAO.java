package com.jsfcourse.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsfcourse.entities.Uzytkownicy;

@Stateless
public class UzytkownicyDAO {

    private final static String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Uzytkownicy uzytkownik) {
        em.persist(uzytkownik);
    }

    public Uzytkownicy merge(Uzytkownicy uzytkownik) {
        return em.merge(uzytkownik);
    }

    public void remove(Uzytkownicy uzytkownik) {
        em.remove(em.merge(uzytkownik));
    }

    public Uzytkownicy find(Object id) {
        return em.find(Uzytkownicy.class, id);
    }

    public List<Uzytkownicy> getFullList() {
        List<Uzytkownicy> list = null;
        Query query = em.createQuery("select u from Uzytkownicy u");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Uzytkownicy> getList(Map<String, Object> searchParams) {
        List<Uzytkownicy> list = null;

        String select = "select u ";
        String from = "from Uzytkownicy u ";
        String where = "";
        String orderby = "order by u.imieNazwisko asc";

        String login = (String) searchParams.get("login");
        if (login != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "u.login like :login ";
        }

        String rola = (String) searchParams.get("rola");
        if (rola != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "u.rola = :rola ";
        }

        Query query = em.createQuery(select + from + where + orderby);

        if (login != null) {
            query.setParameter("login", login + "%");
        }
        if (rola != null) {
            query.setParameter("rola", rola);
        }

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}