/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.forms;

import java.util.ArrayList;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author shamuel
 */
public class InfersForm {
    
    @NotEmpty(message="no debe dejar este campo vacío")
     private String pasoAnt;     
//     @NotEmpty(message="no debe dejar este campo vacío")
     private int nStatement;
     @NotEmpty(message="no debe dejar este campo vacío")
     private String instanciacion;
     @NotEmpty(message="no debe dejar este campo vacío")
     private String leibniz;
     private ArrayList<String> historial;

    public  InfersForm() {
        this.historial = new ArrayList<String>();
    }

    public ArrayList<String> getHistorial() {
        return this.historial;
    }

    public void setHistorial(ArrayList<String> historial) {
        this.historial = historial;
    }

//    public void addHistorial(ArrayList)
    
    
    public InfersForm(String pasoAnt, int nStatement, String instanciacion, String leibniz) {
        this.pasoAnt = pasoAnt;
        this.nStatement = nStatement;
        this.instanciacion = instanciacion;
        this.leibniz = leibniz;
        this.historial = new ArrayList<String>();
    }

    public void setPasoAnt(String pasoAnt) {
        this.pasoAnt = pasoAnt;
    }

    public void setnStatement(int nStatement) {
        this.nStatement = nStatement;
    }

    public void setInstanciacion(String instanciacion) {
        this.instanciacion = instanciacion;
    }

    public void setLeibniz(String leibniz) {
        this.leibniz = leibniz;
    }

    public String getPasoAnt() {
        return pasoAnt;
    }

    public int getnStatement() {
        return nStatement;
    }

    public String getInstanciacion() {
        return instanciacion;
    }

    public String getLeibniz() {
        return leibniz;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.pasoAnt != null ? this.pasoAnt.hashCode() : 0);
        hash = 19 * hash + this.nStatement;
        hash = 19 * hash + (this.instanciacion != null ? this.instanciacion.hashCode() : 0);
        hash = 19 * hash + (this.leibniz != null ? this.leibniz.hashCode() : 0);
        return hash;
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
        if (this.nStatement != other.nStatement) {
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


}
