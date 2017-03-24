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
        Term t = mk.makeTerm("!(p\\/q) ==> !(!p\\/!p)");
        Term t1 = mk.makeTerm("true");
        Term t3 = mk.makeTerm("!p");
        
        Tokenizar tok = new Tokenizar();
        tok.tokenizacion("Hola(p,q)");
        
        System.out.println("t inst " + (t instanceof App));
        System.out.println("t.p inst "+ (((App) t).p instanceof App));
        System.out.println("t.q inst "+(((App) t).q instanceof App));
        System.out.println(t.toStringInf());
        //System.out.println( "p-- "+ ((App) t).p.toStringInf());
        //System.out.println( "p-- "+ ((App)((App) t).p).p.toStringInf());
        //System.out.println( "q-- "+ ((App) t).q.toStringInf());
        
    }
}
