// $ANTLR 3.0.1 Sql.g 2008-04-01 11:01:05

package dbs.search.parser;

import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SPACE", "NL", "COMMA", "DOT", "EQ", "LT", "GT", "VALUE", "STAR", "WS", "'('", "')'", "'WHERE'", "'where'", "'ads'", "'path'", "'release'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'ls'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numlss'", "'size'", "'numruns()'", "'numfiles()'", "'dataquality()'", "'latest()'", "'parentrelease()'", "'childrelease()'", "'intluminosity()'", "'findevents()'", "'select'", "'SELECT'", "'and'", "'AND'", "'or'", "'OR'", "'in'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'"
    };
    public static final int LT=9;
    public static final int WS=13;
    public static final int STAR=12;
    public static final int COMMA=6;
    public static final int GT=10;
    public static final int NL=5;
    public static final int VALUE=11;
    public static final int EQ=8;
    public static final int DOT=7;
    public static final int EOF=-1;
    public static final int SPACE=4;

        public SqlParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "Sql.g"; }


    ArrayList kws = new ArrayList();
    ArrayList constraints = new ArrayList();



    // $ANTLR start stmt
    // Sql.g:14:1: stmt : select SPACE selectList SPACE where SPACE constraintList NL ;
    public final void stmt() throws RecognitionException {
        try {
            // Sql.g:14:6: ( select SPACE selectList SPACE where SPACE constraintList NL )
            // Sql.g:14:8: select SPACE selectList SPACE where SPACE constraintList NL
            {
            pushFollow(FOLLOW_select_in_stmt22);
            select();
            _fsp--;

            match(input,SPACE,FOLLOW_SPACE_in_stmt24); 
            pushFollow(FOLLOW_selectList_in_stmt27);
            selectList();
            _fsp--;

            match(input,SPACE,FOLLOW_SPACE_in_stmt31); 
            pushFollow(FOLLOW_where_in_stmt33);
            where();
            _fsp--;

            match(input,SPACE,FOLLOW_SPACE_in_stmt36); 
            pushFollow(FOLLOW_constraintList_in_stmt38);
            constraintList();
            _fsp--;

            match(input,NL,FOLLOW_NL_in_stmt41); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end stmt


    // $ANTLR start selectList
    // Sql.g:16:1: selectList : kw= keyword ( COMMA kw= keyword )* ;
    public final void selectList() throws RecognitionException {
        keyword_return kw = null;


        try {
            // Sql.g:16:12: (kw= keyword ( COMMA kw= keyword )* )
            // Sql.g:16:13: kw= keyword ( COMMA kw= keyword )*
            {
            pushFollow(FOLLOW_keyword_in_selectList54);
            kw=keyword();
            _fsp--;

            kws.add(input.toString(kw.start,kw.stop));
            // Sql.g:17:4: ( COMMA kw= keyword )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==COMMA) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Sql.g:17:5: COMMA kw= keyword
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectList64); 
            	    pushFollow(FOLLOW_keyword_in_selectList72);
            	    kw=keyword();
            	    _fsp--;

            	    kws.add(input.toString(kw.start,kw.stop));

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end selectList

    public static class keyword_return extends ParserRuleReturnScope {
    };

    // $ANTLR start keyword
    // Sql.g:21:1: keyword : ( entity | entity DOT attr | entity DOT funct );
    public final keyword_return keyword() throws RecognitionException {
        keyword_return retval = new keyword_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:21:9: ( entity | entity DOT attr | entity DOT funct )
            int alt2=3;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>=18 && LA2_0<=27)) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==DOT) ) {
                    int LA2_2 = input.LA(3);

                    if ( ((LA2_2>=42 && LA2_2<=49)) ) {
                        alt2=3;
                    }
                    else if ( (LA2_2==19||(LA2_2>=28 && LA2_2<=41)) ) {
                        alt2=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("21:1: keyword : ( entity | entity DOT attr | entity DOT funct );", 2, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA2_1==SPACE||LA2_1==COMMA||(LA2_1>=EQ && LA2_1<=GT)) ) {
                    alt2=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("21:1: keyword : ( entity | entity DOT attr | entity DOT funct );", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("21:1: keyword : ( entity | entity DOT attr | entity DOT funct );", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // Sql.g:21:11: entity
                    {
                    pushFollow(FOLLOW_entity_in_keyword97);
                    entity();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:22:4: entity DOT attr
                    {
                    pushFollow(FOLLOW_entity_in_keyword103);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword105); 
                    pushFollow(FOLLOW_attr_in_keyword107);
                    attr();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Sql.g:23:4: entity DOT funct
                    {
                    pushFollow(FOLLOW_entity_in_keyword112);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword114); 
                    pushFollow(FOLLOW_funct_in_keyword116);
                    funct();
                    _fsp--;


                    }
                    break;

            }
            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end keyword


    // $ANTLR start constraintList
    // Sql.g:25:1: constraintList : constraint ( SPACE rel= logicalOp SPACE constraint )* ;
    public final void constraintList() throws RecognitionException {
        logicalOp_return rel = null;


        try {
            // Sql.g:25:16: ( constraint ( SPACE rel= logicalOp SPACE constraint )* )
            // Sql.g:25:18: constraint ( SPACE rel= logicalOp SPACE constraint )*
            {
            pushFollow(FOLLOW_constraint_in_constraintList125);
            constraint();
            _fsp--;

            // Sql.g:25:29: ( SPACE rel= logicalOp SPACE constraint )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==SPACE) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Sql.g:25:31: SPACE rel= logicalOp SPACE constraint
            	    {
            	    match(input,SPACE,FOLLOW_SPACE_in_constraintList129); 
            	    pushFollow(FOLLOW_logicalOp_in_constraintList136);
            	    rel=logicalOp();
            	    _fsp--;

            	    if(rel== null) System.out.println("REL is NULL"); constraints.add(input.toString(rel.start,rel.stop));
            	    match(input,SPACE,FOLLOW_SPACE_in_constraintList144); 
            	    pushFollow(FOLLOW_constraint_in_constraintList146);
            	    constraint();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end constraintList


    // $ANTLR start constraint
    // Sql.g:29:1: constraint : (kw= keyword op= ( EQ | LT | GT ) val= VALUE | kw= keyword SPACE op1= in SPACE '(' val1= valueList ')' | kw= keyword SPACE op2= like SPACE val2= likeValue );
    public final void constraint() throws RecognitionException {
        Token op=null;
        Token val=null;
        keyword_return kw = null;

        in_return op1 = null;

        valueList_return val1 = null;

        like_return op2 = null;

        likeValue_return val2 = null;


        try {
            // Sql.g:29:12: (kw= keyword op= ( EQ | LT | GT ) val= VALUE | kw= keyword SPACE op1= in SPACE '(' val1= valueList ')' | kw= keyword SPACE op2= like SPACE val2= likeValue )
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=18 && LA4_0<=27)) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA4_2 = input.LA(3);

                    if ( ((LA4_2>=42 && LA4_2<=49)) ) {
                        int LA4_5 = input.LA(4);

                        if ( ((LA4_5>=EQ && LA4_5<=GT)) ) {
                            alt4=1;
                        }
                        else if ( (LA4_5==SPACE) ) {
                            int LA4_3 = input.LA(5);

                            if ( ((LA4_3>=56 && LA4_3<=57)) ) {
                                alt4=2;
                            }
                            else if ( ((LA4_3>=60 && LA4_3<=61)) ) {
                                alt4=3;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("29:1: constraint : (kw= keyword op= ( EQ | LT | GT ) val= VALUE | kw= keyword SPACE op1= in SPACE '(' val1= valueList ')' | kw= keyword SPACE op2= like SPACE val2= likeValue );", 4, 3, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("29:1: constraint : (kw= keyword op= ( EQ | LT | GT ) val= VALUE | kw= keyword SPACE op1= in SPACE '(' val1= valueList ')' | kw= keyword SPACE op2= like SPACE val2= likeValue );", 4, 5, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA4_2==19||(LA4_2>=28 && LA4_2<=41)) ) {
                        int LA4_6 = input.LA(4);

                        if ( ((LA4_6>=EQ && LA4_6<=GT)) ) {
                            alt4=1;
                        }
                        else if ( (LA4_6==SPACE) ) {
                            int LA4_3 = input.LA(5);

                            if ( ((LA4_3>=56 && LA4_3<=57)) ) {
                                alt4=2;
                            }
                            else if ( ((LA4_3>=60 && LA4_3<=61)) ) {
                                alt4=3;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("29:1: constraint : (kw= keyword op= ( EQ | LT | GT ) val= VALUE | kw= keyword SPACE op1= in SPACE '(' val1= valueList ')' | kw= keyword SPACE op2= like SPACE val2= likeValue );", 4, 3, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("29:1: constraint : (kw= keyword op= ( EQ | LT | GT ) val= VALUE | kw= keyword SPACE op1= in SPACE '(' val1= valueList ')' | kw= keyword SPACE op2= like SPACE val2= likeValue );", 4, 6, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("29:1: constraint : (kw= keyword op= ( EQ | LT | GT ) val= VALUE | kw= keyword SPACE op1= in SPACE '(' val1= valueList ')' | kw= keyword SPACE op2= like SPACE val2= likeValue );", 4, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case SPACE:
                    {
                    int LA4_3 = input.LA(3);

                    if ( ((LA4_3>=56 && LA4_3<=57)) ) {
                        alt4=2;
                    }
                    else if ( ((LA4_3>=60 && LA4_3<=61)) ) {
                        alt4=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("29:1: constraint : (kw= keyword op= ( EQ | LT | GT ) val= VALUE | kw= keyword SPACE op1= in SPACE '(' val1= valueList ')' | kw= keyword SPACE op2= like SPACE val2= likeValue );", 4, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case EQ:
                case LT:
                case GT:
                    {
                    alt4=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("29:1: constraint : (kw= keyword op= ( EQ | LT | GT ) val= VALUE | kw= keyword SPACE op1= in SPACE '(' val1= valueList ')' | kw= keyword SPACE op2= like SPACE val2= likeValue );", 4, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("29:1: constraint : (kw= keyword op= ( EQ | LT | GT ) val= VALUE | kw= keyword SPACE op1= in SPACE '(' val1= valueList ')' | kw= keyword SPACE op2= like SPACE val2= likeValue );", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // Sql.g:29:14: kw= keyword op= ( EQ | LT | GT ) val= VALUE
                    {
                    pushFollow(FOLLOW_keyword_in_constraint159);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    op=(Token)input.LT(1);
                    if ( (input.LA(1)>=EQ && input.LA(1)<=GT) ) {
                        input.consume();
                        errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse =
                            new MismatchedSetException(null,input);
                        recoverFromMismatchedSet(input,mse,FOLLOW_set_in_constraint171);    throw mse;
                    }

                    c.setOp(op.getText());
                    val=(Token)input.LT(1);
                    match(input,VALUE,FOLLOW_VALUE_in_constraint194); 
                    c.setValue(val.getText()); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:33:2: kw= keyword SPACE op1= in SPACE '(' val1= valueList ')'
                    {
                    pushFollow(FOLLOW_keyword_in_constraint224);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    match(input,SPACE,FOLLOW_SPACE_in_constraint233); 
                    pushFollow(FOLLOW_in_in_constraint240);
                    op1=in();
                    _fsp--;

                    c.setOp(input.toString(op1.start,op1.stop));
                    match(input,SPACE,FOLLOW_SPACE_in_constraint251); 
                    match(input,14,FOLLOW_14_in_constraint253); 
                    pushFollow(FOLLOW_valueList_in_constraint260);
                    val1=valueList();
                    _fsp--;

                    c.setValue(input.toString(val1.start,val1.stop)); constraints.add(c);
                    match(input,15,FOLLOW_15_in_constraint268); 

                    }
                    break;
                case 3 :
                    // Sql.g:40:2: kw= keyword SPACE op2= like SPACE val2= likeValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint294);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    match(input,SPACE,FOLLOW_SPACE_in_constraint303); 
                    pushFollow(FOLLOW_like_in_constraint310);
                    op2=like();
                    _fsp--;

                    c.setOp(input.toString(op2.start,op2.stop));
                    match(input,SPACE,FOLLOW_SPACE_in_constraint319); 
                    pushFollow(FOLLOW_likeValue_in_constraint326);
                    val2=likeValue();
                    _fsp--;

                    c.setValue(input.toString(val2.start,val2.stop)); constraints.add(c);

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
        return ;
    }
    // $ANTLR end constraint


    // $ANTLR start where
    // Sql.g:46:1: where : ( 'WHERE' | 'where' ) ;
    public final void where() throws RecognitionException {
        try {
            // Sql.g:46:7: ( ( 'WHERE' | 'where' ) )
            // Sql.g:46:8: ( 'WHERE' | 'where' )
            {
            if ( (input.LA(1)>=16 && input.LA(1)<=17) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_where355);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end where

    public static class valueList_return extends ParserRuleReturnScope {
    };

    // $ANTLR start valueList
    // Sql.g:47:1: valueList : VALUE ( COMMA VALUE )* ;
    public final valueList_return valueList() throws RecognitionException {
        valueList_return retval = new valueList_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:47:11: ( VALUE ( COMMA VALUE )* )
            // Sql.g:47:12: VALUE ( COMMA VALUE )*
            {
            match(input,VALUE,FOLLOW_VALUE_in_valueList367); 
            // Sql.g:47:18: ( COMMA VALUE )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==COMMA) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Sql.g:47:19: COMMA VALUE
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_valueList370); 
            	    match(input,VALUE,FOLLOW_VALUE_in_valueList372); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end valueList

    public static class likeValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start likeValue
    // Sql.g:48:1: likeValue : ( VALUE | STAR )+ ;
    public final likeValue_return likeValue() throws RecognitionException {
        likeValue_return retval = new likeValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:48:12: ( ( VALUE | STAR )+ )
            // Sql.g:48:13: ( VALUE | STAR )+
            {
            // Sql.g:48:13: ( VALUE | STAR )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>=VALUE && LA6_0<=STAR)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // Sql.g:
            	    {
            	    if ( (input.LA(1)>=VALUE && input.LA(1)<=STAR) ) {
            	        input.consume();
            	        errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recoverFromMismatchedSet(input,mse,FOLLOW_set_in_likeValue381);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end likeValue

    public static class logicalOp_return extends ParserRuleReturnScope {
    };

    // $ANTLR start logicalOp
    // Sql.g:49:1: logicalOp : ( and | or ) ;
    public final logicalOp_return logicalOp() throws RecognitionException {
        logicalOp_return retval = new logicalOp_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:49:11: ( ( and | or ) )
            // Sql.g:49:12: ( and | or )
            {
            // Sql.g:49:12: ( and | or )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=52 && LA7_0<=53)) ) {
                alt7=1;
            }
            else if ( ((LA7_0>=54 && LA7_0<=55)) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("49:12: ( and | or )", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // Sql.g:49:13: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp394);
                    and();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:49:17: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp396);
                    or();
                    _fsp--;


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end logicalOp


    // $ANTLR start entity
    // Sql.g:50:1: entity : ( 'ads' | 'path' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'ls' ) ;
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:50:8: ( ( 'ads' | 'path' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'ls' ) )
            // Sql.g:50:10: ( 'ads' | 'path' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'ls' )
            {
            if ( (input.LA(1)>=18 && input.LA(1)<=27) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_entity404);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entity


    // $ANTLR start attr
    // Sql.g:51:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'path' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' ) ;
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:51:6: ( ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'path' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' ) )
            // Sql.g:51:7: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'path' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' )
            {
            if ( input.LA(1)==19||(input.LA(1)>=28 && input.LA(1)<=41) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_attr448);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end attr


    // $ANTLR start funct
    // Sql.g:52:1: funct : ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) ;
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:52:7: ( ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) )
            // Sql.g:52:8: ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' )
            {
            if ( (input.LA(1)>=42 && input.LA(1)<=49) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_funct566);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end funct


    // $ANTLR start select
    // Sql.g:53:1: select : ( 'select' | 'SELECT' ) ;
    public final void select() throws RecognitionException {
        try {
            // Sql.g:53:8: ( ( 'select' | 'SELECT' ) )
            // Sql.g:53:9: ( 'select' | 'SELECT' )
            {
            if ( (input.LA(1)>=50 && input.LA(1)<=51) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_select603);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end select


    // $ANTLR start and
    // Sql.g:54:1: and : ( 'and' | 'AND' ) ;
    public final void and() throws RecognitionException {
        try {
            // Sql.g:54:5: ( ( 'and' | 'AND' ) )
            // Sql.g:54:6: ( 'and' | 'AND' )
            {
            if ( (input.LA(1)>=52 && input.LA(1)<=53) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_and615);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end and


    // $ANTLR start or
    // Sql.g:55:1: or : ( 'or' | 'OR' ) ;
    public final void or() throws RecognitionException {
        try {
            // Sql.g:55:4: ( ( 'or' | 'OR' ) )
            // Sql.g:55:5: ( 'or' | 'OR' )
            {
            if ( (input.LA(1)>=54 && input.LA(1)<=55) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_or627);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end or

    public static class in_return extends ParserRuleReturnScope {
    };

    // $ANTLR start in
    // Sql.g:56:1: in : ( 'in' | 'IN' ) ;
    public final in_return in() throws RecognitionException {
        in_return retval = new in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:56:4: ( ( 'in' | 'IN' ) )
            // Sql.g:56:5: ( 'in' | 'IN' )
            {
            if ( (input.LA(1)>=56 && input.LA(1)<=57) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_in639);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end in


    // $ANTLR start not
    // Sql.g:57:1: not : ( 'not' | 'NOT' ) ;
    public final void not() throws RecognitionException {
        try {
            // Sql.g:57:5: ( ( 'not' | 'NOT' ) )
            // Sql.g:57:6: ( 'not' | 'NOT' )
            {
            if ( (input.LA(1)>=58 && input.LA(1)<=59) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_not651);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end not

    public static class like_return extends ParserRuleReturnScope {
    };

    // $ANTLR start like
    // Sql.g:58:1: like : ( 'like' | 'LIKE' ) ;
    public final like_return like() throws RecognitionException {
        like_return retval = new like_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:58:6: ( ( 'like' | 'LIKE' ) )
            // Sql.g:58:7: ( 'like' | 'LIKE' )
            {
            if ( (input.LA(1)>=60 && input.LA(1)<=61) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_like663);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end like


 

    public static final BitSet FOLLOW_select_in_stmt22 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_stmt24 = new BitSet(new long[]{0x000000000FFC0000L});
    public static final BitSet FOLLOW_selectList_in_stmt27 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_stmt31 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_where_in_stmt33 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_stmt36 = new BitSet(new long[]{0x000000000FFC0000L});
    public static final BitSet FOLLOW_constraintList_in_stmt38 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NL_in_stmt41 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_selectList54 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_COMMA_in_selectList64 = new BitSet(new long[]{0x000000000FFC0000L});
    public static final BitSet FOLLOW_keyword_in_selectList72 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_entity_in_keyword97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword103 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_DOT_in_keyword105 = new BitSet(new long[]{0x000003FFF0080000L});
    public static final BitSet FOLLOW_attr_in_keyword107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword112 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_DOT_in_keyword114 = new BitSet(new long[]{0x0003FC0000000000L});
    public static final BitSet FOLLOW_funct_in_keyword116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_constraintList125 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_SPACE_in_constraintList129 = new BitSet(new long[]{0x00F0000000000000L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList136 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_constraintList144 = new BitSet(new long[]{0x000000000FFC0000L});
    public static final BitSet FOLLOW_constraint_in_constraintList146 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_keyword_in_constraint159 = new BitSet(new long[]{0x0000000000000700L});
    public static final BitSet FOLLOW_set_in_constraint171 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_VALUE_in_constraint194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint224 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_constraint233 = new BitSet(new long[]{0x0300000000000000L});
    public static final BitSet FOLLOW_in_in_constraint240 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_constraint251 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_constraint253 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_valueList_in_constraint260 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_constraint268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint294 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_constraint303 = new BitSet(new long[]{0x3000000000000000L});
    public static final BitSet FOLLOW_like_in_constraint310 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_constraint319 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_likeValue_in_constraint326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_valueList367 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_COMMA_in_valueList370 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_VALUE_in_valueList372 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_set_in_likeValue381 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_and_in_logicalOp394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_like663 = new BitSet(new long[]{0x0000000000000002L});

}
