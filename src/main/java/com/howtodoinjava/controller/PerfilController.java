/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.controller;

import com.howtodoinjava.entity.Categoria;
import com.howtodoinjava.entity.Publicacion;
import com.howtodoinjava.entity.PublicacionId;
import com.howtodoinjava.entity.Resuelve;
import com.howtodoinjava.entity.Teorema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.howtodoinjava.entity.Usuario;
import com.howtodoinjava.entity.Termino;
import com.howtodoinjava.entity.TerminoId;
import com.howtodoinjava.forms.AgregarTeorema;
import com.howtodoinjava.forms.InfersForm;
import com.howtodoinjava.forms.InsertarEvaluar;
import com.howtodoinjava.forms.ModificarAliasForm;
import com.howtodoinjava.forms.ModificarForm;
import com.howtodoinjava.forms.UsuarioGuardar;
import com.howtodoinjava.lambdacalculo.App;
import com.howtodoinjava.lambdacalculo.Const;
import com.howtodoinjava.service.TerminoManager;
import com.howtodoinjava.service.UsuarioManager;
import com.howtodoinjava.lambdacalculo.Term;
import com.howtodoinjava.parse.IsNotInDBException;
import com.howtodoinjava.parse.TermLexer;
import com.howtodoinjava.parse.TermParser;
import com.howtodoinjava.service.CategoriaManager;
import com.howtodoinjava.service.ResuelveManager;
import com.howtodoinjava.service.TeoremaManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping(value="/perfil")
public class PerfilController {
    
    @Autowired
    private UsuarioManager usuarioManager;
    @Autowired
    private TerminoManager terminoManager;
    @Autowired
    private ResuelveManager resuelveManager;
    @Autowired
    private TeoremaManager teoremaManager;
    @Autowired
    private CategoriaManager categoriaManager;
    @Autowired
    private HttpSession session;
    
    @RequestMapping(value="/{username}/close", method=RequestMethod.GET)
    public String closeSesion(@PathVariable String username, ModelMap map){
        map.addAttribute("usuariolog",new Usuario());
        map.addAttribute("mensaje", "");
        session.removeAttribute("user");
        return "redirect:../../index";
    }
    
