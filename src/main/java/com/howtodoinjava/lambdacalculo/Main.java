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
    
    
    

    public static void main(String args[]) {
        ArrayList<Object> lisObj = new ArrayList<Object>();
        ArrayList<Term> lisTerm = new ArrayList<Term>();
        ArrayList<Var> lisVar = new ArrayList<Var>();


        MakeTerm mk = new MakeTerm();
        Term t2 = mk.makeTerm("p == (r\\/q)");
        Term t1 = mk.makeTerm("(!q == true) ==> (p== q)");


         Term v = mk.makeTerm("p \\/ q == (q ==> true)");
         Term v1 = mk.makeTerm("r");
         Term a = mk.makeApp("p","r");
         
        
        System.out.println("-----------------------------");
        System.out.println(v.toStringInf());
        System.out.println("+++++++++++++++++++++++++++++");
        System.out.println("+++++++++++++++++++++++++++++");
        System.out.println("+++++++++++++++++++++++++++++");
        
        
        

    }