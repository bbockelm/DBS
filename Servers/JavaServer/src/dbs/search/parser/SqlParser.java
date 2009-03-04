package dbs.search.parser;
// $ANTLR 3.1.2 Sql.g 2009-03-04 12:49:29


import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SPACE", "COMMA", "DOT", "VALUE", "EQ", "LT", "GT", "NOT", "AMP", "NL", "WS", "'('", "')'", "'WHERE'", "'where'", "'in'", "'ads'", "'config'", "'dataset'", "'release'", "'tier'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'lumi'", "'dq'", "'ilumi'", "'phygrp'", "'group'", "'pset'", "'algo'", "'datatype'", "'mcdesc'", "'trigdesc'", "'branch'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numfiles'", "'numlss'", "'totlumi'", "'store'", "'size'", "'count'", "'status'", "'type'", "'id'", "'parent'", "'child'", "'def'", "'evnum'", "'era'", "'tag'", "'xsection'", "'hash'", "'content'", "'family'", "'exe'", "'annotation'", "'checksum'", "'numruns()'", "'numfiles()'", "'dataquality()'", "'latest()'", "'parentrelease()'", "'childrelease()'", "'intluminosity()'", "'findevents()'", "'select'", "'SELECT'", "'find'", "'FIND'", "'and'", "'AND'", "'order'", "'ORDER'", "'by'", "'BY'", "'or'", "'OR'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'", "'COUNT'", "'sum'", "'SUM'", "'asc'", "'ASC'", "'desc'", "'DESC'", "'between'", "'BETWEEN'"
    };
    public static final int COMMA=5;
    public static final int T__42=42;
    public static final int T__109=109;
    public static final int T__47=47;
    public static final int T__73=73;
    public static final int T__21=21;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int DOT=6;
    public static final int T__39=39;
    public static final int T__30=30;
    public static final int T__46=46;
    public static final int T__96=96;
    public static final int T__49=49;
    public static final int AMP=12;
    public static final int T__108=108;
    public static final int T__54=54;
    public static final int T__48=48;
    public static final int SPACE=4;
    public static final int T__89=89;
    public static final int T__20=20;
    public static final int WS=14;
    public static final int EQ=8;
    public static final int T__79=79;
    public static final int T__64=64;
    public static final int LT=9;
    public static final int T__44=44;
    public static final int T__66=66;
    public static final int T__92=92;
    public static final int T__88=88;
    public static final int T__22=22;
    public static final int T__90=90;
    public static final int T__63=63;
    public static final int T__43=43;
    public static final int T__91=91;
    public static final int T__40=40;
    public static final int T__85=85;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__60=60;
    public static final int T__41=41;
    public static final int T__93=93;
    public static final int T__86=86;
    public static final int T__28=28;
    public static final int T__23=23;
    public static final int T__57=57;
    public static final int T__94=94;
    public static final int T__51=51;
    public static final int T__80=80;
    public static final int T__100=100;
    public static final int T__69=69;
    public static final int T__95=95;
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__65=65;
    public static final int T__101=101;
    public static final int T__104=104;
    public static final int VALUE=7;
    public static final int T__107=107;
    public static final int T__67=67;
    public static final int T__87=87;
    public static final int T__106=106;
    public static final int T__74=74;
    public static final int NL=13;
    public static final int T__52=52;
    public static final int T__68=68;
    public static final int T__17=17;
    public static final int T__62=62;
    public static final int T__27=27;
    public static final int T__24=24;
    public static final int T__61=61;
    public static final int T__59=59;
    public static final int T__34=34;
    public static final int T__98=98;
    public static final int T__15=15;
    public static final int T__56=56;
    public static final int T__35=35;
    public static final int T__78=78;
    public static final int T__36=36;
    public static final int T__58=58;
    public static final int GT=10;
    public static final int T__99=99;
    public static final int T__33=33;
    public static final int T__77=77;
    public static final int T__45=45;
    public static final int T__29=29;
    public static final int T__55=55;
    public static final int T__103=103;
    public static final int T__84=84;
    public static final int T__97=97;
    public static final int T__105=105;
    public static final int T__75=75;
    public static final int T__31=31;
    public static final int EOF=-1;
    public static final int T__53=53;
    public static final int T__32=32;
    public static final int T__16=16;
    public static final int T__38=38;
    public static final int T__37=37;
    public static final int T__76=76;
    public static final int T__82=82;
    public static final int T__81=81;
    public static final int T__83=83;
    public static final int NOT=11;
    public static final int T__18=18;
    public static final int T__71=71;
    public static final int T__102=102;

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

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt29);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_selectList_in_stmt31);
                    selectList();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt33);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_where_in_stmt35);
                    where();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt37);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_constraintList_in_stmt39);
                    constraintList();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt41);
                    spaces();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:22:4: select spaces selectList spaces
                    {
                    pushFollow(FOLLOW_select_in_stmt47);
                    select();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt49);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_selectList_in_stmt51);
                    selectList();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt53);
                    spaces();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // Sql.g:23:4: select spaces selectList spaces order spaces by spaces orderList
                    {
                    pushFollow(FOLLOW_select_in_stmt58);
                    select();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt60);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_selectList_in_stmt62);
                    selectList();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt64);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_order_in_stmt66);
                    order();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt68);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_by_in_stmt70);
                    by();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt72);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_orderList_in_stmt74);
                    orderList();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // Sql.g:24:4: select spaces selectList spaces where spaces constraintList spaces order spaces by spaces orderList
                    {
                    pushFollow(FOLLOW_select_in_stmt79);
                    select();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt81);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_selectList_in_stmt83);
                    selectList();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt85);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_where_in_stmt87);
                    where();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt89);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_constraintList_in_stmt91);
                    constraintList();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt93);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_order_in_stmt95);
                    order();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt97);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_by_in_stmt99);
                    by();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_stmt101);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_orderList_in_stmt103);
                    orderList();

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
    // $ANTLR end "stmt"


    // $ANTLR start "spaces"
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
    // $ANTLR end "spaces"


    // $ANTLR start "orderList"
    // Sql.g:29:1: orderList : okw= keyword ( spaces COMMA spaces okw= keyword )* spaces oing= ordering ;
    public final void orderList() throws RecognitionException {
        SqlParser.keyword_return okw = null;

        SqlParser.ordering_return oing = null;


        try {
            // Sql.g:29:11: (okw= keyword ( spaces COMMA spaces okw= keyword )* spaces oing= ordering )
            // Sql.g:29:12: okw= keyword ( spaces COMMA spaces okw= keyword )* spaces oing= ordering
            {
            pushFollow(FOLLOW_keyword_in_orderList125);
            okw=keyword();

            state._fsp--;

            okws.add((okw!=null?input.toString(okw.start,okw.stop):null));
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

            	    state._fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_orderList142); 
            	    pushFollow(FOLLOW_spaces_in_orderList146);
            	    spaces();

            	    state._fsp--;

            	    pushFollow(FOLLOW_keyword_in_orderList153);
            	    okw=keyword();

            	    state._fsp--;

            	    okws.add((okw!=null?input.toString(okw.start,okw.stop):null));

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            pushFollow(FOLLOW_spaces_in_orderList168);
            spaces();

            state._fsp--;

            pushFollow(FOLLOW_ordering_in_orderList176);
            oing=ordering();

            state._fsp--;

            orderingkw=(oing!=null?input.toString(oing.start,oing.stop):null);

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

    public static class ordering_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "ordering"
    // Sql.g:40:1: ordering : ( asc | desc )? ;
    public final SqlParser.ordering_return ordering() throws RecognitionException {
        SqlParser.ordering_return retval = new SqlParser.ordering_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:40:11: ( ( asc | desc )? )
            // Sql.g:40:13: ( asc | desc )?
            {
            // Sql.g:40:13: ( asc | desc )?
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=104 && LA4_0<=105)) ) {
                alt4=1;
            }
            else if ( ((LA4_0>=106 && LA4_0<=107)) ) {
                alt4=2;
            }
            switch (alt4) {
                case 1 :
                    // Sql.g:40:14: asc
                    {
                    pushFollow(FOLLOW_asc_in_ordering192);
                    asc();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:40:18: desc
                    {
                    pushFollow(FOLLOW_desc_in_ordering194);
                    desc();

                    state._fsp--;


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
    // $ANTLR end "ordering"


    // $ANTLR start "selectList"
    // Sql.g:41:1: selectList : kw= keyword ( spaces COMMA spaces kw= keyword )* ;
    public final void selectList() throws RecognitionException {
        SqlParser.keyword_return kw = null;


        try {
            // Sql.g:41:12: (kw= keyword ( spaces COMMA spaces kw= keyword )* )
            // Sql.g:41:13: kw= keyword ( spaces COMMA spaces kw= keyword )*
            {
            pushFollow(FOLLOW_keyword_in_selectList207);
            kw=keyword();

            state._fsp--;

            kws.add((kw!=null?input.toString(kw.start,kw.stop):null));
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

            	    state._fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_selectList224); 
            	    pushFollow(FOLLOW_spaces_in_selectList228);
            	    spaces();

            	    state._fsp--;

            	    pushFollow(FOLLOW_keyword_in_selectList235);
            	    kw=keyword();

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

    public static class keyword_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "keyword"
    // Sql.g:49:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );
    public final SqlParser.keyword_return keyword() throws RecognitionException {
        SqlParser.keyword_return retval = new SqlParser.keyword_return();
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
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
                {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==DOT) ) {
                    int LA6_4 = input.LA(3);

                    if ( ((LA6_4>=76 && LA6_4<=83)) ) {
                        alt6=3;
                    }
                    else if ( ((LA6_4>=22 && LA6_4<=24)||(LA6_4>=42 && LA6_4<=75)) ) {
                        alt6=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 4, input);

                        throw nvae;
                    }
                }
                else if ( (LA6_1==EOF||(LA6_1>=SPACE && LA6_1<=COMMA)||(LA6_1>=EQ && LA6_1<=NOT)||(LA6_1>=17 && LA6_1<=19)||(LA6_1>=90 && LA6_1<=91)||(LA6_1>=96 && LA6_1<=100)||(LA6_1>=104 && LA6_1<=109)) ) {
                    alt6=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
                }
                break;
            case 59:
            case 101:
                {
                alt6=4;
                }
                break;
            case 102:
            case 103:
                {
                alt6=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // Sql.g:49:11: entity
                    {
                    pushFollow(FOLLOW_entity_in_keyword260);
                    entity();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:50:4: entity DOT attr
                    {
                    pushFollow(FOLLOW_entity_in_keyword266);
                    entity();

                    state._fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword268); 
                    pushFollow(FOLLOW_attr_in_keyword270);
                    attr();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // Sql.g:51:4: entity DOT funct
                    {
                    pushFollow(FOLLOW_entity_in_keyword275);
                    entity();

                    state._fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword277); 
                    pushFollow(FOLLOW_funct_in_keyword279);
                    funct();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // Sql.g:52:4: count spaces '(' spaces entity spaces ')'
                    {
                    pushFollow(FOLLOW_count_in_keyword284);
                    count();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword286);
                    spaces();

                    state._fsp--;

                    match(input,15,FOLLOW_15_in_keyword288); 
                    pushFollow(FOLLOW_spaces_in_keyword290);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_entity_in_keyword292);
                    entity();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword294);
                    spaces();

                    state._fsp--;

                    match(input,16,FOLLOW_16_in_keyword296); 

                    }
                    break;
                case 5 :
                    // Sql.g:53:4: sum spaces '(' spaces entity DOT attr spaces ')'
                    {
                    pushFollow(FOLLOW_sum_in_keyword301);
                    sum();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword303);
                    spaces();

                    state._fsp--;

                    match(input,15,FOLLOW_15_in_keyword305); 
                    pushFollow(FOLLOW_spaces_in_keyword307);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_entity_in_keyword309);
                    entity();

                    state._fsp--;

                    match(input,DOT,FOLLOW_DOT_in_keyword311); 
                    pushFollow(FOLLOW_attr_in_keyword313);
                    attr();

                    state._fsp--;

                    pushFollow(FOLLOW_spaces_in_keyword315);
                    spaces();

                    state._fsp--;

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
    // $ANTLR end "keyword"


    // $ANTLR start "constraintList"
    // Sql.g:55:1: constraintList : constraint1 ( spaces rel= logicalOp spaces constraint1 )* ;
    public final void constraintList() throws RecognitionException {
        SqlParser.logicalOp_return rel = null;


        try {
            // Sql.g:55:16: ( constraint1 ( spaces rel= logicalOp spaces constraint1 )* )
            // Sql.g:55:18: constraint1 ( spaces rel= logicalOp spaces constraint1 )*
            {
            pushFollow(FOLLOW_constraint1_in_constraintList326);
            constraint1();

            state._fsp--;

            // Sql.g:55:30: ( spaces rel= logicalOp spaces constraint1 )*
            loop7:
            do {
                int alt7=2;
                alt7 = dfa7.predict(input);
                switch (alt7) {
            	case 1 :
            	    // Sql.g:55:32: spaces rel= logicalOp spaces constraint1
            	    {
            	    pushFollow(FOLLOW_spaces_in_constraintList330);
            	    spaces();

            	    state._fsp--;

            	    pushFollow(FOLLOW_logicalOp_in_constraintList337);
            	    rel=logicalOp();

            	    state._fsp--;

            	     constraints.add((rel!=null?input.toString(rel.start,rel.stop):null));
            	    pushFollow(FOLLOW_spaces_in_constraintList345);
            	    spaces();

            	    state._fsp--;

            	    pushFollow(FOLLOW_constraint1_in_constraintList347);
            	    constraint1();

            	    state._fsp--;


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
    // $ANTLR end "constraintList"

    public static class lopen_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "lopen"
    // Sql.g:58:1: lopen : ( lb )* ;
    public final SqlParser.lopen_return lopen() throws RecognitionException {
        SqlParser.lopen_return retval = new SqlParser.lopen_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:58:8: ( ( lb )* )
            // Sql.g:58:10: ( lb )*
            {
            // Sql.g:58:10: ( lb )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==15) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // Sql.g:58:11: lb
            	    {
            	    pushFollow(FOLLOW_lb_in_lopen358);
            	    lb();

            	    state._fsp--;


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
    // Sql.g:59:1: ropen : ( rb )* ;
    public final SqlParser.ropen_return ropen() throws RecognitionException {
        SqlParser.ropen_return retval = new SqlParser.ropen_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:59:8: ( ( rb )* )
            // Sql.g:59:10: ( rb )*
            {
            // Sql.g:59:10: ( rb )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==16) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Sql.g:59:11: rb
            	    {
            	    pushFollow(FOLLOW_rb_in_ropen369);
            	    rb();

            	    state._fsp--;


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


    // $ANTLR start "constraint1"
    // Sql.g:60:1: constraint1 : kl= lopen constraint kr= ropen ;
    public final void constraint1() throws RecognitionException {
        SqlParser.lopen_return kl = null;

        SqlParser.ropen_return kr = null;


        try {
            // Sql.g:60:17: (kl= lopen constraint kr= ropen )
            // Sql.g:60:19: kl= lopen constraint kr= ropen
            {
            pushFollow(FOLLOW_lopen_in_constraint1387);
            kl=lopen();

            state._fsp--;

            Constraint c1=new Constraint();c1.setBracket((kl!=null?input.toString(kl.start,kl.stop):null));constraints.add(c1);
            pushFollow(FOLLOW_constraint_in_constraint1409);
            constraint();

            state._fsp--;

            pushFollow(FOLLOW_ropen_in_constraint1435);
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
    // Sql.g:64:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue | kw= keyword spaces op3= between spaces val3= betValue );
    public final void constraint() throws RecognitionException {
        SqlParser.keyword_return kw = null;

        SqlParser.compOpt_return op = null;

        SqlParser.genValue_return val = null;

        SqlParser.in_return op1 = null;

        SqlParser.valueList_return val1 = null;

        SqlParser.like_return op2 = null;

        SqlParser.dotValue_return val2 = null;

        SqlParser.between_return op3 = null;

        SqlParser.betValue_return val3 = null;


        try {
            // Sql.g:64:12: (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue | kw= keyword spaces op3= between spaces val3= betValue )
            int alt10=4;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // Sql.g:64:14: kw= keyword spaces op= compOpt spaces val= genValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint450);
                    kw=keyword();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint459);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_compOpt_in_constraint466);
                    op=compOpt();

                    state._fsp--;

                    c.setOp((op!=null?input.toString(op.start,op.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint476);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_genValue_in_constraint483);
                    val=genValue();

                    state._fsp--;

                    c.setValue((val!=null?input.toString(val.start,val.stop):null)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:70:2: kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')'
                    {
                    pushFollow(FOLLOW_keyword_in_constraint511);
                    kw=keyword();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint520);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_in_in_constraint527);
                    op1=in();

                    state._fsp--;

                    c.setOp((op1!=null?input.toString(op1.start,op1.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint538);
                    spaces();

                    state._fsp--;

                    match(input,15,FOLLOW_15_in_constraint540); 
                    pushFollow(FOLLOW_spaces_in_constraint544);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_valueList_in_constraint550);
                    val1=valueList();

                    state._fsp--;

                    c.setValue((val1!=null?input.toString(val1.start,val1.stop):null)); constraints.add(c);
                    pushFollow(FOLLOW_spaces_in_constraint558);
                    spaces();

                    state._fsp--;

                    match(input,16,FOLLOW_16_in_constraint562); 

                    }
                    break;
                case 3 :
                    // Sql.g:79:2: kw= keyword spaces op2= like spaces val2= dotValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint588);
                    kw=keyword();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint597);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_like_in_constraint604);
                    op2=like();

                    state._fsp--;

                    c.setOp((op2!=null?input.toString(op2.start,op2.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint613);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_dotValue_in_constraint620);
                    val2=dotValue();

                    state._fsp--;

                    c.setValue((val2!=null?input.toString(val2.start,val2.stop):null)); constraints.add(c);

                    }
                    break;
                case 4 :
                    // Sql.g:85:3: kw= keyword spaces op3= between spaces val3= betValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint635);
                    kw=keyword();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint644);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_between_in_constraint651);
                    op3=between();

                    state._fsp--;

                    c.setOp((op3!=null?input.toString(op3.start,op3.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint659);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_betValue_in_constraint666);
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


    // $ANTLR start "where"
    // Sql.g:92:1: where : ( 'WHERE' | 'where' ) ;
    public final void where() throws RecognitionException {
        try {
            // Sql.g:92:7: ( ( 'WHERE' | 'where' ) )
            // Sql.g:92:8: ( 'WHERE' | 'where' )
            {
            if ( (input.LA(1)>=17 && input.LA(1)<=18) ) {
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

    public static class dotValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "dotValue"
    // Sql.g:93:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );
    public final SqlParser.dotValue_return dotValue() throws RecognitionException {
        SqlParser.dotValue_return retval = new SqlParser.dotValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:93:17: ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE )
            int alt11=23;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // Sql.g:93:19: VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue733); 

                    }
                    break;
                case 2 :
                    // Sql.g:94:5: 'in'
                    {
                    match(input,19,FOLLOW_19_in_dotValue740); 

                    }
                    break;
                case 3 :
                    // Sql.g:95:5: VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue746); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue748); 
                    match(input,19,FOLLOW_19_in_dotValue750); 

                    }
                    break;
                case 4 :
                    // Sql.g:96:5: VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue756); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue758); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue760); 

                    }
                    break;
                case 5 :
                    // Sql.g:97:5: VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue766); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue768); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue770); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue772); 
                    match(input,19,FOLLOW_19_in_dotValue774); 

                    }
                    break;
                case 6 :
                    // Sql.g:98:5: VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue780); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue782); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue784); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue786); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue788); 

                    }
                    break;
                case 7 :
                    // Sql.g:99:5: VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue794); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue796); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue798); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue800); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue802); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue804); 
                    match(input,19,FOLLOW_19_in_dotValue806); 

                    }
                    break;
                case 8 :
                    // Sql.g:100:5: VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue812); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue814); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue816); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue818); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue820); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue822); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue824); 

                    }
                    break;
                case 9 :
                    // Sql.g:101:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue830); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue832); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue834); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue836); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue838); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue840); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue842); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue844); 
                    match(input,19,FOLLOW_19_in_dotValue846); 

                    }
                    break;
                case 10 :
                    // Sql.g:102:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue852); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue854); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue856); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue858); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue860); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue862); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue864); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue866); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue868); 

                    }
                    break;
                case 11 :
                    // Sql.g:103:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue874); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue876); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue878); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue880); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue882); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue884); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue886); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue888); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue890); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue892); 
                    match(input,19,FOLLOW_19_in_dotValue894); 

                    }
                    break;
                case 12 :
                    // Sql.g:104:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue900); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue902); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue904); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue906); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue908); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue910); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue912); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue914); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue916); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue918); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue920); 

                    }
                    break;
                case 13 :
                    // Sql.g:105:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue926); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue928); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue930); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue932); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue934); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue936); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue938); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue940); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue942); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue944); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue946); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue948); 
                    match(input,19,FOLLOW_19_in_dotValue950); 

                    }
                    break;
                case 14 :
                    // Sql.g:106:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue956); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue958); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue960); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue962); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue964); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue966); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue968); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue970); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue972); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue974); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue976); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue978); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue980); 

                    }
                    break;
                case 15 :
                    // Sql.g:107:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
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
                case 16 :
                    // Sql.g:108:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
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

                    }
                    break;
                case 17 :
                    // Sql.g:109:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
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
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1078); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1080); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1082); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1084); 
                    match(input,19,FOLLOW_19_in_dotValue1086); 

                    }
                    break;
                case 18 :
                    // Sql.g:110:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
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
                    match(input,DOT,FOLLOW_DOT_in_dotValue1122); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1124); 

                    }
                    break;
                case 19 :
                    // Sql.g:111:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
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
                case 20 :
                    // Sql.g:112:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1173); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1175); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1177); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1179); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1181); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1183); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1185); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1187); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1189); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1191); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1193); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1195); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1197); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1199); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1201); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1203); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1205); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1207); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1209); 

                    }
                    break;
                case 21 :
                    // Sql.g:113:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1216); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1218); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1220); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1222); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1224); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1226); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1228); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1230); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1232); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1234); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1236); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1238); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1240); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1242); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1244); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1246); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1248); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1250); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1252); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1254); 
                    match(input,19,FOLLOW_19_in_dotValue1256); 

                    }
                    break;
                case 22 :
                    // Sql.g:114:5: VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1262); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1264); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1266); 

                    }
                    break;
                case 23 :
                    // Sql.g:115:5: VALUE SPACE VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1272); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1274); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1276); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1278); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1280); 

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
    // $ANTLR end "dotValue"

    public static class valueList_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "valueList"
    // Sql.g:119:1: valueList : dotValue ( spaces COMMA spaces dotValue )* ;
    public final SqlParser.valueList_return valueList() throws RecognitionException {
        SqlParser.valueList_return retval = new SqlParser.valueList_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:119:11: ( dotValue ( spaces COMMA spaces dotValue )* )
            // Sql.g:119:12: dotValue ( spaces COMMA spaces dotValue )*
            {
            pushFollow(FOLLOW_dotValue_in_valueList1289);
            dotValue();

            state._fsp--;

            // Sql.g:119:21: ( spaces COMMA spaces dotValue )*
            loop12:
            do {
                int alt12=2;
                alt12 = dfa12.predict(input);
                switch (alt12) {
            	case 1 :
            	    // Sql.g:119:23: spaces COMMA spaces dotValue
            	    {
            	    pushFollow(FOLLOW_spaces_in_valueList1293);
            	    spaces();

            	    state._fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_valueList1295); 
            	    pushFollow(FOLLOW_spaces_in_valueList1297);
            	    spaces();

            	    state._fsp--;

            	    pushFollow(FOLLOW_dotValue_in_valueList1299);
            	    dotValue();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
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
    // $ANTLR end "valueList"

    public static class compOpt_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "compOpt"
    // Sql.g:121:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );
    public final SqlParser.compOpt_return compOpt() throws RecognitionException {
        SqlParser.compOpt_return retval = new SqlParser.compOpt_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:121:10: ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) )
            int alt13=9;
            alt13 = dfa13.predict(input);
            switch (alt13) {
                case 1 :
                    // Sql.g:121:11: ( EQ )
                    {
                    // Sql.g:121:11: ( EQ )
                    // Sql.g:121:12: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1311); 

                    }


                    }
                    break;
                case 2 :
                    // Sql.g:122:4: ( LT )
                    {
                    // Sql.g:122:4: ( LT )
                    // Sql.g:122:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1318); 

                    }


                    }
                    break;
                case 3 :
                    // Sql.g:123:4: ( GT )
                    {
                    // Sql.g:123:4: ( GT )
                    // Sql.g:123:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1325); 

                    }


                    }
                    break;
                case 4 :
                    // Sql.g:124:4: ( NOT ) ( EQ )
                    {
                    // Sql.g:124:4: ( NOT )
                    // Sql.g:124:5: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_compOpt1332); 

                    }

                    // Sql.g:124:9: ( EQ )
                    // Sql.g:124:10: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1335); 

                    }


                    }
                    break;
                case 5 :
                    // Sql.g:125:4: ( EQ ) ( GT )
                    {
                    // Sql.g:125:4: ( EQ )
                    // Sql.g:125:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1342); 

                    }

                    // Sql.g:125:8: ( GT )
                    // Sql.g:125:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1345); 

                    }


                    }
                    break;
                case 6 :
                    // Sql.g:126:4: ( EQ ) ( LT )
                    {
                    // Sql.g:126:4: ( EQ )
                    // Sql.g:126:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1352); 

                    }

                    // Sql.g:126:8: ( LT )
                    // Sql.g:126:9: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1355); 

                    }


                    }
                    break;
                case 7 :
                    // Sql.g:127:4: ( LT ) ( EQ )
                    {
                    // Sql.g:127:4: ( LT )
                    // Sql.g:127:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1362); 

                    }

                    // Sql.g:127:8: ( EQ )
                    // Sql.g:127:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1365); 

                    }


                    }
                    break;
                case 8 :
                    // Sql.g:128:4: ( GT ) ( EQ )
                    {
                    // Sql.g:128:4: ( GT )
                    // Sql.g:128:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1372); 

                    }

                    // Sql.g:128:8: ( EQ )
                    // Sql.g:128:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1375); 

                    }


                    }
                    break;
                case 9 :
                    // Sql.g:129:4: ( LT ) ( GT )
                    {
                    // Sql.g:129:4: ( LT )
                    // Sql.g:129:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1382); 

                    }

                    // Sql.g:129:8: ( GT )
                    // Sql.g:129:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1385); 

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
    // $ANTLR end "compOpt"

    public static class genValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "genValue"
    // Sql.g:131:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );
    public final SqlParser.genValue_return genValue() throws RecognitionException {
        SqlParser.genValue_return retval = new SqlParser.genValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:131:10: ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* )
            int alt15=2;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // Sql.g:131:11: dotValue
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue1393);
                    dotValue();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:132:4: dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )*
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue1398);
                    dotValue();

                    state._fsp--;

                    pushFollow(FOLLOW_compOpt_in_genValue1400);
                    compOpt();

                    state._fsp--;

                    pushFollow(FOLLOW_dotValue_in_genValue1402);
                    dotValue();

                    state._fsp--;

                    // Sql.g:132:30: ( AMP dotValue compOpt dotValue )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==AMP) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // Sql.g:132:31: AMP dotValue compOpt dotValue
                    	    {
                    	    match(input,AMP,FOLLOW_AMP_in_genValue1405); 
                    	    pushFollow(FOLLOW_dotValue_in_genValue1407);
                    	    dotValue();

                    	    state._fsp--;

                    	    pushFollow(FOLLOW_compOpt_in_genValue1409);
                    	    compOpt();

                    	    state._fsp--;

                    	    pushFollow(FOLLOW_dotValue_in_genValue1411);
                    	    dotValue();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
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
    // $ANTLR end "genValue"

    public static class betValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "betValue"
    // Sql.g:133:1: betValue : dotValue spaces and spaces dotValue ;
    public final SqlParser.betValue_return betValue() throws RecognitionException {
        SqlParser.betValue_return retval = new SqlParser.betValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:133:10: ( dotValue spaces and spaces dotValue )
            // Sql.g:133:11: dotValue spaces and spaces dotValue
            {
            pushFollow(FOLLOW_dotValue_in_betValue1419);
            dotValue();

            state._fsp--;

            pushFollow(FOLLOW_spaces_in_betValue1421);
            spaces();

            state._fsp--;

            pushFollow(FOLLOW_and_in_betValue1423);
            and();

            state._fsp--;

            pushFollow(FOLLOW_spaces_in_betValue1425);
            spaces();

            state._fsp--;

            pushFollow(FOLLOW_dotValue_in_betValue1427);
            dotValue();

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

    public static class logicalOp_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "logicalOp"
    // Sql.g:139:1: logicalOp : ( and | or ) ;
    public final SqlParser.logicalOp_return logicalOp() throws RecognitionException {
        SqlParser.logicalOp_return retval = new SqlParser.logicalOp_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:139:11: ( ( and | or ) )
            // Sql.g:139:12: ( and | or )
            {
            // Sql.g:139:12: ( and | or )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=88 && LA16_0<=89)) ) {
                alt16=1;
            }
            else if ( ((LA16_0>=94 && LA16_0<=95)) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // Sql.g:139:13: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp1439);
                    and();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:139:17: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp1441);
                    or();

                    state._fsp--;


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
    // $ANTLR end "logicalOp"


    // $ANTLR start "entity"
    // Sql.g:140:1: entity : ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch' ) ;
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:140:9: ( ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch' ) )
            // Sql.g:140:11: ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch' )
            {
            if ( (input.LA(1)>=20 && input.LA(1)<=41) ) {
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
    // Sql.g:141:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' | 'checksum' ) ;
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:141:7: ( ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' | 'checksum' ) )
            // Sql.g:141:8: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' | 'checksum' )
            {
            if ( (input.LA(1)>=22 && input.LA(1)<=24)||(input.LA(1)>=42 && input.LA(1)<=75) ) {
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
    // Sql.g:142:1: funct : ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) ;
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:142:8: ( ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) )
            // Sql.g:142:9: ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' )
            {
            if ( (input.LA(1)>=76 && input.LA(1)<=83) ) {
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
    // Sql.g:143:1: select : ( 'select' | 'SELECT' | 'find' | 'FIND' ) ;
    public final void select() throws RecognitionException {
        try {
            // Sql.g:143:9: ( ( 'select' | 'SELECT' | 'find' | 'FIND' ) )
            // Sql.g:143:10: ( 'select' | 'SELECT' | 'find' | 'FIND' )
            {
            if ( (input.LA(1)>=84 && input.LA(1)<=87) ) {
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
    // Sql.g:144:1: and : ( 'and' | 'AND' ) ;
    public final void and() throws RecognitionException {
        try {
            // Sql.g:144:6: ( ( 'and' | 'AND' ) )
            // Sql.g:144:7: ( 'and' | 'AND' )
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
    // $ANTLR end "and"


    // $ANTLR start "order"
    // Sql.g:145:1: order : ( 'order' | 'ORDER' ) ;
    public final void order() throws RecognitionException {
        try {
            // Sql.g:145:8: ( ( 'order' | 'ORDER' ) )
            // Sql.g:145:9: ( 'order' | 'ORDER' )
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
    // Sql.g:146:1: by : ( 'by' | 'BY' ) ;
    public final void by() throws RecognitionException {
        try {
            // Sql.g:146:5: ( ( 'by' | 'BY' ) )
            // Sql.g:146:6: ( 'by' | 'BY' )
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
    // $ANTLR end "by"


    // $ANTLR start "or"
    // Sql.g:147:1: or : ( 'or' | 'OR' ) ;
    public final void or() throws RecognitionException {
        try {
            // Sql.g:147:5: ( ( 'or' | 'OR' ) )
            // Sql.g:147:6: ( 'or' | 'OR' )
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
    // $ANTLR end "or"

    public static class in_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "in"
    // Sql.g:148:1: in : ( 'in' | 'IN' ) ;
    public final SqlParser.in_return in() throws RecognitionException {
        SqlParser.in_return retval = new SqlParser.in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:148:5: ( ( 'in' | 'IN' ) )
            // Sql.g:148:6: ( 'in' | 'IN' )
            {
            if ( input.LA(1)==19||input.LA(1)==96 ) {
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
    // Sql.g:149:1: not : ( 'not' | 'NOT' ) ;
    public final void not() throws RecognitionException {
        try {
            // Sql.g:149:6: ( ( 'not' | 'NOT' ) )
            // Sql.g:149:7: ( 'not' | 'NOT' )
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
    // $ANTLR end "not"

    public static class like_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "like"
    // Sql.g:150:1: like : ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' ) ;
    public final SqlParser.like_return like() throws RecognitionException {
        SqlParser.like_return retval = new SqlParser.like_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:150:7: ( ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' ) )
            // Sql.g:150:8: ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' )
            {
            // Sql.g:150:8: ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' )
            int alt17=4;
            switch ( input.LA(1) ) {
            case 99:
                {
                alt17=1;
                }
                break;
            case 100:
                {
                alt17=2;
                }
                break;
            case 97:
                {
                alt17=3;
                }
                break;
            case 98:
                {
                alt17=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // Sql.g:150:9: 'like'
                    {
                    match(input,99,FOLLOW_99_in_like1832); 

                    }
                    break;
                case 2 :
                    // Sql.g:150:18: 'LIKE'
                    {
                    match(input,100,FOLLOW_100_in_like1836); 

                    }
                    break;
                case 3 :
                    // Sql.g:150:27: 'not' spaces 'like'
                    {
                    match(input,97,FOLLOW_97_in_like1840); 
                    pushFollow(FOLLOW_spaces_in_like1842);
                    spaces();

                    state._fsp--;

                    match(input,99,FOLLOW_99_in_like1844); 

                    }
                    break;
                case 4 :
                    // Sql.g:150:49: 'NOT' spaces 'LIKE'
                    {
                    match(input,98,FOLLOW_98_in_like1848); 
                    pushFollow(FOLLOW_spaces_in_like1850);
                    spaces();

                    state._fsp--;

                    match(input,100,FOLLOW_100_in_like1852); 

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
    // $ANTLR end "like"


    // $ANTLR start "count"
    // Sql.g:152:1: count : ( 'count' | 'COUNT' ) ;
    public final void count() throws RecognitionException {
        try {
            // Sql.g:152:8: ( ( 'count' | 'COUNT' ) )
            // Sql.g:152:9: ( 'count' | 'COUNT' )
            {
            if ( input.LA(1)==59||input.LA(1)==101 ) {
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
    // $ANTLR end "count"


    // $ANTLR start "sum"
    // Sql.g:153:1: sum : ( 'sum' | 'SUM' ) ;
    public final void sum() throws RecognitionException {
        try {
            // Sql.g:153:6: ( ( 'sum' | 'SUM' ) )
            // Sql.g:153:7: ( 'sum' | 'SUM' )
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
    // $ANTLR end "sum"


    // $ANTLR start "asc"
    // Sql.g:154:1: asc : ( 'asc' | 'ASC' ) ;
    public final void asc() throws RecognitionException {
        try {
            // Sql.g:154:6: ( ( 'asc' | 'ASC' ) )
            // Sql.g:154:7: ( 'asc' | 'ASC' )
            {
            if ( (input.LA(1)>=104 && input.LA(1)<=105) ) {
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
    // Sql.g:155:1: desc : ( 'desc' | 'DESC' ) ;
    public final void desc() throws RecognitionException {
        try {
            // Sql.g:155:7: ( ( 'desc' | 'DESC' ) )
            // Sql.g:155:8: ( 'desc' | 'DESC' )
            {
            if ( (input.LA(1)>=106 && input.LA(1)<=107) ) {
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
    // Sql.g:156:1: between : ( 'between' | 'BETWEEN' ) ;
    public final SqlParser.between_return between() throws RecognitionException {
        SqlParser.between_return retval = new SqlParser.between_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:156:10: ( ( 'between' | 'BETWEEN' ) )
            // Sql.g:156:11: ( 'between' | 'BETWEEN' )
            {
            if ( (input.LA(1)>=108 && input.LA(1)<=109) ) {
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


    // $ANTLR start "lb"
    // Sql.g:157:1: lb : ( '(' ) ;
    public final void lb() throws RecognitionException {
        try {
            // Sql.g:157:5: ( ( '(' ) )
            // Sql.g:157:7: ( '(' )
            {
            // Sql.g:157:7: ( '(' )
            // Sql.g:157:8: '('
            {
            match(input,15,FOLLOW_15_in_lb1928); 

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
    // $ANTLR end "lb"


    // $ANTLR start "rb"
    // Sql.g:158:1: rb : ( ')' ) ;
    public final void rb() throws RecognitionException {
        try {
            // Sql.g:158:5: ( ( ')' ) )
            // Sql.g:158:7: ( ')' )
            {
            // Sql.g:158:7: ( ')' )
            // Sql.g:158:8: ')'
            {
            match(input,16,FOLLOW_16_in_rb1938); 

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
    // $ANTLR end "rb"

    // Delegated rules


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA3 dfa3 = new DFA3(this);
    protected DFA5 dfa5 = new DFA5(this);
    protected DFA7 dfa7 = new DFA7(this);
    protected DFA10 dfa10 = new DFA10(this);
    protected DFA11 dfa11 = new DFA11(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA13 dfa13 = new DFA13(this);
    protected DFA15 dfa15 = new DFA15(this);
    static final String DFA1_eotS =
        "\u0348\uffff";
    static final String DFA1_eofS =
        "\3\uffff\1\12\3\uffff\1\12\10\uffff\2\12\1\uffff\1\12\41\uffff\1"+
        "\12\1\uffff\2\12\14\uffff\2\146\7\uffff\2\146\12\uffff\1\12\6\uffff"+
        "\2\146\10\uffff\1\146\4\uffff\1\146\4\uffff\1\12\5\uffff\5\146\7"+
        "\uffff\2\146\7\uffff\1\146\1\uffff\3\146\2\uffff\1\12\3\uffff\1"+
        "\146\1\uffff\1\146\21\uffff\1\146\10\uffff\1\146\5\uffff\6\146\4"+
        "\uffff\2\146\6\uffff\2\146\15\uffff\3\146\6\uffff\3\146\2\uffff"+
        "\1\146\2\uffff\1\146\4\uffff\1\146\1\uffff\1\146\13\uffff\1\146"+
        "\17\uffff\12\146\6\uffff\3\146\4\uffff\2\146\3\uffff\1\146\2\uffff"+
        "\3\146\5\uffff\2\146\7\uffff\2\146\4\uffff\1\146\1\uffff\1\146\2"+
        "\uffff\1\146\3\uffff\1\146\14\uffff\1\146\10\uffff\12\146\2\uffff"+
        "\3\146\3\uffff\3\146\4\uffff\2\146\3\uffff\3\146\2\uffff\2\146\5"+
        "\uffff\1\146\11\uffff\1\146\11\uffff\1\146\7\uffff\11\146\4\uffff"+
        "\2\146\5\uffff\2\146\2\uffff\3\146\3\uffff\2\146\2\uffff\3\146\2"+
        "\uffff\2\146\10\uffff\1\146\22\uffff\10\146\3\uffff\5\146\2\uffff"+
        "\2\146\2\uffff\2\146\2\uffff\2\146\2\uffff\2\146\7\uffff\1\146\21"+
        "\uffff\10\146\2\uffff\5\146\2\uffff\2\146\2\uffff\2\146\2\uffff"+
        "\2\146\2\uffff\2\146\30\uffff\10\146\2\uffff\4\146\2\uffff\2\146"+
        "\2\uffff\2\146\2\uffff\2\146\2\uffff\2\146\30\uffff\10\146\2\uffff"+
        "\4\146\2\uffff\2\146\2\uffff\2\146\2\uffff\2\146\2\uffff\2\146\27"+
        "\uffff\7\146\2\uffff\4\146\2\uffff\2\146\2\uffff\2\146\2\uffff\2"+
        "\146\2\uffff\1\146\22\uffff\5\146\2\uffff\4\146\2\uffff\2\146\2"+
        "\uffff\1\146\2\uffff\2\146\17\uffff\3\146\2\uffff\3\146\1\uffff"+
        "\2\146\3\uffff\2\146\10\uffff\1\146\2\uffff\3\146\1\uffff\1\146"+
        "\4\uffff\2\146\1\uffff\1\146";
    static final String DFA1_minS =
        "\1\124\5\4\1\26\3\4\2\uffff\13\4\1\17\6\4\1\6\1\26\4\4\1\26\5\4"+
        "\1\10\13\4\1\26\5\4\1\6\32\4\1\6\3\4\1\26\1\7\3\4\1\7\4\4\2\uffff"+
        "\3\7\1\10\4\4\1\7\3\4\1\26\15\4\1\17\3\4\2\7\2\4\5\7\12\4\1\7\1"+
        "\4\1\7\1\4\1\7\1\4\1\26\4\4\1\10\12\4\1\7\1\4\1\7\6\4\1\7\46\4\1"+
        "\6\4\4\1\10\1\7\1\4\1\7\4\4\2\7\1\4\2\7\1\4\3\7\1\10\1\4\1\7\1\4"+
        "\1\7\10\4\1\26\1\7\1\4\5\7\1\10\6\4\1\7\14\4\6\7\11\4\1\7\10\4\1"+
        "\10\1\6\1\4\2\7\2\4\6\7\3\4\4\7\1\4\1\7\1\4\2\7\1\4\1\7\1\4\1\7"+
        "\11\4\4\7\6\4\1\7\15\4\1\10\11\4\1\7\1\4\1\7\3\4\1\10\1\6\1\10\3"+
        "\4\2\7\2\4\5\7\1\4\5\7\1\10\3\7\7\4\3\7\5\4\1\7\13\4\1\10\1\6\1"+
        "\4\1\7\2\4\5\7\7\4\2\7\3\4\1\10\1\6\3\4\2\7\2\4\10\7\1\4\3\7\5\4"+
        "\3\7\4\4\1\7\12\4\1\10\1\6\1\10\11\4\2\7\2\4\1\10\1\6\2\4\2\7\2"+
        "\4\7\7\1\4\3\7\4\4\3\7\4\4\1\7\12\4\1\10\1\6\11\4\2\7\2\4\1\10\1"+
        "\6\2\4\2\7\2\4\12\7\4\4\3\7\4\4\1\7\12\4\1\10\1\6\10\4\2\7\2\4\1"+
        "\10\1\6\2\4\2\7\2\4\12\7\4\4\3\7\4\4\1\7\12\4\1\10\1\6\10\4\2\7"+
        "\2\4\1\10\1\6\2\4\2\7\2\4\1\23\1\7\1\23\7\7\4\4\3\7\4\4\1\23\10"+
        "\4\1\10\1\6\10\4\2\7\2\4\1\10\1\6\2\4\1\23\1\7\1\4\1\23\7\7\4\4"+
        "\1\23\2\7\10\4\1\10\1\6\10\4\2\7\1\4\1\10\1\6\2\4\2\23\3\7\2\23"+
        "\1\7\4\4\1\23\1\7\4\4\1\10\1\6\6\4\1\23\1\7\1\10\2\4\1\23\2\7\1"+
        "\23\3\4\1\23\1\4\1\10\1\6\3\4\1\23\1\4\1\23\1\7\1\4\1\10\2\4\1\23"+
        "\1\4";
    static final String DFA1_maxS =
        "\1\127\2\147\1\133\2\17\1\123\1\133\2\147\2\uffff\1\17\1\51\1\17"+
        "\1\51\2\133\1\147\1\133\2\17\2\147\1\155\2\17\1\51\1\20\1\51\1\6"+
        "\1\123\1\17\1\51\1\17\1\51\1\123\1\155\4\23\1\10\1\17\2\23\1\143"+
        "\1\144\1\17\1\51\1\17\1\51\1\20\1\133\1\113\2\133\1\51\1\20\1\51"+
        "\1\6\2\155\1\23\2\131\3\23\2\137\4\23\1\17\2\23\2\137\1\143\1\23"+
        "\1\144\1\23\1\51\1\20\1\51\1\6\2\20\1\133\1\113\1\23\1\131\1\23"+
        "\1\131\1\23\2\137\2\147\2\uffff\3\23\1\10\1\137\1\23\2\20\1\23\1"+
        "\137\1\20\1\155\1\113\1\20\1\133\1\20\3\131\1\23\5\137\2\147\1\155"+
        "\2\17\2\23\2\137\5\23\1\20\1\23\1\137\1\20\3\137\2\20\1\133\1\23"+
        "\1\131\1\23\1\137\1\23\1\137\1\123\1\155\3\23\1\10\2\23\1\143\1"+
        "\144\1\23\2\17\1\51\1\17\1\51\1\23\1\137\1\23\3\20\1\23\2\20\1\23"+
        "\1\137\1\20\1\155\3\131\6\137\2\155\2\23\2\137\6\23\2\137\1\143"+
        "\1\23\1\144\2\23\2\131\1\17\1\23\1\51\1\20\1\51\1\6\3\137\2\13\1"+
        "\23\1\20\1\23\1\20\3\137\2\23\1\137\2\23\1\137\3\23\1\10\1\137\1"+
        "\23\1\137\1\23\1\131\1\23\1\131\1\23\3\20\1\155\1\113\1\23\1\137"+
        "\1\23\1\7\3\23\1\10\6\20\1\23\2\131\12\137\6\23\3\137\3\131\1\23"+
        "\2\137\1\23\1\20\1\23\1\137\2\20\3\137\3\13\2\23\2\137\6\23\1\20"+
        "\2\137\4\23\1\137\1\23\1\137\2\23\1\137\1\23\1\131\1\23\1\137\3"+
        "\20\1\23\3\20\1\155\2\23\1\7\1\23\1\137\5\20\1\23\2\131\12\137\2"+
        "\13\3\137\3\131\3\137\1\23\1\20\1\23\1\20\2\137\3\13\3\137\2\23"+
        "\2\137\5\23\1\137\1\23\1\7\3\23\1\10\3\23\1\137\6\20\3\23\1\137"+
        "\4\20\1\23\2\131\11\137\3\13\1\23\2\137\5\23\2\137\2\131\3\137\2"+
        "\23\1\20\2\137\2\13\3\137\2\23\2\137\6\23\1\7\1\23\1\137\3\23\5"+
        "\20\3\23\4\20\1\23\2\131\10\137\3\13\5\137\2\131\2\137\2\23\2\137"+
        "\2\13\2\137\2\23\2\137\7\23\1\137\3\23\4\20\3\23\4\20\1\23\2\131"+
        "\10\137\2\13\5\137\2\131\2\137\2\23\2\137\2\13\2\137\2\23\2\137"+
        "\12\23\4\20\3\23\4\20\1\23\2\131\10\137\2\13\4\137\2\131\2\137\2"+
        "\23\2\137\2\13\2\137\2\23\2\137\12\23\4\20\3\23\4\20\1\23\2\131"+
        "\10\137\2\13\4\137\2\131\2\137\2\23\2\137\2\13\2\137\2\23\2\137"+
        "\12\23\4\20\3\23\4\20\1\23\1\131\7\137\2\13\4\137\2\131\2\137\2"+
        "\23\2\137\2\13\2\137\2\23\1\137\10\23\4\20\3\23\3\20\5\137\2\13"+
        "\4\137\2\131\2\137\2\23\1\137\2\13\2\137\10\23\4\20\2\23\1\20\3"+
        "\137\2\13\3\137\1\131\2\137\2\23\1\13\2\137\4\23\3\20\1\23\1\137"+
        "\2\13\3\137\1\23\1\137\2\23\1\20\1\13\2\137\1\23\1\137";
    static final String DFA1_acceptS =
        "\12\uffff\1\2\1\3\131\uffff\1\4\1\1\u02e1\uffff";
    static final String DFA1_specialS =
        "\u0348\uffff}>";
    static final String[] DFA1_transitionS = {
            "\4\1",
            "\1\2\17\uffff\26\3\21\uffff\1\4\51\uffff\1\4\2\5",
            "\1\2\17\uffff\26\3\21\uffff\1\4\51\uffff\1\4\2\5",
            "\1\7\1\10\1\6\12\uffff\2\11\107\uffff\2\13",
            "\1\14\12\uffff\1\15",
            "\1\16\12\uffff\1\17",
            "\3\20\21\uffff\42\20\10\21",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\13",
            "\1\22\17\uffff\26\23\21\uffff\1\24\51\uffff\1\24\2\25",
            "\1\26\12\uffff\1\27\4\uffff\26\30\21\uffff\1\31\51\uffff\1"+
            "\31\2\32",
            "",
            "",
            "\1\14\12\uffff\1\15",
            "\1\33\17\uffff\26\34",
            "\1\16\12\uffff\1\17",
            "\1\35\17\uffff\26\36",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\13",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\13",
            "\1\22\17\uffff\26\23\21\uffff\1\24\51\uffff\1\24\2\25",
            "\1\7\1\10\1\37\12\uffff\2\11\107\uffff\2\13",
            "\1\40\12\uffff\1\41",
            "\1\42\12\uffff\1\43",
            "\1\26\12\uffff\1\27\4\uffff\26\30\21\uffff\1\31\51\uffff\1"+
            "\31\2\32",
            "\1\27\4\uffff\26\30\21\uffff\1\31\51\uffff\1\31\2\32",
            "\1\45\1\uffff\1\44\1\uffff\1\47\1\50\1\51\1\52\7\uffff\1\53"+
            "\114\uffff\1\53\1\56\1\57\1\54\1\55\7\uffff\2\46",
            "\1\60\12\uffff\1\61",
            "\1\62\12\uffff\1\63",
            "\1\33\17\uffff\26\34",
            "\1\64\13\uffff\1\65",
            "\1\35\17\uffff\26\36",
            "\1\66",
            "\3\67\21\uffff\42\67\10\70",
            "\1\40\12\uffff\1\41",
            "\1\71\17\uffff\26\72",
            "\1\42\12\uffff\1\43",
            "\1\73\17\uffff\26\74",
            "\3\75\21\uffff\42\75\10\76",
            "\1\45\3\uffff\1\47\1\50\1\51\1\52\7\uffff\1\53\114\uffff\1"+
            "\53\1\56\1\57\1\54\1\55\7\uffff\2\46",
            "\1\77\2\uffff\1\100\13\uffff\1\101",
            "\1\104\2\uffff\1\105\1\uffff\1\102\1\103\10\uffff\1\106",
            "\1\104\2\uffff\1\105\1\110\1\uffff\1\107\10\uffff\1\106",
            "\1\104\2\uffff\1\105\1\111\12\uffff\1\106",
            "\1\112",
            "\1\113\12\uffff\1\114",
            "\1\115\2\uffff\1\116\13\uffff\1\117",
            "\1\115\2\uffff\1\116\13\uffff\1\117",
            "\1\120\136\uffff\1\121",
            "\1\122\137\uffff\1\123",
            "\1\60\12\uffff\1\61",
            "\1\124\17\uffff\26\125",
            "\1\62\12\uffff\1\63",
            "\1\126\17\uffff\26\127",
            "\1\64\13\uffff\1\65",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\13",
            "\3\130\21\uffff\42\130",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\13",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\13",
            "\1\71\17\uffff\26\72",
            "\1\131\13\uffff\1\132",
            "\1\73\17\uffff\26\74",
            "\1\133",
            "\1\45\3\uffff\1\47\1\50\1\51\1\52\7\uffff\1\53\114\uffff\1"+
            "\53\1\56\1\57\1\54\1\55\7\uffff\2\46",
            "\1\45\3\uffff\1\47\1\50\1\51\1\52\7\uffff\1\53\114\uffff\1"+
            "\53\1\56\1\57\1\54\1\55\7\uffff\2\46",
            "\1\77\2\uffff\1\100\13\uffff\1\101",
            "\1\135\1\uffff\1\134\121\uffff\2\136",
            "\1\137\123\uffff\2\136",
            "\1\104\2\uffff\1\105\13\uffff\1\106",
            "\1\104\2\uffff\1\105\13\uffff\1\106",
            "\1\104\2\uffff\1\105\13\uffff\1\106",
            "\1\141\1\uffff\1\140\1\uffff\1\147\1\150\1\151\1\152\4\uffff"+
            "\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\147\1\150\1\151\1\152\4\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\104\2\uffff\1\105\13\uffff\1\106",
            "\1\104\2\uffff\1\105\13\uffff\1\106",
            "\1\104\2\uffff\1\105\13\uffff\1\106",
            "\1\104\2\uffff\1\105\13\uffff\1\106",
            "\1\113\12\uffff\1\114",
            "\1\154\2\uffff\1\155\13\uffff\1\156",
            "\1\115\2\uffff\1\116\13\uffff\1\117",
            "\1\160\1\uffff\1\157\11\uffff\1\142\107\uffff\2\143\2\145\2"+
            "\uffff\2\144",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\120\136\uffff\1\121",
            "\1\115\2\uffff\1\116\13\uffff\1\117",
            "\1\122\137\uffff\1\123",
            "\1\115\2\uffff\1\116\13\uffff\1\117",
            "\1\124\17\uffff\26\125",
            "\1\161\13\uffff\1\162",
            "\1\126\17\uffff\26\127",
            "\1\163",
            "\1\164\13\uffff\1\165",
            "\1\131\13\uffff\1\132",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\13",
            "\3\166\21\uffff\42\166",
            "\1\170\13\uffff\1\167",
            "\1\137\2\uffff\1\171\120\uffff\2\136",
            "\1\172\2\uffff\1\173\13\uffff\1\174",
            "\1\137\123\uffff\2\136",
            "\1\176\13\uffff\1\175",
            "\1\153\2\uffff\1\177\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0080\12\uffff\1\u0081\4\uffff\26\u0082\21\uffff\1\u0083"+
            "\51\uffff\1\u0083\2\u0084",
            "\1\u0080\12\uffff\1\u0081\4\uffff\26\u0082\21\uffff\1\u0083"+
            "\51\uffff\1\u0083\2\u0084",
            "",
            "",
            "\1\u0087\1\uffff\1\u0085\1\u0086\10\uffff\1\u0088",
            "\1\u0087\1\u0089\1\uffff\1\u008a\10\uffff\1\u0088",
            "\1\u0087\1\u008b\12\uffff\1\u0088",
            "\1\u008c",
            "\1\153\123\uffff\2\143\2\145\2\uffff\2\144",
            "\1\154\2\uffff\1\155\13\uffff\1\156",
            "\1\u008e\1\u008f\1\u008d\11\uffff\1\u0090",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0093\13\uffff\1\u0092",
            "\1\153\2\uffff\1\u0094\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\161\13\uffff\1\162",
            "\1\45\3\uffff\1\47\1\50\1\51\1\52\7\uffff\1\53\114\uffff\1"+
            "\53\1\56\1\57\1\54\1\55\7\uffff\2\46",
            "\3\u0095\21\uffff\42\u0095",
            "\1\164\13\uffff\1\165",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\13",
            "\1\u0096\13\uffff\1\u0097",
            "\1\137\123\uffff\2\136",
            "\1\137\1\uffff\1\u0098\121\uffff\2\136",
            "\1\u0099\123\uffff\2\136",
            "\1\172\2\uffff\1\173\13\uffff\1\174",
            "\1\u009b\1\uffff\1\u009a\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\147\1\150\1\151\1\152\4\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u009c\1\uffff\1\147\1\150\1\151\1\152\4\uffff"+
            "\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u009d\3\uffff\1\147\1\150\1\151\1\152\4\uffff\1\142\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0080\12\uffff\1\u0081\4\uffff\26\u0082\21\uffff\1\u0083"+
            "\51\uffff\1\u0083\2\u0084",
            "\1\u0081\4\uffff\26\u0082\21\uffff\1\u0083\51\uffff\1\u0083"+
            "\2\u0084",
            "\1\u009f\1\uffff\1\u009e\1\uffff\1\u00a0\1\u00a1\1\u00a2\1"+
            "\u00a3\7\uffff\1\u00a9\114\uffff\1\u00a9\1\u00a6\1\u00a7\1\u00a4"+
            "\1\u00a5\7\uffff\2\u00a8",
            "\1\u00aa\12\uffff\1\u00ab",
            "\1\u00ac\12\uffff\1\u00ad",
            "\1\u0087\13\uffff\1\u0088",
            "\1\u0087\13\uffff\1\u0088",
            "\1\u00af\1\uffff\1\u00ae\5\uffff\1\u00b0\3\uffff\1\142\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u0087\13\uffff\1\u0088",
            "\1\u0087\13\uffff\1\u0088",
            "\1\u0087\13\uffff\1\u0088",
            "\1\u0087\13\uffff\1\u0088",
            "\1\u00b2\13\uffff\1\u00b1",
            "\1\u0091\1\u008f\1\uffff\1\u00b3\10\uffff\1\u0090",
            "\1\u00b4\2\uffff\1\u00b5\13\uffff\1\u00b6",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u00b7\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u00b8\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u00b9\13\uffff\1\u00ba",
            "\1\u0096\13\uffff\1\u0097",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\13",
            "\1\u00bc\13\uffff\1\u00bb",
            "\1\137\2\uffff\1\u00bd\120\uffff\2\136",
            "\1\u00bf\13\uffff\1\u00be",
            "\1\153\2\uffff\1\u00c0\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u00c2\13\uffff\1\u00c1",
            "\1\153\2\uffff\1\u00c3\120\uffff\2\143\2\145\2\uffff\2\144",
            "\3\u00c5\21\uffff\42\u00c5\10\u00c4",
            "\1\u009f\3\uffff\1\u00a0\1\u00a1\1\u00a2\1\u00a3\7\uffff\1"+
            "\u00a9\114\uffff\1\u00a9\1\u00a6\1\u00a7\1\u00a4\1\u00a5\7\uffff"+
            "\2\u00a8",
            "\1\u00c7\2\uffff\1\u00c8\1\uffff\1\u00c6\1\u00ca\10\uffff\1"+
            "\u00c9",
            "\1\u00c7\2\uffff\1\u00c8\1\u00cb\1\uffff\1\u00cc\10\uffff\1"+
            "\u00c9",
            "\1\u00c7\2\uffff\1\u00c8\1\u00cd\12\uffff\1\u00c9",
            "\1\u00ce",
            "\1\u00cf\2\uffff\1\u00d0\13\uffff\1\u00d1",
            "\1\u00cf\2\uffff\1\u00d0\13\uffff\1\u00d1",
            "\1\u00d2\136\uffff\1\u00d3",
            "\1\u00d4\137\uffff\1\u00d5",
            "\1\u00d6\2\uffff\1\u00d7\13\uffff\1\u00d8",
            "\1\u00d9\12\uffff\1\u00da",
            "\1\u00aa\12\uffff\1\u00ab",
            "\1\u00db\17\uffff\26\u00dc",
            "\1\u00ac\12\uffff\1\u00ad",
            "\1\u00dd\17\uffff\26\u00de",
            "\1\u00e0\13\uffff\1\u00df",
            "\1\153\2\uffff\1\u00e1\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u00e2\13\uffff\1\u00e3",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u00e4\11\uffff\1\u0090",
            "\1\u00e5\1\u008f\12\uffff\1\u0090",
            "\1\u00b4\2\uffff\1\u00b5\13\uffff\1\u00b6",
            "\1\u00e7\1\u008f\1\u00e6\11\uffff\1\u0090",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u00e9\13\uffff\1\u00e8",
            "\1\153\2\uffff\1\u00ea\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u00b9\13\uffff\1\u00ba",
            "\1\45\3\uffff\1\47\1\50\1\51\1\52\7\uffff\1\53\114\uffff\1"+
            "\53\1\56\1\57\1\54\1\55\7\uffff\2\46",
            "\1\137\123\uffff\2\136",
            "\1\137\1\uffff\1\u00eb\121\uffff\2\136",
            "\1\137\123\uffff\2\136",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u00ec\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u00ed\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\147\1\150\1\151\1\152\4\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u00ee\1\uffff\1\147\1\150\1\151\1\152\4\uffff"+
            "\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\147\1\150\1\151\1\152\4\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u009f\3\uffff\1\u00a0\1\u00a1\1\u00a2\1\u00a3\7\uffff\1"+
            "\u00a9\114\uffff\1\u00a9\1\u00a6\1\u00a7\1\u00a4\1\u00a5\7\uffff"+
            "\2\u00a8",
            "\1\u009f\3\uffff\1\u00a0\1\u00a1\1\u00a2\1\u00a3\7\uffff\1"+
            "\u00a9\114\uffff\1\u00a9\1\u00a6\1\u00a7\1\u00a4\1\u00a5\7\uffff"+
            "\2\u00a8",
            "\1\u00c7\2\uffff\1\u00c8\13\uffff\1\u00c9",
            "\1\u00c7\2\uffff\1\u00c8\13\uffff\1\u00c9",
            "\1\u00f0\1\uffff\1\u00ef\1\uffff\1\u00f1\1\u00f2\1\u00f3\1"+
            "\u00f4\4\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4\4\uffff\1\u00f5"+
            "\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u00c7\2\uffff\1\u00c8\13\uffff\1\u00c9",
            "\1\u00c7\2\uffff\1\u00c8\13\uffff\1\u00c9",
            "\1\u00c7\2\uffff\1\u00c8\13\uffff\1\u00c9",
            "\1\u00c7\2\uffff\1\u00c8\13\uffff\1\u00c9",
            "\1\u00c7\2\uffff\1\u00c8\13\uffff\1\u00c9",
            "\1\u00cf\2\uffff\1\u00d0\13\uffff\1\u00d1",
            "\1\u00f7\1\uffff\1\u00f6\11\uffff\1\u00f5\107\uffff\2\143\2"+
            "\145\2\uffff\2\144",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u00d2\136\uffff\1\u00d3",
            "\1\u00cf\2\uffff\1\u00d0\13\uffff\1\u00d1",
            "\1\u00d4\137\uffff\1\u00d5",
            "\1\u00cf\2\uffff\1\u00d0\13\uffff\1\u00d1",
            "\1\u00d6\2\uffff\1\u00d7\13\uffff\1\u00d8",
            "\1\u00f9\1\uffff\1\u00f8\121\uffff\2\u00fa",
            "\1\u00fb\123\uffff\2\u00fa",
            "\1\u00d9\12\uffff\1\u00da",
            "\1\u00fc\2\uffff\1\u00fd\13\uffff\1\u00fe",
            "\1\u00db\17\uffff\26\u00dc",
            "\1\u00ff\13\uffff\1\u0100",
            "\1\u00dd\17\uffff\26\u00de",
            "\1\u0101",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0102\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u0103\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u0105\1\uffff\1\u0104\1\uffff\1\u0106\1\u0107\1\u0108\1"+
            "\u0109",
            "\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\u010b\13\uffff\1\u010a",
            "\1\u0091\1\u008f\1\uffff\1\u010c\10\uffff\1\u0090",
            "\1\u010e\13\uffff\1\u010d",
            "\1\u0091\1\u008f\1\uffff\1\u010f\10\uffff\1\u0090",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0110\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0112\13\uffff\1\u0111",
            "\1\u0114\13\uffff\1\u0113",
            "\1\153\2\uffff\1\u0115\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0117\13\uffff\1\u0116",
            "\1\u0119\13\uffff\1\u0118",
            "\1\153\2\uffff\1\u011a\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u011b\1\uffff\1\u011e\1\u011d\10\uffff\1\u011c",
            "\1\u011b\1\u0120\1\uffff\1\u011f\10\uffff\1\u011c",
            "\1\u011b\1\u0121\12\uffff\1\u011c",
            "\1\u0122",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0124\13\uffff\1\u0123",
            "\1\153\2\uffff\1\u0125\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0127\13\uffff\1\u0126",
            "\1\u00fb\2\uffff\1\u0128\120\uffff\2\u00fa",
            "\1\u0129\2\uffff\1\u012a\13\uffff\1\u012b",
            "\1\u00fb\123\uffff\2\u00fa",
            "\1\u00fc\2\uffff\1\u00fd\13\uffff\1\u00fe",
            "\1\u012d\1\u012e\1\u012c\11\uffff\1\u012f",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u00ff\13\uffff\1\u0100",
            "\1\u009f\3\uffff\1\u00a0\1\u00a1\1\u00a2\1\u00a3\7\uffff\1"+
            "\u00a9\114\uffff\1\u00a9\1\u00a6\1\u00a7\1\u00a4\1\u00a5\7\uffff"+
            "\2\u00a8",
            "\3\u0131\21\uffff\42\u0131",
            "\1\u0133\13\uffff\1\u0132",
            "\1\153\2\uffff\1\u0134\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0136\13\uffff\1\u0135",
            "\1\u0137",
            "\1\u013a\1\uffff\1\u0138\1\u0139\10\uffff\1\u013b",
            "\1\u013a\1\u013c\1\uffff\1\u013d\10\uffff\1\u013b",
            "\1\u013a\1\u013e\12\uffff\1\u013b",
            "\1\u013f",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u0140\11\uffff\1\u0090",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u0141\11\uffff\1\u0090",
            "\1\u0142\1\u008f\12\uffff\1\u0090",
            "\1\u0144\13\uffff\1\u0143",
            "\1\137\123\uffff\2\136",
            "\1\137\1\uffff\1\u0145\121\uffff\2\136",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0146\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\147\1\150\1\151\1\152\4\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0147\1\uffff\1\147\1\150\1\151\1\152\4\uffff"+
            "\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4\4\uffff\1\u00f5"+
            "\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0148\1\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4"+
            "\4\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0149\3\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4\4\uffff\1"+
            "\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u014b\1\uffff\1\u014a\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u011b\13\uffff\1\u011c",
            "\1\u011b\13\uffff\1\u011c",
            "\1\u011b\13\uffff\1\u011c",
            "\1\u011b\13\uffff\1\u011c",
            "\1\u011b\13\uffff\1\u011c",
            "\1\u011b\13\uffff\1\u011c",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u014d\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u014e\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u00fb\123\uffff\2\u00fa",
            "\1\u00fb\1\uffff\1\u014f\121\uffff\2\u00fa",
            "\1\u0150\123\uffff\2\u00fa",
            "\1\u0129\2\uffff\1\u012a\13\uffff\1\u012b",
            "\1\u0152\1\uffff\1\u0151\11\uffff\1\u00f5\107\uffff\2\143\2"+
            "\145\2\uffff\2\144",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0154\13\uffff\1\u0153",
            "\1\u0130\1\u012e\1\uffff\1\u0155\10\uffff\1\u012f",
            "\1\u0156\2\uffff\1\u0157\13\uffff\1\u0158",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0159\13\uffff\1\u015a",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u015b\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\u015c\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\u015d\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\u013a\13\uffff\1\u013b",
            "\1\u013a\13\uffff\1\u013b",
            "\1\u015f\1\uffff\1\u015e\5\uffff\1\u00b0\3\uffff\1\142\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u013a\13\uffff\1\u013b",
            "\1\u013a\13\uffff\1\u013b",
            "\1\u013a\13\uffff\1\u013b",
            "\1\u013a\13\uffff\1\u013b",
            "\1\u0161\13\uffff\1\u0160",
            "\1\u0163\13\uffff\1\u0162",
            "\1\u0091\1\u008f\1\uffff\1\u0164\10\uffff\1\u0090",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0165\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u0167\13\uffff\1\u0166",
            "\1\u0169\13\uffff\1\u0168",
            "\1\u016b\13\uffff\1\u016a",
            "\1\u016d\13\uffff\1\u016c",
            "\1\153\2\uffff\1\u016e\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0170\13\uffff\1\u016f",
            "\1\153\2\uffff\1\u0171\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0172\13\uffff\1\u0173",
            "\1\u0175\13\uffff\1\u0174",
            "\1\153\2\uffff\1\u0176\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0178\13\uffff\1\u0177",
            "\1\u00fb\2\uffff\1\u0179\120\uffff\2\u00fa",
            "\1\u017b\13\uffff\1\u017a",
            "\1\153\2\uffff\1\u017c\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u017d\11\uffff\1\u012f",
            "\1\u017e\1\u012e\12\uffff\1\u012f",
            "\1\u0156\2\uffff\1\u0157\13\uffff\1\u0158",
            "\1\u0180\1\u012e\1\u017f\11\uffff\1\u012f",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0159\13\uffff\1\u015a",
            "\1\u009f\3\uffff\1\u00a0\1\u00a1\1\u00a2\1\u00a3\7\uffff\1"+
            "\u00a9\114\uffff\1\u00a9\1\u00a6\1\u00a7\1\u00a4\1\u00a5\7\uffff"+
            "\2\u00a8",
            "\1\u0182\13\uffff\1\u0181",
            "\1\u0184\13\uffff\1\u0183",
            "\1\u0185",
            "\1\u0187\13\uffff\1\u0186",
            "\1\153\2\uffff\1\u0188\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u0189\11\uffff\1\u0090",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u018a\11\uffff\1\u0090",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u018c\13\uffff\1\u018b",
            "\1\137\123\uffff\2\136",
            "\1\137\1\uffff\1\u018d\121\uffff\2\136",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u018e\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\3\uffff\1\147\1\150\1\151\1\152\4\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u018f\1\uffff\1\147\1\150\1\151\1\152\4\uffff"+
            "\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4\4\uffff\1\u00f5"+
            "\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0190\1\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4"+
            "\4\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4\4\uffff\1\u00f5"+
            "\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0191\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0192\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2"+
            "\145\2\uffff\2\144",
            "\1\u0194\1\uffff\1\u0193\1\uffff\1\u0195\1\u0196\1\u0197\1"+
            "\u0198",
            "\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0199\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u00fb\123\uffff\2\u00fa",
            "\1\u00fb\1\uffff\1\u019a\121\uffff\2\u00fa",
            "\1\u00fb\123\uffff\2\u00fa",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u019b\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u019c\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u019e\13\uffff\1\u019d",
            "\1\u0130\1\u012e\1\uffff\1\u019f\10\uffff\1\u012f",
            "\1\u01a1\13\uffff\1\u01a0",
            "\1\u0130\1\u012e\1\uffff\1\u01a2\10\uffff\1\u012f",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u01a3\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\u01a4\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u01a5\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u01a6\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u01a8\13\uffff\1\u01a7",
            "\1\u01aa\13\uffff\1\u01a9",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u01ab\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u01ad\13\uffff\1\u01ac",
            "\1\u01af\13\uffff\1\u01ae",
            "\1\u01b1\13\uffff\1\u01b0",
            "\1\u01b3\13\uffff\1\u01b2",
            "\1\u01b5\13\uffff\1\u01b4",
            "\1\153\2\uffff\1\u01b6\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u01b8\13\uffff\1\u01b7",
            "\1\u01b9",
            "\1\u01bb\1\uffff\1\u01bd\1\u01ba\10\uffff\1\u01bc",
            "\1\u01bb\1\u01be\1\uffff\1\u01bf\10\uffff\1\u01bc",
            "\1\u01bb\1\u01c0\12\uffff\1\u01bc",
            "\1\u01c1",
            "\1\u01c3\13\uffff\1\u01c2",
            "\1\u01c5\13\uffff\1\u01c4",
            "\1\u01c7\13\uffff\1\u01c6",
            "\1\153\2\uffff\1\u01c8\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u01c9\11\uffff\1\u012f",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u01ca\11\uffff\1\u012f",
            "\1\u01cb\1\u012e\12\uffff\1\u012f",
            "\1\u01cd\13\uffff\1\u01cc",
            "\1\u01cf\13\uffff\1\u01ce",
            "\1\u01d1\13\uffff\1\u01d0",
            "\1\153\2\uffff\1\u01d2\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u01d3\11\uffff\1\u0090",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u01d4\11\uffff\1\u0090",
            "\1\u01d6\13\uffff\1\u01d5",
            "\1\137\123\uffff\2\136",
            "\1\137\1\uffff\1\u01d7\121\uffff\2\136",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u01d8\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\3\uffff\1\147\1\150\1\151\1\152\4\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u01d9\1\uffff\1\147\1\150\1\151\1\152\4\uffff"+
            "\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4\4\uffff\1\u00f5"+
            "\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u01da\1\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4"+
            "\4\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u01db\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\u01dc\1\uffff\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\u01dd\3\uffff\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\u01bb\13\uffff\1\u01bc",
            "\1\u01df\1\uffff\1\u01de\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u01bb\13\uffff\1\u01bc",
            "\1\u01bb\13\uffff\1\u01bc",
            "\1\u01bb\13\uffff\1\u01bc",
            "\1\u01bb\13\uffff\1\u01bc",
            "\1\u01bb\13\uffff\1\u01bc",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u01e0\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u00fb\123\uffff\2\u00fa",
            "\1\u00fb\1\uffff\1\u01e1\121\uffff\2\u00fa",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u01e2\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u01e4\13\uffff\1\u01e3",
            "\1\u01e6\13\uffff\1\u01e5",
            "\1\u0130\1\u012e\1\uffff\1\u01e7\10\uffff\1\u012f",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u01e8\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\u01e9\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u01ea\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u01ec\13\uffff\1\u01eb",
            "\1\u01ee\13\uffff\1\u01ed",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u01ef\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u01f1\13\uffff\1\u01f0",
            "\1\u01f3\13\uffff\1\u01f2",
            "\1\u01f5\13\uffff\1\u01f4",
            "\1\u01f7\13\uffff\1\u01f6",
            "\1\u01f9\13\uffff\1\u01f8",
            "\1\u01fb\13\uffff\1\u01fa",
            "\1\u01fc",
            "\1\u01fe\13\uffff\1\u01fd",
            "\1\153\2\uffff\1\u01ff\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0201\13\uffff\1\u0200",
            "\1\u0203\13\uffff\1\u0202",
            "\1\u0205\13\uffff\1\u0204",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u0206\11\uffff\1\u012f",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u0207\11\uffff\1\u012f",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0209\13\uffff\1\u0208",
            "\1\u020b\13\uffff\1\u020a",
            "\1\u020d\13\uffff\1\u020c",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u020e\11\uffff\1\u0090",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u020f\11\uffff\1\u0090",
            "\1\u0211\13\uffff\1\u0210",
            "\1\137\123\uffff\2\136",
            "\1\137\1\uffff\1\u0212\121\uffff\2\136",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0213\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\3\uffff\1\147\1\150\1\151\1\152\4\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0214\1\uffff\1\147\1\150\1\151\1\152\4\uffff"+
            "\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4\4\uffff\1\u00f5"+
            "\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0215\1\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4"+
            "\4\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0216\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\u0217\1\uffff\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0218\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0219\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2"+
            "\145\2\uffff\2\144",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u021a\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u00fb\123\uffff\2\u00fa",
            "\1\u00fb\1\uffff\1\u021b\121\uffff\2\u00fa",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u021c\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u021e\13\uffff\1\u021d",
            "\1\u0220\13\uffff\1\u021f",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0221\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\u0222\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0223\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u0225\13\uffff\1\u0224",
            "\1\u0227\13\uffff\1\u0226",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0228\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u022a\13\uffff\1\u0229",
            "\1\u022c\13\uffff\1\u022b",
            "\1\u022e\13\uffff\1\u022d",
            "\1\u0230\13\uffff\1\u022f",
            "\1\u0232\13\uffff\1\u0231",
            "\1\u0234\13\uffff\1\u0233",
            "\1\u0236\13\uffff\1\u0235",
            "\1\153\2\uffff\1\u0237\120\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0239\13\uffff\1\u0238",
            "\1\u023b\13\uffff\1\u023a",
            "\1\u023d\13\uffff\1\u023c",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u023e\11\uffff\1\u012f",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u023f\11\uffff\1\u012f",
            "\1\u0241\13\uffff\1\u0240",
            "\1\u0243\13\uffff\1\u0242",
            "\1\u0245\13\uffff\1\u0244",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u0246\11\uffff\1\u0090",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u0247\11\uffff\1\u0090",
            "\1\u0249\13\uffff\1\u0248",
            "\1\137\123\uffff\2\136",
            "\1\137\1\uffff\1\u024a\121\uffff\2\136",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u024b\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\3\uffff\1\147\1\150\1\151\1\152\4\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u024c\1\uffff\1\147\1\150\1\151\1\152\4\uffff"+
            "\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4\4\uffff\1\u00f5"+
            "\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u024d\1\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4"+
            "\4\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u024e\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\u024f\1\uffff\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0250\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0251\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u00fb\123\uffff\2\u00fa",
            "\1\u00fb\1\uffff\1\u0252\121\uffff\2\u00fa",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0253\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u0255\13\uffff\1\u0254",
            "\1\u0257\13\uffff\1\u0256",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0258\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\u0259\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u025a\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u025c\13\uffff\1\u025b",
            "\1\u025e\13\uffff\1\u025d",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u025f\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u0261\13\uffff\1\u0260",
            "\1\u0263\13\uffff\1\u0262",
            "\1\u0265\13\uffff\1\u0264",
            "\1\u0267\13\uffff\1\u0266",
            "\1\u0269\13\uffff\1\u0268",
            "\1\u026b\13\uffff\1\u026a",
            "\1\u026d\13\uffff\1\u026c",
            "\1\u026f\13\uffff\1\u026e",
            "\1\u0271\13\uffff\1\u0270",
            "\1\u0273\13\uffff\1\u0272",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u0274\11\uffff\1\u012f",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u0275\11\uffff\1\u012f",
            "\1\u0277\13\uffff\1\u0276",
            "\1\u0279\13\uffff\1\u0278",
            "\1\u027b\13\uffff\1\u027a",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u027c\11\uffff\1\u0090",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u027d\11\uffff\1\u0090",
            "\1\u027f\13\uffff\1\u027e",
            "\1\137\123\uffff\2\136",
            "\1\137\1\uffff\1\u0280\121\uffff\2\136",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0281\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\3\uffff\1\147\1\150\1\151\1\152\4\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0282\1\uffff\1\147\1\150\1\151\1\152\4\uffff"+
            "\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4\4\uffff\1\u00f5"+
            "\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0283\1\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4"+
            "\4\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0284\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\u0285\1\uffff\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0286\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0287\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u00fb\123\uffff\2\u00fa",
            "\1\u00fb\1\uffff\1\u0288\121\uffff\2\u00fa",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0289\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u028b\13\uffff\1\u028a",
            "\1\u028d\13\uffff\1\u028c",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u028e\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\u028f\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0290\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u0292\13\uffff\1\u0291",
            "\1\u0294\13\uffff\1\u0293",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0295\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u0297\13\uffff\1\u0296",
            "\1\u0299\13\uffff\1\u0298",
            "\1\u029b\13\uffff\1\u029a",
            "\1\u029d\13\uffff\1\u029c",
            "\1\u029f\13\uffff\1\u029e",
            "\1\u02a1\13\uffff\1\u02a0",
            "\1\u02a3\13\uffff\1\u02a2",
            "\1\u02a5\13\uffff\1\u02a4",
            "\1\u02a7\13\uffff\1\u02a6",
            "\1\u02a9\13\uffff\1\u02a8",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u02aa\11\uffff\1\u012f",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u02ab\11\uffff\1\u012f",
            "\1\u02ad\13\uffff\1\u02ac",
            "\1\u02af\13\uffff\1\u02ae",
            "\1\u02b1\13\uffff\1\u02b0",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u02b2\11\uffff\1\u0090",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u02b3\11\uffff\1\u0090",
            "\1\u02b5\13\uffff\1\u02b4",
            "\1\137\123\uffff\2\136",
            "\1\137\1\uffff\1\u02b6\121\uffff\2\136",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u02b7\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\3\uffff\1\147\1\150\1\151\1\152\4\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u02b8\1\uffff\1\147\1\150\1\151\1\152\4\uffff"+
            "\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4\4\uffff\1\u00f5"+
            "\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u02b9\1\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4"+
            "\4\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u02ba\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\u02bb\1\uffff\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u02bc\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u02bd\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u00fb\123\uffff\2\u00fa",
            "\1\u00fb\1\uffff\1\u02be\121\uffff\2\u00fa",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u02bf\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u02c1\13\uffff\1\u02c0",
            "\1\u02c3\13\uffff\1\u02c2",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u02c4\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\u02c5\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u02c6\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u02c8\13\uffff\1\u02c7",
            "\1\u02ca\13\uffff\1\u02c9",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u02cb\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u02cc",
            "\1\u02ce\13\uffff\1\u02cd",
            "\1\u02cf",
            "\1\u02d1\13\uffff\1\u02d0",
            "\1\u02d3\13\uffff\1\u02d2",
            "\1\u02d5\13\uffff\1\u02d4",
            "\1\u02d7\13\uffff\1\u02d6",
            "\1\u02d9\13\uffff\1\u02d8",
            "\1\u02db\13\uffff\1\u02da",
            "\1\u02dd\13\uffff\1\u02dc",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u02de\11\uffff\1\u012f",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u02df\11\uffff\1\u012f",
            "\1\u02e1\13\uffff\1\u02e0",
            "\1\u02e3\13\uffff\1\u02e2",
            "\1\u02e5\13\uffff\1\u02e4",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u02e6\11\uffff\1\u0090",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u02e7\11\uffff\1\u0090",
            "\1\u02e8",
            "\1\137\123\uffff\2\136",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u02e9\11\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\3\uffff\1\147\1\150\1\151\1\152\4\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4\4\uffff\1\u00f5"+
            "\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u02ea\1\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4"+
            "\4\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u02eb\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\u02ec\1\uffff\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u02ed\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u02ee\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u00fb\123\uffff\2\u00fa",
            "\1\u00fb\1\uffff\1\u02ef\121\uffff\2\u00fa",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u02f0\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u02f2\13\uffff\1\u02f1",
            "\1\u02f4\13\uffff\1\u02f3",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u02f5\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\u02f6\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u02f7\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u02f8",
            "\1\u02fa\13\uffff\1\u02f9",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u02fb",
            "\1\u02fd\13\uffff\1\u02fc",
            "\1\u02ff\13\uffff\1\u02fe",
            "\1\u0301\13\uffff\1\u0300",
            "\1\u0303\13\uffff\1\u0302",
            "\1\u0305\13\uffff\1\u0304",
            "\1\u0307\13\uffff\1\u0306",
            "\1\u0309\13\uffff\1\u0308",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u030a\11\uffff\1\u012f",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u030b\11\uffff\1\u012f",
            "\1\u030c",
            "\1\u030e\13\uffff\1\u030d",
            "\1\u0310\13\uffff\1\u030f",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\u0091\1\u008f\1\u0311\11\uffff\1\u0090",
            "\1\153\13\uffff\1\142\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\3\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4\4\uffff\1\u00f5"+
            "\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0312\1\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4"+
            "\4\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0313\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\u0314\1\uffff\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0315\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0316\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u00fb\123\uffff\2\u00fa",
            "\1\u00fb\1\uffff\1\u0317\121\uffff\2\u00fa",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0318\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u031a\13\uffff\1\u0319",
            "\1\u031c\13\uffff\1\u031b",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\u031d\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u031e\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u031f",
            "\1\u0320",
            "\1\u0322\13\uffff\1\u0321",
            "\1\u0324\13\uffff\1\u0323",
            "\1\u0326\13\uffff\1\u0325",
            "\1\u0327",
            "\1\u0328",
            "\1\u032a\13\uffff\1\u0329",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u032b\11\uffff\1\u012f",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u032c\11\uffff\1\u012f",
            "\1\u032d",
            "\1\u032f\13\uffff\1\u032e",
            "\1\u0091\1\u008f\12\uffff\1\u0090",
            "\1\153\3\uffff\1\u00f1\1\u00f2\1\u00f3\1\u00f4\4\uffff\1\u00f5"+
            "\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0330\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\u0331\1\uffff\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0332\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u00fb\123\uffff\2\u00fa",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\1\uffff\1\u0333\11\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u0334",
            "\1\u0336\13\uffff\1\u0335",
            "\1\u0106\1\u0107\1\u0108\1\u0109",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0337\5\uffff\1\u00b0\3\uffff\1\142\107\uffff"+
            "\2\143\2\145\2\uffff\2\144",
            "\1\u0338",
            "\1\u033a\13\uffff\1\u0339",
            "\1\u033c\13\uffff\1\u033b",
            "\1\u033d",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0130\1\u012e\1\u033e\11\uffff\1\u012f",
            "\1\u033f",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\u0340\1\uffff\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0341\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\153\13\uffff\1\u00f5\107\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0342",
            "\1\153\7\uffff\1\u00b0\3\uffff\1\142\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\u0343",
            "\1\u0345\13\uffff\1\u0344",
            "\1\u0130\1\u012e\12\uffff\1\u012f",
            "\1\u0195\1\u0196\1\u0197\1\u0198",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144",
            "\1\153\1\uffff\1\u0346\5\uffff\1\u014c\3\uffff\1\u00f5\107"+
            "\uffff\2\143\2\145\2\uffff\2\144",
            "\1\u0347",
            "\1\153\7\uffff\1\u014c\3\uffff\1\u00f5\107\uffff\2\143\2\145"+
            "\2\uffff\2\144"
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
        "\2\153\2\uffff";
    static final String DFA3_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA3_specialS =
        "\4\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\3\142\uffff\4\2",
            "\1\1\1\3\142\uffff\4\2",
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
        "\2\133\2\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA5_specialS =
        "\4\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\1\1\3\13\uffff\2\2\107\uffff\2\2",
            "\1\1\1\3\13\uffff\2\2\107\uffff\2\2",
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
        "\2\137\2\uffff";
    static final String DFA7_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA7_specialS =
        "\4\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\1\123\uffff\2\3\2\2\2\uffff\2\3",
            "\1\1\123\uffff\2\3\2\2\2\uffff\2\3",
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
            return "()* loopback of 55:30: ( spaces rel= logicalOp spaces constraint1 )*";
        }
    }
    static final String DFA10_eotS =
        "\32\uffff";
    static final String DFA10_eofS =
        "\32\uffff";
    static final String DFA10_minS =
        "\1\24\3\4\1\26\1\4\4\uffff\11\4\1\6\2\4\1\26\3\4";
    static final String DFA10_maxS =
        "\1\147\1\155\2\17\1\123\1\155\4\uffff\1\17\1\51\1\17\1\51\2\155"+
        "\1\51\1\20\1\51\1\6\1\20\1\155\1\113\2\20\1\155";
    static final String DFA10_acceptS =
        "\6\uffff\1\2\1\3\1\1\1\4\20\uffff";
    static final String DFA10_specialS =
        "\32\uffff}>";
    static final String[] DFA10_transitionS = {
            "\26\1\21\uffff\1\2\51\uffff\1\2\2\3",
            "\1\5\1\uffff\1\4\1\uffff\4\10\7\uffff\1\6\114\uffff\1\6\4\7"+
            "\7\uffff\2\11",
            "\1\12\12\uffff\1\13",
            "\1\14\12\uffff\1\15",
            "\3\17\21\uffff\42\17\10\16",
            "\1\5\3\uffff\4\10\7\uffff\1\6\114\uffff\1\6\4\7\7\uffff\2\11",
            "",
            "",
            "",
            "",
            "\1\12\12\uffff\1\13",
            "\1\20\17\uffff\26\21",
            "\1\14\12\uffff\1\15",
            "\1\22\17\uffff\26\23",
            "\1\5\3\uffff\4\10\7\uffff\1\6\114\uffff\1\6\4\7\7\uffff\2\11",
            "\1\5\3\uffff\4\10\7\uffff\1\6\114\uffff\1\6\4\7\7\uffff\2\11",
            "\1\20\17\uffff\26\21",
            "\1\24\13\uffff\1\25",
            "\1\22\17\uffff\26\23",
            "\1\26",
            "\1\24\13\uffff\1\25",
            "\1\5\3\uffff\4\10\7\uffff\1\6\114\uffff\1\6\4\7\7\uffff\2\11",
            "\3\27\21\uffff\42\27",
            "\1\30\13\uffff\1\31",
            "\1\30\13\uffff\1\31",
            "\1\5\3\uffff\4\10\7\uffff\1\6\114\uffff\1\6\4\7\7\uffff\2\11"
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
            return "64:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue | kw= keyword spaces op3= between spaces val3= betValue );";
        }
    }
    static final String DFA11_eotS =
        "\56\uffff";
    static final String DFA11_eofS =
        "\1\uffff\1\5\2\uffff\1\5\2\uffff\1\12\1\14\2\uffff\1\14\2\uffff"+
        "\1\21\4\uffff\1\25\3\uffff\1\31\3\uffff\1\35\3\uffff\1\41\3\uffff"+
        "\1\45\3\uffff\1\51\3\uffff\1\55\2\uffff";
    static final String DFA11_minS =
        "\1\7\1\4\1\uffff\1\7\1\4\2\uffff\2\4\1\7\1\uffff\1\4\2\uffff\1\4"+
        "\1\uffff\1\7\2\uffff\1\4\1\7\2\uffff\1\4\1\7\2\uffff\1\4\1\7\2\uffff"+
        "\1\4\1\7\2\uffff\1\4\1\7\2\uffff\1\4\1\7\2\uffff\1\4\2\uffff";
    static final String DFA11_maxS =
        "\1\23\1\137\1\uffff\1\23\1\137\2\uffff\2\137\1\23\1\uffff\1\137"+
        "\2\uffff\1\137\1\uffff\1\23\2\uffff\1\137\1\23\2\uffff\1\137\1\23"+
        "\2\uffff\1\137\1\23\2\uffff\1\137\1\23\2\uffff\1\137\1\23\2\uffff"+
        "\1\137\1\23\2\uffff\1\137\2\uffff";
    static final String DFA11_acceptS =
        "\2\uffff\1\2\2\uffff\1\1\1\3\3\uffff\1\4\1\uffff\1\26\1\5\1\uffff"+
        "\1\27\1\uffff\1\6\1\7\2\uffff\1\10\1\11\2\uffff\1\12\1\13\2\uffff"+
        "\1\14\1\15\2\uffff\1\16\1\17\2\uffff\1\20\1\21\2\uffff\1\22\1\23"+
        "\1\uffff\1\25\1\24";
    static final String DFA11_specialS =
        "\56\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1\13\uffff\1\2",
            "\1\4\1\5\1\3\1\uffff\5\5\3\uffff\1\5\107\uffff\4\5\2\uffff"+
            "\2\5",
            "",
            "\1\7\13\uffff\1\6",
            "\2\5\1\uffff\1\10\10\uffff\1\5\107\uffff\4\5\2\uffff\2\5",
            "",
            "",
            "\2\12\1\11\1\uffff\5\12\3\uffff\1\12\107\uffff\4\12\2\uffff"+
            "\2\12",
            "\1\13\1\14\2\uffff\5\14\3\uffff\1\14\107\uffff\4\14\2\uffff"+
            "\2\14",
            "\1\16\13\uffff\1\15",
            "",
            "\2\14\1\uffff\1\17\10\uffff\1\14\107\uffff\4\14\2\uffff\2\14",
            "",
            "",
            "\2\21\1\20\1\uffff\5\21\3\uffff\1\21\107\uffff\4\21\2\uffff"+
            "\2\21",
            "",
            "\1\23\13\uffff\1\22",
            "",
            "",
            "\2\25\1\24\1\uffff\5\25\3\uffff\1\25\107\uffff\4\25\2\uffff"+
            "\2\25",
            "\1\27\13\uffff\1\26",
            "",
            "",
            "\2\31\1\30\1\uffff\5\31\3\uffff\1\31\107\uffff\4\31\2\uffff"+
            "\2\31",
            "\1\33\13\uffff\1\32",
            "",
            "",
            "\2\35\1\34\1\uffff\5\35\3\uffff\1\35\107\uffff\4\35\2\uffff"+
            "\2\35",
            "\1\37\13\uffff\1\36",
            "",
            "",
            "\2\41\1\40\1\uffff\5\41\3\uffff\1\41\107\uffff\4\41\2\uffff"+
            "\2\41",
            "\1\43\13\uffff\1\42",
            "",
            "",
            "\2\45\1\44\1\uffff\5\45\3\uffff\1\45\107\uffff\4\45\2\uffff"+
            "\2\45",
            "\1\47\13\uffff\1\46",
            "",
            "",
            "\2\51\1\50\1\uffff\5\51\3\uffff\1\51\107\uffff\4\51\2\uffff"+
            "\2\51",
            "\1\53\13\uffff\1\52",
            "",
            "",
            "\2\55\1\54\1\uffff\5\55\3\uffff\1\55\107\uffff\4\55\2\uffff"+
            "\2\55",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "93:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );";
        }
    }
    static final String DFA12_eotS =
        "\4\uffff";
    static final String DFA12_eofS =
        "\4\uffff";
    static final String DFA12_minS =
        "\2\4\2\uffff";
    static final String DFA12_maxS =
        "\2\20\2\uffff";
    static final String DFA12_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA12_specialS =
        "\4\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\1\1\3\12\uffff\1\2",
            "\1\1\1\3\12\uffff\1\2",
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
            return "()* loopback of 119:21: ( spaces COMMA spaces dotValue )*";
        }
    }
    static final String DFA13_eotS =
        "\15\uffff";
    static final String DFA13_eofS =
        "\15\uffff";
    static final String DFA13_minS =
        "\1\10\3\4\11\uffff";
    static final String DFA13_maxS =
        "\1\13\3\23\11\uffff";
    static final String DFA13_acceptS =
        "\4\uffff\1\4\1\6\1\5\1\1\1\2\1\11\1\7\1\10\1\3";
    static final String DFA13_specialS =
        "\15\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\1\1\2\1\3\1\4",
            "\1\7\2\uffff\1\7\1\uffff\1\5\1\6\10\uffff\1\7",
            "\1\10\2\uffff\1\10\1\12\1\uffff\1\11\10\uffff\1\10",
            "\1\14\2\uffff\1\14\1\13\12\uffff\1\14",
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
            return "121:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );";
        }
    }
    static final String DFA15_eotS =
        "\46\uffff";
    static final String DFA15_eofS =
        "\1\uffff\2\5\1\uffff\1\5\2\uffff\3\5\1\uffff\4\5\1\uffff\2\5\1\uffff"+
        "\2\5\1\uffff\2\5\1\uffff\2\5\1\uffff\2\5\1\uffff\2\5\1\uffff\2\5"+
        "\1\uffff\1\5";
    static final String DFA15_minS =
        "\1\7\2\4\1\7\1\4\2\uffff\3\4\1\7\4\4\1\7\2\4\1\7\2\4\1\7\2\4\1\7"+
        "\2\4\1\7\2\4\1\7\2\4\1\7\2\4\1\23\1\4";
    static final String DFA15_maxS =
        "\1\23\2\137\1\23\1\137\2\uffff\3\137\1\23\4\137\1\23\2\137\1\23"+
        "\2\137\1\23\2\137\1\23\2\137\1\23\2\137\1\23\2\137\1\23\2\137\1"+
        "\23\1\137";
    static final String DFA15_acceptS =
        "\5\uffff\1\1\1\2\37\uffff";
    static final String DFA15_specialS =
        "\46\uffff}>";
    static final String[] DFA15_transitionS = {
            "\1\1\13\uffff\1\2",
            "\1\4\1\uffff\1\3\1\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff"+
            "\2\5",
            "\1\5\3\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff\2\5",
            "\1\10\13\uffff\1\7",
            "\1\5\2\uffff\1\11\120\uffff\4\5\2\uffff\2\5",
            "",
            "",
            "\1\5\3\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\12\1\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff"+
            "\2\5",
            "\1\13\3\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff\2\5",
            "\1\15\13\uffff\1\14",
            "\1\5\2\uffff\1\16\120\uffff\4\5\2\uffff\2\5",
            "\1\5\3\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\17\1\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff"+
            "\2\5",
            "\1\5\3\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff\2\5",
            "\1\21\13\uffff\1\20",
            "\1\5\3\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\22\1\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff"+
            "\2\5",
            "\1\24\13\uffff\1\23",
            "\1\5\3\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\25\1\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff"+
            "\2\5",
            "\1\27\13\uffff\1\26",
            "\1\5\3\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\30\1\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff"+
            "\2\5",
            "\1\32\13\uffff\1\31",
            "\1\5\3\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\33\1\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff"+
            "\2\5",
            "\1\35\13\uffff\1\34",
            "\1\5\3\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\36\1\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff"+
            "\2\5",
            "\1\40\13\uffff\1\37",
            "\1\5\3\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\41\1\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff"+
            "\2\5",
            "\1\43\13\uffff\1\42",
            "\1\5\3\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\44\1\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff"+
            "\2\5",
            "\1\45",
            "\1\5\3\uffff\4\6\4\uffff\1\5\107\uffff\4\5\2\uffff\2\5"
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
            return "131:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );";
        }
    }
 

    public static final BitSet FOLLOW_select_in_stmt27 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_spaces_in_stmt29 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_selectList_in_stmt31 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt33 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_where_in_stmt35 = new BitSet(new long[]{0x080003FFFFF08010L,0x000000E000000000L});
    public static final BitSet FOLLOW_spaces_in_stmt37 = new BitSet(new long[]{0x080003FFFFF08010L,0x000000E000000000L});
    public static final BitSet FOLLOW_constraintList_in_stmt39 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_spaces_in_stmt41 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt47 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_spaces_in_stmt49 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_selectList_in_stmt51 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_spaces_in_stmt53 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt58 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_spaces_in_stmt60 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_selectList_in_stmt62 = new BitSet(new long[]{0x0000000000000010L,0x000000000C000000L});
    public static final BitSet FOLLOW_spaces_in_stmt64 = new BitSet(new long[]{0x0000000000000010L,0x000000000C000000L});
    public static final BitSet FOLLOW_order_in_stmt66 = new BitSet(new long[]{0x0000000000000010L,0x0000000030000000L});
    public static final BitSet FOLLOW_spaces_in_stmt68 = new BitSet(new long[]{0x0000000000000010L,0x0000000030000000L});
    public static final BitSet FOLLOW_by_in_stmt70 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_spaces_in_stmt72 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_orderList_in_stmt74 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt79 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_spaces_in_stmt81 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_selectList_in_stmt83 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt85 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_where_in_stmt87 = new BitSet(new long[]{0x080003FFFFF08010L,0x000000E000000000L});
    public static final BitSet FOLLOW_spaces_in_stmt89 = new BitSet(new long[]{0x080003FFFFF08010L,0x000000E000000000L});
    public static final BitSet FOLLOW_constraintList_in_stmt91 = new BitSet(new long[]{0x0000000000000010L,0x000000000C000000L});
    public static final BitSet FOLLOW_spaces_in_stmt93 = new BitSet(new long[]{0x0000000000000010L,0x000000000C000000L});
    public static final BitSet FOLLOW_order_in_stmt95 = new BitSet(new long[]{0x0000000000000010L,0x0000000030000000L});
    public static final BitSet FOLLOW_spaces_in_stmt97 = new BitSet(new long[]{0x0000000000000010L,0x0000000030000000L});
    public static final BitSet FOLLOW_by_in_stmt99 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_spaces_in_stmt101 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_orderList_in_stmt103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SPACE_in_spaces113 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_keyword_in_orderList125 = new BitSet(new long[]{0x0000000000000030L,0x00000F0000000000L});
    public static final BitSet FOLLOW_spaces_in_orderList138 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_orderList142 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_spaces_in_orderList146 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_keyword_in_orderList153 = new BitSet(new long[]{0x0000000000000030L,0x00000F0000000000L});
    public static final BitSet FOLLOW_spaces_in_orderList168 = new BitSet(new long[]{0x0000000000000000L,0x00000F0000000000L});
    public static final BitSet FOLLOW_ordering_in_orderList176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_asc_in_ordering192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_desc_in_ordering194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_selectList207 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_selectList220 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_selectList224 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_spaces_in_selectList228 = new BitSet(new long[]{0x080003FFFFF00010L,0x000000E000000000L});
    public static final BitSet FOLLOW_keyword_in_selectList235 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_entity_in_keyword260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword266 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword268 = new BitSet(new long[]{0xFFFFFC0001C00000L,0x0000000000000FFFL});
    public static final BitSet FOLLOW_attr_in_keyword270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword275 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword277 = new BitSet(new long[]{0x0000000000000000L,0x00000000000FF000L});
    public static final BitSet FOLLOW_funct_in_keyword279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_count_in_keyword284 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword286 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword288 = new BitSet(new long[]{0x000003FFFFF00010L});
    public static final BitSet FOLLOW_spaces_in_keyword290 = new BitSet(new long[]{0x000003FFFFF00000L});
    public static final BitSet FOLLOW_entity_in_keyword292 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword294 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sum_in_keyword301 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword303 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword305 = new BitSet(new long[]{0x000003FFFFF00010L});
    public static final BitSet FOLLOW_spaces_in_keyword307 = new BitSet(new long[]{0x000003FFFFF00000L});
    public static final BitSet FOLLOW_entity_in_keyword309 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword311 = new BitSet(new long[]{0xFFFFFC0001C00000L,0x0000000000000FFFL});
    public static final BitSet FOLLOW_attr_in_keyword313 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword315 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint1_in_constraintList326 = new BitSet(new long[]{0x0000000000000012L,0x00000000C3000000L});
    public static final BitSet FOLLOW_spaces_in_constraintList330 = new BitSet(new long[]{0x0000000000000010L,0x00000000C3000000L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList337 = new BitSet(new long[]{0x080003FFFFF08010L,0x000000E000000000L});
    public static final BitSet FOLLOW_spaces_in_constraintList345 = new BitSet(new long[]{0x080003FFFFF08010L,0x000000E000000000L});
    public static final BitSet FOLLOW_constraint1_in_constraintList347 = new BitSet(new long[]{0x0000000000000012L,0x00000000C3000000L});
    public static final BitSet FOLLOW_lb_in_lopen358 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_rb_in_ropen369 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_lopen_in_constraint1387 = new BitSet(new long[]{0x080003FFFFF08010L,0x000000E000000000L});
    public static final BitSet FOLLOW_constraint_in_constraint1409 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ropen_in_constraint1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint450 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_spaces_in_constraint459 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_compOpt_in_constraint466 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint476 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_genValue_in_constraint483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint511 = new BitSet(new long[]{0x0000000000080010L,0x0000000100000000L});
    public static final BitSet FOLLOW_spaces_in_constraint520 = new BitSet(new long[]{0x0000000000080010L,0x0000000100000000L});
    public static final BitSet FOLLOW_in_in_constraint527 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_constraint538 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_constraint540 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint544 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_valueList_in_constraint550 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_constraint558 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_constraint562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint588 = new BitSet(new long[]{0x0000000000000010L,0x0000001E00000000L});
    public static final BitSet FOLLOW_spaces_in_constraint597 = new BitSet(new long[]{0x0000000000000010L,0x0000001E00000000L});
    public static final BitSet FOLLOW_like_in_constraint604 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint613 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_constraint620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint635 = new BitSet(new long[]{0x0000000000000010L,0x0000300000000000L});
    public static final BitSet FOLLOW_spaces_in_constraint644 = new BitSet(new long[]{0x0000000000000010L,0x0000300000000000L});
    public static final BitSet FOLLOW_between_in_constraint651 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint659 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_betValue_in_constraint666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_dotValue740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue746 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue748 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue756 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue758 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue766 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue768 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue770 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue772 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue780 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue782 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue784 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue786 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue794 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue796 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue798 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue800 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue802 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue804 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue812 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue814 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue816 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue818 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue820 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue822 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue830 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue832 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue834 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue836 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue838 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue840 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue842 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue844 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue852 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue854 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue856 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue858 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue860 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue862 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue864 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue866 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue874 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue876 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue878 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue880 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue882 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue884 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue886 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue888 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue890 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue892 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue900 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue902 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue904 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue906 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue908 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue910 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue912 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue914 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue916 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue918 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue926 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue928 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue930 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue932 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue934 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue936 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue938 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue940 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue942 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue944 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue946 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue948 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue956 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue958 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue960 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue962 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue964 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue966 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue968 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue970 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue972 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue974 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue976 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue978 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue980 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_VALUE_in_dotValue1048 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_DOT_in_dotValue1076 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1078 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1080 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1082 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1084 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue1086 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_VALUE_in_dotValue1120 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1122 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1124 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_VALUE_in_dotValue1173 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1175 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1177 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1179 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1181 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1183 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1185 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1187 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1189 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1191 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1193 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1195 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1197 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1199 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1201 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1203 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1205 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1207 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1216 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1218 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1220 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1222 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1224 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1226 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1228 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1230 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1232 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1234 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1236 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1238 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1240 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1242 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1244 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1246 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1248 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1250 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1252 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1254 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue1256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1262 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1264 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1272 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1274 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1276 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1278 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_valueList1289 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_valueList1293 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_valueList1295 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_valueList1297 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_valueList1299 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_EQ_in_compOpt1311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt1325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_compOpt1332 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt1342 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt1345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt1352 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LT_in_compOpt1355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1362 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt1372 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1382 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt1385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue1393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue1398 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_compOpt_in_genValue1400 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1402 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_AMP_in_genValue1405 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1407 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_compOpt_in_genValue1409 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1411 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_dotValue_in_betValue1419 = new BitSet(new long[]{0x0000000000000010L,0x0000000003000000L});
    public static final BitSet FOLLOW_spaces_in_betValue1421 = new BitSet(new long[]{0x0000000000000000L,0x0000000003000000L});
    public static final BitSet FOLLOW_and_in_betValue1423 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_betValue1425 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_betValue1427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_in_logicalOp1439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp1441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity1450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr1541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct1694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select1732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and1753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_order1766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_by1779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or1792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in1805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not1818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_like1832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_100_in_like1836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_like1840 = new BitSet(new long[]{0x0000000000000010L,0x0000000800000000L});
    public static final BitSet FOLLOW_spaces_in_like1842 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_99_in_like1844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_like1848 = new BitSet(new long[]{0x0000000000000010L,0x0000001000000000L});
    public static final BitSet FOLLOW_spaces_in_like1850 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_like1852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_count1861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sum1874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_asc1887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_desc1900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_between1913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_lb1928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rb1938 = new BitSet(new long[]{0x0000000000000002L});

}