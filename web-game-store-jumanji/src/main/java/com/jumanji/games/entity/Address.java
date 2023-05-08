package com.jumanji.games.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Address {
    @Id
    private String cep;
    private String street;
    private String complement;
    private String neighborhood;
    private String locality;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    protected Address() {
    }

    public Address(String cep){
        this.cep = cep;
    }

    public Address(String cep, String street, String complement, String neighborhood, String locality, String uf, String ibge, String gia, String ddd, String siafi) {
        this.cep = cep;
        this.street = street;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.locality = locality;
        this.uf = uf;
        this.ibge = ibge;
        this.gia = gia;
        this.ddd = ddd;
        this.siafi = siafi;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }
}
