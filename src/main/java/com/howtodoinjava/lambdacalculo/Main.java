/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.lambdacalculo;

import com.howtodoinjava.entity.TerminoId;
import com.howtodoinjava.parse.IsNotInDBException;
import com.howtodoinjava.parse.TermLexer;
import com.howtodoinjava.parse.TermParser;
import com.howtodoinjava.service.TerminoManager;
import java.sql.Array;
import java.util.ArrayList;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

/**
 *
 * @author federico
 */
public class Main {
    
    public static void main(String args[])
    {       
       
        //Iniciamos el lexer y parser
        TerminoId terminoid = null;
        TerminoManager terminoManager = null;
        ANTLRStringStream in = new ANTLRStringStream( "p /\\ q == r");
        TermLexer lexer = new TermLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TermParser parser = new TermParser(tokens);
        Term term;

        TerminoId termid = null;
        TerminoManager termManager = null;
        ANTLRStringStream in1 = new ANTLRStringStream( "t");
        TermLexer lexer1 = new TermLexer(in1);
        CommonTokenStream tokens1 = new CommonTokenStream(lexer1);
        TermParser parser1 = new TermParser(tokens1);
        Term term1;
        
        TerminoId termid1 = null;
        TerminoManager termManager1 = null;
        ANTLRStringStream in2 = new ANTLRStringStream( "p\\/q");
        TermLexer lexer2 = new TermLexer(in2);
        CommonTokenStream tokens2 = new CommonTokenStream(lexer2);
        TermParser parser2 = new TermParser(tokens2);
        Term term2;
        
        ANTLRStringStream in3 = new ANTLRStringStream("p := p \\/q");
        TermLexer lexer3 = new TermLexer(in3);
        CommonTokenStream tokens3 = new CommonTokenStream(lexer3);
        TermParser parser3 = new TermParser(tokens3);
        ArrayList<Object> arr;
        
        try{
            
            arr=parser2.instantiate();
            term = parser.start_rule(terminoid,terminoManager);
            term1 = parser.start_rule(termid,termManager);
            term2 = parser.start_rule(termid1,termManager1);
                    
            System.out.println(arr.toString());        
            ArrayList<Term> lisTerm = new ArrayList<Term>();
            lisTerm.add(term1);
            lisTerm.add(term2);
            //Iniciamos el termino   
            //System.out.println(term.toString());
            //System.out.println(term1.toString());
            //System.out.println(term2.toString());
            //Tokenizamos el nombre introducido
            Tokenizar tok = new Tokenizar();
            tok.tokenizacion("Hola(p,q)");
            
            
            //System.out.println(tok.vars.toString());
            String tt = tok.vars.remove(0);
            
            Brackear brack = new Brackear();
            
            ArrayList<Var> lis= brack.listVars(tok.vars, term);
            
            
            term.sustParall(lis, lisTerm);
            
            //System.out.println(t.toString());
            
            //System.out.println(term.maxVar());
            //System.out.println(term.occur(new Var(4)));
            

            //Le aplico Bracket y cambio las variables, todas las variables estan indexadas
            //Term  brackTerm = new Bracket(new Var(2), term );
            //Term bar = new Bracket( new Var(1),(new Bracket(new Var(4), new Bracket(new Var(2), term))));
            //System.out.println(brackTerm.toString());

            //System.out.println(bar.toString());
            /*Term combTerm = brackTerm.traducBD(); 
             System.out.println(combTerm.toString());

             Term coTer2 = (new App(combTerm, new Var(5))).reducir();

             System.out.println(coTer2.toString());
             Term aux = new App(new App(new Var(1),new Var(3)),new Const("\\in"));
             System.out.println((new App(brackTerm, aux)).reducir());

             */


        }
        catch(IsNotInDBException e)
        {
            String hdr = parser.getErrorHeader(e);
            String msg = parser.getErrorMessage(e, TermParser.tokenNames);

          /*  String hdrUno = parseraxi.getErrorHeader(e);
            String msg1 = parseraxi.getErrorMessage(e, TermParser.tokenNames);

          */
        }
       catch(RecognitionException e)
        {
            String hdr = parser.getErrorHeader(e);
            String msg = parser.getErrorMessage(e, TermParser.tokenNames);

            /*String hdr1 = parseraxi.getErrorHeader(e);
            String msg1 = parseraxi.getErrorMessage(e, TermParser.tokenNames);
            */

       }    
    }
    
}
