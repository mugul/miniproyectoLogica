// $ANTLR 3.2 Sep 23, 2009 12:02:23 Term.g 2017-03-03 11:00:35
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "X", "WORD", "NUMBER", "INITIALDIGIT", "DIGIT", "CAPITALLETTER", "LETTER", "WHITESPACE", "'==='", "'==>'", "'<=='", "'\\\\/'", "'/\\\\'", "'!'", "'('", "')'", "','"
    };
    public static final int LETTER=10;
    public static final int T__20=20;
    public static final int NUMBER=6;
    public static final int CAPITALLETTER=9;
    public static final int INITIALDIGIT=7;
    public static final int WHITESPACE=11;
    public static final int EOF=-1;
    public static final int X=4;
    public static final int WORD=5;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int DIGIT=8;

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
    // Term.g:13:1: start_rule[TerminoId terminoid, TerminoManager terminoManager] returns [Term value] : eq ;
    public final Term start_rule(TerminoId terminoid, TerminoManager terminoManager) throws RecognitionException {
        Term value = null;

        Term eq1 = null;


        try {
            // Term.g:13:84: ( eq )
            // Term.g:13:86: eq
            {
            pushFollow(FOLLOW_eq_in_start_rule22);
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
    // Term.g:15:1: eq returns [Term value] : term eqtail ;
    public final Term eq() throws RecognitionException {
        Term value = null;

        Term term2 = null;

        ArrayList<Term> eqtail3 = null;


        try {
            // Term.g:15:24: ( term eqtail )
            // Term.g:15:26: term eqtail
            {
            pushFollow(FOLLOW_term_in_eq45);
            term2=term();

            state._fsp--;

            pushFollow(FOLLOW_eqtail_in_eq47);
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
    // Term.g:21:1: eqtail returns [ArrayList<Term> value] : ( '===' term tail1= eqtail | );
    public final ArrayList<Term> eqtail() throws RecognitionException {
        ArrayList<Term> value = null;

        ArrayList<Term> tail1 = null;

        Term term4 = null;


        try {
            // Term.g:21:39: ( '===' term tail1= eqtail | )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==12) ) {
                alt1=1;
            }
            else if ( (LA1_0==EOF||LA1_0==19) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // Term.g:22:5: '===' term tail1= eqtail
                    {
                    match(input,12,FOLLOW_12_in_eqtail77); 
                    pushFollow(FOLLOW_term_in_eqtail79);
                    term4=term();

                    state._fsp--;

                    pushFollow(FOLLOW_eqtail_in_eqtail83);
                    tail1=eqtail();

                    state._fsp--;

                    ArrayList<Term> aux=tail1; aux.add(0,term4); value =aux;

                    }
                    break;
                case 2 :
                    // Term.g:24:47: 
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
    // Term.g:26:1: term returns [Term value] : disyconj disyconjtail ;
    public final Term term() throws RecognitionException {
        Term value = null;

        Term disyconj5 = null;

        ArrayList<ParserPair> disyconjtail6 = null;


        try {
            // Term.g:26:26: ( disyconj disyconjtail )
            // Term.g:26:28: disyconj disyconjtail
            {
            pushFollow(FOLLOW_disyconj_in_term164);
            disyconj5=disyconj();

            state._fsp--;

            pushFollow(FOLLOW_disyconjtail_in_term166);
            disyconjtail6=disyconjtail();

            state._fsp--;

             Term aux=disyconj5; 
                                                                ArrayList<ParserPair> rightList = new ArrayList<ParserPair>();
                                                                Term aux2=null;
                                                                boolean left, right;
                                                                left = false;
                                                                right = false;
                                                                for(Iterator<ParserPair> i = disyconjtail6.iterator(); i.hasNext();)
                                                                {
                                                                   ParserPair pair = i.next();
                                                                   if (pair.symbol.equals("\\Leftarrow "))
                                                                   {
                                                                      if (right)
                                                                      {
                                                                        if (rightList.size() > 0) aux2 = rightList.remove(0).term;
                                                                        for(Iterator<ParserPair> j = rightList.iterator(); j.hasNext();)
                                                                        {
                                                                          ParserPair pair2 = j.next();
                                                                          aux2=new App(new App(new Const(pair2.symbol),aux2),pair2.term);
                                                                          if (!j.hasNext())
                                                                             aux = new App(new App(new Const(pair2.symbol),aux2),aux);
                                                                        }
                                                                        rightList = new ArrayList<ParserPair>();
                                                                      }
                                                                      left = true;
                                                                      right = false;
                                                                      aux=new App(new App(new Const(pair.symbol),pair.term),aux); 
                                                                   }
                                                                   else if (pair.symbol.equals("\\Rightarrow "))
                                                                   {
                                                                      if (left)
                                                                         rightList = new ArrayList<ParserPair>();
                                                                      left = false;
                                                                      right = true;
                                                                      rightList.add(0,pair);
                                                                   }
                                                                }
                                                                if (rightList.size() > 0) aux2 = rightList.remove(0).term;
                                                                for(Iterator<ParserPair> j = rightList.iterator(); j.hasNext();)
                                                                {
                                                                   ParserPair pair2 = j.next();
                                                                   aux2=new App(new App(new Const(pair2.symbol),aux2),pair2.term);
                                                                   if (!j.hasNext())
                                                                      aux = new App(new App(new Const(pair2.symbol),aux2),aux);
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
    // $ANTLR end "term"


    // $ANTLR start "disyconjtail"
    // Term.g:73:1: disyconjtail returns [ArrayList<ParserPair> value] : ( '==>' disyconj tail2= disyconjtail | '<==' disyconj tail3= disyconjtail | );
    public final ArrayList<ParserPair> disyconjtail() throws RecognitionException {
        ArrayList<ParserPair> value = null;

        ArrayList<ParserPair> tail2 = null;

        ArrayList<ParserPair> tail3 = null;

        Term disyconj7 = null;

        Term disyconj8 = null;


        try {
            // Term.g:73:51: ( '==>' disyconj tail2= disyconjtail | '<==' disyconj tail3= disyconjtail | )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt2=1;
                }
                break;
            case 14:
                {
                alt2=2;
                }
                break;
            case EOF:
            case 12:
            case 19:
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Term.g:75:6: '==>' disyconj tail2= disyconjtail
                    {
                    match(input,13,FOLLOW_13_in_disyconjtail188); 
                    pushFollow(FOLLOW_disyconj_in_disyconjtail190);
                    disyconj7=disyconj();

                    state._fsp--;

                    pushFollow(FOLLOW_disyconjtail_in_disyconjtail194);
                    tail2=disyconjtail();

                    state._fsp--;

                    ArrayList<ParserPair> aux=tail2; 
                                                                   aux.add(0,new ParserPair("\\Rightarrow ",disyconj7)); value =aux;
                                                                  

                    }
                    break;
                case 2 :
                    // Term.g:79:6: '<==' disyconj tail3= disyconjtail
                    {
                    match(input,14,FOLLOW_14_in_disyconjtail211); 
                    pushFollow(FOLLOW_disyconj_in_disyconjtail213);
                    disyconj8=disyconj();

                    state._fsp--;

                    pushFollow(FOLLOW_disyconjtail_in_disyconjtail217);
                    tail3=disyconjtail();

                    state._fsp--;

                    ArrayList<ParserPair> aux=tail3; 
                                                                   aux.add(0,new ParserPair("\\Leftarrow ", disyconj8)); value =aux;
                                                                  

                    }
                    break;
                case 3 :
                    // Term.g:83:47: 
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
    // $ANTLR end "disyconjtail"


    // $ANTLR start "disyconj"
    // Term.g:85:1: disyconj returns [Term value] : neg negtail ;
    public final Term disyconj() throws RecognitionException {
        Term value = null;

        Term neg9 = null;

        ArrayList<ParserPair> negtail10 = null;


        try {
            // Term.g:85:30: ( neg negtail )
            // Term.g:85:32: neg negtail
            {
            pushFollow(FOLLOW_neg_in_disyconj286);
            neg9=neg();

            state._fsp--;

            pushFollow(FOLLOW_negtail_in_disyconj288);
            negtail10=negtail();

            state._fsp--;

             Term aux=neg9; 
                                                                 for(Iterator<ParserPair> i = negtail10.iterator(); i.hasNext();)
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
    // $ANTLR end "disyconj"


    // $ANTLR start "negtail"
    // Term.g:97:1: negtail returns [ArrayList<ParserPair> value] : ( '\\\\/' neg tail4= negtail | '/\\\\' neg tail5= negtail | );
    public final ArrayList<ParserPair> negtail() throws RecognitionException {
        ArrayList<ParserPair> value = null;

        ArrayList<ParserPair> tail4 = null;

        ArrayList<ParserPair> tail5 = null;

        Term neg11 = null;

        Term neg12 = null;


        try {
            // Term.g:97:46: ( '\\\\/' neg tail4= negtail | '/\\\\' neg tail5= negtail | )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 15:
                {
                alt3=1;
                }
                break;
            case 16:
                {
                alt3=2;
                }
                break;
            case EOF:
            case 12:
            case 13:
            case 14:
            case 19:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // Term.g:99:6: '\\\\/' neg tail4= negtail
                    {
                    match(input,15,FOLLOW_15_in_negtail315); 
                    pushFollow(FOLLOW_neg_in_negtail317);
                    neg11=neg();

                    state._fsp--;

                    pushFollow(FOLLOW_negtail_in_negtail321);
                    tail4=negtail();

                    state._fsp--;

                    ArrayList<ParserPair> aux=tail4;
                                                                   aux.add(0,new ParserPair("\\vee ",neg11)); value =aux;
                                                                  

                    }
                    break;
                case 2 :
                    // Term.g:103:6: '/\\\\' neg tail5= negtail
                    {
                    match(input,16,FOLLOW_16_in_negtail348); 
                    pushFollow(FOLLOW_neg_in_negtail350);
                    neg12=neg();

                    state._fsp--;

                    pushFollow(FOLLOW_negtail_in_negtail354);
                    tail5=negtail();

                    state._fsp--;

                    ArrayList<ParserPair> aux=tail5; 
                                                                   aux.add(0,new ParserPair("\\wedge ",neg12)); value =aux;
                                                                  

                    }
                    break;
                case 3 :
                    // Term.g:107:47: 
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
    // Term.g:109:1: neg returns [Term value] : ( '!' n= neg | '(' eq ')' | X | WORD '(' arguments ')' );
    public final Term neg() throws RecognitionException {
        Term value = null;

        Token X14=null;
        Token WORD15=null;
        Term n = null;

        Term eq13 = null;

        ArrayList<Term> arguments16 = null;


        try {
            // Term.g:109:25: ( '!' n= neg | '(' eq ')' | X | WORD '(' arguments ')' )
            int alt4=4;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt4=1;
                }
                break;
            case 18:
                {
                alt4=2;
                }
                break;
            case X:
                {
                alt4=3;
                }
                break;
            case WORD:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Term.g:111:7: '!' n= neg
                    {
                    match(input,17,FOLLOW_17_in_neg441); 
                    pushFollow(FOLLOW_neg_in_neg445);
                    n=neg();

                    state._fsp--;

                    value =new App(new Const("\\neg "),n);

                    }
                    break;
                case 2 :
                    // Term.g:113:8: '(' eq ')'
                    {
                    match(input,18,FOLLOW_18_in_neg487); 
                    pushFollow(FOLLOW_eq_in_neg489);
                    eq13=eq();

                    state._fsp--;

                    match(input,19,FOLLOW_19_in_neg491); 
                    value =eq13;

                    }
                    break;
                case 3 :
                    // Term.g:115:8: X
                    {
                    X14=(Token)match(input,X,FOLLOW_X_in_neg531); 
                    value =new Var((new Integer((X14!=null?X14.getText():null).substring(1))).intValue());

                    }
                    break;
                case 4 :
                    // Term.g:117:8: WORD '(' arguments ')'
                    {
                    WORD15=(Token)match(input,WORD,FOLLOW_WORD_in_neg580); 
                    match(input,18,FOLLOW_18_in_neg582); 
                    pushFollow(FOLLOW_arguments_in_neg584);
                    arguments16=arguments();

                    state._fsp--;

                    match(input,19,FOLLOW_19_in_neg586); 
                    Term aux = new Const((WORD15!=null?WORD15.getText():null)+" ");
                                                                   for(Iterator<Term> i = arguments16.iterator(); i.hasNext();) 
                                                                      aux=new App(aux,i.next());
                                                                   value =aux;
                                                                  

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


    // $ANTLR start "arguments"
    // Term.g:128:1: arguments returns [ArrayList<Term> value] : ( X ',' arg= arguments | X );
    public final ArrayList<Term> arguments() throws RecognitionException {
        ArrayList<Term> value = null;

        Token X17=null;
        Token X18=null;
        ArrayList<Term> arg = null;


        try {
            // Term.g:128:42: ( X ',' arg= arguments | X )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==X) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==20) ) {
                    alt5=1;
                }
                else if ( (LA5_1==19) ) {
                    alt5=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // Term.g:128:44: X ',' arg= arguments
                    {
                    X17=(Token)match(input,X,FOLLOW_X_in_arguments636); 
                    match(input,20,FOLLOW_20_in_arguments638); 
                    pushFollow(FOLLOW_arguments_in_arguments642);
                    arg=arguments();

                    state._fsp--;

                    ArrayList<Term> aux=arg; 
                                                                                 aux.add(0,new Var((new Integer((X17!=null?X17.getText():null).substring(1))).intValue())); 
                                                                                 value =aux;
                                                                                

                    }
                    break;
                case 2 :
                    // Term.g:133:44: X
                    {
                    X18=(Token)match(input,X,FOLLOW_X_in_arguments690); 
                    ArrayList<Term> list=new ArrayList<Term>();
                                                                                 list.add(0,new Var((new Integer((X18!=null?X18.getText():null).substring(1))).intValue()));
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

    // Delegated rules


 

    public static final BitSet FOLLOW_eq_in_start_rule22 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_eq45 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_eqtail_in_eq47 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_eqtail77 = new BitSet(new long[]{0x0000000000060030L});
    public static final BitSet FOLLOW_term_in_eqtail79 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_eqtail_in_eqtail83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_disyconj_in_term164 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_disyconjtail_in_term166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_disyconjtail188 = new BitSet(new long[]{0x0000000000060030L});
    public static final BitSet FOLLOW_disyconj_in_disyconjtail190 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_disyconjtail_in_disyconjtail194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_disyconjtail211 = new BitSet(new long[]{0x0000000000060030L});
    public static final BitSet FOLLOW_disyconj_in_disyconjtail213 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_disyconjtail_in_disyconjtail217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_neg_in_disyconj286 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_negtail_in_disyconj288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_negtail315 = new BitSet(new long[]{0x0000000000060030L});
    public static final BitSet FOLLOW_neg_in_negtail317 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_negtail_in_negtail321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_negtail348 = new BitSet(new long[]{0x0000000000060030L});
    public static final BitSet FOLLOW_neg_in_negtail350 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_negtail_in_negtail354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_neg441 = new BitSet(new long[]{0x0000000000060030L});
    public static final BitSet FOLLOW_neg_in_neg445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_neg487 = new BitSet(new long[]{0x0000000000060030L});
    public static final BitSet FOLLOW_eq_in_neg489 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_neg491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_X_in_neg531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORD_in_neg580 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_neg582 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_arguments_in_neg584 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_neg586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_X_in_arguments636 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_arguments638 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_arguments_in_arguments642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_X_in_arguments690 = new BitSet(new long[]{0x0000000000000002L});

}