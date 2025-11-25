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
    
    @Inject
    ExternalContext extcontext;
	
    @Inject
    Flash flash;

    @EJB
    SprzetDAO sprzetDAO;

    public List<Sprzet> getList() {
        return sprzetDAO.getFullList();
    }
}
