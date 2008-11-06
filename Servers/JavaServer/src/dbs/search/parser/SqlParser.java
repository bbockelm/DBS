package dbs.search.parser;
// $ANTLR 3.0.1 Sql.g 2008-11-06 14:41:01


import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SPACE", "COMMA", "DOT", "VALUE", "EQ", "LT", "GT", "NOT", "AMP", "NL", "WS", "'('", "')'", "'WHERE'", "'where'", "'in'", "'ads'", "'dataset'", "'release'", "'tier'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'lumi'", "'dq'", "'ilumi'", "'phygrp'", "'group'", "'pset'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numlss'", "'size'", "'count'", "'status'", "'type'", "'id'", "'parent'", "'child'", "'def'", "'evnum'", "'era'", "'tag'", "'numruns()'", "'numfiles()'", "'dataquality()'", "'latest()'", "'parentrelease()'", "'childrelease()'", "'intluminosity()'", "'findevents()'", "'select'", "'SELECT'", "'find'", "'FIND'", "'and'", "'AND'", "'order'", "'ORDER'", "'by'", "'BY'", "'or'", "'OR'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'", "'COUNT'", "'sum'", "'SUM'", "'asc'", "'ASC'", "'desc'", "'DESC'", "'between'", "'BETWEEN'"
    };
    public static final int LT=9;
    public static final int WS=14;
    public static final int COMMA=5;
    public static final int AMP=12;
    public static final int GT=10;
    public static final int NL=13;
    public static final int NOT=11;
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
    String orderingkw = "";



    // $ANTLR start stmt
    // Sql.g:21:1: stmt : ( select spaces selectList spaces where spaces constraintList spaces | select spaces selectList spaces | select spaces selectList spaces order spaces by spaces orderList | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces orderList );
    public final void stmt() throws RecognitionException {
        try {
            // Sql.g:21:6: ( select spaces selectList spaces where spaces constraintList spaces | select spaces selectList spaces | select spaces selectList spaces order spaces by spaces orderList | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces orderList )
            int alt1=4;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // Sql.g:21:8: select spaces selectList spaces where spaces constraintList spaces
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
                    // Sql.g:22:4: select spaces selectList spaces
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
                    // Sql.g:23:4: select spaces selectList spaces order spaces by spaces orderList
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


                    }
                    break;
                case 4 :
                    // Sql.g:24:4: select spaces selectList spaces where spaces constraintList spaces order spaces by spaces orderList
                    {
                    pushFollow(FOLLOW_select_in_stmt79);
                    select();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt81);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_selectList_in_stmt83);
                    selectList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt85);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_where_in_stmt87);
                    where();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt89);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_constraintList_in_stmt91);
                    constraintList();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt93);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_order_in_stmt95);
                    order();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt97);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_by_in_stmt99);
                    by();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt101);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_orderList_in_stmt103);
                    orderList();
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
    // Sql.g:27:1: spaces : ( SPACE )* ;
    public final void spaces() throws RecognitionException {
        try {
            // Sql.g:27:8: ( ( SPACE )* )
            // Sql.g:27:10: ( SPACE )*
            {
            // Sql.g:27:10: ( SPACE )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==SPACE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Sql.g:27:11: SPACE
            	    {
            	    match(input,SPACE,FOLLOW_SPACE_in_spaces113); 

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
    // Sql.g:29:1: orderList : okw= keyword ( spaces COMMA spaces okw= keyword )* spaces oing= ordering ;
    public final void orderList() throws RecognitionException {
        keyword_return okw = null;

        ordering_return oing = null;


        try {
            // Sql.g:29:11: (okw= keyword ( spaces COMMA spaces okw= keyword )* spaces oing= ordering )
            // Sql.g:29:12: okw= keyword ( spaces COMMA spaces okw= keyword )* spaces oing= ordering
            {
            pushFollow(FOLLOW_keyword_in_orderList125);
            okw=keyword();
            _fsp--;

            okws.add(input.toString(okw.start,okw.stop));
            // Sql.g:30:4: ( spaces COMMA spaces okw= keyword )*
            loop3:
            do {
                int alt3=2;
                alt3 = dfa3.predict(input);
                switch (alt3) {
            	case 1 :
            	    // Sql.g:31:3: spaces COMMA spaces okw= keyword
            	    {
            	    pushFollow(FOLLOW_spaces_in_orderList138);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_orderList142); 
            	    pushFollow(FOLLOW_spaces_in_orderList146);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_keyword_in_orderList153);
            	    okw=keyword();
            	    _fsp--;

            	    okws.add(input.toString(okw.start,okw.stop));

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            pushFollow(FOLLOW_spaces_in_orderList168);
            spaces();
            _fsp--;

            pushFollow(FOLLOW_ordering_in_orderList176);
            oing=ordering();
            _fsp--;

            orderingkw=input.toString(oing.start,oing.stop);

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

    public static class ordering_return extends ParserRuleReturnScope {
    };

    // $ANTLR start ordering
    // Sql.g:40:1: ordering : ( asc | desc )? ;
    public final ordering_return ordering() throws RecognitionException {
        ordering_return retval = new ordering_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:40:11: ( ( asc | desc )? )
            // Sql.g:40:13: ( asc | desc )?
            {
            // Sql.g:40:13: ( asc | desc )?
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=88 && LA4_0<=89)) ) {
                alt4=1;
            }
            else if ( ((LA4_0>=90 && LA4_0<=91)) ) {
                alt4=2;
            }
            switch (alt4) {
                case 1 :
                    // Sql.g:40:14: asc
                    {
                    pushFollow(FOLLOW_asc_in_ordering192);
                    asc();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:40:18: desc
                    {
                    pushFollow(FOLLOW_desc_in_ordering194);
                    desc();
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
    // $ANTLR end ordering


    // $ANTLR start selectList
    // Sql.g:41:1: selectList : kw= keyword ( spaces COMMA spaces kw= keyword )* ;
    public final void selectList() throws RecognitionException {
        keyword_return kw = null;


        try {
            // Sql.g:41:12: (kw= keyword ( spaces COMMA spaces kw= keyword )* )
            // Sql.g:41:13: kw= keyword ( spaces COMMA spaces kw= keyword )*
            {
            pushFollow(FOLLOW_keyword_in_selectList207);
            kw=keyword();
            _fsp--;

            kws.add(input.toString(kw.start,kw.stop));
            // Sql.g:42:4: ( spaces COMMA spaces kw= keyword )*
            loop5:
            do {
                int alt5=2;
                alt5 = dfa5.predict(input);
                switch (alt5) {
            	case 1 :
            	    // Sql.g:43:3: spaces COMMA spaces kw= keyword
            	    {
            	    pushFollow(FOLLOW_spaces_in_selectList220);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_selectList224); 
            	    pushFollow(FOLLOW_spaces_in_selectList228);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_keyword_in_selectList235);
            	    kw=keyword();
            	    _fsp--;

            	    kws.add(input.toString(kw.start,kw.stop));

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
    // $ANTLR end selectList

    public static class keyword_return extends ParserRuleReturnScope {
    };

    // $ANTLR start keyword
    // Sql.g:49:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );
    public final keyword_return keyword() throws RecognitionException {
        keyword_return retval = new keyword_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:49:9: ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' )
            int alt6=5;
            switch ( input.LA(1) ) {
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
            case 34:
            case 35:
                {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==EOF||(LA6_1>=SPACE && LA6_1<=COMMA)||(LA6_1>=EQ && LA6_1<=NOT)||(LA6_1>=17 && LA6_1<=19)||(LA6_1>=74 && LA6_1<=75)||(LA6_1>=80 && LA6_1<=84)||(LA6_1>=88 && LA6_1<=93)) ) {
                    alt6=1;
                }
                else if ( (LA6_1==DOT) ) {
                    int LA6_5 = input.LA(3);

                    if ( ((LA6_5>=60 && LA6_5<=67)) ) {
                        alt6=3;
                    }
                    else if ( ((LA6_5>=21 && LA6_5<=23)||(LA6_5>=36 && LA6_5<=59)) ) {
                        alt6=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("49:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 6, 5, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("49:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 6, 1, input);

                    throw nvae;
                }
                }
                break;
            case 50:
            case 85:
                {
                alt6=4;
                }
                break;
            case 86:
            case 87:
                {
                alt6=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("49:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // Sql.g:49:11: entity
                    {
                    pushFollow(FOLLOW_entity_in_keyword260);
                    entity();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:50:4: entity DOT attr
                    {
                    pushFollow(FOLLOW_entity_in_keyword266);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword268); 
                    pushFollow(FOLLOW_attr_in_keyword270);
                    attr();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Sql.g:51:4: entity DOT funct
                    {
                    pushFollow(FOLLOW_entity_in_keyword275);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword277); 
                    pushFollow(FOLLOW_funct_in_keyword279);
                    funct();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Sql.g:52:4: count spaces '(' spaces entity spaces ')'
                    {
                    pushFollow(FOLLOW_count_in_keyword284);
                    count();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword286);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_keyword288); 
                    pushFollow(FOLLOW_spaces_in_keyword290);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_entity_in_keyword292);
                    entity();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword294);
                    spaces();
                    _fsp--;

                    match(input,16,FOLLOW_16_in_keyword296); 

                    }
                    break;
                case 5 :
                    // Sql.g:53:4: sum spaces '(' spaces entity DOT attr spaces ')'
                    {
                    pushFollow(FOLLOW_sum_in_keyword301);
                    sum();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword303);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_keyword305); 
                    pushFollow(FOLLOW_spaces_in_keyword307);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_entity_in_keyword309);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword311); 
                    pushFollow(FOLLOW_attr_in_keyword313);
                    attr();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword315);
                    spaces();
                    _fsp--;

                    match(input,16,FOLLOW_16_in_keyword317); 

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
    // Sql.g:55:1: constraintList : constraint ( spaces rel= logicalOp spaces constraint )* ;
    public final void constraintList() throws RecognitionException {
        logicalOp_return rel = null;


        try {
            // Sql.g:55:16: ( constraint ( spaces rel= logicalOp spaces constraint )* )
            // Sql.g:55:18: constraint ( spaces rel= logicalOp spaces constraint )*
            {
            pushFollow(FOLLOW_constraint_in_constraintList326);
            constraint();
            _fsp--;

            // Sql.g:55:29: ( spaces rel= logicalOp spaces constraint )*
            loop7:
            do {
                int alt7=2;
                alt7 = dfa7.predict(input);
                switch (alt7) {
            	case 1 :
            	    // Sql.g:55:31: spaces rel= logicalOp spaces constraint
            	    {
            	    pushFollow(FOLLOW_spaces_in_constraintList330);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_logicalOp_in_constraintList337);
            	    rel=logicalOp();
            	    _fsp--;

            	     constraints.add(input.toString(rel.start,rel.stop));
            	    pushFollow(FOLLOW_spaces_in_constraintList345);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_constraint_in_constraintList347);
            	    constraint();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
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
    // Sql.g:59:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue | kw= keyword spaces op3= between spaces val3= betValue );
    public final void constraint() throws RecognitionException {
        keyword_return kw = null;

        compOpt_return op = null;

        genValue_return val = null;

        in_return op1 = null;

        valueList_return val1 = null;

        like_return op2 = null;

        dotValue_return val2 = null;

        between_return op3 = null;

        betValue_return val3 = null;


        try {
            // Sql.g:59:12: (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue | kw= keyword spaces op3= between spaces val3= betValue )
            int alt8=4;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // Sql.g:59:14: kw= keyword spaces op= compOpt spaces val= genValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint360);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint369);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_compOpt_in_constraint376);
                    op=compOpt();
                    _fsp--;

                    c.setOp(input.toString(op.start,op.stop));
                    pushFollow(FOLLOW_spaces_in_constraint386);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_genValue_in_constraint393);
                    val=genValue();
                    _fsp--;

                    c.setValue(input.toString(val.start,val.stop)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:65:2: kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')'
                    {
                    pushFollow(FOLLOW_keyword_in_constraint422);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint431);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_in_in_constraint438);
                    op1=in();
                    _fsp--;

                    c.setOp(input.toString(op1.start,op1.stop));
                    pushFollow(FOLLOW_spaces_in_constraint449);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_constraint451); 
                    pushFollow(FOLLOW_spaces_in_constraint455);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_valueList_in_constraint461);
                    val1=valueList();
                    _fsp--;

                    c.setValue(input.toString(val1.start,val1.stop)); constraints.add(c);
                    pushFollow(FOLLOW_spaces_in_constraint469);
                    spaces();
                    _fsp--;

                    match(input,16,FOLLOW_16_in_constraint473); 

                    }
                    break;
                case 3 :
                    // Sql.g:74:2: kw= keyword spaces op2= like spaces val2= dotValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint499);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint508);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_like_in_constraint515);
                    op2=like();
                    _fsp--;

                    c.setOp(input.toString(op2.start,op2.stop));
                    pushFollow(FOLLOW_spaces_in_constraint524);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_dotValue_in_constraint531);
                    val2=dotValue();
                    _fsp--;

                    c.setValue(input.toString(val2.start,val2.stop)); constraints.add(c);

                    }
                    break;
                case 4 :
                    // Sql.g:80:3: kw= keyword spaces op3= between spaces val3= betValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint546);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint555);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_between_in_constraint562);
                    op3=between();
                    _fsp--;

                    c.setOp(input.toString(op3.start,op3.stop));
                    pushFollow(FOLLOW_spaces_in_constraint570);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_betValue_in_constraint577);
                    val3=betValue();
                    _fsp--;

                    c.setValue(input.toString(val3.start,val3.stop)); constraints.add(c);

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
    // Sql.g:87:1: where : ( 'WHERE' | 'where' ) ;
    public final void where() throws RecognitionException {
        try {
            // Sql.g:87:7: ( ( 'WHERE' | 'where' ) )
            // Sql.g:87:8: ( 'WHERE' | 'where' )
            {
            if ( (input.LA(1)>=17 && input.LA(1)<=18) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_where624);    throw mse;
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
    // Sql.g:88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );
    public final dotValue_return dotValue() throws RecognitionException {
        dotValue_return retval = new dotValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:88:17: ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE )
            int alt9=23;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==VALUE) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA9_3 = input.LA(3);

                    if ( (LA9_3==VALUE) ) {
                        int LA9_6 = input.LA(4);

                        if ( (LA9_6==DOT) ) {
                            int LA9_9 = input.LA(5);

                            if ( (LA9_9==VALUE) ) {
                                int LA9_13 = input.LA(6);

                                if ( (LA9_13==DOT) ) {
                                    int LA9_16 = input.LA(7);

                                    if ( (LA9_16==VALUE) ) {
                                        int LA9_18 = input.LA(8);

                                        if ( (LA9_18==DOT) ) {
                                            int LA9_20 = input.LA(9);

                                            if ( (LA9_20==VALUE) ) {
                                                int LA9_22 = input.LA(10);

                                                if ( (LA9_22==DOT) ) {
                                                    int LA9_24 = input.LA(11);

                                                    if ( (LA9_24==VALUE) ) {
                                                        int LA9_26 = input.LA(12);

                                                        if ( (LA9_26==DOT) ) {
                                                            int LA9_28 = input.LA(13);

                                                            if ( (LA9_28==19) ) {
                                                                alt9=13;
                                                            }
                                                            else if ( (LA9_28==VALUE) ) {
                                                                int LA9_31 = input.LA(14);

                                                                if ( (LA9_31==DOT) ) {
                                                                    int LA9_32 = input.LA(15);

                                                                    if ( (LA9_32==VALUE) ) {
                                                                        int LA9_34 = input.LA(16);

                                                                        if ( (LA9_34==DOT) ) {
                                                                            int LA9_36 = input.LA(17);

                                                                            if ( (LA9_36==VALUE) ) {
                                                                                int LA9_38 = input.LA(18);

                                                                                if ( (LA9_38==DOT) ) {
                                                                                    int LA9_40 = input.LA(19);

                                                                                    if ( (LA9_40==VALUE) ) {
                                                                                        int LA9_42 = input.LA(20);

                                                                                        if ( (LA9_42==DOT) ) {
                                                                                            alt9=21;
                                                                                        }
                                                                                        else if ( (LA9_42==EOF||(LA9_42>=SPACE && LA9_42<=COMMA)||(LA9_42>=EQ && LA9_42<=AMP)||LA9_42==16||(LA9_42>=72 && LA9_42<=75)||(LA9_42>=78 && LA9_42<=79)) ) {
                                                                                            alt9=20;
                                                                                        }
                                                                                        else {
                                                                                            NoViableAltException nvae =
                                                                                                new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 42, input);

                                                                                            throw nvae;
                                                                                        }
                                                                                    }
                                                                                    else if ( (LA9_40==19) ) {
                                                                                        alt9=19;
                                                                                    }
                                                                                    else {
                                                                                        NoViableAltException nvae =
                                                                                            new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 40, input);

                                                                                        throw nvae;
                                                                                    }
                                                                                }
                                                                                else if ( (LA9_38==EOF||(LA9_38>=SPACE && LA9_38<=COMMA)||(LA9_38>=EQ && LA9_38<=AMP)||LA9_38==16||(LA9_38>=72 && LA9_38<=75)||(LA9_38>=78 && LA9_38<=79)) ) {
                                                                                    alt9=18;
                                                                                }
                                                                                else {
                                                                                    NoViableAltException nvae =
                                                                                        new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 38, input);

                                                                                    throw nvae;
                                                                                }
                                                                            }
                                                                            else if ( (LA9_36==19) ) {
                                                                                alt9=17;
                                                                            }
                                                                            else {
                                                                                NoViableAltException nvae =
                                                                                    new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 36, input);

                                                                                throw nvae;
                                                                            }
                                                                        }
                                                                        else if ( (LA9_34==EOF||(LA9_34>=SPACE && LA9_34<=COMMA)||(LA9_34>=EQ && LA9_34<=AMP)||LA9_34==16||(LA9_34>=72 && LA9_34<=75)||(LA9_34>=78 && LA9_34<=79)) ) {
                                                                            alt9=16;
                                                                        }
                                                                        else {
                                                                            NoViableAltException nvae =
                                                                                new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 34, input);

                                                                            throw nvae;
                                                                        }
                                                                    }
                                                                    else if ( (LA9_32==19) ) {
                                                                        alt9=15;
                                                                    }
                                                                    else {
                                                                        NoViableAltException nvae =
                                                                            new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 32, input);

                                                                        throw nvae;
                                                                    }
                                                                }
                                                                else if ( (LA9_31==EOF||(LA9_31>=SPACE && LA9_31<=COMMA)||(LA9_31>=EQ && LA9_31<=AMP)||LA9_31==16||(LA9_31>=72 && LA9_31<=75)||(LA9_31>=78 && LA9_31<=79)) ) {
                                                                    alt9=14;
                                                                }
                                                                else {
                                                                    NoViableAltException nvae =
                                                                        new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 31, input);

                                                                    throw nvae;
                                                                }
                                                            }
                                                            else {
                                                                NoViableAltException nvae =
                                                                    new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 28, input);

                                                                throw nvae;
                                                            }
                                                        }
                                                        else if ( (LA9_26==EOF||(LA9_26>=SPACE && LA9_26<=COMMA)||(LA9_26>=EQ && LA9_26<=AMP)||LA9_26==16||(LA9_26>=72 && LA9_26<=75)||(LA9_26>=78 && LA9_26<=79)) ) {
                                                            alt9=12;
                                                        }
                                                        else {
                                                            NoViableAltException nvae =
                                                                new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 26, input);

                                                            throw nvae;
                                                        }
                                                    }
                                                    else if ( (LA9_24==19) ) {
                                                        alt9=11;
                                                    }
                                                    else {
                                                        NoViableAltException nvae =
                                                            new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 24, input);

                                                        throw nvae;
                                                    }
                                                }
                                                else if ( (LA9_22==EOF||(LA9_22>=SPACE && LA9_22<=COMMA)||(LA9_22>=EQ && LA9_22<=AMP)||LA9_22==16||(LA9_22>=72 && LA9_22<=75)||(LA9_22>=78 && LA9_22<=79)) ) {
                                                    alt9=10;
                                                }
                                                else {
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 22, input);

                                                    throw nvae;
                                                }
                                            }
                                            else if ( (LA9_20==19) ) {
                                                alt9=9;
                                            }
                                            else {
                                                NoViableAltException nvae =
                                                    new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 20, input);

                                                throw nvae;
                                            }
                                        }
                                        else if ( (LA9_18==EOF||(LA9_18>=SPACE && LA9_18<=COMMA)||(LA9_18>=EQ && LA9_18<=AMP)||LA9_18==16||(LA9_18>=72 && LA9_18<=75)||(LA9_18>=78 && LA9_18<=79)) ) {
                                            alt9=8;
                                        }
                                        else {
                                            NoViableAltException nvae =
                                                new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 18, input);

                                            throw nvae;
                                        }
                                    }
                                    else if ( (LA9_16==19) ) {
                                        alt9=7;
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 16, input);

                                        throw nvae;
                                    }
                                }
                                else if ( (LA9_13==EOF||(LA9_13>=SPACE && LA9_13<=COMMA)||(LA9_13>=EQ && LA9_13<=AMP)||LA9_13==16||(LA9_13>=72 && LA9_13<=75)||(LA9_13>=78 && LA9_13<=79)) ) {
                                    alt9=6;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 13, input);

                                    throw nvae;
                                }
                            }
                            else if ( (LA9_9==19) ) {
                                alt9=5;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 9, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA9_6==EOF||(LA9_6>=SPACE && LA9_6<=COMMA)||(LA9_6>=EQ && LA9_6<=AMP)||LA9_6==16||(LA9_6>=72 && LA9_6<=75)||(LA9_6>=78 && LA9_6<=79)) ) {
                            alt9=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 6, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA9_3==19) ) {
                        alt9=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case SPACE:
                    {
                    int LA9_4 = input.LA(3);

                    if ( (LA9_4==VALUE) ) {
                        int LA9_8 = input.LA(4);

                        if ( (LA9_8==SPACE) ) {
                            int LA9_11 = input.LA(5);

                            if ( (LA9_11==VALUE) ) {
                                alt9=23;
                            }
                            else if ( (LA9_11==EOF||(LA9_11>=SPACE && LA9_11<=COMMA)||LA9_11==16||(LA9_11>=72 && LA9_11<=75)||(LA9_11>=78 && LA9_11<=79)) ) {
                                alt9=22;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 11, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA9_8==EOF||LA9_8==COMMA||(LA9_8>=EQ && LA9_8<=AMP)||LA9_8==16||(LA9_8>=72 && LA9_8<=75)||(LA9_8>=78 && LA9_8<=79)) ) {
                            alt9=22;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 8, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA9_4==EOF||(LA9_4>=SPACE && LA9_4<=COMMA)||LA9_4==16||(LA9_4>=72 && LA9_4<=75)||(LA9_4>=78 && LA9_4<=79)) ) {
                        alt9=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case EOF:
                case COMMA:
                case EQ:
                case LT:
                case GT:
                case NOT:
                case AMP:
                case 16:
                case 72:
                case 73:
                case 74:
                case 75:
                case 78:
                case 79:
                    {
                    alt9=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 1, input);

                    throw nvae;
                }

            }
            else if ( (LA9_0==19) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // Sql.g:88:19: VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue644); 

                    }
                    break;
                case 2 :
                    // Sql.g:89:5: 'in'
                    {
                    match(input,19,FOLLOW_19_in_dotValue651); 

                    }
                    break;
                case 3 :
                    // Sql.g:90:5: VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue657); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue659); 
                    match(input,19,FOLLOW_19_in_dotValue661); 

                    }
                    break;
                case 4 :
                    // Sql.g:91:5: VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue667); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue669); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue671); 

                    }
                    break;
                case 5 :
                    // Sql.g:92:5: VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue677); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue679); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue681); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue683); 
                    match(input,19,FOLLOW_19_in_dotValue685); 

                    }
                    break;
                case 6 :
                    // Sql.g:93:5: VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue691); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue693); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue695); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue697); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue699); 

                    }
                    break;
                case 7 :
                    // Sql.g:94:5: VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue705); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue707); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue709); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue711); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue713); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue715); 
                    match(input,19,FOLLOW_19_in_dotValue717); 

                    }
                    break;
                case 8 :
                    // Sql.g:95:5: VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue723); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue725); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue727); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue729); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue731); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue733); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue735); 

                    }
                    break;
                case 9 :
                    // Sql.g:96:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue741); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue743); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue745); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue747); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue749); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue751); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue753); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue755); 
                    match(input,19,FOLLOW_19_in_dotValue757); 

                    }
                    break;
                case 10 :
                    // Sql.g:97:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue763); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue765); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue767); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue769); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue771); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue773); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue775); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue777); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue779); 

                    }
                    break;
                case 11 :
                    // Sql.g:98:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue785); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue787); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue789); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue791); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue793); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue795); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue797); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue799); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue801); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue803); 
                    match(input,19,FOLLOW_19_in_dotValue805); 

                    }
                    break;
                case 12 :
                    // Sql.g:99:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue811); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue813); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue815); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue817); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue819); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue821); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue823); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue825); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue827); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue829); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue831); 

                    }
                    break;
                case 13 :
                    // Sql.g:100:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue837); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue839); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue841); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue843); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue845); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue847); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue849); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue851); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue853); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue855); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue857); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue859); 
                    match(input,19,FOLLOW_19_in_dotValue861); 

                    }
                    break;
                case 14 :
                    // Sql.g:101:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue867); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue869); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue871); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue873); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue875); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue877); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue879); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue881); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue883); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue885); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue887); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue889); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue891); 

                    }
                    break;
                case 15 :
                    // Sql.g:102:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue897); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue899); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue901); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue903); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue905); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue907); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue909); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue911); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue913); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue915); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue917); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue919); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue921); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue923); 
                    match(input,19,FOLLOW_19_in_dotValue925); 

                    }
                    break;
                case 16 :
                    // Sql.g:103:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue931); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue933); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue935); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue937); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue939); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue941); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue943); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue945); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue947); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue949); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue951); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue953); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue955); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue957); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue959); 

                    }
                    break;
                case 17 :
                    // Sql.g:104:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue965); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue967); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue969); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue971); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue973); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue975); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue977); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue979); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue981); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue983); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue985); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue987); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue989); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue991); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue993); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue995); 
                    match(input,19,FOLLOW_19_in_dotValue997); 

                    }
                    break;
                case 18 :
                    // Sql.g:105:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1003); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1005); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1007); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1009); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1011); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1013); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1015); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1017); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1019); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1021); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1023); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1025); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1027); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1029); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1031); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1033); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1035); 

                    }
                    break;
                case 19 :
                    // Sql.g:106:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1042); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1044); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1046); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1048); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1050); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1052); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1054); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1056); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1058); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1060); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1062); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1064); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1066); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1068); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1070); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1072); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1074); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1076); 
                    match(input,19,FOLLOW_19_in_dotValue1078); 

                    }
                    break;
                case 20 :
                    // Sql.g:107:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1084); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1086); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1088); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1090); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1092); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1094); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1096); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1098); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1100); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1102); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1104); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1106); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1108); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1110); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1112); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1114); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1116); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1118); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1120); 

                    }
                    break;
                case 21 :
                    // Sql.g:108:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1127); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1129); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1131); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1133); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1135); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1137); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1139); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1141); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1143); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1145); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1147); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1149); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1151); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1153); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1155); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1157); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1159); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1161); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1163); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1165); 
                    match(input,19,FOLLOW_19_in_dotValue1167); 

                    }
                    break;
                case 22 :
                    // Sql.g:109:5: VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1173); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1175); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1177); 

                    }
                    break;
                case 23 :
                    // Sql.g:110:5: VALUE SPACE VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1183); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1185); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1187); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1189); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1191); 

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
    // Sql.g:114:1: valueList : dotValue ( spaces COMMA spaces dotValue )* ;
    public final valueList_return valueList() throws RecognitionException {
        valueList_return retval = new valueList_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:114:11: ( dotValue ( spaces COMMA spaces dotValue )* )
            // Sql.g:114:12: dotValue ( spaces COMMA spaces dotValue )*
            {
            pushFollow(FOLLOW_dotValue_in_valueList1200);
            dotValue();
            _fsp--;

            // Sql.g:114:21: ( spaces COMMA spaces dotValue )*
            loop10:
            do {
                int alt10=2;
                alt10 = dfa10.predict(input);
                switch (alt10) {
            	case 1 :
            	    // Sql.g:114:23: spaces COMMA spaces dotValue
            	    {
            	    pushFollow(FOLLOW_spaces_in_valueList1204);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_valueList1206); 
            	    pushFollow(FOLLOW_spaces_in_valueList1208);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_dotValue_in_valueList1210);
            	    dotValue();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
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
    // Sql.g:116:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );
    public final compOpt_return compOpt() throws RecognitionException {
        compOpt_return retval = new compOpt_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:116:10: ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) )
            int alt11=9;
            switch ( input.LA(1) ) {
            case EQ:
                {
                switch ( input.LA(2) ) {
                case SPACE:
                case VALUE:
                case 19:
                    {
                    alt11=1;
                    }
                    break;
                case LT:
                    {
                    alt11=6;
                    }
                    break;
                case GT:
                    {
                    alt11=5;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("116:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );", 11, 1, input);

                    throw nvae;
                }

                }
                break;
            case LT:
                {
                switch ( input.LA(2) ) {
                case EQ:
                    {
                    alt11=7;
                    }
                    break;
                case GT:
                    {
                    alt11=9;
                    }
                    break;
                case SPACE:
                case VALUE:
                case 19:
                    {
                    alt11=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("116:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );", 11, 2, input);

                    throw nvae;
                }

                }
                break;
            case GT:
                {
                int LA11_3 = input.LA(2);

                if ( (LA11_3==SPACE||LA11_3==VALUE||LA11_3==19) ) {
                    alt11=3;
                }
                else if ( (LA11_3==EQ) ) {
                    alt11=8;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("116:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );", 11, 3, input);

                    throw nvae;
                }
                }
                break;
            case NOT:
                {
                alt11=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("116:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // Sql.g:116:11: ( EQ )
                    {
                    // Sql.g:116:11: ( EQ )
                    // Sql.g:116:12: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1222); 

                    }


                    }
                    break;
                case 2 :
                    // Sql.g:117:4: ( LT )
                    {
                    // Sql.g:117:4: ( LT )
                    // Sql.g:117:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1229); 

                    }


                    }
                    break;
                case 3 :
                    // Sql.g:118:4: ( GT )
                    {
                    // Sql.g:118:4: ( GT )
                    // Sql.g:118:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1236); 

                    }


                    }
                    break;
                case 4 :
                    // Sql.g:119:4: ( NOT ) ( EQ )
                    {
                    // Sql.g:119:4: ( NOT )
                    // Sql.g:119:5: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_compOpt1243); 

                    }

                    // Sql.g:119:9: ( EQ )
                    // Sql.g:119:10: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1246); 

                    }


                    }
                    break;
                case 5 :
                    // Sql.g:120:4: ( EQ ) ( GT )
                    {
                    // Sql.g:120:4: ( EQ )
                    // Sql.g:120:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1253); 

                    }

                    // Sql.g:120:8: ( GT )
                    // Sql.g:120:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1256); 

                    }


                    }
                    break;
                case 6 :
                    // Sql.g:121:4: ( EQ ) ( LT )
                    {
                    // Sql.g:121:4: ( EQ )
                    // Sql.g:121:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1263); 

                    }

                    // Sql.g:121:8: ( LT )
                    // Sql.g:121:9: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1266); 

                    }


                    }
                    break;
                case 7 :
                    // Sql.g:122:4: ( LT ) ( EQ )
                    {
                    // Sql.g:122:4: ( LT )
                    // Sql.g:122:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1273); 

                    }

                    // Sql.g:122:8: ( EQ )
                    // Sql.g:122:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1276); 

                    }


                    }
                    break;
                case 8 :
                    // Sql.g:123:4: ( GT ) ( EQ )
                    {
                    // Sql.g:123:4: ( GT )
                    // Sql.g:123:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1283); 

                    }

                    // Sql.g:123:8: ( EQ )
                    // Sql.g:123:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1286); 

                    }


                    }
                    break;
                case 9 :
                    // Sql.g:124:4: ( LT ) ( GT )
                    {
                    // Sql.g:124:4: ( LT )
                    // Sql.g:124:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1293); 

                    }

                    // Sql.g:124:8: ( GT )
                    // Sql.g:124:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1296); 

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
    // Sql.g:126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );
    public final genValue_return genValue() throws RecognitionException {
        genValue_return retval = new genValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:126:10: ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==VALUE) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA13_3 = input.LA(3);

                    if ( (LA13_3==VALUE) ) {
                        switch ( input.LA(4) ) {
                        case DOT:
                            {
                            int LA13_10 = input.LA(5);

                            if ( (LA13_10==VALUE) ) {
                                switch ( input.LA(6) ) {
                                case DOT:
                                    {
                                    int LA13_15 = input.LA(7);

                                    if ( (LA13_15==VALUE) ) {
                                        switch ( input.LA(8) ) {
                                        case DOT:
                                            {
                                            int LA13_18 = input.LA(9);

                                            if ( (LA13_18==19) ) {
                                                int LA13_19 = input.LA(10);

                                                if ( ((LA13_19>=EQ && LA13_19<=NOT)) ) {
                                                    alt13=2;
                                                }
                                                else if ( (LA13_19==EOF||LA13_19==SPACE||(LA13_19>=72 && LA13_19<=75)||(LA13_19>=78 && LA13_19<=79)) ) {
                                                    alt13=1;
                                                }
                                                else {
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 19, input);

                                                    throw nvae;
                                                }
                                            }
                                            else if ( (LA13_18==VALUE) ) {
                                                switch ( input.LA(10) ) {
                                                case DOT:
                                                    {
                                                    int LA13_21 = input.LA(11);

                                                    if ( (LA13_21==VALUE) ) {
                                                        switch ( input.LA(12) ) {
                                                        case DOT:
                                                            {
                                                            int LA13_24 = input.LA(13);

                                                            if ( (LA13_24==VALUE) ) {
                                                                switch ( input.LA(14) ) {
                                                                case DOT:
                                                                    {
                                                                    int LA13_27 = input.LA(15);

                                                                    if ( (LA13_27==VALUE) ) {
                                                                        switch ( input.LA(16) ) {
                                                                        case DOT:
                                                                            {
                                                                            int LA13_30 = input.LA(17);

                                                                            if ( (LA13_30==VALUE) ) {
                                                                                switch ( input.LA(18) ) {
                                                                                case DOT:
                                                                                    {
                                                                                    int LA13_33 = input.LA(19);

                                                                                    if ( (LA13_33==19) ) {
                                                                                        int LA13_34 = input.LA(20);

                                                                                        if ( (LA13_34==EOF||LA13_34==SPACE||(LA13_34>=72 && LA13_34<=75)||(LA13_34>=78 && LA13_34<=79)) ) {
                                                                                            alt13=1;
                                                                                        }
                                                                                        else if ( ((LA13_34>=EQ && LA13_34<=NOT)) ) {
                                                                                            alt13=2;
                                                                                        }
                                                                                        else {
                                                                                            NoViableAltException nvae =
                                                                                                new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 34, input);

                                                                                            throw nvae;
                                                                                        }
                                                                                    }
                                                                                    else if ( (LA13_33==VALUE) ) {
                                                                                        switch ( input.LA(20) ) {
                                                                                        case DOT:
                                                                                            {
                                                                                            int LA13_36 = input.LA(21);

                                                                                            if ( (LA13_36==19) ) {
                                                                                                int LA13_37 = input.LA(22);

                                                                                                if ( (LA13_37==EOF||LA13_37==SPACE||(LA13_37>=72 && LA13_37<=75)||(LA13_37>=78 && LA13_37<=79)) ) {
                                                                                                    alt13=1;
                                                                                                }
                                                                                                else if ( ((LA13_37>=EQ && LA13_37<=NOT)) ) {
                                                                                                    alt13=2;
                                                                                                }
                                                                                                else {
                                                                                                    NoViableAltException nvae =
                                                                                                        new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 37, input);

                                                                                                    throw nvae;
                                                                                                }
                                                                                            }
                                                                                            else {
                                                                                                NoViableAltException nvae =
                                                                                                    new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 36, input);

                                                                                                throw nvae;
                                                                                            }
                                                                                            }
                                                                                            break;
                                                                                        case EQ:
                                                                                        case LT:
                                                                                        case GT:
                                                                                        case NOT:
                                                                                            {
                                                                                            alt13=2;
                                                                                            }
                                                                                            break;
                                                                                        case EOF:
                                                                                        case SPACE:
                                                                                        case 72:
                                                                                        case 73:
                                                                                        case 74:
                                                                                        case 75:
                                                                                        case 78:
                                                                                        case 79:
                                                                                            {
                                                                                            alt13=1;
                                                                                            }
                                                                                            break;
                                                                                        default:
                                                                                            NoViableAltException nvae =
                                                                                                new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 35, input);

                                                                                            throw nvae;
                                                                                        }

                                                                                    }
                                                                                    else {
                                                                                        NoViableAltException nvae =
                                                                                            new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 33, input);

                                                                                        throw nvae;
                                                                                    }
                                                                                    }
                                                                                    break;
                                                                                case EQ:
                                                                                case LT:
                                                                                case GT:
                                                                                case NOT:
                                                                                    {
                                                                                    alt13=2;
                                                                                    }
                                                                                    break;
                                                                                case EOF:
                                                                                case SPACE:
                                                                                case 72:
                                                                                case 73:
                                                                                case 74:
                                                                                case 75:
                                                                                case 78:
                                                                                case 79:
                                                                                    {
                                                                                    alt13=1;
                                                                                    }
                                                                                    break;
                                                                                default:
                                                                                    NoViableAltException nvae =
                                                                                        new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 31, input);

                                                                                    throw nvae;
                                                                                }

                                                                            }
                                                                            else if ( (LA13_30==19) ) {
                                                                                int LA13_32 = input.LA(18);

                                                                                if ( ((LA13_32>=EQ && LA13_32<=NOT)) ) {
                                                                                    alt13=2;
                                                                                }
                                                                                else if ( (LA13_32==EOF||LA13_32==SPACE||(LA13_32>=72 && LA13_32<=75)||(LA13_32>=78 && LA13_32<=79)) ) {
                                                                                    alt13=1;
                                                                                }
                                                                                else {
                                                                                    NoViableAltException nvae =
                                                                                        new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 32, input);

                                                                                    throw nvae;
                                                                                }
                                                                            }
                                                                            else {
                                                                                NoViableAltException nvae =
                                                                                    new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 30, input);

                                                                                throw nvae;
                                                                            }
                                                                            }
                                                                            break;
                                                                        case EQ:
                                                                        case LT:
                                                                        case GT:
                                                                        case NOT:
                                                                            {
                                                                            alt13=2;
                                                                            }
                                                                            break;
                                                                        case EOF:
                                                                        case SPACE:
                                                                        case 72:
                                                                        case 73:
                                                                        case 74:
                                                                        case 75:
                                                                        case 78:
                                                                        case 79:
                                                                            {
                                                                            alt13=1;
                                                                            }
                                                                            break;
                                                                        default:
                                                                            NoViableAltException nvae =
                                                                                new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 28, input);

                                                                            throw nvae;
                                                                        }

                                                                    }
                                                                    else if ( (LA13_27==19) ) {
                                                                        int LA13_29 = input.LA(16);

                                                                        if ( ((LA13_29>=EQ && LA13_29<=NOT)) ) {
                                                                            alt13=2;
                                                                        }
                                                                        else if ( (LA13_29==EOF||LA13_29==SPACE||(LA13_29>=72 && LA13_29<=75)||(LA13_29>=78 && LA13_29<=79)) ) {
                                                                            alt13=1;
                                                                        }
                                                                        else {
                                                                            NoViableAltException nvae =
                                                                                new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 29, input);

                                                                            throw nvae;
                                                                        }
                                                                    }
                                                                    else {
                                                                        NoViableAltException nvae =
                                                                            new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 27, input);

                                                                        throw nvae;
                                                                    }
                                                                    }
                                                                    break;
                                                                case EOF:
                                                                case SPACE:
                                                                case 72:
                                                                case 73:
                                                                case 74:
                                                                case 75:
                                                                case 78:
                                                                case 79:
                                                                    {
                                                                    alt13=1;
                                                                    }
                                                                    break;
                                                                case EQ:
                                                                case LT:
                                                                case GT:
                                                                case NOT:
                                                                    {
                                                                    alt13=2;
                                                                    }
                                                                    break;
                                                                default:
                                                                    NoViableAltException nvae =
                                                                        new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 25, input);

                                                                    throw nvae;
                                                                }

                                                            }
                                                            else if ( (LA13_24==19) ) {
                                                                int LA13_26 = input.LA(14);

                                                                if ( (LA13_26==EOF||LA13_26==SPACE||(LA13_26>=72 && LA13_26<=75)||(LA13_26>=78 && LA13_26<=79)) ) {
                                                                    alt13=1;
                                                                }
                                                                else if ( ((LA13_26>=EQ && LA13_26<=NOT)) ) {
                                                                    alt13=2;
                                                                }
                                                                else {
                                                                    NoViableAltException nvae =
                                                                        new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 26, input);

                                                                    throw nvae;
                                                                }
                                                            }
                                                            else {
                                                                NoViableAltException nvae =
                                                                    new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 24, input);

                                                                throw nvae;
                                                            }
                                                            }
                                                            break;
                                                        case EOF:
                                                        case SPACE:
                                                        case 72:
                                                        case 73:
                                                        case 74:
                                                        case 75:
                                                        case 78:
                                                        case 79:
                                                            {
                                                            alt13=1;
                                                            }
                                                            break;
                                                        case EQ:
                                                        case LT:
                                                        case GT:
                                                        case NOT:
                                                            {
                                                            alt13=2;
                                                            }
                                                            break;
                                                        default:
                                                            NoViableAltException nvae =
                                                                new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 22, input);

                                                            throw nvae;
                                                        }

                                                    }
                                                    else if ( (LA13_21==19) ) {
                                                        int LA13_23 = input.LA(12);

                                                        if ( (LA13_23==EOF||LA13_23==SPACE||(LA13_23>=72 && LA13_23<=75)||(LA13_23>=78 && LA13_23<=79)) ) {
                                                            alt13=1;
                                                        }
                                                        else if ( ((LA13_23>=EQ && LA13_23<=NOT)) ) {
                                                            alt13=2;
                                                        }
                                                        else {
                                                            NoViableAltException nvae =
                                                                new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 23, input);

                                                            throw nvae;
                                                        }
                                                    }
                                                    else {
                                                        NoViableAltException nvae =
                                                            new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 21, input);

                                                        throw nvae;
                                                    }
                                                    }
                                                    break;
                                                case EQ:
                                                case LT:
                                                case GT:
                                                case NOT:
                                                    {
                                                    alt13=2;
                                                    }
                                                    break;
                                                case EOF:
                                                case SPACE:
                                                case 72:
                                                case 73:
                                                case 74:
                                                case 75:
                                                case 78:
                                                case 79:
                                                    {
                                                    alt13=1;
                                                    }
                                                    break;
                                                default:
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 20, input);

                                                    throw nvae;
                                                }

                                            }
                                            else {
                                                NoViableAltException nvae =
                                                    new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 18, input);

                                                throw nvae;
                                            }
                                            }
                                            break;
                                        case EQ:
                                        case LT:
                                        case GT:
                                        case NOT:
                                            {
                                            alt13=2;
                                            }
                                            break;
                                        case EOF:
                                        case SPACE:
                                        case 72:
                                        case 73:
                                        case 74:
                                        case 75:
                                        case 78:
                                        case 79:
                                            {
                                            alt13=1;
                                            }
                                            break;
                                        default:
                                            NoViableAltException nvae =
                                                new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 16, input);

                                            throw nvae;
                                        }

                                    }
                                    else if ( (LA13_15==19) ) {
                                        int LA13_17 = input.LA(8);

                                        if ( ((LA13_17>=EQ && LA13_17<=NOT)) ) {
                                            alt13=2;
                                        }
                                        else if ( (LA13_17==EOF||LA13_17==SPACE||(LA13_17>=72 && LA13_17<=75)||(LA13_17>=78 && LA13_17<=79)) ) {
                                            alt13=1;
                                        }
                                        else {
                                            NoViableAltException nvae =
                                                new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 17, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 15, input);

                                        throw nvae;
                                    }
                                    }
                                    break;
                                case EQ:
                                case LT:
                                case GT:
                                case NOT:
                                    {
                                    alt13=2;
                                    }
                                    break;
                                case EOF:
                                case SPACE:
                                case 72:
                                case 73:
                                case 74:
                                case 75:
                                case 78:
                                case 79:
                                    {
                                    alt13=1;
                                    }
                                    break;
                                default:
                                    NoViableAltException nvae =
                                        new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 12, input);

                                    throw nvae;
                                }

                            }
                            else if ( (LA13_10==19) ) {
                                int LA13_13 = input.LA(6);

                                if ( (LA13_13==EOF||LA13_13==SPACE||(LA13_13>=72 && LA13_13<=75)||(LA13_13>=78 && LA13_13<=79)) ) {
                                    alt13=1;
                                }
                                else if ( ((LA13_13>=EQ && LA13_13<=NOT)) ) {
                                    alt13=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 13, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 10, input);

                                throw nvae;
                            }
                            }
                            break;
                        case EOF:
                        case SPACE:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 78:
                        case 79:
                            {
                            alt13=1;
                            }
                            break;
                        case EQ:
                        case LT:
                        case GT:
                        case NOT:
                            {
                            alt13=2;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 7, input);

                            throw nvae;
                        }

                    }
                    else if ( (LA13_3==19) ) {
                        int LA13_8 = input.LA(4);

                        if ( (LA13_8==EOF||LA13_8==SPACE||(LA13_8>=72 && LA13_8<=75)||(LA13_8>=78 && LA13_8<=79)) ) {
                            alt13=1;
                        }
                        else if ( ((LA13_8>=EQ && LA13_8<=NOT)) ) {
                            alt13=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 8, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case SPACE:
                    {
                    int LA13_4 = input.LA(3);

                    if ( (LA13_4==VALUE) ) {
                        switch ( input.LA(4) ) {
                        case SPACE:
                            {
                            int LA13_11 = input.LA(5);

                            if ( (LA13_11==VALUE) ) {
                                int LA13_14 = input.LA(6);

                                if ( (LA13_14==EOF||LA13_14==SPACE||(LA13_14>=72 && LA13_14<=75)||(LA13_14>=78 && LA13_14<=79)) ) {
                                    alt13=1;
                                }
                                else if ( ((LA13_14>=EQ && LA13_14<=NOT)) ) {
                                    alt13=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 14, input);

                                    throw nvae;
                                }
                            }
                            else if ( (LA13_11==EOF||LA13_11==SPACE||(LA13_11>=72 && LA13_11<=75)||(LA13_11>=78 && LA13_11<=79)) ) {
                                alt13=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 11, input);

                                throw nvae;
                            }
                            }
                            break;
                        case EOF:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 78:
                        case 79:
                            {
                            alt13=1;
                            }
                            break;
                        case EQ:
                        case LT:
                        case GT:
                        case NOT:
                            {
                            alt13=2;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 9, input);

                            throw nvae;
                        }

                    }
                    else if ( (LA13_4==EOF||LA13_4==SPACE||(LA13_4>=72 && LA13_4<=75)||(LA13_4>=78 && LA13_4<=79)) ) {
                        alt13=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case EOF:
                case 72:
                case 73:
                case 74:
                case 75:
                case 78:
                case 79:
                    {
                    alt13=1;
                    }
                    break;
                case EQ:
                case LT:
                case GT:
                case NOT:
                    {
                    alt13=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 1, input);

                    throw nvae;
                }

            }
            else if ( (LA13_0==19) ) {
                int LA13_2 = input.LA(2);

                if ( ((LA13_2>=EQ && LA13_2<=NOT)) ) {
                    alt13=2;
                }
                else if ( (LA13_2==EOF||LA13_2==SPACE||(LA13_2>=72 && LA13_2<=75)||(LA13_2>=78 && LA13_2<=79)) ) {
                    alt13=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // Sql.g:126:11: dotValue
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue1304);
                    dotValue();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:127:4: dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )*
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue1309);
                    dotValue();
                    _fsp--;

                    pushFollow(FOLLOW_compOpt_in_genValue1311);
                    compOpt();
                    _fsp--;

                    pushFollow(FOLLOW_dotValue_in_genValue1313);
                    dotValue();
                    _fsp--;

                    // Sql.g:127:30: ( AMP dotValue compOpt dotValue )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==AMP) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // Sql.g:127:31: AMP dotValue compOpt dotValue
                    	    {
                    	    match(input,AMP,FOLLOW_AMP_in_genValue1316); 
                    	    pushFollow(FOLLOW_dotValue_in_genValue1318);
                    	    dotValue();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_compOpt_in_genValue1320);
                    	    compOpt();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_dotValue_in_genValue1322);
                    	    dotValue();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
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

    public static class betValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start betValue
    // Sql.g:128:1: betValue : dotValue spaces and spaces dotValue ;
    public final betValue_return betValue() throws RecognitionException {
        betValue_return retval = new betValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:128:10: ( dotValue spaces and spaces dotValue )
            // Sql.g:128:11: dotValue spaces and spaces dotValue
            {
            pushFollow(FOLLOW_dotValue_in_betValue1330);
            dotValue();
            _fsp--;

            pushFollow(FOLLOW_spaces_in_betValue1332);
            spaces();
            _fsp--;

            pushFollow(FOLLOW_and_in_betValue1334);
            and();
            _fsp--;

            pushFollow(FOLLOW_spaces_in_betValue1336);
            spaces();
            _fsp--;

            pushFollow(FOLLOW_dotValue_in_betValue1338);
            dotValue();
            _fsp--;


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
    // $ANTLR end betValue

    public static class logicalOp_return extends ParserRuleReturnScope {
    };

    // $ANTLR start logicalOp
    // Sql.g:134:1: logicalOp : ( and | or ) ;
    public final logicalOp_return logicalOp() throws RecognitionException {
        logicalOp_return retval = new logicalOp_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:134:11: ( ( and | or ) )
            // Sql.g:134:12: ( and | or )
            {
            // Sql.g:134:12: ( and | or )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=72 && LA14_0<=73)) ) {
                alt14=1;
            }
            else if ( ((LA14_0>=78 && LA14_0<=79)) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("134:12: ( and | or )", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // Sql.g:134:13: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp1350);
                    and();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:134:17: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp1352);
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
    // Sql.g:135:1: entity : ( 'ads' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' ) ;
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:135:9: ( ( 'ads' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' ) )
            // Sql.g:135:11: ( 'ads' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' )
            {
            if ( (input.LA(1)>=20 && input.LA(1)<=35) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_entity1361);    throw mse;
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
    // Sql.g:136:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) ;
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:136:7: ( ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) )
            // Sql.g:136:8: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' )
            {
            if ( (input.LA(1)>=21 && input.LA(1)<=23)||(input.LA(1)>=36 && input.LA(1)<=59) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_attr1430);    throw mse;
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
    // Sql.g:137:1: funct : ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) ;
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:137:8: ( ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) )
            // Sql.g:137:9: ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' )
            {
            if ( (input.LA(1)>=60 && input.LA(1)<=67) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_funct1543);    throw mse;
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
    // Sql.g:138:1: select : ( 'select' | 'SELECT' | 'find' | 'FIND' ) ;
    public final void select() throws RecognitionException {
        try {
            // Sql.g:138:9: ( ( 'select' | 'SELECT' | 'find' | 'FIND' ) )
            // Sql.g:138:10: ( 'select' | 'SELECT' | 'find' | 'FIND' )
            {
            if ( (input.LA(1)>=68 && input.LA(1)<=71) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_select1581);    throw mse;
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
    // Sql.g:139:1: and : ( 'and' | 'AND' ) ;
    public final void and() throws RecognitionException {
        try {
            // Sql.g:139:6: ( ( 'and' | 'AND' ) )
            // Sql.g:139:7: ( 'and' | 'AND' )
            {
            if ( (input.LA(1)>=72 && input.LA(1)<=73) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_and1602);    throw mse;
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
    // Sql.g:140:1: order : ( 'order' | 'ORDER' ) ;
    public final void order() throws RecognitionException {
        try {
            // Sql.g:140:8: ( ( 'order' | 'ORDER' ) )
            // Sql.g:140:9: ( 'order' | 'ORDER' )
            {
            if ( (input.LA(1)>=74 && input.LA(1)<=75) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_order1615);    throw mse;
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
    // Sql.g:141:1: by : ( 'by' | 'BY' ) ;
    public final void by() throws RecognitionException {
        try {
            // Sql.g:141:5: ( ( 'by' | 'BY' ) )
            // Sql.g:141:6: ( 'by' | 'BY' )
            {
            if ( (input.LA(1)>=76 && input.LA(1)<=77) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_by1628);    throw mse;
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
    // Sql.g:142:1: or : ( 'or' | 'OR' ) ;
    public final void or() throws RecognitionException {
        try {
            // Sql.g:142:5: ( ( 'or' | 'OR' ) )
            // Sql.g:142:6: ( 'or' | 'OR' )
            {
            if ( (input.LA(1)>=78 && input.LA(1)<=79) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_or1641);    throw mse;
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
    // Sql.g:143:1: in : ( 'in' | 'IN' ) ;
    public final in_return in() throws RecognitionException {
        in_return retval = new in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:143:5: ( ( 'in' | 'IN' ) )
            // Sql.g:143:6: ( 'in' | 'IN' )
            {
            if ( input.LA(1)==19||input.LA(1)==80 ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_in1654);    throw mse;
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
    // Sql.g:144:1: not : ( 'not' | 'NOT' ) ;
    public final void not() throws RecognitionException {
        try {
            // Sql.g:144:6: ( ( 'not' | 'NOT' ) )
            // Sql.g:144:7: ( 'not' | 'NOT' )
            {
            if ( (input.LA(1)>=81 && input.LA(1)<=82) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_not1667);    throw mse;
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
    // Sql.g:145:1: like : ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' ) ;
    public final like_return like() throws RecognitionException {
        like_return retval = new like_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:145:7: ( ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' ) )
            // Sql.g:145:8: ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' )
            {
            // Sql.g:145:8: ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' )
            int alt15=4;
            switch ( input.LA(1) ) {
            case 83:
                {
                alt15=1;
                }
                break;
            case 84:
                {
                alt15=2;
                }
                break;
            case 81:
                {
                alt15=3;
                }
                break;
            case 82:
                {
                alt15=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("145:8: ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' )", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // Sql.g:145:9: 'like'
                    {
                    match(input,83,FOLLOW_83_in_like1681); 

                    }
                    break;
                case 2 :
                    // Sql.g:145:18: 'LIKE'
                    {
                    match(input,84,FOLLOW_84_in_like1685); 

                    }
                    break;
                case 3 :
                    // Sql.g:145:27: 'not' spaces 'like'
                    {
                    match(input,81,FOLLOW_81_in_like1689); 
                    pushFollow(FOLLOW_spaces_in_like1691);
                    spaces();
                    _fsp--;

                    match(input,83,FOLLOW_83_in_like1693); 

                    }
                    break;
                case 4 :
                    // Sql.g:145:49: 'NOT' spaces 'LIKE'
                    {
                    match(input,82,FOLLOW_82_in_like1697); 
                    pushFollow(FOLLOW_spaces_in_like1699);
                    spaces();
                    _fsp--;

                    match(input,84,FOLLOW_84_in_like1701); 

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
    // $ANTLR end like


    // $ANTLR start count
    // Sql.g:147:1: count : ( 'count' | 'COUNT' ) ;
    public final void count() throws RecognitionException {
        try {
            // Sql.g:147:8: ( ( 'count' | 'COUNT' ) )
            // Sql.g:147:9: ( 'count' | 'COUNT' )
            {
            if ( input.LA(1)==50||input.LA(1)==85 ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_count1710);    throw mse;
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
    // Sql.g:148:1: sum : ( 'sum' | 'SUM' ) ;
    public final void sum() throws RecognitionException {
        try {
            // Sql.g:148:6: ( ( 'sum' | 'SUM' ) )
            // Sql.g:148:7: ( 'sum' | 'SUM' )
            {
            if ( (input.LA(1)>=86 && input.LA(1)<=87) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_sum1723);    throw mse;
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


    // $ANTLR start asc
    // Sql.g:149:1: asc : ( 'asc' | 'ASC' ) ;
    public final void asc() throws RecognitionException {
        try {
            // Sql.g:149:6: ( ( 'asc' | 'ASC' ) )
            // Sql.g:149:7: ( 'asc' | 'ASC' )
            {
            if ( (input.LA(1)>=88 && input.LA(1)<=89) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_asc1736);    throw mse;
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
    // $ANTLR end asc


    // $ANTLR start desc
    // Sql.g:150:1: desc : ( 'desc' | 'DESC' ) ;
    public final void desc() throws RecognitionException {
        try {
            // Sql.g:150:7: ( ( 'desc' | 'DESC' ) )
            // Sql.g:150:8: ( 'desc' | 'DESC' )
            {
            if ( (input.LA(1)>=90 && input.LA(1)<=91) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_desc1749);    throw mse;
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
    // $ANTLR end desc

    public static class between_return extends ParserRuleReturnScope {
    };

    // $ANTLR start between
    // Sql.g:151:1: between : ( 'between' | 'BETWEEN' ) ;
    public final between_return between() throws RecognitionException {
        between_return retval = new between_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:151:10: ( ( 'between' | 'BETWEEN' ) )
            // Sql.g:151:11: ( 'between' | 'BETWEEN' )
            {
            if ( (input.LA(1)>=92 && input.LA(1)<=93) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_between1762);    throw mse;
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
    // $ANTLR end between


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA3 dfa3 = new DFA3(this);
    protected DFA5 dfa5 = new DFA5(this);
    protected DFA7 dfa7 = new DFA7(this);
    protected DFA8 dfa8 = new DFA8(this);
    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA1_eotS =
        "\u0344\uffff";
    static final String DFA1_eofS =
        "\3\uffff\1\12\3\uffff\1\12\10\uffff\2\12\1\uffff\1\12\40\uffff\1"+
        "\12\1\uffff\2\12\13\uffff\2\150\10\uffff\2\150\12\uffff\1\12\6\uffff"+
        "\1\150\10\uffff\1\150\4\uffff\1\150\4\uffff\1\12\5\uffff\5\150\6"+
        "\uffff\2\150\7\uffff\1\150\1\uffff\3\150\2\uffff\1\12\3\uffff\1"+
        "\150\1\uffff\1\150\21\uffff\1\150\10\uffff\1\150\5\uffff\6\150\11"+
        "\uffff\2\150\6\uffff\2\150\10\uffff\3\150\6\uffff\3\150\2\uffff"+
        "\1\150\11\uffff\1\150\5\uffff\1\150\4\uffff\1\150\17\uffff\5\150"+
        "\3\uffff\1\150\5\uffff\5\150\2\uffff\2\150\4\uffff\3\150\1\uffff"+
        "\3\150\3\uffff\2\150\11\uffff\2\150\14\uffff\1\150\1\uffff\1\150"+
        "\1\uffff\1\150\2\uffff\1\150\6\uffff\1\150\10\uffff\4\150\7\uffff"+
        "\11\150\2\uffff\5\150\3\uffff\3\150\2\uffff\2\150\13\uffff\1\150"+
        "\2\uffff\1\150\12\uffff\1\150\7\uffff\4\150\5\uffff\10\150\5\uffff"+
        "\2\150\4\uffff\4\150\2\uffff\3\150\2\uffff\2\150\17\uffff\1\150"+
        "\13\uffff\4\150\4\uffff\6\150\3\uffff\7\150\2\uffff\2\150\2\uffff"+
        "\2\150\15\uffff\1\150\13\uffff\4\150\4\uffff\6\150\2\uffff\7\150"+
        "\2\uffff\2\150\2\uffff\2\150\30\uffff\4\150\4\uffff\6\150\2\uffff"+
        "\6\150\2\uffff\2\150\2\uffff\2\150\30\uffff\4\150\4\uffff\6\150"+
        "\2\uffff\6\150\2\uffff\2\150\2\uffff\2\150\27\uffff\3\150\4\uffff"+
        "\6\150\2\uffff\6\150\2\uffff\2\150\2\uffff\1\150\22\uffff\1\150"+
        "\4\uffff\6\150\2\uffff\5\150\2\uffff\2\150\22\uffff\5\150\2\uffff"+
        "\3\150\1\uffff\2\150\11\uffff\2\150\2\uffff\3\150\4\uffff\2\150"+
        "\1\uffff\1\150";
    static final String DFA1_minS =
        "\1\104\5\4\1\25\3\4\2\uffff\21\4\1\6\1\25\4\4\1\25\5\4\1\10\13\4"+
        "\1\25\5\4\1\6\32\4\1\6\3\4\1\25\1\7\3\4\1\7\3\4\1\uffff\3\7\1\10"+
        "\1\uffff\4\4\1\7\3\4\1\25\20\4\2\7\2\4\5\7\12\4\1\7\1\4\1\7\1\4"+
        "\1\7\1\4\1\25\6\4\1\10\10\4\1\7\1\4\1\7\6\4\1\7\46\4\1\6\4\4\1\10"+
        "\1\7\1\4\1\7\4\4\2\7\1\4\1\7\3\4\1\7\3\4\1\7\1\4\3\7\1\10\1\7\3"+
        "\4\1\25\1\7\1\4\5\7\1\10\6\4\1\7\7\4\1\7\15\4\2\7\2\4\4\7\7\4\1"+
        "\6\1\10\3\4\10\7\3\4\3\7\6\4\1\7\1\4\1\7\1\4\1\7\1\4\1\7\1\4\2\7"+
        "\3\4\4\7\6\4\1\7\6\4\1\7\2\4\1\7\15\4\1\10\5\4\1\6\2\10\3\4\2\7"+
        "\2\4\3\7\6\4\2\7\1\4\2\7\1\4\5\7\1\10\4\7\5\4\1\7\6\4\1\7\1\4\1"+
        "\7\12\4\1\6\1\10\1\4\2\7\2\4\4\7\4\4\1\6\1\10\3\4\2\7\2\4\3\7\5"+
        "\4\7\7\1\4\4\7\4\4\1\7\6\4\2\7\10\4\1\6\2\10\7\4\1\6\1\10\2\4\2"+
        "\7\2\4\3\7\4\4\6\7\1\4\4\7\4\4\1\7\6\4\2\7\10\4\1\6\1\10\7\4\1\6"+
        "\1\10\2\4\2\7\2\4\3\7\4\4\12\7\4\4\1\7\6\4\2\7\10\4\1\6\1\10\6\4"+
        "\1\10\1\6\2\4\2\7\2\4\3\7\4\4\12\7\4\4\1\7\6\4\2\7\10\4\1\6\1\10"+
        "\6\4\1\10\1\6\2\4\2\7\2\4\1\23\1\7\1\23\4\4\12\7\4\4\1\23\4\4\2"+
        "\7\10\4\1\6\1\10\6\4\1\10\1\6\2\4\1\23\1\7\1\4\1\23\4\4\7\7\1\23"+
        "\2\7\4\4\2\7\10\4\1\10\1\6\5\4\1\6\1\10\2\4\1\23\4\4\1\23\1\7\1"+
        "\23\3\7\2\23\1\7\1\4\1\23\1\7\6\4\1\6\1\10\3\4\1\10\5\4\2\23\2\7"+
        "\2\23\2\4\1\6\1\10\4\4\1\23\1\7\1\10\2\4\1\23\1\4";
    static final String DFA1_maxS =
        "\1\107\2\127\1\113\2\17\1\103\1\113\2\127\2\uffff\1\17\1\43\1\17"+
        "\1\43\2\113\1\127\1\113\2\17\1\127\1\135\2\17\1\43\1\20\1\43\1\6"+
        "\1\103\1\17\1\43\1\17\1\43\1\103\1\135\4\23\1\10\1\17\2\23\1\123"+
        "\1\124\1\17\1\43\1\17\1\43\1\20\1\113\1\73\2\113\1\43\1\20\1\43"+
        "\1\6\2\135\1\23\2\111\2\23\2\117\5\23\1\17\2\23\2\117\1\123\1\23"+
        "\1\124\1\23\1\43\1\20\1\43\1\6\2\20\1\113\1\73\1\23\1\111\1\23\1"+
        "\111\1\23\1\117\2\127\1\uffff\3\23\1\10\1\uffff\1\117\1\23\2\20"+
        "\1\23\1\117\1\20\1\135\1\73\1\20\1\113\1\20\3\111\1\23\5\117\1\127"+
        "\1\135\2\17\2\23\2\117\5\23\1\20\1\23\1\117\1\20\3\117\2\20\1\113"+
        "\1\23\1\111\1\23\1\117\1\23\1\117\1\103\1\135\1\17\4\23\1\10\2\23"+
        "\1\123\1\124\1\17\1\43\1\17\1\43\1\23\1\117\1\23\3\20\1\23\2\20"+
        "\1\23\1\117\1\20\1\135\3\111\6\117\2\135\1\17\2\23\2\111\2\23\2"+
        "\117\6\23\2\117\1\123\1\23\1\124\1\23\1\43\1\20\1\43\1\6\3\117\2"+
        "\13\1\23\1\20\1\23\1\20\3\117\2\23\1\117\2\23\2\20\1\23\1\111\1"+
        "\23\1\111\1\23\1\117\3\23\1\10\1\23\1\117\1\20\1\135\1\73\1\23\1"+
        "\117\1\23\1\7\3\23\1\10\6\20\1\23\2\111\5\117\1\23\1\20\1\23\1\117"+
        "\1\20\3\111\1\23\5\117\2\23\2\117\4\23\3\117\1\20\3\117\3\13\2\117"+
        "\10\23\1\20\2\117\3\23\3\20\1\23\2\20\1\23\1\111\1\23\1\117\1\23"+
        "\1\117\1\23\1\117\2\23\1\117\1\20\1\135\2\23\1\7\1\23\1\117\5\20"+
        "\1\23\2\111\4\117\1\23\2\20\1\23\3\111\11\117\2\13\5\117\3\13\3"+
        "\117\2\23\2\117\3\23\6\20\2\23\1\117\2\23\1\117\1\23\1\7\3\23\1"+
        "\10\4\23\1\117\4\20\1\23\2\111\4\117\1\23\1\20\1\23\2\111\10\117"+
        "\3\13\2\23\2\117\4\23\4\117\2\13\3\117\2\23\2\117\3\23\5\20\5\23"+
        "\1\7\1\23\1\117\4\23\4\20\1\23\2\111\4\117\2\23\2\111\6\117\3\13"+
        "\7\117\2\13\2\117\2\23\2\117\3\23\4\20\6\23\1\117\4\23\4\20\1\23"+
        "\2\111\4\117\2\23\2\111\6\117\2\13\7\117\2\13\2\117\2\23\2\117\3"+
        "\23\4\20\12\23\4\20\1\23\2\111\4\117\2\23\2\111\6\117\2\13\6\117"+
        "\2\13\2\117\2\23\2\117\3\23\4\20\12\23\4\20\1\23\2\111\4\117\2\23"+
        "\2\111\6\117\2\13\6\117\2\13\2\117\2\23\2\117\3\23\4\20\12\23\4"+
        "\20\1\23\1\111\3\117\2\23\2\111\6\117\2\13\6\117\2\13\2\117\2\23"+
        "\1\117\1\23\4\20\12\23\3\20\1\117\2\23\2\111\6\117\2\13\5\117\2"+
        "\13\2\117\1\23\4\20\11\23\1\20\2\23\1\111\5\117\2\13\3\117\1\13"+
        "\2\117\3\20\6\23\2\117\2\13\3\117\1\20\2\23\1\13\2\117\1\23\1\117";
    static final String DFA1_acceptS =
        "\12\uffff\1\2\1\3\127\uffff\1\4\4\uffff\1\1\u02db\uffff";
    static final String DFA1_specialS =
        "\u0344\uffff}>";
    static final String[] DFA1_transitionS = {
            "\4\1",
            "\1\2\17\uffff\20\3\16\uffff\1\4\42\uffff\1\4\2\5",
            "\1\2\17\uffff\20\3\16\uffff\1\4\42\uffff\1\4\2\5",
            "\1\7\1\10\1\6\12\uffff\2\11\67\uffff\2\13",
            "\1\14\12\uffff\1\15",
            "\1\16\12\uffff\1\17",
            "\3\20\14\uffff\30\20\10\21",
            "\1\7\1\10\13\uffff\2\11\67\uffff\2\13",
            "\1\22\17\uffff\20\23\16\uffff\1\24\42\uffff\1\24\2\25",
            "\1\26\17\uffff\20\27\16\uffff\1\30\42\uffff\1\30\2\31",
            "",
            "",
            "\1\14\12\uffff\1\15",
            "\1\32\17\uffff\20\33",
            "\1\16\12\uffff\1\17",
            "\1\34\17\uffff\20\35",
            "\1\7\1\10\13\uffff\2\11\67\uffff\2\13",
            "\1\7\1\10\13\uffff\2\11\67\uffff\2\13",
            "\1\22\17\uffff\20\23\16\uffff\1\24\42\uffff\1\24\2\25",
            "\1\7\1\10\1\36\12\uffff\2\11\67\uffff\2\13",
            "\1\37\12\uffff\1\40",
            "\1\41\12\uffff\1\42",
            "\1\26\17\uffff\20\27\16\uffff\1\30\42\uffff\1\30\2\31",
            "\1\44\1\uffff\1\43\1\uffff\1\46\1\47\1\50\1\51\7\uffff\1\52"+
            "\74\uffff\1\52\1\55\1\56\1\53\1\54\7\uffff\2\45",
            "\1\57\12\uffff\1\60",
            "\1\61\12\uffff\1\62",
            "\1\32\17\uffff\20\33",
            "\1\63\13\uffff\1\64",
            "\1\34\17\uffff\20\35",
            "\1\65",
            "\3\67\14\uffff\30\67\10\66",
            "\1\37\12\uffff\1\40",
            "\1\70\17\uffff\20\71",
            "\1\41\12\uffff\1\42",
            "\1\72\17\uffff\20\73",
            "\3\74\14\uffff\30\74\10\75",
            "\1\44\3\uffff\1\46\1\47\1\50\1\51\7\uffff\1\52\74\uffff\1\52"+
            "\1\55\1\56\1\53\1\54\7\uffff\2\45",
            "\1\76\2\uffff\1\77\13\uffff\1\100",
            "\1\102\2\uffff\1\103\1\uffff\1\105\1\101\10\uffff\1\104",
            "\1\102\2\uffff\1\103\1\106\1\uffff\1\107\10\uffff\1\104",
            "\1\102\2\uffff\1\103\1\110\12\uffff\1\104",
            "\1\111",
            "\1\112\12\uffff\1\113",
            "\1\114\2\uffff\1\115\13\uffff\1\116",
            "\1\114\2\uffff\1\115\13\uffff\1\116",
            "\1\117\116\uffff\1\120",
            "\1\121\117\uffff\1\122",
            "\1\57\12\uffff\1\60",
            "\1\123\17\uffff\20\124",
            "\1\61\12\uffff\1\62",
            "\1\125\17\uffff\20\126",
            "\1\63\13\uffff\1\64",
            "\1\7\1\10\13\uffff\2\11\67\uffff\2\13",
            "\3\127\14\uffff\30\127",
            "\1\7\1\10\13\uffff\2\11\67\uffff\2\13",
            "\1\7\1\10\13\uffff\2\11\67\uffff\2\13",
            "\1\70\17\uffff\20\71",
            "\1\130\13\uffff\1\131",
            "\1\72\17\uffff\20\73",
            "\1\132",
            "\1\44\3\uffff\1\46\1\47\1\50\1\51\7\uffff\1\52\74\uffff\1\52"+
            "\1\55\1\56\1\53\1\54\7\uffff\2\45",
            "\1\44\3\uffff\1\46\1\47\1\50\1\51\7\uffff\1\52\74\uffff\1\52"+
            "\1\55\1\56\1\53\1\54\7\uffff\2\45",
            "\1\76\2\uffff\1\77\13\uffff\1\100",
            "\1\134\1\uffff\1\133\101\uffff\2\135",
            "\1\136\103\uffff\2\135",
            "\1\102\2\uffff\1\103\13\uffff\1\104",
            "\1\102\2\uffff\1\103\13\uffff\1\104",
            "\1\140\1\uffff\1\137\1\uffff\1\144\1\145\1\146\1\147\74\uffff"+
            "\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\144\1\145\1\146\1\147\74\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\102\2\uffff\1\103\13\uffff\1\104",
            "\1\102\2\uffff\1\103\13\uffff\1\104",
            "\1\102\2\uffff\1\103\13\uffff\1\104",
            "\1\102\2\uffff\1\103\13\uffff\1\104",
            "\1\102\2\uffff\1\103\13\uffff\1\104",
            "\1\112\12\uffff\1\113",
            "\1\152\2\uffff\1\153\13\uffff\1\154",
            "\1\114\2\uffff\1\115\13\uffff\1\116",
            "\1\156\1\uffff\1\155\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\117\116\uffff\1\120",
            "\1\114\2\uffff\1\115\13\uffff\1\116",
            "\1\121\117\uffff\1\122",
            "\1\114\2\uffff\1\115\13\uffff\1\116",
            "\1\123\17\uffff\20\124",
            "\1\157\13\uffff\1\160",
            "\1\125\17\uffff\20\126",
            "\1\161",
            "\1\162\13\uffff\1\163",
            "\1\130\13\uffff\1\131",
            "\1\7\1\10\13\uffff\2\11\67\uffff\2\13",
            "\3\164\14\uffff\30\164",
            "\1\165\13\uffff\1\166",
            "\1\136\2\uffff\1\167\100\uffff\2\135",
            "\1\170\2\uffff\1\171\13\uffff\1\172",
            "\1\136\103\uffff\2\135",
            "\1\173\13\uffff\1\174",
            "\1\151\2\uffff\1\175\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\176\17\uffff\20\177\16\uffff\1\u0080\42\uffff\1\u0080\2\u0081",
            "\1\176\17\uffff\20\177\16\uffff\1\u0080\42\uffff\1\u0080\2\u0081",
            "",
            "\1\u0084\1\uffff\1\u0082\1\u0083\10\uffff\1\u0085",
            "\1\u0084\1\u0086\1\uffff\1\u0087\10\uffff\1\u0085",
            "\1\u0084\1\u0088\12\uffff\1\u0085",
            "\1\u0089",
            "",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\152\2\uffff\1\153\13\uffff\1\154",
            "\1\u008b\1\u008c\1\u008a\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u008f\13\uffff\1\u0090",
            "\1\151\2\uffff\1\u0091\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\157\13\uffff\1\160",
            "\1\44\3\uffff\1\46\1\47\1\50\1\51\7\uffff\1\52\74\uffff\1\52"+
            "\1\55\1\56\1\53\1\54\7\uffff\2\45",
            "\3\u0092\14\uffff\30\u0092",
            "\1\162\13\uffff\1\163",
            "\1\7\1\10\13\uffff\2\11\67\uffff\2\13",
            "\1\u0093\13\uffff\1\u0094",
            "\1\136\1\uffff\1\u0095\101\uffff\2\135",
            "\1\136\103\uffff\2\135",
            "\1\u0096\103\uffff\2\135",
            "\1\170\2\uffff\1\171\13\uffff\1\172",
            "\1\u0098\1\uffff\1\u0097\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0099\1\uffff\1\144\1\145\1\146\1\147\74\uffff"+
            "\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\144\1\145\1\146\1\147\74\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u009a\3\uffff\1\144\1\145\1\146\1\147\74\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\176\17\uffff\20\177\16\uffff\1\u0080\42\uffff\1\u0080\2\u0081",
            "\1\u009c\1\uffff\1\u009b\1\uffff\1\u009f\1\u00a0\1\u00a1\1\u00a2"+
            "\7\uffff\1\u009d\74\uffff\1\u009d\1\u00a5\1\u00a6\1\u00a3\1"+
            "\u00a4\7\uffff\2\u009e",
            "\1\u00a7\12\uffff\1\u00a8",
            "\1\u00a9\12\uffff\1\u00aa",
            "\1\u0084\13\uffff\1\u0085",
            "\1\u0084\13\uffff\1\u0085",
            "\1\u00ac\1\uffff\1\u00ab\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0084\13\uffff\1\u0085",
            "\1\u0084\13\uffff\1\u0085",
            "\1\u0084\13\uffff\1\u0085",
            "\1\u0084\13\uffff\1\u0085",
            "\1\u00ae\13\uffff\1\u00af",
            "\1\u008e\1\u008c\1\uffff\1\u00b0\10\uffff\1\u008d",
            "\1\u00b1\2\uffff\1\u00b2\13\uffff\1\u00b3",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\151\1\uffff\1\u00b4\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u00b5\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u00b6\13\uffff\1\u00b7",
            "\1\u0093\13\uffff\1\u0094",
            "\1\7\1\10\13\uffff\2\11\67\uffff\2\13",
            "\1\u00b8\13\uffff\1\u00b9",
            "\1\136\2\uffff\1\u00ba\100\uffff\2\135",
            "\1\u00bb\13\uffff\1\u00bc",
            "\1\151\2\uffff\1\u00bd\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u00be\13\uffff\1\u00bf",
            "\1\151\2\uffff\1\u00c0\100\uffff\2\141\2\143\2\uffff\2\142",
            "\3\u00c1\14\uffff\30\u00c1\10\u00c2",
            "\1\u009c\3\uffff\1\u009f\1\u00a0\1\u00a1\1\u00a2\7\uffff\1\u009d"+
            "\74\uffff\1\u009d\1\u00a5\1\u00a6\1\u00a3\1\u00a4\7\uffff\2"+
            "\u009e",
            "\1\u00c3\12\uffff\1\u00c4",
            "\1\u00c5\2\uffff\1\u00c6\13\uffff\1\u00c7",
            "\1\u00c9\2\uffff\1\u00ca\1\uffff\1\u00c8\1\u00cc\10\uffff\1"+
            "\u00cb",
            "\1\u00c9\2\uffff\1\u00ca\1\u00ce\1\uffff\1\u00cd\10\uffff\1"+
            "\u00cb",
            "\1\u00c9\2\uffff\1\u00ca\1\u00cf\12\uffff\1\u00cb",
            "\1\u00d0",
            "\1\u00d1\2\uffff\1\u00d2\13\uffff\1\u00d3",
            "\1\u00d1\2\uffff\1\u00d2\13\uffff\1\u00d3",
            "\1\u00d4\116\uffff\1\u00d5",
            "\1\u00d6\117\uffff\1\u00d7",
            "\1\u00a7\12\uffff\1\u00a8",
            "\1\u00d8\17\uffff\20\u00d9",
            "\1\u00a9\12\uffff\1\u00aa",
            "\1\u00da\17\uffff\20\u00db",
            "\1\u00dc\13\uffff\1\u00dd",
            "\1\151\2\uffff\1\u00de\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u00df\13\uffff\1\u00e0",
            "\1\u008e\1\u008c\1\u00e1\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u00e2\1\u008c\12\uffff\1\u008d",
            "\1\u00b1\2\uffff\1\u00b2\13\uffff\1\u00b3",
            "\1\u00e4\1\u008c\1\u00e3\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u00e5\13\uffff\1\u00e6",
            "\1\151\2\uffff\1\u00e7\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u00b6\13\uffff\1\u00b7",
            "\1\44\3\uffff\1\46\1\47\1\50\1\51\7\uffff\1\52\74\uffff\1\52"+
            "\1\55\1\56\1\53\1\54\7\uffff\2\45",
            "\1\136\1\uffff\1\u00e8\101\uffff\2\135",
            "\1\136\103\uffff\2\135",
            "\1\136\103\uffff\2\135",
            "\1\151\1\uffff\1\u00e9\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u00ea\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u00eb\1\uffff\1\144\1\145\1\146\1\147\74\uffff"+
            "\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\144\1\145\1\146\1\147\74\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\3\uffff\1\144\1\145\1\146\1\147\74\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u009c\3\uffff\1\u009f\1\u00a0\1\u00a1\1\u00a2\7\uffff\1\u009d"+
            "\74\uffff\1\u009d\1\u00a5\1\u00a6\1\u00a3\1\u00a4\7\uffff\2"+
            "\u009e",
            "\1\u009c\3\uffff\1\u009f\1\u00a0\1\u00a1\1\u00a2\7\uffff\1\u009d"+
            "\74\uffff\1\u009d\1\u00a5\1\u00a6\1\u00a3\1\u00a4\7\uffff\2"+
            "\u009e",
            "\1\u00c3\12\uffff\1\u00c4",
            "\1\u00ec\2\uffff\1\u00ed\13\uffff\1\u00ee",
            "\1\u00c5\2\uffff\1\u00c6\13\uffff\1\u00c7",
            "\1\u00f0\1\uffff\1\u00ef\101\uffff\2\u00f1",
            "\1\u00f2\103\uffff\2\u00f1",
            "\1\u00c9\2\uffff\1\u00ca\13\uffff\1\u00cb",
            "\1\u00c9\2\uffff\1\u00ca\13\uffff\1\u00cb",
            "\1\u00f4\1\uffff\1\u00f3\1\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8"+
            "\74\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8\74\uffff\2\141"+
            "\2\143\2\uffff\2\142",
            "\1\u00c9\2\uffff\1\u00ca\13\uffff\1\u00cb",
            "\1\u00c9\2\uffff\1\u00ca\13\uffff\1\u00cb",
            "\1\u00c9\2\uffff\1\u00ca\13\uffff\1\u00cb",
            "\1\u00c9\2\uffff\1\u00ca\13\uffff\1\u00cb",
            "\1\u00c9\2\uffff\1\u00ca\13\uffff\1\u00cb",
            "\1\u00d1\2\uffff\1\u00d2\13\uffff\1\u00d3",
            "\1\u00fa\1\uffff\1\u00f9\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u00d4\116\uffff\1\u00d5",
            "\1\u00d1\2\uffff\1\u00d2\13\uffff\1\u00d3",
            "\1\u00d6\117\uffff\1\u00d7",
            "\1\u00d1\2\uffff\1\u00d2\13\uffff\1\u00d3",
            "\1\u00d8\17\uffff\20\u00d9",
            "\1\u00fb\13\uffff\1\u00fc",
            "\1\u00da\17\uffff\20\u00db",
            "\1\u00fd",
            "\1\151\1\uffff\1\u00fe\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u00ff\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0101\1\uffff\1\u0100\1\uffff\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u0106\13\uffff\1\u0107",
            "\1\u008e\1\u008c\1\uffff\1\u0108\10\uffff\1\u008d",
            "\1\u0109\13\uffff\1\u010a",
            "\1\u008e\1\u008c\1\uffff\1\u010b\10\uffff\1\u008d",
            "\1\151\1\uffff\1\u010c\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u010d\13\uffff\1\u010e",
            "\1\u010f\13\uffff\1\u0110",
            "\1\151\2\uffff\1\u0111\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0112\13\uffff\1\u0113",
            "\1\u00ec\2\uffff\1\u00ed\13\uffff\1\u00ee",
            "\1\u0115\1\u0116\1\u0114\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0119\13\uffff\1\u011a",
            "\1\u00f2\2\uffff\1\u011b\100\uffff\2\u00f1",
            "\1\u011c\2\uffff\1\u011d\13\uffff\1\u011e",
            "\1\u00f2\103\uffff\2\u00f1",
            "\1\u011f\13\uffff\1\u0120",
            "\1\151\2\uffff\1\u0121\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0124\1\uffff\1\u0122\1\u0123\10\uffff\1\u0125",
            "\1\u0124\1\u0126\1\uffff\1\u0127\10\uffff\1\u0125",
            "\1\u0124\1\u0128\12\uffff\1\u0125",
            "\1\u0129",
            "\1\u012a\13\uffff\1\u012b",
            "\1\151\2\uffff\1\u012c\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u00fb\13\uffff\1\u00fc",
            "\1\u009c\3\uffff\1\u009f\1\u00a0\1\u00a1\1\u00a2\7\uffff\1\u009d"+
            "\74\uffff\1\u009d\1\u00a5\1\u00a6\1\u00a3\1\u00a4\7\uffff\2"+
            "\u009e",
            "\3\u012d\14\uffff\30\u012d",
            "\1\u012e\13\uffff\1\u012f",
            "\1\151\2\uffff\1\u0130\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0131\13\uffff\1\u0132",
            "\1\u0133",
            "\1\u0134\1\uffff\1\u0137\1\u0136\10\uffff\1\u0135",
            "\1\u0134\1\u0139\1\uffff\1\u0138\10\uffff\1\u0135",
            "\1\u0134\1\u013a\12\uffff\1\u0135",
            "\1\u013b",
            "\1\u008e\1\u008c\1\u013c\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u008e\1\u008c\1\u013d\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u013e\1\u008c\12\uffff\1\u008d",
            "\1\u013f\13\uffff\1\u0140",
            "\1\136\1\uffff\1\u0141\101\uffff\2\135",
            "\1\136\103\uffff\2\135",
            "\1\151\1\uffff\1\u0142\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0143\1\uffff\1\144\1\145\1\146\1\147\74\uffff"+
            "\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\144\1\145\1\146\1\147\74\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u0144\13\uffff\1\u0145",
            "\1\u0118\1\u0116\1\uffff\1\u0146\10\uffff\1\u0117",
            "\1\u0147\2\uffff\1\u0148\13\uffff\1\u0149",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u00f2\1\uffff\1\u014a\101\uffff\2\u00f1",
            "\1\u00f2\103\uffff\2\u00f1",
            "\1\u014b\103\uffff\2\u00f1",
            "\1\u011c\2\uffff\1\u011d\13\uffff\1\u011e",
            "\1\u014d\1\uffff\1\u014c\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u014e\1\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8"+
            "\74\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8\74\uffff\2\141"+
            "\2\143\2\uffff\2\142",
            "\1\u014f\3\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8\74\uffff\2"+
            "\141\2\143\2\uffff\2\142",
            "\1\u0124\13\uffff\1\u0125",
            "\1\u0124\13\uffff\1\u0125",
            "\1\u0151\1\uffff\1\u0150\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0124\13\uffff\1\u0125",
            "\1\u0124\13\uffff\1\u0125",
            "\1\u0124\13\uffff\1\u0125",
            "\1\u0124\13\uffff\1\u0125",
            "\1\151\1\uffff\1\u0153\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0154\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0155\13\uffff\1\u0156",
            "\1\151\1\uffff\1\u0157\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0158\1\uffff\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u0159\3\uffff\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u015b\1\uffff\1\u015a\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0134\13\uffff\1\u0135",
            "\1\u0134\13\uffff\1\u0135",
            "\1\u0134\13\uffff\1\u0135",
            "\1\u0134\13\uffff\1\u0135",
            "\1\u0134\13\uffff\1\u0135",
            "\1\u0134\13\uffff\1\u0135",
            "\1\u015c\13\uffff\1\u015d",
            "\1\u015e\13\uffff\1\u015f",
            "\1\u008e\1\u008c\1\uffff\1\u0160\10\uffff\1\u008d",
            "\1\151\1\uffff\1\u0161\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0162\13\uffff\1\u0163",
            "\1\u0164\13\uffff\1\u0165",
            "\1\u0166\13\uffff\1\u0167",
            "\1\u0118\1\u0116\1\u0168\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0169\1\u0116\12\uffff\1\u0117",
            "\1\u0147\2\uffff\1\u0148\13\uffff\1\u0149",
            "\1\u016a\1\u0116\1\u016b\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u016c\13\uffff\1\u016d",
            "\1\u00f2\2\uffff\1\u016e\100\uffff\2\u00f1",
            "\1\u016f\13\uffff\1\u0170",
            "\1\151\2\uffff\1\u0171\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0172\13\uffff\1\u0173",
            "\1\151\2\uffff\1\u0174\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0175\13\uffff\1\u0176",
            "\1\151\2\uffff\1\u0177\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0178\13\uffff\1\u0179",
            "\1\u017a\13\uffff\1\u017b",
            "\1\151\2\uffff\1\u017c\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0155\13\uffff\1\u0156",
            "\1\u009c\3\uffff\1\u009f\1\u00a0\1\u00a1\1\u00a2\7\uffff\1\u009d"+
            "\74\uffff\1\u009d\1\u00a5\1\u00a6\1\u00a3\1\u00a4\7\uffff\2"+
            "\u009e",
            "\1\u017d\13\uffff\1\u017e",
            "\1\u017f\13\uffff\1\u0180",
            "\1\u0181",
            "\1\u0182\13\uffff\1\u0183",
            "\1\151\2\uffff\1\u0184\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u008e\1\u008c\1\u0185\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u008e\1\u008c\1\u0186\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u0187\13\uffff\1\u0188",
            "\1\136\1\uffff\1\u0189\101\uffff\2\135",
            "\1\136\103\uffff\2\135",
            "\1\151\1\uffff\1\u018a\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u018b\1\uffff\1\144\1\145\1\146\1\147\74\uffff"+
            "\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\144\1\145\1\146\1\147\74\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u018c\13\uffff\1\u018d",
            "\1\u0118\1\u0116\1\uffff\1\u018e\10\uffff\1\u0117",
            "\1\u0118\1\u0116\1\uffff\1\u018f\10\uffff\1\u0117",
            "\1\u0190\13\uffff\1\u0191",
            "\1\u00f2\1\uffff\1\u0192\101\uffff\2\u00f1",
            "\1\u00f2\103\uffff\2\u00f1",
            "\1\u00f2\103\uffff\2\u00f1",
            "\1\151\1\uffff\1\u0193\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0194\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0195\1\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8"+
            "\74\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8\74\uffff\2\141"+
            "\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8\74\uffff\2\141"+
            "\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0196\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0197\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0199\1\uffff\1\u0198\1\uffff\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\151\1\uffff\1\u019e\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u019f\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u01a0\1\uffff\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\151\1\uffff\1\u01a1\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u01a2\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u01a3\13\uffff\1\u01a4",
            "\1\u01a5\13\uffff\1\u01a6",
            "\1\151\1\uffff\1\u01a7\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u01a8\13\uffff\1\u01a9",
            "\1\u01aa\13\uffff\1\u01ab",
            "\1\u01ac\13\uffff\1\u01ad",
            "\1\u0118\1\u0116\1\u01ae\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u01af\1\u0116\12\uffff\1\u0117",
            "\1\u0118\1\u0116\1\u01b0\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u01b1\13\uffff\1\u01b2",
            "\1\u01b3\13\uffff\1\u01b4",
            "\1\151\2\uffff\1\u01b5\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u01b6\13\uffff\1\u01b7",
            "\1\u01b8\13\uffff\1\u01b9",
            "\1\151\2\uffff\1\u01ba\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u01bb\13\uffff\1\u01bc",
            "\1\u01bd",
            "\1\u01c0\1\uffff\1\u01bf\1\u01be\10\uffff\1\u01c1",
            "\1\u01c0\1\u01c3\1\uffff\1\u01c2\10\uffff\1\u01c1",
            "\1\u01c0\1\u01c4\12\uffff\1\u01c1",
            "\1\u01c5",
            "\1\u01c6\13\uffff\1\u01c7",
            "\1\u01c8\13\uffff\1\u01c9",
            "\1\u01ca\13\uffff\1\u01cb",
            "\1\u01cc\13\uffff\1\u01cd",
            "\1\151\2\uffff\1\u01ce\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u008e\1\u008c\1\u01cf\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u008e\1\u008c\1\u01d0\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u01d1\13\uffff\1\u01d2",
            "\1\136\1\uffff\1\u01d3\101\uffff\2\135",
            "\1\136\103\uffff\2\135",
            "\1\151\1\uffff\1\u01d4\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u01d5\1\uffff\1\144\1\145\1\146\1\147\74\uffff"+
            "\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\144\1\145\1\146\1\147\74\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u01d6\13\uffff\1\u01d7",
            "\1\u0118\1\u0116\1\uffff\1\u01d8\10\uffff\1\u0117",
            "\1\u01d9\13\uffff\1\u01da",
            "\1\u00f2\1\uffff\1\u01db\101\uffff\2\u00f1",
            "\1\u00f2\103\uffff\2\u00f1",
            "\1\151\1\uffff\1\u01dc\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u01dd\1\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8"+
            "\74\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8\74\uffff\2\141"+
            "\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u01de\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u01df\1\uffff\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\u01e0\3\uffff\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\u01c0\13\uffff\1\u01c1",
            "\1\u01c0\13\uffff\1\u01c1",
            "\1\u01e2\1\uffff\1\u01e1\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u01c0\13\uffff\1\u01c1",
            "\1\u01c0\13\uffff\1\u01c1",
            "\1\u01c0\13\uffff\1\u01c1",
            "\1\u01c0\13\uffff\1\u01c1",
            "\1\151\1\uffff\1\u01e3\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u01e4\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u01e5\1\uffff\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\151\1\uffff\1\u01e6\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u01e7\13\uffff\1\u01e8",
            "\1\u01e9\13\uffff\1\u01ea",
            "\1\151\1\uffff\1\u01eb\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u01ed\13\uffff\1\u01ec",
            "\1\u01ee\13\uffff\1\u01ef",
            "\1\u01f0\13\uffff\1\u01f1",
            "\1\u0118\1\u0116\1\u01f2\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0118\1\u0116\1\u01f3\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u01f4\13\uffff\1\u01f5",
            "\1\u01f6\13\uffff\1\u01f7",
            "\1\u01f8\13\uffff\1\u01f9",
            "\1\u01fa\13\uffff\1\u01fb",
            "\1\u01fc\13\uffff\1\u01fd",
            "\1\u01fe",
            "\1\u01ff\13\uffff\1\u0200",
            "\1\151\2\uffff\1\u0201\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0202\13\uffff\1\u0203",
            "\1\u0204\13\uffff\1\u0205",
            "\1\u0206\13\uffff\1\u0207",
            "\1\u0208\13\uffff\1\u0209",
            "\1\u008e\1\u008c\1\u020a\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u008e\1\u008c\1\u020b\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u020c\13\uffff\1\u020d",
            "\1\136\103\uffff\2\135",
            "\1\136\1\uffff\1\u020e\101\uffff\2\135",
            "\1\151\1\uffff\1\u020f\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0210\1\uffff\1\144\1\145\1\146\1\147\74\uffff"+
            "\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\144\1\145\1\146\1\147\74\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u0211\13\uffff\1\u0212",
            "\1\u0213\13\uffff\1\u0214",
            "\1\u00f2\1\uffff\1\u0215\101\uffff\2\u00f1",
            "\1\u00f2\103\uffff\2\u00f1",
            "\1\151\1\uffff\1\u0216\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0217\1\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8"+
            "\74\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8\74\uffff\2\141"+
            "\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0218\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0219\1\uffff\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\151\1\uffff\1\u021a\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u021b\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u021c\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u021d\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u021e\1\uffff\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\151\1\uffff\1\u021f\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0221\13\uffff\1\u0220",
            "\1\u0223\13\uffff\1\u0222",
            "\1\151\1\uffff\1\u0224\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0226\13\uffff\1\u0225",
            "\1\u0227\13\uffff\1\u0228",
            "\1\u022a\13\uffff\1\u0229",
            "\1\u0118\1\u0116\1\u022b\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0118\1\u0116\1\u022c\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u022d\13\uffff\1\u022e",
            "\1\u022f\13\uffff\1\u0230",
            "\1\u0231\13\uffff\1\u0232",
            "\1\u0233\13\uffff\1\u0234",
            "\1\u0235\13\uffff\1\u0236",
            "\1\u0237\13\uffff\1\u0238",
            "\1\151\2\uffff\1\u0239\100\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u023a\13\uffff\1\u023b",
            "\1\u023d\13\uffff\1\u023c",
            "\1\u023e\13\uffff\1\u023f",
            "\1\u0240\13\uffff\1\u0241",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u008e\1\u008c\1\u0242\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u008e\1\u008c\1\u0243\11\uffff\1\u008d",
            "\1\u0245\13\uffff\1\u0244",
            "\1\136\103\uffff\2\135",
            "\1\136\1\uffff\1\u0246\101\uffff\2\135",
            "\1\151\1\uffff\1\u0247\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\144\1\145\1\146\1\147\74\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\1\uffff\1\u0248\1\uffff\1\144\1\145\1\146\1\147\74\uffff"+
            "\2\141\2\143\2\uffff\2\142",
            "\1\u0249\13\uffff\1\u024a",
            "\1\u024b\13\uffff\1\u024c",
            "\1\u00f2\1\uffff\1\u024d\101\uffff\2\u00f1",
            "\1\u00f2\103\uffff\2\u00f1",
            "\1\151\1\uffff\1\u024e\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u024f\1\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8"+
            "\74\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8\74\uffff\2\141"+
            "\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0250\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0251\1\uffff\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\151\1\uffff\1\u0252\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0253\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0254\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u0255\1\uffff\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\151\1\uffff\1\u0256\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0257\13\uffff\1\u0258",
            "\1\u0259\13\uffff\1\u025a",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u025b\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u025c\13\uffff\1\u025d",
            "\1\u025e\13\uffff\1\u025f",
            "\1\u0260\13\uffff\1\u0261",
            "\1\u0118\1\u0116\1\u0262\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0118\1\u0116\1\u0263\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0264\13\uffff\1\u0265",
            "\1\u0266\13\uffff\1\u0267",
            "\1\u0269\13\uffff\1\u0268",
            "\1\u026a\13\uffff\1\u026b",
            "\1\u026c\13\uffff\1\u026d",
            "\1\u026e\13\uffff\1\u026f",
            "\1\u0270\13\uffff\1\u0271",
            "\1\u0272\13\uffff\1\u0273",
            "\1\u0275\13\uffff\1\u0274",
            "\1\u0276\13\uffff\1\u0277",
            "\1\u008e\1\u008c\1\u0278\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u008e\1\u008c\1\u0279\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u027b\13\uffff\1\u027a",
            "\1\136\1\uffff\1\u027c\101\uffff\2\135",
            "\1\136\103\uffff\2\135",
            "\1\151\1\uffff\1\u027d\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u027e\1\uffff\1\144\1\145\1\146\1\147\74\uffff"+
            "\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\144\1\145\1\146\1\147\74\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u027f\13\uffff\1\u0280",
            "\1\u0282\13\uffff\1\u0281",
            "\1\u00f2\1\uffff\1\u0283\101\uffff\2\u00f1",
            "\1\u00f2\103\uffff\2\u00f1",
            "\1\151\1\uffff\1\u0284\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8\74\uffff\2\141"+
            "\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0285\1\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8"+
            "\74\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0286\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0287\1\uffff\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\151\1\uffff\1\u0288\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0289\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u028a\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u028b\1\uffff\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\151\1\uffff\1\u028c\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u028d\13\uffff\1\u028e",
            "\1\u028f\13\uffff\1\u0290",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0291\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0292\13\uffff\1\u0293",
            "\1\u0294\13\uffff\1\u0295",
            "\1\u0296\13\uffff\1\u0297",
            "\1\u0118\1\u0116\1\u0298\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0118\1\u0116\1\u0299\11\uffff\1\u0117",
            "\1\u029b\13\uffff\1\u029a",
            "\1\u029c\13\uffff\1\u029d",
            "\1\u029f\13\uffff\1\u029e",
            "\1\u02a1\13\uffff\1\u02a0",
            "\1\u02a2\13\uffff\1\u02a3",
            "\1\u02a4\13\uffff\1\u02a5",
            "\1\u02a7\13\uffff\1\u02a6",
            "\1\u02a8\13\uffff\1\u02a9",
            "\1\u02ab\13\uffff\1\u02aa",
            "\1\u02ad\13\uffff\1\u02ac",
            "\1\u008e\1\u008c\1\u02ae\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u008e\1\u008c\1\u02af\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u02b0\13\uffff\1\u02b1",
            "\1\136\1\uffff\1\u02b2\101\uffff\2\135",
            "\1\136\103\uffff\2\135",
            "\1\151\1\uffff\1\u02b3\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u02b4\1\uffff\1\144\1\145\1\146\1\147\74\uffff"+
            "\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\144\1\145\1\146\1\147\74\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u02b5\13\uffff\1\u02b6",
            "\1\u02b7\13\uffff\1\u02b8",
            "\1\u00f2\103\uffff\2\u00f1",
            "\1\u00f2\1\uffff\1\u02b9\101\uffff\2\u00f1",
            "\1\151\1\uffff\1\u02ba\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8\74\uffff\2\141"+
            "\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u02bb\1\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8"+
            "\74\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u02bc\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u02bd\1\uffff\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\151\1\uffff\1\u02be\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u02bf\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u02c0\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u02c1\1\uffff\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u02c2\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u02c4\13\uffff\1\u02c3",
            "\1\u02c5\13\uffff\1\u02c6",
            "\1\151\1\uffff\1\u02c7\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u02c8",
            "\1\u02c9\13\uffff\1\u02ca",
            "\1\u02cb",
            "\1\u0118\1\u0116\1\u02cc\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0118\1\u0116\1\u02cd\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u02ce\13\uffff\1\u02cf",
            "\1\u02d0\13\uffff\1\u02d1",
            "\1\u02d2\13\uffff\1\u02d3",
            "\1\u02d5\13\uffff\1\u02d4",
            "\1\u02d6\13\uffff\1\u02d7",
            "\1\u02d8\13\uffff\1\u02d9",
            "\1\u02db\13\uffff\1\u02da",
            "\1\u02dc\13\uffff\1\u02dd",
            "\1\u02df\13\uffff\1\u02de",
            "\1\u02e1\13\uffff\1\u02e0",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u008e\1\u008c\1\u02e2\11\uffff\1\u008d",
            "\1\u008e\1\u008c\1\u02e3\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u02e4",
            "\1\136\103\uffff\2\135",
            "\1\151\1\uffff\1\u02e5\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\144\1\145\1\146\1\147\74\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u02e6\13\uffff\1\u02e7",
            "\1\u02e8\13\uffff\1\u02e9",
            "\1\u00f2\1\uffff\1\u02ea\101\uffff\2\u00f1",
            "\1\u00f2\103\uffff\2\u00f1",
            "\1\151\1\uffff\1\u02eb\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u02ec\1\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8"+
            "\74\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8\74\uffff\2\141"+
            "\2\143\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u02ed\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u02ee\1\uffff\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\151\1\uffff\1\u02ef\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u02f0\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u02f1\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u02f2\1\uffff\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u02f3\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\u02f4",
            "\1\u02f5\13\uffff\1\u02f6",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u02f7",
            "\1\u0118\1\u0116\1\u02f8\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0118\1\u0116\1\u02f9\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u02fa\13\uffff\1\u02fb",
            "\1\u02fc\13\uffff\1\u02fd",
            "\1\u02fe\13\uffff\1\u02ff",
            "\1\u0300\13\uffff\1\u0301",
            "\1\u0303\13\uffff\1\u0302",
            "\1\u0304\13\uffff\1\u0305",
            "\1\u0307\13\uffff\1\u0306",
            "\1\u0308",
            "\1\u0309\13\uffff\1\u030a",
            "\1\u030b\13\uffff\1\u030c",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u008e\1\u008c\1\u030d\11\uffff\1\u008d",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u030e\13\uffff\1\u030f",
            "\1\u0310\13\uffff\1\u0311",
            "\1\u00f2\1\uffff\1\u0312\101\uffff\2\u00f1",
            "\1\u00f2\103\uffff\2\u00f1",
            "\1\151\1\uffff\1\u0313\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0314\1\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8"+
            "\74\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8\74\uffff\2\141"+
            "\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0315\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\u0316\1\uffff\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\151\1\uffff\1\u0317\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0318\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0319\1\uffff\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\151\1\uffff\1\u031a\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u031b",
            "\1\u0118\1\u0116\1\u031c\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0118\1\u0116\1\u031d\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u031e",
            "\1\u031f\13\uffff\1\u0320",
            "\1\u0321",
            "\1\u0322\13\uffff\1\u0323",
            "\1\u0324\13\uffff\1\u0325",
            "\1\u0326\13\uffff\1\u0327",
            "\1\u0328",
            "\1\u0329",
            "\1\u032a\13\uffff\1\u032b",
            "\1\u008e\1\u008c\12\uffff\1\u008d",
            "\1\u032c",
            "\1\u032d\13\uffff\1\u032e",
            "\1\u00f2\103\uffff\2\u00f1",
            "\1\151\1\uffff\1\u032f\101\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\3\uffff\1\u00f5\1\u00f6\1\u00f7\1\u00f8\74\uffff\2\141"+
            "\2\143\2\uffff\2\142",
            "\1\151\1\uffff\1\u0330\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0331\1\uffff\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\151\1\uffff\1\u0332\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0102\1\u0103\1\u0104\1\u0105",
            "\1\151\1\uffff\1\u0333\5\uffff\1\u00ad\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0118\1\u0116\1\u0334\11\uffff\1\u0117",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u0335",
            "\1\u0336",
            "\1\u0337\13\uffff\1\u0338",
            "\1\u0339\13\uffff\1\u033a",
            "\1\u033b",
            "\1\u033c",
            "\1\151\103\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u033d\1\uffff\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\151\1\uffff\1\u033e\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\151\7\uffff\1\u00ad\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0118\1\u0116\12\uffff\1\u0117",
            "\1\u033f",
            "\1\u0340\13\uffff\1\u0341",
            "\1\u019a\1\u019b\1\u019c\1\u019d",
            "\1\151\1\uffff\1\u0342\5\uffff\1\u0152\73\uffff\2\141\2\143"+
            "\2\uffff\2\142",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142",
            "\1\u0343",
            "\1\151\7\uffff\1\u0152\73\uffff\2\141\2\143\2\uffff\2\142"
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
            return "21:1: stmt : ( select spaces selectList spaces where spaces constraintList spaces | select spaces selectList spaces | select spaces selectList spaces order spaces by spaces orderList | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces orderList );";
        }
    }
    static final String DFA3_eotS =
        "\4\uffff";
    static final String DFA3_eofS =
        "\2\2\2\uffff";
    static final String DFA3_minS =
        "\2\4\2\uffff";
    static final String DFA3_maxS =
        "\2\133\2\uffff";
    static final String DFA3_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA3_specialS =
        "\4\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\3\122\uffff\4\2",
            "\1\1\1\3\122\uffff\4\2",
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
            return "()* loopback of 30:4: ( spaces COMMA spaces okw= keyword )*";
        }
    }
    static final String DFA5_eotS =
        "\4\uffff";
    static final String DFA5_eofS =
        "\2\2\2\uffff";
    static final String DFA5_minS =
        "\2\4\2\uffff";
    static final String DFA5_maxS =
        "\2\113\2\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA5_specialS =
        "\4\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\1\1\3\13\uffff\2\2\67\uffff\2\2",
            "\1\1\1\3\13\uffff\2\2\67\uffff\2\2",
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
            return "()* loopback of 42:4: ( spaces COMMA spaces kw= keyword )*";
        }
    }
    static final String DFA7_eotS =
        "\4\uffff";
    static final String DFA7_eofS =
        "\2\2\2\uffff";
    static final String DFA7_minS =
        "\2\4\2\uffff";
    static final String DFA7_maxS =
        "\2\117\2\uffff";
    static final String DFA7_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA7_specialS =
        "\4\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\1\103\uffff\2\3\2\2\2\uffff\2\3",
            "\1\1\103\uffff\2\3\2\2\2\uffff\2\3",
            "",
            ""
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
            return "()* loopback of 55:29: ( spaces rel= logicalOp spaces constraint )*";
        }
    }
    static final String DFA8_eotS =
        "\32\uffff";
    static final String DFA8_eofS =
        "\32\uffff";
    static final String DFA8_minS =
        "\1\24\3\4\1\25\1\4\4\uffff\11\4\1\6\2\4\1\25\3\4";
    static final String DFA8_maxS =
        "\1\127\1\135\2\17\1\103\1\135\4\uffff\1\17\1\43\1\17\1\43\2\135"+
        "\1\43\1\20\1\43\1\6\1\20\1\135\1\73\2\20\1\135";
    static final String DFA8_acceptS =
        "\6\uffff\1\1\1\4\1\2\1\3\20\uffff";
    static final String DFA8_specialS =
        "\32\uffff}>";
    static final String[] DFA8_transitionS = {
            "\20\1\16\uffff\1\2\42\uffff\1\2\2\3",
            "\1\5\1\uffff\1\4\1\uffff\4\6\7\uffff\1\10\74\uffff\1\10\4\11"+
            "\7\uffff\2\7",
            "\1\12\12\uffff\1\13",
            "\1\14\12\uffff\1\15",
            "\3\17\14\uffff\30\17\10\16",
            "\1\5\3\uffff\4\6\7\uffff\1\10\74\uffff\1\10\4\11\7\uffff\2\7",
            "",
            "",
            "",
            "",
            "\1\12\12\uffff\1\13",
            "\1\20\17\uffff\20\21",
            "\1\14\12\uffff\1\15",
            "\1\22\17\uffff\20\23",
            "\1\5\3\uffff\4\6\7\uffff\1\10\74\uffff\1\10\4\11\7\uffff\2\7",
            "\1\5\3\uffff\4\6\7\uffff\1\10\74\uffff\1\10\4\11\7\uffff\2\7",
            "\1\20\17\uffff\20\21",
            "\1\24\13\uffff\1\25",
            "\1\22\17\uffff\20\23",
            "\1\26",
            "\1\24\13\uffff\1\25",
            "\1\5\3\uffff\4\6\7\uffff\1\10\74\uffff\1\10\4\11\7\uffff\2\7",
            "\3\27\14\uffff\30\27",
            "\1\30\13\uffff\1\31",
            "\1\30\13\uffff\1\31",
            "\1\5\3\uffff\4\6\7\uffff\1\10\74\uffff\1\10\4\11\7\uffff\2\7"
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
            return "59:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue | kw= keyword spaces op3= between spaces val3= betValue );";
        }
    }
    static final String DFA10_eotS =
        "\4\uffff";
    static final String DFA10_eofS =
        "\4\uffff";
    static final String DFA10_minS =
        "\2\4\2\uffff";
    static final String DFA10_maxS =
        "\2\20\2\uffff";
    static final String DFA10_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA10_specialS =
        "\4\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\1\1\3\12\uffff\1\2",
            "\1\1\1\3\12\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "()* loopback of 114:21: ( spaces COMMA spaces dotValue )*";
        }
    }
 

    public static final BitSet FOLLOW_select_in_stmt27 = new BitSet(new long[]{0x0004000FFFF00010L,0x0000000000E00000L});
    public static final BitSet FOLLOW_spaces_in_stmt29 = new BitSet(new long[]{0x0004000FFFF00000L,0x0000000000E00000L});
    public static final BitSet FOLLOW_selectList_in_stmt31 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt33 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_where_in_stmt35 = new BitSet(new long[]{0x0004000FFFF00010L,0x0000000000E00000L});
    public static final BitSet FOLLOW_spaces_in_stmt37 = new BitSet(new long[]{0x0004000FFFF00000L,0x0000000000E00000L});
    public static final BitSet FOLLOW_constraintList_in_stmt39 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt41 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt47 = new BitSet(new long[]{0x0004000FFFF00010L,0x0000000000E00000L});
    public static final BitSet FOLLOW_spaces_in_stmt49 = new BitSet(new long[]{0x0004000FFFF00000L,0x0000000000E00000L});
    public static final BitSet FOLLOW_selectList_in_stmt51 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt53 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt58 = new BitSet(new long[]{0x0004000FFFF00010L,0x0000000000E00000L});
    public static final BitSet FOLLOW_spaces_in_stmt60 = new BitSet(new long[]{0x0004000FFFF00000L,0x0000000000E00000L});
    public static final BitSet FOLLOW_selectList_in_stmt62 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000C00L});
    public static final BitSet FOLLOW_spaces_in_stmt64 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000C00L});
    public static final BitSet FOLLOW_order_in_stmt66 = new BitSet(new long[]{0x0000000000000010L,0x0000000000003000L});
    public static final BitSet FOLLOW_spaces_in_stmt68 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_by_in_stmt70 = new BitSet(new long[]{0x0004000FFFF00010L,0x0000000000E00000L});
    public static final BitSet FOLLOW_spaces_in_stmt72 = new BitSet(new long[]{0x0004000FFFF00000L,0x0000000000E00000L});
    public static final BitSet FOLLOW_orderList_in_stmt74 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt79 = new BitSet(new long[]{0x0004000FFFF00010L,0x0000000000E00000L});
    public static final BitSet FOLLOW_spaces_in_stmt81 = new BitSet(new long[]{0x0004000FFFF00000L,0x0000000000E00000L});
    public static final BitSet FOLLOW_selectList_in_stmt83 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt85 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_where_in_stmt87 = new BitSet(new long[]{0x0004000FFFF00010L,0x0000000000E00000L});
    public static final BitSet FOLLOW_spaces_in_stmt89 = new BitSet(new long[]{0x0004000FFFF00000L,0x0000000000E00000L});
    public static final BitSet FOLLOW_constraintList_in_stmt91 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000C00L});
    public static final BitSet FOLLOW_spaces_in_stmt93 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000C00L});
    public static final BitSet FOLLOW_order_in_stmt95 = new BitSet(new long[]{0x0000000000000010L,0x0000000000003000L});
    public static final BitSet FOLLOW_spaces_in_stmt97 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_by_in_stmt99 = new BitSet(new long[]{0x0004000FFFF00010L,0x0000000000E00000L});
    public static final BitSet FOLLOW_spaces_in_stmt101 = new BitSet(new long[]{0x0004000FFFF00000L,0x0000000000E00000L});
    public static final BitSet FOLLOW_orderList_in_stmt103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SPACE_in_spaces113 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_keyword_in_orderList125 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_spaces_in_orderList138 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_orderList142 = new BitSet(new long[]{0x0004000FFFF00010L,0x0000000000E00000L});
    public static final BitSet FOLLOW_spaces_in_orderList146 = new BitSet(new long[]{0x0004000FFFF00000L,0x0000000000E00000L});
    public static final BitSet FOLLOW_keyword_in_orderList153 = new BitSet(new long[]{0x0000000000000012L,0x000000000F000000L});
    public static final BitSet FOLLOW_spaces_in_orderList168 = new BitSet(new long[]{0x0000000000000002L,0x000000000F000000L});
    public static final BitSet FOLLOW_ordering_in_orderList176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_asc_in_ordering192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_desc_in_ordering194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_selectList207 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_selectList220 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_selectList224 = new BitSet(new long[]{0x0004000FFFF00010L,0x0000000000E00000L});
    public static final BitSet FOLLOW_spaces_in_selectList228 = new BitSet(new long[]{0x0004000FFFF00000L,0x0000000000E00000L});
    public static final BitSet FOLLOW_keyword_in_selectList235 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_entity_in_keyword260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword266 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword268 = new BitSet(new long[]{0x0FFFFFF000E00000L});
    public static final BitSet FOLLOW_attr_in_keyword270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword275 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword277 = new BitSet(new long[]{0xF000000000000000L,0x000000000000000FL});
    public static final BitSet FOLLOW_funct_in_keyword279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_count_in_keyword284 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword286 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword288 = new BitSet(new long[]{0x0000000FFFF00010L});
    public static final BitSet FOLLOW_spaces_in_keyword290 = new BitSet(new long[]{0x0000000FFFF00000L});
    public static final BitSet FOLLOW_entity_in_keyword292 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword294 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sum_in_keyword301 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword303 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword305 = new BitSet(new long[]{0x0000000FFFF00010L});
    public static final BitSet FOLLOW_spaces_in_keyword307 = new BitSet(new long[]{0x0000000FFFF00000L});
    public static final BitSet FOLLOW_entity_in_keyword309 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword311 = new BitSet(new long[]{0x0FFFFFF000E00000L});
    public static final BitSet FOLLOW_attr_in_keyword313 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword315 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_constraintList326 = new BitSet(new long[]{0x0000000000000012L,0x000000000000C300L});
    public static final BitSet FOLLOW_spaces_in_constraintList330 = new BitSet(new long[]{0x0000000000000000L,0x000000000000C300L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList337 = new BitSet(new long[]{0x0004000FFFF00010L,0x0000000000E00000L});
    public static final BitSet FOLLOW_spaces_in_constraintList345 = new BitSet(new long[]{0x0004000FFFF00000L,0x0000000000E00000L});
    public static final BitSet FOLLOW_constraint_in_constraintList347 = new BitSet(new long[]{0x0000000000000012L,0x000000000000C300L});
    public static final BitSet FOLLOW_keyword_in_constraint360 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_spaces_in_constraint369 = new BitSet(new long[]{0x0000000000000F00L});
    public static final BitSet FOLLOW_compOpt_in_constraint376 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint386 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_genValue_in_constraint393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint422 = new BitSet(new long[]{0x0000000000080010L,0x0000000000010000L});
    public static final BitSet FOLLOW_spaces_in_constraint431 = new BitSet(new long[]{0x0000000000080000L,0x0000000000010000L});
    public static final BitSet FOLLOW_in_in_constraint438 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_constraint449 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_constraint451 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint455 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_valueList_in_constraint461 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_constraint469 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_constraint473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint499 = new BitSet(new long[]{0x0000000000000010L,0x00000000001E0000L});
    public static final BitSet FOLLOW_spaces_in_constraint508 = new BitSet(new long[]{0x0000000000000000L,0x00000000001E0000L});
    public static final BitSet FOLLOW_like_in_constraint515 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint524 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_constraint531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint546 = new BitSet(new long[]{0x0000000000000010L,0x0000000030000000L});
    public static final BitSet FOLLOW_spaces_in_constraint555 = new BitSet(new long[]{0x0000000000000000L,0x0000000030000000L});
    public static final BitSet FOLLOW_between_in_constraint562 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint570 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_betValue_in_constraint577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_dotValue651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue657 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue659 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue667 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue669 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue677 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue679 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue681 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue683 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue691 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue693 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue695 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue697 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue705 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue707 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue709 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue711 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue713 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue715 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue723 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue725 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue727 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue729 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue731 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue733 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue741 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue743 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue745 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue747 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue749 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue751 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue753 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue755 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue763 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue765 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue767 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue769 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue771 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue773 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue775 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue777 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue785 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue787 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue789 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue791 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue793 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue795 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue797 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue799 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue801 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue803 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue811 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue813 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue815 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue817 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue819 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue821 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue823 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue825 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue827 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue829 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue837 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue839 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue841 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue843 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue845 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue847 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue849 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue851 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue853 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue855 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue857 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue859 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue867 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue869 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue871 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue873 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue875 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue877 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue879 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue881 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue883 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue885 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue887 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue889 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue897 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue899 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue901 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue903 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue905 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue907 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue909 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue911 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue913 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue915 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue917 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue919 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue921 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue923 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue931 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue933 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue935 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue937 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue939 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue941 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue943 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue945 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue947 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue949 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue951 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue953 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue955 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue957 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue965 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue967 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue969 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue971 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue973 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue975 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue977 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue979 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue981 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue983 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue985 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue987 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue989 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue991 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue993 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue995 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1003 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1005 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1007 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1009 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1011 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1013 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1015 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1017 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1019 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1021 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1023 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1025 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1027 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1029 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1031 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1033 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1042 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1044 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1046 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1048 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1050 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1052 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1054 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1056 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1058 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1060 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1062 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1064 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1066 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1068 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1070 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1072 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1074 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1076 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue1078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1084 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1086 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1088 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1090 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1092 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1094 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1096 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1098 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1100 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1102 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1104 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1106 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1108 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1110 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1112 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1114 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1116 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1118 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1127 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1129 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1131 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1133 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1135 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1137 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1139 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1141 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1143 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1145 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1147 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1149 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1151 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1153 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1155 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1157 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1159 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1161 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1163 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1165 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue1167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1173 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1175 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1183 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1185 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1187 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1189 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_valueList1200 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_valueList1204 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_valueList1206 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_valueList1208 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_valueList1210 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_EQ_in_compOpt1222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt1236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_compOpt1243 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt1253 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt1256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt1263 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LT_in_compOpt1266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1273 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt1283 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1293 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt1296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue1304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue1309 = new BitSet(new long[]{0x0000000000000F00L});
    public static final BitSet FOLLOW_compOpt_in_genValue1311 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1313 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_AMP_in_genValue1316 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1318 = new BitSet(new long[]{0x0000000000000F00L});
    public static final BitSet FOLLOW_compOpt_in_genValue1320 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1322 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_dotValue_in_betValue1330 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000300L});
    public static final BitSet FOLLOW_spaces_in_betValue1332 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_and_in_betValue1334 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_betValue1336 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_betValue1338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_in_logicalOp1350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp1352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity1361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr1430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct1543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select1581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and1602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_order1615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_by1628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or1641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in1654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not1667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_like1681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_like1685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_like1689 = new BitSet(new long[]{0x0000000000000010L,0x0000000000080000L});
    public static final BitSet FOLLOW_spaces_in_like1691 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_like1693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_like1697 = new BitSet(new long[]{0x0000000000000010L,0x0000000000100000L});
    public static final BitSet FOLLOW_spaces_in_like1699 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_like1701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_count1710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sum1723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_asc1736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_desc1749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_between1762 = new BitSet(new long[]{0x0000000000000002L});

}