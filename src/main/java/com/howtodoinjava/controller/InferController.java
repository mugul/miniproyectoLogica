/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.controller;

import com.howtodoinjava.entity.Resuelve;
import com.howtodoinjava.entity.Solucion;
import com.howtodoinjava.entity.Teorema;
import com.howtodoinjava.entity.TerminoId;
import com.howtodoinjava.entity.Usuario;
import com.howtodoinjava.forms.InfersForm;
import com.howtodoinjava.lambdacalculo.App;
import com.howtodoinjava.lambdacalculo.Const;
import com.howtodoinjava.lambdacalculo.MakeTerm;
import com.howtodoinjava.lambdacalculo.PasoInferencia;
import com.howtodoinjava.lambdacalculo.Var;
import com.howtodoinjava.lambdacalculo.Term;
import com.howtodoinjava.parse.IsNotInDBException;
import com.howtodoinjava.parse.TermLexer;
import com.howtodoinjava.parse.TermParser;
import com.howtodoinjava.service.ResuelveManager;
import com.howtodoinjava.service.SolucionManager;
import com.howtodoinjava.service.TeoremaManager;
import com.howtodoinjava.service.TerminoManager;
import com.howtodoinjava.service.UsuarioManager;
import com.howtodoinjava.service.CategoriaManager;
import com.howtodoinjava.service.MetateoremaManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author shamuel
 */
@Controller
@RequestMapping(value="/infer")
public class InferController {
      
    @Autowired
    private UsuarioManager usuarioManager;
    @Autowired
    private TerminoManager terminoManager;
    @Autowired
    private SolucionManager solucionManager;
    @Autowired
    private TeoremaManager teoremaManager;
    @Autowired
    private MetateoremaManager metateoremaManager;
    @Autowired
    private ResuelveManager resuelveManager;
    @Autowired
    private HttpSession session;
    @Autowired
    private CategoriaManager categoriaManager;
    
