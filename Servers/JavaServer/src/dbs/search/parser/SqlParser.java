package dbs.search.parser;
// $ANTLR 3.0.1 Sql.g 2008-06-12 16:39:47


import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SPACE", "COMMA", "DOT", "EQ", "LT", "GT", "VALUE", "AMP", "STAR", "NL", "WS", "'('", "')'", "'WHERE'", "'where'", "'ads'", "'dataset'", "'release'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'lumi'", "'dq'", "'ilumi'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numlss'", "'size'", "'count'", "'status'", "'type'", "'id'", "'parent'", "'tier'", "'def'", "'evnum'", "'era'", "'tag'", "'numruns()'", "'numfiles()'", "'dataquality()'", "'latest()'", "'parentrelease()'", "'childrelease()'", "'intluminosity()'", "'findevents()'", "'select'", "'SELECT'", "'find'", "'FIND'", "'and'", "'AND'", "'order'", "'ORDER'", "'by'", "'BY'", "'or'", "'OR'", "'in'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'"
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
    ArrayList okws = new ArrayList();
    ArrayList constraints = new ArrayList();



    // $ANTLR start stmt
    // Sql.g:20:1: stmt : ( select spaces selectList spaces where spaces constraintList | select spaces selectList | select spaces selectList spaces order spaces by spaces okw= keyword spaces | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces okw= keyword spaces );
    public final void stmt() throws RecognitionException {
        keyword_return okw = null;


        try {
            // Sql.g:20:6: ( select spaces selectList spaces where spaces constraintList | select spaces selectList | select spaces selectList spaces order spaces by spaces okw= keyword spaces | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces okw= keyword spaces )
            int alt1=4;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // Sql.g:20:8: select spaces selectList spaces where spaces constraintList
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
                    // Sql.g:21:4: select spaces selectList
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
                case 3 :
                    // Sql.g:22:4: select spaces selectList spaces order spaces by spaces okw= keyword spaces
                    {
                    pushFollow(FOLLOW_select_in_stmt55);
                    select();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt57);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_selectList_in_stmt59);
                    selectList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt61);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_order_in_stmt63);
                    order();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt65);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_by_in_stmt67);
                    by();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt69);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_keyword_in_stmt74);
                    okw=keyword();
                    _fsp--;

                    okws.add(input.toString(okw.start,okw.stop));
                    pushFollow(FOLLOW_spaces_in_stmt80);
                    spaces();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Sql.g:25:4: select spaces selectList spaces where spaces constraintList spaces order spaces by spaces okw= keyword spaces
                    {
                    pushFollow(FOLLOW_select_in_stmt85);
                    select();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt87);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_selectList_in_stmt89);
                    selectList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt91);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_where_in_stmt93);
                    where();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt95);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_constraintList_in_stmt97);
                    constraintList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt99);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_order_in_stmt101);
                    order();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt103);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_by_in_stmt105);
                    by();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt107);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_keyword_in_stmt112);
                    okw=keyword();
                    _fsp--;

                    okws.add(input.toString(okw.start,okw.stop));
                    pushFollow(FOLLOW_spaces_in_stmt118);
                    spaces();
                    _fsp--;


                    }
                    break;

            }
        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end stmt


    // $ANTLR start spaces
    // Sql.g:30:1: spaces : ( SPACE )* ;
    public final void spaces() throws RecognitionException {
        try {
            // Sql.g:30:8: ( ( SPACE )* )
            // Sql.g:30:10: ( SPACE )*
            {
            // Sql.g:30:10: ( SPACE )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==SPACE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Sql.g:30:11: SPACE
            	    {
            	    match(input,SPACE,FOLLOW_SPACE_in_spaces128); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end spaces


    // $ANTLR start selectList
    // Sql.g:32:1: selectList : kw= keyword ( spaces COMMA spaces kw= keyword )* ;
    public final void selectList() throws RecognitionException {
        keyword_return kw = null;


        try {
            // Sql.g:32:12: (kw= keyword ( spaces COMMA spaces kw= keyword )* )
            // Sql.g:32:13: kw= keyword ( spaces COMMA spaces kw= keyword )*
            {
            pushFollow(FOLLOW_keyword_in_selectList143);
            kw=keyword();
            _fsp--;

            kws.add(input.toString(kw.start,kw.stop));
            // Sql.g:33:4: ( spaces COMMA spaces kw= keyword )*
            loop3:
            do {
                int alt3=2;
                alt3 = dfa3.predict(input);
                switch (alt3) {
            	case 1 :
            	    // Sql.g:34:3: spaces COMMA spaces kw= keyword
            	    {
            	    pushFollow(FOLLOW_spaces_in_selectList156);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_selectList160); 
            	    pushFollow(FOLLOW_spaces_in_selectList164);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_keyword_in_selectList171);
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

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end selectList

    public static class keyword_return extends ParserRuleReturnScope {
    };

    // $ANTLR start keyword
    // Sql.g:40:1: keyword : ( entity | entity DOT attr | entity DOT funct );
    public final keyword_return keyword() throws RecognitionException {
        keyword_return retval = new keyword_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:40:9: ( entity | entity DOT attr | entity DOT funct )
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=19 && LA4_0<=30)) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==EOF||(LA4_1>=SPACE && LA4_1<=COMMA)||(LA4_1>=EQ && LA4_1<=GT)||(LA4_1>=17 && LA4_1<=18)||(LA4_1>=69 && LA4_1<=70)||(LA4_1>=75 && LA4_1<=76)||(LA4_1>=79 && LA4_1<=80)) ) {
                    alt4=1;
                }
                else if ( (LA4_1==DOT) ) {
                    int LA4_3 = input.LA(3);

                    if ( ((LA4_3>=55 && LA4_3<=62)) ) {
                        alt4=3;
                    }
                    else if ( ((LA4_3>=20 && LA4_3<=21)||(LA4_3>=31 && LA4_3<=54)) ) {
                        alt4=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("40:1: keyword : ( entity | entity DOT attr | entity DOT funct );", 4, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("40:1: keyword : ( entity | entity DOT attr | entity DOT funct );", 4, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("40:1: keyword : ( entity | entity DOT attr | entity DOT funct );", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // Sql.g:40:11: entity
                    {
                    pushFollow(FOLLOW_entity_in_keyword196);
                    entity();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:41:4: entity DOT attr
                    {
                    pushFollow(FOLLOW_entity_in_keyword202);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword204); 
                    pushFollow(FOLLOW_attr_in_keyword206);
                    attr();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Sql.g:42:4: entity DOT funct
                    {
                    pushFollow(FOLLOW_entity_in_keyword211);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword213); 
                    pushFollow(FOLLOW_funct_in_keyword215);
                    funct();
                    _fsp--;


                    }
                    break;

            }
            retval.stop = input.LT(-1);

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end keyword


    // $ANTLR start constraintList
    // Sql.g:44:1: constraintList : constraint ( spaces rel= logicalOp spaces constraint )* ;
    public final void constraintList() throws RecognitionException {
        logicalOp_return rel = null;


        try {
            // Sql.g:44:16: ( constraint ( spaces rel= logicalOp spaces constraint )* )
            // Sql.g:44:18: constraint ( spaces rel= logicalOp spaces constraint )*
            {
            pushFollow(FOLLOW_constraint_in_constraintList224);
            constraint();
            _fsp--;

            // Sql.g:44:29: ( spaces rel= logicalOp spaces constraint )*
            loop5:
            do {
                int alt5=2;
                alt5 = dfa5.predict(input);
                switch (alt5) {
            	case 1 :
            	    // Sql.g:44:31: spaces rel= logicalOp spaces constraint
            	    {
            	    pushFollow(FOLLOW_spaces_in_constraintList228);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_logicalOp_in_constraintList235);
            	    rel=logicalOp();
            	    _fsp--;

            	    if(rel== null) System.out.println("REL is NULL"); constraints.add(input.toString(rel.start,rel.stop));
            	    pushFollow(FOLLOW_spaces_in_constraintList243);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_constraint_in_constraintList245);
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

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end constraintList


    // $ANTLR start constraint
    // Sql.g:48:1: constraint : (kw= keyword spaces op= ( EQ | LT | GT ) spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= likeValue );
    public final void constraint() throws RecognitionException {
        Token op=null;
        keyword_return kw = null;

        genValue_return val = null;

        in_return op1 = null;

        valueList_return val1 = null;

        like_return op2 = null;

        likeValue_return val2 = null;


        try {
            // Sql.g:48:12: (kw= keyword spaces op= ( EQ | LT | GT ) spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= likeValue )
            int alt6=3;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // Sql.g:48:14: kw= keyword spaces op= ( EQ | LT | GT ) spaces val= genValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint258);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint267);
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
                        recoverFromMismatchedSet(input,mse,FOLLOW_set_in_constraint274);    throw mse;
                    }

                    c.setOp(op.getText());
                    pushFollow(FOLLOW_spaces_in_constraint294);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_genValue_in_constraint301);
                    val=genValue();
                    _fsp--;

                    c.setValue(input.toString(val.start,val.stop)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:54:2: kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')'
                    {
                    pushFollow(FOLLOW_keyword_in_constraint330);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint339);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_in_in_constraint346);
                    op1=in();
                    _fsp--;

                    c.setOp(input.toString(op1.start,op1.stop));
                    pushFollow(FOLLOW_spaces_in_constraint357);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_constraint359); 
                    pushFollow(FOLLOW_spaces_in_constraint363);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_valueList_in_constraint369);
                    val1=valueList();
                    _fsp--;

                    c.setValue(input.toString(val1.start,val1.stop)); constraints.add(c);
                    pushFollow(FOLLOW_spaces_in_constraint377);
                    spaces();
                    _fsp--;

                    match(input,16,FOLLOW_16_in_constraint381); 

                    }
                    break;
                case 3 :
                    // Sql.g:63:2: kw= keyword spaces op2= like spaces val2= likeValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint407);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint416);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_like_in_constraint423);
                    op2=like();
                    _fsp--;

                    c.setOp(input.toString(op2.start,op2.stop));
                    pushFollow(FOLLOW_spaces_in_constraint432);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_likeValue_in_constraint439);
                    val2=likeValue();
                    _fsp--;

                    c.setValue(input.toString(val2.start,val2.stop)); constraints.add(c);

                    }
                    break;

            }
        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end constraint


    // $ANTLR start where
    // Sql.g:69:1: where : ( 'WHERE' | 'where' ) ;
    public final void where() throws RecognitionException {
        try {
            // Sql.g:69:7: ( ( 'WHERE' | 'where' ) )
            // Sql.g:69:8: ( 'WHERE' | 'where' )
            {
            if ( (input.LA(1)>=17 && input.LA(1)<=18) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_where468);    throw mse;
            }


            }

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end where


    // $ANTLR start dotValue
    // Sql.g:70:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );
    public final void dotValue() throws RecognitionException {
        try {
            // Sql.g:70:17: ( VALUE | VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE )
            int alt7=4;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==VALUE) ) {
                switch ( input.LA(2) ) {
                case SPACE:
                    {
                    int LA7_2 = input.LA(3);

                    if ( (LA7_2==VALUE) ) {
                        int LA7_5 = input.LA(4);

                        if ( (LA7_5==SPACE) ) {
                            int LA7_6 = input.LA(5);

                            if ( (LA7_6==VALUE) ) {
                                alt7=4;
                            }
                            else if ( ((LA7_6>=SPACE && LA7_6<=COMMA)||LA7_6==16||(LA7_6>=67 && LA7_6<=70)||(LA7_6>=73 && LA7_6<=74)) ) {
                                alt7=3;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("70:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 6, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA7_5==EOF||LA7_5==COMMA||(LA7_5>=EQ && LA7_5<=STAR)||LA7_5==16||(LA7_5>=67 && LA7_5<=70)||(LA7_5>=73 && LA7_5<=74)) ) {
                            alt7=3;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("70:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 5, input);

                            throw nvae;
                        }
                    }
                    else if ( ((LA7_2>=SPACE && LA7_2<=COMMA)||LA7_2==16||(LA7_2>=67 && LA7_2<=70)||(LA7_2>=73 && LA7_2<=74)) ) {
                        alt7=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("70:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case DOT:
                    {
                    alt7=2;
                    }
                    break;
                case EOF:
                case COMMA:
                case EQ:
                case LT:
                case GT:
                case VALUE:
                case AMP:
                case STAR:
                case 16:
                case 67:
                case 68:
                case 69:
                case 70:
                case 73:
                case 74:
                    {
                    alt7=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("70:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("70:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // Sql.g:70:19: VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue488); 

                    }
                    break;
                case 2 :
                    // Sql.g:71:5: VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue495); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue497); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue499); 

                    }
                    break;
                case 3 :
                    // Sql.g:72:5: VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue505); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue507); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue509); 

                    }
                    break;
                case 4 :
                    // Sql.g:73:5: VALUE SPACE VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue515); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue517); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue519); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue521); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue523); 

                    }
                    break;

            }
        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end dotValue

    public static class valueList_return extends ParserRuleReturnScope {
    };

    // $ANTLR start valueList
    // Sql.g:76:1: valueList : dotValue ( spaces COMMA spaces dotValue )* ;
    public final valueList_return valueList() throws RecognitionException {
        valueList_return retval = new valueList_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:76:11: ( dotValue ( spaces COMMA spaces dotValue )* )
            // Sql.g:76:12: dotValue ( spaces COMMA spaces dotValue )*
            {
            pushFollow(FOLLOW_dotValue_in_valueList531);
            dotValue();
            _fsp--;

            // Sql.g:76:21: ( spaces COMMA spaces dotValue )*
            loop8:
            do {
                int alt8=2;
                alt8 = dfa8.predict(input);
                switch (alt8) {
            	case 1 :
            	    // Sql.g:76:23: spaces COMMA spaces dotValue
            	    {
            	    pushFollow(FOLLOW_spaces_in_valueList535);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_valueList537); 
            	    pushFollow(FOLLOW_spaces_in_valueList539);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_dotValue_in_valueList541);
            	    dotValue();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end valueList


    // $ANTLR start compOpt
    // Sql.g:77:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );
    public final void compOpt() throws RecognitionException {
        try {
            // Sql.g:77:10: ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) )
            int alt9=7;
            switch ( input.LA(1) ) {
            case EQ:
                {
                switch ( input.LA(2) ) {
                case GT:
                    {
                    alt9=4;
                    }
                    break;
                case VALUE:
                    {
                    alt9=1;
                    }
                    break;
                case LT:
                    {
                    alt9=5;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("77:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 9, 1, input);

                    throw nvae;
                }

                }
                break;
            case LT:
                {
                int LA9_2 = input.LA(2);

                if ( (LA9_2==EQ) ) {
                    alt9=6;
                }
                else if ( (LA9_2==VALUE) ) {
                    alt9=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("77:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 9, 2, input);

                    throw nvae;
                }
                }
                break;
            case GT:
                {
                int LA9_3 = input.LA(2);

                if ( (LA9_3==VALUE) ) {
                    alt9=3;
                }
                else if ( (LA9_3==EQ) ) {
                    alt9=7;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("77:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 9, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("77:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // Sql.g:77:11: ( EQ )
                    {
                    // Sql.g:77:11: ( EQ )
                    // Sql.g:77:12: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt552); 

                    }


                    }
                    break;
                case 2 :
                    // Sql.g:78:4: ( LT )
                    {
                    // Sql.g:78:4: ( LT )
                    // Sql.g:78:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt559); 

                    }


                    }
                    break;
                case 3 :
                    // Sql.g:79:4: ( GT )
                    {
                    // Sql.g:79:4: ( GT )
                    // Sql.g:79:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt566); 

                    }


                    }
                    break;
                case 4 :
                    // Sql.g:80:4: ( EQ ) ( GT )
                    {
                    // Sql.g:80:4: ( EQ )
                    // Sql.g:80:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt573); 

                    }

                    // Sql.g:80:8: ( GT )
                    // Sql.g:80:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt576); 

                    }


                    }
                    break;
                case 5 :
                    // Sql.g:81:4: ( EQ ) ( LT )
                    {
                    // Sql.g:81:4: ( EQ )
                    // Sql.g:81:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt583); 

                    }

                    // Sql.g:81:8: ( LT )
                    // Sql.g:81:9: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt586); 

                    }


                    }
                    break;
                case 6 :
                    // Sql.g:82:4: ( LT ) ( EQ )
                    {
                    // Sql.g:82:4: ( LT )
                    // Sql.g:82:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt593); 

                    }

                    // Sql.g:82:8: ( EQ )
                    // Sql.g:82:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt596); 

                    }


                    }
                    break;
                case 7 :
                    // Sql.g:83:4: ( GT ) ( EQ )
                    {
                    // Sql.g:83:4: ( GT )
                    // Sql.g:83:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt603); 

                    }

                    // Sql.g:83:8: ( EQ )
                    // Sql.g:83:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt606); 

                    }


                    }
                    break;

            }
        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end compOpt

    public static class genValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start genValue
    // Sql.g:84:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );
    public final genValue_return genValue() throws RecognitionException {
        genValue_return retval = new genValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:84:10: ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==VALUE) ) {
                switch ( input.LA(2) ) {
                case SPACE:
                    {
                    int LA11_2 = input.LA(3);

                    if ( (LA11_2==VALUE) ) {
                        switch ( input.LA(4) ) {
                        case SPACE:
                            {
                            int LA11_8 = input.LA(5);

                            if ( (LA11_8==VALUE) ) {
                                int LA11_9 = input.LA(6);

                                if ( ((LA11_9>=EQ && LA11_9<=GT)) ) {
                                    alt11=2;
                                }
                                else if ( (LA11_9==EOF||LA11_9==SPACE||(LA11_9>=67 && LA11_9<=70)||(LA11_9>=73 && LA11_9<=74)) ) {
                                    alt11=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("84:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 9, input);

                                    throw nvae;
                                }
                            }
                            else if ( (LA11_8==SPACE||(LA11_8>=67 && LA11_8<=70)||(LA11_8>=73 && LA11_8<=74)) ) {
                                alt11=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("84:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 8, input);

                                throw nvae;
                            }
                            }
                            break;
                        case EQ:
                        case LT:
                        case GT:
                            {
                            alt11=2;
                            }
                            break;
                        case EOF:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 73:
                        case 74:
                            {
                            alt11=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("84:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 6, input);

                            throw nvae;
                        }

                    }
                    else if ( (LA11_2==SPACE||(LA11_2>=67 && LA11_2<=70)||(LA11_2>=73 && LA11_2<=74)) ) {
                        alt11=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("84:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case DOT:
                    {
                    int LA11_3 = input.LA(3);

                    if ( (LA11_3==VALUE) ) {
                        int LA11_7 = input.LA(4);

                        if ( (LA11_7==EOF||LA11_7==SPACE||(LA11_7>=67 && LA11_7<=70)||(LA11_7>=73 && LA11_7<=74)) ) {
                            alt11=1;
                        }
                        else if ( ((LA11_7>=EQ && LA11_7<=GT)) ) {
                            alt11=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("84:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 7, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("84:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case EOF:
                case 67:
                case 68:
                case 69:
                case 70:
                case 73:
                case 74:
                    {
                    alt11=1;
                    }
                    break;
                case EQ:
                case LT:
                case GT:
                    {
                    alt11=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("84:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("84:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // Sql.g:84:11: dotValue
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue613);
                    dotValue();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:85:4: dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )*
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue618);
                    dotValue();
                    _fsp--;

                    pushFollow(FOLLOW_compOpt_in_genValue620);
                    compOpt();
                    _fsp--;

                    pushFollow(FOLLOW_dotValue_in_genValue622);
                    dotValue();
                    _fsp--;

                    // Sql.g:85:30: ( AMP dotValue compOpt dotValue )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==AMP) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // Sql.g:85:31: AMP dotValue compOpt dotValue
                    	    {
                    	    match(input,AMP,FOLLOW_AMP_in_genValue625); 
                    	    pushFollow(FOLLOW_dotValue_in_genValue627);
                    	    dotValue();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_compOpt_in_genValue629);
                    	    compOpt();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_dotValue_in_genValue631);
                    	    dotValue();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end genValue

    public static class likeValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start likeValue
    // Sql.g:86:1: likeValue : ( dotValue | STAR )+ ;
    public final likeValue_return likeValue() throws RecognitionException {
        likeValue_return retval = new likeValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:86:12: ( ( dotValue | STAR )+ )
            // Sql.g:86:13: ( dotValue | STAR )+
            {
            // Sql.g:86:13: ( dotValue | STAR )+
            int cnt12=0;
            loop12:
            do {
                int alt12=3;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==VALUE) ) {
                    alt12=1;
                }
                else if ( (LA12_0==STAR) ) {
                    alt12=2;
                }


                switch (alt12) {
            	case 1 :
            	    // Sql.g:86:14: dotValue
            	    {
            	    pushFollow(FOLLOW_dotValue_in_likeValue641);
            	    dotValue();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // Sql.g:86:24: STAR
            	    {
            	    match(input,STAR,FOLLOW_STAR_in_likeValue644); 

            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            }

            retval.stop = input.LT(-1);

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end likeValue

    public static class logicalOp_return extends ParserRuleReturnScope {
    };

    // $ANTLR start logicalOp
    // Sql.g:87:1: logicalOp : ( and | or ) ;
    public final logicalOp_return logicalOp() throws RecognitionException {
        logicalOp_return retval = new logicalOp_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:87:11: ( ( and | or ) )
            // Sql.g:87:12: ( and | or )
            {
            // Sql.g:87:12: ( and | or )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=67 && LA13_0<=68)) ) {
                alt13=1;
            }
            else if ( ((LA13_0>=73 && LA13_0<=74)) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("87:12: ( and | or )", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // Sql.g:87:13: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp653);
                    and();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:87:17: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp655);
                    or();
                    _fsp--;


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end logicalOp


    // $ANTLR start entity
    // Sql.g:88:1: entity : ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' ) ;
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:88:9: ( ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' ) )
            // Sql.g:88:11: ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' )
            {
            if ( (input.LA(1)>=19 && input.LA(1)<=30) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_entity664);    throw mse;
            }


            }

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entity


    // $ANTLR start attr
    // Sql.g:89:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) ;
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:89:7: ( ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) )
            // Sql.g:89:8: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' )
            {
            if ( (input.LA(1)>=20 && input.LA(1)<=21)||(input.LA(1)>=31 && input.LA(1)<=54) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_attr717);    throw mse;
            }


            }

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end attr


    // $ANTLR start funct
    // Sql.g:90:1: funct : ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) ;
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:90:8: ( ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) )
            // Sql.g:90:9: ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' )
            {
            if ( (input.LA(1)>=55 && input.LA(1)<=62) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_funct827);    throw mse;
            }


            }

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end funct


    // $ANTLR start select
    // Sql.g:91:1: select : ( 'select' | 'SELECT' | 'find' | 'FIND' ) ;
    public final void select() throws RecognitionException {
        try {
            // Sql.g:91:9: ( ( 'select' | 'SELECT' | 'find' | 'FIND' ) )
            // Sql.g:91:10: ( 'select' | 'SELECT' | 'find' | 'FIND' )
            {
            if ( (input.LA(1)>=63 && input.LA(1)<=66) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_select865);    throw mse;
            }


            }

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end select


    // $ANTLR start and
    // Sql.g:92:1: and : ( 'and' | 'AND' ) ;
    public final void and() throws RecognitionException {
        try {
            // Sql.g:92:6: ( ( 'and' | 'AND' ) )
            // Sql.g:92:7: ( 'and' | 'AND' )
            {
            if ( (input.LA(1)>=67 && input.LA(1)<=68) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_and886);    throw mse;
            }


            }

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end and


    // $ANTLR start order
    // Sql.g:93:1: order : ( 'order' | 'ORDER' ) ;
    public final void order() throws RecognitionException {
        try {
            // Sql.g:93:8: ( ( 'order' | 'ORDER' ) )
            // Sql.g:93:9: ( 'order' | 'ORDER' )
            {
            if ( (input.LA(1)>=69 && input.LA(1)<=70) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_order899);    throw mse;
            }


            }

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end order


    // $ANTLR start by
    // Sql.g:94:1: by : ( 'by' | 'BY' ) ;
    public final void by() throws RecognitionException {
        try {
            // Sql.g:94:5: ( ( 'by' | 'BY' ) )
            // Sql.g:94:6: ( 'by' | 'BY' )
            {
            if ( (input.LA(1)>=71 && input.LA(1)<=72) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_by912);    throw mse;
            }


            }

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end by


    // $ANTLR start or
    // Sql.g:95:1: or : ( 'or' | 'OR' ) ;
    public final void or() throws RecognitionException {
        try {
            // Sql.g:95:5: ( ( 'or' | 'OR' ) )
            // Sql.g:95:6: ( 'or' | 'OR' )
            {
            if ( (input.LA(1)>=73 && input.LA(1)<=74) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_or925);    throw mse;
            }


            }

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end or

    public static class in_return extends ParserRuleReturnScope {
    };

    // $ANTLR start in
    // Sql.g:96:1: in : ( 'in' | 'IN' ) ;
    public final in_return in() throws RecognitionException {
        in_return retval = new in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:96:5: ( ( 'in' | 'IN' ) )
            // Sql.g:96:6: ( 'in' | 'IN' )
            {
            if ( (input.LA(1)>=75 && input.LA(1)<=76) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_in938);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end in


    // $ANTLR start not
    // Sql.g:97:1: not : ( 'not' | 'NOT' ) ;
    public final void not() throws RecognitionException {
        try {
            // Sql.g:97:6: ( ( 'not' | 'NOT' ) )
            // Sql.g:97:7: ( 'not' | 'NOT' )
            {
            if ( (input.LA(1)>=77 && input.LA(1)<=78) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_not951);    throw mse;
            }


            }

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end not

    public static class like_return extends ParserRuleReturnScope {
    };

    // $ANTLR start like
    // Sql.g:98:1: like : ( 'like' | 'LIKE' ) ;
    public final like_return like() throws RecognitionException {
        like_return retval = new like_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:98:7: ( ( 'like' | 'LIKE' ) )
            // Sql.g:98:8: ( 'like' | 'LIKE' )
            {
            if ( (input.LA(1)>=79 && input.LA(1)<=80) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_like964);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end like


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA3 dfa3 = new DFA3(this);
    protected DFA5 dfa5 = new DFA5(this);
    protected DFA6 dfa6 = new DFA6(this);
    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA1_eotS =
        "\u00bc\uffff";
    static final String DFA1_eofS =
        "\3\uffff\1\10\6\uffff\2\10\1\uffff\1\10\10\uffff\2\10\3\uffff\1"+
        "\45\3\uffff\2\45\16\uffff\2\45\2\uffff\1\45\7\uffff\3\45\17\uffff"+
        "\1\45\3\uffff\2\45\3\uffff\3\45\4\uffff\1\45\22\uffff\2\45\3\uffff"+
        "\3\45\1\uffff\1\45\3\uffff\1\45\3\uffff\1\45\22\uffff\1\45\3\uffff"+
        "\3\45\2\uffff\2\45\13\uffff\1\45\4\uffff\1\45\2\uffff\1\45\5\uffff"+
        "\2\45\1\uffff\1\45";
    static final String DFA1_minS =
        "\1\77\3\4\1\24\3\4\2\uffff\6\4\2\24\17\4\1\12\3\4\2\uffff\1\10\2"+
        "\7\3\4\1\12\6\4\4\12\1\4\1\12\5\4\1\24\5\4\2\12\25\4\1\12\2\4\1"+
        "\12\3\4\1\12\1\10\2\7\1\4\2\12\1\10\2\7\6\4\1\12\4\4\1\12\1\4\3"+
        "\12\2\4\1\7\1\12\1\4\3\12\11\4\3\12\1\4\1\12\4\4\1\12\4\4\1\7\6"+
        "\4\2\12\1\10\2\7\4\4\1\7\2\12\1\4\2\12\2\4\1\12\1\4\1\12\1\7\4\4";
    static final String DFA1_maxS =
        "\1\102\2\36\1\106\1\76\1\106\2\36\2\uffff\2\106\1\36\1\106\1\36"+
        "\1\120\2\76\1\120\1\12\1\17\1\14\2\106\2\120\1\12\1\112\1\17\1\12"+
        "\1\14\2\112\1\12\1\112\2\36\2\uffff\4\12\1\20\1\112\1\12\3\112\1"+
        "\36\1\120\1\112\4\12\1\20\2\12\4\112\1\76\1\120\1\14\1\17\1\12\1"+
        "\112\2\12\3\20\1\12\1\20\2\112\2\120\1\14\2\112\1\17\2\12\3\112"+
        "\1\11\2\20\1\12\2\112\2\12\1\20\1\112\4\12\1\112\5\12\3\20\2\112"+
        "\1\20\2\12\3\112\1\12\1\112\3\12\1\112\2\11\1\12\1\112\3\12\1\20"+
        "\1\112\3\20\1\12\1\20\2\112\3\12\1\112\1\12\1\20\1\112\2\20\1\12"+
        "\3\112\2\11\2\112\3\20\1\112\5\12\1\112\1\20\1\112\2\11\2\12\1\112"+
        "\2\12\1\112\1\20\1\12\1\112\1\12\1\11\4\112";
    static final String DFA1_acceptS =
        "\10\uffff\1\2\1\3\33\uffff\1\1\1\4\u0095\uffff";
    static final String DFA1_specialS =
        "\u00bc\uffff}>";
    static final String[] DFA1_transitionS = {
            "\4\1",
            "\1\2\16\uffff\14\3",
            "\1\2\16\uffff\14\3",
            "\1\5\1\6\1\4\12\uffff\2\7\62\uffff\2\11",
            "\2\13\11\uffff\30\13\10\12",
            "\1\5\1\6\13\uffff\2\7\62\uffff\2\11",
            "\1\14\16\uffff\14\15",
            "\1\16\16\uffff\14\17",
            "",
            "",
            "\1\5\1\6\13\uffff\2\7\62\uffff\2\11",
            "\1\5\1\6\13\uffff\2\7\62\uffff\2\11",
            "\1\14\16\uffff\14\15",
            "\1\5\1\6\1\20\12\uffff\2\7\62\uffff\2\11",
            "\1\16\16\uffff\14\17",
            "\1\22\1\uffff\1\21\3\23\101\uffff\2\24\2\uffff\2\25",
            "\2\26\11\uffff\30\26\10\27",
            "\2\30\11\uffff\30\30\10\31",
            "\1\22\2\uffff\3\23\101\uffff\2\24\2\uffff\2\25",
            "\1\32\5\uffff\1\33",
            "\1\34\12\uffff\1\35",
            "\1\36\5\uffff\1\37\1\uffff\1\40",
            "\1\5\1\6\13\uffff\2\7\62\uffff\2\11",
            "\1\5\1\6\13\uffff\2\7\62\uffff\2\11",
            "\1\22\2\uffff\3\23\101\uffff\2\24\2\uffff\2\25",
            "\1\22\2\uffff\3\23\101\uffff\2\24\2\uffff\2\25",
            "\1\32\5\uffff\1\33",
            "\1\42\1\uffff\1\41\1\47\1\50\1\51\71\uffff\2\43\2\46\2\uffff"+
            "\2\44",
            "\1\34\12\uffff\1\35",
            "\1\52\5\uffff\1\53",
            "\1\36\5\uffff\1\37\1\uffff\1\40",
            "\1\54\1\uffff\1\55\3\uffff\1\37\1\uffff\1\40\66\uffff\2\43\2"+
            "\46\2\uffff\2\44",
            "\1\56\5\uffff\1\37\1\uffff\1\40\66\uffff\2\43\2\46\2\uffff\2"+
            "\44",
            "\1\57",
            "\1\56\5\uffff\1\60\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\61\16\uffff\14\62",
            "\1\61\16\uffff\14\62",
            "",
            "",
            "\1\65\1\64\1\63",
            "\1\66\2\uffff\1\63",
            "\1\67\2\uffff\1\63",
            "\1\52\5\uffff\1\53",
            "\1\70\1\72\1\71\11\uffff\1\73",
            "\1\56\5\uffff\1\74\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\75",
            "\1\56\76\uffff\2\43\2\46\2\uffff\2\44",
            "\1\56\2\uffff\1\47\1\50\1\51\71\uffff\2\43\2\46\2\uffff\2\44",
            "\1\76\2\uffff\1\47\1\50\1\51\71\uffff\2\43\2\46\2\uffff\2\44",
            "\1\61\16\uffff\14\62",
            "\1\100\1\uffff\1\77\3\103\101\uffff\2\102\2\uffff\2\101",
            "\1\104\1\uffff\1\105\4\uffff\1\106\67\uffff\2\43\2\46\2\uffff"+
            "\2\44",
            "\1\63",
            "\1\63",
            "\1\63",
            "\1\63",
            "\1\110\1\72\4\uffff\1\107\5\uffff\1\73",
            "\1\111",
            "\1\112\5\uffff\1\113",
            "\1\56\76\uffff\2\43\2\46\2\uffff\2\44",
            "\1\114\5\uffff\1\37\1\uffff\1\40\66\uffff\2\43\2\46\2\uffff"+
            "\2\44",
            "\1\56\5\uffff\1\37\1\uffff\1\40\66\uffff\2\43\2\46\2\uffff\2"+
            "\44",
            "\1\56\5\uffff\1\115\70\uffff\2\43\2\46\2\uffff\2\44",
            "\2\117\11\uffff\30\117\10\116",
            "\1\100\2\uffff\3\103\101\uffff\2\102\2\uffff\2\101",
            "\1\120\5\uffff\1\121\1\uffff\1\122",
            "\1\123\12\uffff\1\124",
            "\1\125\5\uffff\1\126",
            "\1\56\5\uffff\1\127\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\130",
            "\1\131",
            "\1\132\1\72\12\uffff\1\73",
            "\1\110\1\72\12\uffff\1\73",
            "\1\110\1\72\12\uffff\1\73",
            "\1\112\5\uffff\1\113",
            "\1\133\1\72\1\134\11\uffff\1\73",
            "\1\56\5\uffff\1\135\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\56\2\uffff\1\47\1\50\1\51\71\uffff\2\43\2\46\2\uffff\2\44",
            "\1\100\2\uffff\3\103\101\uffff\2\102\2\uffff\2\101",
            "\1\100\2\uffff\3\103\101\uffff\2\102\2\uffff\2\101",
            "\1\120\5\uffff\1\121\1\uffff\1\122",
            "\1\136\1\uffff\1\137\3\uffff\1\121\1\uffff\1\122\66\uffff\2"+
            "\43\2\46\2\uffff\2\44",
            "\1\56\5\uffff\1\121\1\uffff\1\122\66\uffff\2\43\2\46\2\uffff"+
            "\2\44",
            "\1\123\12\uffff\1\124",
            "\1\140\5\uffff\1\141",
            "\1\125\5\uffff\1\126",
            "\1\142\1\uffff\1\143\1\144\1\145\1\146\71\uffff\2\43\2\46\2"+
            "\uffff\2\44",
            "\1\147\6\uffff\1\106\67\uffff\2\43\2\46\2\uffff\2\44",
            "\1\56\6\uffff\1\106\67\uffff\2\43\2\46\2\uffff\2\44",
            "\1\150\1\uffff\1\151\1\152\1\153\1\154",
            "\1\110\1\72\4\uffff\1\155\5\uffff\1\73",
            "\1\110\1\72\4\uffff\1\156\5\uffff\1\73",
            "\1\157",
            "\1\56\5\uffff\1\37\1\uffff\1\40\66\uffff\2\43\2\46\2\uffff\2"+
            "\44",
            "\1\56\5\uffff\1\160\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\161",
            "\1\140\5\uffff\1\141",
            "\1\162\1\164\1\163\11\uffff\1\165",
            "\1\56\5\uffff\1\166\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\167",
            "\1\172\1\170\1\171",
            "\1\173\2\uffff\1\171",
            "\1\174\2\uffff\1\171",
            "\1\56\5\uffff\1\175\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\176",
            "\1\177",
            "\1\u0082\1\u0080\1\u0081",
            "\1\u0083\2\uffff\1\u0081",
            "\1\u0084\2\uffff\1\u0081",
            "\1\110\1\72\12\uffff\1\73",
            "\1\u0085\1\72\12\uffff\1\73",
            "\1\110\1\72\12\uffff\1\73",
            "\1\u0086\5\uffff\1\121\1\uffff\1\122\66\uffff\2\43\2\46\2\uffff"+
            "\2\44",
            "\1\56\5\uffff\1\121\1\uffff\1\122\66\uffff\2\43\2\46\2\uffff"+
            "\2\44",
            "\1\u0088\1\164\4\uffff\1\u0087\5\uffff\1\165",
            "\1\u0089",
            "\1\u008a\5\uffff\1\u008b",
            "\1\56\76\uffff\2\43\2\46\2\uffff\2\44",
            "\1\u008c\2\uffff\1\144\1\145\1\146\71\uffff\2\43\2\46\2\uffff"+
            "\2\44",
            "\1\56\2\uffff\1\144\1\145\1\146\71\uffff\2\43\2\46\2\uffff\2"+
            "\44",
            "\1\171",
            "\1\u008d\1\uffff\1\u008e\4\uffff\1\u008f\67\uffff\2\43\2\46"+
            "\2\uffff\2\44",
            "\1\171",
            "\1\171",
            "\1\171",
            "\1\56\6\uffff\1\106\67\uffff\2\43\2\46\2\uffff\2\44",
            "\1\u0090\2\uffff\1\152\1\153\1\154",
            "\1\152\1\153\1\154",
            "\1\u0081",
            "\1\u0091\1\uffff\1\u0092\4\uffff\1\106\67\uffff\2\43\2\46\2"+
            "\uffff\2\44",
            "\1\u0081",
            "\1\u0081",
            "\1\u0081",
            "\1\110\1\72\4\uffff\1\u0093\5\uffff\1\73",
            "\1\56\5\uffff\1\u0094\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\u0095\1\164\12\uffff\1\165",
            "\1\u0088\1\164\12\uffff\1\165",
            "\1\u0088\1\164\12\uffff\1\165",
            "\1\u008a\5\uffff\1\u008b",
            "\1\u0096\1\164\1\u0097\11\uffff\1\165",
            "\1\56\5\uffff\1\u0098\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\56\5\uffff\1\u0099\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "\1\56\5\uffff\1\u009d\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\u009e",
            "\1\110\1\72\12\uffff\1\73",
            "\1\56\5\uffff\1\121\1\uffff\1\122\66\uffff\2\43\2\46\2\uffff"+
            "\2\44",
            "\1\u0088\1\164\4\uffff\1\u009f\5\uffff\1\165",
            "\1\u0088\1\164\4\uffff\1\u00a0\5\uffff\1\165",
            "\1\u00a1",
            "\1\56\2\uffff\1\144\1\145\1\146\71\uffff\2\43\2\46\2\uffff\2"+
            "\44",
            "\1\u00a2\6\uffff\1\u008f\67\uffff\2\43\2\46\2\uffff\2\44",
            "\1\56\6\uffff\1\u008f\67\uffff\2\43\2\46\2\uffff\2\44",
            "\1\u00a3\1\uffff\1\u00a4\1\u00a5\1\u00a6\1\u00a7",
            "\1\152\1\153\1\154",
            "\1\u00a8\6\uffff\1\106\67\uffff\2\43\2\46\2\uffff\2\44",
            "\1\56\6\uffff\1\106\67\uffff\2\43\2\46\2\uffff\2\44",
            "\1\u0088\1\164\12\uffff\1\165",
            "\1\u00a9\1\164\12\uffff\1\165",
            "\1\u0088\1\164\12\uffff\1\165",
            "\1\56\5\uffff\1\u00aa\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ae\1\u00ad\1\u00af",
            "\1\u00b0\2\uffff\1\u00af",
            "\1\u00b1\2\uffff\1\u00af",
            "\1\56\5\uffff\1\u00b2\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\u0088\1\164\4\uffff\1\u00b3\5\uffff\1\165",
            "\1\56\6\uffff\1\u008f\67\uffff\2\43\2\46\2\uffff\2\44",
            "\1\u00b4\2\uffff\1\u00a5\1\u00a6\1\u00a7",
            "\1\u00a5\1\u00a6\1\u00a7",
            "\1\u00af",
            "\1\u00af",
            "\1\u00b5\1\uffff\1\u00b6\4\uffff\1\u008f\67\uffff\2\43\2\46"+
            "\2\uffff\2\44",
            "\1\u00af",
            "\1\u00af",
            "\1\56\6\uffff\1\106\67\uffff\2\43\2\46\2\uffff\2\44",
            "\1\u0088\1\164\12\uffff\1\165",
            "\1\u00b7",
            "\1\56\5\uffff\1\u00b8\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\u00b9",
            "\1\u00a5\1\u00a6\1\u00a7",
            "\1\u00ba\6\uffff\1\u008f\67\uffff\2\43\2\46\2\uffff\2\44",
            "\1\56\6\uffff\1\u008f\67\uffff\2\43\2\46\2\uffff\2\44",
            "\1\56\5\uffff\1\u00bb\70\uffff\2\43\2\46\2\uffff\2\44",
            "\1\56\6\uffff\1\u008f\67\uffff\2\43\2\46\2\uffff\2\44"
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
            return "20:1: stmt : ( select spaces selectList spaces where spaces constraintList | select spaces selectList | select spaces selectList spaces order spaces by spaces okw= keyword spaces | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces okw= keyword spaces );";
        }
    }
    static final String DFA3_eotS =
        "\4\uffff";
    static final String DFA3_eofS =
        "\1\2\3\uffff";
    static final String DFA3_minS =
        "\2\4\2\uffff";
    static final String DFA3_maxS =
        "\2\106\2\uffff";
    static final String DFA3_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA3_specialS =
        "\4\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\3\13\uffff\2\2\62\uffff\2\2",
            "\1\1\1\3\13\uffff\2\2\62\uffff\2\2",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "()* loopback of 33:4: ( spaces COMMA spaces kw= keyword )*";
        }
    }
    static final String DFA5_eotS =
        "\4\uffff";
    static final String DFA5_eofS =
        "\1\1\3\uffff";
    static final String DFA5_minS =
        "\1\4\1\uffff\1\4\1\uffff";
    static final String DFA5_maxS =
        "\1\112\1\uffff\1\112\1\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\2\1\uffff\1\1";
    static final String DFA5_specialS =
        "\4\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\2\76\uffff\2\3\2\1\2\uffff\2\3",
            "",
            "\1\2\76\uffff\2\3\2\1\2\uffff\2\3",
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
            return "()* loopback of 44:29: ( spaces rel= logicalOp spaces constraint )*";
        }
    }
    static final String DFA6_eotS =
        "\11\uffff";
    static final String DFA6_eofS =
        "\11\uffff";
    static final String DFA6_minS =
        "\1\23\2\4\1\uffff\1\24\2\uffff\2\4";
    static final String DFA6_maxS =
        "\1\36\2\120\1\uffff\1\76\2\uffff\2\120";
    static final String DFA6_acceptS =
        "\3\uffff\1\2\1\uffff\1\1\1\3\2\uffff";
    static final String DFA6_specialS =
        "\11\uffff}>";
    static final String[] DFA6_transitionS = {
            "\14\1",
            "\1\2\1\uffff\1\4\3\5\101\uffff\2\3\2\uffff\2\6",
            "\1\2\2\uffff\3\5\101\uffff\2\3\2\uffff\2\6",
            "",
            "\2\10\11\uffff\30\10\10\7",
            "",
            "",
            "\1\2\2\uffff\3\5\101\uffff\2\3\2\uffff\2\6",
            "\1\2\2\uffff\3\5\101\uffff\2\3\2\uffff\2\6"
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
            return "48:1: constraint : (kw= keyword spaces op= ( EQ | LT | GT ) spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= likeValue );";
        }
    }
    static final String DFA8_eotS =
        "\4\uffff";
    static final String DFA8_eofS =
        "\4\uffff";
    static final String DFA8_minS =
        "\2\4\2\uffff";
    static final String DFA8_maxS =
        "\2\20\2\uffff";
    static final String DFA8_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA8_specialS =
        "\4\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\1\1\3\12\uffff\1\2",
            "\1\1\1\3\12\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "()* loopback of 76:21: ( spaces COMMA spaces dotValue )*";
        }
    }
 

    public static final BitSet FOLLOW_select_in_stmt27 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_stmt29 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_selectList_in_stmt31 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt33 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_where_in_stmt35 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_stmt37 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_constraintList_in_stmt39 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt46 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_stmt48 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_selectList_in_stmt50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt55 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_stmt57 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_selectList_in_stmt59 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000060L});
    public static final BitSet FOLLOW_spaces_in_stmt61 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_order_in_stmt63 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000180L});
    public static final BitSet FOLLOW_spaces_in_stmt65 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_by_in_stmt67 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_stmt69 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_keyword_in_stmt74 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt80 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt85 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_stmt87 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_selectList_in_stmt89 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt91 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_where_in_stmt93 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_stmt95 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_constraintList_in_stmt97 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000060L});
    public static final BitSet FOLLOW_spaces_in_stmt99 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_order_in_stmt101 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000180L});
    public static final BitSet FOLLOW_spaces_in_stmt103 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_by_in_stmt105 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_stmt107 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_keyword_in_stmt112 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SPACE_in_spaces128 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_keyword_in_selectList143 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_selectList156 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_selectList160 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_selectList164 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_keyword_in_selectList171 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_entity_in_keyword196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword202 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword204 = new BitSet(new long[]{0x007FFFFF80300000L});
    public static final BitSet FOLLOW_attr_in_keyword206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword211 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword213 = new BitSet(new long[]{0x7F80000000000000L});
    public static final BitSet FOLLOW_funct_in_keyword215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_constraintList224 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000618L});
    public static final BitSet FOLLOW_spaces_in_constraintList228 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000618L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList235 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_constraintList243 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_constraint_in_constraintList245 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000618L});
    public static final BitSet FOLLOW_keyword_in_constraint258 = new BitSet(new long[]{0x0000000000000390L});
    public static final BitSet FOLLOW_spaces_in_constraint267 = new BitSet(new long[]{0x0000000000000380L});
    public static final BitSet FOLLOW_set_in_constraint274 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_spaces_in_constraint294 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_genValue_in_constraint301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint330 = new BitSet(new long[]{0x0000000000000010L,0x0000000000001800L});
    public static final BitSet FOLLOW_spaces_in_constraint339 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_in_in_constraint346 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_constraint357 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_constraint359 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_spaces_in_constraint363 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_valueList_in_constraint369 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_constraint377 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_constraint381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint407 = new BitSet(new long[]{0x0000000000000010L,0x0000000000018000L});
    public static final BitSet FOLLOW_spaces_in_constraint416 = new BitSet(new long[]{0x0000000000000000L,0x0000000000018000L});
    public static final BitSet FOLLOW_like_in_constraint423 = new BitSet(new long[]{0x0000000000001410L});
    public static final BitSet FOLLOW_spaces_in_constraint432 = new BitSet(new long[]{0x0000000000001400L});
    public static final BitSet FOLLOW_likeValue_in_constraint439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue495 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue497 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue505 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue507 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue515 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue517 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue519 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue521 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_valueList531 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_valueList535 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_valueList537 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_spaces_in_valueList539 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dotValue_in_valueList541 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_EQ_in_compOpt552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt573 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_GT_in_compOpt576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt583 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_LT_in_compOpt586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt593 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EQ_in_compOpt596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt603 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EQ_in_compOpt606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue618 = new BitSet(new long[]{0x0000000000000380L});
    public static final BitSet FOLLOW_compOpt_in_genValue620 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dotValue_in_genValue622 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_AMP_in_genValue625 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dotValue_in_genValue627 = new BitSet(new long[]{0x0000000000000380L});
    public static final BitSet FOLLOW_compOpt_in_genValue629 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dotValue_in_genValue631 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_dotValue_in_likeValue641 = new BitSet(new long[]{0x0000000000001402L});
    public static final BitSet FOLLOW_STAR_in_likeValue644 = new BitSet(new long[]{0x0000000000001402L});
    public static final BitSet FOLLOW_and_in_logicalOp653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_order899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_by912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_like964 = new BitSet(new long[]{0x0000000000000002L});

}