    @RequestMapping(value="/{username}", method=RequestMethod.GET)
    public String perfilView(@PathVariable String username, ModelMap map) {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("mensaje","");
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","class=\"active\"");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        return "perfil";
    }
    
    @RequestMapping(value="/{username}/guardarteo", method=RequestMethod.GET)
    public String guardarTeoView(@PathVariable String username,ModelMap map) {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("agregarTeorema",new AgregarTeorema());
        map.addAttribute("modificar",new Integer(0));
        map.addAttribute("teorema","");
        map.addAttribute("categoria","");
        map.addAttribute("numeroTeorema","");
        
        
        map.addAttribute("mensaje", "");
        map.addAttribute("admin","admin");
        map.addAttribute("guardarMenu","");
        map.addAttribute("agregarTeoremaMenu","class=\"active\"");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        
        return "agregarTeorema";
    }
    
        
    @RequestMapping(value="/{username}/guardarteo", method=RequestMethod.POST)
    public String guardar(@Valid AgregarTeorema agregarTeorema, BindingResult bindingResult, @PathVariable String username, ModelMap map)
    {
            if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
            {
                return "redirect:/index";
            }
            //Aqui hay que validar sintaxis se puede hacer como un aspecto con 
            // un @Valid
            //aqui se acomoda la estructura de la entidad Termino ya que en la
            // vista lo que se construyo fue un TerminoId nada mas y se uso el 
            // campo login para guardar el String combinador
            if(bindingResult.hasErrors())
            {
                map.addAttribute("usuario", usuarioManager.getUsuario(username));

                map.addAttribute("agregarTeorema",agregarTeorema);
                map.addAttribute("modificar",new Integer(0));
                map.addAttribute("teorema",agregarTeorema.getTeorema());
                map.addAttribute("categoria",agregarTeorema.getCategoria());
                map.addAttribute("numeroTeorema",agregarTeorema.getNumeroTeorema());
        
        
                map.addAttribute("mensaje", "");
                map.addAttribute("admin","admin");
                map.addAttribute("guardarMenu","");
                map.addAttribute("agregarTeoremaMenu","class=\"active\"");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "agregarTeorema";
            }
            
            
            TerminoId terminoid=new TerminoId();   
            terminoid.setLogin(username);           
            
            Teorema teorema = new Teorema();
            Resuelve resuelve = new Resuelve();
            
            Categoria categoria = new Categoria(agregarTeorema.getCategoria());
//            categoria.setNombre(agregarTeorema.getCategoria());
            categoriaManager.addCategoria(categoria);
            

                        Categoria categoria1 = new Categoria("Categoria1");
//            categoria.setNombre(agregarTeorema.getCategoria());
            categoriaManager.addCategoria(categoria1);

                        Categoria categoria2 = new Categoria("Categoria2");
//            categoria.setNombre(agregarTeorema.getCategoria());
            categoriaManager.addCategoria(categoria2);

                        Categoria categoria3 = new Categoria("Categoria3");
//            categoria.setNombre(agregarTeorema.getCategoria());
            categoriaManager.addCategoria(categoria3);

                        Categoria categoria4 = new Categoria("Categoria4");
//            categoria.setNombre(agregarTeorema.getCategoria());
            categoriaManager.addCategoria(categoria4);

            
            Usuario user=usuarioManager.getUsuario(username);
            
            resuelve.setUsuario(user);
            
            teorema.setCategoria(categoria);
            teorema.setOcultartrue(false);
            
            // Esto hay que agregarle todos los resuelve que incluye este teorema
            // getAllResuelveByTeorema y agregar el resuelve q acabo de crear
//            teorema.setResuelves(new HashSet(0));
            
            resuelve.setResuelto(false);         
            resuelve.setNumeroteorema(agregarTeorema.getNumeroTeorema());
            
            // Falta agregar el nombre del teorema a la vista, si no es null, se
            // agrega este campo
            //resuelve.setNombreteorema(agregarTeorema.getNombreTeorema());
            
            
//            Solucion solucion = new Solucion();
//            resuelve.setSolucions(solucion);
            
            
            

            
            
            TerminoId terminoid2=new TerminoId();
            terminoid2.setLogin(username);
            
            
            ANTLRStringStream in = new ANTLRStringStream(agregarTeorema.getTeorema());
            TermLexer lexer = new TermLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TermParser parser = new TermParser(tokens);
            Term teoTerm;
            try //si la sintanxis no es correcta ocurre una Exception
            {

                teoTerm =parser.start_rule(terminoid2,terminoManager);
                teoTerm.setAlias(0);
                // inicializando pa q no ladille java
                Term izq = null;
                Term der = null;
                boolean esEq = true;
                
                try {
                    Const relation = (Const)((App)((App)teoTerm).p).p;
                    esEq = relation.getCon().trim().equals("\\equiv");
                    izq = ((App)teoTerm).q;
                    der = ((App)((App)teoTerm).p).q;
                } 
                catch (java.lang.ClassCastException e) {
                    esEq = false;
                }
                
                if (!esEq){
                    izq = teoTerm;
                    der = new Const("true");
                }
                
                
                teorema.setEnunciadoizq(izq.toString());
                teorema.setEnunciadoder(der.toString());
                teorema.setTeoserializadoizq(izq.toString());
                teorema.setTeoserializadoder(der.toString());
                teorema.setOcultartrue(true);
                // FALTA SETEAR LOS SERIALIZADOS, QUE AUN ESTAN EN LA TABLA RESUELVE, Y NO EN LA TABLA TEOREMA
                //      esta en RESUELVE cambia a TEOREMA
//     private String teoserializadoizq;
//     private String teoserializadoder;
                
                
                // Este teorema sera utilizado para ver si ya existe en la BD
                Teorema teorema2 = teoremaManager.getTeoremaByEnunciados(izq.toString(), der.toString());
                if(teorema2 != null) {
                    throw new TeoremaException("el teorema ya existe");
                } else {
                    // Este teorema sera utilizado para ver si el inverso ya existe en la BD
                    Teorema teorema3 = teoremaManager.getTeoremaByEnunciados(der.toString(),izq.toString());
                    if(teorema3 != null) {
                        throw new TeoremaException("el teorema ya existe aplicando conmutatividad (p == q) == (q == p)");
                    }
                }
                            
            
            

            
//            resuelve.setTeorema(teorema);
                
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("mensaje", "Su teorema ha sido guardado con exito");

                
                teoremaManager.addTeorema(teorema);
        
                map.addAttribute("admin","admin");
                map.addAttribute("guardarMenu","");
                map.addAttribute("agregarTeoremaMenu","");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","class=\"active\"");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "perfil";
                
            }
            catch(TeoremaException e)
            {
                map.addAttribute("usuarioGuardar",new UsuarioGuardar());
                map.addAttribute("usuario",user);
                map.addAttribute("modificar",new Integer(0));
                map.addAttribute("mensaje", "No se puede ingresar su teorema ya que "+e.alias);
                map.addAttribute("admin","admin");
                map.addAttribute("guardarMenu","class=\"active\"");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
            catch(IsNotInDBException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));

                map.addAttribute("agregarTeorema",agregarTeorema);
                map.addAttribute("modificar",new Integer(0));
                map.addAttribute("teorema",agregarTeorema.getTeorema());
                map.addAttribute("categoria",agregarTeorema.getCategoria());
                map.addAttribute("numeroTeorema",agregarTeorema.getNumeroTeorema());
                
                map.addAttribute("mensaje", hdr +((IsNotInDBException)e).message);

                map.addAttribute("admin","admin");
                map.addAttribute("guardarMenu","");
                map.addAttribute("agregarTeoremaMenu","class=\"active\"");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "agregarTeorema";
            }
            catch(RecognitionException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                
                map.addAttribute("infer",new InfersForm());
                map.addAttribute("mensaje", hdr+" "+msg);
                
                map.addAttribute("agregarTeorema",agregarTeorema);
                map.addAttribute("modificar",new Integer(0));
                map.addAttribute("teorema",agregarTeorema.getTeorema());
                map.addAttribute("categoria",agregarTeorema.getCategoria());
                map.addAttribute("numeroTeorema",agregarTeorema.getNumeroTeorema());

                map.addAttribute("admin","admin");
                map.addAttribute("guardarMenu","");
                map.addAttribute("agregarTeoremaMenu","class=\"active\"");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "agregarTeorema";
            }
    }
        
        
    /**
     *
     * @param username
     * @param map
     * @return
     */
    @RequestMapping(value="/{username}/guardar", method=RequestMethod.GET)
    public String guardarView(@PathVariable String username,ModelMap map) {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("usuarioGuardar",new UsuarioGuardar());
        map.addAttribute("modificar",new Integer(0));
        map.addAttribute("termino","");
        map.addAttribute("alias","");
        map.addAttribute("mensaje", "");
        map.addAttribute("admin","admin");
        map.addAttribute("guardarMenu","class=\"active\"");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        
        return "introducirTermino";
    }
    
    @RequestMapping(value="/{username}/guardar", method=RequestMethod.POST)
    public String guardar(@Valid UsuarioGuardar usuarioGuardar, BindingResult bindingResult, @PathVariable String username, ModelMap map)
    {
            if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
            {
                return "redirect:/index";
            }
            //Aqui hay que validar sintaxis se puede hacer como un aspecto con 
            // un @Valid
            //aqui se acomoda la estructura de la entidad Termino ya que en la
            // vista lo que se construyo fue un TerminoId nada mas y se uso el 
            // campo login para guardar el String combinador
            if(bindingResult.hasErrors())
            {
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("modificar",new Integer(0));
                map.addAttribute("termino",usuarioGuardar.getTermino());
                map.addAttribute("alias",usuarioGuardar.getAlias());
                map.addAttribute("mensaje", "");
                map.addAttribute("admin","admin");
                map.addAttribute("guardarMenu","class=\"active\"");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
            
            
            TerminoId terminoid = new TerminoId();
            String alias=usuarioGuardar.getAlias();
            if(username.equals("admin"))
                alias=alias.substring(0, alias.length()-1);
            terminoid.setAlias(alias);
            terminoid.setLogin(username);
            Termino termino = new Termino();
            Usuario user=usuarioManager.getUsuario(username);
            termino.setUsuario(user);
            termino.setId(terminoid);
            TerminoId terminoid2=new TerminoId();
            terminoid2.setLogin(username);
            String programa=usuarioGuardar.getTermino();
            
            //Hay que construir un Term aqui con el String termino.combinador
            //para luego traducir, hace falta construir un parse   
            ANTLRStringStream in = new ANTLRStringStream(programa);
            TermLexer lexer = new TermLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TermParser parser = new TermParser(tokens);
            Term term;
            try //si la sintanxis no es correcta ocurre una Exception
            {

            
                //aqui hay que hacer un query para verificar que el combinador 
                //es no esta ya en la BD, poner esta verificacion en el dig de sec

                Termino terminoEnBD=terminoManager.getTermino(terminoid); //arreglar solo consigue los tuyos mas no los de admin y publico
                if(terminoEnBD == null)
                {
                    //System.out.println(terminoManager.getTermino(terminoid));
                    term=parser.start_rule(terminoid2,terminoManager);
                    term.setAlias(terminoid.getAlias());
                    //aqui se traduce y luego se llama a toString para tener el
                    //combinador en String
                    termino.setTermObject(term);//este metodo serializa de ademas de setear el terminoObject
                    termino.setCombinador(term.traducBD().toStringFinal());
                    Termino termino2=terminoManager.getCombinador(username, termino.getCombinador());
                    if(termino2 != null) 
                        throw new AlphaEquivalenceException(termino2.getId().getAlias());
//                    termino.setSerializado(ToString.toString(term));
                    //verificar si el String combinador existe pero con otro alias
                    termino.getId().setLogin(username);
                    terminoManager.addTermino(termino);
                    map.addAttribute("mensaje", "Su t&eacute;rmino ha sido guardado con exito");
                    map.addAttribute("usuario", usuarioManager.getUsuario(username));
                    map.addAttribute("guardarMenu","");
                    map.addAttribute("listarTerminosMenu","");
                    map.addAttribute("verTerminosPublicosMenu","");
                    map.addAttribute("misPublicacionesMenu","");
                    map.addAttribute("computarMenu","");
                    map.addAttribute("perfilMenu","class=\"active\"");
                    map.addAttribute("overflow","hidden");
                    map.addAttribute("anchuraDiv","1100px");
                    return "perfil";
                }
                else
                {
                    map.addAttribute("usuarioGuardar",new UsuarioGuardar());
                    map.addAttribute("usuario",user);
                    map.addAttribute("modificar",new Integer(0));
                    map.addAttribute("mensaje", "ya existe un t&eacute;rmino con el alias que usted ha colocado");
                    map.addAttribute("termino",programa);
                    map.addAttribute("admin","admin");
                    if(username.equals("admin"))
                        map.addAttribute("alias",alias+"_");
                    else
                        map.addAttribute("alias",alias);
                    map.addAttribute("guardarMenu","class=\"active\"");
                    map.addAttribute("listarTerminosMenu","");
                    map.addAttribute("verTerminosPublicosMenu","");
                    map.addAttribute("misPublicacionesMenu","");
                    map.addAttribute("computarMenu","");
                    map.addAttribute("perfilMenu","");
                    map.addAttribute("overflow","hidden");
                    map.addAttribute("anchuraDiv","1100px");
                    return "introducirTermino";
                }
            }
            catch(AlphaEquivalenceException e)
            {
                map.addAttribute("usuarioGuardar",new UsuarioGuardar());
                map.addAttribute("usuario",user);
                map.addAttribute("modificar",new Integer(0));
                map.addAttribute("mensaje", "No se puede ingresar su t&eacute;rmino ya que es alpha equivalente al t&eacute;rmino ya existente "+e.alias);
                map.addAttribute("termino",programa);
                map.addAttribute("admin","admin");
                if(username.equals("admin"))
                   map.addAttribute("alias",alias+"_");
                else
                   map.addAttribute("alias",alias);
                map.addAttribute("guardarMenu","class=\"active\"");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
            catch(IsNotInDBException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario",user);
                map.addAttribute("usuarioGuardar",new UsuarioGuardar());
                map.addAttribute("modificar",new Integer(0));
                map.addAttribute("mensaje", hdr +((IsNotInDBException)e).message);
                map.addAttribute("termino",programa);
                map.addAttribute("admin","admin");
                if(username.equals("admin"))
                   map.addAttribute("alias",alias+"_");
                else
                   map.addAttribute("alias",alias);
                map.addAttribute("guardarMenu","class=\"active\"");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
            catch(RecognitionException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario",user);
                map.addAttribute("usuarioGuardar",new UsuarioGuardar());
                map.addAttribute("modificar",new Integer(0));
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("termino",programa);
                map.addAttribute("admin","admin");
                if(username.equals("admin"))
                   map.addAttribute("alias",alias+"_");
                else
                   map.addAttribute("alias",alias);
                map.addAttribute("guardarMenu","class=\"active\"");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
    }
    
    @RequestMapping(value="/{username}/modificaralias", method=RequestMethod.GET)
    public String modificarAliasView(ModelMap map, @PathVariable String username, @RequestParam("aliasv") String aliasv) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        //map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("termino","");
        map.addAttribute("modificarAliasForm",new ModificarAliasForm());
        map.addAttribute("modificar",new Integer(2));
        if(username.equals("admin"))
            map.addAttribute("alias",aliasv+"_");
        else
            map.addAttribute("alias",aliasv);
        map.addAttribute("mensaje", "");
        map.addAttribute("admin","admin");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        
        return "introducirTermino";
    }
    
    @RequestMapping(value="/{username}/modificar", method=RequestMethod.GET)
    public String modificarView(ModelMap map, @PathVariable String username, @RequestParam("alias") String alias) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        //map.addAttribute("usuario", usuarioManager.getUsuario(username));
        TerminoId id= new TerminoId();
        id.setAlias(alias);
        id.setLogin(username);
        Termino t=terminoManager.getTermino(id);
        String term=t.getTermObject().toStringFinal();
        String termino;
        termino = term.replace("\\","" ).replace("}", "").replaceAll("[_][{]", "");
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("termino",termino);
        map.addAttribute("modificarForm",new UsuarioGuardar());
        map.addAttribute("modificar",new Integer(1));
        map.addAttribute("alias","");
        map.addAttribute("mensaje", "");
        map.addAttribute("admin","admin");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        
        return "introducirTermino";
    }
    
    @RequestMapping(value="/{username}/modificaralias", method=RequestMethod.POST)
    public String modificarAlias(@Valid ModificarAliasForm modificarAliasForm, BindingResult bindingResult, @PathVariable String username, @RequestParam("aliasv") String aliasv, ModelMap map)
    {
            if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
            {
                return "redirect:/index";
            }
            //Aqui hay que validar sintaxis se puede hacer como un aspecto con 
            // un @Valid
        
            //aqui se acomoda la estructura de la entidad Termino ya que en la
            // vista lo que se construyo fue un TerminoId nada mas y se uso el 
            // campo login para guardar el String combinador
            if(bindingResult.hasErrors())
            {
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("modificar",new Integer(2));
                map.addAttribute("termino","");
                map.addAttribute("alias",modificarAliasForm.getAlias());
                map.addAttribute("mensaje", "");
                map.addAttribute("admin","admin");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
        
            String aliasNuevo=modificarAliasForm.getAlias();
            if(username.equals("admin"))
                aliasNuevo=aliasNuevo.substring(0, aliasNuevo.length()-1);
            TerminoId nuevo=new TerminoId();
            TerminoId anterior=new TerminoId();
            nuevo.setAlias(aliasNuevo);
            nuevo.setLogin(username);
            anterior.setAlias(aliasv);
            anterior.setLogin(username);
            Usuario u =usuarioManager.getUsuario(username);
            
            
            Termino terminoEnBD=null;
            if(!aliasv.equals(aliasNuevo))
                terminoEnBD=terminoManager.getTermino(nuevo); //arreglar solo consigue los tuyos mas no los de admin y publico
            if(terminoEnBD == null)
            {
                nuevo.setLogin(username);
                terminoManager.modificarAlias(anterior,nuevo);
                map.addAttribute("mensaje", "Su Alias se ha modificado con exito");
                map.addAttribute("usuario",u);
                map.addAttribute("guardarMenu","");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","class=\"active\"");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "perfil";
            }
            else
            {
                map.addAttribute("modificarAliasForm",new ModificarAliasForm());
                map.addAttribute("usuario",u);
                map.addAttribute("modificar",new Integer(2));
                map.addAttribute("mensaje", "ya existe un t&eacute;rmino con el alias que usted ha colocado");
                map.addAttribute("termino","");
                map.addAttribute("admin","admin");
                if(username.equals("admin"))
                    map.addAttribute("alias",aliasNuevo+"_");
                else
                    map.addAttribute("alias",aliasNuevo);
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
    }
    
    @RequestMapping(value="/{username}/modificar", method=RequestMethod.POST)
    public String modificar(@Valid ModificarForm modificarForm, BindingResult bindingResult, @PathVariable String username, @RequestParam("alias") String alias, ModelMap map)
    {
            if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
            {
                return "redirect:/index";
            }
            //Aqui hay que validar sintaxis se puede hacer como un aspecto con 
            // un @Valid
        
            //aqui se acomoda la estructura de la entidad Termino ya que en la
            // vista lo que se construyo fue un TerminoId nada mas y se uso el 
            // campo login para guardar el String combinador
            if(bindingResult.hasErrors())
            {
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("modificar",new Integer(1));
                map.addAttribute("termino",modificarForm.getTermino());
                map.addAttribute("alias","");
                map.addAttribute("mensaje", "");
                map.addAttribute("admin","admin");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
        
            TerminoId terminoid=new TerminoId();
            terminoid.setAlias(alias);
            terminoid.setLogin(username);
            Termino termino = new Termino();
            Usuario u =usuarioManager.getUsuario(username);
            termino.setUsuario(u);
            termino.setId(terminoid);
            TerminoId terminoid2=new TerminoId();
            terminoid2.setLogin(terminoid.getLogin());
            String programa=modificarForm.getTermino();
            
            //Hay que construir un Term aqui con el String termino.combinador
            //para luego traducir, hace falta construir un parse   
            ANTLRStringStream in = new ANTLRStringStream(programa);
            TermLexer lexer = new TermLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TermParser parser = new TermParser(tokens);
            Term term;
            try //si la sintanxis no es correcta ocurre una Exception
            {
                term=parser.start_rule(terminoid2,terminoManager);
                term.alias=alias;
                //aqui se traduce y luego se llama a toString para tener el
                //combinador en String
                termino.setTermObject(term);//este metodo serializa de ademas de setear el terminoObject
                termino.setCombinador(term.traducBD().toStringFinal());
                
            
                //verificar si el String combinador existe pero con otro alias
                Termino termino2=terminoManager.getCombinador(username, termino.getCombinador());
                //revisar si la instruccion de arriva arroja una excepcion para diferenciarla de las del parse
                if(termino2 != null) 
                {
                    if(!termino2.getId().getAlias().equals(alias))
                        throw new AlphaEquivalenceException(termino2.getId().getAlias());
                }

                terminoManager.modificarTermino(termino);
                map.addAttribute("mensaje", "Su t&eacute;rmino se ha modificado con exito");
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("guardarMenu","");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","class=\"active\"");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "perfil";
            }
            catch(AlphaEquivalenceException e)
            {
                map.addAttribute("terminoid",new TerminoId());
                map.addAttribute("usuario",usuarioManager.getUsuario(username));
                map.addAttribute("modificar",new Integer(1));
                map.addAttribute("mensaje", "No se puede ingresar su t&eacute;rmino ya que es alpha equivalente al t&eacute;rmino ya existente "+e.alias);
                map.addAttribute("termino",programa);
                map.addAttribute("alias","");
                map.addAttribute("admin","admin");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
            catch(IsNotInDBException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("terminoid",new TerminoId());
                map.addAttribute("usuario",usuarioManager.getUsuario(username));                
                map.addAttribute("modificar",new Integer(1));
                map.addAttribute("mensaje", hdr +((IsNotInDBException)e).message);
                map.addAttribute("termino",programa);
                map.addAttribute("alias","");
                map.addAttribute("admin","admin");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
            catch(RecognitionException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("terminoid",new TerminoId());
                map.addAttribute("usuario",usuarioManager.getUsuario(username));                
                map.addAttribute("modificar",new Integer(1));
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("termino",programa);
                map.addAttribute("alias","");
                map.addAttribute("admin","admin");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
    }
    
    @RequestMapping(value="/{username}/listar", method=RequestMethod.GET)
    public String listarView(@PathVariable String username, ModelMap map, @RequestParam("comb") String comb) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }    
        map.addAttribute("titulo", "Mis T&eacute;rminos");
        map.addAttribute("publico", "publico");
        map.addAttribute("admin","admin");
        map.addAttribute("yes","yes");
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("terminos", terminoManager.getAllTerminos(username));
        if(comb.equals("y"))
            map.addAttribute("comb", new Integer(1));
        else
            map.addAttribute("comb", new Integer(0));
        map.addAttribute("mensaje","");
        map.addAttribute("accion","listar");
        map.addAttribute("perfil",new Integer(1));
        map.addAttribute("click","no");
        map.addAttribute("publicaciones",new Integer(0));
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","class=\"active\"");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","");
        map.addAttribute("overflow","scroll");
        map.addAttribute("anchuraDiv","1163px");
        
        return "listar";
    }
    
    @RequestMapping(value="/{username}/mispublic", method=RequestMethod.GET)
    public String misPublicacionesView(@PathVariable String username, ModelMap map, @RequestParam("comb") String comb) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("titulo", "Mis Publicaciones");
        map.addAttribute("publico", "publico");
        map.addAttribute("admin","admin");
        map.addAttribute("yes","yes");
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("terminos", terminoManager.getAllPublicaciones(username));
        if(comb.equals("y"))
            map.addAttribute("comb", new Integer(1));
        else
            map.addAttribute("comb", new Integer(0));
        map.addAttribute("mensaje","");
        map.addAttribute("accion","mispublic");
        map.addAttribute("perfil",new Integer(1));
        map.addAttribute("click","no");
        map.addAttribute("publicaciones",new Integer(1));
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","class=\"active\"");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","");
        map.addAttribute("overflow","scroll");
        map.addAttribute("anchuraDiv","1163px");
        
        return "listar";
    }
    
    @RequestMapping(value="/{username}/listarocult", method=RequestMethod.GET)
    public String listarOcultEdicionView(@PathVariable String username, ModelMap map, @RequestParam("comb") String comb) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("titulo", "Mis T&eacute;rminos");
        map.addAttribute("publico", "publico");
        map.addAttribute("admin","admin");
        map.addAttribute("yes","yes");
        map.addAttribute("usuario", usuarioManager.getUsuario("publico"));
        map.addAttribute("terminos", terminoManager.getAllTerminos(username));
        if(comb.equals("y"))
            map.addAttribute("comb", new Integer(1));
        else
            map.addAttribute("comb", new Integer(0));
        map.addAttribute("mensaje","");
        map.addAttribute("accion","listarocult");
        map.addAttribute("perfil",new Integer(0));
        map.addAttribute("click","yes");
        map.addAttribute("publicaciones",new Integer(0));
        
        return "listar";
    }
    
    @RequestMapping(value="/{username}/publico", method=RequestMethod.GET)
    public String listarView(ModelMap map, @RequestParam("comb") String comb, @PathVariable String username) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("titulo", "T&eacute;rminos publicos");
        map.addAttribute("publico", "publico");
        map.addAttribute("admin","admin");
        map.addAttribute("yes","yes");
        map.addAttribute("usuario", usuarioManager.getUsuario("publico"));
        map.addAttribute("terminos", terminoManager.getAllTerminos("publico"));
        if(comb.equals("y"))
            map.addAttribute("comb", new Integer(1));
        else
            map.addAttribute("comb", new Integer(0));
        map.addAttribute("mensaje","");
        map.addAttribute("accion","publico");
        map.addAttribute("perfil",new Integer(1));
        map.addAttribute("click","no");
        map.addAttribute("publicaciones",new Integer(0));
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","class=\"active\"");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","");
        map.addAttribute("overflow","scroll");
        map.addAttribute("anchuraDiv","1163px");
        
        return "listar";
    }
    
    @RequestMapping(value="/{username}/publiconoclick", method=RequestMethod.GET)
    public String listarViewNoClick(ModelMap map, @RequestParam("comb") String comb, @PathVariable String username) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("titulo", "T&eacute;rminos publicos");
        map.addAttribute("publico", "publico");
        map.addAttribute("admin","admin");
        map.addAttribute("yes","yes");
        map.addAttribute("usuario", usuarioManager.getUsuario("publico"));
        map.addAttribute("terminos", terminoManager.getAllTerminos("publico"));
        if(comb.equals("y"))
            map.addAttribute("comb", new Integer(1));
        else
            map.addAttribute("comb", new Integer(0));
        map.addAttribute("mensaje","");
        map.addAttribute("accion","publiconoclick");
        map.addAttribute("perfil",new Integer(0));
        map.addAttribute("click","yes");
        map.addAttribute("publicaciones",new Integer(0));
        
        return "listar";
    }
    
    @RequestMapping(value="/{username}/predef", method=RequestMethod.GET)
    public String predefView(ModelMap map, @RequestParam("comb") String comb, @PathVariable String username) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("titulo", "T&eacute;rminos predefinidos");
        map.addAttribute("publico", "publico");
        map.addAttribute("admin","admin");
        map.addAttribute("yes","yes");
        map.addAttribute("usuario", usuarioManager.getUsuario("publico"));
        map.addAttribute("terminos", terminoManager.getAllTerminos("admin"));
        if(comb.equals("y"))
            map.addAttribute("comb", new Integer(1));
        else
            map.addAttribute("comb", new Integer(0));
        map.addAttribute("mensaje","");
        map.addAttribute("accion","predef");
        map.addAttribute("perfil",new Integer(0));
        map.addAttribute("click","yes");
        map.addAttribute("publicaciones",new Integer(0));
        
        return "listar";
    }
    
    @RequestMapping(value="/{username}/eliminar", 
            method=RequestMethod.GET)
    public String eliminarTermino(@PathVariable String username, ModelMap map, @RequestParam("alias") String alias)
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        TerminoId id=new TerminoId( alias, username);
        terminoManager.deleteTermino(id);
        return "redirect:../../perfil/"+username+"/listar?comb=n";
    }
    
    @RequestMapping(value="/{username}/eliminarpubl", 
            method=RequestMethod.GET)
    public String eliminarPublicacion(@PathVariable String username, ModelMap map, @RequestParam("alias") String alias)
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        TerminoId id=new TerminoId( alias, username);
        terminoManager.deletePublicacion(id);
        return "redirect:../../perfil/"+username+"/mispublic?comb=n";
    }
    
    @RequestMapping(value="/{username}/publicar", method=RequestMethod.GET)
    public String publicarTermino(@PathVariable String username, ModelMap map, @RequestParam("alias") String alias)
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        TerminoId terminoid = new TerminoId(alias,username);
        PublicacionId publicacionId = new PublicacionId(alias.substring(0, alias.length()-1),username);
        TerminoId publicTerminoid = new TerminoId(alias.substring(0, alias.length()-1),"publico");
        Termino termino0 = terminoManager.getTermino(publicTerminoid);
        if(termino0 != null)
        {
            map.addAttribute("titulo", "Mis T&eacute;rminos");
            map.addAttribute("publico", "publico");
            map.addAttribute("admin","admin");
            map.addAttribute("usuario", usuarioManager.getUsuario(username));
            map.addAttribute("terminos", terminoManager.getAllTerminos(username));
            map.addAttribute("comb", new Integer(0));
            map.addAttribute("perfil",new Integer(1));
            map.addAttribute("accion","listar");
            map.addAttribute("mensaje", "No se puede publicar su t&eacute;rmino ya que ya existe un t&eacute;rmino p&uacute;blico con el alias "+publicacionId.getAlias());
            map.addAttribute("publicaciones",new Integer(0));
            map.addAttribute("guardarMenu","");
            map.addAttribute("listarTerminosMenu","class=\"active\"");
            map.addAttribute("verTerminosPublicosMenu","");
            map.addAttribute("misPublicacionesMenu","");
            map.addAttribute("computarMenu","");
            map.addAttribute("perfilMenu","");
            map.addAttribute("overflow","scroll");
            map.addAttribute("anchuraDiv","1163px");
            return "listar";
        }
        Termino termino = terminoManager.getTermino(terminoid);
        Termino termino2 = terminoManager.getCombinador("publico", termino.getCombinador());
        Termino termino3 = terminoManager.getCombinador("admin", termino.getCombinador());
        try{
            if(termino2 != null)
                throw new AlphaEquivalenceException(termino2.getId().getAlias());
            else if(termino3 != null)
                throw new AlphaEquivalenceException(termino3.getId().getAlias());
        }
        catch(AlphaEquivalenceException e)
        {
            map.addAttribute("titulo", "Mis T&eacute;rminos");
            map.addAttribute("publico", "publico");
            map.addAttribute("admin","admin");
            map.addAttribute("usuario", usuarioManager.getUsuario(username));
            map.addAttribute("terminos", terminoManager.getAllTerminos(username));
            map.addAttribute("comb", new Integer(0));
            map.addAttribute("perfil",new Integer(1));
            map.addAttribute("accion","listar");
            map.addAttribute("mensaje", "No se puede publicar su t&eacute;rmino ya que es alpha equivalente al t&eacute;rmino p&uacute;blico ya existente "+e.alias);
            map.addAttribute("publicaciones",new Integer(0));
            map.addAttribute("guardarMenu","");
            map.addAttribute("listarTerminosMenu","class=\"active\"");
            map.addAttribute("verTerminosPublicosMenu","");
            map.addAttribute("misPublicacionesMenu","");
            map.addAttribute("computarMenu","");
            map.addAttribute("perfilMenu","");
            map.addAttribute("overflow","scroll");
            map.addAttribute("anchuraDiv","1163px");
            return "listar";
        }
        PublicacionId publicacionId2 = new PublicacionId(alias.substring(0, alias.length()-1),username);
        Publicacion publicacion = new Publicacion(publicacionId2, usuarioManager.getUsuario(username));
        Termino terminoPublico=new Termino();
        TerminoId publicTerminoid2 = new TerminoId(alias.substring(0, alias.length()-1),"publico");
        terminoPublico.setId(publicTerminoid2);
        terminoPublico.setCombinador(termino.getCombinador());
        Term cambioDeAlias = termino.getTermObject();
        cambioDeAlias.setAlias(alias.substring(0, alias.length()-1));
        terminoPublico.setTermObject(cambioDeAlias);
        terminoPublico.setUsuario(usuarioManager.getUsuario("publico"));
        terminoManager.addPublicacion(terminoPublico,publicacion);
        map.addAttribute("mensaje", "Su t&eacute;rmino ha sido publicado con exito");
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","class=\"active\"");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        
        return "perfil";
    }
    
    @RequestMapping(value="/{username}/ingresar", method=RequestMethod.GET)
    public String insertarEvaluarView(@PathVariable String username, ModelMap map)
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        //map.addAttribute("terminoid",new TerminoId());
        map.addAttribute("insertarEvaluar",new InsertarEvaluar());
        map.addAttribute("mensaje","");
        //map.addAttribute("modificar",new Integer(0));
        map.addAttribute("nombre","");
        map.addAttribute("termino","");
        map.addAttribute("admin","admin");
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","class=\"active\"");
        map.addAttribute("perfilMenu","");
        map.addAttribute("hrefAMiMismo","href=ingresar#!");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        
        return "insertarEvaluar";
    }
    
    public void setUsuarioManager(UsuarioManager usuarioManager) 
    {
            this.usuarioManager = usuarioManager;
    }
    
    public void setTerminoManager(TerminoManager terminoManager) 
    {
            this.terminoManager = terminoManager;
    }

    private static class AlphaEquivalenceException extends Exception
    {

        public String alias;
                
        public AlphaEquivalenceException(String ali) 
        {
            alias=ali;
        }
    }
    
    private static class TeoremaException extends Exception
    {

        public String alias;
                
        public TeoremaException(String ali) 
        {
            alias=ali;
        }
    }

    
}
