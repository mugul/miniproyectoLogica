/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.lambdacalculo;


import java.util.ArrayList;

/**
 *
 * @author federico
 */
public class Main {
    
    public static void main(String args[]){       
        ArrayList<Object> lisObj = new ArrayList<Object>();
        ArrayList<Term> lisTerm = new ArrayList<Term>();
        ArrayList<Var> lisVar = new ArrayList<Var>();


        MakeTerm mk = new MakeTerm();
        Term t = mk.makeTerm("p\\/q");
        Term t1 = mk.makeTerm("true");
        Term t3 = mk.makeTerm("p == q");
        
        Tokenizar tok = new Tokenizar();
        tok.tokenizacion("Hola(p,q)");
        
        Brackear brack = new Brackear();
        Term t2 = brack.appBrack(tok.vars, t);
        
        //Lista de Vars
        lisVar = brack.listVars(tok.vars);
        //System.out.println(lisVar.toString());
        
        //Lista de Term
        lisTerm.add(t);
        lisTerm.add(t1);
        //System.out.println(lisTerm.toString());
         
        //Imprimo termino antes de la sustParll
        //System.out.println(t3.toString());               

        //Aplicamos sustParll
        System.out.println(t3.toString());
        lisObj = mk.makeInsta("p,q := true,p\\/q");
        System.out.println(lisObj.toString());
        Term t4 = t3.sustParall((ArrayList<Var>) lisObj.get(0), (ArrayList<Term>) lisObj.get(1));
        System.out.println(t4.toString());
        
         //System.out.println(lisVar.get(0).toStringInf());
         
         //Term tx = new App(t, t1);
         //System.out.println(t3.toStringInf());
         System.out.println();
         //System.out.println(t3.toString());
         //System.out.println(t3.toString());
   
}
