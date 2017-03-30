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
public class Main {

    public static void main(String args[]) 
    {
        ArrayList<Object> lisObj = new ArrayList<Object>();
        ArrayList<Term> lisTerm = new ArrayList<Term>();
        ArrayList<Var> lisVar = new ArrayList<Var>();


        MakeTerm mk = new MakeTerm();
        Term t2 = mk.makeTerm("p /\\ E_{(p == p)}^{q} ");
        Term t1 = mk.makeTerm("p  /\\  (p  \\/ (p  ==  p))");
        Term v = mk.makeTerm("p \\/ q == (q ==> true)");
        Term v1 = mk.makeTerm("r");
        Term a = mk.makeApp("p", "r");

        
        System.out.println(t1.toStringInFin());
        System.out.println("+++++++++++++----------+++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println(t1.toStringInf());
        System.out.println("++++++++++++---------------++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println(t1.toString());
        System.out.println("++++++++++++---------------++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
      


    }
}