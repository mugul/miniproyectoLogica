/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.controller;

import com.howtodoinjava.entity.TerminoId;
import com.howtodoinjava.entity.Usuario;
import com.howtodoinjava.forms.InfersForm;
import com.howtodoinjava.forms.InsertarEvaluar;
import com.howtodoinjava.lambdacalculo.App;
import com.howtodoinjava.lambdacalculo.Corrida;
import com.howtodoinjava.lambdacalculo.Var;
import com.howtodoinjava.lambdacalculo.Term;
import com.howtodoinjava.lambdacalculo.Tripla;
import com.howtodoinjava.parse.IsNotInDBException;
import com.howtodoinjava.parse.TermLexer;
import com.howtodoinjava.parse.TermParser;
import com.howtodoinjava.service.TerminoManager;
import com.howtodoinjava.service.UsuarioManager;
import java.util.ArrayList;
import java.util.List;
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
    private HttpSession session;
    
    @RequestMapping(value="/{username}", method=RequestMethod.GET)
    public String inferView(@PathVariable String username, ModelMap map) {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("infer",new InfersForm());
        map.addAttribute("mensaje","");
        map.addAttribute("pasoAnt","");
        map.addAttribute("nStament","");
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
        map.addAttribute("anchuraDiv","1100px");
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
                map.addAttribute("nStament",infersForm.getnStament());
                map.addAttribute("instanciacion",infersForm.getInstanciacion());
                map.addAttribute("leibniz",infersForm.getLeibniz()); 
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
                map.addAttribute("anchuraDiv","1100px");
                return "infer";
            }
        
            String pasoPost;
            String pasoAnt = infersForm.getPasoAnt();
            String nStament =infersForm.getnStament();
            String instanciacion =infersForm.getInstanciacion();
            String leibniz = infersForm.getLeibniz();
            TerminoId terminoid=new TerminoId();
            terminoid.setLogin(username);
            
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
                map.addAttribute("infer",new InfersForm());
                map.addAttribute("mensaje", hdr +((IsNotInDBException)e).message);
                map.addAttribute("pasoAnt",infersForm.getPasoAnt());
                map.addAttribute("nStament",infersForm.getnStament());
                map.addAttribute("instanciacion",infersForm.getInstanciacion());
                map.addAttribute("leibniz",infersForm.getLeibniz());
                map.addAttribute("formula","");
                map.addAttribute("admin","admin");
                map.addAttribute("guardarMenu","");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","class=\"active\"");
                map.addAttribute("perfilMenu","");
                map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "infer";
            }
            catch(RecognitionException e)
            {
                String hdr = parser0.getErrorHeader(e);
		String msg = parser0.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("infer",new InfersForm());
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("pasoAnt",infersForm.getPasoAnt());
                map.addAttribute("nStament",infersForm.getnStament());
                map.addAttribute("instanciacion",infersForm.getInstanciacion());
                map.addAttribute("leibniz",infersForm.getLeibniz());
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
                map.addAttribute("anchuraDiv","1100px");
                return "infer";
            }
            
            //Hay que construir un Term aqui con el String termino.combinador
            //para luego traducir, hace falta construir un parse   
            ANTLRStringStream in = new ANTLRStringStream(nStament);
            TermLexer lexer = new TermLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TermParser parser = new TermParser(tokens);
            Term term;
            try //si la sintanxis no es correcta ocurre una Exception
            {

                term=parser.start_rule(terminoid,terminoManager);
                term.setAlias(0);
                
            }
            catch(IsNotInDBException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("infer",new InfersForm());
                map.addAttribute("mensaje", hdr +((IsNotInDBException)e).message);
                map.addAttribute("pasoAnt",infersForm.getPasoAnt());
                map.addAttribute("nStament",infersForm.getnStament());
                map.addAttribute("instanciacion",infersForm.getInstanciacion());
                map.addAttribute("leibniz",infersForm.getLeibniz());
                map.addAttribute("formula","");
                map.addAttribute("admin","admin");
                map.addAttribute("guardarMenu","");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","class=\"active\"");
                map.addAttribute("perfilMenu","");
                map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "infer";
            }
            catch(RecognitionException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("infer",new InfersForm());
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("pasoAnt",infersForm.getPasoAnt());
                map.addAttribute("nStament",infersForm.getnStament());
                map.addAttribute("instanciacion",infersForm.getInstanciacion());
                map.addAttribute("leibniz",infersForm.getLeibniz());
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
                map.addAttribute("anchuraDiv","1100px");
                return "infer";
            }
            
            ANTLRStringStream in2 = new ANTLRStringStream(instanciacion);
            TermLexer lexer2 = new TermLexer(in2);
            CommonTokenStream tokens2 = new CommonTokenStream(lexer2);
            TermParser parser2 = new TermParser(tokens2);
            ArrayList<Object> arr;
            try
            {
               arr=parser2.instantiate();
            }
            catch(RecognitionException e)
            {
                String hdr = parser2.getErrorHeader(e);
		String msg = parser2.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("infer",new InfersForm());
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("pasoAnt",infersForm.getPasoAnt());
                map.addAttribute("nStament",infersForm.getnStament());
                map.addAttribute("instanciacion",infersForm.getInstanciacion());
                map.addAttribute("leibniz",infersForm.getLeibniz());
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
                map.addAttribute("anchuraDiv","1100px");
                return "infer";
            }
            
            ANTLRStringStream in3 = new ANTLRStringStream(leibniz);
            TermLexer lexer3 = new TermLexer(in3);
            CommonTokenStream tokens3 = new CommonTokenStream(lexer3);
            TermParser parser3 = new TermParser(tokens3);
            Term leibnizTerm;
            try
            {
               leibnizTerm =parser3.lambda();
            }
            catch(RecognitionException e)
            {
                String hdr = parser3.getErrorHeader(e);
		String msg = parser3.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("infer",new InfersForm());
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("pasoAnt",infersForm.getPasoAnt());
                map.addAttribute("nStament",infersForm.getnStament());
                map.addAttribute("instanciacion",infersForm.getInstanciacion());
                map.addAttribute("leibniz",infersForm.getLeibniz());
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
                map.addAttribute("anchuraDiv","1100px");
                return "infer";
            }
            term = term.sustParall((ArrayList<Var>)arr.get(0), (ArrayList<Term>)arr.get(1));
            Term izq,der;
            izq = ((App)term).q;
            der = ((App)((App)term).p).q;
            izq = new App(leibnizTerm,izq).reducir();
            der = new App(leibnizTerm,der).reducir();
            
            if (izq.equals(pasoAntTerm)) {
                pasoPost = der.toString();
            }else if(der.equals(pasoAntTerm)) {
                pasoPost = izq.toString();
            }else{
                pasoPost = "Regla~de~inferencia~no~validad";
            }
            
            map.addAttribute("usuario", usuarioManager.getUsuario(username));
            map.addAttribute("infer",new InfersForm());
            map.addAttribute("mensaje","");
            map.addAttribute("pasoAnt",infersForm.getPasoAnt());
            map.addAttribute("nStament",infersForm.getnStament());
            map.addAttribute("instanciacion",infersForm.getInstanciacion());
            map.addAttribute("leibniz",infersForm.getLeibniz());   
            map.addAttribute("formula",pasoPost);
            map.addAttribute("guardarMenu","");
            map.addAttribute("admin","admin");
            map.addAttribute("listarTerminosMenu","");
            map.addAttribute("verTerminosPublicosMenu","");
            map.addAttribute("misPublicacionesMenu","");
            map.addAttribute("computarMenu","class=\"active\"");
            map.addAttribute("perfilMenu","");
            map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
            map.addAttribute("overflow","hidden");
            map.addAttribute("anchuraDiv","1100px");
            return "infer";
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
