package dbs.search.parser;
// $ANTLR 3.1.2 Sql.g 2009-06-25 14:11:32


import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMA", "DOT", "LB", "RB", "EQ", "LT", "GT", "NOT", "VALUE", "WS", "'ads'", "'config'", "'dataset'", "'release'", "'tier'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'lumi'", "'dq'", "'ilumi'", "'phygrp'", "'group'", "'pset'", "'algo'", "'datatype'", "'mcdesc'", "'trigdesc'", "'branch'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numfiles'", "'numlss'", "'totlumi'", "'store'", "'size'", "'count'", "'status'", "'type'", "'id'", "'parent'", "'child'", "'def'", "'evnum'", "'era'", "'tag'", "'xsection'", "'hash'", "'content'", "'family'", "'exe'", "'annotation'", "'checksum'", "'sum'", "'max'", "'min'", "'avg'", "'COUNT'", "'SUM'", "'MAX'", "'MIN'", "'AVG'", "'select'", "'SELECT'", "'find'", "'FIND'", "'and'", "'AND'", "'order'", "'ORDER'", "'by'", "'BY'", "'or'", "'OR'", "'in'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'", "'asc'", "'ASC'", "'desc'", "'DESC'", "'between'", "'BETWEEN'", "'where'", "'WHERE'"
    };
    public static final int LT=9;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int NOT=11;
    public static final int EOF=-1;
    public static final int T__93=93;
    public static final int T__19=19;
    public static final int T__94=94;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__90=90;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__14=14;
    public static final int EQ=8;
    public static final int T__99=99;
    public static final int T__98=98;
    public static final int T__97=97;
    public static final int T__96=96;
    public static final int T__95=95;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int VALUE=12;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__89=89;
    public static final int T__88=88;
    public static final int WS=13;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int GT=10;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int T__73=73;
    public static final int T__79=79;
    public static final int T__78=78;
    public static final int T__77=77;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__62=62;
    public static final int RB=7;
    public static final int T__63=63;
    public static final int T__61=61;
    public static final int T__60=60;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int COMMA=4;
    public static final int T__103=103;
    public static final int T__59=59;
    public static final int T__104=104;
    public static final int DOT=5;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__102=102;
    public static final int T__101=101;
    public static final int T__100=100;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int LB=6;

    // delegates
    // delegators


        public SqlParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public SqlParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return SqlParser.tokenNames; }
    public String getGrammarFileName() { return "Sql.g"; }


    ArrayList kws = new ArrayList();
    ArrayList okws = new ArrayList();
    ArrayList constraints = new ArrayList();
    String orderingkw = "";



    // $ANTLR start "stmt"
    // Sql.g:21:1: stmt : select selectList where constraintList ( order by orderList )? ;
    public final void stmt() throws RecognitionException {
        try {
            // Sql.g:21:6: ( select selectList where constraintList ( order by orderList )? )
            // Sql.g:21:8: select selectList where constraintList ( order by orderList )?
            {
            pushFollow(FOLLOW_select_in_stmt27);
            select();

            state._fsp--;

            pushFollow(FOLLOW_selectList_in_stmt29);
            selectList();

            state._fsp--;

            pushFollow(FOLLOW_where_in_stmt31);
            where();

            state._fsp--;

            pushFollow(FOLLOW_constraintList_in_stmt33);
            constraintList();

            state._fsp--;

            // Sql.g:21:47: ( order by orderList )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( ((LA1_0>=85 && LA1_0<=86)) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // Sql.g:21:48: order by orderList
                    {
                    pushFollow(FOLLOW_order_in_stmt36);
                    order();

                    state._fsp--;

                    pushFollow(FOLLOW_by_in_stmt38);
                    by();

                    state._fsp--;

                    pushFollow(FOLLOW_orderList_in_stmt40);
                    orderList();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "stmt"


    // $ANTLR start "orderList"
    // Sql.g:25:1: orderList : ( orderList1 | orderList1 oing= ordering );
    public final void orderList() throws RecognitionException {
        SqlParser.ordering_return oing = null;


        try {
            // Sql.g:25:11: ( orderList1 | orderList1 oing= ordering )
            int alt2=2;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // Sql.g:25:12: orderList1
                    {
                    pushFollow(FOLLOW_orderList1_in_orderList51);
                    orderList1();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:26:5: orderList1 oing= ordering
                    {
                    pushFollow(FOLLOW_orderList1_in_orderList57);
                    orderList1();

                    state._fsp--;

                    pushFollow(FOLLOW_ordering_in_orderList66);
                    oing=ordering();

                    state._fsp--;

                    orderingkw=(oing!=null?input.toString(oing.start,oing.stop):null);

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
    // $ANTLR end "orderList"


    // $ANTLR start "orderList1"
    // Sql.g:28:1: orderList1 : okw= simpleKw ( COMMA okw= simpleKw )* ;
    public final void orderList1() throws RecognitionException {
        SqlParser.simpleKw_return okw = null;


        try {
            // Sql.g:28:12: (okw= simpleKw ( COMMA okw= simpleKw )* )
            // Sql.g:28:13: okw= simpleKw ( COMMA okw= simpleKw )*
            {
            pushFollow(FOLLOW_simpleKw_in_orderList177);
            okw=simpleKw();

            state._fsp--;

            okws.add((okw!=null?input.toString(okw.start,okw.stop):null));
            // Sql.g:29:4: ( COMMA okw= simpleKw )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==COMMA) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Sql.g:30:3: COMMA okw= simpleKw
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_orderList190); 
            	    pushFollow(FOLLOW_simpleKw_in_orderList197);
            	    okw=simpleKw();

            	    state._fsp--;

            	    okws.add((okw!=null?input.toString(okw.start,okw.stop):null));

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
    // $ANTLR end "orderList1"

    public static class ordering_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "ordering"
    // Sql.g:35:1: ordering : ( asc | desc );
    public final SqlParser.ordering_return ordering() throws RecognitionException {
        SqlParser.ordering_return retval = new SqlParser.ordering_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:35:11: ( asc | desc )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=97 && LA4_0<=98)) ) {
                alt4=1;
            }
            else if ( ((LA4_0>=99 && LA4_0<=100)) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // Sql.g:35:13: asc
                    {
                    pushFollow(FOLLOW_asc_in_ordering121);
                    asc();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:35:17: desc
                    {
                    pushFollow(FOLLOW_desc_in_ordering123);
                    desc();

                    state._fsp--;


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
    // $ANTLR end "ordering"


    // $ANTLR start "selectList"
    // Sql.g:36:1: selectList : kw= slkeyword ( COMMA kw= slkeyword )* ;
    public final void selectList() throws RecognitionException {
        SqlParser.slkeyword_return kw = null;


        try {
            // Sql.g:36:12: (kw= slkeyword ( COMMA kw= slkeyword )* )
            // Sql.g:36:13: kw= slkeyword ( COMMA kw= slkeyword )*
            {
            pushFollow(FOLLOW_slkeyword_in_selectList134);
            kw=slkeyword();

            state._fsp--;

            kws.add((kw!=null?input.toString(kw.start,kw.stop):null));
            // Sql.g:37:4: ( COMMA kw= slkeyword )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==COMMA) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Sql.g:38:3: COMMA kw= slkeyword
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectList147); 
            	    pushFollow(FOLLOW_slkeyword_in_selectList155);
            	    kw=slkeyword();

            	    state._fsp--;

            	    kws.add((kw!=null?input.toString(kw.start,kw.stop):null));

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
    // $ANTLR end "selectList"

    public static class simpleKw_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "simpleKw"
    // Sql.g:42:1: simpleKw : ( entity | entity DOT attr );
    public final SqlParser.simpleKw_return simpleKw() throws RecognitionException {
        SqlParser.simpleKw_return retval = new SqlParser.simpleKw_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:42:10: ( entity | entity DOT attr )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( ((LA6_0>=14 && LA6_0<=35)) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==EOF||LA6_1==COMMA||(LA6_1>=RB && LA6_1<=NOT)||(LA6_1>=91 && LA6_1<=104)) ) {
                    alt6=1;
                }
                else if ( (LA6_1==DOT) ) {
                    alt6=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // Sql.g:42:12: entity
                    {
                    pushFollow(FOLLOW_entity_in_simpleKw180);
                    entity();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:43:5: entity DOT attr
                    {
                    pushFollow(FOLLOW_entity_in_simpleKw186);
                    entity();

                    state._fsp--;

                    match(input,DOT,FOLLOW_DOT_in_simpleKw188); 
                    pushFollow(FOLLOW_attr_in_simpleKw190);
                    attr();

                    state._fsp--;


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
    // $ANTLR end "simpleKw"

    public static class slkeyword_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "slkeyword"
    // Sql.g:44:1: slkeyword : ( simpleKw | funct LB simpleKw RB );
    public final SqlParser.slkeyword_return slkeyword() throws RecognitionException {
        SqlParser.slkeyword_return retval = new SqlParser.slkeyword_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:44:11: ( simpleKw | funct LB simpleKw RB )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=14 && LA7_0<=35)) ) {
                alt7=1;
            }
            else if ( (LA7_0==53||(LA7_0>=70 && LA7_0<=78)) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // Sql.g:44:13: simpleKw
                    {
                    pushFollow(FOLLOW_simpleKw_in_slkeyword197);
                    simpleKw();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:45:5: funct LB simpleKw RB
                    {
                    pushFollow(FOLLOW_funct_in_slkeyword203);
                    funct();

                    state._fsp--;

                    match(input,LB,FOLLOW_LB_in_slkeyword205); 
                    pushFollow(FOLLOW_simpleKw_in_slkeyword207);
                    simpleKw();

                    state._fsp--;

                    match(input,RB,FOLLOW_RB_in_slkeyword209); 

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
    // $ANTLR end "slkeyword"

    public static class lopen_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "lopen"
    // Sql.g:50:1: lopen : ( LB )* ;
    public final SqlParser.lopen_return lopen() throws RecognitionException {
        SqlParser.lopen_return retval = new SqlParser.lopen_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:50:8: ( ( LB )* )
            // Sql.g:50:10: ( LB )*
            {
            // Sql.g:50:10: ( LB )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==LB) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // Sql.g:50:11: LB
            	    {
            	    match(input,LB,FOLLOW_LB_in_lopen222); 

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
    // $ANTLR end "lopen"

    public static class ropen_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "ropen"
    // Sql.g:51:1: ropen : ( RB )* ;
    public final SqlParser.ropen_return ropen() throws RecognitionException {
        SqlParser.ropen_return retval = new SqlParser.ropen_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:51:8: ( ( RB )* )
            // Sql.g:51:10: ( RB )*
            {
            // Sql.g:51:10: ( RB )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RB) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Sql.g:51:11: RB
            	    {
            	    match(input,RB,FOLLOW_RB_in_ropen233); 

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
    // $ANTLR end "ropen"


    // $ANTLR start "constraintList"
    // Sql.g:55:1: constraintList : constraint1 (rel= logicalOp constraint1 )* ;
    public final void constraintList() throws RecognitionException {
        SqlParser.logicalOp_return rel = null;


        try {
            // Sql.g:55:16: ( constraint1 (rel= logicalOp constraint1 )* )
            // Sql.g:55:17: constraint1 (rel= logicalOp constraint1 )*
            {
            pushFollow(FOLLOW_constraint1_in_constraintList244);
            constraint1();

            state._fsp--;

            // Sql.g:56:3: (rel= logicalOp constraint1 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=83 && LA10_0<=84)||(LA10_0>=89 && LA10_0<=90)) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // Sql.g:57:3: rel= logicalOp constraint1
            	    {
            	    pushFollow(FOLLOW_logicalOp_in_constraintList258);
            	    rel=logicalOp();

            	    state._fsp--;

            	     constraints.add((rel!=null?input.toString(rel.start,rel.stop):null));
            	    pushFollow(FOLLOW_constraint1_in_constraintList265);
            	    constraint1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
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
    // $ANTLR end "constraintList"


    // $ANTLR start "constraint1"
    // Sql.g:59:1: constraint1 : kl= lopen constraint kr= ropen ;
    public final void constraint1() throws RecognitionException {
        SqlParser.lopen_return kl = null;

        SqlParser.ropen_return kr = null;


        try {
            // Sql.g:59:13: (kl= lopen constraint kr= ropen )
            // Sql.g:60:3: kl= lopen constraint kr= ropen
            {
            pushFollow(FOLLOW_lopen_in_constraint1281);
            kl=lopen();

            state._fsp--;

            Constraint c1=new Constraint();c1.setBracket((kl!=null?input.toString(kl.start,kl.stop):null));constraints.add(c1);
            pushFollow(FOLLOW_constraint_in_constraint1287);
            constraint();

            state._fsp--;

            pushFollow(FOLLOW_ropen_in_constraint1298);
            kr=ropen();

            state._fsp--;

            Constraint c=new Constraint();c.setBracket((kr!=null?input.toString(kr.start,kr.stop):null)); constraints.add(c);

            }

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "constraint1"


    // $ANTLR start "constraint"
    // Sql.g:75:1: constraint : (kw= simpleKw op= compOpt val= genValue | kw= simpleKw op1= in LB val1= inValue RB | kw= simpleKw op2= genLike val2= genValue | kw= simpleKw op3= between val3= betValue );
    public final void constraint() throws RecognitionException {
        SqlParser.simpleKw_return kw = null;

        SqlParser.compOpt_return op = null;

        SqlParser.genValue_return val = null;

        SqlParser.in_return op1 = null;

        SqlParser.inValue_return val1 = null;

        SqlParser.genLike_return op2 = null;

        SqlParser.genValue_return val2 = null;

        SqlParser.between_return op3 = null;

        SqlParser.betValue_return val3 = null;


        try {
            // Sql.g:75:12: (kw= simpleKw op= compOpt val= genValue | kw= simpleKw op1= in LB val1= inValue RB | kw= simpleKw op2= genLike val2= genValue | kw= simpleKw op3= between val3= betValue )
            int alt11=4;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=14 && LA11_0<=35)) ) {
                switch ( input.LA(2) ) {
                case EQ:
                case LT:
                case GT:
                case NOT:
                    {
                    alt11=1;
                    }
                    break;
                case 91:
                case 92:
                    {
                    alt11=2;
                    }
                    break;
                case 101:
                case 102:
                    {
                    alt11=4;
                    }
                    break;
                case DOT:
                    {
                    int LA11_5 = input.LA(3);

                    if ( ((LA11_5>=16 && LA11_5<=18)||(LA11_5>=36 && LA11_5<=69)) ) {
                        switch ( input.LA(4) ) {
                        case EQ:
                        case LT:
                        case GT:
                        case NOT:
                            {
                            alt11=1;
                            }
                            break;
                        case 93:
                        case 94:
                        case 95:
                        case 96:
                            {
                            alt11=3;
                            }
                            break;
                        case 101:
                        case 102:
                            {
                            alt11=4;
                            }
                            break;
                        case 91:
                        case 92:
                            {
                            alt11=2;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 11, 7, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 5, input);

                        throw nvae;
                    }
                    }
                    break;
                case 93:
                case 94:
                case 95:
                case 96:
                    {
                    alt11=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // Sql.g:76:2: kw= simpleKw op= compOpt val= genValue
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint315);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_compOpt_in_constraint326);
                    op=compOpt();

                    state._fsp--;

                    c.setOp((op!=null?input.toString(op.start,op.stop):null));
                    pushFollow(FOLLOW_genValue_in_constraint338);
                    val=genValue();

                    state._fsp--;

                    c.setValue((val!=null?input.toString(val.start,val.stop):null)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:80:2: kw= simpleKw op1= in LB val1= inValue RB
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint366);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_in_in_constraint377);
                    op1=in();

                    state._fsp--;

                    c.setOp((op1!=null?input.toString(op1.start,op1.stop):null));
                    match(input,LB,FOLLOW_LB_in_constraint388); 
                    pushFollow(FOLLOW_inValue_in_constraint394);
                    val1=inValue();

                    state._fsp--;

                    c.setValue((val1!=null?input.toString(val1.start,val1.stop):null)); constraints.add(c);
                    match(input,RB,FOLLOW_RB_in_constraint402); 

                    }
                    break;
                case 3 :
                    // Sql.g:86:2: kw= simpleKw op2= genLike val2= genValue
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint428);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_genLike_in_constraint439);
                    op2=genLike();

                    state._fsp--;

                    c.setOp((op2!=null?input.toString(op2.start,op2.stop):null));
                    pushFollow(FOLLOW_genValue_in_constraint450);
                    val2=genValue();

                    state._fsp--;

                    c.setValue((val2!=null?input.toString(val2.start,val2.stop):null)); constraints.add(c);

                    }
                    break;
                case 4 :
                    // Sql.g:90:3: kw= simpleKw op3= between val3= betValue
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint465);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_between_in_constraint476);
                    op3=between();

                    state._fsp--;

                    c.setOp((op3!=null?input.toString(op3.start,op3.stop):null));
                    pushFollow(FOLLOW_betValue_in_constraint486);
                    val3=betValue();

                    state._fsp--;

                    c.setValue((val3!=null?input.toString(val3.start,val3.stop):null)); constraints.add(c);

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
    // $ANTLR end "constraint"

    public static class compOpt_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "compOpt"
    // Sql.g:95:1: compOpt : ( EQ | LT | GT | NOT EQ | EQ GT | EQ LT | LT EQ | GT EQ | LT GT );
    public final SqlParser.compOpt_return compOpt() throws RecognitionException {
        SqlParser.compOpt_return retval = new SqlParser.compOpt_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:95:10: ( EQ | LT | GT | NOT EQ | EQ GT | EQ LT | LT EQ | GT EQ | LT GT )
            int alt12=9;
            alt12 = dfa12.predict(input);
            switch (alt12) {
                case 1 :
                    // Sql.g:95:11: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt517); 

                    }
                    break;
                case 2 :
                    // Sql.g:96:4: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt522); 

                    }
                    break;
                case 3 :
                    // Sql.g:97:4: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt527); 

                    }
                    break;
                case 4 :
                    // Sql.g:98:4: NOT EQ
                    {
                    match(input,NOT,FOLLOW_NOT_in_compOpt532); 
                    match(input,EQ,FOLLOW_EQ_in_compOpt534); 

                    }
                    break;
                case 5 :
                    // Sql.g:99:4: EQ GT
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt539); 
                    match(input,GT,FOLLOW_GT_in_compOpt541); 

                    }
                    break;
                case 6 :
                    // Sql.g:100:4: EQ LT
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt546); 
                    match(input,LT,FOLLOW_LT_in_compOpt548); 

                    }
                    break;
                case 7 :
                    // Sql.g:101:4: LT EQ
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt553); 
                    match(input,EQ,FOLLOW_EQ_in_compOpt555); 

                    }
                    break;
                case 8 :
                    // Sql.g:102:4: GT EQ
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt560); 
                    match(input,EQ,FOLLOW_EQ_in_compOpt562); 

                    }
                    break;
                case 9 :
                    // Sql.g:103:4: LT GT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt567); 
                    match(input,GT,FOLLOW_GT_in_compOpt569); 

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
    // $ANTLR end "compOpt"

    public static class genValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "genValue"
    // Sql.g:104:1: genValue : VALUE ( VALUE )* ( DOT VALUE )* ( compOpt VALUE )* ;
    public final SqlParser.genValue_return genValue() throws RecognitionException {
        SqlParser.genValue_return retval = new SqlParser.genValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:104:10: ( VALUE ( VALUE )* ( DOT VALUE )* ( compOpt VALUE )* )
            // Sql.g:104:12: VALUE ( VALUE )* ( DOT VALUE )* ( compOpt VALUE )*
            {
            match(input,VALUE,FOLLOW_VALUE_in_genValue576); 
            // Sql.g:104:18: ( VALUE )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==VALUE) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // Sql.g:104:19: VALUE
            	    {
            	    match(input,VALUE,FOLLOW_VALUE_in_genValue579); 

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            // Sql.g:104:27: ( DOT VALUE )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==DOT) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // Sql.g:104:28: DOT VALUE
            	    {
            	    match(input,DOT,FOLLOW_DOT_in_genValue584); 
            	    match(input,VALUE,FOLLOW_VALUE_in_genValue586); 

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            // Sql.g:104:40: ( compOpt VALUE )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=EQ && LA15_0<=NOT)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // Sql.g:104:41: compOpt VALUE
            	    {
            	    pushFollow(FOLLOW_compOpt_in_genValue591);
            	    compOpt();

            	    state._fsp--;

            	    match(input,VALUE,FOLLOW_VALUE_in_genValue593); 

            	    }
            	    break;

            	default :
            	    break loop15;
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
    // $ANTLR end "genValue"

    public static class betValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "betValue"
    // Sql.g:107:1: betValue : genValue and genValue ;
    public final SqlParser.betValue_return betValue() throws RecognitionException {
        SqlParser.betValue_return retval = new SqlParser.betValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:107:10: ( genValue and genValue )
            // Sql.g:107:11: genValue and genValue
            {
            pushFollow(FOLLOW_genValue_in_betValue604);
            genValue();

            state._fsp--;

            pushFollow(FOLLOW_and_in_betValue606);
            and();

            state._fsp--;

            pushFollow(FOLLOW_genValue_in_betValue608);
            genValue();

            state._fsp--;


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
    // $ANTLR end "betValue"

    public static class inValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "inValue"
    // Sql.g:108:1: inValue : genValue ( COMMA genValue )* ;
    public final SqlParser.inValue_return inValue() throws RecognitionException {
        SqlParser.inValue_return retval = new SqlParser.inValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:108:10: ( genValue ( COMMA genValue )* )
            // Sql.g:108:11: genValue ( COMMA genValue )*
            {
            pushFollow(FOLLOW_genValue_in_inValue615);
            genValue();

            state._fsp--;

            // Sql.g:108:20: ( COMMA genValue )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==COMMA) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // Sql.g:108:22: COMMA genValue
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_inValue619); 
            	    pushFollow(FOLLOW_genValue_in_inValue621);
            	    genValue();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
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
    // $ANTLR end "inValue"

    public static class logicalOp_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "logicalOp"
    // Sql.g:109:1: logicalOp : ( and | or );
    public final SqlParser.logicalOp_return logicalOp() throws RecognitionException {
        SqlParser.logicalOp_return retval = new SqlParser.logicalOp_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:109:11: ( and | or )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>=83 && LA17_0<=84)) ) {
                alt17=1;
            }
            else if ( ((LA17_0>=89 && LA17_0<=90)) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // Sql.g:109:12: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp630);
                    and();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:109:16: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp632);
                    or();

                    state._fsp--;


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
    // $ANTLR end "logicalOp"


    // $ANTLR start "entity"
    // Sql.g:111:1: entity : ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch' );
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:111:9: ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch' )
            // Sql.g:
            {
            if ( (input.LA(1)>=14 && input.LA(1)<=35) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "entity"


    // $ANTLR start "attr"
    // Sql.g:112:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' | 'checksum' );
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:112:7: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' | 'checksum' )
            // Sql.g:
            {
            if ( (input.LA(1)>=16 && input.LA(1)<=18)||(input.LA(1)>=36 && input.LA(1)<=69) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "attr"


    // $ANTLR start "funct"
    // Sql.g:113:1: funct : ( 'count' | 'sum' | 'max' | 'min' | 'avg' | 'COUNT' | 'SUM' | 'MAX' | 'MIN' | 'AVG' );
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:113:8: ( 'count' | 'sum' | 'max' | 'min' | 'avg' | 'COUNT' | 'SUM' | 'MAX' | 'MIN' | 'AVG' )
            // Sql.g:
            {
            if ( input.LA(1)==53||(input.LA(1)>=70 && input.LA(1)<=78) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "funct"


    // $ANTLR start "select"
    // Sql.g:114:1: select : ( 'select' | 'SELECT' | 'find' | 'FIND' );
    public final void select() throws RecognitionException {
        try {
            // Sql.g:114:9: ( 'select' | 'SELECT' | 'find' | 'FIND' )
            // Sql.g:
            {
            if ( (input.LA(1)>=79 && input.LA(1)<=82) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "select"


    // $ANTLR start "and"
    // Sql.g:115:1: and : ( 'and' | 'AND' );
    public final void and() throws RecognitionException {
        try {
            // Sql.g:115:6: ( 'and' | 'AND' )
            // Sql.g:
            {
            if ( (input.LA(1)>=83 && input.LA(1)<=84) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "and"


    // $ANTLR start "order"
    // Sql.g:116:1: order : ( 'order' | 'ORDER' );
    public final void order() throws RecognitionException {
        try {
            // Sql.g:116:8: ( 'order' | 'ORDER' )
            // Sql.g:
            {
            if ( (input.LA(1)>=85 && input.LA(1)<=86) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "order"


    // $ANTLR start "by"
    // Sql.g:117:1: by : ( 'by' | 'BY' );
    public final void by() throws RecognitionException {
        try {
            // Sql.g:117:5: ( 'by' | 'BY' )
            // Sql.g:
            {
            if ( (input.LA(1)>=87 && input.LA(1)<=88) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "by"


    // $ANTLR start "or"
    // Sql.g:118:1: or : ( 'or' | 'OR' );
    public final void or() throws RecognitionException {
        try {
            // Sql.g:118:5: ( 'or' | 'OR' )
            // Sql.g:
            {
            if ( (input.LA(1)>=89 && input.LA(1)<=90) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "or"

    public static class in_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "in"
    // Sql.g:119:1: in : ( 'in' | 'IN' );
    public final SqlParser.in_return in() throws RecognitionException {
        SqlParser.in_return retval = new SqlParser.in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:119:5: ( 'in' | 'IN' )
            // Sql.g:
            {
            if ( (input.LA(1)>=91 && input.LA(1)<=92) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "in"


    // $ANTLR start "not"
    // Sql.g:120:1: not : ( 'not' | 'NOT' );
    public final void not() throws RecognitionException {
        try {
            // Sql.g:120:6: ( 'not' | 'NOT' )
            // Sql.g:
            {
            if ( (input.LA(1)>=93 && input.LA(1)<=94) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "not"


    // $ANTLR start "like"
    // Sql.g:121:1: like : ( 'like' | 'LIKE' );
    public final void like() throws RecognitionException {
        try {
            // Sql.g:121:7: ( 'like' | 'LIKE' )
            // Sql.g:
            {
            if ( (input.LA(1)>=95 && input.LA(1)<=96) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "like"


    // $ANTLR start "notLike"
    // Sql.g:122:1: notLike : not like ;
    public final void notLike() throws RecognitionException {
        try {
            // Sql.g:122:10: ( not like )
            // Sql.g:122:12: not like
            {
            pushFollow(FOLLOW_not_in_notLike1020);
            not();

            state._fsp--;

            pushFollow(FOLLOW_like_in_notLike1022);
            like();

            state._fsp--;


            }

        }

        catch (RecognitionException e) {
        	throw e;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "notLike"

    public static class genLike_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "genLike"
    // Sql.g:123:1: genLike : ( like | notLike );
    public final SqlParser.genLike_return genLike() throws RecognitionException {
        SqlParser.genLike_return retval = new SqlParser.genLike_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:123:10: ( like | notLike )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=95 && LA18_0<=96)) ) {
                alt18=1;
            }
            else if ( ((LA18_0>=93 && LA18_0<=94)) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // Sql.g:123:12: like
                    {
                    pushFollow(FOLLOW_like_in_genLike1030);
                    like();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:123:19: notLike
                    {
                    pushFollow(FOLLOW_notLike_in_genLike1034);
                    notLike();

                    state._fsp--;


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
    // $ANTLR end "genLike"


    // $ANTLR start "asc"
    // Sql.g:124:1: asc : ( 'asc' | 'ASC' );
    public final void asc() throws RecognitionException {
        try {
            // Sql.g:124:6: ( 'asc' | 'ASC' )
            // Sql.g:
            {
            if ( (input.LA(1)>=97 && input.LA(1)<=98) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "asc"


    // $ANTLR start "desc"
    // Sql.g:125:1: desc : ( 'desc' | 'DESC' );
    public final void desc() throws RecognitionException {
        try {
            // Sql.g:125:7: ( 'desc' | 'DESC' )
            // Sql.g:
            {
            if ( (input.LA(1)>=99 && input.LA(1)<=100) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "desc"

    public static class between_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "between"
    // Sql.g:126:1: between : ( 'between' | 'BETWEEN' );
    public final SqlParser.between_return between() throws RecognitionException {
        SqlParser.between_return retval = new SqlParser.between_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:126:10: ( 'between' | 'BETWEEN' )
            // Sql.g:
            {
            if ( (input.LA(1)>=101 && input.LA(1)<=102) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "between"


    // $ANTLR start "where"
    // Sql.g:129:1: where : ( 'where' | 'WHERE' );
    public final void where() throws RecognitionException {
        try {
            // Sql.g:129:8: ( 'where' | 'WHERE' )
            // Sql.g:
            {
            if ( (input.LA(1)>=103 && input.LA(1)<=104) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "where"

    // Delegated rules


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA12 dfa12 = new DFA12(this);
    static final String DFA2_eotS =
        "\12\uffff";
    static final String DFA2_eofS =
        "\1\uffff\1\5\4\uffff\2\5\1\uffff\1\5";
    static final String DFA2_minS =
        "\1\16\1\4\1\16\1\uffff\1\20\1\uffff\2\4\1\20\1\4";
    static final String DFA2_maxS =
        "\1\43\1\144\1\43\1\uffff\1\105\1\uffff\2\144\1\105\1\144";
    static final String DFA2_acceptS =
        "\3\uffff\1\2\1\uffff\1\1\4\uffff";
    static final String DFA2_specialS =
        "\12\uffff}>";
    static final String[] DFA2_transitionS = {
            "\26\1",
            "\1\2\1\4\133\uffff\4\3",
            "\26\6",
            "",
            "\3\7\21\uffff\42\7",
            "",
            "\1\2\1\10\133\uffff\4\3",
            "\1\2\134\uffff\4\3",
            "\3\11\21\uffff\42\11",
            "\1\2\134\uffff\4\3"
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "25:1: orderList : ( orderList1 | orderList1 oing= ordering );";
        }
    }
    static final String DFA12_eotS =
        "\15\uffff";
    static final String DFA12_eofS =
        "\15\uffff";
    static final String DFA12_minS =
        "\1\10\1\11\2\10\11\uffff";
    static final String DFA12_maxS =
        "\1\13\3\14\11\uffff";
    static final String DFA12_acceptS =
        "\4\uffff\1\4\1\5\1\6\1\1\1\7\1\11\1\2\1\10\1\3";
    static final String DFA12_specialS =
        "\15\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\1\1\2\1\3\1\4",
            "\1\6\1\5\1\uffff\1\7",
            "\1\10\1\uffff\1\11\1\uffff\1\12",
            "\1\13\3\uffff\1\14",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "95:1: compOpt : ( EQ | LT | GT | NOT EQ | EQ GT | EQ LT | LT EQ | GT EQ | LT GT );";
        }
    }
 

    public static final BitSet FOLLOW_select_in_stmt27 = new BitSet(new long[]{0x0020000FFFFFC000L,0x0000000000007FC0L});
    public static final BitSet FOLLOW_selectList_in_stmt29 = new BitSet(new long[]{0x0000000000000000L,0x0000018000000000L});
    public static final BitSet FOLLOW_where_in_stmt31 = new BitSet(new long[]{0x0000000FFFFFC040L});
    public static final BitSet FOLLOW_constraintList_in_stmt33 = new BitSet(new long[]{0x0000000000000002L,0x0000000000600000L});
    public static final BitSet FOLLOW_order_in_stmt36 = new BitSet(new long[]{0x0000000000000000L,0x0000000001800000L});
    public static final BitSet FOLLOW_by_in_stmt38 = new BitSet(new long[]{0x0000000FFFFFC000L});
    public static final BitSet FOLLOW_orderList_in_stmt40 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orderList1_in_orderList51 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orderList1_in_orderList57 = new BitSet(new long[]{0x0000000000000000L,0x0000001E00000000L});
    public static final BitSet FOLLOW_ordering_in_orderList66 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_orderList177 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_COMMA_in_orderList190 = new BitSet(new long[]{0x0000000FFFFFC000L});
    public static final BitSet FOLLOW_simpleKw_in_orderList197 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_asc_in_ordering121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_desc_in_ordering123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_slkeyword_in_selectList134 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_COMMA_in_selectList147 = new BitSet(new long[]{0x0020000FFFFFC000L,0x0000000000007FC0L});
    public static final BitSet FOLLOW_slkeyword_in_selectList155 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_entity_in_simpleKw180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_simpleKw186 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_DOT_in_simpleKw188 = new BitSet(new long[]{0xFFFFFFF000070000L,0x000000000000003FL});
    public static final BitSet FOLLOW_attr_in_simpleKw190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_slkeyword197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funct_in_slkeyword203 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LB_in_slkeyword205 = new BitSet(new long[]{0x0000000FFFFFC000L});
    public static final BitSet FOLLOW_simpleKw_in_slkeyword207 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RB_in_slkeyword209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LB_in_lopen222 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_RB_in_ropen233 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_constraint1_in_constraintList244 = new BitSet(new long[]{0x0000000000000002L,0x0000000006180000L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList258 = new BitSet(new long[]{0x0000000FFFFFC040L});
    public static final BitSet FOLLOW_constraint1_in_constraintList265 = new BitSet(new long[]{0x0000000000000002L,0x0000000006180000L});
    public static final BitSet FOLLOW_lopen_in_constraint1281 = new BitSet(new long[]{0x0000000FFFFFC040L});
    public static final BitSet FOLLOW_constraint_in_constraint1287 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ropen_in_constraint1298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint315 = new BitSet(new long[]{0x0000000000000F00L});
    public static final BitSet FOLLOW_compOpt_in_constraint326 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_genValue_in_constraint338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint366 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_in_in_constraint377 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LB_in_constraint388 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_inValue_in_constraint394 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RB_in_constraint402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint428 = new BitSet(new long[]{0x0000000000000000L,0x00000001E0000000L});
    public static final BitSet FOLLOW_genLike_in_constraint439 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_genValue_in_constraint450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint465 = new BitSet(new long[]{0x0000000000000000L,0x0000006000000000L});
    public static final BitSet FOLLOW_between_in_constraint476 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_betValue_in_constraint486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_compOpt532 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt539 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt546 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LT_in_compOpt548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt553 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt560 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt567 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_genValue576 = new BitSet(new long[]{0x0000000000001F22L});
    public static final BitSet FOLLOW_VALUE_in_genValue579 = new BitSet(new long[]{0x0000000000001F22L});
    public static final BitSet FOLLOW_DOT_in_genValue584 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_VALUE_in_genValue586 = new BitSet(new long[]{0x0000000000000F22L});
    public static final BitSet FOLLOW_compOpt_in_genValue591 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_VALUE_in_genValue593 = new BitSet(new long[]{0x0000000000000F02L});
    public static final BitSet FOLLOW_genValue_in_betValue604 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L});
    public static final BitSet FOLLOW_and_in_betValue606 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_genValue_in_betValue608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_genValue_in_inValue615 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_COMMA_in_inValue619 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_genValue_in_inValue621 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_and_in_logicalOp630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_order0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_by0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_like0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_in_notLike1020 = new BitSet(new long[]{0x0000000000000000L,0x0000000180000000L});
    public static final BitSet FOLLOW_like_in_notLike1022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_genLike1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_notLike_in_genLike1034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_asc0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_desc0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_between0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where0 = new BitSet(new long[]{0x0000000000000002L});

}