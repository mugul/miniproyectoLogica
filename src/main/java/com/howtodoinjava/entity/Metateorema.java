package com.howtodoinjava.entity;
// Generated Mar 20, 2017 12:50:11 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Metateorema generated by hbm2java
 */
public class Metateorema  implements java.io.Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue( strategy= GenerationType.SEQUENCE, generator="metateorema_id_seq")
    @SequenceGenerator(name="metateorema_id_seq", sequenceName="metateorema_id_seq")
     private int id;
     private Categoria categoria;
     private String enunciadoizq;
     private String enunciadoder;
     private byte[] metateoserializadoizq;
     private byte[] metateoserializadoder;
     private boolean ocultartrue;
     private Set dispones = new HashSet(0);

    public Metateorema() {
    }

    public Metateorema(int id, Categoria categoria, String enunciadoizq, String enunciadoder, byte[] metateoserializadoizq, byte[] metateoserializadoder, boolean ocultartrue) {
        this.id = id;
        this.categoria = categoria;
        this.enunciadoizq = enunciadoizq;
        this.enunciadoder = enunciadoder;
        this.metateoserializadoizq = metateoserializadoizq;
        this.metateoserializadoder = metateoserializadoder;
        this.ocultartrue = ocultartrue;
    }
    
    public Metateorema(Categoria categoria, String enunciadoizq, String enunciadoder, byte[] metateoserializadoizq, byte[] metateoserializadoder, boolean ocultartrue) {
        this.categoria = categoria;
        this.enunciadoizq = enunciadoizq;
        this.enunciadoder = enunciadoder;
        this.metateoserializadoizq = metateoserializadoizq;
        this.metateoserializadoder = metateoserializadoder;
        this.ocultartrue = ocultartrue;
    }


    public byte[] getMetateoserializadoizq() {
        return metateoserializadoizq;
    }

    public byte[] getMetateoserializadoder() {
        return metateoserializadoder;
    }

    public void setMetateoserializadoizq(byte[] metateoserializadoizq) {
        this.metateoserializadoizq = metateoserializadoizq;
    }

    public void setMetateoserializadoder(byte[] metateoserializadoder) {
        this.metateoserializadoder = metateoserializadoder;
    }

    
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getEnunciadoizq() {
        return this.enunciadoizq;
    }
    
    public void setEnunciadoizq(String enunciadoizq) {
        this.enunciadoizq = enunciadoizq;
    }
    public String getEnunciadoder() {
        return this.enunciadoder;
    }
    
    public void setEnunciadoder(String enunciadoder) {
        this.enunciadoder = enunciadoder;
    }
    public boolean isOcultartrue() {
        return this.ocultartrue;
    }
    
    public void setOcultartrue(boolean ocultartrue) {
        this.ocultartrue = ocultartrue;
    }
    public Set getDispones() {
        return this.dispones;
    }
    
    public void setDispones(Set dispones) {
        this.dispones = dispones;
    }




}


