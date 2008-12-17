package dbs.search.parser;
// $ANTLR 3.1.1 /Users/vk/Sql.g 2008-12-17 13:45:28


import java.util.ArrayList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SPACE", "COMMA", "DOT", "VALUE", "EQ", "LT", "GT", "NOT", "AMP", "NL", "WS", "'('", "')'", "'WHERE'", "'where'", "'in'", "'ads'", "'config'", "'dataset'", "'release'", "'tier'", "'site'", "'block'", "'file'", "'primds'", "'procds'", "'run'", "'lumi'", "'dq'", "'ilumi'", "'phygrp'", "'group'", "'pset'", "'algo'", "'datatype'", "'mcdesc'", "'trigdesc'", "'createdate'", "'moddate'", "'starttime'", "'endtime'", "'createby'", "'modby'", "'name'", "'version'", "'number'", "'startevnum'", "'endevnum'", "'numevents'", "'numfiles'", "'numlss'", "'totlumi'", "'store'", "'size'", "'count'", "'status'", "'type'", "'id'", "'parent'", "'child'", "'def'", "'evnum'", "'era'", "'tag'", "'xsection'", "'hash'", "'content'", "'family'", "'exe'", "'annotation'", "'numruns()'", "'numfiles()'", "'dataquality()'", "'latest()'", "'parentrelease()'", "'childrelease()'", "'intluminosity()'", "'findevents()'", "'select'", "'SELECT'", "'find'", "'FIND'", "'and'", "'AND'", "'order'", "'ORDER'", "'by'", "'BY'", "'or'", "'OR'", "'IN'", "'not'", "'NOT'", "'like'", "'LIKE'", "'COUNT'", "'sum'", "'SUM'", "'asc'", "'ASC'", "'desc'", "'DESC'", "'between'", "'BETWEEN'"
    };
    public static final int COMMA=5;
    public static final int T__42=42;
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
    public String getGrammarFileName() { return "/Users/vk/Sql.g"; }


    ArrayList kws = new ArrayList();
    ArrayList okws = new ArrayList();
    ArrayList constraints = new ArrayList();
    String orderingkw = "";



    // $ANTLR start "stmt"
    // /Users/vk/Sql.g:21:1: stmt : ( select spaces selectList spaces where spaces constraintList spaces | select spaces selectList spaces | select spaces selectList spaces order spaces by spaces orderList | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces orderList );
    public final void stmt() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:21:6: ( select spaces selectList spaces where spaces constraintList spaces | select spaces selectList spaces | select spaces selectList spaces order spaces by spaces orderList | select spaces selectList spaces where spaces constraintList spaces order spaces by spaces orderList )
            int alt1=4;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // /Users/vk/Sql.g:21:8: select spaces selectList spaces where spaces constraintList spaces
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
                    // /Users/vk/Sql.g:22:4: select spaces selectList spaces
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
                    // /Users/vk/Sql.g:23:4: select spaces selectList spaces order spaces by spaces orderList
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
                    // /Users/vk/Sql.g:24:4: select spaces selectList spaces where spaces constraintList spaces order spaces by spaces orderList
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
    // /Users/vk/Sql.g:27:1: spaces : ( SPACE )* ;
    public final void spaces() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:27:8: ( ( SPACE )* )
            // /Users/vk/Sql.g:27:10: ( SPACE )*
            {
            // /Users/vk/Sql.g:27:10: ( SPACE )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==SPACE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/vk/Sql.g:27:11: SPACE
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
    // /Users/vk/Sql.g:29:1: orderList : okw= keyword ( spaces COMMA spaces okw= keyword )* spaces oing= ordering ;
    public final void orderList() throws RecognitionException {
        SqlParser.keyword_return okw = null;

        SqlParser.ordering_return oing = null;


        try {
            // /Users/vk/Sql.g:29:11: (okw= keyword ( spaces COMMA spaces okw= keyword )* spaces oing= ordering )
            // /Users/vk/Sql.g:29:12: okw= keyword ( spaces COMMA spaces okw= keyword )* spaces oing= ordering
            {
            pushFollow(FOLLOW_keyword_in_orderList125);
            okw=keyword();

            state._fsp--;

            okws.add((okw!=null?input.toString(okw.start,okw.stop):null));
            // /Users/vk/Sql.g:30:4: ( spaces COMMA spaces okw= keyword )*
            loop3:
            do {
                int alt3=2;
                alt3 = dfa3.predict(input);
                switch (alt3) {
            	case 1 :
            	    // /Users/vk/Sql.g:31:3: spaces COMMA spaces okw= keyword
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
    // /Users/vk/Sql.g:40:1: ordering : ( asc | desc )? ;
    public final SqlParser.ordering_return ordering() throws RecognitionException {
        SqlParser.ordering_return retval = new SqlParser.ordering_return();
        retval.start = input.LT(1);

        try {
            // /Users/vk/Sql.g:40:11: ( ( asc | desc )? )
            // /Users/vk/Sql.g:40:13: ( asc | desc )?
            {
            // /Users/vk/Sql.g:40:13: ( asc | desc )?
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=102 && LA4_0<=103)) ) {
                alt4=1;
            }
            else if ( ((LA4_0>=104 && LA4_0<=105)) ) {
                alt4=2;
            }
            switch (alt4) {
                case 1 :
                    // /Users/vk/Sql.g:40:14: asc
                    {
                    pushFollow(FOLLOW_asc_in_ordering192);
                    asc();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/vk/Sql.g:40:18: desc
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
    // /Users/vk/Sql.g:41:1: selectList : kw= keyword ( spaces COMMA spaces kw= keyword )* ;
    public final void selectList() throws RecognitionException {
        SqlParser.keyword_return kw = null;


        try {
            // /Users/vk/Sql.g:41:12: (kw= keyword ( spaces COMMA spaces kw= keyword )* )
            // /Users/vk/Sql.g:41:13: kw= keyword ( spaces COMMA spaces kw= keyword )*
            {
            pushFollow(FOLLOW_keyword_in_selectList207);
            kw=keyword();

            state._fsp--;

            kws.add((kw!=null?input.toString(kw.start,kw.stop):null));
            // /Users/vk/Sql.g:42:4: ( spaces COMMA spaces kw= keyword )*
            loop5:
            do {
                int alt5=2;
                alt5 = dfa5.predict(input);
                switch (alt5) {
            	case 1 :
            	    // /Users/vk/Sql.g:43:3: spaces COMMA spaces kw= keyword
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
    // /Users/vk/Sql.g:49:1: keyword : ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' );
    public final SqlParser.keyword_return keyword() throws RecognitionException {
        SqlParser.keyword_return retval = new SqlParser.keyword_return();
        retval.start = input.LT(1);

        try {
            // /Users/vk/Sql.g:49:9: ( entity | entity DOT attr | entity DOT funct | count spaces '(' spaces entity spaces ')' | sum spaces '(' spaces entity DOT attr spaces ')' )
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
                {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==EOF||(LA6_1>=SPACE && LA6_1<=COMMA)||(LA6_1>=EQ && LA6_1<=NOT)||(LA6_1>=17 && LA6_1<=19)||(LA6_1>=88 && LA6_1<=89)||(LA6_1>=94 && LA6_1<=98)||(LA6_1>=102 && LA6_1<=107)) ) {
                    alt6=1;
                }
                else if ( (LA6_1==DOT) ) {
                    int LA6_5 = input.LA(3);

                    if ( ((LA6_5>=22 && LA6_5<=24)||(LA6_5>=41 && LA6_5<=73)) ) {
                        alt6=2;
                    }
                    else if ( ((LA6_5>=74 && LA6_5<=81)) ) {
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
            case 58:
            case 99:
                {
                alt6=4;
                }
                break;
            case 100:
            case 101:
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
                    // /Users/vk/Sql.g:49:11: entity
                    {
                    pushFollow(FOLLOW_entity_in_keyword260);
                    entity();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/vk/Sql.g:50:4: entity DOT attr
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
                    // /Users/vk/Sql.g:51:4: entity DOT funct
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
                    // /Users/vk/Sql.g:52:4: count spaces '(' spaces entity spaces ')'
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
                    // /Users/vk/Sql.g:53:4: sum spaces '(' spaces entity DOT attr spaces ')'
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
    // /Users/vk/Sql.g:55:1: constraintList : constraint ( spaces rel= logicalOp spaces constraint )* ;
    public final void constraintList() throws RecognitionException {
        SqlParser.logicalOp_return rel = null;


        try {
            // /Users/vk/Sql.g:55:16: ( constraint ( spaces rel= logicalOp spaces constraint )* )
            // /Users/vk/Sql.g:55:18: constraint ( spaces rel= logicalOp spaces constraint )*
            {
            pushFollow(FOLLOW_constraint_in_constraintList326);
            constraint();

            state._fsp--;

            // /Users/vk/Sql.g:55:29: ( spaces rel= logicalOp spaces constraint )*
            loop7:
            do {
                int alt7=2;
                alt7 = dfa7.predict(input);
                switch (alt7) {
            	case 1 :
            	    // /Users/vk/Sql.g:55:31: spaces rel= logicalOp spaces constraint
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

            	    pushFollow(FOLLOW_constraint_in_constraintList347);
            	    constraint();

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


    // $ANTLR start "constraint"
    // /Users/vk/Sql.g:59:1: constraint : (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue | kw= keyword spaces op3= between spaces val3= betValue );
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
            // /Users/vk/Sql.g:59:12: (kw= keyword spaces op= compOpt spaces val= genValue | kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')' | kw= keyword spaces op2= like spaces val2= dotValue | kw= keyword spaces op3= between spaces val3= betValue )
            int alt8=4;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // /Users/vk/Sql.g:59:14: kw= keyword spaces op= compOpt spaces val= genValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint360);
                    kw=keyword();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint369);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_compOpt_in_constraint376);
                    op=compOpt();

                    state._fsp--;

                    c.setOp((op!=null?input.toString(op.start,op.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint386);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_genValue_in_constraint393);
                    val=genValue();

                    state._fsp--;

                    c.setValue((val!=null?input.toString(val.start,val.stop):null)); constraints.add(c); 	

                    }
                    break;
                case 2 :
                    // /Users/vk/Sql.g:65:2: kw= keyword spaces op1= in spaces '(' spaces val1= valueList spaces ')'
                    {
                    pushFollow(FOLLOW_keyword_in_constraint422);
                    kw=keyword();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint431);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_in_in_constraint438);
                    op1=in();

                    state._fsp--;

                    c.setOp((op1!=null?input.toString(op1.start,op1.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint449);
                    spaces();

                    state._fsp--;

                    match(input,15,FOLLOW_15_in_constraint451); 
                    pushFollow(FOLLOW_spaces_in_constraint455);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_valueList_in_constraint461);
                    val1=valueList();

                    state._fsp--;

                    c.setValue((val1!=null?input.toString(val1.start,val1.stop):null)); constraints.add(c);
                    pushFollow(FOLLOW_spaces_in_constraint469);
                    spaces();

                    state._fsp--;

                    match(input,16,FOLLOW_16_in_constraint473); 

                    }
                    break;
                case 3 :
                    // /Users/vk/Sql.g:74:2: kw= keyword spaces op2= like spaces val2= dotValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint499);
                    kw=keyword();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint508);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_like_in_constraint515);
                    op2=like();

                    state._fsp--;

                    c.setOp((op2!=null?input.toString(op2.start,op2.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint524);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_dotValue_in_constraint531);
                    val2=dotValue();

                    state._fsp--;

                    c.setValue((val2!=null?input.toString(val2.start,val2.stop):null)); constraints.add(c);

                    }
                    break;
                case 4 :
                    // /Users/vk/Sql.g:80:3: kw= keyword spaces op3= between spaces val3= betValue
                    {
                    pushFollow(FOLLOW_keyword_in_constraint546);
                    kw=keyword();

                    state._fsp--;

                    Constraint c= new Constraint(); c.setKey((kw!=null?input.toString(kw.start,kw.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint555);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_between_in_constraint562);
                    op3=between();

                    state._fsp--;

                    c.setOp((op3!=null?input.toString(op3.start,op3.stop):null));
                    pushFollow(FOLLOW_spaces_in_constraint570);
                    spaces();

                    state._fsp--;

                    pushFollow(FOLLOW_betValue_in_constraint577);
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
    // /Users/vk/Sql.g:87:1: where : ( 'WHERE' | 'where' ) ;
    public final void where() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:87:7: ( ( 'WHERE' | 'where' ) )
            // /Users/vk/Sql.g:87:8: ( 'WHERE' | 'where' )
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
    // /Users/vk/Sql.g:88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );
    public final SqlParser.dotValue_return dotValue() throws RecognitionException {
        SqlParser.dotValue_return retval = new SqlParser.dotValue_return();
        retval.start = input.LT(1);

        try {
            // /Users/vk/Sql.g:88:17: ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE )
            int alt9=23;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // /Users/vk/Sql.g:88:19: VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue644); 

                    }
                    break;
                case 2 :
                    // /Users/vk/Sql.g:89:5: 'in'
                    {
                    match(input,19,FOLLOW_19_in_dotValue651); 

                    }
                    break;
                case 3 :
                    // /Users/vk/Sql.g:90:5: VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue657); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue659); 
                    match(input,19,FOLLOW_19_in_dotValue661); 

                    }
                    break;
                case 4 :
                    // /Users/vk/Sql.g:91:5: VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue667); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue669); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue671); 

                    }
                    break;
                case 5 :
                    // /Users/vk/Sql.g:92:5: VALUE DOT VALUE DOT 'in'
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue677); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue679); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue681); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue683); 
                    match(input,19,FOLLOW_19_in_dotValue685); 

                    }
                    break;
                case 6 :
                    // /Users/vk/Sql.g:93:5: VALUE DOT VALUE DOT VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue691); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue693); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue695); 
                    match(input,DOT,FOLLOW_DOT_in_dotValue697); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue699); 

                    }
                    break;
                case 7 :
                    // /Users/vk/Sql.g:94:5: VALUE DOT VALUE DOT VALUE DOT 'in'
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
                    // /Users/vk/Sql.g:95:5: VALUE DOT VALUE DOT VALUE DOT VALUE
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
                    // /Users/vk/Sql.g:96:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
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
                    // /Users/vk/Sql.g:97:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
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
                    // /Users/vk/Sql.g:98:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
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
                    // /Users/vk/Sql.g:99:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
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
                    // /Users/vk/Sql.g:100:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
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
                    // /Users/vk/Sql.g:101:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
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
                    // /Users/vk/Sql.g:102:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
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
                    // /Users/vk/Sql.g:103:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
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
                    // /Users/vk/Sql.g:104:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
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
                    // /Users/vk/Sql.g:105:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
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
                    // /Users/vk/Sql.g:106:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
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
                    // /Users/vk/Sql.g:107:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
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
                    // /Users/vk/Sql.g:108:5: VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
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
                    // /Users/vk/Sql.g:109:5: VALUE SPACE VALUE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1173); 
                    match(input,SPACE,FOLLOW_SPACE_in_dotValue1175); 
                    match(input,VALUE,FOLLOW_VALUE_in_dotValue1177); 

                    }
                    break;
                case 23 :
                    // /Users/vk/Sql.g:110:5: VALUE SPACE VALUE SPACE VALUE
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
    // $ANTLR end "dotValue"

    public static class valueList_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "valueList"
    // /Users/vk/Sql.g:114:1: valueList : dotValue ( spaces COMMA spaces dotValue )* ;
    public final SqlParser.valueList_return valueList() throws RecognitionException {
        SqlParser.valueList_return retval = new SqlParser.valueList_return();
        retval.start = input.LT(1);

        try {
            // /Users/vk/Sql.g:114:11: ( dotValue ( spaces COMMA spaces dotValue )* )
            // /Users/vk/Sql.g:114:12: dotValue ( spaces COMMA spaces dotValue )*
            {
            pushFollow(FOLLOW_dotValue_in_valueList1200);
            dotValue();

            state._fsp--;

            // /Users/vk/Sql.g:114:21: ( spaces COMMA spaces dotValue )*
            loop10:
            do {
                int alt10=2;
                alt10 = dfa10.predict(input);
                switch (alt10) {
            	case 1 :
            	    // /Users/vk/Sql.g:114:23: spaces COMMA spaces dotValue
            	    {
            	    pushFollow(FOLLOW_spaces_in_valueList1204);
            	    spaces();

            	    state._fsp--;

            	    match(input,COMMA,FOLLOW_COMMA_in_valueList1206); 
            	    pushFollow(FOLLOW_spaces_in_valueList1208);
            	    spaces();

            	    state._fsp--;

            	    pushFollow(FOLLOW_dotValue_in_valueList1210);
            	    dotValue();

            	    state._fsp--;


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
    // $ANTLR end "valueList"

    public static class compOpt_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "compOpt"
    // /Users/vk/Sql.g:116:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );
    public final SqlParser.compOpt_return compOpt() throws RecognitionException {
        SqlParser.compOpt_return retval = new SqlParser.compOpt_return();
        retval.start = input.LT(1);

        try {
            // /Users/vk/Sql.g:116:10: ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) )
            int alt11=9;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // /Users/vk/Sql.g:116:11: ( EQ )
                    {
                    // /Users/vk/Sql.g:116:11: ( EQ )
                    // /Users/vk/Sql.g:116:12: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1222); 

                    }


                    }
                    break;
                case 2 :
                    // /Users/vk/Sql.g:117:4: ( LT )
                    {
                    // /Users/vk/Sql.g:117:4: ( LT )
                    // /Users/vk/Sql.g:117:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1229); 

                    }


                    }
                    break;
                case 3 :
                    // /Users/vk/Sql.g:118:4: ( GT )
                    {
                    // /Users/vk/Sql.g:118:4: ( GT )
                    // /Users/vk/Sql.g:118:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1236); 

                    }


                    }
                    break;
                case 4 :
                    // /Users/vk/Sql.g:119:4: ( NOT ) ( EQ )
                    {
                    // /Users/vk/Sql.g:119:4: ( NOT )
                    // /Users/vk/Sql.g:119:5: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_compOpt1243); 

                    }

                    // /Users/vk/Sql.g:119:9: ( EQ )
                    // /Users/vk/Sql.g:119:10: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1246); 

                    }


                    }
                    break;
                case 5 :
                    // /Users/vk/Sql.g:120:4: ( EQ ) ( GT )
                    {
                    // /Users/vk/Sql.g:120:4: ( EQ )
                    // /Users/vk/Sql.g:120:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1253); 

                    }

                    // /Users/vk/Sql.g:120:8: ( GT )
                    // /Users/vk/Sql.g:120:9: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1256); 

                    }


                    }
                    break;
                case 6 :
                    // /Users/vk/Sql.g:121:4: ( EQ ) ( LT )
                    {
                    // /Users/vk/Sql.g:121:4: ( EQ )
                    // /Users/vk/Sql.g:121:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1263); 

                    }

                    // /Users/vk/Sql.g:121:8: ( LT )
                    // /Users/vk/Sql.g:121:9: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1266); 

                    }


                    }
                    break;
                case 7 :
                    // /Users/vk/Sql.g:122:4: ( LT ) ( EQ )
                    {
                    // /Users/vk/Sql.g:122:4: ( LT )
                    // /Users/vk/Sql.g:122:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1273); 

                    }

                    // /Users/vk/Sql.g:122:8: ( EQ )
                    // /Users/vk/Sql.g:122:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1276); 

                    }


                    }
                    break;
                case 8 :
                    // /Users/vk/Sql.g:123:4: ( GT ) ( EQ )
                    {
                    // /Users/vk/Sql.g:123:4: ( GT )
                    // /Users/vk/Sql.g:123:5: GT
                    {
                    match(input,GT,FOLLOW_GT_in_compOpt1283); 

                    }

                    // /Users/vk/Sql.g:123:8: ( EQ )
                    // /Users/vk/Sql.g:123:9: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_compOpt1286); 

                    }


                    }
                    break;
                case 9 :
                    // /Users/vk/Sql.g:124:4: ( LT ) ( GT )
                    {
                    // /Users/vk/Sql.g:124:4: ( LT )
                    // /Users/vk/Sql.g:124:5: LT
                    {
                    match(input,LT,FOLLOW_LT_in_compOpt1293); 

                    }

                    // /Users/vk/Sql.g:124:8: ( GT )
                    // /Users/vk/Sql.g:124:9: GT
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
    // $ANTLR end "compOpt"

    public static class genValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "genValue"
    // /Users/vk/Sql.g:126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );
    public final SqlParser.genValue_return genValue() throws RecognitionException {
        SqlParser.genValue_return retval = new SqlParser.genValue_return();
        retval.start = input.LT(1);

        try {
            // /Users/vk/Sql.g:126:10: ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* )
            int alt13=2;
            alt13 = dfa13.predict(input);
            switch (alt13) {
                case 1 :
                    // /Users/vk/Sql.g:126:11: dotValue
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue1304);
                    dotValue();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/vk/Sql.g:127:4: dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )*
                    {
                    pushFollow(FOLLOW_dotValue_in_genValue1309);
                    dotValue();

                    state._fsp--;

                    pushFollow(FOLLOW_compOpt_in_genValue1311);
                    compOpt();

                    state._fsp--;

                    pushFollow(FOLLOW_dotValue_in_genValue1313);
                    dotValue();

                    state._fsp--;

                    // /Users/vk/Sql.g:127:30: ( AMP dotValue compOpt dotValue )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==AMP) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /Users/vk/Sql.g:127:31: AMP dotValue compOpt dotValue
                    	    {
                    	    match(input,AMP,FOLLOW_AMP_in_genValue1316); 
                    	    pushFollow(FOLLOW_dotValue_in_genValue1318);
                    	    dotValue();

                    	    state._fsp--;

                    	    pushFollow(FOLLOW_compOpt_in_genValue1320);
                    	    compOpt();

                    	    state._fsp--;

                    	    pushFollow(FOLLOW_dotValue_in_genValue1322);
                    	    dotValue();

                    	    state._fsp--;


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
    // $ANTLR end "genValue"

    public static class betValue_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "betValue"
    // /Users/vk/Sql.g:128:1: betValue : dotValue spaces and spaces dotValue ;
    public final SqlParser.betValue_return betValue() throws RecognitionException {
        SqlParser.betValue_return retval = new SqlParser.betValue_return();
        retval.start = input.LT(1);

        try {
            // /Users/vk/Sql.g:128:10: ( dotValue spaces and spaces dotValue )
            // /Users/vk/Sql.g:128:11: dotValue spaces and spaces dotValue
            {
            pushFollow(FOLLOW_dotValue_in_betValue1330);
            dotValue();

            state._fsp--;

            pushFollow(FOLLOW_spaces_in_betValue1332);
            spaces();

            state._fsp--;

            pushFollow(FOLLOW_and_in_betValue1334);
            and();

            state._fsp--;

            pushFollow(FOLLOW_spaces_in_betValue1336);
            spaces();

            state._fsp--;

            pushFollow(FOLLOW_dotValue_in_betValue1338);
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
    // /Users/vk/Sql.g:134:1: logicalOp : ( and | or ) ;
    public final SqlParser.logicalOp_return logicalOp() throws RecognitionException {
        SqlParser.logicalOp_return retval = new SqlParser.logicalOp_return();
        retval.start = input.LT(1);

        try {
            // /Users/vk/Sql.g:134:11: ( ( and | or ) )
            // /Users/vk/Sql.g:134:12: ( and | or )
            {
            // /Users/vk/Sql.g:134:12: ( and | or )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=86 && LA14_0<=87)) ) {
                alt14=1;
            }
            else if ( ((LA14_0>=92 && LA14_0<=93)) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // /Users/vk/Sql.g:134:13: and
                    {
                    pushFollow(FOLLOW_and_in_logicalOp1350);
                    and();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/vk/Sql.g:134:17: or
                    {
                    pushFollow(FOLLOW_or_in_logicalOp1352);
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
    // /Users/vk/Sql.g:135:1: entity : ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' ) ;
    public final void entity() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:135:9: ( ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' ) )
            // /Users/vk/Sql.g:135:11: ( 'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group' | 'pset' | 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' )
            {
            if ( (input.LA(1)>=20 && input.LA(1)<=40) ) {
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
    // /Users/vk/Sql.g:136:1: attr : ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' ) ;
    public final void attr() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:136:7: ( ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' ) )
            // /Users/vk/Sql.g:136:8: ( 'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family' | 'exe' | 'annotation' )
            {
            if ( (input.LA(1)>=22 && input.LA(1)<=24)||(input.LA(1)>=41 && input.LA(1)<=73) ) {
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
    // /Users/vk/Sql.g:137:1: funct : ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) ;
    public final void funct() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:137:8: ( ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' ) )
            // /Users/vk/Sql.g:137:9: ( 'numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' )
            {
            if ( (input.LA(1)>=74 && input.LA(1)<=81) ) {
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
    // /Users/vk/Sql.g:138:1: select : ( 'select' | 'SELECT' | 'find' | 'FIND' ) ;
    public final void select() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:138:9: ( ( 'select' | 'SELECT' | 'find' | 'FIND' ) )
            // /Users/vk/Sql.g:138:10: ( 'select' | 'SELECT' | 'find' | 'FIND' )
            {
            if ( (input.LA(1)>=82 && input.LA(1)<=85) ) {
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
    // /Users/vk/Sql.g:139:1: and : ( 'and' | 'AND' ) ;
    public final void and() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:139:6: ( ( 'and' | 'AND' ) )
            // /Users/vk/Sql.g:139:7: ( 'and' | 'AND' )
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
    // $ANTLR end "and"


    // $ANTLR start "order"
    // /Users/vk/Sql.g:140:1: order : ( 'order' | 'ORDER' ) ;
    public final void order() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:140:8: ( ( 'order' | 'ORDER' ) )
            // /Users/vk/Sql.g:140:9: ( 'order' | 'ORDER' )
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
    // $ANTLR end "order"


    // $ANTLR start "by"
    // /Users/vk/Sql.g:141:1: by : ( 'by' | 'BY' ) ;
    public final void by() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:141:5: ( ( 'by' | 'BY' ) )
            // /Users/vk/Sql.g:141:6: ( 'by' | 'BY' )
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
    // $ANTLR end "by"


    // $ANTLR start "or"
    // /Users/vk/Sql.g:142:1: or : ( 'or' | 'OR' ) ;
    public final void or() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:142:5: ( ( 'or' | 'OR' ) )
            // /Users/vk/Sql.g:142:6: ( 'or' | 'OR' )
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
    // $ANTLR end "or"

    public static class in_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "in"
    // /Users/vk/Sql.g:143:1: in : ( 'in' | 'IN' ) ;
    public final SqlParser.in_return in() throws RecognitionException {
        SqlParser.in_return retval = new SqlParser.in_return();
        retval.start = input.LT(1);

        try {
            // /Users/vk/Sql.g:143:5: ( ( 'in' | 'IN' ) )
            // /Users/vk/Sql.g:143:6: ( 'in' | 'IN' )
            {
            if ( input.LA(1)==19||input.LA(1)==94 ) {
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
    // /Users/vk/Sql.g:144:1: not : ( 'not' | 'NOT' ) ;
    public final void not() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:144:6: ( ( 'not' | 'NOT' ) )
            // /Users/vk/Sql.g:144:7: ( 'not' | 'NOT' )
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
    // $ANTLR end "not"

    public static class like_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "like"
    // /Users/vk/Sql.g:145:1: like : ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' ) ;
    public final SqlParser.like_return like() throws RecognitionException {
        SqlParser.like_return retval = new SqlParser.like_return();
        retval.start = input.LT(1);

        try {
            // /Users/vk/Sql.g:145:7: ( ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' ) )
            // /Users/vk/Sql.g:145:8: ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' )
            {
            // /Users/vk/Sql.g:145:8: ( 'like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE' )
            int alt15=4;
            switch ( input.LA(1) ) {
            case 97:
                {
                alt15=1;
                }
                break;
            case 98:
                {
                alt15=2;
                }
                break;
            case 95:
                {
                alt15=3;
                }
                break;
            case 96:
                {
                alt15=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // /Users/vk/Sql.g:145:9: 'like'
                    {
                    match(input,97,FOLLOW_97_in_like1736); 

                    }
                    break;
                case 2 :
                    // /Users/vk/Sql.g:145:18: 'LIKE'
                    {
                    match(input,98,FOLLOW_98_in_like1740); 

                    }
                    break;
                case 3 :
                    // /Users/vk/Sql.g:145:27: 'not' spaces 'like'
                    {
                    match(input,95,FOLLOW_95_in_like1744); 
                    pushFollow(FOLLOW_spaces_in_like1746);
                    spaces();

                    state._fsp--;

                    match(input,97,FOLLOW_97_in_like1748); 

                    }
                    break;
                case 4 :
                    // /Users/vk/Sql.g:145:49: 'NOT' spaces 'LIKE'
                    {
                    match(input,96,FOLLOW_96_in_like1752); 
                    pushFollow(FOLLOW_spaces_in_like1754);
                    spaces();

                    state._fsp--;

                    match(input,98,FOLLOW_98_in_like1756); 

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
    // /Users/vk/Sql.g:147:1: count : ( 'count' | 'COUNT' ) ;
    public final void count() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:147:8: ( ( 'count' | 'COUNT' ) )
            // /Users/vk/Sql.g:147:9: ( 'count' | 'COUNT' )
            {
            if ( input.LA(1)==58||input.LA(1)==99 ) {
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
    // /Users/vk/Sql.g:148:1: sum : ( 'sum' | 'SUM' ) ;
    public final void sum() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:148:6: ( ( 'sum' | 'SUM' ) )
            // /Users/vk/Sql.g:148:7: ( 'sum' | 'SUM' )
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
    // /Users/vk/Sql.g:149:1: asc : ( 'asc' | 'ASC' ) ;
    public final void asc() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:149:6: ( ( 'asc' | 'ASC' ) )
            // /Users/vk/Sql.g:149:7: ( 'asc' | 'ASC' )
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
    // $ANTLR end "asc"


    // $ANTLR start "desc"
    // /Users/vk/Sql.g:150:1: desc : ( 'desc' | 'DESC' ) ;
    public final void desc() throws RecognitionException {
        try {
            // /Users/vk/Sql.g:150:7: ( ( 'desc' | 'DESC' ) )
            // /Users/vk/Sql.g:150:8: ( 'desc' | 'DESC' )
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
    // $ANTLR end "desc"

    public static class between_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "between"
    // /Users/vk/Sql.g:151:1: between : ( 'between' | 'BETWEEN' ) ;
    public final SqlParser.between_return between() throws RecognitionException {
        SqlParser.between_return retval = new SqlParser.between_return();
        retval.start = input.LT(1);

        try {
            // /Users/vk/Sql.g:151:10: ( ( 'between' | 'BETWEEN' ) )
            // /Users/vk/Sql.g:151:11: ( 'between' | 'BETWEEN' )
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

    // Delegated rules


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA3 dfa3 = new DFA3(this);
    protected DFA5 dfa5 = new DFA5(this);
    protected DFA7 dfa7 = new DFA7(this);
    protected DFA8 dfa8 = new DFA8(this);
    protected DFA9 dfa9 = new DFA9(this);
    protected DFA10 dfa10 = new DFA10(this);
    protected DFA11 dfa11 = new DFA11(this);
    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA1_eotS =
        "\u0344\uffff";
    static final String DFA1_eofS =
        "\3\uffff\1\12\3\uffff\1\12\10\uffff\2\12\1\uffff\1\12\40\uffff\1"+
        "\12\1\uffff\2\12\7\uffff\2\147\14\uffff\2\147\12\uffff\1\12\5\uffff"+
        "\1\147\10\uffff\1\147\5\uffff\1\147\4\uffff\1\12\4\uffff\1\147\1"+
        "\uffff\3\147\1\uffff\2\147\15\uffff\5\147\2\uffff\1\12\7\uffff\1"+
        "\147\1\uffff\1\147\24\uffff\1\147\1\uffff\1\147\6\uffff\6\147\5"+
        "\uffff\2\147\12\uffff\2\147\15\uffff\6\147\10\uffff\1\147\7\uffff"+
        "\1\147\5\uffff\1\147\14\uffff\1\147\4\uffff\5\147\4\uffff\2\147"+
        "\5\uffff\3\147\4\uffff\7\147\11\uffff\1\147\4\uffff\5\147\12\uffff"+
        "\1\147\1\uffff\1\147\3\uffff\1\147\1\uffff\1\147\1\uffff\1\147\16"+
        "\uffff\4\147\3\uffff\6\147\3\uffff\11\147\10\uffff\4\147\10\uffff"+
        "\1\147\3\uffff\1\147\2\uffff\1\147\21\uffff\4\147\2\uffff\5\147"+
        "\2\uffff\10\147\4\uffff\2\147\12\uffff\4\147\20\uffff\1\147\12\uffff"+
        "\4\147\2\uffff\4\147\2\uffff\6\147\3\uffff\3\147\4\uffff\4\147\17"+
        "\uffff\1\147\11\uffff\4\147\2\uffff\4\147\2\uffff\6\147\2\uffff"+
        "\3\147\4\uffff\4\147\30\uffff\4\147\2\uffff\4\147\2\uffff\6\147"+
        "\2\uffff\2\147\4\uffff\4\147\30\uffff\4\147\2\uffff\4\147\2\uffff"+
        "\6\147\2\uffff\2\147\4\uffff\4\147\30\uffff\3\147\2\uffff\4\147"+
        "\2\uffff\6\147\2\uffff\2\147\3\uffff\3\147\23\uffff\1\147\2\uffff"+
        "\4\147\2\uffff\6\147\2\uffff\2\147\2\uffff\1\147\17\uffff\3\147"+
        "\1\uffff\5\147\2\uffff\2\147\12\uffff\3\147\2\uffff\2\147\5\uffff"+
        "\2\147\1\uffff\1\147";
    static final String DFA1_minS =
        "\1\122\5\4\1\26\2\4\2\uffff\22\4\1\6\1\26\11\4\1\10\1\26\13\4\1"+
        "\26\5\4\1\6\32\4\1\6\3\4\1\26\3\4\1\7\1\4\3\7\1\10\2\4\2\uffff\1"+
        "\4\1\7\3\4\1\7\3\4\1\26\3\4\1\7\7\4\1\7\2\4\5\7\26\4\1\7\1\4\1\7"+
        "\1\4\1\7\1\26\11\4\1\10\5\4\1\7\1\4\1\7\1\4\1\7\3\4\1\7\1\4\1\7"+
        "\10\4\1\10\32\4\1\6\17\4\2\7\1\4\5\7\1\10\1\7\1\4\1\7\3\4\1\7\1"+
        "\4\3\7\1\10\5\4\1\26\2\7\1\4\3\7\6\4\1\10\1\6\1\4\1\7\2\4\5\7\16"+
        "\4\7\7\21\4\5\7\1\4\1\7\1\4\1\7\1\4\1\7\1\4\1\7\1\4\1\7\1\4\1\7"+
        "\10\4\5\7\4\4\1\10\1\6\1\10\23\4\1\10\1\7\1\4\1\7\13\4\4\7\1\4\3"+
        "\7\1\4\2\7\1\4\5\7\1\10\6\4\5\7\4\4\1\10\1\6\17\4\1\10\1\6\1\4\1"+
        "\7\2\4\7\7\13\4\14\7\6\4\5\7\4\4\1\10\1\6\14\4\1\10\1\6\1\10\3\4"+
        "\2\7\12\4\13\7\5\4\5\7\4\4\1\10\1\6\14\4\1\10\1\6\3\4\2\7\12\4\13"+
        "\7\4\4\5\7\4\4\1\10\1\6\14\4\1\10\1\6\2\4\2\7\12\4\13\7\4\4\5\7"+
        "\4\4\1\10\1\6\14\4\1\10\1\6\2\4\2\7\12\4\1\23\12\7\4\4\1\23\1\7"+
        "\2\23\1\7\3\4\1\10\1\6\14\4\1\10\1\6\2\4\2\7\7\4\1\23\11\7\4\4\2"+
        "\23\1\4\1\10\1\6\14\4\1\10\1\6\2\4\2\7\2\4\1\23\1\7\2\23\1\7\1\23"+
        "\3\7\4\4\1\10\11\4\1\10\1\6\2\4\1\23\1\7\3\23\2\7\6\4\1\10\1\6\2"+
        "\4\2\23\1\7\1\4\1\10\2\4\1\23\1\4";
    static final String DFA1_maxS =
        "\1\125\2\145\1\131\2\17\1\121\1\131\1\145\2\uffff\1\145\1\17\1\50"+
        "\1\17\1\50\2\131\1\145\1\131\2\17\1\145\1\153\2\17\1\50\1\20\1\50"+
        "\1\6\1\121\1\17\1\50\1\17\1\50\1\153\1\17\3\23\1\10\1\121\3\23\1"+
        "\141\1\142\1\17\1\50\1\17\1\50\1\20\1\131\1\111\2\131\1\50\1\20"+
        "\1\50\1\6\1\17\2\23\2\135\6\23\2\153\1\23\2\127\1\23\2\135\1\141"+
        "\1\23\1\142\1\23\1\50\1\20\1\50\1\6\2\20\1\131\1\111\1\23\2\20\1"+
        "\23\1\135\3\23\1\10\2\145\2\uffff\1\135\1\23\1\127\1\23\1\127\1"+
        "\23\1\135\1\20\1\153\1\111\1\20\1\131\1\20\1\23\1\20\1\23\1\135"+
        "\1\20\3\135\1\23\2\135\5\23\1\145\1\153\2\17\3\127\1\23\5\135\2"+
        "\20\1\131\3\20\1\23\2\20\1\23\1\135\1\23\1\135\1\23\1\121\1\153"+
        "\2\23\1\141\1\142\4\23\1\10\2\17\1\50\1\17\1\50\1\23\1\127\1\23"+
        "\1\135\1\23\1\135\1\20\1\153\1\23\1\20\1\23\1\20\6\135\2\13\2\153"+
        "\1\23\2\135\1\141\1\23\1\142\2\23\2\127\3\23\2\135\4\23\1\17\1\23"+
        "\1\50\1\20\1\50\1\6\3\127\6\135\6\20\2\23\1\135\1\23\1\7\3\23\1"+
        "\10\1\23\1\135\1\23\1\127\1\23\1\127\1\23\1\135\3\23\1\10\1\23\3"+
        "\20\1\153\1\111\2\23\1\135\3\23\1\20\5\135\3\13\1\23\2\135\5\23"+
        "\3\135\3\127\1\23\7\135\7\23\1\20\1\23\1\135\2\20\2\127\5\135\5"+
        "\20\3\23\1\7\1\23\1\135\1\23\1\135\1\23\1\127\1\23\1\135\1\23\1"+
        "\135\1\23\1\135\1\23\3\20\1\23\3\20\1\153\5\23\4\135\3\13\6\135"+
        "\3\127\11\135\2\13\1\23\1\20\1\23\1\20\2\127\4\135\4\20\4\23\1\135"+
        "\3\23\1\135\2\23\1\135\1\23\1\7\3\23\1\10\6\20\5\23\4\135\2\13\5"+
        "\135\2\127\10\135\3\13\1\23\2\135\7\23\1\20\2\127\4\135\4\20\12"+
        "\23\1\7\1\23\1\135\5\20\5\23\4\135\2\13\4\135\2\127\6\135\3\13\3"+
        "\135\2\23\2\127\4\135\4\20\13\23\1\135\4\20\5\23\4\135\2\13\4\135"+
        "\2\127\6\135\2\13\3\135\2\23\2\127\4\135\4\20\13\23\4\20\5\23\4"+
        "\135\2\13\4\135\2\127\6\135\2\13\2\135\2\23\2\127\4\135\4\20\13"+
        "\23\4\20\5\23\4\135\2\13\4\135\2\127\6\135\2\13\2\135\2\23\2\127"+
        "\4\135\4\20\13\23\4\20\5\23\3\135\2\13\4\135\2\127\6\135\2\13\2"+
        "\135\2\23\1\127\3\135\3\20\12\23\4\20\2\23\1\135\2\13\4\135\2\127"+
        "\6\135\2\13\2\135\2\23\1\135\1\20\11\23\4\20\1\13\3\135\1\127\5"+
        "\135\2\13\2\135\7\23\3\20\3\135\2\13\2\135\3\23\1\20\1\13\2\135"+
        "\1\23\1\135";
    static final String DFA1_acceptS =
        "\11\uffff\1\3\1\2\133\uffff\1\4\1\1\u02dc\uffff";
    static final String DFA1_specialS =
        "\u0344\uffff}>";
    static final String[] DFA1_transitionS = {
            "\4\1",
            "\1\2\17\uffff\25\3\21\uffff\1\4\50\uffff\1\4\2\5",
            "\1\2\17\uffff\25\3\21\uffff\1\4\50\uffff\1\4\2\5",
            "\1\7\1\10\1\6\12\uffff\2\13\105\uffff\2\11",
            "\1\14\12\uffff\1\15",
            "\1\16\12\uffff\1\17",
            "\3\21\20\uffff\41\21\10\20",
            "\1\7\1\10\13\uffff\2\13\105\uffff\2\11",
            "\1\22\17\uffff\25\23\21\uffff\1\24\50\uffff\1\24\2\25",
            "",
            "",
            "\1\26\17\uffff\25\27\21\uffff\1\30\50\uffff\1\30\2\31",
            "\1\14\12\uffff\1\15",
            "\1\32\17\uffff\25\33",
            "\1\16\12\uffff\1\17",
            "\1\34\17\uffff\25\35",
            "\1\7\1\10\13\uffff\2\13\105\uffff\2\11",
            "\1\7\1\10\13\uffff\2\13\105\uffff\2\11",
            "\1\22\17\uffff\25\23\21\uffff\1\24\50\uffff\1\24\2\25",
            "\1\7\1\10\1\36\12\uffff\2\13\105\uffff\2\11",
            "\1\37\12\uffff\1\40",
            "\1\41\12\uffff\1\42",
            "\1\26\17\uffff\25\27\21\uffff\1\30\50\uffff\1\30\2\31",
            "\1\43\1\uffff\1\51\1\uffff\1\45\1\46\1\47\1\50\7\uffff\1\44"+
            "\112\uffff\1\44\1\55\1\56\1\53\1\54\7\uffff\2\52",
            "\1\57\12\uffff\1\60",
            "\1\61\12\uffff\1\62",
            "\1\32\17\uffff\25\33",
            "\1\63\13\uffff\1\64",
            "\1\34\17\uffff\25\35",
            "\1\65",
            "\3\66\20\uffff\41\66\10\67",
            "\1\37\12\uffff\1\40",
            "\1\70\17\uffff\25\71",
            "\1\41\12\uffff\1\42",
            "\1\72\17\uffff\25\73",
            "\1\43\3\uffff\1\45\1\46\1\47\1\50\7\uffff\1\44\112\uffff\1"+
            "\44\1\55\1\56\1\53\1\54\7\uffff\2\52",
            "\1\74\12\uffff\1\75",
            "\1\76\2\uffff\1\77\1\uffff\1\101\1\102\10\uffff\1\100",
            "\1\76\2\uffff\1\77\1\103\1\uffff\1\104\10\uffff\1\100",
            "\1\76\2\uffff\1\77\1\105\12\uffff\1\100",
            "\1\106",
            "\3\110\20\uffff\41\110\10\107",
            "\1\111\2\uffff\1\112\13\uffff\1\113",
            "\1\114\2\uffff\1\115\13\uffff\1\116",
            "\1\114\2\uffff\1\115\13\uffff\1\116",
            "\1\117\134\uffff\1\120",
            "\1\121\135\uffff\1\122",
            "\1\57\12\uffff\1\60",
            "\1\123\17\uffff\25\124",
            "\1\61\12\uffff\1\62",
            "\1\125\17\uffff\25\126",
            "\1\63\13\uffff\1\64",
            "\1\7\1\10\13\uffff\2\13\105\uffff\2\11",
            "\3\127\20\uffff\41\127",
            "\1\7\1\10\13\uffff\2\13\105\uffff\2\11",
            "\1\7\1\10\13\uffff\2\13\105\uffff\2\11",
            "\1\70\17\uffff\25\71",
            "\1\130\13\uffff\1\131",
            "\1\72\17\uffff\25\73",
            "\1\132",
            "\1\74\12\uffff\1\75",
            "\1\133\2\uffff\1\134\13\uffff\1\135",
            "\1\76\2\uffff\1\77\13\uffff\1\100",
            "\1\137\1\uffff\1\136\1\uffff\1\140\1\141\1\142\1\143\112\uffff"+
            "\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\140\1\141\1\142\1\143\112\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\76\2\uffff\1\77\13\uffff\1\100",
            "\1\76\2\uffff\1\77\13\uffff\1\100",
            "\1\76\2\uffff\1\77\13\uffff\1\100",
            "\1\76\2\uffff\1\77\13\uffff\1\100",
            "\1\76\2\uffff\1\77\13\uffff\1\100",
            "\1\76\2\uffff\1\77\13\uffff\1\100",
            "\1\43\3\uffff\1\45\1\46\1\47\1\50\7\uffff\1\44\112\uffff\1"+
            "\44\1\55\1\56\1\53\1\54\7\uffff\2\52",
            "\1\43\3\uffff\1\45\1\46\1\47\1\50\7\uffff\1\44\112\uffff\1"+
            "\44\1\55\1\56\1\53\1\54\7\uffff\2\52",
            "\1\111\2\uffff\1\112\13\uffff\1\113",
            "\1\152\1\uffff\1\151\117\uffff\2\153",
            "\1\154\121\uffff\2\153",
            "\1\114\2\uffff\1\115\13\uffff\1\116",
            "\1\156\1\uffff\1\155\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\117\134\uffff\1\120",
            "\1\114\2\uffff\1\115\13\uffff\1\116",
            "\1\121\135\uffff\1\122",
            "\1\114\2\uffff\1\115\13\uffff\1\116",
            "\1\123\17\uffff\25\124",
            "\1\157\13\uffff\1\160",
            "\1\125\17\uffff\25\126",
            "\1\161",
            "\1\162\13\uffff\1\163",
            "\1\130\13\uffff\1\131",
            "\1\7\1\10\13\uffff\2\13\105\uffff\2\11",
            "\3\164\20\uffff\41\164",
            "\1\133\2\uffff\1\134\13\uffff\1\135",
            "\1\166\1\167\1\165\11\uffff\1\170",
            "\1\171\1\167\12\uffff\1\170",
            "\1\173\13\uffff\1\172",
            "\1\150\2\uffff\1\174\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\176\1\uffff\1\u0080\1\175\10\uffff\1\177",
            "\1\176\1\u0081\1\uffff\1\u0082\10\uffff\1\177",
            "\1\176\1\u0083\12\uffff\1\177",
            "\1\u0084",
            "\1\u0085\17\uffff\25\u0086\21\uffff\1\u0087\50\uffff\1\u0087"+
            "\2\u0088",
            "\1\u0085\17\uffff\25\u0086\21\uffff\1\u0087\50\uffff\1\u0087"+
            "\2\u0088",
            "",
            "",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u008a\13\uffff\1\u0089",
            "\1\154\2\uffff\1\u008b\116\uffff\2\153",
            "\1\u008c\2\uffff\1\u008d\13\uffff\1\u008e",
            "\1\154\121\uffff\2\153",
            "\1\u0090\13\uffff\1\u008f",
            "\1\150\2\uffff\1\u0091\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\157\13\uffff\1\160",
            "\1\43\3\uffff\1\45\1\46\1\47\1\50\7\uffff\1\44\112\uffff\1"+
            "\44\1\55\1\56\1\53\1\54\7\uffff\2\52",
            "\3\u0092\20\uffff\41\u0092",
            "\1\162\13\uffff\1\163",
            "\1\7\1\10\13\uffff\2\13\105\uffff\2\11",
            "\1\u0093\13\uffff\1\u0094",
            "\1\u0096\13\uffff\1\u0095",
            "\1\171\1\167\1\uffff\1\u0097\10\uffff\1\170",
            "\1\u0098\2\uffff\1\u0099\13\uffff\1\u009a",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\171\1\167\12\uffff\1\170",
            "\1\150\3\uffff\1\140\1\141\1\142\1\143\112\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\1\uffff\1\u009b\1\uffff\1\140\1\141\1\142\1\143\112"+
            "\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u009c\3\uffff\1\140\1\141\1\142\1\143\112\uffff\2\144\2"+
            "\146\2\uffff\2\145",
            "\1\176\13\uffff\1\177",
            "\1\u009e\1\uffff\1\u009d\5\uffff\1\u009f\111\uffff\2\144\2"+
            "\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\176\13\uffff\1\177",
            "\1\176\13\uffff\1\177",
            "\1\176\13\uffff\1\177",
            "\1\176\13\uffff\1\177",
            "\1\176\13\uffff\1\177",
            "\1\u0085\17\uffff\25\u0086\21\uffff\1\u0087\50\uffff\1\u0087"+
            "\2\u0088",
            "\1\u00a1\1\uffff\1\u00a0\1\uffff\1\u00a7\1\u00a8\1\u00a9\1"+
            "\u00aa\7\uffff\1\u00ab\112\uffff\1\u00ab\1\u00a4\1\u00a5\1\u00a2"+
            "\1\u00a3\7\uffff\2\u00a6",
            "\1\u00ac\12\uffff\1\u00ad",
            "\1\u00ae\12\uffff\1\u00af",
            "\1\154\121\uffff\2\153",
            "\1\154\1\uffff\1\u00b0\117\uffff\2\153",
            "\1\u00b1\121\uffff\2\153",
            "\1\u008c\2\uffff\1\u008d\13\uffff\1\u008e",
            "\1\u00b3\1\uffff\1\u00b2\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u00b4\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00b5\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00b6\13\uffff\1\u00b7",
            "\1\u0093\13\uffff\1\u0094",
            "\1\7\1\10\13\uffff\2\13\105\uffff\2\11",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u00b8\11\uffff\1\170",
            "\1\u00b9\1\167\12\uffff\1\170",
            "\1\u0098\2\uffff\1\u0099\13\uffff\1\u009a",
            "\1\u00bb\1\167\1\u00ba\11\uffff\1\170",
            "\1\171\1\167\12\uffff\1\170",
            "\1\u00bd\13\uffff\1\u00bc",
            "\1\150\2\uffff\1\u00be\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00c0\13\uffff\1\u00bf",
            "\1\150\2\uffff\1\u00c1\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00c2\13\uffff\1\u00c3",
            "\3\u00c4\20\uffff\41\u00c4\10\u00c5",
            "\1\u00a1\3\uffff\1\u00a7\1\u00a8\1\u00a9\1\u00aa\7\uffff\1"+
            "\u00ab\112\uffff\1\u00ab\1\u00a4\1\u00a5\1\u00a2\1\u00a3\7\uffff"+
            "\2\u00a6",
            "\1\u00c6\2\uffff\1\u00c7\13\uffff\1\u00c8",
            "\1\u00c6\2\uffff\1\u00c7\13\uffff\1\u00c8",
            "\1\u00c9\134\uffff\1\u00ca",
            "\1\u00cb\135\uffff\1\u00cc",
            "\1\u00cd\2\uffff\1\u00ce\13\uffff\1\u00cf",
            "\1\u00d2\2\uffff\1\u00d3\1\uffff\1\u00d1\1\u00d0\10\uffff\1"+
            "\u00d4",
            "\1\u00d2\2\uffff\1\u00d3\1\u00d6\1\uffff\1\u00d5\10\uffff\1"+
            "\u00d4",
            "\1\u00d2\2\uffff\1\u00d3\1\u00d7\12\uffff\1\u00d4",
            "\1\u00d8",
            "\1\u00d9\12\uffff\1\u00da",
            "\1\u00ac\12\uffff\1\u00ad",
            "\1\u00db\17\uffff\25\u00dc",
            "\1\u00ae\12\uffff\1\u00af",
            "\1\u00dd\17\uffff\25\u00de",
            "\1\u00e0\13\uffff\1\u00df",
            "\1\154\2\uffff\1\u00e1\116\uffff\2\153",
            "\1\u00e3\13\uffff\1\u00e2",
            "\1\150\2\uffff\1\u00e4\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00e6\13\uffff\1\u00e5",
            "\1\150\2\uffff\1\u00e7\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00b6\13\uffff\1\u00b7",
            "\1\43\3\uffff\1\45\1\46\1\47\1\50\7\uffff\1\44\112\uffff\1"+
            "\44\1\55\1\56\1\53\1\54\7\uffff\2\52",
            "\1\u00e9\13\uffff\1\u00e8",
            "\1\171\1\167\1\uffff\1\u00ea\10\uffff\1\170",
            "\1\u00ec\13\uffff\1\u00eb",
            "\1\171\1\167\1\uffff\1\u00ed\10\uffff\1\170",
            "\1\150\3\uffff\1\140\1\141\1\142\1\143\112\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\1\uffff\1\u00ee\1\uffff\1\140\1\141\1\142\1\143\112"+
            "\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\140\1\141\1\142\1\143\112\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u00ef\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u00f0\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00f2\1\uffff\1\u00f1\1\uffff\1\u00f3\1\u00f4\1\u00f5\1"+
            "\u00f6",
            "\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\u00a1\3\uffff\1\u00a7\1\u00a8\1\u00a9\1\u00aa\7\uffff\1"+
            "\u00ab\112\uffff\1\u00ab\1\u00a4\1\u00a5\1\u00a2\1\u00a3\7\uffff"+
            "\2\u00a6",
            "\1\u00a1\3\uffff\1\u00a7\1\u00a8\1\u00a9\1\u00aa\7\uffff\1"+
            "\u00ab\112\uffff\1\u00ab\1\u00a4\1\u00a5\1\u00a2\1\u00a3\7\uffff"+
            "\2\u00a6",
            "\1\u00c6\2\uffff\1\u00c7\13\uffff\1\u00c8",
            "\1\u00f8\1\uffff\1\u00f7\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00c9\134\uffff\1\u00ca",
            "\1\u00c6\2\uffff\1\u00c7\13\uffff\1\u00c8",
            "\1\u00cb\135\uffff\1\u00cc",
            "\1\u00c6\2\uffff\1\u00c7\13\uffff\1\u00c8",
            "\1\u00cd\2\uffff\1\u00ce\13\uffff\1\u00cf",
            "\1\u00fa\1\uffff\1\u00f9\117\uffff\2\u00fb",
            "\1\u00fc\121\uffff\2\u00fb",
            "\1\u00d2\2\uffff\1\u00d3\13\uffff\1\u00d4",
            "\1\u00d2\2\uffff\1\u00d3\13\uffff\1\u00d4",
            "\1\u00d2\2\uffff\1\u00d3\13\uffff\1\u00d4",
            "\1\u00fe\1\uffff\1\u00fd\1\uffff\1\u00ff\1\u0100\1\u0101\1"+
            "\u0102\112\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\u00ff\1\u0100\1\u0101\1\u0102\112\uffff\2"+
            "\144\2\146\2\uffff\2\145",
            "\1\u00d2\2\uffff\1\u00d3\13\uffff\1\u00d4",
            "\1\u00d2\2\uffff\1\u00d3\13\uffff\1\u00d4",
            "\1\u00d2\2\uffff\1\u00d3\13\uffff\1\u00d4",
            "\1\u00d2\2\uffff\1\u00d3\13\uffff\1\u00d4",
            "\1\u00d9\12\uffff\1\u00da",
            "\1\u0103\2\uffff\1\u0104\13\uffff\1\u0105",
            "\1\u00db\17\uffff\25\u00dc",
            "\1\u0106\13\uffff\1\u0107",
            "\1\u00dd\17\uffff\25\u00de",
            "\1\u0108",
            "\1\154\121\uffff\2\153",
            "\1\154\1\uffff\1\u0109\117\uffff\2\153",
            "\1\154\121\uffff\2\153",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u010a\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u010b\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u010c\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u010d\11\uffff\1\170",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u010e\11\uffff\1\170",
            "\1\u010f\1\167\12\uffff\1\170",
            "\1\u0111\13\uffff\1\u0110",
            "\1\u0113\13\uffff\1\u0112",
            "\1\150\2\uffff\1\u0114\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u0116\13\uffff\1\u0115",
            "\1\u0117",
            "\1\u0119\1\uffff\1\u0118\1\u011b\10\uffff\1\u011a",
            "\1\u0119\1\u011c\1\uffff\1\u011d\10\uffff\1\u011a",
            "\1\u0119\1\u011e\12\uffff\1\u011a",
            "\1\u011f",
            "\1\u0121\13\uffff\1\u0120",
            "\1\150\2\uffff\1\u0122\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u0124\13\uffff\1\u0123",
            "\1\u00fc\2\uffff\1\u0125\116\uffff\2\u00fb",
            "\1\u0126\2\uffff\1\u0127\13\uffff\1\u0128",
            "\1\u00fc\121\uffff\2\u00fb",
            "\1\u012a\13\uffff\1\u0129",
            "\1\150\2\uffff\1\u012b\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u012c\1\uffff\1\u012f\1\u012e\10\uffff\1\u012d",
            "\1\u012c\1\u0131\1\uffff\1\u0130\10\uffff\1\u012d",
            "\1\u012c\1\u0132\12\uffff\1\u012d",
            "\1\u0133",
            "\1\u0103\2\uffff\1\u0104\13\uffff\1\u0105",
            "\1\u0135\1\u0136\1\u0134\11\uffff\1\u0137",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0106\13\uffff\1\u0107",
            "\1\u00a1\3\uffff\1\u00a7\1\u00a8\1\u00a9\1\u00aa\7\uffff\1"+
            "\u00ab\112\uffff\1\u00ab\1\u00a4\1\u00a5\1\u00a2\1\u00a3\7\uffff"+
            "\2\u00a6",
            "\3\u0139\20\uffff\41\u0139",
            "\1\u013b\13\uffff\1\u013a",
            "\1\u013d\13\uffff\1\u013c",
            "\1\150\2\uffff\1\u013e\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u0140\13\uffff\1\u013f",
            "\1\u0142\13\uffff\1\u0141",
            "\1\u0144\13\uffff\1\u0143",
            "\1\171\1\167\1\uffff\1\u0145\10\uffff\1\170",
            "\1\150\3\uffff\1\140\1\141\1\142\1\143\112\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\1\uffff\1\u0146\1\uffff\1\140\1\141\1\142\1\143\112"+
            "\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0147\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\u0148\1\uffff\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\u0149\3\uffff\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\u0119\13\uffff\1\u011a",
            "\1\u014b\1\uffff\1\u014a\5\uffff\1\u009f\111\uffff\2\144\2"+
            "\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u0119\13\uffff\1\u011a",
            "\1\u0119\13\uffff\1\u011a",
            "\1\u0119\13\uffff\1\u011a",
            "\1\u0119\13\uffff\1\u011a",
            "\1\u0119\13\uffff\1\u011a",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u014c\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u014d\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00fc\121\uffff\2\u00fb",
            "\1\u00fc\1\uffff\1\u014e\117\uffff\2\u00fb",
            "\1\u014f\121\uffff\2\u00fb",
            "\1\u0126\2\uffff\1\u0127\13\uffff\1\u0128",
            "\1\u0151\1\uffff\1\u0150\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\u00ff\1\u0100\1\u0101\1\u0102\112\uffff\2"+
            "\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0152\1\uffff\1\u00ff\1\u0100\1\u0101\1\u0102"+
            "\112\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u0153\3\uffff\1\u00ff\1\u0100\1\u0101\1\u0102\112\uffff"+
            "\2\144\2\146\2\uffff\2\145",
            "\1\u0155\1\uffff\1\u0154\5\uffff\1\u0156\111\uffff\2\144\2"+
            "\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u012c\13\uffff\1\u012d",
            "\1\u012c\13\uffff\1\u012d",
            "\1\u012c\13\uffff\1\u012d",
            "\1\u012c\13\uffff\1\u012d",
            "\1\u012c\13\uffff\1\u012d",
            "\1\u012c\13\uffff\1\u012d",
            "\1\u0158\13\uffff\1\u0157",
            "\1\u0138\1\u0136\1\uffff\1\u0159\10\uffff\1\u0137",
            "\1\u015a\2\uffff\1\u015b\13\uffff\1\u015c",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u015d\13\uffff\1\u015e",
            "\1\154\121\uffff\2\153",
            "\1\154\1\uffff\1\u015f\117\uffff\2\153",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0160\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0161\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u0162\11\uffff\1\170",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u0163\11\uffff\1\170",
            "\1\171\1\167\12\uffff\1\170",
            "\1\u0165\13\uffff\1\u0164",
            "\1\u0167\13\uffff\1\u0166",
            "\1\u0169\13\uffff\1\u0168",
            "\1\u016a",
            "\1\u016c\13\uffff\1\u016b",
            "\1\150\2\uffff\1\u016d\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u016f\13\uffff\1\u016e",
            "\1\150\2\uffff\1\u0170\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u0172\13\uffff\1\u0171",
            "\1\u00fc\2\uffff\1\u0173\116\uffff\2\u00fb",
            "\1\u0175\13\uffff\1\u0174",
            "\1\150\2\uffff\1\u0176\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u0178\13\uffff\1\u0177",
            "\1\150\2\uffff\1\u0179\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u017b\13\uffff\1\u017a",
            "\1\150\2\uffff\1\u017c\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u017d\13\uffff\1\u017e",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u017f\11\uffff\1\u0137",
            "\1\u0180\1\u0136\12\uffff\1\u0137",
            "\1\u015a\2\uffff\1\u015b\13\uffff\1\u015c",
            "\1\u0182\1\u0136\1\u0181\11\uffff\1\u0137",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u015d\13\uffff\1\u015e",
            "\1\u00a1\3\uffff\1\u00a7\1\u00a8\1\u00a9\1\u00aa\7\uffff\1"+
            "\u00ab\112\uffff\1\u00ab\1\u00a4\1\u00a5\1\u00a2\1\u00a3\7\uffff"+
            "\2\u00a6",
            "\1\u0184\13\uffff\1\u0183",
            "\1\u0186\13\uffff\1\u0185",
            "\1\u0188\13\uffff\1\u0187",
            "\1\u018a\13\uffff\1\u0189",
            "\1\u018c\13\uffff\1\u018b",
            "\1\150\3\uffff\1\140\1\141\1\142\1\143\112\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\1\uffff\1\u018d\1\uffff\1\140\1\141\1\142\1\143\112"+
            "\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u018e\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\u018f\1\uffff\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0190\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u0191\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0192\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00fc\121\uffff\2\u00fb",
            "\1\u00fc\1\uffff\1\u0193\117\uffff\2\u00fb",
            "\1\u00fc\121\uffff\2\u00fb",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0194\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u0195\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\u00ff\1\u0100\1\u0101\1\u0102\112\uffff\2"+
            "\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0196\1\uffff\1\u00ff\1\u0100\1\u0101\1\u0102"+
            "\112\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\u00ff\1\u0100\1\u0101\1\u0102\112\uffff\2"+
            "\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0197\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u0198\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u019a\1\uffff\1\u0199\1\uffff\1\u019b\1\u019c\1\u019d\1"+
            "\u019e",
            "\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\u01a0\13\uffff\1\u019f",
            "\1\u0138\1\u0136\1\uffff\1\u01a1\10\uffff\1\u0137",
            "\1\u01a3\13\uffff\1\u01a2",
            "\1\u0138\1\u0136\1\uffff\1\u01a4\10\uffff\1\u0137",
            "\1\154\121\uffff\2\153",
            "\1\154\1\uffff\1\u01a5\117\uffff\2\153",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u01a6\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u01a7\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u01a8\11\uffff\1\170",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u01a9\11\uffff\1\170",
            "\1\u01ab\13\uffff\1\u01aa",
            "\1\u01ad\13\uffff\1\u01ac",
            "\1\u01af\13\uffff\1\u01ae",
            "\1\u01b1\13\uffff\1\u01b0",
            "\1\150\2\uffff\1\u01b2\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u01b4\13\uffff\1\u01b3",
            "\1\u01b6\13\uffff\1\u01b5",
            "\1\u01b8\13\uffff\1\u01b7",
            "\1\150\2\uffff\1\u01b9\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u01bb\13\uffff\1\u01ba",
            "\1\u01bd\13\uffff\1\u01bc",
            "\1\150\2\uffff\1\u01be\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u01c0\13\uffff\1\u01bf",
            "\1\u01c1",
            "\1\u01c3\1\uffff\1\u01c5\1\u01c2\10\uffff\1\u01c4",
            "\1\u01c3\1\u01c7\1\uffff\1\u01c6\10\uffff\1\u01c4",
            "\1\u01c3\1\u01c8\12\uffff\1\u01c4",
            "\1\u01c9",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u01ca\11\uffff\1\u0137",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u01cb\11\uffff\1\u0137",
            "\1\u01cc\1\u0136\12\uffff\1\u0137",
            "\1\u01ce\13\uffff\1\u01cd",
            "\1\u01d0\13\uffff\1\u01cf",
            "\1\u01d2\13\uffff\1\u01d1",
            "\1\u01d4\13\uffff\1\u01d3",
            "\1\u01d6\13\uffff\1\u01d5",
            "\1\150\3\uffff\1\140\1\141\1\142\1\143\112\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\1\uffff\1\u01d7\1\uffff\1\140\1\141\1\142\1\143\112"+
            "\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u01d8\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\u01d9\1\uffff\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u01da\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u01db\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00fc\121\uffff\2\u00fb",
            "\1\u00fc\1\uffff\1\u01dc\117\uffff\2\u00fb",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u01dd\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\u00ff\1\u0100\1\u0101\1\u0102\112\uffff\2"+
            "\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u01de\1\uffff\1\u00ff\1\u0100\1\u0101\1\u0102"+
            "\112\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u01df\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\u01e0\1\uffff\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\u01e1\3\uffff\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\u01c3\13\uffff\1\u01c4",
            "\1\u01e3\1\uffff\1\u01e2\5\uffff\1\u0156\111\uffff\2\144\2"+
            "\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u01c3\13\uffff\1\u01c4",
            "\1\u01c3\13\uffff\1\u01c4",
            "\1\u01c3\13\uffff\1\u01c4",
            "\1\u01c3\13\uffff\1\u01c4",
            "\1\u01c3\13\uffff\1\u01c4",
            "\1\u01e5\13\uffff\1\u01e4",
            "\1\u01e7\13\uffff\1\u01e6",
            "\1\u0138\1\u0136\1\uffff\1\u01e8\10\uffff\1\u0137",
            "\1\154\121\uffff\2\153",
            "\1\154\1\uffff\1\u01e9\117\uffff\2\153",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u01ea\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u01eb\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u01ec\11\uffff\1\170",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u01ed\11\uffff\1\170",
            "\1\u01ef\13\uffff\1\u01ee",
            "\1\u01f1\13\uffff\1\u01f0",
            "\1\u01f3\13\uffff\1\u01f2",
            "\1\u01f5\13\uffff\1\u01f4",
            "\1\u01f7\13\uffff\1\u01f6",
            "\1\u01f9\13\uffff\1\u01f8",
            "\1\u01fb\13\uffff\1\u01fa",
            "\1\u01fd\13\uffff\1\u01fc",
            "\1\u01ff\13\uffff\1\u01fe",
            "\1\u0201\13\uffff\1\u0200",
            "\1\u0202",
            "\1\u0204\13\uffff\1\u0203",
            "\1\150\2\uffff\1\u0205\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u0206\11\uffff\1\u0137",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u0207\11\uffff\1\u0137",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0209\13\uffff\1\u0208",
            "\1\u020b\13\uffff\1\u020a",
            "\1\u020d\13\uffff\1\u020c",
            "\1\u020f\13\uffff\1\u020e",
            "\1\u0211\13\uffff\1\u0210",
            "\1\150\3\uffff\1\140\1\141\1\142\1\143\112\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\1\uffff\1\u0212\1\uffff\1\140\1\141\1\142\1\143\112"+
            "\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0213\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\u0214\1\uffff\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0215\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0216\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00fc\121\uffff\2\u00fb",
            "\1\u00fc\1\uffff\1\u0217\117\uffff\2\u00fb",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0218\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\u00ff\1\u0100\1\u0101\1\u0102\112\uffff\2"+
            "\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0219\1\uffff\1\u00ff\1\u0100\1\u0101\1\u0102"+
            "\112\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u021a\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\u021b\1\uffff\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u021c\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u021d\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u021f\13\uffff\1\u021e",
            "\1\u0221\13\uffff\1\u0220",
            "\1\154\121\uffff\2\153",
            "\1\154\1\uffff\1\u0222\117\uffff\2\153",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0223\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0224\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u0225\11\uffff\1\170",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u0226\11\uffff\1\170",
            "\1\u0228\13\uffff\1\u0227",
            "\1\u022a\13\uffff\1\u0229",
            "\1\u022c\13\uffff\1\u022b",
            "\1\u022e\13\uffff\1\u022d",
            "\1\u0230\13\uffff\1\u022f",
            "\1\u0232\13\uffff\1\u0231",
            "\1\u0234\13\uffff\1\u0233",
            "\1\u0236\13\uffff\1\u0235",
            "\1\u0238\13\uffff\1\u0237",
            "\1\u023a\13\uffff\1\u0239",
            "\1\u023c\13\uffff\1\u023b",
            "\1\150\2\uffff\1\u023d\116\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u023e\11\uffff\1\u0137",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u023f\11\uffff\1\u0137",
            "\1\u0241\13\uffff\1\u0240",
            "\1\u0243\13\uffff\1\u0242",
            "\1\u0245\13\uffff\1\u0244",
            "\1\u0247\13\uffff\1\u0246",
            "\1\u0249\13\uffff\1\u0248",
            "\1\150\3\uffff\1\140\1\141\1\142\1\143\112\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\1\uffff\1\u024a\1\uffff\1\140\1\141\1\142\1\143\112"+
            "\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u024b\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\u024c\1\uffff\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u024d\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u024e\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00fc\121\uffff\2\u00fb",
            "\1\u00fc\1\uffff\1\u024f\117\uffff\2\u00fb",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0250\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\u00ff\1\u0100\1\u0101\1\u0102\112\uffff\2"+
            "\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0251\1\uffff\1\u00ff\1\u0100\1\u0101\1\u0102"+
            "\112\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0252\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\u0253\1\uffff\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0254\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u0256\13\uffff\1\u0255",
            "\1\u0258\13\uffff\1\u0257",
            "\1\154\121\uffff\2\153",
            "\1\154\1\uffff\1\u0259\117\uffff\2\153",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u025a\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u025b\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u025c\11\uffff\1\170",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u025d\11\uffff\1\170",
            "\1\u025f\13\uffff\1\u025e",
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
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u0274\11\uffff\1\u0137",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u0275\11\uffff\1\u0137",
            "\1\u0277\13\uffff\1\u0276",
            "\1\u0279\13\uffff\1\u0278",
            "\1\u027b\13\uffff\1\u027a",
            "\1\u027d\13\uffff\1\u027c",
            "\1\u027f\13\uffff\1\u027e",
            "\1\150\3\uffff\1\140\1\141\1\142\1\143\112\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\1\uffff\1\u0280\1\uffff\1\140\1\141\1\142\1\143\112"+
            "\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0281\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\u0282\1\uffff\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0283\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0284\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00fc\121\uffff\2\u00fb",
            "\1\u00fc\1\uffff\1\u0285\117\uffff\2\u00fb",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0286\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\u00ff\1\u0100\1\u0101\1\u0102\112\uffff\2"+
            "\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0287\1\uffff\1\u00ff\1\u0100\1\u0101\1\u0102"+
            "\112\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0288\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\u0289\1\uffff\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u028a\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u028c\13\uffff\1\u028b",
            "\1\u028e\13\uffff\1\u028d",
            "\1\154\121\uffff\2\153",
            "\1\154\1\uffff\1\u028f\117\uffff\2\153",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0290\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0291\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u0292\11\uffff\1\170",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u0293\11\uffff\1\170",
            "\1\u0295\13\uffff\1\u0294",
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
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u02aa\11\uffff\1\u0137",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u02ab\11\uffff\1\u0137",
            "\1\u02ad\13\uffff\1\u02ac",
            "\1\u02af\13\uffff\1\u02ae",
            "\1\u02b1\13\uffff\1\u02b0",
            "\1\u02b3\13\uffff\1\u02b2",
            "\1\u02b5\13\uffff\1\u02b4",
            "\1\150\3\uffff\1\140\1\141\1\142\1\143\112\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\1\uffff\1\u02b6\1\uffff\1\140\1\141\1\142\1\143\112"+
            "\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02b7\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\u02b8\1\uffff\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02b9\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02ba\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00fc\121\uffff\2\u00fb",
            "\1\u00fc\1\uffff\1\u02bb\117\uffff\2\u00fb",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02bc\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\u00ff\1\u0100\1\u0101\1\u0102\112\uffff\2"+
            "\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02bd\1\uffff\1\u00ff\1\u0100\1\u0101\1\u0102"+
            "\112\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02be\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\u02bf\1\uffff\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02c0\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u02c2\13\uffff\1\u02c1",
            "\1\u02c4\13\uffff\1\u02c3",
            "\1\154\121\uffff\2\153",
            "\1\154\1\uffff\1\u02c5\117\uffff\2\153",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02c6\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02c7\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u02c8\11\uffff\1\170",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u02c9\11\uffff\1\170",
            "\1\u02ca",
            "\1\u02cc\13\uffff\1\u02cb",
            "\1\u02ce\13\uffff\1\u02cd",
            "\1\u02d0\13\uffff\1\u02cf",
            "\1\u02d2\13\uffff\1\u02d1",
            "\1\u02d4\13\uffff\1\u02d3",
            "\1\u02d6\13\uffff\1\u02d5",
            "\1\u02d8\13\uffff\1\u02d7",
            "\1\u02da\13\uffff\1\u02d9",
            "\1\u02dc\13\uffff\1\u02db",
            "\1\u02de\13\uffff\1\u02dd",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u02df\11\uffff\1\u0137",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u02e0\11\uffff\1\u0137",
            "\1\u02e1",
            "\1\u02e3\13\uffff\1\u02e2",
            "\1\u02e4",
            "\1\u02e5",
            "\1\u02e7\13\uffff\1\u02e6",
            "\1\150\3\uffff\1\140\1\141\1\142\1\143\112\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02e8\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\u02e9\1\uffff\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02ea\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02eb\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00fc\121\uffff\2\u00fb",
            "\1\u00fc\1\uffff\1\u02ec\117\uffff\2\u00fb",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02ed\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\u00ff\1\u0100\1\u0101\1\u0102\112\uffff\2"+
            "\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02ee\1\uffff\1\u00ff\1\u0100\1\u0101\1\u0102"+
            "\112\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02ef\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\u02f0\1\uffff\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02f1\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u02f3\13\uffff\1\u02f2",
            "\1\u02f5\13\uffff\1\u02f4",
            "\1\154\121\uffff\2\153",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u02f6\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\12\uffff\1\170",
            "\1\171\1\167\1\u02f7\11\uffff\1\170",
            "\1\u02f8",
            "\1\u02fa\13\uffff\1\u02f9",
            "\1\u02fc\13\uffff\1\u02fb",
            "\1\u02fe\13\uffff\1\u02fd",
            "\1\u0300\13\uffff\1\u02ff",
            "\1\u0302\13\uffff\1\u0301",
            "\1\u0304\13\uffff\1\u0303",
            "\1\u0306\13\uffff\1\u0305",
            "\1\u0308\13\uffff\1\u0307",
            "\1\u030a\13\uffff\1\u0309",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u030b\11\uffff\1\u0137",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u030c\11\uffff\1\u0137",
            "\1\u030d",
            "\1\u030e",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\u030f\1\uffff\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0310\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0311\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00fc\121\uffff\2\u00fb",
            "\1\u00fc\1\uffff\1\u0312\117\uffff\2\u00fb",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0313\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\u00ff\1\u0100\1\u0101\1\u0102\112\uffff\2"+
            "\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0314\1\uffff\1\u00ff\1\u0100\1\u0101\1\u0102"+
            "\112\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0315\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\u0316\1\uffff\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0317\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u0319\13\uffff\1\u0318",
            "\1\u031b\13\uffff\1\u031a",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\171\1\167\12\uffff\1\170",
            "\1\u031c",
            "\1\u031e\13\uffff\1\u031d",
            "\1\u031f",
            "\1\u0320",
            "\1\u0322\13\uffff\1\u0321",
            "\1\u0323",
            "\1\u0325\13\uffff\1\u0324",
            "\1\u0327\13\uffff\1\u0326",
            "\1\u0329\13\uffff\1\u0328",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u032a\11\uffff\1\u0137",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u032b\11\uffff\1\u0137",
            "\1\u00f3\1\u00f4\1\u00f5\1\u00f6",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u032c\5\uffff\1\u009f\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u00fc\121\uffff\2\u00fb",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u032d\117\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\3\uffff\1\u00ff\1\u0100\1\u0101\1\u0102\112\uffff\2"+
            "\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u032e\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\u032f\1\uffff\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0330\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u0331",
            "\1\u0333\13\uffff\1\u0332",
            "\1\u0334",
            "\1\u0335",
            "\1\u0336",
            "\1\u0338\13\uffff\1\u0337",
            "\1\u033a\13\uffff\1\u0339",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u0138\1\u0136\1\u033b\11\uffff\1\u0137",
            "\1\150\7\uffff\1\u009f\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\121\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\u033c\1\uffff\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u033d\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u033e",
            "\1\u033f",
            "\1\u0341\13\uffff\1\u0340",
            "\1\u0138\1\u0136\12\uffff\1\u0137",
            "\1\u019b\1\u019c\1\u019d\1\u019e",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145",
            "\1\150\1\uffff\1\u0342\5\uffff\1\u0156\111\uffff\2\144\2\146"+
            "\2\uffff\2\145",
            "\1\u0343",
            "\1\150\7\uffff\1\u0156\111\uffff\2\144\2\146\2\uffff\2\145"
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
        "\2\151\2\uffff";
    static final String DFA3_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA3_specialS =
        "\4\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\3\140\uffff\4\2",
            "\1\1\1\3\140\uffff\4\2",
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
        "\2\131\2\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA5_specialS =
        "\4\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\1\1\3\13\uffff\2\2\105\uffff\2\2",
            "\1\1\1\3\13\uffff\2\2\105\uffff\2\2",
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
        "\2\135\2\uffff";
    static final String DFA7_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA7_specialS =
        "\4\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\1\121\uffff\2\3\2\2\2\uffff\2\3",
            "\1\1\121\uffff\2\3\2\2\2\uffff\2\3",
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
        "\1\24\3\4\1\26\1\4\4\uffff\11\4\1\6\2\4\1\26\3\4";
    static final String DFA8_maxS =
        "\1\145\1\153\2\17\1\121\1\153\4\uffff\1\17\1\50\1\17\1\50\2\153"+
        "\1\50\1\20\1\50\1\6\1\20\1\153\1\111\2\20\1\153";
    static final String DFA8_acceptS =
        "\6\uffff\1\4\1\2\1\3\1\1\20\uffff";
    static final String DFA8_specialS =
        "\32\uffff}>";
    static final String[] DFA8_transitionS = {
            "\25\1\21\uffff\1\2\50\uffff\1\2\2\3",
            "\1\5\1\uffff\1\4\1\uffff\4\11\7\uffff\1\7\112\uffff\1\7\4\10"+
            "\7\uffff\2\6",
            "\1\12\12\uffff\1\13",
            "\1\14\12\uffff\1\15",
            "\3\16\20\uffff\41\16\10\17",
            "\1\5\3\uffff\4\11\7\uffff\1\7\112\uffff\1\7\4\10\7\uffff\2"+
            "\6",
            "",
            "",
            "",
            "",
            "\1\12\12\uffff\1\13",
            "\1\20\17\uffff\25\21",
            "\1\14\12\uffff\1\15",
            "\1\22\17\uffff\25\23",
            "\1\5\3\uffff\4\11\7\uffff\1\7\112\uffff\1\7\4\10\7\uffff\2"+
            "\6",
            "\1\5\3\uffff\4\11\7\uffff\1\7\112\uffff\1\7\4\10\7\uffff\2"+
            "\6",
            "\1\20\17\uffff\25\21",
            "\1\24\13\uffff\1\25",
            "\1\22\17\uffff\25\23",
            "\1\26",
            "\1\24\13\uffff\1\25",
            "\1\5\3\uffff\4\11\7\uffff\1\7\112\uffff\1\7\4\10\7\uffff\2"+
            "\6",
            "\3\27\20\uffff\41\27",
            "\1\30\13\uffff\1\31",
            "\1\30\13\uffff\1\31",
            "\1\5\3\uffff\4\11\7\uffff\1\7\112\uffff\1\7\4\10\7\uffff\2"+
            "\6"
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
    static final String DFA9_eotS =
        "\56\uffff";
    static final String DFA9_eofS =
        "\1\uffff\1\5\2\uffff\1\5\2\uffff\1\12\1\14\2\uffff\1\14\2\uffff"+
        "\1\21\4\uffff\1\25\3\uffff\1\31\3\uffff\1\35\3\uffff\1\41\3\uffff"+
        "\1\45\3\uffff\1\51\3\uffff\1\55\2\uffff";
    static final String DFA9_minS =
        "\1\7\1\4\1\uffff\1\7\1\4\2\uffff\2\4\1\7\1\uffff\1\4\2\uffff\1\4"+
        "\1\uffff\1\7\2\uffff\1\4\1\7\2\uffff\1\4\1\7\2\uffff\1\4\1\7\2\uffff"+
        "\1\4\1\7\2\uffff\1\4\1\7\2\uffff\1\4\1\7\2\uffff\1\4\2\uffff";
    static final String DFA9_maxS =
        "\1\23\1\135\1\uffff\1\23\1\135\2\uffff\2\135\1\23\1\uffff\1\135"+
        "\2\uffff\1\135\1\uffff\1\23\2\uffff\1\135\1\23\2\uffff\1\135\1\23"+
        "\2\uffff\1\135\1\23\2\uffff\1\135\1\23\2\uffff\1\135\1\23\2\uffff"+
        "\1\135\1\23\2\uffff\1\135\2\uffff";
    static final String DFA9_acceptS =
        "\2\uffff\1\2\2\uffff\1\1\1\3\3\uffff\1\4\1\uffff\1\26\1\5\1\uffff"+
        "\1\27\1\uffff\1\6\1\7\2\uffff\1\10\1\11\2\uffff\1\12\1\13\2\uffff"+
        "\1\14\1\15\2\uffff\1\16\1\17\2\uffff\1\20\1\21\2\uffff\1\22\1\23"+
        "\1\uffff\1\25\1\24";
    static final String DFA9_specialS =
        "\56\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\1\13\uffff\1\2",
            "\1\4\1\5\1\3\1\uffff\5\5\3\uffff\1\5\105\uffff\4\5\2\uffff"+
            "\2\5",
            "",
            "\1\7\13\uffff\1\6",
            "\2\5\1\uffff\1\10\10\uffff\1\5\105\uffff\4\5\2\uffff\2\5",
            "",
            "",
            "\2\12\1\11\1\uffff\5\12\3\uffff\1\12\105\uffff\4\12\2\uffff"+
            "\2\12",
            "\1\13\1\14\2\uffff\5\14\3\uffff\1\14\105\uffff\4\14\2\uffff"+
            "\2\14",
            "\1\16\13\uffff\1\15",
            "",
            "\2\14\1\uffff\1\17\10\uffff\1\14\105\uffff\4\14\2\uffff\2\14",
            "",
            "",
            "\2\21\1\20\1\uffff\5\21\3\uffff\1\21\105\uffff\4\21\2\uffff"+
            "\2\21",
            "",
            "\1\23\13\uffff\1\22",
            "",
            "",
            "\2\25\1\24\1\uffff\5\25\3\uffff\1\25\105\uffff\4\25\2\uffff"+
            "\2\25",
            "\1\27\13\uffff\1\26",
            "",
            "",
            "\2\31\1\30\1\uffff\5\31\3\uffff\1\31\105\uffff\4\31\2\uffff"+
            "\2\31",
            "\1\33\13\uffff\1\32",
            "",
            "",
            "\2\35\1\34\1\uffff\5\35\3\uffff\1\35\105\uffff\4\35\2\uffff"+
            "\2\35",
            "\1\37\13\uffff\1\36",
            "",
            "",
            "\2\41\1\40\1\uffff\5\41\3\uffff\1\41\105\uffff\4\41\2\uffff"+
            "\2\41",
            "\1\43\13\uffff\1\42",
            "",
            "",
            "\2\45\1\44\1\uffff\5\45\3\uffff\1\45\105\uffff\4\45\2\uffff"+
            "\2\45",
            "\1\47\13\uffff\1\46",
            "",
            "",
            "\2\51\1\50\1\uffff\5\51\3\uffff\1\51\105\uffff\4\51\2\uffff"+
            "\2\51",
            "\1\53\13\uffff\1\52",
            "",
            "",
            "\2\55\1\54\1\uffff\5\55\3\uffff\1\55\105\uffff\4\55\2\uffff"+
            "\2\55",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "88:1: dotValue : ( VALUE | 'in' | VALUE DOT 'in' | VALUE DOT VALUE | VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE | VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in' | VALUE SPACE VALUE | VALUE SPACE VALUE SPACE VALUE );";
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
    static final String DFA11_eotS =
        "\15\uffff";
    static final String DFA11_eofS =
        "\15\uffff";
    static final String DFA11_minS =
        "\1\10\3\4\11\uffff";
    static final String DFA11_maxS =
        "\1\13\3\23\11\uffff";
    static final String DFA11_acceptS =
        "\4\uffff\1\4\1\6\1\5\1\1\1\7\1\11\1\2\1\10\1\3";
    static final String DFA11_specialS =
        "\15\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1\1\2\1\3\1\4",
            "\1\7\2\uffff\1\7\1\uffff\1\5\1\6\10\uffff\1\7",
            "\1\12\2\uffff\1\12\1\10\1\uffff\1\11\10\uffff\1\12",
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
            return "116:1: compOpt : ( ( EQ ) | ( LT ) | ( GT ) | ( NOT ) ( EQ ) | ( EQ ) ( GT ) | ( EQ ) ( LT ) | ( LT ) ( EQ ) | ( GT ) ( EQ ) | ( LT ) ( GT ) );";
        }
    }
    static final String DFA13_eotS =
        "\46\uffff";
    static final String DFA13_eofS =
        "\1\uffff\2\5\1\uffff\1\5\2\uffff\3\5\1\uffff\4\5\1\uffff\2\5\1\uffff"+
        "\2\5\1\uffff\2\5\1\uffff\2\5\1\uffff\2\5\1\uffff\2\5\1\uffff\2\5"+
        "\1\uffff\1\5";
    static final String DFA13_minS =
        "\1\7\2\4\1\7\1\4\2\uffff\3\4\1\7\4\4\1\7\2\4\1\7\2\4\1\7\2\4\1\7"+
        "\2\4\1\7\2\4\1\7\2\4\1\7\2\4\1\23\1\4";
    static final String DFA13_maxS =
        "\1\23\2\135\1\23\1\135\2\uffff\3\135\1\23\4\135\1\23\2\135\1\23"+
        "\2\135\1\23\2\135\1\23\2\135\1\23\2\135\1\23\2\135\1\23\2\135\1"+
        "\23\1\135";
    static final String DFA13_acceptS =
        "\5\uffff\1\1\1\2\37\uffff";
    static final String DFA13_specialS =
        "\46\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\1\13\uffff\1\2",
            "\1\4\1\uffff\1\3\1\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\5\3\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\10\13\uffff\1\7",
            "\1\5\2\uffff\1\11\116\uffff\4\5\2\uffff\2\5",
            "",
            "",
            "\1\5\3\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\12\1\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\13\3\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\15\13\uffff\1\14",
            "\1\5\2\uffff\1\16\116\uffff\4\5\2\uffff\2\5",
            "\1\5\3\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\17\1\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\5\3\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\21\13\uffff\1\20",
            "\1\5\3\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\22\1\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\24\13\uffff\1\23",
            "\1\5\3\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\25\1\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\27\13\uffff\1\26",
            "\1\5\3\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\30\1\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\32\13\uffff\1\31",
            "\1\5\3\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\33\1\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\35\13\uffff\1\34",
            "\1\5\3\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\36\1\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\40\13\uffff\1\37",
            "\1\5\3\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\41\1\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\43\13\uffff\1\42",
            "\1\5\3\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\5\1\uffff\1\44\1\uffff\4\6\112\uffff\4\5\2\uffff\2\5",
            "\1\45",
            "\1\5\3\uffff\4\6\112\uffff\4\5\2\uffff\2\5"
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
            return "126:1: genValue : ( dotValue | dotValue compOpt dotValue ( AMP dotValue compOpt dotValue )* );";
        }
    }
 

    public static final BitSet FOLLOW_select_in_stmt27 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_spaces_in_stmt29 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_selectList_in_stmt31 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt33 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_where_in_stmt35 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_spaces_in_stmt37 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_constraintList_in_stmt39 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_spaces_in_stmt41 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt47 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_spaces_in_stmt49 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_selectList_in_stmt51 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_spaces_in_stmt53 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt58 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_spaces_in_stmt60 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_selectList_in_stmt62 = new BitSet(new long[]{0x0000000000000010L,0x0000000003000000L});
    public static final BitSet FOLLOW_spaces_in_stmt64 = new BitSet(new long[]{0x0000000000000010L,0x0000000003000000L});
    public static final BitSet FOLLOW_order_in_stmt66 = new BitSet(new long[]{0x0000000000000010L,0x000000000C000000L});
    public static final BitSet FOLLOW_spaces_in_stmt68 = new BitSet(new long[]{0x0000000000000010L,0x000000000C000000L});
    public static final BitSet FOLLOW_by_in_stmt70 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_spaces_in_stmt72 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_orderList_in_stmt74 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_stmt79 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_spaces_in_stmt81 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_selectList_in_stmt83 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_spaces_in_stmt85 = new BitSet(new long[]{0x0000000000060010L});
    public static final BitSet FOLLOW_where_in_stmt87 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_spaces_in_stmt89 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_constraintList_in_stmt91 = new BitSet(new long[]{0x0000000000000010L,0x0000000003000000L});
    public static final BitSet FOLLOW_spaces_in_stmt93 = new BitSet(new long[]{0x0000000000000010L,0x0000000003000000L});
    public static final BitSet FOLLOW_order_in_stmt95 = new BitSet(new long[]{0x0000000000000010L,0x000000000C000000L});
    public static final BitSet FOLLOW_spaces_in_stmt97 = new BitSet(new long[]{0x0000000000000010L,0x000000000C000000L});
    public static final BitSet FOLLOW_by_in_stmt99 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_spaces_in_stmt101 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_orderList_in_stmt103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SPACE_in_spaces113 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_keyword_in_orderList125 = new BitSet(new long[]{0x0000000000000030L,0x000003C000000000L});
    public static final BitSet FOLLOW_spaces_in_orderList138 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_orderList142 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_spaces_in_orderList146 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_keyword_in_orderList153 = new BitSet(new long[]{0x0000000000000030L,0x000003C000000000L});
    public static final BitSet FOLLOW_spaces_in_orderList168 = new BitSet(new long[]{0x0000000000000000L,0x000003C000000000L});
    public static final BitSet FOLLOW_ordering_in_orderList176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_asc_in_ordering192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_desc_in_ordering194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_selectList207 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_spaces_in_selectList220 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_selectList224 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_spaces_in_selectList228 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_keyword_in_selectList235 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_entity_in_keyword260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword266 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword268 = new BitSet(new long[]{0xFFFFFE0001C00000L,0x00000000000003FFL});
    public static final BitSet FOLLOW_attr_in_keyword270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_keyword275 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword277 = new BitSet(new long[]{0x0000000000000000L,0x000000000003FC00L});
    public static final BitSet FOLLOW_funct_in_keyword279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_count_in_keyword284 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword286 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword288 = new BitSet(new long[]{0x000001FFFFF00010L});
    public static final BitSet FOLLOW_spaces_in_keyword290 = new BitSet(new long[]{0x000001FFFFF00000L});
    public static final BitSet FOLLOW_entity_in_keyword292 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword294 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sum_in_keyword301 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_keyword303 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_keyword305 = new BitSet(new long[]{0x000001FFFFF00010L});
    public static final BitSet FOLLOW_spaces_in_keyword307 = new BitSet(new long[]{0x000001FFFFF00000L});
    public static final BitSet FOLLOW_entity_in_keyword309 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_keyword311 = new BitSet(new long[]{0xFFFFFE0001C00000L,0x00000000000003FFL});
    public static final BitSet FOLLOW_attr_in_keyword313 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_keyword315 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_keyword317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_constraintList326 = new BitSet(new long[]{0x0000000000000012L,0x0000000030C00000L});
    public static final BitSet FOLLOW_spaces_in_constraintList330 = new BitSet(new long[]{0x0000000000000010L,0x0000000030C00000L});
    public static final BitSet FOLLOW_logicalOp_in_constraintList337 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_spaces_in_constraintList345 = new BitSet(new long[]{0x040001FFFFF00010L,0x0000003800000000L});
    public static final BitSet FOLLOW_constraint_in_constraintList347 = new BitSet(new long[]{0x0000000000000012L,0x0000000030C00000L});
    public static final BitSet FOLLOW_keyword_in_constraint360 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_spaces_in_constraint369 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_compOpt_in_constraint376 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint386 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_genValue_in_constraint393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint422 = new BitSet(new long[]{0x0000000000080010L,0x0000000040000000L});
    public static final BitSet FOLLOW_spaces_in_constraint431 = new BitSet(new long[]{0x0000000000080010L,0x0000000040000000L});
    public static final BitSet FOLLOW_in_in_constraint438 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_spaces_in_constraint449 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_constraint451 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint455 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_valueList_in_constraint461 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_spaces_in_constraint469 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_constraint473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint499 = new BitSet(new long[]{0x0000000000000010L,0x0000000780000000L});
    public static final BitSet FOLLOW_spaces_in_constraint508 = new BitSet(new long[]{0x0000000000000010L,0x0000000780000000L});
    public static final BitSet FOLLOW_like_in_constraint515 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint524 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_constraint531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_constraint546 = new BitSet(new long[]{0x0000000000000010L,0x00000C0000000000L});
    public static final BitSet FOLLOW_spaces_in_constraint555 = new BitSet(new long[]{0x0000000000000010L,0x00000C0000000000L});
    public static final BitSet FOLLOW_between_in_constraint562 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_constraint570 = new BitSet(new long[]{0x0000000000080090L});
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
    public static final BitSet FOLLOW_dotValue_in_genValue1309 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_compOpt_in_genValue1311 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1313 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_AMP_in_genValue1316 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1318 = new BitSet(new long[]{0x0000000000000F10L});
    public static final BitSet FOLLOW_compOpt_in_genValue1320 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_genValue1322 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_dotValue_in_betValue1330 = new BitSet(new long[]{0x0000000000000010L,0x0000000000C00000L});
    public static final BitSet FOLLOW_spaces_in_betValue1332 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
    public static final BitSet FOLLOW_and_in_betValue1334 = new BitSet(new long[]{0x0000000000080090L});
    public static final BitSet FOLLOW_spaces_in_betValue1336 = new BitSet(new long[]{0x0000000000080080L});
    public static final BitSet FOLLOW_dotValue_in_betValue1338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_in_logicalOp1350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_logicalOp1352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_entity1361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr1449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funct1598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_select1636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_and1657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_order1670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_by1683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_or1696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_in1709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_not1722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_like1736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_like1740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_like1744 = new BitSet(new long[]{0x0000000000000010L,0x0000000200000000L});
    public static final BitSet FOLLOW_spaces_in_like1746 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_97_in_like1748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_like1752 = new BitSet(new long[]{0x0000000000000010L,0x0000000400000000L});
    public static final BitSet FOLLOW_spaces_in_like1754 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_98_in_like1756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_count1765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sum1778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_asc1791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_desc1804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_between1817 = new BitSet(new long[]{0x0000000000000002L});

}
