package com.howtodoinjava.entity;
// Generated Feb 28, 2017 9:07:39 PM by Hibernate Tools 3.2.1.GA



/**
 * Solucion generated by hbm2java
 */
public class Solucion  implements java.io.Serializable {


     private SolucionId id;
     private Resuelve resuelve;

    public Solucion() {
    }

    public Solucion(SolucionId id, Resuelve resuelve) {
       this.id = id;
       this.resuelve = resuelve;
    }
   
    public SolucionId getId() {
        return this.id;
    }
    
    public void setId(SolucionId id) {
        this.id = id;
    }
    public Resuelve getResuelve() {
        return this.resuelve;
    }
    
    public void setResuelve(Resuelve resuelve) {
        this.resuelve = resuelve;
    }




}


