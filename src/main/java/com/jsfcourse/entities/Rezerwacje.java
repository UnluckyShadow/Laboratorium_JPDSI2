/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author mateu
 */
@Entity
@Table(name = "rezerwacje")
@NamedQueries({
    @NamedQuery(name = "Rezerwacje.findAll", query = "SELECT r FROM Rezerwacje r"),
    @NamedQuery(name = "Rezerwacje.findById", query = "SELECT r FROM Rezerwacje r WHERE r.id = :id"),
    @NamedQuery(name = "Rezerwacje.findByKlient", query = "SELECT r FROM Rezerwacje r WHERE r.klient = :klient"),
    @NamedQuery(name = "Rezerwacje.findByEmail", query = "SELECT r FROM Rezerwacje r WHERE r.email = :email"),
    @NamedQuery(name = "Rezerwacje.findByTelefon", query = "SELECT r FROM Rezerwacje r WHERE r.telefon = :telefon"),
    @NamedQuery(name = "Rezerwacje.findByDataWypozyczenia", query = "SELECT r FROM Rezerwacje r WHERE r.dataWypozyczenia = :dataWypozyczenia"),
    @NamedQuery(name = "Rezerwacje.findByDataZwrotu", query = "SELECT r FROM Rezerwacje r WHERE r.dataZwrotu = :dataZwrotu"),
    @NamedQuery(name = "Rezerwacje.findByStatus", query = "SELECT r FROM Rezerwacje r WHERE r.status = :status")})
public class Rezerwacje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "klient")
    private String klient;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "telefon")
    private String telefon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_wypozyczenia")
    @Temporal(TemporalType.DATE)
    private Date dataWypozyczenia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_zwrotu")
    @Temporal(TemporalType.DATE)
    private Date dataZwrotu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "sprzet_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sprzet sprzetId;

    public Rezerwacje() {
    }

    public Rezerwacje(Integer id) {
        this.id = id;
    }

    public Rezerwacje(Integer id, String klient, String email, String telefon, Date dataWypozyczenia, Date dataZwrotu, String status) {
        this.id = id;
        this.klient = klient;
        this.email = email;
        this.telefon = telefon;
        this.dataWypozyczenia = dataWypozyczenia;
        this.dataZwrotu = dataZwrotu;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKlient() {
        return klient;
    }

    public void setKlient(String klient) {
        this.klient = klient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Date getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(Date dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public Date getDataZwrotu() {
        return dataZwrotu;
    }

    public void setDataZwrotu(Date dataZwrotu) {
        this.dataZwrotu = dataZwrotu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Sprzet getSprzetId() {
        return sprzetId;
    }

    public void setSprzetId(Sprzet sprzetId) {
        this.sprzetId = sprzetId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rezerwacje)) {
            return false;
        }
        Rezerwacje other = (Rezerwacje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Rezerwacje[ id=" + id + " ]";
    }
    
}
