/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.lambdacalculo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author federico
 */
public class I extends Term {
    //final String i;

    public I() {}
    
    /*public I(String i) {
        this.i = i;
    }

    public String getCon() {
        return i;
    }*/
 
    public boolean occur(Var x)
    {
        return false;
    }
    
    public Term sust(Var x,Term t)
    {
        return this;
    }
    
   
    
    public int setAlias(int currentAlia)
    {
        if(alias != null)
        {
            alias = alias+"@"+currentAlia;
            currentAlia++;
        }
        
        return currentAlia;
    }
 
    public int maxVar()
    {
        return -1;
    }
    
    public Tipo esRedex()
    {
        return new Tipo(false,false);
    }
    
    public Tipo esRedexFinal()
    {
        return new Tipo(false,false,false);
    }
    
    public Redex buscarRedexIzq(Term itexto,boolean p)
    {
        return null;
    }
    
    public Redex buscarRedexIzqFinal(Term itexto,boolean p)
    {
        return null;
    }
    
    public Term traducBD()
    {
        return this;
    }
    
    public List<Term> itandotraducBD()
    {
        List<Term> list=new ArrayList<Term>();
        
        return list;
    }
    
    public Term invBraBD()
    {
        return null;
    }
    
    public Term invBD()
    {
        return null;
    }
    
    public Term invBDOneStep()
    {
        return null;
    }
    
    public Term bracketAbsBD(Var x)
    {
        return new App(new Const("\\Phi_{K}"),this);
    }
    
    public Term bracketAbsSH(Var x)
    {
        return new App(new Const("K"),this);
    }
    
    public String toString()
    {
        return "I";
    }
   
        @Override
    public String toStringInFin() {
        return "I";
    }     
        
    @Override
    public String toStringInf() {
        return "I";
    }

    
    public ToString toStringAbrvV1(ToString toString)
    {
        toString.term=this.toString();
        return toString;
    }
    
    public ToString toStringAbrv(ToString toString)
    {
        toString.term=this.toString();
        return toString;
    }

        
    @Override
    protected Object clone() throws CloneNotSupportedException{
        return this;
    }

    @Override
    public Term sustParall(ArrayList<Var> Vars, ArrayList<Term> varsTerm) {
        return this;
    }

    @Override
    public Term checkApp() {
        return this;
    }

    @Override
    public List<Term> contandotraducBD() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
