/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.forms;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author shamuel
 */
public class InfersForm {
    
    @NotEmpty(message="no debe dejar este campo vacío")
     private String pasoAnt;     
     @NotEmpty(message="no debe dejar este campo vacío")
     private String nStament;
     @NotEmpty(message="no debe dejar este campo vacío")
     private String instanciacion;
     @NotEmpty(message="no debe dejar este campo vacío")
     private String leibniz;

    public  InfersForm() {
    }

    public InfersForm(String pasoAnt, String nStament, String instanciacion, String leibniz) {
        this.pasoAnt = pasoAnt;
        this.nStament = nStament;
        this.instanciacion = instanciacion;
        this.leibniz = leibniz;
    }

    public void setPasoAnt(String pasoAnt) {
        this.pasoAnt = pasoAnt;
    }

    public String getPasoAnt() {
        return pasoAnt;
    }

    public String getnStament() {
        return nStament;
    }

    public String getLeibniz() {
        return leibniz;
    }

    public String getInstanciacion() {
        return instanciacion;
    }

    public void setnStament(String nStament) {
        this.nStament = nStament;
    }

    public void setInstanciacion(String instanciacion) {
        this.instanciacion = instanciacion;
    }

    public void setLeibniz(String leibniz) {
        this.leibniz = leibniz;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InfersForm other = (InfersForm) obj;
        if ((this.pasoAnt == null) ? (other.pasoAnt != null) : !this.pasoAnt.equals(other.pasoAnt)) {
            return false;
        }
        if ((this.nStament == null) ? (other.nStament != null) : !this.nStament.equals(other.nStament)) {
            return false;
        }
        if ((this.instanciacion == null) ? (other.instanciacion != null) : !this.instanciacion.equals(other.instanciacion)) {
            return false;
        }
        if ((this.leibniz == null) ? (other.leibniz != null) : !this.leibniz.equals(other.leibniz)) {
            return false;
        }
        return true;
    }
   
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (this.pasoAnt != null ? this.pasoAnt.hashCode() : 0);
        hash = 41 * hash + (this.nStament != null ? this.nStament.hashCode() : 0);
        hash = 41 * hash + (this.instanciacion != null ? this.instanciacion.hashCode() : 0);
        hash = 41 * hash + (this.leibniz != null ? this.leibniz.hashCode() : 0);
        return hash;
    }
}
