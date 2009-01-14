package dbs.search.parser;
// $ANTLR 3.1.1 /Users/vk/Sql.g 2009-01-13 11:37:40

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlLexer extends Lexer {
    public static final int COMMA=5;
    public static final int T__42=42;
    public static final int T__47=47;
    public static final int T__109=109;
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
    public static final int T__48=48;
    public static final int T__54=54;
    public static final int SPACE=4;
    public static final int T__89=89;
    public static final int T__20=20;
    public static final int WS=14;
    public static final int T__79=79;
    public static final int EQ=8;
    public static final int T__64=64;
    public static final int T__44=44;
    public static final int LT=9;
    public static final int T__66=66;
    public static final int T__92=92;
    public static final int T__88=88;
    public static final int T__22=22;
    public static final int T__90=90;
    public static final int T__63=63;
    public static final int T__91=91;
    public static final int T__43=43;
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
    public static final int T__75=75;
    public static final int T__105=105;
    public static final int T__31=31;
    public static final int EOF=-1;
    public static final int T__53=53;
    public static final int T__16=16;
    public static final int T__32=32;
    public static final int T__38=38;
    public static final int T__76=76;
    public static final int T__37=37;
    public static final int T__82=82;
    public static final int T__81=81;
    public static final int T__83=83;
    public static final int NOT=11;
    public static final int T__71=71;
    public static final int T__18=18;
    public static final int T__102=102;

    // delegates
    // delegators

    public SqlLexer() {;} 
    public SqlLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public SqlLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/Users/vk/Sql.g"; }

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:3:7: ( '(' )
            // /Users/vk/Sql.g:3:9: '('
            {
            match('('); 

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
            // /Users/vk/Sql.g:4:7: ( ')' )
            // /Users/vk/Sql.g:4:9: ')'
            {
            match(')'); 

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
            // /Users/vk/Sql.g:5:7: ( 'WHERE' )
            // /Users/vk/Sql.g:5:9: 'WHERE'
            {
            match("WHERE"); 


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
            // /Users/vk/Sql.g:6:7: ( 'where' )
            // /Users/vk/Sql.g:6:9: 'where'
            {
            match("where"); 


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
            // /Users/vk/Sql.g:7:7: ( 'in' )
            // /Users/vk/Sql.g:7:9: 'in'
            {
            match("in"); 


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
            // /Users/vk/Sql.g:8:7: ( 'ads' )
            // /Users/vk/Sql.g:8:9: 'ads'
            {
            match("ads"); 


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
            // /Users/vk/Sql.g:9:7: ( 'config' )
            // /Users/vk/Sql.g:9:9: 'config'
            {
            match("config"); 


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
            // /Users/vk/Sql.g:10:7: ( 'dataset' )
            // /Users/vk/Sql.g:10:9: 'dataset'
            {
            match("dataset"); 


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
            // /Users/vk/Sql.g:11:7: ( 'release' )
            // /Users/vk/Sql.g:11:9: 'release'
            {
            match("release"); 


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
            // /Users/vk/Sql.g:12:7: ( 'tier' )
            // /Users/vk/Sql.g:12:9: 'tier'
            {
            match("tier"); 


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
            // /Users/vk/Sql.g:13:7: ( 'site' )
            // /Users/vk/Sql.g:13:9: 'site'
            {
            match("site"); 


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
            // /Users/vk/Sql.g:14:7: ( 'block' )
            // /Users/vk/Sql.g:14:9: 'block'
            {
            match("block"); 


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
            // /Users/vk/Sql.g:15:7: ( 'file' )
            // /Users/vk/Sql.g:15:9: 'file'
            {
            match("file"); 


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
            // /Users/vk/Sql.g:16:7: ( 'primds' )
            // /Users/vk/Sql.g:16:9: 'primds'
            {
            match("primds"); 


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
            // /Users/vk/Sql.g:17:7: ( 'procds' )
            // /Users/vk/Sql.g:17:9: 'procds'
            {
            match("procds"); 


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
            // /Users/vk/Sql.g:18:7: ( 'run' )
            // /Users/vk/Sql.g:18:9: 'run'
            {
            match("run"); 


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
            // /Users/vk/Sql.g:19:7: ( 'lumi' )
            // /Users/vk/Sql.g:19:9: 'lumi'
            {
            match("lumi"); 


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
            // /Users/vk/Sql.g:20:7: ( 'dq' )
            // /Users/vk/Sql.g:20:9: 'dq'
            {
            match("dq"); 


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
            // /Users/vk/Sql.g:21:7: ( 'ilumi' )
            // /Users/vk/Sql.g:21:9: 'ilumi'
            {
            match("ilumi"); 


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
            // /Users/vk/Sql.g:22:7: ( 'phygrp' )
            // /Users/vk/Sql.g:22:9: 'phygrp'
            {
            match("phygrp"); 


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
            // /Users/vk/Sql.g:23:7: ( 'group' )
            // /Users/vk/Sql.g:23:9: 'group'
            {
            match("group"); 


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
            // /Users/vk/Sql.g:24:7: ( 'pset' )
            // /Users/vk/Sql.g:24:9: 'pset'
            {
            match("pset"); 


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
            // /Users/vk/Sql.g:25:7: ( 'algo' )
            // /Users/vk/Sql.g:25:9: 'algo'
            {
            match("algo"); 


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
            // /Users/vk/Sql.g:26:7: ( 'datatype' )
            // /Users/vk/Sql.g:26:9: 'datatype'
            {
            match("datatype"); 


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
            // /Users/vk/Sql.g:27:7: ( 'mcdesc' )
            // /Users/vk/Sql.g:27:9: 'mcdesc'
            {
            match("mcdesc"); 


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
            // /Users/vk/Sql.g:28:7: ( 'trigdesc' )
            // /Users/vk/Sql.g:28:9: 'trigdesc'
            {
            match("trigdesc"); 


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
            // /Users/vk/Sql.g:29:7: ( 'branch' )
            // /Users/vk/Sql.g:29:9: 'branch'
            {
            match("branch"); 


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
            // /Users/vk/Sql.g:30:7: ( 'createdate' )
            // /Users/vk/Sql.g:30:9: 'createdate'
            {
            match("createdate"); 


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
            // /Users/vk/Sql.g:31:7: ( 'moddate' )
            // /Users/vk/Sql.g:31:9: 'moddate'
            {
            match("moddate"); 


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
            // /Users/vk/Sql.g:32:7: ( 'starttime' )
            // /Users/vk/Sql.g:32:9: 'starttime'
            {
            match("starttime"); 


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
            // /Users/vk/Sql.g:33:7: ( 'endtime' )
            // /Users/vk/Sql.g:33:9: 'endtime'
            {
            match("endtime"); 


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
            // /Users/vk/Sql.g:34:7: ( 'createby' )
            // /Users/vk/Sql.g:34:9: 'createby'
            {
            match("createby"); 


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
            // /Users/vk/Sql.g:35:7: ( 'modby' )
            // /Users/vk/Sql.g:35:9: 'modby'
            {
            match("modby"); 


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
            // /Users/vk/Sql.g:36:7: ( 'name' )
            // /Users/vk/Sql.g:36:9: 'name'
            {
            match("name"); 


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
            // /Users/vk/Sql.g:37:7: ( 'version' )
            // /Users/vk/Sql.g:37:9: 'version'
            {
            match("version"); 


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
            // /Users/vk/Sql.g:38:7: ( 'number' )
            // /Users/vk/Sql.g:38:9: 'number'
            {
            match("number"); 


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
            // /Users/vk/Sql.g:39:7: ( 'startevnum' )
            // /Users/vk/Sql.g:39:9: 'startevnum'
            {
            match("startevnum"); 


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
            // /Users/vk/Sql.g:40:7: ( 'endevnum' )
            // /Users/vk/Sql.g:40:9: 'endevnum'
            {
            match("endevnum"); 


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
            // /Users/vk/Sql.g:41:7: ( 'numevents' )
            // /Users/vk/Sql.g:41:9: 'numevents'
            {
            match("numevents"); 


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
            // /Users/vk/Sql.g:42:7: ( 'numfiles' )
            // /Users/vk/Sql.g:42:9: 'numfiles'
            {
            match("numfiles"); 


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
            // /Users/vk/Sql.g:43:7: ( 'numlss' )
            // /Users/vk/Sql.g:43:9: 'numlss'
            {
            match("numlss"); 


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
            // /Users/vk/Sql.g:44:7: ( 'totlumi' )
            // /Users/vk/Sql.g:44:9: 'totlumi'
            {
            match("totlumi"); 


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
            // /Users/vk/Sql.g:45:7: ( 'store' )
            // /Users/vk/Sql.g:45:9: 'store'
            {
            match("store"); 


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
            // /Users/vk/Sql.g:46:7: ( 'size' )
            // /Users/vk/Sql.g:46:9: 'size'
            {
            match("size"); 


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
            // /Users/vk/Sql.g:47:7: ( 'count' )
            // /Users/vk/Sql.g:47:9: 'count'
            {
            match("count"); 


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
            // /Users/vk/Sql.g:48:7: ( 'status' )
            // /Users/vk/Sql.g:48:9: 'status'
            {
            match("status"); 


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
            // /Users/vk/Sql.g:49:7: ( 'type' )
            // /Users/vk/Sql.g:49:9: 'type'
            {
            match("type"); 


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
            // /Users/vk/Sql.g:50:7: ( 'id' )
            // /Users/vk/Sql.g:50:9: 'id'
            {
            match("id"); 


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
            // /Users/vk/Sql.g:51:7: ( 'parent' )
            // /Users/vk/Sql.g:51:9: 'parent'
            {
            match("parent"); 


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
            // /Users/vk/Sql.g:52:7: ( 'child' )
            // /Users/vk/Sql.g:52:9: 'child'
            {
            match("child"); 


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
            // /Users/vk/Sql.g:53:7: ( 'def' )
            // /Users/vk/Sql.g:53:9: 'def'
            {
            match("def"); 


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
            // /Users/vk/Sql.g:54:7: ( 'evnum' )
            // /Users/vk/Sql.g:54:9: 'evnum'
            {
            match("evnum"); 


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
            // /Users/vk/Sql.g:55:7: ( 'era' )
            // /Users/vk/Sql.g:55:9: 'era'
            {
            match("era"); 


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
            // /Users/vk/Sql.g:56:7: ( 'tag' )
            // /Users/vk/Sql.g:56:9: 'tag'
            {
            match("tag"); 


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
            // /Users/vk/Sql.g:57:7: ( 'xsection' )
            // /Users/vk/Sql.g:57:9: 'xsection'
            {
            match("xsection"); 


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
            // /Users/vk/Sql.g:58:7: ( 'hash' )
            // /Users/vk/Sql.g:58:9: 'hash'
            {
            match("hash"); 


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
            // /Users/vk/Sql.g:59:7: ( 'content' )
            // /Users/vk/Sql.g:59:9: 'content'
            {
            match("content"); 


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
            // /Users/vk/Sql.g:60:7: ( 'family' )
            // /Users/vk/Sql.g:60:9: 'family'
            {
            match("family"); 


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
            // /Users/vk/Sql.g:61:7: ( 'exe' )
            // /Users/vk/Sql.g:61:9: 'exe'
            {
            match("exe"); 


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
            // /Users/vk/Sql.g:62:7: ( 'annotation' )
            // /Users/vk/Sql.g:62:9: 'annotation'
            {
            match("annotation"); 


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
            // /Users/vk/Sql.g:63:7: ( 'checksum' )
            // /Users/vk/Sql.g:63:9: 'checksum'
            {
            match("checksum"); 


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
            // /Users/vk/Sql.g:64:7: ( 'numruns()' )
            // /Users/vk/Sql.g:64:9: 'numruns()'
            {
            match("numruns()"); 


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
            // /Users/vk/Sql.g:65:7: ( 'numfiles()' )
            // /Users/vk/Sql.g:65:9: 'numfiles()'
            {
            match("numfiles()"); 


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
            // /Users/vk/Sql.g:66:7: ( 'dataquality()' )
            // /Users/vk/Sql.g:66:9: 'dataquality()'
            {
            match("dataquality()"); 


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
            // /Users/vk/Sql.g:67:7: ( 'latest()' )
            // /Users/vk/Sql.g:67:9: 'latest()'
            {
            match("latest()"); 


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
            // /Users/vk/Sql.g:68:7: ( 'parentrelease()' )
            // /Users/vk/Sql.g:68:9: 'parentrelease()'
            {
            match("parentrelease()"); 


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
            // /Users/vk/Sql.g:69:7: ( 'childrelease()' )
            // /Users/vk/Sql.g:69:9: 'childrelease()'
            {
            match("childrelease()"); 


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
            // /Users/vk/Sql.g:70:7: ( 'intluminosity()' )
            // /Users/vk/Sql.g:70:9: 'intluminosity()'
            {
            match("intluminosity()"); 


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
            // /Users/vk/Sql.g:71:7: ( 'findevents()' )
            // /Users/vk/Sql.g:71:9: 'findevents()'
            {
            match("findevents()"); 


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
            // /Users/vk/Sql.g:72:7: ( 'select' )
            // /Users/vk/Sql.g:72:9: 'select'
            {
            match("select"); 


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
            // /Users/vk/Sql.g:73:7: ( 'SELECT' )
            // /Users/vk/Sql.g:73:9: 'SELECT'
            {
            match("SELECT"); 


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
            // /Users/vk/Sql.g:74:7: ( 'find' )
            // /Users/vk/Sql.g:74:9: 'find'
            {
            match("find"); 


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
            // /Users/vk/Sql.g:75:7: ( 'FIND' )
            // /Users/vk/Sql.g:75:9: 'FIND'
            {
            match("FIND"); 


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
            // /Users/vk/Sql.g:76:7: ( 'and' )
            // /Users/vk/Sql.g:76:9: 'and'
            {
            match("and"); 


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
            // /Users/vk/Sql.g:77:7: ( 'AND' )
            // /Users/vk/Sql.g:77:9: 'AND'
            {
            match("AND"); 


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
            // /Users/vk/Sql.g:78:7: ( 'order' )
            // /Users/vk/Sql.g:78:9: 'order'
            {
            match("order"); 


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
            // /Users/vk/Sql.g:79:7: ( 'ORDER' )
            // /Users/vk/Sql.g:79:9: 'ORDER'
            {
            match("ORDER"); 


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
            // /Users/vk/Sql.g:80:7: ( 'by' )
            // /Users/vk/Sql.g:80:9: 'by'
            {
            match("by"); 


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
            // /Users/vk/Sql.g:81:7: ( 'BY' )
            // /Users/vk/Sql.g:81:9: 'BY'
            {
            match("BY"); 


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
            // /Users/vk/Sql.g:82:7: ( 'or' )
            // /Users/vk/Sql.g:82:9: 'or'
            {
            match("or"); 


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
            // /Users/vk/Sql.g:83:7: ( 'OR' )
            // /Users/vk/Sql.g:83:9: 'OR'
            {
            match("OR"); 


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
            // /Users/vk/Sql.g:84:7: ( 'IN' )
            // /Users/vk/Sql.g:84:9: 'IN'
            {
            match("IN"); 


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
            // /Users/vk/Sql.g:85:7: ( 'not' )
            // /Users/vk/Sql.g:85:9: 'not'
            {
            match("not"); 


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
            // /Users/vk/Sql.g:86:7: ( 'NOT' )
            // /Users/vk/Sql.g:86:9: 'NOT'
            {
            match("NOT"); 


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
            // /Users/vk/Sql.g:87:7: ( 'like' )
            // /Users/vk/Sql.g:87:9: 'like'
            {
            match("like"); 


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
            // /Users/vk/Sql.g:88:8: ( 'LIKE' )
            // /Users/vk/Sql.g:88:10: 'LIKE'
            {
            match("LIKE"); 


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
            // /Users/vk/Sql.g:89:8: ( 'COUNT' )
            // /Users/vk/Sql.g:89:10: 'COUNT'
            {
            match("COUNT"); 


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
            // /Users/vk/Sql.g:90:8: ( 'sum' )
            // /Users/vk/Sql.g:90:10: 'sum'
            {
            match("sum"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__102"

    // $ANTLR start "T__103"
    public final void mT__103() throws RecognitionException {
        try {
            int _type = T__103;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:91:8: ( 'SUM' )
            // /Users/vk/Sql.g:91:10: 'SUM'
            {
            match("SUM"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__103"

    // $ANTLR start "T__104"
    public final void mT__104() throws RecognitionException {
        try {
            int _type = T__104;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:92:8: ( 'asc' )
            // /Users/vk/Sql.g:92:10: 'asc'
            {
            match("asc"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__104"

    // $ANTLR start "T__105"
    public final void mT__105() throws RecognitionException {
        try {
            int _type = T__105;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:93:8: ( 'ASC' )
            // /Users/vk/Sql.g:93:10: 'ASC'
            {
            match("ASC"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__105"

    // $ANTLR start "T__106"
    public final void mT__106() throws RecognitionException {
        try {
            int _type = T__106;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:94:8: ( 'desc' )
            // /Users/vk/Sql.g:94:10: 'desc'
            {
            match("desc"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__106"

    // $ANTLR start "T__107"
    public final void mT__107() throws RecognitionException {
        try {
            int _type = T__107;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:95:8: ( 'DESC' )
            // /Users/vk/Sql.g:95:10: 'DESC'
            {
            match("DESC"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__107"

    // $ANTLR start "T__108"
    public final void mT__108() throws RecognitionException {
        try {
            int _type = T__108;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:96:8: ( 'between' )
            // /Users/vk/Sql.g:96:10: 'between'
            {
            match("between"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__108"

    // $ANTLR start "T__109"
    public final void mT__109() throws RecognitionException {
        try {
            int _type = T__109;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:97:8: ( 'BETWEEN' )
            // /Users/vk/Sql.g:97:10: 'BETWEEN'
            {
            match("BETWEEN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__109"

    // $ANTLR start "VALUE"
    public final void mVALUE() throws RecognitionException {
        try {
            int _type = VALUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:165:8: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' | '*' | '%' )+ )
            // /Users/vk/Sql.g:165:9: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' | '*' | '%' )+
            {
            // /Users/vk/Sql.g:165:9: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' | '*' | '%' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='#'||LA1_0=='%'||LA1_0=='*'||LA1_0=='-'||(LA1_0>='/' && LA1_0<=':')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/vk/Sql.g:
            	    {
            	    if ( input.LA(1)=='#'||input.LA(1)=='%'||input.LA(1)=='*'||input.LA(1)=='-'||(input.LA(1)>='/' && input.LA(1)<=':')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VALUE"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:171:8: ( ( ',' ) )
            // /Users/vk/Sql.g:171:9: ( ',' )
            {
            // /Users/vk/Sql.g:171:9: ( ',' )
            // /Users/vk/Sql.g:171:10: ','
            {
            match(','); 

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "SPACE"
    public final void mSPACE() throws RecognitionException {
        try {
            int _type = SPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:172:8: ( ( ' ' ) )
            // /Users/vk/Sql.g:172:9: ( ' ' )
            {
            // /Users/vk/Sql.g:172:9: ( ' ' )
            // /Users/vk/Sql.g:172:10: ' '
            {
            match(' '); 

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SPACE"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:173:6: ( ( '.' ) )
            // /Users/vk/Sql.g:173:7: ( '.' )
            {
            // /Users/vk/Sql.g:173:7: ( '.' )
            // /Users/vk/Sql.g:173:8: '.'
            {
            match('.'); 

            }


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
            // /Users/vk/Sql.g:175:5: ( ( '>' ) )
            // /Users/vk/Sql.g:175:6: ( '>' )
            {
            // /Users/vk/Sql.g:175:6: ( '>' )
            // /Users/vk/Sql.g:175:7: '>'
            {
            match('>'); 

            }


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
            // /Users/vk/Sql.g:176:5: ( ( '<' ) )
            // /Users/vk/Sql.g:176:6: ( '<' )
            {
            // /Users/vk/Sql.g:176:6: ( '<' )
            // /Users/vk/Sql.g:176:7: '<'
            {
            match('<'); 

            }


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
            // /Users/vk/Sql.g:177:5: ( ( '=' ) )
            // /Users/vk/Sql.g:177:6: ( '=' )
            {
            // /Users/vk/Sql.g:177:6: ( '=' )
            // /Users/vk/Sql.g:177:7: '='
            {
            match('='); 

            }


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
            // /Users/vk/Sql.g:178:6: ( ( '!' ) )
            // /Users/vk/Sql.g:178:7: ( '!' )
            {
            // /Users/vk/Sql.g:178:7: ( '!' )
            // /Users/vk/Sql.g:178:8: '!'
            {
            match('!'); 

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "AMP"
    public final void mAMP() throws RecognitionException {
        try {
            int _type = AMP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:179:6: ( ( '&' ) )
            // /Users/vk/Sql.g:179:7: ( '&' )
            {
            // /Users/vk/Sql.g:179:7: ( '&' )
            // /Users/vk/Sql.g:179:8: '&'
            {
            match('&'); 

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AMP"

    // $ANTLR start "NL"
    public final void mNL() throws RecognitionException {
        try {
            int _type = NL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:181:5: ( ( '\\n' ) )
            // /Users/vk/Sql.g:181:6: ( '\\n' )
            {
            // /Users/vk/Sql.g:181:6: ( '\\n' )
            // /Users/vk/Sql.g:181:7: '\\n'
            {
            match('\n'); 

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NL"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/vk/Sql.g:182:6: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // /Users/vk/Sql.g:182:8: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // /Users/vk/Sql.g:182:8: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
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
            	    // /Users/vk/Sql.g:
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
        // /Users/vk/Sql.g:1:8: ( T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | VALUE | COMMA | SPACE | DOT | GT | LT | EQ | NOT | AMP | NL | WS )
        int alt3=106;
        alt3 = dfa3.predict(input);
        switch (alt3) {
            case 1 :
                // /Users/vk/Sql.g:1:10: T__15
                {
                mT__15(); 

                }
                break;
            case 2 :
                // /Users/vk/Sql.g:1:16: T__16
                {
                mT__16(); 

                }
                break;
            case 3 :
                // /Users/vk/Sql.g:1:22: T__17
                {
                mT__17(); 

                }
                break;
            case 4 :
                // /Users/vk/Sql.g:1:28: T__18
                {
                mT__18(); 

                }
                break;
            case 5 :
                // /Users/vk/Sql.g:1:34: T__19
                {
                mT__19(); 

                }
                break;
            case 6 :
                // /Users/vk/Sql.g:1:40: T__20
                {
                mT__20(); 

                }
                break;
            case 7 :
                // /Users/vk/Sql.g:1:46: T__21
                {
                mT__21(); 

                }
                break;
            case 8 :
                // /Users/vk/Sql.g:1:52: T__22
                {
                mT__22(); 

                }
                break;
            case 9 :
                // /Users/vk/Sql.g:1:58: T__23
                {
                mT__23(); 

                }
                break;
            case 10 :
                // /Users/vk/Sql.g:1:64: T__24
                {
                mT__24(); 

                }
                break;
            case 11 :
                // /Users/vk/Sql.g:1:70: T__25
                {
                mT__25(); 

                }
                break;
            case 12 :
                // /Users/vk/Sql.g:1:76: T__26
                {
                mT__26(); 

                }
                break;
            case 13 :
                // /Users/vk/Sql.g:1:82: T__27
                {
                mT__27(); 

                }
                break;
            case 14 :
                // /Users/vk/Sql.g:1:88: T__28
                {
                mT__28(); 

                }
                break;
            case 15 :
                // /Users/vk/Sql.g:1:94: T__29
                {
                mT__29(); 

                }
                break;
            case 16 :
                // /Users/vk/Sql.g:1:100: T__30
                {
                mT__30(); 

                }
                break;
            case 17 :
                // /Users/vk/Sql.g:1:106: T__31
                {
                mT__31(); 

                }
                break;
            case 18 :
                // /Users/vk/Sql.g:1:112: T__32
                {
                mT__32(); 

                }
                break;
            case 19 :
                // /Users/vk/Sql.g:1:118: T__33
                {
                mT__33(); 

                }
                break;
            case 20 :
                // /Users/vk/Sql.g:1:124: T__34
                {
                mT__34(); 

                }
                break;
            case 21 :
                // /Users/vk/Sql.g:1:130: T__35
                {
                mT__35(); 

                }
                break;
            case 22 :
                // /Users/vk/Sql.g:1:136: T__36
                {
                mT__36(); 

                }
                break;
            case 23 :
                // /Users/vk/Sql.g:1:142: T__37
                {
                mT__37(); 

                }
                break;
            case 24 :
                // /Users/vk/Sql.g:1:148: T__38
                {
                mT__38(); 

                }
                break;
            case 25 :
                // /Users/vk/Sql.g:1:154: T__39
                {
                mT__39(); 

                }
                break;
            case 26 :
                // /Users/vk/Sql.g:1:160: T__40
                {
                mT__40(); 

                }
                break;
            case 27 :
                // /Users/vk/Sql.g:1:166: T__41
                {
                mT__41(); 

                }
                break;
            case 28 :
                // /Users/vk/Sql.g:1:172: T__42
                {
                mT__42(); 

                }
                break;
            case 29 :
                // /Users/vk/Sql.g:1:178: T__43
                {
                mT__43(); 

                }
                break;
            case 30 :
                // /Users/vk/Sql.g:1:184: T__44
                {
                mT__44(); 

                }
                break;
            case 31 :
                // /Users/vk/Sql.g:1:190: T__45
                {
                mT__45(); 

                }
                break;
            case 32 :
                // /Users/vk/Sql.g:1:196: T__46
                {
                mT__46(); 

                }
                break;
            case 33 :
                // /Users/vk/Sql.g:1:202: T__47
                {
                mT__47(); 

                }
                break;
            case 34 :
                // /Users/vk/Sql.g:1:208: T__48
                {
                mT__48(); 

                }
                break;
            case 35 :
                // /Users/vk/Sql.g:1:214: T__49
                {
                mT__49(); 

                }
                break;
            case 36 :
                // /Users/vk/Sql.g:1:220: T__50
                {
                mT__50(); 

                }
                break;
            case 37 :
                // /Users/vk/Sql.g:1:226: T__51
                {
                mT__51(); 

                }
                break;
            case 38 :
                // /Users/vk/Sql.g:1:232: T__52
                {
                mT__52(); 

                }
                break;
            case 39 :
                // /Users/vk/Sql.g:1:238: T__53
                {
                mT__53(); 

                }
                break;
            case 40 :
                // /Users/vk/Sql.g:1:244: T__54
                {
                mT__54(); 

                }
                break;
            case 41 :
                // /Users/vk/Sql.g:1:250: T__55
                {
                mT__55(); 

                }
                break;
            case 42 :
                // /Users/vk/Sql.g:1:256: T__56
                {
                mT__56(); 

                }
                break;
            case 43 :
                // /Users/vk/Sql.g:1:262: T__57
                {
                mT__57(); 

                }
                break;
            case 44 :
                // /Users/vk/Sql.g:1:268: T__58
                {
                mT__58(); 

                }
                break;
            case 45 :
                // /Users/vk/Sql.g:1:274: T__59
                {
                mT__59(); 

                }
                break;
            case 46 :
                // /Users/vk/Sql.g:1:280: T__60
                {
                mT__60(); 

                }
                break;
            case 47 :
                // /Users/vk/Sql.g:1:286: T__61
                {
                mT__61(); 

                }
                break;
            case 48 :
                // /Users/vk/Sql.g:1:292: T__62
                {
                mT__62(); 

                }
                break;
            case 49 :
                // /Users/vk/Sql.g:1:298: T__63
                {
                mT__63(); 

                }
                break;
            case 50 :
                // /Users/vk/Sql.g:1:304: T__64
                {
                mT__64(); 

                }
                break;
            case 51 :
                // /Users/vk/Sql.g:1:310: T__65
                {
                mT__65(); 

                }
                break;
            case 52 :
                // /Users/vk/Sql.g:1:316: T__66
                {
                mT__66(); 

                }
                break;
            case 53 :
                // /Users/vk/Sql.g:1:322: T__67
                {
                mT__67(); 

                }
                break;
            case 54 :
                // /Users/vk/Sql.g:1:328: T__68
                {
                mT__68(); 

                }
                break;
            case 55 :
                // /Users/vk/Sql.g:1:334: T__69
                {
                mT__69(); 

                }
                break;
            case 56 :
                // /Users/vk/Sql.g:1:340: T__70
                {
                mT__70(); 

                }
                break;
            case 57 :
                // /Users/vk/Sql.g:1:346: T__71
                {
                mT__71(); 

                }
                break;
            case 58 :
                // /Users/vk/Sql.g:1:352: T__72
                {
                mT__72(); 

                }
                break;
            case 59 :
                // /Users/vk/Sql.g:1:358: T__73
                {
                mT__73(); 

                }
                break;
            case 60 :
                // /Users/vk/Sql.g:1:364: T__74
                {
                mT__74(); 

                }
                break;
            case 61 :
                // /Users/vk/Sql.g:1:370: T__75
                {
                mT__75(); 

                }
                break;
            case 62 :
                // /Users/vk/Sql.g:1:376: T__76
                {
                mT__76(); 

                }
                break;
            case 63 :
                // /Users/vk/Sql.g:1:382: T__77
                {
                mT__77(); 

                }
                break;
            case 64 :
                // /Users/vk/Sql.g:1:388: T__78
                {
                mT__78(); 

                }
                break;
            case 65 :
                // /Users/vk/Sql.g:1:394: T__79
                {
                mT__79(); 

                }
                break;
            case 66 :
                // /Users/vk/Sql.g:1:400: T__80
                {
                mT__80(); 

                }
                break;
            case 67 :
                // /Users/vk/Sql.g:1:406: T__81
                {
                mT__81(); 

                }
                break;
            case 68 :
                // /Users/vk/Sql.g:1:412: T__82
                {
                mT__82(); 

                }
                break;
            case 69 :
                // /Users/vk/Sql.g:1:418: T__83
                {
                mT__83(); 

                }
                break;
            case 70 :
                // /Users/vk/Sql.g:1:424: T__84
                {
                mT__84(); 

                }
                break;
            case 71 :
                // /Users/vk/Sql.g:1:430: T__85
                {
                mT__85(); 

                }
                break;
            case 72 :
                // /Users/vk/Sql.g:1:436: T__86
                {
                mT__86(); 

                }
                break;
            case 73 :
                // /Users/vk/Sql.g:1:442: T__87
                {
                mT__87(); 

                }
                break;
            case 74 :
                // /Users/vk/Sql.g:1:448: T__88
                {
                mT__88(); 

                }
                break;
            case 75 :
                // /Users/vk/Sql.g:1:454: T__89
                {
                mT__89(); 

                }
                break;
            case 76 :
                // /Users/vk/Sql.g:1:460: T__90
                {
                mT__90(); 

                }
                break;
            case 77 :
                // /Users/vk/Sql.g:1:466: T__91
                {
                mT__91(); 

                }
                break;
            case 78 :
                // /Users/vk/Sql.g:1:472: T__92
                {
                mT__92(); 

                }
                break;
            case 79 :
                // /Users/vk/Sql.g:1:478: T__93
                {
                mT__93(); 

                }
                break;
            case 80 :
                // /Users/vk/Sql.g:1:484: T__94
                {
                mT__94(); 

                }
                break;
            case 81 :
                // /Users/vk/Sql.g:1:490: T__95
                {
                mT__95(); 

                }
                break;
            case 82 :
                // /Users/vk/Sql.g:1:496: T__96
                {
                mT__96(); 

                }
                break;
            case 83 :
                // /Users/vk/Sql.g:1:502: T__97
                {
                mT__97(); 

                }
                break;
            case 84 :
                // /Users/vk/Sql.g:1:508: T__98
                {
                mT__98(); 

                }
                break;
            case 85 :
                // /Users/vk/Sql.g:1:514: T__99
                {
                mT__99(); 

                }
                break;
            case 86 :
                // /Users/vk/Sql.g:1:520: T__100
                {
                mT__100(); 

                }
                break;
            case 87 :
                // /Users/vk/Sql.g:1:527: T__101
                {
                mT__101(); 

                }
                break;
            case 88 :
                // /Users/vk/Sql.g:1:534: T__102
                {
                mT__102(); 

                }
                break;
            case 89 :
                // /Users/vk/Sql.g:1:541: T__103
                {
                mT__103(); 

                }
                break;
            case 90 :
                // /Users/vk/Sql.g:1:548: T__104
                {
                mT__104(); 

                }
                break;
            case 91 :
                // /Users/vk/Sql.g:1:555: T__105
                {
                mT__105(); 

                }
                break;
            case 92 :
                // /Users/vk/Sql.g:1:562: T__106
                {
                mT__106(); 

                }
                break;
            case 93 :
                // /Users/vk/Sql.g:1:569: T__107
                {
                mT__107(); 

                }
                break;
            case 94 :
                // /Users/vk/Sql.g:1:576: T__108
                {
                mT__108(); 

                }
                break;
            case 95 :
                // /Users/vk/Sql.g:1:583: T__109
                {
                mT__109(); 

                }
                break;
            case 96 :
                // /Users/vk/Sql.g:1:590: VALUE
                {
                mVALUE(); 

                }
                break;
            case 97 :
                // /Users/vk/Sql.g:1:596: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 98 :
                // /Users/vk/Sql.g:1:602: SPACE
                {
                mSPACE(); 

                }
                break;
            case 99 :
                // /Users/vk/Sql.g:1:608: DOT
                {
                mDOT(); 

                }
                break;
            case 100 :
                // /Users/vk/Sql.g:1:612: GT
                {
                mGT(); 

                }
                break;
            case 101 :
                // /Users/vk/Sql.g:1:615: LT
                {
                mLT(); 

                }
                break;
            case 102 :
                // /Users/vk/Sql.g:1:618: EQ
                {
                mEQ(); 

                }
                break;
            case 103 :
                // /Users/vk/Sql.g:1:621: NOT
                {
                mNOT(); 

                }
                break;
            case 104 :
                // /Users/vk/Sql.g:1:625: AMP
                {
                mAMP(); 

                }
                break;
            case 105 :
                // /Users/vk/Sql.g:1:629: NL
                {
                mNL(); 

                }
                break;
            case 106 :
                // /Users/vk/Sql.g:1:632: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\3\uffff\37\42\2\uffff\1\157\6\uffff\1\160\1\uffff\2\42\1\164\1"+
        "\42\1\166\10\42\1\u0082\16\42\1\u0094\34\42\1\u00b4\1\u00b6\1\u00b7"+
        "\1\42\1\u00b9\4\42\2\uffff\3\42\1\uffff\1\42\1\uffff\1\u00c2\2\42"+
        "\1\u00c5\1\u00c6\6\42\1\uffff\1\u00ce\2\42\1\u00d1\4\42\1\u00d6"+
        "\5\42\1\u00dd\2\42\1\uffff\21\42\1\u00f3\1\u00f4\2\42\1\u00fb\4"+
        "\42\1\u0100\1\42\1\u0102\1\u0103\1\42\1\uffff\1\42\2\uffff\1\42"+
        "\1\uffff\1\u0107\7\42\1\uffff\1\u010f\1\42\2\uffff\7\42\1\uffff"+
        "\1\u011a\1\42\1\uffff\1\u011c\2\42\1\u011f\1\uffff\1\u0120\1\u0121"+
        "\4\42\1\uffff\3\42\1\u0129\1\u012b\4\42\1\u0130\1\42\1\u0132\1\42"+
        "\1\u0134\7\42\2\uffff\1\u013c\5\42\1\uffff\2\42\1\u0144\1\42\1\uffff"+
        "\1\u0146\2\uffff\3\42\1\uffff\1\u014a\1\42\1\u014c\1\u014d\1\u014e"+
        "\1\42\1\u0150\1\uffff\3\42\1\u0154\1\42\1\u0157\4\42\1\uffff\1\42"+
        "\1\uffff\2\42\3\uffff\2\42\1\u0162\1\42\1\u0164\2\42\1\uffff\1\42"+
        "\1\uffff\4\42\1\uffff\1\42\1\uffff\1\42\1\uffff\1\u016e\2\42\1\u0171"+
        "\2\42\1\u0174\1\uffff\7\42\1\uffff\1\42\1\uffff\1\u017d\1\u017e"+
        "\1\42\1\uffff\1\u0180\3\uffff\1\42\1\uffff\1\42\1\u0183\1\42\1\uffff"+
        "\2\42\1\uffff\11\42\1\u0191\1\uffff\1\u0192\1\uffff\1\u0193\2\42"+
        "\1\u0196\1\u0197\1\u0198\1\u0199\1\u019b\1\42\1\uffff\1\u019d\1"+
        "\42\1\uffff\2\42\1\uffff\1\u01a1\2\42\1\u01a4\3\42\1\u01a8\2\uffff"+
        "\1\42\1\uffff\2\42\1\uffff\1\u01ac\4\42\1\u01b1\2\42\1\u01b4\1\42"+
        "\1\u01b6\2\42\3\uffff\1\u01b9\1\42\4\uffff\1\42\3\uffff\1\u01bc"+
        "\1\u01bd\1\42\1\uffff\2\42\1\uffff\1\42\1\u01c2\1\42\1\uffff\1\u01c4"+
        "\2\42\1\uffff\1\42\1\u01c8\1\42\1\u01ca\1\uffff\1\u01cb\1\42\1\uffff"+
        "\1\u01cd\1\uffff\2\42\1\uffff\2\42\2\uffff\1\u01d2\1\42\1\u01d5"+
        "\2\uffff\1\u01d6\1\uffff\3\42\1\uffff\1\42\2\uffff\1\42\1\uffff"+
        "\1\u01dc\3\42\1\uffff\1\u01e0\3\uffff\1\42\1\u01e2\1\u01e3\2\42"+
        "\1\uffff\1\u01e6\2\42\1\uffff\1\42\2\uffff\2\42\2\uffff\3\42\1\uffff"+
        "\2\42\1\uffff\1\42\2\uffff";
    static final String DFA3_eofS =
        "\u01f2\uffff";
    static final String DFA3_minS =
        "\1\11\2\uffff\1\110\1\150\2\144\1\150\1\141\1\145\1\141\2\145\3"+
        "\141\1\162\1\143\1\156\1\141\1\145\1\163\1\141\1\105\1\111\1\116"+
        "\1\162\1\122\1\105\1\116\1\117\1\111\1\117\1\105\2\uffff\1\11\6"+
        "\uffff\1\11\1\uffff\1\105\1\145\1\43\1\165\1\43\1\163\1\147\1\144"+
        "\1\143\1\156\2\145\1\164\1\43\1\146\1\154\1\156\1\145\1\151\1\164"+
        "\1\160\1\147\1\164\1\141\1\154\1\155\1\157\1\141\1\43\1\164\1\154"+
        "\1\155\1\151\1\171\1\145\1\162\1\155\1\164\1\153\1\157\3\144\1\156"+
        "\1\141\1\145\2\155\1\164\1\162\1\145\1\163\1\114\1\115\1\116\1\104"+
        "\1\103\3\43\1\124\1\43\1\124\1\113\1\125\1\123\2\uffff\1\122\1\162"+
        "\1\154\1\uffff\1\155\1\uffff\1\43\2\157\2\43\1\146\1\156\1\141\1"+
        "\154\1\143\1\141\1\uffff\1\43\1\143\1\145\1\43\1\162\1\147\1\154"+
        "\1\145\1\43\2\145\2\162\1\145\1\43\1\143\1\156\1\uffff\1\167\1\145"+
        "\1\144\1\151\1\155\1\143\1\147\1\164\1\145\1\151\2\145\1\165\1\145"+
        "\1\142\1\145\1\165\2\43\1\145\1\142\1\43\1\163\1\143\1\150\1\105"+
        "\1\43\1\104\2\43\1\145\1\uffff\1\105\2\uffff\1\127\1\uffff\1\43"+
        "\1\105\1\116\1\103\1\105\1\145\1\165\1\151\1\uffff\1\43\1\164\2"+
        "\uffff\1\151\1\145\2\164\1\144\1\153\1\161\1\uffff\1\43\1\141\1"+
        "\uffff\1\43\1\144\1\165\1\43\1\uffff\2\43\1\164\1\165\1\145\1\143"+
        "\1\uffff\1\153\1\143\1\145\2\43\1\154\2\144\1\162\1\43\1\156\1\43"+
        "\1\163\1\43\1\160\1\163\1\141\1\171\1\151\1\166\1\155\2\uffff\1"+
        "\43\1\145\1\166\1\151\1\163\1\165\1\uffff\1\151\1\164\1\43\1\103"+
        "\1\uffff\1\43\2\uffff\1\162\1\122\1\105\1\uffff\1\43\1\124\3\43"+
        "\1\155\1\43\1\uffff\1\141\1\147\1\156\1\43\1\145\1\43\1\163\1\145"+
        "\1\171\1\165\1\uffff\1\163\1\uffff\1\145\1\155\3\uffff\1\145\1\163"+
        "\1\43\1\164\1\43\1\150\1\145\1\uffff\1\166\1\uffff\1\171\2\163\1"+
        "\160\1\uffff\1\164\1\uffff\1\164\1\uffff\1\43\1\143\1\164\1\43\1"+
        "\155\1\156\1\43\1\uffff\1\162\1\145\1\154\1\163\1\156\1\157\1\151"+
        "\1\uffff\1\124\1\uffff\2\43\1\105\1\uffff\1\43\3\uffff\1\151\1\uffff"+
        "\1\164\1\43\1\164\1\uffff\1\142\1\145\1\uffff\1\165\1\164\1\160"+
        "\1\141\1\145\1\163\2\151\1\166\1\43\1\uffff\1\43\1\uffff\1\43\1"+
        "\156\1\145\5\43\1\50\1\uffff\1\43\1\145\1\uffff\1\145\1\165\1\uffff"+
        "\1\43\1\156\1\145\1\43\1\163\1\156\1\157\1\43\2\uffff\1\116\1\uffff"+
        "\1\156\1\151\1\uffff\1\43\1\141\1\171\1\154\1\155\1\43\1\145\1\154"+
        "\1\43\1\143\1\43\1\155\1\156\3\uffff\1\43\1\156\4\uffff\1\145\3"+
        "\uffff\2\43\1\155\1\uffff\1\164\1\163\1\uffff\1\50\1\43\1\156\1"+
        "\uffff\1\43\2\157\1\uffff\1\164\1\43\1\145\1\43\1\uffff\1\43\1\151"+
        "\1\uffff\1\43\1\uffff\1\145\1\165\1\uffff\1\164\1\154\2\uffff\1"+
        "\43\1\163\1\43\2\uffff\1\43\1\uffff\1\163\1\156\1\145\1\uffff\1"+
        "\141\2\uffff\1\164\1\uffff\1\43\1\155\1\163\1\145\1\uffff\1\43\3"+
        "\uffff\1\151\2\43\1\163\1\171\1\uffff\1\43\1\50\1\141\1\uffff\1"+
        "\164\2\uffff\1\145\1\50\2\uffff\1\163\1\171\1\50\1\uffff\1\145\1"+
        "\50\1\uffff\1\50\2\uffff";
    static final String DFA3_maxS =
        "\1\172\2\uffff\1\110\1\150\1\156\1\163\1\162\1\161\1\165\1\171\1"+
        "\165\1\171\1\151\1\163\1\165\1\162\1\157\1\170\1\165\1\145\1\163"+
        "\1\141\1\125\1\111\1\123\1\162\1\122\1\131\1\116\1\117\1\111\1\117"+
        "\1\105\2\uffff\1\40\6\uffff\1\40\1\uffff\1\105\1\145\1\172\1\165"+
        "\1\172\1\163\1\147\1\156\1\143\1\165\1\145\1\151\1\164\1\172\1\163"+
        "\1\154\1\156\1\145\1\151\1\164\1\160\1\147\1\172\1\157\1\154\1\155"+
        "\1\157\1\141\1\172\1\164\1\156\1\155\1\157\1\171\1\145\1\162\1\155"+
        "\1\164\1\153\1\157\3\144\1\156\1\141\1\145\2\155\1\164\1\162\1\145"+
        "\1\163\1\114\1\115\1\116\1\104\1\103\3\172\1\124\1\172\1\124\1\113"+
        "\1\125\1\123\2\uffff\1\122\1\162\1\154\1\uffff\1\155\1\uffff\1\172"+
        "\2\157\2\172\1\164\1\156\1\141\1\154\1\143\1\141\1\uffff\1\172\1"+
        "\143\1\145\1\172\1\162\1\147\1\154\1\145\1\172\2\145\1\164\1\162"+
        "\1\145\1\172\1\143\1\156\1\uffff\1\167\1\145\1\144\1\151\1\155\1"+
        "\143\1\147\1\164\1\145\1\151\2\145\1\165\1\145\1\144\1\164\1\165"+
        "\2\172\1\145\1\162\1\172\1\163\1\143\1\150\1\105\1\172\1\104\2\172"+
        "\1\145\1\uffff\1\105\2\uffff\1\127\1\uffff\1\172\1\105\1\116\1\103"+
        "\1\105\1\145\1\165\1\151\1\uffff\1\172\1\164\2\uffff\1\151\1\145"+
        "\2\164\1\144\1\153\1\164\1\uffff\1\172\1\141\1\uffff\1\172\1\144"+
        "\1\165\1\172\1\uffff\2\172\1\164\1\165\1\145\1\143\1\uffff\1\153"+
        "\1\143\1\145\2\172\1\154\2\144\1\162\1\172\1\156\1\172\1\163\1\172"+
        "\1\160\1\163\1\141\1\171\1\151\1\166\1\155\2\uffff\1\172\1\145\1"+
        "\166\1\151\1\163\1\165\1\uffff\1\151\1\164\1\172\1\103\1\uffff\1"+
        "\172\2\uffff\1\162\1\122\1\105\1\uffff\1\172\1\124\3\172\1\155\1"+
        "\172\1\uffff\1\141\1\147\1\156\1\172\1\145\1\172\1\163\1\145\1\171"+
        "\1\165\1\uffff\1\163\1\uffff\1\145\1\155\3\uffff\1\164\1\163\1\172"+
        "\1\164\1\172\1\150\1\145\1\uffff\1\166\1\uffff\1\171\2\163\1\160"+
        "\1\uffff\1\164\1\uffff\1\164\1\uffff\1\172\1\143\1\164\1\172\1\155"+
        "\1\156\1\172\1\uffff\1\162\1\145\1\154\1\163\1\156\1\157\1\151\1"+
        "\uffff\1\124\1\uffff\2\172\1\105\1\uffff\1\172\3\uffff\1\151\1\uffff"+
        "\1\164\1\172\1\164\1\uffff\1\144\1\145\1\uffff\1\165\1\164\1\160"+
        "\1\141\1\145\1\163\2\151\1\166\1\172\1\uffff\1\172\1\uffff\1\172"+
        "\1\156\1\145\5\172\1\50\1\uffff\1\172\1\145\1\uffff\1\145\1\165"+
        "\1\uffff\1\172\1\156\1\145\1\172\1\163\1\156\1\157\1\172\2\uffff"+
        "\1\116\1\uffff\1\156\1\151\1\uffff\1\172\1\141\1\171\1\154\1\155"+
        "\1\172\1\145\1\154\1\172\1\143\1\172\1\155\1\156\3\uffff\1\172\1"+
        "\156\4\uffff\1\145\3\uffff\2\172\1\155\1\uffff\1\164\1\163\1\uffff"+
        "\1\50\1\172\1\156\1\uffff\1\172\2\157\1\uffff\1\164\1\172\1\145"+
        "\1\172\1\uffff\1\172\1\151\1\uffff\1\172\1\uffff\1\145\1\165\1\uffff"+
        "\1\164\1\154\2\uffff\1\172\1\163\1\172\2\uffff\1\172\1\uffff\1\163"+
        "\1\156\1\145\1\uffff\1\141\2\uffff\1\164\1\uffff\1\172\1\155\1\163"+
        "\1\145\1\uffff\1\172\3\uffff\1\151\2\172\1\163\1\171\1\uffff\1\172"+
        "\1\50\1\141\1\uffff\1\164\2\uffff\1\145\1\50\2\uffff\1\163\1\171"+
        "\1\50\1\uffff\1\145\1\50\1\uffff\1\50\2\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\1\2\37\uffff\1\140\1\141\1\uffff\1\143\1\144\1\145"+
        "\1\146\1\147\1\150\1\uffff\1\152\102\uffff\1\142\1\151\3\uffff\1"+
        "\5\1\uffff\1\60\13\uffff\1\22\21\uffff\1\116\37\uffff\1\120\1\uffff"+
        "\1\121\1\117\1\uffff\1\122\10\uffff\1\6\2\uffff\1\112\1\132\7\uffff"+
        "\1\63\2\uffff\1\20\4\uffff\1\66\6\uffff\1\130\25\uffff\1\65\1\73"+
        "\6\uffff\1\123\4\uffff\1\131\1\uffff\1\113\1\133\3\uffff\1\124\7"+
        "\uffff\1\27\12\uffff\1\134\1\uffff\1\12\2\uffff\1\57\1\13\1\54\7"+
        "\uffff\1\15\1\uffff\1\110\4\uffff\1\26\1\uffff\1\21\1\uffff\1\125"+
        "\7\uffff\1\42\7\uffff\1\70\1\uffff\1\111\3\uffff\1\126\1\uffff\1"+
        "\135\1\3\1\4\1\uffff\1\23\3\uffff\1\55\2\uffff\1\62\12\uffff\1\53"+
        "\1\uffff\1\14\11\uffff\1\25\2\uffff\1\41\2\uffff\1\64\10\uffff\1"+
        "\114\1\115\1\uffff\1\127\2\uffff\1\7\15\uffff\1\56\1\106\1\33\2"+
        "\uffff\1\72\1\16\1\17\1\24\1\uffff\1\61\1\101\1\31\3\uffff\1\44"+
        "\2\uffff\1\51\3\uffff\1\107\3\uffff\1\71\4\uffff\1\10\2\uffff\1"+
        "\11\1\uffff\1\52\2\uffff\1\136\2\uffff\1\35\1\37\3\uffff\1\76\1"+
        "\43\1\uffff\1\137\3\uffff\1\40\1\uffff\1\75\1\30\1\uffff\1\32\4"+
        "\uffff\1\46\1\uffff\1\77\1\50\1\67\5\uffff\1\36\3\uffff\1\47\1\uffff"+
        "\1\74\1\34\2\uffff\1\45\1\105\3\uffff\1\100\2\uffff\1\103\1\uffff"+
        "\1\104\1\102";
    static final String DFA3_specialS =
        "\u01f2\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\54\1\53\1\uffff\2\54\22\uffff\1\44\1\51\1\uffff\1\42\1\uffff"+
            "\1\42\1\52\1\uffff\1\1\1\2\1\42\1\uffff\1\43\1\42\1\45\14\42"+
            "\1\uffff\1\47\1\50\1\46\2\uffff\1\31\1\34\1\40\1\41\1\42\1\30"+
            "\2\42\1\35\2\42\1\37\1\42\1\36\1\33\3\42\1\27\3\42\1\3\3\42"+
            "\4\uffff\1\42\1\uffff\1\6\1\14\1\7\1\10\1\22\1\15\1\20\1\26"+
            "\1\5\2\42\1\17\1\21\1\23\1\32\1\16\1\42\1\11\1\13\1\12\1\42"+
            "\1\24\1\4\1\25\2\42",
            "",
            "",
            "\1\55",
            "\1\56",
            "\1\61\7\uffff\1\60\1\uffff\1\57",
            "\1\62\7\uffff\1\63\1\uffff\1\64\4\uffff\1\65",
            "\1\70\6\uffff\1\66\2\uffff\1\67",
            "\1\71\3\uffff\1\73\13\uffff\1\72",
            "\1\74\17\uffff\1\75",
            "\1\102\7\uffff\1\76\5\uffff\1\100\2\uffff\1\77\6\uffff\1\101",
            "\1\105\3\uffff\1\103\12\uffff\1\104\1\106",
            "\1\112\6\uffff\1\107\5\uffff\1\110\6\uffff\1\111",
            "\1\114\7\uffff\1\113",
            "\1\120\6\uffff\1\116\11\uffff\1\115\1\117",
            "\1\122\7\uffff\1\123\13\uffff\1\121",
            "\1\124",
            "\1\125\13\uffff\1\126",
            "\1\127\3\uffff\1\131\3\uffff\1\130\1\uffff\1\132",
            "\1\133\15\uffff\1\135\5\uffff\1\134",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141\17\uffff\1\142",
            "\1\143",
            "\1\144\4\uffff\1\145",
            "\1\146",
            "\1\147",
            "\1\151\23\uffff\1\150",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "",
            "",
            "\2\54\1\uffff\2\54\22\uffff\1\54",
            "",
            "",
            "",
            "",
            "",
            "",
            "\2\54\1\uffff\2\54\22\uffff\1\54",
            "",
            "\1\161",
            "\1\162",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\23\42\1\163\6\42",
            "\1\165",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\167",
            "\1\170",
            "\1\172\11\uffff\1\171",
            "\1\173",
            "\1\174\6\uffff\1\175",
            "\1\176",
            "\1\u0080\3\uffff\1\177",
            "\1\u0081",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0083\14\uffff\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c\5\uffff\1\u008d",
            "\1\u008e\15\uffff\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0095",
            "\1\u0096\1\uffff\1\u0097",
            "\1\u0098",
            "\1\u0099\5\uffff\1\u009a",
            "\1\u009b",
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
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\3\42\1\u00b3\26\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\3\42\1\u00b5\26\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00b8",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "",
            "",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "",
            "\1\u00c1",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00c3",
            "\1\u00c4",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00c7\15\uffff\1\u00c8",
            "\1\u00c9",
            "\1\u00ca",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00cf",
            "\1\u00d0",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00d2",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00d7",
            "\1\u00d8",
            "\1\u00d9\1\uffff\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00de",
            "\1\u00df",
            "",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\1\u00ea",
            "\1\u00eb",
            "\1\u00ec",
            "\1\u00ed",
            "\1\u00ef\1\uffff\1\u00ee",
            "\1\u00f1\16\uffff\1\u00f0",
            "\1\u00f2",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00f5",
            "\1\u00f6\2\uffff\1\u00f7\1\u00f8\5\uffff\1\u00f9\5\uffff\1"+
            "\u00fa",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00fc",
            "\1\u00fd",
            "\1\u00fe",
            "\1\u00ff",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0101",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0104",
            "",
            "\1\u0105",
            "",
            "",
            "\1\u0106",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0108",
            "\1\u0109",
            "\1\u010a",
            "\1\u010b",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0110",
            "",
            "",
            "\1\u0111",
            "\1\u0112",
            "\1\u0113",
            "\1\u0114",
            "\1\u0115",
            "\1\u0116",
            "\1\u0119\1\uffff\1\u0117\1\u0118",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u011b",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u011d",
            "\1\u011e",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0122",
            "\1\u0123",
            "\1\u0124",
            "\1\u0125",
            "",
            "\1\u0126",
            "\1\u0127",
            "\1\u0128",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\4\42\1\u012a\25\42",
            "\1\u012c",
            "\1\u012d",
            "\1\u012e",
            "\1\u012f",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0131",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0133",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0135",
            "\1\u0136",
            "\1\u0137",
            "\1\u0138",
            "\1\u0139",
            "\1\u013a",
            "\1\u013b",
            "",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u013d",
            "\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\u0141",
            "",
            "\1\u0142",
            "\1\u0143",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0145",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "\1\u0147",
            "\1\u0148",
            "\1\u0149",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u014b",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u014f",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\u0151",
            "\1\u0152",
            "\1\u0153",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0155",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\21\42\1\u0156\10\42",
            "\1\u0158",
            "\1\u0159",
            "\1\u015a",
            "\1\u015b",
            "",
            "\1\u015c",
            "",
            "\1\u015d",
            "\1\u015e",
            "",
            "",
            "",
            "\1\u0160\16\uffff\1\u015f",
            "\1\u0161",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0163",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0165",
            "\1\u0166",
            "",
            "\1\u0167",
            "",
            "\1\u0168",
            "\1\u0169",
            "\1\u016a",
            "\1\u016b",
            "",
            "\1\u016c",
            "",
            "\1\u016d",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u016f",
            "\1\u0170",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0172",
            "\1\u0173",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\u0175",
            "\1\u0176",
            "\1\u0177",
            "\1\u0178",
            "\1\u0179",
            "\1\u017a",
            "\1\u017b",
            "",
            "\1\u017c",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u017f",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "",
            "\1\u0181",
            "",
            "\1\u0182",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0184",
            "",
            "\1\u0186\1\uffff\1\u0185",
            "\1\u0187",
            "",
            "\1\u0188",
            "\1\u0189",
            "\1\u018a",
            "\1\u018b",
            "\1\u018c",
            "\1\u018d",
            "\1\u018e",
            "\1\u018f",
            "\1\u0190",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0194",
            "\1\u0195",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\21\42\1\u019a\10\42",
            "\1\u019c",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u019e",
            "",
            "\1\u019f",
            "\1\u01a0",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01a2",
            "\1\u01a3",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01a5",
            "\1\u01a6",
            "\1\u01a7",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "\1\u01a9",
            "",
            "\1\u01aa",
            "\1\u01ab",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01ad",
            "\1\u01ae",
            "\1\u01af",
            "\1\u01b0",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01b2",
            "\1\u01b3",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01b5",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01b7",
            "\1\u01b8",
            "",
            "",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01ba",
            "",
            "",
            "",
            "",
            "\1\u01bb",
            "",
            "",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01be",
            "",
            "\1\u01bf",
            "\1\u01c0",
            "",
            "\1\u01c1",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01c3",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01c5",
            "\1\u01c6",
            "",
            "\1\u01c7",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01c9",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01cc",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\u01ce",
            "\1\u01cf",
            "",
            "\1\u01d0",
            "\1\u01d1",
            "",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01d3",
            "\1\42\1\uffff\1\42\2\uffff\1\u01d4\1\uffff\1\42\2\uffff\1\42"+
            "\1\uffff\14\42\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\u01d7",
            "\1\u01d8",
            "\1\u01d9",
            "",
            "\1\u01da",
            "",
            "",
            "\1\u01db",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01dd",
            "\1\u01de",
            "\1\u01df",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "",
            "\1\u01e1",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01e4",
            "\1\u01e5",
            "",
            "\1\42\1\uffff\1\42\4\uffff\1\42\2\uffff\1\42\1\uffff\14\42"+
            "\6\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u01e7",
            "\1\u01e8",
            "",
            "\1\u01e9",
            "",
            "",
            "\1\u01ea",
            "\1\u01eb",
            "",
            "",
            "\1\u01ec",
            "\1\u01ed",
            "\1\u01ee",
            "",
            "\1\u01ef",
            "\1\u01f0",
            "",
            "\1\u01f1",
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
            return "1:1: Tokens : ( T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | VALUE | COMMA | SPACE | DOT | GT | LT | EQ | NOT | AMP | NL | WS );";
        }
    }
 

}
