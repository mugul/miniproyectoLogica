/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.lambdacalculo;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author shamuel
 */
public class Tokenizar {

    ArrayList<String> vars;
    
    public Tokenizar() {
        vars = new ArrayList<String>();
    }
    
    public void tokenizacion(String string){
    
    StringTokenizer token, subToken;
    
    token = new StringTokenizer(string, "(");
    System.out.println(string);   
    
    string = token.nextToken();

    subToken = new StringTokenizer(token.nextToken().toString().replace(")", ""),",");    


   while( subToken.hasMoreElements()) {
       //string = subToken.nextElement();
       
       vars.add(subToken.nextElement().toString());
       System.out.println(vars.toString());
    }
    
    }
    
    
}
