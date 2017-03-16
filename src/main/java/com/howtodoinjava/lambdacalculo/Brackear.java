/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.lambdacalculo;

import com.howtodoinjava.entity.TerminoId;
import com.howtodoinjava.parse.TermLexer;
import com.howtodoinjava.parse.TermParser;
import com.howtodoinjava.service.TerminoManager;
import java.util.ArrayList;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;

/**
 *
 * @author shamuel
 */
public class Brackear {

    public Brackear() {
    }
    
    public Term appBrack(ArrayList<String> vars, Term term) throws RecognitionException{
        TerminoId terminoid = null;
        TerminoManager terminoManager = null;
        Term t;
        t = term; 
        try{

            for(int i = vars.toArray().length -1;  0 <= i ; i--){
            ANTLRStringStream in = new ANTLRStringStream(vars.get(i).toString());    
            TermLexer lexer = new TermLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TermParser parser = new TermParser(tokens);
            Term term2 = parser.start_rule(terminoid,terminoManager);
            term = new Bracket((Var) term2, term);
 
            }  
            
           Term termAux = term.traducBD();
           System.out.println(termAux.traducBD().toString());
        
        //comprobar las instancias
        return term;
        
        }catch(Exception e){                   
            System.out.println("ERROR:");
            return null;
        }
    }
        
        public ArrayList<Var>  listVars(ArrayList<String>  vars, Term term) throws RecognitionException{
        
        TerminoId terminoid = null;
        TerminoManager terminoManager = null;
        Term t;
        ArrayList<Var> auxList = new ArrayList<Var>();

            for(int i = vars.toArray().length -1;  0 <= i ; i--){
            ANTLRStringStream in = new ANTLRStringStream(vars.get(i).toString());    
            TermLexer lexer = new TermLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TermParser parser = new TermParser(tokens);
            Term term2 = parser.start_rule(terminoid,terminoManager);
            auxList.add((Var) term2);
 
            }  
            
     
        return auxList;
            
    }    
    
    
    public boolean check(Term t){
         if (t instanceof App) {
                System.out.println("a");
              
            }    else if (t instanceof Bracket) {
                                    System.out.println("b");

                    
               }else if(t instanceof Const){
                    
                                    System.out.println("c");

                    
                }else{
                                System.out.println("ulti");

                }       
        return true;
    }
}
