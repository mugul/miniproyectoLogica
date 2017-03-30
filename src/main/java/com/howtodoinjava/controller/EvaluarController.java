/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.controller;

import com.howtodoinjava.entity.Categoria;
import com.howtodoinjava.entity.Dispone;
import com.howtodoinjava.entity.Metateorema;
import com.howtodoinjava.entity.Resuelve;
import com.howtodoinjava.entity.Solucion;
import com.howtodoinjava.entity.Teorema;
import com.howtodoinjava.entity.TerminoId;
import com.howtodoinjava.entity.Usuario;
import com.howtodoinjava.forms.AgregarTeorema;
import com.howtodoinjava.forms.InsertarEvaluar;
import com.howtodoinjava.lambdacalculo.App;
import com.howtodoinjava.lambdacalculo.Const;
import com.howtodoinjava.lambdacalculo.Corrida;
import com.howtodoinjava.lambdacalculo.PasoInferencia;
import com.howtodoinjava.lambdacalculo.Term;
import com.howtodoinjava.lambdacalculo.Tripla;
import com.howtodoinjava.parse.IsNotInDBException;
import com.howtodoinjava.parse.TermLexer;
import com.howtodoinjava.parse.TermParser;
import com.howtodoinjava.service.CategoriaManager;
import com.howtodoinjava.service.DisponeManager;
import com.howtodoinjava.service.MetateoremaManager;
import com.howtodoinjava.service.ResuelveManager;
import com.howtodoinjava.service.SolucionManager;
import com.howtodoinjava.service.TeoremaManager;
import com.howtodoinjava.service.TerminoManager;
import com.howtodoinjava.service.UsuarioManager;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.SerializationUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author federico
 */
@Controller
@RequestMapping(value = "/eval")
public class EvaluarController {


    @Autowired
    private UsuarioManager usuarioManager;
    @Autowired
    private TerminoManager terminoManager;
    @Autowired
    private ResuelveManager resuelveManager;
    @Autowired
    private DisponeManager disponeManager;
    @Autowired
    private TeoremaManager teoremaManager;
    @Autowired
    private MetateoremaManager metateoremaManager;
    @Autowired
    private CategoriaManager categoriaManager;
    @Autowired
    private SolucionManager solucionManager;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/{username}/pruebaPredicado/{id}", method = RequestMethod.GET)
    public String pruebaPredicadoView(@PathVariable String username, @PathVariable String id, ModelMap map) {

        ////// ESTE CODIGO ES SOLO DE PRUEBAS
        ////// ESTE CODIGO ES SOLO DE PRUEBAS
        ////// ESTE CODIGO ES SOLO DE PRUEBAS
        ////// ESTE CODIGO ES SOLO DE PRUEBAS
        ////// ESTE CODIGO ES SOLO DE PRUEBAS
        ////// ESTE CODIGO ES SOLO DE PRUEBAS
        ////// ESTE CODIGO ES SOLO DE PRUEBAS
        ////// ESTE CODIGO ES SOLO DE PRUEBAS
        ////// ESTE CODIGO ES SOLO DE PRUEBAS
        ////// ESTE CODIGO ES SOLO DE PRUEBAS
        ////// ESTE CODIGO ES SOLO DE PRUEBA
        
    
            
//            List<Teorema> teoList = teoremaManager.getTeoremasByCategoria(1);
//            for (Teorema x: teoList) {
//                System.out.print("El teorema ");
//                System.out.print(x.getId());
//                System.out.print(" es de la categoria ");
//                System.out.println(x.getCategoria().getId());
//            }
            
            
            AgregarTeorema agregarTeorema = new AgregarTeorema("(p == p) == (q == q)", "45", "3.23", "El 3.23");
        
            Usuario user = usuarioManager.getUsuario(username);
            usuarioManager.getAllTeoremas(user);

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


//    public class PasoInferencia
//    {
//        public  Term expresion; // Teorema a demostrar 
//        public  Term teoIzq;  // Teorema a utilizar, parte izquierda
//        public  Term teoDer;  // Teorema a utilizar, parte derecha  
//        public  Term leibniz;
//        public  String  instancia;
//     
//    public Solucion(Resuelve resuelve, byte[] arregloSerializado, List<PasoInferencia> arregloInferencias) {
//        this.resuelve = resuelve;
//        this.arregloSerializado = SerializationUtils.serialize(arregloInferencias);
//        this.arregloInferencias = arregloInferencias;
//    }
    
            System.out.println("Llego hasta aqui");
            Solucion solucion = new Solucion();
            solucion.setResuelve(resuelveManager.getResuelve(1));
            PasoInferencia paso = new PasoInferencia(teoTerm, izq, der, teoTerm, "Aqui va la instanciacion");
            solucion.addArregloInferencias(paso);
            paso = new PasoInferencia(teoTerm, izq, der, teoTerm, "Aqui va la segunda instanciacion");
            solucion.addArregloInferencias(paso);
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
            }
            
            
            System.out.println(solucion.getArregloInferencias());
            System.out.println("Y aqui el toString()");
            System.out.println(solucion.getArregloInferencias().toString());
            
            
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


