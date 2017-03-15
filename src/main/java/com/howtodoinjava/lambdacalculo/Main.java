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
        
        /** TODO code application logic her<e
        String string = "nombre(x1,x2,x3,v,p)";
        String name;
        List<String> variables = new LinkedList<>();
        
        
        StringTokenizer token, subToken;
        token = new StringTokenizer(string,"(");
        
        string = token.nextToken();
        System.out.println(string);
        
        subToken = new StringTokenizer(token.nextToken().toString().replace(")", ""),",");    
         
         
       while( subToken.hasMoreElements()) {
           
           variables.add(subToken.nextElement().toString());
           System.out.println(variables.toString());
        } **/
        
            
        Tokenizar tok = new Tokenizar();
        tok.tokenizacion("hola(x3,x4,x5)");
        /*
         * Min 
         * Comentario
         */
        
        
        TerminoId terminoid = null;
        TerminoManager terminoManager = null;
        //tengo que copiar todo esto para usarlo
        // expresion boleana hasta ahora solo puede ser xi
        ANTLRStringStream in = new ANTLRStringStream( "x2 /\\ x4");
        //ANTLRStringStream axiom = new ANTLRFileStream("x1");
        //creamo el lexer        
        TermLexer lexer = new TermLexer(in);
        //TermLexer lexerAxio = new TermLexer(axiom);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        //CommonTokenStream tokaxiom  = new CommonTokenStream(lexerAxio);

        TermParser parser = new TermParser(tokens);
        //TermParser parseraxi = new TermParser(tokaxiom);
        //defino tipo termino
        Term term;
        try //si la sintanxis no es correcta ocurre una Exception
            {
            
            //parseo el termino     
            term=parser.start_rule(terminoid,terminoManager);
            System.out.println(term.toString());
            System.out.println(term.maxVar());
            System.out.println(term.occur(new Var(4)));
            System.out.println(term.kappa().toString());


            //Le aplico Bracket y cambio las variables, todas las variables estan indexadas
            //Term  brackTerm = new Bracket(new Var(2), term );
            Term bar = new Bracket( new Var(1),(new Bracket(new Var(4), new Bracket(new Var(2), term))));
            //System.out.println(brackTerm.toString());

             System.out.println(bar.toString());
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
