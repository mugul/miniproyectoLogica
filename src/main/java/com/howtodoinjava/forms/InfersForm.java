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
     private String nStament;
     @NotEmpty(message="no debe dejar este campo vacío")
     private String instanciacion;
     @NotEmpty(message="no debe dejar este campo vacío")
     private String leibniz;

    public  InfersForm() {
    }

    public  InfersForm(String nombre, String algoritmo, String leib) {
       this.nStament = nombre;
       this.instanciacion = algoritmo;
       this.leibniz = leib;
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
        int hash = 7;
        hash = 73 * hash + (this.nStament != null ? this.nStament.hashCode() : 0);
        hash = 73 * hash + (this.instanciacion != null ? this.instanciacion.hashCode() : 0);
        hash = 73 * hash + (this.leibniz != null ? this.leibniz.hashCode() : 0);
        return hash;
    }
}
