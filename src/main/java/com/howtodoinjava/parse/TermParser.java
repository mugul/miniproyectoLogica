// $ANTLR 3.2 Sep 23, 2009 12:02:23 Term.g 2017-03-17 13:30:40
package com.howtodoinjava.parse; 

import com.howtodoinjava.entity.Termino;
import com.howtodoinjava.entity.TerminoId;
import com.howtodoinjava.lambdacalculo.*;
import com.howtodoinjava.service.TerminoManager;
import java.util.Iterator;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TermParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAPITALLETTER", "LETTER", "WORD", "NUMBER", "X", "INITIALDIGIT", "DIGIT", "WHITESPACE", "'=='", "'==>'", "'<=='", "'\\\\/'", "'/\\\\'", "'!'", "'true'", "'false'", "'_{'", "'}^{'", "'}'", "'('", "')'", "':='", "','", "'lambda'", "'.'"
    };
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int LETTER=5;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int NUMBER=7;
    public static final int CAPITALLETTER=4;
    public static final int INITIALDIGIT=9;
    public static final int WHITESPACE=11;
    public static final int EOF=-1;
    public static final int X=8;
    public static final int WORD=6;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int DIGIT=10;

    // delegates
    // delegators


        public TermParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public TermParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TermParser.tokenNames; }
    public String getGrammarFileName() { return "Term.g"; }



    // $ANTLR start "start_rule"
    // Term.g:12:1: start_rule[TerminoId terminoid, TerminoManager terminoManager] returns [Term value] : eq ;
    public final Term start_rule(TerminoId terminoid, TerminoManager terminoManager) throws RecognitionException {
        Term value = null;

        Term eq1 = null;


        try {
            // Term.g:12:85: ( eq )
            // Term.g:12:87: eq
            {
            pushFollow(FOLLOW_eq_in_start_rule21);
            eq1=eq();

            state._fsp--;

             value =eq1;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "start_rule"


    // $ANTLR start "eq"
    // Term.g:14:1: eq returns [Term value] : term eqtail ;
    public final Term eq() throws RecognitionException {
        Term value = null;

        Term term2 = null;

        ArrayList<Term> eqtail3 = null;


        try {
            // Term.g:14:24: ( term eqtail )
            // Term.g:14:26: term eqtail
            {
            pushFollow(FOLLOW_term_in_eq44);
            term2=term();

            state._fsp--;

            pushFollow(FOLLOW_eqtail_in_eq46);
            eqtail3=eqtail();

            state._fsp--;

             Term aux=term2;
                                                            for(Iterator<Term> i = eqtail3.iterator(); i.hasNext();) 
                                                               aux=new App(new App(new Const("\\equiv "),i.next()),aux);
                                                            value =aux;
                                                          

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "eq"


    // $ANTLR start "eqtail"
    // Term.g:20:1: eqtail returns [ArrayList<Term> value] : ( '==' term tail1= eqtail | );
    public final ArrayList<Term> eqtail() throws RecognitionException {
        ArrayList<Term> value = null;

        ArrayList<Term> tail1 = null;

        Term term4 = null;


        try {
            // Term.g:20:39: ( '==' term tail1= eqtail | )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==12) ) {
                alt1=1;
            }
            else if ( (LA1_0==EOF||LA1_0==21||LA1_0==24||LA1_0==26) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // Term.g:21:5: '==' term tail1= eqtail
                    {
                    match(input,12,FOLLOW_12_in_eqtail76); 
                    pushFollow(FOLLOW_term_in_eqtail78);
                    term4=term();

                    state._fsp--;

                    pushFollow(FOLLOW_eqtail_in_eqtail82);
                    tail1=eqtail();

                    state._fsp--;

                    ArrayList<Term> aux=tail1; aux.add(0,term4); value =aux;

                    }
                    break;
                case 2 :
                    // Term.g:23:47: 
                    {
                    value =new ArrayList<Term>();

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "eqtail"


    // $ANTLR start "term"
    // Term.g:25:1: term returns [Term value] : disyconj disyconjtail ;
    public final Term term() throws RecognitionException {
        Term value = null;

        Term disyconjtail5 = null;

        Term disyconj6 = null;


        try {
            // Term.g:25:26: ( disyconj disyconjtail )
            // Term.g:25:28: disyconj disyconjtail
            {
            pushFollow(FOLLOW_disyconj_in_term163);
            disyconj6=disyconj();

            state._fsp--;

            pushFollow(FOLLOW_disyconjtail_in_term165);
            disyconjtail5=disyconjtail();

            state._fsp--;

             
                                                                if (disyconjtail5 == null)
                                                                   value = disyconj6;
                                                                else
                                                                   value = new App(disyconjtail5,disyconj6);
                                                              

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "term"


    // $ANTLR start "disyconjtail"
    // Term.g:32:1: disyconjtail returns [Term value] : ( '==>' disyconj tail2= disyconjtail | );
    public final Term disyconjtail() throws RecognitionException {
        Term value = null;

        Term tail2 = null;

        Term disyconj7 = null;


        try {
            // Term.g:32:34: ( '==>' disyconj tail2= disyconjtail | )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            else if ( (LA2_0==EOF||LA2_0==12||LA2_0==21||LA2_0==24||LA2_0==26) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // Term.g:34:6: '==>' disyconj tail2= disyconjtail
                    {
                    match(input,13,FOLLOW_13_in_disyconjtail187); 
                    pushFollow(FOLLOW_disyconj_in_disyconjtail189);
                    disyconj7=disyconj();

                    state._fsp--;

                    pushFollow(FOLLOW_disyconjtail_in_disyconjtail193);
                    tail2=disyconjtail();

                    state._fsp--;


                                                                   if (tail2 == null)
                                                                      value = new App(new Const("\\Rightarrow "),disyconj7);
                                                                   else
                                                                   value =new App(new Const("\\Rightarrow "),new App(tail2,disyconj7));
                                                                  

                    }
                    break;
                case 2 :
                    // Term.g:41:47: 
                    {
                    value =null;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "disyconjtail"


    // $ANTLR start "disyconj"
    // Term.g:43:1: disyconj returns [Term value] : conc conctail ;
    public final Term disyconj() throws RecognitionException {
        Term value = null;

        Term conc8 = null;

        ArrayList<Term> conctail9 = null;


        try {
            // Term.g:43:30: ( conc conctail )
            // Term.g:43:32: conc conctail
            {
            pushFollow(FOLLOW_conc_in_disyconj262);
            conc8=conc();

            state._fsp--;

            pushFollow(FOLLOW_conctail_in_disyconj264);
            conctail9=conctail();

            state._fsp--;

             Term aux=conc8;
                                                            for(Iterator<Term> i = conctail9.iterator(); i.hasNext();) 
                                                               aux=new App(new App(new Const("\\Leftarrow "),i.next()),aux);
                                                            value =aux;
                                                          

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "disyconj"


    // $ANTLR start "conctail"
    // Term.g:49:1: conctail returns [ArrayList<Term> value] : ( '<==' conc tail3= conctail | );
    public final ArrayList<Term> conctail() throws RecognitionException {
        ArrayList<Term> value = null;

        ArrayList<Term> tail3 = null;

        Term conc10 = null;


        try {
            // Term.g:49:41: ( '<==' conc tail3= conctail | )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            else if ( (LA3_0==EOF||(LA3_0>=12 && LA3_0<=13)||LA3_0==21||LA3_0==24||LA3_0==26) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // Term.g:51:6: '<==' conc tail3= conctail
                    {
                    match(input,14,FOLLOW_14_in_conctail284); 
                    pushFollow(FOLLOW_conc_in_conctail286);
                    conc10=conc();

                    state._fsp--;

                    pushFollow(FOLLOW_conctail_in_conctail290);
                    tail3=conctail();

                    state._fsp--;

                    ArrayList<Term> aux=tail3; 
                                                                   aux.add(0,conc10); value =aux;
                                                                  

                    }
                    break;
                case 2 :
                    // Term.g:55:47: 
                    {
                    value =new ArrayList<Term>();

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "conctail"


    // $ANTLR start "conc"
    // Term.g:57:1: conc returns [Term value] : neg negtail ;
    public final Term conc() throws RecognitionException {
        Term value = null;

        Term neg11 = null;

        ArrayList<ParserPair> negtail12 = null;


        try {
            // Term.g:57:26: ( neg negtail )
            // Term.g:57:28: neg negtail
            {
            pushFollow(FOLLOW_neg_in_conc367);
            neg11=neg();

            state._fsp--;

            pushFollow(FOLLOW_negtail_in_conc369);
            negtail12=negtail();

            state._fsp--;

             Term aux=neg11; 
                                                                 for(Iterator<ParserPair> i = negtail12.iterator(); i.hasNext();)
                                                                 {
                                                                    ParserPair pair = i.next();
                                                                    if (pair.symbol.equals("\\vee "))
                                                                       aux=new App(new App(new Const(pair.symbol),pair.term),aux); 
                                                                    else if (pair.symbol.equals("\\wedge "))
                                                                       aux=new App(new App(new Const(pair.symbol),pair.term),aux); 
                                                                 }
                                                                 value =aux;
                                                               

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "conc"


    // $ANTLR start "negtail"
    // Term.g:69:1: negtail returns [ArrayList<ParserPair> value] : ( '\\\\/' neg tail4= negtail | '/\\\\' neg tail5= negtail | );
    public final ArrayList<ParserPair> negtail() throws RecognitionException {
        ArrayList<ParserPair> value = null;

        ArrayList<ParserPair> tail4 = null;

        ArrayList<ParserPair> tail5 = null;

        Term neg13 = null;

        Term neg14 = null;


        try {
            // Term.g:69:46: ( '\\\\/' neg tail4= negtail | '/\\\\' neg tail5= negtail | )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 15:
                {
                alt4=1;
                }
                break;
            case 16:
                {
                alt4=2;
                }
                break;
            case EOF:
            case 12:
            case 13:
            case 14:
            case 21:
            case 24:
            case 26:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Term.g:71:6: '\\\\/' neg tail4= negtail
                    {
                    match(input,15,FOLLOW_15_in_negtail400); 
                    pushFollow(FOLLOW_neg_in_negtail402);
                    neg13=neg();

                    state._fsp--;

                    pushFollow(FOLLOW_negtail_in_negtail406);
                    tail4=negtail();

                    state._fsp--;

                    ArrayList<ParserPair> aux=tail4;
                                                                   aux.add(0,new ParserPair("\\vee ",neg13)); value =aux;
                                                                  

                    }
                    break;
                case 2 :
                    // Term.g:75:6: '/\\\\' neg tail5= negtail
                    {
                    match(input,16,FOLLOW_16_in_negtail433); 
                    pushFollow(FOLLOW_neg_in_negtail435);
                    neg14=neg();

                    state._fsp--;

                    pushFollow(FOLLOW_negtail_in_negtail439);
                    tail5=negtail();

                    state._fsp--;

                    ArrayList<ParserPair> aux=tail5; 
                                                                   aux.add(0,new ParserPair("\\wedge ",neg14)); value =aux;
                                                                  

                    }
                    break;
                case 3 :
                    // Term.g:79:47: 
                    {
                    value =new ArrayList<ParserPair>();

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "negtail"


    // $ANTLR start "neg"
    // Term.g:81:1: neg returns [Term value] : ( '!' n= neg | CAPITALLETTER | LETTER | 'true' | 'false' | CAPITALLETTER '_{' eq '}^{' LETTER '}' | WORD '(' arguments ')' | '(' eq ')' );
    public final Term neg() throws RecognitionException {
        Term value = null;

        Token CAPITALLETTER15=null;
        Token LETTER16=null;
        Token LETTER17=null;
        Token CAPITALLETTER18=null;
        Token WORD20=null;
        Term n = null;

        Term eq19 = null;

        ArrayList<Var> arguments21 = null;

        Term eq22 = null;


        try {
            // Term.g:81:25: ( '!' n= neg | CAPITALLETTER | LETTER | 'true' | 'false' | CAPITALLETTER '_{' eq '}^{' LETTER '}' | WORD '(' arguments ')' | '(' eq ')' )
            int alt5=8;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // Term.g:83:7: '!' n= neg
                    {
                    match(input,17,FOLLOW_17_in_neg526); 
                    pushFollow(FOLLOW_neg_in_neg530);
                    n=neg();

                    state._fsp--;

                    value =new App(new Const("\\neg "),n);

                    }
                    break;
                case 2 :
                    // Term.g:85:8: CAPITALLETTER
                    {
                    CAPITALLETTER15=(Token)match(input,CAPITALLETTER,FOLLOW_CAPITALLETTER_in_neg572); 
                    value = new Var((new Integer((int)(CAPITALLETTER15!=null?CAPITALLETTER15.getText():null).charAt(0))).intValue());

                    }
                    break;
                case 3 :
                    // Term.g:87:8: LETTER
                    {
                    LETTER16=(Token)match(input,LETTER,FOLLOW_LETTER_in_neg609); 
                    value = new Var((new Integer((int)(LETTER16!=null?LETTER16.getText():null).charAt(0))).intValue());

                    }
                    break;
                case 4 :
                    // Term.g:89:8: 'true'
                    {
                    match(input,18,FOLLOW_18_in_neg653); 
                    value = new Const("true ");

                    }
                    break;
                case 5 :
                    // Term.g:91:8: 'false'
                    {
                    match(input,19,FOLLOW_19_in_neg697); 
                    value = new Const("false ");

                    }
                    break;
                case 6 :
                    // Term.g:93:8: CAPITALLETTER '_{' eq '}^{' LETTER '}'
                    {
                    CAPITALLETTER18=(Token)match(input,CAPITALLETTER,FOLLOW_CAPITALLETTER_in_neg740); 
                    match(input,20,FOLLOW_20_in_neg742); 
                    pushFollow(FOLLOW_eq_in_neg744);
                    eq19=eq();

                    state._fsp--;

                    match(input,21,FOLLOW_21_in_neg746); 
                    LETTER17=(Token)match(input,LETTER,FOLLOW_LETTER_in_neg748); 
                    match(input,22,FOLLOW_22_in_neg750); 
                    Var letter = new Var((new Integer((int)(LETTER17!=null?LETTER17.getText():null).charAt(0))).intValue());
                                                                   Var capl = new Var((new Integer((int)(CAPITALLETTER18!=null?CAPITALLETTER18.getText():null).charAt(0))).intValue());
                                                                   value = new App(new Bracket(letter,capl),eq19);
                                                                  

                    }
                    break;
                case 7 :
                    // Term.g:98:8: WORD '(' arguments ')'
                    {
                    WORD20=(Token)match(input,WORD,FOLLOW_WORD_in_neg763); 
                    match(input,23,FOLLOW_23_in_neg765); 
                    pushFollow(FOLLOW_arguments_in_neg767);
                    arguments21=arguments();

                    state._fsp--;

                    match(input,24,FOLLOW_24_in_neg769); 
                    Term aux = new Const((WORD20!=null?WORD20.getText():null));
                                                                   for(Iterator<Var> i = arguments21.iterator(); i.hasNext();) 
                                                                      aux=new App(aux,i.next());
                                                                   value =aux;
                                                                  

                    }
                    break;
                case 8 :
                    // Term.g:104:8: '(' eq ')'
                    {
                    match(input,23,FOLLOW_23_in_neg797); 
                    pushFollow(FOLLOW_eq_in_neg799);
                    eq22=eq();

                    state._fsp--;

                    match(input,24,FOLLOW_24_in_neg801); 
                    value =eq22;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "neg"


    // $ANTLR start "instantiate"
    // Term.g:106:1: instantiate returns [ArrayList<Object> value] : arguments ':=' explist ;
    public final ArrayList<Object> instantiate() throws RecognitionException {
        ArrayList<Object> value = null;

        ArrayList<Var> arguments23 = null;

        ArrayList<Term> explist24 = null;


        try {
            // Term.g:106:46: ( arguments ':=' explist )
            // Term.g:108:6: arguments ':=' explist
            {
            pushFollow(FOLLOW_arguments_in_instantiate849);
            arguments23=arguments();

            state._fsp--;

            match(input,25,FOLLOW_25_in_instantiate851); 
            pushFollow(FOLLOW_explist_in_instantiate853);
            explist24=explist();

            state._fsp--;

            ArrayList<Object> arr=new ArrayList<Object>();
                                                           arr.add(arguments23);
                                                           arr.add(explist24);
                                                           value = arr;
                                                          

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "instantiate"


    // $ANTLR start "explist"
    // Term.g:114:1: explist returns [ArrayList<Term> value] : eq explisttail ;
    public final ArrayList<Term> explist() throws RecognitionException {
        ArrayList<Term> value = null;

        ArrayList<Term> explisttail25 = null;

        Term eq26 = null;


        try {
            // Term.g:114:40: ( eq explisttail )
            // Term.g:116:6: eq explisttail
            {
            pushFollow(FOLLOW_eq_in_explist891);
            eq26=eq();

            state._fsp--;

            pushFollow(FOLLOW_explisttail_in_explist894);
            explisttail25=explisttail();

            state._fsp--;

            ArrayList<Term> aux = explisttail25;
                                                           aux.add(0,eq26);
                                                           value = aux;
                                                          

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "explist"


    // $ANTLR start "explisttail"
    // Term.g:121:1: explisttail returns [ArrayList<Term> value] : ( ',' eq tail6= explisttail | );
    public final ArrayList<Term> explisttail() throws RecognitionException {
        ArrayList<Term> value = null;

        ArrayList<Term> tail6 = null;

        Term eq27 = null;


        try {
            // Term.g:121:44: ( ',' eq tail6= explisttail | )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==26) ) {
                alt6=1;
            }
            else if ( (LA6_0==EOF) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // Term.g:123:6: ',' eq tail6= explisttail
                    {
                    match(input,26,FOLLOW_26_in_explisttail939); 
                    pushFollow(FOLLOW_eq_in_explisttail941);
                    eq27=eq();

                    state._fsp--;

                    pushFollow(FOLLOW_explisttail_in_explisttail945);
                    tail6=explisttail();

                    state._fsp--;

                    ArrayList<Term> aux = tail6;
                                                                   aux.add(0,eq27);
                                                                   value =aux;
                                                                  

                    }
                    break;
                case 2 :
                    // Term.g:128:47: 
                    {
                    value = new ArrayList<Term>();

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "explisttail"


    // $ANTLR start "arguments"
    // Term.g:135:1: arguments returns [ArrayList<Var> value] : ( LETTER ',' arg= arguments | CAPITALLETTER ',' arg= arguments | LETTER | CAPITALLETTER );
    public final ArrayList<Var> arguments() throws RecognitionException {
        ArrayList<Var> value = null;

        Token LETTER28=null;
        Token CAPITALLETTER29=null;
        Token LETTER30=null;
        Token CAPITALLETTER31=null;
        ArrayList<Var> arg = null;


        try {
            // Term.g:135:41: ( LETTER ',' arg= arguments | CAPITALLETTER ',' arg= arguments | LETTER | CAPITALLETTER )
            int alt7=4;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==LETTER) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==26) ) {
                    alt7=1;
                }
                else if ( ((LA7_1>=24 && LA7_1<=25)) ) {
                    alt7=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA7_0==CAPITALLETTER) ) {
                int LA7_2 = input.LA(2);

                if ( (LA7_2==26) ) {
                    alt7=2;
                }
                else if ( ((LA7_2>=24 && LA7_2<=25)) ) {
                    alt7=4;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // Term.g:135:43: LETTER ',' arg= arguments
                    {
                    LETTER28=(Token)match(input,LETTER,FOLLOW_LETTER_in_arguments1044); 
                    match(input,26,FOLLOW_26_in_arguments1046); 
                    pushFollow(FOLLOW_arguments_in_arguments1050);
                    arg=arguments();

                    state._fsp--;

                    ArrayList<Var> aux=arg; 
                                                                                Var v=new Var((new Integer((int)(LETTER28!=null?LETTER28.getText():null).charAt(0))).intValue());
                                                                                aux.add(0,v); 
                                                                                value =aux;
                                                                               

                    }
                    break;
                case 2 :
                    // Term.g:141:44: CAPITALLETTER ',' arg= arguments
                    {
                    CAPITALLETTER29=(Token)match(input,CAPITALLETTER,FOLLOW_CAPITALLETTER_in_arguments1098); 
                    match(input,26,FOLLOW_26_in_arguments1100); 
                    pushFollow(FOLLOW_arguments_in_arguments1104);
                    arg=arguments();

                    state._fsp--;

                    ArrayList<Var> aux=arg; 
                                                                         Var v=new Var((new Integer((int)(CAPITALLETTER29!=null?CAPITALLETTER29.getText():null).charAt(0))).intValue());
                                                                                aux.add(0,v); 
                                                                                value =aux;
                                                                               

                    }
                    break;
                case 3 :
                    // Term.g:147:44: LETTER
                    {
                    LETTER30=(Token)match(input,LETTER,FOLLOW_LETTER_in_arguments1152); 
                    ArrayList<Var> list=new ArrayList<Var>();
                                                                                Var v=new Var((new Integer((LETTER30!=null?LETTER30.getText():null).charAt(0))).intValue());
                                                                                list.add(0,v);
                                                                                value = list;
                                                                               

                    }
                    break;
                case 4 :
                    // Term.g:153:44: CAPITALLETTER
                    {
                    CAPITALLETTER31=(Token)match(input,CAPITALLETTER,FOLLOW_CAPITALLETTER_in_arguments1209); 
                    ArrayList<Var> list=new ArrayList<Var>();
                                                                           Var v=new Var((new Integer((CAPITALLETTER31!=null?CAPITALLETTER31.getText():null).charAt(0))).intValue());
                                                                                 list.add(0,v);
                                                                                 value = list;
                                                                               

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "arguments"


    // $ANTLR start "lambda"
    // Term.g:159:1: lambda returns [Term value] : 'lambda' LETTER '.' eq ;
    public final Term lambda() throws RecognitionException {
        Term value = null;

        Token LETTER32=null;
        Term eq33 = null;


        try {
            // Term.g:159:28: ( 'lambda' LETTER '.' eq )
            // Term.g:159:30: 'lambda' LETTER '.' eq
            {
            match(input,27,FOLLOW_27_in_lambda1224); 
            LETTER32=(Token)match(input,LETTER,FOLLOW_LETTER_in_lambda1226); 
            match(input,28,FOLLOW_28_in_lambda1228); 
            pushFollow(FOLLOW_eq_in_lambda1230);
            eq33=eq();

            state._fsp--;

            Var v=new Var((new Integer((LETTER32!=null?LETTER32.getText():null).charAt(0))).intValue());
                                                                        value = new Bracket(v,eq33);
                                                                       

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "lambda"

    // Delegated rules


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\12\uffff";
    static final String DFA5_eofS =
        "\2\uffff\1\11\7\uffff";
    static final String DFA5_minS =
        "\1\4\1\uffff\1\14\7\uffff";
    static final String DFA5_maxS =
        "\1\27\1\uffff\1\32\7\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\4\1\5\1\7\1\10\1\6\1\2";
    static final String DFA5_specialS =
        "\12\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\2\1\3\1\6\12\uffff\1\1\1\4\1\5\3\uffff\1\7",
            "",
            "\5\11\3\uffff\1\10\1\11\2\uffff\1\11\1\uffff\1\11",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "81:1: neg returns [Term value] : ( '!' n= neg | CAPITALLETTER | LETTER | 'true' | 'false' | CAPITALLETTER '_{' eq '}^{' LETTER '}' | WORD '(' arguments ')' | '(' eq ')' );";
        }
    }
 

    public static final BitSet FOLLOW_eq_in_start_rule21 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_eq44 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_eqtail_in_eq46 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_eqtail76 = new BitSet(new long[]{0x00000000008E0070L});
    public static final BitSet FOLLOW_term_in_eqtail78 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_eqtail_in_eqtail82 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_disyconj_in_term163 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_disyconjtail_in_term165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_disyconjtail187 = new BitSet(new long[]{0x00000000008E0070L});
    public static final BitSet FOLLOW_disyconj_in_disyconjtail189 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_disyconjtail_in_disyconjtail193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conc_in_disyconj262 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_conctail_in_disyconj264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_conctail284 = new BitSet(new long[]{0x00000000008E0070L});
    public static final BitSet FOLLOW_conc_in_conctail286 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_conctail_in_conctail290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_neg_in_conc367 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_negtail_in_conc369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_negtail400 = new BitSet(new long[]{0x00000000008E0070L});
    public static final BitSet FOLLOW_neg_in_negtail402 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_negtail_in_negtail406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_negtail433 = new BitSet(new long[]{0x00000000008E0070L});
    public static final BitSet FOLLOW_neg_in_negtail435 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_negtail_in_negtail439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_neg526 = new BitSet(new long[]{0x00000000008E0070L});
    public static final BitSet FOLLOW_neg_in_neg530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAPITALLETTER_in_neg572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LETTER_in_neg609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_neg653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_neg697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAPITALLETTER_in_neg740 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_neg742 = new BitSet(new long[]{0x00000000008E0070L});
    public static final BitSet FOLLOW_eq_in_neg744 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_neg746 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LETTER_in_neg748 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_neg750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORD_in_neg763 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_neg765 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_arguments_in_neg767 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_neg769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_neg797 = new BitSet(new long[]{0x00000000008E0070L});
    public static final BitSet FOLLOW_eq_in_neg799 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_neg801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arguments_in_instantiate849 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_instantiate851 = new BitSet(new long[]{0x00000000008E0070L});
    public static final BitSet FOLLOW_explist_in_instantiate853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_eq_in_explist891 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_explisttail_in_explist894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_explisttail939 = new BitSet(new long[]{0x00000000008E0070L});
    public static final BitSet FOLLOW_eq_in_explisttail941 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_explisttail_in_explisttail945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LETTER_in_arguments1044 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_arguments1046 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_arguments_in_arguments1050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAPITALLETTER_in_arguments1098 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_arguments1100 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_arguments_in_arguments1104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LETTER_in_arguments1152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAPITALLETTER_in_arguments1209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_lambda1224 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LETTER_in_lambda1226 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_lambda1228 = new BitSet(new long[]{0x00000000008E0070L});
    public static final BitSet FOLLOW_eq_in_lambda1230 = new BitSet(new long[]{0x0000000000000002L});

}