    @RequestMapping(value="/{username}", method=RequestMethod.GET)
    public String inferView(@PathVariable String username, ModelMap map) {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        InfersForm infersForm = new InfersForm();
        infersForm.setSolucionId(0);
        
        map.addAttribute("infer",infersForm);
        map.addAttribute("mensaje","");
        map.addAttribute("pasoAnt","");
        map.addAttribute("nStatement","");
        map.addAttribute("instanciacion","");
        
        map.addAttribute("leibniz","");    
        map.addAttribute("formula","");
        map.addAttribute("guardarMenu","");
        map.addAttribute("admin","admin");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","class=\"active\"");
        map.addAttribute("perfilMenu","");
        map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1200px");
        map.addAttribute("categorias",categoriaManager.getAllCategorias());
        map.addAttribute("teoremas", usuarioManager.getAllTeoremas(usuarioManager.getUsuario(username)));
        map.addAttribute("metateoremas",metateoremaManager);
        map.addAttribute("resuelveManager",resuelveManager);
        map.addAttribute("makeTerm",new MakeTerm());
        System.out.println("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        System.out.println("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        System.out.println("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        System.out.println("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        System.out.println(infersForm.getSolucionId());
        System.out.println("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        System.out.println("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        System.out.println("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        System.out.println("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        return "infer";
    }
     
    @RequestMapping(value="/{username}", method=RequestMethod.POST)
    public String infer(@Valid InfersForm infersForm, BindingResult bindingResult, @PathVariable String username, ModelMap map)
    {
            if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
            {
                return "redirect:/index";
            }
            if( bindingResult.hasErrors() )
            {
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("mensaje","");
                map.addAttribute("infer", infersForm);
                map.addAttribute("pasoAnt",infersForm.getPasoAnt());
                map.addAttribute("nStatement",infersForm.getnStatement());
                map.addAttribute("instanciacion",infersForm.getInstanciacion());
                map.addAttribute("leibniz",infersForm.getLeibniz()); 
                map.addAttribute("teoremas", usuarioManager.getAllTeoremas(usuarioManager.getUsuario(username)));
                map.addAttribute("formula",infersForm.getHistorial());
                map.addAttribute("guardarMenu","");
                map.addAttribute("admin","admin");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","class=\"active\"");
                map.addAttribute("perfilMenu","");
        map.addAttribute("resuelveManager",resuelveManager);
        map.addAttribute("metateoremas",metateoremaManager);
                map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1200px");
                map.addAttribute("categorias",categoriaManager.getAllCategorias());
                return "infer";
            }
        
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(infersForm.getSolucionId());
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            String pasoPost;
            String pasoAnt = infersForm.getPasoAnt();
            int nStatement =new Integer(infersForm.getnStatement());
            String instanciacion =infersForm.getInstanciacion();
            String leibniz = infersForm.getLeibniz();
            TerminoId terminoid=new TerminoId();
            terminoid.setLogin(username);
            

            
            
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        System.out.println(infersForm.getSolucionId());
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
            
            ANTLRStringStream in0 = new ANTLRStringStream(pasoAnt);
            TermLexer lexer0 = new TermLexer(in0);
            CommonTokenStream tokens0 = new CommonTokenStream(lexer0);
            TermParser parser0 = new TermParser(tokens0);
            Term pasoAntTerm;
            try //si la sintanxis no es correcta ocurre una Exception
            {

                pasoAntTerm =parser0.start_rule(terminoid,terminoManager);
                pasoAntTerm.setAlias(0);
                
            }
            catch(IsNotInDBException e)
            {
                String hdr = parser0.getErrorHeader(e);
		String msg = parser0.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("infer",infersForm);
                map.addAttribute("mensaje", hdr +((IsNotInDBException)e).message);
                map.addAttribute("pasoAnt",infersForm.getPasoAnt());
                map.addAttribute("nStatement",infersForm.getnStatement());
                map.addAttribute("instanciacion",infersForm.getInstanciacion());
                map.addAttribute("leibniz",infersForm.getLeibniz());
                map.addAttribute("formula",infersForm.getHistorial());
                map.addAttribute("admin","admin");
                map.addAttribute("guardarMenu","");
        map.addAttribute("metateoremas",metateoremaManager);
                map.addAttribute("listarTerminosMenu","");
        map.addAttribute("categorias",categoriaManager.getAllCategorias());
        map.addAttribute("teoremas", usuarioManager.getAllTeoremas(usuarioManager.getUsuario(username)));
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","class=\"active\"");
        map.addAttribute("resuelveManager",resuelveManager);
                map.addAttribute("perfilMenu","");
                map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1200px");
                return "infer";
            }
            catch(RecognitionException e)
            {
                String hdr = parser0.getErrorHeader(e);
		String msg = parser0.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("infer",infersForm);
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("pasoAnt",infersForm.getPasoAnt());
                map.addAttribute("nStatement",infersForm.getnStatement());
                map.addAttribute("instanciacion",infersForm.getInstanciacion());
        map.addAttribute("resuelveManager",resuelveManager);
                map.addAttribute("leibniz",infersForm.getLeibniz());
                map.addAttribute("formula",infersForm.getHistorial());
                map.addAttribute("guardarMenu","");
                map.addAttribute("admin","admin");
                map.addAttribute("listarTerminosMenu","");
        map.addAttribute("categorias",categoriaManager.getAllCategorias());
        map.addAttribute("teoremas", usuarioManager.getAllTeoremas(usuarioManager.getUsuario(username)));
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","class=\"active\"");
        map.addAttribute("metateoremas",metateoremaManager);
                map.addAttribute("perfilMenu","");
                map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1200px");
                return "infer";
            }
       
            
            Teorema teorema = teoremaManager.getTeorema(nStatement);
            MakeTerm mk = new MakeTerm();
            
            Term term = null;
            ObjectInputStream in;
            try {
                in = new ObjectInputStream(new ByteArrayInputStream(teorema.getTeoserializadoizq()));
                Term terIzq = (Term) in.readObject();
                in.close();

                in = new ObjectInputStream(new ByteArrayInputStream(teorema.getTeoserializadoder()));
                Term terDer = (Term) in.readObject();
                in.close();
                
                System.out.println(terIzq.toStringInf());
                System.out.println(terDer.toStringInf());
                term = new App(new App((new Const("\\equiv")) , terDer),terIzq);
            } catch (IOException ex) {
                Logger.getLogger(InferController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InferController.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
              
            ANTLRStringStream in2 = new ANTLRStringStream(instanciacion);
            TermLexer lexer2 = new TermLexer(in2);
            CommonTokenStream tokens2 = new CommonTokenStream(lexer2);
            TermParser parser2 = new TermParser(tokens2);
            ArrayList<Object> arr;
            try
            {
               arr=parser2.instantiate(terminoid,terminoManager);
            }
            catch(RecognitionException e)
            {
                String hdr = parser2.getErrorHeader(e);
		String msg = parser2.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("infer",infersForm);
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("pasoAnt",infersForm.getPasoAnt());
                map.addAttribute("nStatement",infersForm.getnStatement());
                map.addAttribute("instanciacion",infersForm.getInstanciacion());
                map.addAttribute("leibniz",infersForm.getLeibniz());
                map.addAttribute("formula",infersForm.getHistorial());
        map.addAttribute("resuelveManager",resuelveManager);
                map.addAttribute("guardarMenu","");
        map.addAttribute("metateoremas",metateoremaManager);
        map.addAttribute("categorias",categoriaManager.getAllCategorias());
        map.addAttribute("teoremas", usuarioManager.getAllTeoremas(usuarioManager.getUsuario(username)));
                map.addAttribute("admin","admin");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","class=\"active\"");
                map.addAttribute("perfilMenu","");
                map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1200px");
                return "infer";
            }
            
            ANTLRStringStream in3 = new ANTLRStringStream(leibniz);
            TermLexer lexer3 = new TermLexer(in3);
            CommonTokenStream tokens3 = new CommonTokenStream(lexer3);
            TermParser parser3 = new TermParser(tokens3);
            Term leibnizTerm;
            try
            {
               leibnizTerm =parser3.lambda(terminoid,terminoManager);
            }
            catch(RecognitionException e)
            {
                String hdr = parser3.getErrorHeader(e);
		String msg = parser3.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("infer",infersForm);
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("pasoAnt",infersForm.getPasoAnt());
                map.addAttribute("nStatement",infersForm.getnStatement());
                map.addAttribute("instanciacion",infersForm.getInstanciacion());
                map.addAttribute("leibniz",infersForm.getLeibniz());
        map.addAttribute("resuelveManager",resuelveManager);
                map.addAttribute("formula",infersForm.getHistorial());
                map.addAttribute("guardarMenu","");
                map.addAttribute("admin","admin");
                map.addAttribute("listarTerminosMenu","");
        map.addAttribute("metateoremas",metateoremaManager);
        map.addAttribute("categorias",categoriaManager.getAllCategorias());
        map.addAttribute("teoremas", usuarioManager.getAllTeoremas(usuarioManager.getUsuario(username)));
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","class=\"active\"");
                map.addAttribute("perfilMenu","");
                map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1200px");
                return "infer";
            }
            term = term.sustParall((ArrayList<Var>)arr.get(0), (ArrayList<Term>)arr.get(1));
            Term izq,der;
            izq = ((App)term).q;
            der = ((App)((App)term).p).q;
            PasoInferencia paso = new PasoInferencia(pasoAntTerm, izq, der, leibnizTerm, instanciacion);
            izq = new App(leibnizTerm,izq).reducir();
            der = new App(leibnizTerm,der).reducir();
            

            boolean valida = true;
            boolean resuelto = false;
            
            
            if (izq.equals(pasoAntTerm)) {
                pasoPost = der.toStringInFin();
//                Term pasoPostTerm = new MakeTerm().makeTerm(pasoPost);
//                if ((pasoPostTerm instanceof Const) && (pasoPost.contains("true"))){
//                    resuelto = true;
//                }
            }else if(der.equals(pasoAntTerm)) {
                pasoPost = izq.toStringInFin();
//                Term pasoPostTerm = new MakeTerm().makeTerm(pasoPost);
//                if ((pasoPostTerm instanceof Const) && (pasoPost.contains("true"))){
//                    resuelto = true;
//                }
            }else{
                pasoPost = "Regla~de~inferencia~no~validad";
                valida = false;
            }
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("El paso es "+pasoPost);
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("PASOPOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
            
            int solucionId = infersForm.getSolucionId();
            Solucion solucion = null;
            
            System.out.println("La solucion en el formulario tiene id numero: "+solucionId);
            Resuelve resuelve = resuelveManager.getResuelveByUserAndTeorema(username,infersForm.getnStatement());
            
            
            
            if (pasoPost.equalsIgnoreCase("true") || pasoPost.equalsIgnoreCase("(true)") || pasoPost.equalsIgnoreCase("true ") || pasoPost.equalsIgnoreCase(" true") || pasoPost.equalsIgnoreCase(" true ") ||
                    pasoPost.equalsIgnoreCase(" (true)") || pasoPost.equalsIgnoreCase("(true) ") || pasoPost.equalsIgnoreCase("( true)") || pasoPost.equalsIgnoreCase("(true )") || 
                    pasoPost.equalsIgnoreCase(" ( true)") || pasoPost.equalsIgnoreCase(" (true )") || pasoPost.equalsIgnoreCase("( true) ") || pasoPost.equalsIgnoreCase("(true ) ")) {
//            if (resuelto) {
                resuelve.setResuelto(true);
                resuelveManager.updateResuelve(resuelve);
            }
            
            if (solucionId == 0) {
                List<Solucion> solList =  solucionManager.getAllSolucionesByResuelve(resuelve.getId());
                if (solList.isEmpty()) {
                    solucion = new Solucion(resuelve, paso);
                    solucionManager.addSolucion(solucion);
                } else {
                    solucion = solList.get(0);
                }
                solucionId = solucion.getId();
                infersForm.setSolucionId(solucionId);
            System.out.println("Ahora la solucion en el formulario tiene id numero: "+solucionId);
                
            } else {
                solucion = solucionManager.getSolucion(solucionId);
                
            }    
            
//            
//            List<Solucion> solList =  solucionManager.getAllSolucionesByResuelve(resuelveManager.getResuelveByUserAndTeorema(username,infersForm.getnStatement()).getId());
//            Solucion solucion = solList.get(0);
//            
//
////            solucion.setResuelve(resuelveManager.getResuelve(1));
//              solucionManager.addPaso(solucionId,paso);
////            paso = new PasoInferencia(teoTerm, izq, der, teoTerm, "Aqui va la segunda instanciacion");
            solucion.addArregloInferencias(paso);
            solucionManager.updateSolucion(solucion);
            infersForm.setHistorial("$$ ");  
            System.out.println("Aqui se imprime la solucion");
            for (PasoInferencia x: solucion.getArregloInferencias()) {
                System.out.println("=============================");
                System.out.print("El teorema a resolver: ");
                System.out.println(x.getExpresion().toStringInf());
                System.out.print("El lado izq del teo es: ");
                System.out.println(x.getTeoIzq().toStringInf());
                System.out.print("El lado der del teo es: ");
                System.out.println(x.getTeoDer().toStringInf());
                System.out.print("El leibniz es: ");
                System.out.println(x.getLeibniz().toStringInf());
                System.out.print("Finalmente, instanciacion es: ");
                System.out.println(x.getInstancia().toString());
                System.out.println("------------------------------");
                infersForm.setHistorial("$$ "+infersForm.getHistorial()+x.getExpresion().toStringInFin()+" \n $$" + " $$ < " + new MakeTerm().makeApp(x.getTeoIzq().toStringInFin(), x.getTeoDer().toStringInFin()).toStringInFin() + " - " + x.getLeibniz().toStringInf() + "  -  " + x.getInstancia().toString()+" > \n$$");
            }

//            
//            infersForm.setHistorial(infersForm.getHistorial()+" > $$ \n" );
//            infersForm.setHistorial(infersForm.getHistorial()+" > \n" );
//             infersForm.setHistorial(infersForm.getHistorial()+"$$ "+infersForm.getPasoAnt()+" $$ \n");
            
//            ArrayList<String> lista = infersForm.getHistorial();
//            lista.add(infersForm.getPasoAnt());
//            lista.add("< " +infersForm.getnStatement() + " - " + infersForm.getInstanciacion() + " - " + infersForm.getLeibniz() + " >");
//            infersForm.setHistorial(lista);
//            // Agrego el termino 
//            infersForm.setHistorial(pasoPost);
////            infersForm.setHistorial();
//            infersForm.setHistorial("<br>"+infersForm.getHistorial()+"<br>");
            System.out.println("El valor de pasoPost es: "+pasoPost);
            map.addAttribute("usuario", usuarioManager.getUsuario(username));
            map.addAttribute("infer",infersForm);

            map.addAttribute("mensaje",usuarioManager.getAllTeoremas(usuarioManager.getUsuario(username)));

            map.addAttribute("pasoAnt",pasoPost);
//            map.addAttribute("pasoAnt","");
            map.addAttribute("nStatement",infersForm.getnStatement());
            map.addAttribute("instanciacion",infersForm.getInstanciacion());
            map.addAttribute("leibniz",infersForm.getLeibniz());   
            if (valida) {
                map.addAttribute("formula",infersForm.getHistorial());
            } else {
                map.addAttribute("formula",infersForm.getPasoAnt());
            }
        map.addAttribute("categorias",categoriaManager.getAllCategorias());
        map.addAttribute("teoremas", usuarioManager.getAllTeoremas(usuarioManager.getUsuario(username)));
            map.addAttribute("guardarMenu","");
            map.addAttribute("admin","admin");
        map.addAttribute("resuelveManager",resuelveManager);
            map.addAttribute("listarTerminosMenu","");
            map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("metateoremas",metateoremaManager);
            map.addAttribute("misPublicacionesMenu","");
            map.addAttribute("computarMenu","class=\"active\"");
            map.addAttribute("perfilMenu","");
            map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
            map.addAttribute("overflow","hidden");
            map.addAttribute("anchuraDiv","1200px");
            return "infer";
//            
//            
//            map.addAttribute("usuario", usuarioManager.getUsuario(username));
//            map.addAttribute("infer",new InfersForm());
//            map.addAttribute("mensaje", teoremaTerm.toStringInf());
//            map.addAttribute("pasoAnt",infersForm.getPasoAnt());
//            map.addAttribute("nStatement",infersForm.getnStatement());
//            map.addAttribute("instanciacion",infersForm.getInstanciacion());
//            map.addAttribute("leibniz",infersForm.getLeibniz());
//            map.addAttribute("formula","");
//            map.addAttribute("admin","admin");
//            map.addAttribute("guardarMenu","");
//            map.addAttribute("listarTerminosMenu","");
//            map.addAttribute("verTerminosPublicosMenu","");
//            map.addAttribute("misPublicacionesMenu","");
//            map.addAttribute("computarMenu","class=\"active\"");
//            map.addAttribute("perfilMenu","");
//            map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
//            map.addAttribute("overflow","hidden");
//            map.addAttribute("anchuraDiv","1100px");
//            return "infer";    
    }
    
    public void setUsuarioManager(UsuarioManager usuarioManager) 
    {
            this.usuarioManager = usuarioManager;
    }
    
    public void setTerminoManager(TerminoManager terminoManager) 
    {
            this.terminoManager = terminoManager;
    }
}
