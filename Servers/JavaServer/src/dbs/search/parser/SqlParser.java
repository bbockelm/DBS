package dbs.search.parser;
// $ANTLR 3.1.2 Sql.g 2009-05-13 13:32:53


import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMA", "DOT", "LB", "RB", "EQ", "LT", "GT", "NOT", "VALUE", "WS", "'ads'", "'config'", "'dataset'", "'release'", "'tier'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'lumi'", "'dq'", "'ilumi'", "'phygrp'", "'group'", "'pset'", "'algo'", "'datatype'", "'mcdesc'", "'trigdesc'", "'branch'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numfiles'", "'numlss'", "'totlumi'", "'store'", "'size'", "'count'", "'status'", "'type'", "'id'", "'parent'", "'child'", "'def'", "'evnum'", "'era'", "'tag'", "'xsection'", "'hash'", "'content'", "'family'", "'exe'", "'annotation'", "'checksum'", "'sum'", "'COUNT'", "'SUM'", "'select'", "'SELECT'", "'find'", "'FIND'", "'and'", "'AND'", "'order'", "'ORDER'", "'by'", "'BY'", "'or'", "'OR'", "'in'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'", "'asc'", "'ASC'", "'desc'", "'DESC'", "'between'", "'BETWEEN'", "'where'", "'WHERE'"
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

            if ( ((LA1_0>=97 && LA1_0<=98)) ) {
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

            if ( ((LA2_0>=79 && LA2_0<=80)) ) {
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

            if ( ((LA5_0>=91 && LA5_0<=92)) ) {
                alt5=1;
            }
            else if ( ((LA5_0>=93 && LA5_0<=94)) ) {
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
                else if ( (LA7_1==EOF||LA7_1==COMMA||(LA7_1>=RB && LA7_1<=NOT)||(LA7_1>=79 && LA7_1<=80)||(LA7_1>=85 && LA7_1<=98)) ) {
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
            else if ( (LA8_0==53||(LA8_0>=70 && LA8_0<=72)) ) {
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


    // $ANTLR start "constraintList"
    // Sql.g:47:1: constraintList : constraint1 (rel= logicalOp constraint1 )* ;
    public final void constraintList() throws RecognitionException {
        SqlParser.logicalOp_return rel = null;


        try {
            // Sql.g:47:16: ( constraint1 (rel= logicalOp constraint1 )* )
            // Sql.g:47:18: constraint1 (rel= logicalOp constraint1 )*
            {
            pushFollow(FOLLOW_constraint1_in_constraintList221);
            constraint1();

            state._fsp--;

            // Sql.g:47:30: (rel= logicalOp constraint1 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=77 && LA9_0<=78)||(LA9_0>=83 && LA9_0<=84)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Sql.g:48:2: rel= logicalOp constraint1
            	    {
            	    pushFollow(FOLLOW_logicalOp_in_constraintList230);
            	    rel=logicalOp();

            	    state._fsp--;

            	     constraints.add((rel!=null?input.toString(rel.start,rel.stop):null));
            	    pushFollow(FOLLOW_constraint1_in_constraintList238);
            	    constraint1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
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

    public static class lopen_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "lopen"
    // Sql.g:50:1: lopen : LB ( LB )* ;
    public final SqlParser.lopen_return lopen() throws RecognitionException {
        SqlParser.lopen_return retval = new SqlParser.lopen_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:50:8: ( LB ( LB )* )
            // Sql.g:50:10: LB ( LB )*
            {
            match(input,LB,FOLLOW_LB_in_lopen248); 
            // Sql.g:50:12: ( LB )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==LB) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // Sql.g:50:13: LB
            	    {
            	    match(input,LB,FOLLOW_LB_in_lopen250); 

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
    // $ANTLR end "lopen"

    public static class ropen_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "ropen"
    // Sql.g:51:1: ropen : RB ( RB )* ;
    public final SqlParser.ropen_return ropen() throws RecognitionException {
        SqlParser.ropen_return retval = new SqlParser.ropen_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:51:8: ( RB ( RB )* )
            // Sql.g:51:10: RB ( RB )*
            {
            match(input,RB,FOLLOW_RB_in_ropen260); 
            // Sql.g:51:12: ( RB )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RB) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // Sql.g:51:13: RB
            	    {
            	    match(input,RB,FOLLOW_RB_in_ropen262); 

            	    }
            	    break;

            	default :
            	    break loop11;
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


    // $ANTLR start "constraint1"
    // Sql.g:52:1: constraint1 : (kl= lopen constraint (rel= logicalOp constraint )* kr= ropen | constraint );
    public final void constraint1() throws RecognitionException {
        SqlParser.lopen_return kl = null;

        SqlParser.logicalOp_return rel = null;

        SqlParser.ropen_return kr = null;


        try {
            // Sql.g:52:17: (kl= lopen constraint (rel= logicalOp constraint )* kr= ropen | constraint )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==LB) ) {
                alt13=1;
            }
            else if ( ((LA13_0>=14 && LA13_0<=35)) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // Sql.g:52:19: kl= lopen constraint (rel= logicalOp constraint )* kr= ropen
                    {
                    pushFollow(FOLLOW_lopen_in_constraint1280);
                    kl=lopen();

                    state._fsp--;

                    Constraint c1=new Constraint();c1.setBracket((kl!=null?input.toString(kl.start,kl.stop):null));constraints.add(c1);
                    pushFollow(FOLLOW_constraint_in_constraint1302);
                    constraint();

                    state._fsp--;

                    // Sql.g:54:3: (rel= logicalOp constraint )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>=77 && LA12_0<=78)||(LA12_0>=83 && LA12_0<=84)) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // Sql.g:55:3: rel= logicalOp constraint
                    	    {
                    	    pushFollow(FOLLOW_logicalOp_in_constraint1316);
                    	    rel=logicalOp();

                    	    state._fsp--;

                    	     constraints.add((rel!=null?input.toString(rel.start,rel.stop):null));
                    	    pushFollow(FOLLOW_constraint_in_constraint1336);
                    	    constraint();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    pushFollow(FOLLOW_ropen_in_constraint1366);
                    kr=ropen();

                    state._fsp--;

                    Constraint c=new Constraint();c.setBracket((kr!=null?input.toString(kr.start,kr.stop):null)); constraints.add(c);

                    }
                    break;
                case 2 :
                    // Sql.g:59:5: constraint
                    {
                    pushFollow(FOLLOW_constraint_in_constraint1376);
                    constraint();

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
    // $ANTLR end "constraint1"


    // $ANTLR start "constraint"
    // Sql.g:61:1: constraint : (kw= simpleKw op= compOpt val= genValue | kw= simpleKw op1= in LB val1= inValue RB | kw= simpleKw op2= genLike val2= genValue | kw= simpleKw op3= between val3= betValue );
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
            // Sql.g:61:12: (kw= simpleKw op= compOpt val= genValue | kw= simpleKw op1= in LB val1= inValue RB | kw= simpleKw op2= genLike val2= genValue | kw= simpleKw op3= between val3= betValue )
            int alt14=4;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=14 && LA14_0<=35)) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA14_2 = input.LA(3);

                    if ( ((LA14_2>=16 && LA14_2<=18)||(LA14_2>=36 && LA14_2<=69)) ) {
                        switch ( input.LA(4) ) {
                        case EQ:
                        case LT:
                        case GT:
                        case NOT:
                            {
                            alt14=1;
                            }
                            break;
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                            {
                            alt14=3;
                            }
                            break;
                        case 95:
                        case 96:
                            {
                            alt14=4;
                            }
                            break;
                        case 85:
                        case 86:
                            {
                            alt14=2;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 14, 7, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 14, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case 95:
                case 96:
                    {
                    alt14=4;
                    }
                    break;
                case 87:
                case 88:
                case 89:
                case 90:
                    {
                    alt14=3;
                    }
                    break;
                case 85:
                case 86:
                    {
                    alt14=2;
                    }
                    break;
                case EQ:
                case LT:
                case GT:
                case NOT:
                    {
                    alt14=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // Sql.g:62:2: kw= simpleKw op= compOpt val= genValue
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint388);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_compOpt_in_constraint399);
                    op=compOpt();

                    state._fsp--;

                    c.setOp((op!=null?input.toString(op.start,op.stop):null));
                    pushFollow(FOLLOW_genValue_in_constraint411);
                    val=genValue();

                    state._fsp--;

                    c.setValue((val!=null?input.toString(val.start,val.stop):null)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:66:2: kw= simpleKw op1= in LB val1= inValue RB
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint439);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_in_in_constraint450);
                    op1=in();

                    state._fsp--;

                    c.setOp((op1!=null?input.toString(op1.start,op1.stop):null));
                    match(input,LB,FOLLOW_LB_in_constraint461); 
                    pushFollow(FOLLOW_inValue_in_constraint467);
                    val1=inValue();

                    state._fsp--;

                    c.setValue((val1!=null?input.toString(val1.start,val1.stop):null)); constraints.add(c);
                    match(input,RB,FOLLOW_RB_in_constraint475); 

                    }
                    break;
                case 3 :
                    // Sql.g:72:2: kw= simpleKw op2= genLike val2= genValue
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint501);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_genLike_in_constraint512);
                    op2=genLike();

                    state._fsp--;

                    c.setOp((op2!=null?input.toString(op2.start,op2.stop):null));
                    pushFollow(FOLLOW_genValue_in_constraint523);
                    val2=genValue();

                    state._fsp--;

                    c.setValue((val2!=null?input.toString(val2.start,val2.stop):null)); constraints.add(c);

                    }
                    break;
                case 4 :
                    // Sql.g:76:3: kw= simpleKw op3= between val3= betValue
                    {
                    pushFollow(FOLLOW_simpleKw_in_constraint538);
                    kw=simpleKw();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_between_in_constraint549);
                    op3=between();

                    state._fsp--;

                    c.setOp((op3!=null?input.toString(op3.start,op3.stop):null));
                    pushFollow(FOLLOW_betValue_in_constraint559);
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
    // Sql.g:81:1: compOpt : ( EQ | LT | GT | NOT EQ | EQ GT | EQ LT | LT EQ | GT EQ | LT GT );
    public final SqlParser.compOpt_return compOpt() throws RecognitionException {
        SqlParser.compOpt_return retval = new SqlParser.compOpt_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:81:10: ( EQ | LT | GT | NOT EQ | EQ GT | EQ LT | LT EQ | GT EQ | LT GT )
            int alt15=9;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // Sql.g:81:11: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt590); 

                    }
                    break;
                case 2 :
                    // Sql.g:82:4: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt595); 

                    }
                    break;
                case 3 :
                    // Sql.g:83:4: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt600); 

                    }
                    break;
                case 4 :
                    // Sql.g:84:4: NOT EQ
                    {
                    match(input,NOT,FOLLOW_NOT_in_compOpt605); 
                    match(input,EQ,FOLLOW_EQ_in_compOpt607); 

                    }
                    break;
                case 5 :
                    // Sql.g:85:4: EQ GT
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt612); 
                    match(input,GT,FOLLOW_GT_in_compOpt614); 

                    }
                    break;
                case 6 :
                    // Sql.g:86:4: EQ LT
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt619); 
                    match(input,LT,FOLLOW_LT_in_compOpt621); 

                    }
                    break;
                case 7 :
                    // Sql.g:87:4: LT EQ
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt626); 
                    match(input,EQ,FOLLOW_EQ_in_compOpt628); 

                    }
                    break;
                case 8 :
                    // Sql.g:88:4: GT EQ
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt633); 
                    match(input,EQ,FOLLOW_EQ_in_compOpt635); 

                    }
                    break;
                case 9 :
                    // Sql.g:89:4: LT GT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt640); 
                    match(input,GT,FOLLOW_GT_in_compOpt642); 

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
    // Sql.g:90:1: genValue : VALUE ( VALUE )* ( DOT VALUE )* ( compOpt VALUE )* ;
    public final SqlParser.genValue_return genValue() throws RecognitionException {
        SqlParser.genValue_return retval = new SqlParser.genValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:90:10: ( VALUE ( VALUE )* ( DOT VALUE )* ( compOpt VALUE )* )
            // Sql.g:90:12: VALUE ( VALUE )* ( DOT VALUE )* ( compOpt VALUE )*
            {
            match(input,VALUE,FOLLOW_VALUE_in_genValue649); 
            // Sql.g:90:18: ( VALUE )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==VALUE) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // Sql.g:90:19: VALUE
            	    {
            	    match(input,VALUE,FOLLOW_VALUE_in_genValue652); 

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            // Sql.g:90:27: ( DOT VALUE )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==DOT) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // Sql.g:90:28: DOT VALUE
            	    {
            	    match(input,DOT,FOLLOW_DOT_in_genValue657); 
            	    match(input,VALUE,FOLLOW_VALUE_in_genValue659); 

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            // Sql.g:90:40: ( compOpt VALUE )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=EQ && LA18_0<=NOT)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // Sql.g:90:41: compOpt VALUE
            	    {
            	    pushFollow(FOLLOW_compOpt_in_genValue664);
            	    compOpt();

            	    state._fsp--;

            	    match(input,VALUE,FOLLOW_VALUE_in_genValue666); 

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
    // Sql.g:93:1: betValue : genValue and genValue ;
    public final SqlParser.betValue_return betValue() throws RecognitionException {
        SqlParser.betValue_return retval = new SqlParser.betValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:93:10: ( genValue and genValue )
            // Sql.g:93:11: genValue and genValue
            {
            pushFollow(FOLLOW_genValue_in_betValue677);
            genValue();

            state._fsp--;

            pushFollow(FOLLOW_and_in_betValue679);
            and();

            state._fsp--;

            pushFollow(FOLLOW_genValue_in_betValue681);
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
    // Sql.g:94:1: inValue : genValue ( COMMA genValue )* ;
    public final SqlParser.inValue_return inValue() throws RecognitionException {
        SqlParser.inValue_return retval = new SqlParser.inValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:94:10: ( genValue ( COMMA genValue )* )
            // Sql.g:94:11: genValue ( COMMA genValue )*
            {
            pushFollow(FOLLOW_genValue_in_inValue688);
            genValue();

            state._fsp--;

            // Sql.g:94:20: ( COMMA genValue )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==COMMA) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // Sql.g:94:22: COMMA genValue
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_inValue692); 
            	    pushFollow(FOLLOW_genValue_in_inValue694);
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
    // Sql.g:95:1: logicalOp : ( and | or );
    public final SqlParser.logicalOp_return logicalOp() throws RecognitionException {
        SqlParser.logicalOp_return retval = new SqlParser.logicalOp_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:95:11: ( and | or )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=77 && LA20_0<=78)) ) {
                alt20=1;
            }
            else if ( ((LA20_0>=83 && LA20_0<=84)) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // Sql.g:95:12: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp703);
                    and();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:95:16: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp705);
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
    // Sql.g:97:1: entity : ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch' );
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:97:9: ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch' )
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
    // Sql.g:98:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' | 'checksum' );
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:98:7: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' | 'checksum' )
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
    // Sql.g:99:1: funct : ( 'count' | 'sum' | 'COUNT' | 'SUM' );
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:99:8: ( 'count' | 'sum' | 'COUNT' | 'SUM' )
            // Sql.g:
            {
            if ( input.LA(1)==53||(input.LA(1)>=70 && input.LA(1)<=72) ) {
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
    // Sql.g:100:1: select : ( 'select' | 'SELECT' | 'find' | 'FIND' );
    public final void select() throws RecognitionException {
        try {
            // Sql.g:100:9: ( 'select' | 'SELECT' | 'find' | 'FIND' )
            // Sql.g:
            {
            if ( (input.LA(1)>=73 && input.LA(1)<=76) ) {
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
    // Sql.g:101:1: and : ( 'and' | 'AND' );
    public final void and() throws RecognitionException {
        try {
            // Sql.g:101:6: ( 'and' | 'AND' )
            // Sql.g:
            {
            if ( (input.LA(1)>=77 && input.LA(1)<=78) ) {
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
    // Sql.g:102:1: order : ( 'order' | 'ORDER' );
    public final void order() throws RecognitionException {
        try {
            // Sql.g:102:8: ( 'order' | 'ORDER' )
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
    // $ANTLR end "order"


    // $ANTLR start "by"
    // Sql.g:103:1: by : ( 'by' | 'BY' );
    public final void by() throws RecognitionException {
        try {
            // Sql.g:103:5: ( 'by' | 'BY' )
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
    // $ANTLR end "by"


    // $ANTLR start "or"
    // Sql.g:104:1: or : ( 'or' | 'OR' );
    public final void or() throws RecognitionException {
        try {
            // Sql.g:104:5: ( 'or' | 'OR' )
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
    // $ANTLR end "or"

    public static class in_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "in"
    // Sql.g:105:1: in : ( 'in' | 'IN' );
    public final SqlParser.in_return in() throws RecognitionException {
        SqlParser.in_return retval = new SqlParser.in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:105:5: ( 'in' | 'IN' )
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
    // Sql.g:106:1: not : ( 'not' | 'NOT' );
    public final void not() throws RecognitionException {
        try {
            // Sql.g:106:6: ( 'not' | 'NOT' )
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
    // $ANTLR end "not"


    // $ANTLR start "like"
    // Sql.g:107:1: like : ( 'like' | 'LIKE' );
    public final void like() throws RecognitionException {
        try {
            // Sql.g:107:7: ( 'like' | 'LIKE' )
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
    // $ANTLR end "like"


    // $ANTLR start "notLike"
    // Sql.g:108:1: notLike : not like ;
    public final void notLike() throws RecognitionException {
        try {
            // Sql.g:108:10: ( not like )
            // Sql.g:108:12: not like
            {
            pushFollow(FOLLOW_not_in_notLike1069);
            not();

            state._fsp--;

            pushFollow(FOLLOW_like_in_notLike1071);
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
    // Sql.g:109:1: genLike : ( like | notLike );
    public final SqlParser.genLike_return genLike() throws RecognitionException {
        SqlParser.genLike_return retval = new SqlParser.genLike_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:109:10: ( like | notLike )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0>=89 && LA21_0<=90)) ) {
                alt21=1;
            }
            else if ( ((LA21_0>=87 && LA21_0<=88)) ) {
                alt21=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // Sql.g:109:12: like
                    {
                    pushFollow(FOLLOW_like_in_genLike1079);
                    like();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:109:19: notLike
                    {
                    pushFollow(FOLLOW_notLike_in_genLike1083);
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
    // Sql.g:110:1: asc : ( 'asc' | 'ASC' );
    public final void asc() throws RecognitionException {
        try {
            // Sql.g:110:6: ( 'asc' | 'ASC' )
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
    // $ANTLR end "asc"


    // $ANTLR start "desc"
    // Sql.g:111:1: desc : ( 'desc' | 'DESC' );
    public final void desc() throws RecognitionException {
        try {
            // Sql.g:111:7: ( 'desc' | 'DESC' )
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
    // $ANTLR end "desc"

    public static class between_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "between"
    // Sql.g:112:1: between : ( 'between' | 'BETWEEN' );
    public final SqlParser.between_return between() throws RecognitionException {
        SqlParser.between_return retval = new SqlParser.between_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:112:10: ( 'between' | 'BETWEEN' )
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
    // Sql.g:115:1: where : ( 'where' | 'WHERE' );
    public final void where() throws RecognitionException {
        try {
            // Sql.g:115:8: ( 'where' | 'WHERE' )
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
    // $ANTLR end "where"

    // Delegated rules


    protected DFA3 dfa3 = new DFA3(this);
    protected DFA15 dfa15 = new DFA15(this);
    static final String DFA3_eotS =
        "\12\uffff";
    static final String DFA3_eofS =
        "\1\uffff\1\3\4\uffff\2\3\1\uffff\1\3";
    static final String DFA3_minS =
        "\1\16\1\4\1\16\1\uffff\1\20\1\uffff\2\4\1\20\1\4";
    static final String DFA3_maxS =
        "\1\43\1\136\1\43\1\uffff\1\105\1\uffff\2\136\1\105\1\136";
    static final String DFA3_acceptS =
        "\3\uffff\1\1\1\uffff\1\2\4\uffff";
    static final String DFA3_specialS =
        "\12\uffff}>";
    static final String[] DFA3_transitionS = {
            "\26\1",
            "\1\2\1\4\125\uffff\4\5",
            "\26\6",
            "",
            "\3\7\21\uffff\42\7",
            "",
            "\1\2\1\10\125\uffff\4\5",
            "\1\2\126\uffff\4\5",
            "\3\11\21\uffff\42\11",
            "\1\2\126\uffff\4\5"
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
    static final String DFA15_eotS =
        "\15\uffff";
    static final String DFA15_eofS =
        "\15\uffff";
    static final String DFA15_minS =
        "\1\10\1\11\2\10\11\uffff";
    static final String DFA15_maxS =
        "\1\13\3\14\11\uffff";
    static final String DFA15_acceptS =
        "\4\uffff\1\4\1\5\1\6\1\1\1\7\1\11\1\2\1\10\1\3";
    static final String DFA15_specialS =
        "\15\uffff}>";
    static final String[] DFA15_transitionS = {
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

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "81:1: compOpt : ( EQ | LT | GT | NOT EQ | EQ GT | EQ LT | LT EQ | GT EQ | LT GT );";
        }
    }
 

    public static final BitSet FOLLOW_select_in_stmt27 = new BitSet(new long[]{0x0020000FFFFFC000L,0x00000000000001C0L});
    public static final BitSet FOLLOW_selectList_in_stmt29 = new BitSet(new long[]{0x0000000000000002L,0x0000000600018000L});
    public static final BitSet FOLLOW_where_in_stmt32 = new BitSet(new long[]{0x0000000FFFFFC040L});
    public static final BitSet FOLLOW_constraintList_in_stmt34 = new BitSet(new long[]{0x0000000000000002L,0x0000000000018000L});
    public static final BitSet FOLLOW_order_in_stmt39 = new BitSet(new long[]{0x0000000000000000L,0x0000000000060000L});
    public static final BitSet FOLLOW_by_in_stmt41 = new BitSet(new long[]{0x0000000FFFFFC000L});
    public static final BitSet FOLLOW_orderList_in_stmt43 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orderList1_in_orderList54 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orderList1_in_orderList60 = new BitSet(new long[]{0x0000000000000000L,0x0000000078000000L});
    public static final BitSet FOLLOW_ordering_in_orderList69 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_orderList180 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_COMMA_in_orderList193 = new BitSet(new long[]{0x0000000FFFFFC000L});
    public static final BitSet FOLLOW_simpleKw_in_orderList1100 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_asc_in_ordering124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_desc_in_ordering126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_slkeyword_in_selectList137 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_COMMA_in_selectList150 = new BitSet(new long[]{0x0020000FFFFFC000L,0x00000000000001C0L});
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
    public static final BitSet FOLLOW_constraint1_in_constraintList221 = new BitSet(new long[]{0x0000000000000002L,0x0000000000186000L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList230 = new BitSet(new long[]{0x0000000FFFFFC040L});
    public static final BitSet FOLLOW_constraint1_in_constraintList238 = new BitSet(new long[]{0x0000000000000002L,0x0000000000186000L});
    public static final BitSet FOLLOW_LB_in_lopen248 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_LB_in_lopen250 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_RB_in_ropen260 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_RB_in_ropen262 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_lopen_in_constraint1280 = new BitSet(new long[]{0x0000000FFFFFC040L});
    public static final BitSet FOLLOW_constraint_in_constraint1302 = new BitSet(new long[]{0x0000000000000080L,0x0000000000186000L});
    public static final BitSet FOLLOW_logicalOp_in_constraint1316 = new BitSet(new long[]{0x0000000FFFFFC040L});
    public static final BitSet FOLLOW_constraint_in_constraint1336 = new BitSet(new long[]{0x0000000000000080L,0x0000000000186000L});
    public static final BitSet FOLLOW_ropen_in_constraint1366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_constraint1376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint388 = new BitSet(new long[]{0x0000000000000F00L});
    public static final BitSet FOLLOW_compOpt_in_constraint399 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_genValue_in_constraint411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint439 = new BitSet(new long[]{0x0000000000000000L,0x0000000000600000L});
    public static final BitSet FOLLOW_in_in_constraint450 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LB_in_constraint461 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_inValue_in_constraint467 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RB_in_constraint475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint501 = new BitSet(new long[]{0x0000000000000000L,0x0000000007800000L});
    public static final BitSet FOLLOW_genLike_in_constraint512 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_genValue_in_constraint523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleKw_in_constraint538 = new BitSet(new long[]{0x0000000000000000L,0x0000000180000000L});
    public static final BitSet FOLLOW_between_in_constraint549 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_betValue_in_constraint559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_compOpt605 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt612 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt619 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LT_in_compOpt621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt626 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt633 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt640 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_genValue649 = new BitSet(new long[]{0x0000000000001F22L});
    public static final BitSet FOLLOW_VALUE_in_genValue652 = new BitSet(new long[]{0x0000000000001F22L});
    public static final BitSet FOLLOW_DOT_in_genValue657 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_VALUE_in_genValue659 = new BitSet(new long[]{0x0000000000000F22L});
    public static final BitSet FOLLOW_compOpt_in_genValue664 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_VALUE_in_genValue666 = new BitSet(new long[]{0x0000000000000F02L});
    public static final BitSet FOLLOW_genValue_in_betValue677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000006000L});
    public static final BitSet FOLLOW_and_in_betValue679 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_genValue_in_betValue681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_genValue_in_inValue688 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_COMMA_in_inValue692 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_genValue_in_inValue694 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_and_in_logicalOp703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp705 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_not_in_notLike1069 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
    public static final BitSet FOLLOW_like_in_notLike1071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_genLike1079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_notLike_in_genLike1083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_asc0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_desc0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_between0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where0 = new BitSet(new long[]{0x0000000000000002L});

}