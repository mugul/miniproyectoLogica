/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.lambdacalculo;


import com.howtodoinjava.forms.InfersForm;
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
        Term t = mk.makeTerm("p == p");
        Term t1 = mk.makeTerm("q == q");
//        Term t3 = mk.makeTerm("!p");
        
//        Tokenizar tok = new Tokenizar();
//        tok.tokenizacion("Hola(p,q)");
        
        Term termFin = new App(new App((new Const("\\equiv")) , t1),t);
  
                
          
        System.out.println(termFin.toStringInf());
//        System.out.println("t.p inst "+ (((App) t).p instanceof App));
//        System.out.println("t.q inst "+(((App) t).q instanceof App));
//        System.out.println(t.toStringInf());
        //System.out.println( "p-- "+ ((App) t).p.toStringInf());
        //System.out.println( "p-- "+ ((App)((App) t).p).p.toStringInf());
        //System.out.println( "q-- "+ ((App) t).q.toStringInf());
        
//        InfersForm infer = new InfersForm();
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("hola");
        System.out.println(lista.toString());
        
    }
}
