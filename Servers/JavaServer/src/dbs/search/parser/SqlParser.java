package dbs.search.parser;
// $ANTLR 3.1.2 Sql.g 2009-03-04 11:49:56


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

                if ( (LA6_1==EOF||(LA6_1>=SPACE && LA6_1<=COMMA)||(LA6_1>=EQ && LA6_1<=NOT)||(LA6_1>=17 && LA6_1<=19)||(LA6_1>=90 && LA6_1<=91)||(LA6_1>=96 && LA6_1<=100)||(LA6_1>=104 && LA6_1<=109)) ) {
                    alt6=1;
                }
                else if ( (LA6_1==DOT) ) {
                    int LA6_5 = input.LA(3);

                    if ( ((LA6_5>=22 && LA6_5<=24)||(LA6_5>=42 && LA6_5<=75)) ) {
                        alt6=2;
                    }
                    else if ( ((LA6_5>=76 && LA6_5<=83)) ) {
                        alt6=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 5, input);

                        throw nvae;
                    }
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


    // $ANTLR start "constraint1"
    // Sql.g:59:1: constraint1 : (kl= ( '(' ) )* constraint (kr= ( ')' ) )* ;
    public final void constraint1() throws RecognitionException {
        Token kl=null;
        Token kr=null;

        try {
            // Sql.g:59:17: ( (kl= ( '(' ) )* constraint (kr= ( ')' ) )* )
            // Sql.g:59:19: (kl= ( '(' ) )* constraint (kr= ( ')' ) )*
            {
            // Sql.g:59:21: (kl= ( '(' ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==15) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // Sql.g:59:21: kl= ( '(' )
            	    {
            	    // Sql.g:59:25: ( '(' )
            	    // Sql.g:59:26: '('
            	    {
            	    match(input,15,FOLLOW_15_in_constraint1367); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            Constraint c1=new Constraint();c1.setBracket((kl!=null?kl.getText():null));constraints.add(c1);
            pushFollow(FOLLOW_constraint_in_constraint1391);
            constraint();

            state._fsp--;

            // Sql.g:61:19: (kr= ( ')' ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==16) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Sql.g:61:19: kr= ( ')' )
            	    {
            	    // Sql.g:61:24: ( ')' )
            	    // Sql.g:61:25: ')'
            	    {
            	    match(input,16,FOLLOW_16_in_constraint1417); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            Constraint c=new Constraint();c.setBracket((kr!=null?kr.getText():null)); constraints.add(c);

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
    // Sql.g:63:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue | kw= keyword spaces op3= between spaces val3= betValue );
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
            // Sql.g:63:12: (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue | kw= keyword spaces op3= between spaces val3= betValue )
            int alt10=4;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // Sql.g:63:14: kw= keyword spaces op= compOpt spaces val= genValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint433);
                    kw=keyword();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint442);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_compOpt_in_constraint449);
                    op=compOpt();

                    state._fsp--;

                    c.setOp((op!=null?input.toString(op.start,op.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint459);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_genValue_in_constraint466);
                    val=genValue();

                    state._fsp--;

                    c.setValue((val!=null?input.toString(val.start,val.stop):null)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // Sql.g:69:2: kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')'
                    {
                    pushFollow(FOLLOW_keyword_in_constraint494);
                    kw=keyword();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint503);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_in_in_constraint510);
                    op1=in();

                    state._fsp--;

                    c.setOp((op1!=null?input.toString(op1.start,op1.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint521);
                    spaces();

                    state._fsp--;

                    match(input,15,FOLLOW_15_in_constraint523); 
                    pushFollow(FOLLOW_spaces_in_constraint527);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_valueList_in_constraint533);
                    val1=valueList();

                    state._fsp--;

                    c.setValue((val1!=null?input.toString(val1.start,val1.stop):null)); constraints.add(c);
                    pushFollow(FOLLOW_spaces_in_constraint541);
                    spaces();

                    state._fsp--;

                    match(input,16,FOLLOW_16_in_constraint545); 

                    }
                    break;
                case 3 :
                    // Sql.g:78:2: kw= keyword spaces op2= like spaces val2= dotValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint571);
                    kw=keyword();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint580);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_like_in_constraint587);
                    op2=like();

                    state._fsp--;

                    c.setOp((op2!=null?input.toString(op2.start,op2.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint596);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_dotValue_in_constraint603);
                    val2=dotValue();

                    state._fsp--;

                    c.setValue((val2!=null?input.toString(val2.start,val2.stop):null)); constraints.add(c);

                    }
                    break;
                case 4 :
                    // Sql.g:84:3: kw= keyword spaces op3= between spaces val3= betValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint618);
                    kw=keyword();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint627);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_between_in_constraint634);
                    op3=between();

                    state._fsp--;

                    c.setOp((op3!=null?input.toString(op3.start,op3.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint642);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_betValue_in_constraint649);
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
    // Sql.g:91:1: where : ( 'WHERE' | 'where' ) ;
    public final void where() throws RecognitionException {
        try {
            // Sql.g:91:7: ( ( 'WHERE' | 'where' ) )
            // Sql.g:91:8: ( 'WHERE' | 'where' )
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
    // Sql.g:92:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );
    public final SqlParser.dotValue_return dotValue() throws RecognitionException {
        SqlParser.dotValue_return retval = new SqlParser.dotValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:92:17: ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE )
            int alt11=23;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // Sql.g:92:19: VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue716); 

                    }
                    break;
                case 2 :
                    // Sql.g:93:5: 'in'
                    {
                    match(input,19,FOLLOW_19_in_dotValue723); 

                    }
                    break;
                case 3 :
                    // Sql.g:94:5: VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue729); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue731); 
                    match(input,19,FOLLOW_19_in_dotValue733); 

                    }
                    break;
                case 4 :
                    // Sql.g:95:5: VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue739); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue741); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue743); 

                    }
                    break;
                case 5 :
                    // Sql.g:96:5: VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue749); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue751); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue753); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue755); 
                    match(input,19,FOLLOW_19_in_dotValue757); 

                    }
                    break;
                case 6 :
                    // Sql.g:97:5: VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue763); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue765); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue767); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue769); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue771); 

                    }
                    break;
                case 7 :
                    // Sql.g:98:5: VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue777); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue779); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue781); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue783); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue785); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue787); 
                    match(input,19,FOLLOW_19_in_dotValue789); 

                    }
                    break;
                case 8 :
                    // Sql.g:99:5: VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue795); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue797); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue799); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue801); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue803); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue805); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue807); 

                    }
                    break;
                case 9 :
                    // Sql.g:100:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue813); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue815); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue817); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue819); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue821); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue823); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue825); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue827); 
                    match(input,19,FOLLOW_19_in_dotValue829); 

                    }
                    break;
                case 10 :
                    // Sql.g:101:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue835); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue837); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue839); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue841); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue843); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue845); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue847); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue849); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue851); 

                    }
                    break;
                case 11 :
                    // Sql.g:102:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue857); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue859); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue861); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue863); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue865); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue867); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue869); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue871); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue873); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue875); 
                    match(input,19,FOLLOW_19_in_dotValue877); 

                    }
                    break;
                case 12 :
                    // Sql.g:103:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue883); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue885); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue887); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue889); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue891); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue893); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue895); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue897); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue899); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue901); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue903); 

                    }
                    break;
                case 13 :
                    // Sql.g:104:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
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
                case 14 :
                    // Sql.g:105:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
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

                    }
                    break;
                case 15 :
                    // Sql.g:106:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
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
                case 16 :
                    // Sql.g:107:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
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

                    }
                    break;
                case 17 :
                    // Sql.g:108:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1037); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1039); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1041); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1043); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1045); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1047); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1049); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1051); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1053); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1055); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1057); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1059); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1061); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1063); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1065); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1067); 
                    match(input,19,FOLLOW_19_in_dotValue1069); 

                    }
                    break;
                case 18 :
                    // Sql.g:109:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
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
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1103); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1105); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1107); 

                    }
                    break;
                case 19 :
                    // Sql.g:110:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1114); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1116); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1118); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1120); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1122); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1124); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1126); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1128); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1130); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1132); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1134); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1136); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1138); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1140); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1142); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1144); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1146); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1148); 
                    match(input,19,FOLLOW_19_in_dotValue1150); 

                    }
                    break;
                case 20 :
                    // Sql.g:111:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1156); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1158); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1160); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1162); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1164); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1166); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1168); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1170); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1172); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1174); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1176); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1178); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1180); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1182); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1184); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1186); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1188); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1190); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1192); 

                    }
                    break;
                case 21 :
                    // Sql.g:112:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1199); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1201); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1203); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1205); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1207); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1209); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1211); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1213); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1215); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1217); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1219); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1221); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1223); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1225); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1227); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1229); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1231); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1233); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1235); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue1237); 
                    match(input,19,FOLLOW_19_in_dotValue1239); 

                    }
                    break;
                case 22 :
                    // Sql.g:113:5: VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1245); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1247); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1249); 

                    }
                    break;
                case 23 :
                    // Sql.g:114:5: VALUE SPACE VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1255); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1257); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1259); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1261); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1263); 

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
    // Sql.g:118:1: valueList : dotValue ( spaces COMMA spaces dotValue )* ;
    public final SqlParser.valueList_return valueList() throws RecognitionException {
        SqlParser.valueList_return retval = new SqlParser.valueList_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:118:11: ( dotValue ( spaces COMMA spaces dotValue )* )
            // Sql.g:118:12: dotValue ( spaces COMMA spaces dotValue )*
            {
            pushFollow(FOLLOW_dotValue_in_valueList1272);
            dotValue();

            state._fsp--;

            // Sql.g:118:21: ( spaces COMMA spaces dotValue )*
            loop12:
            do {
                int alt12=2;
                alt12 = dfa12.predict(input);
                switch (alt12) {
            	case 1 :
            	    // Sql.g:118:23: spaces COMMA spaces dotValue
            	    {
            	    pushFollow(FOLLOW_spaces_in_valueList1276);
            	    spaces();

            	    state._fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_valueList1278); 
            	    pushFollow(FOLLOW_spaces_in_valueList1280);
            	    spaces();

            	    state._fsp--;

            	    pushFollow(FOLLOW_dotValue_in_valueList1282);
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
    // Sql.g:120:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );
    public final SqlParser.compOpt_return compOpt() throws RecognitionException {
        SqlParser.compOpt_return retval = new SqlParser.compOpt_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:120:10: ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) )
            int alt13=9;
            alt13 = dfa13.predict(input);
            switch (alt13) {
                case 1 :
                    // Sql.g:120:11: ( EQ )
                    {
                    // Sql.g:120:11: ( EQ )
                    // Sql.g:120:12: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1294); 

                    }


                    }
                    break;
                case 2 :
                    // Sql.g:121:4: ( LT )
                    {
                    // Sql.g:121:4: ( LT )
                    // Sql.g:121:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1301); 

                    }


                    }
                    break;
                case 3 :
                    // Sql.g:122:4: ( GT )
                    {
                    // Sql.g:122:4: ( GT )
                    // Sql.g:122:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1308); 

                    }


                    }
                    break;
                case 4 :
                    // Sql.g:123:4: ( NOT ) ( EQ )
                    {
                    // Sql.g:123:4: ( NOT )
                    // Sql.g:123:5: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_compOpt1315); 

                    }

                    // Sql.g:123:9: ( EQ )
                    // Sql.g:123:10: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1318); 

                    }


                    }
                    break;
                case 5 :
                    // Sql.g:124:4: ( EQ ) ( GT )
                    {
                    // Sql.g:124:4: ( EQ )
                    // Sql.g:124:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1325); 

                    }

                    // Sql.g:124:8: ( GT )
                    // Sql.g:124:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1328); 

                    }


                    }
                    break;
                case 6 :
                    // Sql.g:125:4: ( EQ ) ( LT )
                    {
                    // Sql.g:125:4: ( EQ )
                    // Sql.g:125:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1335); 

                    }

                    // Sql.g:125:8: ( LT )
                    // Sql.g:125:9: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1338); 

                    }


                    }
                    break;
                case 7 :
                    // Sql.g:126:4: ( LT ) ( EQ )
                    {
                    // Sql.g:126:4: ( LT )
                    // Sql.g:126:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1345); 

                    }

                    // Sql.g:126:8: ( EQ )
                    // Sql.g:126:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1348); 

                    }


                    }
                    break;
                case 8 :
                    // Sql.g:127:4: ( GT ) ( EQ )
                    {
                    // Sql.g:127:4: ( GT )
                    // Sql.g:127:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1355); 

                    }

                    // Sql.g:127:8: ( EQ )
                    // Sql.g:127:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1358); 

                    }


                    }
                    break;
                case 9 :
                    // Sql.g:128:4: ( LT ) ( GT )
                    {
                    // Sql.g:128:4: ( LT )
                    // Sql.g:128:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1365); 

                    }

                    // Sql.g:128:8: ( GT )
                    // Sql.g:128:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1368); 

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
    // Sql.g:130:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );
    public final SqlParser.genValue_return genValue() throws RecognitionException {
        SqlParser.genValue_return retval = new SqlParser.genValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:130:10: ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* )
            int alt15=2;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // Sql.g:130:11: dotValue
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue1376);
                    dotValue();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:131:4: dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )*
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue1381);
                    dotValue();

                    state._fsp--;

                    pushFollow(FOLLOW_compOpt_in_genValue1383);
                    compOpt();

                    state._fsp--;

                    pushFollow(FOLLOW_dotValue_in_genValue1385);
                    dotValue();

                    state._fsp--;

                    // Sql.g:131:30: ( AMP dotValue compOpt dotValue )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==AMP) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // Sql.g:131:31: AMP dotValue compOpt dotValue
                    	    {
                    	    match(input,AMP,FOLLOW_AMP_in_genValue1388); 
                    	    pushFollow(FOLLOW_dotValue_in_genValue1390);
                    	    dotValue();

                    	    state._fsp--;

                    	    pushFollow(FOLLOW_compOpt_in_genValue1392);
                    	    compOpt();

                    	    state._fsp--;

                    	    pushFollow(FOLLOW_dotValue_in_genValue1394);
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
    // Sql.g:132:1: betValue : dotValue spaces and spaces dotValue ;
    public final SqlParser.betValue_return betValue() throws RecognitionException {
        SqlParser.betValue_return retval = new SqlParser.betValue_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:132:10: ( dotValue spaces and spaces dotValue )
            // Sql.g:132:11: dotValue spaces and spaces dotValue
            {
            pushFollow(FOLLOW_dotValue_in_betValue1402);
            dotValue();

            state._fsp--;

            pushFollow(FOLLOW_spaces_in_betValue1404);
            spaces();

            state._fsp--;

            pushFollow(FOLLOW_and_in_betValue1406);
            and();

            state._fsp--;

            pushFollow(FOLLOW_spaces_in_betValue1408);
            spaces();

            state._fsp--;

            pushFollow(FOLLOW_dotValue_in_betValue1410);
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
    // Sql.g:138:1: logicalOp : ( and | or ) ;
    public final SqlParser.logicalOp_return logicalOp() throws RecognitionException {
        SqlParser.logicalOp_return retval = new SqlParser.logicalOp_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:138:11: ( ( and | or ) )
            // Sql.g:138:12: ( and | or )
            {
            // Sql.g:138:12: ( and | or )
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
                    // Sql.g:138:13: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp1422);
                    and();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // Sql.g:138:17: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp1424);
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
    // Sql.g:139:1: entity : ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch' ) ;
    public final void entity() throws RecognitionException {
        try {
            // Sql.g:139:9: ( ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch' ) )
            // Sql.g:139:11: ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch' )
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
    // Sql.g:140:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' | 'checksum' ) ;
    public final void attr() throws RecognitionException {
        try {
            // Sql.g:140:7: ( ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' | 'checksum' ) )
            // Sql.g:140:8: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' | 'checksum' )
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
    // Sql.g:141:1: funct : ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) ;
    public final void funct() throws RecognitionException {
        try {
            // Sql.g:141:8: ( ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) )
            // Sql.g:141:9: ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' )
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
    // Sql.g:142:1: select : ( 'select' | 'SELECT' | 'find' | 'FIND' ) ;
    public final void select() throws RecognitionException {
        try {
            // Sql.g:142:9: ( ( 'select' | 'SELECT' | 'find' | 'FIND' ) )
            // Sql.g:142:10: ( 'select' | 'SELECT' | 'find' | 'FIND' )
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
    // Sql.g:143:1: and : ( 'and' | 'AND' ) ;
    public final void and() throws RecognitionException {
        try {
            // Sql.g:143:6: ( ( 'and' | 'AND' ) )
            // Sql.g:143:7: ( 'and' | 'AND' )
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
    // Sql.g:144:1: order : ( 'order' | 'ORDER' ) ;
    public final void order() throws RecognitionException {
        try {
            // Sql.g:144:8: ( ( 'order' | 'ORDER' ) )
            // Sql.g:144:9: ( 'order' | 'ORDER' )
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
    // Sql.g:145:1: by : ( 'by' | 'BY' ) ;
    public final void by() throws RecognitionException {
        try {
            // Sql.g:145:5: ( ( 'by' | 'BY' ) )
            // Sql.g:145:6: ( 'by' | 'BY' )
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
    // Sql.g:146:1: or : ( 'or' | 'OR' ) ;
    public final void or() throws RecognitionException {
        try {
            // Sql.g:146:5: ( ( 'or' | 'OR' ) )
            // Sql.g:146:6: ( 'or' | 'OR' )
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
    // Sql.g:147:1: in : ( 'in' | 'IN' ) ;
    public final SqlParser.in_return in() throws RecognitionException {
        SqlParser.in_return retval = new SqlParser.in_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:147:5: ( ( 'in' | 'IN' ) )
            // Sql.g:147:6: ( 'in' | 'IN' )
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
    // Sql.g:148:1: not : ( 'not' | 'NOT' ) ;
    public final void not() throws RecognitionException {
        try {
            // Sql.g:148:6: ( ( 'not' | 'NOT' ) )
            // Sql.g:148:7: ( 'not' | 'NOT' )
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
    // Sql.g:149:1: like : ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' ) ;
    public final SqlParser.like_return like() throws RecognitionException {
        SqlParser.like_return retval = new SqlParser.like_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:149:7: ( ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' ) )
            // Sql.g:149:8: ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' )
            {
            // Sql.g:149:8: ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' )
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
                    // Sql.g:149:9: 'like'
                    {
                    match(input,99,FOLLOW_99_in_like1815); 

                    }
                    break;
                case 2 :
                    // Sql.g:149:18: 'LIKE'
                    {
                    match(input,100,FOLLOW_100_in_like1819); 

                    }
                    break;
                case 3 :
                    // Sql.g:149:27: 'not' spaces 'like'
                    {
                    match(input,97,FOLLOW_97_in_like1823); 
                    pushFollow(FOLLOW_spaces_in_like1825);
                    spaces();

                    state._fsp--;

                    match(input,99,FOLLOW_99_in_like1827); 

                    }
                    break;
                case 4 :
                    // Sql.g:149:49: 'NOT' spaces 'LIKE'
                    {
                    match(input,98,FOLLOW_98_in_like1831); 
                    pushFollow(FOLLOW_spaces_in_like1833);
                    spaces();

                    state._fsp--;

                    match(input,100,FOLLOW_100_in_like1835); 

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
    // Sql.g:151:1: count : ( 'count' | 'COUNT' ) ;
    public final void count() throws RecognitionException {
        try {
            // Sql.g:151:8: ( ( 'count' | 'COUNT' ) )
            // Sql.g:151:9: ( 'count' | 'COUNT' )
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
    // Sql.g:152:1: sum : ( 'sum' | 'SUM' ) ;
    public final void sum() throws RecognitionException {
        try {
            // Sql.g:152:6: ( ( 'sum' | 'SUM' ) )
            // Sql.g:152:7: ( 'sum' | 'SUM' )
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
    // Sql.g:153:1: asc : ( 'asc' | 'ASC' ) ;
    public final void asc() throws RecognitionException {
        try {
            // Sql.g:153:6: ( ( 'asc' | 'ASC' ) )
            // Sql.g:153:7: ( 'asc' | 'ASC' )
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
    // Sql.g:154:1: desc : ( 'desc' | 'DESC' ) ;
    public final void desc() throws RecognitionException {
        try {
            // Sql.g:154:7: ( ( 'desc' | 'DESC' ) )
            // Sql.g:154:8: ( 'desc' | 'DESC' )
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
    // Sql.g:155:1: between : ( 'between' | 'BETWEEN' ) ;
    public final SqlParser.between_return between() throws RecognitionException {
        SqlParser.between_return retval = new SqlParser.between_return();
        retval.start = input.LT(1);

        try {
            // Sql.g:155:10: ( ( 'between' | 'BETWEEN' ) )
            // Sql.g:155:11: ( 'between' | 'BETWEEN' )
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
    // Sql.g:156:1: lb : ( '(' ) ;
    public final void lb() throws RecognitionException {
        try {
            // Sql.g:156:5: ( ( '(' ) )
            // Sql.g:156:7: ( '(' )
            {
            // Sql.g:156:7: ( '(' )
            // Sql.g:156:8: '('
            {
            match(input,15,FOLLOW_15_in_lb1911); 

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
    // Sql.g:157:1: rb : ( ')' ) ;
    public final void rb() throws RecognitionException {
        try {
            // Sql.g:157:5: ( ( ')' ) )
            // Sql.g:157:7: ( ')' )
            {
            // Sql.g:157:7: ( ')' )
            // Sql.g:157:8: ')'
            {
            match(input,16,FOLLOW_16_in_rb1921); 

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
        "\3\uffff\1\13\3\uffff\1\13\10\uffff\2\13\1\uffff\1\13\41\uffff\1"+
        "\13\1\uffff\2\13\13\uffff\2\150\10\uffff\2\150\12\uffff\1\13\5\uffff"+
        "\1\150\4\uffff\1\150\4\uffff\1\150\5\uffff\1\150\4\uffff\1\13\4"+
        "\uffff\1\150\1\uffff\5\150\17\uffff\5\150\2\uffff\1\13\7\uffff\1"+
        "\150\1\uffff\1\150\24\uffff\1\150\1\uffff\1\150\6\uffff\6\150\10"+
        "\uffff\2\150\7\uffff\2\150\15\uffff\6\150\10\uffff\1\150\13\uffff"+
        "\2\150\4\uffff\1\150\11\uffff\1\150\4\uffff\5\150\5\uffff\2\150"+
        "\10\uffff\5\150\3\uffff\1\150\1\uffff\5\150\11\uffff\5\150\12\uffff"+
        "\1\150\3\uffff\1\150\1\uffff\1\150\7\uffff\1\150\1\uffff\1\150\10"+
        "\uffff\4\150\3\uffff\3\150\3\uffff\6\150\4\uffff\6\150\4\uffff\4"+
        "\150\10\uffff\1\150\2\uffff\1\150\11\uffff\1\150\13\uffff\4\150"+
        "\2\uffff\3\150\2\uffff\5\150\3\uffff\5\150\4\uffff\2\150\7\uffff"+
        "\4\150\25\uffff\1\150\5\uffff\4\150\2\uffff\2\150\2\uffff\4\150"+
        "\2\uffff\4\150\3\uffff\3\150\2\uffff\4\150\23\uffff\1\150\5\uffff"+
        "\4\150\2\uffff\2\150\2\uffff\4\150\2\uffff\4\150\2\uffff\3\150\2"+
        "\uffff\4\150\30\uffff\4\150\2\uffff\2\150\2\uffff\4\150\2\uffff"+
        "\4\150\2\uffff\2\150\2\uffff\4\150\30\uffff\4\150\2\uffff\2\150"+
        "\2\uffff\4\150\2\uffff\4\150\2\uffff\2\150\2\uffff\4\150\30\uffff"+
        "\3\150\2\uffff\2\150\2\uffff\4\150\2\uffff\4\150\2\uffff\2\150\1"+
        "\uffff\3\150\23\uffff\1\150\2\uffff\2\150\2\uffff\4\150\2\uffff"+
        "\4\150\2\uffff\3\150\17\uffff\2\150\1\uffff\3\150\2\uffff\3\150"+
        "\2\uffff\2\150\10\uffff\2\150\1\uffff\1\150\2\uffff\2\150\4\uffff"+
        "\2\150\1\uffff\1\150";
    static final String DFA1_minS =
        "\1\124\5\4\1\26\3\4\2\uffff\13\4\1\17\6\4\1\6\1\26\4\4\1\26\5\4"+
        "\1\10\13\4\1\26\5\4\1\6\32\4\1\6\3\4\1\26\3\4\1\7\1\4\3\7\1\10\3"+
        "\4\2\uffff\1\4\1\7\3\4\1\7\3\4\1\26\3\4\1\7\11\4\6\7\1\4\1\17\25"+
        "\4\1\7\1\4\1\7\1\4\1\7\1\26\12\4\1\10\4\4\1\7\1\4\1\7\1\4\1\7\3"+
        "\4\1\7\1\4\1\7\10\4\1\10\32\4\1\6\17\4\2\7\1\4\5\7\1\10\1\7\3\4"+
        "\1\7\5\4\1\7\1\4\3\7\1\10\2\4\1\26\2\7\1\4\3\7\6\4\1\10\1\6\1\4"+
        "\2\7\2\4\4\7\11\4\1\7\11\4\6\7\15\4\5\7\1\4\1\7\1\4\1\7\1\4\1\7"+
        "\7\4\1\7\1\4\1\7\1\4\1\7\2\4\5\7\4\4\1\10\1\6\1\10\14\4\1\7\1\4"+
        "\1\7\10\4\1\10\12\4\4\7\1\4\2\7\1\4\1\7\6\4\2\7\1\4\5\7\1\10\5\7"+
        "\4\4\1\10\1\6\12\4\2\7\6\4\1\10\1\6\1\4\1\7\2\4\5\7\12\4\7\7\5\4"+
        "\5\7\1\4\5\7\4\4\1\10\1\6\10\4\2\7\4\4\1\10\1\6\1\10\15\4\7\7\4"+
        "\4\4\7\1\4\5\7\4\4\1\10\1\6\10\4\2\7\4\4\1\10\1\6\15\4\7\7\4\4\11"+
        "\7\4\4\1\10\1\6\10\4\2\7\4\4\1\10\1\6\14\4\7\7\4\4\11\7\4\4\1\10"+
        "\1\6\10\4\2\7\4\4\1\10\1\6\14\4\1\23\6\7\4\4\4\7\1\23\1\7\2\23\1"+
        "\7\3\4\1\10\1\6\10\4\2\7\4\4\1\10\1\6\11\4\1\23\5\7\4\4\4\7\2\23"+
        "\1\4\1\10\1\6\10\4\2\7\4\4\1\10\1\6\4\4\1\23\1\7\1\23\1\7\1\23\4"+
        "\4\1\23\3\7\1\10\6\4\1\23\1\7\3\4\1\10\1\6\2\4\2\23\3\4\1\23\2\7"+
        "\2\4\1\23\1\4\1\10\1\6\3\4\1\23\1\7\1\10\2\4\1\23\1\4";
    static final String DFA1_maxS =
        "\1\127\2\147\1\133\2\17\1\123\1\133\2\147\2\uffff\1\17\1\51\1\17"+
        "\1\51\2\133\1\147\1\133\2\17\2\147\1\155\2\17\1\51\1\20\1\51\1\6"+
        "\1\123\1\17\1\51\1\17\1\51\1\123\1\155\1\17\3\23\1\10\3\23\1\143"+
        "\1\144\1\17\1\51\1\17\1\51\1\20\1\133\1\113\2\133\1\51\1\20\1\51"+
        "\1\6\2\155\1\17\4\23\2\137\5\23\2\131\1\23\2\137\1\143\1\23\1\144"+
        "\1\23\1\51\1\20\1\51\1\6\2\20\1\133\1\113\1\23\2\20\1\23\1\137\3"+
        "\23\1\10\1\137\2\147\2\uffff\1\137\1\23\1\131\1\23\1\131\1\23\1"+
        "\137\1\20\1\155\1\113\1\20\1\133\1\20\1\23\1\20\1\23\1\137\1\20"+
        "\5\137\6\23\2\147\1\155\2\17\3\131\1\23\5\137\2\20\1\133\3\20\1"+
        "\23\2\20\1\23\1\137\1\23\1\137\1\23\1\123\1\155\3\23\1\143\1\144"+
        "\1\17\3\23\1\10\1\17\1\51\1\17\1\51\1\23\1\131\1\23\1\137\1\23\1"+
        "\137\1\20\1\155\1\23\1\20\1\23\1\20\6\137\2\13\2\155\1\23\2\131"+
        "\1\23\2\137\1\143\1\23\1\144\1\23\1\17\2\23\2\137\6\23\1\51\1\20"+
        "\1\51\1\6\3\131\6\137\6\20\2\23\1\137\1\23\1\7\3\23\1\10\1\23\1"+
        "\131\1\23\1\131\1\23\2\137\1\23\2\20\1\23\1\137\3\23\1\10\1\20\1"+
        "\155\1\113\2\23\1\137\3\23\1\20\5\137\3\13\2\23\2\137\4\23\3\131"+
        "\1\23\5\137\1\23\1\20\1\23\1\137\1\20\5\137\6\23\1\20\2\131\5\137"+
        "\5\20\3\23\1\7\1\23\1\137\1\23\1\131\1\23\1\137\1\23\1\137\3\20"+
        "\1\23\2\20\1\23\1\137\1\23\1\137\1\23\1\20\1\155\5\23\4\137\3\13"+
        "\3\137\3\131\6\137\1\23\1\20\1\23\1\20\6\137\2\13\2\131\4\137\4"+
        "\20\4\23\1\137\2\23\1\137\1\23\6\20\2\23\1\137\1\23\1\7\3\23\1\10"+
        "\5\23\4\137\2\13\3\137\2\131\5\137\2\23\1\20\5\137\3\13\1\23\2\137"+
        "\5\23\2\131\4\137\4\20\7\23\5\20\3\23\1\7\1\23\1\137\5\23\4\137"+
        "\2\13\2\137\2\131\4\137\2\23\4\137\3\13\3\137\2\131\4\137\4\20\7"+
        "\23\4\20\4\23\1\137\5\23\4\137\2\13\2\137\2\131\4\137\2\23\4\137"+
        "\2\13\3\137\2\131\4\137\4\20\7\23\4\20\11\23\4\137\2\13\2\137\2"+
        "\131\4\137\2\23\4\137\2\13\2\137\2\131\4\137\4\20\7\23\4\20\11\23"+
        "\4\137\2\13\2\137\2\131\4\137\2\23\4\137\2\13\2\137\2\131\4\137"+
        "\4\20\7\23\4\20\11\23\3\137\2\13\2\137\2\131\4\137\2\23\4\137\2"+
        "\13\2\137\1\131\3\137\3\20\6\23\4\20\6\23\1\137\2\13\2\137\2\131"+
        "\4\137\2\23\4\137\2\13\3\137\1\20\5\23\4\20\4\23\1\13\2\137\1\131"+
        "\3\137\2\23\3\137\2\13\2\137\2\23\3\20\3\23\2\137\1\23\1\137\2\13"+
        "\2\137\1\20\2\23\1\13\2\137\1\23\1\137";
    static final String DFA1_acceptS =
        "\12\uffff\1\3\1\2\134\uffff\1\1\1\4\u02de\uffff";
    static final String DFA1_specialS =
        "\u0348\uffff}>";
    static final String[] DFA1_transitionS = {
            "\4\1",
            "\1\2\17\uffff\26\3\21\uffff\1\4\51\uffff\1\4\2\5",
            "\1\2\17\uffff\26\3\21\uffff\1\4\51\uffff\1\4\2\5",
            "\1\7\1\10\1\6\12\uffff\2\11\107\uffff\2\12",
            "\1\14\12\uffff\1\15",
            "\1\16\12\uffff\1\17",
            "\3\21\21\uffff\42\21\10\20",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\12",
            "\1\22\17\uffff\26\23\21\uffff\1\24\51\uffff\1\24\2\25",
            "\1\26\12\uffff\1\27\4\uffff\26\30\21\uffff\1\31\51\uffff\1"+
            "\31\2\32",
            "",
            "",
            "\1\14\12\uffff\1\15",
            "\1\33\17\uffff\26\34",
            "\1\16\12\uffff\1\17",
            "\1\35\17\uffff\26\36",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\12",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\12",
            "\1\22\17\uffff\26\23\21\uffff\1\24\51\uffff\1\24\2\25",
            "\1\7\1\10\1\37\12\uffff\2\11\107\uffff\2\12",
            "\1\40\12\uffff\1\41",
            "\1\42\12\uffff\1\43",
            "\1\26\12\uffff\1\27\4\uffff\26\30\21\uffff\1\31\51\uffff\1"+
            "\31\2\32",
            "\1\27\4\uffff\26\30\21\uffff\1\31\51\uffff\1\31\2\32",
            "\1\45\1\uffff\1\44\1\uffff\1\47\1\50\1\51\1\52\7\uffff\1\46"+
            "\114\uffff\1\46\1\56\1\57\1\54\1\55\7\uffff\2\53",
            "\1\60\12\uffff\1\61",
            "\1\62\12\uffff\1\63",
            "\1\33\17\uffff\26\34",
            "\1\64\13\uffff\1\65",
            "\1\35\17\uffff\26\36",
            "\1\66",
            "\3\70\21\uffff\42\70\10\67",
            "\1\40\12\uffff\1\41",
            "\1\71\17\uffff\26\72",
            "\1\42\12\uffff\1\43",
            "\1\73\17\uffff\26\74",
            "\3\75\21\uffff\42\75\10\76",
            "\1\45\3\uffff\1\47\1\50\1\51\1\52\7\uffff\1\46\114\uffff\1"+
            "\46\1\56\1\57\1\54\1\55\7\uffff\2\53",
            "\1\77\12\uffff\1\100",
            "\1\103\2\uffff\1\104\1\uffff\1\101\1\102\10\uffff\1\105",
            "\1\103\2\uffff\1\104\1\106\1\uffff\1\107\10\uffff\1\105",
            "\1\103\2\uffff\1\104\1\110\12\uffff\1\105",
            "\1\111",
            "\1\112\2\uffff\1\113\13\uffff\1\114",
            "\1\115\2\uffff\1\116\13\uffff\1\117",
            "\1\115\2\uffff\1\116\13\uffff\1\117",
            "\1\120\136\uffff\1\121",
            "\1\122\137\uffff\1\123",
            "\1\60\12\uffff\1\61",
            "\1\124\17\uffff\26\125",
            "\1\62\12\uffff\1\63",
            "\1\126\17\uffff\26\127",
            "\1\64\13\uffff\1\65",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\12",
            "\3\130\21\uffff\42\130",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\12",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\12",
            "\1\71\17\uffff\26\72",
            "\1\131\13\uffff\1\132",
            "\1\73\17\uffff\26\74",
            "\1\133",
            "\1\45\3\uffff\1\47\1\50\1\51\1\52\7\uffff\1\46\114\uffff\1"+
            "\46\1\56\1\57\1\54\1\55\7\uffff\2\53",
            "\1\45\3\uffff\1\47\1\50\1\51\1\52\7\uffff\1\46\114\uffff\1"+
            "\46\1\56\1\57\1\54\1\55\7\uffff\2\53",
            "\1\77\12\uffff\1\100",
            "\1\134\2\uffff\1\135\13\uffff\1\136",
            "\1\103\2\uffff\1\104\13\uffff\1\105",
            "\1\103\2\uffff\1\104\13\uffff\1\105",
            "\1\103\2\uffff\1\104\13\uffff\1\105",
            "\1\140\1\uffff\1\137\1\uffff\1\141\1\142\1\143\1\144\4\uffff"+
            "\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\3\uffff\1\141\1\142\1\143\1\144\4\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\103\2\uffff\1\104\13\uffff\1\105",
            "\1\103\2\uffff\1\104\13\uffff\1\105",
            "\1\103\2\uffff\1\104\13\uffff\1\105",
            "\1\103\2\uffff\1\104\13\uffff\1\105",
            "\1\112\2\uffff\1\113\13\uffff\1\114",
            "\1\154\1\uffff\1\153\121\uffff\2\155",
            "\1\156\123\uffff\2\155",
            "\1\115\2\uffff\1\116\13\uffff\1\117",
            "\1\160\1\uffff\1\157\11\uffff\1\145\107\uffff\2\146\2\151\2"+
            "\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
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
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\12",
            "\3\166\21\uffff\42\166",
            "\1\134\2\uffff\1\135\13\uffff\1\136",
            "\1\170\1\171\1\167\11\uffff\1\172",
            "\1\173\1\171\12\uffff\1\172",
            "\1\175\13\uffff\1\174",
            "\1\152\2\uffff\1\176\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\177\1\uffff\1\u0082\1\u0081\10\uffff\1\u0080",
            "\1\177\1\u0083\1\uffff\1\u0084\10\uffff\1\u0080",
            "\1\177\1\u0085\12\uffff\1\u0080",
            "\1\u0086",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0087\12\uffff\1\u0088\4\uffff\26\u0089\21\uffff\1\u008a"+
            "\51\uffff\1\u008a\2\u008b",
            "\1\u0087\12\uffff\1\u0088\4\uffff\26\u0089\21\uffff\1\u008a"+
            "\51\uffff\1\u008a\2\u008b",
            "",
            "",
            "\1\152\123\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u008d\13\uffff\1\u008c",
            "\1\156\2\uffff\1\u008e\120\uffff\2\155",
            "\1\u008f\2\uffff\1\u0090\13\uffff\1\u0091",
            "\1\156\123\uffff\2\155",
            "\1\u0093\13\uffff\1\u0092",
            "\1\152\2\uffff\1\u0094\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\161\13\uffff\1\162",
            "\1\45\3\uffff\1\47\1\50\1\51\1\52\7\uffff\1\46\114\uffff\1"+
            "\46\1\56\1\57\1\54\1\55\7\uffff\2\53",
            "\3\u0095\21\uffff\42\u0095",
            "\1\164\13\uffff\1\165",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\12",
            "\1\u0096\13\uffff\1\u0097",
            "\1\u0099\13\uffff\1\u0098",
            "\1\173\1\171\1\uffff\1\u009a\10\uffff\1\172",
            "\1\u009b\2\uffff\1\u009c\13\uffff\1\u009d",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\173\1\171\12\uffff\1\172",
            "\1\152\3\uffff\1\141\1\142\1\143\1\144\4\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u009e\1\uffff\1\141\1\142\1\143\1\144\4\uffff"+
            "\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u009f\3\uffff\1\141\1\142\1\143\1\144\4\uffff\1\145\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u00a1\1\uffff\1\u00a0\5\uffff\1\u00a2\3\uffff\1\145\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\177\13\uffff\1\u0080",
            "\1\177\13\uffff\1\u0080",
            "\1\177\13\uffff\1\u0080",
            "\1\177\13\uffff\1\u0080",
            "\1\177\13\uffff\1\u0080",
            "\1\177\13\uffff\1\u0080",
            "\1\u0087\12\uffff\1\u0088\4\uffff\26\u0089\21\uffff\1\u008a"+
            "\51\uffff\1\u008a\2\u008b",
            "\1\u0088\4\uffff\26\u0089\21\uffff\1\u008a\51\uffff\1\u008a"+
            "\2\u008b",
            "\1\u00a4\1\uffff\1\u00a3\1\uffff\1\u00ab\1\u00ac\1\u00ad\1"+
            "\u00ae\7\uffff\1\u00aa\114\uffff\1\u00aa\1\u00a8\1\u00a9\1\u00a6"+
            "\1\u00a7\7\uffff\2\u00a5",
            "\1\u00af\12\uffff\1\u00b0",
            "\1\u00b1\12\uffff\1\u00b2",
            "\1\156\123\uffff\2\155",
            "\1\156\1\uffff\1\u00b3\121\uffff\2\155",
            "\1\u00b4\123\uffff\2\155",
            "\1\u008f\2\uffff\1\u0090\13\uffff\1\u0091",
            "\1\u00b6\1\uffff\1\u00b5\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u00b7\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u00b8\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u00b9\13\uffff\1\u00ba",
            "\1\u0096\13\uffff\1\u0097",
            "\1\7\1\10\13\uffff\2\11\107\uffff\2\12",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u00bb\11\uffff\1\172",
            "\1\u00bc\1\171\12\uffff\1\172",
            "\1\u009b\2\uffff\1\u009c\13\uffff\1\u009d",
            "\1\u00be\1\171\1\u00bd\11\uffff\1\172",
            "\1\173\1\171\12\uffff\1\172",
            "\1\u00c0\13\uffff\1\u00bf",
            "\1\152\2\uffff\1\u00c1\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u00c3\13\uffff\1\u00c2",
            "\1\152\2\uffff\1\u00c4\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u00c5\13\uffff\1\u00c6",
            "\3\u00c7\21\uffff\42\u00c7\10\u00c8",
            "\1\u00a4\3\uffff\1\u00ab\1\u00ac\1\u00ad\1\u00ae\7\uffff\1"+
            "\u00aa\114\uffff\1\u00aa\1\u00a8\1\u00a9\1\u00a6\1\u00a7\7\uffff"+
            "\2\u00a5",
            "\1\u00c9\2\uffff\1\u00ca\13\uffff\1\u00cb",
            "\1\u00cc\2\uffff\1\u00cd\13\uffff\1\u00ce",
            "\1\u00cc\2\uffff\1\u00cd\13\uffff\1\u00ce",
            "\1\u00cf\136\uffff\1\u00d0",
            "\1\u00d1\137\uffff\1\u00d2",
            "\1\u00d3\12\uffff\1\u00d4",
            "\1\u00d5\2\uffff\1\u00d6\1\uffff\1\u00d8\1\u00d9\10\uffff\1"+
            "\u00d7",
            "\1\u00d5\2\uffff\1\u00d6\1\u00db\1\uffff\1\u00da\10\uffff\1"+
            "\u00d7",
            "\1\u00d5\2\uffff\1\u00d6\1\u00dc\12\uffff\1\u00d7",
            "\1\u00dd",
            "\1\u00af\12\uffff\1\u00b0",
            "\1\u00de\17\uffff\26\u00df",
            "\1\u00b1\12\uffff\1\u00b2",
            "\1\u00e0\17\uffff\26\u00e1",
            "\1\u00e3\13\uffff\1\u00e2",
            "\1\156\2\uffff\1\u00e4\120\uffff\2\155",
            "\1\u00e6\13\uffff\1\u00e5",
            "\1\152\2\uffff\1\u00e7\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u00e9\13\uffff\1\u00e8",
            "\1\152\2\uffff\1\u00ea\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u00b9\13\uffff\1\u00ba",
            "\1\45\3\uffff\1\47\1\50\1\51\1\52\7\uffff\1\46\114\uffff\1"+
            "\46\1\56\1\57\1\54\1\55\7\uffff\2\53",
            "\1\u00ec\13\uffff\1\u00eb",
            "\1\173\1\171\1\uffff\1\u00ed\10\uffff\1\172",
            "\1\u00ef\13\uffff\1\u00ee",
            "\1\173\1\171\1\uffff\1\u00f0\10\uffff\1\172",
            "\1\152\3\uffff\1\141\1\142\1\143\1\144\4\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u00f1\1\uffff\1\141\1\142\1\143\1\144\4\uffff"+
            "\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\3\uffff\1\141\1\142\1\143\1\144\4\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u00f2\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00f3\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u00f5\1\uffff\1\u00f4\1\uffff\1\u00f6\1\u00f7\1\u00f8\1"+
            "\u00f9",
            "\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\u00a4\3\uffff\1\u00ab\1\u00ac\1\u00ad\1\u00ae\7\uffff\1"+
            "\u00aa\114\uffff\1\u00aa\1\u00a8\1\u00a9\1\u00a6\1\u00a7\7\uffff"+
            "\2\u00a5",
            "\1\u00a4\3\uffff\1\u00ab\1\u00ac\1\u00ad\1\u00ae\7\uffff\1"+
            "\u00aa\114\uffff\1\u00aa\1\u00a8\1\u00a9\1\u00a6\1\u00a7\7\uffff"+
            "\2\u00a5",
            "\1\u00c9\2\uffff\1\u00ca\13\uffff\1\u00cb",
            "\1\u00fb\1\uffff\1\u00fa\121\uffff\2\u00fc",
            "\1\u00fd\123\uffff\2\u00fc",
            "\1\u00cc\2\uffff\1\u00cd\13\uffff\1\u00ce",
            "\1\u00ff\1\uffff\1\u00fe\11\uffff\1\u0100\107\uffff\2\146\2"+
            "\151\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u00cf\136\uffff\1\u00d0",
            "\1\u00cc\2\uffff\1\u00cd\13\uffff\1\u00ce",
            "\1\u00d1\137\uffff\1\u00d2",
            "\1\u00cc\2\uffff\1\u00cd\13\uffff\1\u00ce",
            "\1\u00d3\12\uffff\1\u00d4",
            "\1\u0101\2\uffff\1\u0102\13\uffff\1\u0103",
            "\1\u00d5\2\uffff\1\u00d6\13\uffff\1\u00d7",
            "\1\u0105\1\uffff\1\u0104\1\uffff\1\u0106\1\u0107\1\u0108\1"+
            "\u0109\4\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109\4\uffff\1\u0100"+
            "\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u00d5\2\uffff\1\u00d6\13\uffff\1\u00d7",
            "\1\u00d5\2\uffff\1\u00d6\13\uffff\1\u00d7",
            "\1\u00d5\2\uffff\1\u00d6\13\uffff\1\u00d7",
            "\1\u00d5\2\uffff\1\u00d6\13\uffff\1\u00d7",
            "\1\u00d5\2\uffff\1\u00d6\13\uffff\1\u00d7",
            "\1\u00d5\2\uffff\1\u00d6\13\uffff\1\u00d7",
            "\1\u00de\17\uffff\26\u00df",
            "\1\u010a\13\uffff\1\u010b",
            "\1\u00e0\17\uffff\26\u00e1",
            "\1\u010c",
            "\1\156\123\uffff\2\155",
            "\1\156\1\uffff\1\u010d\121\uffff\2\155",
            "\1\156\123\uffff\2\155",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u010e\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u010f\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0110\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u0111\11\uffff\1\172",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u0112\11\uffff\1\172",
            "\1\u0113\1\171\12\uffff\1\172",
            "\1\u0115\13\uffff\1\u0114",
            "\1\u0117\13\uffff\1\u0116",
            "\1\152\2\uffff\1\u0118\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u011a\13\uffff\1\u0119",
            "\1\u011b",
            "\1\u011e\1\uffff\1\u011c\1\u011d\10\uffff\1\u011f",
            "\1\u011e\1\u0121\1\uffff\1\u0120\10\uffff\1\u011f",
            "\1\u011e\1\u0122\12\uffff\1\u011f",
            "\1\u0123",
            "\1\u0125\13\uffff\1\u0124",
            "\1\u00fd\2\uffff\1\u0126\120\uffff\2\u00fc",
            "\1\u0127\2\uffff\1\u0128\13\uffff\1\u0129",
            "\1\u00fd\123\uffff\2\u00fc",
            "\1\u012b\13\uffff\1\u012a",
            "\1\152\2\uffff\1\u012c\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0101\2\uffff\1\u0102\13\uffff\1\u0103",
            "\1\u012e\1\u012f\1\u012d\11\uffff\1\u0130",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0133\13\uffff\1\u0132",
            "\1\152\2\uffff\1\u0134\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0135\1\uffff\1\u0138\1\u0137\10\uffff\1\u0136",
            "\1\u0135\1\u013a\1\uffff\1\u0139\10\uffff\1\u0136",
            "\1\u0135\1\u013b\12\uffff\1\u0136",
            "\1\u013c",
            "\1\u010a\13\uffff\1\u010b",
            "\1\u00a4\3\uffff\1\u00ab\1\u00ac\1\u00ad\1\u00ae\7\uffff\1"+
            "\u00aa\114\uffff\1\u00aa\1\u00a8\1\u00a9\1\u00a6\1\u00a7\7\uffff"+
            "\2\u00a5",
            "\3\u013d\21\uffff\42\u013d",
            "\1\u013f\13\uffff\1\u013e",
            "\1\u0141\13\uffff\1\u0140",
            "\1\152\2\uffff\1\u0142\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0144\13\uffff\1\u0143",
            "\1\u0146\13\uffff\1\u0145",
            "\1\u0148\13\uffff\1\u0147",
            "\1\173\1\171\1\uffff\1\u0149\10\uffff\1\172",
            "\1\152\3\uffff\1\141\1\142\1\143\1\144\4\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u014a\1\uffff\1\141\1\142\1\143\1\144\4\uffff"+
            "\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u014b\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\u014c\1\uffff\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\u014d\3\uffff\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\u011e\13\uffff\1\u011f",
            "\1\u011e\13\uffff\1\u011f",
            "\1\u014f\1\uffff\1\u014e\5\uffff\1\u00a2\3\uffff\1\145\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u011e\13\uffff\1\u011f",
            "\1\u011e\13\uffff\1\u011f",
            "\1\u011e\13\uffff\1\u011f",
            "\1\u011e\13\uffff\1\u011f",
            "\1\u00fd\123\uffff\2\u00fc",
            "\1\u00fd\1\uffff\1\u0150\121\uffff\2\u00fc",
            "\1\u0151\123\uffff\2\u00fc",
            "\1\u0127\2\uffff\1\u0128\13\uffff\1\u0129",
            "\1\u0153\1\uffff\1\u0152\11\uffff\1\u0100\107\uffff\2\146\2"+
            "\151\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0154\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u0155\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0157\13\uffff\1\u0156",
            "\1\u0131\1\u012f\1\uffff\1\u0158\10\uffff\1\u0130",
            "\1\u0159\2\uffff\1\u015a\13\uffff\1\u015b",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\152\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109\4\uffff\1\u0100"+
            "\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u015c\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109"+
            "\4\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u015d\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109\4\uffff\1"+
            "\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u015f\1\uffff\1\u015e\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u0135\13\uffff\1\u0136",
            "\1\u0135\13\uffff\1\u0136",
            "\1\u0135\13\uffff\1\u0136",
            "\1\u0135\13\uffff\1\u0136",
            "\1\u0135\13\uffff\1\u0136",
            "\1\u0135\13\uffff\1\u0136",
            "\1\u0161\13\uffff\1\u0162",
            "\1\156\123\uffff\2\155",
            "\1\156\1\uffff\1\u0163\121\uffff\2\155",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0164\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0165\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u0166\11\uffff\1\172",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u0167\11\uffff\1\172",
            "\1\173\1\171\12\uffff\1\172",
            "\1\u0169\13\uffff\1\u0168",
            "\1\u016b\13\uffff\1\u016a",
            "\1\u016d\13\uffff\1\u016c",
            "\1\u016e",
            "\1\u0170\13\uffff\1\u016f",
            "\1\152\2\uffff\1\u0171\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0173\13\uffff\1\u0172",
            "\1\u00fd\2\uffff\1\u0174\120\uffff\2\u00fc",
            "\1\u0176\13\uffff\1\u0175",
            "\1\152\2\uffff\1\u0177\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0179\13\uffff\1\u0178",
            "\1\152\2\uffff\1\u017a\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u017b\11\uffff\1\u0130",
            "\1\u017c\1\u012f\12\uffff\1\u0130",
            "\1\u0159\2\uffff\1\u015a\13\uffff\1\u015b",
            "\1\u017e\1\u012f\1\u017d\11\uffff\1\u0130",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0180\13\uffff\1\u017f",
            "\1\152\2\uffff\1\u0181\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0183\13\uffff\1\u0182",
            "\1\152\2\uffff\1\u0184\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0185\13\uffff\1\u0186",
            "\1\u0161\13\uffff\1\u0162",
            "\1\u00a4\3\uffff\1\u00ab\1\u00ac\1\u00ad\1\u00ae\7\uffff\1"+
            "\u00aa\114\uffff\1\u00aa\1\u00a8\1\u00a9\1\u00a6\1\u00a7\7\uffff"+
            "\2\u00a5",
            "\1\u0188\13\uffff\1\u0187",
            "\1\u018a\13\uffff\1\u0189",
            "\1\u018c\13\uffff\1\u018b",
            "\1\u018e\13\uffff\1\u018d",
            "\1\u0190\13\uffff\1\u018f",
            "\1\152\3\uffff\1\141\1\142\1\143\1\144\4\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0191\1\uffff\1\141\1\142\1\143\1\144\4\uffff"+
            "\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0192\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\u0193\1\uffff\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0194\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u0195\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u00fd\123\uffff\2\u00fc",
            "\1\u00fd\1\uffff\1\u0196\121\uffff\2\u00fc",
            "\1\u00fd\123\uffff\2\u00fc",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0197\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u0198\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0199\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u019b\13\uffff\1\u019a",
            "\1\u0131\1\u012f\1\uffff\1\u019c\10\uffff\1\u0130",
            "\1\u019e\13\uffff\1\u019d",
            "\1\u0131\1\u012f\1\uffff\1\u019f\10\uffff\1\u0130",
            "\1\152\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109\4\uffff\1\u0100"+
            "\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u01a0\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109"+
            "\4\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109\4\uffff\1\u0100"+
            "\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u01a1\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u01a2\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2"+
            "\151\2\uffff\2\147",
            "\1\u01a4\1\uffff\1\u01a3\1\uffff\1\u01a5\1\u01a6\1\u01a7\1"+
            "\u01a8",
            "\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\156\123\uffff\2\155",
            "\1\156\1\uffff\1\u01a9\121\uffff\2\155",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u01aa\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u01ab\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u01ac\11\uffff\1\172",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u01ad\11\uffff\1\172",
            "\1\u01af\13\uffff\1\u01ae",
            "\1\u01b1\13\uffff\1\u01b0",
            "\1\u01b3\13\uffff\1\u01b2",
            "\1\u01b5\13\uffff\1\u01b4",
            "\1\152\2\uffff\1\u01b6\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u01b8\13\uffff\1\u01b7",
            "\1\u01ba\13\uffff\1\u01b9",
            "\1\152\2\uffff\1\u01bb\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u01bd\13\uffff\1\u01bc",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u01be\11\uffff\1\u0130",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u01bf\11\uffff\1\u0130",
            "\1\u01c0\1\u012f\12\uffff\1\u0130",
            "\1\u01c2\13\uffff\1\u01c1",
            "\1\u01c4\13\uffff\1\u01c3",
            "\1\152\2\uffff\1\u01c5\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u01c7\13\uffff\1\u01c6",
            "\1\u01c8",
            "\1\u01ca\1\uffff\1\u01cc\1\u01c9\10\uffff\1\u01cb",
            "\1\u01ca\1\u01ce\1\uffff\1\u01cd\10\uffff\1\u01cb",
            "\1\u01ca\1\u01cf\12\uffff\1\u01cb",
            "\1\u01d0",
            "\1\u01d2\13\uffff\1\u01d1",
            "\1\u01d4\13\uffff\1\u01d3",
            "\1\u01d6\13\uffff\1\u01d5",
            "\1\u01d8\13\uffff\1\u01d7",
            "\1\u01da\13\uffff\1\u01d9",
            "\1\152\3\uffff\1\141\1\142\1\143\1\144\4\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u01db\1\uffff\1\141\1\142\1\143\1\144\4\uffff"+
            "\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u01dc\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\u01dd\1\uffff\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u01de\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u00fd\123\uffff\2\u00fc",
            "\1\u00fd\1\uffff\1\u01df\121\uffff\2\u00fc",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u01e0\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u01e1\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u01e3\13\uffff\1\u01e2",
            "\1\u01e5\13\uffff\1\u01e4",
            "\1\u0131\1\u012f\1\uffff\1\u01e6\10\uffff\1\u0130",
            "\1\152\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109\4\uffff\1\u0100"+
            "\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u01e7\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109"+
            "\4\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u01e8\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\u01e9\1\uffff\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\u01ea\3\uffff\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\u01ca\13\uffff\1\u01cb",
            "\1\u01ec\1\uffff\1\u01eb\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u01ca\13\uffff\1\u01cb",
            "\1\u01ca\13\uffff\1\u01cb",
            "\1\u01ca\13\uffff\1\u01cb",
            "\1\u01ca\13\uffff\1\u01cb",
            "\1\u01ca\13\uffff\1\u01cb",
            "\1\156\123\uffff\2\155",
            "\1\156\1\uffff\1\u01ed\121\uffff\2\155",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u01ee\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u01ef\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u01f0\11\uffff\1\172",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u01f1\11\uffff\1\172",
            "\1\u01f3\13\uffff\1\u01f2",
            "\1\u01f5\13\uffff\1\u01f4",
            "\1\u01f7\13\uffff\1\u01f6",
            "\1\u01f9\13\uffff\1\u01f8",
            "\1\u01fb\13\uffff\1\u01fa",
            "\1\u01fd\13\uffff\1\u01fc",
            "\1\u01ff\13\uffff\1\u01fe",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u0200\11\uffff\1\u0130",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u0201\11\uffff\1\u0130",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0203\13\uffff\1\u0202",
            "\1\u0205\13\uffff\1\u0204",
            "\1\u0207\13\uffff\1\u0206",
            "\1\u0208",
            "\1\u020a\13\uffff\1\u0209",
            "\1\152\2\uffff\1\u020b\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u020d\13\uffff\1\u020c",
            "\1\u020f\13\uffff\1\u020e",
            "\1\u0211\13\uffff\1\u0210",
            "\1\u0213\13\uffff\1\u0212",
            "\1\u0215\13\uffff\1\u0214",
            "\1\152\3\uffff\1\141\1\142\1\143\1\144\4\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0216\1\uffff\1\141\1\142\1\143\1\144\4\uffff"+
            "\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0217\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\u0218\1\uffff\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0219\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00fd\123\uffff\2\u00fc",
            "\1\u00fd\1\uffff\1\u021a\121\uffff\2\u00fc",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u021b\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u021c\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u021e\13\uffff\1\u021d",
            "\1\u0220\13\uffff\1\u021f",
            "\1\152\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109\4\uffff\1\u0100"+
            "\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0221\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109"+
            "\4\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0222\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\u0223\1\uffff\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0224\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0225\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2"+
            "\151\2\uffff\2\147",
            "\1\156\123\uffff\2\155",
            "\1\156\1\uffff\1\u0226\121\uffff\2\155",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0227\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0228\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u0229\11\uffff\1\172",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u022a\11\uffff\1\172",
            "\1\u022c\13\uffff\1\u022b",
            "\1\u022e\13\uffff\1\u022d",
            "\1\u0230\13\uffff\1\u022f",
            "\1\u0232\13\uffff\1\u0231",
            "\1\u0234\13\uffff\1\u0233",
            "\1\u0236\13\uffff\1\u0235",
            "\1\u0238\13\uffff\1\u0237",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u0239\11\uffff\1\u0130",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u023a\11\uffff\1\u0130",
            "\1\u023c\13\uffff\1\u023b",
            "\1\u023e\13\uffff\1\u023d",
            "\1\u0240\13\uffff\1\u023f",
            "\1\u0242\13\uffff\1\u0241",
            "\1\152\2\uffff\1\u0243\120\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0245\13\uffff\1\u0244",
            "\1\u0247\13\uffff\1\u0246",
            "\1\u0249\13\uffff\1\u0248",
            "\1\u024b\13\uffff\1\u024a",
            "\1\u024d\13\uffff\1\u024c",
            "\1\152\3\uffff\1\141\1\142\1\143\1\144\4\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u024e\1\uffff\1\141\1\142\1\143\1\144\4\uffff"+
            "\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u024f\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\u0250\1\uffff\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0251\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00fd\123\uffff\2\u00fc",
            "\1\u00fd\1\uffff\1\u0252\121\uffff\2\u00fc",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0253\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0254\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u0256\13\uffff\1\u0255",
            "\1\u0258\13\uffff\1\u0257",
            "\1\152\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109\4\uffff\1\u0100"+
            "\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0259\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109"+
            "\4\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u025a\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\u025b\1\uffff\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u025c\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\156\123\uffff\2\155",
            "\1\156\1\uffff\1\u025d\121\uffff\2\155",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u025e\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u025f\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u0260\11\uffff\1\172",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u0261\11\uffff\1\172",
            "\1\u0263\13\uffff\1\u0262",
            "\1\u0265\13\uffff\1\u0264",
            "\1\u0267\13\uffff\1\u0266",
            "\1\u0269\13\uffff\1\u0268",
            "\1\u026b\13\uffff\1\u026a",
            "\1\u026d\13\uffff\1\u026c",
            "\1\u026f\13\uffff\1\u026e",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u0270\11\uffff\1\u0130",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u0271\11\uffff\1\u0130",
            "\1\u0273\13\uffff\1\u0272",
            "\1\u0275\13\uffff\1\u0274",
            "\1\u0277\13\uffff\1\u0276",
            "\1\u0279\13\uffff\1\u0278",
            "\1\u027b\13\uffff\1\u027a",
            "\1\u027d\13\uffff\1\u027c",
            "\1\u027f\13\uffff\1\u027e",
            "\1\u0281\13\uffff\1\u0280",
            "\1\u0283\13\uffff\1\u0282",
            "\1\152\3\uffff\1\141\1\142\1\143\1\144\4\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0284\1\uffff\1\141\1\142\1\143\1\144\4\uffff"+
            "\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0285\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\u0286\1\uffff\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0287\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00fd\123\uffff\2\u00fc",
            "\1\u00fd\1\uffff\1\u0288\121\uffff\2\u00fc",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0289\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u028a\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u028c\13\uffff\1\u028b",
            "\1\u028e\13\uffff\1\u028d",
            "\1\152\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109\4\uffff\1\u0100"+
            "\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u028f\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109"+
            "\4\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0290\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\u0291\1\uffff\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0292\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\156\123\uffff\2\155",
            "\1\156\1\uffff\1\u0293\121\uffff\2\155",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0294\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0295\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u0296\11\uffff\1\172",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u0297\11\uffff\1\172",
            "\1\u0299\13\uffff\1\u0298",
            "\1\u029b\13\uffff\1\u029a",
            "\1\u029d\13\uffff\1\u029c",
            "\1\u029f\13\uffff\1\u029e",
            "\1\u02a1\13\uffff\1\u02a0",
            "\1\u02a3\13\uffff\1\u02a2",
            "\1\u02a5\13\uffff\1\u02a4",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u02a6\11\uffff\1\u0130",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u02a7\11\uffff\1\u0130",
            "\1\u02a9\13\uffff\1\u02a8",
            "\1\u02ab\13\uffff\1\u02aa",
            "\1\u02ad\13\uffff\1\u02ac",
            "\1\u02af\13\uffff\1\u02ae",
            "\1\u02b1\13\uffff\1\u02b0",
            "\1\u02b3\13\uffff\1\u02b2",
            "\1\u02b5\13\uffff\1\u02b4",
            "\1\u02b7\13\uffff\1\u02b6",
            "\1\u02b9\13\uffff\1\u02b8",
            "\1\152\3\uffff\1\141\1\142\1\143\1\144\4\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u02ba\1\uffff\1\141\1\142\1\143\1\144\4\uffff"+
            "\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u02bb\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\u02bc\1\uffff\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u02bd\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00fd\123\uffff\2\u00fc",
            "\1\u00fd\1\uffff\1\u02be\121\uffff\2\u00fc",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u02bf\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u02c0\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u02c2\13\uffff\1\u02c1",
            "\1\u02c4\13\uffff\1\u02c3",
            "\1\152\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109\4\uffff\1\u0100"+
            "\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u02c5\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109"+
            "\4\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u02c6\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\u02c7\1\uffff\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u02c8\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\156\123\uffff\2\155",
            "\1\156\1\uffff\1\u02c9\121\uffff\2\155",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u02ca\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u02cb\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u02cc\11\uffff\1\172",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u02cd\11\uffff\1\172",
            "\1\u02ce",
            "\1\u02d0\13\uffff\1\u02cf",
            "\1\u02d2\13\uffff\1\u02d1",
            "\1\u02d4\13\uffff\1\u02d3",
            "\1\u02d6\13\uffff\1\u02d5",
            "\1\u02d8\13\uffff\1\u02d7",
            "\1\u02da\13\uffff\1\u02d9",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u02db\11\uffff\1\u0130",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u02dc\11\uffff\1\u0130",
            "\1\u02de\13\uffff\1\u02dd",
            "\1\u02e0\13\uffff\1\u02df",
            "\1\u02e2\13\uffff\1\u02e1",
            "\1\u02e4\13\uffff\1\u02e3",
            "\1\u02e5",
            "\1\u02e7\13\uffff\1\u02e6",
            "\1\u02e8",
            "\1\u02e9",
            "\1\u02eb\13\uffff\1\u02ea",
            "\1\152\3\uffff\1\141\1\142\1\143\1\144\4\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u02ec\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\u02ed\1\uffff\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u02ee\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00fd\123\uffff\2\u00fc",
            "\1\u00fd\1\uffff\1\u02ef\121\uffff\2\u00fc",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u02f0\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u02f1\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u02f3\13\uffff\1\u02f2",
            "\1\u02f5\13\uffff\1\u02f4",
            "\1\152\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109\4\uffff\1\u0100"+
            "\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u02f6\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109"+
            "\4\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u02f7\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\u02f8\1\uffff\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u02f9\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\156\123\uffff\2\155",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u02fa\11\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\12\uffff\1\172",
            "\1\173\1\171\1\u02fb\11\uffff\1\172",
            "\1\u02fc",
            "\1\u02fe\13\uffff\1\u02fd",
            "\1\u0300\13\uffff\1\u02ff",
            "\1\u0302\13\uffff\1\u0301",
            "\1\u0304\13\uffff\1\u0303",
            "\1\u0306\13\uffff\1\u0305",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u0307\11\uffff\1\u0130",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u0308\11\uffff\1\u0130",
            "\1\u030a\13\uffff\1\u0309",
            "\1\u030c\13\uffff\1\u030b",
            "\1\u030e\13\uffff\1\u030d",
            "\1\u0310\13\uffff\1\u030f",
            "\1\u0311",
            "\1\u0312",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\u0313\1\uffff\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0314\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00fd\123\uffff\2\u00fc",
            "\1\u00fd\1\uffff\1\u0315\121\uffff\2\u00fc",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0316\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0317\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u0319\13\uffff\1\u0318",
            "\1\u031b\13\uffff\1\u031a",
            "\1\152\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109\4\uffff\1\u0100"+
            "\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u031c\1\uffff\1\u0106\1\u0107\1\u0108\1\u0109"+
            "\4\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u031d\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\u031e\1\uffff\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u031f\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\13\uffff\1\145\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\173\1\171\12\uffff\1\172",
            "\1\u0320",
            "\1\u0322\13\uffff\1\u0321",
            "\1\u0323",
            "\1\u0325\13\uffff\1\u0324",
            "\1\u0326",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u0327\11\uffff\1\u0130",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u0328\11\uffff\1\u0130",
            "\1\u0329",
            "\1\u032b\13\uffff\1\u032a",
            "\1\u032d\13\uffff\1\u032c",
            "\1\u032f\13\uffff\1\u032e",
            "\1\u00f6\1\u00f7\1\u00f8\1\u00f9",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0330\5\uffff\1\u00a2\3\uffff\1\145\107\uffff"+
            "\2\146\2\151\2\uffff\2\147",
            "\1\u00fd\123\uffff\2\u00fc",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\1\uffff\1\u0331\11\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0332",
            "\1\u0334\13\uffff\1\u0333",
            "\1\152\3\uffff\1\u0106\1\u0107\1\u0108\1\u0109\4\uffff\1\u0100"+
            "\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0335\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\u0336\1\uffff\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0337\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0338",
            "\1\u0339",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0131\1\u012f\1\u033a\11\uffff\1\u0130",
            "\1\u033b",
            "\1\u033d\13\uffff\1\u033c",
            "\1\u033f\13\uffff\1\u033e",
            "\1\152\7\uffff\1\u00a2\3\uffff\1\145\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\13\uffff\1\u0100\107\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0340",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\u0341\1\uffff\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0342\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0131\1\u012f\12\uffff\1\u0130",
            "\1\u0343",
            "\1\u0345\13\uffff\1\u0344",
            "\1\u01a5\1\u01a6\1\u01a7\1\u01a8",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147",
            "\1\152\1\uffff\1\u0346\5\uffff\1\u0160\3\uffff\1\u0100\107"+
            "\uffff\2\146\2\151\2\uffff\2\147",
            "\1\u0347",
            "\1\152\7\uffff\1\u0160\3\uffff\1\u0100\107\uffff\2\146\2\151"+
            "\2\uffff\2\147"
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
        "\6\uffff\1\4\1\2\1\1\1\3\20\uffff";
    static final String DFA10_specialS =
        "\32\uffff}>";
    static final String[] DFA10_transitionS = {
            "\26\1\21\uffff\1\2\51\uffff\1\2\2\3",
            "\1\5\1\uffff\1\4\1\uffff\4\10\7\uffff\1\7\114\uffff\1\7\4\11"+
            "\7\uffff\2\6",
            "\1\12\12\uffff\1\13",
            "\1\14\12\uffff\1\15",
            "\3\16\21\uffff\42\16\10\17",
            "\1\5\3\uffff\4\10\7\uffff\1\7\114\uffff\1\7\4\11\7\uffff\2"+
            "\6",
            "",
            "",
            "",
            "",
            "\1\12\12\uffff\1\13",
            "\1\20\17\uffff\26\21",
            "\1\14\12\uffff\1\15",
            "\1\22\17\uffff\26\23",
            "\1\5\3\uffff\4\10\7\uffff\1\7\114\uffff\1\7\4\11\7\uffff\2"+
            "\6",
            "\1\5\3\uffff\4\10\7\uffff\1\7\114\uffff\1\7\4\11\7\uffff\2"+
            "\6",
            "\1\20\17\uffff\26\21",
            "\1\24\13\uffff\1\25",
            "\1\22\17\uffff\26\23",
            "\1\26",
            "\1\24\13\uffff\1\25",
            "\1\5\3\uffff\4\10\7\uffff\1\7\114\uffff\1\7\4\11\7\uffff\2"+
            "\6",
            "\3\27\21\uffff\42\27",
            "\1\30\13\uffff\1\31",
            "\1\30\13\uffff\1\31",
            "\1\5\3\uffff\4\10\7\uffff\1\7\114\uffff\1\7\4\11\7\uffff\2"+
            "\6"
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
            return "63:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue | kw= keyword spaces op3= between spaces val3= betValue );";
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
            return "92:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );";
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
            return "()* loopback of 118:21: ( spaces COMMA spaces dotValue )*";
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
        "\4\uffff\1\4\1\5\1\1\1\6\1\2\1\7\1\11\1\10\1\3";
    static final String DFA13_specialS =
        "\15\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\1\1\2\1\3\1\4",
            "\1\6\2\uffff\1\6\1\uffff\1\7\1\5\10\uffff\1\6",
            "\1\10\2\uffff\1\10\1\11\1\uffff\1\12\10\uffff\1\10",
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
            return "120:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );";
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
            return "130:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );";
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
    public static final BitSet FOLLOW_15_in_constraint1367 = new BitSet(new long[]{0x080003FFFFF08010L,0x000000E000000000L});
    public static final BitSet FOLLOW_constraint_in_constraint1391 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_constraint1417 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_keyword_in_constraint433 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_spaces_in_constraint442 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_compOpt_in_constraint449 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint459 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_genValue_in_constraint466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint494 = new BitSet(new long[]{0x0000000000080010L,0x0000000100000000L});
    public static final BitSet FOLLOW_spaces_in_constraint503 = new BitSet(new long[]{0x0000000000080010L,0x0000000100000000L});
    public static final BitSet FOLLOW_in_in_constraint510 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_constraint521 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_constraint523 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint527 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_valueList_in_constraint533 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_constraint541 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_constraint545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint571 = new BitSet(new long[]{0x0000000000000010L,0x0000001E00000000L});
    public static final BitSet FOLLOW_spaces_in_constraint580 = new BitSet(new long[]{0x0000000000000010L,0x0000001E00000000L});
    public static final BitSet FOLLOW_like_in_constraint587 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint596 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_constraint603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint618 = new BitSet(new long[]{0x0000000000000010L,0x0000300000000000L});
    public static final BitSet FOLLOW_spaces_in_constraint627 = new BitSet(new long[]{0x0000000000000010L,0x0000300000000000L});
    public static final BitSet FOLLOW_between_in_constraint634 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint642 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_betValue_in_constraint649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_where696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_dotValue723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue729 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue731 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue739 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue741 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue749 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue751 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue753 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue755 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue763 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue765 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue767 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue769 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue777 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue779 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue781 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue783 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue785 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue787 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue795 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue797 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue799 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue801 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue803 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue805 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue813 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue815 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue817 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue819 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue821 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue823 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue825 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue827 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue835 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue837 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue839 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue841 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue843 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue845 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue847 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue849 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue857 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue859 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue861 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue863 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue865 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue867 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue869 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue871 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue873 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue875 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue883 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue885 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue887 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue889 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue891 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue893 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue895 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue897 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue899 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue901 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue903 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_VALUE_in_dotValue963 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_VALUE_in_dotValue1031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1037 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1039 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1041 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1043 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1045 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1047 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1049 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1051 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1053 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1055 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1057 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1059 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1061 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1063 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1065 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1067 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue1069 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_DOT_in_dotValue1101 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1103 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1105 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1114 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1116 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1118 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1120 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1122 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1124 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1126 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1128 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1130 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1132 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1134 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1136 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1138 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1140 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1142 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1144 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1146 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1148 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue1150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1156 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1158 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1160 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1162 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1164 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1166 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1168 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1170 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1172 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1174 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1176 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1178 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1180 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1182 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1184 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1186 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1188 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1190 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1199 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1201 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1203 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1205 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1207 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1209 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1211 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1213 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1215 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1217 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1219 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1221 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1223 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1225 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1227 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1229 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1231 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1233 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1235 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_dotValue1237 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_dotValue1239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1245 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1247 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1255 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1257 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1259 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SPACE_in_dotValue1261 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_VALUE_in_dotValue1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_valueList1272 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_valueList1276 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_valueList1278 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_valueList1280 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_valueList1282 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_EQ_in_compOpt1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt1308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_compOpt1315 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt1325 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt1328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpt1335 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LT_in_compOpt1338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1345 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpt1355 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EQ_in_compOpt1358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpt1365 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_GT_in_compOpt1368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue1376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotValue_in_genValue1381 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_compOpt_in_genValue1383 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1385 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_AMP_in_genValue1388 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1390 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_compOpt_in_genValue1392 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1394 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_dotValue_in_betValue1402 = new BitSet(new long[]{0x0000000000000010L,0x0000000003000000L});
    public static final BitSet FOLLOW_spaces_in_betValue1404 = new BitSet(new long[]{0x0000000000000000L,0x0000000003000000L});
    public static final BitSet FOLLOW_and_in_betValue1406 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_betValue1408 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_betValue1410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_in_logicalOp1422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp1424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity1433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr1524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct1677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select1715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and1736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_order1749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_by1762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or1775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in1788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not1801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_like1815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_100_in_like1819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_like1823 = new BitSet(new long[]{0x0000000000000010L,0x0000000800000000L});
    public static final BitSet FOLLOW_spaces_in_like1825 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_99_in_like1827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_like1831 = new BitSet(new long[]{0x0000000000000010L,0x0000001000000000L});
    public static final BitSet FOLLOW_spaces_in_like1833 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_like1835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_count1844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sum1857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_asc1870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_desc1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_between1896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_lb1911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rb1921 = new BitSet(new long[]{0x0000000000000002L});

}