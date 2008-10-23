package dbs.search.parser;
// $ANTLR 3.0.1 Sql.g 2008-10-23 13:43:44


import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SPACE", "COMMA", "DOT", "VALUE", "EQ", "LT", "GT", "NOT", "AMP", "NL", "WS", "'('", "')'", "'WHERE'", "'where'", "'in'", "'ads'", "'dataset'", "'release'", "'tier'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'lumi'", "'dq'", "'ilumi'", "'phygrp'", "'group'", "'pset'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numlss'", "'size'", "'count'", "'status'", "'type'", "'id'", "'parent'", "'child'", "'def'", "'evnum'", "'era'", "'tag'", "'numruns()'", "'numfiles()'", "'dataquality()'", "'latest()'", "'parentrelease()'", "'childrelease()'", "'intluminosity()'", "'findevents()'", "'select'", "'SELECT'", "'find'", "'FIND'", "'and'", "'AND'", "'order'", "'ORDER'", "'by'", "'BY'", "'or'", "'OR'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'", "'COUNT'", "'sum'", "'SUM'", "'asc'", "'ASC'", "'desc'", "'DESC'"
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

                if ( (LA6_1==DOT) ) {
                    int LA6_4 = input.LA(3);

                    if ( ((LA6_4>=21 && LA6_4<=23)||(LA6_4>=36 && LA6_4<=59)) ) {
                        alt6=2;
                    }
                    else if ( ((LA6_4>=60 && LA6_4<=67)) ) {
                        alt6=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("49:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 6, 4, input);

                        throw nvae;
                    }
                }
                else if ( (LA6_1==EOF||(LA6_1>=SPACE && LA6_1<=COMMA)||(LA6_1>=EQ && LA6_1<=NOT)||(LA6_1>=17 && LA6_1<=19)||(LA6_1>=74 && LA6_1<=75)||(LA6_1>=80 && LA6_1<=84)||(LA6_1>=88 && LA6_1<=91)) ) {
                    alt6=1;
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
    // Sql.g:59:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue );
    public final void constraint() throws RecognitionException {
        keyword_return kw = null;

        compOpt_return op = null;

        genValue_return val = null;

        in_return op1 = null;

        valueList_return val1 = null;

        like_return op2 = null;

        dotValue_return val2 = null;


        try {
            // Sql.g:59:12: (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue )
            int alt8=3;
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
    // Sql.g:80:1: where : ( 'WHERE' | 'where' ) ;
    public final void where() throws RecognitionException {
        try {
            // Sql.g:80:7: ( ( 'WHERE' | 'where' ) )
            // Sql.g:80:8: ( 'WHERE' | 'where' )
            {
            if ( (input.LA(1)>=17 && input.LA(1)<=18) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_where560);    throw mse;
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
    // Sql.g:81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );
    public final dotValue_return dotValue() throws RecognitionException {
        dotValue_return retval = new dotValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:81:17: ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE )
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

                                                            if ( (LA9_28==VALUE) ) {
                                                                int LA9_30 = input.LA(14);

                                                                if ( (LA9_30==DOT) ) {
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
                                                                                                new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 42, input);

                                                                                            throw nvae;
                                                                                        }
                                                                                    }
                                                                                    else if ( (LA9_40==19) ) {
                                                                                        alt9=19;
                                                                                    }
                                                                                    else {
                                                                                        NoViableAltException nvae =
                                                                                            new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 40, input);

                                                                                        throw nvae;
                                                                                    }
                                                                                }
                                                                                else if ( (LA9_38==EOF||(LA9_38>=SPACE && LA9_38<=COMMA)||(LA9_38>=EQ && LA9_38<=AMP)||LA9_38==16||(LA9_38>=72 && LA9_38<=75)||(LA9_38>=78 && LA9_38<=79)) ) {
                                                                                    alt9=18;
                                                                                }
                                                                                else {
                                                                                    NoViableAltException nvae =
                                                                                        new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 38, input);

                                                                                    throw nvae;
                                                                                }
                                                                            }
                                                                            else if ( (LA9_36==19) ) {
                                                                                alt9=17;
                                                                            }
                                                                            else {
                                                                                NoViableAltException nvae =
                                                                                    new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 36, input);

                                                                                throw nvae;
                                                                            }
                                                                        }
                                                                        else if ( (LA9_34==EOF||(LA9_34>=SPACE && LA9_34<=COMMA)||(LA9_34>=EQ && LA9_34<=AMP)||LA9_34==16||(LA9_34>=72 && LA9_34<=75)||(LA9_34>=78 && LA9_34<=79)) ) {
                                                                            alt9=16;
                                                                        }
                                                                        else {
                                                                            NoViableAltException nvae =
                                                                                new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 34, input);

                                                                            throw nvae;
                                                                        }
                                                                    }
                                                                    else if ( (LA9_32==19) ) {
                                                                        alt9=15;
                                                                    }
                                                                    else {
                                                                        NoViableAltException nvae =
                                                                            new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 32, input);

                                                                        throw nvae;
                                                                    }
                                                                }
                                                                else if ( (LA9_30==EOF||(LA9_30>=SPACE && LA9_30<=COMMA)||(LA9_30>=EQ && LA9_30<=AMP)||LA9_30==16||(LA9_30>=72 && LA9_30<=75)||(LA9_30>=78 && LA9_30<=79)) ) {
                                                                    alt9=14;
                                                                }
                                                                else {
                                                                    NoViableAltException nvae =
                                                                        new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 30, input);

                                                                    throw nvae;
                                                                }
                                                            }
                                                            else if ( (LA9_28==19) ) {
                                                                alt9=13;
                                                            }
                                                            else {
                                                                NoViableAltException nvae =
                                                                    new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 28, input);

                                                                throw nvae;
                                                            }
                                                        }
                                                        else if ( (LA9_26==EOF||(LA9_26>=SPACE && LA9_26<=COMMA)||(LA9_26>=EQ && LA9_26<=AMP)||LA9_26==16||(LA9_26>=72 && LA9_26<=75)||(LA9_26>=78 && LA9_26<=79)) ) {
                                                            alt9=12;
                                                        }
                                                        else {
                                                            NoViableAltException nvae =
                                                                new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 26, input);

                                                            throw nvae;
                                                        }
                                                    }
                                                    else if ( (LA9_24==19) ) {
                                                        alt9=11;
                                                    }
                                                    else {
                                                        NoViableAltException nvae =
                                                            new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 24, input);

                                                        throw nvae;
                                                    }
                                                }
                                                else if ( (LA9_22==EOF||(LA9_22>=SPACE && LA9_22<=COMMA)||(LA9_22>=EQ && LA9_22<=AMP)||LA9_22==16||(LA9_22>=72 && LA9_22<=75)||(LA9_22>=78 && LA9_22<=79)) ) {
                                                    alt9=10;
                                                }
                                                else {
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 22, input);

                                                    throw nvae;
                                                }
                                            }
                                            else if ( (LA9_20==19) ) {
                                                alt9=9;
                                            }
                                            else {
                                                NoViableAltException nvae =
                                                    new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 20, input);

                                                throw nvae;
                                            }
                                        }
                                        else if ( (LA9_18==EOF||(LA9_18>=SPACE && LA9_18<=COMMA)||(LA9_18>=EQ && LA9_18<=AMP)||LA9_18==16||(LA9_18>=72 && LA9_18<=75)||(LA9_18>=78 && LA9_18<=79)) ) {
                                            alt9=8;
                                        }
                                        else {
                                            NoViableAltException nvae =
                                                new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 18, input);

                                            throw nvae;
                                        }
                                    }
                                    else if ( (LA9_16==19) ) {
                                        alt9=7;
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 16, input);

                                        throw nvae;
                                    }
                                }
                                else if ( (LA9_13==EOF||(LA9_13>=SPACE && LA9_13<=COMMA)||(LA9_13>=EQ && LA9_13<=AMP)||LA9_13==16||(LA9_13>=72 && LA9_13<=75)||(LA9_13>=78 && LA9_13<=79)) ) {
                                    alt9=6;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 13, input);

                                    throw nvae;
                                }
                            }
                            else if ( (LA9_9==19) ) {
                                alt9=5;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 9, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA9_6==EOF||(LA9_6>=SPACE && LA9_6<=COMMA)||(LA9_6>=EQ && LA9_6<=AMP)||LA9_6==16||(LA9_6>=72 && LA9_6<=75)||(LA9_6>=78 && LA9_6<=79)) ) {
                            alt9=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 6, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA9_3==19) ) {
                        alt9=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 3, input);

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
                                    new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 11, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA9_8==EOF||LA9_8==COMMA||(LA9_8>=EQ && LA9_8<=AMP)||LA9_8==16||(LA9_8>=72 && LA9_8<=75)||(LA9_8>=78 && LA9_8<=79)) ) {
                            alt9=22;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 8, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA9_4==EOF||(LA9_4>=SPACE && LA9_4<=COMMA)||LA9_4==16||(LA9_4>=72 && LA9_4<=75)||(LA9_4>=78 && LA9_4<=79)) ) {
                        alt9=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 4, input);

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
                        new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 1, input);

                    throw nvae;
                }

            }
            else if ( (LA9_0==19) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("81:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // Sql.g:81:19: VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue580); 

                    }
                    break;
                case 2 :
                    // Sql.g:82:5: 'in'
                    {
                    match(input,19,FOLLOW_19_in_dotValue587); 

                    }
                    break;
                case 3 :
                    // Sql.g:83:5: VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue593); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue595); 
                    match(input,19,FOLLOW_19_in_dotValue597); 

                    }
                    break;
                case 4 :
                    // Sql.g:84:5: VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue603); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue605); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue607); 

                    }
                    break;
                case 5 :
                    // Sql.g:85:5: VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue613); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue615); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue617); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue619); 
                    match(input,19,FOLLOW_19_in_dotValue621); 

                    }
                    break;
                case 6 :
                    // Sql.g:86:5: VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue627); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue629); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue631); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue633); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue635); 

                    }
                    break;
                case 7 :
                    // Sql.g:87:5: VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue641); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue643); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue645); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue647); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue649); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue651); 
                    match(input,19,FOLLOW_19_in_dotValue653); 

                    }
                    break;
                case 8 :
                    // Sql.g:88:5: VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue659); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue661); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue663); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue665); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue667); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue669); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue671); 

                    }
                    break;
                case 9 :
                    // Sql.g:89:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue677); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue679); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue681); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue683); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue685); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue687); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue689); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue691); 
                    match(input,19,FOLLOW_19_in_dotValue693); 

                    }
                    break;
                case 10 :
                    // Sql.g:90:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue699); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue701); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue703); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue705); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue707); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue709); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue711); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue713); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue715); 

                    }
                    break;
                case 11 :
                    // Sql.g:91:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue721); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue723); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue725); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue727); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue729); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue731); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue733); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue735); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue737); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue739); 
                    match(input,19,FOLLOW_19_in_dotValue741); 

                    }
                    break;
                case 12 :
                    // Sql.g:92:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue747); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue749); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue751); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue753); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue755); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue757); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue759); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue761); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue763); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue765); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue767); 

                    }
                    break;
                case 13 :
                    // Sql.g:93:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
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
                    match(input,DOT,FOLLOW_DOT_in_dotValue795); 
                    match(input,19,FOLLOW_19_in_dotValue797); 

                    }
                    break;
                case 14 :
                    // Sql.g:94:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue803); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue805); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue807); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue809); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue811); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue813); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue815); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue817); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue819); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue821); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue823); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue825); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue827); 

                    }
                    break;
                case 15 :
                    // Sql.g:95:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue833); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue835); 
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
                case 16 :
                    // Sql.g:96:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
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
                    match(input,DOT,FOLLOW_DOT_in_dotValue893); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue895); 

                    }
                    break;
                case 17 :
                    // Sql.g:97:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
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
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue925); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue927); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue929); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue931); 
                    match(input,19,FOLLOW_19_in_dotValue933); 

                    }
                    break;
                case 18 :
                    // Sql.g:98:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
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
                    match(input,DOT,FOLLOW_DOT_in_dotValue961); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue963); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue965); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue967); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue969); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue971); 

                    }
                    break;
                case 19 :
                    // Sql.g:99:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue978); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue980); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue982); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue984); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue986); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue988); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue990); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue992); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue994); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue996); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue998); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1000); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1002); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1004); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1006); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1008); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1010); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1012); 
                    match(input,19,FOLLOW_19_in_dotValue1014); 

                    }
                    break;
                case 20 :
                    // Sql.g:100:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1020); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1022); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1024); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1026); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1028); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1030); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1032); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1034); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1036); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1038); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1040); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1042); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1044); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1046); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1048); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1050); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1052); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1054); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1056); 

                    }
                    break;
                case 21 :
                    // Sql.g:101:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1063); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1065); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1067); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1069); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1071); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1073); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1075); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1077); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1079); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1081); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1083); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1085); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1087); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1089); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1091); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1093); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1095); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1097); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1099); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1101); 
                    match(input,19,FOLLOW_19_in_dotValue1103); 

                    }
                    break;
                case 22 :
                    // Sql.g:102:5: VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1109); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1111); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1113); 

                    }
                    break;
                case 23 :
                    // Sql.g:103:5: VALUE SPACE VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1119); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1121); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1123); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1125); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1127); 

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
    // Sql.g:107:1: valueList : dotValue ( spaces COMMA spaces dotValue )* ;
    public final valueList_return valueList() throws RecognitionException {
        valueList_return retval = new valueList_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:107:11: ( dotValue ( spaces COMMA spaces dotValue )* )
            // Sql.g:107:12: dotValue ( spaces COMMA spaces dotValue )*
            {
            pushFollow(FOLLOW_dotValue_in_valueList1136);
            dotValue();
            _fsp--;

            // Sql.g:107:21: ( spaces COMMA spaces dotValue )*
            loop10:
            do {
                int alt10=2;
                alt10 = dfa10.predict(input);
                switch (alt10) {
            	case 1 :
            	    // Sql.g:107:23: spaces COMMA spaces dotValue
            	    {
            	    pushFollow(FOLLOW_spaces_in_valueList1140);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_valueList1142); 
            	    pushFollow(FOLLOW_spaces_in_valueList1144);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_dotValue_in_valueList1146);
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
    // Sql.g:109:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );
    public final compOpt_return compOpt() throws RecognitionException {
        compOpt_return retval = new compOpt_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:109:10: ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) )
            int alt11=9;
            switch ( input.LA(1) ) {
            case EQ:
                {
                switch ( input.LA(2) ) {
                case LT:
                    {
                    alt11=6;
                    }
                    break;
                case SPACE:
                case VALUE:
                case 19:
                    {
                    alt11=1;
                    }
                    break;
                case GT:
                    {
                    alt11=5;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("109:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );", 11, 1, input);

                    throw nvae;
                }

                }
                break;
            case LT:
                {
                switch ( input.LA(2) ) {
                case SPACE:
                case VALUE:
                case 19:
                    {
                    alt11=2;
                    }
                    break;
                case GT:
                    {
                    alt11=9;
                    }
                    break;
                case EQ:
                    {
                    alt11=7;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("109:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );", 11, 2, input);

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
                        new NoViableAltException("109:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );", 11, 3, input);

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
                    new NoViableAltException("109:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // Sql.g:109:11: ( EQ )
                    {
                    // Sql.g:109:11: ( EQ )
                    // Sql.g:109:12: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1158); 

                    }


                    }
                    break;
                case 2 :
                    // Sql.g:110:4: ( LT )
                    {
                    // Sql.g:110:4: ( LT )
                    // Sql.g:110:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1165); 

                    }


                    }
                    break;
                case 3 :
                    // Sql.g:111:4: ( GT )
                    {
                    // Sql.g:111:4: ( GT )
                    // Sql.g:111:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1172); 

                    }


                    }
                    break;
                case 4 :
                    // Sql.g:112:4: ( NOT ) ( EQ )
                    {
                    // Sql.g:112:4: ( NOT )
                    // Sql.g:112:5: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_compOpt1179); 

                    }

                    // Sql.g:112:9: ( EQ )
                    // Sql.g:112:10: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1182); 

                    }


                    }
                    break;
                case 5 :
                    // Sql.g:113:4: ( EQ ) ( GT )
                    {
                    // Sql.g:113:4: ( EQ )
                    // Sql.g:113:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1189); 

                    }

                    // Sql.g:113:8: ( GT )
                    // Sql.g:113:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1192); 

                    }


                    }
                    break;
                case 6 :
                    // Sql.g:114:4: ( EQ ) ( LT )
                    {
                    // Sql.g:114:4: ( EQ )
                    // Sql.g:114:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1199); 

                    }

                    // Sql.g:114:8: ( LT )
                    // Sql.g:114:9: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1202); 

                    }


                    }
                    break;
                case 7 :
                    // Sql.g:115:4: ( LT ) ( EQ )
                    {
                    // Sql.g:115:4: ( LT )
                    // Sql.g:115:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1209); 

                    }

                    // Sql.g:115:8: ( EQ )
                    // Sql.g:115:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1212); 

                    }


                    }
                    break;
                case 8 :
                    // Sql.g:116:4: ( GT ) ( EQ )
                    {
                    // Sql.g:116:4: ( GT )
                    // Sql.g:116:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1219); 

                    }

                    // Sql.g:116:8: ( EQ )
                    // Sql.g:116:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1222); 

                    }


                    }
                    break;
                case 9 :
                    // Sql.g:117:4: ( LT ) ( GT )
                    {
                    // Sql.g:117:4: ( LT )
                    // Sql.g:117:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1229); 

                    }

                    // Sql.g:117:8: ( GT )
                    // Sql.g:117:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1232); 

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
    // Sql.g:118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );
    public final genValue_return genValue() throws RecognitionException {
        genValue_return retval = new genValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:118:10: ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* )
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

                                            if ( (LA13_18==VALUE) ) {
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

                                                                                    if ( (LA13_33==VALUE) ) {
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
                                                                                                        new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 37, input);

                                                                                                    throw nvae;
                                                                                                }
                                                                                            }
                                                                                            else {
                                                                                                NoViableAltException nvae =
                                                                                                    new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 36, input);

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
                                                                                                new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 34, input);

                                                                                            throw nvae;
                                                                                        }

                                                                                    }
                                                                                    else if ( (LA13_33==19) ) {
                                                                                        int LA13_35 = input.LA(20);

                                                                                        if ( ((LA13_35>=EQ && LA13_35<=NOT)) ) {
                                                                                            alt13=2;
                                                                                        }
                                                                                        else if ( (LA13_35==EOF||LA13_35==SPACE||(LA13_35>=72 && LA13_35<=75)||(LA13_35>=78 && LA13_35<=79)) ) {
                                                                                            alt13=1;
                                                                                        }
                                                                                        else {
                                                                                            NoViableAltException nvae =
                                                                                                new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 35, input);

                                                                                            throw nvae;
                                                                                        }
                                                                                    }
                                                                                    else {
                                                                                        NoViableAltException nvae =
                                                                                            new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 33, input);

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
                                                                                        new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 31, input);

                                                                                    throw nvae;
                                                                                }

                                                                            }
                                                                            else if ( (LA13_30==19) ) {
                                                                                int LA13_32 = input.LA(18);

                                                                                if ( (LA13_32==EOF||LA13_32==SPACE||(LA13_32>=72 && LA13_32<=75)||(LA13_32>=78 && LA13_32<=79)) ) {
                                                                                    alt13=1;
                                                                                }
                                                                                else if ( ((LA13_32>=EQ && LA13_32<=NOT)) ) {
                                                                                    alt13=2;
                                                                                }
                                                                                else {
                                                                                    NoViableAltException nvae =
                                                                                        new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 32, input);

                                                                                    throw nvae;
                                                                                }
                                                                            }
                                                                            else {
                                                                                NoViableAltException nvae =
                                                                                    new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 30, input);

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
                                                                                new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 28, input);

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
                                                                                new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 29, input);

                                                                            throw nvae;
                                                                        }
                                                                    }
                                                                    else {
                                                                        NoViableAltException nvae =
                                                                            new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 27, input);

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
                                                                        new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 25, input);

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
                                                                        new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 26, input);

                                                                    throw nvae;
                                                                }
                                                            }
                                                            else {
                                                                NoViableAltException nvae =
                                                                    new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 24, input);

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
                                                                new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 22, input);

                                                            throw nvae;
                                                        }

                                                    }
                                                    else if ( (LA13_21==19) ) {
                                                        int LA13_23 = input.LA(12);

                                                        if ( ((LA13_23>=EQ && LA13_23<=NOT)) ) {
                                                            alt13=2;
                                                        }
                                                        else if ( (LA13_23==EOF||LA13_23==SPACE||(LA13_23>=72 && LA13_23<=75)||(LA13_23>=78 && LA13_23<=79)) ) {
                                                            alt13=1;
                                                        }
                                                        else {
                                                            NoViableAltException nvae =
                                                                new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 23, input);

                                                            throw nvae;
                                                        }
                                                    }
                                                    else {
                                                        NoViableAltException nvae =
                                                            new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 21, input);

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
                                                        new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 19, input);

                                                    throw nvae;
                                                }

                                            }
                                            else if ( (LA13_18==19) ) {
                                                int LA13_20 = input.LA(10);

                                                if ( (LA13_20==EOF||LA13_20==SPACE||(LA13_20>=72 && LA13_20<=75)||(LA13_20>=78 && LA13_20<=79)) ) {
                                                    alt13=1;
                                                }
                                                else if ( ((LA13_20>=EQ && LA13_20<=NOT)) ) {
                                                    alt13=2;
                                                }
                                                else {
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 20, input);

                                                    throw nvae;
                                                }
                                            }
                                            else {
                                                NoViableAltException nvae =
                                                    new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 18, input);

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
                                                new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 16, input);

                                            throw nvae;
                                        }

                                    }
                                    else if ( (LA13_15==19) ) {
                                        int LA13_17 = input.LA(8);

                                        if ( (LA13_17==EOF||LA13_17==SPACE||(LA13_17>=72 && LA13_17<=75)||(LA13_17>=78 && LA13_17<=79)) ) {
                                            alt13=1;
                                        }
                                        else if ( ((LA13_17>=EQ && LA13_17<=NOT)) ) {
                                            alt13=2;
                                        }
                                        else {
                                            NoViableAltException nvae =
                                                new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 17, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 15, input);

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
                                        new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 12, input);

                                    throw nvae;
                                }

                            }
                            else if ( (LA13_10==19) ) {
                                int LA13_13 = input.LA(6);

                                if ( ((LA13_13>=EQ && LA13_13<=NOT)) ) {
                                    alt13=2;
                                }
                                else if ( (LA13_13==EOF||LA13_13==SPACE||(LA13_13>=72 && LA13_13<=75)||(LA13_13>=78 && LA13_13<=79)) ) {
                                    alt13=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 13, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 10, input);

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
                                new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 7, input);

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
                                new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 8, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 3, input);

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
                                        new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 14, input);

                                    throw nvae;
                                }
                            }
                            else if ( (LA13_11==EOF||LA13_11==SPACE||(LA13_11>=72 && LA13_11<=75)||(LA13_11>=78 && LA13_11<=79)) ) {
                                alt13=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 11, input);

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
                                new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 9, input);

                            throw nvae;
                        }

                    }
                    else if ( (LA13_4==EOF||LA13_4==SPACE||(LA13_4>=72 && LA13_4<=75)||(LA13_4>=78 && LA13_4<=79)) ) {
                        alt13=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 4, input);

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
                        new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 1, input);

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
                        new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("118:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // Sql.g:118:11: dotValue
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue1239);
                    dotValue();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:119:4: dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )*
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue1244);
                    dotValue();
                    _fsp--;

                    pushFollow(FOLLOW_compOpt_in_genValue1246);
                    compOpt();
                    _fsp--;

                    pushFollow(FOLLOW_dotValue_in_genValue1248);
                    dotValue();
                    _fsp--;

                    // Sql.g:119:30: ( AMP dotValue compOpt dotValue )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==AMP) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // Sql.g:119:31: AMP dotValue compOpt dotValue
                    	    {
                    	    match(input,AMP,FOLLOW_AMP_in_genValue1251); 
                    	    pushFollow(FOLLOW_dotValue_in_genValue1253);
                    	    dotValue();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_compOpt_in_genValue1255);
                    	    compOpt();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_dotValue_in_genValue1257);
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

    public static class logicalOp_return extends ParserRuleReturnScope {
    };

    // $ANTLR start logicalOp
    // Sql.g:125:1: logicalOp : ( and | or ) ;
    public final logicalOp_return logicalOp() throws RecognitionException {
        logicalOp_return retval = new logicalOp_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:125:11: ( ( and | or ) )
            // Sql.g:125:12: ( and | or )
            {
            // Sql.g:125:12: ( and | or )
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
                    new NoViableAltException("125:12: ( and | or )", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // Sql.g:125:13: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp1271);
                    and();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:125:17: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp1273);
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
    // Sql.g:126:1: entity : ( 'ads' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' ) ;
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:126:9: ( ( 'ads' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' ) )
            // Sql.g:126:11: ( 'ads' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' )
            {
            if ( (input.LA(1)>=20 && input.LA(1)<=35) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_entity1282);    throw mse;
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
    // Sql.g:127:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) ;
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:127:7: ( ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) )
            // Sql.g:127:8: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' )
            {
            if ( (input.LA(1)>=21 && input.LA(1)<=23)||(input.LA(1)>=36 && input.LA(1)<=59) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_attr1351);    throw mse;
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
    // Sql.g:128:1: funct : ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) ;
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:128:8: ( ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) )
            // Sql.g:128:9: ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' )
            {
            if ( (input.LA(1)>=60 && input.LA(1)<=67) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_funct1464);    throw mse;
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
    // Sql.g:129:1: select : ( 'select' | 'SELECT' | 'find' | 'FIND' ) ;
    public final void select() throws RecognitionException {
        try {
            // Sql.g:129:9: ( ( 'select' | 'SELECT' | 'find' | 'FIND' ) )
            // Sql.g:129:10: ( 'select' | 'SELECT' | 'find' | 'FIND' )
            {
            if ( (input.LA(1)>=68 && input.LA(1)<=71) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_select1502);    throw mse;
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
    // Sql.g:130:1: and : ( 'and' | 'AND' ) ;
    public final void and() throws RecognitionException {
        try {
            // Sql.g:130:6: ( ( 'and' | 'AND' ) )
            // Sql.g:130:7: ( 'and' | 'AND' )
            {
            if ( (input.LA(1)>=72 && input.LA(1)<=73) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_and1523);    throw mse;
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
    // Sql.g:131:1: order : ( 'order' | 'ORDER' ) ;
    public final void order() throws RecognitionException {
        try {
            // Sql.g:131:8: ( ( 'order' | 'ORDER' ) )
            // Sql.g:131:9: ( 'order' | 'ORDER' )
            {
            if ( (input.LA(1)>=74 && input.LA(1)<=75) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_order1536);    throw mse;
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
    // Sql.g:132:1: by : ( 'by' | 'BY' ) ;
    public final void by() throws RecognitionException {
        try {
            // Sql.g:132:5: ( ( 'by' | 'BY' ) )
            // Sql.g:132:6: ( 'by' | 'BY' )
            {
            if ( (input.LA(1)>=76 && input.LA(1)<=77) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_by1549);    throw mse;
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
    // Sql.g:133:1: or : ( 'or' | 'OR' ) ;
    public final void or() throws RecognitionException {
        try {
            // Sql.g:133:5: ( ( 'or' | 'OR' ) )
            // Sql.g:133:6: ( 'or' | 'OR' )
            {
            if ( (input.LA(1)>=78 && input.LA(1)<=79) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_or1562);    throw mse;
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
    // Sql.g:134:1: in : ( 'in' | 'IN' ) ;
    public final in_return in() throws RecognitionException {
        in_return retval = new in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:134:5: ( ( 'in' | 'IN' ) )
            // Sql.g:134:6: ( 'in' | 'IN' )
            {
            if ( input.LA(1)==19||input.LA(1)==80 ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_in1575);    throw mse;
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
    // Sql.g:135:1: not : ( 'not' | 'NOT' ) ;
    public final void not() throws RecognitionException {
        try {
            // Sql.g:135:6: ( ( 'not' | 'NOT' ) )
            // Sql.g:135:7: ( 'not' | 'NOT' )
            {
            if ( (input.LA(1)>=81 && input.LA(1)<=82) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_not1588);    throw mse;
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
    // Sql.g:136:1: like : ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' ) ;
    public final like_return like() throws RecognitionException {
        like_return retval = new like_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:136:7: ( ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' ) )
            // Sql.g:136:8: ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' )
            {
            // Sql.g:136:8: ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' )
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
                    new NoViableAltException("136:8: ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' )", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // Sql.g:136:9: 'like'
                    {
                    match(input,83,FOLLOW_83_in_like1602); 

                    }
                    break;
                case 2 :
                    // Sql.g:136:18: 'LIKE'
                    {
                    match(input,84,FOLLOW_84_in_like1606); 

                    }
                    break;
                case 3 :
                    // Sql.g:136:27: 'not' spaces 'like'
                    {
                    match(input,81,FOLLOW_81_in_like1610); 
                    pushFollow(FOLLOW_spaces_in_like1612);
                    spaces();
                    _fsp--;

                    match(input,83,FOLLOW_83_in_like1614); 

                    }
                    break;
                case 4 :
                    // Sql.g:136:49: 'NOT' spaces 'LIKE'
                    {
                    match(input,82,FOLLOW_82_in_like1618); 
                    pushFollow(FOLLOW_spaces_in_like1620);
                    spaces();
                    _fsp--;

                    match(input,84,FOLLOW_84_in_like1622); 

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
    // Sql.g:138:1: count : ( 'count' | 'COUNT' ) ;
    public final void count() throws RecognitionException {
        try {
            // Sql.g:138:8: ( ( 'count' | 'COUNT' ) )
            // Sql.g:138:9: ( 'count' | 'COUNT' )
            {
            if ( input.LA(1)==50||input.LA(1)==85 ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_count1631);    throw mse;
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
    // Sql.g:139:1: sum : ( 'sum' | 'SUM' ) ;
    public final void sum() throws RecognitionException {
        try {
            // Sql.g:139:6: ( ( 'sum' | 'SUM' ) )
            // Sql.g:139:7: ( 'sum' | 'SUM' )
            {
            if ( (input.LA(1)>=86 && input.LA(1)<=87) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_sum1644);    throw mse;
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
    // Sql.g:140:1: asc : ( 'asc' | 'ASC' ) ;
    public final void asc() throws RecognitionException {
        try {
            // Sql.g:140:6: ( ( 'asc' | 'ASC' ) )
            // Sql.g:140:7: ( 'asc' | 'ASC' )
            {
            if ( (input.LA(1)>=88 && input.LA(1)<=89) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_asc1657);    throw mse;
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
    // Sql.g:141:1: desc : ( 'desc' | 'DESC' ) ;
    public final void desc() throws RecognitionException {
        try {
            // Sql.g:141:7: ( ( 'desc' | 'DESC' ) )
            // Sql.g:141:8: ( 'desc' | 'DESC' )
            {
            if ( (input.LA(1)>=90 && input.LA(1)<=91) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_desc1670);    throw mse;
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


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA3 dfa3 = new DFA3(this);
    protected DFA5 dfa5 = new DFA5(this);
    protected DFA7 dfa7 = new DFA7(this);
    protected DFA8 dfa8 = new DFA8(this);
    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA1_eotS =
        "\u02ae\uffff";
    static final String DFA1_eofS =
        "\3\uffff\1\11\2\uffff\1\11\12\uffff\1\11\6\uffff\2\11\31\uffff\1"+
        "\11\1\uffff\2\11\11\uffff\2\136\5\uffff\2\136\14\uffff\1\11\5\uffff"+
        "\1\136\4\uffff\1\136\1\uffff\1\136\10\uffff\1\11\4\uffff\1\136\1"+
        "\uffff\3\136\4\uffff\5\136\10\uffff\1\11\7\uffff\1\136\20\uffff"+
        "\1\136\1\uffff\1\136\7\uffff\3\136\1\uffff\2\136\10\uffff\2\136"+
        "\13\uffff\6\136\12\uffff\1\136\1\uffff\1\136\14\uffff\1\136\11\uffff"+
        "\10\136\2\uffff\2\136\7\uffff\1\136\2\uffff\5\136\4\uffff\2\136"+
        "\14\uffff\1\136\1\uffff\2\136\16\uffff\1\136\3\uffff\13\136\6\uffff"+
        "\4\136\3\uffff\3\136\7\uffff\1\136\20\uffff\1\136\3\uffff\11\136"+
        "\4\uffff\2\136\10\uffff\4\136\2\uffff\3\136\12\uffff\1\136\14\uffff"+
        "\10\136\3\uffff\3\136\2\uffff\4\136\2\uffff\2\136\11\uffff\1\136"+
        "\13\uffff\10\136\2\uffff\3\136\2\uffff\4\136\2\uffff\2\136\24\uffff"+
        "\10\136\2\uffff\2\136\2\uffff\4\136\2\uffff\2\136\24\uffff\10\136"+
        "\2\uffff\2\136\2\uffff\4\136\2\uffff\2\136\24\uffff\7\136\2\uffff"+
        "\2\136\2\uffff\3\136\2\uffff\2\136\20\uffff\6\136\2\uffff\2\136"+
        "\2\uffff\1\136\2\uffff\2\136\14\uffff\4\136\2\uffff\2\136\3\uffff"+
        "\2\136\7\uffff\1\136\2\uffff\2\136\1\uffff\1\136\4\uffff\2\136\1"+
        "\uffff\1\136";
    static final String DFA1_minS =
        "\1\104\10\4\1\uffff\1\25\1\uffff\21\4\1\6\1\25\4\4\1\25\11\4\1\10"+
        "\6\4\1\25\5\4\1\6\27\4\1\6\3\4\1\25\3\4\1\7\3\4\2\uffff\1\4\1\7"+
        "\1\4\3\7\1\10\2\4\1\25\3\4\1\7\20\4\6\7\11\4\1\7\6\4\1\25\3\4\1"+
        "\10\5\4\1\7\1\4\1\7\1\4\1\7\2\4\1\7\1\4\1\7\33\4\1\6\7\4\1\10\6"+
        "\4\2\7\1\4\1\7\1\4\3\7\1\10\5\4\1\25\2\7\1\4\5\7\1\10\2\7\11\4\2"+
        "\7\2\4\5\7\12\4\1\6\1\10\1\4\1\7\2\4\5\7\5\4\2\7\1\4\1\7\2\4\2\7"+
        "\10\4\4\7\1\4\3\7\14\4\1\10\1\7\1\4\1\7\5\4\1\6\2\10\7\4\3\7\1\4"+
        "\6\7\1\10\6\4\3\7\1\4\3\7\11\4\1\6\1\10\1\4\1\7\2\4\7\7\5\4\1\10"+
        "\1\6\7\4\6\7\1\4\1\7\5\4\6\7\10\4\1\6\2\10\3\4\2\7\4\4\1\10\1\6"+
        "\6\4\5\7\1\4\1\7\4\4\6\7\10\4\1\6\1\10\3\4\2\7\4\4\1\6\1\10\6\4"+
        "\6\7\4\4\6\7\10\4\1\6\1\10\2\4\2\7\4\4\1\6\1\10\6\4\6\7\4\4\6\7"+
        "\10\4\1\6\1\10\2\4\2\7\4\4\1\6\1\10\6\4\1\23\5\7\4\4\1\23\3\7\1"+
        "\23\1\7\7\4\1\6\1\10\2\4\2\7\3\4\1\6\1\10\5\4\5\7\4\4\1\23\2\7\1"+
        "\23\6\4\1\6\1\10\2\4\2\7\1\4\1\6\1\10\3\4\2\23\3\7\4\4\1\23\1\7"+
        "\4\4\1\6\1\10\2\4\1\23\1\7\1\10\2\4\1\23\2\7\3\4\1\23\1\4\1\6\1"+
        "\10\2\4\1\23\1\4\1\23\1\7\1\4\1\10\2\4\1\23\1\4";
    static final String DFA1_maxS =
        "\1\107\2\127\1\113\2\17\1\113\2\127\1\uffff\1\103\1\uffff\1\17\1"+
        "\43\1\17\1\43\1\127\1\113\2\17\1\127\1\124\2\17\2\113\1\43\1\20"+
        "\1\43\1\6\1\103\1\17\1\43\1\17\1\43\1\103\1\124\1\17\2\23\1\123"+
        "\1\124\3\23\1\10\1\17\1\43\1\17\1\43\1\20\1\113\1\73\2\113\1\43"+
        "\1\20\1\43\1\6\2\124\1\17\2\23\2\117\1\123\1\23\1\124\2\23\2\117"+
        "\6\23\1\43\1\20\1\43\1\6\2\20\1\113\1\73\1\23\2\20\1\23\1\117\2"+
        "\127\2\uffff\1\117\1\23\1\117\3\23\1\10\1\20\1\124\1\73\1\20\1\113"+
        "\1\20\1\23\1\20\1\23\1\117\1\20\3\117\1\127\1\124\2\17\5\117\6\23"+
        "\2\20\1\113\3\20\1\23\2\20\1\23\1\117\1\124\2\23\1\123\1\124\1\103"+
        "\3\23\1\10\2\17\1\43\1\17\1\43\1\23\1\117\1\23\1\117\1\23\1\20\1"+
        "\124\1\23\1\20\1\23\1\20\3\117\1\23\2\117\1\123\1\23\1\124\1\23"+
        "\2\124\2\23\2\117\5\23\1\17\1\23\1\43\1\20\1\43\1\6\6\117\2\13\6"+
        "\20\2\23\1\117\1\23\1\117\3\23\1\10\1\23\3\20\1\124\1\73\2\23\1"+
        "\117\1\23\1\7\3\23\1\10\2\23\1\20\10\117\2\23\2\117\5\23\1\20\1"+
        "\23\1\117\2\20\5\117\3\13\1\23\2\117\5\23\5\20\2\23\1\117\1\23\2"+
        "\117\2\23\3\20\1\23\3\20\1\124\3\23\1\7\1\117\3\23\13\117\2\13\1"+
        "\23\1\20\1\23\1\20\4\117\3\13\3\117\4\20\3\23\1\117\2\23\1\7\3\23"+
        "\1\10\6\20\3\23\1\117\3\23\11\117\3\13\1\23\2\117\7\23\1\20\4\117"+
        "\2\13\3\117\4\20\5\23\1\7\1\117\1\23\5\20\6\23\10\117\3\13\3\117"+
        "\2\23\4\117\2\13\2\117\4\20\5\23\1\117\1\23\4\20\6\23\10\117\2\13"+
        "\3\117\2\23\4\117\2\13\2\117\4\20\6\23\4\20\6\23\10\117\2\13\2\117"+
        "\2\23\4\117\2\13\2\117\4\20\6\23\4\20\6\23\10\117\2\13\2\117\2\23"+
        "\4\117\2\13\2\117\4\20\6\23\4\20\6\23\7\117\2\13\2\117\2\23\3\117"+
        "\2\13\2\117\3\20\5\23\4\20\4\23\6\117\2\13\2\117\2\23\1\117\2\13"+
        "\2\117\1\20\5\23\4\20\2\23\4\117\2\13\2\117\2\23\1\13\2\117\3\23"+
        "\3\20\1\23\1\117\2\13\2\117\1\23\1\117\2\23\1\20\1\13\2\117\1\23"+
        "\1\117";
    static final String DFA1_acceptS =
        "\11\uffff\1\2\1\uffff\1\3\122\uffff\1\1\1\4\u024e\uffff";
    static final String DFA1_specialS =
        "\u02ae\uffff}>";
    static final String[] DFA1_transitionS = {
            "\4\1",
            "\1\2\17\uffff\20\3\16\uffff\1\4\42\uffff\1\4\2\5",
            "\1\2\17\uffff\20\3\16\uffff\1\4\42\uffff\1\4\2\5",
            "\1\6\1\7\1\12\12\uffff\2\10\67\uffff\2\13",
            "\1\14\12\uffff\1\15",
            "\1\16\12\uffff\1\17",
            "\1\6\1\7\13\uffff\2\10\67\uffff\2\13",
            "\1\20\17\uffff\20\21\16\uffff\1\22\42\uffff\1\22\2\23",
            "\1\24\17\uffff\20\25\16\uffff\1\26\42\uffff\1\26\2\27",
            "",
            "\3\30\14\uffff\30\30\10\31",
            "",
            "\1\14\12\uffff\1\15",
            "\1\32\17\uffff\20\33",
            "\1\16\12\uffff\1\17",
            "\1\34\17\uffff\20\35",
            "\1\20\17\uffff\20\21\16\uffff\1\22\42\uffff\1\22\2\23",
            "\1\6\1\7\1\36\12\uffff\2\10\67\uffff\2\13",
            "\1\37\12\uffff\1\40",
            "\1\41\12\uffff\1\42",
            "\1\24\17\uffff\20\25\16\uffff\1\26\42\uffff\1\26\2\27",
            "\1\44\1\uffff\1\43\1\uffff\1\52\1\53\1\54\1\55\7\uffff\1\45"+
            "\74\uffff\1\45\1\50\1\51\1\46\1\47",
            "\1\56\12\uffff\1\57",
            "\1\60\12\uffff\1\61",
            "\1\6\1\7\13\uffff\2\10\67\uffff\2\13",
            "\1\6\1\7\13\uffff\2\10\67\uffff\2\13",
            "\1\32\17\uffff\20\33",
            "\1\62\13\uffff\1\63",
            "\1\34\17\uffff\20\35",
            "\1\64",
            "\3\66\14\uffff\30\66\10\65",
            "\1\37\12\uffff\1\40",
            "\1\67\17\uffff\20\70",
            "\1\41\12\uffff\1\42",
            "\1\71\17\uffff\20\72",
            "\3\73\14\uffff\30\73\10\74",
            "\1\44\3\uffff\1\52\1\53\1\54\1\55\7\uffff\1\45\74\uffff\1\45"+
            "\1\50\1\51\1\46\1\47",
            "\1\75\12\uffff\1\76",
            "\1\77\2\uffff\1\100\13\uffff\1\101",
            "\1\77\2\uffff\1\100\13\uffff\1\101",
            "\1\102\116\uffff\1\103",
            "\1\104\117\uffff\1\105",
            "\1\106\2\uffff\1\107\1\uffff\1\111\1\112\10\uffff\1\110",
            "\1\106\2\uffff\1\107\1\113\1\uffff\1\114\10\uffff\1\110",
            "\1\106\2\uffff\1\107\1\115\12\uffff\1\110",
            "\1\116",
            "\1\56\12\uffff\1\57",
            "\1\117\17\uffff\20\120",
            "\1\60\12\uffff\1\61",
            "\1\121\17\uffff\20\122",
            "\1\62\13\uffff\1\63",
            "\1\6\1\7\13\uffff\2\10\67\uffff\2\13",
            "\3\123\14\uffff\30\123",
            "\1\6\1\7\13\uffff\2\10\67\uffff\2\13",
            "\1\6\1\7\13\uffff\2\10\67\uffff\2\13",
            "\1\67\17\uffff\20\70",
            "\1\124\13\uffff\1\125",
            "\1\71\17\uffff\20\72",
            "\1\126",
            "\1\44\3\uffff\1\52\1\53\1\54\1\55\7\uffff\1\45\74\uffff\1\45"+
            "\1\50\1\51\1\46\1\47",
            "\1\44\3\uffff\1\52\1\53\1\54\1\55\7\uffff\1\45\74\uffff\1\45"+
            "\1\50\1\51\1\46\1\47",
            "\1\75\12\uffff\1\76",
            "\1\127\2\uffff\1\130\13\uffff\1\131",
            "\1\77\2\uffff\1\100\13\uffff\1\101",
            "\1\133\1\uffff\1\132\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\102\116\uffff\1\103",
            "\1\77\2\uffff\1\100\13\uffff\1\101",
            "\1\104\117\uffff\1\105",
            "\1\77\2\uffff\1\100\13\uffff\1\101",
            "\1\106\2\uffff\1\107\13\uffff\1\110",
            "\1\142\1\uffff\1\141\1\uffff\1\143\1\144\1\145\1\146\74\uffff"+
            "\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\143\1\144\1\145\1\146\74\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\106\2\uffff\1\107\13\uffff\1\110",
            "\1\106\2\uffff\1\107\13\uffff\1\110",
            "\1\106\2\uffff\1\107\13\uffff\1\110",
            "\1\106\2\uffff\1\107\13\uffff\1\110",
            "\1\106\2\uffff\1\107\13\uffff\1\110",
            "\1\106\2\uffff\1\107\13\uffff\1\110",
            "\1\117\17\uffff\20\120",
            "\1\147\13\uffff\1\150",
            "\1\121\17\uffff\20\122",
            "\1\151",
            "\1\152\13\uffff\1\153",
            "\1\124\13\uffff\1\125",
            "\1\6\1\7\13\uffff\2\10\67\uffff\2\13",
            "\3\154\14\uffff\30\154",
            "\1\127\2\uffff\1\130\13\uffff\1\131",
            "\1\156\1\157\1\155\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\162\13\uffff\1\163",
            "\1\140\2\uffff\1\164\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\165\17\uffff\20\166\16\uffff\1\167\42\uffff\1\167\2\170",
            "\1\165\17\uffff\20\166\16\uffff\1\167\42\uffff\1\167\2\170",
            "",
            "",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\171\13\uffff\1\172",
            "\1\140\2\uffff\1\173\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\174\1\uffff\1\176\1\177\10\uffff\1\175",
            "\1\174\1\u0081\1\uffff\1\u0080\10\uffff\1\175",
            "\1\174\1\u0082\12\uffff\1\175",
            "\1\u0083",
            "\1\147\13\uffff\1\150",
            "\1\44\3\uffff\1\52\1\53\1\54\1\55\7\uffff\1\45\74\uffff\1\45"+
            "\1\50\1\51\1\46\1\47",
            "\3\u0084\14\uffff\30\u0084",
            "\1\152\13\uffff\1\153",
            "\1\6\1\7\13\uffff\2\10\67\uffff\2\13",
            "\1\u0085\13\uffff\1\u0086",
            "\1\u0087\13\uffff\1\u0088",
            "\1\161\1\157\1\uffff\1\u0089\10\uffff\1\160",
            "\1\u008a\2\uffff\1\u008b\13\uffff\1\u008c",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\161\1\157\12\uffff\1\160",
            "\1\140\1\uffff\1\u008d\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u008e\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\165\17\uffff\20\166\16\uffff\1\167\42\uffff\1\167\2\170",
            "\1\u008f\1\uffff\1\u0094\1\uffff\1\u0095\1\u0096\1\u0097\1\u0098"+
            "\7\uffff\1\u0099\74\uffff\1\u0099\1\u0092\1\u0093\1\u0090\1"+
            "\u0091",
            "\1\u009a\12\uffff\1\u009b",
            "\1\u009c\12\uffff\1\u009d",
            "\1\140\1\uffff\1\u009e\1\uffff\1\143\1\144\1\145\1\146\74\uffff"+
            "\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\143\1\144\1\145\1\146\74\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\u009f\3\uffff\1\143\1\144\1\145\1\146\74\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\u00a1\1\uffff\1\u00a0\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\174\13\uffff\1\175",
            "\1\174\13\uffff\1\175",
            "\1\174\13\uffff\1\175",
            "\1\174\13\uffff\1\175",
            "\1\174\13\uffff\1\175",
            "\1\174\13\uffff\1\175",
            "\1\u00a3\13\uffff\1\u00a4",
            "\1\u0085\13\uffff\1\u0086",
            "\1\6\1\7\13\uffff\2\10\67\uffff\2\13",
            "\1\161\1\157\1\u00a5\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\u00a6\1\157\12\uffff\1\160",
            "\1\u008a\2\uffff\1\u008b\13\uffff\1\u008c",
            "\1\u00a8\1\157\1\u00a7\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\u00a9\13\uffff\1\u00aa",
            "\1\140\2\uffff\1\u00ab\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u008f\3\uffff\1\u0095\1\u0096\1\u0097\1\u0098\7\uffff\1\u0099"+
            "\74\uffff\1\u0099\1\u0092\1\u0093\1\u0090\1\u0091",
            "\1\u00ac\2\uffff\1\u00ad\13\uffff\1\u00ae",
            "\1\u00ac\2\uffff\1\u00ad\13\uffff\1\u00ae",
            "\1\u00af\116\uffff\1\u00b0",
            "\1\u00b1\117\uffff\1\u00b2",
            "\3\u00b4\14\uffff\30\u00b4\10\u00b3",
            "\1\u00b6\2\uffff\1\u00b7\1\uffff\1\u00b9\1\u00b5\10\uffff\1"+
            "\u00b8",
            "\1\u00b6\2\uffff\1\u00b7\1\u00bb\1\uffff\1\u00ba\10\uffff\1"+
            "\u00b8",
            "\1\u00b6\2\uffff\1\u00b7\1\u00bc\12\uffff\1\u00b8",
            "\1\u00bd",
            "\1\u00be\12\uffff\1\u00bf",
            "\1\u009a\12\uffff\1\u009b",
            "\1\u00c0\17\uffff\20\u00c1",
            "\1\u009c\12\uffff\1\u009d",
            "\1\u00c2\17\uffff\20\u00c3",
            "\1\u00c4\13\uffff\1\u00c5",
            "\1\140\2\uffff\1\u00c6\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u00c7\13\uffff\1\u00c8",
            "\1\140\2\uffff\1\u00c9\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u00ca\13\uffff\1\u00cb",
            "\1\u00a3\13\uffff\1\u00a4",
            "\1\44\3\uffff\1\52\1\53\1\54\1\55\7\uffff\1\45\74\uffff\1\45"+
            "\1\50\1\51\1\46\1\47",
            "\1\u00cc\13\uffff\1\u00cd",
            "\1\161\1\157\1\uffff\1\u00ce\10\uffff\1\160",
            "\1\u00cf\13\uffff\1\u00d0",
            "\1\161\1\157\1\uffff\1\u00d1\10\uffff\1\160",
            "\1\140\1\uffff\1\u00d2\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u00ac\2\uffff\1\u00ad\13\uffff\1\u00ae",
            "\1\u00d4\1\uffff\1\u00d3\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u00af\116\uffff\1\u00b0",
            "\1\u00ac\2\uffff\1\u00ad\13\uffff\1\u00ae",
            "\1\u00b1\117\uffff\1\u00b2",
            "\1\u00ac\2\uffff\1\u00ad\13\uffff\1\u00ae",
            "\1\u008f\3\uffff\1\u0095\1\u0096\1\u0097\1\u0098\7\uffff\1\u0099"+
            "\74\uffff\1\u0099\1\u0092\1\u0093\1\u0090\1\u0091",
            "\1\u008f\3\uffff\1\u0095\1\u0096\1\u0097\1\u0098\7\uffff\1\u0099"+
            "\74\uffff\1\u0099\1\u0092\1\u0093\1\u0090\1\u0091",
            "\1\u00b6\2\uffff\1\u00b7\13\uffff\1\u00b8",
            "\1\u00b6\2\uffff\1\u00b7\13\uffff\1\u00b8",
            "\1\u00d6\1\uffff\1\u00d5\1\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da"+
            "\74\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da\74\uffff\2\134"+
            "\2\137\2\uffff\2\135",
            "\1\u00b6\2\uffff\1\u00b7\13\uffff\1\u00b8",
            "\1\u00b6\2\uffff\1\u00b7\13\uffff\1\u00b8",
            "\1\u00b6\2\uffff\1\u00b7\13\uffff\1\u00b8",
            "\1\u00b6\2\uffff\1\u00b7\13\uffff\1\u00b8",
            "\1\u00b6\2\uffff\1\u00b7\13\uffff\1\u00b8",
            "\1\u00be\12\uffff\1\u00bf",
            "\1\u00db\2\uffff\1\u00dc\13\uffff\1\u00dd",
            "\1\u00c0\17\uffff\20\u00c1",
            "\1\u00de\13\uffff\1\u00df",
            "\1\u00c2\17\uffff\20\u00c3",
            "\1\u00e0",
            "\1\140\1\uffff\1\u00e1\1\uffff\1\143\1\144\1\145\1\146\74\uffff"+
            "\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\143\1\144\1\145\1\146\74\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\3\uffff\1\143\1\144\1\145\1\146\74\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\1\uffff\1\u00e2\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u00e3\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u00e5\1\uffff\1\u00e4\1\uffff\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\161\1\157\1\u00ea\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\161\1\157\1\u00eb\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\u00ec\1\157\12\uffff\1\160",
            "\1\u00ed\13\uffff\1\u00ee",
            "\1\u00ef\13\uffff\1\u00f0",
            "\1\140\2\uffff\1\u00f1\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u00f2\13\uffff\1\u00f3",
            "\1\140\2\uffff\1\u00f4\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u00f7\1\uffff\1\u00f6\1\u00f5\10\uffff\1\u00f8",
            "\1\u00f7\1\u00fa\1\uffff\1\u00f9\10\uffff\1\u00f8",
            "\1\u00f7\1\u00fb\12\uffff\1\u00f8",
            "\1\u00fc",
            "\1\u00db\2\uffff\1\u00dc\13\uffff\1\u00dd",
            "\1\u00fe\1\u00ff\1\u00fd\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u00de\13\uffff\1\u00df",
            "\1\u008f\3\uffff\1\u0095\1\u0096\1\u0097\1\u0098\7\uffff\1\u0099"+
            "\74\uffff\1\u0099\1\u0092\1\u0093\1\u0090\1\u0091",
            "\3\u0102\14\uffff\30\u0102",
            "\1\u0103\13\uffff\1\u0104",
            "\1\u0105\13\uffff\1\u0106",
            "\1\140\2\uffff\1\u0107\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0108\13\uffff\1\u0109",
            "\1\u010a",
            "\1\u010c\1\uffff\1\u010e\1\u010b\10\uffff\1\u010d",
            "\1\u010c\1\u010f\1\uffff\1\u0110\10\uffff\1\u010d",
            "\1\u010c\1\u0111\12\uffff\1\u010d",
            "\1\u0112",
            "\1\u0113\13\uffff\1\u0114",
            "\1\u0115\13\uffff\1\u0116",
            "\1\161\1\157\1\uffff\1\u0117\10\uffff\1\160",
            "\1\140\1\uffff\1\u0118\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0119\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u011a\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u011b\1\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da"+
            "\74\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da\74\uffff\2\134"+
            "\2\137\2\uffff\2\135",
            "\1\u011c\3\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da\74\uffff\2"+
            "\134\2\137\2\uffff\2\135",
            "\1\u00f7\13\uffff\1\u00f8",
            "\1\u00f7\13\uffff\1\u00f8",
            "\1\u011d\1\uffff\1\u011e\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u00f7\13\uffff\1\u00f8",
            "\1\u00f7\13\uffff\1\u00f8",
            "\1\u00f7\13\uffff\1\u00f8",
            "\1\u00f7\13\uffff\1\u00f8",
            "\1\u0120\13\uffff\1\u0121",
            "\1\u0101\1\u00ff\1\uffff\1\u0122\10\uffff\1\u0100",
            "\1\u0123\2\uffff\1\u0124\13\uffff\1\u0125",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0126\13\uffff\1\u0127",
            "\1\140\1\uffff\1\u0128\1\uffff\1\143\1\144\1\145\1\146\74\uffff"+
            "\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\143\1\144\1\145\1\146\74\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\1\uffff\1\u0129\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u012a\1\uffff\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u012b\3\uffff\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u010c\13\uffff\1\u010d",
            "\1\u012c\1\uffff\1\u012d\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u010c\13\uffff\1\u010d",
            "\1\u010c\13\uffff\1\u010d",
            "\1\u010c\13\uffff\1\u010d",
            "\1\u010c\13\uffff\1\u010d",
            "\1\u010c\13\uffff\1\u010d",
            "\1\161\1\157\1\u012e\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\161\1\157\1\u012f\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\u0130\13\uffff\1\u0131",
            "\1\u0132\13\uffff\1\u0133",
            "\1\140\2\uffff\1\u0134\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0135\13\uffff\1\u0136",
            "\1\140\2\uffff\1\u0137\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\2\uffff\1\u0138\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0139\13\uffff\1\u013a",
            "\1\u013b\13\uffff\1\u013c",
            "\1\u0101\1\u00ff\1\u013d\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u013e\1\u00ff\12\uffff\1\u0100",
            "\1\u0123\2\uffff\1\u0124\13\uffff\1\u0125",
            "\1\u0140\1\u00ff\1\u013f\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0126\13\uffff\1\u0127",
            "\1\u008f\3\uffff\1\u0095\1\u0096\1\u0097\1\u0098\7\uffff\1\u0099"+
            "\74\uffff\1\u0099\1\u0092\1\u0093\1\u0090\1\u0091",
            "\1\u0141\13\uffff\1\u0142",
            "\1\u0143\13\uffff\1\u0144",
            "\1\u0145\13\uffff\1\u0146",
            "\1\u0147",
            "\1\140\2\uffff\1\u0148\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0149\13\uffff\1\u014a",
            "\1\u014b\13\uffff\1\u014c",
            "\1\u014d\13\uffff\1\u014e",
            "\1\140\1\uffff\1\u014f\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0150\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0151\1\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da"+
            "\74\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da\74\uffff\2\134"+
            "\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da\74\uffff\2\134"+
            "\2\137\2\uffff\2\135",
            "\1\u0152\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0153\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0155\1\uffff\1\u0154\1\uffff\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u015a\13\uffff\1\u015b",
            "\1\u0101\1\u00ff\1\uffff\1\u015c\10\uffff\1\u0100",
            "\1\u015d\13\uffff\1\u015e",
            "\1\u0101\1\u00ff\1\uffff\1\u015f\10\uffff\1\u0100",
            "\1\140\1\uffff\1\u0160\1\uffff\1\143\1\144\1\145\1\146\74\uffff"+
            "\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\143\1\144\1\145\1\146\74\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\1\uffff\1\u0161\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0162\1\uffff\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u0163\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0164\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\161\1\157\1\u0165\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\161\1\157\1\u0166\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\u0167\13\uffff\1\u0168",
            "\1\u016a\13\uffff\1\u0169",
            "\1\u016b\13\uffff\1\u016c",
            "\1\140\2\uffff\1\u016d\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u016e\13\uffff\1\u016f",
            "\1\u0170\13\uffff\1\u0171",
            "\1\u0172",
            "\1\u0174\1\uffff\1\u0173\1\u0176\10\uffff\1\u0175",
            "\1\u0174\1\u0178\1\uffff\1\u0177\10\uffff\1\u0175",
            "\1\u0174\1\u0179\12\uffff\1\u0175",
            "\1\u017a",
            "\1\u0101\1\u00ff\1\u017b\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0101\1\u00ff\1\u017c\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u017d\1\u00ff\12\uffff\1\u0100",
            "\1\u017e\13\uffff\1\u017f",
            "\1\u0180\13\uffff\1\u0181",
            "\1\u0183\13\uffff\1\u0182",
            "\1\140\2\uffff\1\u0184\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0185\13\uffff\1\u0186",
            "\1\u0188\13\uffff\1\u0187",
            "\1\u0189\13\uffff\1\u018a",
            "\1\140\1\uffff\1\u018b\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u018c\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u018d\1\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da"+
            "\74\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da\74\uffff\2\134"+
            "\2\137\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u018e\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u018f\1\uffff\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u0190\3\uffff\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u0174\13\uffff\1\u0175",
            "\1\u0191\1\uffff\1\u0192\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0174\13\uffff\1\u0175",
            "\1\u0174\13\uffff\1\u0175",
            "\1\u0174\13\uffff\1\u0175",
            "\1\u0174\13\uffff\1\u0175",
            "\1\u0174\13\uffff\1\u0175",
            "\1\u0193\13\uffff\1\u0194",
            "\1\u0195\13\uffff\1\u0196",
            "\1\u0101\1\u00ff\1\uffff\1\u0197\10\uffff\1\u0100",
            "\1\140\1\uffff\1\u0198\1\uffff\1\143\1\144\1\145\1\146\74\uffff"+
            "\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\143\1\144\1\145\1\146\74\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\1\uffff\1\u0199\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u019a\1\uffff\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u019b\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\161\1\157\12\uffff\1\160",
            "\1\161\1\157\1\u019c\11\uffff\1\160",
            "\1\161\1\157\1\u019d\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\u019e\13\uffff\1\u019f",
            "\1\u01a0\13\uffff\1\u01a1",
            "\1\u01a2\13\uffff\1\u01a3",
            "\1\u01a4\13\uffff\1\u01a5",
            "\1\u01a6\13\uffff\1\u01a7",
            "\1\u01a8",
            "\1\140\2\uffff\1\u01a9\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u01aa\13\uffff\1\u01ab",
            "\1\u0101\1\u00ff\1\u01ac\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0101\1\u00ff\1\u01ad\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u01ae\13\uffff\1\u01af",
            "\1\u01b0\13\uffff\1\u01b1",
            "\1\u01b3\13\uffff\1\u01b2",
            "\1\u01b4\13\uffff\1\u01b5",
            "\1\u01b6\13\uffff\1\u01b7",
            "\1\u01b8\13\uffff\1\u01b9",
            "\1\140\1\uffff\1\u01ba\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u01bb\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u01bc\1\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da"+
            "\74\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da\74\uffff\2\134"+
            "\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u01bd\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u01be\1\uffff\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u01bf\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u01c0\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u01c1\13\uffff\1\u01c2",
            "\1\u01c3\13\uffff\1\u01c4",
            "\1\140\1\uffff\1\u01c5\1\uffff\1\143\1\144\1\145\1\146\74\uffff"+
            "\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\143\1\144\1\145\1\146\74\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\1\uffff\1\u01c6\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u01c7\1\uffff\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\140\1\uffff\1\u01c8\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\161\1\157\1\u01c9\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\161\1\157\1\u01ca\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\u01cb\13\uffff\1\u01cc",
            "\1\u01cd\13\uffff\1\u01ce",
            "\1\u01cf\13\uffff\1\u01d0",
            "\1\u01d1\13\uffff\1\u01d2",
            "\1\u01d3\13\uffff\1\u01d4",
            "\1\140\2\uffff\1\u01d5\100\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u01d6\13\uffff\1\u01d7",
            "\1\u0101\1\u00ff\1\u01d8\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0101\1\u00ff\1\u01d9\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u01db\13\uffff\1\u01da",
            "\1\u01dc\13\uffff\1\u01dd",
            "\1\u01de\13\uffff\1\u01df",
            "\1\u01e0\13\uffff\1\u01e1",
            "\1\u01e2\13\uffff\1\u01e3",
            "\1\u01e5\13\uffff\1\u01e4",
            "\1\140\1\uffff\1\u01e6\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u01e7\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u01e8\1\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da"+
            "\74\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da\74\uffff\2\134"+
            "\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u01e9\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u01ea\1\uffff\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u01eb\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u01ec\13\uffff\1\u01ed",
            "\1\u01ee\13\uffff\1\u01ef",
            "\1\140\3\uffff\1\143\1\144\1\145\1\146\74\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\1\uffff\1\u01f0\1\uffff\1\143\1\144\1\145\1\146\74\uffff"+
            "\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u01f1\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u01f2\1\uffff\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\140\1\uffff\1\u01f3\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\161\1\157\1\u01f4\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\161\1\157\1\u01f5\11\uffff\1\160",
            "\1\u01f6\13\uffff\1\u01f7",
            "\1\u01f8\13\uffff\1\u01f9",
            "\1\u01fa\13\uffff\1\u01fb",
            "\1\u01fc\13\uffff\1\u01fd",
            "\1\u01fe\13\uffff\1\u01ff",
            "\1\u0200\13\uffff\1\u0201",
            "\1\u0101\1\u00ff\1\u0202\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0101\1\u00ff\1\u0203\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0205\13\uffff\1\u0204",
            "\1\u0206\13\uffff\1\u0207",
            "\1\u0208\13\uffff\1\u0209",
            "\1\u020a\13\uffff\1\u020b",
            "\1\u020d\13\uffff\1\u020c",
            "\1\u020e\13\uffff\1\u020f",
            "\1\140\1\uffff\1\u0210\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0211\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0212\1\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da"+
            "\74\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da\74\uffff\2\134"+
            "\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0213\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0214\1\uffff\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\140\1\uffff\1\u0215\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0216\13\uffff\1\u0217",
            "\1\u0218\13\uffff\1\u0219",
            "\1\140\3\uffff\1\143\1\144\1\145\1\146\74\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\1\uffff\1\u021a\1\uffff\1\143\1\144\1\145\1\146\74\uffff"+
            "\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u021b\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u021c\1\uffff\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\140\1\uffff\1\u021d\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\161\1\157\12\uffff\1\160",
            "\1\161\1\157\1\u021e\11\uffff\1\160",
            "\1\161\1\157\1\u021f\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\u0221\13\uffff\1\u0220",
            "\1\u0222\13\uffff\1\u0223",
            "\1\u0224\13\uffff\1\u0225",
            "\1\u0226\13\uffff\1\u0227",
            "\1\u0228\13\uffff\1\u0229",
            "\1\u022a\13\uffff\1\u022b",
            "\1\u0101\1\u00ff\1\u022c\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0101\1\u00ff\1\u022d\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u022f\13\uffff\1\u022e",
            "\1\u0231\13\uffff\1\u0230",
            "\1\u0232\13\uffff\1\u0233",
            "\1\u0234\13\uffff\1\u0235",
            "\1\u0236\13\uffff\1\u0237",
            "\1\u0238\13\uffff\1\u0239",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u023a\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u023b\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u023c\1\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da"+
            "\74\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da\74\uffff\2\134"+
            "\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u023d\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u023e\1\uffff\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\140\1\uffff\1\u023f\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0240\13\uffff\1\u0241",
            "\1\u0242\13\uffff\1\u0243",
            "\1\140\3\uffff\1\143\1\144\1\145\1\146\74\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\1\uffff\1\u0244\1\uffff\1\143\1\144\1\145\1\146\74\uffff"+
            "\2\134\2\137\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0245\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\u0246\1\uffff\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\140\1\uffff\1\u0247\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\161\1\157\1\u0248\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\161\1\157\1\u0249\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\u024a",
            "\1\u024b\13\uffff\1\u024c",
            "\1\u024e\13\uffff\1\u024d",
            "\1\u024f\13\uffff\1\u0250",
            "\1\u0251\13\uffff\1\u0252",
            "\1\u0253\13\uffff\1\u0254",
            "\1\u0101\1\u00ff\1\u0255\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0101\1\u00ff\1\u0256\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0257",
            "\1\u0259\13\uffff\1\u0258",
            "\1\u025a\13\uffff\1\u025b",
            "\1\u025c\13\uffff\1\u025d",
            "\1\u025e",
            "\1\u025f\13\uffff\1\u0260",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0261\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da\74\uffff\2\134"+
            "\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0262\1\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da"+
            "\74\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0263\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0264\1\uffff\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\140\1\uffff\1\u0265\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0266\13\uffff\1\u0267",
            "\1\u0268\13\uffff\1\u0269",
            "\1\140\3\uffff\1\143\1\144\1\145\1\146\74\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u026a\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\u026b\1\uffff\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\140\1\uffff\1\u026c\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\161\1\157\12\uffff\1\160",
            "\1\161\1\157\1\u026d\11\uffff\1\160",
            "\1\161\1\157\12\uffff\1\160",
            "\1\u026e\13\uffff\1\u026f",
            "\1\u0271\13\uffff\1\u0270",
            "\1\u0273\13\uffff\1\u0272",
            "\1\u0274\13\uffff\1\u0275",
            "\1\u0276\13\uffff\1\u0277",
            "\1\u0101\1\u00ff\1\u0278\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0101\1\u00ff\1\u0279\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u027a",
            "\1\u027b\13\uffff\1\u027c",
            "\1\u027e\13\uffff\1\u027d",
            "\1\u027f",
            "\1\140\1\uffff\1\u0280\101\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da\74\uffff\2\134"+
            "\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0281\1\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da"+
            "\74\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0282\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\u0283\1\uffff\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\140\1\uffff\1\u0284\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0285\13\uffff\1\u0286",
            "\1\u0287\13\uffff\1\u0288",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u0289\1\uffff\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u028a\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\161\1\157\12\uffff\1\160",
            "\1\u028b",
            "\1\u028c",
            "\1\u028e\13\uffff\1\u028d",
            "\1\u028f\13\uffff\1\u0290",
            "\1\u0291\13\uffff\1\u0292",
            "\1\u0101\1\u00ff\1\u0293\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0101\1\u00ff\1\u0294\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0295",
            "\1\u0297\13\uffff\1\u0296",
            "\1\140\103\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\3\uffff\1\u00d7\1\u00d8\1\u00d9\1\u00da\74\uffff\2\134"+
            "\2\137\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u0298\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\u0299\1\uffff\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\140\1\uffff\1\u029a\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u029b",
            "\1\u029c\13\uffff\1\u029d",
            "\1\u00e6\1\u00e7\1\u00e8\1\u00e9",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u029e\5\uffff\1\u00a2\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\u029f",
            "\1\u02a0\13\uffff\1\u02a1",
            "\1\u02a3\13\uffff\1\u02a2",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0101\1\u00ff\1\u02a4\11\uffff\1\u0100",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u02a5",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u02a6\1\uffff\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\140\1\uffff\1\u02a7\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\u02a8",
            "\1\140\7\uffff\1\u00a2\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u02a9",
            "\1\u02aa\13\uffff\1\u02ab",
            "\1\u0101\1\u00ff\12\uffff\1\u0100",
            "\1\u0156\1\u0157\1\u0158\1\u0159",
            "\1\140\1\uffff\1\u02ac\5\uffff\1\u011f\73\uffff\2\134\2\137"+
            "\2\uffff\2\135",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135",
            "\1\u02ad",
            "\1\140\7\uffff\1\u011f\73\uffff\2\134\2\137\2\uffff\2\135"
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
        "\31\uffff";
    static final String DFA8_eofS =
        "\31\uffff";
    static final String DFA8_minS =
        "\1\24\4\4\1\uffff\1\25\2\uffff\11\4\1\6\2\4\1\25\3\4";
    static final String DFA8_maxS =
        "\1\127\1\124\2\17\1\124\1\uffff\1\103\2\uffff\1\17\1\43\1\17\1\43"+
        "\2\124\1\43\1\20\1\43\1\6\1\20\1\124\1\73\2\20\1\124";
    static final String DFA8_acceptS =
        "\5\uffff\1\1\1\uffff\1\2\1\3\20\uffff";
    static final String DFA8_specialS =
        "\31\uffff}>";
    static final String[] DFA8_transitionS = {
            "\20\1\16\uffff\1\2\42\uffff\1\2\2\3",
            "\1\4\1\uffff\1\6\1\uffff\4\5\7\uffff\1\7\74\uffff\1\7\4\10",
            "\1\11\12\uffff\1\12",
            "\1\13\12\uffff\1\14",
            "\1\4\3\uffff\4\5\7\uffff\1\7\74\uffff\1\7\4\10",
            "",
            "\3\15\14\uffff\30\15\10\16",
            "",
            "",
            "\1\11\12\uffff\1\12",
            "\1\17\17\uffff\20\20",
            "\1\13\12\uffff\1\14",
            "\1\21\17\uffff\20\22",
            "\1\4\3\uffff\4\5\7\uffff\1\7\74\uffff\1\7\4\10",
            "\1\4\3\uffff\4\5\7\uffff\1\7\74\uffff\1\7\4\10",
            "\1\17\17\uffff\20\20",
            "\1\23\13\uffff\1\24",
            "\1\21\17\uffff\20\22",
            "\1\25",
            "\1\23\13\uffff\1\24",
            "\1\4\3\uffff\4\5\7\uffff\1\7\74\uffff\1\7\4\10",
            "\3\26\14\uffff\30\26",
            "\1\27\13\uffff\1\30",
            "\1\27\13\uffff\1\30",
            "\1\4\3\uffff\4\5\7\uffff\1\7\74\uffff\1\7\4\10"
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
            return "59:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue );";
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
            return "()* loopback of 107:21: ( spaces COMMA spaces dotValue )*";
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
    public static final BitSet FOLLOW_set_in_where560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_dotValue587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue593 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue595 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue603 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue605 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue613 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue615 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue617 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue619 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue627 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue629 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue631 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue633 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue641 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue643 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue645 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue647 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue649 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue651 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue659 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue661 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue663 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue665 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue667 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue669 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue677 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue679 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue681 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue683 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue685 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue687 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue689 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue691 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue699 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue701 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue703 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue705 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue707 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue709 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue711 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue713 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue721 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue723 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue725 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue727 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue729 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue731 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue733 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue735 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue737 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue739 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue747 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue749 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue751 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue753 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue755 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue757 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue759 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue761 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue763 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue765 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue767 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_VALUE_in_dotValue793 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue795 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue803 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue805 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue807 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue809 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue811 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue813 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue815 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue817 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue819 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue821 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue823 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue825 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue833 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue835 = new BitSet(new long[]{0x0000000000000080L});
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
    public static final BitSet FOLLOW_VALUE_in_dotValue891 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue893 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue895 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_DOT_in_dotValue923 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue925 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue927 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue929 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue931 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue933 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_VALUE_in_dotValue959 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue961 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue963 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue965 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue967 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue969 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue978 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue980 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue982 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue984 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue986 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue988 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue990 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue992 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue994 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue996 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue998 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1000 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1002 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1004 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1006 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1008 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1010 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1012 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue1014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1020 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1022 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1024 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1026 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1028 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1030 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1032 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1034 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1036 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1038 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1040 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1042 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1044 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1046 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1048 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1050 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1052 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1054 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1063 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1065 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1067 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1069 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1071 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1073 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1075 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1077 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1079 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1081 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1083 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1085 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1087 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1089 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1091 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1093 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1095 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1097 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1099 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1101 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue1103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1109 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1111 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1119 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1121 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1123 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1125 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_valueList1136 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_valueList1140 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_valueList1142 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_valueList1144 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_valueList1146 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_EQ_in_compOpt1158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt1172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_compOpt1179 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt1189 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt1192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt1199 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LT_in_compOpt1202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1209 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt1219 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1229 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue1239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue1244 = new BitSet(new long[]{0x0000000000000F00L});
    public static final BitSet FOLLOW_compOpt_in_genValue1246 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1248 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_AMP_in_genValue1251 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1253 = new BitSet(new long[]{0x0000000000000F00L});
    public static final BitSet FOLLOW_compOpt_in_genValue1255 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1257 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_and_in_logicalOp1271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp1273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity1282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr1351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct1464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select1502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and1523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_order1536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_by1549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or1562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in1575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not1588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_like1602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_like1606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_like1610 = new BitSet(new long[]{0x0000000000000010L,0x0000000000080000L});
    public static final BitSet FOLLOW_spaces_in_like1612 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_like1614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_like1618 = new BitSet(new long[]{0x0000000000000010L,0x0000000000100000L});
    public static final BitSet FOLLOW_spaces_in_like1620 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_like1622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_count1631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sum1644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_asc1657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_desc1670 = new BitSet(new long[]{0x0000000000000002L});

}