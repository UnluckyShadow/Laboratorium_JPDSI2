/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author mateusz
 */
@Entity
@Table(name = "sprzet")
@NamedQueries({
    @NamedQuery(name = "Sprzet.findAll", query = "SELECT s FROM Sprzet s"),
    @NamedQuery(name = "Sprzet.findById", query = "SELECT s FROM Sprzet s WHERE s.id = :id"),
    @NamedQuery(name = "Sprzet.findByNazwa", query = "SELECT s FROM Sprzet s WHERE s.nazwa = :nazwa"),
    @NamedQuery(name = "Sprzet.findByKategoria", query = "SELECT s FROM Sprzet s WHERE s.kategoria = :kategoria"),
    @NamedQuery(name = "Sprzet.findByCenaDzienna", query = "SELECT s FROM Sprzet s WHERE s.cenaDzienna = :cenaDzienna"),
    @NamedQuery(name = "Sprzet.findByDostepny", query = "SELECT s FROM Sprzet s WHERE s.dostepny = :dostepny"),
    @NamedQuery(name = "Sprzet.findByZdjecie", query = "SELECT s FROM Sprzet s WHERE s.zdjecie = :zdjecie"),
    @NamedQuery(name = "Sprzet.findByDataDodania", query = "SELECT s FROM Sprzet s WHERE s.dataDodania = :dataDodania")})
public class Sprzet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nazwa")
    private String nazwa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "kategoria")
    private String kategoria;
    @Lob
    @Size(max = 65535)
    @Column(name = "opis")
    private String opis;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cena_dzienna")
    private BigDecimal cenaDzienna;
    @Column(name = "dostepny")
    private Boolean dostepny;
    @Size(max = 255)
    @Column(name = "zdjecie")
    private String zdjecie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_dodania")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDodania;
    @Lob
    @Size(max = 65535)
    @Column(name = "specyfikacja")
    private String specyfikacja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sprzetId")
    private Collection<Rezerwacje> rezerwacjeCollection;

    public Sprzet() {
    }

    public Sprzet(Integer id) {
        this.id = id;
    }

    public Sprzet(Integer id, String nazwa, String kategoria, BigDecimal cenaDzienna, Date dataDodania) {
        this.id = id;
        this.nazwa = nazwa;
        this.kategoria = kategoria;
        this.cenaDzienna = cenaDzienna;
        this.dataDodania = dataDodania;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public BigDecimal getCenaDzienna() {
        return cenaDzienna;
    }

    public void setCenaDzienna(BigDecimal cenaDzienna) {
        this.cenaDzienna = cenaDzienna;
    }

    public Boolean getDostepny() {
        return dostepny;
    }

    public void setDostepny(Boolean dostepny) {
        this.dostepny = dostepny;
    }

    public String getZdjecie() {
        return zdjecie;
    }

    public void setZdjecie(String zdjecie) {
        this.zdjecie = zdjecie;
    }

    public Date getDataDodania() {
        return dataDodania;
    }

    public void setDataDodania(Date dataDodania) {
        this.dataDodania = dataDodania;
    }

    public String getSpecyfikacja() {
        return specyfikacja;
    }

    public void setSpecyfikacja(String specyfikacja) {
        this.specyfikacja = specyfikacja;
    }

    public Collection<Rezerwacje> getRezerwacjeCollection() {
        return rezerwacjeCollection;
    }

    public void setRezerwacjeCollection(Collection<Rezerwacje> rezerwacjeCollection) {
        this.rezerwacjeCollection = rezerwacjeCollection;
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
        if (!(object instanceof Sprzet)) {
            return false;
        }
        Sprzet other = (Sprzet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Sprzet[ id=" + id + " ]";
    }
    
}
