package com.jsfcourse.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsfcourse.dao.SprzetDAO;
import com.jsfcourse.entities.Sprzet;

@Named
@ViewScoped
public class SprzetEditBB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String PAGE_SPRZET_LIST = "equipmentList?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private Sprzet sprzet = new Sprzet();
    private Sprzet loaded = null;

    @EJB
    SprzetDAO sprzetDAO;

    @Inject
    FacesContext context;

    @Inject
    Flash flash;

    public Sprzet getSprzet() {
        return sprzet;
    }

    public void onLoad() throws IOException {
        loaded = (Sprzet) flash.get("sprzet");

        if (loaded != null) {
            sprzet = loaded;
        } else {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu - brak danych", null));
        }
    }

    public String saveData() {

        if (loaded == null) {
            return PAGE_STAY_AT_THE_SAME;
        }

        try {
            if (sprzet.getId() == null) {
                sprzetDAO.create(sprzet);
            } else {
                sprzetDAO.merge(sprzet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystąpił błąd podczas zapisu", null));
            return PAGE_STAY_AT_THE_SAME;
        }

        return PAGE_SPRZET_LIST;
    }
}