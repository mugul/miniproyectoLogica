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
        ANTLRStringStream in = new ANTLRStringStream( "x2 /\\ x4");
        TermLexer lexer = new TermLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TermParser parser = new TermParser(tokens);
        Term term;

        try{
            //Iniciamos el termino     
            term = parser.start_rule(terminoid,terminoManager);
            
            //Tokenizamos el nombre introducido
            Tokenizar tok = new Tokenizar();
            tok.tokenizacion("hola(x3,x4,x5)");
            
            
            System.out.println(tok.vars.toString());
            String tt = tok.vars.remove(0);
            
            Brackear brack = new Brackear();
            Term t = brack.appBrack(tok.vars, term);
            
            System.out.println(t.toString());
            
            //System.out.println(term.maxVar());
            //System.out.println(term.occur(new Var(4)));
            System.out.println(t.kappa().toString());

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
