grammar Term;

@header{package com.howtodoinjava.parse; 

import com.howtodoinjava.entity.Termino;
import com.howtodoinjava.entity.TerminoId;
import com.howtodoinjava.lambdacalculo.*;
import com.howtodoinjava.service.TerminoManager;
import java.util.Iterator;}

// Parser Rules
start_rule[TerminoId terminoid, TerminoManager terminoManager]  returns [Term value]: eq           { $value=$eq.value;};

eq returns [Term value]: term eqtail          { Term aux=$term.value;
                                                for(Iterator<Term> i = $eqtail.value.iterator(); i.hasNext();) 
                                                   aux=new App(new App(new Const("\\equiv "),i.next()),aux);
                                                $value=aux;
                                              };

eqtail returns [ArrayList<Term> value]:    
    '==' term tail1=eqtail                    {ArrayList<Term> aux=$tail1.value; aux.add(0,$term.value); $value=aux;}

   |                                          {$value=new ArrayList<Term>();};

term returns [Term value]: disyconj disyconjtail  { 
                                                    if ($disyconjtail.value == null)
                                                       $value = $disyconj.value;
                                                    else
                                                       $value = new App($disyconjtail.value,$disyconj.value);
                                                  };

disyconjtail returns [Term value]:  

     '==>' disyconj tail2=disyconjtail        {
                                               if ($tail2.value == null)
                                                  $value = new App(new Const("\\Rightarrow "),$disyconj.value);
                                               else
                                               $value=new App(new Const("\\Rightarrow "),new App($tail2.value,$disyconj.value));
                                              }

   |                                          {$value=null;};

disyconj returns [Term value]: conc conctail  { Term aux=$conc.value;
                                                for(Iterator<Term> i = $conctail.value.iterator(); i.hasNext();) 
                                                   aux=new App(new App(new Const("\\Leftarrow "),i.next()),aux);
                                                $value=aux;
                                              };

conctail returns [ArrayList<Term> value]:

     '<==' conc tail3=conctail                {ArrayList<Term> aux=$tail3.value; 
                                               aux.add(0,$conc.value); $value=aux;
                                              }

   |                                          {$value=new ArrayList<Term>();};

conc returns [Term value]: neg negtail             { Term aux=$neg.value; 
                                                     for(Iterator<ParserPair> i = $negtail.value.iterator(); i.hasNext();)
                                                     {
                                                        ParserPair pair = i.next();
                                                        if (pair.symbol.equals("\\vee "))
                                                           aux=new App(new App(new Const(pair.symbol),pair.term),aux); 
                                                        else if (pair.symbol.equals("\\wedge "))
                                                           aux=new App(new App(new Const(pair.symbol),pair.term),aux); 
                                                     }
                                                     $value=aux;
                                                   };

negtail returns [ArrayList<ParserPair> value]:

     '\\/' neg tail4=negtail                  {ArrayList<ParserPair> aux=$tail4.value;
                                               aux.add(0,new ParserPair("\\vee ",$neg.value)); $value=aux;
                                              }

   | '/\\' neg tail5=negtail                  {ArrayList<ParserPair> aux=$tail5.value; 
                                               aux.add(0,new ParserPair("\\wedge ",$neg.value)); $value=aux;
                                              }

   |                                          {$value=new ArrayList<ParserPair>();};

neg returns [Term value]: 

      '!' n=neg                               {$value=new App(new Const("\\neg "),$n.value);}

     | CAPITALLETTER                          {$value = new Var((new Integer((int)$CAPITALLETTER.text.charAt(0))).intValue());}

     | LETTER                                 {$value = new Var((new Integer((int)$LETTER.text.charAt(0))).intValue());}

     | 'true'                                 {$value = new Const("true ");}

     | 'false'                                {$value = new Const("false ");}

     | CAPITALLETTER '_{' eq '}^{' LETTER '}' {Var letter = new Var((new Integer((int)$LETTER.text.charAt(0))).intValue());
                                               Var capl = new Var((new Integer((int)$CAPITALLETTER.text.charAt(0))).intValue());
                                               $value = new App(new Bracket(letter,capl),$eq.value);
                                              } 

     | WORD '(' arguments ')'                 {Term aux = new Const($WORD.text);
                                               for(Iterator<Var> i = $arguments.value.iterator(); i.hasNext();) 
                                                  aux=new App(aux,i.next());
                                               $value=aux;
                                              }

     | '(' eq ')'                             {$value=$eq.value;};

instantiate returns [ArrayList<Object> value]: 

     arguments ':=' explist                   {ArrayList<Object> arr=new ArrayList<Object>();
                                               arr.add($arguments.value);
                                               arr.add($explist.value);
                                               $value = arr;
                                              };

explist returns [ArrayList<Term> value]: 

     eq  explisttail                          {ArrayList<Term> aux = $explisttail.value;
                                               aux.add(0,$eq.value);
                                               $value = aux;
                                              };

explisttail returns [ArrayList<Term> value]: 

     ',' eq tail6=explisttail                 {ArrayList<Term> aux = $tail6.value;
                                               aux.add(0,$eq.value);
                                               $value =aux;
                                              }

     |                                        {$value = new ArrayList<Term>();};

X:
   'X' NUMBER

  | 'x' NUMBER;

arguments returns [ArrayList<Var> value]: LETTER ',' arg=arguments {ArrayList<Var> aux=$arg.value; 
                                                            Var v=new Var((new Integer((int)$LETTER.text.charAt(0))).intValue());
                                                            aux.add(0,v); 
                                                            $value=aux;
                                                           }

                                         | CAPITALLETTER ',' arg=arguments {ArrayList<Var> aux=$arg.value; 
                                                     Var v=new Var((new Integer((int)$CAPITALLETTER.text.charAt(0))).intValue());
                                                            aux.add(0,v); 
                                                            $value=aux;
                                                           }

                                         | LETTER          {ArrayList<Var> list=new ArrayList<Var>();
                                                            Var v=new Var((new Integer($LETTER.text.charAt(0))).intValue());
                                                            list.add(0,v);
                                                            $value = list;
                                                           }

                                         | CAPITALLETTER   {ArrayList<Var> list=new ArrayList<Var>();
                                                       Var v=new Var((new Integer($CAPITALLETTER.text.charAt(0))).intValue());
                                                             list.add(0,v);
                                                             $value = list;
                                                           };

lambda returns [Term value]: 'lambda' LETTER '.' eq        {Var v=new Var((new Integer($LETTER.text.charAt(0))).intValue());
                                                            $value = new Bracket(v,$eq.value);
                                                           };

INITIALDIGIT: '1'..'9';

DIGIT: '0'|INITIALDIGIT;

NUMBER: '0' | INITIALDIGIT (DIGIT)*;

CAPITALLETTER: 'A'..'Z';

LETTER: 'a'..'z';

WORD:   CAPITALLETTER (LETTER)+

      | CAPITALLETTER;

WHITESPACE: (' ' | '\r')+ {$channel = HIDDEN;};
