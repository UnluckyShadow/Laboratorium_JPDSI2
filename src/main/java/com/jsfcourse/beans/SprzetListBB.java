package com.jsfcourse.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

import com.jsfcourse.dao.SprzetDAO;
import com.jsfcourse.entities.Sprzet;

@Named
@RequestScoped
public class SprzetListBB {

    private static final String PAGE_SPRZET_EDIT = "equipmentEdit?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private String nazwa;
    private String kategoria;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    SprzetDAO sprzetDAO;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public List<Sprzet> getFullList() {
        return sprzetDAO.getFullList();
    }

    public List<Sprzet> getList() {
        List<Sprzet> list = null;

        Map<String, Object> searchParams = new HashMap<String, Object>();

        if (nazwa != null && nazwa.length() > 0) {
            searchParams.put("nazwa", nazwa);
        }
        if (kategoria != null && kategoria.length() > 0) {
            searchParams.put("kategoria", kategoria);
        }

        list = sprzetDAO.getList(searchParams);

        return list;
    }

    public String newSprzet() {
        Sprzet sprzet = new Sprzet();

        flash.put("sprzet", sprzet);

        return PAGE_SPRZET_EDIT;
    }

    public String editSprzet(Sprzet sprzet) {

        flash.put("sprzet", sprzet);

        return PAGE_SPRZET_EDIT;
    }

    public String deleteSprzet(Sprzet sprzet) {
        sprzetDAO.remove(sprzet);
        return PAGE_STAY_AT_THE_SAME;
    }
}