package dbs.search.parser;
// $ANTLR 3.0.1 Sql.g 2008-07-15 13:45:30


import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SPACE", "COMMA", "DOT", "VALUE", "EQ", "LT", "GT", "AMP", "STAR", "NL", "WS", "'('", "')'", "'WHERE'", "'where'", "'ads'", "'dataset'", "'release'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'lumi'", "'dq'", "'ilumi'", "'phygrp'", "'group'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numlss'", "'size'", "'count'", "'status'", "'type'", "'id'", "'parent'", "'child'", "'tier'", "'def'", "'evnum'", "'era'", "'tag'", "'numruns()'", "'numfiles()'", "'dataquality()'", "'latest()'", "'parentrelease()'", "'childrelease()'", "'intluminosity()'", "'findevents()'", "'select'", "'SELECT'", "'find'", "'FIND'", "'and'", "'AND'", "'order'", "'ORDER'", "'by'", "'BY'", "'or'", "'OR'", "'in'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'", "'COUNT'", "'sum'", "'SUM'"
    };
    public static final int COMMA=5;
    public static final int SPACE=4;
    public static final int NL=13;
    public static final int WS=14;
    public static final int EOF=-1;
    public static final int EQ=8;
    public static final int AMP=11;
    public static final int LT=9;
    public static final int GT=10;
    public static final int STAR=12;
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
                {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==EOF||(LA5_1>=SPACE && LA5_1<=COMMA)||(LA5_1>=EQ && LA5_1<=GT)||(LA5_1>=17 && LA5_1<=18)||(LA5_1>=72 && LA5_1<=73)||(LA5_1>=78 && LA5_1<=79)||(LA5_1>=82 && LA5_1<=83)) ) {
                    alt5=1;
                }
                else if ( (LA5_1==DOT) ) {
                    int LA5_5 = input.LA(3);

                    if ( ((LA5_5>=20 && LA5_5<=21)||(LA5_5>=33 && LA5_5<=57)) ) {
                        alt5=2;
                    }
                    else if ( ((LA5_5>=58 && LA5_5<=65)) ) {
                        alt5=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("44:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 5, 5, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("44:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 5, 1, input);

                    throw nvae;
                }
                }
                break;
            case 47:
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

                    match(input,15,FOLLOW_15_in_keyword265); 
                    pushFollow(FOLLOW_spaces_in_keyword267);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_entity_in_keyword269);
                    entity();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword271);
                    spaces();
                    _fsp--;

                    match(input,16,FOLLOW_16_in_keyword273); 

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

                    match(input,15,FOLLOW_15_in_keyword282); 
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

                    match(input,16,FOLLOW_16_in_keyword294); 

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
    // Sql.g:54:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= likeValue );
    public final void constraint() throws RecognitionException {
        keyword_return kw = null;

        compOpt_return op = null;

        genValue_return val = null;

        in_return op1 = null;

        valueList_return val1 = null;

        like_return op2 = null;

        likeValue_return val2 = null;


        try {
            // Sql.g:54:12: (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= likeValue )
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

                    match(input,15,FOLLOW_15_in_constraint428); 
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

                    match(input,16,FOLLOW_16_in_constraint450); 

                    }
                    break;
                case 3 :
                    // Sql.g:69:2: kw= keyword spaces op2= like spaces val2= likeValue
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

                    pushFollow(FOLLOW_likeValue_in_constraint508);
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
    // Sql.g:75:1: where : ( 'WHERE' | 'where' ) ;
    public final void where() throws RecognitionException {
        try {
            // Sql.g:75:7: ( ( 'WHERE' | 'where' ) )
            // Sql.g:75:8: ( 'WHERE' | 'where' )
            {
            if ( (input.LA(1)>=17 && input.LA(1)<=18) ) {
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


    // $ANTLR start dotValue
    // Sql.g:76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );
    public final void dotValue() throws RecognitionException {
        try {
            // Sql.g:76:17: ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE )
            int alt8=12;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==VALUE) ) {
                switch ( input.LA(2) ) {
                case SPACE:
                    {
                    int LA8_2 = input.LA(3);

                    if ( (LA8_2==VALUE) ) {
                        int LA8_5 = input.LA(4);

                        if ( (LA8_5==SPACE) ) {
                            int LA8_7 = input.LA(5);

                            if ( (LA8_7==VALUE) ) {
                                alt8=12;
                            }
                            else if ( (LA8_7==EOF||(LA8_7>=SPACE && LA8_7<=COMMA)||LA8_7==16||(LA8_7>=70 && LA8_7<=73)||(LA8_7>=76 && LA8_7<=77)) ) {
                                alt8=11;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 7, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA8_5==EOF||LA8_5==COMMA||(LA8_5>=VALUE && LA8_5<=STAR)||LA8_5==16||(LA8_5>=70 && LA8_5<=73)||(LA8_5>=76 && LA8_5<=77)) ) {
                            alt8=11;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 5, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA8_2==EOF||(LA8_2>=SPACE && LA8_2<=COMMA)||LA8_2==16||(LA8_2>=70 && LA8_2<=73)||(LA8_2>=76 && LA8_2<=77)) ) {
                        alt8=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case DOT:
                    {
                    int LA8_3 = input.LA(3);

                    if ( (LA8_3==VALUE) ) {
                        int LA8_6 = input.LA(4);

                        if ( (LA8_6==DOT) ) {
                            int LA8_9 = input.LA(5);

                            if ( (LA8_9==VALUE) ) {
                                int LA8_12 = input.LA(6);

                                if ( (LA8_12==DOT) ) {
                                    int LA8_13 = input.LA(7);

                                    if ( (LA8_13==VALUE) ) {
                                        int LA8_15 = input.LA(8);

                                        if ( (LA8_15==DOT) ) {
                                            int LA8_16 = input.LA(9);

                                            if ( (LA8_16==VALUE) ) {
                                                int LA8_18 = input.LA(10);

                                                if ( (LA8_18==DOT) ) {
                                                    int LA8_19 = input.LA(11);

                                                    if ( (LA8_19==VALUE) ) {
                                                        int LA8_21 = input.LA(12);

                                                        if ( (LA8_21==DOT) ) {
                                                            int LA8_22 = input.LA(13);

                                                            if ( (LA8_22==VALUE) ) {
                                                                int LA8_24 = input.LA(14);

                                                                if ( (LA8_24==DOT) ) {
                                                                    int LA8_25 = input.LA(15);

                                                                    if ( (LA8_25==VALUE) ) {
                                                                        int LA8_27 = input.LA(16);

                                                                        if ( (LA8_27==DOT) ) {
                                                                            int LA8_28 = input.LA(17);

                                                                            if ( (LA8_28==VALUE) ) {
                                                                                int LA8_30 = input.LA(18);

                                                                                if ( (LA8_30==DOT) ) {
                                                                                    alt8=10;
                                                                                }
                                                                                else if ( (LA8_30==EOF||(LA8_30>=SPACE && LA8_30<=COMMA)||(LA8_30>=VALUE && LA8_30<=STAR)||LA8_30==16||(LA8_30>=70 && LA8_30<=73)||(LA8_30>=76 && LA8_30<=77)) ) {
                                                                                    alt8=9;
                                                                                }
                                                                                else {
                                                                                    NoViableAltException nvae =
                                                                                        new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 30, input);

                                                                                    throw nvae;
                                                                                }
                                                                            }
                                                                            else {
                                                                                NoViableAltException nvae =
                                                                                    new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 28, input);

                                                                                throw nvae;
                                                                            }
                                                                        }
                                                                        else if ( (LA8_27==EOF||(LA8_27>=SPACE && LA8_27<=COMMA)||(LA8_27>=VALUE && LA8_27<=STAR)||LA8_27==16||(LA8_27>=70 && LA8_27<=73)||(LA8_27>=76 && LA8_27<=77)) ) {
                                                                            alt8=8;
                                                                        }
                                                                        else {
                                                                            NoViableAltException nvae =
                                                                                new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 27, input);

                                                                            throw nvae;
                                                                        }
                                                                    }
                                                                    else {
                                                                        NoViableAltException nvae =
                                                                            new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 25, input);

                                                                        throw nvae;
                                                                    }
                                                                }
                                                                else if ( (LA8_24==EOF||(LA8_24>=SPACE && LA8_24<=COMMA)||(LA8_24>=VALUE && LA8_24<=STAR)||LA8_24==16||(LA8_24>=70 && LA8_24<=73)||(LA8_24>=76 && LA8_24<=77)) ) {
                                                                    alt8=7;
                                                                }
                                                                else {
                                                                    NoViableAltException nvae =
                                                                        new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 24, input);

                                                                    throw nvae;
                                                                }
                                                            }
                                                            else {
                                                                NoViableAltException nvae =
                                                                    new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 22, input);

                                                                throw nvae;
                                                            }
                                                        }
                                                        else if ( (LA8_21==EOF||(LA8_21>=SPACE && LA8_21<=COMMA)||(LA8_21>=VALUE && LA8_21<=STAR)||LA8_21==16||(LA8_21>=70 && LA8_21<=73)||(LA8_21>=76 && LA8_21<=77)) ) {
                                                            alt8=6;
                                                        }
                                                        else {
                                                            NoViableAltException nvae =
                                                                new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 21, input);

                                                            throw nvae;
                                                        }
                                                    }
                                                    else {
                                                        NoViableAltException nvae =
                                                            new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 19, input);

                                                        throw nvae;
                                                    }
                                                }
                                                else if ( (LA8_18==EOF||(LA8_18>=SPACE && LA8_18<=COMMA)||(LA8_18>=VALUE && LA8_18<=STAR)||LA8_18==16||(LA8_18>=70 && LA8_18<=73)||(LA8_18>=76 && LA8_18<=77)) ) {
                                                    alt8=5;
                                                }
                                                else {
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 18, input);

                                                    throw nvae;
                                                }
                                            }
                                            else {
                                                NoViableAltException nvae =
                                                    new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 16, input);

                                                throw nvae;
                                            }
                                        }
                                        else if ( (LA8_15==EOF||(LA8_15>=SPACE && LA8_15<=COMMA)||(LA8_15>=VALUE && LA8_15<=STAR)||LA8_15==16||(LA8_15>=70 && LA8_15<=73)||(LA8_15>=76 && LA8_15<=77)) ) {
                                            alt8=4;
                                        }
                                        else {
                                            NoViableAltException nvae =
                                                new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 15, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 13, input);

                                        throw nvae;
                                    }
                                }
                                else if ( (LA8_12==EOF||(LA8_12>=SPACE && LA8_12<=COMMA)||(LA8_12>=VALUE && LA8_12<=STAR)||LA8_12==16||(LA8_12>=70 && LA8_12<=73)||(LA8_12>=76 && LA8_12<=77)) ) {
                                    alt8=3;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 12, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 9, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA8_6==EOF||(LA8_6>=SPACE && LA8_6<=COMMA)||(LA8_6>=VALUE && LA8_6<=STAR)||LA8_6==16||(LA8_6>=70 && LA8_6<=73)||(LA8_6>=76 && LA8_6<=77)) ) {
                            alt8=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 6, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case EOF:
                case COMMA:
                case VALUE:
                case EQ:
                case LT:
                case GT:
                case AMP:
                case STAR:
                case 16:
                case 70:
                case 71:
                case 72:
                case 73:
                case 76:
                case 77:
                    {
                    alt8=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("76:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 8, 0, input);

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
                    // Sql.g:77:5: VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue564); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue566); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue568); 

                    }
                    break;
                case 3 :
                    // Sql.g:78:5: VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue574); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue576); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue578); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue580); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue582); 

                    }
                    break;
                case 4 :
                    // Sql.g:79:5: VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue588); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue590); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue592); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue594); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue596); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue598); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue600); 

                    }
                    break;
                case 5 :
                    // Sql.g:80:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue606); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue608); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue610); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue612); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue614); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue616); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue618); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue620); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue622); 

                    }
                    break;
                case 6 :
                    // Sql.g:81:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue628); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue630); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue632); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue634); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue636); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue638); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue640); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue642); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue644); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue646); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue648); 

                    }
                    break;
                case 7 :
                    // Sql.g:82:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue654); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue656); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue658); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue660); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue662); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue664); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue666); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue668); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue670); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue672); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue674); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue676); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue678); 

                    }
                    break;
                case 8 :
                    // Sql.g:83:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue684); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue686); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue688); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue690); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue692); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue694); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue696); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue698); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue700); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue702); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue704); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue706); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue708); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue710); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue712); 

                    }
                    break;
                case 9 :
                    // Sql.g:84:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue718); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue720); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue722); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue724); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue726); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue728); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue730); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue732); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue734); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue736); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue738); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue740); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue742); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue744); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue746); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue748); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue750); 

                    }
                    break;
                case 10 :
                    // Sql.g:85:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue757); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue759); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue761); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue763); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue765); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue767); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue769); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue771); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue773); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue775); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue777); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue779); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue781); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue783); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue785); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue787); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue789); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue791); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue793); 

                    }
                    break;
                case 11 :
                    // Sql.g:86:5: VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue800); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue802); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue804); 

                    }
                    break;
                case 12 :
                    // Sql.g:87:5: VALUE SPACE VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue810); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue812); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue814); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue816); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue818); 

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
    // Sql.g:90:1: valueList : dotValue ( spaces COMMA spaces dotValue )* ;
    public final valueList_return valueList() throws RecognitionException {
        valueList_return retval = new valueList_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:90:11: ( dotValue ( spaces COMMA spaces dotValue )* )
            // Sql.g:90:12: dotValue ( spaces COMMA spaces dotValue )*
            {
            pushFollow(FOLLOW_dotValue_in_valueList826);
            dotValue();
            _fsp--;

            // Sql.g:90:21: ( spaces COMMA spaces dotValue )*
            loop9:
            do {
                int alt9=2;
                alt9 = dfa9.predict(input);
                switch (alt9) {
            	case 1 :
            	    // Sql.g:90:23: spaces COMMA spaces dotValue
            	    {
            	    pushFollow(FOLLOW_spaces_in_valueList830);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_valueList832); 
            	    pushFollow(FOLLOW_spaces_in_valueList834);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_dotValue_in_valueList836);
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
    // Sql.g:91:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );
    public final compOpt_return compOpt() throws RecognitionException {
        compOpt_return retval = new compOpt_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:91:10: ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) )
            int alt10=7;
            switch ( input.LA(1) ) {
            case EQ:
                {
                switch ( input.LA(2) ) {
                case GT:
                    {
                    alt10=4;
                    }
                    break;
                case SPACE:
                case VALUE:
                    {
                    alt10=1;
                    }
                    break;
                case LT:
                    {
                    alt10=5;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("91:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 10, 1, input);

                    throw nvae;
                }

                }
                break;
            case LT:
                {
                int LA10_2 = input.LA(2);

                if ( (LA10_2==SPACE||LA10_2==VALUE) ) {
                    alt10=2;
                }
                else if ( (LA10_2==EQ) ) {
                    alt10=6;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("91:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 10, 2, input);

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
                else if ( (LA10_3==SPACE||LA10_3==VALUE) ) {
                    alt10=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("91:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 10, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("91:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // Sql.g:91:11: ( EQ )
                    {
                    // Sql.g:91:11: ( EQ )
                    // Sql.g:91:12: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt847); 

                    }


                    }
                    break;
                case 2 :
                    // Sql.g:92:4: ( LT )
                    {
                    // Sql.g:92:4: ( LT )
                    // Sql.g:92:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt854); 

                    }


                    }
                    break;
                case 3 :
                    // Sql.g:93:4: ( GT )
                    {
                    // Sql.g:93:4: ( GT )
                    // Sql.g:93:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt861); 

                    }


                    }
                    break;
                case 4 :
                    // Sql.g:94:4: ( EQ ) ( GT )
                    {
                    // Sql.g:94:4: ( EQ )
                    // Sql.g:94:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt868); 

                    }

                    // Sql.g:94:8: ( GT )
                    // Sql.g:94:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt871); 

                    }


                    }
                    break;
                case 5 :
                    // Sql.g:95:4: ( EQ ) ( LT )
                    {
                    // Sql.g:95:4: ( EQ )
                    // Sql.g:95:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt878); 

                    }

                    // Sql.g:95:8: ( LT )
                    // Sql.g:95:9: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt881); 

                    }


                    }
                    break;
                case 6 :
                    // Sql.g:96:4: ( LT ) ( EQ )
                    {
                    // Sql.g:96:4: ( LT )
                    // Sql.g:96:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt888); 

                    }

                    // Sql.g:96:8: ( EQ )
                    // Sql.g:96:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt891); 

                    }


                    }
                    break;
                case 7 :
                    // Sql.g:97:4: ( GT ) ( EQ )
                    {
                    // Sql.g:97:4: ( GT )
                    // Sql.g:97:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt898); 

                    }

                    // Sql.g:97:8: ( EQ )
                    // Sql.g:97:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt901); 

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
    // Sql.g:98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );
    public final genValue_return genValue() throws RecognitionException {
        genValue_return retval = new genValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:98:10: ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==VALUE) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA12_2 = input.LA(3);

                    if ( (LA12_2==VALUE) ) {
                        switch ( input.LA(4) ) {
                        case DOT:
                            {
                            int LA12_8 = input.LA(5);

                            if ( (LA12_8==VALUE) ) {
                                switch ( input.LA(6) ) {
                                case DOT:
                                    {
                                    int LA12_12 = input.LA(7);

                                    if ( (LA12_12==VALUE) ) {
                                        switch ( input.LA(8) ) {
                                        case DOT:
                                            {
                                            int LA12_14 = input.LA(9);

                                            if ( (LA12_14==VALUE) ) {
                                                switch ( input.LA(10) ) {
                                                case DOT:
                                                    {
                                                    int LA12_16 = input.LA(11);

                                                    if ( (LA12_16==VALUE) ) {
                                                        switch ( input.LA(12) ) {
                                                        case DOT:
                                                            {
                                                            int LA12_18 = input.LA(13);

                                                            if ( (LA12_18==VALUE) ) {
                                                                switch ( input.LA(14) ) {
                                                                case DOT:
                                                                    {
                                                                    int LA12_20 = input.LA(15);

                                                                    if ( (LA12_20==VALUE) ) {
                                                                        switch ( input.LA(16) ) {
                                                                        case DOT:
                                                                            {
                                                                            int LA12_22 = input.LA(17);

                                                                            if ( (LA12_22==VALUE) ) {
                                                                                switch ( input.LA(18) ) {
                                                                                case DOT:
                                                                                    {
                                                                                    int LA12_24 = input.LA(19);

                                                                                    if ( (LA12_24==VALUE) ) {
                                                                                        int LA12_25 = input.LA(20);

                                                                                        if ( ((LA12_25>=EQ && LA12_25<=GT)) ) {
                                                                                            alt12=2;
                                                                                        }
                                                                                        else if ( (LA12_25==EOF||LA12_25==SPACE||(LA12_25>=70 && LA12_25<=73)||(LA12_25>=76 && LA12_25<=77)) ) {
                                                                                            alt12=1;
                                                                                        }
                                                                                        else {
                                                                                            NoViableAltException nvae =
                                                                                                new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 25, input);

                                                                                            throw nvae;
                                                                                        }
                                                                                    }
                                                                                    else {
                                                                                        NoViableAltException nvae =
                                                                                            new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 24, input);

                                                                                        throw nvae;
                                                                                    }
                                                                                    }
                                                                                    break;
                                                                                case EOF:
                                                                                case SPACE:
                                                                                case 70:
                                                                                case 71:
                                                                                case 72:
                                                                                case 73:
                                                                                case 76:
                                                                                case 77:
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
                                                                                        new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 23, input);

                                                                                    throw nvae;
                                                                                }

                                                                            }
                                                                            else {
                                                                                NoViableAltException nvae =
                                                                                    new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 22, input);

                                                                                throw nvae;
                                                                            }
                                                                            }
                                                                            break;
                                                                        case EOF:
                                                                        case SPACE:
                                                                        case 70:
                                                                        case 71:
                                                                        case 72:
                                                                        case 73:
                                                                        case 76:
                                                                        case 77:
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
                                                                                new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 21, input);

                                                                            throw nvae;
                                                                        }

                                                                    }
                                                                    else {
                                                                        NoViableAltException nvae =
                                                                            new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 20, input);

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
                                                                case 70:
                                                                case 71:
                                                                case 72:
                                                                case 73:
                                                                case 76:
                                                                case 77:
                                                                    {
                                                                    alt12=1;
                                                                    }
                                                                    break;
                                                                default:
                                                                    NoViableAltException nvae =
                                                                        new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 19, input);

                                                                    throw nvae;
                                                                }

                                                            }
                                                            else {
                                                                NoViableAltException nvae =
                                                                    new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 18, input);

                                                                throw nvae;
                                                            }
                                                            }
                                                            break;
                                                        case EOF:
                                                        case SPACE:
                                                        case 70:
                                                        case 71:
                                                        case 72:
                                                        case 73:
                                                        case 76:
                                                        case 77:
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
                                                                new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 17, input);

                                                            throw nvae;
                                                        }

                                                    }
                                                    else {
                                                        NoViableAltException nvae =
                                                            new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 16, input);

                                                        throw nvae;
                                                    }
                                                    }
                                                    break;
                                                case EOF:
                                                case SPACE:
                                                case 70:
                                                case 71:
                                                case 72:
                                                case 73:
                                                case 76:
                                                case 77:
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
                                                        new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 15, input);

                                                    throw nvae;
                                                }

                                            }
                                            else {
                                                NoViableAltException nvae =
                                                    new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 14, input);

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
                                        case 70:
                                        case 71:
                                        case 72:
                                        case 73:
                                        case 76:
                                        case 77:
                                            {
                                            alt12=1;
                                            }
                                            break;
                                        default:
                                            NoViableAltException nvae =
                                                new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 13, input);

                                            throw nvae;
                                        }

                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 12, input);

                                        throw nvae;
                                    }
                                    }
                                    break;
                                case EOF:
                                case SPACE:
                                case 70:
                                case 71:
                                case 72:
                                case 73:
                                case 76:
                                case 77:
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
                                        new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 10, input);

                                    throw nvae;
                                }

                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 8, input);

                                throw nvae;
                            }
                            }
                            break;
                        case EOF:
                        case SPACE:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 76:
                        case 77:
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
                                new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 6, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case SPACE:
                    {
                    int LA12_3 = input.LA(3);

                    if ( (LA12_3==VALUE) ) {
                        switch ( input.LA(4) ) {
                        case SPACE:
                            {
                            int LA12_9 = input.LA(5);

                            if ( (LA12_9==VALUE) ) {
                                int LA12_11 = input.LA(6);

                                if ( (LA12_11==EOF||LA12_11==SPACE||(LA12_11>=70 && LA12_11<=73)||(LA12_11>=76 && LA12_11<=77)) ) {
                                    alt12=1;
                                }
                                else if ( ((LA12_11>=EQ && LA12_11<=GT)) ) {
                                    alt12=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 11, input);

                                    throw nvae;
                                }
                            }
                            else if ( (LA12_9==EOF||LA12_9==SPACE||(LA12_9>=70 && LA12_9<=73)||(LA12_9>=76 && LA12_9<=77)) ) {
                                alt12=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 9, input);

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
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 76:
                        case 77:
                            {
                            alt12=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 7, input);

                            throw nvae;
                        }

                    }
                    else if ( (LA12_3==EOF||LA12_3==SPACE||(LA12_3>=70 && LA12_3<=73)||(LA12_3>=76 && LA12_3<=77)) ) {
                        alt12=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case EOF:
                case 70:
                case 71:
                case 72:
                case 73:
                case 76:
                case 77:
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
                        new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("98:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // Sql.g:98:11: dotValue
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue908);
                    dotValue();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:99:4: dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )*
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue913);
                    dotValue();
                    _fsp--;

                    pushFollow(FOLLOW_compOpt_in_genValue915);
                    compOpt();
                    _fsp--;

                    pushFollow(FOLLOW_dotValue_in_genValue917);
                    dotValue();
                    _fsp--;

                    // Sql.g:99:30: ( AMP dotValue compOpt dotValue )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==AMP) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // Sql.g:99:31: AMP dotValue compOpt dotValue
                    	    {
                    	    match(input,AMP,FOLLOW_AMP_in_genValue920); 
                    	    pushFollow(FOLLOW_dotValue_in_genValue922);
                    	    dotValue();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_compOpt_in_genValue924);
                    	    compOpt();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_dotValue_in_genValue926);
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

    public static class likeValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start likeValue
    // Sql.g:100:1: likeValue : ( dotValue | STAR )+ ;
    public final likeValue_return likeValue() throws RecognitionException {
        likeValue_return retval = new likeValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:100:12: ( ( dotValue | STAR )+ )
            // Sql.g:100:13: ( dotValue | STAR )+
            {
            // Sql.g:100:13: ( dotValue | STAR )+
            int cnt13=0;
            loop13:
            do {
                int alt13=3;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==VALUE) ) {
                    alt13=1;
                }
                else if ( (LA13_0==STAR) ) {
                    alt13=2;
                }


                switch (alt13) {
            	case 1 :
            	    // Sql.g:100:14: dotValue
            	    {
            	    pushFollow(FOLLOW_dotValue_in_likeValue936);
            	    dotValue();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // Sql.g:100:24: STAR
            	    {
            	    match(input,STAR,FOLLOW_STAR_in_likeValue939); 

            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
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
    // Sql.g:101:1: logicalOp : ( and | or ) ;
    public final logicalOp_return logicalOp() throws RecognitionException {
        logicalOp_return retval = new logicalOp_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:101:11: ( ( and | or ) )
            // Sql.g:101:12: ( and | or )
            {
            // Sql.g:101:12: ( and | or )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=70 && LA14_0<=71)) ) {
                alt14=1;
            }
            else if ( ((LA14_0>=76 && LA14_0<=77)) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("101:12: ( and | or )", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // Sql.g:101:13: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp948);
                    and();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:101:17: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp950);
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
    // Sql.g:102:1: entity : ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' ) ;
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:102:9: ( ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' ) )
            // Sql.g:102:11: ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' )
            {
            if ( (input.LA(1)>=19 && input.LA(1)<=32) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_entity959);    throw mse;
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
    // Sql.g:103:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) ;
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:103:7: ( ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) )
            // Sql.g:103:8: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' )
            {
            if ( (input.LA(1)>=20 && input.LA(1)<=21)||(input.LA(1)>=33 && input.LA(1)<=57) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_attr1020);    throw mse;
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
    // Sql.g:104:1: funct : ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) ;
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:104:8: ( ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) )
            // Sql.g:104:9: ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' )
            {
            if ( (input.LA(1)>=58 && input.LA(1)<=65) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_funct1134);    throw mse;
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
    // Sql.g:105:1: select : ( 'select' | 'SELECT' | 'find' | 'FIND' ) ;
    public final void select() throws RecognitionException {
        try {
            // Sql.g:105:9: ( ( 'select' | 'SELECT' | 'find' | 'FIND' ) )
            // Sql.g:105:10: ( 'select' | 'SELECT' | 'find' | 'FIND' )
            {
            if ( (input.LA(1)>=66 && input.LA(1)<=69) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_select1172);    throw mse;
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
    // Sql.g:106:1: and : ( 'and' | 'AND' ) ;
    public final void and() throws RecognitionException {
        try {
            // Sql.g:106:6: ( ( 'and' | 'AND' ) )
            // Sql.g:106:7: ( 'and' | 'AND' )
            {
            if ( (input.LA(1)>=70 && input.LA(1)<=71) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_and1193);    throw mse;
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
    // Sql.g:107:1: order : ( 'order' | 'ORDER' ) ;
    public final void order() throws RecognitionException {
        try {
            // Sql.g:107:8: ( ( 'order' | 'ORDER' ) )
            // Sql.g:107:9: ( 'order' | 'ORDER' )
            {
            if ( (input.LA(1)>=72 && input.LA(1)<=73) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_order1206);    throw mse;
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
    // Sql.g:108:1: by : ( 'by' | 'BY' ) ;
    public final void by() throws RecognitionException {
        try {
            // Sql.g:108:5: ( ( 'by' | 'BY' ) )
            // Sql.g:108:6: ( 'by' | 'BY' )
            {
            if ( (input.LA(1)>=74 && input.LA(1)<=75) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_by1219);    throw mse;
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
    // Sql.g:109:1: or : ( 'or' | 'OR' ) ;
    public final void or() throws RecognitionException {
        try {
            // Sql.g:109:5: ( ( 'or' | 'OR' ) )
            // Sql.g:109:6: ( 'or' | 'OR' )
            {
            if ( (input.LA(1)>=76 && input.LA(1)<=77) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_or1232);    throw mse;
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
    // Sql.g:110:1: in : ( 'in' | 'IN' ) ;
    public final in_return in() throws RecognitionException {
        in_return retval = new in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:110:5: ( ( 'in' | 'IN' ) )
            // Sql.g:110:6: ( 'in' | 'IN' )
            {
            if ( (input.LA(1)>=78 && input.LA(1)<=79) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_in1245);    throw mse;
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
    // Sql.g:111:1: not : ( 'not' | 'NOT' ) ;
    public final void not() throws RecognitionException {
        try {
            // Sql.g:111:6: ( ( 'not' | 'NOT' ) )
            // Sql.g:111:7: ( 'not' | 'NOT' )
            {
            if ( (input.LA(1)>=80 && input.LA(1)<=81) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_not1258);    throw mse;
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
    // Sql.g:112:1: like : ( 'like' | 'LIKE' ) ;
    public final like_return like() throws RecognitionException {
        like_return retval = new like_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:112:7: ( ( 'like' | 'LIKE' ) )
            // Sql.g:112:8: ( 'like' | 'LIKE' )
            {
            if ( (input.LA(1)>=82 && input.LA(1)<=83) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_like1271);    throw mse;
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
    // Sql.g:113:1: count : ( 'count' | 'COUNT' ) ;
    public final void count() throws RecognitionException {
        try {
            // Sql.g:113:8: ( ( 'count' | 'COUNT' ) )
            // Sql.g:113:9: ( 'count' | 'COUNT' )
            {
            if ( input.LA(1)==47||input.LA(1)==84 ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_count1284);    throw mse;
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
    // Sql.g:114:1: sum : ( 'sum' | 'SUM' ) ;
    public final void sum() throws RecognitionException {
        try {
            // Sql.g:114:6: ( ( 'sum' | 'SUM' ) )
            // Sql.g:114:7: ( 'sum' | 'SUM' )
            {
            if ( (input.LA(1)>=85 && input.LA(1)<=86) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_sum1297);    throw mse;
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
        "\u01e8\uffff";
    static final String DFA1_eofS =
        "\3\uffff\1\13\3\uffff\1\13\10\uffff\2\13\1\uffff\1\13\33\uffff\1"+
        "\13\1\uffff\2\13\7\uffff\1\120\5\uffff\2\120\10\uffff\1\13\2\uffff"+
        "\1\120\7\uffff\1\120\1\uffff\1\120\6\uffff\1\13\1\uffff\2\120\4"+
        "\uffff\1\120\4\uffff\2\120\3\uffff\1\120\2\uffff\1\13\1\uffff\1"+
        "\120\14\uffff\1\120\1\uffff\1\120\10\uffff\2\120\3\uffff\2\120\3"+
        "\uffff\1\120\10\uffff\2\120\1\uffff\2\120\6\uffff\2\120\12\uffff"+
        "\1\120\12\uffff\6\120\7\uffff\1\120\1\uffff\2\120\2\uffff\1\120"+
        "\4\uffff\1\120\5\uffff\2\120\1\uffff\1\120\15\uffff\1\120\4\uffff"+
        "\7\120\5\uffff\1\120\2\uffff\3\120\5\uffff\1\120\15\uffff\1\120"+
        "\3\uffff\5\120\3\uffff\1\120\6\uffff\1\120\1\uffff\3\120\11\uffff"+
        "\1\120\11\uffff\4\120\2\uffff\2\120\2\uffff\1\120\1\uffff\2\120"+
        "\10\uffff\1\120\10\uffff\4\120\1\uffff\2\120\2\uffff\1\120\1\uffff"+
        "\2\120\20\uffff\4\120\1\uffff\1\120\2\uffff\1\120\1\uffff\2\120"+
        "\20\uffff\4\120\1\uffff\1\120\2\uffff\1\120\1\uffff\2\120\16\uffff"+
        "\3\120\1\uffff\1\120\2\uffff\1\120\1\uffff\1\120\13\uffff\3\120"+
        "\1\uffff\1\120\3\uffff\1\120\6\uffff\1\120\1\uffff\1\120\1\uffff"+
        "\1\120\4\uffff\1\120\1\uffff\1\120";
    static final String DFA1_minS =
        "\1\102\5\4\1\24\3\4\2\uffff\21\4\1\6\1\24\4\4\1\24\14\4\1\24\5\4"+
        "\1\6\20\4\1\6\3\4\1\24\1\7\3\4\2\uffff\3\7\1\4\1\7\5\4\1\24\12\4"+
        "\4\7\2\4\1\7\6\4\1\7\1\4\1\24\12\4\1\7\1\4\1\7\1\4\1\7\31\4\1\6"+
        "\5\4\1\7\1\4\1\7\1\4\2\7\2\4\4\7\4\4\1\24\1\7\1\4\6\7\12\4\5\7\6"+
        "\4\1\6\2\4\4\7\1\4\2\7\1\4\2\7\2\4\1\7\1\4\2\7\7\4\4\7\1\4\1\7\13"+
        "\4\1\7\1\4\1\7\2\4\1\6\1\10\3\4\5\7\1\4\6\7\4\4\3\7\1\4\1\7\10\4"+
        "\1\6\1\7\1\4\5\7\2\4\1\6\3\4\11\7\4\4\4\7\6\4\1\10\1\6\2\4\2\7\1"+
        "\4\1\6\2\4\10\7\3\4\4\7\6\4\1\6\2\4\2\7\1\4\1\6\2\4\10\7\2\4\4\7"+
        "\6\4\1\6\1\4\2\7\1\4\1\6\2\4\10\7\2\4\4\7\6\4\1\6\1\4\2\7\1\4\1"+
        "\6\2\4\7\7\2\4\3\7\5\4\1\6\1\4\2\7\1\4\1\6\1\4\6\7\2\4\2\7\4\4\1"+
        "\6\1\4\2\7\1\10\1\4\3\7\2\4\1\7\1\4\1\6\1\4\1\7\1\4\2\7\1\4\1\10"+
        "\1\4\1\7\1\4";
    static final String DFA1_maxS =
        "\1\105\2\126\1\111\2\17\1\101\1\111\2\126\2\uffff\1\17\1\40\1\17"+
        "\1\40\2\111\1\126\1\111\2\17\1\126\1\123\2\17\1\40\1\20\1\40\1\6"+
        "\1\101\1\17\1\40\1\17\1\40\1\101\1\123\1\12\2\10\1\14\2\17\1\40"+
        "\1\17\1\40\1\20\1\111\1\71\2\111\1\40\1\20\1\40\1\6\2\123\1\7\1"+
        "\115\4\7\1\14\2\115\1\17\1\7\1\40\1\20\1\40\1\6\2\20\1\111\1\71"+
        "\1\7\1\115\2\126\2\uffff\1\12\2\10\1\115\1\7\1\115\1\7\2\20\1\123"+
        "\1\71\1\20\1\111\1\20\2\115\1\126\1\123\2\17\1\115\4\7\2\115\1\7"+
        "\1\20\1\7\1\115\2\20\1\111\1\7\1\115\1\101\1\123\1\14\1\12\2\10"+
        "\2\17\1\40\1\17\1\40\1\7\1\115\1\7\1\115\1\7\3\20\1\7\2\20\1\123"+
        "\2\115\2\123\1\14\2\115\3\7\1\115\2\7\1\17\1\7\1\40\1\20\1\40\1"+
        "\6\2\115\1\12\2\115\1\7\1\20\1\7\1\20\2\7\2\115\1\7\1\12\2\10\1"+
        "\7\2\20\1\123\1\71\1\7\1\115\2\7\1\12\2\10\1\7\4\20\6\115\5\7\1"+
        "\20\1\7\1\115\1\20\2\115\2\12\1\115\4\7\1\115\2\7\1\20\2\7\2\115"+
        "\1\7\1\115\2\7\3\20\1\7\2\20\1\123\4\7\1\115\1\7\3\20\7\115\1\12"+
        "\1\7\1\20\1\7\1\20\1\115\2\12\3\115\5\7\1\115\3\7\1\12\2\10\4\20"+
        "\3\7\1\115\1\7\2\20\5\115\2\12\1\7\1\115\5\7\1\20\1\115\1\12\3\115"+
        "\11\7\1\115\3\20\4\7\2\20\4\115\2\12\2\115\2\7\1\115\1\12\2\115"+
        "\10\7\1\115\2\20\4\7\2\20\4\115\1\12\2\115\2\7\1\115\1\12\2\115"+
        "\10\7\2\20\4\7\2\20\4\115\1\12\1\115\2\7\1\115\1\12\2\115\10\7\2"+
        "\20\4\7\2\20\4\115\1\12\1\115\2\7\1\115\1\12\2\115\7\7\2\20\3\7"+
        "\2\20\3\115\1\12\1\115\2\7\1\115\1\12\1\115\6\7\2\20\2\7\1\20\3"+
        "\115\1\12\1\115\2\7\1\12\1\115\3\7\2\20\1\7\1\115\1\12\1\115\1\7"+
        "\1\115\2\7\1\20\1\12\1\115\1\7\1\115";
    static final String DFA1_acceptS =
        "\12\uffff\1\3\1\2\104\uffff\1\1\1\4\u0196\uffff";
    static final String DFA1_specialS =
        "\u01e8\uffff}>";
    static final String[] DFA1_transitionS = {
            "\4\1",
            "\1\2\16\uffff\16\3\16\uffff\1\4\44\uffff\1\4\2\5",
            "\1\2\16\uffff\16\3\16\uffff\1\4\44\uffff\1\4\2\5",
            "\1\7\1\10\1\6\12\uffff\2\11\65\uffff\2\12",
            "\1\14\12\uffff\1\15",
            "\1\16\12\uffff\1\17",
            "\2\20\13\uffff\31\20\10\21",
            "\1\7\1\10\13\uffff\2\11\65\uffff\2\12",
            "\1\22\16\uffff\16\23\16\uffff\1\24\44\uffff\1\24\2\25",
            "\1\26\16\uffff\16\27\16\uffff\1\30\44\uffff\1\30\2\31",
            "",
            "",
            "\1\14\12\uffff\1\15",
            "\1\32\16\uffff\16\33",
            "\1\16\12\uffff\1\17",
            "\1\34\16\uffff\16\35",
            "\1\7\1\10\13\uffff\2\11\65\uffff\2\12",
            "\1\7\1\10\13\uffff\2\11\65\uffff\2\12",
            "\1\22\16\uffff\16\23\16\uffff\1\24\44\uffff\1\24\2\25",
            "\1\7\1\10\1\36\12\uffff\2\11\65\uffff\2\12",
            "\1\37\12\uffff\1\40",
            "\1\41\12\uffff\1\42",
            "\1\26\16\uffff\16\27\16\uffff\1\30\44\uffff\1\30\2\31",
            "\1\44\1\uffff\1\43\1\uffff\1\45\1\46\1\47\103\uffff\2\51\2\uffff"+
            "\2\50",
            "\1\52\12\uffff\1\53",
            "\1\54\12\uffff\1\55",
            "\1\32\16\uffff\16\33",
            "\1\56\13\uffff\1\57",
            "\1\34\16\uffff\16\35",
            "\1\60",
            "\2\62\13\uffff\31\62\10\61",
            "\1\37\12\uffff\1\40",
            "\1\63\16\uffff\16\64",
            "\1\41\12\uffff\1\42",
            "\1\65\16\uffff\16\66",
            "\2\67\13\uffff\31\67\10\70",
            "\1\44\3\uffff\1\45\1\46\1\47\103\uffff\2\51\2\uffff\2\50",
            "\1\71\2\uffff\1\72\1\uffff\1\73\1\74",
            "\1\71\2\uffff\1\72\1\75",
            "\1\71\2\uffff\1\72\1\76",
            "\1\77\2\uffff\1\100\4\uffff\1\101",
            "\1\102\12\uffff\1\103",
            "\1\52\12\uffff\1\53",
            "\1\104\16\uffff\16\105",
            "\1\54\12\uffff\1\55",
            "\1\106\16\uffff\16\107",
            "\1\56\13\uffff\1\57",
            "\1\7\1\10\13\uffff\2\11\65\uffff\2\12",
            "\2\110\13\uffff\31\110",
            "\1\7\1\10\13\uffff\2\11\65\uffff\2\12",
            "\1\7\1\10\13\uffff\2\11\65\uffff\2\12",
            "\1\63\16\uffff\16\64",
            "\1\111\13\uffff\1\112",
            "\1\65\16\uffff\16\66",
            "\1\113",
            "\1\44\3\uffff\1\45\1\46\1\47\103\uffff\2\51\2\uffff\2\50",
            "\1\44\3\uffff\1\45\1\46\1\47\103\uffff\2\51\2\uffff\2\50",
            "\1\71\2\uffff\1\72",
            "\1\115\1\uffff\1\114\1\uffff\1\122\1\123\1\124\73\uffff\2\116"+
            "\2\121\2\uffff\2\117",
            "\1\71\2\uffff\1\72",
            "\1\71\2\uffff\1\72",
            "\1\71\2\uffff\1\72",
            "\1\71\2\uffff\1\72",
            "\1\77\2\uffff\1\100\4\uffff\1\101",
            "\1\125\1\uffff\1\126\1\100\4\uffff\1\101\71\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\127\2\uffff\1\100\4\uffff\1\101\71\uffff\2\116\2\121\2\uffff"+
            "\2\117",
            "\1\102\12\uffff\1\103",
            "\1\130\2\uffff\1\131",
            "\1\104\16\uffff\16\105",
            "\1\132\13\uffff\1\133",
            "\1\106\16\uffff\16\107",
            "\1\134",
            "\1\135\13\uffff\1\136",
            "\1\111\13\uffff\1\112",
            "\1\7\1\10\13\uffff\2\11\65\uffff\2\12",
            "\2\137\13\uffff\31\137",
            "\1\140",
            "\1\127\2\uffff\1\141\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\142\16\uffff\16\143\16\uffff\1\144\44\uffff\1\144\2\145",
            "\1\142\16\uffff\16\143\16\uffff\1\144\44\uffff\1\144\2\145",
            "",
            "",
            "\1\146\1\uffff\1\147\1\150",
            "\1\146\1\151",
            "\1\146\1\152",
            "\1\127\2\uffff\1\153\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\154",
            "\1\127\101\uffff\2\116\2\121\2\uffff\2\117",
            "\1\130\2\uffff\1\131",
            "\1\156\1\157\1\155\11\uffff\1\160",
            "\1\132\13\uffff\1\133",
            "\1\44\3\uffff\1\45\1\46\1\47\103\uffff\2\51\2\uffff\2\50",
            "\2\161\13\uffff\31\161",
            "\1\135\13\uffff\1\136",
            "\1\7\1\10\13\uffff\2\11\65\uffff\2\12",
            "\1\162\13\uffff\1\163",
            "\1\127\1\uffff\1\164\1\uffff\1\122\1\123\1\124\73\uffff\2\116"+
            "\2\121\2\uffff\2\117",
            "\1\165\3\uffff\1\122\1\123\1\124\73\uffff\2\116\2\121\2\uffff"+
            "\2\117",
            "\1\142\16\uffff\16\143\16\uffff\1\144\44\uffff\1\144\2\145",
            "\1\167\1\uffff\1\166\1\uffff\1\171\1\172\1\173\103\uffff\2\174"+
            "\2\uffff\2\170",
            "\1\175\12\uffff\1\176",
            "\1\177\12\uffff\1\u0080",
            "\1\u0082\1\uffff\1\u0081\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\146",
            "\1\146",
            "\1\146",
            "\1\146",
            "\1\u0084\2\uffff\1\100\4\uffff\1\101\71\uffff\2\116\2\121\2"+
            "\uffff\2\117",
            "\1\127\1\uffff\1\u0085\1\100\4\uffff\1\101\71\uffff\2\116\2"+
            "\121\2\uffff\2\117",
            "\1\u0086",
            "\1\u0088\1\157\1\uffff\1\u0087\10\uffff\1\160",
            "\1\u0089\2\uffff\1\u008a",
            "\1\127\101\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u008b\13\uffff\1\u008c",
            "\1\162\13\uffff\1\163",
            "\1\7\1\10\13\uffff\2\11\65\uffff\2\12",
            "\1\u008d",
            "\1\127\2\uffff\1\u008e\76\uffff\2\116\2\121\2\uffff\2\117",
            "\2\u008f\13\uffff\31\u008f\10\u0090",
            "\1\167\3\uffff\1\171\1\172\1\173\103\uffff\2\174\2\uffff\2\170",
            "\1\u0091\2\uffff\1\u0092\4\uffff\1\u0093",
            "\1\u0096\2\uffff\1\u0097\1\uffff\1\u0094\1\u0095",
            "\1\u0096\2\uffff\1\u0097\1\u0098",
            "\1\u0096\2\uffff\1\u0097\1\u0099",
            "\1\u009a\12\uffff\1\u009b",
            "\1\175\12\uffff\1\176",
            "\1\u009c\16\uffff\16\u009d",
            "\1\177\12\uffff\1\u0080",
            "\1\u009e\16\uffff\16\u009f",
            "\1\u00a0",
            "\1\127\2\uffff\1\u00a1\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u00a2",
            "\1\127\2\uffff\1\u00a3\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u00a4",
            "\1\u0088\1\157\1\u00a5\11\uffff\1\160",
            "\1\u00a6\1\157\12\uffff\1\160",
            "\1\u0088\1\157\12\uffff\1\160",
            "\1\u0089\2\uffff\1\u008a",
            "\1\u00a8\1\157\1\u00a7\11\uffff\1\160",
            "\1\u008b\13\uffff\1\u008c",
            "\1\44\3\uffff\1\45\1\46\1\47\103\uffff\2\51\2\uffff\2\50",
            "\1\127\1\uffff\1\u00a9\1\uffff\1\122\1\123\1\124\73\uffff\2"+
            "\116\2\121\2\uffff\2\117",
            "\1\127\3\uffff\1\122\1\123\1\124\73\uffff\2\116\2\121\2\uffff"+
            "\2\117",
            "\1\167\3\uffff\1\171\1\172\1\173\103\uffff\2\174\2\uffff\2\170",
            "\1\167\3\uffff\1\171\1\172\1\173\103\uffff\2\174\2\uffff\2\170",
            "\1\u0091\2\uffff\1\u0092\4\uffff\1\u0093",
            "\1\u00ab\1\uffff\1\u00aa\1\u0092\4\uffff\1\u0093\71\uffff\2"+
            "\116\2\121\2\uffff\2\117",
            "\1\127\2\uffff\1\u0092\4\uffff\1\u0093\71\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u0096\2\uffff\1\u0097",
            "\1\u0096\2\uffff\1\u0097",
            "\1\u0096\2\uffff\1\u0097",
            "\1\u00ac\1\uffff\1\u00ad\1\uffff\1\u00ae\1\u00af\1\u00b0\73"+
            "\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u0096\2\uffff\1\u0097",
            "\1\u0096\2\uffff\1\u0097",
            "\1\u009a\12\uffff\1\u009b",
            "\1\u00b1\2\uffff\1\u00b2",
            "\1\u009c\16\uffff\16\u009d",
            "\1\u00b3\13\uffff\1\u00b4",
            "\1\u009e\16\uffff\16\u009f",
            "\1\u00b5",
            "\1\127\1\uffff\1\u00b6\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u00b7\6\uffff\1\u0083\72\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u00b9\1\uffff\1\u00b8\1\uffff\1\u00ba\1\u00bb\1\u00bc",
            "\1\127\2\uffff\1\100\4\uffff\1\101\71\uffff\2\116\2\121\2\uffff"+
            "\2\117",
            "\1\127\1\uffff\1\u00bd\1\100\4\uffff\1\101\71\uffff\2\116\2"+
            "\121\2\uffff\2\117",
            "\1\u00be",
            "\1\u0088\1\157\1\uffff\1\u00bf\10\uffff\1\160",
            "\1\u00c0",
            "\1\u0088\1\157\1\uffff\1\u00c1\10\uffff\1\160",
            "\1\u00c2",
            "\1\u00c3",
            "\1\127\2\uffff\1\u00c4\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\127\2\uffff\1\u00c5\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u00c6",
            "\1\u00c7\1\uffff\1\u00c9\1\u00c8",
            "\1\u00c7\1\u00ca",
            "\1\u00c7\1\u00cb",
            "\1\u00b1\2\uffff\1\u00b2",
            "\1\u00cd\1\u00ce\1\u00cc\11\uffff\1\u00cf",
            "\1\u00b3\13\uffff\1\u00b4",
            "\1\167\3\uffff\1\171\1\172\1\173\103\uffff\2\174\2\uffff\2\170",
            "\2\u00d0\13\uffff\31\u00d0",
            "\1\u00d1",
            "\1\127\2\uffff\1\u00d2\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5\1\uffff\1\u00d6\1\u00d7",
            "\1\u00d5\1\u00d8",
            "\1\u00d5\1\u00d9",
            "\1\u00da",
            "\1\u0088\1\157\1\u00db\11\uffff\1\160",
            "\1\u0088\1\157\12\uffff\1\160",
            "\1\u0088\1\157\1\u00dc\11\uffff\1\160",
            "\1\u00dd\1\157\12\uffff\1\160",
            "\1\127\1\uffff\1\u00de\1\uffff\1\122\1\123\1\124\73\uffff\2"+
            "\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u00df\1\u0092\4\uffff\1\u0093\71\uffff\2\116"+
            "\2\121\2\uffff\2\117",
            "\1\u00e0\2\uffff\1\u0092\4\uffff\1\u0093\71\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u00e1\3\uffff\1\u00ae\1\u00af\1\u00b0\73\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\127\1\uffff\1\u00e2\1\uffff\1\u00ae\1\u00af\1\u00b0\73\uffff"+
            "\2\116\2\121\2\uffff\2\117",
            "\1\u00e3\1\uffff\1\u00e4\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u00c7",
            "\1\u00c7",
            "\1\u00c7",
            "\1\u00c7",
            "\1\u00e6",
            "\1\u00e8\1\u00ce\1\uffff\1\u00e7\10\uffff\1\u00cf",
            "\1\u00e9\2\uffff\1\u00ea",
            "\1\127\101\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u00eb\13\uffff\1\u00ec",
            "\1\127\1\uffff\1\u00ed\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\127\6\uffff\1\u0083\72\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u00ee\1\uffff\1\u00ba\1\u00bb\1\u00bc",
            "\1\u00ef\3\uffff\1\u00ba\1\u00bb\1\u00bc",
            "\1\u00f1\1\uffff\1\u00f0\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u00d5",
            "\1\u00d5",
            "\1\u00d5",
            "\1\u00d5",
            "\1\127\1\uffff\1\u00f2\1\100\4\uffff\1\101\71\uffff\2\116\2"+
            "\121\2\uffff\2\117",
            "\1\u00f3",
            "\1\u00f4",
            "\1\u0088\1\157\1\uffff\1\u00f5\10\uffff\1\160",
            "\1\u00f6",
            "\1\u00f7",
            "\1\127\2\uffff\1\u00f8\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\127\2\uffff\1\u00f9\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u00fa",
            "\1\127\2\uffff\1\u00fb\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u00fc",
            "\1\u00fd",
            "\1\u00e8\1\u00ce\1\u00fe\11\uffff\1\u00cf",
            "\1\u00ff\1\u00ce\12\uffff\1\u00cf",
            "\1\u00e8\1\u00ce\12\uffff\1\u00cf",
            "\1\u00e9\2\uffff\1\u00ea",
            "\1\u0101\1\u00ce\1\u0100\11\uffff\1\u00cf",
            "\1\u00eb\13\uffff\1\u00ec",
            "\1\167\3\uffff\1\171\1\172\1\173\103\uffff\2\174\2\uffff\2\170",
            "\1\u0102",
            "\1\u0103",
            "\1\u0104",
            "\1\u0105",
            "\1\127\2\uffff\1\u0106\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u0107",
            "\1\u0088\1\157\1\u0108\11\uffff\1\160",
            "\1\u0088\1\157\1\u0109\11\uffff\1\160",
            "\1\u0088\1\157\12\uffff\1\160",
            "\1\127\1\uffff\1\u010a\1\uffff\1\122\1\123\1\124\73\uffff\2"+
            "\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u010b\1\u0092\4\uffff\1\u0093\71\uffff\2\116"+
            "\2\121\2\uffff\2\117",
            "\1\127\2\uffff\1\u0092\4\uffff\1\u0093\71\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\127\3\uffff\1\u00ae\1\u00af\1\u00b0\73\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\127\1\uffff\1\u010c\1\uffff\1\u00ae\1\u00af\1\u00b0\73\uffff"+
            "\2\116\2\121\2\uffff\2\117",
            "\1\u010d\6\uffff\1\u00e5\72\uffff\2\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u010e\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u010f\1\uffff\1\u0110\1\uffff\1\u0111\1\u0112\1\u0113",
            "\1\u0114",
            "\1\u00e8\1\u00ce\1\uffff\1\u0115\10\uffff\1\u00cf",
            "\1\u0116",
            "\1\u00e8\1\u00ce\1\uffff\1\u0117\10\uffff\1\u00cf",
            "\1\127\1\uffff\1\u0118\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u0119\1\uffff\1\u00ba\1\u00bb\1\u00bc",
            "\1\u00ba\1\u00bb\1\u00bc",
            "\1\127\1\uffff\1\u011a\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u011b\6\uffff\1\u0083\72\uffff\2\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u011c\1\100\4\uffff\1\101\71\uffff\2\116\2"+
            "\121\2\uffff\2\117",
            "\1\u011d",
            "\1\u011e",
            "\1\u011f",
            "\1\u0120",
            "\1\u0121",
            "\1\127\2\uffff\1\u0122\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u0123",
            "\1\u0124",
            "\1\u0125",
            "\1\u0127\1\uffff\1\u0126\1\u0128",
            "\1\u0127\1\u0129",
            "\1\u0127\1\u012a",
            "\1\u00e8\1\u00ce\1\u012b\11\uffff\1\u00cf",
            "\1\u00e8\1\u00ce\12\uffff\1\u00cf",
            "\1\u00e8\1\u00ce\1\u012c\11\uffff\1\u00cf",
            "\1\u012d\1\u00ce\12\uffff\1\u00cf",
            "\1\u012e",
            "\1\u012f",
            "\1\u0130",
            "\1\127\2\uffff\1\u0131\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u0132",
            "\1\u0088\1\157\1\u0133\11\uffff\1\160",
            "\1\u0088\1\157\1\u0134\11\uffff\1\160",
            "\1\127\1\uffff\1\u0135\1\uffff\1\122\1\123\1\124\73\uffff\2"+
            "\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u0136\1\u0092\4\uffff\1\u0093\71\uffff\2\116"+
            "\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u0137\1\uffff\1\u00ae\1\u00af\1\u00b0\73\uffff"+
            "\2\116\2\121\2\uffff\2\117",
            "\1\127\6\uffff\1\u00e5\72\uffff\2\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u0138\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u0139\3\uffff\1\u0111\1\u0112\1\u0113",
            "\1\u013a\1\uffff\1\u0111\1\u0112\1\u0113",
            "\1\u0127",
            "\1\u013c\1\uffff\1\u013b\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u0127",
            "\1\u0127",
            "\1\u0127",
            "\1\u013d",
            "\1\u013e",
            "\1\u00e8\1\u00ce\1\uffff\1\u013f\10\uffff\1\u00cf",
            "\1\127\1\uffff\1\u0140\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u0141\1\uffff\1\u00ba\1\u00bb\1\u00bc",
            "\1\127\1\uffff\1\u0142\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\127\6\uffff\1\u0083\72\uffff\2\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u0143\1\100\4\uffff\1\101\71\uffff\2\116\2"+
            "\121\2\uffff\2\117",
            "\1\u0144",
            "\1\u0145",
            "\1\u0146",
            "\1\u0147",
            "\1\u0148",
            "\1\u0149",
            "\1\u014a",
            "\1\u014b",
            "\1\u014c",
            "\1\127\2\uffff\1\u014d\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u00e8\1\u00ce\1\u014e\11\uffff\1\u00cf",
            "\1\u00e8\1\u00ce\1\u014f\11\uffff\1\u00cf",
            "\1\u00e8\1\u00ce\12\uffff\1\u00cf",
            "\1\u0150",
            "\1\u0151",
            "\1\u0152",
            "\1\u0153",
            "\1\u0088\1\157\1\u0154\11\uffff\1\160",
            "\1\u0088\1\157\1\u0155\11\uffff\1\160",
            "\1\127\1\uffff\1\u0156\1\uffff\1\122\1\123\1\124\73\uffff\2"+
            "\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u0157\1\u0092\4\uffff\1\u0093\71\uffff\2\116"+
            "\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u0158\1\uffff\1\u00ae\1\u00af\1\u00b0\73\uffff"+
            "\2\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u0159\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u0111\1\u0112\1\u0113",
            "\1\u015a\1\uffff\1\u0111\1\u0112\1\u0113",
            "\1\127\1\uffff\1\u015b\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u015c\6\uffff\1\u00e5\72\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u015d",
            "\1\u015e",
            "\1\127\1\uffff\1\u015f\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u0160\1\uffff\1\u00ba\1\u00bb\1\u00bc",
            "\1\127\1\uffff\1\u0161\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\127\1\uffff\1\u0162\1\100\4\uffff\1\101\71\uffff\2\116\2"+
            "\121\2\uffff\2\117",
            "\1\u0163",
            "\1\u0164",
            "\1\u0165",
            "\1\u0166",
            "\1\u0167",
            "\1\u0168",
            "\1\u0169",
            "\1\u016a",
            "\1\127\2\uffff\1\u016b\76\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u00e8\1\u00ce\1\u016c\11\uffff\1\u00cf",
            "\1\u00e8\1\u00ce\1\u016d\11\uffff\1\u00cf",
            "\1\u016e",
            "\1\u016f",
            "\1\u0170",
            "\1\u0171",
            "\1\u0088\1\157\1\u0172\11\uffff\1\160",
            "\1\u0088\1\157\1\u0173\11\uffff\1\160",
            "\1\127\1\uffff\1\u0174\1\uffff\1\122\1\123\1\124\73\uffff\2"+
            "\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u0175\1\u0092\4\uffff\1\u0093\71\uffff\2\116"+
            "\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u0176\1\uffff\1\u00ae\1\u00af\1\u00b0\73\uffff"+
            "\2\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u0177\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u0178\1\uffff\1\u0111\1\u0112\1\u0113",
            "\1\127\1\uffff\1\u0179\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\127\6\uffff\1\u00e5\72\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u017a",
            "\1\u017b",
            "\1\127\1\uffff\1\u017c\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u017d\1\uffff\1\u00ba\1\u00bb\1\u00bc",
            "\1\127\1\uffff\1\u017e\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\127\1\uffff\1\u017f\1\100\4\uffff\1\101\71\uffff\2\116\2"+
            "\121\2\uffff\2\117",
            "\1\u0180",
            "\1\u0181",
            "\1\u0182",
            "\1\u0183",
            "\1\u0184",
            "\1\u0185",
            "\1\u0186",
            "\1\u0187",
            "\1\u00e8\1\u00ce\1\u0188\11\uffff\1\u00cf",
            "\1\u00e8\1\u00ce\1\u0189\11\uffff\1\u00cf",
            "\1\u018a",
            "\1\u018b",
            "\1\u018c",
            "\1\u018d",
            "\1\u0088\1\157\1\u018e\11\uffff\1\160",
            "\1\u0088\1\157\1\u018f\11\uffff\1\160",
            "\1\127\1\uffff\1\u0190\1\uffff\1\122\1\123\1\124\73\uffff\2"+
            "\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u0191\1\u0092\4\uffff\1\u0093\71\uffff\2\116"+
            "\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u0192\1\uffff\1\u00ae\1\u00af\1\u00b0\73\uffff"+
            "\2\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u0193\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u0194\1\uffff\1\u0111\1\u0112\1\u0113",
            "\1\127\1\uffff\1\u0195\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u0196",
            "\1\u0197",
            "\1\127\1\uffff\1\u0198\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u0199\1\uffff\1\u00ba\1\u00bb\1\u00bc",
            "\1\127\1\uffff\1\u019a\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\127\1\uffff\1\u019b\1\100\4\uffff\1\101\71\uffff\2\116\2"+
            "\121\2\uffff\2\117",
            "\1\u019c",
            "\1\u019d",
            "\1\u019e",
            "\1\u019f",
            "\1\u01a0",
            "\1\u01a1",
            "\1\u01a2",
            "\1\u01a3",
            "\1\u00e8\1\u00ce\1\u01a4\11\uffff\1\u00cf",
            "\1\u00e8\1\u00ce\1\u01a5\11\uffff\1\u00cf",
            "\1\u01a6",
            "\1\u01a7",
            "\1\u01a8",
            "\1\u01a9",
            "\1\u0088\1\157\1\u01aa\11\uffff\1\160",
            "\1\u0088\1\157\1\u01ab\11\uffff\1\160",
            "\1\127\3\uffff\1\122\1\123\1\124\73\uffff\2\116\2\121\2\uffff"+
            "\2\117",
            "\1\127\1\uffff\1\u01ac\1\u0092\4\uffff\1\u0093\71\uffff\2\116"+
            "\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u01ad\1\uffff\1\u00ae\1\u00af\1\u00b0\73\uffff"+
            "\2\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u01ae\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u01af\1\uffff\1\u0111\1\u0112\1\u0113",
            "\1\127\1\uffff\1\u01b0\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u01b1",
            "\1\u01b2",
            "\1\127\1\uffff\1\u01b3\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u01b4\1\uffff\1\u00ba\1\u00bb\1\u00bc",
            "\1\127\1\uffff\1\u01b5\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\127\2\uffff\1\100\4\uffff\1\101\71\uffff\2\116\2\121\2\uffff"+
            "\2\117",
            "\1\u01b6",
            "\1\u01b7",
            "\1\u01b8",
            "\1\u01b9",
            "\1\u01ba",
            "\1\u01bb",
            "\1\u01bc",
            "\1\u00e8\1\u00ce\1\u01bd\11\uffff\1\u00cf",
            "\1\u00e8\1\u00ce\1\u01be\11\uffff\1\u00cf",
            "\1\u01bf",
            "\1\u01c0",
            "\1\u01c1",
            "\1\u0088\1\157\12\uffff\1\160",
            "\1\u0088\1\157\1\u01c2\11\uffff\1\160",
            "\1\127\1\uffff\1\u01c3\1\u0092\4\uffff\1\u0093\71\uffff\2\116"+
            "\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u01c4\1\uffff\1\u00ae\1\u00af\1\u00b0\73\uffff"+
            "\2\116\2\121\2\uffff\2\117",
            "\1\127\1\uffff\1\u01c5\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u01c6\1\uffff\1\u0111\1\u0112\1\u0113",
            "\1\127\1\uffff\1\u01c7\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u01c8",
            "\1\u01c9",
            "\1\127\6\uffff\1\u0083\72\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u01ca\1\uffff\1\u00ba\1\u00bb\1\u00bc",
            "\1\127\1\uffff\1\u01cb\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u01cc",
            "\1\u01cd",
            "\1\u01ce",
            "\1\u01cf",
            "\1\u01d0",
            "\1\u01d1",
            "\1\u00e8\1\u00ce\1\u01d2\11\uffff\1\u00cf",
            "\1\u00e8\1\u00ce\1\u01d3\11\uffff\1\u00cf",
            "\1\u01d4",
            "\1\u01d5",
            "\1\u0088\1\157\12\uffff\1\160",
            "\1\127\2\uffff\1\u0092\4\uffff\1\u0093\71\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\127\3\uffff\1\u00ae\1\u00af\1\u00b0\73\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\127\1\uffff\1\u01d6\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u01d7\1\uffff\1\u0111\1\u0112\1\u0113",
            "\1\127\1\uffff\1\u01d8\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u01d9",
            "\1\u01da",
            "\1\u00ba\1\u00bb\1\u00bc",
            "\1\127\1\uffff\1\u01db\4\uffff\1\u0083\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u01dc",
            "\1\u01dd",
            "\1\u01de",
            "\1\u00e8\1\u00ce\12\uffff\1\u00cf",
            "\1\u00e8\1\u00ce\1\u01df\11\uffff\1\u00cf",
            "\1\u01e0",
            "\1\127\6\uffff\1\u00e5\72\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u01e1\1\uffff\1\u0111\1\u0112\1\u0113",
            "\1\127\1\uffff\1\u01e2\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u01e3",
            "\1\127\6\uffff\1\u0083\72\uffff\2\116\2\121\2\uffff\2\117",
            "\1\u01e4",
            "\1\u01e5",
            "\1\u00e8\1\u00ce\12\uffff\1\u00cf",
            "\1\u0111\1\u0112\1\u0113",
            "\1\127\1\uffff\1\u01e6\4\uffff\1\u00e5\72\uffff\2\116\2\121"+
            "\2\uffff\2\117",
            "\1\u01e7",
            "\1\127\6\uffff\1\u00e5\72\uffff\2\116\2\121\2\uffff\2\117"
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
        "\2\111\2\uffff";
    static final String DFA4_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA4_specialS =
        "\4\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\1\1\3\13\uffff\2\2\65\uffff\2\2",
            "\1\1\1\3\13\uffff\2\2\65\uffff\2\2",
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
        "\2\115\2\uffff";
    static final String DFA6_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA6_specialS =
        "\4\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\1\101\uffff\2\3\2\2\2\uffff\2\3",
            "\1\1\101\uffff\2\3\2\2\2\uffff\2\3",
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
        "\1\23\4\4\1\uffff\1\24\2\uffff\11\4\1\6\2\4\1\24\3\4";
    static final String DFA7_maxS =
        "\1\126\1\123\2\17\1\123\1\uffff\1\101\2\uffff\1\17\1\40\1\17\1\40"+
        "\2\123\1\40\1\20\1\40\1\6\1\20\1\123\1\71\2\20\1\123";
    static final String DFA7_acceptS =
        "\5\uffff\1\3\1\uffff\1\2\1\1\20\uffff";
    static final String DFA7_specialS =
        "\31\uffff}>";
    static final String[] DFA7_transitionS = {
            "\16\1\16\uffff\1\2\44\uffff\1\2\2\3",
            "\1\4\1\uffff\1\6\1\uffff\3\10\103\uffff\2\7\2\uffff\2\5",
            "\1\11\12\uffff\1\12",
            "\1\13\12\uffff\1\14",
            "\1\4\3\uffff\3\10\103\uffff\2\7\2\uffff\2\5",
            "",
            "\2\15\13\uffff\31\15\10\16",
            "",
            "",
            "\1\11\12\uffff\1\12",
            "\1\17\16\uffff\16\20",
            "\1\13\12\uffff\1\14",
            "\1\21\16\uffff\16\22",
            "\1\4\3\uffff\3\10\103\uffff\2\7\2\uffff\2\5",
            "\1\4\3\uffff\3\10\103\uffff\2\7\2\uffff\2\5",
            "\1\17\16\uffff\16\20",
            "\1\23\13\uffff\1\24",
            "\1\21\16\uffff\16\22",
            "\1\25",
            "\1\23\13\uffff\1\24",
            "\1\4\3\uffff\3\10\103\uffff\2\7\2\uffff\2\5",
            "\2\26\13\uffff\31\26",
            "\1\27\13\uffff\1\30",
            "\1\27\13\uffff\1\30",
            "\1\4\3\uffff\3\10\103\uffff\2\7\2\uffff\2\5"
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
            return "54:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= likeValue );";
        }
    }
    static final String DFA9_eotS =
        "\4\uffff";
    static final String DFA9_eofS =
        "\4\uffff";
    static final String DFA9_minS =
        "\2\4\2\uffff";
    static final String DFA9_maxS =
        "\2\20\2\uffff";
    static final String DFA9_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA9_specialS =
        "\4\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\1\1\3\12\uffff\1\2",
            "\1\1\1\3\12\uffff\1\2",
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
            return "()* loopback of 90:21: ( spaces COMMA spaces dotValue )*";
        }
    }
 

    public static final BitSet FOLLOW_select_in_stmt27 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt29 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_selectList_in_stmt31 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt33 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_where_in_stmt35 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt37 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_constraintList_in_stmt39 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt41 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt47 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt49 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_selectList_in_stmt51 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt53 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt58 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt60 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_selectList_in_stmt62 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000300L});
    public static final BitSet FOLLOW_spaces_in_stmt64 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_order_in_stmt66 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000C00L});
    public static final BitSet FOLLOW_spaces_in_stmt68 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000C00L});
    public static final BitSet FOLLOW_by_in_stmt70 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt72 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_orderList_in_stmt74 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt76 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt81 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt83 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_selectList_in_stmt85 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt87 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_where_in_stmt89 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt91 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_constraintList_in_stmt93 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000300L});
    public static final BitSet FOLLOW_spaces_in_stmt95 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_order_in_stmt97 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000C00L});
    public static final BitSet FOLLOW_spaces_in_stmt99 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000C00L});
    public static final BitSet FOLLOW_by_in_stmt101 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt103 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_orderList_in_stmt105 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SPACE_in_spaces117 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_keyword_in_orderList130 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_orderList143 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_orderList147 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_orderList151 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_keyword_in_orderList158 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_keyword_in_selectList184 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_selectList197 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_selectList201 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_selectList205 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_keyword_in_selectList212 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_entity_in_keyword237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword243 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword245 = new BitSet(new long[]{0x03FFFFFE00300000L});
    public static final BitSet FOLLOW_attr_in_keyword247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword252 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword254 = new BitSet(new long[]{0xFC00000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_funct_in_keyword256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_count_in_keyword261 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword263 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword265 = new BitSet(new long[]{0x00000001FFF80010L});
    public static final BitSet FOLLOW_spaces_in_keyword267 = new BitSet(new long[]{0x00000001FFF80000L});
    public static final BitSet FOLLOW_entity_in_keyword269 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword271 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sum_in_keyword278 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword280 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword282 = new BitSet(new long[]{0x00000001FFF80010L});
    public static final BitSet FOLLOW_spaces_in_keyword284 = new BitSet(new long[]{0x00000001FFF80000L});
    public static final BitSet FOLLOW_entity_in_keyword286 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword288 = new BitSet(new long[]{0x03FFFFFE00300000L});
    public static final BitSet FOLLOW_attr_in_keyword290 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword292 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_constraintList303 = new BitSet(new long[]{0x0000000000000012L,0x00000000000030C0L});
    public static final BitSet FOLLOW_spaces_in_constraintList307 = new BitSet(new long[]{0x0000000000000000L,0x00000000000030C0L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList314 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_constraintList322 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_constraint_in_constraintList324 = new BitSet(new long[]{0x0000000000000012L,0x00000000000030C0L});
    public static final BitSet FOLLOW_keyword_in_constraint337 = new BitSet(new long[]{0x0000000000000710L});
    public static final BitSet FOLLOW_spaces_in_constraint346 = new BitSet(new long[]{0x0000000000000700L});
    public static final BitSet FOLLOW_compOpt_in_constraint353 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_spaces_in_constraint363 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_genValue_in_constraint370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint399 = new BitSet(new long[]{0x0000000000000010L,0x000000000000C000L});
    public static final BitSet FOLLOW_spaces_in_constraint408 = new BitSet(new long[]{0x0000000000000000L,0x000000000000C000L});
    public static final BitSet FOLLOW_in_in_constraint415 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_constraint426 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_constraint428 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_spaces_in_constraint432 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_valueList_in_constraint438 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_constraint446 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_constraint450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint476 = new BitSet(new long[]{0x0000000000000010L,0x00000000000C0000L});
    public static final BitSet FOLLOW_spaces_in_constraint485 = new BitSet(new long[]{0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_like_in_constraint492 = new BitSet(new long[]{0x0000000000001090L});
    public static final BitSet FOLLOW_spaces_in_constraint501 = new BitSet(new long[]{0x0000000000001080L});
    public static final BitSet FOLLOW_likeValue_in_constraint508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue564 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue566 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue574 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue576 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue578 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue580 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue588 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue590 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue592 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue594 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue596 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue598 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue606 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue608 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue610 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue612 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue614 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue616 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue618 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue620 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue628 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue630 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue632 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue634 = new BitSet(new long[]{0x0000000000000080L});
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
    public static final BitSet FOLLOW_DOT_in_dotValue668 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue670 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue672 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue674 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue676 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue684 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue686 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue688 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue690 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue692 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue694 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue696 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue698 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue700 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue702 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue704 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue706 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue708 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue710 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue718 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue720 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue722 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue724 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue726 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue728 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue730 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue732 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue734 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue736 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue738 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue740 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue742 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue744 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue746 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue748 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue757 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue759 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue761 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue763 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue765 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue767 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue769 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue771 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue773 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue775 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue777 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue779 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue781 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue783 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue785 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue787 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue789 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue791 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue800 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue802 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue810 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue812 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue814 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue816 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_valueList826 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_valueList830 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_valueList832 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_spaces_in_valueList834 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_dotValue_in_valueList836 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_EQ_in_compOpt847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt868 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt878 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LT_in_compOpt881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt888 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt898 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue913 = new BitSet(new long[]{0x0000000000000700L});
    public static final BitSet FOLLOW_compOpt_in_genValue915 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_dotValue_in_genValue917 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_AMP_in_genValue920 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_dotValue_in_genValue922 = new BitSet(new long[]{0x0000000000000700L});
    public static final BitSet FOLLOW_compOpt_in_genValue924 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_dotValue_in_genValue926 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_dotValue_in_likeValue936 = new BitSet(new long[]{0x0000000000001082L});
    public static final BitSet FOLLOW_STAR_in_likeValue939 = new BitSet(new long[]{0x0000000000001082L});
    public static final BitSet FOLLOW_and_in_logicalOp948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr1020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct1134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select1172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and1193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_order1206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_by1219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in1245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not1258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_like1271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_count1284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sum1297 = new BitSet(new long[]{0x0000000000000002L});

}