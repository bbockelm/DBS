package dbs.search.parser;
// $ANTLR 3.0.1 Sql.g 2008-07-07 09:50:09


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
                case 4 :
                    // Sql.g:43:4: count spaces '(' spaces entity spaces ')'
                    {
                    pushFollow(FOLLOW_count_in_keyword220);
                    count();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword222);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_keyword224); 
                    pushFollow(FOLLOW_spaces_in_keyword226);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_entity_in_keyword228);
                    entity();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword230);
                    spaces();
                    _fsp--;

                    match(input,16,FOLLOW_16_in_keyword232); 

                    }
                    break;
                case 5 :
                    // Sql.g:44:4: sum spaces '(' spaces entity DOT attr spaces ')'
                    {
                    pushFollow(FOLLOW_sum_in_keyword237);
                    sum();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword239);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_keyword241); 
                    pushFollow(FOLLOW_spaces_in_keyword243);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_entity_in_keyword245);
                    entity();
                    _fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword247); 
                    pushFollow(FOLLOW_attr_in_keyword249);
                    attr();
                    _fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword251);
                    spaces();
                    _fsp--;

                    match(input,16,FOLLOW_16_in_keyword253); 

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
            pushFollow(FOLLOW_constraint_in_constraintList262);
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
            	    pushFollow(FOLLOW_spaces_in_constraintList266);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_logicalOp_in_constraintList273);
            	    rel=logicalOp();
            	    _fsp--;

            	     constraints.add(input.toString(rel.start,rel.stop));
            	    pushFollow(FOLLOW_spaces_in_constraintList281);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_constraint_in_constraintList283);
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
                    pushFollow(FOLLOW_keyword_in_constraint296);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint305);
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
                        recoverFromMismatchedSet(input,mse,FOLLOW_set_in_constraint312);    throw mse;
                    }

                    c.setOp(op.getText());
                    pushFollow(FOLLOW_spaces_in_constraint332);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_genValue_in_constraint339);
                    val=genValue();
                    _fsp--;

                    c.setValue(input.toString(val.start,val.stop)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:56:2: kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')'
                    {
                    pushFollow(FOLLOW_keyword_in_constraint368);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint377);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_in_in_constraint384);
                    op1=in();
                    _fsp--;

                    c.setOp(input.toString(op1.start,op1.stop));
                    pushFollow(FOLLOW_spaces_in_constraint395);
                    spaces();
                    _fsp--;

                    match(input,15,FOLLOW_15_in_constraint397); 
                    pushFollow(FOLLOW_spaces_in_constraint401);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_valueList_in_constraint407);
                    val1=valueList();
                    _fsp--;

                    c.setValue(input.toString(val1.start,val1.stop)); constraints.add(c);
                    pushFollow(FOLLOW_spaces_in_constraint415);
                    spaces();
                    _fsp--;

                    match(input,16,FOLLOW_16_in_constraint419); 

                    }
                    break;
                case 3 :
                    // Sql.g:65:2: kw= keyword spaces op2= like spaces val2= likeValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint445);
                    kw=keyword();
                    _fsp--;

                    Constraint c= new Constraint(); c.setKey(input.toString(kw.start,kw.stop));
                    pushFollow(FOLLOW_spaces_in_constraint454);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_like_in_constraint461);
                    op2=like();
                    _fsp--;

                    c.setOp(input.toString(op2.start,op2.stop));
                    pushFollow(FOLLOW_spaces_in_constraint470);
                    spaces();
                    _fsp--;

                    pushFollow(FOLLOW_likeValue_in_constraint477);
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_where506);    throw mse;
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
                case SPACE:
                    {
                    int LA7_2 = input.LA(3);

                    if ( (LA7_2==VALUE) ) {
                        int LA7_5 = input.LA(4);

                        if ( (LA7_5==SPACE) ) {
                            int LA7_7 = input.LA(5);

                            if ( (LA7_7==VALUE) ) {
                                alt7=8;
                            }
                            else if ( ((LA7_7>=SPACE && LA7_7<=COMMA)||LA7_7==16||(LA7_7>=68 && LA7_7<=71)||(LA7_7>=74 && LA7_7<=75)) ) {
                                alt7=7;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 7, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA7_5==EOF||LA7_5==COMMA||(LA7_5>=EQ && LA7_5<=STAR)||LA7_5==16||(LA7_5>=68 && LA7_5<=71)||(LA7_5>=74 && LA7_5<=75)) ) {
                            alt7=7;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 5, input);

                            throw nvae;
                        }
                    }
                    else if ( ((LA7_2>=SPACE && LA7_2<=COMMA)||LA7_2==16||(LA7_2>=68 && LA7_2<=71)||(LA7_2>=74 && LA7_2<=75)) ) {
                        alt7=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case DOT:
                    {
                    int LA7_3 = input.LA(3);

                    if ( (LA7_3==VALUE) ) {
                        int LA7_6 = input.LA(4);

                        if ( (LA7_6==DOT) ) {
                            int LA7_9 = input.LA(5);

                            if ( (LA7_9==VALUE) ) {
                                int LA7_12 = input.LA(6);

                                if ( (LA7_12==DOT) ) {
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
                                else if ( (LA7_12==EOF||(LA7_12>=SPACE && LA7_12<=COMMA)||(LA7_12>=EQ && LA7_12<=STAR)||LA7_12==16||(LA7_12>=68 && LA7_12<=71)||(LA7_12>=74 && LA7_12<=75)) ) {
                                    alt7=3;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 12, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 9, input);

                                throw nvae;
                            }
                        }
                        else if ( (LA7_6==EOF||(LA7_6>=SPACE && LA7_6<=COMMA)||(LA7_6>=EQ && LA7_6<=STAR)||LA7_6==16||(LA7_6>=68 && LA7_6<=71)||(LA7_6>=74 && LA7_6<=75)) ) {
                            alt7=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("72:1: dotValue : ( VALUE | VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );", 7, 6, input);

                            throw nvae;
                        }
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
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue526); 

                    }
                    break;
                case 2 :
                    // Sql.g:73:5: VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue533); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue535); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue537); 

                    }
                    break;
                case 3 :
                    // Sql.g:74:5: VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue543); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue545); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue547); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue549); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue551); 

                    }
                    break;
                case 4 :
                    // Sql.g:75:5: VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue557); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue559); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue561); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue563); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue565); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue567); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue569); 

                    }
                    break;
                case 5 :
                    // Sql.g:76:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue575); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue577); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue579); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue581); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue583); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue585); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue587); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue589); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue591); 

                    }
                    break;
                case 6 :
                    // Sql.g:77:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue597); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue599); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue601); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue603); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue605); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue607); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue609); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue611); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue613); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue615); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue617); 

                    }
                    break;
                case 7 :
                    // Sql.g:78:5: VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue623); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue625); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue627); 

                    }
                    break;
                case 8 :
                    // Sql.g:79:5: VALUE SPACE VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue633); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue635); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue637); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue639); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue641); 

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
            pushFollow(FOLLOW_dotValue_in_valueList649);
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
            	    pushFollow(FOLLOW_spaces_in_valueList653);
            	    spaces();
            	    _fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_valueList655); 
            	    pushFollow(FOLLOW_spaces_in_valueList657);
            	    spaces();
            	    _fsp--;

            	    pushFollow(FOLLOW_dotValue_in_valueList659);
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

                if ( (LA9_3==EQ) ) {
                    alt9=7;
                }
                else if ( (LA9_3==VALUE) ) {
                    alt9=3;
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
                    match(input,EQ,FOLLOW_EQ_in_compOpt670); 

                    }


                    }
                    break;
                case 2 :
                    // Sql.g:84:4: ( LT )
                    {
                    // Sql.g:84:4: ( LT )
                    // Sql.g:84:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt677); 

                    }


                    }
                    break;
                case 3 :
                    // Sql.g:85:4: ( GT )
                    {
                    // Sql.g:85:4: ( GT )
                    // Sql.g:85:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt684); 

                    }


                    }
                    break;
                case 4 :
                    // Sql.g:86:4: ( EQ ) ( GT )
                    {
                    // Sql.g:86:4: ( EQ )
                    // Sql.g:86:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt691); 

                    }

                    // Sql.g:86:8: ( GT )
                    // Sql.g:86:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt694); 

                    }


                    }
                    break;
                case 5 :
                    // Sql.g:87:4: ( EQ ) ( LT )
                    {
                    // Sql.g:87:4: ( EQ )
                    // Sql.g:87:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt701); 

                    }

                    // Sql.g:87:8: ( LT )
                    // Sql.g:87:9: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt704); 

                    }


                    }
                    break;
                case 6 :
                    // Sql.g:88:4: ( LT ) ( EQ )
                    {
                    // Sql.g:88:4: ( LT )
                    // Sql.g:88:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt711); 

                    }

                    // Sql.g:88:8: ( EQ )
                    // Sql.g:88:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt714); 

                    }


                    }
                    break;
                case 7 :
                    // Sql.g:89:4: ( GT ) ( EQ )
                    {
                    // Sql.g:89:4: ( GT )
                    // Sql.g:89:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt721); 

                    }

                    // Sql.g:89:8: ( EQ )
                    // Sql.g:89:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt724); 

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
                            else if ( (LA11_9==SPACE||(LA11_9>=68 && LA11_9<=71)||(LA11_9>=74 && LA11_9<=75)) ) {
                                alt11=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 9, input);

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
                                new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 7, input);

                            throw nvae;
                        }

                    }
                    else if ( (LA11_3==SPACE||(LA11_3>=68 && LA11_3<=71)||(LA11_3>=74 && LA11_3<=75)) ) {
                        alt11=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("90:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );", 11, 3, input);

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
                    pushFollow(FOLLOW_dotValue_in_genValue731);
                    dotValue();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:91:4: dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )*
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue736);
                    dotValue();
                    _fsp--;

                    pushFollow(FOLLOW_compOpt_in_genValue738);
                    compOpt();
                    _fsp--;

                    pushFollow(FOLLOW_dotValue_in_genValue740);
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
                    	    match(input,AMP,FOLLOW_AMP_in_genValue743); 
                    	    pushFollow(FOLLOW_dotValue_in_genValue745);
                    	    dotValue();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_compOpt_in_genValue747);
                    	    compOpt();
                    	    _fsp--;

                    	    pushFollow(FOLLOW_dotValue_in_genValue749);
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
            	    pushFollow(FOLLOW_dotValue_in_likeValue759);
            	    dotValue();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // Sql.g:92:24: STAR
            	    {
            	    match(input,STAR,FOLLOW_STAR_in_likeValue762); 

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
                    pushFollow(FOLLOW_and_in_logicalOp771);
                    and();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:93:17: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp773);
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_entity782);    throw mse;
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_attr835);    throw mse;
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_funct949);    throw mse;
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_select987);    throw mse;
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_and1008);    throw mse;
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_order1021);    throw mse;
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_by1034);    throw mse;
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_or1047);    throw mse;
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_in1060);    throw mse;
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_not1073);    throw mse;
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_like1086);    throw mse;
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_count1099);    throw mse;
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
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_sum1112);    throw mse;
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
        "\3\uffff\1\13\14\uffff\2\13\1\uffff\1\13\31\uffff\1\13\1\uffff\2"+
        "\13\11\uffff\1\117\1\uffff\2\117\6\uffff\1\13\23\uffff\1\13\4\uffff"+
        "\3\117\1\uffff\1\117\7\uffff\2\117\2\uffff\1\13\33\uffff\4\117\6"+
        "\uffff\2\117\1\uffff\1\117\4\uffff\2\117\34\uffff\3\117\3\uffff"+
        "\1\117\6\uffff\5\117\1\uffff\1\117\4\uffff\1\117\32\uffff\2\117"+
        "\2\uffff\2\117\4\uffff\6\117\1\uffff\1\117\27\uffff\2\117\1\uffff"+
        "\2\117\3\uffff\4\117\2\uffff\1\117\4\uffff\1\117\20\uffff\1\117"+
        "\1\uffff\1\117\2\uffff\3\117\2\uffff\2\117\14\uffff\1\117\2\uffff"+
        "\3\117\1\uffff\2\117\6\uffff\1\117\1\uffff\1\117\1\uffff\1\117\4"+
        "\uffff\1\117\1\uffff\1\117";
    static final String DFA1_minS =
        "\1\100\5\4\1\24\3\4\2\uffff\21\4\1\6\1\24\4\4\1\24\12\4\1\24\5\4"+
        "\1\6\14\4\1\6\3\4\1\24\2\4\1\12\1\4\1\10\2\7\2\4\2\uffff\1\4\1\12"+
        "\3\4\1\24\3\4\1\12\5\4\1\12\1\4\3\12\16\4\1\12\2\4\2\12\2\4\1\24"+
        "\7\4\1\12\2\4\1\12\1\4\1\12\22\4\1\6\6\4\1\12\1\4\3\12\1\10\2\7"+
        "\2\4\1\12\1\4\1\12\1\4\1\10\2\7\2\4\1\24\3\12\4\4\1\6\1\4\1\12\1"+
        "\4\4\12\7\4\1\12\1\4\3\12\5\4\5\12\6\4\1\12\1\4\1\12\1\4\1\12\1"+
        "\4\1\12\2\4\3\12\2\4\1\6\1\7\2\4\1\12\1\4\1\12\13\4\4\12\5\4\3\12"+
        "\1\4\2\12\1\10\2\7\3\12\2\4\1\6\2\4\2\12\5\4\1\6\2\4\4\12\3\4\3"+
        "\12\3\4\6\12\1\4\1\12\1\4\1\6\1\4\2\12\3\4\1\6\1\7\3\4\2\12\2\4"+
        "\5\12\1\4\1\7\1\4\2\12\3\4\1\6\2\4\1\12\2\4\3\12\1\4\1\12\1\4\1"+
        "\6\2\4\2\12\1\7\1\4\1\12\1\4";
    static final String DFA1_maxS =
        "\1\103\2\124\1\107\2\17\1\77\1\107\2\124\2\uffff\1\17\1\36\1\17"+
        "\1\36\2\107\1\124\1\107\2\17\1\124\1\121\2\17\1\36\1\20\1\36\1\6"+
        "\1\77\1\17\1\36\1\17\1\36\1\77\1\121\1\17\1\12\1\14\1\17\1\36\1"+
        "\17\1\36\1\20\1\107\1\67\2\107\1\36\1\20\1\36\1\6\2\121\1\17\2\12"+
        "\1\113\1\14\2\113\1\36\1\20\1\36\1\6\2\20\1\107\1\67\1\12\1\20\1"+
        "\12\1\113\3\12\2\124\2\uffff\1\113\1\12\1\113\1\20\1\121\1\67\1"+
        "\20\1\107\1\20\1\12\1\20\1\12\3\113\1\12\1\113\3\12\1\124\1\121"+
        "\2\17\2\113\2\20\1\107\3\20\1\12\1\20\1\12\2\113\2\12\1\121\1\17"+
        "\1\77\1\14\1\12\1\17\1\36\1\17\1\36\1\113\1\12\1\20\1\121\1\12\1"+
        "\20\1\12\1\20\4\113\1\11\1\17\1\12\2\121\1\14\2\113\1\12\1\113\1"+
        "\36\1\20\1\36\1\6\2\113\4\20\1\12\1\113\7\12\1\20\1\12\1\113\1\12"+
        "\1\113\3\12\1\20\1\121\1\67\3\12\1\20\3\113\2\11\1\12\1\113\4\12"+
        "\1\20\1\12\5\113\1\12\1\113\3\12\1\20\1\113\3\20\5\12\1\113\3\20"+
        "\1\12\1\20\1\12\1\113\1\12\1\113\1\12\1\113\1\12\1\20\1\121\3\12"+
        "\2\113\2\11\2\113\1\12\1\20\1\12\1\20\6\113\1\11\1\113\2\20\4\12"+
        "\1\113\4\20\3\12\1\113\10\12\2\113\1\11\2\113\2\12\1\20\4\113\2"+
        "\11\1\113\4\12\1\113\2\20\3\12\3\20\6\12\1\113\1\12\1\113\1\11\1"+
        "\113\2\12\3\113\2\11\2\113\1\20\2\12\2\20\5\12\1\113\1\11\1\113"+
        "\2\12\3\113\1\11\2\113\1\12\2\20\3\12\1\113\1\12\1\113\1\11\1\113"+
        "\1\20\2\12\1\11\1\113\1\12\1\113";
    static final String DFA1_acceptS =
        "\12\uffff\1\3\1\2\103\uffff\1\1\1\4\u011b\uffff";
    static final String DFA1_specialS =
        "\u016c\uffff}>";
    static final String[] DFA1_transitionS = {
            "\4\1",
            "\1\2\16\uffff\14\3\16\uffff\1\4\44\uffff\1\4\2\5",
            "\1\2\16\uffff\14\3\16\uffff\1\4\44\uffff\1\4\2\5",
            "\1\7\1\10\1\6\12\uffff\2\11\63\uffff\2\12",
            "\1\14\12\uffff\1\15",
            "\1\16\12\uffff\1\17",
            "\2\20\11\uffff\31\20\10\21",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\12",
            "\1\22\16\uffff\14\23\16\uffff\1\24\44\uffff\1\24\2\25",
            "\1\26\16\uffff\14\27\16\uffff\1\30\44\uffff\1\30\2\31",
            "",
            "",
            "\1\14\12\uffff\1\15",
            "\1\32\16\uffff\14\33",
            "\1\16\12\uffff\1\17",
            "\1\34\16\uffff\14\35",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\12",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\12",
            "\1\22\16\uffff\14\23\16\uffff\1\24\44\uffff\1\24\2\25",
            "\1\7\1\10\1\36\12\uffff\2\11\63\uffff\2\12",
            "\1\37\12\uffff\1\40",
            "\1\41\12\uffff\1\42",
            "\1\26\16\uffff\14\27\16\uffff\1\30\44\uffff\1\30\2\31",
            "\1\44\1\uffff\1\43\3\46\102\uffff\2\45\2\uffff\2\47",
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
            "\1\44\2\uffff\3\46\102\uffff\2\45\2\uffff\2\47",
            "\1\67\12\uffff\1\70",
            "\1\71\5\uffff\1\72",
            "\1\73\5\uffff\1\74\1\uffff\1\75",
            "\1\50\12\uffff\1\51",
            "\1\76\16\uffff\14\77",
            "\1\52\12\uffff\1\53",
            "\1\100\16\uffff\14\101",
            "\1\54\13\uffff\1\55",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\12",
            "\2\102\11\uffff\31\102",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\12",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\12",
            "\1\61\16\uffff\14\62",
            "\1\103\13\uffff\1\104",
            "\1\63\16\uffff\14\64",
            "\1\105",
            "\1\44\2\uffff\3\46\102\uffff\2\45\2\uffff\2\47",
            "\1\44\2\uffff\3\46\102\uffff\2\45\2\uffff\2\47",
            "\1\67\12\uffff\1\70",
            "\1\106\5\uffff\1\107",
            "\1\71\5\uffff\1\72",
            "\1\111\1\uffff\1\110\1\112\1\113\1\114\72\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\73\5\uffff\1\74\1\uffff\1\75",
            "\1\121\1\uffff\1\122\3\uffff\1\74\1\uffff\1\75\67\uffff\2\115"+
            "\2\120\2\uffff\2\116",
            "\1\123\5\uffff\1\74\1\uffff\1\75\67\uffff\2\115\2\120\2\uffff"+
            "\2\116",
            "\1\76\16\uffff\14\77",
            "\1\124\13\uffff\1\125",
            "\1\100\16\uffff\14\101",
            "\1\126",
            "\1\127\13\uffff\1\130",
            "\1\103\13\uffff\1\104",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\12",
            "\2\131\11\uffff\31\131",
            "\1\106\5\uffff\1\107",
            "\1\133\1\134\1\132\11\uffff\1\135",
            "\1\136",
            "\1\123\5\uffff\1\137\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\140\1\142\1\141",
            "\1\143\2\uffff\1\141",
            "\1\144\2\uffff\1\141",
            "\1\145\16\uffff\14\146\16\uffff\1\147\44\uffff\1\147\2\150",
            "\1\145\16\uffff\14\146\16\uffff\1\147\44\uffff\1\147\2\150",
            "",
            "",
            "\1\123\5\uffff\1\151\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\152",
            "\1\123\77\uffff\2\115\2\120\2\uffff\2\116",
            "\1\124\13\uffff\1\125",
            "\1\44\2\uffff\3\46\102\uffff\2\45\2\uffff\2\47",
            "\2\153\11\uffff\31\153",
            "\1\127\13\uffff\1\130",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\12",
            "\1\154\13\uffff\1\155",
            "\1\156",
            "\1\160\1\134\4\uffff\1\157\5\uffff\1\135",
            "\1\161\5\uffff\1\162",
            "\1\123\77\uffff\2\115\2\120\2\uffff\2\116",
            "\1\123\1\uffff\1\163\1\112\1\113\1\114\72\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\164\2\uffff\1\112\1\113\1\114\72\uffff\2\115\2\120\2\uffff"+
            "\2\116",
            "\1\141",
            "\1\165\1\uffff\1\166\4\uffff\1\167\70\uffff\2\115\2\120\2\uffff"+
            "\2\116",
            "\1\141",
            "\1\141",
            "\1\141",
            "\1\145\16\uffff\14\146\16\uffff\1\147\44\uffff\1\147\2\150",
            "\1\170\1\uffff\1\172\3\174\102\uffff\2\171\2\uffff\2\173",
            "\1\175\12\uffff\1\176",
            "\1\177\12\uffff\1\u0080",
            "\1\u0081\5\uffff\1\74\1\uffff\1\75\67\uffff\2\115\2\120\2\uffff"+
            "\2\116",
            "\1\123\1\uffff\1\u0082\3\uffff\1\74\1\uffff\1\75\67\uffff\2"+
            "\115\2\120\2\uffff\2\116",
            "\1\u0083\13\uffff\1\u0084",
            "\1\154\13\uffff\1\155",
            "\1\7\1\10\13\uffff\2\11\63\uffff\2\12",
            "\1\160\1\134\1\u0085\11\uffff\1\135",
            "\1\u0086\1\134\12\uffff\1\135",
            "\1\160\1\134\12\uffff\1\135",
            "\1\161\5\uffff\1\162",
            "\1\u0088\1\134\1\u0087\11\uffff\1\135",
            "\1\u0089",
            "\1\123\5\uffff\1\u008a\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\123\5\uffff\1\u008b\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u008c",
            "\1\u008d",
            "\1\170\2\uffff\3\174\102\uffff\2\171\2\uffff\2\173",
            "\1\u008e\12\uffff\1\u008f",
            "\2\u0091\11\uffff\31\u0091\10\u0090",
            "\1\u0092\5\uffff\1\u0093\1\uffff\1\u0094",
            "\1\u0095\5\uffff\1\u0096",
            "\1\175\12\uffff\1\176",
            "\1\u0097\16\uffff\14\u0098",
            "\1\177\12\uffff\1\u0080",
            "\1\u0099\16\uffff\14\u009a",
            "\1\123\5\uffff\1\u009b\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u009c",
            "\1\u0083\13\uffff\1\u0084",
            "\1\44\2\uffff\3\46\102\uffff\2\45\2\uffff\2\47",
            "\1\u009d",
            "\1\160\1\134\4\uffff\1\u009e\5\uffff\1\135",
            "\1\u009f",
            "\1\160\1\134\4\uffff\1\u00a0\5\uffff\1\135",
            "\1\123\1\uffff\1\u00a1\1\112\1\113\1\114\72\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\123\2\uffff\1\112\1\113\1\114\72\uffff\2\115\2\120\2\uffff"+
            "\2\116",
            "\1\u00a2\6\uffff\1\167\70\uffff\2\115\2\120\2\uffff\2\116",
            "\1\123\1\uffff\1\u00a3\4\uffff\1\167\70\uffff\2\115\2\120\2"+
            "\uffff\2\116",
            "\1\u00a5\1\uffff\1\u00a4\1\u00a6\1\u00a7\1\u00a8",
            "\1\u008e\12\uffff\1\u008f",
            "\1\u00a9\5\uffff\1\u00aa",
            "\1\170\2\uffff\3\174\102\uffff\2\171\2\uffff\2\173",
            "\1\170\2\uffff\3\174\102\uffff\2\171\2\uffff\2\173",
            "\1\u0092\5\uffff\1\u0093\1\uffff\1\u0094",
            "\1\u00ac\1\uffff\1\u00ab\3\uffff\1\u0093\1\uffff\1\u0094\67"+
            "\uffff\2\115\2\120\2\uffff\2\116",
            "\1\123\5\uffff\1\u0093\1\uffff\1\u0094\67\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\u0095\5\uffff\1\u0096",
            "\1\u00ae\1\uffff\1\u00ad\1\u00af\1\u00b0\1\u00b1\72\uffff\2"+
            "\115\2\120\2\uffff\2\116",
            "\1\u0097\16\uffff\14\u0098",
            "\1\u00b2\13\uffff\1\u00b3",
            "\1\u0099\16\uffff\14\u009a",
            "\1\u00b4",
            "\1\123\5\uffff\1\74\1\uffff\1\75\67\uffff\2\115\2\120\2\uffff"+
            "\2\116",
            "\1\123\1\uffff\1\u00b5\3\uffff\1\74\1\uffff\1\75\67\uffff\2"+
            "\115\2\120\2\uffff\2\116",
            "\1\160\1\134\1\u00b6\11\uffff\1\135",
            "\1\160\1\134\12\uffff\1\135",
            "\1\160\1\134\1\u00b7\11\uffff\1\135",
            "\1\u00b8\1\134\12\uffff\1\135",
            "\1\u00b9",
            "\1\123\5\uffff\1\u00ba\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00c0\1\u00be\1\u00bf",
            "\1\u00c1\2\uffff\1\u00bf",
            "\1\u00c2\2\uffff\1\u00bf",
            "\1\u00a9\5\uffff\1\u00aa",
            "\1\u00c4\1\u00c5\1\u00c3\11\uffff\1\u00c6",
            "\1\u00c7",
            "\1\123\5\uffff\1\u00c8\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u00c9",
            "\1\123\5\uffff\1\u00ca\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u00cd\1\u00cb\1\u00cc",
            "\1\u00ce\2\uffff\1\u00cc",
            "\1\u00cf\2\uffff\1\u00cc",
            "\1\u00b2\13\uffff\1\u00b3",
            "\1\170\2\uffff\3\174\102\uffff\2\171\2\uffff\2\173",
            "\2\u00d0\11\uffff\31\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "\1\160\1\134\4\uffff\1\u00d4\5\uffff\1\135",
            "\1\123\1\uffff\1\u00d5\1\112\1\113\1\114\72\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\123\6\uffff\1\167\70\uffff\2\115\2\120\2\uffff\2\116",
            "\1\123\1\uffff\1\u00d6\4\uffff\1\167\70\uffff\2\115\2\120\2"+
            "\uffff\2\116",
            "\1\u00d7\1\u00a6\1\u00a7\1\u00a8",
            "\1\u00d8\2\uffff\1\u00a6\1\u00a7\1\u00a8",
            "\1\u00bf",
            "\1\u00da\1\uffff\1\u00d9\4\uffff\1\167\70\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\u00bf",
            "\1\u00bf",
            "\1\u00bf",
            "\1\u00db",
            "\1\u00dd\1\u00c5\4\uffff\1\u00dc\5\uffff\1\u00c6",
            "\1\u00de\5\uffff\1\u00df",
            "\1\123\77\uffff\2\115\2\120\2\uffff\2\116",
            "\1\123\1\uffff\1\u00e0\3\uffff\1\u0093\1\uffff\1\u0094\67\uffff"+
            "\2\115\2\120\2\uffff\2\116",
            "\1\u00e1\5\uffff\1\u0093\1\uffff\1\u0094\67\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\123\1\uffff\1\u00e2\1\u00af\1\u00b0\1\u00b1\72\uffff\2\115"+
            "\2\120\2\uffff\2\116",
            "\1\u00e3\2\uffff\1\u00af\1\u00b0\1\u00b1\72\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\u00cc",
            "\1\u00e5\1\uffff\1\u00e4\4\uffff\1\u00e6\70\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\u00cc",
            "\1\u00cc",
            "\1\u00cc",
            "\1\u00e7\13\uffff\1\u00e8",
            "\1\123\1\uffff\1\u00e9\3\uffff\1\74\1\uffff\1\75\67\uffff\2"+
            "\115\2\120\2\uffff\2\116",
            "\1\160\1\134\1\u00ea\11\uffff\1\135",
            "\1\160\1\134\1\u00eb\11\uffff\1\135",
            "\1\160\1\134\12\uffff\1\135",
            "\1\u00ec",
            "\1\u00ed",
            "\1\u00ee",
            "\1\u00ef",
            "\1\u00f0",
            "\1\123\5\uffff\1\u00f1\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u00dd\1\u00c5\1\u00f2\11\uffff\1\u00c6",
            "\1\u00f3\1\u00c5\12\uffff\1\u00c6",
            "\1\u00dd\1\u00c5\12\uffff\1\u00c6",
            "\1\u00de\5\uffff\1\u00df",
            "\1\u00f5\1\u00c5\1\u00f4\11\uffff\1\u00c6",
            "\1\u00f6",
            "\1\123\5\uffff\1\u00f7\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u00f8",
            "\1\123\5\uffff\1\u00f9\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u00fa",
            "\1\123\5\uffff\1\u00fb\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u00fc",
            "\1\u00e7\13\uffff\1\u00e8",
            "\1\170\2\uffff\3\174\102\uffff\2\171\2\uffff\2\173",
            "\1\u00fd",
            "\1\u00fe",
            "\1\u00ff",
            "\1\123\1\uffff\1\u0100\1\112\1\113\1\114\72\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\123\1\uffff\1\u0101\4\uffff\1\167\70\uffff\2\115\2\120\2"+
            "\uffff\2\116",
            "\1\u0102\1\u00a6\1\u00a7\1\u00a8",
            "\1\u00a6\1\u00a7\1\u00a8",
            "\1\123\1\uffff\1\u0103\4\uffff\1\167\70\uffff\2\115\2\120\2"+
            "\uffff\2\116",
            "\1\u0104\6\uffff\1\167\70\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u0105",
            "\1\u00dd\1\u00c5\4\uffff\1\u0106\5\uffff\1\u00c6",
            "\1\u0107",
            "\1\u00dd\1\u00c5\4\uffff\1\u0108\5\uffff\1\u00c6",
            "\1\123\1\uffff\1\u0109\3\uffff\1\u0093\1\uffff\1\u0094\67\uffff"+
            "\2\115\2\120\2\uffff\2\116",
            "\1\123\5\uffff\1\u0093\1\uffff\1\u0094\67\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\123\1\uffff\1\u010a\1\u00af\1\u00b0\1\u00b1\72\uffff\2\115"+
            "\2\120\2\uffff\2\116",
            "\1\123\2\uffff\1\u00af\1\u00b0\1\u00b1\72\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\123\1\uffff\1\u010b\4\uffff\1\u00e6\70\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\u010c\6\uffff\1\u00e6\70\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u010e\1\uffff\1\u010d\1\u010f\1\u0110\1\u0111",
            "\1\123\1\uffff\1\u0112\3\uffff\1\74\1\uffff\1\75\67\uffff\2"+
            "\115\2\120\2\uffff\2\116",
            "\1\160\1\134\1\u0113\11\uffff\1\135",
            "\1\160\1\134\1\u0114\11\uffff\1\135",
            "\1\u0115",
            "\1\u0116",
            "\1\u0117",
            "\1\u0118",
            "\1\123\5\uffff\1\u0119\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u00dd\1\u00c5\1\u011a\11\uffff\1\u00c6",
            "\1\u00dd\1\u00c5\12\uffff\1\u00c6",
            "\1\u00dd\1\u00c5\1\u011b\11\uffff\1\u00c6",
            "\1\u011c\1\u00c5\12\uffff\1\u00c6",
            "\1\u011d",
            "\1\u011e",
            "\1\u011f",
            "\1\123\5\uffff\1\u0120\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u0121",
            "\1\u0122",
            "\1\u0124\1\u0125\1\u0123",
            "\1\u0126\2\uffff\1\u0123",
            "\1\u0127\2\uffff\1\u0123",
            "\1\u0128",
            "\1\u0129",
            "\1\u012a",
            "\1\123\2\uffff\1\112\1\113\1\114\72\uffff\2\115\2\120\2\uffff"+
            "\2\116",
            "\1\123\1\uffff\1\u012b\4\uffff\1\167\70\uffff\2\115\2\120\2"+
            "\uffff\2\116",
            "\1\u012c\1\u00a6\1\u00a7\1\u00a8",
            "\1\123\1\uffff\1\u012d\4\uffff\1\167\70\uffff\2\115\2\120\2"+
            "\uffff\2\116",
            "\1\123\6\uffff\1\167\70\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u012e",
            "\1\u012f",
            "\1\u00dd\1\u00c5\4\uffff\1\u0130\5\uffff\1\u00c6",
            "\1\123\1\uffff\1\u0131\3\uffff\1\u0093\1\uffff\1\u0094\67\uffff"+
            "\2\115\2\120\2\uffff\2\116",
            "\1\123\1\uffff\1\u0132\1\u00af\1\u00b0\1\u00b1\72\uffff\2\115"+
            "\2\120\2\uffff\2\116",
            "\1\123\1\uffff\1\u0133\4\uffff\1\u00e6\70\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\123\6\uffff\1\u00e6\70\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u0134\1\u010f\1\u0110\1\u0111",
            "\1\u0135\2\uffff\1\u010f\1\u0110\1\u0111",
            "\1\u0137\1\uffff\1\u0136\4\uffff\1\u00e6\70\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\u0123",
            "\1\u0123",
            "\1\u0123",
            "\1\u0123",
            "\1\123\5\uffff\1\74\1\uffff\1\75\67\uffff\2\115\2\120\2\uffff"+
            "\2\116",
            "\1\160\1\134\12\uffff\1\135",
            "\1\160\1\134\1\u0138\11\uffff\1\135",
            "\1\u0139",
            "\1\u013a",
            "\1\u013b",
            "\1\u00dd\1\u00c5\1\u013c\11\uffff\1\u00c6",
            "\1\u00dd\1\u00c5\1\u013d\11\uffff\1\u00c6",
            "\1\u00dd\1\u00c5\12\uffff\1\u00c6",
            "\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\u0141",
            "\1\u0142",
            "\1\u0143",
            "\1\123\5\uffff\1\u0144\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u0145",
            "\1\123\6\uffff\1\167\70\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u0146\1\u00a6\1\u00a7\1\u00a8",
            "\1\123\1\uffff\1\u0147\4\uffff\1\167\70\uffff\2\115\2\120\2"+
            "\uffff\2\116",
            "\1\u0148",
            "\1\u0149",
            "\1\123\1\uffff\1\u014a\3\uffff\1\u0093\1\uffff\1\u0094\67\uffff"+
            "\2\115\2\120\2\uffff\2\116",
            "\1\123\1\uffff\1\u014b\1\u00af\1\u00b0\1\u00b1\72\uffff\2\115"+
            "\2\120\2\uffff\2\116",
            "\1\123\1\uffff\1\u014c\4\uffff\1\u00e6\70\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\u014d\1\u010f\1\u0110\1\u0111",
            "\1\u010f\1\u0110\1\u0111",
            "\1\123\1\uffff\1\u014e\4\uffff\1\u00e6\70\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\u014f\6\uffff\1\u00e6\70\uffff\2\115\2\120\2\uffff\2\116",
            "\1\160\1\134\12\uffff\1\135",
            "\1\u0150",
            "\1\u0151",
            "\1\u00dd\1\u00c5\1\u0152\11\uffff\1\u00c6",
            "\1\u00dd\1\u00c5\1\u0153\11\uffff\1\u00c6",
            "\1\u0154",
            "\1\u0155",
            "\1\u0156",
            "\1\u0157",
            "\1\u0158",
            "\1\123\5\uffff\1\u0159\71\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u00a6\1\u00a7\1\u00a8",
            "\1\123\1\uffff\1\u015a\4\uffff\1\167\70\uffff\2\115\2\120\2"+
            "\uffff\2\116",
            "\1\u015b",
            "\1\u015c",
            "\1\123\5\uffff\1\u0093\1\uffff\1\u0094\67\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\123\2\uffff\1\u00af\1\u00b0\1\u00b1\72\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\123\1\uffff\1\u015d\4\uffff\1\u00e6\70\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\u015e\1\u010f\1\u0110\1\u0111",
            "\1\123\1\uffff\1\u015f\4\uffff\1\u00e6\70\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\123\6\uffff\1\u00e6\70\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u0160",
            "\1\u00dd\1\u00c5\12\uffff\1\u00c6",
            "\1\u00dd\1\u00c5\1\u0161\11\uffff\1\u00c6",
            "\1\u0162",
            "\1\u0163",
            "\1\u0164",
            "\1\123\6\uffff\1\167\70\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u0165",
            "\1\123\6\uffff\1\u00e6\70\uffff\2\115\2\120\2\uffff\2\116",
            "\1\u0166\1\u010f\1\u0110\1\u0111",
            "\1\123\1\uffff\1\u0167\4\uffff\1\u00e6\70\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\u00dd\1\u00c5\12\uffff\1\u00c6",
            "\1\u0168",
            "\1\u0169",
            "\1\u010f\1\u0110\1\u0111",
            "\1\123\1\uffff\1\u016a\4\uffff\1\u00e6\70\uffff\2\115\2\120"+
            "\2\uffff\2\116",
            "\1\u016b",
            "\1\123\6\uffff\1\u00e6\70\uffff\2\115\2\120\2\uffff\2\116"
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
        "\1\1\3\uffff";
    static final String DFA5_minS =
        "\1\4\1\uffff\1\4\1\uffff";
    static final String DFA5_maxS =
        "\1\113\1\uffff\1\113\1\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\2\1\uffff\1\1";
    static final String DFA5_specialS =
        "\4\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\2\77\uffff\2\3\2\1\2\uffff\2\3",
            "",
            "\1\2\77\uffff\2\3\2\1\2\uffff\2\3",
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
        "\1\124\1\121\2\17\1\77\1\121\3\uffff\1\17\1\36\1\17\1\36\2\121\1"+
        "\36\1\20\1\36\1\6\1\20\1\121\1\67\2\20\1\121";
    static final String DFA6_acceptS =
        "\6\uffff\1\2\1\1\1\3\20\uffff";
    static final String DFA6_specialS =
        "\31\uffff}>";
    static final String[] DFA6_transitionS = {
            "\14\1\16\uffff\1\2\44\uffff\1\2\2\3",
            "\1\5\1\uffff\1\4\3\7\102\uffff\2\6\2\uffff\2\10",
            "\1\11\12\uffff\1\12",
            "\1\13\12\uffff\1\14",
            "\2\16\11\uffff\31\16\10\15",
            "\1\5\2\uffff\3\7\102\uffff\2\6\2\uffff\2\10",
            "",
            "",
            "",
            "\1\11\12\uffff\1\12",
            "\1\17\16\uffff\14\20",
            "\1\13\12\uffff\1\14",
            "\1\21\16\uffff\14\22",
            "\1\5\2\uffff\3\7\102\uffff\2\6\2\uffff\2\10",
            "\1\5\2\uffff\3\7\102\uffff\2\6\2\uffff\2\10",
            "\1\17\16\uffff\14\20",
            "\1\23\13\uffff\1\24",
            "\1\21\16\uffff\14\22",
            "\1\25",
            "\1\23\13\uffff\1\24",
            "\1\5\2\uffff\3\7\102\uffff\2\6\2\uffff\2\10",
            "\2\26\11\uffff\31\26",
            "\1\27\13\uffff\1\30",
            "\1\27\13\uffff\1\30",
            "\1\5\2\uffff\3\7\102\uffff\2\6\2\uffff\2\10"
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
    public static final BitSet FOLLOW_constraintList_in_stmt39 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt46 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt48 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_selectList_in_stmt50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt55 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt57 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_selectList_in_stmt59 = new BitSet(new long[]{0x0000000000000010L,0x00000000000000C0L});
    public static final BitSet FOLLOW_spaces_in_stmt61 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_order_in_stmt63 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000300L});
    public static final BitSet FOLLOW_spaces_in_stmt65 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_by_in_stmt67 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt69 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_keyword_in_stmt74 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt80 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt85 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt87 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_selectList_in_stmt89 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt91 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_where_in_stmt93 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt95 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_constraintList_in_stmt97 = new BitSet(new long[]{0x0000000000000010L,0x00000000000000C0L});
    public static final BitSet FOLLOW_spaces_in_stmt99 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_order_in_stmt101 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000300L});
    public static final BitSet FOLLOW_spaces_in_stmt103 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_by_in_stmt105 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_stmt107 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_keyword_in_stmt112 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_spaces_in_stmt118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SPACE_in_spaces128 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_keyword_in_selectList143 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_selectList156 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_selectList160 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_selectList164 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_keyword_in_selectList171 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_entity_in_keyword196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword202 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword204 = new BitSet(new long[]{0x00FFFFFF80300000L});
    public static final BitSet FOLLOW_attr_in_keyword206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword211 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword213 = new BitSet(new long[]{0xFF00000000000000L});
    public static final BitSet FOLLOW_funct_in_keyword215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_count_in_keyword220 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword222 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword224 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_keyword226 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_entity_in_keyword228 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword230 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sum_in_keyword237 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword239 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword241 = new BitSet(new long[]{0x000000007FF80010L});
    public static final BitSet FOLLOW_spaces_in_keyword243 = new BitSet(new long[]{0x000000007FF80000L});
    public static final BitSet FOLLOW_entity_in_keyword245 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword247 = new BitSet(new long[]{0x00FFFFFF80300000L});
    public static final BitSet FOLLOW_attr_in_keyword249 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword251 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_constraintList262 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000C30L});
    public static final BitSet FOLLOW_spaces_in_constraintList266 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000C30L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList273 = new BitSet(new long[]{0x000020007FF80010L,0x00000000001C0000L});
    public static final BitSet FOLLOW_spaces_in_constraintList281 = new BitSet(new long[]{0x000020007FF80000L,0x00000000001C0000L});
    public static final BitSet FOLLOW_constraint_in_constraintList283 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000C30L});
    public static final BitSet FOLLOW_keyword_in_constraint296 = new BitSet(new long[]{0x0000000000000390L});
    public static final BitSet FOLLOW_spaces_in_constraint305 = new BitSet(new long[]{0x0000000000000380L});
    public static final BitSet FOLLOW_set_in_constraint312 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_spaces_in_constraint332 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_genValue_in_constraint339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint368 = new BitSet(new long[]{0x0000000000000010L,0x0000000000003000L});
    public static final BitSet FOLLOW_spaces_in_constraint377 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003000L});
    public static final BitSet FOLLOW_in_in_constraint384 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_constraint395 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_constraint397 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_spaces_in_constraint401 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_valueList_in_constraint407 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_constraint415 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_constraint419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint445 = new BitSet(new long[]{0x0000000000000010L,0x0000000000030000L});
    public static final BitSet FOLLOW_spaces_in_constraint454 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_like_in_constraint461 = new BitSet(new long[]{0x0000000000001410L});
    public static final BitSet FOLLOW_spaces_in_constraint470 = new BitSet(new long[]{0x0000000000001400L});
    public static final BitSet FOLLOW_likeValue_in_constraint477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue533 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue535 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue543 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue545 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue547 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue549 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue557 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue559 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue561 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue563 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue565 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue567 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue575 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue577 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue579 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue581 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue583 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue585 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue587 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue589 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue597 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue599 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue601 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue603 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue605 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue607 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue609 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue611 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue613 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue615 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue623 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue625 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue633 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue635 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue637 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue639 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_VALUE_in_dotValue641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_valueList649 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_valueList653 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_valueList655 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_spaces_in_valueList657 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dotValue_in_valueList659 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_EQ_in_compOpt670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt691 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_GT_in_compOpt694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt701 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_LT_in_compOpt704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt711 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EQ_in_compOpt714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt721 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EQ_in_compOpt724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue736 = new BitSet(new long[]{0x0000000000000380L});
    public static final BitSet FOLLOW_compOpt_in_genValue738 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dotValue_in_genValue740 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_AMP_in_genValue743 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dotValue_in_genValue745 = new BitSet(new long[]{0x0000000000000380L});
    public static final BitSet FOLLOW_compOpt_in_genValue747 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dotValue_in_genValue749 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_dotValue_in_likeValue759 = new BitSet(new long[]{0x0000000000001402L});
    public static final BitSet FOLLOW_STAR_in_likeValue762 = new BitSet(new long[]{0x0000000000001402L});
    public static final BitSet FOLLOW_and_in_logicalOp771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and1008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_order1021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_by1034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or1047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in1060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not1073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_like1086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_count1099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sum1112 = new BitSet(new long[]{0x0000000000000002L});

}