package dbs.search.parser;
// $ANTLR 3.0.1 Sql.g 2008-11-17 11:00:43

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlLexer extends Lexer {
    public static final int LT=9;
    public static final int T29=29;
    public static final int T28=28;
    public static final int T27=27;
    public static final int NOT=11;
    public static final int T26=26;
    public static final int T25=25;
    public static final int T24=24;
    public static final int EOF=-1;
    public static final int T23=23;
    public static final int T22=22;
    public static final int T21=21;
    public static final int T20=20;
    public static final int T38=38;
    public static final int T37=37;
    public static final int NL=13;
    public static final int T39=39;
    public static final int EQ=8;
    public static final int T34=34;
    public static final int T33=33;
    public static final int T36=36;
    public static final int T35=35;
    public static final int T30=30;
    public static final int T32=32;
    public static final int T31=31;
    public static final int T49=49;
    public static final int T48=48;
    public static final int VALUE=7;
    public static final int T43=43;
    public static final int T42=42;
    public static final int T41=41;
    public static final int T40=40;
    public static final int T47=47;
    public static final int T46=46;
    public static final int T45=45;
    public static final int T44=44;
    public static final int WS=14;
    public static final int T50=50;
    public static final int T59=59;
    public static final int GT=10;
    public static final int T52=52;
    public static final int T51=51;
    public static final int T54=54;
    public static final int T53=53;
    public static final int T56=56;
    public static final int T55=55;
    public static final int T58=58;
    public static final int T57=57;
    public static final int T75=75;
    public static final int T76=76;
    public static final int T73=73;
    public static final int T74=74;
    public static final int T79=79;
    public static final int T77=77;
    public static final int T78=78;
    public static final int AMP=12;
    public static final int SPACE=4;
    public static final int T72=72;
    public static final int T71=71;
    public static final int T70=70;
    public static final int T62=62;
    public static final int T63=63;
    public static final int T64=64;
    public static final int T65=65;
    public static final int T66=66;
    public static final int T67=67;
    public static final int T68=68;
    public static final int T69=69;
    public static final int COMMA=5;
    public static final int DOT=6;
    public static final int T61=61;
    public static final int T60=60;
    public static final int T97=97;
    public static final int T98=98;
    public static final int T95=95;
    public static final int T96=96;
    public static final int T94=94;
    public static final int Tokens=99;
    public static final int T93=93;
    public static final int T92=92;
    public static final int T91=91;
    public static final int T90=90;
    public static final int T88=88;
    public static final int T89=89;
    public static final int T84=84;
    public static final int T85=85;
    public static final int T86=86;
    public static final int T87=87;
    public static final int T15=15;
    public static final int T81=81;
    public static final int T16=16;
    public static final int T80=80;
    public static final int T17=17;
    public static final int T83=83;
    public static final int T18=18;
    public static final int T82=82;
    public static final int T19=19;
    public SqlLexer() {;} 
    public SqlLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "Sql.g"; }

    // $ANTLR start T15
    public final void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // Sql.g:3:5: ( '(' )
            // Sql.g:3:7: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // Sql.g:4:5: ( ')' )
            // Sql.g:4:7: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // Sql.g:5:5: ( 'WHERE' )
            // Sql.g:5:7: 'WHERE'
            {
            match("WHERE"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start T18
    public final void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // Sql.g:6:5: ( 'where' )
            // Sql.g:6:7: 'where'
            {
            match("where"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public final void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // Sql.g:7:5: ( 'in' )
            // Sql.g:7:7: 'in'
            {
            match("in"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public final void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // Sql.g:8:5: ( 'ads' )
            // Sql.g:8:7: 'ads'
            {
            match("ads"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public final void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // Sql.g:9:5: ( 'dataset' )
            // Sql.g:9:7: 'dataset'
            {
            match("dataset"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public final void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // Sql.g:10:5: ( 'release' )
            // Sql.g:10:7: 'release'
            {
            match("release"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start T23
    public final void mT23() throws RecognitionException {
        try {
            int _type = T23;
            // Sql.g:11:5: ( 'tier' )
            // Sql.g:11:7: 'tier'
            {
            match("tier"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T23

    // $ANTLR start T24
    public final void mT24() throws RecognitionException {
        try {
            int _type = T24;
            // Sql.g:12:5: ( 'site' )
            // Sql.g:12:7: 'site'
            {
            match("site"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T24

    // $ANTLR start T25
    public final void mT25() throws RecognitionException {
        try {
            int _type = T25;
            // Sql.g:13:5: ( 'block' )
            // Sql.g:13:7: 'block'
            {
            match("block"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T25

    // $ANTLR start T26
    public final void mT26() throws RecognitionException {
        try {
            int _type = T26;
            // Sql.g:14:5: ( 'file' )
            // Sql.g:14:7: 'file'
            {
            match("file"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T26

    // $ANTLR start T27
    public final void mT27() throws RecognitionException {
        try {
            int _type = T27;
            // Sql.g:15:5: ( 'primds' )
            // Sql.g:15:7: 'primds'
            {
            match("primds"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T27

    // $ANTLR start T28
    public final void mT28() throws RecognitionException {
        try {
            int _type = T28;
            // Sql.g:16:5: ( 'procds' )
            // Sql.g:16:7: 'procds'
            {
            match("procds"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T28

    // $ANTLR start T29
    public final void mT29() throws RecognitionException {
        try {
            int _type = T29;
            // Sql.g:17:5: ( 'run' )
            // Sql.g:17:7: 'run'
            {
            match("run"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T29

    // $ANTLR start T30
    public final void mT30() throws RecognitionException {
        try {
            int _type = T30;
            // Sql.g:18:5: ( 'lumi' )
            // Sql.g:18:7: 'lumi'
            {
            match("lumi"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T30

    // $ANTLR start T31
    public final void mT31() throws RecognitionException {
        try {
            int _type = T31;
            // Sql.g:19:5: ( 'dq' )
            // Sql.g:19:7: 'dq'
            {
            match("dq"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T31

    // $ANTLR start T32
    public final void mT32() throws RecognitionException {
        try {
            int _type = T32;
            // Sql.g:20:5: ( 'ilumi' )
            // Sql.g:20:7: 'ilumi'
            {
            match("ilumi"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T32

    // $ANTLR start T33
    public final void mT33() throws RecognitionException {
        try {
            int _type = T33;
            // Sql.g:21:5: ( 'phygrp' )
            // Sql.g:21:7: 'phygrp'
            {
            match("phygrp"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T33

    // $ANTLR start T34
    public final void mT34() throws RecognitionException {
        try {
            int _type = T34;
            // Sql.g:22:5: ( 'group' )
            // Sql.g:22:7: 'group'
            {
            match("group"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T34

    // $ANTLR start T35
    public final void mT35() throws RecognitionException {
        try {
            int _type = T35;
            // Sql.g:23:5: ( 'pset' )
            // Sql.g:23:7: 'pset'
            {
            match("pset"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T35

    // $ANTLR start T36
    public final void mT36() throws RecognitionException {
        try {
            int _type = T36;
            // Sql.g:24:5: ( 'algo' )
            // Sql.g:24:7: 'algo'
            {
            match("algo"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T36

    // $ANTLR start T37
    public final void mT37() throws RecognitionException {
        try {
            int _type = T37;
            // Sql.g:25:5: ( 'createdate' )
            // Sql.g:25:7: 'createdate'
            {
            match("createdate"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T37

    // $ANTLR start T38
    public final void mT38() throws RecognitionException {
        try {
            int _type = T38;
            // Sql.g:26:5: ( 'moddate' )
            // Sql.g:26:7: 'moddate'
            {
            match("moddate"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T38

    // $ANTLR start T39
    public final void mT39() throws RecognitionException {
        try {
            int _type = T39;
            // Sql.g:27:5: ( 'starttime' )
            // Sql.g:27:7: 'starttime'
            {
            match("starttime"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T39

    // $ANTLR start T40
    public final void mT40() throws RecognitionException {
        try {
            int _type = T40;
            // Sql.g:28:5: ( 'endtime' )
            // Sql.g:28:7: 'endtime'
            {
            match("endtime"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T40

    // $ANTLR start T41
    public final void mT41() throws RecognitionException {
        try {
            int _type = T41;
            // Sql.g:29:5: ( 'createby' )
            // Sql.g:29:7: 'createby'
            {
            match("createby"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T41

    // $ANTLR start T42
    public final void mT42() throws RecognitionException {
        try {
            int _type = T42;
            // Sql.g:30:5: ( 'modby' )
            // Sql.g:30:7: 'modby'
            {
            match("modby"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T42

    // $ANTLR start T43
    public final void mT43() throws RecognitionException {
        try {
            int _type = T43;
            // Sql.g:31:5: ( 'name' )
            // Sql.g:31:7: 'name'
            {
            match("name"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T43

    // $ANTLR start T44
    public final void mT44() throws RecognitionException {
        try {
            int _type = T44;
            // Sql.g:32:5: ( 'version' )
            // Sql.g:32:7: 'version'
            {
            match("version"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T44

    // $ANTLR start T45
    public final void mT45() throws RecognitionException {
        try {
            int _type = T45;
            // Sql.g:33:5: ( 'number' )
            // Sql.g:33:7: 'number'
            {
            match("number"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T45

    // $ANTLR start T46
    public final void mT46() throws RecognitionException {
        try {
            int _type = T46;
            // Sql.g:34:5: ( 'startevnum' )
            // Sql.g:34:7: 'startevnum'
            {
            match("startevnum"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T46

    // $ANTLR start T47
    public final void mT47() throws RecognitionException {
        try {
            int _type = T47;
            // Sql.g:35:5: ( 'endevnum' )
            // Sql.g:35:7: 'endevnum'
            {
            match("endevnum"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T47

    // $ANTLR start T48
    public final void mT48() throws RecognitionException {
        try {
            int _type = T48;
            // Sql.g:36:5: ( 'numevents' )
            // Sql.g:36:7: 'numevents'
            {
            match("numevents"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T48

    // $ANTLR start T49
    public final void mT49() throws RecognitionException {
        try {
            int _type = T49;
            // Sql.g:37:5: ( 'numlss' )
            // Sql.g:37:7: 'numlss'
            {
            match("numlss"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T49

    // $ANTLR start T50
    public final void mT50() throws RecognitionException {
        try {
            int _type = T50;
            // Sql.g:38:5: ( 'size' )
            // Sql.g:38:7: 'size'
            {
            match("size"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T50

    // $ANTLR start T51
    public final void mT51() throws RecognitionException {
        try {
            int _type = T51;
            // Sql.g:39:5: ( 'count' )
            // Sql.g:39:7: 'count'
            {
            match("count"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T51

    // $ANTLR start T52
    public final void mT52() throws RecognitionException {
        try {
            int _type = T52;
            // Sql.g:40:5: ( 'status' )
            // Sql.g:40:7: 'status'
            {
            match("status"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T52

    // $ANTLR start T53
    public final void mT53() throws RecognitionException {
        try {
            int _type = T53;
            // Sql.g:41:5: ( 'type' )
            // Sql.g:41:7: 'type'
            {
            match("type"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T53

    // $ANTLR start T54
    public final void mT54() throws RecognitionException {
        try {
            int _type = T54;
            // Sql.g:42:5: ( 'id' )
            // Sql.g:42:7: 'id'
            {
            match("id"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T54

    // $ANTLR start T55
    public final void mT55() throws RecognitionException {
        try {
            int _type = T55;
            // Sql.g:43:5: ( 'parent' )
            // Sql.g:43:7: 'parent'
            {
            match("parent"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T55

    // $ANTLR start T56
    public final void mT56() throws RecognitionException {
        try {
            int _type = T56;
            // Sql.g:44:5: ( 'child' )
            // Sql.g:44:7: 'child'
            {
            match("child"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T56

    // $ANTLR start T57
    public final void mT57() throws RecognitionException {
        try {
            int _type = T57;
            // Sql.g:45:5: ( 'def' )
            // Sql.g:45:7: 'def'
            {
            match("def"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T57

    // $ANTLR start T58
    public final void mT58() throws RecognitionException {
        try {
            int _type = T58;
            // Sql.g:46:5: ( 'evnum' )
            // Sql.g:46:7: 'evnum'
            {
            match("evnum"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T58

    // $ANTLR start T59
    public final void mT59() throws RecognitionException {
        try {
            int _type = T59;
            // Sql.g:47:5: ( 'era' )
            // Sql.g:47:7: 'era'
            {
            match("era"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T59

    // $ANTLR start T60
    public final void mT60() throws RecognitionException {
        try {
            int _type = T60;
            // Sql.g:48:5: ( 'tag' )
            // Sql.g:48:7: 'tag'
            {
            match("tag"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T60

    // $ANTLR start T61
    public final void mT61() throws RecognitionException {
        try {
            int _type = T61;
            // Sql.g:49:5: ( 'hash' )
            // Sql.g:49:7: 'hash'
            {
            match("hash"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T61

    // $ANTLR start T62
    public final void mT62() throws RecognitionException {
        try {
            int _type = T62;
            // Sql.g:50:5: ( 'content' )
            // Sql.g:50:7: 'content'
            {
            match("content"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T62

    // $ANTLR start T63
    public final void mT63() throws RecognitionException {
        try {
            int _type = T63;
            // Sql.g:51:5: ( 'family' )
            // Sql.g:51:7: 'family'
            {
            match("family"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T63

    // $ANTLR start T64
    public final void mT64() throws RecognitionException {
        try {
            int _type = T64;
            // Sql.g:52:5: ( 'exe' )
            // Sql.g:52:7: 'exe'
            {
            match("exe"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T64

    // $ANTLR start T65
    public final void mT65() throws RecognitionException {
        try {
            int _type = T65;
            // Sql.g:53:5: ( 'numruns()' )
            // Sql.g:53:7: 'numruns()'
            {
            match("numruns()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T65

    // $ANTLR start T66
    public final void mT66() throws RecognitionException {
        try {
            int _type = T66;
            // Sql.g:54:5: ( 'numfiles()' )
            // Sql.g:54:7: 'numfiles()'
            {
            match("numfiles()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T66

    // $ANTLR start T67
    public final void mT67() throws RecognitionException {
        try {
            int _type = T67;
            // Sql.g:55:5: ( 'dataquality()' )
            // Sql.g:55:7: 'dataquality()'
            {
            match("dataquality()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T67

    // $ANTLR start T68
    public final void mT68() throws RecognitionException {
        try {
            int _type = T68;
            // Sql.g:56:5: ( 'latest()' )
            // Sql.g:56:7: 'latest()'
            {
            match("latest()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T68

    // $ANTLR start T69
    public final void mT69() throws RecognitionException {
        try {
            int _type = T69;
            // Sql.g:57:5: ( 'parentrelease()' )
            // Sql.g:57:7: 'parentrelease()'
            {
            match("parentrelease()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T69

    // $ANTLR start T70
    public final void mT70() throws RecognitionException {
        try {
            int _type = T70;
            // Sql.g:58:5: ( 'childrelease()' )
            // Sql.g:58:7: 'childrelease()'
            {
            match("childrelease()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T70

    // $ANTLR start T71
    public final void mT71() throws RecognitionException {
        try {
            int _type = T71;
            // Sql.g:59:5: ( 'intluminosity()' )
            // Sql.g:59:7: 'intluminosity()'
            {
            match("intluminosity()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T71

    // $ANTLR start T72
    public final void mT72() throws RecognitionException {
        try {
            int _type = T72;
            // Sql.g:60:5: ( 'findevents()' )
            // Sql.g:60:7: 'findevents()'
            {
            match("findevents()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T72

    // $ANTLR start T73
    public final void mT73() throws RecognitionException {
        try {
            int _type = T73;
            // Sql.g:61:5: ( 'select' )
            // Sql.g:61:7: 'select'
            {
            match("select"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T73

    // $ANTLR start T74
    public final void mT74() throws RecognitionException {
        try {
            int _type = T74;
            // Sql.g:62:5: ( 'SELECT' )
            // Sql.g:62:7: 'SELECT'
            {
            match("SELECT"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T74

    // $ANTLR start T75
    public final void mT75() throws RecognitionException {
        try {
            int _type = T75;
            // Sql.g:63:5: ( 'find' )
            // Sql.g:63:7: 'find'
            {
            match("find"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T75

    // $ANTLR start T76
    public final void mT76() throws RecognitionException {
        try {
            int _type = T76;
            // Sql.g:64:5: ( 'FIND' )
            // Sql.g:64:7: 'FIND'
            {
            match("FIND"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T76

    // $ANTLR start T77
    public final void mT77() throws RecognitionException {
        try {
            int _type = T77;
            // Sql.g:65:5: ( 'and' )
            // Sql.g:65:7: 'and'
            {
            match("and"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T77

    // $ANTLR start T78
    public final void mT78() throws RecognitionException {
        try {
            int _type = T78;
            // Sql.g:66:5: ( 'AND' )
            // Sql.g:66:7: 'AND'
            {
            match("AND"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T78

    // $ANTLR start T79
    public final void mT79() throws RecognitionException {
        try {
            int _type = T79;
            // Sql.g:67:5: ( 'order' )
            // Sql.g:67:7: 'order'
            {
            match("order"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T79

    // $ANTLR start T80
    public final void mT80() throws RecognitionException {
        try {
            int _type = T80;
            // Sql.g:68:5: ( 'ORDER' )
            // Sql.g:68:7: 'ORDER'
            {
            match("ORDER"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T80

    // $ANTLR start T81
    public final void mT81() throws RecognitionException {
        try {
            int _type = T81;
            // Sql.g:69:5: ( 'by' )
            // Sql.g:69:7: 'by'
            {
            match("by"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T81

    // $ANTLR start T82
    public final void mT82() throws RecognitionException {
        try {
            int _type = T82;
            // Sql.g:70:5: ( 'BY' )
            // Sql.g:70:7: 'BY'
            {
            match("BY"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T82

    // $ANTLR start T83
    public final void mT83() throws RecognitionException {
        try {
            int _type = T83;
            // Sql.g:71:5: ( 'or' )
            // Sql.g:71:7: 'or'
            {
            match("or"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T83

    // $ANTLR start T84
    public final void mT84() throws RecognitionException {
        try {
            int _type = T84;
            // Sql.g:72:5: ( 'OR' )
            // Sql.g:72:7: 'OR'
            {
            match("OR"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T84

    // $ANTLR start T85
    public final void mT85() throws RecognitionException {
        try {
            int _type = T85;
            // Sql.g:73:5: ( 'IN' )
            // Sql.g:73:7: 'IN'
            {
            match("IN"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T85

    // $ANTLR start T86
    public final void mT86() throws RecognitionException {
        try {
            int _type = T86;
            // Sql.g:74:5: ( 'not' )
            // Sql.g:74:7: 'not'
            {
            match("not"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T86

    // $ANTLR start T87
    public final void mT87() throws RecognitionException {
        try {
            int _type = T87;
            // Sql.g:75:5: ( 'NOT' )
            // Sql.g:75:7: 'NOT'
            {
            match("NOT"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T87

    // $ANTLR start T88
    public final void mT88() throws RecognitionException {
        try {
            int _type = T88;
            // Sql.g:76:5: ( 'like' )
            // Sql.g:76:7: 'like'
            {
            match("like"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T88

    // $ANTLR start T89
    public final void mT89() throws RecognitionException {
        try {
            int _type = T89;
            // Sql.g:77:5: ( 'LIKE' )
            // Sql.g:77:7: 'LIKE'
            {
            match("LIKE"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T89

    // $ANTLR start T90
    public final void mT90() throws RecognitionException {
        try {
            int _type = T90;
            // Sql.g:78:5: ( 'COUNT' )
            // Sql.g:78:7: 'COUNT'
            {
            match("COUNT"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T90

    // $ANTLR start T91
    public final void mT91() throws RecognitionException {
        try {
            int _type = T91;
            // Sql.g:79:5: ( 'sum' )
            // Sql.g:79:7: 'sum'
            {
            match("sum"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T91

    // $ANTLR start T92
    public final void mT92() throws RecognitionException {
        try {
            int _type = T92;
            // Sql.g:80:5: ( 'SUM' )
            // Sql.g:80:7: 'SUM'
            {
            match("SUM"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T92

    // $ANTLR start T93
    public final void mT93() throws RecognitionException {
        try {
            int _type = T93;
            // Sql.g:81:5: ( 'asc' )
            // Sql.g:81:7: 'asc'
            {
            match("asc"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T93

    // $ANTLR start T94
    public final void mT94() throws RecognitionException {
        try {
            int _type = T94;
            // Sql.g:82:5: ( 'ASC' )
            // Sql.g:82:7: 'ASC'
            {
            match("ASC"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T94

    // $ANTLR start T95
    public final void mT95() throws RecognitionException {
        try {
            int _type = T95;
            // Sql.g:83:5: ( 'desc' )
            // Sql.g:83:7: 'desc'
            {
            match("desc"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T95

    // $ANTLR start T96
    public final void mT96() throws RecognitionException {
        try {
            int _type = T96;
            // Sql.g:84:5: ( 'DESC' )
            // Sql.g:84:7: 'DESC'
            {
            match("DESC"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T96

    // $ANTLR start T97
    public final void mT97() throws RecognitionException {
        try {
            int _type = T97;
            // Sql.g:85:5: ( 'between' )
            // Sql.g:85:7: 'between'
            {
            match("between"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T97

    // $ANTLR start T98
    public final void mT98() throws RecognitionException {
        try {
            int _type = T98;
            // Sql.g:86:5: ( 'BETWEEN' )
            // Sql.g:86:7: 'BETWEEN'
            {
            match("BETWEEN"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T98

    // $ANTLR start VALUE
    public final void mVALUE() throws RecognitionException {
        try {
            int _type = VALUE;
            // Sql.g:156:8: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' | '*' | '%' )+ )
            // Sql.g:156:9: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' | '*' | '%' )+
            {
            // Sql.g:156:9: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' | '*' | '%' )+
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
            	    // Sql.g:
            	    {
            	    if ( input.LA(1)=='#'||input.LA(1)=='%'||input.LA(1)=='*'||input.LA(1)=='-'||(input.LA(1)>='/' && input.LA(1)<=':')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


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

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end VALUE

    // $ANTLR start COMMA
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            // Sql.g:162:8: ( ( ',' ) )
            // Sql.g:162:9: ( ',' )
            {
            // Sql.g:162:9: ( ',' )
            // Sql.g:162:10: ','
            {
            match(','); 

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMA

    // $ANTLR start SPACE
    public final void mSPACE() throws RecognitionException {
        try {
            int _type = SPACE;
            // Sql.g:163:8: ( ( ' ' ) )
            // Sql.g:163:9: ( ' ' )
            {
            // Sql.g:163:9: ( ' ' )
            // Sql.g:163:10: ' '
            {
            match(' '); 

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SPACE

    // $ANTLR start DOT
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            // Sql.g:164:6: ( ( '.' ) )
            // Sql.g:164:7: ( '.' )
            {
            // Sql.g:164:7: ( '.' )
            // Sql.g:164:8: '.'
            {
            match('.'); 

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOT

    // $ANTLR start GT
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            // Sql.g:166:5: ( ( '>' ) )
            // Sql.g:166:6: ( '>' )
            {
            // Sql.g:166:6: ( '>' )
            // Sql.g:166:7: '>'
            {
            match('>'); 

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GT

    // $ANTLR start LT
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            // Sql.g:167:5: ( ( '<' ) )
            // Sql.g:167:6: ( '<' )
            {
            // Sql.g:167:6: ( '<' )
            // Sql.g:167:7: '<'
            {
            match('<'); 

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LT

    // $ANTLR start EQ
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            // Sql.g:168:5: ( ( '=' ) )
            // Sql.g:168:6: ( '=' )
            {
            // Sql.g:168:6: ( '=' )
            // Sql.g:168:7: '='
            {
            match('='); 

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EQ

    // $ANTLR start NOT
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            // Sql.g:169:6: ( ( '!' ) )
            // Sql.g:169:7: ( '!' )
            {
            // Sql.g:169:7: ( '!' )
            // Sql.g:169:8: '!'
            {
            match('!'); 

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NOT

    // $ANTLR start AMP
    public final void mAMP() throws RecognitionException {
        try {
            int _type = AMP;
            // Sql.g:170:6: ( ( '&' ) )
            // Sql.g:170:7: ( '&' )
            {
            // Sql.g:170:7: ( '&' )
            // Sql.g:170:8: '&'
            {
            match('&'); 

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AMP

    // $ANTLR start NL
    public final void mNL() throws RecognitionException {
        try {
            int _type = NL;
            // Sql.g:172:5: ( ( '\\n' ) )
            // Sql.g:172:6: ( '\\n' )
            {
            // Sql.g:172:6: ( '\\n' )
            // Sql.g:172:7: '\\n'
            {
            match('\n'); 

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NL

    // $ANTLR start WS
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            // Sql.g:173:6: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // Sql.g:173:8: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // Sql.g:173:8: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
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
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


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

             channel = HIDDEN; 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WS

    public void mTokens() throws RecognitionException {
        // Sql.g:1:8: ( T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | T63 | T64 | T65 | T66 | T67 | T68 | T69 | T70 | T71 | T72 | T73 | T74 | T75 | T76 | T77 | T78 | T79 | T80 | T81 | T82 | T83 | T84 | T85 | T86 | T87 | T88 | T89 | T90 | T91 | T92 | T93 | T94 | T95 | T96 | T97 | T98 | VALUE | COMMA | SPACE | DOT | GT | LT | EQ | NOT | AMP | NL | WS )
        int alt3=95;
        switch ( input.LA(1) ) {
        case '(':
            {
            alt3=1;
            }
            break;
        case ')':
            {
            alt3=2;
            }
            break;
        case 'W':
            {
            int LA3_3 = input.LA(2);

            if ( (LA3_3=='H') ) {
                int LA3_44 = input.LA(3);

                if ( (LA3_44=='E') ) {
                    int LA3_107 = input.LA(4);

                    if ( (LA3_107=='R') ) {
                        int LA3_176 = input.LA(5);

                        if ( (LA3_176=='E') ) {
                            int LA3_244 = input.LA(6);

                            if ( (LA3_244=='#'||LA3_244=='%'||LA3_244=='*'||LA3_244=='-'||(LA3_244>='/' && LA3_244<=':')||(LA3_244>='A' && LA3_244<='Z')||LA3_244=='_'||(LA3_244>='a' && LA3_244<='z')) ) {
                                alt3=85;
                            }
                            else {
                                alt3=3;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
            }
            else {
                alt3=85;}
            }
            break;
        case 'w':
            {
            int LA3_4 = input.LA(2);

            if ( (LA3_4=='h') ) {
                int LA3_45 = input.LA(3);

                if ( (LA3_45=='e') ) {
                    int LA3_108 = input.LA(4);

                    if ( (LA3_108=='r') ) {
                        int LA3_177 = input.LA(5);

                        if ( (LA3_177=='e') ) {
                            int LA3_245 = input.LA(6);

                            if ( (LA3_245=='#'||LA3_245=='%'||LA3_245=='*'||LA3_245=='-'||(LA3_245>='/' && LA3_245<=':')||(LA3_245>='A' && LA3_245<='Z')||LA3_245=='_'||(LA3_245>='a' && LA3_245<='z')) ) {
                                alt3=85;
                            }
                            else {
                                alt3=4;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
            }
            else {
                alt3=85;}
            }
            break;
        case 'i':
            {
            switch ( input.LA(2) ) {
            case 'n':
                {
                switch ( input.LA(3) ) {
                case 't':
                    {
                    int LA3_109 = input.LA(4);

                    if ( (LA3_109=='l') ) {
                        int LA3_178 = input.LA(5);

                        if ( (LA3_178=='u') ) {
                            int LA3_246 = input.LA(6);

                            if ( (LA3_246=='m') ) {
                                int LA3_302 = input.LA(7);

                                if ( (LA3_302=='i') ) {
                                    int LA3_342 = input.LA(8);

                                    if ( (LA3_342=='n') ) {
                                        int LA3_374 = input.LA(9);

                                        if ( (LA3_374=='o') ) {
                                            int LA3_395 = input.LA(10);

                                            if ( (LA3_395=='s') ) {
                                                int LA3_407 = input.LA(11);

                                                if ( (LA3_407=='i') ) {
                                                    int LA3_416 = input.LA(12);

                                                    if ( (LA3_416=='t') ) {
                                                        int LA3_423 = input.LA(13);

                                                        if ( (LA3_423=='y') ) {
                                                            int LA3_427 = input.LA(14);

                                                            if ( (LA3_427=='(') ) {
                                                                alt3=57;
                                                            }
                                                            else {
                                                                alt3=85;}
                                                        }
                                                        else {
                                                            alt3=85;}
                                                    }
                                                    else {
                                                        alt3=85;}
                                                }
                                                else {
                                                    alt3=85;}
                                            }
                                            else {
                                                alt3=85;}
                                        }
                                        else {
                                            alt3=85;}
                                    }
                                    else {
                                        alt3=85;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                    }
                    break;
                case '#':
                case '%':
                case '*':
                case '-':
                case '/':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case ':':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case '_':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt3=85;
                    }
                    break;
                default:
                    alt3=5;}

                }
                break;
            case 'l':
                {
                int LA3_47 = input.LA(3);

                if ( (LA3_47=='u') ) {
                    int LA3_111 = input.LA(4);

                    if ( (LA3_111=='m') ) {
                        int LA3_179 = input.LA(5);

                        if ( (LA3_179=='i') ) {
                            int LA3_247 = input.LA(6);

                            if ( (LA3_247=='#'||LA3_247=='%'||LA3_247=='*'||LA3_247=='-'||(LA3_247>='/' && LA3_247<=':')||(LA3_247>='A' && LA3_247<='Z')||LA3_247=='_'||(LA3_247>='a' && LA3_247<='z')) ) {
                                alt3=85;
                            }
                            else {
                                alt3=18;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'd':
                {
                int LA3_48 = input.LA(3);

                if ( (LA3_48=='#'||LA3_48=='%'||LA3_48=='*'||LA3_48=='-'||(LA3_48>='/' && LA3_48<=':')||(LA3_48>='A' && LA3_48<='Z')||LA3_48=='_'||(LA3_48>='a' && LA3_48<='z')) ) {
                    alt3=85;
                }
                else {
                    alt3=40;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'a':
            {
            switch ( input.LA(2) ) {
            case 'd':
                {
                int LA3_49 = input.LA(3);

                if ( (LA3_49=='s') ) {
                    int LA3_113 = input.LA(4);

                    if ( (LA3_113=='#'||LA3_113=='%'||LA3_113=='*'||LA3_113=='-'||(LA3_113>='/' && LA3_113<=':')||(LA3_113>='A' && LA3_113<='Z')||LA3_113=='_'||(LA3_113>='a' && LA3_113<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=6;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'l':
                {
                int LA3_50 = input.LA(3);

                if ( (LA3_50=='g') ) {
                    int LA3_114 = input.LA(4);

                    if ( (LA3_114=='o') ) {
                        int LA3_181 = input.LA(5);

                        if ( (LA3_181=='#'||LA3_181=='%'||LA3_181=='*'||LA3_181=='-'||(LA3_181>='/' && LA3_181<=':')||(LA3_181>='A' && LA3_181<='Z')||LA3_181=='_'||(LA3_181>='a' && LA3_181<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=22;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'n':
                {
                int LA3_51 = input.LA(3);

                if ( (LA3_51=='d') ) {
                    int LA3_115 = input.LA(4);

                    if ( (LA3_115=='#'||LA3_115=='%'||LA3_115=='*'||LA3_115=='-'||(LA3_115>='/' && LA3_115<=':')||(LA3_115>='A' && LA3_115<='Z')||LA3_115=='_'||(LA3_115>='a' && LA3_115<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=63;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 's':
                {
                int LA3_52 = input.LA(3);

                if ( (LA3_52=='c') ) {
                    int LA3_116 = input.LA(4);

                    if ( (LA3_116=='#'||LA3_116=='%'||LA3_116=='*'||LA3_116=='-'||(LA3_116>='/' && LA3_116<=':')||(LA3_116>='A' && LA3_116<='Z')||LA3_116=='_'||(LA3_116>='a' && LA3_116<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=79;}
                }
                else {
                    alt3=85;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'd':
            {
            switch ( input.LA(2) ) {
            case 'a':
                {
                int LA3_53 = input.LA(3);

                if ( (LA3_53=='t') ) {
                    int LA3_117 = input.LA(4);

                    if ( (LA3_117=='a') ) {
                        switch ( input.LA(5) ) {
                        case 's':
                            {
                            int LA3_249 = input.LA(6);

                            if ( (LA3_249=='e') ) {
                                int LA3_304 = input.LA(7);

                                if ( (LA3_304=='t') ) {
                                    int LA3_343 = input.LA(8);

                                    if ( (LA3_343=='#'||LA3_343=='%'||LA3_343=='*'||LA3_343=='-'||(LA3_343>='/' && LA3_343<=':')||(LA3_343>='A' && LA3_343<='Z')||LA3_343=='_'||(LA3_343>='a' && LA3_343<='z')) ) {
                                        alt3=85;
                                    }
                                    else {
                                        alt3=7;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                            }
                            break;
                        case 'q':
                            {
                            int LA3_250 = input.LA(6);

                            if ( (LA3_250=='u') ) {
                                int LA3_305 = input.LA(7);

                                if ( (LA3_305=='a') ) {
                                    int LA3_344 = input.LA(8);

                                    if ( (LA3_344=='l') ) {
                                        int LA3_376 = input.LA(9);

                                        if ( (LA3_376=='i') ) {
                                            int LA3_396 = input.LA(10);

                                            if ( (LA3_396=='t') ) {
                                                int LA3_408 = input.LA(11);

                                                if ( (LA3_408=='y') ) {
                                                    int LA3_417 = input.LA(12);

                                                    if ( (LA3_417=='(') ) {
                                                        alt3=53;
                                                    }
                                                    else {
                                                        alt3=85;}
                                                }
                                                else {
                                                    alt3=85;}
                                            }
                                            else {
                                                alt3=85;}
                                        }
                                        else {
                                            alt3=85;}
                                    }
                                    else {
                                        alt3=85;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                            }
                            break;
                        default:
                            alt3=85;}

                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'q':
                {
                int LA3_54 = input.LA(3);

                if ( (LA3_54=='#'||LA3_54=='%'||LA3_54=='*'||LA3_54=='-'||(LA3_54>='/' && LA3_54<=':')||(LA3_54>='A' && LA3_54<='Z')||LA3_54=='_'||(LA3_54>='a' && LA3_54<='z')) ) {
                    alt3=85;
                }
                else {
                    alt3=17;}
                }
                break;
            case 'e':
                {
                switch ( input.LA(3) ) {
                case 's':
                    {
                    int LA3_119 = input.LA(4);

                    if ( (LA3_119=='c') ) {
                        int LA3_185 = input.LA(5);

                        if ( (LA3_185=='#'||LA3_185=='%'||LA3_185=='*'||LA3_185=='-'||(LA3_185>='/' && LA3_185<=':')||(LA3_185>='A' && LA3_185<='Z')||LA3_185=='_'||(LA3_185>='a' && LA3_185<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=81;}
                    }
                    else {
                        alt3=85;}
                    }
                    break;
                case 'f':
                    {
                    int LA3_120 = input.LA(4);

                    if ( (LA3_120=='#'||LA3_120=='%'||LA3_120=='*'||LA3_120=='-'||(LA3_120>='/' && LA3_120<=':')||(LA3_120>='A' && LA3_120<='Z')||LA3_120=='_'||(LA3_120>='a' && LA3_120<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=43;}
                    }
                    break;
                default:
                    alt3=85;}

                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'r':
            {
            switch ( input.LA(2) ) {
            case 'e':
                {
                int LA3_56 = input.LA(3);

                if ( (LA3_56=='l') ) {
                    int LA3_121 = input.LA(4);

                    if ( (LA3_121=='e') ) {
                        int LA3_187 = input.LA(5);

                        if ( (LA3_187=='a') ) {
                            int LA3_252 = input.LA(6);

                            if ( (LA3_252=='s') ) {
                                int LA3_306 = input.LA(7);

                                if ( (LA3_306=='e') ) {
                                    int LA3_345 = input.LA(8);

                                    if ( (LA3_345=='#'||LA3_345=='%'||LA3_345=='*'||LA3_345=='-'||(LA3_345>='/' && LA3_345<=':')||(LA3_345>='A' && LA3_345<='Z')||LA3_345=='_'||(LA3_345>='a' && LA3_345<='z')) ) {
                                        alt3=85;
                                    }
                                    else {
                                        alt3=8;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'u':
                {
                int LA3_57 = input.LA(3);

                if ( (LA3_57=='n') ) {
                    int LA3_122 = input.LA(4);

                    if ( (LA3_122=='#'||LA3_122=='%'||LA3_122=='*'||LA3_122=='-'||(LA3_122>='/' && LA3_122<=':')||(LA3_122>='A' && LA3_122<='Z')||LA3_122=='_'||(LA3_122>='a' && LA3_122<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=15;}
                }
                else {
                    alt3=85;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 't':
            {
            switch ( input.LA(2) ) {
            case 'i':
                {
                int LA3_58 = input.LA(3);

                if ( (LA3_58=='e') ) {
                    int LA3_123 = input.LA(4);

                    if ( (LA3_123=='r') ) {
                        int LA3_189 = input.LA(5);

                        if ( (LA3_189=='#'||LA3_189=='%'||LA3_189=='*'||LA3_189=='-'||(LA3_189>='/' && LA3_189<=':')||(LA3_189>='A' && LA3_189<='Z')||LA3_189=='_'||(LA3_189>='a' && LA3_189<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=9;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'a':
                {
                int LA3_59 = input.LA(3);

                if ( (LA3_59=='g') ) {
                    int LA3_124 = input.LA(4);

                    if ( (LA3_124=='#'||LA3_124=='%'||LA3_124=='*'||LA3_124=='-'||(LA3_124>='/' && LA3_124<=':')||(LA3_124>='A' && LA3_124<='Z')||LA3_124=='_'||(LA3_124>='a' && LA3_124<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=46;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'y':
                {
                int LA3_60 = input.LA(3);

                if ( (LA3_60=='p') ) {
                    int LA3_125 = input.LA(4);

                    if ( (LA3_125=='e') ) {
                        int LA3_191 = input.LA(5);

                        if ( (LA3_191=='#'||LA3_191=='%'||LA3_191=='*'||LA3_191=='-'||(LA3_191>='/' && LA3_191<=':')||(LA3_191>='A' && LA3_191<='Z')||LA3_191=='_'||(LA3_191>='a' && LA3_191<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=39;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 's':
            {
            switch ( input.LA(2) ) {
            case 'i':
                {
                switch ( input.LA(3) ) {
                case 't':
                    {
                    int LA3_126 = input.LA(4);

                    if ( (LA3_126=='e') ) {
                        int LA3_192 = input.LA(5);

                        if ( (LA3_192=='#'||LA3_192=='%'||LA3_192=='*'||LA3_192=='-'||(LA3_192>='/' && LA3_192<=':')||(LA3_192>='A' && LA3_192<='Z')||LA3_192=='_'||(LA3_192>='a' && LA3_192<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=10;}
                    }
                    else {
                        alt3=85;}
                    }
                    break;
                case 'z':
                    {
                    int LA3_127 = input.LA(4);

                    if ( (LA3_127=='e') ) {
                        int LA3_193 = input.LA(5);

                        if ( (LA3_193=='#'||LA3_193=='%'||LA3_193=='*'||LA3_193=='-'||(LA3_193>='/' && LA3_193<=':')||(LA3_193>='A' && LA3_193<='Z')||LA3_193=='_'||(LA3_193>='a' && LA3_193<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=36;}
                    }
                    else {
                        alt3=85;}
                    }
                    break;
                default:
                    alt3=85;}

                }
                break;
            case 'e':
                {
                int LA3_62 = input.LA(3);

                if ( (LA3_62=='l') ) {
                    int LA3_128 = input.LA(4);

                    if ( (LA3_128=='e') ) {
                        int LA3_194 = input.LA(5);

                        if ( (LA3_194=='c') ) {
                            int LA3_257 = input.LA(6);

                            if ( (LA3_257=='t') ) {
                                int LA3_307 = input.LA(7);

                                if ( (LA3_307=='#'||LA3_307=='%'||LA3_307=='*'||LA3_307=='-'||(LA3_307>='/' && LA3_307<=':')||(LA3_307>='A' && LA3_307<='Z')||LA3_307=='_'||(LA3_307>='a' && LA3_307<='z')) ) {
                                    alt3=85;
                                }
                                else {
                                    alt3=59;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 't':
                {
                int LA3_63 = input.LA(3);

                if ( (LA3_63=='a') ) {
                    switch ( input.LA(4) ) {
                    case 'r':
                        {
                        int LA3_195 = input.LA(5);

                        if ( (LA3_195=='t') ) {
                            switch ( input.LA(6) ) {
                            case 'e':
                                {
                                int LA3_308 = input.LA(7);

                                if ( (LA3_308=='v') ) {
                                    int LA3_347 = input.LA(8);

                                    if ( (LA3_347=='n') ) {
                                        int LA3_378 = input.LA(9);

                                        if ( (LA3_378=='u') ) {
                                            int LA3_397 = input.LA(10);

                                            if ( (LA3_397=='m') ) {
                                                int LA3_409 = input.LA(11);

                                                if ( (LA3_409=='#'||LA3_409=='%'||LA3_409=='*'||LA3_409=='-'||(LA3_409>='/' && LA3_409<=':')||(LA3_409>='A' && LA3_409<='Z')||LA3_409=='_'||(LA3_409>='a' && LA3_409<='z')) ) {
                                                    alt3=85;
                                                }
                                                else {
                                                    alt3=32;}
                                            }
                                            else {
                                                alt3=85;}
                                        }
                                        else {
                                            alt3=85;}
                                    }
                                    else {
                                        alt3=85;}
                                }
                                else {
                                    alt3=85;}
                                }
                                break;
                            case 't':
                                {
                                int LA3_309 = input.LA(7);

                                if ( (LA3_309=='i') ) {
                                    int LA3_348 = input.LA(8);

                                    if ( (LA3_348=='m') ) {
                                        int LA3_379 = input.LA(9);

                                        if ( (LA3_379=='e') ) {
                                            int LA3_398 = input.LA(10);

                                            if ( (LA3_398=='#'||LA3_398=='%'||LA3_398=='*'||LA3_398=='-'||(LA3_398>='/' && LA3_398<=':')||(LA3_398>='A' && LA3_398<='Z')||LA3_398=='_'||(LA3_398>='a' && LA3_398<='z')) ) {
                                                alt3=85;
                                            }
                                            else {
                                                alt3=25;}
                                        }
                                        else {
                                            alt3=85;}
                                    }
                                    else {
                                        alt3=85;}
                                }
                                else {
                                    alt3=85;}
                                }
                                break;
                            default:
                                alt3=85;}

                        }
                        else {
                            alt3=85;}
                        }
                        break;
                    case 't':
                        {
                        int LA3_196 = input.LA(5);

                        if ( (LA3_196=='u') ) {
                            int LA3_259 = input.LA(6);

                            if ( (LA3_259=='s') ) {
                                int LA3_310 = input.LA(7);

                                if ( (LA3_310=='#'||LA3_310=='%'||LA3_310=='*'||LA3_310=='-'||(LA3_310>='/' && LA3_310<=':')||(LA3_310>='A' && LA3_310<='Z')||LA3_310=='_'||(LA3_310>='a' && LA3_310<='z')) ) {
                                    alt3=85;
                                }
                                else {
                                    alt3=38;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                        }
                        break;
                    default:
                        alt3=85;}

                }
                else {
                    alt3=85;}
                }
                break;
            case 'u':
                {
                int LA3_64 = input.LA(3);

                if ( (LA3_64=='m') ) {
                    int LA3_130 = input.LA(4);

                    if ( (LA3_130=='#'||LA3_130=='%'||LA3_130=='*'||LA3_130=='-'||(LA3_130>='/' && LA3_130<=':')||(LA3_130>='A' && LA3_130<='Z')||LA3_130=='_'||(LA3_130>='a' && LA3_130<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=77;}
                }
                else {
                    alt3=85;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'b':
            {
            switch ( input.LA(2) ) {
            case 'l':
                {
                int LA3_65 = input.LA(3);

                if ( (LA3_65=='o') ) {
                    int LA3_131 = input.LA(4);

                    if ( (LA3_131=='c') ) {
                        int LA3_198 = input.LA(5);

                        if ( (LA3_198=='k') ) {
                            int LA3_260 = input.LA(6);

                            if ( (LA3_260=='#'||LA3_260=='%'||LA3_260=='*'||LA3_260=='-'||(LA3_260>='/' && LA3_260<=':')||(LA3_260>='A' && LA3_260<='Z')||LA3_260=='_'||(LA3_260>='a' && LA3_260<='z')) ) {
                                alt3=85;
                            }
                            else {
                                alt3=11;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'y':
                {
                int LA3_66 = input.LA(3);

                if ( (LA3_66=='#'||LA3_66=='%'||LA3_66=='*'||LA3_66=='-'||(LA3_66>='/' && LA3_66<=':')||(LA3_66>='A' && LA3_66<='Z')||LA3_66=='_'||(LA3_66>='a' && LA3_66<='z')) ) {
                    alt3=85;
                }
                else {
                    alt3=67;}
                }
                break;
            case 'e':
                {
                int LA3_67 = input.LA(3);

                if ( (LA3_67=='t') ) {
                    int LA3_133 = input.LA(4);

                    if ( (LA3_133=='w') ) {
                        int LA3_199 = input.LA(5);

                        if ( (LA3_199=='e') ) {
                            int LA3_261 = input.LA(6);

                            if ( (LA3_261=='e') ) {
                                int LA3_312 = input.LA(7);

                                if ( (LA3_312=='n') ) {
                                    int LA3_350 = input.LA(8);

                                    if ( (LA3_350=='#'||LA3_350=='%'||LA3_350=='*'||LA3_350=='-'||(LA3_350>='/' && LA3_350<=':')||(LA3_350>='A' && LA3_350<='Z')||LA3_350=='_'||(LA3_350>='a' && LA3_350<='z')) ) {
                                        alt3=85;
                                    }
                                    else {
                                        alt3=83;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'f':
            {
            switch ( input.LA(2) ) {
            case 'i':
                {
                switch ( input.LA(3) ) {
                case 'l':
                    {
                    int LA3_134 = input.LA(4);

                    if ( (LA3_134=='e') ) {
                        int LA3_200 = input.LA(5);

                        if ( (LA3_200=='#'||LA3_200=='%'||LA3_200=='*'||LA3_200=='-'||(LA3_200>='/' && LA3_200<=':')||(LA3_200>='A' && LA3_200<='Z')||LA3_200=='_'||(LA3_200>='a' && LA3_200<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=12;}
                    }
                    else {
                        alt3=85;}
                    }
                    break;
                case 'n':
                    {
                    int LA3_135 = input.LA(4);

                    if ( (LA3_135=='d') ) {
                        switch ( input.LA(5) ) {
                        case 'e':
                            {
                            int LA3_263 = input.LA(6);

                            if ( (LA3_263=='v') ) {
                                int LA3_313 = input.LA(7);

                                if ( (LA3_313=='e') ) {
                                    int LA3_351 = input.LA(8);

                                    if ( (LA3_351=='n') ) {
                                        int LA3_381 = input.LA(9);

                                        if ( (LA3_381=='t') ) {
                                            int LA3_399 = input.LA(10);

                                            if ( (LA3_399=='s') ) {
                                                int LA3_411 = input.LA(11);

                                                if ( (LA3_411=='(') ) {
                                                    alt3=58;
                                                }
                                                else {
                                                    alt3=85;}
                                            }
                                            else {
                                                alt3=85;}
                                        }
                                        else {
                                            alt3=85;}
                                    }
                                    else {
                                        alt3=85;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                            }
                            break;
                        case '#':
                        case '%':
                        case '*':
                        case '-':
                        case '/':
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                        case ':':
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                        case 'G':
                        case 'H':
                        case 'I':
                        case 'J':
                        case 'K':
                        case 'L':
                        case 'M':
                        case 'N':
                        case 'O':
                        case 'P':
                        case 'Q':
                        case 'R':
                        case 'S':
                        case 'T':
                        case 'U':
                        case 'V':
                        case 'W':
                        case 'X':
                        case 'Y':
                        case 'Z':
                        case '_':
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'f':
                        case 'g':
                        case 'h':
                        case 'i':
                        case 'j':
                        case 'k':
                        case 'l':
                        case 'm':
                        case 'n':
                        case 'o':
                        case 'p':
                        case 'q':
                        case 'r':
                        case 's':
                        case 't':
                        case 'u':
                        case 'v':
                        case 'w':
                        case 'x':
                        case 'y':
                        case 'z':
                            {
                            alt3=85;
                            }
                            break;
                        default:
                            alt3=61;}

                    }
                    else {
                        alt3=85;}
                    }
                    break;
                default:
                    alt3=85;}

                }
                break;
            case 'a':
                {
                int LA3_69 = input.LA(3);

                if ( (LA3_69=='m') ) {
                    int LA3_136 = input.LA(4);

                    if ( (LA3_136=='i') ) {
                        int LA3_202 = input.LA(5);

                        if ( (LA3_202=='l') ) {
                            int LA3_265 = input.LA(6);

                            if ( (LA3_265=='y') ) {
                                int LA3_314 = input.LA(7);

                                if ( (LA3_314=='#'||LA3_314=='%'||LA3_314=='*'||LA3_314=='-'||(LA3_314>='/' && LA3_314<=':')||(LA3_314>='A' && LA3_314<='Z')||LA3_314=='_'||(LA3_314>='a' && LA3_314<='z')) ) {
                                    alt3=85;
                                }
                                else {
                                    alt3=49;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'p':
            {
            switch ( input.LA(2) ) {
            case 'a':
                {
                int LA3_70 = input.LA(3);

                if ( (LA3_70=='r') ) {
                    int LA3_137 = input.LA(4);

                    if ( (LA3_137=='e') ) {
                        int LA3_203 = input.LA(5);

                        if ( (LA3_203=='n') ) {
                            int LA3_266 = input.LA(6);

                            if ( (LA3_266=='t') ) {
                                switch ( input.LA(7) ) {
                                case 'r':
                                    {
                                    int LA3_353 = input.LA(8);

                                    if ( (LA3_353=='e') ) {
                                        int LA3_382 = input.LA(9);

                                        if ( (LA3_382=='l') ) {
                                            int LA3_400 = input.LA(10);

                                            if ( (LA3_400=='e') ) {
                                                int LA3_412 = input.LA(11);

                                                if ( (LA3_412=='a') ) {
                                                    int LA3_420 = input.LA(12);

                                                    if ( (LA3_420=='s') ) {
                                                        int LA3_425 = input.LA(13);

                                                        if ( (LA3_425=='e') ) {
                                                            int LA3_428 = input.LA(14);

                                                            if ( (LA3_428=='(') ) {
                                                                alt3=55;
                                                            }
                                                            else {
                                                                alt3=85;}
                                                        }
                                                        else {
                                                            alt3=85;}
                                                    }
                                                    else {
                                                        alt3=85;}
                                                }
                                                else {
                                                    alt3=85;}
                                            }
                                            else {
                                                alt3=85;}
                                        }
                                        else {
                                            alt3=85;}
                                    }
                                    else {
                                        alt3=85;}
                                    }
                                    break;
                                case '#':
                                case '%':
                                case '*':
                                case '-':
                                case '/':
                                case '0':
                                case '1':
                                case '2':
                                case '3':
                                case '4':
                                case '5':
                                case '6':
                                case '7':
                                case '8':
                                case '9':
                                case ':':
                                case 'A':
                                case 'B':
                                case 'C':
                                case 'D':
                                case 'E':
                                case 'F':
                                case 'G':
                                case 'H':
                                case 'I':
                                case 'J':
                                case 'K':
                                case 'L':
                                case 'M':
                                case 'N':
                                case 'O':
                                case 'P':
                                case 'Q':
                                case 'R':
                                case 'S':
                                case 'T':
                                case 'U':
                                case 'V':
                                case 'W':
                                case 'X':
                                case 'Y':
                                case 'Z':
                                case '_':
                                case 'a':
                                case 'b':
                                case 'c':
                                case 'd':
                                case 'e':
                                case 'f':
                                case 'g':
                                case 'h':
                                case 'i':
                                case 'j':
                                case 'k':
                                case 'l':
                                case 'm':
                                case 'n':
                                case 'o':
                                case 'p':
                                case 'q':
                                case 's':
                                case 't':
                                case 'u':
                                case 'v':
                                case 'w':
                                case 'x':
                                case 'y':
                                case 'z':
                                    {
                                    alt3=85;
                                    }
                                    break;
                                default:
                                    alt3=41;}

                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 's':
                {
                int LA3_71 = input.LA(3);

                if ( (LA3_71=='e') ) {
                    int LA3_138 = input.LA(4);

                    if ( (LA3_138=='t') ) {
                        int LA3_204 = input.LA(5);

                        if ( (LA3_204=='#'||LA3_204=='%'||LA3_204=='*'||LA3_204=='-'||(LA3_204>='/' && LA3_204<=':')||(LA3_204>='A' && LA3_204<='Z')||LA3_204=='_'||(LA3_204>='a' && LA3_204<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=21;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'r':
                {
                switch ( input.LA(3) ) {
                case 'i':
                    {
                    int LA3_139 = input.LA(4);

                    if ( (LA3_139=='m') ) {
                        int LA3_205 = input.LA(5);

                        if ( (LA3_205=='d') ) {
                            int LA3_268 = input.LA(6);

                            if ( (LA3_268=='s') ) {
                                int LA3_316 = input.LA(7);

                                if ( (LA3_316=='#'||LA3_316=='%'||LA3_316=='*'||LA3_316=='-'||(LA3_316>='/' && LA3_316<=':')||(LA3_316>='A' && LA3_316<='Z')||LA3_316=='_'||(LA3_316>='a' && LA3_316<='z')) ) {
                                    alt3=85;
                                }
                                else {
                                    alt3=13;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                    }
                    break;
                case 'o':
                    {
                    int LA3_140 = input.LA(4);

                    if ( (LA3_140=='c') ) {
                        int LA3_206 = input.LA(5);

                        if ( (LA3_206=='d') ) {
                            int LA3_269 = input.LA(6);

                            if ( (LA3_269=='s') ) {
                                int LA3_317 = input.LA(7);

                                if ( (LA3_317=='#'||LA3_317=='%'||LA3_317=='*'||LA3_317=='-'||(LA3_317>='/' && LA3_317<=':')||(LA3_317>='A' && LA3_317<='Z')||LA3_317=='_'||(LA3_317>='a' && LA3_317<='z')) ) {
                                    alt3=85;
                                }
                                else {
                                    alt3=14;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                    }
                    break;
                default:
                    alt3=85;}

                }
                break;
            case 'h':
                {
                int LA3_73 = input.LA(3);

                if ( (LA3_73=='y') ) {
                    int LA3_141 = input.LA(4);

                    if ( (LA3_141=='g') ) {
                        int LA3_207 = input.LA(5);

                        if ( (LA3_207=='r') ) {
                            int LA3_270 = input.LA(6);

                            if ( (LA3_270=='p') ) {
                                int LA3_318 = input.LA(7);

                                if ( (LA3_318=='#'||LA3_318=='%'||LA3_318=='*'||LA3_318=='-'||(LA3_318>='/' && LA3_318<=':')||(LA3_318>='A' && LA3_318<='Z')||LA3_318=='_'||(LA3_318>='a' && LA3_318<='z')) ) {
                                    alt3=85;
                                }
                                else {
                                    alt3=19;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'l':
            {
            switch ( input.LA(2) ) {
            case 'a':
                {
                int LA3_74 = input.LA(3);

                if ( (LA3_74=='t') ) {
                    int LA3_142 = input.LA(4);

                    if ( (LA3_142=='e') ) {
                        int LA3_208 = input.LA(5);

                        if ( (LA3_208=='s') ) {
                            int LA3_271 = input.LA(6);

                            if ( (LA3_271=='t') ) {
                                int LA3_319 = input.LA(7);

                                if ( (LA3_319=='(') ) {
                                    alt3=54;
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'u':
                {
                int LA3_75 = input.LA(3);

                if ( (LA3_75=='m') ) {
                    int LA3_143 = input.LA(4);

                    if ( (LA3_143=='i') ) {
                        int LA3_209 = input.LA(5);

                        if ( (LA3_209=='#'||LA3_209=='%'||LA3_209=='*'||LA3_209=='-'||(LA3_209>='/' && LA3_209<=':')||(LA3_209>='A' && LA3_209<='Z')||LA3_209=='_'||(LA3_209>='a' && LA3_209<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=16;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'i':
                {
                int LA3_76 = input.LA(3);

                if ( (LA3_76=='k') ) {
                    int LA3_144 = input.LA(4);

                    if ( (LA3_144=='e') ) {
                        int LA3_210 = input.LA(5);

                        if ( (LA3_210=='#'||LA3_210=='%'||LA3_210=='*'||LA3_210=='-'||(LA3_210>='/' && LA3_210<=':')||(LA3_210>='A' && LA3_210<='Z')||LA3_210=='_'||(LA3_210>='a' && LA3_210<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=74;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'g':
            {
            int LA3_15 = input.LA(2);

            if ( (LA3_15=='r') ) {
                int LA3_77 = input.LA(3);

                if ( (LA3_77=='o') ) {
                    int LA3_145 = input.LA(4);

                    if ( (LA3_145=='u') ) {
                        int LA3_211 = input.LA(5);

                        if ( (LA3_211=='p') ) {
                            int LA3_274 = input.LA(6);

                            if ( (LA3_274=='#'||LA3_274=='%'||LA3_274=='*'||LA3_274=='-'||(LA3_274>='/' && LA3_274<=':')||(LA3_274>='A' && LA3_274<='Z')||LA3_274=='_'||(LA3_274>='a' && LA3_274<='z')) ) {
                                alt3=85;
                            }
                            else {
                                alt3=20;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
            }
            else {
                alt3=85;}
            }
            break;
        case 'c':
            {
            switch ( input.LA(2) ) {
            case 'h':
                {
                int LA3_78 = input.LA(3);

                if ( (LA3_78=='i') ) {
                    int LA3_146 = input.LA(4);

                    if ( (LA3_146=='l') ) {
                        int LA3_212 = input.LA(5);

                        if ( (LA3_212=='d') ) {
                            switch ( input.LA(6) ) {
                            case 'r':
                                {
                                int LA3_321 = input.LA(7);

                                if ( (LA3_321=='e') ) {
                                    int LA3_359 = input.LA(8);

                                    if ( (LA3_359=='l') ) {
                                        int LA3_383 = input.LA(9);

                                        if ( (LA3_383=='e') ) {
                                            int LA3_401 = input.LA(10);

                                            if ( (LA3_401=='a') ) {
                                                int LA3_413 = input.LA(11);

                                                if ( (LA3_413=='s') ) {
                                                    int LA3_421 = input.LA(12);

                                                    if ( (LA3_421=='e') ) {
                                                        int LA3_426 = input.LA(13);

                                                        if ( (LA3_426=='(') ) {
                                                            alt3=56;
                                                        }
                                                        else {
                                                            alt3=85;}
                                                    }
                                                    else {
                                                        alt3=85;}
                                                }
                                                else {
                                                    alt3=85;}
                                            }
                                            else {
                                                alt3=85;}
                                        }
                                        else {
                                            alt3=85;}
                                    }
                                    else {
                                        alt3=85;}
                                }
                                else {
                                    alt3=85;}
                                }
                                break;
                            case '#':
                            case '%':
                            case '*':
                            case '-':
                            case '/':
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                            case ':':
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                            case 'G':
                            case 'H':
                            case 'I':
                            case 'J':
                            case 'K':
                            case 'L':
                            case 'M':
                            case 'N':
                            case 'O':
                            case 'P':
                            case 'Q':
                            case 'R':
                            case 'S':
                            case 'T':
                            case 'U':
                            case 'V':
                            case 'W':
                            case 'X':
                            case 'Y':
                            case 'Z':
                            case '_':
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                            case 'g':
                            case 'h':
                            case 'i':
                            case 'j':
                            case 'k':
                            case 'l':
                            case 'm':
                            case 'n':
                            case 'o':
                            case 'p':
                            case 'q':
                            case 's':
                            case 't':
                            case 'u':
                            case 'v':
                            case 'w':
                            case 'x':
                            case 'y':
                            case 'z':
                                {
                                alt3=85;
                                }
                                break;
                            default:
                                alt3=42;}

                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'r':
                {
                int LA3_79 = input.LA(3);

                if ( (LA3_79=='e') ) {
                    int LA3_147 = input.LA(4);

                    if ( (LA3_147=='a') ) {
                        int LA3_213 = input.LA(5);

                        if ( (LA3_213=='t') ) {
                            int LA3_276 = input.LA(6);

                            if ( (LA3_276=='e') ) {
                                switch ( input.LA(7) ) {
                                case 'b':
                                    {
                                    int LA3_360 = input.LA(8);

                                    if ( (LA3_360=='y') ) {
                                        int LA3_384 = input.LA(9);

                                        if ( (LA3_384=='#'||LA3_384=='%'||LA3_384=='*'||LA3_384=='-'||(LA3_384>='/' && LA3_384<=':')||(LA3_384>='A' && LA3_384<='Z')||LA3_384=='_'||(LA3_384>='a' && LA3_384<='z')) ) {
                                            alt3=85;
                                        }
                                        else {
                                            alt3=27;}
                                    }
                                    else {
                                        alt3=85;}
                                    }
                                    break;
                                case 'd':
                                    {
                                    int LA3_361 = input.LA(8);

                                    if ( (LA3_361=='a') ) {
                                        int LA3_385 = input.LA(9);

                                        if ( (LA3_385=='t') ) {
                                            int LA3_403 = input.LA(10);

                                            if ( (LA3_403=='e') ) {
                                                int LA3_414 = input.LA(11);

                                                if ( (LA3_414=='#'||LA3_414=='%'||LA3_414=='*'||LA3_414=='-'||(LA3_414>='/' && LA3_414<=':')||(LA3_414>='A' && LA3_414<='Z')||LA3_414=='_'||(LA3_414>='a' && LA3_414<='z')) ) {
                                                    alt3=85;
                                                }
                                                else {
                                                    alt3=23;}
                                            }
                                            else {
                                                alt3=85;}
                                        }
                                        else {
                                            alt3=85;}
                                    }
                                    else {
                                        alt3=85;}
                                    }
                                    break;
                                default:
                                    alt3=85;}

                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'o':
                {
                switch ( input.LA(3) ) {
                case 'n':
                    {
                    int LA3_148 = input.LA(4);

                    if ( (LA3_148=='t') ) {
                        int LA3_214 = input.LA(5);

                        if ( (LA3_214=='e') ) {
                            int LA3_277 = input.LA(6);

                            if ( (LA3_277=='n') ) {
                                int LA3_324 = input.LA(7);

                                if ( (LA3_324=='t') ) {
                                    int LA3_362 = input.LA(8);

                                    if ( (LA3_362=='#'||LA3_362=='%'||LA3_362=='*'||LA3_362=='-'||(LA3_362>='/' && LA3_362<=':')||(LA3_362>='A' && LA3_362<='Z')||LA3_362=='_'||(LA3_362>='a' && LA3_362<='z')) ) {
                                        alt3=85;
                                    }
                                    else {
                                        alt3=48;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                    }
                    break;
                case 'u':
                    {
                    int LA3_149 = input.LA(4);

                    if ( (LA3_149=='n') ) {
                        int LA3_215 = input.LA(5);

                        if ( (LA3_215=='t') ) {
                            int LA3_278 = input.LA(6);

                            if ( (LA3_278=='#'||LA3_278=='%'||LA3_278=='*'||LA3_278=='-'||(LA3_278>='/' && LA3_278<=':')||(LA3_278>='A' && LA3_278<='Z')||LA3_278=='_'||(LA3_278>='a' && LA3_278<='z')) ) {
                                alt3=85;
                            }
                            else {
                                alt3=37;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                    }
                    break;
                default:
                    alt3=85;}

                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'm':
            {
            int LA3_17 = input.LA(2);

            if ( (LA3_17=='o') ) {
                int LA3_81 = input.LA(3);

                if ( (LA3_81=='d') ) {
                    switch ( input.LA(4) ) {
                    case 'd':
                        {
                        int LA3_216 = input.LA(5);

                        if ( (LA3_216=='a') ) {
                            int LA3_279 = input.LA(6);

                            if ( (LA3_279=='t') ) {
                                int LA3_326 = input.LA(7);

                                if ( (LA3_326=='e') ) {
                                    int LA3_363 = input.LA(8);

                                    if ( (LA3_363=='#'||LA3_363=='%'||LA3_363=='*'||LA3_363=='-'||(LA3_363>='/' && LA3_363<=':')||(LA3_363>='A' && LA3_363<='Z')||LA3_363=='_'||(LA3_363>='a' && LA3_363<='z')) ) {
                                        alt3=85;
                                    }
                                    else {
                                        alt3=24;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                        }
                        break;
                    case 'b':
                        {
                        int LA3_217 = input.LA(5);

                        if ( (LA3_217=='y') ) {
                            int LA3_280 = input.LA(6);

                            if ( (LA3_280=='#'||LA3_280=='%'||LA3_280=='*'||LA3_280=='-'||(LA3_280>='/' && LA3_280<=':')||(LA3_280>='A' && LA3_280<='Z')||LA3_280=='_'||(LA3_280>='a' && LA3_280<='z')) ) {
                                alt3=85;
                            }
                            else {
                                alt3=28;}
                        }
                        else {
                            alt3=85;}
                        }
                        break;
                    default:
                        alt3=85;}

                }
                else {
                    alt3=85;}
            }
            else {
                alt3=85;}
            }
            break;
        case 'e':
            {
            switch ( input.LA(2) ) {
            case 'x':
                {
                int LA3_82 = input.LA(3);

                if ( (LA3_82=='e') ) {
                    int LA3_151 = input.LA(4);

                    if ( (LA3_151=='#'||LA3_151=='%'||LA3_151=='*'||LA3_151=='-'||(LA3_151>='/' && LA3_151<=':')||(LA3_151>='A' && LA3_151<='Z')||LA3_151=='_'||(LA3_151>='a' && LA3_151<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'n':
                {
                int LA3_83 = input.LA(3);

                if ( (LA3_83=='d') ) {
                    switch ( input.LA(4) ) {
                    case 't':
                        {
                        int LA3_219 = input.LA(5);

                        if ( (LA3_219=='i') ) {
                            int LA3_281 = input.LA(6);

                            if ( (LA3_281=='m') ) {
                                int LA3_328 = input.LA(7);

                                if ( (LA3_328=='e') ) {
                                    int LA3_364 = input.LA(8);

                                    if ( (LA3_364=='#'||LA3_364=='%'||LA3_364=='*'||LA3_364=='-'||(LA3_364>='/' && LA3_364<=':')||(LA3_364>='A' && LA3_364<='Z')||LA3_364=='_'||(LA3_364>='a' && LA3_364<='z')) ) {
                                        alt3=85;
                                    }
                                    else {
                                        alt3=26;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                        }
                        break;
                    case 'e':
                        {
                        int LA3_220 = input.LA(5);

                        if ( (LA3_220=='v') ) {
                            int LA3_282 = input.LA(6);

                            if ( (LA3_282=='n') ) {
                                int LA3_329 = input.LA(7);

                                if ( (LA3_329=='u') ) {
                                    int LA3_365 = input.LA(8);

                                    if ( (LA3_365=='m') ) {
                                        int LA3_389 = input.LA(9);

                                        if ( (LA3_389=='#'||LA3_389=='%'||LA3_389=='*'||LA3_389=='-'||(LA3_389>='/' && LA3_389<=':')||(LA3_389>='A' && LA3_389<='Z')||LA3_389=='_'||(LA3_389>='a' && LA3_389<='z')) ) {
                                            alt3=85;
                                        }
                                        else {
                                            alt3=33;}
                                    }
                                    else {
                                        alt3=85;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                        }
                        break;
                    default:
                        alt3=85;}

                }
                else {
                    alt3=85;}
                }
                break;
            case 'v':
                {
                int LA3_84 = input.LA(3);

                if ( (LA3_84=='n') ) {
                    int LA3_153 = input.LA(4);

                    if ( (LA3_153=='u') ) {
                        int LA3_221 = input.LA(5);

                        if ( (LA3_221=='m') ) {
                            int LA3_283 = input.LA(6);

                            if ( (LA3_283=='#'||LA3_283=='%'||LA3_283=='*'||LA3_283=='-'||(LA3_283>='/' && LA3_283<=':')||(LA3_283>='A' && LA3_283<='Z')||LA3_283=='_'||(LA3_283>='a' && LA3_283<='z')) ) {
                                alt3=85;
                            }
                            else {
                                alt3=44;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'r':
                {
                int LA3_85 = input.LA(3);

                if ( (LA3_85=='a') ) {
                    int LA3_154 = input.LA(4);

                    if ( (LA3_154=='#'||LA3_154=='%'||LA3_154=='*'||LA3_154=='-'||(LA3_154>='/' && LA3_154<=':')||(LA3_154>='A' && LA3_154<='Z')||LA3_154=='_'||(LA3_154>='a' && LA3_154<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=45;}
                }
                else {
                    alt3=85;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'n':
            {
            switch ( input.LA(2) ) {
            case 'u':
                {
                int LA3_86 = input.LA(3);

                if ( (LA3_86=='m') ) {
                    switch ( input.LA(4) ) {
                    case 'l':
                        {
                        int LA3_223 = input.LA(5);

                        if ( (LA3_223=='s') ) {
                            int LA3_284 = input.LA(6);

                            if ( (LA3_284=='s') ) {
                                int LA3_331 = input.LA(7);

                                if ( (LA3_331=='#'||LA3_331=='%'||LA3_331=='*'||LA3_331=='-'||(LA3_331>='/' && LA3_331<=':')||(LA3_331>='A' && LA3_331<='Z')||LA3_331=='_'||(LA3_331>='a' && LA3_331<='z')) ) {
                                    alt3=85;
                                }
                                else {
                                    alt3=35;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                        }
                        break;
                    case 'f':
                        {
                        int LA3_224 = input.LA(5);

                        if ( (LA3_224=='i') ) {
                            int LA3_285 = input.LA(6);

                            if ( (LA3_285=='l') ) {
                                int LA3_332 = input.LA(7);

                                if ( (LA3_332=='e') ) {
                                    int LA3_367 = input.LA(8);

                                    if ( (LA3_367=='s') ) {
                                        int LA3_390 = input.LA(9);

                                        if ( (LA3_390=='(') ) {
                                            alt3=52;
                                        }
                                        else {
                                            alt3=85;}
                                    }
                                    else {
                                        alt3=85;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                        }
                        break;
                    case 'e':
                        {
                        int LA3_225 = input.LA(5);

                        if ( (LA3_225=='v') ) {
                            int LA3_286 = input.LA(6);

                            if ( (LA3_286=='e') ) {
                                int LA3_333 = input.LA(7);

                                if ( (LA3_333=='n') ) {
                                    int LA3_368 = input.LA(8);

                                    if ( (LA3_368=='t') ) {
                                        int LA3_391 = input.LA(9);

                                        if ( (LA3_391=='s') ) {
                                            int LA3_406 = input.LA(10);

                                            if ( (LA3_406=='#'||LA3_406=='%'||LA3_406=='*'||LA3_406=='-'||(LA3_406>='/' && LA3_406<=':')||(LA3_406>='A' && LA3_406<='Z')||LA3_406=='_'||(LA3_406>='a' && LA3_406<='z')) ) {
                                                alt3=85;
                                            }
                                            else {
                                                alt3=34;}
                                        }
                                        else {
                                            alt3=85;}
                                    }
                                    else {
                                        alt3=85;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                        }
                        break;
                    case 'b':
                        {
                        int LA3_226 = input.LA(5);

                        if ( (LA3_226=='e') ) {
                            int LA3_287 = input.LA(6);

                            if ( (LA3_287=='r') ) {
                                int LA3_334 = input.LA(7);

                                if ( (LA3_334=='#'||LA3_334=='%'||LA3_334=='*'||LA3_334=='-'||(LA3_334>='/' && LA3_334<=':')||(LA3_334>='A' && LA3_334<='Z')||LA3_334=='_'||(LA3_334>='a' && LA3_334<='z')) ) {
                                    alt3=85;
                                }
                                else {
                                    alt3=31;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                        }
                        break;
                    case 'r':
                        {
                        int LA3_227 = input.LA(5);

                        if ( (LA3_227=='u') ) {
                            int LA3_288 = input.LA(6);

                            if ( (LA3_288=='n') ) {
                                int LA3_335 = input.LA(7);

                                if ( (LA3_335=='s') ) {
                                    int LA3_370 = input.LA(8);

                                    if ( (LA3_370=='(') ) {
                                        alt3=51;
                                    }
                                    else {
                                        alt3=85;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                        }
                        break;
                    default:
                        alt3=85;}

                }
                else {
                    alt3=85;}
                }
                break;
            case 'a':
                {
                int LA3_87 = input.LA(3);

                if ( (LA3_87=='m') ) {
                    int LA3_156 = input.LA(4);

                    if ( (LA3_156=='e') ) {
                        int LA3_228 = input.LA(5);

                        if ( (LA3_228=='#'||LA3_228=='%'||LA3_228=='*'||LA3_228=='-'||(LA3_228>='/' && LA3_228<=':')||(LA3_228>='A' && LA3_228<='Z')||LA3_228=='_'||(LA3_228>='a' && LA3_228<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=29;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'o':
                {
                int LA3_88 = input.LA(3);

                if ( (LA3_88=='t') ) {
                    int LA3_157 = input.LA(4);

                    if ( (LA3_157=='#'||LA3_157=='%'||LA3_157=='*'||LA3_157=='-'||(LA3_157>='/' && LA3_157<=':')||(LA3_157>='A' && LA3_157<='Z')||LA3_157=='_'||(LA3_157>='a' && LA3_157<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=85;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'v':
            {
            int LA3_20 = input.LA(2);

            if ( (LA3_20=='e') ) {
                int LA3_89 = input.LA(3);

                if ( (LA3_89=='r') ) {
                    int LA3_158 = input.LA(4);

                    if ( (LA3_158=='s') ) {
                        int LA3_230 = input.LA(5);

                        if ( (LA3_230=='i') ) {
                            int LA3_290 = input.LA(6);

                            if ( (LA3_290=='o') ) {
                                int LA3_336 = input.LA(7);

                                if ( (LA3_336=='n') ) {
                                    int LA3_371 = input.LA(8);

                                    if ( (LA3_371=='#'||LA3_371=='%'||LA3_371=='*'||LA3_371=='-'||(LA3_371>='/' && LA3_371<=':')||(LA3_371>='A' && LA3_371<='Z')||LA3_371=='_'||(LA3_371>='a' && LA3_371<='z')) ) {
                                        alt3=85;
                                    }
                                    else {
                                        alt3=30;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
            }
            else {
                alt3=85;}
            }
            break;
        case 'h':
            {
            int LA3_21 = input.LA(2);

            if ( (LA3_21=='a') ) {
                int LA3_90 = input.LA(3);

                if ( (LA3_90=='s') ) {
                    int LA3_159 = input.LA(4);

                    if ( (LA3_159=='h') ) {
                        int LA3_231 = input.LA(5);

                        if ( (LA3_231=='#'||LA3_231=='%'||LA3_231=='*'||LA3_231=='-'||(LA3_231>='/' && LA3_231<=':')||(LA3_231>='A' && LA3_231<='Z')||LA3_231=='_'||(LA3_231>='a' && LA3_231<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=47;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
            }
            else {
                alt3=85;}
            }
            break;
        case 'S':
            {
            switch ( input.LA(2) ) {
            case 'E':
                {
                int LA3_91 = input.LA(3);

                if ( (LA3_91=='L') ) {
                    int LA3_160 = input.LA(4);

                    if ( (LA3_160=='E') ) {
                        int LA3_232 = input.LA(5);

                        if ( (LA3_232=='C') ) {
                            int LA3_292 = input.LA(6);

                            if ( (LA3_292=='T') ) {
                                int LA3_337 = input.LA(7);

                                if ( (LA3_337=='#'||LA3_337=='%'||LA3_337=='*'||LA3_337=='-'||(LA3_337>='/' && LA3_337<=':')||(LA3_337>='A' && LA3_337<='Z')||LA3_337=='_'||(LA3_337>='a' && LA3_337<='z')) ) {
                                    alt3=85;
                                }
                                else {
                                    alt3=60;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'U':
                {
                int LA3_92 = input.LA(3);

                if ( (LA3_92=='M') ) {
                    int LA3_161 = input.LA(4);

                    if ( (LA3_161=='#'||LA3_161=='%'||LA3_161=='*'||LA3_161=='-'||(LA3_161>='/' && LA3_161<=':')||(LA3_161>='A' && LA3_161<='Z')||LA3_161=='_'||(LA3_161>='a' && LA3_161<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=78;}
                }
                else {
                    alt3=85;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'F':
            {
            int LA3_23 = input.LA(2);

            if ( (LA3_23=='I') ) {
                int LA3_93 = input.LA(3);

                if ( (LA3_93=='N') ) {
                    int LA3_162 = input.LA(4);

                    if ( (LA3_162=='D') ) {
                        int LA3_234 = input.LA(5);

                        if ( (LA3_234=='#'||LA3_234=='%'||LA3_234=='*'||LA3_234=='-'||(LA3_234>='/' && LA3_234<=':')||(LA3_234>='A' && LA3_234<='Z')||LA3_234=='_'||(LA3_234>='a' && LA3_234<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=62;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
            }
            else {
                alt3=85;}
            }
            break;
        case 'A':
            {
            switch ( input.LA(2) ) {
            case 'N':
                {
                int LA3_94 = input.LA(3);

                if ( (LA3_94=='D') ) {
                    int LA3_163 = input.LA(4);

                    if ( (LA3_163=='#'||LA3_163=='%'||LA3_163=='*'||LA3_163=='-'||(LA3_163>='/' && LA3_163<=':')||(LA3_163>='A' && LA3_163<='Z')||LA3_163=='_'||(LA3_163>='a' && LA3_163<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=64;}
                }
                else {
                    alt3=85;}
                }
                break;
            case 'S':
                {
                int LA3_95 = input.LA(3);

                if ( (LA3_95=='C') ) {
                    int LA3_164 = input.LA(4);

                    if ( (LA3_164=='#'||LA3_164=='%'||LA3_164=='*'||LA3_164=='-'||(LA3_164>='/' && LA3_164<=':')||(LA3_164>='A' && LA3_164<='Z')||LA3_164=='_'||(LA3_164>='a' && LA3_164<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=80;}
                }
                else {
                    alt3=85;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'o':
            {
            int LA3_25 = input.LA(2);

            if ( (LA3_25=='r') ) {
                switch ( input.LA(3) ) {
                case 'd':
                    {
                    int LA3_165 = input.LA(4);

                    if ( (LA3_165=='e') ) {
                        int LA3_237 = input.LA(5);

                        if ( (LA3_237=='r') ) {
                            int LA3_294 = input.LA(6);

                            if ( (LA3_294=='#'||LA3_294=='%'||LA3_294=='*'||LA3_294=='-'||(LA3_294>='/' && LA3_294<=':')||(LA3_294>='A' && LA3_294<='Z')||LA3_294=='_'||(LA3_294>='a' && LA3_294<='z')) ) {
                                alt3=85;
                            }
                            else {
                                alt3=65;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                    }
                    break;
                case '#':
                case '%':
                case '*':
                case '-':
                case '/':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case ':':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case '_':
                case 'a':
                case 'b':
                case 'c':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt3=85;
                    }
                    break;
                default:
                    alt3=69;}

            }
            else {
                alt3=85;}
            }
            break;
        case 'O':
            {
            int LA3_26 = input.LA(2);

            if ( (LA3_26=='R') ) {
                switch ( input.LA(3) ) {
                case 'D':
                    {
                    int LA3_167 = input.LA(4);

                    if ( (LA3_167=='E') ) {
                        int LA3_238 = input.LA(5);

                        if ( (LA3_238=='R') ) {
                            int LA3_295 = input.LA(6);

                            if ( (LA3_295=='#'||LA3_295=='%'||LA3_295=='*'||LA3_295=='-'||(LA3_295>='/' && LA3_295<=':')||(LA3_295>='A' && LA3_295<='Z')||LA3_295=='_'||(LA3_295>='a' && LA3_295<='z')) ) {
                                alt3=85;
                            }
                            else {
                                alt3=66;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                    }
                    break;
                case '#':
                case '%':
                case '*':
                case '-':
                case '/':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case ':':
                case 'A':
                case 'B':
                case 'C':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case '_':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt3=85;
                    }
                    break;
                default:
                    alt3=70;}

            }
            else {
                alt3=85;}
            }
            break;
        case 'B':
            {
            switch ( input.LA(2) ) {
            case 'Y':
                {
                int LA3_98 = input.LA(3);

                if ( (LA3_98=='#'||LA3_98=='%'||LA3_98=='*'||LA3_98=='-'||(LA3_98>='/' && LA3_98<=':')||(LA3_98>='A' && LA3_98<='Z')||LA3_98=='_'||(LA3_98>='a' && LA3_98<='z')) ) {
                    alt3=85;
                }
                else {
                    alt3=68;}
                }
                break;
            case 'E':
                {
                int LA3_99 = input.LA(3);

                if ( (LA3_99=='T') ) {
                    int LA3_170 = input.LA(4);

                    if ( (LA3_170=='W') ) {
                        int LA3_239 = input.LA(5);

                        if ( (LA3_239=='E') ) {
                            int LA3_296 = input.LA(6);

                            if ( (LA3_296=='E') ) {
                                int LA3_340 = input.LA(7);

                                if ( (LA3_340=='N') ) {
                                    int LA3_373 = input.LA(8);

                                    if ( (LA3_373=='#'||LA3_373=='%'||LA3_373=='*'||LA3_373=='-'||(LA3_373>='/' && LA3_373<=':')||(LA3_373>='A' && LA3_373<='Z')||LA3_373=='_'||(LA3_373>='a' && LA3_373<='z')) ) {
                                        alt3=85;
                                    }
                                    else {
                                        alt3=84;}
                                }
                                else {
                                    alt3=85;}
                            }
                            else {
                                alt3=85;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
                }
                break;
            default:
                alt3=85;}

            }
            break;
        case 'I':
            {
            int LA3_28 = input.LA(2);

            if ( (LA3_28=='N') ) {
                int LA3_100 = input.LA(3);

                if ( (LA3_100=='#'||LA3_100=='%'||LA3_100=='*'||LA3_100=='-'||(LA3_100>='/' && LA3_100<=':')||(LA3_100>='A' && LA3_100<='Z')||LA3_100=='_'||(LA3_100>='a' && LA3_100<='z')) ) {
                    alt3=85;
                }
                else {
                    alt3=71;}
            }
            else {
                alt3=85;}
            }
            break;
        case 'N':
            {
            int LA3_29 = input.LA(2);

            if ( (LA3_29=='O') ) {
                int LA3_101 = input.LA(3);

                if ( (LA3_101=='T') ) {
                    int LA3_172 = input.LA(4);

                    if ( (LA3_172=='#'||LA3_172=='%'||LA3_172=='*'||LA3_172=='-'||(LA3_172>='/' && LA3_172<=':')||(LA3_172>='A' && LA3_172<='Z')||LA3_172=='_'||(LA3_172>='a' && LA3_172<='z')) ) {
                        alt3=85;
                    }
                    else {
                        alt3=73;}
                }
                else {
                    alt3=85;}
            }
            else {
                alt3=85;}
            }
            break;
        case 'L':
            {
            int LA3_30 = input.LA(2);

            if ( (LA3_30=='I') ) {
                int LA3_102 = input.LA(3);

                if ( (LA3_102=='K') ) {
                    int LA3_173 = input.LA(4);

                    if ( (LA3_173=='E') ) {
                        int LA3_241 = input.LA(5);

                        if ( (LA3_241=='#'||LA3_241=='%'||LA3_241=='*'||LA3_241=='-'||(LA3_241>='/' && LA3_241<=':')||(LA3_241>='A' && LA3_241<='Z')||LA3_241=='_'||(LA3_241>='a' && LA3_241<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=75;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
            }
            else {
                alt3=85;}
            }
            break;
        case 'C':
            {
            int LA3_31 = input.LA(2);

            if ( (LA3_31=='O') ) {
                int LA3_103 = input.LA(3);

                if ( (LA3_103=='U') ) {
                    int LA3_174 = input.LA(4);

                    if ( (LA3_174=='N') ) {
                        int LA3_242 = input.LA(5);

                        if ( (LA3_242=='T') ) {
                            int LA3_298 = input.LA(6);

                            if ( (LA3_298=='#'||LA3_298=='%'||LA3_298=='*'||LA3_298=='-'||(LA3_298>='/' && LA3_298<=':')||(LA3_298>='A' && LA3_298<='Z')||LA3_298=='_'||(LA3_298>='a' && LA3_298<='z')) ) {
                                alt3=85;
                            }
                            else {
                                alt3=76;}
                        }
                        else {
                            alt3=85;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
            }
            else {
                alt3=85;}
            }
            break;
        case 'D':
            {
            int LA3_32 = input.LA(2);

            if ( (LA3_32=='E') ) {
                int LA3_104 = input.LA(3);

                if ( (LA3_104=='S') ) {
                    int LA3_175 = input.LA(4);

                    if ( (LA3_175=='C') ) {
                        int LA3_243 = input.LA(5);

                        if ( (LA3_243=='#'||LA3_243=='%'||LA3_243=='*'||LA3_243=='-'||(LA3_243>='/' && LA3_243<=':')||(LA3_243>='A' && LA3_243<='Z')||LA3_243=='_'||(LA3_243>='a' && LA3_243<='z')) ) {
                            alt3=85;
                        }
                        else {
                            alt3=82;}
                    }
                    else {
                        alt3=85;}
                }
                else {
                    alt3=85;}
            }
            else {
                alt3=85;}
            }
            break;
        case '#':
        case '%':
        case '*':
        case '-':
        case '/':
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
        case ':':
        case 'E':
        case 'G':
        case 'H':
        case 'J':
        case 'K':
        case 'M':
        case 'P':
        case 'Q':
        case 'R':
        case 'T':
        case 'U':
        case 'V':
        case 'X':
        case 'Y':
        case 'Z':
        case '_':
        case 'j':
        case 'k':
        case 'q':
        case 'u':
        case 'x':
        case 'y':
        case 'z':
            {
            alt3=85;
            }
            break;
        case ',':
            {
            alt3=86;
            }
            break;
        case ' ':
            {
            int LA3_35 = input.LA(2);

            if ( ((LA3_35>='\t' && LA3_35<='\n')||(LA3_35>='\f' && LA3_35<='\r')||LA3_35==' ') ) {
                alt3=95;
            }
            else {
                alt3=87;}
            }
            break;
        case '.':
            {
            alt3=88;
            }
            break;
        case '>':
            {
            alt3=89;
            }
            break;
        case '<':
            {
            alt3=90;
            }
            break;
        case '=':
            {
            alt3=91;
            }
            break;
        case '!':
            {
            alt3=92;
            }
            break;
        case '&':
            {
            alt3=93;
            }
            break;
        case '\n':
            {
            int LA3_42 = input.LA(2);

            if ( ((LA3_42>='\t' && LA3_42<='\n')||(LA3_42>='\f' && LA3_42<='\r')||LA3_42==' ') ) {
                alt3=95;
            }
            else {
                alt3=94;}
            }
            break;
        case '\t':
        case '\f':
        case '\r':
            {
            alt3=95;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | T63 | T64 | T65 | T66 | T67 | T68 | T69 | T70 | T71 | T72 | T73 | T74 | T75 | T76 | T77 | T78 | T79 | T80 | T81 | T82 | T83 | T84 | T85 | T86 | T87 | T88 | T89 | T90 | T91 | T92 | T93 | T94 | T95 | T96 | T97 | T98 | VALUE | COMMA | SPACE | DOT | GT | LT | EQ | NOT | AMP | NL | WS );", 3, 0, input);

            throw nvae;
        }

        switch (alt3) {
            case 1 :
                // Sql.g:1:10: T15
                {
                mT15(); 

                }
                break;
            case 2 :
                // Sql.g:1:14: T16
                {
                mT16(); 

                }
                break;
            case 3 :
                // Sql.g:1:18: T17
                {
                mT17(); 

                }
                break;
            case 4 :
                // Sql.g:1:22: T18
                {
                mT18(); 

                }
                break;
            case 5 :
                // Sql.g:1:26: T19
                {
                mT19(); 

                }
                break;
            case 6 :
                // Sql.g:1:30: T20
                {
                mT20(); 

                }
                break;
            case 7 :
                // Sql.g:1:34: T21
                {
                mT21(); 

                }
                break;
            case 8 :
                // Sql.g:1:38: T22
                {
                mT22(); 

                }
                break;
            case 9 :
                // Sql.g:1:42: T23
                {
                mT23(); 

                }
                break;
            case 10 :
                // Sql.g:1:46: T24
                {
                mT24(); 

                }
                break;
            case 11 :
                // Sql.g:1:50: T25
                {
                mT25(); 

                }
                break;
            case 12 :
                // Sql.g:1:54: T26
                {
                mT26(); 

                }
                break;
            case 13 :
                // Sql.g:1:58: T27
                {
                mT27(); 

                }
                break;
            case 14 :
                // Sql.g:1:62: T28
                {
                mT28(); 

                }
                break;
            case 15 :
                // Sql.g:1:66: T29
                {
                mT29(); 

                }
                break;
            case 16 :
                // Sql.g:1:70: T30
                {
                mT30(); 

                }
                break;
            case 17 :
                // Sql.g:1:74: T31
                {
                mT31(); 

                }
                break;
            case 18 :
                // Sql.g:1:78: T32
                {
                mT32(); 

                }
                break;
            case 19 :
                // Sql.g:1:82: T33
                {
                mT33(); 

                }
                break;
            case 20 :
                // Sql.g:1:86: T34
                {
                mT34(); 

                }
                break;
            case 21 :
                // Sql.g:1:90: T35
                {
                mT35(); 

                }
                break;
            case 22 :
                // Sql.g:1:94: T36
                {
                mT36(); 

                }
                break;
            case 23 :
                // Sql.g:1:98: T37
                {
                mT37(); 

                }
                break;
            case 24 :
                // Sql.g:1:102: T38
                {
                mT38(); 

                }
                break;
            case 25 :
                // Sql.g:1:106: T39
                {
                mT39(); 

                }
                break;
            case 26 :
                // Sql.g:1:110: T40
                {
                mT40(); 

                }
                break;
            case 27 :
                // Sql.g:1:114: T41
                {
                mT41(); 

                }
                break;
            case 28 :
                // Sql.g:1:118: T42
                {
                mT42(); 

                }
                break;
            case 29 :
                // Sql.g:1:122: T43
                {
                mT43(); 

                }
                break;
            case 30 :
                // Sql.g:1:126: T44
                {
                mT44(); 

                }
                break;
            case 31 :
                // Sql.g:1:130: T45
                {
                mT45(); 

                }
                break;
            case 32 :
                // Sql.g:1:134: T46
                {
                mT46(); 

                }
                break;
            case 33 :
                // Sql.g:1:138: T47
                {
                mT47(); 

                }
                break;
            case 34 :
                // Sql.g:1:142: T48
                {
                mT48(); 

                }
                break;
            case 35 :
                // Sql.g:1:146: T49
                {
                mT49(); 

                }
                break;
            case 36 :
                // Sql.g:1:150: T50
                {
                mT50(); 

                }
                break;
            case 37 :
                // Sql.g:1:154: T51
                {
                mT51(); 

                }
                break;
            case 38 :
                // Sql.g:1:158: T52
                {
                mT52(); 

                }
                break;
            case 39 :
                // Sql.g:1:162: T53
                {
                mT53(); 

                }
                break;
            case 40 :
                // Sql.g:1:166: T54
                {
                mT54(); 

                }
                break;
            case 41 :
                // Sql.g:1:170: T55
                {
                mT55(); 

                }
                break;
            case 42 :
                // Sql.g:1:174: T56
                {
                mT56(); 

                }
                break;
            case 43 :
                // Sql.g:1:178: T57
                {
                mT57(); 

                }
                break;
            case 44 :
                // Sql.g:1:182: T58
                {
                mT58(); 

                }
                break;
            case 45 :
                // Sql.g:1:186: T59
                {
                mT59(); 

                }
                break;
            case 46 :
                // Sql.g:1:190: T60
                {
                mT60(); 

                }
                break;
            case 47 :
                // Sql.g:1:194: T61
                {
                mT61(); 

                }
                break;
            case 48 :
                // Sql.g:1:198: T62
                {
                mT62(); 

                }
                break;
            case 49 :
                // Sql.g:1:202: T63
                {
                mT63(); 

                }
                break;
            case 50 :
                // Sql.g:1:206: T64
                {
                mT64(); 

                }
                break;
            case 51 :
                // Sql.g:1:210: T65
                {
                mT65(); 

                }
                break;
            case 52 :
                // Sql.g:1:214: T66
                {
                mT66(); 

                }
                break;
            case 53 :
                // Sql.g:1:218: T67
                {
                mT67(); 

                }
                break;
            case 54 :
                // Sql.g:1:222: T68
                {
                mT68(); 

                }
                break;
            case 55 :
                // Sql.g:1:226: T69
                {
                mT69(); 

                }
                break;
            case 56 :
                // Sql.g:1:230: T70
                {
                mT70(); 

                }
                break;
            case 57 :
                // Sql.g:1:234: T71
                {
                mT71(); 

                }
                break;
            case 58 :
                // Sql.g:1:238: T72
                {
                mT72(); 

                }
                break;
            case 59 :
                // Sql.g:1:242: T73
                {
                mT73(); 

                }
                break;
            case 60 :
                // Sql.g:1:246: T74
                {
                mT74(); 

                }
                break;
            case 61 :
                // Sql.g:1:250: T75
                {
                mT75(); 

                }
                break;
            case 62 :
                // Sql.g:1:254: T76
                {
                mT76(); 

                }
                break;
            case 63 :
                // Sql.g:1:258: T77
                {
                mT77(); 

                }
                break;
            case 64 :
                // Sql.g:1:262: T78
                {
                mT78(); 

                }
                break;
            case 65 :
                // Sql.g:1:266: T79
                {
                mT79(); 

                }
                break;
            case 66 :
                // Sql.g:1:270: T80
                {
                mT80(); 

                }
                break;
            case 67 :
                // Sql.g:1:274: T81
                {
                mT81(); 

                }
                break;
            case 68 :
                // Sql.g:1:278: T82
                {
                mT82(); 

                }
                break;
            case 69 :
                // Sql.g:1:282: T83
                {
                mT83(); 

                }
                break;
            case 70 :
                // Sql.g:1:286: T84
                {
                mT84(); 

                }
                break;
            case 71 :
                // Sql.g:1:290: T85
                {
                mT85(); 

                }
                break;
            case 72 :
                // Sql.g:1:294: T86
                {
                mT86(); 

                }
                break;
            case 73 :
                // Sql.g:1:298: T87
                {
                mT87(); 

                }
                break;
            case 74 :
                // Sql.g:1:302: T88
                {
                mT88(); 

                }
                break;
            case 75 :
                // Sql.g:1:306: T89
                {
                mT89(); 

                }
                break;
            case 76 :
                // Sql.g:1:310: T90
                {
                mT90(); 

                }
                break;
            case 77 :
                // Sql.g:1:314: T91
                {
                mT91(); 

                }
                break;
            case 78 :
                // Sql.g:1:318: T92
                {
                mT92(); 

                }
                break;
            case 79 :
                // Sql.g:1:322: T93
                {
                mT93(); 

                }
                break;
            case 80 :
                // Sql.g:1:326: T94
                {
                mT94(); 

                }
                break;
            case 81 :
                // Sql.g:1:330: T95
                {
                mT95(); 

                }
                break;
            case 82 :
                // Sql.g:1:334: T96
                {
                mT96(); 

                }
                break;
            case 83 :
                // Sql.g:1:338: T97
                {
                mT97(); 

                }
                break;
            case 84 :
                // Sql.g:1:342: T98
                {
                mT98(); 

                }
                break;
            case 85 :
                // Sql.g:1:346: VALUE
                {
                mVALUE(); 

                }
                break;
            case 86 :
                // Sql.g:1:352: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 87 :
                // Sql.g:1:358: SPACE
                {
                mSPACE(); 

                }
                break;
            case 88 :
                // Sql.g:1:364: DOT
                {
                mDOT(); 

                }
                break;
            case 89 :
                // Sql.g:1:368: GT
                {
                mGT(); 

                }
                break;
            case 90 :
                // Sql.g:1:371: LT
                {
                mLT(); 

                }
                break;
            case 91 :
                // Sql.g:1:374: EQ
                {
                mEQ(); 

                }
                break;
            case 92 :
                // Sql.g:1:377: NOT
                {
                mNOT(); 

                }
                break;
            case 93 :
                // Sql.g:1:381: AMP
                {
                mAMP(); 

                }
                break;
            case 94 :
                // Sql.g:1:385: NL
                {
                mNL(); 

                }
                break;
            case 95 :
                // Sql.g:1:388: WS
                {
                mWS(); 

                }
                break;

        }

    }


 

}