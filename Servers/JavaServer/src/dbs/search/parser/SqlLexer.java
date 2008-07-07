package dbs.search.parser;
// $ANTLR 3.0.1 Sql.g 2008-07-07 13:17:39

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlLexer extends Lexer {
    public static final int T75=75;
    public static final int T76=76;
    public static final int T73=73;
    public static final int T74=74;
    public static final int LT=8;
    public static final int T79=79;
    public static final int STAR=12;
    public static final int T77=77;
    public static final int T78=78;
    public static final int AMP=11;
    public static final int T29=29;
    public static final int T28=28;
    public static final int T27=27;
    public static final int T26=26;
    public static final int T25=25;
    public static final int SPACE=4;
    public static final int EOF=-1;
    public static final int T24=24;
    public static final int T23=23;
    public static final int T22=22;
    public static final int T72=72;
    public static final int T21=21;
    public static final int T71=71;
    public static final int T20=20;
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
    public static final int T38=38;
    public static final int T37=37;
    public static final int NL=13;
    public static final int EQ=7;
    public static final int T39=39;
    public static final int DOT=6;
    public static final int T34=34;
    public static final int T33=33;
    public static final int T36=36;
    public static final int T35=35;
    public static final int T30=30;
    public static final int T61=61;
    public static final int T32=32;
    public static final int T60=60;
    public static final int T31=31;
    public static final int T49=49;
    public static final int T48=48;
    public static final int VALUE=10;
    public static final int T43=43;
    public static final int Tokens=86;
    public static final int T42=42;
    public static final int T41=41;
    public static final int T40=40;
    public static final int T47=47;
    public static final int T46=46;
    public static final int T45=45;
    public static final int T44=44;
    public static final int T84=84;
    public static final int WS=14;
    public static final int T85=85;
    public static final int T50=50;
    public static final int T59=59;
    public static final int GT=9;
    public static final int T81=81;
    public static final int T52=52;
    public static final int T15=15;
    public static final int T80=80;
    public static final int T51=51;
    public static final int T16=16;
    public static final int T83=83;
    public static final int T54=54;
    public static final int T17=17;
    public static final int T82=82;
    public static final int T53=53;
    public static final int T18=18;
    public static final int T56=56;
    public static final int T19=19;
    public static final int T55=55;
    public static final int T58=58;
    public static final int T57=57;
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
            // Sql.g:7:5: ( 'ads' )
            // Sql.g:7:7: 'ads'
            {
            match("ads"); 


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
            // Sql.g:8:5: ( 'dataset' )
            // Sql.g:8:7: 'dataset'
            {
            match("dataset"); 


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
            // Sql.g:9:5: ( 'release' )
            // Sql.g:9:7: 'release'
            {
            match("release"); 


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
            // Sql.g:10:5: ( 'site' )
            // Sql.g:10:7: 'site'
            {
            match("site"); 


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
            // Sql.g:11:5: ( 'block' )
            // Sql.g:11:7: 'block'
            {
            match("block"); 


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
            // Sql.g:12:5: ( 'file' )
            // Sql.g:12:7: 'file'
            {
            match("file"); 


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
            // Sql.g:13:5: ( 'primds' )
            // Sql.g:13:7: 'primds'
            {
            match("primds"); 


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
            // Sql.g:14:5: ( 'procds' )
            // Sql.g:14:7: 'procds'
            {
            match("procds"); 


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
            // Sql.g:15:5: ( 'run' )
            // Sql.g:15:7: 'run'
            {
            match("run"); 


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
            // Sql.g:16:5: ( 'lumi' )
            // Sql.g:16:7: 'lumi'
            {
            match("lumi"); 


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
            // Sql.g:17:5: ( 'dq' )
            // Sql.g:17:7: 'dq'
            {
            match("dq"); 


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
            // Sql.g:18:5: ( 'ilumi' )
            // Sql.g:18:7: 'ilumi'
            {
            match("ilumi"); 


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
            // Sql.g:19:5: ( 'phygrp' )
            // Sql.g:19:7: 'phygrp'
            {
            match("phygrp"); 


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
            // Sql.g:20:5: ( 'createdate' )
            // Sql.g:20:7: 'createdate'
            {
            match("createdate"); 


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
            // Sql.g:21:5: ( 'moddate' )
            // Sql.g:21:7: 'moddate'
            {
            match("moddate"); 


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
            // Sql.g:22:5: ( 'starttime' )
            // Sql.g:22:7: 'starttime'
            {
            match("starttime"); 


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
            // Sql.g:23:5: ( 'endtime' )
            // Sql.g:23:7: 'endtime'
            {
            match("endtime"); 


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
            // Sql.g:24:5: ( 'createby' )
            // Sql.g:24:7: 'createby'
            {
            match("createby"); 


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
            // Sql.g:25:5: ( 'modby' )
            // Sql.g:25:7: 'modby'
            {
            match("modby"); 


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
            // Sql.g:26:5: ( 'name' )
            // Sql.g:26:7: 'name'
            {
            match("name"); 


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
            // Sql.g:27:5: ( 'version' )
            // Sql.g:27:7: 'version'
            {
            match("version"); 


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
            // Sql.g:28:5: ( 'number' )
            // Sql.g:28:7: 'number'
            {
            match("number"); 


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
            // Sql.g:29:5: ( 'startevnum' )
            // Sql.g:29:7: 'startevnum'
            {
            match("startevnum"); 


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
            // Sql.g:30:5: ( 'endevnum' )
            // Sql.g:30:7: 'endevnum'
            {
            match("endevnum"); 


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
            // Sql.g:31:5: ( 'numevents' )
            // Sql.g:31:7: 'numevents'
            {
            match("numevents"); 


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
            // Sql.g:32:5: ( 'numlss' )
            // Sql.g:32:7: 'numlss'
            {
            match("numlss"); 


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
            // Sql.g:33:5: ( 'size' )
            // Sql.g:33:7: 'size'
            {
            match("size"); 


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
            // Sql.g:34:5: ( 'count' )
            // Sql.g:34:7: 'count'
            {
            match("count"); 


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
            // Sql.g:35:5: ( 'status' )
            // Sql.g:35:7: 'status'
            {
            match("status"); 


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
            // Sql.g:36:5: ( 'type' )
            // Sql.g:36:7: 'type'
            {
            match("type"); 


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
            // Sql.g:37:5: ( 'id' )
            // Sql.g:37:7: 'id'
            {
            match("id"); 


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
            // Sql.g:38:5: ( 'parent' )
            // Sql.g:38:7: 'parent'
            {
            match("parent"); 


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
            // Sql.g:39:5: ( 'child' )
            // Sql.g:39:7: 'child'
            {
            match("child"); 


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
            // Sql.g:40:5: ( 'tier' )
            // Sql.g:40:7: 'tier'
            {
            match("tier"); 


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
            // Sql.g:41:5: ( 'def' )
            // Sql.g:41:7: 'def'
            {
            match("def"); 


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
            // Sql.g:42:5: ( 'evnum' )
            // Sql.g:42:7: 'evnum'
            {
            match("evnum"); 


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
            // Sql.g:43:5: ( 'era' )
            // Sql.g:43:7: 'era'
            {
            match("era"); 


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
            // Sql.g:44:5: ( 'tag' )
            // Sql.g:44:7: 'tag'
            {
            match("tag"); 


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
            // Sql.g:45:5: ( 'numruns()' )
            // Sql.g:45:7: 'numruns()'
            {
            match("numruns()"); 


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
            // Sql.g:46:5: ( 'numfiles()' )
            // Sql.g:46:7: 'numfiles()'
            {
            match("numfiles()"); 


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
            // Sql.g:47:5: ( 'dataquality()' )
            // Sql.g:47:7: 'dataquality()'
            {
            match("dataquality()"); 


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
            // Sql.g:48:5: ( 'latest()' )
            // Sql.g:48:7: 'latest()'
            {
            match("latest()"); 


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
            // Sql.g:49:5: ( 'parentrelease()' )
            // Sql.g:49:7: 'parentrelease()'
            {
            match("parentrelease()"); 


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
            // Sql.g:50:5: ( 'childrelease()' )
            // Sql.g:50:7: 'childrelease()'
            {
            match("childrelease()"); 


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
            // Sql.g:51:5: ( 'intluminosity()' )
            // Sql.g:51:7: 'intluminosity()'
            {
            match("intluminosity()"); 


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
            // Sql.g:52:5: ( 'findevents()' )
            // Sql.g:52:7: 'findevents()'
            {
            match("findevents()"); 


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
            // Sql.g:53:5: ( 'select' )
            // Sql.g:53:7: 'select'
            {
            match("select"); 


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
            // Sql.g:54:5: ( 'SELECT' )
            // Sql.g:54:7: 'SELECT'
            {
            match("SELECT"); 


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
            // Sql.g:55:5: ( 'find' )
            // Sql.g:55:7: 'find'
            {
            match("find"); 


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
            // Sql.g:56:5: ( 'FIND' )
            // Sql.g:56:7: 'FIND'
            {
            match("FIND"); 


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
            // Sql.g:57:5: ( 'and' )
            // Sql.g:57:7: 'and'
            {
            match("and"); 


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
            // Sql.g:58:5: ( 'AND' )
            // Sql.g:58:7: 'AND'
            {
            match("AND"); 


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
            // Sql.g:59:5: ( 'order' )
            // Sql.g:59:7: 'order'
            {
            match("order"); 


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
            // Sql.g:60:5: ( 'ORDER' )
            // Sql.g:60:7: 'ORDER'
            {
            match("ORDER"); 


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
            // Sql.g:61:5: ( 'by' )
            // Sql.g:61:7: 'by'
            {
            match("by"); 


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
            // Sql.g:62:5: ( 'BY' )
            // Sql.g:62:7: 'BY'
            {
            match("BY"); 


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
            // Sql.g:63:5: ( 'or' )
            // Sql.g:63:7: 'or'
            {
            match("or"); 


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
            // Sql.g:64:5: ( 'OR' )
            // Sql.g:64:7: 'OR'
            {
            match("OR"); 


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
            // Sql.g:65:5: ( 'in' )
            // Sql.g:65:7: 'in'
            {
            match("in"); 


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
            // Sql.g:66:5: ( 'IN' )
            // Sql.g:66:7: 'IN'
            {
            match("IN"); 


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
            // Sql.g:67:5: ( 'not' )
            // Sql.g:67:7: 'not'
            {
            match("not"); 


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
            // Sql.g:68:5: ( 'NOT' )
            // Sql.g:68:7: 'NOT'
            {
            match("NOT"); 


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
            // Sql.g:69:5: ( 'like' )
            // Sql.g:69:7: 'like'
            {
            match("like"); 


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
            // Sql.g:70:5: ( 'LIKE' )
            // Sql.g:70:7: 'LIKE'
            {
            match("LIKE"); 


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
            // Sql.g:71:5: ( 'COUNT' )
            // Sql.g:71:7: 'COUNT'
            {
            match("COUNT"); 


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
            // Sql.g:72:5: ( 'sum' )
            // Sql.g:72:7: 'sum'
            {
            match("sum"); 


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
            // Sql.g:73:5: ( 'SUM' )
            // Sql.g:73:7: 'SUM'
            {
            match("SUM"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T85

    // $ANTLR start VALUE
    public final void mVALUE() throws RecognitionException {
        try {
            int _type = VALUE;
            // Sql.g:107:8: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' )+ )
            // Sql.g:107:9: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' )+
            {
            // Sql.g:107:9: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='#'||LA1_0=='-'||(LA1_0>='/' && LA1_0<=':')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Sql.g:
            	    {
            	    if ( input.LA(1)=='#'||input.LA(1)=='-'||(input.LA(1)>='/' && input.LA(1)<=':')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
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
            // Sql.g:112:8: ( ( ',' ) )
            // Sql.g:112:9: ( ',' )
            {
            // Sql.g:112:9: ( ',' )
            // Sql.g:112:10: ','
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
            // Sql.g:113:8: ( ( ' ' ) )
            // Sql.g:113:9: ( ' ' )
            {
            // Sql.g:113:9: ( ' ' )
            // Sql.g:113:10: ' '
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
            // Sql.g:114:6: ( ( '.' ) )
            // Sql.g:114:7: ( '.' )
            {
            // Sql.g:114:7: ( '.' )
            // Sql.g:114:8: '.'
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
            // Sql.g:116:5: ( ( '>' ) )
            // Sql.g:116:6: ( '>' )
            {
            // Sql.g:116:6: ( '>' )
            // Sql.g:116:7: '>'
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
            // Sql.g:117:5: ( ( '<' ) )
            // Sql.g:117:6: ( '<' )
            {
            // Sql.g:117:6: ( '<' )
            // Sql.g:117:7: '<'
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
            // Sql.g:118:5: ( ( '=' ) )
            // Sql.g:118:6: ( '=' )
            {
            // Sql.g:118:6: ( '=' )
            // Sql.g:118:7: '='
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

    // $ANTLR start AMP
    public final void mAMP() throws RecognitionException {
        try {
            int _type = AMP;
            // Sql.g:119:6: ( ( '&' ) )
            // Sql.g:119:7: ( '&' )
            {
            // Sql.g:119:7: ( '&' )
            // Sql.g:119:8: '&'
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

    // $ANTLR start STAR
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            // Sql.g:120:7: ( ( '*' | '%' ) )
            // Sql.g:120:8: ( '*' | '%' )
            {
            if ( input.LA(1)=='%'||input.LA(1)=='*' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STAR

    // $ANTLR start NL
    public final void mNL() throws RecognitionException {
        try {
            int _type = NL;
            // Sql.g:121:5: ( ( '\\n' ) )
            // Sql.g:121:6: ( '\\n' )
            {
            // Sql.g:121:6: ( '\\n' )
            // Sql.g:121:7: '\\n'
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
            // Sql.g:122:6: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // Sql.g:122:8: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // Sql.g:122:8: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
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
        // Sql.g:1:8: ( T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | T63 | T64 | T65 | T66 | T67 | T68 | T69 | T70 | T71 | T72 | T73 | T74 | T75 | T76 | T77 | T78 | T79 | T80 | T81 | T82 | T83 | T84 | T85 | VALUE | COMMA | SPACE | DOT | GT | LT | EQ | AMP | STAR | NL | WS )
        int alt3=82;
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
                int LA3_41 = input.LA(3);

                if ( (LA3_41=='E') ) {
                    int LA3_93 = input.LA(4);

                    if ( (LA3_93=='R') ) {
                        int LA3_149 = input.LA(5);

                        if ( (LA3_149=='E') ) {
                            int LA3_204 = input.LA(6);

                            if ( (LA3_204=='#'||LA3_204=='-'||(LA3_204>='/' && LA3_204<=':')||(LA3_204>='A' && LA3_204<='Z')||LA3_204=='_'||(LA3_204>='a' && LA3_204<='z')) ) {
                                alt3=72;
                            }
                            else {
                                alt3=3;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
            }
            else {
                alt3=72;}
            }
            break;
        case 'w':
            {
            int LA3_4 = input.LA(2);

            if ( (LA3_4=='h') ) {
                int LA3_42 = input.LA(3);

                if ( (LA3_42=='e') ) {
                    int LA3_94 = input.LA(4);

                    if ( (LA3_94=='r') ) {
                        int LA3_150 = input.LA(5);

                        if ( (LA3_150=='e') ) {
                            int LA3_205 = input.LA(6);

                            if ( (LA3_205=='#'||LA3_205=='-'||(LA3_205>='/' && LA3_205<=':')||(LA3_205>='A' && LA3_205<='Z')||LA3_205=='_'||(LA3_205>='a' && LA3_205<='z')) ) {
                                alt3=72;
                            }
                            else {
                                alt3=4;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
            }
            else {
                alt3=72;}
            }
            break;
        case 'a':
            {
            switch ( input.LA(2) ) {
            case 'd':
                {
                int LA3_43 = input.LA(3);

                if ( (LA3_43=='s') ) {
                    int LA3_95 = input.LA(4);

                    if ( (LA3_95=='#'||LA3_95=='-'||(LA3_95>='/' && LA3_95<=':')||(LA3_95>='A' && LA3_95<='Z')||LA3_95=='_'||(LA3_95>='a' && LA3_95<='z')) ) {
                        alt3=72;
                    }
                    else {
                        alt3=5;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'n':
                {
                int LA3_44 = input.LA(3);

                if ( (LA3_44=='d') ) {
                    int LA3_96 = input.LA(4);

                    if ( (LA3_96=='#'||LA3_96=='-'||(LA3_96>='/' && LA3_96<=':')||(LA3_96>='A' && LA3_96<='Z')||LA3_96=='_'||(LA3_96>='a' && LA3_96<='z')) ) {
                        alt3=72;
                    }
                    else {
                        alt3=55;}
                }
                else {
                    alt3=72;}
                }
                break;
            default:
                alt3=72;}

            }
            break;
        case 'd':
            {
            switch ( input.LA(2) ) {
            case 'q':
                {
                int LA3_45 = input.LA(3);

                if ( (LA3_45=='#'||LA3_45=='-'||(LA3_45>='/' && LA3_45<=':')||(LA3_45>='A' && LA3_45<='Z')||LA3_45=='_'||(LA3_45>='a' && LA3_45<='z')) ) {
                    alt3=72;
                }
                else {
                    alt3=15;}
                }
                break;
            case 'a':
                {
                int LA3_46 = input.LA(3);

                if ( (LA3_46=='t') ) {
                    int LA3_98 = input.LA(4);

                    if ( (LA3_98=='a') ) {
                        switch ( input.LA(5) ) {
                        case 'q':
                            {
                            int LA3_206 = input.LA(6);

                            if ( (LA3_206=='u') ) {
                                int LA3_252 = input.LA(7);

                                if ( (LA3_252=='a') ) {
                                    int LA3_287 = input.LA(8);

                                    if ( (LA3_287=='l') ) {
                                        int LA3_315 = input.LA(9);

                                        if ( (LA3_315=='i') ) {
                                            int LA3_333 = input.LA(10);

                                            if ( (LA3_333=='t') ) {
                                                int LA3_345 = input.LA(11);

                                                if ( (LA3_345=='y') ) {
                                                    int LA3_354 = input.LA(12);

                                                    if ( (LA3_354=='(') ) {
                                                        alt3=45;
                                                    }
                                                    else {
                                                        alt3=72;}
                                                }
                                                else {
                                                    alt3=72;}
                                            }
                                            else {
                                                alt3=72;}
                                        }
                                        else {
                                            alt3=72;}
                                    }
                                    else {
                                        alt3=72;}
                                }
                                else {
                                    alt3=72;}
                            }
                            else {
                                alt3=72;}
                            }
                            break;
                        case 's':
                            {
                            int LA3_207 = input.LA(6);

                            if ( (LA3_207=='e') ) {
                                int LA3_253 = input.LA(7);

                                if ( (LA3_253=='t') ) {
                                    int LA3_288 = input.LA(8);

                                    if ( (LA3_288=='#'||LA3_288=='-'||(LA3_288>='/' && LA3_288<=':')||(LA3_288>='A' && LA3_288<='Z')||LA3_288=='_'||(LA3_288>='a' && LA3_288<='z')) ) {
                                        alt3=72;
                                    }
                                    else {
                                        alt3=6;}
                                }
                                else {
                                    alt3=72;}
                            }
                            else {
                                alt3=72;}
                            }
                            break;
                        default:
                            alt3=72;}

                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'e':
                {
                int LA3_47 = input.LA(3);

                if ( (LA3_47=='f') ) {
                    int LA3_99 = input.LA(4);

                    if ( (LA3_99=='#'||LA3_99=='-'||(LA3_99>='/' && LA3_99<=':')||(LA3_99>='A' && LA3_99<='Z')||LA3_99=='_'||(LA3_99>='a' && LA3_99<='z')) ) {
                        alt3=72;
                    }
                    else {
                        alt3=39;}
                }
                else {
                    alt3=72;}
                }
                break;
            default:
                alt3=72;}

            }
            break;
        case 'r':
            {
            switch ( input.LA(2) ) {
            case 'u':
                {
                int LA3_48 = input.LA(3);

                if ( (LA3_48=='n') ) {
                    int LA3_100 = input.LA(4);

                    if ( (LA3_100=='#'||LA3_100=='-'||(LA3_100>='/' && LA3_100<=':')||(LA3_100>='A' && LA3_100<='Z')||LA3_100=='_'||(LA3_100>='a' && LA3_100<='z')) ) {
                        alt3=72;
                    }
                    else {
                        alt3=13;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'e':
                {
                int LA3_49 = input.LA(3);

                if ( (LA3_49=='l') ) {
                    int LA3_101 = input.LA(4);

                    if ( (LA3_101=='e') ) {
                        int LA3_156 = input.LA(5);

                        if ( (LA3_156=='a') ) {
                            int LA3_208 = input.LA(6);

                            if ( (LA3_208=='s') ) {
                                int LA3_254 = input.LA(7);

                                if ( (LA3_254=='e') ) {
                                    int LA3_289 = input.LA(8);

                                    if ( (LA3_289=='#'||LA3_289=='-'||(LA3_289>='/' && LA3_289<=':')||(LA3_289>='A' && LA3_289<='Z')||LA3_289=='_'||(LA3_289>='a' && LA3_289<='z')) ) {
                                        alt3=72;
                                    }
                                    else {
                                        alt3=7;}
                                }
                                else {
                                    alt3=72;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            default:
                alt3=72;}

            }
            break;
        case 's':
            {
            switch ( input.LA(2) ) {
            case 't':
                {
                int LA3_50 = input.LA(3);

                if ( (LA3_50=='a') ) {
                    switch ( input.LA(4) ) {
                    case 't':
                        {
                        int LA3_157 = input.LA(5);

                        if ( (LA3_157=='u') ) {
                            int LA3_209 = input.LA(6);

                            if ( (LA3_209=='s') ) {
                                int LA3_255 = input.LA(7);

                                if ( (LA3_255=='#'||LA3_255=='-'||(LA3_255>='/' && LA3_255<=':')||(LA3_255>='A' && LA3_255<='Z')||LA3_255=='_'||(LA3_255>='a' && LA3_255<='z')) ) {
                                    alt3=72;
                                }
                                else {
                                    alt3=33;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                        }
                        break;
                    case 'r':
                        {
                        int LA3_158 = input.LA(5);

                        if ( (LA3_158=='t') ) {
                            switch ( input.LA(6) ) {
                            case 't':
                                {
                                int LA3_256 = input.LA(7);

                                if ( (LA3_256=='i') ) {
                                    int LA3_291 = input.LA(8);

                                    if ( (LA3_291=='m') ) {
                                        int LA3_318 = input.LA(9);

                                        if ( (LA3_318=='e') ) {
                                            int LA3_334 = input.LA(10);

                                            if ( (LA3_334=='#'||LA3_334=='-'||(LA3_334>='/' && LA3_334<=':')||(LA3_334>='A' && LA3_334<='Z')||LA3_334=='_'||(LA3_334>='a' && LA3_334<='z')) ) {
                                                alt3=72;
                                            }
                                            else {
                                                alt3=20;}
                                        }
                                        else {
                                            alt3=72;}
                                    }
                                    else {
                                        alt3=72;}
                                }
                                else {
                                    alt3=72;}
                                }
                                break;
                            case 'e':
                                {
                                int LA3_257 = input.LA(7);

                                if ( (LA3_257=='v') ) {
                                    int LA3_292 = input.LA(8);

                                    if ( (LA3_292=='n') ) {
                                        int LA3_319 = input.LA(9);

                                        if ( (LA3_319=='u') ) {
                                            int LA3_335 = input.LA(10);

                                            if ( (LA3_335=='m') ) {
                                                int LA3_347 = input.LA(11);

                                                if ( (LA3_347=='#'||LA3_347=='-'||(LA3_347>='/' && LA3_347<=':')||(LA3_347>='A' && LA3_347<='Z')||LA3_347=='_'||(LA3_347>='a' && LA3_347<='z')) ) {
                                                    alt3=72;
                                                }
                                                else {
                                                    alt3=27;}
                                            }
                                            else {
                                                alt3=72;}
                                        }
                                        else {
                                            alt3=72;}
                                    }
                                    else {
                                        alt3=72;}
                                }
                                else {
                                    alt3=72;}
                                }
                                break;
                            default:
                                alt3=72;}

                        }
                        else {
                            alt3=72;}
                        }
                        break;
                    default:
                        alt3=72;}

                }
                else {
                    alt3=72;}
                }
                break;
            case 'u':
                {
                int LA3_51 = input.LA(3);

                if ( (LA3_51=='m') ) {
                    int LA3_103 = input.LA(4);

                    if ( (LA3_103=='#'||LA3_103=='-'||(LA3_103>='/' && LA3_103<=':')||(LA3_103>='A' && LA3_103<='Z')||LA3_103=='_'||(LA3_103>='a' && LA3_103<='z')) ) {
                        alt3=72;
                    }
                    else {
                        alt3=70;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'i':
                {
                switch ( input.LA(3) ) {
                case 't':
                    {
                    int LA3_104 = input.LA(4);

                    if ( (LA3_104=='e') ) {
                        int LA3_160 = input.LA(5);

                        if ( (LA3_160=='#'||LA3_160=='-'||(LA3_160>='/' && LA3_160<=':')||(LA3_160>='A' && LA3_160<='Z')||LA3_160=='_'||(LA3_160>='a' && LA3_160<='z')) ) {
                            alt3=72;
                        }
                        else {
                            alt3=8;}
                    }
                    else {
                        alt3=72;}
                    }
                    break;
                case 'z':
                    {
                    int LA3_105 = input.LA(4);

                    if ( (LA3_105=='e') ) {
                        int LA3_161 = input.LA(5);

                        if ( (LA3_161=='#'||LA3_161=='-'||(LA3_161>='/' && LA3_161<=':')||(LA3_161>='A' && LA3_161<='Z')||LA3_161=='_'||(LA3_161>='a' && LA3_161<='z')) ) {
                            alt3=72;
                        }
                        else {
                            alt3=31;}
                    }
                    else {
                        alt3=72;}
                    }
                    break;
                default:
                    alt3=72;}

                }
                break;
            case 'e':
                {
                int LA3_53 = input.LA(3);

                if ( (LA3_53=='l') ) {
                    int LA3_106 = input.LA(4);

                    if ( (LA3_106=='e') ) {
                        int LA3_162 = input.LA(5);

                        if ( (LA3_162=='c') ) {
                            int LA3_213 = input.LA(6);

                            if ( (LA3_213=='t') ) {
                                int LA3_258 = input.LA(7);

                                if ( (LA3_258=='#'||LA3_258=='-'||(LA3_258>='/' && LA3_258<=':')||(LA3_258>='A' && LA3_258<='Z')||LA3_258=='_'||(LA3_258>='a' && LA3_258<='z')) ) {
                                    alt3=72;
                                }
                                else {
                                    alt3=51;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            default:
                alt3=72;}

            }
            break;
        case 'b':
            {
            switch ( input.LA(2) ) {
            case 'y':
                {
                int LA3_54 = input.LA(3);

                if ( (LA3_54=='#'||LA3_54=='-'||(LA3_54>='/' && LA3_54<=':')||(LA3_54>='A' && LA3_54<='Z')||LA3_54=='_'||(LA3_54>='a' && LA3_54<='z')) ) {
                    alt3=72;
                }
                else {
                    alt3=59;}
                }
                break;
            case 'l':
                {
                int LA3_55 = input.LA(3);

                if ( (LA3_55=='o') ) {
                    int LA3_108 = input.LA(4);

                    if ( (LA3_108=='c') ) {
                        int LA3_163 = input.LA(5);

                        if ( (LA3_163=='k') ) {
                            int LA3_214 = input.LA(6);

                            if ( (LA3_214=='#'||LA3_214=='-'||(LA3_214>='/' && LA3_214<=':')||(LA3_214>='A' && LA3_214<='Z')||LA3_214=='_'||(LA3_214>='a' && LA3_214<='z')) ) {
                                alt3=72;
                            }
                            else {
                                alt3=9;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            default:
                alt3=72;}

            }
            break;
        case 'f':
            {
            int LA3_10 = input.LA(2);

            if ( (LA3_10=='i') ) {
                switch ( input.LA(3) ) {
                case 'n':
                    {
                    int LA3_109 = input.LA(4);

                    if ( (LA3_109=='d') ) {
                        switch ( input.LA(5) ) {
                        case 'e':
                            {
                            int LA3_215 = input.LA(6);

                            if ( (LA3_215=='v') ) {
                                int LA3_260 = input.LA(7);

                                if ( (LA3_260=='e') ) {
                                    int LA3_294 = input.LA(8);

                                    if ( (LA3_294=='n') ) {
                                        int LA3_320 = input.LA(9);

                                        if ( (LA3_320=='t') ) {
                                            int LA3_336 = input.LA(10);

                                            if ( (LA3_336=='s') ) {
                                                int LA3_348 = input.LA(11);

                                                if ( (LA3_348=='(') ) {
                                                    alt3=50;
                                                }
                                                else {
                                                    alt3=72;}
                                            }
                                            else {
                                                alt3=72;}
                                        }
                                        else {
                                            alt3=72;}
                                    }
                                    else {
                                        alt3=72;}
                                }
                                else {
                                    alt3=72;}
                            }
                            else {
                                alt3=72;}
                            }
                            break;
                        case '#':
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
                            alt3=72;
                            }
                            break;
                        default:
                            alt3=53;}

                    }
                    else {
                        alt3=72;}
                    }
                    break;
                case 'l':
                    {
                    int LA3_110 = input.LA(4);

                    if ( (LA3_110=='e') ) {
                        int LA3_165 = input.LA(5);

                        if ( (LA3_165=='#'||LA3_165=='-'||(LA3_165>='/' && LA3_165<=':')||(LA3_165>='A' && LA3_165<='Z')||LA3_165=='_'||(LA3_165>='a' && LA3_165<='z')) ) {
                            alt3=72;
                        }
                        else {
                            alt3=10;}
                    }
                    else {
                        alt3=72;}
                    }
                    break;
                default:
                    alt3=72;}

            }
            else {
                alt3=72;}
            }
            break;
        case 'p':
            {
            switch ( input.LA(2) ) {
            case 'h':
                {
                int LA3_57 = input.LA(3);

                if ( (LA3_57=='y') ) {
                    int LA3_111 = input.LA(4);

                    if ( (LA3_111=='g') ) {
                        int LA3_166 = input.LA(5);

                        if ( (LA3_166=='r') ) {
                            int LA3_218 = input.LA(6);

                            if ( (LA3_218=='p') ) {
                                int LA3_261 = input.LA(7);

                                if ( (LA3_261=='#'||LA3_261=='-'||(LA3_261>='/' && LA3_261<=':')||(LA3_261>='A' && LA3_261<='Z')||LA3_261=='_'||(LA3_261>='a' && LA3_261<='z')) ) {
                                    alt3=72;
                                }
                                else {
                                    alt3=17;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'a':
                {
                int LA3_58 = input.LA(3);

                if ( (LA3_58=='r') ) {
                    int LA3_112 = input.LA(4);

                    if ( (LA3_112=='e') ) {
                        int LA3_167 = input.LA(5);

                        if ( (LA3_167=='n') ) {
                            int LA3_219 = input.LA(6);

                            if ( (LA3_219=='t') ) {
                                switch ( input.LA(7) ) {
                                case 'r':
                                    {
                                    int LA3_296 = input.LA(8);

                                    if ( (LA3_296=='e') ) {
                                        int LA3_321 = input.LA(9);

                                        if ( (LA3_321=='l') ) {
                                            int LA3_337 = input.LA(10);

                                            if ( (LA3_337=='e') ) {
                                                int LA3_349 = input.LA(11);

                                                if ( (LA3_349=='a') ) {
                                                    int LA3_357 = input.LA(12);

                                                    if ( (LA3_357=='s') ) {
                                                        int LA3_362 = input.LA(13);

                                                        if ( (LA3_362=='e') ) {
                                                            int LA3_365 = input.LA(14);

                                                            if ( (LA3_365=='(') ) {
                                                                alt3=47;
                                                            }
                                                            else {
                                                                alt3=72;}
                                                        }
                                                        else {
                                                            alt3=72;}
                                                    }
                                                    else {
                                                        alt3=72;}
                                                }
                                                else {
                                                    alt3=72;}
                                            }
                                            else {
                                                alt3=72;}
                                        }
                                        else {
                                            alt3=72;}
                                    }
                                    else {
                                        alt3=72;}
                                    }
                                    break;
                                case '#':
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
                                    alt3=72;
                                    }
                                    break;
                                default:
                                    alt3=36;}

                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'r':
                {
                switch ( input.LA(3) ) {
                case 'o':
                    {
                    int LA3_113 = input.LA(4);

                    if ( (LA3_113=='c') ) {
                        int LA3_168 = input.LA(5);

                        if ( (LA3_168=='d') ) {
                            int LA3_220 = input.LA(6);

                            if ( (LA3_220=='s') ) {
                                int LA3_263 = input.LA(7);

                                if ( (LA3_263=='#'||LA3_263=='-'||(LA3_263>='/' && LA3_263<=':')||(LA3_263>='A' && LA3_263<='Z')||LA3_263=='_'||(LA3_263>='a' && LA3_263<='z')) ) {
                                    alt3=72;
                                }
                                else {
                                    alt3=12;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                    }
                    break;
                case 'i':
                    {
                    int LA3_114 = input.LA(4);

                    if ( (LA3_114=='m') ) {
                        int LA3_169 = input.LA(5);

                        if ( (LA3_169=='d') ) {
                            int LA3_221 = input.LA(6);

                            if ( (LA3_221=='s') ) {
                                int LA3_264 = input.LA(7);

                                if ( (LA3_264=='#'||LA3_264=='-'||(LA3_264>='/' && LA3_264<=':')||(LA3_264>='A' && LA3_264<='Z')||LA3_264=='_'||(LA3_264>='a' && LA3_264<='z')) ) {
                                    alt3=72;
                                }
                                else {
                                    alt3=11;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                    }
                    break;
                default:
                    alt3=72;}

                }
                break;
            default:
                alt3=72;}

            }
            break;
        case 'l':
            {
            switch ( input.LA(2) ) {
            case 'i':
                {
                int LA3_60 = input.LA(3);

                if ( (LA3_60=='k') ) {
                    int LA3_115 = input.LA(4);

                    if ( (LA3_115=='e') ) {
                        int LA3_170 = input.LA(5);

                        if ( (LA3_170=='#'||LA3_170=='-'||(LA3_170>='/' && LA3_170<=':')||(LA3_170>='A' && LA3_170<='Z')||LA3_170=='_'||(LA3_170>='a' && LA3_170<='z')) ) {
                            alt3=72;
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'u':
                {
                int LA3_61 = input.LA(3);

                if ( (LA3_61=='m') ) {
                    int LA3_116 = input.LA(4);

                    if ( (LA3_116=='i') ) {
                        int LA3_171 = input.LA(5);

                        if ( (LA3_171=='#'||LA3_171=='-'||(LA3_171>='/' && LA3_171<=':')||(LA3_171>='A' && LA3_171<='Z')||LA3_171=='_'||(LA3_171>='a' && LA3_171<='z')) ) {
                            alt3=72;
                        }
                        else {
                            alt3=14;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'a':
                {
                int LA3_62 = input.LA(3);

                if ( (LA3_62=='t') ) {
                    int LA3_117 = input.LA(4);

                    if ( (LA3_117=='e') ) {
                        int LA3_172 = input.LA(5);

                        if ( (LA3_172=='s') ) {
                            int LA3_224 = input.LA(6);

                            if ( (LA3_224=='t') ) {
                                int LA3_265 = input.LA(7);

                                if ( (LA3_265=='(') ) {
                                    alt3=46;
                                }
                                else {
                                    alt3=72;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            default:
                alt3=72;}

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
                    int LA3_118 = input.LA(4);

                    if ( (LA3_118=='l') ) {
                        int LA3_173 = input.LA(5);

                        if ( (LA3_173=='u') ) {
                            int LA3_225 = input.LA(6);

                            if ( (LA3_225=='m') ) {
                                int LA3_266 = input.LA(7);

                                if ( (LA3_266=='i') ) {
                                    int LA3_301 = input.LA(8);

                                    if ( (LA3_301=='n') ) {
                                        int LA3_322 = input.LA(9);

                                        if ( (LA3_322=='o') ) {
                                            int LA3_338 = input.LA(10);

                                            if ( (LA3_338=='s') ) {
                                                int LA3_350 = input.LA(11);

                                                if ( (LA3_350=='i') ) {
                                                    int LA3_358 = input.LA(12);

                                                    if ( (LA3_358=='t') ) {
                                                        int LA3_363 = input.LA(13);

                                                        if ( (LA3_363=='y') ) {
                                                            int LA3_366 = input.LA(14);

                                                            if ( (LA3_366=='(') ) {
                                                                alt3=49;
                                                            }
                                                            else {
                                                                alt3=72;}
                                                        }
                                                        else {
                                                            alt3=72;}
                                                    }
                                                    else {
                                                        alt3=72;}
                                                }
                                                else {
                                                    alt3=72;}
                                            }
                                            else {
                                                alt3=72;}
                                        }
                                        else {
                                            alt3=72;}
                                    }
                                    else {
                                        alt3=72;}
                                }
                                else {
                                    alt3=72;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                    }
                    break;
                case '#':
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
                    alt3=72;
                    }
                    break;
                default:
                    alt3=63;}

                }
                break;
            case 'l':
                {
                int LA3_64 = input.LA(3);

                if ( (LA3_64=='u') ) {
                    int LA3_120 = input.LA(4);

                    if ( (LA3_120=='m') ) {
                        int LA3_174 = input.LA(5);

                        if ( (LA3_174=='i') ) {
                            int LA3_226 = input.LA(6);

                            if ( (LA3_226=='#'||LA3_226=='-'||(LA3_226>='/' && LA3_226<=':')||(LA3_226>='A' && LA3_226<='Z')||LA3_226=='_'||(LA3_226>='a' && LA3_226<='z')) ) {
                                alt3=72;
                            }
                            else {
                                alt3=16;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'd':
                {
                int LA3_65 = input.LA(3);

                if ( (LA3_65=='#'||LA3_65=='-'||(LA3_65>='/' && LA3_65<=':')||(LA3_65>='A' && LA3_65<='Z')||LA3_65=='_'||(LA3_65>='a' && LA3_65<='z')) ) {
                    alt3=72;
                }
                else {
                    alt3=35;}
                }
                break;
            default:
                alt3=72;}

            }
            break;
        case 'c':
            {
            switch ( input.LA(2) ) {
            case 'r':
                {
                int LA3_66 = input.LA(3);

                if ( (LA3_66=='e') ) {
                    int LA3_122 = input.LA(4);

                    if ( (LA3_122=='a') ) {
                        int LA3_175 = input.LA(5);

                        if ( (LA3_175=='t') ) {
                            int LA3_227 = input.LA(6);

                            if ( (LA3_227=='e') ) {
                                switch ( input.LA(7) ) {
                                case 'b':
                                    {
                                    int LA3_302 = input.LA(8);

                                    if ( (LA3_302=='y') ) {
                                        int LA3_323 = input.LA(9);

                                        if ( (LA3_323=='#'||LA3_323=='-'||(LA3_323>='/' && LA3_323<=':')||(LA3_323>='A' && LA3_323<='Z')||LA3_323=='_'||(LA3_323>='a' && LA3_323<='z')) ) {
                                            alt3=72;
                                        }
                                        else {
                                            alt3=22;}
                                    }
                                    else {
                                        alt3=72;}
                                    }
                                    break;
                                case 'd':
                                    {
                                    int LA3_303 = input.LA(8);

                                    if ( (LA3_303=='a') ) {
                                        int LA3_324 = input.LA(9);

                                        if ( (LA3_324=='t') ) {
                                            int LA3_340 = input.LA(10);

                                            if ( (LA3_340=='e') ) {
                                                int LA3_351 = input.LA(11);

                                                if ( (LA3_351=='#'||LA3_351=='-'||(LA3_351>='/' && LA3_351<=':')||(LA3_351>='A' && LA3_351<='Z')||LA3_351=='_'||(LA3_351>='a' && LA3_351<='z')) ) {
                                                    alt3=72;
                                                }
                                                else {
                                                    alt3=18;}
                                            }
                                            else {
                                                alt3=72;}
                                        }
                                        else {
                                            alt3=72;}
                                    }
                                    else {
                                        alt3=72;}
                                    }
                                    break;
                                default:
                                    alt3=72;}

                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'o':
                {
                int LA3_67 = input.LA(3);

                if ( (LA3_67=='u') ) {
                    int LA3_123 = input.LA(4);

                    if ( (LA3_123=='n') ) {
                        int LA3_176 = input.LA(5);

                        if ( (LA3_176=='t') ) {
                            int LA3_228 = input.LA(6);

                            if ( (LA3_228=='#'||LA3_228=='-'||(LA3_228>='/' && LA3_228<=':')||(LA3_228>='A' && LA3_228<='Z')||LA3_228=='_'||(LA3_228>='a' && LA3_228<='z')) ) {
                                alt3=72;
                            }
                            else {
                                alt3=32;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'h':
                {
                int LA3_68 = input.LA(3);

                if ( (LA3_68=='i') ) {
                    int LA3_124 = input.LA(4);

                    if ( (LA3_124=='l') ) {
                        int LA3_177 = input.LA(5);

                        if ( (LA3_177=='d') ) {
                            switch ( input.LA(6) ) {
                            case 'r':
                                {
                                int LA3_270 = input.LA(7);

                                if ( (LA3_270=='e') ) {
                                    int LA3_304 = input.LA(8);

                                    if ( (LA3_304=='l') ) {
                                        int LA3_325 = input.LA(9);

                                        if ( (LA3_325=='e') ) {
                                            int LA3_341 = input.LA(10);

                                            if ( (LA3_341=='a') ) {
                                                int LA3_352 = input.LA(11);

                                                if ( (LA3_352=='s') ) {
                                                    int LA3_360 = input.LA(12);

                                                    if ( (LA3_360=='e') ) {
                                                        int LA3_364 = input.LA(13);

                                                        if ( (LA3_364=='(') ) {
                                                            alt3=48;
                                                        }
                                                        else {
                                                            alt3=72;}
                                                    }
                                                    else {
                                                        alt3=72;}
                                                }
                                                else {
                                                    alt3=72;}
                                            }
                                            else {
                                                alt3=72;}
                                        }
                                        else {
                                            alt3=72;}
                                    }
                                    else {
                                        alt3=72;}
                                }
                                else {
                                    alt3=72;}
                                }
                                break;
                            case '#':
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
                                alt3=72;
                                }
                                break;
                            default:
                                alt3=37;}

                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            default:
                alt3=72;}

            }
            break;
        case 'm':
            {
            int LA3_15 = input.LA(2);

            if ( (LA3_15=='o') ) {
                int LA3_69 = input.LA(3);

                if ( (LA3_69=='d') ) {
                    switch ( input.LA(4) ) {
                    case 'd':
                        {
                        int LA3_178 = input.LA(5);

                        if ( (LA3_178=='a') ) {
                            int LA3_230 = input.LA(6);

                            if ( (LA3_230=='t') ) {
                                int LA3_272 = input.LA(7);

                                if ( (LA3_272=='e') ) {
                                    int LA3_305 = input.LA(8);

                                    if ( (LA3_305=='#'||LA3_305=='-'||(LA3_305>='/' && LA3_305<=':')||(LA3_305>='A' && LA3_305<='Z')||LA3_305=='_'||(LA3_305>='a' && LA3_305<='z')) ) {
                                        alt3=72;
                                    }
                                    else {
                                        alt3=19;}
                                }
                                else {
                                    alt3=72;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                        }
                        break;
                    case 'b':
                        {
                        int LA3_179 = input.LA(5);

                        if ( (LA3_179=='y') ) {
                            int LA3_231 = input.LA(6);

                            if ( (LA3_231=='#'||LA3_231=='-'||(LA3_231>='/' && LA3_231<=':')||(LA3_231>='A' && LA3_231<='Z')||LA3_231=='_'||(LA3_231>='a' && LA3_231<='z')) ) {
                                alt3=72;
                            }
                            else {
                                alt3=23;}
                        }
                        else {
                            alt3=72;}
                        }
                        break;
                    default:
                        alt3=72;}

                }
                else {
                    alt3=72;}
            }
            else {
                alt3=72;}
            }
            break;
        case 'e':
            {
            switch ( input.LA(2) ) {
            case 'n':
                {
                int LA3_70 = input.LA(3);

                if ( (LA3_70=='d') ) {
                    switch ( input.LA(4) ) {
                    case 't':
                        {
                        int LA3_180 = input.LA(5);

                        if ( (LA3_180=='i') ) {
                            int LA3_232 = input.LA(6);

                            if ( (LA3_232=='m') ) {
                                int LA3_274 = input.LA(7);

                                if ( (LA3_274=='e') ) {
                                    int LA3_306 = input.LA(8);

                                    if ( (LA3_306=='#'||LA3_306=='-'||(LA3_306>='/' && LA3_306<=':')||(LA3_306>='A' && LA3_306<='Z')||LA3_306=='_'||(LA3_306>='a' && LA3_306<='z')) ) {
                                        alt3=72;
                                    }
                                    else {
                                        alt3=21;}
                                }
                                else {
                                    alt3=72;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                        }
                        break;
                    case 'e':
                        {
                        int LA3_181 = input.LA(5);

                        if ( (LA3_181=='v') ) {
                            int LA3_233 = input.LA(6);

                            if ( (LA3_233=='n') ) {
                                int LA3_275 = input.LA(7);

                                if ( (LA3_275=='u') ) {
                                    int LA3_307 = input.LA(8);

                                    if ( (LA3_307=='m') ) {
                                        int LA3_328 = input.LA(9);

                                        if ( (LA3_328=='#'||LA3_328=='-'||(LA3_328>='/' && LA3_328<=':')||(LA3_328>='A' && LA3_328<='Z')||LA3_328=='_'||(LA3_328>='a' && LA3_328<='z')) ) {
                                            alt3=72;
                                        }
                                        else {
                                            alt3=28;}
                                    }
                                    else {
                                        alt3=72;}
                                }
                                else {
                                    alt3=72;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                        }
                        break;
                    default:
                        alt3=72;}

                }
                else {
                    alt3=72;}
                }
                break;
            case 'r':
                {
                int LA3_71 = input.LA(3);

                if ( (LA3_71=='a') ) {
                    int LA3_127 = input.LA(4);

                    if ( (LA3_127=='#'||LA3_127=='-'||(LA3_127>='/' && LA3_127<=':')||(LA3_127>='A' && LA3_127<='Z')||LA3_127=='_'||(LA3_127>='a' && LA3_127<='z')) ) {
                        alt3=72;
                    }
                    else {
                        alt3=41;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'v':
                {
                int LA3_72 = input.LA(3);

                if ( (LA3_72=='n') ) {
                    int LA3_128 = input.LA(4);

                    if ( (LA3_128=='u') ) {
                        int LA3_183 = input.LA(5);

                        if ( (LA3_183=='m') ) {
                            int LA3_234 = input.LA(6);

                            if ( (LA3_234=='#'||LA3_234=='-'||(LA3_234>='/' && LA3_234<=':')||(LA3_234>='A' && LA3_234<='Z')||LA3_234=='_'||(LA3_234>='a' && LA3_234<='z')) ) {
                                alt3=72;
                            }
                            else {
                                alt3=40;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            default:
                alt3=72;}

            }
            break;
        case 'n':
            {
            switch ( input.LA(2) ) {
            case 'a':
                {
                int LA3_73 = input.LA(3);

                if ( (LA3_73=='m') ) {
                    int LA3_129 = input.LA(4);

                    if ( (LA3_129=='e') ) {
                        int LA3_184 = input.LA(5);

                        if ( (LA3_184=='#'||LA3_184=='-'||(LA3_184>='/' && LA3_184<=':')||(LA3_184>='A' && LA3_184<='Z')||LA3_184=='_'||(LA3_184>='a' && LA3_184<='z')) ) {
                            alt3=72;
                        }
                        else {
                            alt3=24;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'o':
                {
                int LA3_74 = input.LA(3);

                if ( (LA3_74=='t') ) {
                    int LA3_130 = input.LA(4);

                    if ( (LA3_130=='#'||LA3_130=='-'||(LA3_130>='/' && LA3_130<=':')||(LA3_130>='A' && LA3_130<='Z')||LA3_130=='_'||(LA3_130>='a' && LA3_130<='z')) ) {
                        alt3=72;
                    }
                    else {
                        alt3=65;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'u':
                {
                int LA3_75 = input.LA(3);

                if ( (LA3_75=='m') ) {
                    switch ( input.LA(4) ) {
                    case 'l':
                        {
                        int LA3_186 = input.LA(5);

                        if ( (LA3_186=='s') ) {
                            int LA3_236 = input.LA(6);

                            if ( (LA3_236=='s') ) {
                                int LA3_277 = input.LA(7);

                                if ( (LA3_277=='#'||LA3_277=='-'||(LA3_277>='/' && LA3_277<=':')||(LA3_277>='A' && LA3_277<='Z')||LA3_277=='_'||(LA3_277>='a' && LA3_277<='z')) ) {
                                    alt3=72;
                                }
                                else {
                                    alt3=30;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                        }
                        break;
                    case 'r':
                        {
                        int LA3_187 = input.LA(5);

                        if ( (LA3_187=='u') ) {
                            int LA3_237 = input.LA(6);

                            if ( (LA3_237=='n') ) {
                                int LA3_278 = input.LA(7);

                                if ( (LA3_278=='s') ) {
                                    int LA3_309 = input.LA(8);

                                    if ( (LA3_309=='(') ) {
                                        alt3=43;
                                    }
                                    else {
                                        alt3=72;}
                                }
                                else {
                                    alt3=72;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                        }
                        break;
                    case 'b':
                        {
                        int LA3_188 = input.LA(5);

                        if ( (LA3_188=='e') ) {
                            int LA3_238 = input.LA(6);

                            if ( (LA3_238=='r') ) {
                                int LA3_279 = input.LA(7);

                                if ( (LA3_279=='#'||LA3_279=='-'||(LA3_279>='/' && LA3_279<=':')||(LA3_279>='A' && LA3_279<='Z')||LA3_279=='_'||(LA3_279>='a' && LA3_279<='z')) ) {
                                    alt3=72;
                                }
                                else {
                                    alt3=26;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                        }
                        break;
                    case 'f':
                        {
                        int LA3_189 = input.LA(5);

                        if ( (LA3_189=='i') ) {
                            int LA3_239 = input.LA(6);

                            if ( (LA3_239=='l') ) {
                                int LA3_280 = input.LA(7);

                                if ( (LA3_280=='e') ) {
                                    int LA3_311 = input.LA(8);

                                    if ( (LA3_311=='s') ) {
                                        int LA3_330 = input.LA(9);

                                        if ( (LA3_330=='(') ) {
                                            alt3=44;
                                        }
                                        else {
                                            alt3=72;}
                                    }
                                    else {
                                        alt3=72;}
                                }
                                else {
                                    alt3=72;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                        }
                        break;
                    case 'e':
                        {
                        int LA3_190 = input.LA(5);

                        if ( (LA3_190=='v') ) {
                            int LA3_240 = input.LA(6);

                            if ( (LA3_240=='e') ) {
                                int LA3_281 = input.LA(7);

                                if ( (LA3_281=='n') ) {
                                    int LA3_312 = input.LA(8);

                                    if ( (LA3_312=='t') ) {
                                        int LA3_331 = input.LA(9);

                                        if ( (LA3_331=='s') ) {
                                            int LA3_344 = input.LA(10);

                                            if ( (LA3_344=='#'||LA3_344=='-'||(LA3_344>='/' && LA3_344<=':')||(LA3_344>='A' && LA3_344<='Z')||LA3_344=='_'||(LA3_344>='a' && LA3_344<='z')) ) {
                                                alt3=72;
                                            }
                                            else {
                                                alt3=29;}
                                        }
                                        else {
                                            alt3=72;}
                                    }
                                    else {
                                        alt3=72;}
                                }
                                else {
                                    alt3=72;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                        }
                        break;
                    default:
                        alt3=72;}

                }
                else {
                    alt3=72;}
                }
                break;
            default:
                alt3=72;}

            }
            break;
        case 'v':
            {
            int LA3_18 = input.LA(2);

            if ( (LA3_18=='e') ) {
                int LA3_76 = input.LA(3);

                if ( (LA3_76=='r') ) {
                    int LA3_132 = input.LA(4);

                    if ( (LA3_132=='s') ) {
                        int LA3_191 = input.LA(5);

                        if ( (LA3_191=='i') ) {
                            int LA3_241 = input.LA(6);

                            if ( (LA3_241=='o') ) {
                                int LA3_282 = input.LA(7);

                                if ( (LA3_282=='n') ) {
                                    int LA3_313 = input.LA(8);

                                    if ( (LA3_313=='#'||LA3_313=='-'||(LA3_313>='/' && LA3_313<=':')||(LA3_313>='A' && LA3_313<='Z')||LA3_313=='_'||(LA3_313>='a' && LA3_313<='z')) ) {
                                        alt3=72;
                                    }
                                    else {
                                        alt3=25;}
                                }
                                else {
                                    alt3=72;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
            }
            else {
                alt3=72;}
            }
            break;
        case 't':
            {
            switch ( input.LA(2) ) {
            case 'y':
                {
                int LA3_77 = input.LA(3);

                if ( (LA3_77=='p') ) {
                    int LA3_133 = input.LA(4);

                    if ( (LA3_133=='e') ) {
                        int LA3_192 = input.LA(5);

                        if ( (LA3_192=='#'||LA3_192=='-'||(LA3_192>='/' && LA3_192<=':')||(LA3_192>='A' && LA3_192<='Z')||LA3_192=='_'||(LA3_192>='a' && LA3_192<='z')) ) {
                            alt3=72;
                        }
                        else {
                            alt3=34;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'i':
                {
                int LA3_78 = input.LA(3);

                if ( (LA3_78=='e') ) {
                    int LA3_134 = input.LA(4);

                    if ( (LA3_134=='r') ) {
                        int LA3_193 = input.LA(5);

                        if ( (LA3_193=='#'||LA3_193=='-'||(LA3_193>='/' && LA3_193<=':')||(LA3_193>='A' && LA3_193<='Z')||LA3_193=='_'||(LA3_193>='a' && LA3_193<='z')) ) {
                            alt3=72;
                        }
                        else {
                            alt3=38;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'a':
                {
                int LA3_79 = input.LA(3);

                if ( (LA3_79=='g') ) {
                    int LA3_135 = input.LA(4);

                    if ( (LA3_135=='#'||LA3_135=='-'||(LA3_135>='/' && LA3_135<=':')||(LA3_135>='A' && LA3_135<='Z')||LA3_135=='_'||(LA3_135>='a' && LA3_135<='z')) ) {
                        alt3=72;
                    }
                    else {
                        alt3=42;}
                }
                else {
                    alt3=72;}
                }
                break;
            default:
                alt3=72;}

            }
            break;
        case 'S':
            {
            switch ( input.LA(2) ) {
            case 'U':
                {
                int LA3_80 = input.LA(3);

                if ( (LA3_80=='M') ) {
                    int LA3_136 = input.LA(4);

                    if ( (LA3_136=='#'||LA3_136=='-'||(LA3_136>='/' && LA3_136<=':')||(LA3_136>='A' && LA3_136<='Z')||LA3_136=='_'||(LA3_136>='a' && LA3_136<='z')) ) {
                        alt3=72;
                    }
                    else {
                        alt3=71;}
                }
                else {
                    alt3=72;}
                }
                break;
            case 'E':
                {
                int LA3_81 = input.LA(3);

                if ( (LA3_81=='L') ) {
                    int LA3_137 = input.LA(4);

                    if ( (LA3_137=='E') ) {
                        int LA3_196 = input.LA(5);

                        if ( (LA3_196=='C') ) {
                            int LA3_244 = input.LA(6);

                            if ( (LA3_244=='T') ) {
                                int LA3_283 = input.LA(7);

                                if ( (LA3_283=='#'||LA3_283=='-'||(LA3_283>='/' && LA3_283<=':')||(LA3_283>='A' && LA3_283<='Z')||LA3_283=='_'||(LA3_283>='a' && LA3_283<='z')) ) {
                                    alt3=72;
                                }
                                else {
                                    alt3=52;}
                            }
                            else {
                                alt3=72;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
                }
                break;
            default:
                alt3=72;}

            }
            break;
        case 'F':
            {
            int LA3_21 = input.LA(2);

            if ( (LA3_21=='I') ) {
                int LA3_82 = input.LA(3);

                if ( (LA3_82=='N') ) {
                    int LA3_138 = input.LA(4);

                    if ( (LA3_138=='D') ) {
                        int LA3_197 = input.LA(5);

                        if ( (LA3_197=='#'||LA3_197=='-'||(LA3_197>='/' && LA3_197<=':')||(LA3_197>='A' && LA3_197<='Z')||LA3_197=='_'||(LA3_197>='a' && LA3_197<='z')) ) {
                            alt3=72;
                        }
                        else {
                            alt3=54;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
            }
            else {
                alt3=72;}
            }
            break;
        case 'A':
            {
            int LA3_22 = input.LA(2);

            if ( (LA3_22=='N') ) {
                int LA3_83 = input.LA(3);

                if ( (LA3_83=='D') ) {
                    int LA3_139 = input.LA(4);

                    if ( (LA3_139=='#'||LA3_139=='-'||(LA3_139>='/' && LA3_139<=':')||(LA3_139>='A' && LA3_139<='Z')||LA3_139=='_'||(LA3_139>='a' && LA3_139<='z')) ) {
                        alt3=72;
                    }
                    else {
                        alt3=56;}
                }
                else {
                    alt3=72;}
            }
            else {
                alt3=72;}
            }
            break;
        case 'o':
            {
            int LA3_23 = input.LA(2);

            if ( (LA3_23=='r') ) {
                switch ( input.LA(3) ) {
                case 'd':
                    {
                    int LA3_140 = input.LA(4);

                    if ( (LA3_140=='e') ) {
                        int LA3_199 = input.LA(5);

                        if ( (LA3_199=='r') ) {
                            int LA3_246 = input.LA(6);

                            if ( (LA3_246=='#'||LA3_246=='-'||(LA3_246>='/' && LA3_246<=':')||(LA3_246>='A' && LA3_246<='Z')||LA3_246=='_'||(LA3_246>='a' && LA3_246<='z')) ) {
                                alt3=72;
                            }
                            else {
                                alt3=57;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                    }
                    break;
                case '#':
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
                    alt3=72;
                    }
                    break;
                default:
                    alt3=61;}

            }
            else {
                alt3=72;}
            }
            break;
        case 'O':
            {
            int LA3_24 = input.LA(2);

            if ( (LA3_24=='R') ) {
                switch ( input.LA(3) ) {
                case 'D':
                    {
                    int LA3_142 = input.LA(4);

                    if ( (LA3_142=='E') ) {
                        int LA3_200 = input.LA(5);

                        if ( (LA3_200=='R') ) {
                            int LA3_247 = input.LA(6);

                            if ( (LA3_247=='#'||LA3_247=='-'||(LA3_247>='/' && LA3_247<=':')||(LA3_247>='A' && LA3_247<='Z')||LA3_247=='_'||(LA3_247>='a' && LA3_247<='z')) ) {
                                alt3=72;
                            }
                            else {
                                alt3=58;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                    }
                    break;
                case '#':
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
                    alt3=72;
                    }
                    break;
                default:
                    alt3=62;}

            }
            else {
                alt3=72;}
            }
            break;
        case 'B':
            {
            int LA3_25 = input.LA(2);

            if ( (LA3_25=='Y') ) {
                int LA3_86 = input.LA(3);

                if ( (LA3_86=='#'||LA3_86=='-'||(LA3_86>='/' && LA3_86<=':')||(LA3_86>='A' && LA3_86<='Z')||LA3_86=='_'||(LA3_86>='a' && LA3_86<='z')) ) {
                    alt3=72;
                }
                else {
                    alt3=60;}
            }
            else {
                alt3=72;}
            }
            break;
        case 'I':
            {
            int LA3_26 = input.LA(2);

            if ( (LA3_26=='N') ) {
                int LA3_87 = input.LA(3);

                if ( (LA3_87=='#'||LA3_87=='-'||(LA3_87>='/' && LA3_87<=':')||(LA3_87>='A' && LA3_87<='Z')||LA3_87=='_'||(LA3_87>='a' && LA3_87<='z')) ) {
                    alt3=72;
                }
                else {
                    alt3=64;}
            }
            else {
                alt3=72;}
            }
            break;
        case 'N':
            {
            int LA3_27 = input.LA(2);

            if ( (LA3_27=='O') ) {
                int LA3_88 = input.LA(3);

                if ( (LA3_88=='T') ) {
                    int LA3_146 = input.LA(4);

                    if ( (LA3_146=='#'||LA3_146=='-'||(LA3_146>='/' && LA3_146<=':')||(LA3_146>='A' && LA3_146<='Z')||LA3_146=='_'||(LA3_146>='a' && LA3_146<='z')) ) {
                        alt3=72;
                    }
                    else {
                        alt3=66;}
                }
                else {
                    alt3=72;}
            }
            else {
                alt3=72;}
            }
            break;
        case 'L':
            {
            int LA3_28 = input.LA(2);

            if ( (LA3_28=='I') ) {
                int LA3_89 = input.LA(3);

                if ( (LA3_89=='K') ) {
                    int LA3_147 = input.LA(4);

                    if ( (LA3_147=='E') ) {
                        int LA3_202 = input.LA(5);

                        if ( (LA3_202=='#'||LA3_202=='-'||(LA3_202>='/' && LA3_202<=':')||(LA3_202>='A' && LA3_202<='Z')||LA3_202=='_'||(LA3_202>='a' && LA3_202<='z')) ) {
                            alt3=72;
                        }
                        else {
                            alt3=68;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
            }
            else {
                alt3=72;}
            }
            break;
        case 'C':
            {
            int LA3_29 = input.LA(2);

            if ( (LA3_29=='O') ) {
                int LA3_90 = input.LA(3);

                if ( (LA3_90=='U') ) {
                    int LA3_148 = input.LA(4);

                    if ( (LA3_148=='N') ) {
                        int LA3_203 = input.LA(5);

                        if ( (LA3_203=='T') ) {
                            int LA3_249 = input.LA(6);

                            if ( (LA3_249=='#'||LA3_249=='-'||(LA3_249>='/' && LA3_249<=':')||(LA3_249>='A' && LA3_249<='Z')||LA3_249=='_'||(LA3_249>='a' && LA3_249<='z')) ) {
                                alt3=72;
                            }
                            else {
                                alt3=69;}
                        }
                        else {
                            alt3=72;}
                    }
                    else {
                        alt3=72;}
                }
                else {
                    alt3=72;}
            }
            else {
                alt3=72;}
            }
            break;
        case '#':
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
        case 'D':
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
        case 'g':
        case 'h':
        case 'j':
        case 'k':
        case 'q':
        case 'u':
        case 'x':
        case 'y':
        case 'z':
            {
            alt3=72;
            }
            break;
        case ',':
            {
            alt3=73;
            }
            break;
        case ' ':
            {
            int LA3_32 = input.LA(2);

            if ( ((LA3_32>='\t' && LA3_32<='\n')||(LA3_32>='\f' && LA3_32<='\r')||LA3_32==' ') ) {
                alt3=82;
            }
            else {
                alt3=74;}
            }
            break;
        case '.':
            {
            alt3=75;
            }
            break;
        case '>':
            {
            alt3=76;
            }
            break;
        case '<':
            {
            alt3=77;
            }
            break;
        case '=':
            {
            alt3=78;
            }
            break;
        case '&':
            {
            alt3=79;
            }
            break;
        case '%':
        case '*':
            {
            alt3=80;
            }
            break;
        case '\n':
            {
            int LA3_39 = input.LA(2);

            if ( ((LA3_39>='\t' && LA3_39<='\n')||(LA3_39>='\f' && LA3_39<='\r')||LA3_39==' ') ) {
                alt3=82;
            }
            else {
                alt3=81;}
            }
            break;
        case '\t':
        case '\f':
        case '\r':
            {
            alt3=82;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | T63 | T64 | T65 | T66 | T67 | T68 | T69 | T70 | T71 | T72 | T73 | T74 | T75 | T76 | T77 | T78 | T79 | T80 | T81 | T82 | T83 | T84 | T85 | VALUE | COMMA | SPACE | DOT | GT | LT | EQ | AMP | STAR | NL | WS );", 3, 0, input);

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
                // Sql.g:1:294: VALUE
                {
                mVALUE(); 

                }
                break;
            case 73 :
                // Sql.g:1:300: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 74 :
                // Sql.g:1:306: SPACE
                {
                mSPACE(); 

                }
                break;
            case 75 :
                // Sql.g:1:312: DOT
                {
                mDOT(); 

                }
                break;
            case 76 :
                // Sql.g:1:316: GT
                {
                mGT(); 

                }
                break;
            case 77 :
                // Sql.g:1:319: LT
                {
                mLT(); 

                }
                break;
            case 78 :
                // Sql.g:1:322: EQ
                {
                mEQ(); 

                }
                break;
            case 79 :
                // Sql.g:1:325: AMP
                {
                mAMP(); 

                }
                break;
            case 80 :
                // Sql.g:1:329: STAR
                {
                mSTAR(); 

                }
                break;
            case 81 :
                // Sql.g:1:334: NL
                {
                mNL(); 

                }
                break;
            case 82 :
                // Sql.g:1:337: WS
                {
                mWS(); 

                }
                break;

        }

    }


 

}