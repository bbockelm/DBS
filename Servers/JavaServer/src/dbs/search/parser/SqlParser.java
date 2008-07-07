package dbs.search.parser;
// $ANTLR 3.0.1 Sql.g 2008-07-07 10:55:14


import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SPACE", "COMMA", "DOT", "EQ", "LT", "GT", "VALUE", "AMP", "STAR", "NL", "WS", "'('", "')'", "'WHERE'", "'where'", "'ads'", "'dataset'", "'release'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'lumi'", "'dq'", "'ilumi'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numlss'", "'size'", "'count'", "'status'", "'type'", "'id'", "'parent'", "'child'", "'tier'", "'def'", "'evnum'", "'era'", "'tag'", "'numruns()'", "'numfiles()'", "'dataquality()'", "'latest()'", "'parentrelease()'", "'childrelease()'", "'intluminosity()'", "'findevents()'", "'select'", "'SELECT'", "'find'", "'FIND'", "'and'", "'AND'", "'order'", "'ORDER'", "'by'", "'BY'", "'or'", "'OR'", "'in'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'", "'COUNT'", "'sum'", "'SUM'"
    };
    public static final int LT=8;
    public static final int WS=14;
    public static final int STAR=12;
    public static final int COMMA=5;
    public static final int AMP=11;
    public static final int GT=9;
    public static final int NL=13;
    public static final int VALUE=10;
    public static final int EQ=7;
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
                {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==DOT) ) {
                    int LA4_4 = input.LA(3);

                    if ( ((LA4_4>=20 && LA4_4<=21)||(LA4_4>=31 && LA4_4<=55)) ) {
                        alt4=2;
                    }
                    else if ( ((LA4_4>=56 && LA4_4<=63)) ) {
                        alt4=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("40:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 4, 4, input);

                        throw nvae;
                    }
                }
                else if ( (LA4_1==EOF||(LA4_1>=SPACE && LA4_1<=COMMA)||(LA4_1>=EQ && LA4_1<=GT)||(LA4_1>=17 && LA4_1<=18)||(LA4_1>=70 && LA4_1<=71)||(LA4_1>=76 && LA4_1<=77)||(LA4_1>=80 && LA4_1<=81)) ) {
                    alt4=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("40:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );", 4, 1, input);

                    throw nvae;
                }
                }
                break;
            case 45:
            case 82:
                {
                alt4=4;
                }
                break;
            case 83:
            case 84:
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
    // Sql.g:50:1: constraint : (kw= keyword spaces op= ( EQ | LT | GT ) spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= likeValue );
    public final void constraint() throws RecognitionException {
        Token op=null;
        keyword_return kw = null;

        genValue_return val = null;

        in_return op1 = null;

        valueList_return val1 = null;

        like_return op2 = null;

        likeValue_return val2 = null;


        try {
            // Sql.g:50:12: (kw= keyword spaces op= ( EQ | LT | GT ) spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= likeValue )
            int alt6=3;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // Sql.g:50:14: kw= keyword spaces op= ( EQ | LT | GT ) spaces val= genValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint299);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint308);
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
                        recoverFromMismatchedSet(input,mse,FOLLOW_set_in_constraint315);    throw mse;
                    }

                    c.setOp(op.getText());
                    pushFollow(FOLLOW_spaces_in_constraint335);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_genValue_in_constraint342);
                    val=genValue();
                    _fsp--;

                    c.setValue(input.toString(val.start,val.stop)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:56:2: kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')'
                    {
                    pushFollow(FOLLOW_keyword_in_constraint371);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint380);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_in_in_constraint387);
                    op1=in();
                    _fsp--;

                    c.setOp(input.toString(op1.start,op1.stop));
                    pushFollow(FOLLOW_spaces_in_constraint398);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_constraint400); 
                    pushFollow(FOLLOW_spaces_in_constraint404);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_valueList_in_constraint410);
                    val1=valueList();
                    _fsp--;

                    c.setValue(input.toString(val1.start,val1.stop)); constraints.add(c);
                    pushFollow(FOLLOW_spaces_in_constraint418);
                    spaces();
                    _fsp--;

                    match(input,16,FOLLOW_16_in_constraint422); 

                    }
                    break;
                case 3 :
                    // Sql.g:65:2: kw= keyword spaces op2= like spaces val2= likeValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint448);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint457);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_like_in_constraint464);
                    op2=like();
                    _fsp--;

                    c.setOp(input.toString(op2.start,op2.stop));
                    pushFollow(FOLLOW_spaces_in_constraint473);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_likeValue_in_constraint480);
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_where509);    throw mse;
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
    // Sql.g:72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );
    public final void dotValue() throws RecognitionException {
        try {
            // Sql.g:72:17: ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE )
            int alt7=8;
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
                                                    alt7=6;
                                                }
                                                else if ( (LA7_18==EOF||(LA7_18>=SPACE && LA7_18<=COMMA)||(LA7_18>=EQ && LA7_18<=STAR)||LA7_18==16||(LA7_18>=68 && LA7_18<=71)||(LA7_18>=74 && LA7_18<=75)) ) {
                                                    alt7=5;
                                                }
                                                else {
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 18, input);

                                                    throw nvae;
                                                }
                                            }
                                            else {
                                                NoViableAltException nvae =
                                                    new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 16, input);

                                                throw nvae;
                                            }
                                        }
                                        else if ( (LA7_15==EOF||(LA7_15>=SPACE && LA7_15<=COMMA)||(LA7_15>=EQ && LA7_15<=STAR)||LA7_15==16||(LA7_15>=68 && LA7_15<=71)||(LA7_15>=74 && LA7_15<=75)) ) {
                                            alt7=4;
                                        }
                                        else {
                                            NoViableAltException nvae =
                                                new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 15, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 13, input);

                                        throw nvae;
                                    }
                                }
                                else if ( (LA7_11==EOF||(LA7_11>=SPACE && LA7_11<=COMMA)||(LA7_11>=EQ && LA7_11<=STAR)||LA7_11==16||(LA7_11>=68 && LA7_11<=71)||(LA7_11>=74 && LA7_11<=75)) ) {
                                    alt7=3;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 11, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 7, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA7_5==EOF||(LA7_5>=SPACE && LA7_5<=COMMA)||(LA7_5>=EQ && LA7_5<=STAR)||LA7_5==16||(LA7_5>=68 && LA7_5<=71)||(LA7_5>=74 && LA7_5<=75)) ) {
                            alt7=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 5, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 2, input);

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
                                alt7=8;
                            }
                            else if ( (LA7_9==EOF||(LA7_9>=SPACE && LA7_9<=COMMA)||LA7_9==16||(LA7_9>=68 && LA7_9<=71)||(LA7_9>=74 && LA7_9<=75)) ) {
                                alt7=7;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 9, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA7_6==EOF||LA7_6==COMMA||(LA7_6>=EQ && LA7_6<=STAR)||LA7_6==16||(LA7_6>=68 && LA7_6<=71)||(LA7_6>=74 && LA7_6<=75)) ) {
                            alt7=7;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 6, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA7_3==EOF||(LA7_3>=SPACE && LA7_3<=COMMA)||LA7_3==16||(LA7_3>=68 && LA7_3<=71)||(LA7_3>=74 && LA7_3<=75)) ) {
                        alt7=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 3, input);

                        throw nvae;
                    }
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
                case 68:
                case 69:
                case 70:
                case 71:
                case 74:
                case 75:
                    {
                    alt7=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // Sql.g:72:19: VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue529); 

                    }
                    break;
                case 2 :
                    // Sql.g:73:5: VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue536); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue538); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue540); 

                    }
                    break;
                case 3 :
                    // Sql.g:74:5: VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue546); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue548); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue550); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue552); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue554); 

                    }
                    break;
                case 4 :
                    // Sql.g:75:5: VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue560); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue562); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue564); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue566); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue568); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue570); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue572); 

                    }
                    break;
                case 5 :
                    // Sql.g:76:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue578); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue580); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue582); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue584); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue586); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue588); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue590); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue592); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue594); 

                    }
                    break;
                case 6 :
                    // Sql.g:77:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue600); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue602); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue604); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue606); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue608); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue610); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue612); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue614); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue616); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue618); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue620); 

                    }
                    break;
                case 7 :
                    // Sql.g:78:5: VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue626); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue628); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue630); 

                    }
                    break;
                case 8 :
                    // Sql.g:79:5: VALUE SPACE VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue636); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue638); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue640); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue642); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue644); 

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
    // Sql.g:82:1: valueList : dotValue ( spaces COMMA spaces dotValue )* ;
    public final valueList_return valueList() throws RecognitionException {
        valueList_return retval = new valueList_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:82:11: ( dotValue ( spaces COMMA spaces dotValue )* )
            // Sql.g:82:12: dotValue ( spaces COMMA spaces dotValue )*
            {
            pushFollow(FOLLOW_dotValue_in_valueList652);
            dotValue();
            _fsp--;

            // Sql.g:82:21: ( spaces COMMA spaces dotValue )*
            loop8:
            do {
                int alt8=2;
                alt8 = dfa8.predict(input);
                switch (alt8) {
            	case 1 :
            	    // Sql.g:82:23: spaces COMMA spaces dotValue
            	    {
            	    pushFollow(FOLLOW_spaces_in_valueList656);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_valueList658); 
            	    pushFollow(FOLLOW_spaces_in_valueList660);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_dotValue_in_valueList662);
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
    // Sql.g:83:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );
    public final void compOpt() throws RecognitionException {
        try {
            // Sql.g:83:10: ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) )
            int alt9=7;
            switch ( input.LA(1) ) {
            case EQ:
                {
                switch ( input.LA(2) ) {
                case LT:
                    {
                    alt9=5;
                    }
                    break;
                case VALUE:
                    {
                    alt9=1;
                    }
                    break;
                case GT:
                    {
                    alt9=4;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("83:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 9, 1, input);

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
                        new NoViableAltException("83:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 9, 2, input);

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
                        new NoViableAltException("83:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 9, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("83:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) );", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // Sql.g:83:11: ( EQ )
                    {
                    // Sql.g:83:11: ( EQ )
                    // Sql.g:83:12: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt673); 

                    }


                    }
                    break;
                case 2 :
                    // Sql.g:84:4: ( LT )
                    {
                    // Sql.g:84:4: ( LT )
                    // Sql.g:84:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt680); 

                    }


                    }
                    break;
                case 3 :
                    // Sql.g:85:4: ( GT )
                    {
                    // Sql.g:85:4: ( GT )
                    // Sql.g:85:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt687); 

                    }


                    }
                    break;
                case 4 :
                    // Sql.g:86:4: ( EQ ) ( GT )
                    {
                    // Sql.g:86:4: ( EQ )
                    // Sql.g:86:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt694); 

                    }

                    // Sql.g:86:8: ( GT )
                    // Sql.g:86:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt697); 

                    }


                    }
                    break;
                case 5 :
                    // Sql.g:87:4: ( EQ ) ( LT )
                    {
                    // Sql.g:87:4: ( EQ )
                    // Sql.g:87:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt704); 

                    }

                    // Sql.g:87:8: ( LT )
                    // Sql.g:87:9: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt707); 

                    }


                    }
                    break;
                case 6 :
                    // Sql.g:88:4: ( LT ) ( EQ )
                    {
                    // Sql.g:88:4: ( LT )
                    // Sql.g:88:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt714); 

                    }

                    // Sql.g:88:8: ( EQ )
                    // Sql.g:88:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt717); 

                    }


                    }
                    break;
                case 7 :
                    // Sql.g:89:4: ( GT ) ( EQ )
                    {
                    // Sql.g:89:4: ( GT )
                    // Sql.g:89:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt724); 

                    }

                    // Sql.g:89:8: ( EQ )
                    // Sql.g:89:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt727); 

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
    // Sql.g:90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );
    public final genValue_return genValue() throws RecognitionException {
        genValue_return retval = new genValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:90:10: ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* )
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
                                                        int LA11_17 = input.LA(12);

                                                        if ( (LA11_17==EOF||LA11_17==SPACE||(LA11_17>=68 && LA11_17<=71)||(LA11_17>=74 && LA11_17<=75)) ) {
                                                            alt11=1;
                                                        }
                                                        else if ( ((LA11_17>=EQ && LA11_17<=GT)) ) {
                                                            alt11=2;
                                                        }
                                                        else {
                                                            NoViableAltException nvae =
                                                                new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 17, input);

                                                            throw nvae;
                                                        }
                                                    }
                                                    else {
                                                        NoViableAltException nvae =
                                                            new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 16, input);

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
                                                case 68:
                                                case 69:
                                                case 70:
                                                case 71:
                                                case 74:
                                                case 75:
                                                    {
                                                    alt11=1;
                                                    }
                                                    break;
                                                default:
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 15, input);

                                                    throw nvae;
                                                }

                                            }
                                            else {
                                                NoViableAltException nvae =
                                                    new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 14, input);

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
                                        case 68:
                                        case 69:
                                        case 70:
                                        case 71:
                                        case 74:
                                        case 75:
                                            {
                                            alt11=1;
                                            }
                                            break;
                                        default:
                                            NoViableAltException nvae =
                                                new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 13, input);

                                            throw nvae;
                                        }

                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 12, input);

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
                                case 68:
                                case 69:
                                case 70:
                                case 71:
                                case 74:
                                case 75:
                                    {
                                    alt11=1;
                                    }
                                    break;
                                default:
                                    NoViableAltException nvae =
                                        new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 10, input);

                                    throw nvae;
                                }

                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 8, input);

                                throw nvae;
                            }
                            }
                            break;
                        case EOF:
                        case SPACE:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 74:
                        case 75:
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
                                new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 6, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 2, input);

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

                                if ( (LA11_11==EOF||LA11_11==SPACE||(LA11_11>=68 && LA11_11<=71)||(LA11_11>=74 && LA11_11<=75)) ) {
                                    alt11=1;
                                }
                                else if ( ((LA11_11>=EQ && LA11_11<=GT)) ) {
                                    alt11=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 11, input);

                                    throw nvae;
                                }
                            }
                            else if ( (LA11_9==EOF||LA11_9==SPACE||(LA11_9>=68 && LA11_9<=71)||(LA11_9>=74 && LA11_9<=75)) ) {
                                alt11=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 9, input);

                                throw nvae;
                            }
                            }
                            break;
                        case EOF:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 74:
                        case 75:
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
                                new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 7, input);

                            throw nvae;
                        }

                    }
                    else if ( (LA11_3==EOF||LA11_3==SPACE||(LA11_3>=68 && LA11_3<=71)||(LA11_3>=74 && LA11_3<=75)) ) {
                        alt11=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case EOF:
                case 68:
                case 69:
                case 70:
                case 71:
                case 74:
                case 75:
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
                        new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // Sql.g:90:11: dotValue
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue734);
                    dotValue();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:91:4: dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )*
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue739);
                    dotValue();
                    _fsp--;

                    pushFollow(FOLLOW_compOpt_in_genValue741);
                    compOpt();
                    _fsp--;

                    pushFollow(FOLLOW_dotValue_in_genValue743);
                    dotValue();
                    _fsp--;

                    // Sql.g:91:30: ( AMP dotValue compOpt dotValue )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==AMP) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // Sql.g:91:31: AMP dotValue compOpt dotValue
                    	    {
                    	    match(input,AMP,FOLLOW_AMP_in_genValue746); 
                    	    pushFollow(FOLLOW_dotValue_in_genValue748);
                    	    dotValue();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_compOpt_in_genValue750);
                    	    compOpt();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_dotValue_in_genValue752);
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
    // Sql.g:92:1: likeValue : ( dotValue | STAR )+ ;
    public final likeValue_return likeValue() throws RecognitionException {
        likeValue_return retval = new likeValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:92:12: ( ( dotValue | STAR )+ )
            // Sql.g:92:13: ( dotValue | STAR )+
            {
            // Sql.g:92:13: ( dotValue | STAR )+
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
            	    // Sql.g:92:14: dotValue
            	    {
            	    pushFollow(FOLLOW_dotValue_in_likeValue762);
            	    dotValue();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // Sql.g:92:24: STAR
            	    {
            	    match(input,STAR,FOLLOW_STAR_in_likeValue765); 

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
    // Sql.g:93:1: logicalOp : ( and | or ) ;
    public final logicalOp_return logicalOp() throws RecognitionException {
        logicalOp_return retval = new logicalOp_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:93:11: ( ( and | or ) )
            // Sql.g:93:12: ( and | or )
            {
            // Sql.g:93:12: ( and | or )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=68 && LA13_0<=69)) ) {
                alt13=1;
            }
            else if ( ((LA13_0>=74 && LA13_0<=75)) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("93:12: ( and | or )", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // Sql.g:93:13: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp774);
                    and();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:93:17: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp776);
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
    // Sql.g:94:1: entity : ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' ) ;
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:94:9: ( ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' ) )
            // Sql.g:94:11: ( 'ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' )
            {
            if ( (input.LA(1)>=19 && input.LA(1)<=30) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_entity785);    throw mse;
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
    // Sql.g:95:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) ;
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:95:7: ( ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' ) )
            // Sql.g:95:8: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' )
            {
            if ( (input.LA(1)>=20 && input.LA(1)<=21)||(input.LA(1)>=31 && input.LA(1)<=55) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_attr838);    throw mse;
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
    // Sql.g:96:1: funct : ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) ;
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:96:8: ( ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) )
            // Sql.g:96:9: ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' )
            {
            if ( (input.LA(1)>=56 && input.LA(1)<=63) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_funct952);    throw mse;
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
    // Sql.g:97:1: select : ( 'select' | 'SELECT' | 'find' | 'FIND' ) ;
    public final void select() throws RecognitionException {
        try {
            // Sql.g:97:9: ( ( 'select' | 'SELECT' | 'find' | 'FIND' ) )
            // Sql.g:97:10: ( 'select' | 'SELECT' | 'find' | 'FIND' )
            {
            if ( (input.LA(1)>=64 && input.LA(1)<=67) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_select990);    throw mse;
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
    // Sql.g:98:1: and : ( 'and' | 'AND' ) ;
    public final void and() throws RecognitionException {
        try {
            // Sql.g:98:6: ( ( 'and' | 'AND' ) )
            // Sql.g:98:7: ( 'and' | 'AND' )
            {
            if ( (input.LA(1)>=68 && input.LA(1)<=69) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_and1011);    throw mse;
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
    // Sql.g:99:1: order : ( 'order' | 'ORDER' ) ;
    public final void order() throws RecognitionException {
        try {
            // Sql.g:99:8: ( ( 'order' | 'ORDER' ) )
            // Sql.g:99:9: ( 'order' | 'ORDER' )
            {
            if ( (input.LA(1)>=70 && input.LA(1)<=71) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_order1024);    throw mse;
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
    // Sql.g:100:1: by : ( 'by' | 'BY' ) ;
    public final void by() throws RecognitionException {
        try {
            // Sql.g:100:5: ( ( 'by' | 'BY' ) )
            // Sql.g:100:6: ( 'by' | 'BY' )
            {
            if ( (input.LA(1)>=72 && input.LA(1)<=73) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_by1037);    throw mse;
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
    // Sql.g:101:1: or : ( 'or' | 'OR' ) ;
    public final void or() throws RecognitionException {
        try {
            // Sql.g:101:5: ( ( 'or' | 'OR' ) )
            // Sql.g:101:6: ( 'or' | 'OR' )
            {
            if ( (input.LA(1)>=74 && input.LA(1)<=75) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_or1050);    throw mse;
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
    // Sql.g:102:1: in : ( 'in' | 'IN' ) ;
    public final in_return in() throws RecognitionException {
        in_return retval = new in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:102:5: ( ( 'in' | 'IN' ) )
            // Sql.g:102:6: ( 'in' | 'IN' )
            {
            if ( (input.LA(1)>=76 && input.LA(1)<=77) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_in1063);    throw mse;
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
    // Sql.g:103:1: not : ( 'not' | 'NOT' ) ;
    public final void not() throws RecognitionException {
        try {
            // Sql.g:103:6: ( ( 'not' | 'NOT' ) )
            // Sql.g:103:7: ( 'not' | 'NOT' )
            {
            if ( (input.LA(1)>=78 && input.LA(1)<=79) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_not1076);    throw mse;
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
    // Sql.g:104:1: like : ( 'like' | 'LIKE' ) ;
    public final like_return like() throws RecognitionException {
        like_return retval = new like_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:104:7: ( ( 'like' | 'LIKE' ) )
            // Sql.g:104:8: ( 'like' | 'LIKE' )
            {
            if ( (input.LA(1)>=80 && input.LA(1)<=81) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_like1089);    throw mse;
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
    // Sql.g:105:1: count : ( 'count' | 'COUNT' ) ;
    public final void count() throws RecognitionException {
        try {
            // Sql.g:105:8: ( ( 'count' | 'COUNT' ) )
            // Sql.g:105:9: ( 'count' | 'COUNT' )
            {
            if ( input.LA(1)==45||input.LA(1)==82 ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_count1102);    throw mse;
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
    // Sql.g:106:1: sum : ( 'sum' | 'SUM' ) ;
    public final void sum() throws RecognitionException {
        try {
            // Sql.g:106:6: ( ( 'sum' | 'SUM' ) )
            // Sql.g:106:7: ( 'sum' | 'SUM' )
            {
            if ( (input.LA(1)>=83 && input.LA(1)<=84) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_sum1115);    throw mse;
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
        "\u016c\uffff";
    static final String DFA1_eofS =
        "\3\uffff\1\12\3\uffff\1\12\10\uffff\2\12\1\uffff\1\12\31\uffff\1"+
        "\12\1\uffff\2\12\11\uffff\2\115\1\uffff\1\115\6\uffff\1\12\4\uffff"+
        "\1\115\4\uffff\1\115\1\uffff\1\115\7\uffff\1\12\4\uffff\3\115\4"+
        "\uffff\3\115\6\uffff\1\12\6\uffff\1\115\12\uffff\1\115\1\uffff\1"+
        "\115\7\uffff\2\115\5\uffff\2\115\1\uffff\1\115\4\uffff\4\115\11"+
        "\uffff\1\115\1\uffff\1\115\10\uffff\1\115\10\uffff\1\115\3\uffff"+
        "\5\115\1\uffff\1\115\4\uffff\3\115\3\uffff\1\115\15\uffff\1\115"+
        "\1\uffff\1\115\1\uffff\1\115\10\uffff\1\115\2\uffff\1\115\4\uffff"+
        "\6\115\1\uffff\2\115\2\uffff\2\115\12\uffff\1\115\11\uffff\1\115"+
        "\2\uffff\1\115\3\uffff\4\115\3\uffff\1\115\3\uffff\2\115\1\uffff"+
        "\2\115\13\uffff\1\115\6\uffff\3\115\2\uffff\3\115\1\uffff\1\115"+
        "\10\uffff\1\115\4\uffff\3\115\1\uffff\2\115\1\uffff\1\115\7\uffff"+
        "\1\115\1\uffff\2\115\4\uffff\1\115\1\uffff\1\115";
    static final String DFA1_minS =
        "\1\100\5\4\1\24\3\4\2\uffff\21\4\1\6\1\24\4\4\1\24\12\4\1\24\5\4"+
        "\1\6\14\4\1\6\3\4\1\24\2\4\1\12\3\4\2\uffff\1\4\1\12\1\4\1\10\2"+
        "\7\2\4\1\24\3\4\1\12\14\4\4\12\10\4\1\12\1\4\1\24\10\4\1\12\1\4"+
        "\1\12\1\4\1\12\2\4\1\12\2\4\1\12\16\4\1\6\11\4\1\12\2\4\1\12\1\4"+
        "\1\12\1\4\1\10\2\7\2\4\1\24\2\12\1\4\2\12\1\10\2\7\1\12\1\4\1\12"+
        "\2\4\1\12\6\4\1\12\1\4\3\12\4\4\1\6\1\4\1\12\1\4\3\12\3\4\1\12\5"+
        "\4\1\12\1\4\1\12\1\4\1\12\1\4\1\12\2\4\5\12\1\4\2\12\2\4\1\12\1"+
        "\4\1\12\11\4\1\6\1\7\4\4\1\12\4\4\3\12\1\4\2\12\1\10\2\7\4\12\1"+
        "\4\2\12\1\4\1\12\1\4\1\12\4\4\1\6\1\4\1\12\1\4\3\12\2\4\1\6\7\4"+
        "\6\12\1\4\6\12\3\4\1\6\1\7\3\4\1\6\4\4\5\12\1\4\4\12\3\4\1\6\2\4"+
        "\1\7\3\4\5\12\1\4\1\6\3\4\2\12\1\7\1\4\1\12\1\4";
    static final String DFA1_maxS =
        "\1\103\2\124\1\107\2\17\1\77\1\107\2\124\2\uffff\1\17\1\36\1\17"+
        "\1\36\2\107\1\124\1\107\2\17\1\124\1\121\2\17\1\36\1\20\1\36\1\6"+
        "\1\77\1\17\1\36\1\17\1\36\1\77\1\121\1\17\1\14\1\12\1\17\1\36\1"+
        "\17\1\36\1\20\1\107\1\67\2\107\1\36\1\20\1\36\1\6\2\121\1\17\1\12"+
        "\1\14\2\113\1\12\1\113\1\36\1\20\1\36\1\6\2\20\1\107\1\67\1\12\1"+
        "\20\1\12\1\113\2\124\2\uffff\1\113\1\12\1\113\3\12\1\20\1\121\1"+
        "\67\1\20\1\107\1\20\1\12\1\20\1\12\3\113\1\124\1\121\2\17\3\113"+
        "\4\12\2\20\1\107\3\20\1\12\1\20\1\12\1\113\1\77\1\121\1\17\1\14"+
        "\1\12\1\17\1\36\1\17\1\36\1\12\1\113\1\12\1\113\1\12\1\20\1\121"+
        "\1\12\2\20\1\12\2\113\2\121\1\17\1\12\1\14\2\113\1\12\1\113\1\36"+
        "\1\20\1\36\1\6\4\113\1\11\4\20\2\12\1\20\1\12\1\113\1\12\1\113\3"+
        "\12\1\20\1\121\1\67\2\12\1\113\6\12\1\20\1\12\1\113\1\20\2\12\5"+
        "\113\1\12\1\113\3\12\1\20\3\113\2\11\1\12\1\113\3\12\3\20\1\12\3"+
        "\20\1\12\1\20\1\12\1\113\1\12\1\113\1\12\1\113\1\12\1\20\1\121\5"+
        "\12\1\113\2\12\1\113\1\20\1\12\1\20\1\12\6\113\1\11\2\113\2\11\2"+
        "\113\2\20\1\12\4\20\3\12\1\113\11\12\1\113\2\12\1\113\1\12\1\20"+
        "\1\12\4\113\2\11\1\12\1\113\3\12\2\113\1\11\2\113\5\20\6\12\1\113"+
        "\6\12\3\113\2\11\3\113\1\11\1\113\3\20\5\12\1\113\4\12\3\113\1\11"+
        "\2\113\1\11\1\113\2\20\5\12\1\113\1\11\2\113\1\20\2\12\1\11\1\113"+
        "\1\12\1\113";
    static final String DFA1_acceptS =
        "\12\uffff\1\2\1\3\100\uffff\1\4\1\1\u011e\uffff";
    static final String DFA1_specialS =
        "\u016c\uffff}>";
    static final String[] DFA1_transitionS = {
            "\4\1",
            "\1\2\16\uffff\14\3\16\uffff\1\4\44\uffff\1\4\2\5",
            "\1\2\16\uffff\14\3\16\uffff\1\4\44\uffff\1\4\2\5",
            "\1\7\1\10\1\6\12\uffff\2\11\63\uffff\2\13",
            "\1\14\12\uffff\1\15",
            "\1\16\12\uffff\1\17",
            "\2\21\11\uffff\31\21\10\20",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\13",
            "\1\22\16\uffff\14\23\16\uffff\1\24\44\uffff\1\24\2\25",
            "\1\26\16\uffff\14\27\16\uffff\1\30\44\uffff\1\30\2\31",
            "",
            "",
            "\1\14\12\uffff\1\15",
            "\1\32\16\uffff\14\33",
            "\1\16\12\uffff\1\17",
            "\1\34\16\uffff\14\35",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\13",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\13",
            "\1\22\16\uffff\14\23\16\uffff\1\24\44\uffff\1\24\2\25",
            "\1\7\1\10\1\36\12\uffff\2\11\63\uffff\2\13",
            "\1\37\12\uffff\1\40",
            "\1\41\12\uffff\1\42",
            "\1\26\16\uffff\14\27\16\uffff\1\30\44\uffff\1\30\2\31",
            "\1\44\1\uffff\1\43\3\47\102\uffff\2\45\2\uffff\2\46",
            "\1\50\12\uffff\1\51",
            "\1\52\12\uffff\1\53",
            "\1\32\16\uffff\14\33",
            "\1\54\13\uffff\1\55",
            "\1\34\16\uffff\14\35",
            "\1\56",
            "\2\60\11\uffff\31\60\10\57",
            "\1\37\12\uffff\1\40",
            "\1\61\16\uffff\14\62",
            "\1\41\12\uffff\1\42",
            "\1\63\16\uffff\14\64",
            "\2\65\11\uffff\31\65\10\66",
            "\1\44\2\uffff\3\47\102\uffff\2\45\2\uffff\2\46",
            "\1\67\12\uffff\1\70",
            "\1\71\5\uffff\1\72\1\uffff\1\73",
            "\1\74\5\uffff\1\75",
            "\1\50\12\uffff\1\51",
            "\1\76\16\uffff\14\77",
            "\1\52\12\uffff\1\53",
            "\1\100\16\uffff\14\101",
            "\1\54\13\uffff\1\55",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\13",
            "\2\102\11\uffff\31\102",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\13",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\13",
            "\1\61\16\uffff\14\62",
            "\1\103\13\uffff\1\104",
            "\1\63\16\uffff\14\64",
            "\1\105",
            "\1\44\2\uffff\3\47\102\uffff\2\45\2\uffff\2\46",
            "\1\44\2\uffff\3\47\102\uffff\2\45\2\uffff\2\46",
            "\1\67\12\uffff\1\70",
            "\1\106\5\uffff\1\107",
            "\1\71\5\uffff\1\72\1\uffff\1\73",
            "\1\111\1\uffff\1\110\3\uffff\1\72\1\uffff\1\73\67\uffff\2\112"+
            "\2\114\2\uffff\2\113",
            "\1\116\5\uffff\1\72\1\uffff\1\73\67\uffff\2\112\2\114\2\uffff"+
            "\2\113",
            "\1\74\5\uffff\1\75",
            "\1\120\1\uffff\1\117\1\121\1\122\1\123\72\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\76\16\uffff\14\77",
            "\1\124\13\uffff\1\125",
            "\1\100\16\uffff\14\101",
            "\1\126",
            "\1\127\13\uffff\1\130",
            "\1\103\13\uffff\1\104",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\13",
            "\2\131\11\uffff\31\131",
            "\1\106\5\uffff\1\107",
            "\1\133\1\134\1\132\11\uffff\1\135",
            "\1\136",
            "\1\116\5\uffff\1\137\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\140\16\uffff\14\141\16\uffff\1\142\44\uffff\1\142\2\143",
            "\1\140\16\uffff\14\141\16\uffff\1\142\44\uffff\1\142\2\143",
            "",
            "",
            "\1\116\77\uffff\2\112\2\114\2\uffff\2\113",
            "\1\144",
            "\1\116\5\uffff\1\145\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\150\1\147\1\146",
            "\1\151\2\uffff\1\146",
            "\1\152\2\uffff\1\146",
            "\1\124\13\uffff\1\125",
            "\1\44\2\uffff\3\47\102\uffff\2\45\2\uffff\2\46",
            "\2\153\11\uffff\31\153",
            "\1\127\13\uffff\1\130",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\13",
            "\1\154\13\uffff\1\155",
            "\1\156",
            "\1\160\1\134\4\uffff\1\157\5\uffff\1\135",
            "\1\161\5\uffff\1\162",
            "\1\116\77\uffff\2\112\2\114\2\uffff\2\113",
            "\1\116\1\uffff\1\163\3\uffff\1\72\1\uffff\1\73\67\uffff\2\112"+
            "\2\114\2\uffff\2\113",
            "\1\164\5\uffff\1\72\1\uffff\1\73\67\uffff\2\112\2\114\2\uffff"+
            "\2\113",
            "\1\140\16\uffff\14\141\16\uffff\1\142\44\uffff\1\142\2\143",
            "\1\166\1\uffff\1\165\3\171\102\uffff\2\167\2\uffff\2\170",
            "\1\172\12\uffff\1\173",
            "\1\174\12\uffff\1\175",
            "\1\116\1\uffff\1\176\1\121\1\122\1\123\72\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\177\2\uffff\1\121\1\122\1\123\72\uffff\2\112\2\114\2\uffff"+
            "\2\113",
            "\1\u0081\1\uffff\1\u0080\4\uffff\1\u0082\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\146",
            "\1\146",
            "\1\146",
            "\1\146",
            "\1\u0083\13\uffff\1\u0084",
            "\1\154\13\uffff\1\155",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\13",
            "\1\160\1\134\1\u0085\11\uffff\1\135",
            "\1\u0086\1\134\12\uffff\1\135",
            "\1\160\1\134\12\uffff\1\135",
            "\1\161\5\uffff\1\162",
            "\1\u0087\1\134\1\u0088\11\uffff\1\135",
            "\1\u0089",
            "\1\116\5\uffff\1\u008a\71\uffff\2\112\2\114\2\uffff\2\113",
            "\2\u008c\11\uffff\31\u008c\10\u008b",
            "\1\166\2\uffff\3\171\102\uffff\2\167\2\uffff\2\170",
            "\1\u008d\12\uffff\1\u008e",
            "\1\u008f\5\uffff\1\u0090\1\uffff\1\u0091",
            "\1\u0092\5\uffff\1\u0093",
            "\1\172\12\uffff\1\173",
            "\1\u0094\16\uffff\14\u0095",
            "\1\174\12\uffff\1\175",
            "\1\u0096\16\uffff\14\u0097",
            "\1\u0098",
            "\1\116\5\uffff\1\u0099\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u009a",
            "\1\116\5\uffff\1\u009b\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u009c",
            "\1\u0083\13\uffff\1\u0084",
            "\1\44\2\uffff\3\47\102\uffff\2\45\2\uffff\2\46",
            "\1\u009d",
            "\1\160\1\134\4\uffff\1\u009e\5\uffff\1\135",
            "\1\160\1\134\4\uffff\1\u009f\5\uffff\1\135",
            "\1\u00a0",
            "\1\116\1\uffff\1\u00a1\3\uffff\1\72\1\uffff\1\73\67\uffff\2"+
            "\112\2\114\2\uffff\2\113",
            "\1\116\5\uffff\1\72\1\uffff\1\73\67\uffff\2\112\2\114\2\uffff"+
            "\2\113",
            "\1\166\2\uffff\3\171\102\uffff\2\167\2\uffff\2\170",
            "\1\166\2\uffff\3\171\102\uffff\2\167\2\uffff\2\170",
            "\1\u008d\12\uffff\1\u008e",
            "\1\u00a2\5\uffff\1\u00a3",
            "\1\u008f\5\uffff\1\u0090\1\uffff\1\u0091",
            "\1\u00a5\1\uffff\1\u00a4\3\uffff\1\u0090\1\uffff\1\u0091\67"+
            "\uffff\2\112\2\114\2\uffff\2\113",
            "\1\116\5\uffff\1\u0090\1\uffff\1\u0091\67\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u0092\5\uffff\1\u0093",
            "\1\u00a7\1\uffff\1\u00a6\1\u00a8\1\u00a9\1\u00aa\72\uffff\2"+
            "\112\2\114\2\uffff\2\113",
            "\1\u0094\16\uffff\14\u0095",
            "\1\u00ab\13\uffff\1\u00ac",
            "\1\u0096\16\uffff\14\u0097",
            "\1\u00ad",
            "\1\116\1\uffff\1\u00ae\1\121\1\122\1\123\72\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\116\2\uffff\1\121\1\122\1\123\72\uffff\2\112\2\114\2\uffff"+
            "\2\113",
            "\1\116\1\uffff\1\u00af\4\uffff\1\u0082\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u00b0\6\uffff\1\u0082\70\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u00b2\1\uffff\1\u00b1\1\u00b3\1\u00b4\1\u00b5",
            "\1\160\1\134\1\u00b6\11\uffff\1\135",
            "\1\160\1\134\12\uffff\1\135",
            "\1\u00b7\1\134\12\uffff\1\135",
            "\1\160\1\134\1\u00b8\11\uffff\1\135",
            "\1\u00b9",
            "\1\u00a2\5\uffff\1\u00a3",
            "\1\u00ba\1\u00bc\1\u00bb\11\uffff\1\u00bd",
            "\1\u00be",
            "\1\116\5\uffff\1\u00bf\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u00c0",
            "\1\116\5\uffff\1\u00c1\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u00c4\1\u00c2\1\u00c3",
            "\1\u00c5\2\uffff\1\u00c3",
            "\1\u00c6\2\uffff\1\u00c3",
            "\1\u00ab\13\uffff\1\u00ac",
            "\1\166\2\uffff\3\171\102\uffff\2\167\2\uffff\2\170",
            "\2\u00c7\11\uffff\31\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\1\116\5\uffff\1\u00ca\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd\1\u00cf\1\u00ce",
            "\1\u00d0\2\uffff\1\u00ce",
            "\1\u00d1\2\uffff\1\u00ce",
            "\1\u00d2",
            "\1\160\1\134\4\uffff\1\u00d3\5\uffff\1\135",
            "\1\u00d4",
            "\1\116\1\uffff\1\u00d5\3\uffff\1\72\1\uffff\1\73\67\uffff\2"+
            "\112\2\114\2\uffff\2\113",
            "\1\u00d7\1\u00bc\4\uffff\1\u00d6\5\uffff\1\u00bd",
            "\1\u00d8",
            "\1\u00d9\5\uffff\1\u00da",
            "\1\116\77\uffff\2\112\2\114\2\uffff\2\113",
            "\1\116\1\uffff\1\u00db\3\uffff\1\u0090\1\uffff\1\u0091\67\uffff"+
            "\2\112\2\114\2\uffff\2\113",
            "\1\u00dc\5\uffff\1\u0090\1\uffff\1\u0091\67\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\116\1\uffff\1\u00dd\1\u00a8\1\u00a9\1\u00aa\72\uffff\2\112"+
            "\2\114\2\uffff\2\113",
            "\1\u00de\2\uffff\1\u00a8\1\u00a9\1\u00aa\72\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u00c3",
            "\1\u00e0\1\uffff\1\u00df\4\uffff\1\u00e1\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u00c3",
            "\1\u00c3",
            "\1\u00c3",
            "\1\u00e2\13\uffff\1\u00e3",
            "\1\116\1\uffff\1\u00e4\1\121\1\122\1\123\72\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\116\1\uffff\1\u00e5\4\uffff\1\u0082\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\116\6\uffff\1\u0082\70\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u00e6\1\u00b3\1\u00b4\1\u00b5",
            "\1\u00e7\2\uffff\1\u00b3\1\u00b4\1\u00b5",
            "\1\u00ce",
            "\1\u00e9\1\uffff\1\u00e8\4\uffff\1\u0082\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u00ce",
            "\1\u00ce",
            "\1\u00ce",
            "\1\160\1\134\1\u00ea\11\uffff\1\135",
            "\1\160\1\134\12\uffff\1\135",
            "\1\160\1\134\1\u00eb\11\uffff\1\135",
            "\1\u00ec",
            "\1\u00ed\1\u00bc\12\uffff\1\u00bd",
            "\1\u00d7\1\u00bc\12\uffff\1\u00bd",
            "\1\u00d7\1\u00bc\1\u00ee\11\uffff\1\u00bd",
            "\1\u00d9\5\uffff\1\u00da",
            "\1\u00ef\1\u00bc\1\u00f0\11\uffff\1\u00bd",
            "\1\u00f1",
            "\1\116\5\uffff\1\u00f2\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u00f3",
            "\1\116\5\uffff\1\u00f4\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u00f5",
            "\1\116\5\uffff\1\u00f6\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u00f7",
            "\1\u00e2\13\uffff\1\u00e3",
            "\1\166\2\uffff\3\171\102\uffff\2\167\2\uffff\2\170",
            "\1\u00f8",
            "\1\u00f9",
            "\1\u00fa",
            "\1\u00fb",
            "\1\u00fc",
            "\1\116\5\uffff\1\u00fd\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u00fe",
            "\1\u00ff",
            "\1\116\1\uffff\1\u0100\3\uffff\1\72\1\uffff\1\73\67\uffff\2"+
            "\112\2\114\2\uffff\2\113",
            "\1\u00d7\1\u00bc\4\uffff\1\u0101\5\uffff\1\u00bd",
            "\1\u0102",
            "\1\u00d7\1\u00bc\4\uffff\1\u0103\5\uffff\1\u00bd",
            "\1\u0104",
            "\1\116\1\uffff\1\u0105\3\uffff\1\u0090\1\uffff\1\u0091\67\uffff"+
            "\2\112\2\114\2\uffff\2\113",
            "\1\116\5\uffff\1\u0090\1\uffff\1\u0091\67\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\116\1\uffff\1\u0106\1\u00a8\1\u00a9\1\u00aa\72\uffff\2\112"+
            "\2\114\2\uffff\2\113",
            "\1\116\2\uffff\1\u00a8\1\u00a9\1\u00aa\72\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\116\1\uffff\1\u0107\4\uffff\1\u00e1\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u0108\6\uffff\1\u00e1\70\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u010a\1\uffff\1\u0109\1\u010b\1\u010c\1\u010d",
            "\1\116\1\uffff\1\u010e\1\121\1\122\1\123\72\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\116\1\uffff\1\u010f\4\uffff\1\u0082\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u0110\1\u00b3\1\u00b4\1\u00b5",
            "\1\u00b3\1\u00b4\1\u00b5",
            "\1\116\1\uffff\1\u0111\4\uffff\1\u0082\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u0112\6\uffff\1\u0082\70\uffff\2\112\2\114\2\uffff\2\113",
            "\1\160\1\134\1\u0113\11\uffff\1\135",
            "\1\160\1\134\1\u0114\11\uffff\1\135",
            "\1\u0115",
            "\1\u00d7\1\u00bc\12\uffff\1\u00bd",
            "\1\u00d7\1\u00bc\1\u0116\11\uffff\1\u00bd",
            "\1\u0117\1\u00bc\12\uffff\1\u00bd",
            "\1\u00d7\1\u00bc\1\u0118\11\uffff\1\u00bd",
            "\1\u0119",
            "\1\u011a",
            "\1\u011b",
            "\1\116\5\uffff\1\u011c\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u011d",
            "\1\u011e",
            "\1\u011f\1\u0121\1\u0120",
            "\1\u0122\2\uffff\1\u0120",
            "\1\u0123\2\uffff\1\u0120",
            "\1\u0124",
            "\1\u0125",
            "\1\u0126",
            "\1\u0127",
            "\1\116\5\uffff\1\u0128\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u0129",
            "\1\u012a",
            "\1\116\5\uffff\1\72\1\uffff\1\73\67\uffff\2\112\2\114\2\uffff"+
            "\2\113",
            "\1\u012b",
            "\1\u00d7\1\u00bc\4\uffff\1\u012c\5\uffff\1\u00bd",
            "\1\u012d",
            "\1\116\1\uffff\1\u012e\3\uffff\1\u0090\1\uffff\1\u0091\67\uffff"+
            "\2\112\2\114\2\uffff\2\113",
            "\1\116\1\uffff\1\u012f\1\u00a8\1\u00a9\1\u00aa\72\uffff\2\112"+
            "\2\114\2\uffff\2\113",
            "\1\116\1\uffff\1\u0130\4\uffff\1\u00e1\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\116\6\uffff\1\u00e1\70\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u0131\1\u010b\1\u010c\1\u010d",
            "\1\u0132\2\uffff\1\u010b\1\u010c\1\u010d",
            "\1\u0120",
            "\1\u0134\1\uffff\1\u0133\4\uffff\1\u00e1\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u0120",
            "\1\u0120",
            "\1\u0120",
            "\1\116\2\uffff\1\121\1\122\1\123\72\uffff\2\112\2\114\2\uffff"+
            "\2\113",
            "\1\116\1\uffff\1\u0135\4\uffff\1\u0082\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u0136\1\u00b3\1\u00b4\1\u00b5",
            "\1\116\1\uffff\1\u0137\4\uffff\1\u0082\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\116\6\uffff\1\u0082\70\uffff\2\112\2\114\2\uffff\2\113",
            "\1\160\1\134\12\uffff\1\135",
            "\1\160\1\134\1\u0138\11\uffff\1\135",
            "\1\u00d7\1\u00bc\1\u0139\11\uffff\1\u00bd",
            "\1\u00d7\1\u00bc\12\uffff\1\u00bd",
            "\1\u00d7\1\u00bc\1\u013a\11\uffff\1\u00bd",
            "\1\u013b",
            "\1\u013c",
            "\1\u013d",
            "\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\116\5\uffff\1\u0141\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u0142",
            "\1\u0143",
            "\1\u0144",
            "\1\u0145",
            "\1\u0146",
            "\1\u0147",
            "\1\116\1\uffff\1\u0148\3\uffff\1\u0090\1\uffff\1\u0091\67\uffff"+
            "\2\112\2\114\2\uffff\2\113",
            "\1\116\1\uffff\1\u0149\1\u00a8\1\u00a9\1\u00aa\72\uffff\2\112"+
            "\2\114\2\uffff\2\113",
            "\1\116\1\uffff\1\u014a\4\uffff\1\u00e1\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u014b\1\u010b\1\u010c\1\u010d",
            "\1\u010b\1\u010c\1\u010d",
            "\1\116\1\uffff\1\u014c\4\uffff\1\u00e1\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u014d\6\uffff\1\u00e1\70\uffff\2\112\2\114\2\uffff\2\113",
            "\1\116\6\uffff\1\u0082\70\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u014e\1\u00b3\1\u00b4\1\u00b5",
            "\1\116\1\uffff\1\u014f\4\uffff\1\u0082\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\160\1\134\12\uffff\1\135",
            "\1\u00d7\1\u00bc\1\u0150\11\uffff\1\u00bd",
            "\1\u00d7\1\u00bc\1\u0151\11\uffff\1\u00bd",
            "\1\u0152",
            "\1\u0153",
            "\1\u0154",
            "\1\u0155",
            "\1\u0156",
            "\1\116\5\uffff\1\u0157\71\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u0158",
            "\1\u0159",
            "\1\u015a",
            "\1\u015b",
            "\1\116\5\uffff\1\u0090\1\uffff\1\u0091\67\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\116\2\uffff\1\u00a8\1\u00a9\1\u00aa\72\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\116\1\uffff\1\u015c\4\uffff\1\u00e1\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u015d\1\u010b\1\u010c\1\u010d",
            "\1\116\1\uffff\1\u015e\4\uffff\1\u00e1\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\116\6\uffff\1\u00e1\70\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u00b3\1\u00b4\1\u00b5",
            "\1\116\1\uffff\1\u015f\4\uffff\1\u0082\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u00d7\1\u00bc\12\uffff\1\u00bd",
            "\1\u00d7\1\u00bc\1\u0160\11\uffff\1\u00bd",
            "\1\u0161",
            "\1\u0162",
            "\1\u0163",
            "\1\u0164",
            "\1\u0165",
            "\1\116\6\uffff\1\u00e1\70\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u0166\1\u010b\1\u010c\1\u010d",
            "\1\116\1\uffff\1\u0167\4\uffff\1\u00e1\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\116\6\uffff\1\u0082\70\uffff\2\112\2\114\2\uffff\2\113",
            "\1\u00d7\1\u00bc\12\uffff\1\u00bd",
            "\1\u0168",
            "\1\u0169",
            "\1\u010b\1\u010c\1\u010d",
            "\1\116\1\uffff\1\u016a\4\uffff\1\u00e1\70\uffff\2\112\2\114"+
            "\2\uffff\2\113",
            "\1\u016b",
            "\1\116\6\uffff\1\u00e1\70\uffff\2\112\2\114\2\uffff\2\113"
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
        "\2\107\2\uffff";
    static final String DFA3_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA3_specialS =
        "\4\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\3\13\uffff\2\2\63\uffff\2\2",
            "\1\1\1\3\13\uffff\2\2\63\uffff\2\2",
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
        "\2\113\2\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA5_specialS =
        "\4\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\1\77\uffff\2\3\2\2\2\uffff\2\3",
            "\1\1\77\uffff\2\3\2\2\2\uffff\2\3",
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
        "\1\23\4\4\1\uffff\1\24\2\uffff\11\4\1\6\2\4\1\24\3\4";
    static final String DFA6_maxS =
        "\1\124\1\121\2\17\1\121\1\uffff\1\77\2\uffff\1\17\1\36\1\17\1\36"+
        "\2\121\1\36\1\20\1\36\1\6\1\20\1\121\1\67\2\20\1\121";
    static final String DFA6_acceptS =
        "\5\uffff\1\2\1\uffff\1\1\1\3\20\uffff";
    static final String DFA6_specialS =
        "\31\uffff}>";
    static final String[] DFA6_transitionS = {
            "\14\1\16\uffff\1\2\44\uffff\1\2\2\3",
            "\1\4\1\uffff\1\6\3\7\102\uffff\2\5\2\uffff\2\10",
            "\1\11\12\uffff\1\12",
            "\1\13\12\uffff\1\14",
            "\1\4\2\uffff\3\7\102\uffff\2\5\2\uffff\2\10",
            "",
            "\2\15\11\uffff\31\15\10\16",
            "",
            "",
            "\1\11\12\uffff\1\12",
            "\1\17\16\uffff\14\20",
            "\1\13\12\uffff\1\14",
            "\1\21\16\uffff\14\22",
            "\1\4\2\uffff\3\7\102\uffff\2\5\2\uffff\2\10",
            "\1\4\2\uffff\3\7\102\uffff\2\5\2\uffff\2\10",
            "\1\17\16\uffff\14\20",
            "\1\23\13\uffff\1\24",
            "\1\21\16\uffff\14\22",
            "\1\25",
            "\1\23\13\uffff\1\24",
            "\1\4\2\uffff\3\7\102\uffff\2\5\2\uffff\2\10",
            "\2\26\11\uffff\31\26",
            "\1\27\13\uffff\1\30",
            "\1\27\13\uffff\1\30",
            "\1\4\2\uffff\3\7\102\uffff\2\5\2\uffff\2\10"
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
            return "50:1: constraint : (kw= keyword spaces op= ( EQ | LT | GT ) spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= likeValue );";
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
            return "()* loopback of 82:21: ( spaces COMMA spaces dotValue )*";
        }
    }
 

    public static final BitSet FOLLOW_select_in_stmt27 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt29 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_selectList_in_stmt31 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt33 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_where_in_stmt35 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt37 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_constraintList_in_stmt39 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt41 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt47 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt49 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_selectList_in_stmt51 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt53 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt58 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt60 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_selectList_in_stmt62 = new BitSet(new long[]{0x0000000000000010L,0x00000000000000C0L});
    public static final BitSet FOLLOW_spaces_in_stmt64 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_order_in_stmt66 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000300L});
    public static final BitSet FOLLOW_spaces_in_stmt68 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_by_in_stmt70 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt72 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_keyword_in_stmt77 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt88 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt90 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_selectList_in_stmt92 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt94 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_where_in_stmt96 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt98 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_constraintList_in_stmt100 = new BitSet(new long[]{0x0000000000000010L,0x00000000000000C0L});
    public static final BitSet FOLLOW_spaces_in_stmt102 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_order_in_stmt104 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000300L});
    public static final BitSet FOLLOW_spaces_in_stmt106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_by_in_stmt108 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt110 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_keyword_in_stmt115 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SPACE_in_spaces131 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_keyword_in_selectList146 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_selectList159 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_selectList163 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_selectList167 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_keyword_in_selectList174 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_entity_in_keyword199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword205 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword207 = new BitSet(new long[]{0x00FFFFFF80300000L});
    public static final BitSet FOLLOW_attr_in_keyword209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword214 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword216 = new BitSet(new long[]{0xFF00000000000000L});
    public static final BitSet FOLLOW_funct_in_keyword218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_count_in_keyword223 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword225 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword227 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_keyword229 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_entity_in_keyword231 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword233 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sum_in_keyword240 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword242 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword244 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_keyword246 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_entity_in_keyword248 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword250 = new BitSet(new long[]{0x00FFFFFF80300000L});
    public static final BitSet FOLLOW_attr_in_keyword252 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword254 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_constraintList265 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000C30L});
    public static final BitSet FOLLOW_spaces_in_constraintList269 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000C30L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList276 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_constraintList284 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_constraint_in_constraintList286 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000C30L});
    public static final BitSet FOLLOW_keyword_in_constraint299 = new BitSet(new long[]{0x0000000000000390L});
    public static final BitSet FOLLOW_spaces_in_constraint308 = new BitSet(new long[]{0x0000000000000380L});
    public static final BitSet FOLLOW_set_in_constraint315 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_spaces_in_constraint335 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_genValue_in_constraint342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint371 = new BitSet(new long[]{0x0000000000000010L,0x0000000000003000L});
    public static final BitSet FOLLOW_spaces_in_constraint380 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_in_in_constraint387 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_constraint398 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_constraint400 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_spaces_in_constraint404 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_valueList_in_constraint410 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_constraint418 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_constraint422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint448 = new BitSet(new long[]{0x0000000000000010L,0x0000000000030000L});
    public static final BitSet FOLLOW_spaces_in_constraint457 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_like_in_constraint464 = new BitSet(new long[]{0x0000000000001410L});
    public static final BitSet FOLLOW_spaces_in_constraint473 = new BitSet(new long[]{0x0000000000001400L});
    public static final BitSet FOLLOW_likeValue_in_constraint480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue536 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue538 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue546 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue548 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue550 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue552 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue560 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue562 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue564 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue566 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue568 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue570 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue578 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue580 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue582 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue584 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue586 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue588 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue590 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue592 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue600 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue602 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue604 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue606 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue608 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue610 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue612 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue614 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue616 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue618 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue626 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue628 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue636 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue638 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue640 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue642 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_valueList652 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_valueList656 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_valueList658 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_spaces_in_valueList660 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dotValue_in_valueList662 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_EQ_in_compOpt673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt694 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_GT_in_compOpt697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt704 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_LT_in_compOpt707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt714 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EQ_in_compOpt717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt724 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EQ_in_compOpt727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue739 = new BitSet(new long[]{0x0000000000000380L});
    public static final BitSet FOLLOW_compOpt_in_genValue741 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dotValue_in_genValue743 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_AMP_in_genValue746 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dotValue_in_genValue748 = new BitSet(new long[]{0x0000000000000380L});
    public static final BitSet FOLLOW_compOpt_in_genValue750 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dotValue_in_genValue752 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_dotValue_in_likeValue762 = new BitSet(new long[]{0x0000000000001402L});
    public static final BitSet FOLLOW_STAR_in_likeValue765 = new BitSet(new long[]{0x0000000000001402L});
    public static final BitSet FOLLOW_and_in_logicalOp774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and1011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_order1024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_by1037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or1050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in1063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not1076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_like1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_count1102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sum1115 = new BitSet(new long[]{0x0000000000000002L});

}