package dbs.search.parser;
// $ANTLR 3.0.1 Sql.g 2008-04-22 14:34:06


import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SPACE", "COMMA", "DOT", "EQ", "LT", "GT", "VALUE", "AMP", "STAR", "NL", "WS", "'('", "')'", "'WHERE'", "'where'", "'ads'", "'dataset'", "'release'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'ls'", "'dq'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numlss'", "'size'", "'count'", "'status'", "'type'", "'id'", "'numruns()'", "'numfiles()'", "'dataquality()'", "'latest()'", "'parentrelease()'", "'childrelease()'", "'intluminosity()'", "'findevents()'", "'select'", "'SELECT'", "'find'", "'FIND'", "'and'", "'AND'", "'or'", "'OR'", "'in'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'"
    };
    public static final int COMMA=5;
    public static final int SPACE=4;
    public static final int NL=13;
    public static final int WS=14;
    public static final int EOF=-1;
    public static final int EQ=7;
    public static final int AMP=11;
    public static final int LT=8;
    public static final int GT=9;
    public static final int STAR=12;
    public static final int VALUE=10;
    public static final int DOT=6;

        public SqlParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "Sql.g"; }


    ArrayList kws = new ArrayList();
    ArrayList constraints = new ArrayList();



    // $ANTLR start stmt
    // Sql.g:19:1: stmt : ( select spaces selectList spaces where spaces constraintList | select spaces selectList );
    public final void stmt() throws RecognitionException {
        try {
            // Sql.g:19:6: ( select spaces selectList spaces where spaces constraintList | select spaces selectList )
            int alt1=2;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // Sql.g:19:8: select spaces selectList spaces where spaces constraintList
                    {
                    pushFollow(FOLLOW_select_in_stmt27);
                    select();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt29);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_selectList_in_stmt31);
                    selectList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt33);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_where_in_stmt35);
                    where();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt37);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_constraintList_in_stmt39);
                    constraintList();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:20:4: select spaces selectList
                    {
                    pushFollow(FOLLOW_select_in_stmt46);
                    select();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt48);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_selectList_in_stmt50);
                    selectList();
                    _fsp--;


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
    // $ANTLR end stmt


    // $ANTLR start spaces
    // Sql.g:22:1: spaces : ( SPACE )* ;
    public final void spaces() throws RecognitionException {
        try {
            // Sql.g:22:8: ( ( SPACE )* )
            // Sql.g:22:10: ( SPACE )*
            {
            // Sql.g:22:10: ( SPACE )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==SPACE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Sql.g:22:11: SPACE
            	    {
            	    match(input,SPACE,FOLLOW_SPACE_in_spaces59); 

            	    }
            	    break;

            	default :
            	    break loop2;
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
    // $ANTLR end spaces


    // $ANTLR start selectList
    // Sql.g:24:1: selectList : kw= keyword ( COMMA spaces kw= keyword )* ;
    public final void selectList() throws RecognitionException {
        keyword_return kw = null;


        try {
            // Sql.g:24:12: (kw= keyword ( COMMA spaces kw= keyword )* )
            // Sql.g:24:13: kw= keyword ( COMMA spaces kw= keyword )*
            {
            pushFollow(FOLLOW_keyword_in_selectList74);
            kw=keyword();
            _fsp--;

            kws.add(input.toString(kw.start,kw.stop));
            // Sql.g:25:4: ( COMMA spaces kw= keyword )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==COMMA) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Sql.g:25:5: COMMA spaces kw= keyword
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectList84); 
            	    pushFollow(FOLLOW_spaces_in_selectList88);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_keyword_in_selectList95);
            	    kw=keyword();
            	    _fsp--;

            	    kws.add(input.toString(kw.start,kw.stop));

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
    // $ANTLR end selectList

    public static class keyword_return extends ParserRuleReturnScope {
    };

    // $ANTLR start keyword
    // Sql.g:30:1: keyword : ( entity | entity DOT attr | entity DOT funct );
    public final keyword_return keyword() throws RecognitionException {
        keyword_return retval = new keyword_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:30:9: ( entity | entity DOT attr | entity DOT funct )
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=19 && LA4_0<=29)) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==DOT) ) {
                    int LA4_2 = input.LA(3);

                    if ( ((LA4_2>=48 && LA4_2<=55)) ) {
                        alt4=3;
                    }
                    else if ( ((LA4_2>=20 && LA4_2<=21)||(LA4_2>=30 && LA4_2<=47)) ) {
                        alt4=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("30:1: keyword : ( entity | entity DOT attr | entity DOT funct );", 4, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA4_1==EOF||(LA4_1>=SPACE && LA4_1<=COMMA)||(LA4_1>=EQ && LA4_1<=GT)||(LA4_1>=17 && LA4_1<=18)||(LA4_1>=64 && LA4_1<=65)||(LA4_1>=68 && LA4_1<=69)) ) {
                    alt4=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("30:1: keyword : ( entity | entity DOT attr | entity DOT funct );", 4, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("30:1: keyword : ( entity | entity DOT attr | entity DOT funct );", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // Sql.g:30:11: entity
                    {
                    pushFollow(FOLLOW_entity_in_keyword120);
                    entity();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:31:4: entity DOT attr
                    {
                    pushFollow(FOLLOW_entity_in_keyword126);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword128); 
                    pushFollow(FOLLOW_attr_in_keyword130);
                    attr();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Sql.g:32:4: entity DOT funct
                    {
                    pushFollow(FOLLOW_entity_in_keyword135);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword137); 
                    pushFollow(FOLLOW_funct_in_keyword139);
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
    // Sql.g:34:1: constraintList : constraint ( spaces rel= logicalOp spaces constraint )* ;
    public final void constraintList() throws RecognitionException {
        logicalOp_return rel = null;


        try {
            // Sql.g:34:16: ( constraint ( spaces rel= logicalOp spaces constraint )* )
            // Sql.g:34:18: constraint ( spaces rel= logicalOp spaces constraint )*
            {
            pushFollow(FOLLOW_constraint_in_constraintList148);
            constraint();
            _fsp--;

            // Sql.g:34:29: ( spaces rel= logicalOp spaces constraint )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==SPACE||(LA5_0>=60 && LA5_0<=63)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Sql.g:34:31: spaces rel= logicalOp spaces constraint
            	    {
            	    pushFollow(FOLLOW_spaces_in_constraintList152);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_logicalOp_in_constraintList159);
            	    rel=logicalOp();
            	    _fsp--;

            	    if(rel== null) System.out.println("REL is NULL"); constraints.add(input.toString(rel.start,rel.stop));
            	    pushFollow(FOLLOW_spaces_in_constraintList167);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_constraint_in_constraintList169);
            	    constraint();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
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
    // Sql.g:38:1: constraint : (kw= keyword spaces op= ( EQ | LT | GT ) spaces val= genValue | kw= keyword spaces op1= in spaces '(' val1= valueList ')' | kw= keyword spaces op2= like spaces val2= likeValue );
    public final void constraint() throws RecognitionException {
        Token op=null;
        keyword_return kw = null;

        genValue_return val = null;

        in_return op1 = null;

        valueList_return val1 = null;

        like_return op2 = null;

        likeValue_return val2 = null;


        try {
            // Sql.g:38:12: (kw= keyword spaces op= ( EQ | LT | GT ) spaces val= genValue | kw= keyword spaces op1= in spaces '(' val1= valueList ')' | kw= keyword spaces op2= like spaces val2= likeValue )
            int alt6=3;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // Sql.g:38:14: kw= keyword spaces op= ( EQ | LT | GT ) spaces val= genValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint182);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint191);
                    spaces();
                    _fsp--;

                    op=(Token)input.LT(1);
                    if ( (input.LA(1)>=EQ && input.LA(1)<=GT) ) {
                        input.consume();
                        errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse =
                            new MismatchedSetException(null,input);
                        recoverFromMismatchedSet(input,mse,FOLLOW_set_in_constraint198);    throw mse;
                    }

                    c.setOp(op.getText());
                    pushFollow(FOLLOW_spaces_in_constraint218);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_genValue_in_constraint225);
                    val=genValue();
                    _fsp--;

                    c.setValue(input.toString(val.start,val.stop)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:44:2: kw= keyword spaces op1= in spaces '(' val1= valueList ')'
                    {
                    pushFollow(FOLLOW_keyword_in_constraint253);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint262);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_in_in_constraint269);
                    op1=in();
                    _fsp--;

                    c.setOp(input.toString(op1.start,op1.stop));
                    pushFollow(FOLLOW_spaces_in_constraint280);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_constraint282); 
                    pushFollow(FOLLOW_valueList_in_constraint289);
                    val1=valueList();
                    _fsp--;

                    c.setValue(input.toString(val1.start,val1.stop)); constraints.add(c);
                    match(input,16,FOLLOW_16_in_constraint297); 

                    }
                    break;
                case 3 :
                    // Sql.g:51:2: kw= keyword spaces op2= like spaces val2= likeValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint323);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint332);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_like_in_constraint339);
                    op2=like();
                    _fsp--;

                    c.setOp(input.toString(op2.start,op2.stop));
                    pushFollow(FOLLOW_spaces_in_constraint348);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_likeValue_in_constraint355);
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
    // Sql.g:57:1: where : ( 'WHERE' | 'where' ) ;
    public final void where() throws RecognitionException {
        try {
            // Sql.g:57:7: ( ( 'WHERE' | 'where' ) )
            // Sql.g:57:8: ( 'WHERE' | 'where' )
            {
            if ( (input.LA(1)>=17 && input.LA(1)<=18) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_where384);    throw mse;
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
    // Sql.g:58:1: valueList : VALUE ( COMMA VALUE )* ;
    public final valueList_return valueList() throws RecognitionException {
        valueList_return retval = new valueList_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:58:11: ( VALUE ( COMMA VALUE )* )
            // Sql.g:58:12: VALUE ( COMMA VALUE )*
            {
            match(input,VALUE,FOLLOW_VALUE_in_valueList396); 
            // Sql.g:58:18: ( COMMA VALUE )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==COMMA) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // Sql.g:58:19: COMMA VALUE
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_valueList399); 
            	    match(input,VALUE,FOLLOW_VALUE_in_valueList401); 

            	    }
            	    break;

            	default :
            	    break loop7;
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

    public static class genValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start genValue
    // Sql.g:59:1: genValue : ( VALUE | VALUE EQ VALUE ( AMP VALUE EQ VALUE )* );
    public final genValue_return genValue() throws RecognitionException {
        genValue_return retval = new genValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:59:10: ( VALUE | VALUE EQ VALUE ( AMP VALUE EQ VALUE )* )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==VALUE) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==EQ) ) {
                    alt9=2;
                }
                else if ( (LA9_1==EOF||LA9_1==SPACE||(LA9_1>=60 && LA9_1<=63)) ) {
                    alt9=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("59:1: genValue : ( VALUE | VALUE EQ VALUE ( AMP VALUE EQ VALUE )* );", 9, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("59:1: genValue : ( VALUE | VALUE EQ VALUE ( AMP VALUE EQ VALUE )* );", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // Sql.g:59:11: VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_genValue409); 

                    }
                    break;
                case 2 :
                    // Sql.g:60:3: VALUE EQ VALUE ( AMP VALUE EQ VALUE )*
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_genValue414); 
                    match(input,EQ,FOLLOW_EQ_in_genValue416); 
                    match(input,VALUE,FOLLOW_VALUE_in_genValue418); 
                    // Sql.g:60:18: ( AMP VALUE EQ VALUE )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==AMP) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // Sql.g:60:19: AMP VALUE EQ VALUE
                    	    {
                    	    match(input,AMP,FOLLOW_AMP_in_genValue421); 
                    	    match(input,VALUE,FOLLOW_VALUE_in_genValue423); 
                    	    match(input,EQ,FOLLOW_EQ_in_genValue425); 
                    	    match(input,VALUE,FOLLOW_VALUE_in_genValue427); 

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


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
    // $ANTLR end genValue

    public static class likeValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start likeValue
    // Sql.g:61:1: likeValue : ( VALUE | STAR )+ ;
    public final likeValue_return likeValue() throws RecognitionException {
        likeValue_return retval = new likeValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:61:12: ( ( VALUE | STAR )+ )
            // Sql.g:61:13: ( VALUE | STAR )+
            {
            // Sql.g:61:13: ( VALUE | STAR )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==VALUE||LA10_0==STAR) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // Sql.g:
            	    {
            	    if ( input.LA(1)==VALUE||input.LA(1)==STAR ) {
            	        input.consume();
            	        errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recoverFromMismatchedSet(input,mse,FOLLOW_set_in_likeValue436);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
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
    // Sql.g:62:1: logicalOp : ( and | or ) ;
    public final logicalOp_return logicalOp() throws RecognitionException {
        logicalOp_return retval = new logicalOp_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:62:11: ( ( and | or ) )
            // Sql.g:62:12: ( and | or )
            {
            // Sql.g:62:12: ( and | or )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=60 && LA11_0<=61)) ) {
                alt11=1;
            }
            else if ( ((LA11_0>=62 && LA11_0<=63)) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("62:12: ( and | or )", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // Sql.g:62:13: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp449);
                    and();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:62:17: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp451);
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
    // Sql.g:63:1: entity : ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'ls' | 'dq' ) ;
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:63:8: ( ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'ls' | 'dq' ) )
            // Sql.g:63:10: ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'ls' | 'dq' )
            {
            if ( (input.LA(1)>=19 && input.LA(1)<=29) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_entity459);    throw mse;
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
    // Sql.g:64:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' ) ;
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:64:6: ( ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' ) )
            // Sql.g:64:7: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' )
            {
            if ( (input.LA(1)>=20 && input.LA(1)<=21)||(input.LA(1)>=30 && input.LA(1)<=47) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_attr507);    throw mse;
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
    // Sql.g:65:1: funct : ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) ;
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:65:7: ( ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) )
            // Sql.g:65:8: ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' )
            {
            if ( (input.LA(1)>=48 && input.LA(1)<=55) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_funct592);    throw mse;
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
    // Sql.g:66:1: select : ( 'select' | 'SELECT' | 'find' | 'FIND' ) ;
    public final void select() throws RecognitionException {
        try {
            // Sql.g:66:8: ( ( 'select' | 'SELECT' | 'find' | 'FIND' ) )
            // Sql.g:66:9: ( 'select' | 'SELECT' | 'find' | 'FIND' )
            {
            if ( (input.LA(1)>=56 && input.LA(1)<=59) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_select629);    throw mse;
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
    // Sql.g:67:1: and : ( 'and' | 'AND' ) ;
    public final void and() throws RecognitionException {
        try {
            // Sql.g:67:5: ( ( 'and' | 'AND' ) )
            // Sql.g:67:6: ( 'and' | 'AND' )
            {
            if ( (input.LA(1)>=60 && input.LA(1)<=61) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_and649);    throw mse;
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
    // Sql.g:68:1: or : ( 'or' | 'OR' ) ;
    public final void or() throws RecognitionException {
        try {
            // Sql.g:68:4: ( ( 'or' | 'OR' ) )
            // Sql.g:68:5: ( 'or' | 'OR' )
            {
            if ( (input.LA(1)>=62 && input.LA(1)<=63) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_or661);    throw mse;
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
    // Sql.g:69:1: in : ( 'in' | 'IN' ) ;
    public final in_return in() throws RecognitionException {
        in_return retval = new in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:69:4: ( ( 'in' | 'IN' ) )
            // Sql.g:69:5: ( 'in' | 'IN' )
            {
            if ( (input.LA(1)>=64 && input.LA(1)<=65) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_in673);    throw mse;
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
    // Sql.g:70:1: not : ( 'not' | 'NOT' ) ;
    public final void not() throws RecognitionException {
        try {
            // Sql.g:70:5: ( ( 'not' | 'NOT' ) )
            // Sql.g:70:6: ( 'not' | 'NOT' )
            {
            if ( (input.LA(1)>=66 && input.LA(1)<=67) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_not685);    throw mse;
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
    // Sql.g:71:1: like : ( 'like' | 'LIKE' ) ;
    public final like_return like() throws RecognitionException {
        like_return retval = new like_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:71:6: ( ( 'like' | 'LIKE' ) )
            // Sql.g:71:7: ( 'like' | 'LIKE' )
            {
            if ( (input.LA(1)>=68 && input.LA(1)<=69) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_like697);    throw mse;
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


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA1_eotS =
        "\17\uffff";
    static final String DFA1_eofS =
        "\3\uffff\1\5\5\uffff\3\5\1\uffff\2\5";
    static final String DFA1_minS =
        "\1\70\4\4\1\uffff\1\24\1\uffff\4\4\1\24\2\4";
    static final String DFA1_maxS =
        "\1\73\2\35\1\22\1\35\1\uffff\1\67\1\uffff\1\35\3\22\1\67\2\22";
    static final String DFA1_acceptS =
        "\5\uffff\1\2\1\uffff\1\1\7\uffff";
    static final String DFA1_specialS =
        "\17\uffff}>";
    static final String[] DFA1_transitionS = {
            "\4\1",
            "\1\2\16\uffff\13\3",
            "\1\2\16\uffff\13\3",
            "\1\7\1\4\1\6\12\uffff\2\7",
            "\1\10\16\uffff\13\11",
            "",
            "\2\12\10\uffff\22\12\10\13",
            "",
            "\1\10\16\uffff\13\11",
            "\1\7\1\4\1\14\12\uffff\2\7",
            "\1\7\1\4\13\uffff\2\7",
            "\1\7\1\4\13\uffff\2\7",
            "\2\16\10\uffff\22\16\10\15",
            "\1\7\1\4\13\uffff\2\7",
            "\1\7\1\4\13\uffff\2\7"
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "19:1: stmt : ( select spaces selectList spaces where spaces constraintList | select spaces selectList );";
        }
    }
    static final String DFA6_eotS =
        "\11\uffff";
    static final String DFA6_eofS =
        "\11\uffff";
    static final String DFA6_minS =
        "\1\23\1\4\1\24\1\4\3\uffff\2\4";
    static final String DFA6_maxS =
        "\1\35\1\105\1\67\1\105\3\uffff\2\105";
    static final String DFA6_acceptS =
        "\4\uffff\1\3\1\1\1\2\2\uffff";
    static final String DFA6_specialS =
        "\11\uffff}>";
    static final String[] DFA6_transitionS = {
            "\13\1",
            "\1\3\1\uffff\1\2\3\5\66\uffff\2\6\2\uffff\2\4",
            "\2\10\10\uffff\22\10\10\7",
            "\1\3\2\uffff\3\5\66\uffff\2\6\2\uffff\2\4",
            "",
            "",
            "",
            "\1\3\2\uffff\3\5\66\uffff\2\6\2\uffff\2\4",
            "\1\3\2\uffff\3\5\66\uffff\2\6\2\uffff\2\4"
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "38:1: constraint : (kw= keyword spaces op= ( EQ | LT | GT ) spaces val= genValue | kw= keyword spaces op1= in spaces '(' val1= valueList ')' | kw= keyword spaces op2= like spaces val2= likeValue );";
        }
    }
 

    public static final BitSet FOLLOW_select_in_stmt27 = new BitSet(new long[]{0x000000003FF80010L});
    public static final BitSet FOLLOW_spaces_in_stmt29 = new BitSet(new long[]{0x000000003FF80000L});
    public static final BitSet FOLLOW_selectList_in_stmt31 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt33 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_where_in_stmt35 = new BitSet(new long[]{0x000000003FF80010L});
    public static final BitSet FOLLOW_spaces_in_stmt37 = new BitSet(new long[]{0x000000003FF80000L});
    public static final BitSet FOLLOW_constraintList_in_stmt39 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt46 = new BitSet(new long[]{0x000000003FF80010L});
    public static final BitSet FOLLOW_spaces_in_stmt48 = new BitSet(new long[]{0x000000003FF80000L});
    public static final BitSet FOLLOW_selectList_in_stmt50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SPACE_in_spaces59 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_keyword_in_selectList74 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_COMMA_in_selectList84 = new BitSet(new long[]{0x000000003FF80010L});
    public static final BitSet FOLLOW_spaces_in_selectList88 = new BitSet(new long[]{0x000000003FF80000L});
    public static final BitSet FOLLOW_keyword_in_selectList95 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_entity_in_keyword120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword126 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword128 = new BitSet(new long[]{0x0000FFFFC0300000L});
    public static final BitSet FOLLOW_attr_in_keyword130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword135 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword137 = new BitSet(new long[]{0x00FF000000000000L});
    public static final BitSet FOLLOW_funct_in_keyword139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_constraintList148 = new BitSet(new long[]{0xF000000000000012L});
    public static final BitSet FOLLOW_spaces_in_constraintList152 = new BitSet(new long[]{0xF000000000000000L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList159 = new BitSet(new long[]{0x000000003FF80010L});
    public static final BitSet FOLLOW_spaces_in_constraintList167 = new BitSet(new long[]{0x000000003FF80000L});
    public static final BitSet FOLLOW_constraint_in_constraintList169 = new BitSet(new long[]{0xF000000000000012L});
    public static final BitSet FOLLOW_keyword_in_constraint182 = new BitSet(new long[]{0x0000000000000390L});
    public static final BitSet FOLLOW_spaces_in_constraint191 = new BitSet(new long[]{0x0000000000000380L});
    public static final BitSet FOLLOW_set_in_constraint198 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_spaces_in_constraint218 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_genValue_in_constraint225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint253 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
    public static final BitSet FOLLOW_spaces_in_constraint262 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_in_in_constraint269 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_constraint280 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_constraint282 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_valueList_in_constraint289 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_constraint297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint323 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000030L});
    public static final BitSet FOLLOW_spaces_in_constraint332 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000030L});
    public static final BitSet FOLLOW_like_in_constraint339 = new BitSet(new long[]{0x0000000000001410L});
    public static final BitSet FOLLOW_spaces_in_constraint348 = new BitSet(new long[]{0x0000000000001400L});
    public static final BitSet FOLLOW_likeValue_in_constraint355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_valueList396 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_COMMA_in_valueList399 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_valueList401 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_VALUE_in_genValue409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_genValue414 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EQ_in_genValue416 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_genValue418 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_AMP_in_genValue421 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_genValue423 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EQ_in_genValue425 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_genValue427 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_set_in_likeValue436 = new BitSet(new long[]{0x0000000000001402L});
    public static final BitSet FOLLOW_and_in_logicalOp449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_like697 = new BitSet(new long[]{0x0000000000000002L});

}