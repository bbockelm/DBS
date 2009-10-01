package dbs.search.parser;
// $ANTLR 3.2 Sep 23, 2009 12:02:23 Sql.g 2009-10-01 16:12:48

import java.util.ArrayList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMA", "DOT", "LB", "RB", "EQ", "LT", "GT", "NOT", "VALUE", "WS", "'xyzzy'", "'ads'", "'config'", "'dataset'", "'release'", "'tier'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'lumi'", "'dq'", "'ilumi'", "'phygrp'", "'group'", "'pset'", "'algo'", "'datatype'", "'mcdesc'", "'trigdesc'", "'branch'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numfiles'", "'numlss'", "'totlumi'", "'store'", "'size'", "'count'", "'status'", "'type'", "'id'", "'parent'", "'child'", "'def'", "'evnum'", "'era'", "'tag'", "'xsection'", "'hash'", "'content'", "'family'", "'exe'", "'annotation'", "'checksum'", "'sum'", "'max'", "'min'", "'avg'", "'COUNT'", "'SUM'", "'MAX'", "'MIN'", "'AVG'", "'find'", "'FIND'", "'and'", "'AND'", "'order'", "'ORDER'", "'by'", "'BY'", "'or'", "'OR'", "'in'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'", "'asc'", "'ASC'", "'desc'", "'DESC'", "'between'", "'BETWEEN'", "'where'", "'WHERE'"
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
    // Sql.g:20:1: stmt : select selectList ( where constraintList )? ( order by orderList )? ( EOF | 'xyzzy' ) ;
    public final void stmt() throws RecognitionException {
        try {
            // Sql.g:20:6: ( select selectList ( where constraintList )? ( order by orderList )? ( EOF | 'xyzzy' ) )
            // Sql.g:20:8: select selectList ( where constraintList )? ( order by orderList )? ( EOF | 'xyzzy' )
            {
            pushFollow(FOLLOW_select_in_stmt28);
            select();

            state._fsp--;

            pushFollow(FOLLOW_selectList_in_stmt30);
            selectList();

            state._fsp--;

            // Sql.g:20:26: ( where constraintList )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( ((LA1_0>=102 && LA1_0<=103)) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // Sql.g:20:27: where constraintList
                    {
                    pushFollow(FOLLOW_where_in_stmt33);
                    where();

                    state._fsp--;

                    pushFollow(FOLLOW_constraintList_in_stmt35);
                    constraintList();

                    state._fsp--;


                    }
                    break;

            }

            // Sql.g:20:50: ( order by orderList )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>=84 && LA2_0<=85)) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // Sql.g:20:51: order by orderList
                    {
                    pushFollow(FOLLOW_order_in_stmt40);
                    order();

                    state._fsp--;

                    pushFollow(FOLLOW_by_in_stmt42);
                    by();

                    state._fsp--;

                    pushFollow(FOLLOW_orderList_in_stmt44);
                    orderList();

                    state._fsp--;


                    }
                    break;

            }

            if ( input.LA(1)==EOF||input.LA(1)==14 ) {
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
                    pushFollow(FOLLOW_orderList1_in_orderList67);
                    orderList1();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:26:5: orderList1 oing= ordering
                    {
                    pushFollow(FOLLOW_orderList1_in_orderList73);
                    orderList1();

                    state._fsp--;

                    pushFollow(FOLLOW_ordering_in_orderList82);
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
            pushFollow(FOLLOW_simpleKw_in_orderList193);
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
            	    match(input,COMMA,FOLLOW_COMMA_in_orderList1106); 
            	    pushFollow(FOLLOW_simpleKw_in_orderList1113);
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

            if ( ((LA5_0>=96 && LA5_0<=97)) ) {
                alt5=1;
            }
            else if ( ((LA5_0>=98 && LA5_0<=99)) ) {
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
                    pushFollow(FOLLOW_asc_in_ordering137);
                    asc();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:35:17: desc
                    {
                    pushFollow(FOLLOW_desc_in_ordering139);
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
            pushFollow(FOLLOW_slkeyword_in_selectList150);
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
            	    match(input,COMMA,FOLLOW_COMMA_in_selectList163); 
            	    pushFollow(FOLLOW_slkeyword_in_selectList171);
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

            if ( ((LA7_0>=15 && LA7_0<=36)) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==EOF||LA7_1==COMMA||(LA7_1>=RB && LA7_1<=NOT)||LA7_1==14||(LA7_1>=84 && LA7_1<=85)||(LA7_1>=90 && LA7_1<=103)) ) {
                    alt7=1;
                }
                else if ( (LA7_1==DOT) ) {
                    alt7=2;
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
                    pushFollow(FOLLOW_entity_in_simpleKw196);
                    entity();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:43:5: entity DOT attr
                    {
                    pushFollow(FOLLOW_entity_in_simpleKw202);
                    entity();

                    state._fsp--;

                    match(input,DOT,FOLLOW_DOT_in_simpleKw204); 
                    pushFollow(FOLLOW_attr_in_simpleKw206);
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

            if ( ((LA8_0>=15 && LA8_0<=36)) ) {
                alt8=1;
            }
            else if ( (LA8_0==54||(LA8_0>=71 && LA8_0<=79)) ) {
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
                    pushFollow(FOLLOW_simpleKw_in_slkeyword213);
                    simpleKw();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:45:5: funct LB simpleKw RB
                    {
                    pushFollow(FOLLOW_funct_in_slkeyword219);
                    funct();

                    state._fsp--;

                    match(input,LB,FOLLOW_LB_in_slkeyword221); 
                    pushFollow(FOLLOW_simpleKw_in_slkeyword223);
                    simpleKw();

                    state._fsp--;

                    match(input,RB,FOLLOW_RB_in_slkeyword225); 

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
            	    match(input,LB,FOLLOW_LB_in_lopen238); 

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
            	    match(input,RB,FOLLOW_RB_in_ropen249); 

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
            pushFollow(FOLLOW_constraint1_in_constraintList260);
            constraint1();

            state._fsp--;

            // Sql.g:56:3: (rel= logicalOp constraint1 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=82 && LA11_0<=83)||(LA11_0>=88 && LA11_0<=89)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // Sql.g:57:3: rel= logicalOp constraint1
            	    {
            	    pushFollow(FOLLOW_logicalOp_in_constraintList274);
            	    rel=logicalOp();

            	    state._fsp--;

            	     constraints.add((rel!=null?input.toString(rel.start,rel.stop):null));
            	    pushFollow(FOLLOW_constraint1_in_constraintList281);
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
            pushFollow(FOLLOW_lopen_in_constraint1297);
            kl=lopen();

            state._fsp--;

            Constraint c1=new Constraint();c1.setBracket((kl!=null?input.toString(kl.start,kl.stop):null));constraints.add(c1);
            pushFollow(FOLLOW_constraint_in_constraint1303);
            constraint();

            state._fsp--;

            pushFollow(FOLLOW_ropen_in_constraint1314);
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

            if ( ((LA12_0>=15 && LA12_0<=36)) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA12_2 = input.LA(3);

                    if ( ((LA12_2>=17 && LA12_2<=19)||(LA12_2>=37 && LA12_2<=70)) ) {
                        switch ( input.LA(4) ) {
                        case 100:
                        case 101:
                            {
                            alt12=4;
                            }
                            break;
                        case 92:
                        case 93:
                        case 94:
                        case 95:
                            {
                            alt12=3;
                            }
                            break;
                        case 90:
                        case 91:
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
                case 100:
                case 101:
                    {
                    alt12=4;
                    }
                    break;
                case 90:
                case 91:
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
                case 92:
                case 93:
                case 94:
                case 95:
                    {
                    alt12=3;
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
                    pushFollow(FOLLOW_simpleKw_in_constraint331);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_compOpt_in_constraint342);
                    op=compOpt();

                    state._fsp--;

                    c.setOp((op!=null?input.toString(op.start,op.stop):null));
                    pushFollow(FOLLOW_genValue_in_constraint354);
                    val=genValue();

                    state._fsp--;

                    c.setValue((val!=null?input.toString(val.start,val.stop):null)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:80:2: kw= simpleKw op1= in LB val1= inValue RB
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint382);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_in_in_constraint393);
                    op1=in();

                    state._fsp--;

                    c.setOp((op1!=null?input.toString(op1.start,op1.stop):null));
                    match(input,LB,FOLLOW_LB_in_constraint404); 
                    pushFollow(FOLLOW_inValue_in_constraint410);
                    val1=inValue();

                    state._fsp--;

                    c.setValue((val1!=null?input.toString(val1.start,val1.stop):null)); constraints.add(c);
                    match(input,RB,FOLLOW_RB_in_constraint418); 

                    }
                    break;
                case 3 :
                    // Sql.g:86:2: kw= simpleKw op2= genLike val2= genValue
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint444);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_genLike_in_constraint455);
                    op2=genLike();

                    state._fsp--;

                    c.setOp((op2!=null?input.toString(op2.start,op2.stop):null));
                    pushFollow(FOLLOW_genValue_in_constraint466);
                    val2=genValue();

                    state._fsp--;

                    c.setValue((val2!=null?input.toString(val2.start,val2.stop):null)); constraints.add(c);

                    }
                    break;
                case 4 :
                    // Sql.g:90:3: kw= simpleKw op3= between val3= betValue
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint481);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_between_in_constraint492);
                    op3=between();

                    state._fsp--;

                    c.setOp((op3!=null?input.toString(op3.start,op3.stop):null));
                    pushFollow(FOLLOW_betValue_in_constraint502);
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
                    match(input,EQ,FOLLOW_EQ_in_compOpt533); 

                    }
                    break;
                case 2 :
                    // Sql.g:96:4: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt538); 

                    }
                    break;
                case 3 :
                    // Sql.g:97:4: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt543); 

                    }
                    break;
                case 4 :
                    // Sql.g:98:4: NOT EQ
                    {
                    match(input,NOT,FOLLOW_NOT_in_compOpt548); 
                    match(input,EQ,FOLLOW_EQ_in_compOpt550); 

                    }
                    break;
                case 5 :
                    // Sql.g:99:4: EQ GT
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt555); 
                    match(input,GT,FOLLOW_GT_in_compOpt557); 

                    }
                    break;
                case 6 :
                    // Sql.g:100:4: EQ LT
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt562); 
                    match(input,LT,FOLLOW_LT_in_compOpt564); 

                    }
                    break;
                case 7 :
                    // Sql.g:101:4: LT EQ
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt569); 
                    match(input,EQ,FOLLOW_EQ_in_compOpt571); 

                    }
                    break;
                case 8 :
                    // Sql.g:102:4: GT EQ
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt576); 
                    match(input,EQ,FOLLOW_EQ_in_compOpt578); 

                    }
                    break;
                case 9 :
                    // Sql.g:103:4: LT GT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt583); 
                    match(input,GT,FOLLOW_GT_in_compOpt585); 

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
    // Sql.g:104:1: genValue : ( VALUE | keyword ) ( DOT ( VALUE | keyword ) )* ( compOpt ( VALUE | keyword ) )* ;
    public final SqlParser.genValue_return genValue() throws RecognitionException {
        SqlParser.genValue_return retval = new SqlParser.genValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:104:10: ( ( VALUE | keyword ) ( DOT ( VALUE | keyword ) )* ( compOpt ( VALUE | keyword ) )* )
            // Sql.g:104:12: ( VALUE | keyword ) ( DOT ( VALUE | keyword ) )* ( compOpt ( VALUE | keyword ) )*
            {
            // Sql.g:104:12: ( VALUE | keyword )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==VALUE) ) {
                alt14=1;
            }
            else if ( ((LA14_0>=15 && LA14_0<=103)) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // Sql.g:104:13: VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_genValue593); 

                    }
                    break;
                case 2 :
                    // Sql.g:104:21: keyword
                    {
                    pushFollow(FOLLOW_keyword_in_genValue597);
                    keyword();

                    state._fsp--;


                    }
                    break;

            }

            // Sql.g:104:30: ( DOT ( VALUE | keyword ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==DOT) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // Sql.g:104:31: DOT ( VALUE | keyword )
            	    {
            	    match(input,DOT,FOLLOW_DOT_in_genValue601); 
            	    // Sql.g:104:35: ( VALUE | keyword )
            	    int alt15=2;
            	    int LA15_0 = input.LA(1);

            	    if ( (LA15_0==VALUE) ) {
            	        alt15=1;
            	    }
            	    else if ( ((LA15_0>=15 && LA15_0<=103)) ) {
            	        alt15=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 15, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt15) {
            	        case 1 :
            	            // Sql.g:104:36: VALUE
            	            {
            	            match(input,VALUE,FOLLOW_VALUE_in_genValue604); 

            	            }
            	            break;
            	        case 2 :
            	            // Sql.g:104:44: keyword
            	            {
            	            pushFollow(FOLLOW_keyword_in_genValue608);
            	            keyword();

            	            state._fsp--;


            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            // Sql.g:104:55: ( compOpt ( VALUE | keyword ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=EQ && LA18_0<=NOT)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // Sql.g:104:56: compOpt ( VALUE | keyword )
            	    {
            	    pushFollow(FOLLOW_compOpt_in_genValue614);
            	    compOpt();

            	    state._fsp--;

            	    // Sql.g:104:64: ( VALUE | keyword )
            	    int alt17=2;
            	    int LA17_0 = input.LA(1);

            	    if ( (LA17_0==VALUE) ) {
            	        alt17=1;
            	    }
            	    else if ( ((LA17_0>=15 && LA17_0<=103)) ) {
            	        alt17=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 17, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt17) {
            	        case 1 :
            	            // Sql.g:104:65: VALUE
            	            {
            	            match(input,VALUE,FOLLOW_VALUE_in_genValue617); 

            	            }
            	            break;
            	        case 2 :
            	            // Sql.g:104:73: keyword
            	            {
            	            pushFollow(FOLLOW_keyword_in_genValue621);
            	            keyword();

            	            state._fsp--;


            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop18;
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
            pushFollow(FOLLOW_genValue_in_betValue633);
            genValue();

            state._fsp--;

            pushFollow(FOLLOW_and_in_betValue635);
            and();

            state._fsp--;

            pushFollow(FOLLOW_genValue_in_betValue637);
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
            pushFollow(FOLLOW_genValue_in_inValue644);
            genValue();

            state._fsp--;

            // Sql.g:108:20: ( COMMA genValue )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==COMMA) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // Sql.g:108:22: COMMA genValue
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_inValue648); 
            	    pushFollow(FOLLOW_genValue_in_inValue650);
            	    genValue();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
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
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=82 && LA20_0<=83)) ) {
                alt20=1;
            }
            else if ( ((LA20_0>=88 && LA20_0<=89)) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // Sql.g:109:12: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp659);
                    and();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:109:16: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp661);
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


    // $ANTLR start "keyword"
    // Sql.g:111:1: keyword : ( entity | attr | funct | select | and | order | by | or | in | not | like | asc | desc | between | where );
    public final void keyword() throws RecognitionException {
        try {
            // Sql.g:111:10: ( entity | attr | funct | select | and | order | by | or | in | not | like | asc | desc | between | where )
            int alt21=15;
            alt21 = dfa21.predict(input);
            switch (alt21) {
                case 1 :
                    // Sql.g:111:12: entity
                    {
                    pushFollow(FOLLOW_entity_in_keyword670);
                    entity();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:111:21: attr
                    {
                    pushFollow(FOLLOW_attr_in_keyword674);
                    attr();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // Sql.g:111:28: funct
                    {
                    pushFollow(FOLLOW_funct_in_keyword678);
                    funct();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // Sql.g:111:36: select
                    {
                    pushFollow(FOLLOW_select_in_keyword682);
                    select();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // Sql.g:111:45: and
                    {
                    pushFollow(FOLLOW_and_in_keyword686);
                    and();

                    state._fsp--;


                    }
                    break;
                case 6 :
                    // Sql.g:111:51: order
                    {
                    pushFollow(FOLLOW_order_in_keyword690);
                    order();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // Sql.g:111:59: by
                    {
                    pushFollow(FOLLOW_by_in_keyword694);
                    by();

                    state._fsp--;


                    }
                    break;
                case 8 :
                    // Sql.g:111:64: or
                    {
                    pushFollow(FOLLOW_or_in_keyword698);
                    or();

                    state._fsp--;


                    }
                    break;
                case 9 :
                    // Sql.g:111:69: in
                    {
                    pushFollow(FOLLOW_in_in_keyword702);
                    in();

                    state._fsp--;


                    }
                    break;
                case 10 :
                    // Sql.g:111:74: not
                    {
                    pushFollow(FOLLOW_not_in_keyword706);
                    not();

                    state._fsp--;


                    }
                    break;
                case 11 :
                    // Sql.g:111:80: like
                    {
                    pushFollow(FOLLOW_like_in_keyword710);
                    like();

                    state._fsp--;


                    }
                    break;
                case 12 :
                    // Sql.g:111:87: asc
                    {
                    pushFollow(FOLLOW_asc_in_keyword714);
                    asc();

                    state._fsp--;


                    }
                    break;
                case 13 :
                    // Sql.g:111:93: desc
                    {
                    pushFollow(FOLLOW_desc_in_keyword718);
                    desc();

                    state._fsp--;


                    }
                    break;
                case 14 :
                    // Sql.g:111:100: between
                    {
                    pushFollow(FOLLOW_between_in_keyword722);
                    between();

                    state._fsp--;


                    }
                    break;
                case 15 :
                    // Sql.g:111:110: where
                    {
                    pushFollow(FOLLOW_where_in_keyword726);
                    where();

                    state._fsp--;


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
    // $ANTLR end "keyword"


    // $ANTLR start "entity"
    // Sql.g:113:1: entity : ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch' );
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:113:9: ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch' )
            // Sql.g:
            {
            if ( (input.LA(1)>=15 && input.LA(1)<=36) ) {
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
    // Sql.g:114:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' | 'checksum' );
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:114:7: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' | 'checksum' )
            // Sql.g:
            {
            if ( (input.LA(1)>=17 && input.LA(1)<=19)||(input.LA(1)>=37 && input.LA(1)<=70) ) {
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
    // Sql.g:115:1: funct : ( 'count' | 'sum' | 'max' | 'min' | 'avg' | 'COUNT' | 'SUM' | 'MAX' | 'MIN' | 'AVG' );
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:115:8: ( 'count' | 'sum' | 'max' | 'min' | 'avg' | 'COUNT' | 'SUM' | 'MAX' | 'MIN' | 'AVG' )
            // Sql.g:
            {
            if ( input.LA(1)==54||(input.LA(1)>=71 && input.LA(1)<=79) ) {
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
    // Sql.g:117:1: select : ( 'find' | 'FIND' );
    public final void select() throws RecognitionException {
        try {
            // Sql.g:117:9: ( 'find' | 'FIND' )
            // Sql.g:
            {
            if ( (input.LA(1)>=80 && input.LA(1)<=81) ) {
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
    // Sql.g:118:1: and : ( 'and' | 'AND' );
    public final void and() throws RecognitionException {
        try {
            // Sql.g:118:6: ( 'and' | 'AND' )
            // Sql.g:
            {
            if ( (input.LA(1)>=82 && input.LA(1)<=83) ) {
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
    // Sql.g:119:1: order : ( 'order' | 'ORDER' );
    public final void order() throws RecognitionException {
        try {
            // Sql.g:119:8: ( 'order' | 'ORDER' )
            // Sql.g:
            {
            if ( (input.LA(1)>=84 && input.LA(1)<=85) ) {
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
    // Sql.g:120:1: by : ( 'by' | 'BY' );
    public final void by() throws RecognitionException {
        try {
            // Sql.g:120:5: ( 'by' | 'BY' )
            // Sql.g:
            {
            if ( (input.LA(1)>=86 && input.LA(1)<=87) ) {
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
    // Sql.g:121:1: or : ( 'or' | 'OR' );
    public final void or() throws RecognitionException {
        try {
            // Sql.g:121:5: ( 'or' | 'OR' )
            // Sql.g:
            {
            if ( (input.LA(1)>=88 && input.LA(1)<=89) ) {
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
    // Sql.g:122:1: in : ( 'in' | 'IN' );
    public final SqlParser.in_return in() throws RecognitionException {
        SqlParser.in_return retval = new SqlParser.in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:122:5: ( 'in' | 'IN' )
            // Sql.g:
            {
            if ( (input.LA(1)>=90 && input.LA(1)<=91) ) {
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
    // Sql.g:123:1: not : ( 'not' | 'NOT' );
    public final void not() throws RecognitionException {
        try {
            // Sql.g:123:6: ( 'not' | 'NOT' )
            // Sql.g:
            {
            if ( (input.LA(1)>=92 && input.LA(1)<=93) ) {
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
    // Sql.g:124:1: like : ( 'like' | 'LIKE' );
    public final void like() throws RecognitionException {
        try {
            // Sql.g:124:7: ( 'like' | 'LIKE' )
            // Sql.g:
            {
            if ( (input.LA(1)>=94 && input.LA(1)<=95) ) {
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
    // Sql.g:125:1: notLike : not like ;
    public final void notLike() throws RecognitionException {
        try {
            // Sql.g:125:10: ( not like )
            // Sql.g:125:12: not like
            {
            pushFollow(FOLLOW_not_in_notLike1107);
            not();

            state._fsp--;

            pushFollow(FOLLOW_like_in_notLike1109);
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
    // Sql.g:126:1: genLike : ( like | notLike );
    public final SqlParser.genLike_return genLike() throws RecognitionException {
        SqlParser.genLike_return retval = new SqlParser.genLike_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:126:10: ( like | notLike )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=94 && LA22_0<=95)) ) {
                alt22=1;
            }
            else if ( ((LA22_0>=92 && LA22_0<=93)) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // Sql.g:126:12: like
                    {
                    pushFollow(FOLLOW_like_in_genLike1117);
                    like();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:126:19: notLike
                    {
                    pushFollow(FOLLOW_notLike_in_genLike1121);
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
    // Sql.g:127:1: asc : ( 'asc' | 'ASC' );
    public final void asc() throws RecognitionException {
        try {
            // Sql.g:127:6: ( 'asc' | 'ASC' )
            // Sql.g:
            {
            if ( (input.LA(1)>=96 && input.LA(1)<=97) ) {
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
    // Sql.g:128:1: desc : ( 'desc' | 'DESC' );
    public final void desc() throws RecognitionException {
        try {
            // Sql.g:128:7: ( 'desc' | 'DESC' )
            // Sql.g:
            {
            if ( (input.LA(1)>=98 && input.LA(1)<=99) ) {
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
    // Sql.g:129:1: between : ( 'between' | 'BETWEEN' );
    public final SqlParser.between_return between() throws RecognitionException {
        SqlParser.between_return retval = new SqlParser.between_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:129:10: ( 'between' | 'BETWEEN' )
            // Sql.g:
            {
            if ( (input.LA(1)>=100 && input.LA(1)<=101) ) {
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
    // Sql.g:132:1: where : ( 'where' | 'WHERE' );
    public final void where() throws RecognitionException {
        try {
            // Sql.g:132:8: ( 'where' | 'WHERE' )
            // Sql.g:
            {
            if ( (input.LA(1)>=102 && input.LA(1)<=103) ) {
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
    protected DFA21 dfa21 = new DFA21(this);
    static final String DFA3_eotS =
        "\12\uffff";
    static final String DFA3_eofS =
        "\1\uffff\1\5\4\uffff\2\5\1\uffff\1\5";
    static final String DFA3_minS =
        "\1\17\1\4\1\21\1\17\2\uffff\2\4\1\21\1\4";
    static final String DFA3_maxS =
        "\1\44\1\143\1\106\1\44\2\uffff\2\143\1\106\1\143";
    static final String DFA3_acceptS =
        "\4\uffff\1\2\1\1\4\uffff";
    static final String DFA3_specialS =
        "\12\uffff}>";
    static final String[] DFA3_transitionS = {
            "\26\1",
            "\1\3\1\2\10\uffff\1\5\121\uffff\4\4",
            "\3\6\21\uffff\42\6",
            "\26\7",
            "",
            "",
            "\1\3\11\uffff\1\5\121\uffff\4\4",
            "\1\3\1\10\10\uffff\1\5\121\uffff\4\4",
            "\3\11\21\uffff\42\11",
            "\1\3\11\uffff\1\5\121\uffff\4\4"
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
        "\1\13\3\147\11\uffff";
    static final String DFA13_acceptS =
        "\4\uffff\1\4\1\5\1\6\1\1\1\7\1\11\1\2\1\10\1\3";
    static final String DFA13_specialS =
        "\15\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\1\1\2\1\3\1\4",
            "\1\6\1\5\1\uffff\1\7\2\uffff\131\7",
            "\1\10\1\uffff\1\11\1\uffff\1\12\2\uffff\131\12",
            "\1\13\3\uffff\1\14\2\uffff\131\14",
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
    static final String DFA21_eotS =
        "\20\uffff";
    static final String DFA21_eofS =
        "\20\uffff";
    static final String DFA21_minS =
        "\1\17\17\uffff";
    static final String DFA21_maxS =
        "\1\147\17\uffff";
    static final String DFA21_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17";
    static final String DFA21_specialS =
        "\20\uffff}>";
    static final String[] DFA21_transitionS = {
            "\26\1\42\2\11\3\2\4\2\5\2\6\2\7\2\10\2\11\2\12\2\13\2\14\2\15"+
            "\2\16\2\17",
            "",
            "",
            "",
            "",
            "",
            "",
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

    static final short[] DFA21_eot = DFA.unpackEncodedString(DFA21_eotS);
    static final short[] DFA21_eof = DFA.unpackEncodedString(DFA21_eofS);
    static final char[] DFA21_min = DFA.unpackEncodedStringToUnsignedChars(DFA21_minS);
    static final char[] DFA21_max = DFA.unpackEncodedStringToUnsignedChars(DFA21_maxS);
    static final short[] DFA21_accept = DFA.unpackEncodedString(DFA21_acceptS);
    static final short[] DFA21_special = DFA.unpackEncodedString(DFA21_specialS);
    static final short[][] DFA21_transition;

    static {
        int numStates = DFA21_transitionS.length;
        DFA21_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA21_transition[i] = DFA.unpackEncodedString(DFA21_transitionS[i]);
        }
    }

    class DFA21 extends DFA {

        public DFA21(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 21;
            this.eot = DFA21_eot;
            this.eof = DFA21_eof;
            this.min = DFA21_min;
            this.max = DFA21_max;
            this.accept = DFA21_accept;
            this.special = DFA21_special;
            this.transition = DFA21_transition;
        }
        public String getDescription() {
            return "111:1: keyword : ( entity | attr | funct | select | and | order | by | or | in | not | like | asc | desc | between | where );";
        }
    }
 

    public static final BitSet FOLLOW_select_in_stmt28 = new BitSet(new long[]{0x0040001FFFFF8000L,0x000000000000FF80L});
    public static final BitSet FOLLOW_selectList_in_stmt30 = new BitSet(new long[]{0x0000000000004000L,0x000000C000300000L});
    public static final BitSet FOLLOW_where_in_stmt33 = new BitSet(new long[]{0x0000001FFFFF8040L});
    public static final BitSet FOLLOW_constraintList_in_stmt35 = new BitSet(new long[]{0x0000000000004000L,0x0000000000300000L});
    public static final BitSet FOLLOW_order_in_stmt40 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
    public static final BitSet FOLLOW_by_in_stmt42 = new BitSet(new long[]{0x0000001FFFFF8000L});
    public static final BitSet FOLLOW_orderList_in_stmt44 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_set_in_stmt50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orderList1_in_orderList67 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orderList1_in_orderList73 = new BitSet(new long[]{0x0000000000000000L,0x0000000F00000000L});
    public static final BitSet FOLLOW_ordering_in_orderList82 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_orderList193 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_COMMA_in_orderList1106 = new BitSet(new long[]{0x0000001FFFFF8000L});
    public static final BitSet FOLLOW_simpleKw_in_orderList1113 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_asc_in_ordering137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_desc_in_ordering139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_slkeyword_in_selectList150 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_COMMA_in_selectList163 = new BitSet(new long[]{0x0040001FFFFF8000L,0x000000000000FF80L});
    public static final BitSet FOLLOW_slkeyword_in_selectList171 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_entity_in_simpleKw196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_simpleKw202 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_DOT_in_simpleKw204 = new BitSet(new long[]{0xFFFFFFE0000E0000L,0x000000000000007FL});
    public static final BitSet FOLLOW_attr_in_simpleKw206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_slkeyword213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funct_in_slkeyword219 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LB_in_slkeyword221 = new BitSet(new long[]{0x0000001FFFFF8000L});
    public static final BitSet FOLLOW_simpleKw_in_slkeyword223 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RB_in_slkeyword225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LB_in_lopen238 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_RB_in_ropen249 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_constraint1_in_constraintList260 = new BitSet(new long[]{0x0000000000000002L,0x00000000030C0000L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList274 = new BitSet(new long[]{0x0000001FFFFF8040L});
    public static final BitSet FOLLOW_constraint1_in_constraintList281 = new BitSet(new long[]{0x0000000000000002L,0x00000000030C0000L});
    public static final BitSet FOLLOW_lopen_in_constraint1297 = new BitSet(new long[]{0x0000001FFFFF8040L});
    public static final BitSet FOLLOW_constraint_in_constraint1303 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ropen_in_constraint1314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint331 = new BitSet(new long[]{0x0000000000000F00L});
    public static final BitSet FOLLOW_compOpt_in_constraint342 = new BitSet(new long[]{0xFFFFFFFFFFFF9000L,0x000000FFFFFFFFFFL});
    public static final BitSet FOLLOW_genValue_in_constraint354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint382 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
    public static final BitSet FOLLOW_in_in_constraint393 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LB_in_constraint404 = new BitSet(new long[]{0xFFFFFFFFFFFF9000L,0x000000FFFFFFFFFFL});
    public static final BitSet FOLLOW_inValue_in_constraint410 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RB_in_constraint418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint444 = new BitSet(new long[]{0x0000000000000000L,0x00000000F0000000L});
    public static final BitSet FOLLOW_genLike_in_constraint455 = new BitSet(new long[]{0xFFFFFFFFFFFF9000L,0x000000FFFFFFFFFFL});
    public static final BitSet FOLLOW_genValue_in_constraint466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint481 = new BitSet(new long[]{0x0000000000000000L,0x0000003000000000L});
    public static final BitSet FOLLOW_between_in_constraint492 = new BitSet(new long[]{0xFFFFFFFFFFFF9000L,0x000000FFFFFFFFFFL});
    public static final BitSet FOLLOW_betValue_in_constraint502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_compOpt548 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt555 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt562 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LT_in_compOpt564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt569 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt576 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt583 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_genValue593 = new BitSet(new long[]{0x0000000000000F22L});
    public static final BitSet FOLLOW_keyword_in_genValue597 = new BitSet(new long[]{0x0000000000000F22L});
    public static final BitSet FOLLOW_DOT_in_genValue601 = new BitSet(new long[]{0xFFFFFFFFFFFF9000L,0x000000FFFFFFFFFFL});
    public static final BitSet FOLLOW_VALUE_in_genValue604 = new BitSet(new long[]{0x0000000000000F22L});
    public static final BitSet FOLLOW_keyword_in_genValue608 = new BitSet(new long[]{0x0000000000000F22L});
    public static final BitSet FOLLOW_compOpt_in_genValue614 = new BitSet(new long[]{0xFFFFFFFFFFFF9000L,0x000000FFFFFFFFFFL});
    public static final BitSet FOLLOW_VALUE_in_genValue617 = new BitSet(new long[]{0x0000000000000F02L});
    public static final BitSet FOLLOW_keyword_in_genValue621 = new BitSet(new long[]{0x0000000000000F02L});
    public static final BitSet FOLLOW_genValue_in_betValue633 = new BitSet(new long[]{0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_and_in_betValue635 = new BitSet(new long[]{0xFFFFFFFFFFFF9000L,0x000000FFFFFFFFFFL});
    public static final BitSet FOLLOW_genValue_in_betValue637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_genValue_in_inValue644 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_COMMA_in_inValue648 = new BitSet(new long[]{0xFFFFFFFFFFFF9000L,0x000000FFFFFFFFFFL});
    public static final BitSet FOLLOW_genValue_in_inValue650 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_and_in_logicalOp659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attr_in_keyword674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funct_in_keyword678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_keyword682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_in_keyword686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_order_in_keyword690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_by_in_keyword694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_keyword698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_in_keyword702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_in_keyword706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_keyword710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_asc_in_keyword714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_desc_in_keyword718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_between_in_keyword722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_where_in_keyword726 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_not_in_notLike1107 = new BitSet(new long[]{0x0000000000000000L,0x00000000C0000000L});
    public static final BitSet FOLLOW_like_in_notLike1109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_genLike1117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_notLike_in_genLike1121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_asc0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_desc0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_between0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where0 = new BitSet(new long[]{0x0000000000000002L});

}