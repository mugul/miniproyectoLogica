/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.lambdacalculo;

import com.howtodoinjava.controller.PerfilController;
import com.howtodoinjava.entity.Categoria;
import com.howtodoinjava.entity.Dispone;
import com.howtodoinjava.entity.Metateorema;
import com.howtodoinjava.entity.Resuelve;
import com.howtodoinjava.entity.Teorema;
import com.howtodoinjava.entity.TerminoId;
import com.howtodoinjava.entity.Usuario;
import com.howtodoinjava.forms.AgregarTeorema;
import com.howtodoinjava.forms.InfersForm;
import com.howtodoinjava.forms.UsuarioGuardar;
import com.howtodoinjava.parse.IsNotInDBException;
import com.howtodoinjava.parse.TermLexer;
import com.howtodoinjava.parse.TermParser;
import com.howtodoinjava.service.CategoriaManager;
import com.howtodoinjava.service.DisponeManager;
import com.howtodoinjava.service.MetateoremaManager;
import com.howtodoinjava.service.ResuelveManager;
import com.howtodoinjava.service.TeoremaManager;
import com.howtodoinjava.service.TerminoManager;
import com.howtodoinjava.service.UsuarioManager;
import java.util.ArrayList;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.springframework.util.SerializationUtils;

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
        Term t = mk.makeTerm("p == p");
        Term t1 = mk.makeTerm("q == q");
//        Term t3 = mk.makeTerm("!p");

//        Tokenizar tok = new Tokenizar();
//        tok.tokenizacion("Hola(p,q)");

        Term termFin = new App(new App((new Const("\\equiv")), t1), t);



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






        // De aqui pabajo es miguel probando =D

        UsuarioManager usuarioManager = null;
        TerminoManager terminoManager = null;
        ResuelveManager resuelveManager = null;
        DisponeManager disponeManager = null;
        TeoremaManager teoremaManager = null;
        MetateoremaManager metateoremaManager = null;
        CategoriaManager categoriaManager = null;

//            AgregarTeorema agregarTeorema = new AgregarTeorema(String teorema, String categoria, String numeroTeorema, String nombreTeorema)
        AgregarTeorema agregarTeorema = new AgregarTeorema("(p == p) == true", "3", "3.25", "El 3.25");
        String username = "admin";

        Usuario user = usuarioManager.getUsuario(username);

        TerminoId terminoid2 = new TerminoId();
        terminoid2.setLogin(username);


        ANTLRStringStream in = new ANTLRStringStream(agregarTeorema.getTeorema());
        TermLexer lexer = new TermLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TermParser parser = new TermParser(tokens);
        Term teoTerm;
        try //si la sintanxis no es correcta ocurre una Exception
        {

            teoTerm = parser.start_rule(terminoid2, terminoManager);
            teoTerm.setAlias(0);
            // inicializando pa q no ladille java
            Term izq = null;
            Term der = null;
            boolean esEq = true;

            try {
                Const relation = (Const) ((App) ((App) teoTerm).p).p;
                esEq = relation.getCon().trim().equals("\\equiv");
                izq = ((App) teoTerm).q;
                der = ((App) ((App) teoTerm).p).q;
            } catch (java.lang.ClassCastException e) {
                esEq = false;
            }

            if (!esEq) {
                izq = teoTerm;
                der = new Const("true");
            }


            // Este teorema sera utilizado para ver si ya existe en la BD
            Teorema teorema2 = teoremaManager.getTeoremaByEnunciados(izq.toString(), der.toString());
            if (teorema2 != null) {
                System.out.println("el teorema ya existe");
            } else {
                // Este teorema sera utilizado para ver si el inverso ya existe en la BD
                Teorema teorema3 = teoremaManager.getTeoremaByEnunciados(der.toString(), izq.toString());
                if (teorema3 != null) {
                    System.out.println("el teorema ya existe aplicando conmutatividad (p == q) == (q == p)");
                }
            }


            Categoria categoria = categoriaManager.getCategoria(new Integer(agregarTeorema.getCategoria()));
            if (categoria == null) {
                categoria = new Categoria(agregarTeorema.getCategoria());
                categoriaManager.addCategoria(categoria);
            }


//              public Teorema(Categoria categoria, String enunciadoizq, String enunciadoder, byte[] teoserializadoizq, byte[] teoserializadoder, boolean ocultartrue, boolean esquema) {
            Teorema teorema = new Teorema(categoria, izq.traducBD().toStringFinal(), der.traducBD().toStringFinal(), SerializationUtils.serialize(izq), SerializationUtils.serialize(der), !esEq, false);
            teoremaManager.addTeorema(teorema);

            Resuelve resuelve = new Resuelve(user, teorema, agregarTeorema.getNombreTeorema(), agregarTeorema.getNumeroTeorema(), false);
            resuelveManager.addResuelve(resuelve);

            // public Metateorema(int id, Categoria categoria, String enunciadoizq, String enunciadoder, String metateoserializadoizq, String metateoserializadoder, boolean ocultartrue)                
            Metateorema metateorema = new Metateorema(teorema.getId(), categoria, teoTerm.traducBD().toStringFinal(), "true", SerializationUtils.serialize(teoTerm), SerializationUtils.serialize("true"), false);
            metateoremaManager.addMetateorema(metateorema);

            // public Dispone(int id, Usuario usuario, Metateorema metateorema, String numerometateorema, boolean resuelto)
            Dispone dispone = new Dispone(resuelve.getId(), user, metateorema, agregarTeorema.getNumeroTeorema(), false);
            disponeManager.addDispone(dispone);

            System.out.println("Su teorema ha sido guardado con exito");

            System.out.println("Todas las categorias son:");
            System.out.println(categoriaManager.getAllCategorias());


        } catch (IsNotInDBException e) {
            String hdr = parser.getErrorHeader(e);
            String msg = parser.getErrorMessage(e, TermParser.tokenNames);
            System.out.println("Hubo el error:" + msg);
        } catch (RecognitionException e) {
            String hdr = parser.getErrorHeader(e);
            String msg = parser.getErrorMessage(e, TermParser.tokenNames);
            System.out.println("Hubo el error:" + msg);
        }
    }
}
