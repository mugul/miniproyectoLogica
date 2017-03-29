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
        Term t2 = mk.makeTerm("E_{p == p}^{q} ");
        Term t1 = mk.makeTerm("(!q == true) ==> (p== q)");

        System.out.println(t2.toStringInf());
         
                 

    }