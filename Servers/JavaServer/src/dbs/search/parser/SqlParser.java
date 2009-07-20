package dbs.search.parser;
// $ANTLR 3.1.2 Sql.g 2009-07-20 09:16:23


import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMA", "DOT", "LB", "RB", "EQ", "LT", "GT", "NOT", "VALUE", "WS", "'ads'", "'config'", "'dataset'", "'release'", "'tier'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'lumi'", "'dq'", "'ilumi'", "'phygrp'", "'group'", "'pset'", "'algo'", "'datatype'", "'mcdesc'", "'trigdesc'", "'branch'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numfiles'", "'numlss'", "'totlumi'", "'store'", "'size'", "'count'", "'status'", "'type'", "'id'", "'parent'", "'child'", "'def'", "'evnum'", "'era'", "'tag'", "'xsection'", "'hash'", "'content'", "'family'", "'exe'", "'annotation'", "'checksum'", "'sum'", "'max'", "'min'", "'avg'", "'COUNT'", "'SUM'", "'MAX'", "'MIN'", "'AVG'", "'find'", "'FIND'", "'and'", "'AND'", "'order'", "'ORDER'", "'by'", "'BY'", "'or'", "'OR'", "'in'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'", "'asc'", "'ASC'", "'desc'", "'DESC'", "'between'", "'BETWEEN'", "'where'", "'WHERE'"
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
    public static final int T__59=59;
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
    // Sql.g:21:1: stmt : select selectList ( where constraintList )? ( order by orderList )? ;
    public final void stmt() throws RecognitionException {
        try {
            // Sql.g:21:6: ( select selectList ( where constraintList )? ( order by orderList )? )
            // Sql.g:21:8: select selectList ( where constraintList )? ( order by orderList )?
            {
            pushFollow(FOLLOW_select_in_stmt27);
            select();

            state._fsp--;

            pushFollow(FOLLOW_selectList_in_stmt29);
            selectList();

            state._fsp--;

            // Sql.g:21:26: ( where constraintList )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( ((LA1_0>=101 && LA1_0<=102)) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // Sql.g:21:27: where constraintList
                    {
                    pushFollow(FOLLOW_where_in_stmt32);
                    where();

                    state._fsp--;

                    pushFollow(FOLLOW_constraintList_in_stmt34);
                    constraintList();

                    state._fsp--;


                    }
                    break;

            }

            // Sql.g:21:50: ( order by orderList )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>=83 && LA2_0<=84)) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // Sql.g:21:51: order by orderList
                    {
                    pushFollow(FOLLOW_order_in_stmt39);
                    order();

                    state._fsp--;

                    pushFollow(FOLLOW_by_in_stmt41);
                    by();

                    state._fsp--;

                    pushFollow(FOLLOW_orderList_in_stmt43);
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
            int alt3=2;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // Sql.g:25:12: orderList1
                    {
                    pushFollow(FOLLOW_orderList1_in_orderList54);
                    orderList1();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:26:5: orderList1 oing= ordering
                    {
                    pushFollow(FOLLOW_orderList1_in_orderList60);
                    orderList1();

                    state._fsp--;

                    pushFollow(FOLLOW_ordering_in_orderList69);
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
            pushFollow(FOLLOW_simpleKw_in_orderList180);
            okw=simpleKw();

            state._fsp--;

            okws.add((okw!=null?input.toString(okw.start,okw.stop):null));
            // Sql.g:29:4: ( COMMA okw= simpleKw )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==COMMA) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Sql.g:30:3: COMMA okw= simpleKw
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_orderList193); 
            	    pushFollow(FOLLOW_simpleKw_in_orderList1100);
            	    okw=simpleKw();

            	    state._fsp--;

            	    okws.add((okw!=null?input.toString(okw.start,okw.stop):null));

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
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>=95 && LA5_0<=96)) ) {
                alt5=1;
            }
            else if ( ((LA5_0>=97 && LA5_0<=98)) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // Sql.g:35:13: asc
                    {
                    pushFollow(FOLLOW_asc_in_ordering124);
                    asc();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:35:17: desc
                    {
                    pushFollow(FOLLOW_desc_in_ordering126);
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
            pushFollow(FOLLOW_slkeyword_in_selectList137);
            kw=slkeyword();

            state._fsp--;

            kws.add((kw!=null?input.toString(kw.start,kw.stop):null));
            // Sql.g:37:4: ( COMMA kw= slkeyword )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==COMMA) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // Sql.g:38:3: COMMA kw= slkeyword
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectList150); 
            	    pushFollow(FOLLOW_slkeyword_in_selectList158);
            	    kw=slkeyword();

            	    state._fsp--;

            	    kws.add((kw!=null?input.toString(kw.start,kw.stop):null));

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
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=14 && LA7_0<=35)) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==DOT) ) {
                    alt7=2;
                }
                else if ( (LA7_1==EOF||LA7_1==COMMA||(LA7_1>=RB && LA7_1<=NOT)||(LA7_1>=83 && LA7_1<=84)||(LA7_1>=89 && LA7_1<=102)) ) {
                    alt7=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // Sql.g:42:12: entity
                    {
                    pushFollow(FOLLOW_entity_in_simpleKw183);
                    entity();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:43:5: entity DOT attr
                    {
                    pushFollow(FOLLOW_entity_in_simpleKw189);
                    entity();

                    state._fsp--;

                    match(input,DOT,FOLLOW_DOT_in_simpleKw191); 
                    pushFollow(FOLLOW_attr_in_simpleKw193);
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
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( ((LA8_0>=14 && LA8_0<=35)) ) {
                alt8=1;
            }
            else if ( (LA8_0==53||(LA8_0>=70 && LA8_0<=78)) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // Sql.g:44:13: simpleKw
                    {
                    pushFollow(FOLLOW_simpleKw_in_slkeyword200);
                    simpleKw();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:45:5: funct LB simpleKw RB
                    {
                    pushFollow(FOLLOW_funct_in_slkeyword206);
                    funct();

                    state._fsp--;

                    match(input,LB,FOLLOW_LB_in_slkeyword208); 
                    pushFollow(FOLLOW_simpleKw_in_slkeyword210);
                    simpleKw();

                    state._fsp--;

                    match(input,RB,FOLLOW_RB_in_slkeyword212); 

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
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==LB) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Sql.g:50:11: LB
            	    {
            	    match(input,LB,FOLLOW_LB_in_lopen225); 

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
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RB) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // Sql.g:51:11: RB
            	    {
            	    match(input,RB,FOLLOW_RB_in_ropen236); 

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
    // $ANTLR end "ropen"


    // $ANTLR start "constraintList"
    // Sql.g:55:1: constraintList : constraint1 (rel= logicalOp constraint1 )* ;
    public final void constraintList() throws RecognitionException {
        SqlParser.logicalOp_return rel = null;


        try {
            // Sql.g:55:16: ( constraint1 (rel= logicalOp constraint1 )* )
            // Sql.g:55:17: constraint1 (rel= logicalOp constraint1 )*
            {
            pushFollow(FOLLOW_constraint1_in_constraintList247);
            constraint1();

            state._fsp--;

            // Sql.g:56:3: (rel= logicalOp constraint1 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=81 && LA11_0<=82)||(LA11_0>=87 && LA11_0<=88)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // Sql.g:57:3: rel= logicalOp constraint1
            	    {
            	    pushFollow(FOLLOW_logicalOp_in_constraintList261);
            	    rel=logicalOp();

            	    state._fsp--;

            	     constraints.add((rel!=null?input.toString(rel.start,rel.stop):null));
            	    pushFollow(FOLLOW_constraint1_in_constraintList268);
            	    constraint1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
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
            pushFollow(FOLLOW_lopen_in_constraint1284);
            kl=lopen();

            state._fsp--;

            Constraint c1=new Constraint();c1.setBracket((kl!=null?input.toString(kl.start,kl.stop):null));constraints.add(c1);
            pushFollow(FOLLOW_constraint_in_constraint1290);
            constraint();

            state._fsp--;

            pushFollow(FOLLOW_ropen_in_constraint1301);
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
            int alt12=4;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=14 && LA12_0<=35)) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA12_2 = input.LA(3);

                    if ( ((LA12_2>=16 && LA12_2<=18)||(LA12_2>=36 && LA12_2<=69)) ) {
                        switch ( input.LA(4) ) {
                        case 99:
                        case 100:
                            {
                            alt12=4;
                            }
                            break;
                        case 89:
                        case 90:
                            {
                            alt12=2;
                            }
                            break;
                        case EQ:
                        case LT:
                        case GT:
                        case NOT:
                            {
                            alt12=1;
                            }
                            break;
                        case 91:
                        case 92:
                        case 93:
                        case 94:
                            {
                            alt12=3;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 12, 7, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 12, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case EQ:
                case LT:
                case GT:
                case NOT:
                    {
                    alt12=1;
                    }
                    break;
                case 91:
                case 92:
                case 93:
                case 94:
                    {
                    alt12=3;
                    }
                    break;
                case 99:
                case 100:
                    {
                    alt12=4;
                    }
                    break;
                case 89:
                case 90:
                    {
                    alt12=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // Sql.g:76:2: kw= simpleKw op= compOpt val= genValue
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint318);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_compOpt_in_constraint329);
                    op=compOpt();

                    state._fsp--;

                    c.setOp((op!=null?input.toString(op.start,op.stop):null));
                    pushFollow(FOLLOW_genValue_in_constraint341);
                    val=genValue();

                    state._fsp--;

                    c.setValue((val!=null?input.toString(val.start,val.stop):null)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:80:2: kw= simpleKw op1= in LB val1= inValue RB
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint369);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_in_in_constraint380);
                    op1=in();

                    state._fsp--;

                    c.setOp((op1!=null?input.toString(op1.start,op1.stop):null));
                    match(input,LB,FOLLOW_LB_in_constraint391); 
                    pushFollow(FOLLOW_inValue_in_constraint397);
                    val1=inValue();

                    state._fsp--;

                    c.setValue((val1!=null?input.toString(val1.start,val1.stop):null)); constraints.add(c);
                    match(input,RB,FOLLOW_RB_in_constraint405); 

                    }
                    break;
                case 3 :
                    // Sql.g:86:2: kw= simpleKw op2= genLike val2= genValue
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint431);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_genLike_in_constraint442);
                    op2=genLike();

                    state._fsp--;

                    c.setOp((op2!=null?input.toString(op2.start,op2.stop):null));
                    pushFollow(FOLLOW_genValue_in_constraint453);
                    val2=genValue();

                    state._fsp--;

                    c.setValue((val2!=null?input.toString(val2.start,val2.stop):null)); constraints.add(c);

                    }
                    break;
                case 4 :
                    // Sql.g:90:3: kw= simpleKw op3= between val3= betValue
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint468);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_between_in_constraint479);
                    op3=between();

                    state._fsp--;

                    c.setOp((op3!=null?input.toString(op3.start,op3.stop):null));
                    pushFollow(FOLLOW_betValue_in_constraint489);
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
            int alt13=9;
            alt13 = dfa13.predict(input);
            switch (alt13) {
                case 1 :
                    // Sql.g:95:11: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt520); 

                    }
                    break;
                case 2 :
                    // Sql.g:96:4: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt525); 

                    }
                    break;
                case 3 :
                    // Sql.g:97:4: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt530); 

                    }
                    break;
                case 4 :
                    // Sql.g:98:4: NOT EQ
                    {
                    match(input,NOT,FOLLOW_NOT_in_compOpt535); 
                    match(input,EQ,FOLLOW_EQ_in_compOpt537); 

                    }
                    break;
                case 5 :
                    // Sql.g:99:4: EQ GT
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt542); 
                    match(input,GT,FOLLOW_GT_in_compOpt544); 

                    }
                    break;
                case 6 :
                    // Sql.g:100:4: EQ LT
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt549); 
                    match(input,LT,FOLLOW_LT_in_compOpt551); 

                    }
                    break;
                case 7 :
                    // Sql.g:101:4: LT EQ
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt556); 
                    match(input,EQ,FOLLOW_EQ_in_compOpt558); 

                    }
                    break;
                case 8 :
                    // Sql.g:102:4: GT EQ
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt563); 
                    match(input,EQ,FOLLOW_EQ_in_compOpt565); 

                    }
                    break;
                case 9 :
                    // Sql.g:103:4: LT GT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt570); 
                    match(input,GT,FOLLOW_GT_in_compOpt572); 

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
            match(input,VALUE,FOLLOW_VALUE_in_genValue579); 
            // Sql.g:104:18: ( VALUE )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==VALUE) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // Sql.g:104:19: VALUE
            	    {
            	    match(input,VALUE,FOLLOW_VALUE_in_genValue582); 

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            // Sql.g:104:27: ( DOT VALUE )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==DOT) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // Sql.g:104:28: DOT VALUE
            	    {
            	    match(input,DOT,FOLLOW_DOT_in_genValue587); 
            	    match(input,VALUE,FOLLOW_VALUE_in_genValue589); 

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            // Sql.g:104:40: ( compOpt VALUE )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=EQ && LA16_0<=NOT)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // Sql.g:104:41: compOpt VALUE
            	    {
            	    pushFollow(FOLLOW_compOpt_in_genValue594);
            	    compOpt();

            	    state._fsp--;

            	    match(input,VALUE,FOLLOW_VALUE_in_genValue596); 

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
            pushFollow(FOLLOW_genValue_in_betValue607);
            genValue();

            state._fsp--;

            pushFollow(FOLLOW_and_in_betValue609);
            and();

            state._fsp--;

            pushFollow(FOLLOW_genValue_in_betValue611);
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
            pushFollow(FOLLOW_genValue_in_inValue618);
            genValue();

            state._fsp--;

            // Sql.g:108:20: ( COMMA genValue )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==COMMA) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // Sql.g:108:22: COMMA genValue
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_inValue622); 
            	    pushFollow(FOLLOW_genValue_in_inValue624);
            	    genValue();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
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
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=81 && LA18_0<=82)) ) {
                alt18=1;
            }
            else if ( ((LA18_0>=87 && LA18_0<=88)) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // Sql.g:109:12: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp633);
                    and();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:109:16: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp635);
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
    // Sql.g:115:1: select : ( 'find' | 'FIND' );
    public final void select() throws RecognitionException {
        try {
            // Sql.g:115:9: ( 'find' | 'FIND' )
            // Sql.g:
            {
            if ( (input.LA(1)>=79 && input.LA(1)<=80) ) {
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
    // Sql.g:116:1: and : ( 'and' | 'AND' );
    public final void and() throws RecognitionException {
        try {
            // Sql.g:116:6: ( 'and' | 'AND' )
            // Sql.g:
            {
            if ( (input.LA(1)>=81 && input.LA(1)<=82) ) {
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
    // Sql.g:117:1: order : ( 'order' | 'ORDER' );
    public final void order() throws RecognitionException {
        try {
            // Sql.g:117:8: ( 'order' | 'ORDER' )
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
    // $ANTLR end "order"


    // $ANTLR start "by"
    // Sql.g:118:1: by : ( 'by' | 'BY' );
    public final void by() throws RecognitionException {
        try {
            // Sql.g:118:5: ( 'by' | 'BY' )
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
    // $ANTLR end "by"


    // $ANTLR start "or"
    // Sql.g:119:1: or : ( 'or' | 'OR' );
    public final void or() throws RecognitionException {
        try {
            // Sql.g:119:5: ( 'or' | 'OR' )
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
    // $ANTLR end "or"

    public static class in_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "in"
    // Sql.g:120:1: in : ( 'in' | 'IN' );
    public final SqlParser.in_return in() throws RecognitionException {
        SqlParser.in_return retval = new SqlParser.in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:120:5: ( 'in' | 'IN' )
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
    // Sql.g:121:1: not : ( 'not' | 'NOT' );
    public final void not() throws RecognitionException {
        try {
            // Sql.g:121:6: ( 'not' | 'NOT' )
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
    // Sql.g:122:1: like : ( 'like' | 'LIKE' );
    public final void like() throws RecognitionException {
        try {
            // Sql.g:122:7: ( 'like' | 'LIKE' )
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
    // $ANTLR end "like"


    // $ANTLR start "notLike"
    // Sql.g:123:1: notLike : not like ;
    public final void notLike() throws RecognitionException {
        try {
            // Sql.g:123:10: ( not like )
            // Sql.g:123:12: not like
            {
            pushFollow(FOLLOW_not_in_notLike1016);
            not();

            state._fsp--;

            pushFollow(FOLLOW_like_in_notLike1018);
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
    // Sql.g:124:1: genLike : ( like | notLike );
    public final SqlParser.genLike_return genLike() throws RecognitionException {
        SqlParser.genLike_return retval = new SqlParser.genLike_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:124:10: ( like | notLike )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=93 && LA19_0<=94)) ) {
                alt19=1;
            }
            else if ( ((LA19_0>=91 && LA19_0<=92)) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // Sql.g:124:12: like
                    {
                    pushFollow(FOLLOW_like_in_genLike1026);
                    like();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:124:19: notLike
                    {
                    pushFollow(FOLLOW_notLike_in_genLike1030);
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
    // Sql.g:125:1: asc : ( 'asc' | 'ASC' );
    public final void asc() throws RecognitionException {
        try {
            // Sql.g:125:6: ( 'asc' | 'ASC' )
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
    // $ANTLR end "asc"


    // $ANTLR start "desc"
    // Sql.g:126:1: desc : ( 'desc' | 'DESC' );
    public final void desc() throws RecognitionException {
        try {
            // Sql.g:126:7: ( 'desc' | 'DESC' )
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
    // $ANTLR end "desc"

    public static class between_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "between"
    // Sql.g:127:1: between : ( 'between' | 'BETWEEN' );
    public final SqlParser.between_return between() throws RecognitionException {
        SqlParser.between_return retval = new SqlParser.between_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:127:10: ( 'between' | 'BETWEEN' )
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
    // Sql.g:130:1: where : ( 'where' | 'WHERE' );
    public final void where() throws RecognitionException {
        try {
            // Sql.g:130:8: ( 'where' | 'WHERE' )
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


    protected DFA3 dfa3 = new DFA3(this);
    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA3_eotS =
        "\12\uffff";
    static final String DFA3_eofS =
        "\1\uffff\1\5\4\uffff\2\5\1\uffff\1\5";
    static final String DFA3_minS =
        "\1\16\1\4\1\20\1\16\2\uffff\2\4\1\20\1\4";
    static final String DFA3_maxS =
        "\1\43\1\142\1\105\1\43\2\uffff\2\142\1\105\1\142";
    static final String DFA3_acceptS =
        "\4\uffff\1\2\1\1\4\uffff";
    static final String DFA3_specialS =
        "\12\uffff}>";
    static final String[] DFA3_transitionS = {
            "\26\1",
            "\1\3\1\2\131\uffff\4\4",
            "\3\6\21\uffff\42\6",
            "\26\7",
            "",
            "",
            "\1\3\132\uffff\4\4",
            "\1\3\1\10\131\uffff\4\4",
            "\3\11\21\uffff\42\11",
            "\1\3\132\uffff\4\4"
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
            return "25:1: orderList : ( orderList1 | orderList1 oing= ordering );";
        }
    }
    static final String DFA13_eotS =
        "\15\uffff";
    static final String DFA13_eofS =
        "\15\uffff";
    static final String DFA13_minS =
        "\1\10\1\11\2\10\11\uffff";
    static final String DFA13_maxS =
        "\1\13\3\14\11\uffff";
    static final String DFA13_acceptS =
        "\4\uffff\1\4\1\5\1\6\1\1\1\7\1\11\1\2\1\10\1\3";
    static final String DFA13_specialS =
        "\15\uffff}>";
    static final String[] DFA13_transitionS = {
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

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "95:1: compOpt : ( EQ | LT | GT | NOT EQ | EQ GT | EQ LT | LT EQ | GT EQ | LT GT );";
        }
    }
 

    public static final BitSet FOLLOW_select_in_stmt27 = new BitSet(new long[]{0x0020000FFFFFC000L,0x0000000000007FC0L});
    public static final BitSet FOLLOW_selectList_in_stmt29 = new BitSet(new long[]{0x0000000000000002L,0x0000006000180000L});
    public static final BitSet FOLLOW_where_in_stmt32 = new BitSet(new long[]{0x0000000FFFFFC040L});
    public static final BitSet FOLLOW_constraintList_in_stmt34 = new BitSet(new long[]{0x0000000000000002L,0x0000000000180000L});
    public static final BitSet FOLLOW_order_in_stmt39 = new BitSet(new long[]{0x0000000000000000L,0x0000000000600000L});
    public static final BitSet FOLLOW_by_in_stmt41 = new BitSet(new long[]{0x0000000FFFFFC000L});
    public static final BitSet FOLLOW_orderList_in_stmt43 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orderList1_in_orderList54 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orderList1_in_orderList60 = new BitSet(new long[]{0x0000000000000000L,0x0000000780000000L});
    public static final BitSet FOLLOW_ordering_in_orderList69 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_orderList180 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_COMMA_in_orderList193 = new BitSet(new long[]{0x0000000FFFFFC000L});
    public static final BitSet FOLLOW_simpleKw_in_orderList1100 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_asc_in_ordering124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_desc_in_ordering126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_slkeyword_in_selectList137 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_COMMA_in_selectList150 = new BitSet(new long[]{0x0020000FFFFFC000L,0x0000000000007FC0L});
    public static final BitSet FOLLOW_slkeyword_in_selectList158 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_entity_in_simpleKw183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_simpleKw189 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_DOT_in_simpleKw191 = new BitSet(new long[]{0xFFFFFFF000070000L,0x000000000000003FL});
    public static final BitSet FOLLOW_attr_in_simpleKw193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_slkeyword200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funct_in_slkeyword206 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LB_in_slkeyword208 = new BitSet(new long[]{0x0000000FFFFFC000L});
    public static final BitSet FOLLOW_simpleKw_in_slkeyword210 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RB_in_slkeyword212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LB_in_lopen225 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_RB_in_ropen236 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_constraint1_in_constraintList247 = new BitSet(new long[]{0x0000000000000002L,0x0000000001860000L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList261 = new BitSet(new long[]{0x0000000FFFFFC040L});
    public static final BitSet FOLLOW_constraint1_in_constraintList268 = new BitSet(new long[]{0x0000000000000002L,0x0000000001860000L});
    public static final BitSet FOLLOW_lopen_in_constraint1284 = new BitSet(new long[]{0x0000000FFFFFC040L});
    public static final BitSet FOLLOW_constraint_in_constraint1290 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ropen_in_constraint1301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint318 = new BitSet(new long[]{0x0000000000000F00L});
    public static final BitSet FOLLOW_compOpt_in_constraint329 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_genValue_in_constraint341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint369 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
    public static final BitSet FOLLOW_in_in_constraint380 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LB_in_constraint391 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_inValue_in_constraint397 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RB_in_constraint405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint431 = new BitSet(new long[]{0x0000000000000000L,0x0000000078000000L});
    public static final BitSet FOLLOW_genLike_in_constraint442 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_genValue_in_constraint453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint468 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
    public static final BitSet FOLLOW_between_in_constraint479 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_betValue_in_constraint489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_compOpt535 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt542 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt549 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LT_in_compOpt551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt556 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt563 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt570 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_genValue579 = new BitSet(new long[]{0x0000000000001F22L});
    public static final BitSet FOLLOW_VALUE_in_genValue582 = new BitSet(new long[]{0x0000000000001F22L});
    public static final BitSet FOLLOW_DOT_in_genValue587 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_VALUE_in_genValue589 = new BitSet(new long[]{0x0000000000000F22L});
    public static final BitSet FOLLOW_compOpt_in_genValue594 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_VALUE_in_genValue596 = new BitSet(new long[]{0x0000000000000F02L});
    public static final BitSet FOLLOW_genValue_in_betValue607 = new BitSet(new long[]{0x0000000000000000L,0x0000000000060000L});
    public static final BitSet FOLLOW_and_in_betValue609 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_genValue_in_betValue611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_genValue_in_inValue618 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_COMMA_in_inValue622 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_genValue_in_inValue624 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_and_in_logicalOp633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp635 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_not_in_notLike1016 = new BitSet(new long[]{0x0000000000000000L,0x0000000060000000L});
    public static final BitSet FOLLOW_like_in_notLike1018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_genLike1026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_notLike_in_genLike1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_asc0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_desc0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_between0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where0 = new BitSet(new long[]{0x0000000000000002L});

}