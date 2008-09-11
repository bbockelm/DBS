package dbs.search.parser;
// $ANTLR 3.0.1 Sql.g 2008-09-11 13:21:17


import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SPACE", "COMMA", "DOT", "VALUE", "EQ", "LT", "GT", "AMP", "NL", "WS", "'('", "')'", "'WHERE'", "'where'", "'in'", "'ads'", "'dataset'", "'release'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'lumi'", "'dq'", "'ilumi'", "'phygrp'", "'group'", "'pset'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numlss'", "'size'", "'count'", "'status'", "'type'", "'id'", "'parent'", "'child'", "'tier'", "'def'", "'evnum'", "'era'", "'tag'", "'numruns()'", "'numfiles()'", "'dataquality()'", "'latest()'", "'parentrelease()'", "'childrelease()'", "'intluminosity()'", "'findevents()'", "'select'", "'SELECT'", "'find'", "'FIND'", "'and'", "'AND'", "'order'", "'ORDER'", "'by'", "'BY'", "'or'", "'OR'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'", "'COUNT'", "'sum'", "'SUM'"
    };
    public static final int COMMA=5;
    public static final int SPACE=4;
    public static final int NL=12;
    public static final int WS=13;
    public static final int EOF=-1;
    public static final int EQ=8;
    public static final int AMP=11;
    public static final int LT=9;
    public static final int GT=10;
    public static final int VALUE=7;
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
    // Sql.g:20:1: stmt : ( select spaces selectList spaces where spaces constraintList spaces | select spaces selectList spaces | select spaces selectList spaces order spaces by spaces orderList spaces | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces orderList spaces );
    public final void stmt() throws RecognitionException {
        try {
            // Sql.g:20:6: ( select spaces selectList spaces where spaces constraintList spaces | select spaces selectList spaces | select spaces selectList spaces order spaces by spaces orderList spaces | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces orderList spaces )
            int alt1=4;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // Sql.g:20:8: select spaces selectList spaces where spaces constraintList spaces
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

                    pushFollow(FOLLOW_spaces_in_stmt41);
                    spaces();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:21:4: select spaces selectList spaces
                    {
                    pushFollow(FOLLOW_select_in_stmt47);
                    select();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt49);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_selectList_in_stmt51);
                    selectList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt53);
                    spaces();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Sql.g:22:4: select spaces selectList spaces order spaces by spaces orderList spaces
                    {
                    pushFollow(FOLLOW_select_in_stmt58);
                    select();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt60);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_selectList_in_stmt62);
                    selectList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt64);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_order_in_stmt66);
                    order();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt68);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_by_in_stmt70);
                    by();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt72);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_orderList_in_stmt74);
                    orderList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt76);
                    spaces();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Sql.g:23:4: select spaces selectList spaces where spaces constraintList spaces order spaces by spaces orderList spaces
                    {
                    pushFollow(FOLLOW_select_in_stmt81);
                    select();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt83);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_selectList_in_stmt85);
                    selectList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt87);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_where_in_stmt89);
                    where();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt91);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_constraintList_in_stmt93);
                    constraintList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt95);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_order_in_stmt97);
                    order();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt99);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_by_in_stmt101);
                    by();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt103);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_orderList_in_stmt105);
                    orderList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt107);
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
    // Sql.g:26:1: spaces : ( SPACE )* ;
    public final void spaces() throws RecognitionException {
        try {
            // Sql.g:26:8: ( ( SPACE )* )
            // Sql.g:26:10: ( SPACE )*
            {
            // Sql.g:26:10: ( SPACE )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==SPACE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Sql.g:26:11: SPACE
            	    {
            	    match(input,SPACE,FOLLOW_SPACE_in_spaces117); 

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


    // $ANTLR start orderList
    // Sql.g:28:1: orderList : okw= keyword ( spaces COMMA spaces okw= keyword )* ;
    public final void orderList() throws RecognitionException {
        keyword_return okw = null;


        try {
            // Sql.g:28:11: (okw= keyword ( spaces COMMA spaces okw= keyword )* )
            // Sql.g:28:12: okw= keyword ( spaces COMMA spaces okw= keyword )*
            {
            pushFollow(FOLLOW_keyword_in_orderList130);
            okw=keyword();
            _fsp--;

            okws.add(input.toString(okw.start,okw.stop));
            // Sql.g:29:4: ( spaces COMMA spaces okw= keyword )*
            loop3:
            do {
                int alt3=2;
                alt3 = dfa3.predict(input);
                switch (alt3) {
            	case 1 :
            	    // Sql.g:30:3: spaces COMMA spaces okw= keyword
            	    {
            	    pushFollow(FOLLOW_spaces_in_orderList143);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_orderList147); 
            	    pushFollow(FOLLOW_spaces_in_orderList151);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_keyword_in_orderList158);
            	    okw=keyword();
            	    _fsp--;

            	    okws.add(input.toString(okw.start,okw.stop));

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
    // $ANTLR end orderList


    // $ANTLR start selectList
    // Sql.g:36:1: selectList : kw= keyword ( spaces COMMA spaces kw= keyword )* ;
    public final void selectList() throws RecognitionException {
        keyword_return kw = null;


        try {
            // Sql.g:36:12: (kw= keyword ( spaces COMMA spaces kw= keyword )* )
            // Sql.g:36:13: kw= keyword ( spaces COMMA spaces kw= keyword )*
            {
            pushFollow(FOLLOW_keyword_in_selectList184);
            kw=keyword();
            _fsp--;

            kws.add(input.toString(kw.start,kw.stop));
            // Sql.g:37:4: ( spaces COMMA spaces kw= keyword )*
            loop4:
            do {
                int alt4=2;
                alt4 = dfa4.predict(input);
                switch (alt4) {
            	case 1 :
            	    // Sql.g:38:3: spaces COMMA spaces kw= keyword
            	    {
            	    pushFollow(FOLLOW_spaces_in_selectList197);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_selectList201); 
            	    pushFollow(FOLLOW_spaces_in_selectList205);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_keyword_in_selectList212);
            	    kw=keyword();
            	    _fsp--;

            	    kws.add(input.toString(kw.start,kw.stop));

            	    }
            	    break;

            	default :
            	    break loop4;
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
    // Sql.g:44:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );
    public final keyword_return keyword() throws RecognitionException {
        keyword_return retval = new keyword_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:44:9: ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' )
            int alt5=5;
            switch ( input.LA(1) ) {
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
                {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==DOT) ) {
                    int LA5_4 = input.LA(3);

                    if ( ((LA5_4>=20 && LA5_4<=21)||(LA5_4>=34 && LA5_4<=58)) ) {
                        alt5=2;
                    }
                    else if ( ((LA5_4>=59 && LA5_4<=66)) ) {
                        alt5=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("44:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 5, 4, input);

                        throw nvae;
                    }
                }
                else if ( (LA5_1==EOF||(LA5_1>=SPACE && LA5_1<=COMMA)||(LA5_1>=EQ && LA5_1<=GT)||(LA5_1>=16 && LA5_1<=18)||(LA5_1>=73 && LA5_1<=74)||LA5_1==79||(LA5_1>=82 && LA5_1<=83)) ) {
                    alt5=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("44:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 5, 1, input);

                    throw nvae;
                }
                }
                break;
            case 48:
            case 84:
                {
                alt5=4;
                }
                break;
            case 85:
            case 86:
                {
                alt5=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("44:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // Sql.g:44:11: entity
                    {
                    pushFollow(FOLLOW_entity_in_keyword237);
                    entity();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:45:4: entity DOT attr
                    {
                    pushFollow(FOLLOW_entity_in_keyword243);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword245); 
                    pushFollow(FOLLOW_attr_in_keyword247);
                    attr();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Sql.g:46:4: entity DOT funct
                    {
                    pushFollow(FOLLOW_entity_in_keyword252);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword254); 
                    pushFollow(FOLLOW_funct_in_keyword256);
                    funct();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Sql.g:47:4: count spaces '(' spaces entity spaces ')'
                    {
                    pushFollow(FOLLOW_count_in_keyword261);
                    count();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword263);
                    spaces();
                    _fsp--;

                    match(input,14,FOLLOW_14_in_keyword265); 
                    pushFollow(FOLLOW_spaces_in_keyword267);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_entity_in_keyword269);
                    entity();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword271);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_keyword273); 

                    }
                    break;
                case 5 :
                    // Sql.g:48:4: sum spaces '(' spaces entity DOT attr spaces ')'
                    {
                    pushFollow(FOLLOW_sum_in_keyword278);
                    sum();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword280);
                    spaces();
                    _fsp--;

                    match(input,14,FOLLOW_14_in_keyword282); 
                    pushFollow(FOLLOW_spaces_in_keyword284);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_entity_in_keyword286);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword288); 
                    pushFollow(FOLLOW_attr_in_keyword290);
                    attr();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword292);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_keyword294); 

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
    // Sql.g:50:1: constraintList : constraint ( spaces rel= logicalOp spaces constraint )* ;
    public final void constraintList() throws RecognitionException {
        logicalOp_return rel = null;


        try {
            // Sql.g:50:16: ( constraint ( spaces rel= logicalOp spaces constraint )* )
            // Sql.g:50:18: constraint ( spaces rel= logicalOp spaces constraint )*
            {
            pushFollow(FOLLOW_constraint_in_constraintList303);
            constraint();
            _fsp--;

            // Sql.g:50:29: ( spaces rel= logicalOp spaces constraint )*
            loop6:
            do {
                int alt6=2;
                alt6 = dfa6.predict(input);
                switch (alt6) {
            	case 1 :
            	    // Sql.g:50:31: spaces rel= logicalOp spaces constraint
            	    {
            	    pushFollow(FOLLOW_spaces_in_constraintList307);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_logicalOp_in_constraintList314);
            	    rel=logicalOp();
            	    _fsp--;

            	     constraints.add(input.toString(rel.start,rel.stop));
            	    pushFollow(FOLLOW_spaces_in_constraintList322);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_constraint_in_constraintList324);
            	    constraint();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
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
    // Sql.g:54:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue );
    public final void constraint() throws RecognitionException {
        keyword_return kw = null;

        compOpt_return op = null;

        genValue_return val = null;

        in_return op1 = null;

        valueList_return val1 = null;

        like_return op2 = null;

        dotValue_return val2 = null;


        try {
            // Sql.g:54:12: (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue )
            int alt7=3;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // Sql.g:54:14: kw= keyword spaces op= compOpt spaces val= genValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint337);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint346);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_compOpt_in_constraint353);
                    op=compOpt();
                    _fsp--;

                    c.setOp(input.toString(op.start,op.stop));
                    pushFollow(FOLLOW_spaces_in_constraint363);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_genValue_in_constraint370);
                    val=genValue();
                    _fsp--;

                    c.setValue(input.toString(val.start,val.stop)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:60:2: kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')'
                    {
                    pushFollow(FOLLOW_keyword_in_constraint399);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint408);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_in_in_constraint415);
                    op1=in();
                    _fsp--;

                    c.setOp(input.toString(op1.start,op1.stop));
                    pushFollow(FOLLOW_spaces_in_constraint426);
                    spaces();
                    _fsp--;

                    match(input,14,FOLLOW_14_in_constraint428); 
                    pushFollow(FOLLOW_spaces_in_constraint432);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_valueList_in_constraint438);
                    val1=valueList();
                    _fsp--;

                    c.setValue(input.toString(val1.start,val1.stop)); constraints.add(c);
                    pushFollow(FOLLOW_spaces_in_constraint446);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_constraint450); 

                    }
                    break;
                case 3 :
                    // Sql.g:69:2: kw= keyword spaces op2= like spaces val2= dotValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint476);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint485);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_like_in_constraint492);
                    op2=like();
                    _fsp--;

                    c.setOp(input.toString(op2.start,op2.stop));
                    pushFollow(FOLLOW_spaces_in_constraint501);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_dotValue_in_constraint508);
                    val2=dotValue();
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
    // Sql.g:75:1: where : ( 'WHERE' | 'where' ) ;
    public final void where() throws RecognitionException {
        try {
            // Sql.g:75:7: ( ( 'WHERE' | 'where' ) )
            // Sql.g:75:8: ( 'WHERE' | 'where' )
            {
            if ( (input.LA(1)>=16 && input.LA(1)<=17) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_where537);    throw mse;
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

    public static class dotValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start dotValue
    // Sql.g:76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );
    public final dotValue_return dotValue() throws RecognitionException {
        dotValue_return retval = new dotValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:76:17: ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE )
            int alt8=23;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==VALUE) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA8_3 = input.LA(3);

                    if ( (LA8_3==VALUE) ) {
                        int LA8_6 = input.LA(4);

                        if ( (LA8_6==DOT) ) {
                            int LA8_9 = input.LA(5);

                            if ( (LA8_9==VALUE) ) {
                                int LA8_13 = input.LA(6);

                                if ( (LA8_13==DOT) ) {
                                    int LA8_16 = input.LA(7);

                                    if ( (LA8_16==VALUE) ) {
                                        int LA8_18 = input.LA(8);

                                        if ( (LA8_18==DOT) ) {
                                            int LA8_20 = input.LA(9);

                                            if ( (LA8_20==VALUE) ) {
                                                int LA8_22 = input.LA(10);

                                                if ( (LA8_22==DOT) ) {
                                                    int LA8_24 = input.LA(11);

                                                    if ( (LA8_24==VALUE) ) {
                                                        int LA8_26 = input.LA(12);

                                                        if ( (LA8_26==DOT) ) {
                                                            int LA8_28 = input.LA(13);

                                                            if ( (LA8_28==VALUE) ) {
                                                                int LA8_30 = input.LA(14);

                                                                if ( (LA8_30==DOT) ) {
                                                                    int LA8_32 = input.LA(15);

                                                                    if ( (LA8_32==VALUE) ) {
                                                                        int LA8_34 = input.LA(16);

                                                                        if ( (LA8_34==DOT) ) {
                                                                            int LA8_36 = input.LA(17);

                                                                            if ( (LA8_36==18) ) {
                                                                                alt8=17;
                                                                            }
                                                                            else if ( (LA8_36==VALUE) ) {
                                                                                int LA8_39 = input.LA(18);

                                                                                if ( (LA8_39==DOT) ) {
                                                                                    int LA8_40 = input.LA(19);

                                                                                    if ( (LA8_40==VALUE) ) {
                                                                                        int LA8_42 = input.LA(20);

                                                                                        if ( (LA8_42==DOT) ) {
                                                                                            alt8=21;
                                                                                        }
                                                                                        else if ( (LA8_42==EOF||(LA8_42>=SPACE && LA8_42<=COMMA)||(LA8_42>=EQ && LA8_42<=AMP)||LA8_42==15||(LA8_42>=71 && LA8_42<=74)||(LA8_42>=77 && LA8_42<=78)) ) {
                                                                                            alt8=20;
                                                                                        }
                                                                                        else {
                                                                                            NoViableAltException nvae =
                                                                                                new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 42, input);

                                                                                            throw nvae;
                                                                                        }
                                                                                    }
                                                                                    else if ( (LA8_40==18) ) {
                                                                                        alt8=19;
                                                                                    }
                                                                                    else {
                                                                                        NoViableAltException nvae =
                                                                                            new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 40, input);

                                                                                        throw nvae;
                                                                                    }
                                                                                }
                                                                                else if ( (LA8_39==EOF||(LA8_39>=SPACE && LA8_39<=COMMA)||(LA8_39>=EQ && LA8_39<=AMP)||LA8_39==15||(LA8_39>=71 && LA8_39<=74)||(LA8_39>=77 && LA8_39<=78)) ) {
                                                                                    alt8=18;
                                                                                }
                                                                                else {
                                                                                    NoViableAltException nvae =
                                                                                        new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 39, input);

                                                                                    throw nvae;
                                                                                }
                                                                            }
                                                                            else {
                                                                                NoViableAltException nvae =
                                                                                    new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 36, input);

                                                                                throw nvae;
                                                                            }
                                                                        }
                                                                        else if ( (LA8_34==EOF||(LA8_34>=SPACE && LA8_34<=COMMA)||(LA8_34>=EQ && LA8_34<=AMP)||LA8_34==15||(LA8_34>=71 && LA8_34<=74)||(LA8_34>=77 && LA8_34<=78)) ) {
                                                                            alt8=16;
                                                                        }
                                                                        else {
                                                                            NoViableAltException nvae =
                                                                                new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 34, input);

                                                                            throw nvae;
                                                                        }
                                                                    }
                                                                    else if ( (LA8_32==18) ) {
                                                                        alt8=15;
                                                                    }
                                                                    else {
                                                                        NoViableAltException nvae =
                                                                            new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 32, input);

                                                                        throw nvae;
                                                                    }
                                                                }
                                                                else if ( (LA8_30==EOF||(LA8_30>=SPACE && LA8_30<=COMMA)||(LA8_30>=EQ && LA8_30<=AMP)||LA8_30==15||(LA8_30>=71 && LA8_30<=74)||(LA8_30>=77 && LA8_30<=78)) ) {
                                                                    alt8=14;
                                                                }
                                                                else {
                                                                    NoViableAltException nvae =
                                                                        new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 30, input);

                                                                    throw nvae;
                                                                }
                                                            }
                                                            else if ( (LA8_28==18) ) {
                                                                alt8=13;
                                                            }
                                                            else {
                                                                NoViableAltException nvae =
                                                                    new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 28, input);

                                                                throw nvae;
                                                            }
                                                        }
                                                        else if ( (LA8_26==EOF||(LA8_26>=SPACE && LA8_26<=COMMA)||(LA8_26>=EQ && LA8_26<=AMP)||LA8_26==15||(LA8_26>=71 && LA8_26<=74)||(LA8_26>=77 && LA8_26<=78)) ) {
                                                            alt8=12;
                                                        }
                                                        else {
                                                            NoViableAltException nvae =
                                                                new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 26, input);

                                                            throw nvae;
                                                        }
                                                    }
                                                    else if ( (LA8_24==18) ) {
                                                        alt8=11;
                                                    }
                                                    else {
                                                        NoViableAltException nvae =
                                                            new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 24, input);

                                                        throw nvae;
                                                    }
                                                }
                                                else if ( (LA8_22==EOF||(LA8_22>=SPACE && LA8_22<=COMMA)||(LA8_22>=EQ && LA8_22<=AMP)||LA8_22==15||(LA8_22>=71 && LA8_22<=74)||(LA8_22>=77 && LA8_22<=78)) ) {
                                                    alt8=10;
                                                }
                                                else {
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 22, input);

                                                    throw nvae;
                                                }
                                            }
                                            else if ( (LA8_20==18) ) {
                                                alt8=9;
                                            }
                                            else {
                                                NoViableAltException nvae =
                                                    new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 20, input);

                                                throw nvae;
                                            }
                                        }
                                        else if ( (LA8_18==EOF||(LA8_18>=SPACE && LA8_18<=COMMA)||(LA8_18>=EQ && LA8_18<=AMP)||LA8_18==15||(LA8_18>=71 && LA8_18<=74)||(LA8_18>=77 && LA8_18<=78)) ) {
                                            alt8=8;
                                        }
                                        else {
                                            NoViableAltException nvae =
                                                new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 18, input);

                                            throw nvae;
                                        }
                                    }
                                    else if ( (LA8_16==18) ) {
                                        alt8=7;
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 16, input);

                                        throw nvae;
                                    }
                                }
                                else if ( (LA8_13==EOF||(LA8_13>=SPACE && LA8_13<=COMMA)||(LA8_13>=EQ && LA8_13<=AMP)||LA8_13==15||(LA8_13>=71 && LA8_13<=74)||(LA8_13>=77 && LA8_13<=78)) ) {
                                    alt8=6;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 13, input);

                                    throw nvae;
                                }
                            }
                            else if ( (LA8_9==18) ) {
                                alt8=5;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 9, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA8_6==EOF||(LA8_6>=SPACE && LA8_6<=COMMA)||(LA8_6>=EQ && LA8_6<=AMP)||LA8_6==15||(LA8_6>=71 && LA8_6<=74)||(LA8_6>=77 && LA8_6<=78)) ) {
                            alt8=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 6, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA8_3==18) ) {
                        alt8=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case SPACE:
                    {
                    int LA8_4 = input.LA(3);

                    if ( (LA8_4==VALUE) ) {
                        int LA8_8 = input.LA(4);

                        if ( (LA8_8==SPACE) ) {
                            int LA8_11 = input.LA(5);

                            if ( (LA8_11==VALUE) ) {
                                alt8=23;
                            }
                            else if ( (LA8_11==EOF||(LA8_11>=SPACE && LA8_11<=COMMA)||LA8_11==15||(LA8_11>=71 && LA8_11<=74)||(LA8_11>=77 && LA8_11<=78)) ) {
                                alt8=22;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 11, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA8_8==EOF||LA8_8==COMMA||(LA8_8>=EQ && LA8_8<=AMP)||LA8_8==15||(LA8_8>=71 && LA8_8<=74)||(LA8_8>=77 && LA8_8<=78)) ) {
                            alt8=22;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 8, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA8_4==EOF||(LA8_4>=SPACE && LA8_4<=COMMA)||LA8_4==15||(LA8_4>=71 && LA8_4<=74)||(LA8_4>=77 && LA8_4<=78)) ) {
                        alt8=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case EOF:
                case COMMA:
                case EQ:
                case LT:
                case GT:
                case AMP:
                case 15:
                case 71:
                case 72:
                case 73:
                case 74:
                case 77:
                case 78:
                    {
                    alt8=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 1, input);

                    throw nvae;
                }

            }
            else if ( (LA8_0==18) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("76:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // Sql.g:76:19: VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue557); 

                    }
                    break;
                case 2 :
                    // Sql.g:77:5: 'in'
                    {
                    match(input,18,FOLLOW_18_in_dotValue564); 

                    }
                    break;
                case 3 :
                    // Sql.g:78:5: VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue570); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue572); 
                    match(input,18,FOLLOW_18_in_dotValue574); 

                    }
                    break;
                case 4 :
                    // Sql.g:79:5: VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue580); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue582); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue584); 

                    }
                    break;
                case 5 :
                    // Sql.g:80:5: VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue590); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue592); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue594); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue596); 
                    match(input,18,FOLLOW_18_in_dotValue598); 

                    }
                    break;
                case 6 :
                    // Sql.g:81:5: VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue604); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue606); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue608); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue610); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue612); 

                    }
                    break;
                case 7 :
                    // Sql.g:82:5: VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue618); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue620); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue622); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue624); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue626); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue628); 
                    match(input,18,FOLLOW_18_in_dotValue630); 

                    }
                    break;
                case 8 :
                    // Sql.g:83:5: VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue636); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue638); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue640); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue642); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue644); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue646); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue648); 

                    }
                    break;
                case 9 :
                    // Sql.g:84:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue654); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue656); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue658); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue660); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue662); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue664); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue666); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue668); 
                    match(input,18,FOLLOW_18_in_dotValue670); 

                    }
                    break;
                case 10 :
                    // Sql.g:85:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue676); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue678); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue680); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue682); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue684); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue686); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue688); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue690); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue692); 

                    }
                    break;
                case 11 :
                    // Sql.g:86:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue698); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue700); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue702); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue704); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue706); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue708); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue710); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue712); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue714); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue716); 
                    match(input,18,FOLLOW_18_in_dotValue718); 

                    }
                    break;
                case 12 :
                    // Sql.g:87:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue724); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue726); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue728); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue730); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue732); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue734); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue736); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue738); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue740); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue742); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue744); 

                    }
                    break;
                case 13 :
                    // Sql.g:88:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue750); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue752); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue754); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue756); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue758); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue760); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue762); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue764); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue766); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue768); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue770); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue772); 
                    match(input,18,FOLLOW_18_in_dotValue774); 

                    }
                    break;
                case 14 :
                    // Sql.g:89:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue780); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue782); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue784); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue786); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue788); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue790); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue792); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue794); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue796); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue798); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue800); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue802); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue804); 

                    }
                    break;
                case 15 :
                    // Sql.g:90:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue810); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue812); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue814); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue816); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue818); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue820); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue822); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue824); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue826); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue828); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue830); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue832); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue834); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue836); 
                    match(input,18,FOLLOW_18_in_dotValue838); 

                    }
                    break;
                case 16 :
                    // Sql.g:91:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue844); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue846); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue848); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue850); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue852); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue854); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue856); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue858); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue860); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue862); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue864); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue866); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue868); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue870); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue872); 

                    }
                    break;
                case 17 :
                    // Sql.g:92:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue878); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue880); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue882); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue884); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue886); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue888); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue890); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue892); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue894); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue896); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue898); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue900); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue902); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue904); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue906); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue908); 
                    match(input,18,FOLLOW_18_in_dotValue910); 

                    }
                    break;
                case 18 :
                    // Sql.g:93:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue916); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue918); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue920); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue922); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue924); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue926); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue928); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue930); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue932); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue934); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue936); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue938); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue940); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue942); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue944); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue946); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue948); 

                    }
                    break;
                case 19 :
                    // Sql.g:94:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue955); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue957); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue959); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue961); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue963); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue965); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue967); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue969); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue971); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue973); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue975); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue977); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue979); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue981); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue983); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue985); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue987); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue989); 
                    match(input,18,FOLLOW_18_in_dotValue991); 

                    }
                    break;
                case 20 :
                    // Sql.g:95:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue997); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue999); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1001); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1003); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1005); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1007); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1009); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1011); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1013); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1015); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1017); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1019); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1021); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1023); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1025); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1027); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1029); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1031); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1033); 

                    }
                    break;
                case 21 :
                    // Sql.g:96:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1040); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1042); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1044); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1046); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1048); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1050); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1052); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1054); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1056); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1058); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1060); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1062); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1064); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1066); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1068); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1070); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1072); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1074); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1076); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1078); 
                    match(input,18,FOLLOW_18_in_dotValue1080); 

                    }
                    break;
                case 22 :
                    // Sql.g:97:5: VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1086); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1088); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1090); 

                    }
                    break;
                case 23 :
                    // Sql.g:98:5: VALUE SPACE VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1096); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1098); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1100); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1102); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1104); 

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
    // $ANTLR end dotValue

    public static class valueList_return extends ParserRuleReturnScope {
    };

    // $ANTLR start valueList
    // Sql.g:102:1: valueList : dotValue ( spaces COMMA spaces dotValue )* ;
    public final valueList_return valueList() throws RecognitionException {
        valueList_return retval = new valueList_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:102:11: ( dotValue ( spaces COMMA spaces dotValue )* )
            // Sql.g:102:12: dotValue ( spaces COMMA spaces dotValue )*
            {
            pushFollow(FOLLOW_dotValue_in_valueList1113);
            dotValue();
            _fsp--;

            // Sql.g:102:21: ( spaces COMMA spaces dotValue )*
            loop9:
            do {
                int alt9=2;
                alt9 = dfa9.predict(input);
                switch (alt9) {
            	case 1 :
            	    // Sql.g:102:23: spaces COMMA spaces dotValue
            	    {
            	    pushFollow(FOLLOW_spaces_in_valueList1117);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_valueList1119); 
            	    pushFollow(FOLLOW_spaces_in_valueList1121);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_dotValue_in_valueList1123);
            	    dotValue();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
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

    public static class compOpt_return extends ParserRuleReturnScope {
    };

    // $ANTLR start compOpt
    // Sql.g:104:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );
    public final compOpt_return compOpt() throws RecognitionException {
        compOpt_return retval = new compOpt_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:104:10: ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) )
            int alt10=8;
            switch ( input.LA(1) ) {
            case EQ:
                {
                switch ( input.LA(2) ) {
                case LT:
                    {
                    alt10=5;
                    }
                    break;
                case GT:
                    {
                    alt10=4;
                    }
                    break;
                case SPACE:
                case VALUE:
                case 18:
                    {
                    alt10=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("104:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );", 10, 1, input);

                    throw nvae;
                }

                }
                break;
            case LT:
                {
                switch ( input.LA(2) ) {
                case EQ:
                    {
                    alt10=6;
                    }
                    break;
                case SPACE:
                case VALUE:
                case 18:
                    {
                    alt10=2;
                    }
                    break;
                case GT:
                    {
                    alt10=8;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("104:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );", 10, 2, input);

                    throw nvae;
                }

                }
                break;
            case GT:
                {
                int LA10_3 = input.LA(2);

                if ( (LA10_3==EQ) ) {
                    alt10=7;
                }
                else if ( (LA10_3==SPACE||LA10_3==VALUE||LA10_3==18) ) {
                    alt10=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("104:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );", 10, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("104:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // Sql.g:104:11: ( EQ )
                    {
                    // Sql.g:104:11: ( EQ )
                    // Sql.g:104:12: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1135); 

                    }


                    }
                    break;
                case 2 :
                    // Sql.g:105:4: ( LT )
                    {
                    // Sql.g:105:4: ( LT )
                    // Sql.g:105:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1142); 

                    }


                    }
                    break;
                case 3 :
                    // Sql.g:106:4: ( GT )
                    {
                    // Sql.g:106:4: ( GT )
                    // Sql.g:106:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1149); 

                    }


                    }
                    break;
                case 4 :
                    // Sql.g:107:4: ( EQ ) ( GT )
                    {
                    // Sql.g:107:4: ( EQ )
                    // Sql.g:107:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1156); 

                    }

                    // Sql.g:107:8: ( GT )
                    // Sql.g:107:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1159); 

                    }


                    }
                    break;
                case 5 :
                    // Sql.g:108:4: ( EQ ) ( LT )
                    {
                    // Sql.g:108:4: ( EQ )
                    // Sql.g:108:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1166); 

                    }

                    // Sql.g:108:8: ( LT )
                    // Sql.g:108:9: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1169); 

                    }


                    }
                    break;
                case 6 :
                    // Sql.g:109:4: ( LT ) ( EQ )
                    {
                    // Sql.g:109:4: ( LT )
                    // Sql.g:109:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1176); 

                    }

                    // Sql.g:109:8: ( EQ )
                    // Sql.g:109:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1179); 

                    }


                    }
                    break;
                case 7 :
                    // Sql.g:110:4: ( GT ) ( EQ )
                    {
                    // Sql.g:110:4: ( GT )
                    // Sql.g:110:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1186); 

                    }

                    // Sql.g:110:8: ( EQ )
                    // Sql.g:110:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1189); 

                    }


                    }
                    break;
                case 8 :
                    // Sql.g:111:4: ( LT ) ( GT )
                    {
                    // Sql.g:111:4: ( LT )
                    // Sql.g:111:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1196); 

                    }

                    // Sql.g:111:8: ( GT )
                    // Sql.g:111:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1199); 

                    }


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
    // $ANTLR end compOpt

    public static class genValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start genValue
    // Sql.g:112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );
    public final genValue_return genValue() throws RecognitionException {
        genValue_return retval = new genValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:112:10: ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==VALUE) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA12_3 = input.LA(3);

                    if ( (LA12_3==VALUE) ) {
                        switch ( input.LA(4) ) {
                        case DOT:
                            {
                            int LA12_10 = input.LA(5);

                            if ( (LA12_10==VALUE) ) {
                                switch ( input.LA(6) ) {
                                case DOT:
                                    {
                                    int LA12_15 = input.LA(7);

                                    if ( (LA12_15==VALUE) ) {
                                        switch ( input.LA(8) ) {
                                        case DOT:
                                            {
                                            int LA12_18 = input.LA(9);

                                            if ( (LA12_18==VALUE) ) {
                                                switch ( input.LA(10) ) {
                                                case DOT:
                                                    {
                                                    int LA12_21 = input.LA(11);

                                                    if ( (LA12_21==VALUE) ) {
                                                        switch ( input.LA(12) ) {
                                                        case DOT:
                                                            {
                                                            int LA12_24 = input.LA(13);

                                                            if ( (LA12_24==VALUE) ) {
                                                                switch ( input.LA(14) ) {
                                                                case DOT:
                                                                    {
                                                                    int LA12_27 = input.LA(15);

                                                                    if ( (LA12_27==VALUE) ) {
                                                                        switch ( input.LA(16) ) {
                                                                        case DOT:
                                                                            {
                                                                            int LA12_30 = input.LA(17);

                                                                            if ( (LA12_30==VALUE) ) {
                                                                                switch ( input.LA(18) ) {
                                                                                case DOT:
                                                                                    {
                                                                                    int LA12_33 = input.LA(19);

                                                                                    if ( (LA12_33==VALUE) ) {
                                                                                        switch ( input.LA(20) ) {
                                                                                        case DOT:
                                                                                            {
                                                                                            int LA12_36 = input.LA(21);

                                                                                            if ( (LA12_36==18) ) {
                                                                                                int LA12_37 = input.LA(22);

                                                                                                if ( (LA12_37==EOF||LA12_37==SPACE||(LA12_37>=71 && LA12_37<=74)||(LA12_37>=77 && LA12_37<=78)) ) {
                                                                                                    alt12=1;
                                                                                                }
                                                                                                else if ( ((LA12_37>=EQ && LA12_37<=GT)) ) {
                                                                                                    alt12=2;
                                                                                                }
                                                                                                else {
                                                                                                    NoViableAltException nvae =
                                                                                                        new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 37, input);

                                                                                                    throw nvae;
                                                                                                }
                                                                                            }
                                                                                            else {
                                                                                                NoViableAltException nvae =
                                                                                                    new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 36, input);

                                                                                                throw nvae;
                                                                                            }
                                                                                            }
                                                                                            break;
                                                                                        case EOF:
                                                                                        case SPACE:
                                                                                        case 71:
                                                                                        case 72:
                                                                                        case 73:
                                                                                        case 74:
                                                                                        case 77:
                                                                                        case 78:
                                                                                            {
                                                                                            alt12=1;
                                                                                            }
                                                                                            break;
                                                                                        case EQ:
                                                                                        case LT:
                                                                                        case GT:
                                                                                            {
                                                                                            alt12=2;
                                                                                            }
                                                                                            break;
                                                                                        default:
                                                                                            NoViableAltException nvae =
                                                                                                new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 34, input);

                                                                                            throw nvae;
                                                                                        }

                                                                                    }
                                                                                    else if ( (LA12_33==18) ) {
                                                                                        int LA12_35 = input.LA(20);

                                                                                        if ( (LA12_35==EOF||LA12_35==SPACE||(LA12_35>=71 && LA12_35<=74)||(LA12_35>=77 && LA12_35<=78)) ) {
                                                                                            alt12=1;
                                                                                        }
                                                                                        else if ( ((LA12_35>=EQ && LA12_35<=GT)) ) {
                                                                                            alt12=2;
                                                                                        }
                                                                                        else {
                                                                                            NoViableAltException nvae =
                                                                                                new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 35, input);

                                                                                            throw nvae;
                                                                                        }
                                                                                    }
                                                                                    else {
                                                                                        NoViableAltException nvae =
                                                                                            new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 33, input);

                                                                                        throw nvae;
                                                                                    }
                                                                                    }
                                                                                    break;
                                                                                case EQ:
                                                                                case LT:
                                                                                case GT:
                                                                                    {
                                                                                    alt12=2;
                                                                                    }
                                                                                    break;
                                                                                case EOF:
                                                                                case SPACE:
                                                                                case 71:
                                                                                case 72:
                                                                                case 73:
                                                                                case 74:
                                                                                case 77:
                                                                                case 78:
                                                                                    {
                                                                                    alt12=1;
                                                                                    }
                                                                                    break;
                                                                                default:
                                                                                    NoViableAltException nvae =
                                                                                        new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 31, input);

                                                                                    throw nvae;
                                                                                }

                                                                            }
                                                                            else if ( (LA12_30==18) ) {
                                                                                int LA12_32 = input.LA(18);

                                                                                if ( (LA12_32==EOF||LA12_32==SPACE||(LA12_32>=71 && LA12_32<=74)||(LA12_32>=77 && LA12_32<=78)) ) {
                                                                                    alt12=1;
                                                                                }
                                                                                else if ( ((LA12_32>=EQ && LA12_32<=GT)) ) {
                                                                                    alt12=2;
                                                                                }
                                                                                else {
                                                                                    NoViableAltException nvae =
                                                                                        new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 32, input);

                                                                                    throw nvae;
                                                                                }
                                                                            }
                                                                            else {
                                                                                NoViableAltException nvae =
                                                                                    new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 30, input);

                                                                                throw nvae;
                                                                            }
                                                                            }
                                                                            break;
                                                                        case EOF:
                                                                        case SPACE:
                                                                        case 71:
                                                                        case 72:
                                                                        case 73:
                                                                        case 74:
                                                                        case 77:
                                                                        case 78:
                                                                            {
                                                                            alt12=1;
                                                                            }
                                                                            break;
                                                                        case EQ:
                                                                        case LT:
                                                                        case GT:
                                                                            {
                                                                            alt12=2;
                                                                            }
                                                                            break;
                                                                        default:
                                                                            NoViableAltException nvae =
                                                                                new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 28, input);

                                                                            throw nvae;
                                                                        }

                                                                    }
                                                                    else if ( (LA12_27==18) ) {
                                                                        int LA12_29 = input.LA(16);

                                                                        if ( (LA12_29==EOF||LA12_29==SPACE||(LA12_29>=71 && LA12_29<=74)||(LA12_29>=77 && LA12_29<=78)) ) {
                                                                            alt12=1;
                                                                        }
                                                                        else if ( ((LA12_29>=EQ && LA12_29<=GT)) ) {
                                                                            alt12=2;
                                                                        }
                                                                        else {
                                                                            NoViableAltException nvae =
                                                                                new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 29, input);

                                                                            throw nvae;
                                                                        }
                                                                    }
                                                                    else {
                                                                        NoViableAltException nvae =
                                                                            new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 27, input);

                                                                        throw nvae;
                                                                    }
                                                                    }
                                                                    break;
                                                                case EOF:
                                                                case SPACE:
                                                                case 71:
                                                                case 72:
                                                                case 73:
                                                                case 74:
                                                                case 77:
                                                                case 78:
                                                                    {
                                                                    alt12=1;
                                                                    }
                                                                    break;
                                                                case EQ:
                                                                case LT:
                                                                case GT:
                                                                    {
                                                                    alt12=2;
                                                                    }
                                                                    break;
                                                                default:
                                                                    NoViableAltException nvae =
                                                                        new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 25, input);

                                                                    throw nvae;
                                                                }

                                                            }
                                                            else if ( (LA12_24==18) ) {
                                                                int LA12_26 = input.LA(14);

                                                                if ( ((LA12_26>=EQ && LA12_26<=GT)) ) {
                                                                    alt12=2;
                                                                }
                                                                else if ( (LA12_26==EOF||LA12_26==SPACE||(LA12_26>=71 && LA12_26<=74)||(LA12_26>=77 && LA12_26<=78)) ) {
                                                                    alt12=1;
                                                                }
                                                                else {
                                                                    NoViableAltException nvae =
                                                                        new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 26, input);

                                                                    throw nvae;
                                                                }
                                                            }
                                                            else {
                                                                NoViableAltException nvae =
                                                                    new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 24, input);

                                                                throw nvae;
                                                            }
                                                            }
                                                            break;
                                                        case EQ:
                                                        case LT:
                                                        case GT:
                                                            {
                                                            alt12=2;
                                                            }
                                                            break;
                                                        case EOF:
                                                        case SPACE:
                                                        case 71:
                                                        case 72:
                                                        case 73:
                                                        case 74:
                                                        case 77:
                                                        case 78:
                                                            {
                                                            alt12=1;
                                                            }
                                                            break;
                                                        default:
                                                            NoViableAltException nvae =
                                                                new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 22, input);

                                                            throw nvae;
                                                        }

                                                    }
                                                    else if ( (LA12_21==18) ) {
                                                        int LA12_23 = input.LA(12);

                                                        if ( ((LA12_23>=EQ && LA12_23<=GT)) ) {
                                                            alt12=2;
                                                        }
                                                        else if ( (LA12_23==EOF||LA12_23==SPACE||(LA12_23>=71 && LA12_23<=74)||(LA12_23>=77 && LA12_23<=78)) ) {
                                                            alt12=1;
                                                        }
                                                        else {
                                                            NoViableAltException nvae =
                                                                new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 23, input);

                                                            throw nvae;
                                                        }
                                                    }
                                                    else {
                                                        NoViableAltException nvae =
                                                            new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 21, input);

                                                        throw nvae;
                                                    }
                                                    }
                                                    break;
                                                case EOF:
                                                case SPACE:
                                                case 71:
                                                case 72:
                                                case 73:
                                                case 74:
                                                case 77:
                                                case 78:
                                                    {
                                                    alt12=1;
                                                    }
                                                    break;
                                                case EQ:
                                                case LT:
                                                case GT:
                                                    {
                                                    alt12=2;
                                                    }
                                                    break;
                                                default:
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 19, input);

                                                    throw nvae;
                                                }

                                            }
                                            else if ( (LA12_18==18) ) {
                                                int LA12_20 = input.LA(10);

                                                if ( ((LA12_20>=EQ && LA12_20<=GT)) ) {
                                                    alt12=2;
                                                }
                                                else if ( (LA12_20==EOF||LA12_20==SPACE||(LA12_20>=71 && LA12_20<=74)||(LA12_20>=77 && LA12_20<=78)) ) {
                                                    alt12=1;
                                                }
                                                else {
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 20, input);

                                                    throw nvae;
                                                }
                                            }
                                            else {
                                                NoViableAltException nvae =
                                                    new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 18, input);

                                                throw nvae;
                                            }
                                            }
                                            break;
                                        case EOF:
                                        case SPACE:
                                        case 71:
                                        case 72:
                                        case 73:
                                        case 74:
                                        case 77:
                                        case 78:
                                            {
                                            alt12=1;
                                            }
                                            break;
                                        case EQ:
                                        case LT:
                                        case GT:
                                            {
                                            alt12=2;
                                            }
                                            break;
                                        default:
                                            NoViableAltException nvae =
                                                new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 16, input);

                                            throw nvae;
                                        }

                                    }
                                    else if ( (LA12_15==18) ) {
                                        int LA12_17 = input.LA(8);

                                        if ( ((LA12_17>=EQ && LA12_17<=GT)) ) {
                                            alt12=2;
                                        }
                                        else if ( (LA12_17==EOF||LA12_17==SPACE||(LA12_17>=71 && LA12_17<=74)||(LA12_17>=77 && LA12_17<=78)) ) {
                                            alt12=1;
                                        }
                                        else {
                                            NoViableAltException nvae =
                                                new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 17, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 15, input);

                                        throw nvae;
                                    }
                                    }
                                    break;
                                case EOF:
                                case SPACE:
                                case 71:
                                case 72:
                                case 73:
                                case 74:
                                case 77:
                                case 78:
                                    {
                                    alt12=1;
                                    }
                                    break;
                                case EQ:
                                case LT:
                                case GT:
                                    {
                                    alt12=2;
                                    }
                                    break;
                                default:
                                    NoViableAltException nvae =
                                        new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 12, input);

                                    throw nvae;
                                }

                            }
                            else if ( (LA12_10==18) ) {
                                int LA12_13 = input.LA(6);

                                if ( (LA12_13==EOF||LA12_13==SPACE||(LA12_13>=71 && LA12_13<=74)||(LA12_13>=77 && LA12_13<=78)) ) {
                                    alt12=1;
                                }
                                else if ( ((LA12_13>=EQ && LA12_13<=GT)) ) {
                                    alt12=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 13, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 10, input);

                                throw nvae;
                            }
                            }
                            break;
                        case EQ:
                        case LT:
                        case GT:
                            {
                            alt12=2;
                            }
                            break;
                        case EOF:
                        case SPACE:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 77:
                        case 78:
                            {
                            alt12=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 7, input);

                            throw nvae;
                        }

                    }
                    else if ( (LA12_3==18) ) {
                        int LA12_8 = input.LA(4);

                        if ( (LA12_8==EOF||LA12_8==SPACE||(LA12_8>=71 && LA12_8<=74)||(LA12_8>=77 && LA12_8<=78)) ) {
                            alt12=1;
                        }
                        else if ( ((LA12_8>=EQ && LA12_8<=GT)) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 8, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case SPACE:
                    {
                    int LA12_4 = input.LA(3);

                    if ( (LA12_4==VALUE) ) {
                        switch ( input.LA(4) ) {
                        case SPACE:
                            {
                            int LA12_11 = input.LA(5);

                            if ( (LA12_11==VALUE) ) {
                                int LA12_14 = input.LA(6);

                                if ( (LA12_14==EOF||LA12_14==SPACE||(LA12_14>=71 && LA12_14<=74)||(LA12_14>=77 && LA12_14<=78)) ) {
                                    alt12=1;
                                }
                                else if ( ((LA12_14>=EQ && LA12_14<=GT)) ) {
                                    alt12=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 14, input);

                                    throw nvae;
                                }
                            }
                            else if ( (LA12_11==EOF||LA12_11==SPACE||(LA12_11>=71 && LA12_11<=74)||(LA12_11>=77 && LA12_11<=78)) ) {
                                alt12=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 11, input);

                                throw nvae;
                            }
                            }
                            break;
                        case EOF:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 77:
                        case 78:
                            {
                            alt12=1;
                            }
                            break;
                        case EQ:
                        case LT:
                        case GT:
                            {
                            alt12=2;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 9, input);

                            throw nvae;
                        }

                    }
                    else if ( (LA12_4==EOF||LA12_4==SPACE||(LA12_4>=71 && LA12_4<=74)||(LA12_4>=77 && LA12_4<=78)) ) {
                        alt12=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case EOF:
                case 71:
                case 72:
                case 73:
                case 74:
                case 77:
                case 78:
                    {
                    alt12=1;
                    }
                    break;
                case EQ:
                case LT:
                case GT:
                    {
                    alt12=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 1, input);

                    throw nvae;
                }

            }
            else if ( (LA12_0==18) ) {
                int LA12_2 = input.LA(2);

                if ( (LA12_2==EOF||LA12_2==SPACE||(LA12_2>=71 && LA12_2<=74)||(LA12_2>=77 && LA12_2<=78)) ) {
                    alt12=1;
                }
                else if ( ((LA12_2>=EQ && LA12_2<=GT)) ) {
                    alt12=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("112:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // Sql.g:112:11: dotValue
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue1206);
                    dotValue();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:113:4: dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )*
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue1211);
                    dotValue();
                    _fsp--;

                    pushFollow(FOLLOW_compOpt_in_genValue1213);
                    compOpt();
                    _fsp--;

                    pushFollow(FOLLOW_dotValue_in_genValue1215);
                    dotValue();
                    _fsp--;

                    // Sql.g:113:30: ( AMP dotValue compOpt dotValue )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==AMP) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // Sql.g:113:31: AMP dotValue compOpt dotValue
                    	    {
                    	    match(input,AMP,FOLLOW_AMP_in_genValue1218); 
                    	    pushFollow(FOLLOW_dotValue_in_genValue1220);
                    	    dotValue();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_compOpt_in_genValue1222);
                    	    compOpt();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_dotValue_in_genValue1224);
                    	    dotValue();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
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

    public static class logicalOp_return extends ParserRuleReturnScope {
    };

    // $ANTLR start logicalOp
    // Sql.g:118:1: logicalOp : ( and | or ) ;
    public final logicalOp_return logicalOp() throws RecognitionException {
        logicalOp_return retval = new logicalOp_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:118:11: ( ( and | or ) )
            // Sql.g:118:12: ( and | or )
            {
            // Sql.g:118:12: ( and | or )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=71 && LA13_0<=72)) ) {
                alt13=1;
            }
            else if ( ((LA13_0>=77 && LA13_0<=78)) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("118:12: ( and | or )", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // Sql.g:118:13: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp1237);
                    and();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:118:17: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp1239);
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
    // Sql.g:119:1: entity : ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' ) ;
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:119:9: ( ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' ) )
            // Sql.g:119:11: ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' )
            {
            if ( (input.LA(1)>=19 && input.LA(1)<=33) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_entity1248);    throw mse;
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
    // Sql.g:120:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) ;
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:120:7: ( ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) )
            // Sql.g:120:8: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' )
            {
            if ( (input.LA(1)>=20 && input.LA(1)<=21)||(input.LA(1)>=34 && input.LA(1)<=58) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_attr1313);    throw mse;
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
    // Sql.g:121:1: funct : ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) ;
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:121:8: ( ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) )
            // Sql.g:121:9: ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' )
            {
            if ( (input.LA(1)>=59 && input.LA(1)<=66) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_funct1427);    throw mse;
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
    // Sql.g:122:1: select : ( 'select' | 'SELECT' | 'find' | 'FIND' ) ;
    public final void select() throws RecognitionException {
        try {
            // Sql.g:122:9: ( ( 'select' | 'SELECT' | 'find' | 'FIND' ) )
            // Sql.g:122:10: ( 'select' | 'SELECT' | 'find' | 'FIND' )
            {
            if ( (input.LA(1)>=67 && input.LA(1)<=70) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_select1465);    throw mse;
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
    // Sql.g:123:1: and : ( 'and' | 'AND' ) ;
    public final void and() throws RecognitionException {
        try {
            // Sql.g:123:6: ( ( 'and' | 'AND' ) )
            // Sql.g:123:7: ( 'and' | 'AND' )
            {
            if ( (input.LA(1)>=71 && input.LA(1)<=72) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_and1486);    throw mse;
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
    // Sql.g:124:1: order : ( 'order' | 'ORDER' ) ;
    public final void order() throws RecognitionException {
        try {
            // Sql.g:124:8: ( ( 'order' | 'ORDER' ) )
            // Sql.g:124:9: ( 'order' | 'ORDER' )
            {
            if ( (input.LA(1)>=73 && input.LA(1)<=74) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_order1499);    throw mse;
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
    // Sql.g:125:1: by : ( 'by' | 'BY' ) ;
    public final void by() throws RecognitionException {
        try {
            // Sql.g:125:5: ( ( 'by' | 'BY' ) )
            // Sql.g:125:6: ( 'by' | 'BY' )
            {
            if ( (input.LA(1)>=75 && input.LA(1)<=76) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_by1512);    throw mse;
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
    // Sql.g:126:1: or : ( 'or' | 'OR' ) ;
    public final void or() throws RecognitionException {
        try {
            // Sql.g:126:5: ( ( 'or' | 'OR' ) )
            // Sql.g:126:6: ( 'or' | 'OR' )
            {
            if ( (input.LA(1)>=77 && input.LA(1)<=78) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_or1525);    throw mse;
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
    // Sql.g:127:1: in : ( 'in' | 'IN' ) ;
    public final in_return in() throws RecognitionException {
        in_return retval = new in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:127:5: ( ( 'in' | 'IN' ) )
            // Sql.g:127:6: ( 'in' | 'IN' )
            {
            if ( input.LA(1)==18||input.LA(1)==79 ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_in1538);    throw mse;
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
    // Sql.g:128:1: not : ( 'not' | 'NOT' ) ;
    public final void not() throws RecognitionException {
        try {
            // Sql.g:128:6: ( ( 'not' | 'NOT' ) )
            // Sql.g:128:7: ( 'not' | 'NOT' )
            {
            if ( (input.LA(1)>=80 && input.LA(1)<=81) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_not1551);    throw mse;
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
    // Sql.g:129:1: like : ( 'like' | 'LIKE' ) ;
    public final like_return like() throws RecognitionException {
        like_return retval = new like_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:129:7: ( ( 'like' | 'LIKE' ) )
            // Sql.g:129:8: ( 'like' | 'LIKE' )
            {
            if ( (input.LA(1)>=82 && input.LA(1)<=83) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_like1564);    throw mse;
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


    // $ANTLR start count
    // Sql.g:130:1: count : ( 'count' | 'COUNT' ) ;
    public final void count() throws RecognitionException {
        try {
            // Sql.g:130:8: ( ( 'count' | 'COUNT' ) )
            // Sql.g:130:9: ( 'count' | 'COUNT' )
            {
            if ( input.LA(1)==48||input.LA(1)==84 ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_count1577);    throw mse;
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
    // $ANTLR end count


    // $ANTLR start sum
    // Sql.g:131:1: sum : ( 'sum' | 'SUM' ) ;
    public final void sum() throws RecognitionException {
        try {
            // Sql.g:131:6: ( ( 'sum' | 'SUM' ) )
            // Sql.g:131:7: ( 'sum' | 'SUM' )
            {
            if ( (input.LA(1)>=85 && input.LA(1)<=86) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_sum1590);    throw mse;
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
    // $ANTLR end sum


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA3 dfa3 = new DFA3(this);
    protected DFA4 dfa4 = new DFA4(this);
    protected DFA6 dfa6 = new DFA6(this);
    protected DFA7 dfa7 = new DFA7(this);
    protected DFA9 dfa9 = new DFA9(this);
    static final String DFA1_eotS =
        "\u0294\uffff";
    static final String DFA1_eofS =
        "\3\uffff\1\13\3\uffff\1\13\10\uffff\2\13\1\uffff\1\13\33\uffff\1"+
        "\13\1\uffff\2\13\11\uffff\2\126\2\uffff\2\126\12\uffff\1\13\5\uffff"+
        "\1\126\4\uffff\1\126\1\uffff\1\126\7\uffff\1\13\4\uffff\1\126\1"+
        "\uffff\3\126\4\uffff\5\126\7\uffff\1\13\7\uffff\1\126\14\uffff\1"+
        "\126\1\uffff\1\126\7\uffff\3\126\5\uffff\2\126\6\uffff\2\126\4\uffff"+
        "\6\126\15\uffff\1\126\4\uffff\1\126\5\uffff\1\126\10\uffff\2\126"+
        "\3\uffff\1\126\1\uffff\3\126\2\uffff\2\126\3\uffff\3\126\1\uffff"+
        "\5\126\5\uffff\2\126\20\uffff\1\126\1\uffff\1\126\2\uffff\1\126"+
        "\7\uffff\1\126\2\uffff\2\126\4\uffff\6\126\2\uffff\7\126\3\uffff"+
        "\3\126\15\uffff\1\126\12\uffff\1\126\2\uffff\2\126\3\uffff\5\126"+
        "\3\uffff\2\126\5\uffff\6\126\2\uffff\3\126\17\uffff\1\126\7\uffff"+
        "\2\126\2\uffff\4\126\3\uffff\11\126\2\uffff\2\126\15\uffff\1\126"+
        "\7\uffff\2\126\2\uffff\4\126\2\uffff\11\126\2\uffff\2\126\24\uffff"+
        "\2\126\2\uffff\4\126\2\uffff\10\126\2\uffff\2\126\24\uffff\2\126"+
        "\2\uffff\4\126\2\uffff\10\126\2\uffff\2\126\24\uffff\1\126\2\uffff"+
        "\4\126\2\uffff\7\126\2\uffff\2\126\22\uffff\4\126\2\uffff\5\126"+
        "\2\uffff\2\126\16\uffff\3\126\2\uffff\3\126\1\uffff\2\126\10\uffff"+
        "\1\126\2\uffff\3\126\4\uffff\2\126\1\uffff\1\126";
    static final String DFA1_minS =
        "\1\103\5\4\1\24\2\4\1\uffff\1\4\1\uffff\21\4\1\6\1\24\4\4\1\24\14"+
        "\4\1\24\5\4\1\6\22\4\1\6\3\4\1\24\3\4\1\7\3\4\2\uffff\1\4\1\7\1"+
        "\4\3\7\2\4\1\24\3\4\1\7\20\4\5\7\11\4\1\7\1\4\1\24\12\4\1\7\1\4"+
        "\1\7\1\4\1\7\2\4\1\7\1\4\1\7\26\4\1\6\7\4\1\10\6\4\1\7\3\4\1\7\1"+
        "\4\4\7\3\4\1\24\2\7\1\4\7\7\3\4\1\7\7\4\2\7\2\4\3\7\12\4\1\6\1\10"+
        "\2\7\2\4\3\7\5\4\1\7\6\4\1\7\1\4\1\7\1\4\2\7\3\4\5\7\1\4\2\7\2\4"+
        "\1\7\1\4\1\7\10\4\1\10\7\4\2\10\1\6\7\4\1\7\6\4\2\7\1\4\12\7\1\4"+
        "\2\7\2\4\2\7\6\4\1\6\1\10\3\4\5\7\6\4\1\6\1\10\7\4\1\7\5\4\5\7\1"+
        "\4\7\7\2\4\2\7\4\4\1\6\2\10\11\4\1\6\1\10\6\4\1\7\4\4\4\7\1\4\7"+
        "\7\2\4\2\7\4\4\1\6\1\10\11\4\1\6\1\10\6\4\1\7\4\4\13\7\2\4\2\7\4"+
        "\4\1\6\1\10\10\4\1\6\1\10\6\4\1\7\4\4\13\7\2\4\2\7\4\4\1\10\1\6"+
        "\10\4\1\6\1\10\6\4\1\22\4\4\5\7\1\22\3\7\1\22\1\7\1\4\2\7\4\4\1"+
        "\6\1\10\7\4\1\6\1\10\11\4\5\7\1\22\2\7\1\22\2\7\4\4\1\6\1\10\5\4"+
        "\1\10\1\6\7\4\1\22\3\7\2\22\1\7\1\22\1\7\3\4\1\6\1\10\3\4\1\10\5"+
        "\4\1\22\2\7\2\22\1\4\1\10\1\6\4\4\1\22\1\7\1\10\2\4\1\22\1\4";
    static final String DFA1_maxS =
        "\1\106\2\126\1\112\2\16\1\102\1\112\1\126\1\uffff\1\126\1\uffff"+
        "\1\16\1\41\1\16\1\41\2\112\1\126\1\112\2\16\1\126\1\123\2\16\1\41"+
        "\1\17\1\41\1\6\1\102\1\16\1\41\1\16\1\41\1\102\1\123\1\16\4\22\1"+
        "\16\1\41\1\16\1\41\1\17\1\112\1\72\2\112\1\41\1\17\1\41\1\6\2\123"+
        "\1\16\2\22\2\116\2\22\2\116\4\22\1\41\1\17\1\41\1\6\2\17\1\112\1"+
        "\72\1\22\2\17\1\22\1\116\2\126\2\uffff\1\116\1\22\1\116\3\22\1\17"+
        "\1\123\1\72\1\17\1\112\1\17\1\22\1\17\1\22\1\116\1\17\3\116\1\126"+
        "\1\123\2\16\5\116\5\22\2\17\1\112\3\17\1\22\2\17\1\22\1\116\1\102"+
        "\1\123\1\16\4\22\1\16\1\41\1\16\1\41\1\22\1\116\1\22\1\116\1\22"+
        "\1\17\1\123\1\22\1\17\1\22\1\17\3\116\2\123\1\16\2\22\2\116\6\22"+
        "\2\116\1\41\1\17\1\41\1\6\6\116\2\12\6\17\2\22\2\17\1\22\1\116\4"+
        "\22\1\116\1\17\1\123\1\72\2\22\1\116\1\7\6\22\1\17\2\116\1\22\1"+
        "\17\1\22\1\116\1\17\3\116\2\22\2\116\3\22\3\116\1\17\5\116\3\12"+
        "\2\22\2\116\3\22\5\17\1\22\3\17\1\22\2\17\1\22\1\116\1\22\1\116"+
        "\2\22\1\116\1\17\1\123\2\22\1\7\2\22\1\116\2\22\2\116\1\22\1\17"+
        "\1\22\1\17\6\116\2\12\7\116\3\12\3\116\4\17\1\22\6\17\2\22\1\116"+
        "\1\22\1\7\10\22\1\116\2\22\2\116\2\22\1\17\5\116\3\12\2\116\5\22"+
        "\6\116\2\12\3\116\4\17\1\22\5\17\3\22\1\7\1\22\1\116\7\22\2\116"+
        "\2\22\4\116\3\12\11\116\2\12\2\116\4\17\1\22\4\17\4\22\1\116\7\22"+
        "\2\116\2\22\4\116\2\12\11\116\2\12\2\116\4\17\1\22\4\17\13\22\2"+
        "\116\2\22\4\116\2\12\10\116\2\12\2\116\4\17\1\22\4\17\13\22\2\116"+
        "\2\22\4\116\2\12\10\116\2\12\2\116\4\17\1\22\4\17\13\22\1\116\2"+
        "\22\4\116\2\12\7\116\2\12\2\116\7\17\13\22\4\116\2\12\5\116\2\12"+
        "\2\116\5\17\11\22\3\116\2\12\3\116\1\12\2\116\3\17\5\22\1\116\2"+
        "\12\3\116\1\17\2\22\1\12\2\116\1\22\1\116";
    static final String DFA1_acceptS =
        "\11\uffff\1\3\1\uffff\1\2\111\uffff\1\4\1\1\u023d\uffff";
    static final String DFA1_specialS =
        "\u0294\uffff}>";
    static final String[] DFA1_transitionS = {
            "\4\1",
            "\1\2\16\uffff\17\3\16\uffff\1\4\43\uffff\1\4\2\5",
            "\1\2\16\uffff\17\3\16\uffff\1\4\43\uffff\1\4\2\5",
            "\1\7\1\10\1\6\11\uffff\2\12\67\uffff\2\11",
            "\1\14\11\uffff\1\15",
            "\1\16\11\uffff\1\17",
            "\2\21\14\uffff\31\21\10\20",
            "\1\7\1\10\12\uffff\2\12\67\uffff\2\11",
            "\1\22\16\uffff\17\23\16\uffff\1\24\43\uffff\1\24\2\25",
            "",
            "\1\26\16\uffff\17\27\16\uffff\1\30\43\uffff\1\30\2\31",
            "",
            "\1\14\11\uffff\1\15",
            "\1\32\16\uffff\17\33",
            "\1\16\11\uffff\1\17",
            "\1\34\16\uffff\17\35",
            "\1\7\1\10\12\uffff\2\12\67\uffff\2\11",
            "\1\7\1\10\12\uffff\2\12\67\uffff\2\11",
            "\1\22\16\uffff\17\23\16\uffff\1\24\43\uffff\1\24\2\25",
            "\1\7\1\10\1\36\11\uffff\2\12\67\uffff\2\11",
            "\1\37\11\uffff\1\40",
            "\1\41\11\uffff\1\42",
            "\1\26\16\uffff\17\27\16\uffff\1\30\43\uffff\1\30\2\31",
            "\1\44\1\uffff\1\43\1\uffff\1\47\1\50\1\51\7\uffff\1\45\74\uffff"+
            "\1\45\2\uffff\2\46",
            "\1\52\11\uffff\1\53",
            "\1\54\11\uffff\1\55",
            "\1\32\16\uffff\17\33",
            "\1\56\12\uffff\1\57",
            "\1\34\16\uffff\17\35",
            "\1\60",
            "\2\61\14\uffff\31\61\10\62",
            "\1\37\11\uffff\1\40",
            "\1\63\16\uffff\17\64",
            "\1\41\11\uffff\1\42",
            "\1\65\16\uffff\17\66",
            "\2\70\14\uffff\31\70\10\67",
            "\1\44\3\uffff\1\47\1\50\1\51\7\uffff\1\45\74\uffff\1\45\2\uffff"+
            "\2\46",
            "\1\71\11\uffff\1\72",
            "\1\73\2\uffff\1\74\12\uffff\1\75",
            "\1\77\2\uffff\1\100\1\uffff\1\76\1\102\7\uffff\1\101",
            "\1\77\2\uffff\1\100\1\103\1\uffff\1\104\7\uffff\1\101",
            "\1\77\2\uffff\1\100\1\105\11\uffff\1\101",
            "\1\52\11\uffff\1\53",
            "\1\106\16\uffff\17\107",
            "\1\54\11\uffff\1\55",
            "\1\110\16\uffff\17\111",
            "\1\56\12\uffff\1\57",
            "\1\7\1\10\12\uffff\2\12\67\uffff\2\11",
            "\2\112\14\uffff\31\112",
            "\1\7\1\10\12\uffff\2\12\67\uffff\2\11",
            "\1\7\1\10\12\uffff\2\12\67\uffff\2\11",
            "\1\63\16\uffff\17\64",
            "\1\113\12\uffff\1\114",
            "\1\65\16\uffff\17\66",
            "\1\115",
            "\1\44\3\uffff\1\47\1\50\1\51\7\uffff\1\45\74\uffff\1\45\2\uffff"+
            "\2\46",
            "\1\44\3\uffff\1\47\1\50\1\51\7\uffff\1\45\74\uffff\1\45\2\uffff"+
            "\2\46",
            "\1\71\11\uffff\1\72",
            "\1\116\2\uffff\1\117\12\uffff\1\120",
            "\1\73\2\uffff\1\74\12\uffff\1\75",
            "\1\122\1\uffff\1\121\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\77\2\uffff\1\100\12\uffff\1\101",
            "\1\77\2\uffff\1\100\12\uffff\1\101",
            "\1\131\1\uffff\1\130\1\uffff\1\132\1\133\1\134\74\uffff\2\123"+
            "\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\132\1\133\1\134\74\uffff\2\123\2\125\2\uffff"+
            "\2\124",
            "\1\77\2\uffff\1\100\12\uffff\1\101",
            "\1\77\2\uffff\1\100\12\uffff\1\101",
            "\1\77\2\uffff\1\100\12\uffff\1\101",
            "\1\77\2\uffff\1\100\12\uffff\1\101",
            "\1\106\16\uffff\17\107",
            "\1\135\12\uffff\1\136",
            "\1\110\16\uffff\17\111",
            "\1\137",
            "\1\140\12\uffff\1\141",
            "\1\113\12\uffff\1\114",
            "\1\7\1\10\12\uffff\2\12\67\uffff\2\11",
            "\2\142\14\uffff\31\142",
            "\1\116\2\uffff\1\117\12\uffff\1\120",
            "\1\144\1\145\1\143\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\150\12\uffff\1\151",
            "\1\127\2\uffff\1\152\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\153\16\uffff\17\154\16\uffff\1\155\43\uffff\1\155\2\156",
            "\1\153\16\uffff\17\154\16\uffff\1\155\43\uffff\1\155\2\156",
            "",
            "",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\157\12\uffff\1\160",
            "\1\127\2\uffff\1\161\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\162\1\uffff\1\164\1\165\7\uffff\1\163",
            "\1\162\1\166\1\uffff\1\167\7\uffff\1\163",
            "\1\162\1\170\11\uffff\1\163",
            "\1\135\12\uffff\1\136",
            "\1\44\3\uffff\1\47\1\50\1\51\7\uffff\1\45\74\uffff\1\45\2\uffff"+
            "\2\46",
            "\2\171\14\uffff\31\171",
            "\1\140\12\uffff\1\141",
            "\1\7\1\10\12\uffff\2\12\67\uffff\2\11",
            "\1\172\12\uffff\1\173",
            "\1\174\12\uffff\1\175",
            "\1\147\1\145\1\uffff\1\176\7\uffff\1\146",
            "\1\177\2\uffff\1\u0080\12\uffff\1\u0081",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\147\1\145\11\uffff\1\146",
            "\1\127\1\uffff\1\u0082\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0083\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\153\16\uffff\17\154\16\uffff\1\155\43\uffff\1\155\2\156",
            "\1\u0085\1\uffff\1\u0084\1\uffff\1\u0087\1\u0088\1\u0089\7\uffff"+
            "\1\u0086\74\uffff\1\u0086\2\uffff\2\u008a",
            "\1\u008b\11\uffff\1\u008c",
            "\1\u008d\11\uffff\1\u008e",
            "\1\127\1\uffff\1\u008f\1\uffff\1\132\1\133\1\134\74\uffff\2"+
            "\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\132\1\133\1\134\74\uffff\2\123\2\125\2\uffff"+
            "\2\124",
            "\1\u0090\3\uffff\1\132\1\133\1\134\74\uffff\2\123\2\125\2\uffff"+
            "\2\124",
            "\1\u0092\1\uffff\1\u0091\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\162\12\uffff\1\163",
            "\1\162\12\uffff\1\163",
            "\1\162\12\uffff\1\163",
            "\1\162\12\uffff\1\163",
            "\1\162\12\uffff\1\163",
            "\1\u0094\12\uffff\1\u0095",
            "\1\172\12\uffff\1\173",
            "\1\7\1\10\12\uffff\2\12\67\uffff\2\11",
            "\1\147\1\145\1\u0096\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\u0097\1\145\11\uffff\1\146",
            "\1\177\2\uffff\1\u0080\12\uffff\1\u0081",
            "\1\u0099\1\145\1\u0098\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\u009a\12\uffff\1\u009b",
            "\1\127\2\uffff\1\u009c\77\uffff\2\123\2\125\2\uffff\2\124",
            "\2\u009d\14\uffff\31\u009d\10\u009e",
            "\1\u0085\3\uffff\1\u0087\1\u0088\1\u0089\7\uffff\1\u0086\74"+
            "\uffff\1\u0086\2\uffff\2\u008a",
            "\1\u009f\11\uffff\1\u00a0",
            "\1\u00a1\2\uffff\1\u00a2\1\uffff\1\u00a5\1\u00a4\7\uffff\1\u00a3",
            "\1\u00a1\2\uffff\1\u00a2\1\u00a7\1\uffff\1\u00a6\7\uffff\1\u00a3",
            "\1\u00a1\2\uffff\1\u00a2\1\u00a8\11\uffff\1\u00a3",
            "\1\u00a9\2\uffff\1\u00aa\12\uffff\1\u00ab",
            "\1\u008b\11\uffff\1\u008c",
            "\1\u00ac\16\uffff\17\u00ad",
            "\1\u008d\11\uffff\1\u008e",
            "\1\u00ae\16\uffff\17\u00af",
            "\1\u00b0\12\uffff\1\u00b1",
            "\1\127\2\uffff\1\u00b2\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00b3\12\uffff\1\u00b4",
            "\1\127\2\uffff\1\u00b5\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00b6\12\uffff\1\u00b7",
            "\1\u0094\12\uffff\1\u0095",
            "\1\44\3\uffff\1\47\1\50\1\51\7\uffff\1\45\74\uffff\1\45\2\uffff"+
            "\2\46",
            "\1\u00b8\12\uffff\1\u00b9",
            "\1\147\1\145\1\uffff\1\u00ba\7\uffff\1\146",
            "\1\u00bb\12\uffff\1\u00bc",
            "\1\147\1\145\1\uffff\1\u00bd\7\uffff\1\146",
            "\1\127\1\uffff\1\u00be\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0085\3\uffff\1\u0087\1\u0088\1\u0089\7\uffff\1\u0086\74"+
            "\uffff\1\u0086\2\uffff\2\u008a",
            "\1\u0085\3\uffff\1\u0087\1\u0088\1\u0089\7\uffff\1\u0086\74"+
            "\uffff\1\u0086\2\uffff\2\u008a",
            "\1\u009f\11\uffff\1\u00a0",
            "\1\u00bf\2\uffff\1\u00c0\12\uffff\1\u00c1",
            "\1\u00a1\2\uffff\1\u00a2\12\uffff\1\u00a3",
            "\1\u00c3\1\uffff\1\u00c2\1\uffff\1\u00c4\1\u00c5\1\u00c6\74"+
            "\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\u00a1\2\uffff\1\u00a2\12\uffff\1\u00a3",
            "\1\u00a1\2\uffff\1\u00a2\12\uffff\1\u00a3",
            "\1\u00a1\2\uffff\1\u00a2\12\uffff\1\u00a3",
            "\1\u00a1\2\uffff\1\u00a2\12\uffff\1\u00a3",
            "\1\u00a1\2\uffff\1\u00a2\12\uffff\1\u00a3",
            "\1\u00a9\2\uffff\1\u00aa\12\uffff\1\u00ab",
            "\1\u00c8\1\uffff\1\u00c7\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00ac\16\uffff\17\u00ad",
            "\1\u00c9\12\uffff\1\u00ca",
            "\1\u00ae\16\uffff\17\u00af",
            "\1\u00cb",
            "\1\127\1\uffff\1\u00cc\1\uffff\1\132\1\133\1\134\74\uffff\2"+
            "\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\132\1\133\1\134\74\uffff\2\123\2\125\2\uffff"+
            "\2\124",
            "\1\127\3\uffff\1\132\1\133\1\134\74\uffff\2\123\2\125\2\uffff"+
            "\2\124",
            "\1\127\1\uffff\1\u00cd\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00ce\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00cf\1\uffff\1\u00d0\1\uffff\1\u00d1\1\u00d2\1\u00d3",
            "\1\u00d1\1\u00d2\1\u00d3",
            "\1\147\1\145\1\u00d4\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\147\1\145\1\u00d5\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\u00d6\1\145\11\uffff\1\146",
            "\1\u00d7\12\uffff\1\u00d8",
            "\1\u00bf\2\uffff\1\u00c0\12\uffff\1\u00c1",
            "\1\u00da\1\u00db\1\u00d9\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00de\12\uffff\1\u00df",
            "\1\127\2\uffff\1\u00e0\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00e3\1\uffff\1\u00e2\1\u00e1\7\uffff\1\u00e4",
            "\1\u00e3\1\u00e5\1\uffff\1\u00e6\7\uffff\1\u00e4",
            "\1\u00e3\1\u00e7\11\uffff\1\u00e4",
            "\1\u00e8\12\uffff\1\u00e9",
            "\1\127\2\uffff\1\u00ea\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00c9\12\uffff\1\u00ca",
            "\1\u0085\3\uffff\1\u0087\1\u0088\1\u0089\7\uffff\1\u0086\74"+
            "\uffff\1\u0086\2\uffff\2\u008a",
            "\2\u00eb\14\uffff\31\u00eb",
            "\1\u00ec\12\uffff\1\u00ed",
            "\1\u00ee\12\uffff\1\u00ef",
            "\1\127\2\uffff\1\u00f0\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00f1",
            "\1\u00f2\12\uffff\1\u00f3",
            "\1\u00f6\1\uffff\1\u00f5\1\u00f4\7\uffff\1\u00f7",
            "\1\u00f6\1\u00f9\1\uffff\1\u00f8\7\uffff\1\u00f7",
            "\1\u00f6\1\u00fa\11\uffff\1\u00f7",
            "\1\u00fb\12\uffff\1\u00fc",
            "\1\u00fd\12\uffff\1\u00fe",
            "\1\147\1\145\1\uffff\1\u00ff\7\uffff\1\146",
            "\1\127\1\uffff\1\u0100\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0101\12\uffff\1\u0102",
            "\1\u00dd\1\u00db\1\uffff\1\u0103\7\uffff\1\u00dc",
            "\1\u0104\2\uffff\1\u0105\12\uffff\1\u0106",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\127\1\uffff\1\u0107\1\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff"+
            "\2\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\u0108\3\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\u00e3\12\uffff\1\u00e4",
            "\1\u00e3\12\uffff\1\u00e4",
            "\1\u010a\1\uffff\1\u0109\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00e3\12\uffff\1\u00e4",
            "\1\u00e3\12\uffff\1\u00e4",
            "\1\u00e3\12\uffff\1\u00e4",
            "\1\127\1\uffff\1\u010c\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u010d\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u010e\12\uffff\1\u010f",
            "\1\127\1\uffff\1\u0110\1\uffff\1\132\1\133\1\134\74\uffff\2"+
            "\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\132\1\133\1\134\74\uffff\2\123\2\125\2\uffff"+
            "\2\124",
            "\1\127\1\uffff\1\u0111\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0112\3\uffff\1\u00d1\1\u00d2\1\u00d3",
            "\1\u0113\1\uffff\1\u00d1\1\u00d2\1\u00d3",
            "\1\u00d1\1\u00d2\1\u00d3",
            "\1\u00f6\12\uffff\1\u00f7",
            "\1\u00f6\12\uffff\1\u00f7",
            "\1\u0115\1\uffff\1\u0114\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00f6\12\uffff\1\u00f7",
            "\1\u00f6\12\uffff\1\u00f7",
            "\1\u00f6\12\uffff\1\u00f7",
            "\1\147\1\145\1\u0116\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\147\1\145\1\u0117\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\u0118\12\uffff\1\u0119",
            "\1\u00dd\1\u00db\1\u011a\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u011b\1\u00db\11\uffff\1\u00dc",
            "\1\u0104\2\uffff\1\u0105\12\uffff\1\u0106",
            "\1\u011d\1\u00db\1\u011c\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u011e\12\uffff\1\u011f",
            "\1\127\2\uffff\1\u0120\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0121\12\uffff\1\u0122",
            "\1\127\2\uffff\1\u0123\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0124\12\uffff\1\u0125",
            "\1\u0127\12\uffff\1\u0126",
            "\1\127\2\uffff\1\u0128\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u010e\12\uffff\1\u010f",
            "\1\u0085\3\uffff\1\u0087\1\u0088\1\u0089\7\uffff\1\u0086\74"+
            "\uffff\1\u0086\2\uffff\2\u008a",
            "\1\u0129\12\uffff\1\u012a",
            "\1\u012b\12\uffff\1\u012c",
            "\1\u012d",
            "\1\u012f\12\uffff\1\u012e",
            "\1\u0130\12\uffff\1\u0131",
            "\1\127\2\uffff\1\u0132\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0133\12\uffff\1\u0134",
            "\1\u0135\12\uffff\1\u0136",
            "\1\127\1\uffff\1\u0137\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0138\12\uffff\1\u0139",
            "\1\u00dd\1\u00db\1\uffff\1\u013a\7\uffff\1\u00dc",
            "\1\u013b\12\uffff\1\u013c",
            "\1\u00dd\1\u00db\1\uffff\1\u013d\7\uffff\1\u00dc",
            "\1\127\1\uffff\1\u013e\1\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff"+
            "\2\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\3\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\1\uffff\1\u013f\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0140\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0142\1\uffff\1\u0141\1\uffff\1\u0143\1\u0144\1\u0145",
            "\1\u0143\1\u0144\1\u0145",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u0146\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u0147\1\uffff\1\132\1\133\1\134\74\uffff\2"+
            "\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\132\1\133\1\134\74\uffff\2\123\2\125\2\uffff"+
            "\2\124",
            "\1\127\1\uffff\1\u0148\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00d1\1\u00d2\1\u00d3",
            "\1\u00d1\1\u00d2\1\u00d3",
            "\1\u0149\1\uffff\1\u00d1\1\u00d2\1\u00d3",
            "\1\127\1\uffff\1\u014a\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u014b\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\147\1\145\1\u014c\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\147\1\145\1\u014d\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\u014f\12\uffff\1\u014e",
            "\1\u00dd\1\u00db\1\u0150\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00dd\1\u00db\1\u0151\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u0152\1\u00db\11\uffff\1\u00dc",
            "\1\u0153\12\uffff\1\u0154",
            "\1\u0155\12\uffff\1\u0156",
            "\1\127\2\uffff\1\u0157\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0158\12\uffff\1\u0159",
            "\1\u015a",
            "\1\u015b\1\uffff\1\u015d\1\u015e\7\uffff\1\u015c",
            "\1\u015b\1\u015f\1\uffff\1\u0160\7\uffff\1\u015c",
            "\1\u015b\1\u0161\11\uffff\1\u015c",
            "\1\u0162\12\uffff\1\u0163",
            "\1\u0165\12\uffff\1\u0164",
            "\1\u0166\12\uffff\1\u0167",
            "\1\u0168\12\uffff\1\u0169",
            "\1\u016a\12\uffff\1\u016b",
            "\1\127\2\uffff\1\u016c\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u016d\12\uffff\1\u016e",
            "\1\u0170\12\uffff\1\u016f",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u0171\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0172\12\uffff\1\u0173",
            "\1\u0174\12\uffff\1\u0175",
            "\1\u00dd\1\u00db\1\uffff\1\u0176\7\uffff\1\u00dc",
            "\1\127\1\uffff\1\u0177\1\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff"+
            "\2\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\1\uffff\1\u0178\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0179\1\uffff\1\u0143\1\u0144\1\u0145",
            "\1\u0143\1\u0144\1\u0145",
            "\1\u017a\3\uffff\1\u0143\1\u0144\1\u0145",
            "\1\u017c\1\uffff\1\u017b\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u015b\12\uffff\1\u015c",
            "\1\u015b\12\uffff\1\u015c",
            "\1\u015b\12\uffff\1\u015c",
            "\1\u015b\12\uffff\1\u015c",
            "\1\u015b\12\uffff\1\u015c",
            "\1\127\1\uffff\1\u017d\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\132\1\133\1\134\74\uffff\2\123\2\125\2\uffff"+
            "\2\124",
            "\1\127\1\uffff\1\u017e\1\uffff\1\132\1\133\1\134\74\uffff\2"+
            "\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u017f\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0180\1\uffff\1\u00d1\1\u00d2\1\u00d3",
            "\1\u00d1\1\u00d2\1\u00d3",
            "\1\127\1\uffff\1\u0181\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\147\1\145\1\u0182\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\147\1\145\1\u0183\10\uffff\1\146",
            "\1\u0184\12\uffff\1\u0185",
            "\1\u00dd\1\u00db\1\u0186\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00dd\1\u00db\1\u0187\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u0188\12\uffff\1\u0189",
            "\1\u018a\12\uffff\1\u018b",
            "\1\u018c\12\uffff\1\u018d",
            "\1\u018e",
            "\1\u018f\12\uffff\1\u0190",
            "\1\127\2\uffff\1\u0191\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0192\12\uffff\1\u0193",
            "\1\u0194\12\uffff\1\u0195",
            "\1\u0196\12\uffff\1\u0197",
            "\1\u0198\12\uffff\1\u0199",
            "\1\u019a\12\uffff\1\u019b",
            "\1\u019c\12\uffff\1\u019d",
            "\1\u019e\12\uffff\1\u019f",
            "\1\127\1\uffff\1\u01a0\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u01a1\12\uffff\1\u01a2",
            "\1\u01a3\12\uffff\1\u01a4",
            "\1\127\1\uffff\1\u01a5\1\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff"+
            "\2\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\1\uffff\1\u01a6\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u01a7\1\uffff\1\u0143\1\u0144\1\u0145",
            "\1\u0143\1\u0144\1\u0145",
            "\1\u0143\1\u0144\1\u0145",
            "\1\127\1\uffff\1\u01a8\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u01a9\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u01aa\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u01ab\1\uffff\1\132\1\133\1\134\74\uffff\2"+
            "\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\132\1\133\1\134\74\uffff\2\123\2\125\2\uffff"+
            "\2\124",
            "\1\127\1\uffff\1\u01ac\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u01ad\1\uffff\1\u00d1\1\u00d2\1\u00d3",
            "\1\u00d1\1\u00d2\1\u00d3",
            "\1\127\1\uffff\1\u01ae\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\147\1\145\1\u01af\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\147\1\145\1\u01b0\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\u01b2\12\uffff\1\u01b1",
            "\1\u00dd\1\u00db\1\u01b3\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00dd\1\u00db\1\u01b4\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u01b5\12\uffff\1\u01b6",
            "\1\u01b7\12\uffff\1\u01b8",
            "\1\u01b9\12\uffff\1\u01ba",
            "\1\u01bb\12\uffff\1\u01bc",
            "\1\127\2\uffff\1\u01bd\77\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u01be\12\uffff\1\u01bf",
            "\1\u01c1\12\uffff\1\u01c0",
            "\1\u01c3\12\uffff\1\u01c2",
            "\1\u01c4\12\uffff\1\u01c5",
            "\1\u01c6\12\uffff\1\u01c7",
            "\1\u01c8\12\uffff\1\u01c9",
            "\1\u01ca\12\uffff\1\u01cb",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u01cc\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u01ce\12\uffff\1\u01cd",
            "\1\u01cf\12\uffff\1\u01d0",
            "\1\127\1\uffff\1\u01d1\1\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff"+
            "\2\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\1\uffff\1\u01d2\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u01d3\1\uffff\1\u0143\1\u0144\1\u0145",
            "\1\u0143\1\u0144\1\u0145",
            "\1\127\1\uffff\1\u01d4\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u01d5\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\132\1\133\1\134\74\uffff\2\123\2\125\2\uffff"+
            "\2\124",
            "\1\127\1\uffff\1\u01d6\1\uffff\1\132\1\133\1\134\74\uffff\2"+
            "\123\2\125\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u01d7\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\u01d8\1\uffff\1\u00d1\1\u00d2\1\u00d3",
            "\1\u00d1\1\u00d2\1\u00d3",
            "\1\127\1\uffff\1\u01d9\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\147\1\145\1\u01da\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\147\1\145\1\u01db\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\u01dd\12\uffff\1\u01dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00dd\1\u00db\1\u01de\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\1\u01df\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u01e1\12\uffff\1\u01e0",
            "\1\u01e2\12\uffff\1\u01e3",
            "\1\u01e4\12\uffff\1\u01e5",
            "\1\u01e6\12\uffff\1\u01e7",
            "\1\u01e8\12\uffff\1\u01e9",
            "\1\u01ea\12\uffff\1\u01eb",
            "\1\u01ed\12\uffff\1\u01ec",
            "\1\u01ee\12\uffff\1\u01ef",
            "\1\u01f0\12\uffff\1\u01f1",
            "\1\u01f3\12\uffff\1\u01f2",
            "\1\u01f4\12\uffff\1\u01f5",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u01f6\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u01f7\12\uffff\1\u01f8",
            "\1\u01f9\12\uffff\1\u01fa",
            "\1\127\3\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\1\uffff\1\u01fb\1\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff"+
            "\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u01fc\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u01fd\1\uffff\1\u0143\1\u0144\1\u0145",
            "\1\u0143\1\u0144\1\u0145",
            "\1\127\1\uffff\1\u01fe\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u01ff\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u0200\1\uffff\1\132\1\133\1\134\74\uffff\2"+
            "\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\132\1\133\1\134\74\uffff\2\123\2\125\2\uffff"+
            "\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u0201\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\u0202\1\uffff\1\u00d1\1\u00d2\1\u00d3",
            "\1\u00d1\1\u00d2\1\u00d3",
            "\1\127\1\uffff\1\u0203\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\147\1\145\11\uffff\1\146",
            "\1\147\1\145\1\u0204\10\uffff\1\146",
            "\1\147\1\145\1\u0205\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\u0207\12\uffff\1\u0206",
            "\1\u00dd\1\u00db\1\u0208\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00dd\1\u00db\1\u0209\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u020a\12\uffff\1\u020b",
            "\1\u020c\12\uffff\1\u020d",
            "\1\u020f\12\uffff\1\u020e",
            "\1\u0210\12\uffff\1\u0211",
            "\1\u0212\12\uffff\1\u0213",
            "\1\u0214\12\uffff\1\u0215",
            "\1\u0217\12\uffff\1\u0216",
            "\1\u0218\12\uffff\1\u0219",
            "\1\u021a\12\uffff\1\u021b",
            "\1\u021d\12\uffff\1\u021c",
            "\1\u021e\12\uffff\1\u021f",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u0220\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0221\12\uffff\1\u0222",
            "\1\u0223\12\uffff\1\u0224",
            "\1\127\1\uffff\1\u0225\1\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff"+
            "\2\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\1\uffff\1\u0226\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0143\1\u0144\1\u0145",
            "\1\u0227\1\uffff\1\u0143\1\u0144\1\u0145",
            "\1\127\1\uffff\1\u0228\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u0229\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u022a\1\uffff\1\132\1\133\1\134\74\uffff\2"+
            "\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\132\1\133\1\134\74\uffff\2\123\2\125\2\uffff"+
            "\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u022b\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\u022c\1\uffff\1\u00d1\1\u00d2\1\u00d3",
            "\1\u00d1\1\u00d2\1\u00d3",
            "\1\127\1\uffff\1\u022d\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\147\1\145\11\uffff\1\146",
            "\1\147\1\145\1\u022e\10\uffff\1\146",
            "\1\147\1\145\1\u022f\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\u0230",
            "\1\u00dd\1\u00db\1\u0231\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00dd\1\u00db\1\u0232\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u0234\12\uffff\1\u0233",
            "\1\u0235\12\uffff\1\u0236",
            "\1\u0237\12\uffff\1\u0238",
            "\1\u0239\12\uffff\1\u023a",
            "\1\u023b\12\uffff\1\u023c",
            "\1\u023d",
            "\1\u023f\12\uffff\1\u023e",
            "\1\u0240\12\uffff\1\u0241",
            "\1\u0242\12\uffff\1\u0243",
            "\1\u0244",
            "\1\u0245\12\uffff\1\u0246",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0247\12\uffff\1\u0248",
            "\1\u0249\12\uffff\1\u024a",
            "\1\127\3\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\1\uffff\1\u024b\1\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff"+
            "\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u024c\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u024d\1\uffff\1\u0143\1\u0144\1\u0145",
            "\1\u0143\1\u0144\1\u0145",
            "\1\127\1\uffff\1\u024e\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u024f\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\132\1\133\1\134\74\uffff\2\123\2\125\2\uffff"+
            "\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u0250\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\u0251\1\uffff\1\u00d1\1\u00d2\1\u00d3",
            "\1\u00d1\1\u00d2\1\u00d3",
            "\1\127\1\uffff\1\u0252\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\147\1\145\11\uffff\1\146",
            "\1\147\1\145\1\u0253\10\uffff\1\146",
            "\1\147\1\145\11\uffff\1\146",
            "\1\u00dd\1\u00db\1\u0254\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00dd\1\u00db\1\u0255\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u0256\12\uffff\1\u0257",
            "\1\u0258\12\uffff\1\u0259",
            "\1\u025a\12\uffff\1\u025b",
            "\1\u025c\12\uffff\1\u025d",
            "\1\u025e\12\uffff\1\u025f",
            "\1\u0260",
            "\1\u0262\12\uffff\1\u0261",
            "\1\u0263\12\uffff\1\u0264",
            "\1\u0265",
            "\1\u0267\12\uffff\1\u0266",
            "\1\u0269\12\uffff\1\u0268",
            "\1\127\1\uffff\1\u026a\1\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff"+
            "\2\123\2\125\2\uffff\2\124",
            "\1\127\3\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\1\uffff\1\u026b\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u026c\1\uffff\1\u0143\1\u0144\1\u0145",
            "\1\u0143\1\u0144\1\u0145",
            "\1\127\1\uffff\1\u026d\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u026e\100\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00d1\1\u00d2\1\u00d3",
            "\1\u026f\1\uffff\1\u00d1\1\u00d2\1\u00d3",
            "\1\127\1\uffff\1\u0270\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\147\1\145\11\uffff\1\146",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00dd\1\u00db\1\u0271\10\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00dd\1\u00db\1\u0272\10\uffff\1\u00dc",
            "\1\u0273",
            "\1\u0274\12\uffff\1\u0275",
            "\1\u0276\12\uffff\1\u0277",
            "\1\u0278\12\uffff\1\u0279",
            "\1\u027a",
            "\1\u027b",
            "\1\u027d\12\uffff\1\u027c",
            "\1\u027e",
            "\1\u0280\12\uffff\1\u027f",
            "\1\127\3\uffff\1\u00c4\1\u00c5\1\u00c6\74\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\1\uffff\1\u0281\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0282\1\uffff\1\u0143\1\u0144\1\u0145",
            "\1\u0143\1\u0144\1\u0145",
            "\1\127\1\uffff\1\u0283\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\102\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00d1\1\u00d2\1\u00d3",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u0284\4\uffff\1\u0093\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u00dd\1\u00db\1\u0285\10\uffff\1\u00dc",
            "\1\u0286",
            "\1\u0288\12\uffff\1\u0287",
            "\1\u028a\12\uffff\1\u0289",
            "\1\u028b",
            "\1\u028c",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u0143\1\u0144\1\u0145",
            "\1\u028d\1\uffff\1\u0143\1\u0144\1\u0145",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u028e\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\127\6\uffff\1\u0093\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\u00dd\1\u00db\11\uffff\1\u00dc",
            "\1\u028f",
            "\1\u0291\12\uffff\1\u0290",
            "\1\u0143\1\u0144\1\u0145",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124",
            "\1\127\1\uffff\1\u0292\4\uffff\1\u010b\73\uffff\2\123\2\125"+
            "\2\uffff\2\124",
            "\1\u0293",
            "\1\127\6\uffff\1\u010b\73\uffff\2\123\2\125\2\uffff\2\124"
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
            return "20:1: stmt : ( select spaces selectList spaces where spaces constraintList spaces | select spaces selectList spaces | select spaces selectList spaces order spaces by spaces orderList spaces | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces orderList spaces );";
        }
    }
    static final String DFA3_eotS =
        "\4\uffff";
    static final String DFA3_eofS =
        "\2\2\2\uffff";
    static final String DFA3_minS =
        "\2\4\2\uffff";
    static final String DFA3_maxS =
        "\2\5\2\uffff";
    static final String DFA3_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA3_specialS =
        "\4\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\3",
            "\1\1\1\3",
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
            return "()* loopback of 29:4: ( spaces COMMA spaces okw= keyword )*";
        }
    }
    static final String DFA4_eotS =
        "\4\uffff";
    static final String DFA4_eofS =
        "\2\2\2\uffff";
    static final String DFA4_minS =
        "\2\4\2\uffff";
    static final String DFA4_maxS =
        "\2\112\2\uffff";
    static final String DFA4_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA4_specialS =
        "\4\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\1\1\3\12\uffff\2\2\67\uffff\2\2",
            "\1\1\1\3\12\uffff\2\2\67\uffff\2\2",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "()* loopback of 37:4: ( spaces COMMA spaces kw= keyword )*";
        }
    }
    static final String DFA6_eotS =
        "\4\uffff";
    static final String DFA6_eofS =
        "\2\2\2\uffff";
    static final String DFA6_minS =
        "\2\4\2\uffff";
    static final String DFA6_maxS =
        "\2\116\2\uffff";
    static final String DFA6_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA6_specialS =
        "\4\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\1\102\uffff\2\3\2\2\2\uffff\2\3",
            "\1\1\102\uffff\2\3\2\2\2\uffff\2\3",
            "",
            ""
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
            return "()* loopback of 50:29: ( spaces rel= logicalOp spaces constraint )*";
        }
    }
    static final String DFA7_eotS =
        "\31\uffff";
    static final String DFA7_eofS =
        "\31\uffff";
    static final String DFA7_minS =
        "\1\23\4\4\2\uffff\1\24\1\uffff\11\4\1\6\2\4\1\24\3\4";
    static final String DFA7_maxS =
        "\1\126\1\123\2\16\1\123\2\uffff\1\102\1\uffff\1\16\1\41\1\16\1\41"+
        "\2\123\1\41\1\17\1\41\1\6\1\17\1\123\1\72\2\17\1\123";
    static final String DFA7_acceptS =
        "\5\uffff\1\1\1\2\1\uffff\1\3\20\uffff";
    static final String DFA7_specialS =
        "\31\uffff}>";
    static final String[] DFA7_transitionS = {
            "\17\1\16\uffff\1\2\43\uffff\1\2\2\3",
            "\1\4\1\uffff\1\7\1\uffff\3\5\7\uffff\1\6\74\uffff\1\6\2\uffff"+
            "\2\10",
            "\1\11\11\uffff\1\12",
            "\1\13\11\uffff\1\14",
            "\1\4\3\uffff\3\5\7\uffff\1\6\74\uffff\1\6\2\uffff\2\10",
            "",
            "",
            "\2\15\14\uffff\31\15\10\16",
            "",
            "\1\11\11\uffff\1\12",
            "\1\17\16\uffff\17\20",
            "\1\13\11\uffff\1\14",
            "\1\21\16\uffff\17\22",
            "\1\4\3\uffff\3\5\7\uffff\1\6\74\uffff\1\6\2\uffff\2\10",
            "\1\4\3\uffff\3\5\7\uffff\1\6\74\uffff\1\6\2\uffff\2\10",
            "\1\17\16\uffff\17\20",
            "\1\23\12\uffff\1\24",
            "\1\21\16\uffff\17\22",
            "\1\25",
            "\1\23\12\uffff\1\24",
            "\1\4\3\uffff\3\5\7\uffff\1\6\74\uffff\1\6\2\uffff\2\10",
            "\2\26\14\uffff\31\26",
            "\1\27\12\uffff\1\30",
            "\1\27\12\uffff\1\30",
            "\1\4\3\uffff\3\5\7\uffff\1\6\74\uffff\1\6\2\uffff\2\10"
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "54:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue );";
        }
    }
    static final String DFA9_eotS =
        "\4\uffff";
    static final String DFA9_eofS =
        "\4\uffff";
    static final String DFA9_minS =
        "\2\4\2\uffff";
    static final String DFA9_maxS =
        "\2\17\2\uffff";
    static final String DFA9_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA9_specialS =
        "\4\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\1\1\3\11\uffff\1\2",
            "\1\1\1\3\11\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "()* loopback of 102:21: ( spaces COMMA spaces dotValue )*";
        }
    }
 

    public static final BitSet FOLLOW_select_in_stmt27 = new BitSet(new long[]{0x00010003FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt29 = new BitSet(new long[]{0x00010003FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_selectList_in_stmt31 = new BitSet(new long[]{0x0000000000030010L});
    public static final BitSet FOLLOW_spaces_in_stmt33 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_where_in_stmt35 = new BitSet(new long[]{0x00010003FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt37 = new BitSet(new long[]{0x00010003FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_constraintList_in_stmt39 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt41 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt47 = new BitSet(new long[]{0x00010003FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt49 = new BitSet(new long[]{0x00010003FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_selectList_in_stmt51 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt53 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt58 = new BitSet(new long[]{0x00010003FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt60 = new BitSet(new long[]{0x00010003FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_selectList_in_stmt62 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000600L});
    public static final BitSet FOLLOW_spaces_in_stmt64 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000600L});
    public static final BitSet FOLLOW_order_in_stmt66 = new BitSet(new long[]{0x0000000000000010L,0x0000000000001800L});
    public static final BitSet FOLLOW_spaces_in_stmt68 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_by_in_stmt70 = new BitSet(new long[]{0x00010003FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt72 = new BitSet(new long[]{0x00010003FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_orderList_in_stmt74 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt76 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt81 = new BitSet(new long[]{0x00010003FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt83 = new BitSet(new long[]{0x00010003FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_selectList_in_stmt85 = new BitSet(new long[]{0x0000000000030010L});
    public static final BitSet FOLLOW_spaces_in_stmt87 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_where_in_stmt89 = new BitSet(new long[]{0x00010003FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt91 = new BitSet(new long[]{0x00010003FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_constraintList_in_stmt93 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000600L});
    public static final BitSet FOLLOW_spaces_in_stmt95 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000600L});
    public static final BitSet FOLLOW_order_in_stmt97 = new BitSet(new long[]{0x0000000000000010L,0x0000000000001800L});
    public static final BitSet FOLLOW_spaces_in_stmt99 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_by_in_stmt101 = new BitSet(new long[]{0x00010003FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt103 = new BitSet(new long[]{0x00010003FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_orderList_in_stmt105 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SPACE_in_spaces117 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_keyword_in_orderList130 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_orderList143 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_orderList147 = new BitSet(new long[]{0x00010003FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_orderList151 = new BitSet(new long[]{0x00010003FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_keyword_in_orderList158 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_keyword_in_selectList184 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_selectList197 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_selectList201 = new BitSet(new long[]{0x00010003FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_selectList205 = new BitSet(new long[]{0x00010003FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_keyword_in_selectList212 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_entity_in_keyword237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword243 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword245 = new BitSet(new long[]{0x07FFFFFC00300000L});
    public static final BitSet FOLLOW_attr_in_keyword247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword252 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword254 = new BitSet(new long[]{0xF800000000000000L,0x0000000000000007L});
    public static final BitSet FOLLOW_funct_in_keyword256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_count_in_keyword261 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_spaces_in_keyword263 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_keyword265 = new BitSet(new long[]{0x00000003FFF80010L});
    public static final BitSet FOLLOW_spaces_in_keyword267 = new BitSet(new long[]{0x00000003FFF80000L});
    public static final BitSet FOLLOW_entity_in_keyword269 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword271 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sum_in_keyword278 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_spaces_in_keyword280 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_keyword282 = new BitSet(new long[]{0x00000003FFF80010L});
    public static final BitSet FOLLOW_spaces_in_keyword284 = new BitSet(new long[]{0x00000003FFF80000L});
    public static final BitSet FOLLOW_entity_in_keyword286 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword288 = new BitSet(new long[]{0x07FFFFFC00300000L});
    public static final BitSet FOLLOW_attr_in_keyword290 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword292 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_constraintList303 = new BitSet(new long[]{0x0000000000000012L,0x0000000000006180L});
    public static final BitSet FOLLOW_spaces_in_constraintList307 = new BitSet(new long[]{0x0000000000000000L,0x0000000000006180L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList314 = new BitSet(new long[]{0x00010003FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_constraintList322 = new BitSet(new long[]{0x00010003FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_constraint_in_constraintList324 = new BitSet(new long[]{0x0000000000000012L,0x0000000000006180L});
    public static final BitSet FOLLOW_keyword_in_constraint337 = new BitSet(new long[]{0x0000000000000710L});
    public static final BitSet FOLLOW_spaces_in_constraint346 = new BitSet(new long[]{0x0000000000000700L});
    public static final BitSet FOLLOW_compOpt_in_constraint353 = new BitSet(new long[]{0x0000000000040090L});
    public static final BitSet FOLLOW_spaces_in_constraint363 = new BitSet(new long[]{0x0000000000040080L});
    public static final BitSet FOLLOW_genValue_in_constraint370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint399 = new BitSet(new long[]{0x0000000000040010L,0x0000000000008000L});
    public static final BitSet FOLLOW_spaces_in_constraint408 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008000L});
    public static final BitSet FOLLOW_in_in_constraint415 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_spaces_in_constraint426 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_constraint428 = new BitSet(new long[]{0x0000000000040090L});
    public static final BitSet FOLLOW_spaces_in_constraint432 = new BitSet(new long[]{0x0000000000040080L});
    public static final BitSet FOLLOW_valueList_in_constraint438 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_constraint446 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_constraint450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint476 = new BitSet(new long[]{0x0000000000000010L,0x00000000000C0000L});
    public static final BitSet FOLLOW_spaces_in_constraint485 = new BitSet(new long[]{0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_like_in_constraint492 = new BitSet(new long[]{0x0000000000040090L});
    public static final BitSet FOLLOW_spaces_in_constraint501 = new BitSet(new long[]{0x0000000000040080L});
    public static final BitSet FOLLOW_dotValue_in_constraint508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_dotValue564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue570 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue572 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_dotValue574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue580 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue582 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue590 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue592 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue594 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue596 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_dotValue598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue604 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue606 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue608 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue610 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue618 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue620 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue622 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue624 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue626 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue628 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_dotValue630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue636 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue638 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue640 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue642 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue644 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue646 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue654 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue656 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue658 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue660 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue662 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue664 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue666 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue668 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_dotValue670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue676 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue678 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue680 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue682 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue684 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue686 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue688 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue690 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue698 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue700 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue702 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue704 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue706 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue708 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue710 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue712 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue714 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue716 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_dotValue718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue724 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue726 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue728 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue730 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue732 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue734 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue736 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue738 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue740 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue742 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue750 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue752 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue754 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue756 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue758 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue760 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue762 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue764 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue766 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue768 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue770 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue772 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_dotValue774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue780 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue782 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue784 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue786 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue788 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue790 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue792 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue794 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue796 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue798 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue800 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue802 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue810 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue812 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue814 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue816 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue818 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue820 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue822 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue824 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue826 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue828 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue830 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue832 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue834 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue836 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_dotValue838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue844 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue846 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue848 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue850 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue852 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue854 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue856 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue858 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue860 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue862 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue864 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue866 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue868 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue870 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue878 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue880 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue882 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue884 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue886 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue888 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue890 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue892 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue894 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue896 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue898 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue900 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue902 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue904 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue906 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue908 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_dotValue910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue916 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue918 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue920 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue922 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue924 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue926 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue928 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue930 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue932 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue934 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue936 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue938 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue940 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue942 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue944 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue946 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue955 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue957 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue959 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue961 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue963 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue965 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue967 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue969 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue971 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue973 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue975 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue977 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue979 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue981 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue983 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue985 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue987 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue989 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_dotValue991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue997 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue999 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1001 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1003 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1005 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1007 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1009 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1011 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1013 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1015 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1017 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1019 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1021 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1023 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1025 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1027 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1029 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1031 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1040 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1042 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1044 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1046 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1048 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1050 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1052 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1054 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1056 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1058 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1060 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1062 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1064 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1066 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1068 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1070 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1072 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1074 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1076 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1078 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_dotValue1080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1086 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1088 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1096 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1098 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1100 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1102 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_valueList1113 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_valueList1117 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_valueList1119 = new BitSet(new long[]{0x0000000000040090L});
    public static final BitSet FOLLOW_spaces_in_valueList1121 = new BitSet(new long[]{0x0000000000040080L});
    public static final BitSet FOLLOW_dotValue_in_valueList1123 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_EQ_in_compOpt1135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt1149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt1156 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt1159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt1166 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LT_in_compOpt1169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1176 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt1186 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1196 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt1199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue1206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue1211 = new BitSet(new long[]{0x0000000000000700L});
    public static final BitSet FOLLOW_compOpt_in_genValue1213 = new BitSet(new long[]{0x0000000000040080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1215 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_AMP_in_genValue1218 = new BitSet(new long[]{0x0000000000040080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1220 = new BitSet(new long[]{0x0000000000000700L});
    public static final BitSet FOLLOW_compOpt_in_genValue1222 = new BitSet(new long[]{0x0000000000040080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1224 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_and_in_logicalOp1237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp1239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity1248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr1313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct1427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select1465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and1486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_order1499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_by1512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or1525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in1538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not1551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_like1564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_count1577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sum1590 = new BitSet(new long[]{0x0000000000000002L});

}