                // ESTO DEBE MOSTRAR LAS CATEGORIAS
                // Se busca si existe la cat, si no existe se crea
                Categoria categoria = categoriaManager.getCategoria(new Integer(agregarTeorema.getCategoria()));
                if (categoria == null) {
                    categoria = new Categoria("Equivalencia");
//                    categoriaManager.addCategoria(categoria);
                }

                System.out.println("AHHHHHH");
                System.out.println(izq);
                System.out.println(izq.toStringInf());
                System.out.println(izq.traducBD().toStringAbrvFinal());
                System.out.println(SerializationUtils.serialize(izq));
//              public Teorema(Categoria categoria, String enunciadoizq, String enunciadoder, byte[] teoserializadoizq, byte[] teoserializadoder, boolean ocultartrue, boolean esquema) {
                Teorema teorema = new Teorema(categoria, izq.traducBD().toStringFinal(), der.traducBD().toStringFinal(), izq, der, !esEq, false);
//                teoremaManager.addTeorema(teorema);

//                Resuelve resuelve = new Resuelve(user, teorema, agregarTeorema.getNombreTeorema(), agregarTeorema.getNumeroTeorema(), false);
                Resuelve resuelve = new Resuelve(user, teorema, "", "Teo de prueba", false);
//                resuelveManager.addResuelve(resuelve);

                // public Metateorema(int id, Categoria categoria, String enunciadoizq, String enunciadoder, String metateoserializadoizq, String metateoserializadoder, boolean ocultartrue)                
                Metateorema metateorema = new Metateorema(teorema.getId(), categoria, teoTerm.traducBD().toStringFinal(), "true", SerializationUtils.serialize(teoTerm), SerializationUtils.serialize("true"), false);
//                metateoremaManager.addMetateorema(metateorema);

                // public Dispone(int id, Usuario usuario, Metateorema metateorema, String numerometateorema, boolean resuelto)
                Dispone dispone = new Dispone(resuelve.getId(), user, metateorema, agregarTeorema.getNumeroTeorema(), false);
//                disponeManager.addDispone(dispone);
                
                Teorema teo = teoremaManager.getTeorema(1);
                
