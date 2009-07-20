package dbs.search.parser;
// $ANTLR 3.1.2 Sql.g 2009-07-20 09:16:23

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlLexer extends Lexer {
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
    public static final int T__90=90;
    public static final int T__15=15;
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

    public SqlLexer() {;} 
    public SqlLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public SqlLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "Sql.g"; }

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:3:7: ( 'ads' )
            // Sql.g:3:9: 'ads'
            {
            match("ads"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:4:7: ( 'config' )
            // Sql.g:4:9: 'config'
            {
            match("config"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:5:7: ( 'dataset' )
            // Sql.g:5:9: 'dataset'
            {
            match("dataset"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:6:7: ( 'release' )
            // Sql.g:6:9: 'release'
            {
            match("release"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:7:7: ( 'tier' )
            // Sql.g:7:9: 'tier'
            {
            match("tier"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:8:7: ( 'site' )
            // Sql.g:8:9: 'site'
            {
            match("site"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:9:7: ( 'block' )
            // Sql.g:9:9: 'block'
            {
            match("block"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:10:7: ( 'file' )
            // Sql.g:10:9: 'file'
            {
            match("file"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:11:7: ( 'primds' )
            // Sql.g:11:9: 'primds'
            {
            match("primds"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:12:7: ( 'procds' )
            // Sql.g:12:9: 'procds'
            {
            match("procds"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:13:7: ( 'run' )
            // Sql.g:13:9: 'run'
            {
            match("run"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:14:7: ( 'lumi' )
            // Sql.g:14:9: 'lumi'
            {
            match("lumi"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:15:7: ( 'dq' )
            // Sql.g:15:9: 'dq'
            {
            match("dq"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:16:7: ( 'ilumi' )
            // Sql.g:16:9: 'ilumi'
            {
            match("ilumi"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:17:7: ( 'phygrp' )
            // Sql.g:17:9: 'phygrp'
            {
            match("phygrp"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:18:7: ( 'group' )
            // Sql.g:18:9: 'group'
            {
            match("group"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:19:7: ( 'pset' )
            // Sql.g:19:9: 'pset'
            {
            match("pset"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:20:7: ( 'algo' )
            // Sql.g:20:9: 'algo'
            {
            match("algo"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:21:7: ( 'datatype' )
            // Sql.g:21:9: 'datatype'
            {
            match("datatype"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:22:7: ( 'mcdesc' )
            // Sql.g:22:9: 'mcdesc'
            {
            match("mcdesc"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:23:7: ( 'trigdesc' )
            // Sql.g:23:9: 'trigdesc'
            {
            match("trigdesc"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:24:7: ( 'branch' )
            // Sql.g:24:9: 'branch'
            {
            match("branch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:25:7: ( 'createdate' )
            // Sql.g:25:9: 'createdate'
            {
            match("createdate"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:26:7: ( 'moddate' )
            // Sql.g:26:9: 'moddate'
            {
            match("moddate"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:27:7: ( 'starttime' )
            // Sql.g:27:9: 'starttime'
            {
            match("starttime"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:28:7: ( 'endtime' )
            // Sql.g:28:9: 'endtime'
            {
            match("endtime"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:29:7: ( 'createby' )
            // Sql.g:29:9: 'createby'
            {
            match("createby"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:30:7: ( 'modby' )
            // Sql.g:30:9: 'modby'
            {
            match("modby"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:31:7: ( 'name' )
            // Sql.g:31:9: 'name'
            {
            match("name"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:32:7: ( 'version' )
            // Sql.g:32:9: 'version'
            {
            match("version"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:33:7: ( 'number' )
            // Sql.g:33:9: 'number'
            {
            match("number"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:34:7: ( 'startevnum' )
            // Sql.g:34:9: 'startevnum'
            {
            match("startevnum"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:35:7: ( 'endevnum' )
            // Sql.g:35:9: 'endevnum'
            {
            match("endevnum"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:36:7: ( 'numevents' )
            // Sql.g:36:9: 'numevents'
            {
            match("numevents"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:37:7: ( 'numfiles' )
            // Sql.g:37:9: 'numfiles'
            {
            match("numfiles"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:38:7: ( 'numlss' )
            // Sql.g:38:9: 'numlss'
            {
            match("numlss"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:39:7: ( 'totlumi' )
            // Sql.g:39:9: 'totlumi'
            {
            match("totlumi"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:40:7: ( 'store' )
            // Sql.g:40:9: 'store'
            {
            match("store"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:41:7: ( 'size' )
            // Sql.g:41:9: 'size'
            {
            match("size"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:42:7: ( 'count' )
            // Sql.g:42:9: 'count'
            {
            match("count"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:43:7: ( 'status' )
            // Sql.g:43:9: 'status'
            {
            match("status"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:44:7: ( 'type' )
            // Sql.g:44:9: 'type'
            {
            match("type"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:45:7: ( 'id' )
            // Sql.g:45:9: 'id'
            {
            match("id"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:46:7: ( 'parent' )
            // Sql.g:46:9: 'parent'
            {
            match("parent"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:47:7: ( 'child' )
            // Sql.g:47:9: 'child'
            {
            match("child"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:48:7: ( 'def' )
            // Sql.g:48:9: 'def'
            {
            match("def"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:49:7: ( 'evnum' )
            // Sql.g:49:9: 'evnum'
            {
            match("evnum"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:50:7: ( 'era' )
            // Sql.g:50:9: 'era'
            {
            match("era"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:51:7: ( 'tag' )
            // Sql.g:51:9: 'tag'
            {
            match("tag"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:52:7: ( 'xsection' )
            // Sql.g:52:9: 'xsection'
            {
            match("xsection"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:53:7: ( 'hash' )
            // Sql.g:53:9: 'hash'
            {
            match("hash"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:54:7: ( 'content' )
            // Sql.g:54:9: 'content'
            {
            match("content"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:55:7: ( 'family' )
            // Sql.g:55:9: 'family'
            {
            match("family"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:56:7: ( 'exe' )
            // Sql.g:56:9: 'exe'
            {
            match("exe"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:57:7: ( 'annotation' )
            // Sql.g:57:9: 'annotation'
            {
            match("annotation"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:58:7: ( 'checksum' )
            // Sql.g:58:9: 'checksum'
            {
            match("checksum"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:59:7: ( 'sum' )
            // Sql.g:59:9: 'sum'
            {
            match("sum"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:60:7: ( 'max' )
            // Sql.g:60:9: 'max'
            {
            match("max"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:61:7: ( 'min' )
            // Sql.g:61:9: 'min'
            {
            match("min"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:62:7: ( 'avg' )
            // Sql.g:62:9: 'avg'
            {
            match("avg"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:63:7: ( 'COUNT' )
            // Sql.g:63:9: 'COUNT'
            {
            match("COUNT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:64:7: ( 'SUM' )
            // Sql.g:64:9: 'SUM'
            {
            match("SUM"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:65:7: ( 'MAX' )
            // Sql.g:65:9: 'MAX'
            {
            match("MAX"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:66:7: ( 'MIN' )
            // Sql.g:66:9: 'MIN'
            {
            match("MIN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:67:7: ( 'AVG' )
            // Sql.g:67:9: 'AVG'
            {
            match("AVG"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:68:7: ( 'find' )
            // Sql.g:68:9: 'find'
            {
            match("find"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:69:7: ( 'FIND' )
            // Sql.g:69:9: 'FIND'
            {
            match("FIND"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:70:7: ( 'and' )
            // Sql.g:70:9: 'and'
            {
            match("and"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:71:7: ( 'AND' )
            // Sql.g:71:9: 'AND'
            {
            match("AND"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:72:7: ( 'order' )
            // Sql.g:72:9: 'order'
            {
            match("order"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:73:7: ( 'ORDER' )
            // Sql.g:73:9: 'ORDER'
            {
            match("ORDER"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:74:7: ( 'by' )
            // Sql.g:74:9: 'by'
            {
            match("by"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:75:7: ( 'BY' )
            // Sql.g:75:9: 'BY'
            {
            match("BY"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:76:7: ( 'or' )
            // Sql.g:76:9: 'or'
            {
            match("or"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:77:7: ( 'OR' )
            // Sql.g:77:9: 'OR'
            {
            match("OR"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:78:7: ( 'in' )
            // Sql.g:78:9: 'in'
            {
            match("in"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:79:7: ( 'IN' )
            // Sql.g:79:9: 'IN'
            {
            match("IN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__90"

    // $ANTLR start "T__91"
    public final void mT__91() throws RecognitionException {
        try {
            int _type = T__91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:80:7: ( 'not' )
            // Sql.g:80:9: 'not'
            {
            match("not"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__91"

    // $ANTLR start "T__92"
    public final void mT__92() throws RecognitionException {
        try {
            int _type = T__92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:81:7: ( 'NOT' )
            // Sql.g:81:9: 'NOT'
            {
            match("NOT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__92"

    // $ANTLR start "T__93"
    public final void mT__93() throws RecognitionException {
        try {
            int _type = T__93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:82:7: ( 'like' )
            // Sql.g:82:9: 'like'
            {
            match("like"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__93"

    // $ANTLR start "T__94"
    public final void mT__94() throws RecognitionException {
        try {
            int _type = T__94;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:83:7: ( 'LIKE' )
            // Sql.g:83:9: 'LIKE'
            {
            match("LIKE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__94"

    // $ANTLR start "T__95"
    public final void mT__95() throws RecognitionException {
        try {
            int _type = T__95;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:84:7: ( 'asc' )
            // Sql.g:84:9: 'asc'
            {
            match("asc"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__95"

    // $ANTLR start "T__96"
    public final void mT__96() throws RecognitionException {
        try {
            int _type = T__96;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:85:7: ( 'ASC' )
            // Sql.g:85:9: 'ASC'
            {
            match("ASC"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__96"

    // $ANTLR start "T__97"
    public final void mT__97() throws RecognitionException {
        try {
            int _type = T__97;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:86:7: ( 'desc' )
            // Sql.g:86:9: 'desc'
            {
            match("desc"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__97"

    // $ANTLR start "T__98"
    public final void mT__98() throws RecognitionException {
        try {
            int _type = T__98;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:87:7: ( 'DESC' )
            // Sql.g:87:9: 'DESC'
            {
            match("DESC"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__98"

    // $ANTLR start "T__99"
    public final void mT__99() throws RecognitionException {
        try {
            int _type = T__99;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:88:7: ( 'between' )
            // Sql.g:88:9: 'between'
            {
            match("between"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__99"

    // $ANTLR start "T__100"
    public final void mT__100() throws RecognitionException {
        try {
            int _type = T__100;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:89:8: ( 'BETWEEN' )
            // Sql.g:89:10: 'BETWEEN'
            {
            match("BETWEEN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__100"

    // $ANTLR start "T__101"
    public final void mT__101() throws RecognitionException {
        try {
            int _type = T__101;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:90:8: ( 'where' )
            // Sql.g:90:10: 'where'
            {
            match("where"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__101"

    // $ANTLR start "T__102"
    public final void mT__102() throws RecognitionException {
        try {
            int _type = T__102;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:91:8: ( 'WHERE' )
            // Sql.g:91:10: 'WHERE'
            {
            match("WHERE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__102"

    // $ANTLR start "VALUE"
    public final void mVALUE() throws RecognitionException {
        try {
            int _type = VALUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:129:8: ( ( 'a' .. 'z' | '0' .. '9' | 'A' .. 'Z' | '/' | '-' | '_' | ':' | '#' | '*' | '%' | '&' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' | '*' | '%' | '&' )* )
            // Sql.g:129:9: ( 'a' .. 'z' | '0' .. '9' | 'A' .. 'Z' | '/' | '-' | '_' | ':' | '#' | '*' | '%' | '&' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' | '*' | '%' | '&' )*
            {
            if ( input.LA(1)=='#'||(input.LA(1)>='%' && input.LA(1)<='&')||input.LA(1)=='*'||input.LA(1)=='-'||(input.LA(1)>='/' && input.LA(1)<=':')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // Sql.g:129:84: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' | '*' | '%' | '&' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='#'||(LA1_0>='%' && LA1_0<='&')||LA1_0=='*'||LA1_0=='-'||(LA1_0>='/' && LA1_0<=':')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Sql.g:
            	    {
            	    if ( input.LA(1)=='#'||(input.LA(1)>='%' && input.LA(1)<='&')||input.LA(1)=='*'||input.LA(1)=='-'||(input.LA(1)>='/' && input.LA(1)<=':')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VALUE"

    // $ANTLR start "LB"
    public final void mLB() throws RecognitionException {
        try {
            int _type = LB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:131:5: ( '(' )
            // Sql.g:131:7: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LB"

    // $ANTLR start "RB"
    public final void mRB() throws RecognitionException {
        try {
            int _type = RB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:132:5: ( ')' )
            // Sql.g:132:7: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RB"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:133:8: ( ',' )
            // Sql.g:133:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:134:6: ( '.' )
            // Sql.g:134:7: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:135:5: ( '>' )
            // Sql.g:135:6: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:136:5: ( '<' )
            // Sql.g:136:6: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:137:5: ( '=' )
            // Sql.g:137:6: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:138:6: ( '!' )
            // Sql.g:138:7: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sql.g:140:6: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // Sql.g:140:8: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // Sql.g:140:8: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='\t' && LA2_0<='\n')||(LA2_0>='\f' && LA2_0<='\r')||LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Sql.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // Sql.g:1:8: ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | VALUE | LB | RB | COMMA | DOT | GT | LT | EQ | NOT | WS )
        int alt3=99;
        alt3 = dfa3.predict(input);
        switch (alt3) {
            case 1 :
                // Sql.g:1:10: T__14
                {
                mT__14(); 

                }
                break;
            case 2 :
                // Sql.g:1:16: T__15
                {
                mT__15(); 

                }
                break;
            case 3 :
                // Sql.g:1:22: T__16
                {
                mT__16(); 

                }
                break;
            case 4 :
                // Sql.g:1:28: T__17
                {
                mT__17(); 

                }
                break;
            case 5 :
                // Sql.g:1:34: T__18
                {
                mT__18(); 

                }
                break;
            case 6 :
                // Sql.g:1:40: T__19
                {
                mT__19(); 

                }
                break;
            case 7 :
                // Sql.g:1:46: T__20
                {
                mT__20(); 

                }
                break;
            case 8 :
                // Sql.g:1:52: T__21
                {
                mT__21(); 

                }
                break;
            case 9 :
                // Sql.g:1:58: T__22
                {
                mT__22(); 

                }
                break;
            case 10 :
                // Sql.g:1:64: T__23
                {
                mT__23(); 

                }
                break;
            case 11 :
                // Sql.g:1:70: T__24
                {
                mT__24(); 

                }
                break;
            case 12 :
                // Sql.g:1:76: T__25
                {
                mT__25(); 

                }
                break;
            case 13 :
                // Sql.g:1:82: T__26
                {
                mT__26(); 

                }
                break;
            case 14 :
                // Sql.g:1:88: T__27
                {
                mT__27(); 

                }
                break;
            case 15 :
                // Sql.g:1:94: T__28
                {
                mT__28(); 

                }
                break;
            case 16 :
                // Sql.g:1:100: T__29
                {
                mT__29(); 

                }
                break;
            case 17 :
                // Sql.g:1:106: T__30
                {
                mT__30(); 

                }
                break;
            case 18 :
                // Sql.g:1:112: T__31
                {
                mT__31(); 

                }
                break;
            case 19 :
                // Sql.g:1:118: T__32
                {
                mT__32(); 

                }
                break;
            case 20 :
                // Sql.g:1:124: T__33
                {
                mT__33(); 

                }
                break;
            case 21 :
                // Sql.g:1:130: T__34
                {
                mT__34(); 

                }
                break;
            case 22 :
                // Sql.g:1:136: T__35
                {
                mT__35(); 

                }
                break;
            case 23 :
                // Sql.g:1:142: T__36
                {
                mT__36(); 

                }
                break;
            case 24 :
                // Sql.g:1:148: T__37
                {
                mT__37(); 

                }
                break;
            case 25 :
                // Sql.g:1:154: T__38
                {
                mT__38(); 

                }
                break;
            case 26 :
                // Sql.g:1:160: T__39
                {
                mT__39(); 

                }
                break;
            case 27 :
                // Sql.g:1:166: T__40
                {
                mT__40(); 

                }
                break;
            case 28 :
                // Sql.g:1:172: T__41
                {
                mT__41(); 

                }
                break;
            case 29 :
                // Sql.g:1:178: T__42
                {
                mT__42(); 

                }
                break;
            case 30 :
                // Sql.g:1:184: T__43
                {
                mT__43(); 

                }
                break;
            case 31 :
                // Sql.g:1:190: T__44
                {
                mT__44(); 

                }
                break;
            case 32 :
                // Sql.g:1:196: T__45
                {
                mT__45(); 

                }
                break;
            case 33 :
                // Sql.g:1:202: T__46
                {
                mT__46(); 

                }
                break;
            case 34 :
                // Sql.g:1:208: T__47
                {
                mT__47(); 

                }
                break;
            case 35 :
                // Sql.g:1:214: T__48
                {
                mT__48(); 

                }
                break;
            case 36 :
                // Sql.g:1:220: T__49
                {
                mT__49(); 

                }
                break;
            case 37 :
                // Sql.g:1:226: T__50
                {
                mT__50(); 

                }
                break;
            case 38 :
                // Sql.g:1:232: T__51
                {
                mT__51(); 

                }
                break;
            case 39 :
                // Sql.g:1:238: T__52
                {
                mT__52(); 

                }
                break;
            case 40 :
                // Sql.g:1:244: T__53
                {
                mT__53(); 

                }
                break;
            case 41 :
                // Sql.g:1:250: T__54
                {
                mT__54(); 

                }
                break;
            case 42 :
                // Sql.g:1:256: T__55
                {
                mT__55(); 

                }
                break;
            case 43 :
                // Sql.g:1:262: T__56
                {
                mT__56(); 

                }
                break;
            case 44 :
                // Sql.g:1:268: T__57
                {
                mT__57(); 

                }
                break;
            case 45 :
                // Sql.g:1:274: T__58
                {
                mT__58(); 

                }
                break;
            case 46 :
                // Sql.g:1:280: T__59
                {
                mT__59(); 

                }
                break;
            case 47 :
                // Sql.g:1:286: T__60
                {
                mT__60(); 

                }
                break;
            case 48 :
                // Sql.g:1:292: T__61
                {
                mT__61(); 

                }
                break;
            case 49 :
                // Sql.g:1:298: T__62
                {
                mT__62(); 

                }
                break;
            case 50 :
                // Sql.g:1:304: T__63
                {
                mT__63(); 

                }
                break;
            case 51 :
                // Sql.g:1:310: T__64
                {
                mT__64(); 

                }
                break;
            case 52 :
                // Sql.g:1:316: T__65
                {
                mT__65(); 

                }
                break;
            case 53 :
                // Sql.g:1:322: T__66
                {
                mT__66(); 

                }
                break;
            case 54 :
                // Sql.g:1:328: T__67
                {
                mT__67(); 

                }
                break;
            case 55 :
                // Sql.g:1:334: T__68
                {
                mT__68(); 

                }
                break;
            case 56 :
                // Sql.g:1:340: T__69
                {
                mT__69(); 

                }
                break;
            case 57 :
                // Sql.g:1:346: T__70
                {
                mT__70(); 

                }
                break;
            case 58 :
                // Sql.g:1:352: T__71
                {
                mT__71(); 

                }
                break;
            case 59 :
                // Sql.g:1:358: T__72
                {
                mT__72(); 

                }
                break;
            case 60 :
                // Sql.g:1:364: T__73
                {
                mT__73(); 

                }
                break;
            case 61 :
                // Sql.g:1:370: T__74
                {
                mT__74(); 

                }
                break;
            case 62 :
                // Sql.g:1:376: T__75
                {
                mT__75(); 

                }
                break;
            case 63 :
                // Sql.g:1:382: T__76
                {
                mT__76(); 

                }
                break;
            case 64 :
                // Sql.g:1:388: T__77
                {
                mT__77(); 

                }
                break;
            case 65 :
                // Sql.g:1:394: T__78
                {
                mT__78(); 

                }
                break;
            case 66 :
                // Sql.g:1:400: T__79
                {
                mT__79(); 

                }
                break;
            case 67 :
                // Sql.g:1:406: T__80
                {
                mT__80(); 

                }
                break;
            case 68 :
                // Sql.g:1:412: T__81
                {
                mT__81(); 

                }
                break;
            case 69 :
                // Sql.g:1:418: T__82
                {
                mT__82(); 

                }
                break;
            case 70 :
                // Sql.g:1:424: T__83
                {
                mT__83(); 

                }
                break;
            case 71 :
                // Sql.g:1:430: T__84
                {
                mT__84(); 

                }
                break;
            case 72 :
                // Sql.g:1:436: T__85
                {
                mT__85(); 

                }
                break;
            case 73 :
                // Sql.g:1:442: T__86
                {
                mT__86(); 

                }
                break;
            case 74 :
                // Sql.g:1:448: T__87
                {
                mT__87(); 

                }
                break;
            case 75 :
                // Sql.g:1:454: T__88
                {
                mT__88(); 

                }
                break;
            case 76 :
                // Sql.g:1:460: T__89
                {
                mT__89(); 

                }
                break;
            case 77 :
                // Sql.g:1:466: T__90
                {
                mT__90(); 

                }
                break;
            case 78 :
                // Sql.g:1:472: T__91
                {
                mT__91(); 

                }
                break;
            case 79 :
                // Sql.g:1:478: T__92
                {
                mT__92(); 

                }
                break;
            case 80 :
                // Sql.g:1:484: T__93
                {
                mT__93(); 

                }
                break;
            case 81 :
                // Sql.g:1:490: T__94
                {
                mT__94(); 

                }
                break;
            case 82 :
                // Sql.g:1:496: T__95
                {
                mT__95(); 

                }
                break;
            case 83 :
                // Sql.g:1:502: T__96
                {
                mT__96(); 

                }
                break;
            case 84 :
                // Sql.g:1:508: T__97
                {
                mT__97(); 

                }
                break;
            case 85 :
                // Sql.g:1:514: T__98
                {
                mT__98(); 

                }
                break;
            case 86 :
                // Sql.g:1:520: T__99
                {
                mT__99(); 

                }
                break;
            case 87 :
                // Sql.g:1:526: T__100
                {
                mT__100(); 

                }
                break;
            case 88 :
                // Sql.g:1:533: T__101
                {
                mT__101(); 

                }
                break;
            case 89 :
                // Sql.g:1:540: T__102
                {
                mT__102(); 

                }
                break;
            case 90 :
                // Sql.g:1:547: VALUE
                {
                mVALUE(); 

                }
                break;
            case 91 :
                // Sql.g:1:553: LB
                {
                mLB(); 

                }
                break;
            case 92 :
                // Sql.g:1:556: RB
                {
                mRB(); 

                }
                break;
            case 93 :
                // Sql.g:1:559: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 94 :
                // Sql.g:1:565: DOT
                {
                mDOT(); 

                }
                break;
            case 95 :
                // Sql.g:1:569: GT
                {
                mGT(); 

                }
                break;
            case 96 :
                // Sql.g:1:572: LT
                {
                mLT(); 

                }
                break;
            case 97 :
                // Sql.g:1:575: EQ
                {
                mEQ(); 

                }
                break;
            case 98 :
                // Sql.g:1:578: NOT
                {
                mNOT(); 

                }
                break;
            case 99 :
                // Sql.g:1:582: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\1\uffff\40\41\12\uffff\11\41\1\174\15\41\1\u008d\12\41\1\u009a"+
        "\1\u009b\27\41\1\u00b4\1\u00b6\1\u00b7\1\41\1\u00b9\5\41\1\u00bf"+
        "\2\41\1\u00c2\1\u00c3\1\u00c4\6\41\1\uffff\1\u00cc\2\41\1\u00cf"+
        "\4\41\1\u00d4\4\41\1\u00da\2\41\1\uffff\14\41\2\uffff\3\41\1\u00ed"+
        "\1\u00ee\2\41\1\u00f2\1\u00f3\2\41\1\u00f9\4\41\1\u00fe\1\u00ff"+
        "\1\u0100\1\u0101\1\u0102\1\u0103\2\41\1\uffff\1\41\2\uffff\1\41"+
        "\1\uffff\1\u0108\4\41\1\uffff\1\u010d\1\41\3\uffff\7\41\1\uffff"+
        "\1\u0117\1\41\1\uffff\1\u0119\2\41\1\u011c\1\uffff\1\u011d\1\u011e"+
        "\3\41\1\uffff\3\41\1\u0125\1\u0126\4\41\1\u012b\1\41\1\u012d\1\u012e"+
        "\5\41\2\uffff\3\41\2\uffff\1\u0137\4\41\1\uffff\2\41\1\u013e\1\41"+
        "\6\uffff\1\u0140\3\41\1\uffff\1\u0144\1\u0145\2\41\1\uffff\3\41"+
        "\1\u014b\1\41\1\u014d\3\41\1\uffff\1\41\1\uffff\2\41\3\uffff\2\41"+
        "\1\u0157\1\u0158\2\41\2\uffff\4\41\1\uffff\1\41\2\uffff\1\u0160"+
        "\1\u0161\2\41\1\u0164\2\41\1\u0167\1\uffff\6\41\1\uffff\1\u016e"+
        "\1\uffff\1\u016f\1\u0170\1\41\2\uffff\1\u0172\1\u0173\1\41\1\u0175"+
        "\1\41\1\uffff\1\41\1\uffff\10\41\1\u0181\2\uffff\1\u0182\1\41\1"+
        "\u0184\1\u0185\1\u0186\1\u0187\1\u0188\2\uffff\1\u0189\1\41\1\uffff"+
        "\2\41\1\uffff\1\u018d\2\41\1\u0190\2\41\3\uffff\1\41\2\uffff\1\41"+
        "\1\uffff\1\u0195\3\41\1\u0199\1\41\1\u019b\1\41\1\u019d\2\41\2\uffff"+
        "\1\u01a0\6\uffff\1\u01a1\1\u01a2\1\41\1\uffff\2\41\1\uffff\1\u01a6"+
        "\1\41\1\u01a8\1\41\1\uffff\1\41\1\u01ab\1\u01ac\1\uffff\1\u01ad"+
        "\1\uffff\1\u01ae\1\uffff\2\41\3\uffff\1\u01b1\1\41\1\u01b3\1\uffff"+
        "\1\u01b4\1\uffff\2\41\4\uffff\1\u01b7\1\41\1\uffff\1\u01b9\2\uffff"+
        "\1\u01ba\1\u01bb\1\uffff\1\u01bc\4\uffff";
    static final String DFA3_eofS =
        "\u01bd\uffff";
    static final String DFA3_minS =
        "\1\11\1\144\1\150\1\141\1\145\1\141\1\151\1\145\2\141\1\151\1\144"+
        "\1\162\1\141\1\156\1\141\1\145\1\163\1\141\1\117\1\125\1\101\1\116"+
        "\1\111\1\162\1\122\1\105\1\116\1\117\1\111\1\105\1\150\1\110\12"+
        "\uffff\1\163\1\147\1\144\1\147\1\143\1\156\2\145\1\164\1\43\1\146"+
        "\1\154\1\156\1\145\1\151\1\164\1\160\1\147\1\164\1\141\1\155\1\157"+
        "\1\141\1\43\1\164\1\154\1\155\1\151\1\171\1\145\1\162\1\155\1\153"+
        "\1\165\2\43\1\157\2\144\1\170\1\156\1\144\1\156\1\141\1\145\2\155"+
        "\1\164\1\162\1\145\1\163\1\125\1\115\1\130\1\116\1\107\1\104\1\103"+
        "\1\116\3\43\1\124\1\43\1\124\1\113\1\123\1\145\1\105\1\43\2\157"+
        "\3\43\1\146\1\156\1\141\1\154\1\143\1\141\1\uffff\1\43\1\143\1\145"+
        "\1\43\1\162\1\147\1\154\1\145\1\43\2\145\2\162\1\43\1\143\1\156"+
        "\1\uffff\1\167\1\145\1\144\1\151\1\155\1\143\1\147\1\164\1\145\1"+
        "\151\1\145\1\155\2\uffff\1\165\1\145\1\142\2\43\1\145\1\165\2\43"+
        "\1\145\1\142\1\43\1\163\1\143\1\150\1\116\6\43\1\104\1\145\1\uffff"+
        "\1\105\2\uffff\1\127\1\uffff\1\43\1\105\1\103\1\162\1\122\1\uffff"+
        "\1\43\1\164\3\uffff\1\151\1\145\2\164\1\144\1\153\1\163\1\uffff"+
        "\1\43\1\141\1\uffff\1\43\1\144\1\165\1\43\1\uffff\2\43\1\164\1\165"+
        "\1\145\1\uffff\1\153\1\143\1\145\2\43\1\154\2\144\1\162\1\43\1\156"+
        "\2\43\1\151\1\160\1\163\1\141\1\171\2\uffff\1\151\1\166\1\155\2"+
        "\uffff\1\43\1\145\1\166\1\151\1\163\1\uffff\1\151\1\164\1\43\1\124"+
        "\6\uffff\1\43\1\162\1\122\1\105\1\uffff\2\43\1\145\1\105\1\uffff"+
        "\1\141\1\147\1\156\1\43\1\145\1\43\1\163\1\145\1\171\1\uffff\1\163"+
        "\1\uffff\1\145\1\155\3\uffff\1\145\1\163\2\43\1\150\1\145\2\uffff"+
        "\1\171\2\163\1\160\1\uffff\1\164\2\uffff\2\43\1\143\1\164\1\43\1"+
        "\155\1\156\1\43\1\uffff\1\162\1\145\1\154\1\163\1\157\1\151\1\uffff"+
        "\1\43\1\uffff\2\43\1\105\2\uffff\2\43\1\164\1\43\1\164\1\uffff\1"+
        "\142\1\uffff\1\165\1\164\1\160\1\145\1\163\2\151\1\166\1\43\2\uffff"+
        "\1\43\1\156\5\43\2\uffff\1\43\1\145\1\uffff\1\145\1\165\1\uffff"+
        "\1\43\1\156\1\145\1\43\1\156\1\157\3\uffff\1\116\2\uffff\1\151\1"+
        "\uffff\1\43\1\141\1\171\1\155\1\43\1\145\1\43\1\143\1\43\1\155\1"+
        "\156\2\uffff\1\43\6\uffff\2\43\1\155\1\uffff\1\164\1\163\1\uffff"+
        "\1\43\1\156\1\43\1\157\1\uffff\1\164\2\43\1\uffff\1\43\1\uffff\1"+
        "\43\1\uffff\1\145\1\165\3\uffff\1\43\1\163\1\43\1\uffff\1\43\1\uffff"+
        "\1\156\1\145\4\uffff\1\43\1\155\1\uffff\1\43\2\uffff\2\43\1\uffff"+
        "\1\43\4\uffff";
    static final String DFA3_maxS =
        "\1\172\1\166\1\162\1\161\1\165\1\171\1\165\1\171\1\151\1\163\1\165"+
        "\1\156\1\162\1\157\1\170\1\165\1\145\1\163\1\141\1\117\1\125\1\111"+
        "\1\126\1\111\1\162\1\122\1\131\1\116\1\117\1\111\1\105\1\150\1\110"+
        "\12\uffff\1\163\1\147\1\156\1\147\1\143\1\165\1\145\1\151\1\164"+
        "\1\172\1\163\1\154\1\156\1\145\1\151\1\164\1\160\1\147\1\172\1\157"+
        "\1\155\1\157\1\141\1\172\1\164\1\156\1\155\1\157\1\171\1\145\1\162"+
        "\1\155\1\153\1\165\2\172\1\157\2\144\1\170\1\156\1\144\1\156\1\141"+
        "\1\145\2\155\1\164\1\162\1\145\1\163\1\125\1\115\1\130\1\116\1\107"+
        "\1\104\1\103\1\116\3\172\1\124\1\172\1\124\1\113\1\123\1\145\1\105"+
        "\1\172\2\157\3\172\1\164\1\156\1\141\1\154\1\143\1\141\1\uffff\1"+
        "\172\1\143\1\145\1\172\1\162\1\147\1\154\1\145\1\172\2\145\1\164"+
        "\1\162\1\172\1\143\1\156\1\uffff\1\167\1\145\1\144\1\151\1\155\1"+
        "\143\1\147\1\164\1\145\1\151\1\145\1\155\2\uffff\1\165\1\145\1\144"+
        "\2\172\1\164\1\165\2\172\1\145\1\154\1\172\1\163\1\143\1\150\1\116"+
        "\6\172\1\104\1\145\1\uffff\1\105\2\uffff\1\127\1\uffff\1\172\1\105"+
        "\1\103\1\162\1\122\1\uffff\1\172\1\164\3\uffff\1\151\1\145\2\164"+
        "\1\144\1\153\1\164\1\uffff\1\172\1\141\1\uffff\1\172\1\144\1\165"+
        "\1\172\1\uffff\2\172\1\164\1\165\1\145\1\uffff\1\153\1\143\1\145"+
        "\2\172\1\154\2\144\1\162\1\172\1\156\2\172\1\151\1\160\1\163\1\141"+
        "\1\171\2\uffff\1\151\1\166\1\155\2\uffff\1\172\1\145\1\166\1\151"+
        "\1\163\1\uffff\1\151\1\164\1\172\1\124\6\uffff\1\172\1\162\1\122"+
        "\1\105\1\uffff\2\172\1\145\1\105\1\uffff\1\141\1\147\1\156\1\172"+
        "\1\145\1\172\1\163\1\145\1\171\1\uffff\1\163\1\uffff\1\145\1\155"+
        "\3\uffff\1\164\1\163\2\172\1\150\1\145\2\uffff\1\171\2\163\1\160"+
        "\1\uffff\1\164\2\uffff\2\172\1\143\1\164\1\172\1\155\1\156\1\172"+
        "\1\uffff\1\162\1\145\1\154\1\163\1\157\1\151\1\uffff\1\172\1\uffff"+
        "\2\172\1\105\2\uffff\2\172\1\164\1\172\1\164\1\uffff\1\144\1\uffff"+
        "\1\165\1\164\1\160\1\145\1\163\2\151\1\166\1\172\2\uffff\1\172\1"+
        "\156\5\172\2\uffff\1\172\1\145\1\uffff\1\145\1\165\1\uffff\1\172"+
        "\1\156\1\145\1\172\1\156\1\157\3\uffff\1\116\2\uffff\1\151\1\uffff"+
        "\1\172\1\141\1\171\1\155\1\172\1\145\1\172\1\143\1\172\1\155\1\156"+
        "\2\uffff\1\172\6\uffff\2\172\1\155\1\uffff\1\164\1\163\1\uffff\1"+
        "\172\1\156\1\172\1\157\1\uffff\1\164\2\172\1\uffff\1\172\1\uffff"+
        "\1\172\1\uffff\1\145\1\165\3\uffff\1\172\1\163\1\172\1\uffff\1\172"+
        "\1\uffff\1\156\1\145\4\uffff\1\172\1\155\1\uffff\1\172\2\uffff\2"+
        "\172\1\uffff\1\172\4\uffff";
    static final String DFA3_acceptS =
        "\41\uffff\1\132\1\133\1\134\1\135\1\136\1\137\1\140\1\141\1\142"+
        "\1\143\121\uffff\1\15\20\uffff\1\110\14\uffff\1\53\1\114\30\uffff"+
        "\1\112\1\uffff\1\113\1\111\1\uffff\1\115\5\uffff\1\1\2\uffff\1\104"+
        "\1\74\1\122\7\uffff\1\56\2\uffff\1\13\4\uffff\1\61\5\uffff\1\71"+
        "\22\uffff\1\72\1\73\3\uffff\1\60\1\66\5\uffff\1\116\4\uffff\1\76"+
        "\1\77\1\100\1\101\1\105\1\123\4\uffff\1\117\4\uffff\1\22\11\uffff"+
        "\1\124\1\uffff\1\5\2\uffff\1\52\1\6\1\47\6\uffff\1\10\1\102\4\uffff"+
        "\1\21\1\uffff\1\14\1\120\10\uffff\1\35\6\uffff\1\63\1\uffff\1\103"+
        "\3\uffff\1\121\1\125\5\uffff\1\50\1\uffff\1\55\11\uffff\1\46\1\7"+
        "\7\uffff\1\16\1\20\2\uffff\1\34\2\uffff\1\57\6\uffff\1\75\1\106"+
        "\1\107\1\uffff\1\130\1\131\1\uffff\1\2\13\uffff\1\51\1\26\1\uffff"+
        "\1\65\1\11\1\12\1\17\1\54\1\24\3\uffff\1\37\2\uffff\1\44\4\uffff"+
        "\1\64\3\uffff\1\3\1\uffff\1\4\1\uffff\1\45\2\uffff\1\126\1\30\1"+
        "\32\3\uffff\1\36\1\uffff\1\127\2\uffff\1\33\1\70\1\23\1\25\2\uffff"+
        "\1\41\1\uffff\1\43\1\62\2\uffff\1\31\1\uffff\1\42\1\67\1\27\1\40";
    static final String DFA3_specialS =
        "\u01bd\uffff}>";
    static final String[] DFA3_transitionS = {
            "\2\52\1\uffff\2\52\22\uffff\1\52\1\51\1\uffff\1\41\1\uffff\2"+
            "\41\1\uffff\1\42\1\43\1\41\1\uffff\1\44\1\41\1\45\14\41\1\uffff"+
            "\1\47\1\50\1\46\2\uffff\1\26\1\32\1\23\1\36\1\41\1\27\2\41\1"+
            "\33\2\41\1\35\1\25\1\34\1\31\3\41\1\24\3\41\1\40\3\41\4\uffff"+
            "\1\41\1\uffff\1\1\1\7\1\2\1\3\1\16\1\10\1\14\1\22\1\13\2\41"+
            "\1\12\1\15\1\17\1\30\1\11\1\41\1\4\1\6\1\5\1\41\1\20\1\37\1"+
            "\21\2\41",
            "\1\53\7\uffff\1\54\1\uffff\1\55\4\uffff\1\57\2\uffff\1\56",
            "\1\62\6\uffff\1\60\2\uffff\1\61",
            "\1\63\3\uffff\1\65\13\uffff\1\64",
            "\1\66\17\uffff\1\67",
            "\1\74\7\uffff\1\70\5\uffff\1\72\2\uffff\1\71\6\uffff\1\73",
            "\1\75\12\uffff\1\76\1\77",
            "\1\103\6\uffff\1\100\5\uffff\1\101\6\uffff\1\102",
            "\1\105\7\uffff\1\104",
            "\1\111\6\uffff\1\107\11\uffff\1\106\1\110",
            "\1\113\13\uffff\1\112",
            "\1\115\7\uffff\1\114\1\uffff\1\116",
            "\1\117",
            "\1\122\1\uffff\1\120\5\uffff\1\123\5\uffff\1\121",
            "\1\124\3\uffff\1\126\3\uffff\1\125\1\uffff\1\127",
            "\1\130\15\uffff\1\132\5\uffff\1\131",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140\7\uffff\1\141",
            "\1\143\4\uffff\1\144\2\uffff\1\142",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\151\23\uffff\1\150",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157",
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
            "\1\160",
            "\1\161",
            "\1\163\11\uffff\1\162",
            "\1\164",
            "\1\165",
            "\1\166\6\uffff\1\167",
            "\1\170",
            "\1\172\3\uffff\1\171",
            "\1\173",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\175\14\uffff\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086\5\uffff\1\u0087",
            "\1\u0088\15\uffff\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u008e",
            "\1\u008f\1\uffff\1\u0090",
            "\1\u0091",
            "\1\u0092\5\uffff\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b2",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\3\41\1\u00b3\26\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\3\41\1\u00b5\26\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00b8",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00c0",
            "\1\u00c1",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00c5\15\uffff\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\1\u00ca",
            "\1\u00cb",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00cd",
            "\1\u00ce",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7\1\uffff\1\u00d8",
            "\1\u00d9",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00db",
            "\1\u00dc",
            "",
            "\1\u00dd",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "\1\u00e7",
            "\1\u00e8",
            "",
            "",
            "\1\u00e9",
            "\1\u00ea",
            "\1\u00ec\1\uffff\1\u00eb",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00f0\16\uffff\1\u00ef",
            "\1\u00f1",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00f4",
            "\1\u00f5\2\uffff\1\u00f6\1\u00f7\5\uffff\1\u00f8",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00fa",
            "\1\u00fb",
            "\1\u00fc",
            "\1\u00fd",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0104",
            "\1\u0105",
            "",
            "\1\u0106",
            "",
            "",
            "\1\u0107",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0109",
            "\1\u010a",
            "\1\u010b",
            "\1\u010c",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u010e",
            "",
            "",
            "",
            "\1\u010f",
            "\1\u0110",
            "\1\u0111",
            "\1\u0112",
            "\1\u0113",
            "\1\u0114",
            "\1\u0115\1\u0116",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0118",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u011a",
            "\1\u011b",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u011f",
            "\1\u0120",
            "\1\u0121",
            "",
            "\1\u0122",
            "\1\u0123",
            "\1\u0124",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0127",
            "\1\u0128",
            "\1\u0129",
            "\1\u012a",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u012c",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u012f",
            "\1\u0130",
            "\1\u0131",
            "\1\u0132",
            "\1\u0133",
            "",
            "",
            "\1\u0134",
            "\1\u0135",
            "\1\u0136",
            "",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0138",
            "\1\u0139",
            "\1\u013a",
            "\1\u013b",
            "",
            "\1\u013c",
            "\1\u013d",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u013f",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0141",
            "\1\u0142",
            "\1\u0143",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0146",
            "\1\u0147",
            "",
            "\1\u0148",
            "\1\u0149",
            "\1\u014a",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u014c",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u014e",
            "\1\u014f",
            "\1\u0150",
            "",
            "\1\u0151",
            "",
            "\1\u0152",
            "\1\u0153",
            "",
            "",
            "",
            "\1\u0155\16\uffff\1\u0154",
            "\1\u0156",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0159",
            "\1\u015a",
            "",
            "",
            "\1\u015b",
            "\1\u015c",
            "\1\u015d",
            "\1\u015e",
            "",
            "\1\u015f",
            "",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0162",
            "\1\u0163",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0165",
            "\1\u0166",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\u0168",
            "\1\u0169",
            "\1\u016a",
            "\1\u016b",
            "\1\u016c",
            "\1\u016d",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0171",
            "",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0174",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0176",
            "",
            "\1\u0178\1\uffff\1\u0177",
            "",
            "\1\u0179",
            "\1\u017a",
            "\1\u017b",
            "\1\u017c",
            "\1\u017d",
            "\1\u017e",
            "\1\u017f",
            "\1\u0180",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0183",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u018a",
            "",
            "\1\u018b",
            "\1\u018c",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u018e",
            "\1\u018f",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0191",
            "\1\u0192",
            "",
            "",
            "",
            "\1\u0193",
            "",
            "",
            "\1\u0194",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0196",
            "\1\u0197",
            "\1\u0198",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u019a",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u019c",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u019e",
            "\1\u019f",
            "",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01a3",
            "",
            "\1\u01a4",
            "\1\u01a5",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01a7",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01a9",
            "",
            "\1\u01aa",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\u01af",
            "\1\u01b0",
            "",
            "",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01b2",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\u01b5",
            "\1\u01b6",
            "",
            "",
            "",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01b8",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\41\1\uffff\2\41\3\uffff\1\41\2\uffff\1\41\1\uffff\14\41"+
            "\6\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
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
            return "1:1: Tokens : ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | VALUE | LB | RB | COMMA | DOT | GT | LT | EQ | NOT | WS );";
        }
    }
 

}