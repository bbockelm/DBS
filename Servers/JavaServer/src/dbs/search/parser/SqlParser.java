package dbs.search.parser;
// $ANTLR 3.0.1 Sql.g 2008-07-14 10:40:44


import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SPACE", "COMMA", "DOT", "VALUE", "EQ", "LT", "GT", "AMP", "STAR", "NL", "WS", "'('", "')'", "'WHERE'", "'where'", "'ads'", "'dataset'", "'release'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'lumi'", "'dq'", "'ilumi'", "'phygrp'", "'group'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numlss'", "'size'", "'count'", "'status'", "'type'", "'id'", "'parent'", "'child'", "'tier'", "'def'", "'evnum'", "'era'", "'tag'", "'numruns()'", "'numfiles()'", "'dataquality()'", "'latest()'", "'parentrelease()'", "'childrelease()'", "'intluminosity()'", "'findevents()'", "'select'", "'SELECT'", "'find'", "'FIND'", "'and'", "'AND'", "'order'", "'ORDER'", "'by'", "'BY'", "'or'", "'OR'", "'in'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'", "'COUNT'", "'sum'", "'SUM'"
    };
    public static final int LT=9;
    public static final int WS=14;
    public static final int STAR=12;
    public static final int COMMA=5;
    public static final int AMP=11;
    public static final int GT=10;
    public static final int NL=13;
    public static final int EQ=8;
    public static final int VALUE=7;
    public static final int DOT=6;
    public static final int EOF=-1;
    public static final int SPACE=4;

        public SqlParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "Sql.g"; }


    ArrayList kws = new ArrayList();
    ArrayList okws = new ArrayList();
    ArrayList constraints = new ArrayList();



    // $ANTLR start stmt
    // Sql.g:20:1: stmt : ( select spaces selectList spaces where spaces constraintList spaces | select spaces selectList spaces | select spaces selectList spaces order spaces by spaces okw= keyword spaces | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces okw= keyword spaces );
    public final void stmt() throws RecognitionException {
        keyword_return okw = null;


        try {
            // Sql.g:20:6: ( select spaces selectList spaces where spaces constraintList spaces | select spaces selectList spaces | select spaces selectList spaces order spaces by spaces okw= keyword spaces | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces okw= keyword spaces )
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
                    // Sql.g:22:4: select spaces selectList spaces order spaces by spaces okw= keyword spaces
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

                    pushFollow(FOLLOW_keyword_in_stmt77);
                    okw=keyword();
                    _fsp--;

                    okws.add(input.toString(okw.start,okw.stop));
                    pushFollow(FOLLOW_spaces_in_stmt83);
                    spaces();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Sql.g:25:4: select spaces selectList spaces where spaces constraintList spaces order spaces by spaces okw= keyword spaces
                    {
                    pushFollow(FOLLOW_select_in_stmt88);
                    select();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt90);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_selectList_in_stmt92);
                    selectList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt94);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_where_in_stmt96);
                    where();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt98);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_constraintList_in_stmt100);
                    constraintList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt102);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_order_in_stmt104);
                    order();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt106);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_by_in_stmt108);
                    by();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt110);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_keyword_in_stmt115);
                    okw=keyword();
                    _fsp--;

                    okws.add(input.toString(okw.start,okw.stop));
                    pushFollow(FOLLOW_spaces_in_stmt121);
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
            	    match(input,SPACE,FOLLOW_SPACE_in_spaces131); 

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
            pushFollow(FOLLOW_keyword_in_selectList146);
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
            	    pushFollow(FOLLOW_spaces_in_selectList159);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_selectList163); 
            	    pushFollow(FOLLOW_spaces_in_selectList167);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_keyword_in_selectList174);
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
    // Sql.g:40:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );
    public final keyword_return keyword() throws RecognitionException {
        keyword_return retval = new keyword_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:40:9: ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' )
            int alt4=5;
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
                int LA4_1 = input.LA(2);

                if ( (LA4_1==DOT) ) {
                    int LA4_4 = input.LA(3);

                    if ( ((LA4_4>=20 && LA4_4<=21)||(LA4_4>=33 && LA4_4<=57)) ) {
                        alt4=2;
                    }
                    else if ( ((LA4_4>=58 && LA4_4<=65)) ) {
                        alt4=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("40:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 4, 4, input);

                        throw nvae;
                    }
                }
                else if ( (LA4_1==EOF||(LA4_1>=SPACE && LA4_1<=COMMA)||(LA4_1>=EQ && LA4_1<=GT)||(LA4_1>=17 && LA4_1<=18)||(LA4_1>=72 && LA4_1<=73)||(LA4_1>=78 && LA4_1<=79)||(LA4_1>=82 && LA4_1<=83)) ) {
                    alt4=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("40:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 4, 1, input);

                    throw nvae;
                }
                }
                break;
            case 47:
            case 84:
                {
                alt4=4;
                }
                break;
            case 85:
            case 86:
                {
                alt4=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("40:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Sql.g:40:11: entity
                    {
                    pushFollow(FOLLOW_entity_in_keyword199);
                    entity();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:41:4: entity DOT attr
                    {
                    pushFollow(FOLLOW_entity_in_keyword205);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword207); 
                    pushFollow(FOLLOW_attr_in_keyword209);
                    attr();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Sql.g:42:4: entity DOT funct
                    {
                    pushFollow(FOLLOW_entity_in_keyword214);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword216); 
                    pushFollow(FOLLOW_funct_in_keyword218);
                    funct();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Sql.g:43:4: count spaces '(' spaces entity spaces ')'
                    {
                    pushFollow(FOLLOW_count_in_keyword223);
                    count();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword225);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_keyword227); 
                    pushFollow(FOLLOW_spaces_in_keyword229);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_entity_in_keyword231);
                    entity();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword233);
                    spaces();
                    _fsp--;

                    match(input,16,FOLLOW_16_in_keyword235); 

                    }
                    break;
                case 5 :
                    // Sql.g:44:4: sum spaces '(' spaces entity DOT attr spaces ')'
                    {
                    pushFollow(FOLLOW_sum_in_keyword240);
                    sum();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword242);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_keyword244); 
                    pushFollow(FOLLOW_spaces_in_keyword246);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_entity_in_keyword248);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword250); 
                    pushFollow(FOLLOW_attr_in_keyword252);
                    attr();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword254);
                    spaces();
                    _fsp--;

                    match(input,16,FOLLOW_16_in_keyword256); 

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
    // Sql.g:46:1: constraintList : constraint ( spaces rel= logicalOp spaces constraint )* ;
    public final void constraintList() throws RecognitionException {
        logicalOp_return rel = null;


        try {
            // Sql.g:46:16: ( constraint ( spaces rel= logicalOp spaces constraint )* )
            // Sql.g:46:18: constraint ( spaces rel= logicalOp spaces constraint )*
            {
            pushFollow(FOLLOW_constraint_in_constraintList265);
            constraint();
            _fsp--;

            // Sql.g:46:29: ( spaces rel= logicalOp spaces constraint )*
            loop5:
            do {
                int alt5=2;
                alt5 = dfa5.predict(input);
                switch (alt5) {
            	case 1 :
            	    // Sql.g:46:31: spaces rel= logicalOp spaces constraint
            	    {
            	    pushFollow(FOLLOW_spaces_in_constraintList269);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_logicalOp_in_constraintList276);
            	    rel=logicalOp();
            	    _fsp--;

            	     constraints.add(input.toString(rel.start,rel.stop));
            	    pushFollow(FOLLOW_spaces_in_constraintList284);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_constraint_in_constraintList286);
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
    // Sql.g:50:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= likeValue );
    public final void constraint() throws RecognitionException {
        keyword_return kw = null;

        compOpt_return op = null;

        genValue_return val = null;

        in_return op1 = null;

        valueList_return val1 = null;

        like_return op2 = null;

        likeValue_return val2 = null;


        try {
            // Sql.g:50:12: (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= likeValue )
            int alt6=3;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // Sql.g:50:14: kw= keyword spaces op= compOpt spaces val= genValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint299);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint308);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_compOpt_in_constraint315);
                    op=compOpt();
                    _fsp--;

                    c.setOp(input.toString(op.start,op.stop));
                    pushFollow(FOLLOW_spaces_in_constraint325);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_genValue_in_constraint332);
                    val=genValue();
                    _fsp--;

                    c.setValue(input.toString(val.start,val.stop)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:56:2: kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')'
                    {
                    pushFollow(FOLLOW_keyword_in_constraint361);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint370);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_in_in_constraint377);
                    op1=in();
                    _fsp--;

                    c.setOp(input.toString(op1.start,op1.stop));
                    pushFollow(FOLLOW_spaces_in_constraint388);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_constraint390); 
                    pushFollow(FOLLOW_spaces_in_constraint394);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_valueList_in_constraint400);
                    val1=valueList();
                    _fsp--;

                    c.setValue(input.toString(val1.start,val1.stop)); constraints.add(c);
                    pushFollow(FOLLOW_spaces_in_constraint408);
                    spaces();
                    _fsp--;

                    match(input,16,FOLLOW_16_in_constraint412); 

                    }
                    break;
                case 3 :
                    // Sql.g:65:2: kw= keyword spaces op2= like spaces val2= likeValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint438);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint447);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_like_in_constraint454);
                    op2=like();
                    _fsp--;

                    c.setOp(input.toString(op2.start,op2.stop));
                    pushFollow(FOLLOW_spaces_in_constraint463);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_likeValue_in_constraint470);
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
    // Sql.g:71:1: where : ( 'WHERE' | 'where' ) ;
    public final void where() throws RecognitionException {
        try {
            // Sql.g:71:7: ( ( 'WHERE' | 'where' ) )
            // Sql.g:71:8: ( 'WHERE' | 'where' )
            {
            if ( (input.LA(1)>=17 && input.LA(1)<=18) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_where499);    throw mse;
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
    // Sql.g:72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );
    public final void dotValue() throws RecognitionException {
        try {
            // Sql.g:72:17: ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE )
            int alt7=12;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==VALUE) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA7_2 = input.LA(3);

                    if ( (LA7_2==VALUE) ) {
                        int LA7_5 = input.LA(4);

                        if ( (LA7_5==DOT) ) {
                            int LA7_7 = input.LA(5);

                            if ( (LA7_7==VALUE) ) {
                                int LA7_11 = input.LA(6);

                                if ( (LA7_11==DOT) ) {
                                    int LA7_13 = input.LA(7);

                                    if ( (LA7_13==VALUE) ) {
                                        int LA7_15 = input.LA(8);

                                        if ( (LA7_15==DOT) ) {
                                            int LA7_16 = input.LA(9);

                                            if ( (LA7_16==VALUE) ) {
                                                int LA7_18 = input.LA(10);

                                                if ( (LA7_18==DOT) ) {
                                                    int LA7_19 = input.LA(11);

                                                    if ( (LA7_19==VALUE) ) {
                                                        int LA7_21 = input.LA(12);

                                                        if ( (LA7_21==DOT) ) {
                                                            int LA7_22 = input.LA(13);

                                                            if ( (LA7_22==VALUE) ) {
                                                                int LA7_24 = input.LA(14);

                                                                if ( (LA7_24==DOT) ) {
                                                                    int LA7_25 = input.LA(15);

                                                                    if ( (LA7_25==VALUE) ) {
                                                                        int LA7_27 = input.LA(16);

                                                                        if ( (LA7_27==DOT) ) {
                                                                            int LA7_28 = input.LA(17);

                                                                            if ( (LA7_28==VALUE) ) {
                                                                                int LA7_30 = input.LA(18);

                                                                                if ( (LA7_30==DOT) ) {
                                                                                    alt7=10;
                                                                                }
                                                                                else if ( (LA7_30==EOF||(LA7_30>=SPACE && LA7_30<=COMMA)||(LA7_30>=VALUE && LA7_30<=STAR)||LA7_30==16||(LA7_30>=70 && LA7_30<=73)||(LA7_30>=76 && LA7_30<=77)) ) {
                                                                                    alt7=9;
                                                                                }
                                                                                else {
                                                                                    NoViableAltException nvae =
                                                                                        new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 30, input);

                                                                                    throw nvae;
                                                                                }
                                                                            }
                                                                            else {
                                                                                NoViableAltException nvae =
                                                                                    new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 28, input);

                                                                                throw nvae;
                                                                            }
                                                                        }
                                                                        else if ( (LA7_27==EOF||(LA7_27>=SPACE && LA7_27<=COMMA)||(LA7_27>=VALUE && LA7_27<=STAR)||LA7_27==16||(LA7_27>=70 && LA7_27<=73)||(LA7_27>=76 && LA7_27<=77)) ) {
                                                                            alt7=8;
                                                                        }
                                                                        else {
                                                                            NoViableAltException nvae =
                                                                                new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 27, input);

                                                                            throw nvae;
                                                                        }
                                                                    }
                                                                    else {
                                                                        NoViableAltException nvae =
                                                                            new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 25, input);

                                                                        throw nvae;
                                                                    }
                                                                }
                                                                else if ( (LA7_24==EOF||(LA7_24>=SPACE && LA7_24<=COMMA)||(LA7_24>=VALUE && LA7_24<=STAR)||LA7_24==16||(LA7_24>=70 && LA7_24<=73)||(LA7_24>=76 && LA7_24<=77)) ) {
                                                                    alt7=7;
                                                                }
                                                                else {
                                                                    NoViableAltException nvae =
                                                                        new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 24, input);

                                                                    throw nvae;
                                                                }
                                                            }
                                                            else {
                                                                NoViableAltException nvae =
                                                                    new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 22, input);

                                                                throw nvae;
                                                            }
                                                        }
                                                        else if ( (LA7_21==EOF||(LA7_21>=SPACE && LA7_21<=COMMA)||(LA7_21>=VALUE && LA7_21<=STAR)||LA7_21==16||(LA7_21>=70 && LA7_21<=73)||(LA7_21>=76 && LA7_21<=77)) ) {
                                                            alt7=6;
                                                        }
                                                        else {
                                                            NoViableAltException nvae =
                                                                new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 21, input);

                                                            throw nvae;
                                                        }
                                                    }
                                                    else {
                                                        NoViableAltException nvae =
                                                            new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 19, input);

                                                        throw nvae;
                                                    }
                                                }
                                                else if ( (LA7_18==EOF||(LA7_18>=SPACE && LA7_18<=COMMA)||(LA7_18>=VALUE && LA7_18<=STAR)||LA7_18==16||(LA7_18>=70 && LA7_18<=73)||(LA7_18>=76 && LA7_18<=77)) ) {
                                                    alt7=5;
                                                }
                                                else {
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 18, input);

                                                    throw nvae;
                                                }
                                            }
                                            else {
                                                NoViableAltException nvae =
                                                    new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 16, input);

                                                throw nvae;
                                            }
                                        }
                                        else if ( (LA7_15==EOF||(LA7_15>=SPACE && LA7_15<=COMMA)||(LA7_15>=VALUE && LA7_15<=STAR)||LA7_15==16||(LA7_15>=70 && LA7_15<=73)||(LA7_15>=76 && LA7_15<=77)) ) {
                                            alt7=4;
                                        }
                                        else {
                                            NoViableAltException nvae =
                                                new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 15, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 13, input);

                                        throw nvae;
                                    }
                                }
                                else if ( (LA7_11==EOF||(LA7_11>=SPACE && LA7_11<=COMMA)||(LA7_11>=VALUE && LA7_11<=STAR)||LA7_11==16||(LA7_11>=70 && LA7_11<=73)||(LA7_11>=76 && LA7_11<=77)) ) {
                                    alt7=3;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 11, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 7, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA7_5==EOF||(LA7_5>=SPACE && LA7_5<=COMMA)||(LA7_5>=VALUE && LA7_5<=STAR)||LA7_5==16||(LA7_5>=70 && LA7_5<=73)||(LA7_5>=76 && LA7_5<=77)) ) {
                            alt7=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 5, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case SPACE:
                    {
                    int LA7_3 = input.LA(3);

                    if ( (LA7_3==VALUE) ) {
                        int LA7_6 = input.LA(4);

                        if ( (LA7_6==SPACE) ) {
                            int LA7_9 = input.LA(5);

                            if ( (LA7_9==VALUE) ) {
                                alt7=12;
                            }
                            else if ( (LA7_9==EOF||(LA7_9>=SPACE && LA7_9<=COMMA)||LA7_9==16||(LA7_9>=70 && LA7_9<=73)||(LA7_9>=76 && LA7_9<=77)) ) {
                                alt7=11;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 9, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA7_6==EOF||LA7_6==COMMA||(LA7_6>=VALUE && LA7_6<=STAR)||LA7_6==16||(LA7_6>=70 && LA7_6<=73)||(LA7_6>=76 && LA7_6<=77)) ) {
                            alt7=11;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 6, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA7_3==EOF||(LA7_3>=SPACE && LA7_3<=COMMA)||LA7_3==16||(LA7_3>=70 && LA7_3<=73)||(LA7_3>=76 && LA7_3<=77)) ) {
                        alt7=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 3, input);

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
                    alt7=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // Sql.g:72:19: VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue519); 

                    }
                    break;
                case 2 :
                    // Sql.g:73:5: VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue526); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue528); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue530); 

                    }
                    break;
                case 3 :
                    // Sql.g:74:5: VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue536); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue538); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue540); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue542); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue544); 

                    }
                    break;
                case 4 :
                    // Sql.g:75:5: VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue550); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue552); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue554); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue556); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue558); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue560); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue562); 

                    }
                    break;
                case 5 :
                    // Sql.g:76:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue568); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue570); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue572); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue574); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue576); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue578); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue580); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue582); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue584); 

                    }
                    break;
                case 6 :
                    // Sql.g:77:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue590); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue592); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue594); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue596); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue598); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue600); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue602); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue604); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue606); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue608); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue610); 

                    }
                    break;
                case 7 :
                    // Sql.g:78:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue616); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue618); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue620); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue622); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue624); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue626); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue628); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue630); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue632); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue634); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue636); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue638); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue640); 

                    }
                    break;
                case 8 :
                    // Sql.g:79:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue646); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue648); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue650); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue652); 
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

                    }
                    break;
                case 9 :
                    // Sql.g:80:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue680); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue682); 
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
                case 10 :
                    // Sql.g:81:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue719); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue721); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue723); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue725); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue727); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue729); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue731); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue733); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue735); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue737); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue739); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue741); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue743); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue745); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue747); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue749); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue751); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue753); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue755); 

                    }
                    break;
                case 11 :
                    // Sql.g:82:5: VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue762); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue764); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue766); 

                    }
                    break;
                case 12 :
                    // Sql.g:83:5: VALUE SPACE VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue772); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue774); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue776); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue778); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue780); 

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
    // Sql.g:86:1: valueList : dotValue ( spaces COMMA spaces dotValue )* ;
    public final valueList_return valueList() throws RecognitionException {
        valueList_return retval = new valueList_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:86:11: ( dotValue ( spaces COMMA spaces dotValue )* )
            // Sql.g:86:12: dotValue ( spaces COMMA spaces dotValue )*
            {
            pushFollow(FOLLOW_dotValue_in_valueList788);
            dotValue();
            _fsp--;

            // Sql.g:86:21: ( spaces COMMA spaces dotValue )*
            loop8:
            do {
                int alt8=2;
                alt8 = dfa8.predict(input);
                switch (alt8) {
            	case 1 :
            	    // Sql.g:86:23: spaces COMMA spaces dotValue
            	    {
            	    pushFollow(FOLLOW_spaces_in_valueList792);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_valueList794); 
            	    pushFollow(FOLLOW_spaces_in_valueList796);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_dotValue_in_valueList798);
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

    public static class compOpt_return extends ParserRuleReturnScope {
    };

    // $ANTLR start compOpt
    // Sql.g:87:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );
    public final compOpt_return compOpt() throws RecognitionException {
        compOpt_return retval = new compOpt_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:87:10: ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) )
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
                case LT:
                    {
                    alt9=5;
                    }
                    break;
                case SPACE:
                case VALUE:
                    {
                    alt9=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("87:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 9, 1, input);

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
                else if ( (LA9_2==SPACE||LA9_2==VALUE) ) {
                    alt9=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("87:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 9, 2, input);

                    throw nvae;
                }
                }
                break;
            case GT:
                {
                int LA9_3 = input.LA(2);

                if ( (LA9_3==SPACE||LA9_3==VALUE) ) {
                    alt9=3;
                }
                else if ( (LA9_3==EQ) ) {
                    alt9=7;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("87:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 9, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("87:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // Sql.g:87:11: ( EQ )
                    {
                    // Sql.g:87:11: ( EQ )
                    // Sql.g:87:12: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt809); 

                    }


                    }
                    break;
                case 2 :
                    // Sql.g:88:4: ( LT )
                    {
                    // Sql.g:88:4: ( LT )
                    // Sql.g:88:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt816); 

                    }


                    }
                    break;
                case 3 :
                    // Sql.g:89:4: ( GT )
                    {
                    // Sql.g:89:4: ( GT )
                    // Sql.g:89:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt823); 

                    }


                    }
                    break;
                case 4 :
                    // Sql.g:90:4: ( EQ ) ( GT )
                    {
                    // Sql.g:90:4: ( EQ )
                    // Sql.g:90:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt830); 

                    }

                    // Sql.g:90:8: ( GT )
                    // Sql.g:90:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt833); 

                    }


                    }
                    break;
                case 5 :
                    // Sql.g:91:4: ( EQ ) ( LT )
                    {
                    // Sql.g:91:4: ( EQ )
                    // Sql.g:91:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt840); 

                    }

                    // Sql.g:91:8: ( LT )
                    // Sql.g:91:9: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt843); 

                    }


                    }
                    break;
                case 6 :
                    // Sql.g:92:4: ( LT ) ( EQ )
                    {
                    // Sql.g:92:4: ( LT )
                    // Sql.g:92:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt850); 

                    }

                    // Sql.g:92:8: ( EQ )
                    // Sql.g:92:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt853); 

                    }


                    }
                    break;
                case 7 :
                    // Sql.g:93:4: ( GT ) ( EQ )
                    {
                    // Sql.g:93:4: ( GT )
                    // Sql.g:93:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt860); 

                    }

                    // Sql.g:93:8: ( EQ )
                    // Sql.g:93:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt863); 

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
    // Sql.g:94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );
    public final genValue_return genValue() throws RecognitionException {
        genValue_return retval = new genValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:94:10: ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==VALUE) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA11_2 = input.LA(3);

                    if ( (LA11_2==VALUE) ) {
                        switch ( input.LA(4) ) {
                        case DOT:
                            {
                            int LA11_8 = input.LA(5);

                            if ( (LA11_8==VALUE) ) {
                                switch ( input.LA(6) ) {
                                case DOT:
                                    {
                                    int LA11_12 = input.LA(7);

                                    if ( (LA11_12==VALUE) ) {
                                        switch ( input.LA(8) ) {
                                        case DOT:
                                            {
                                            int LA11_14 = input.LA(9);

                                            if ( (LA11_14==VALUE) ) {
                                                switch ( input.LA(10) ) {
                                                case DOT:
                                                    {
                                                    int LA11_16 = input.LA(11);

                                                    if ( (LA11_16==VALUE) ) {
                                                        switch ( input.LA(12) ) {
                                                        case DOT:
                                                            {
                                                            int LA11_18 = input.LA(13);

                                                            if ( (LA11_18==VALUE) ) {
                                                                switch ( input.LA(14) ) {
                                                                case DOT:
                                                                    {
                                                                    int LA11_20 = input.LA(15);

                                                                    if ( (LA11_20==VALUE) ) {
                                                                        switch ( input.LA(16) ) {
                                                                        case DOT:
                                                                            {
                                                                            int LA11_22 = input.LA(17);

                                                                            if ( (LA11_22==VALUE) ) {
                                                                                switch ( input.LA(18) ) {
                                                                                case DOT:
                                                                                    {
                                                                                    int LA11_24 = input.LA(19);

                                                                                    if ( (LA11_24==VALUE) ) {
                                                                                        int LA11_25 = input.LA(20);

                                                                                        if ( ((LA11_25>=EQ && LA11_25<=GT)) ) {
                                                                                            alt11=2;
                                                                                        }
                                                                                        else if ( (LA11_25==EOF||LA11_25==SPACE||(LA11_25>=70 && LA11_25<=73)||(LA11_25>=76 && LA11_25<=77)) ) {
                                                                                            alt11=1;
                                                                                        }
                                                                                        else {
                                                                                            NoViableAltException nvae =
                                                                                                new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 25, input);

                                                                                            throw nvae;
                                                                                        }
                                                                                    }
                                                                                    else {
                                                                                        NoViableAltException nvae =
                                                                                            new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 24, input);

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
                                                                                case SPACE:
                                                                                case 70:
                                                                                case 71:
                                                                                case 72:
                                                                                case 73:
                                                                                case 76:
                                                                                case 77:
                                                                                    {
                                                                                    alt11=1;
                                                                                    }
                                                                                    break;
                                                                                default:
                                                                                    NoViableAltException nvae =
                                                                                        new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 23, input);

                                                                                    throw nvae;
                                                                                }

                                                                            }
                                                                            else {
                                                                                NoViableAltException nvae =
                                                                                    new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 22, input);

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
                                                                                new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 21, input);

                                                                            throw nvae;
                                                                        }

                                                                    }
                                                                    else {
                                                                        NoViableAltException nvae =
                                                                            new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 20, input);

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
                                                                        new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 19, input);

                                                                    throw nvae;
                                                                }

                                                            }
                                                            else {
                                                                NoViableAltException nvae =
                                                                    new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 18, input);

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
                                                                new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 17, input);

                                                            throw nvae;
                                                        }

                                                    }
                                                    else {
                                                        NoViableAltException nvae =
                                                            new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 16, input);

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
                                                        new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 15, input);

                                                    throw nvae;
                                                }

                                            }
                                            else {
                                                NoViableAltException nvae =
                                                    new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 14, input);

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
                                                new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 13, input);

                                            throw nvae;
                                        }

                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 12, input);

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
                                case SPACE:
                                case 70:
                                case 71:
                                case 72:
                                case 73:
                                case 76:
                                case 77:
                                    {
                                    alt11=1;
                                    }
                                    break;
                                default:
                                    NoViableAltException nvae =
                                        new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 10, input);

                                    throw nvae;
                                }

                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 8, input);

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
                        case SPACE:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 76:
                        case 77:
                            {
                            alt11=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 6, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case SPACE:
                    {
                    int LA11_3 = input.LA(3);

                    if ( (LA11_3==VALUE) ) {
                        switch ( input.LA(4) ) {
                        case SPACE:
                            {
                            int LA11_9 = input.LA(5);

                            if ( (LA11_9==VALUE) ) {
                                int LA11_11 = input.LA(6);

                                if ( ((LA11_11>=EQ && LA11_11<=GT)) ) {
                                    alt11=2;
                                }
                                else if ( (LA11_11==EOF||LA11_11==SPACE||(LA11_11>=70 && LA11_11<=73)||(LA11_11>=76 && LA11_11<=77)) ) {
                                    alt11=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 11, input);

                                    throw nvae;
                                }
                            }
                            else if ( (LA11_9==EOF||LA11_9==SPACE||(LA11_9>=70 && LA11_9<=73)||(LA11_9>=76 && LA11_9<=77)) ) {
                                alt11=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 9, input);

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
                                new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 7, input);

                            throw nvae;
                        }

                    }
                    else if ( (LA11_3==EOF||LA11_3==SPACE||(LA11_3>=70 && LA11_3<=73)||(LA11_3>=76 && LA11_3<=77)) ) {
                        alt11=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 3, input);

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
                case 70:
                case 71:
                case 72:
                case 73:
                case 76:
                case 77:
                    {
                    alt11=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("94:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // Sql.g:94:11: dotValue
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue870);
                    dotValue();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:95:4: dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )*
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue875);
                    dotValue();
                    _fsp--;

                    pushFollow(FOLLOW_compOpt_in_genValue877);
                    compOpt();
                    _fsp--;

                    pushFollow(FOLLOW_dotValue_in_genValue879);
                    dotValue();
                    _fsp--;

                    // Sql.g:95:30: ( AMP dotValue compOpt dotValue )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==AMP) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // Sql.g:95:31: AMP dotValue compOpt dotValue
                    	    {
                    	    match(input,AMP,FOLLOW_AMP_in_genValue882); 
                    	    pushFollow(FOLLOW_dotValue_in_genValue884);
                    	    dotValue();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_compOpt_in_genValue886);
                    	    compOpt();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_dotValue_in_genValue888);
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
    // Sql.g:96:1: likeValue : ( dotValue | STAR )+ ;
    public final likeValue_return likeValue() throws RecognitionException {
        likeValue_return retval = new likeValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:96:12: ( ( dotValue | STAR )+ )
            // Sql.g:96:13: ( dotValue | STAR )+
            {
            // Sql.g:96:13: ( dotValue | STAR )+
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
            	    // Sql.g:96:14: dotValue
            	    {
            	    pushFollow(FOLLOW_dotValue_in_likeValue898);
            	    dotValue();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // Sql.g:96:24: STAR
            	    {
            	    match(input,STAR,FOLLOW_STAR_in_likeValue901); 

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
    // Sql.g:97:1: logicalOp : ( and | or ) ;
    public final logicalOp_return logicalOp() throws RecognitionException {
        logicalOp_return retval = new logicalOp_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:97:11: ( ( and | or ) )
            // Sql.g:97:12: ( and | or )
            {
            // Sql.g:97:12: ( and | or )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=70 && LA13_0<=71)) ) {
                alt13=1;
            }
            else if ( ((LA13_0>=76 && LA13_0<=77)) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("97:12: ( and | or )", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // Sql.g:97:13: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp910);
                    and();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:97:17: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp912);
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
    // Sql.g:98:1: entity : ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' ) ;
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:98:9: ( ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' ) )
            // Sql.g:98:11: ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' )
            {
            if ( (input.LA(1)>=19 && input.LA(1)<=32) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_entity921);    throw mse;
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
    // Sql.g:99:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) ;
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:99:7: ( ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) )
            // Sql.g:99:8: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' )
            {
            if ( (input.LA(1)>=20 && input.LA(1)<=21)||(input.LA(1)>=33 && input.LA(1)<=57) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_attr982);    throw mse;
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
    // Sql.g:100:1: funct : ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) ;
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:100:8: ( ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) )
            // Sql.g:100:9: ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' )
            {
            if ( (input.LA(1)>=58 && input.LA(1)<=65) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_funct1096);    throw mse;
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
    // Sql.g:101:1: select : ( 'select' | 'SELECT' | 'find' | 'FIND' ) ;
    public final void select() throws RecognitionException {
        try {
            // Sql.g:101:9: ( ( 'select' | 'SELECT' | 'find' | 'FIND' ) )
            // Sql.g:101:10: ( 'select' | 'SELECT' | 'find' | 'FIND' )
            {
            if ( (input.LA(1)>=66 && input.LA(1)<=69) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_select1134);    throw mse;
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
    // Sql.g:102:1: and : ( 'and' | 'AND' ) ;
    public final void and() throws RecognitionException {
        try {
            // Sql.g:102:6: ( ( 'and' | 'AND' ) )
            // Sql.g:102:7: ( 'and' | 'AND' )
            {
            if ( (input.LA(1)>=70 && input.LA(1)<=71) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_and1155);    throw mse;
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
    // Sql.g:103:1: order : ( 'order' | 'ORDER' ) ;
    public final void order() throws RecognitionException {
        try {
            // Sql.g:103:8: ( ( 'order' | 'ORDER' ) )
            // Sql.g:103:9: ( 'order' | 'ORDER' )
            {
            if ( (input.LA(1)>=72 && input.LA(1)<=73) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_order1168);    throw mse;
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
    // Sql.g:104:1: by : ( 'by' | 'BY' ) ;
    public final void by() throws RecognitionException {
        try {
            // Sql.g:104:5: ( ( 'by' | 'BY' ) )
            // Sql.g:104:6: ( 'by' | 'BY' )
            {
            if ( (input.LA(1)>=74 && input.LA(1)<=75) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_by1181);    throw mse;
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
    // Sql.g:105:1: or : ( 'or' | 'OR' ) ;
    public final void or() throws RecognitionException {
        try {
            // Sql.g:105:5: ( ( 'or' | 'OR' ) )
            // Sql.g:105:6: ( 'or' | 'OR' )
            {
            if ( (input.LA(1)>=76 && input.LA(1)<=77) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_or1194);    throw mse;
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
    // Sql.g:106:1: in : ( 'in' | 'IN' ) ;
    public final in_return in() throws RecognitionException {
        in_return retval = new in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:106:5: ( ( 'in' | 'IN' ) )
            // Sql.g:106:6: ( 'in' | 'IN' )
            {
            if ( (input.LA(1)>=78 && input.LA(1)<=79) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_in1207);    throw mse;
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
    // Sql.g:107:1: not : ( 'not' | 'NOT' ) ;
    public final void not() throws RecognitionException {
        try {
            // Sql.g:107:6: ( ( 'not' | 'NOT' ) )
            // Sql.g:107:7: ( 'not' | 'NOT' )
            {
            if ( (input.LA(1)>=80 && input.LA(1)<=81) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_not1220);    throw mse;
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
    // Sql.g:108:1: like : ( 'like' | 'LIKE' ) ;
    public final like_return like() throws RecognitionException {
        like_return retval = new like_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:108:7: ( ( 'like' | 'LIKE' ) )
            // Sql.g:108:8: ( 'like' | 'LIKE' )
            {
            if ( (input.LA(1)>=82 && input.LA(1)<=83) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_like1233);    throw mse;
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
    // Sql.g:109:1: count : ( 'count' | 'COUNT' ) ;
    public final void count() throws RecognitionException {
        try {
            // Sql.g:109:8: ( ( 'count' | 'COUNT' ) )
            // Sql.g:109:9: ( 'count' | 'COUNT' )
            {
            if ( input.LA(1)==47||input.LA(1)==84 ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_count1246);    throw mse;
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
    // Sql.g:110:1: sum : ( 'sum' | 'SUM' ) ;
    public final void sum() throws RecognitionException {
        try {
            // Sql.g:110:6: ( ( 'sum' | 'SUM' ) )
            // Sql.g:110:7: ( 'sum' | 'SUM' )
            {
            if ( (input.LA(1)>=85 && input.LA(1)<=86) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_sum1259);    throw mse;
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
    protected DFA5 dfa5 = new DFA5(this);
    protected DFA6 dfa6 = new DFA6(this);
    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA1_eotS =
        "\u01e8\uffff";
    static final String DFA1_eofS =
        "\3\uffff\1\10\2\uffff\1\10\12\uffff\1\10\2\uffff\2\10\31\uffff\1"+
        "\10\1\uffff\2\10\11\uffff\2\122\2\uffff\1\122\11\uffff\1\10\4\uffff"+
        "\1\122\4\uffff\1\122\1\uffff\1\122\7\uffff\1\10\4\uffff\3\122\4"+
        "\uffff\2\122\2\uffff\1\122\4\uffff\1\10\6\uffff\1\122\14\uffff\1"+
        "\122\1\uffff\1\122\7\uffff\2\122\3\uffff\2\122\1\uffff\1\122\12"+
        "\uffff\4\122\7\uffff\1\122\1\uffff\1\122\12\uffff\1\122\10\uffff"+
        "\5\122\1\uffff\1\122\6\uffff\1\122\1\uffff\3\122\4\uffff\1\122\7"+
        "\uffff\1\122\1\uffff\1\122\1\uffff\1\122\15\uffff\1\122\2\uffff"+
        "\7\122\5\uffff\2\122\2\uffff\2\122\6\uffff\1\122\15\uffff\1\122"+
        "\2\uffff\5\122\2\uffff\1\122\7\uffff\2\122\1\uffff\2\122\11\uffff"+
        "\1\122\11\uffff\4\122\2\uffff\2\122\2\uffff\2\122\1\uffff\1\122"+
        "\10\uffff\1\122\10\uffff\4\122\1\uffff\2\122\2\uffff\2\122\1\uffff"+
        "\1\122\20\uffff\4\122\1\uffff\1\122\2\uffff\2\122\1\uffff\1\122"+
        "\20\uffff\4\122\1\uffff\1\122\2\uffff\2\122\1\uffff\1\122\15\uffff"+
        "\3\122\1\uffff\1\122\2\uffff\1\122\1\uffff\1\122\12\uffff\3\122"+
        "\1\uffff\1\122\3\uffff\1\122\6\uffff\1\122\1\uffff\1\122\1\uffff"+
        "\1\122\4\uffff\1\122\1\uffff\1\122";
    static final String DFA1_minS =
        "\1\102\7\4\1\uffff\1\24\1\uffff\22\4\1\6\1\24\6\4\1\24\12\4\1\24"+
        "\5\4\1\6\20\4\1\6\3\4\1\24\2\4\1\7\3\4\2\uffff\1\4\1\7\1\4\3\7\2"+
        "\4\1\24\3\4\1\7\13\4\2\7\1\4\2\7\10\4\1\7\1\4\1\24\12\4\1\7\1\4"+
        "\1\7\1\4\1\7\2\4\1\7\1\4\1\7\23\4\1\6\11\4\2\7\1\4\1\7\1\4\3\7\4"+
        "\4\1\24\2\7\1\4\7\7\6\4\1\7\1\4\4\7\7\4\1\6\1\4\2\7\1\4\2\7\3\4"+
        "\2\7\1\4\1\7\1\4\1\7\1\4\1\7\7\4\5\7\1\4\2\7\10\4\1\7\1\4\1\7\3"+
        "\4\1\6\1\10\4\4\4\7\1\4\5\7\4\4\4\7\1\4\2\7\5\4\1\6\2\4\6\7\3\4"+
        "\1\6\4\4\7\7\4\4\6\7\4\4\1\6\1\10\2\4\2\7\2\4\1\6\3\4\6\7\3\4\6"+
        "\7\4\4\1\6\2\4\2\7\2\4\1\6\3\4\6\7\2\4\6\7\4\4\1\6\1\4\2\7\2\4\1"+
        "\6\3\4\6\7\2\4\6\7\4\4\1\6\1\4\2\7\2\4\1\6\3\4\5\7\2\4\4\7\3\4\1"+
        "\6\1\4\2\7\1\4\1\6\2\4\5\7\2\4\2\7\3\4\1\6\1\4\2\7\1\10\1\4\3\7"+
        "\2\4\1\7\1\4\1\6\1\4\1\7\1\4\2\7\1\4\1\10\1\4\1\7\1\4";
    static final String DFA1_maxS =
        "\1\105\2\126\1\111\2\17\1\111\1\126\1\uffff\1\101\1\uffff\1\126"+
        "\1\17\1\40\1\17\1\40\1\126\1\111\2\17\2\111\1\126\1\123\2\17\1\40"+
        "\1\20\1\40\1\6\1\101\1\17\1\40\1\17\1\40\1\123\1\17\1\101\1\14\1"+
        "\12\2\10\1\17\1\40\1\17\1\40\1\20\1\111\1\71\2\111\1\40\1\20\1\40"+
        "\1\6\1\17\1\7\2\123\1\14\2\115\2\7\1\115\3\7\1\40\1\20\1\40\1\6"+
        "\2\20\1\111\1\71\1\7\1\20\1\7\1\115\2\126\2\uffff\1\115\1\7\1\115"+
        "\1\12\2\10\1\20\1\123\1\71\1\20\1\111\1\20\1\7\1\20\1\7\3\115\1"+
        "\126\1\123\2\17\2\115\2\7\1\115\2\7\2\20\1\111\3\20\1\7\1\20\1\7"+
        "\1\115\1\101\1\123\1\14\1\12\2\10\2\17\1\40\1\17\1\40\1\7\1\115"+
        "\1\7\1\115\1\7\1\20\1\123\1\7\1\20\1\7\1\20\2\115\2\123\1\14\2\115"+
        "\1\7\1\115\4\7\1\17\1\7\1\40\1\20\1\40\1\6\4\115\1\12\4\20\2\7\1"+
        "\115\1\7\1\115\1\12\2\10\1\7\2\20\1\123\1\71\2\7\1\115\2\7\1\12"+
        "\2\10\2\7\1\20\5\115\1\7\1\115\4\7\1\20\1\7\1\115\1\20\3\115\2\12"+
        "\2\7\1\115\2\7\3\20\2\7\1\115\1\7\1\115\1\7\1\115\1\7\3\20\1\7\2"+
        "\20\1\123\5\7\1\115\2\7\7\115\1\12\1\7\1\20\1\7\1\20\2\115\2\12"+
        "\2\115\2\20\4\7\1\115\2\7\1\12\2\10\4\20\4\7\1\115\2\7\5\115\2\12"+
        "\1\115\6\7\1\20\2\115\1\12\2\115\2\20\7\7\1\115\3\20\6\7\4\115\2"+
        "\12\2\115\2\7\2\115\1\12\1\115\2\20\6\7\1\115\2\20\6\7\4\115\1\12"+
        "\2\115\2\7\2\115\1\12\1\115\2\20\6\7\2\20\6\7\4\115\1\12\1\115\2"+
        "\7\2\115\1\12\1\115\2\20\6\7\2\20\6\7\4\115\1\12\1\115\2\7\2\115"+
        "\1\12\1\115\2\20\5\7\2\20\4\7\3\115\1\12\1\115\2\7\1\115\1\12\1"+
        "\115\1\20\5\7\2\20\2\7\3\115\1\12\1\115\2\7\1\12\1\115\3\7\2\20"+
        "\1\7\1\115\1\12\1\115\1\7\1\115\2\7\1\20\1\12\1\115\1\7\1\115";
    static final String DFA1_acceptS =
        "\10\uffff\1\2\1\uffff\1\3\107\uffff\1\1\1\4\u0194\uffff";
    static final String DFA1_specialS =
        "\u01e8\uffff}>";
    static final String[] DFA1_transitionS = {
            "\4\1",
            "\1\2\16\uffff\16\3\16\uffff\1\4\44\uffff\1\4\2\5",
            "\1\2\16\uffff\16\3\16\uffff\1\4\44\uffff\1\4\2\5",
            "\1\6\1\7\1\11\12\uffff\2\13\65\uffff\2\12",
            "\1\14\12\uffff\1\15",
            "\1\16\12\uffff\1\17",
            "\1\6\1\7\13\uffff\2\13\65\uffff\2\12",
            "\1\20\16\uffff\16\21\16\uffff\1\22\44\uffff\1\22\2\23",
            "",
            "\2\25\13\uffff\31\25\10\24",
            "",
            "\1\26\16\uffff\16\27\16\uffff\1\30\44\uffff\1\30\2\31",
            "\1\14\12\uffff\1\15",
            "\1\32\16\uffff\16\33",
            "\1\16\12\uffff\1\17",
            "\1\34\16\uffff\16\35",
            "\1\20\16\uffff\16\21\16\uffff\1\22\44\uffff\1\22\2\23",
            "\1\6\1\7\1\36\12\uffff\2\13\65\uffff\2\12",
            "\1\37\12\uffff\1\40",
            "\1\41\12\uffff\1\42",
            "\1\6\1\7\13\uffff\2\13\65\uffff\2\12",
            "\1\6\1\7\13\uffff\2\13\65\uffff\2\12",
            "\1\26\16\uffff\16\27\16\uffff\1\30\44\uffff\1\30\2\31",
            "\1\43\1\uffff\1\45\1\uffff\1\47\1\50\1\51\103\uffff\2\44\2\uffff"+
            "\2\46",
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
            "\1\43\3\uffff\1\47\1\50\1\51\103\uffff\2\44\2\uffff\2\46",
            "\1\67\12\uffff\1\70",
            "\2\71\13\uffff\31\71\10\72",
            "\1\73\2\uffff\1\74\4\uffff\1\75",
            "\1\77\2\uffff\1\100\1\uffff\1\101\1\76",
            "\1\77\2\uffff\1\100\1\102",
            "\1\77\2\uffff\1\100\1\103",
            "\1\52\12\uffff\1\53",
            "\1\104\16\uffff\16\105",
            "\1\54\12\uffff\1\55",
            "\1\106\16\uffff\16\107",
            "\1\56\13\uffff\1\57",
            "\1\6\1\7\13\uffff\2\13\65\uffff\2\12",
            "\2\110\13\uffff\31\110",
            "\1\6\1\7\13\uffff\2\13\65\uffff\2\12",
            "\1\6\1\7\13\uffff\2\13\65\uffff\2\12",
            "\1\63\16\uffff\16\64",
            "\1\111\13\uffff\1\112",
            "\1\65\16\uffff\16\66",
            "\1\113",
            "\1\67\12\uffff\1\70",
            "\1\114\2\uffff\1\115",
            "\1\43\3\uffff\1\47\1\50\1\51\103\uffff\2\44\2\uffff\2\46",
            "\1\43\3\uffff\1\47\1\50\1\51\103\uffff\2\44\2\uffff\2\46",
            "\1\73\2\uffff\1\74\4\uffff\1\75",
            "\1\117\1\uffff\1\116\1\74\4\uffff\1\75\71\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\2\uffff\1\74\4\uffff\1\75\71\uffff\2\120\2\123\2\uffff"+
            "\2\121",
            "\1\77\2\uffff\1\100",
            "\1\77\2\uffff\1\100",
            "\1\126\1\uffff\1\125\1\uffff\1\127\1\130\1\131\73\uffff\2\120"+
            "\2\123\2\uffff\2\121",
            "\1\77\2\uffff\1\100",
            "\1\77\2\uffff\1\100",
            "\1\77\2\uffff\1\100",
            "\1\104\16\uffff\16\105",
            "\1\132\13\uffff\1\133",
            "\1\106\16\uffff\16\107",
            "\1\134",
            "\1\135\13\uffff\1\136",
            "\1\111\13\uffff\1\112",
            "\1\6\1\7\13\uffff\2\13\65\uffff\2\12",
            "\2\137\13\uffff\31\137",
            "\1\114\2\uffff\1\115",
            "\1\141\1\142\1\140\11\uffff\1\143",
            "\1\144",
            "\1\124\2\uffff\1\145\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\146\16\uffff\16\147\16\uffff\1\150\44\uffff\1\150\2\151",
            "\1\146\16\uffff\16\147\16\uffff\1\150\44\uffff\1\150\2\151",
            "",
            "",
            "\1\124\101\uffff\2\120\2\123\2\uffff\2\121",
            "\1\152",
            "\1\124\2\uffff\1\153\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\156\1\uffff\1\154\1\155",
            "\1\156\1\157",
            "\1\156\1\160",
            "\1\132\13\uffff\1\133",
            "\1\43\3\uffff\1\47\1\50\1\51\103\uffff\2\44\2\uffff\2\46",
            "\2\161\13\uffff\31\161",
            "\1\135\13\uffff\1\136",
            "\1\6\1\7\13\uffff\2\13\65\uffff\2\12",
            "\1\162\13\uffff\1\163",
            "\1\164",
            "\1\166\1\142\1\uffff\1\165\10\uffff\1\143",
            "\1\167\2\uffff\1\170",
            "\1\124\101\uffff\2\120\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\171\1\74\4\uffff\1\75\71\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\172\2\uffff\1\74\4\uffff\1\75\71\uffff\2\120\2\123\2\uffff"+
            "\2\121",
            "\1\146\16\uffff\16\147\16\uffff\1\150\44\uffff\1\150\2\151",
            "\1\174\1\uffff\1\173\1\uffff\1\176\1\177\1\u0080\103\uffff\2"+
            "\u0081\2\uffff\2\175",
            "\1\u0082\12\uffff\1\u0083",
            "\1\u0084\12\uffff\1\u0085",
            "\1\124\1\uffff\1\u0086\1\uffff\1\127\1\130\1\131\73\uffff\2"+
            "\120\2\123\2\uffff\2\121",
            "\1\u0087\3\uffff\1\127\1\130\1\131\73\uffff\2\120\2\123\2\uffff"+
            "\2\121",
            "\1\156",
            "\1\156",
            "\1\u0089\1\uffff\1\u0088\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\156",
            "\1\156",
            "\1\u008b\13\uffff\1\u008c",
            "\1\162\13\uffff\1\163",
            "\1\6\1\7\13\uffff\2\13\65\uffff\2\12",
            "\1\166\1\142\1\u008d\11\uffff\1\143",
            "\1\u008e\1\142\12\uffff\1\143",
            "\1\166\1\142\12\uffff\1\143",
            "\1\167\2\uffff\1\170",
            "\1\u0090\1\142\1\u008f\11\uffff\1\143",
            "\1\u0091",
            "\1\124\2\uffff\1\u0092\76\uffff\2\120\2\123\2\uffff\2\121",
            "\2\u0094\13\uffff\31\u0094\10\u0093",
            "\1\174\3\uffff\1\176\1\177\1\u0080\103\uffff\2\u0081\2\uffff"+
            "\2\175",
            "\1\u0095\2\uffff\1\u0096\4\uffff\1\u0097",
            "\1\u0098\2\uffff\1\u0099\1\uffff\1\u009a\1\u009b",
            "\1\u0098\2\uffff\1\u0099\1\u009c",
            "\1\u0098\2\uffff\1\u0099\1\u009d",
            "\1\u009e\12\uffff\1\u009f",
            "\1\u0082\12\uffff\1\u0083",
            "\1\u00a0\16\uffff\16\u00a1",
            "\1\u0084\12\uffff\1\u0085",
            "\1\u00a2\16\uffff\16\u00a3",
            "\1\u00a4",
            "\1\124\2\uffff\1\u00a5\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u00a6",
            "\1\124\2\uffff\1\u00a7\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u00a8",
            "\1\u008b\13\uffff\1\u008c",
            "\1\43\3\uffff\1\47\1\50\1\51\103\uffff\2\44\2\uffff\2\46",
            "\1\u00a9",
            "\1\166\1\142\1\uffff\1\u00aa\10\uffff\1\143",
            "\1\u00ab",
            "\1\166\1\142\1\uffff\1\u00ac\10\uffff\1\143",
            "\1\124\1\uffff\1\u00ad\1\74\4\uffff\1\75\71\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\2\uffff\1\74\4\uffff\1\75\71\uffff\2\120\2\123\2\uffff"+
            "\2\121",
            "\1\174\3\uffff\1\176\1\177\1\u0080\103\uffff\2\u0081\2\uffff"+
            "\2\175",
            "\1\174\3\uffff\1\176\1\177\1\u0080\103\uffff\2\u0081\2\uffff"+
            "\2\175",
            "\1\u0095\2\uffff\1\u0096\4\uffff\1\u0097",
            "\1\u00af\1\uffff\1\u00ae\1\u0096\4\uffff\1\u0097\71\uffff\2"+
            "\120\2\123\2\uffff\2\121",
            "\1\124\2\uffff\1\u0096\4\uffff\1\u0097\71\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u0098\2\uffff\1\u0099",
            "\1\u00b1\1\uffff\1\u00b0\1\uffff\1\u00b2\1\u00b3\1\u00b4\73"+
            "\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u0098\2\uffff\1\u0099",
            "\1\u0098\2\uffff\1\u0099",
            "\1\u0098\2\uffff\1\u0099",
            "\1\u0098\2\uffff\1\u0099",
            "\1\u009e\12\uffff\1\u009f",
            "\1\u00b5\2\uffff\1\u00b6",
            "\1\u00a0\16\uffff\16\u00a1",
            "\1\u00b7\13\uffff\1\u00b8",
            "\1\u00a2\16\uffff\16\u00a3",
            "\1\u00b9",
            "\1\124\1\uffff\1\u00ba\1\uffff\1\127\1\130\1\131\73\uffff\2"+
            "\120\2\123\2\uffff\2\121",
            "\1\124\3\uffff\1\127\1\130\1\131\73\uffff\2\120\2\123\2\uffff"+
            "\2\121",
            "\1\124\1\uffff\1\u00bb\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u00bc\6\uffff\1\u008a\72\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u00be\1\uffff\1\u00bd\1\uffff\1\u00bf\1\u00c0\1\u00c1",
            "\1\166\1\142\1\u00c2\11\uffff\1\143",
            "\1\166\1\142\12\uffff\1\143",
            "\1\166\1\142\1\u00c3\11\uffff\1\143",
            "\1\u00c4\1\142\12\uffff\1\143",
            "\1\u00c5",
            "\1\u00c6",
            "\1\124\2\uffff\1\u00c7\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u00c8",
            "\1\124\2\uffff\1\u00c9\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u00cb\1\uffff\1\u00ca\1\u00cc",
            "\1\u00cb\1\u00cd",
            "\1\u00cb\1\u00ce",
            "\1\u00b5\2\uffff\1\u00b6",
            "\1\u00d0\1\u00d1\1\u00cf\11\uffff\1\u00d2",
            "\1\u00b7\13\uffff\1\u00b8",
            "\1\174\3\uffff\1\176\1\177\1\u0080\103\uffff\2\u0081\2\uffff"+
            "\2\175",
            "\2\u00d3\13\uffff\31\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\124\2\uffff\1\u00d6\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u00d7",
            "\1\u00d8",
            "\1\u00db\1\uffff\1\u00d9\1\u00da",
            "\1\u00db\1\u00dc",
            "\1\u00db\1\u00dd",
            "\1\u00de",
            "\1\u00df",
            "\1\166\1\142\1\uffff\1\u00e0\10\uffff\1\143",
            "\1\124\1\uffff\1\u00e1\1\74\4\uffff\1\75\71\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\1\uffff\1\u00e2\1\u0096\4\uffff\1\u0097\71\uffff\2\120"+
            "\2\123\2\uffff\2\121",
            "\1\u00e3\2\uffff\1\u0096\4\uffff\1\u0097\71\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\1\uffff\1\u00e4\1\uffff\1\u00b2\1\u00b3\1\u00b4\73\uffff"+
            "\2\120\2\123\2\uffff\2\121",
            "\1\u00e5\3\uffff\1\u00b2\1\u00b3\1\u00b4\73\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u00cb",
            "\1\u00e7\1\uffff\1\u00e6\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u00cb",
            "\1\u00cb",
            "\1\u00cb",
            "\1\u00e9",
            "\1\u00eb\1\u00d1\1\uffff\1\u00ea\10\uffff\1\u00d2",
            "\1\u00ec\2\uffff\1\u00ed",
            "\1\124\101\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u00ee\13\uffff\1\u00ef",
            "\1\124\1\uffff\1\u00f0\1\uffff\1\127\1\130\1\131\73\uffff\2"+
            "\120\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u00f1\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\6\uffff\1\u008a\72\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u00f2\1\uffff\1\u00bf\1\u00c0\1\u00c1",
            "\1\u00f3\3\uffff\1\u00bf\1\u00c0\1\u00c1",
            "\1\u00db",
            "\1\u00db",
            "\1\u00f5\1\uffff\1\u00f4\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u00db",
            "\1\u00db",
            "\1\166\1\142\1\u00f6\11\uffff\1\143",
            "\1\166\1\142\1\u00f7\11\uffff\1\143",
            "\1\166\1\142\12\uffff\1\143",
            "\1\u00f8",
            "\1\u00f9",
            "\1\124\2\uffff\1\u00fa\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u00fb",
            "\1\124\2\uffff\1\u00fc\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u00fd",
            "\1\124\2\uffff\1\u00fe\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u00ff",
            "\1\u00eb\1\u00d1\1\u0100\11\uffff\1\u00d2",
            "\1\u0101\1\u00d1\12\uffff\1\u00d2",
            "\1\u00eb\1\u00d1\12\uffff\1\u00d2",
            "\1\u00ec\2\uffff\1\u00ed",
            "\1\u0103\1\u00d1\1\u0102\11\uffff\1\u00d2",
            "\1\u00ee\13\uffff\1\u00ef",
            "\1\174\3\uffff\1\176\1\177\1\u0080\103\uffff\2\u0081\2\uffff"+
            "\2\175",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "\1\u0107",
            "\1\u0108",
            "\1\124\2\uffff\1\u0109\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u010a",
            "\1\u010b",
            "\1\124\1\uffff\1\u010c\1\74\4\uffff\1\75\71\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\1\uffff\1\u010d\1\u0096\4\uffff\1\u0097\71\uffff\2\120"+
            "\2\123\2\uffff\2\121",
            "\1\124\2\uffff\1\u0096\4\uffff\1\u0097\71\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\1\uffff\1\u010e\1\uffff\1\u00b2\1\u00b3\1\u00b4\73\uffff"+
            "\2\120\2\123\2\uffff\2\121",
            "\1\124\3\uffff\1\u00b2\1\u00b3\1\u00b4\73\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\1\uffff\1\u010f\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u0110\6\uffff\1\u00e8\72\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u0112\1\uffff\1\u0111\1\uffff\1\u0113\1\u0114\1\u0115",
            "\1\u0116",
            "\1\u00eb\1\u00d1\1\uffff\1\u0117\10\uffff\1\u00d2",
            "\1\u0118",
            "\1\u00eb\1\u00d1\1\uffff\1\u0119\10\uffff\1\u00d2",
            "\1\124\1\uffff\1\u011a\1\uffff\1\127\1\130\1\131\73\uffff\2"+
            "\120\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u011b\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u011c\1\uffff\1\u00bf\1\u00c0\1\u00c1",
            "\1\u00bf\1\u00c0\1\u00c1",
            "\1\124\1\uffff\1\u011d\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u011e\6\uffff\1\u008a\72\uffff\2\120\2\123\2\uffff\2\121",
            "\1\166\1\142\1\u011f\11\uffff\1\143",
            "\1\166\1\142\1\u0120\11\uffff\1\143",
            "\1\u0121",
            "\1\u0122",
            "\1\u0123",
            "\1\u0124",
            "\1\124\2\uffff\1\u0125\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u0126",
            "\1\u0127",
            "\1\u0128\1\uffff\1\u0129\1\u012a",
            "\1\u0128\1\u012b",
            "\1\u0128\1\u012c",
            "\1\u00eb\1\u00d1\1\u012d\11\uffff\1\u00d2",
            "\1\u00eb\1\u00d1\12\uffff\1\u00d2",
            "\1\u00eb\1\u00d1\1\u012e\11\uffff\1\u00d2",
            "\1\u012f\1\u00d1\12\uffff\1\u00d2",
            "\1\u0130",
            "\1\u0131",
            "\1\u0132",
            "\1\u0133",
            "\1\124\2\uffff\1\u0134\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u0135",
            "\1\u0136",
            "\1\124\1\uffff\1\u0137\1\74\4\uffff\1\75\71\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\1\uffff\1\u0138\1\u0096\4\uffff\1\u0097\71\uffff\2\120"+
            "\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u0139\1\uffff\1\u00b2\1\u00b3\1\u00b4\73\uffff"+
            "\2\120\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u013a\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\6\uffff\1\u00e8\72\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u013b\1\uffff\1\u0113\1\u0114\1\u0115",
            "\1\u013c\3\uffff\1\u0113\1\u0114\1\u0115",
            "\1\u013e\1\uffff\1\u013d\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u0128",
            "\1\u0128",
            "\1\u0128",
            "\1\u0128",
            "\1\u013f",
            "\1\u0140",
            "\1\u00eb\1\u00d1\1\uffff\1\u0141\10\uffff\1\u00d2",
            "\1\124\1\uffff\1\u0142\1\uffff\1\127\1\130\1\131\73\uffff\2"+
            "\120\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u0143\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u0144\1\uffff\1\u00bf\1\u00c0\1\u00c1",
            "\1\124\1\uffff\1\u0145\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\6\uffff\1\u008a\72\uffff\2\120\2\123\2\uffff\2\121",
            "\1\166\1\142\1\u0146\11\uffff\1\143",
            "\1\166\1\142\1\u0147\11\uffff\1\143",
            "\1\u0148",
            "\1\u0149",
            "\1\u014a",
            "\1\u014b",
            "\1\u014c",
            "\1\u014d",
            "\1\u014e",
            "\1\124\2\uffff\1\u014f\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u00eb\1\u00d1\1\u0150\11\uffff\1\u00d2",
            "\1\u00eb\1\u00d1\1\u0151\11\uffff\1\u00d2",
            "\1\u00eb\1\u00d1\12\uffff\1\u00d2",
            "\1\u0152",
            "\1\u0153",
            "\1\u0154",
            "\1\u0155",
            "\1\u0156",
            "\1\u0157",
            "\1\124\1\uffff\1\u0158\1\74\4\uffff\1\75\71\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\1\uffff\1\u0159\1\u0096\4\uffff\1\u0097\71\uffff\2\120"+
            "\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u015a\1\uffff\1\u00b2\1\u00b3\1\u00b4\73\uffff"+
            "\2\120\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u015b\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u015c\1\uffff\1\u0113\1\u0114\1\u0115",
            "\1\u0113\1\u0114\1\u0115",
            "\1\124\1\uffff\1\u015d\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u015e\6\uffff\1\u00e8\72\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u015f",
            "\1\u0160",
            "\1\124\1\uffff\1\u0161\1\uffff\1\127\1\130\1\131\73\uffff\2"+
            "\120\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u0162\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u0163\1\uffff\1\u00bf\1\u00c0\1\u00c1",
            "\1\124\1\uffff\1\u0164\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\166\1\142\1\u0165\11\uffff\1\143",
            "\1\166\1\142\1\u0166\11\uffff\1\143",
            "\1\u0167",
            "\1\u0168",
            "\1\u0169",
            "\1\u016a",
            "\1\u016b",
            "\1\u016c",
            "\1\124\2\uffff\1\u016d\76\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u00eb\1\u00d1\1\u016e\11\uffff\1\u00d2",
            "\1\u00eb\1\u00d1\1\u016f\11\uffff\1\u00d2",
            "\1\u0170",
            "\1\u0171",
            "\1\u0172",
            "\1\u0173",
            "\1\u0174",
            "\1\u0175",
            "\1\124\1\uffff\1\u0176\1\74\4\uffff\1\75\71\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\1\uffff\1\u0177\1\u0096\4\uffff\1\u0097\71\uffff\2\120"+
            "\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u0178\1\uffff\1\u00b2\1\u00b3\1\u00b4\73\uffff"+
            "\2\120\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u0179\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u017a\1\uffff\1\u0113\1\u0114\1\u0115",
            "\1\124\1\uffff\1\u017b\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\6\uffff\1\u00e8\72\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u017c",
            "\1\u017d",
            "\1\124\1\uffff\1\u017e\1\uffff\1\127\1\130\1\131\73\uffff\2"+
            "\120\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u017f\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u0180\1\uffff\1\u00bf\1\u00c0\1\u00c1",
            "\1\124\1\uffff\1\u0181\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\166\1\142\1\u0182\11\uffff\1\143",
            "\1\166\1\142\1\u0183\11\uffff\1\143",
            "\1\u0184",
            "\1\u0185",
            "\1\u0186",
            "\1\u0187",
            "\1\u0188",
            "\1\u0189",
            "\1\u00eb\1\u00d1\1\u018a\11\uffff\1\u00d2",
            "\1\u00eb\1\u00d1\1\u018b\11\uffff\1\u00d2",
            "\1\u018c",
            "\1\u018d",
            "\1\u018e",
            "\1\u018f",
            "\1\u0190",
            "\1\u0191",
            "\1\124\1\uffff\1\u0192\1\74\4\uffff\1\75\71\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\1\uffff\1\u0193\1\u0096\4\uffff\1\u0097\71\uffff\2\120"+
            "\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u0194\1\uffff\1\u00b2\1\u00b3\1\u00b4\73\uffff"+
            "\2\120\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u0195\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u0196\1\uffff\1\u0113\1\u0114\1\u0115",
            "\1\124\1\uffff\1\u0197\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u0198",
            "\1\u0199",
            "\1\124\1\uffff\1\u019a\1\uffff\1\127\1\130\1\131\73\uffff\2"+
            "\120\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u019b\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u019c\1\uffff\1\u00bf\1\u00c0\1\u00c1",
            "\1\124\1\uffff\1\u019d\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\166\1\142\1\u019e\11\uffff\1\143",
            "\1\166\1\142\1\u019f\11\uffff\1\143",
            "\1\u01a0",
            "\1\u01a1",
            "\1\u01a2",
            "\1\u01a3",
            "\1\u01a4",
            "\1\u01a5",
            "\1\u00eb\1\u00d1\1\u01a6\11\uffff\1\u00d2",
            "\1\u00eb\1\u00d1\1\u01a7\11\uffff\1\u00d2",
            "\1\u01a8",
            "\1\u01a9",
            "\1\u01aa",
            "\1\u01ab",
            "\1\u01ac",
            "\1\u01ad",
            "\1\124\2\uffff\1\74\4\uffff\1\75\71\uffff\2\120\2\123\2\uffff"+
            "\2\121",
            "\1\124\1\uffff\1\u01ae\1\u0096\4\uffff\1\u0097\71\uffff\2\120"+
            "\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u01af\1\uffff\1\u00b2\1\u00b3\1\u00b4\73\uffff"+
            "\2\120\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u01b0\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u01b1\1\uffff\1\u0113\1\u0114\1\u0115",
            "\1\124\1\uffff\1\u01b2\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u01b3",
            "\1\u01b4",
            "\1\124\3\uffff\1\127\1\130\1\131\73\uffff\2\120\2\123\2\uffff"+
            "\2\121",
            "\1\124\1\uffff\1\u01b5\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u01b6\1\uffff\1\u00bf\1\u00c0\1\u00c1",
            "\1\124\1\uffff\1\u01b7\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\166\1\142\12\uffff\1\143",
            "\1\166\1\142\1\u01b8\11\uffff\1\143",
            "\1\u01b9",
            "\1\u01ba",
            "\1\u01bb",
            "\1\u01bc",
            "\1\u01bd",
            "\1\u00eb\1\u00d1\1\u01be\11\uffff\1\u00d2",
            "\1\u00eb\1\u00d1\1\u01bf\11\uffff\1\u00d2",
            "\1\u01c0",
            "\1\u01c1",
            "\1\u01c2",
            "\1\u01c3",
            "\1\124\1\uffff\1\u01c4\1\u0096\4\uffff\1\u0097\71\uffff\2\120"+
            "\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u01c5\1\uffff\1\u00b2\1\u00b3\1\u00b4\73\uffff"+
            "\2\120\2\123\2\uffff\2\121",
            "\1\124\1\uffff\1\u01c6\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u01c7\1\uffff\1\u0113\1\u0114\1\u0115",
            "\1\124\1\uffff\1\u01c8\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u01c9",
            "\1\u01ca",
            "\1\124\6\uffff\1\u008a\72\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u01cb\1\uffff\1\u00bf\1\u00c0\1\u00c1",
            "\1\124\1\uffff\1\u01cc\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\166\1\142\12\uffff\1\143",
            "\1\u01cd",
            "\1\u01ce",
            "\1\u01cf",
            "\1\u01d0",
            "\1\u01d1",
            "\1\u00eb\1\u00d1\1\u01d2\11\uffff\1\u00d2",
            "\1\u00eb\1\u00d1\1\u01d3\11\uffff\1\u00d2",
            "\1\u01d4",
            "\1\u01d5",
            "\1\124\2\uffff\1\u0096\4\uffff\1\u0097\71\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\3\uffff\1\u00b2\1\u00b3\1\u00b4\73\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\124\1\uffff\1\u01d6\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u01d7\1\uffff\1\u0113\1\u0114\1\u0115",
            "\1\124\1\uffff\1\u01d8\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u01d9",
            "\1\u01da",
            "\1\u00bf\1\u00c0\1\u00c1",
            "\1\124\1\uffff\1\u01db\4\uffff\1\u008a\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u01dc",
            "\1\u01dd",
            "\1\u01de",
            "\1\u00eb\1\u00d1\12\uffff\1\u00d2",
            "\1\u00eb\1\u00d1\1\u01df\11\uffff\1\u00d2",
            "\1\u01e0",
            "\1\124\6\uffff\1\u00e8\72\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u01e1\1\uffff\1\u0113\1\u0114\1\u0115",
            "\1\124\1\uffff\1\u01e2\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u01e3",
            "\1\124\6\uffff\1\u008a\72\uffff\2\120\2\123\2\uffff\2\121",
            "\1\u01e4",
            "\1\u01e5",
            "\1\u00eb\1\u00d1\12\uffff\1\u00d2",
            "\1\u0113\1\u0114\1\u0115",
            "\1\124\1\uffff\1\u01e6\4\uffff\1\u00e8\72\uffff\2\120\2\123"+
            "\2\uffff\2\121",
            "\1\u01e7",
            "\1\124\6\uffff\1\u00e8\72\uffff\2\120\2\123\2\uffff\2\121"
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
            return "20:1: stmt : ( select spaces selectList spaces where spaces constraintList spaces | select spaces selectList spaces | select spaces selectList spaces order spaces by spaces okw= keyword spaces | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces okw= keyword spaces );";
        }
    }
    static final String DFA3_eotS =
        "\4\uffff";
    static final String DFA3_eofS =
        "\2\2\2\uffff";
    static final String DFA3_minS =
        "\2\4\2\uffff";
    static final String DFA3_maxS =
        "\2\111\2\uffff";
    static final String DFA3_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA3_specialS =
        "\4\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\3\13\uffff\2\2\65\uffff\2\2",
            "\1\1\1\3\13\uffff\2\2\65\uffff\2\2",
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
        "\2\2\2\uffff";
    static final String DFA5_minS =
        "\2\4\2\uffff";
    static final String DFA5_maxS =
        "\2\115\2\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA5_specialS =
        "\4\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\1\101\uffff\2\3\2\2\2\uffff\2\3",
            "\1\1\101\uffff\2\3\2\2\2\uffff\2\3",
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
            return "()* loopback of 46:29: ( spaces rel= logicalOp spaces constraint )*";
        }
    }
    static final String DFA6_eotS =
        "\31\uffff";
    static final String DFA6_eofS =
        "\31\uffff";
    static final String DFA6_minS =
        "\1\23\3\4\1\24\1\4\3\uffff\11\4\1\6\2\4\1\24\3\4";
    static final String DFA6_maxS =
        "\1\126\1\123\2\17\1\101\1\123\3\uffff\1\17\1\40\1\17\1\40\2\123"+
        "\1\40\1\20\1\40\1\6\1\20\1\123\1\71\2\20\1\123";
    static final String DFA6_acceptS =
        "\6\uffff\1\3\1\2\1\1\20\uffff";
    static final String DFA6_specialS =
        "\31\uffff}>";
    static final String[] DFA6_transitionS = {
            "\16\1\16\uffff\1\2\44\uffff\1\2\2\3",
            "\1\5\1\uffff\1\4\1\uffff\3\10\103\uffff\2\7\2\uffff\2\6",
            "\1\11\12\uffff\1\12",
            "\1\13\12\uffff\1\14",
            "\2\15\13\uffff\31\15\10\16",
            "\1\5\3\uffff\3\10\103\uffff\2\7\2\uffff\2\6",
            "",
            "",
            "",
            "\1\11\12\uffff\1\12",
            "\1\17\16\uffff\16\20",
            "\1\13\12\uffff\1\14",
            "\1\21\16\uffff\16\22",
            "\1\5\3\uffff\3\10\103\uffff\2\7\2\uffff\2\6",
            "\1\5\3\uffff\3\10\103\uffff\2\7\2\uffff\2\6",
            "\1\17\16\uffff\16\20",
            "\1\23\13\uffff\1\24",
            "\1\21\16\uffff\16\22",
            "\1\25",
            "\1\23\13\uffff\1\24",
            "\1\5\3\uffff\3\10\103\uffff\2\7\2\uffff\2\6",
            "\2\26\13\uffff\31\26",
            "\1\27\13\uffff\1\30",
            "\1\27\13\uffff\1\30",
            "\1\5\3\uffff\3\10\103\uffff\2\7\2\uffff\2\6"
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
            return "50:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= likeValue );";
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
            return "()* loopback of 86:21: ( spaces COMMA spaces dotValue )*";
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
    public static final BitSet FOLLOW_keyword_in_stmt77 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt88 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt90 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_selectList_in_stmt92 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt94 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_where_in_stmt96 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt98 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_constraintList_in_stmt100 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000300L});
    public static final BitSet FOLLOW_spaces_in_stmt102 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_order_in_stmt104 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000C00L});
    public static final BitSet FOLLOW_spaces_in_stmt106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000C00L});
    public static final BitSet FOLLOW_by_in_stmt108 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_stmt110 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_keyword_in_stmt115 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SPACE_in_spaces131 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_keyword_in_selectList146 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_selectList159 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_selectList163 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_selectList167 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_keyword_in_selectList174 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_entity_in_keyword199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword205 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword207 = new BitSet(new long[]{0x03FFFFFE00300000L});
    public static final BitSet FOLLOW_attr_in_keyword209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword214 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword216 = new BitSet(new long[]{0xFC00000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_funct_in_keyword218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_count_in_keyword223 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword225 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword227 = new BitSet(new long[]{0x00000001FFF80010L});
    public static final BitSet FOLLOW_spaces_in_keyword229 = new BitSet(new long[]{0x00000001FFF80000L});
    public static final BitSet FOLLOW_entity_in_keyword231 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword233 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sum_in_keyword240 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword242 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword244 = new BitSet(new long[]{0x00000001FFF80010L});
    public static final BitSet FOLLOW_spaces_in_keyword246 = new BitSet(new long[]{0x00000001FFF80000L});
    public static final BitSet FOLLOW_entity_in_keyword248 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword250 = new BitSet(new long[]{0x03FFFFFE00300000L});
    public static final BitSet FOLLOW_attr_in_keyword252 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword254 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_constraintList265 = new BitSet(new long[]{0x0000000000000012L,0x00000000000030C0L});
    public static final BitSet FOLLOW_spaces_in_constraintList269 = new BitSet(new long[]{0x0000000000000000L,0x00000000000030C0L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList276 = new BitSet(new long[]{0x00008001FFF80010L,0x0000000000700000L});
    public static final BitSet FOLLOW_spaces_in_constraintList284 = new BitSet(new long[]{0x00008001FFF80000L,0x0000000000700000L});
    public static final BitSet FOLLOW_constraint_in_constraintList286 = new BitSet(new long[]{0x0000000000000012L,0x00000000000030C0L});
    public static final BitSet FOLLOW_keyword_in_constraint299 = new BitSet(new long[]{0x0000000000000710L});
    public static final BitSet FOLLOW_spaces_in_constraint308 = new BitSet(new long[]{0x0000000000000700L});
    public static final BitSet FOLLOW_compOpt_in_constraint315 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_spaces_in_constraint325 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_genValue_in_constraint332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint361 = new BitSet(new long[]{0x0000000000000010L,0x000000000000C000L});
    public static final BitSet FOLLOW_spaces_in_constraint370 = new BitSet(new long[]{0x0000000000000000L,0x000000000000C000L});
    public static final BitSet FOLLOW_in_in_constraint377 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_constraint388 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_constraint390 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_spaces_in_constraint394 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_valueList_in_constraint400 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_constraint408 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_constraint412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint438 = new BitSet(new long[]{0x0000000000000010L,0x00000000000C0000L});
    public static final BitSet FOLLOW_spaces_in_constraint447 = new BitSet(new long[]{0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_like_in_constraint454 = new BitSet(new long[]{0x0000000000001090L});
    public static final BitSet FOLLOW_spaces_in_constraint463 = new BitSet(new long[]{0x0000000000001080L});
    public static final BitSet FOLLOW_likeValue_in_constraint470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue526 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue528 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue536 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue538 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue540 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue542 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue550 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue552 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue554 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue556 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue558 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue560 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue568 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue570 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue572 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue574 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue576 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue578 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue580 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue582 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue590 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue592 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue594 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue596 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue598 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue600 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue602 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue604 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue606 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue608 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue616 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue618 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue620 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue622 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue624 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue626 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue628 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue630 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue632 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue634 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue636 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue638 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue646 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue648 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue650 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue652 = new BitSet(new long[]{0x0000000000000080L});
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
    public static final BitSet FOLLOW_VALUE_in_dotValue674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue680 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue682 = new BitSet(new long[]{0x0000000000000080L});
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
    public static final BitSet FOLLOW_VALUE_in_dotValue719 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue721 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue723 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue725 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue727 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue729 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue731 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue733 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue735 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue737 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue739 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue741 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue743 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue745 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue747 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue749 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue751 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue753 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue762 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue764 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue772 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue774 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue776 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue778 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_valueList788 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_valueList792 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_valueList794 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_spaces_in_valueList796 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_dotValue_in_valueList798 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_EQ_in_compOpt809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt830 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt840 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LT_in_compOpt843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt850 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt860 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue875 = new BitSet(new long[]{0x0000000000000700L});
    public static final BitSet FOLLOW_compOpt_in_genValue877 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_dotValue_in_genValue879 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_AMP_in_genValue882 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_dotValue_in_genValue884 = new BitSet(new long[]{0x0000000000000700L});
    public static final BitSet FOLLOW_compOpt_in_genValue886 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_dotValue_in_genValue888 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_dotValue_in_likeValue898 = new BitSet(new long[]{0x0000000000001082L});
    public static final BitSet FOLLOW_STAR_in_likeValue901 = new BitSet(new long[]{0x0000000000001082L});
    public static final BitSet FOLLOW_and_in_logicalOp910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct1096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select1134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and1155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_order1168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_by1181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or1194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in1207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not1220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_like1233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_count1246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sum1259 = new BitSet(new long[]{0x0000000000000002L});

}