                usuarioManager.getAllTeoremas(user);
                map.addAttribute("id", izq.toStringInf());
                map.addAttribute("usuario", teo.getTeoIzqTerm().toStringInf());
                map.addAttribute("predicado", teo.getTeoDerTerm().toStringInf());
                map.addAttribute("alias", teo.getId());
                map.addAttribute("predserializado", categoriaManager.getAllCategorias().toString());
                return "PagParaVerPredicado";
            }
            catch (IsNotInDBException e) {
                String hdr = parser.getErrorHeader(e);
                String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));

                map.addAttribute("agregarTeoremaMenu", "class=\"active\"");
                map.addAttribute("listarTerminosMenu", "");
                map.addAttribute("verTerminosPublicosMenu", "");
                map.addAttribute("misPublicacionesMenu", "");
                map.addAttribute("computarMenu", "");
                map.addAttribute("perfilMenu", "");
                map.addAttribute("overflow", "hidden");
                map.addAttribute("anchuraDiv", "1100px");
                return "PagParaVerPredicado";
            } catch (RecognitionException e) {
                String hdr = parser.getErrorHeader(e);
                String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));

                map.addAttribute("admin", "admin");
                map.addAttribute("guardarMenu", "");
                map.addAttribute("agregarTeoremaMenu", "class=\"active\"");
                map.addAttribute("listarTerminosMenu", "");
                map.addAttribute("verTerminosPublicosMenu", "");
                map.addAttribute("misPublicacionesMenu", "");
                map.addAttribute("computarMenu", "");
                map.addAttribute("perfilMenu", "");
                map.addAttribute("overflow", "hidden");
                map.addAttribute("anchuraDiv", "1100px");
                return "PagParaVerPredicado";
            }
        }

        ////// HASTA AQUI LLEGA EL CODIGO ES SOLO DE PRUEBAS
        ////// HASTA AQUI LLEGA EL CODIGO ES SOLO DE PRUEBAS
        ////// HASTA AQUI LLEGA EL CODIGO ES SOLO DE PRUEBAS
        ////// HASTA AQUI LLEGA EL CODIGO ES SOLO DE PRUEBAS
        ////// HASTA AQUI LLEGA EL CODIGO ES SOLO DE PRUEBAS
        ////// HASTA AQUI LLEGA EL CODIGO ES SOLO DE PRUEBAS
        ////// HASTA AQUI LLEGA EL CODIGO ES SOLO DE PRUEBAS
        ////// HASTA AQUI LLEGA EL CODIGO ES SOLO DE PRUEBAS
        ////// HASTA AQUI LLEGA EL CODIGO ES SOLO DE PRUEBAS
        ////// HASTA AQUI LLEGA EL CODIGO ES SOLO DE PRUEBAS
        ////// HASTA AQUI LLEGA EL CODIGO ES SOLO DE PRUEBAS

//        if ((Usuario) session.getAttribute("user") == null || !((Usuario) session.getAttribute("user")).getLogin().equals(username)) {
//            return "redirect:/index";
//        }

//        System.out.println("Hello World");
//        int idInterno = new Integer(id).intValue();
//        System.out.print(idInterno);
//        System.out.print(" - ");
//        System.out.println(username);
//        PredicadoId predId = new PredicadoId(idInterno, username);
//
//        Predicado pred = predicadoManager.getPredicado(predId);
////        predId = pred.getId();
//        System.out.print(pred);
//        map.addAttribute("id", pred.getId());
//        map.addAttribute("usuario", pred.getUsuario());
//        map.addAttribute("predicado", pred.getPredicado());
//        map.addAttribute("alias", pred.getAlias());
//        map.addAttribute("predserializado", pred.getPredserializado());
//
//
//        return "PagParaVerPredicado";
//    }
    

