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
    this.vars.add(token.nextToken());

    subToken = new StringTokenizer(token.nextToken().toString().replace(")", ""),",");    


    while( subToken.hasMoreElements()) {
       String aux = subToken.nextElement().toString();
       try{
           if ((aux.length() < 3) && !(aux.startsWith(" ")) ) {
                vars.add(aux);
           }
        }catch(Exception e){
           System.out.println("ERROR: Introduzca nuevamente los datos");
        }           
    }    
    }
    
}