//    
//        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
//        {
//            return "redirect:/index";
//        }
//        map.addAttribute("usuario", usuarioManager.getUsuario(username));
//        //map.addAttribute("terminoid",new TerminoId());
//        map.addAttribute("insertarEvaluar",new InsertarEvaluar());
//        map.addAttribute("mensaje","");
//        //map.addAttribute("modificar",new Integer(0));
//        map.addAttribute("nombre","");
//        map.addAttribute("termino","");
//        map.addAttribute("admin","admin");
//        map.addAttribute("guardarMenu","");
//        map.addAttribute("listarTerminosMenu","");
//        map.addAttribute("verTerminosPublicosMenu","");
//        map.addAttribute("misPublicacionesMenu","");
//        map.addAttribute("computarMenu","class=\"active\"");
//        map.addAttribute("perfilMenu","");
//        map.addAttribute("hrefAMiMismo","href=ingresar#!");
//        map.addAttribute("overflow","hidden");
//        map.addAttribute("anchuraDiv","1100px");
//        
//        return "insertarEvaluar";
//    }    
//    
//    @RequestMapping(value = "/{username}/paso", method = RequestMethod.POST)
//    public String evaluarView(@Valid InsertarEvaluar insertarEvaluar, BindingResult bindingResult, @PathVariable String username, ModelMap map) {
//        if ((Usuario) session.getAttribute("user") == null || !((Usuario) session.getAttribute("user")).getLogin().equals(username)) {
//            return "redirect:/index";
//        }
//        if (bindingResult.hasErrors()) {
//            map.addAttribute("usuario", usuarioManager.getUsuario(username));
//            map.addAttribute("mensaje", "");
//            map.addAttribute("termino", insertarEvaluar.getAlgoritmo());
//            map.addAttribute("nombre", insertarEvaluar.getNombre());
//            map.addAttribute("guardarMenu", "");
//            map.addAttribute("listarTerminosMenu", "");
//            map.addAttribute("verTerminosPublicosMenu", "");
//            map.addAttribute("misPublicacionesMenu", "");
//            map.addAttribute("computarMenu", "class=\"active\"");
//            map.addAttribute("perfilMenu", "");
//            map.addAttribute("hrefAMiMismo", "href=../../eval/" + username + "#!");
//            return "insertarEvaluar";
//        }
//
//        String programa = insertarEvaluar.getAlgoritmo();
//        TerminoId terminoid = new TerminoId();
//        terminoid.setLogin(username);
//
//        //Hay que construir un Term aqui con el String termino.combinador
//        //para luego traducir, hace falta construir un parse   
//        ANTLRStringStream in = new ANTLRStringStream(programa);
//        TermLexer lexer = new TermLexer(in);
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//        TermParser parser = new TermParser(tokens);
//        Term term;
//        try //si la sintanxis no es correcta ocurre una Exception
//        {
//
//            term = parser.start_rule(terminoid, terminoManager);
//
//        } catch (IsNotInDBException e) {
//            String hdr = parser.getErrorHeader(e);
//            String msg = parser.getErrorMessage(e, TermParser.tokenNames);
//            map.addAttribute("usuario", usuarioManager.getUsuario(username));
//            map.addAttribute("insertarEvaluar", new InsertarEvaluar());
//            map.addAttribute("mensaje", hdr + ((IsNotInDBException) e).message);
//            map.addAttribute("termino", programa);
//            map.addAttribute("nombre", insertarEvaluar.getNombre());
//            map.addAttribute("guardarMenu", "");
//            map.addAttribute("listarTerminosMenu", "");
//            map.addAttribute("verTerminosPublicosMenu", "");
//            map.addAttribute("misPublicacionesMenu", "");
//            map.addAttribute("computarMenu", "class=\"active\"");
//            map.addAttribute("perfilMenu", "");
//            map.addAttribute("hrefAMiMismo", "href=../../eval/" + username + "#!");
//            return "insertarEvaluar";
//        } catch (RecognitionException e) {
//
//            String hdr = parser.getErrorHeader(e);
//            String msg = parser.getErrorMessage(e, TermParser.tokenNames);
//            map.addAttribute("usuario", usuarioManager.getUsuario(username));
//            map.addAttribute("insertarEvaluar", new InsertarEvaluar());
//            map.addAttribute("mensaje", hdr + " " + msg);
//            map.addAttribute("termino", programa);
//            map.addAttribute("nombre", insertarEvaluar.getNombre());
//            map.addAttribute("guardarMenu", "");
//            map.addAttribute("listarTerminosMenu", "");
//            map.addAttribute("verTerminosPublicosMenu", "");
//            map.addAttribute("misPublicacionesMenu", "");
//            map.addAttribute("computarMenu", "class=\"active\"");
//            map.addAttribute("perfilMenu", "");
//            map.addAttribute("hrefAMiMismo", "href=../../eval/" + username + "#!");
//            return "insertarEvaluar";
//        }
//
//        ArrayList<String> terminos = new ArrayList<String>();
//        ArrayList<String> operations = new ArrayList<String>();
//        Term term2 = term;
//        Term term1 = term2;
//        int nTerms = 0;
//        int i = -1;
//        do {
//
//            term1 = term2;
//            if (i == -1) {
//                operations.add("\\downarrow");
//            } else {
//                if (i == 2) {
//                    i = 0;
//                }
//                operations.add("\\uparrow");
//            }
//            terminos.add(term1.toStringFinal());
//            nTerms++;
//            i++;
//            Redex redex = term1.buscarRedexIzq(null, false);
//            while (redex != null) {
//                if (redex.tipo.l) {
//                    operations.add("\\downarrow");
//                    terminos.add(term1.volverPuro().toStringFinal());
//                    nTerms++;
//                }
//                term1 = term1.reducir();
//
//                operations.add("\\rightarrow");
//                terminos.add(term1.toStringFinal());
//                nTerms++;
//
//                redex = term1.buscarRedexIzq(null, false);
//            }
//            term2 = term1.invBDOneStep();
//        } while (!term1.equals(term2));
//
//        map.addAttribute("nTerms", new Integer(nTerms - 1));
//        map.addAttribute("terminos", terminos);
//        map.addAttribute("operations", operations);
//        return "evaluarTodo";
//    }
//
//    /*@RequestMapping(value="/{username}", method=RequestMethod.POST)
//     public String evaluarPasoAPasoView(@Valid InsertarEvaluar insertarEvaluar, BindingResult bindingResult, @PathVariable String username, ModelMap map)
//     {
//
//     if( bindingResult.hasErrors() )
//     {
//     map.addAttribute("usuario", usuarioManager.getUsuario(username));
//     map.addAttribute("mensaje","");
//     map.addAttribute("termino",insertarEvaluar.getAlgoritmo());
//     map.addAttribute("nombre",insertarEvaluar.getNombre());
//     return "insertarEvaluar";
//     }
//        
//     String programa=insertarEvaluar.getAlgoritmo();
//     TerminoId terminoid=new TerminoId();
//     terminoid.setLogin(username);
//            
//     //Hay que construir un Term aqui con el String termino.combinador
//     //para luego traducir, hace falta construir un parse   
//     ANTLRStringStream in = new ANTLRStringStream(programa);
//     TermLexer lexer = new TermLexer(in);
//     CommonTokenStream tokens = new CommonTokenStream(lexer);
//     TermParser parser = new TermParser(tokens);
//     Term term;
//     try //si la sintanxis no es correcta ocurre una Exception
//     {
//
//     term=parser.t(terminoid,terminoManager);
//                
//                
//     }
//     catch(IsNotInDBException e)
//     {
//     String hdr = parser.getErrorHeader(e);
//     String msg = parser.getErrorMessage(e, TermParser.tokenNames);
//     map.addAttribute("usuario", usuarioManager.getUsuario(username));
//     map.addAttribute("insertarEvaluar",new InsertarEvaluar());
//     map.addAttribute("mensaje", hdr +((IsNotInDBException)e).message);
//     map.addAttribute("nombre",insertarEvaluar.getNombre());
//     map.addAttribute("termino",programa);
//     return "insertarEvaluar";
//     }
//     catch(RecognitionException e)
//     {
//     String hdr = parser.getErrorHeader(e);
//     String msg = parser.getErrorMessage(e, TermParser.tokenNames);
//     map.addAttribute("usuario", usuarioManager.getUsuario(username));
//     map.addAttribute("insertarEvaluar",new InsertarEvaluar());
//     map.addAttribute("mensaje", hdr+" "+msg);
//     map.addAttribute("nombre",insertarEvaluar.getNombre());
//     map.addAttribute("termino",programa);
//     return "insertarEvaluar";
//     }
//            
//     Tripla tripla=term.toStringAbrvFinal();
//     ArrayList<String> terminos = new ArrayList<String>();
//     List<String> alias=tripla.alias;
//     List<String> valores=tripla.valores;
//     ArrayList<Integer> operations = new ArrayList<Integer>();
//     Term term2=term;
//     Term term1=term2;
//     int nTerms=0;
//     int i=-1;
//     do
//     {
//
//     term1=term2;
//     if(i==-1) 
//     operations.add(new Integer(1));
//     else
//     {
//     if( i == 2)
//     i=0;
//     operations.add(new Integer(2));
//     }
//     //tripla=term1.toStringAbrvFinal();
//     //terminos.add(tripla.term.replace("\\", "\\\\"));
//     terminos.add(term1.toStringFinal().replace("\\", "\\\\"));
//     nTerms++;
//     i++;
//     Redex redex=term1.buscarRedexIzq(null, false);
//     while(redex!=null)
//     {
//     if(redex.tipo.l)
//     {
//     operations.add(new Integer(1));
//     //tripla=term1.volverPuro().toStringAbrvFinal();
//     //terminos.add(tripla.term.replace("\\", "\\\\"));
//     terminos.add(term1.volverPuro().toStringFinal().replace("\\", "\\\\"));
//     nTerms++;
//     }
//     term1=term1.reducir();
//
//     operations.add(new Integer(3));
//     //tripla=term1.toStringAbrvFinal();
//     //terminos.add(tripla.term.replace("\\", "\\\\"));
//     terminos.add(term1.toStringFinal().replace("\\", "\\\\"));
//     nTerms++;
//
//     redex=term1.buscarRedexIzq(null, false);
//     }
//     term2=term1.invBDOneStep();
//     }while(!term1.equals(term2));
//            
//     terminos.set(0, tripla.term.replace("\\", "\\\\"));
//            
//     map.addAttribute("nTerms",new Integer(nTerms-1));
//     map.addAttribute("terminos",terminos);
//     map.addAttribute("operations",operations);
//     map.addAttribute("nAlias",new Integer(alias.size()-1));
//     map.addAttribute("alias",alias);
//     map.addAttribute("valores",valores);
//     return "evaluar";
//     }*/
    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public String evaluarPasoAPasoView(@Valid InsertarEvaluar insertarEvaluar, BindingResult bindingResult, @PathVariable String username, ModelMap map) {
        if ((Usuario) session.getAttribute("user") == null || !((Usuario) session.getAttribute("user")).getLogin().equals(username)) {
            return "redirect:/index";
        }
        if (bindingResult.hasErrors()) {
            map.addAttribute("usuario", usuarioManager.getUsuario(username));
            map.addAttribute("mensaje", "");
            map.addAttribute("termino", insertarEvaluar.getAlgoritmo());
            map.addAttribute("nombre", insertarEvaluar.getNombre());
            map.addAttribute("guardarMenu", "");
            map.addAttribute("admin", "admin");
            map.addAttribute("listarTerminosMenu", "");
            map.addAttribute("verTerminosPublicosMenu", "");
            map.addAttribute("misPublicacionesMenu", "");
            map.addAttribute("computarMenu", "class=\"active\"");
            map.addAttribute("perfilMenu", "");
            map.addAttribute("hrefAMiMismo", "href=../../eval/" + username + "#!");
            map.addAttribute("overflow", "hidden");
            map.addAttribute("anchuraDiv", "1100px");
            return "insertarEvaluar";
        }

        String programa = insertarEvaluar.getAlgoritmo();
        TerminoId terminoid = new TerminoId();
        terminoid.setLogin(username);

        //Hay que construir un Term aqui con el String termino.combinador
        //para luego traducir, hace falta construir un parse   
        ANTLRStringStream in = new ANTLRStringStream(programa);
        TermLexer lexer = new TermLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TermParser parser = new TermParser(tokens);
        Term term;
        try //si la sintanxis no es correcta ocurre una Exception
        {

            term = parser.start_rule(terminoid, terminoManager);
            term.setAlias(0);

        } catch (IsNotInDBException e) {
            String hdr = parser.getErrorHeader(e);
            String msg = parser.getErrorMessage(e, TermParser.tokenNames);
            map.addAttribute("usuario", usuarioManager.getUsuario(username));
            map.addAttribute("insertarEvaluar", new InsertarEvaluar());
            map.addAttribute("mensaje", hdr + ((IsNotInDBException) e).message);
            map.addAttribute("nombre", insertarEvaluar.getNombre());
            map.addAttribute("termino", programa);
            map.addAttribute("admin", "admin");
            map.addAttribute("guardarMenu", "");
            map.addAttribute("listarTerminosMenu", "");
            map.addAttribute("verTerminosPublicosMenu", "");
            map.addAttribute("misPublicacionesMenu", "");
            map.addAttribute("computarMenu", "class=\"active\"");
            map.addAttribute("perfilMenu", "");
            map.addAttribute("hrefAMiMismo", "href=../../eval/" + username + "#!");
            map.addAttribute("overflow", "hidden");
            map.addAttribute("anchuraDiv", "1100px");
            return "insertarEvaluar";
        } catch (RecognitionException e) {
            String hdr = parser.getErrorHeader(e);
            String msg = parser.getErrorMessage(e, TermParser.tokenNames);
            map.addAttribute("usuario", usuarioManager.getUsuario(username));
            map.addAttribute("insertarEvaluar", new InsertarEvaluar());
            map.addAttribute("mensaje", hdr + " " + msg);
            map.addAttribute("nombre", insertarEvaluar.getNombre());
            map.addAttribute("termino", programa);
            map.addAttribute("guardarMenu", "");
            map.addAttribute("admin", "admin");
            map.addAttribute("listarTerminosMenu", "");
            map.addAttribute("verTerminosPublicosMenu", "");
            map.addAttribute("misPublicacionesMenu", "");
            map.addAttribute("computarMenu", "class=\"active\"");
            map.addAttribute("perfilMenu", "");
            map.addAttribute("hrefAMiMismo", "href=../../eval/" + username + "#!");
            map.addAttribute("overflow", "hidden");
            map.addAttribute("anchuraDiv", "1100px");
            return "insertarEvaluar";
        }

        Tripla tripla = term.toStringAbrvFinal();
        List<String> alias = tripla.alias;
        List<String> valores = tripla.valores;
        Corrida corr = term.evaluarFinal();
        corr.operations.add(new Integer(7));//agrego cualquier cosa

        //corr.terminos.set(0, tripla.term.replace("\\", "\\\\"));

        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("nReducciones", new Float(((float) corr.reducciones / (float) (corr.reducciones + corr.traducciones)) * 100));
        map.addAttribute("nTraduc", new Float(((float) corr.traducciones / (float) (corr.reducciones + corr.traducciones)) * 100));
        map.addAttribute("nTerms", new Integer(corr.terminos.size() - 1));
        map.addAttribute("nTermsPuros", new Integer(corr.lambdaTerms.size() - 1));
        map.addAttribute("terminos", corr.terminos);
        map.addAttribute("operations", corr.operations);
        map.addAttribute("termPuros", corr.lambdaTerms);
        if (alias.size() != 0) {
            map.addAttribute("nAlias", new Integer(alias.size() - 1));
        } else {
            map.addAttribute("nAlias", new Integer(0));
            alias.add("");
            valores.add("");
        }
        map.addAttribute("alias", alias);
        map.addAttribute("valores", valores);
        return "evaluar";
    }

    public void setUsuarioManager(UsuarioManager usuarioManager) {
        this.usuarioManager = usuarioManager;
    }

    public void setTerminoManager(TerminoManager terminoManager) {
        this.terminoManager = terminoManager;
    }
}
