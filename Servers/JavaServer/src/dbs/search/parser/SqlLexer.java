package dbs.search.parser;
// $ANTLR 3.0.1 Sql.g 2008-06-17 11:42:53

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
    public static final int Tokens=81;
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
    public static final int GT=9;
    public static final int T52=52;
    public static final int T15=15;
    public static final int T80=80;
    public static final int T51=51;
    public static final int T16=16;
    public static final int T54=54;
    public static final int T17=17;
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
            // Sql.g:19:5: ( 'createdate' )
            // Sql.g:19:7: 'createdate'
            {
            match("createdate"); 


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
            // Sql.g:20:5: ( 'moddate' )
            // Sql.g:20:7: 'moddate'
            {
            match("moddate"); 


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
            // Sql.g:21:5: ( 'starttime' )
            // Sql.g:21:7: 'starttime'
            {
            match("starttime"); 


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
            // Sql.g:22:5: ( 'endtime' )
            // Sql.g:22:7: 'endtime'
            {
            match("endtime"); 


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
            // Sql.g:23:5: ( 'createby' )
            // Sql.g:23:7: 'createby'
            {
            match("createby"); 


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
            // Sql.g:24:5: ( 'modby' )
            // Sql.g:24:7: 'modby'
            {
            match("modby"); 


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
            // Sql.g:25:5: ( 'name' )
            // Sql.g:25:7: 'name'
            {
            match("name"); 


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
            // Sql.g:26:5: ( 'version' )
            // Sql.g:26:7: 'version'
            {
            match("version"); 


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
            // Sql.g:27:5: ( 'number' )
            // Sql.g:27:7: 'number'
            {
            match("number"); 


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
            // Sql.g:28:5: ( 'startevnum' )
            // Sql.g:28:7: 'startevnum'
            {
            match("startevnum"); 


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
            // Sql.g:29:5: ( 'endevnum' )
            // Sql.g:29:7: 'endevnum'
            {
            match("endevnum"); 


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
            // Sql.g:30:5: ( 'numevents' )
            // Sql.g:30:7: 'numevents'
            {
            match("numevents"); 


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
            // Sql.g:31:5: ( 'numlss' )
            // Sql.g:31:7: 'numlss'
            {
            match("numlss"); 


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
            // Sql.g:32:5: ( 'size' )
            // Sql.g:32:7: 'size'
            {
            match("size"); 


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
            // Sql.g:33:5: ( 'count' )
            // Sql.g:33:7: 'count'
            {
            match("count"); 


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
            // Sql.g:34:5: ( 'status' )
            // Sql.g:34:7: 'status'
            {
            match("status"); 


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
            // Sql.g:35:5: ( 'type' )
            // Sql.g:35:7: 'type'
            {
            match("type"); 


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
            // Sql.g:36:5: ( 'id' )
            // Sql.g:36:7: 'id'
            {
            match("id"); 


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
            // Sql.g:37:5: ( 'parent' )
            // Sql.g:37:7: 'parent'
            {
            match("parent"); 


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
            // Sql.g:38:5: ( 'tier' )
            // Sql.g:38:7: 'tier'
            {
            match("tier"); 


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
            // Sql.g:39:5: ( 'def' )
            // Sql.g:39:7: 'def'
            {
            match("def"); 


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
            // Sql.g:40:5: ( 'evnum' )
            // Sql.g:40:7: 'evnum'
            {
            match("evnum"); 


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
            // Sql.g:41:5: ( 'era' )
            // Sql.g:41:7: 'era'
            {
            match("era"); 


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
            // Sql.g:42:5: ( 'tag' )
            // Sql.g:42:7: 'tag'
            {
            match("tag"); 


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
            // Sql.g:43:5: ( 'numruns()' )
            // Sql.g:43:7: 'numruns()'
            {
            match("numruns()"); 


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
            // Sql.g:44:5: ( 'numfiles()' )
            // Sql.g:44:7: 'numfiles()'
            {
            match("numfiles()"); 


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
            // Sql.g:45:5: ( 'dataquality()' )
            // Sql.g:45:7: 'dataquality()'
            {
            match("dataquality()"); 


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
            // Sql.g:46:5: ( 'latest()' )
            // Sql.g:46:7: 'latest()'
            {
            match("latest()"); 


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
            // Sql.g:47:5: ( 'parentrelease()' )
            // Sql.g:47:7: 'parentrelease()'
            {
            match("parentrelease()"); 


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
            // Sql.g:48:5: ( 'childrelease()' )
            // Sql.g:48:7: 'childrelease()'
            {
            match("childrelease()"); 


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
            // Sql.g:49:5: ( 'intluminosity()' )
            // Sql.g:49:7: 'intluminosity()'
            {
            match("intluminosity()"); 


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
            // Sql.g:50:5: ( 'findevents()' )
            // Sql.g:50:7: 'findevents()'
            {
            match("findevents()"); 


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
            // Sql.g:51:5: ( 'select' )
            // Sql.g:51:7: 'select'
            {
            match("select"); 


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
            // Sql.g:52:5: ( 'SELECT' )
            // Sql.g:52:7: 'SELECT'
            {
            match("SELECT"); 


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
            // Sql.g:53:5: ( 'find' )
            // Sql.g:53:7: 'find'
            {
            match("find"); 


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
            // Sql.g:54:5: ( 'FIND' )
            // Sql.g:54:7: 'FIND'
            {
            match("FIND"); 


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
            // Sql.g:55:5: ( 'and' )
            // Sql.g:55:7: 'and'
            {
            match("and"); 


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
            // Sql.g:56:5: ( 'AND' )
            // Sql.g:56:7: 'AND'
            {
            match("AND"); 


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
            // Sql.g:57:5: ( 'order' )
            // Sql.g:57:7: 'order'
            {
            match("order"); 


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
            // Sql.g:58:5: ( 'ORDER' )
            // Sql.g:58:7: 'ORDER'
            {
            match("ORDER"); 


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
            // Sql.g:59:5: ( 'by' )
            // Sql.g:59:7: 'by'
            {
            match("by"); 


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
            // Sql.g:60:5: ( 'BY' )
            // Sql.g:60:7: 'BY'
            {
            match("BY"); 


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
            // Sql.g:61:5: ( 'or' )
            // Sql.g:61:7: 'or'
            {
            match("or"); 


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
            // Sql.g:62:5: ( 'OR' )
            // Sql.g:62:7: 'OR'
            {
            match("OR"); 


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
            // Sql.g:63:5: ( 'in' )
            // Sql.g:63:7: 'in'
            {
            match("in"); 


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
            // Sql.g:64:5: ( 'IN' )
            // Sql.g:64:7: 'IN'
            {
            match("IN"); 


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
            // Sql.g:65:5: ( 'not' )
            // Sql.g:65:7: 'not'
            {
            match("not"); 


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
            // Sql.g:66:5: ( 'NOT' )
            // Sql.g:66:7: 'NOT'
            {
            match("NOT"); 


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
            // Sql.g:67:5: ( 'like' )
            // Sql.g:67:7: 'like'
            {
            match("like"); 


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
            // Sql.g:68:5: ( 'LIKE' )
            // Sql.g:68:7: 'LIKE'
            {
            match("LIKE"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T80

    // $ANTLR start VALUE
    public final void mVALUE() throws RecognitionException {
        try {
            int _type = VALUE;
            // Sql.g:99:8: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' )+ )
            // Sql.g:99:9: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' )+
            {
            // Sql.g:99:9: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' | ':' | '#' )+
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
            // Sql.g:104:8: ( ( ',' ) )
            // Sql.g:104:9: ( ',' )
            {
            // Sql.g:104:9: ( ',' )
            // Sql.g:104:10: ','
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
            // Sql.g:105:8: ( ( ' ' ) )
            // Sql.g:105:9: ( ' ' )
            {
            // Sql.g:105:9: ( ' ' )
            // Sql.g:105:10: ' '
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
            // Sql.g:106:6: ( ( '.' ) )
            // Sql.g:106:7: ( '.' )
            {
            // Sql.g:106:7: ( '.' )
            // Sql.g:106:8: '.'
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
            // Sql.g:108:5: ( ( '>' ) )
            // Sql.g:108:6: ( '>' )
            {
            // Sql.g:108:6: ( '>' )
            // Sql.g:108:7: '>'
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
            // Sql.g:109:5: ( ( '<' ) )
            // Sql.g:109:6: ( '<' )
            {
            // Sql.g:109:6: ( '<' )
            // Sql.g:109:7: '<'
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
            // Sql.g:110:5: ( ( '=' ) )
            // Sql.g:110:6: ( '=' )
            {
            // Sql.g:110:6: ( '=' )
            // Sql.g:110:7: '='
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
            // Sql.g:111:6: ( ( '&' ) )
            // Sql.g:111:7: ( '&' )
            {
            // Sql.g:111:7: ( '&' )
            // Sql.g:111:8: '&'
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
            // Sql.g:112:7: ( ( '*' | '%' ) )
            // Sql.g:112:8: ( '*' | '%' )
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
            // Sql.g:113:5: ( ( '\\n' ) )
            // Sql.g:113:6: ( '\\n' )
            {
            // Sql.g:113:6: ( '\\n' )
            // Sql.g:113:7: '\\n'
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
            // Sql.g:114:6: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // Sql.g:114:8: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // Sql.g:114:8: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
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
        // Sql.g:1:8: ( T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | T63 | T64 | T65 | T66 | T67 | T68 | T69 | T70 | T71 | T72 | T73 | T74 | T75 | T76 | T77 | T78 | T79 | T80 | VALUE | COMMA | SPACE | DOT | GT | LT | EQ | AMP | STAR | NL | WS )
        int alt3=77;
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
                int LA3_40 = input.LA(3);

                if ( (LA3_40=='E') ) {
                    int LA3_88 = input.LA(4);

                    if ( (LA3_88=='R') ) {
                        int LA3_140 = input.LA(5);

                        if ( (LA3_140=='E') ) {
                            int LA3_191 = input.LA(6);

                            if ( (LA3_191=='#'||LA3_191=='-'||(LA3_191>='/' && LA3_191<=':')||(LA3_191>='A' && LA3_191<='Z')||LA3_191=='_'||(LA3_191>='a' && LA3_191<='z')) ) {
                                alt3=67;
                            }
                            else {
                                alt3=3;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
            }
            else {
                alt3=67;}
            }
            break;
        case 'w':
            {
            int LA3_4 = input.LA(2);

            if ( (LA3_4=='h') ) {
                int LA3_41 = input.LA(3);

                if ( (LA3_41=='e') ) {
                    int LA3_89 = input.LA(4);

                    if ( (LA3_89=='r') ) {
                        int LA3_141 = input.LA(5);

                        if ( (LA3_141=='e') ) {
                            int LA3_192 = input.LA(6);

                            if ( (LA3_192=='#'||LA3_192=='-'||(LA3_192>='/' && LA3_192<=':')||(LA3_192>='A' && LA3_192<='Z')||LA3_192=='_'||(LA3_192>='a' && LA3_192<='z')) ) {
                                alt3=67;
                            }
                            else {
                                alt3=4;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
            }
            else {
                alt3=67;}
            }
            break;
        case 'a':
            {
            switch ( input.LA(2) ) {
            case 'd':
                {
                int LA3_42 = input.LA(3);

                if ( (LA3_42=='s') ) {
                    int LA3_90 = input.LA(4);

                    if ( (LA3_90=='#'||LA3_90=='-'||(LA3_90>='/' && LA3_90<=':')||(LA3_90>='A' && LA3_90<='Z')||LA3_90=='_'||(LA3_90>='a' && LA3_90<='z')) ) {
                        alt3=67;
                    }
                    else {
                        alt3=5;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'n':
                {
                int LA3_43 = input.LA(3);

                if ( (LA3_43=='d') ) {
                    int LA3_91 = input.LA(4);

                    if ( (LA3_91=='#'||LA3_91=='-'||(LA3_91>='/' && LA3_91<=':')||(LA3_91>='A' && LA3_91<='Z')||LA3_91=='_'||(LA3_91>='a' && LA3_91<='z')) ) {
                        alt3=67;
                    }
                    else {
                        alt3=53;}
                }
                else {
                    alt3=67;}
                }
                break;
            default:
                alt3=67;}

            }
            break;
        case 'd':
            {
            switch ( input.LA(2) ) {
            case 'e':
                {
                int LA3_44 = input.LA(3);

                if ( (LA3_44=='f') ) {
                    int LA3_92 = input.LA(4);

                    if ( (LA3_92=='#'||LA3_92=='-'||(LA3_92>='/' && LA3_92<=':')||(LA3_92>='A' && LA3_92<='Z')||LA3_92=='_'||(LA3_92>='a' && LA3_92<='z')) ) {
                        alt3=67;
                    }
                    else {
                        alt3=37;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'a':
                {
                int LA3_45 = input.LA(3);

                if ( (LA3_45=='t') ) {
                    int LA3_93 = input.LA(4);

                    if ( (LA3_93=='a') ) {
                        switch ( input.LA(5) ) {
                        case 's':
                            {
                            int LA3_193 = input.LA(6);

                            if ( (LA3_193=='e') ) {
                                int LA3_237 = input.LA(7);

                                if ( (LA3_237=='t') ) {
                                    int LA3_269 = input.LA(8);

                                    if ( (LA3_269=='#'||LA3_269=='-'||(LA3_269>='/' && LA3_269<=':')||(LA3_269>='A' && LA3_269<='Z')||LA3_269=='_'||(LA3_269>='a' && LA3_269<='z')) ) {
                                        alt3=67;
                                    }
                                    else {
                                        alt3=6;}
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
                            }
                            break;
                        case 'q':
                            {
                            int LA3_194 = input.LA(6);

                            if ( (LA3_194=='u') ) {
                                int LA3_238 = input.LA(7);

                                if ( (LA3_238=='a') ) {
                                    int LA3_270 = input.LA(8);

                                    if ( (LA3_270=='l') ) {
                                        int LA3_297 = input.LA(9);

                                        if ( (LA3_297=='i') ) {
                                            int LA3_314 = input.LA(10);

                                            if ( (LA3_314=='t') ) {
                                                int LA3_326 = input.LA(11);

                                                if ( (LA3_326=='y') ) {
                                                    int LA3_335 = input.LA(12);

                                                    if ( (LA3_335=='(') ) {
                                                        alt3=43;
                                                    }
                                                    else {
                                                        alt3=67;}
                                                }
                                                else {
                                                    alt3=67;}
                                            }
                                            else {
                                                alt3=67;}
                                        }
                                        else {
                                            alt3=67;}
                                    }
                                    else {
                                        alt3=67;}
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
                            }
                            break;
                        default:
                            alt3=67;}

                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'q':
                {
                int LA3_46 = input.LA(3);

                if ( (LA3_46=='#'||LA3_46=='-'||(LA3_46>='/' && LA3_46<=':')||(LA3_46>='A' && LA3_46<='Z')||LA3_46=='_'||(LA3_46>='a' && LA3_46<='z')) ) {
                    alt3=67;
                }
                else {
                    alt3=15;}
                }
                break;
            default:
                alt3=67;}

            }
            break;
        case 'r':
            {
            switch ( input.LA(2) ) {
            case 'e':
                {
                int LA3_47 = input.LA(3);

                if ( (LA3_47=='l') ) {
                    int LA3_95 = input.LA(4);

                    if ( (LA3_95=='e') ) {
                        int LA3_146 = input.LA(5);

                        if ( (LA3_146=='a') ) {
                            int LA3_195 = input.LA(6);

                            if ( (LA3_195=='s') ) {
                                int LA3_239 = input.LA(7);

                                if ( (LA3_239=='e') ) {
                                    int LA3_271 = input.LA(8);

                                    if ( (LA3_271=='#'||LA3_271=='-'||(LA3_271>='/' && LA3_271<=':')||(LA3_271>='A' && LA3_271<='Z')||LA3_271=='_'||(LA3_271>='a' && LA3_271<='z')) ) {
                                        alt3=67;
                                    }
                                    else {
                                        alt3=7;}
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'u':
                {
                int LA3_48 = input.LA(3);

                if ( (LA3_48=='n') ) {
                    int LA3_96 = input.LA(4);

                    if ( (LA3_96=='#'||LA3_96=='-'||(LA3_96>='/' && LA3_96<=':')||(LA3_96>='A' && LA3_96<='Z')||LA3_96=='_'||(LA3_96>='a' && LA3_96<='z')) ) {
                        alt3=67;
                    }
                    else {
                        alt3=13;}
                }
                else {
                    alt3=67;}
                }
                break;
            default:
                alt3=67;}

            }
            break;
        case 's':
            {
            switch ( input.LA(2) ) {
            case 't':
                {
                int LA3_49 = input.LA(3);

                if ( (LA3_49=='a') ) {
                    switch ( input.LA(4) ) {
                    case 'r':
                        {
                        int LA3_148 = input.LA(5);

                        if ( (LA3_148=='t') ) {
                            switch ( input.LA(6) ) {
                            case 'e':
                                {
                                int LA3_240 = input.LA(7);

                                if ( (LA3_240=='v') ) {
                                    int LA3_272 = input.LA(8);

                                    if ( (LA3_272=='n') ) {
                                        int LA3_299 = input.LA(9);

                                        if ( (LA3_299=='u') ) {
                                            int LA3_315 = input.LA(10);

                                            if ( (LA3_315=='m') ) {
                                                int LA3_327 = input.LA(11);

                                                if ( (LA3_327=='#'||LA3_327=='-'||(LA3_327>='/' && LA3_327<=':')||(LA3_327>='A' && LA3_327<='Z')||LA3_327=='_'||(LA3_327>='a' && LA3_327<='z')) ) {
                                                    alt3=67;
                                                }
                                                else {
                                                    alt3=26;}
                                            }
                                            else {
                                                alt3=67;}
                                        }
                                        else {
                                            alt3=67;}
                                    }
                                    else {
                                        alt3=67;}
                                }
                                else {
                                    alt3=67;}
                                }
                                break;
                            case 't':
                                {
                                int LA3_241 = input.LA(7);

                                if ( (LA3_241=='i') ) {
                                    int LA3_273 = input.LA(8);

                                    if ( (LA3_273=='m') ) {
                                        int LA3_300 = input.LA(9);

                                        if ( (LA3_300=='e') ) {
                                            int LA3_316 = input.LA(10);

                                            if ( (LA3_316=='#'||LA3_316=='-'||(LA3_316>='/' && LA3_316<=':')||(LA3_316>='A' && LA3_316<='Z')||LA3_316=='_'||(LA3_316>='a' && LA3_316<='z')) ) {
                                                alt3=67;
                                            }
                                            else {
                                                alt3=19;}
                                        }
                                        else {
                                            alt3=67;}
                                    }
                                    else {
                                        alt3=67;}
                                }
                                else {
                                    alt3=67;}
                                }
                                break;
                            default:
                                alt3=67;}

                        }
                        else {
                            alt3=67;}
                        }
                        break;
                    case 't':
                        {
                        int LA3_149 = input.LA(5);

                        if ( (LA3_149=='u') ) {
                            int LA3_197 = input.LA(6);

                            if ( (LA3_197=='s') ) {
                                int LA3_242 = input.LA(7);

                                if ( (LA3_242=='#'||LA3_242=='-'||(LA3_242>='/' && LA3_242<=':')||(LA3_242>='A' && LA3_242<='Z')||LA3_242=='_'||(LA3_242>='a' && LA3_242<='z')) ) {
                                    alt3=67;
                                }
                                else {
                                    alt3=32;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                        }
                        break;
                    default:
                        alt3=67;}

                }
                else {
                    alt3=67;}
                }
                break;
            case 'i':
                {
                switch ( input.LA(3) ) {
                case 't':
                    {
                    int LA3_98 = input.LA(4);

                    if ( (LA3_98=='e') ) {
                        int LA3_150 = input.LA(5);

                        if ( (LA3_150=='#'||LA3_150=='-'||(LA3_150>='/' && LA3_150<=':')||(LA3_150>='A' && LA3_150<='Z')||LA3_150=='_'||(LA3_150>='a' && LA3_150<='z')) ) {
                            alt3=67;
                        }
                        else {
                            alt3=8;}
                    }
                    else {
                        alt3=67;}
                    }
                    break;
                case 'z':
                    {
                    int LA3_99 = input.LA(4);

                    if ( (LA3_99=='e') ) {
                        int LA3_151 = input.LA(5);

                        if ( (LA3_151=='#'||LA3_151=='-'||(LA3_151>='/' && LA3_151<=':')||(LA3_151>='A' && LA3_151<='Z')||LA3_151=='_'||(LA3_151>='a' && LA3_151<='z')) ) {
                            alt3=67;
                        }
                        else {
                            alt3=30;}
                    }
                    else {
                        alt3=67;}
                    }
                    break;
                default:
                    alt3=67;}

                }
                break;
            case 'e':
                {
                int LA3_51 = input.LA(3);

                if ( (LA3_51=='l') ) {
                    int LA3_100 = input.LA(4);

                    if ( (LA3_100=='e') ) {
                        int LA3_152 = input.LA(5);

                        if ( (LA3_152=='c') ) {
                            int LA3_200 = input.LA(6);

                            if ( (LA3_200=='t') ) {
                                int LA3_243 = input.LA(7);

                                if ( (LA3_243=='#'||LA3_243=='-'||(LA3_243>='/' && LA3_243<=':')||(LA3_243>='A' && LA3_243<='Z')||LA3_243=='_'||(LA3_243>='a' && LA3_243<='z')) ) {
                                    alt3=67;
                                }
                                else {
                                    alt3=49;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            default:
                alt3=67;}

            }
            break;
        case 'b':
            {
            switch ( input.LA(2) ) {
            case 'l':
                {
                int LA3_52 = input.LA(3);

                if ( (LA3_52=='o') ) {
                    int LA3_101 = input.LA(4);

                    if ( (LA3_101=='c') ) {
                        int LA3_153 = input.LA(5);

                        if ( (LA3_153=='k') ) {
                            int LA3_201 = input.LA(6);

                            if ( (LA3_201=='#'||LA3_201=='-'||(LA3_201>='/' && LA3_201<=':')||(LA3_201>='A' && LA3_201<='Z')||LA3_201=='_'||(LA3_201>='a' && LA3_201<='z')) ) {
                                alt3=67;
                            }
                            else {
                                alt3=9;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'y':
                {
                int LA3_53 = input.LA(3);

                if ( (LA3_53=='#'||LA3_53=='-'||(LA3_53>='/' && LA3_53<=':')||(LA3_53>='A' && LA3_53<='Z')||LA3_53=='_'||(LA3_53>='a' && LA3_53<='z')) ) {
                    alt3=67;
                }
                else {
                    alt3=57;}
                }
                break;
            default:
                alt3=67;}

            }
            break;
        case 'f':
            {
            int LA3_10 = input.LA(2);

            if ( (LA3_10=='i') ) {
                switch ( input.LA(3) ) {
                case 'n':
                    {
                    int LA3_103 = input.LA(4);

                    if ( (LA3_103=='d') ) {
                        switch ( input.LA(5) ) {
                        case 'e':
                            {
                            int LA3_202 = input.LA(6);

                            if ( (LA3_202=='v') ) {
                                int LA3_245 = input.LA(7);

                                if ( (LA3_245=='e') ) {
                                    int LA3_276 = input.LA(8);

                                    if ( (LA3_276=='n') ) {
                                        int LA3_301 = input.LA(9);

                                        if ( (LA3_301=='t') ) {
                                            int LA3_317 = input.LA(10);

                                            if ( (LA3_317=='s') ) {
                                                int LA3_329 = input.LA(11);

                                                if ( (LA3_329=='(') ) {
                                                    alt3=48;
                                                }
                                                else {
                                                    alt3=67;}
                                            }
                                            else {
                                                alt3=67;}
                                        }
                                        else {
                                            alt3=67;}
                                    }
                                    else {
                                        alt3=67;}
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
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
                            alt3=67;
                            }
                            break;
                        default:
                            alt3=51;}

                    }
                    else {
                        alt3=67;}
                    }
                    break;
                case 'l':
                    {
                    int LA3_104 = input.LA(4);

                    if ( (LA3_104=='e') ) {
                        int LA3_155 = input.LA(5);

                        if ( (LA3_155=='#'||LA3_155=='-'||(LA3_155>='/' && LA3_155<=':')||(LA3_155>='A' && LA3_155<='Z')||LA3_155=='_'||(LA3_155>='a' && LA3_155<='z')) ) {
                            alt3=67;
                        }
                        else {
                            alt3=10;}
                    }
                    else {
                        alt3=67;}
                    }
                    break;
                default:
                    alt3=67;}

            }
            else {
                alt3=67;}
            }
            break;
        case 'p':
            {
            switch ( input.LA(2) ) {
            case 'a':
                {
                int LA3_55 = input.LA(3);

                if ( (LA3_55=='r') ) {
                    int LA3_105 = input.LA(4);

                    if ( (LA3_105=='e') ) {
                        int LA3_156 = input.LA(5);

                        if ( (LA3_156=='n') ) {
                            int LA3_205 = input.LA(6);

                            if ( (LA3_205=='t') ) {
                                switch ( input.LA(7) ) {
                                case 'r':
                                    {
                                    int LA3_277 = input.LA(8);

                                    if ( (LA3_277=='e') ) {
                                        int LA3_302 = input.LA(9);

                                        if ( (LA3_302=='l') ) {
                                            int LA3_318 = input.LA(10);

                                            if ( (LA3_318=='e') ) {
                                                int LA3_330 = input.LA(11);

                                                if ( (LA3_330=='a') ) {
                                                    int LA3_338 = input.LA(12);

                                                    if ( (LA3_338=='s') ) {
                                                        int LA3_343 = input.LA(13);

                                                        if ( (LA3_343=='e') ) {
                                                            int LA3_346 = input.LA(14);

                                                            if ( (LA3_346=='(') ) {
                                                                alt3=45;
                                                            }
                                                            else {
                                                                alt3=67;}
                                                        }
                                                        else {
                                                            alt3=67;}
                                                    }
                                                    else {
                                                        alt3=67;}
                                                }
                                                else {
                                                    alt3=67;}
                                            }
                                            else {
                                                alt3=67;}
                                        }
                                        else {
                                            alt3=67;}
                                    }
                                    else {
                                        alt3=67;}
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
                                    alt3=67;
                                    }
                                    break;
                                default:
                                    alt3=35;}

                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'r':
                {
                switch ( input.LA(3) ) {
                case 'i':
                    {
                    int LA3_106 = input.LA(4);

                    if ( (LA3_106=='m') ) {
                        int LA3_157 = input.LA(5);

                        if ( (LA3_157=='d') ) {
                            int LA3_206 = input.LA(6);

                            if ( (LA3_206=='s') ) {
                                int LA3_247 = input.LA(7);

                                if ( (LA3_247=='#'||LA3_247=='-'||(LA3_247>='/' && LA3_247<=':')||(LA3_247>='A' && LA3_247<='Z')||LA3_247=='_'||(LA3_247>='a' && LA3_247<='z')) ) {
                                    alt3=67;
                                }
                                else {
                                    alt3=11;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                    }
                    break;
                case 'o':
                    {
                    int LA3_107 = input.LA(4);

                    if ( (LA3_107=='c') ) {
                        int LA3_158 = input.LA(5);

                        if ( (LA3_158=='d') ) {
                            int LA3_207 = input.LA(6);

                            if ( (LA3_207=='s') ) {
                                int LA3_248 = input.LA(7);

                                if ( (LA3_248=='#'||LA3_248=='-'||(LA3_248>='/' && LA3_248<=':')||(LA3_248>='A' && LA3_248<='Z')||LA3_248=='_'||(LA3_248>='a' && LA3_248<='z')) ) {
                                    alt3=67;
                                }
                                else {
                                    alt3=12;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                    }
                    break;
                default:
                    alt3=67;}

                }
                break;
            default:
                alt3=67;}

            }
            break;
        case 'l':
            {
            switch ( input.LA(2) ) {
            case 'i':
                {
                int LA3_57 = input.LA(3);

                if ( (LA3_57=='k') ) {
                    int LA3_108 = input.LA(4);

                    if ( (LA3_108=='e') ) {
                        int LA3_159 = input.LA(5);

                        if ( (LA3_159=='#'||LA3_159=='-'||(LA3_159>='/' && LA3_159<=':')||(LA3_159>='A' && LA3_159<='Z')||LA3_159=='_'||(LA3_159>='a' && LA3_159<='z')) ) {
                            alt3=67;
                        }
                        else {
                            alt3=65;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'a':
                {
                int LA3_58 = input.LA(3);

                if ( (LA3_58=='t') ) {
                    int LA3_109 = input.LA(4);

                    if ( (LA3_109=='e') ) {
                        int LA3_160 = input.LA(5);

                        if ( (LA3_160=='s') ) {
                            int LA3_209 = input.LA(6);

                            if ( (LA3_209=='t') ) {
                                int LA3_249 = input.LA(7);

                                if ( (LA3_249=='(') ) {
                                    alt3=44;
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'u':
                {
                int LA3_59 = input.LA(3);

                if ( (LA3_59=='m') ) {
                    int LA3_110 = input.LA(4);

                    if ( (LA3_110=='i') ) {
                        int LA3_161 = input.LA(5);

                        if ( (LA3_161=='#'||LA3_161=='-'||(LA3_161>='/' && LA3_161<=':')||(LA3_161>='A' && LA3_161<='Z')||LA3_161=='_'||(LA3_161>='a' && LA3_161<='z')) ) {
                            alt3=67;
                        }
                        else {
                            alt3=14;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            default:
                alt3=67;}

            }
            break;
        case 'i':
            {
            switch ( input.LA(2) ) {
            case 'd':
                {
                int LA3_60 = input.LA(3);

                if ( (LA3_60=='#'||LA3_60=='-'||(LA3_60>='/' && LA3_60<=':')||(LA3_60>='A' && LA3_60<='Z')||LA3_60=='_'||(LA3_60>='a' && LA3_60<='z')) ) {
                    alt3=67;
                }
                else {
                    alt3=34;}
                }
                break;
            case 'n':
                {
                switch ( input.LA(3) ) {
                case 't':
                    {
                    int LA3_112 = input.LA(4);

                    if ( (LA3_112=='l') ) {
                        int LA3_162 = input.LA(5);

                        if ( (LA3_162=='u') ) {
                            int LA3_211 = input.LA(6);

                            if ( (LA3_211=='m') ) {
                                int LA3_250 = input.LA(7);

                                if ( (LA3_250=='i') ) {
                                    int LA3_282 = input.LA(8);

                                    if ( (LA3_282=='n') ) {
                                        int LA3_303 = input.LA(9);

                                        if ( (LA3_303=='o') ) {
                                            int LA3_319 = input.LA(10);

                                            if ( (LA3_319=='s') ) {
                                                int LA3_331 = input.LA(11);

                                                if ( (LA3_331=='i') ) {
                                                    int LA3_339 = input.LA(12);

                                                    if ( (LA3_339=='t') ) {
                                                        int LA3_344 = input.LA(13);

                                                        if ( (LA3_344=='y') ) {
                                                            int LA3_347 = input.LA(14);

                                                            if ( (LA3_347=='(') ) {
                                                                alt3=47;
                                                            }
                                                            else {
                                                                alt3=67;}
                                                        }
                                                        else {
                                                            alt3=67;}
                                                    }
                                                    else {
                                                        alt3=67;}
                                                }
                                                else {
                                                    alt3=67;}
                                            }
                                            else {
                                                alt3=67;}
                                        }
                                        else {
                                            alt3=67;}
                                    }
                                    else {
                                        alt3=67;}
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
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
                    alt3=67;
                    }
                    break;
                default:
                    alt3=61;}

                }
                break;
            case 'l':
                {
                int LA3_62 = input.LA(3);

                if ( (LA3_62=='u') ) {
                    int LA3_114 = input.LA(4);

                    if ( (LA3_114=='m') ) {
                        int LA3_163 = input.LA(5);

                        if ( (LA3_163=='i') ) {
                            int LA3_212 = input.LA(6);

                            if ( (LA3_212=='#'||LA3_212=='-'||(LA3_212>='/' && LA3_212<=':')||(LA3_212>='A' && LA3_212<='Z')||LA3_212=='_'||(LA3_212>='a' && LA3_212<='z')) ) {
                                alt3=67;
                            }
                            else {
                                alt3=16;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            default:
                alt3=67;}

            }
            break;
        case 'c':
            {
            switch ( input.LA(2) ) {
            case 'r':
                {
                int LA3_63 = input.LA(3);

                if ( (LA3_63=='e') ) {
                    int LA3_115 = input.LA(4);

                    if ( (LA3_115=='a') ) {
                        int LA3_164 = input.LA(5);

                        if ( (LA3_164=='t') ) {
                            int LA3_213 = input.LA(6);

                            if ( (LA3_213=='e') ) {
                                switch ( input.LA(7) ) {
                                case 'b':
                                    {
                                    int LA3_283 = input.LA(8);

                                    if ( (LA3_283=='y') ) {
                                        int LA3_304 = input.LA(9);

                                        if ( (LA3_304=='#'||LA3_304=='-'||(LA3_304>='/' && LA3_304<=':')||(LA3_304>='A' && LA3_304<='Z')||LA3_304=='_'||(LA3_304>='a' && LA3_304<='z')) ) {
                                            alt3=67;
                                        }
                                        else {
                                            alt3=21;}
                                    }
                                    else {
                                        alt3=67;}
                                    }
                                    break;
                                case 'd':
                                    {
                                    int LA3_284 = input.LA(8);

                                    if ( (LA3_284=='a') ) {
                                        int LA3_305 = input.LA(9);

                                        if ( (LA3_305=='t') ) {
                                            int LA3_321 = input.LA(10);

                                            if ( (LA3_321=='e') ) {
                                                int LA3_332 = input.LA(11);

                                                if ( (LA3_332=='#'||LA3_332=='-'||(LA3_332>='/' && LA3_332<=':')||(LA3_332>='A' && LA3_332<='Z')||LA3_332=='_'||(LA3_332>='a' && LA3_332<='z')) ) {
                                                    alt3=67;
                                                }
                                                else {
                                                    alt3=17;}
                                            }
                                            else {
                                                alt3=67;}
                                        }
                                        else {
                                            alt3=67;}
                                    }
                                    else {
                                        alt3=67;}
                                    }
                                    break;
                                default:
                                    alt3=67;}

                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'o':
                {
                int LA3_64 = input.LA(3);

                if ( (LA3_64=='u') ) {
                    int LA3_116 = input.LA(4);

                    if ( (LA3_116=='n') ) {
                        int LA3_165 = input.LA(5);

                        if ( (LA3_165=='t') ) {
                            int LA3_214 = input.LA(6);

                            if ( (LA3_214=='#'||LA3_214=='-'||(LA3_214>='/' && LA3_214<=':')||(LA3_214>='A' && LA3_214<='Z')||LA3_214=='_'||(LA3_214>='a' && LA3_214<='z')) ) {
                                alt3=67;
                            }
                            else {
                                alt3=31;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'h':
                {
                int LA3_65 = input.LA(3);

                if ( (LA3_65=='i') ) {
                    int LA3_117 = input.LA(4);

                    if ( (LA3_117=='l') ) {
                        int LA3_166 = input.LA(5);

                        if ( (LA3_166=='d') ) {
                            int LA3_215 = input.LA(6);

                            if ( (LA3_215=='r') ) {
                                int LA3_254 = input.LA(7);

                                if ( (LA3_254=='e') ) {
                                    int LA3_285 = input.LA(8);

                                    if ( (LA3_285=='l') ) {
                                        int LA3_306 = input.LA(9);

                                        if ( (LA3_306=='e') ) {
                                            int LA3_322 = input.LA(10);

                                            if ( (LA3_322=='a') ) {
                                                int LA3_333 = input.LA(11);

                                                if ( (LA3_333=='s') ) {
                                                    int LA3_341 = input.LA(12);

                                                    if ( (LA3_341=='e') ) {
                                                        int LA3_345 = input.LA(13);

                                                        if ( (LA3_345=='(') ) {
                                                            alt3=46;
                                                        }
                                                        else {
                                                            alt3=67;}
                                                    }
                                                    else {
                                                        alt3=67;}
                                                }
                                                else {
                                                    alt3=67;}
                                            }
                                            else {
                                                alt3=67;}
                                        }
                                        else {
                                            alt3=67;}
                                    }
                                    else {
                                        alt3=67;}
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            default:
                alt3=67;}

            }
            break;
        case 'm':
            {
            int LA3_15 = input.LA(2);

            if ( (LA3_15=='o') ) {
                int LA3_66 = input.LA(3);

                if ( (LA3_66=='d') ) {
                    switch ( input.LA(4) ) {
                    case 'b':
                        {
                        int LA3_167 = input.LA(5);

                        if ( (LA3_167=='y') ) {
                            int LA3_216 = input.LA(6);

                            if ( (LA3_216=='#'||LA3_216=='-'||(LA3_216>='/' && LA3_216<=':')||(LA3_216>='A' && LA3_216<='Z')||LA3_216=='_'||(LA3_216>='a' && LA3_216<='z')) ) {
                                alt3=67;
                            }
                            else {
                                alt3=22;}
                        }
                        else {
                            alt3=67;}
                        }
                        break;
                    case 'd':
                        {
                        int LA3_168 = input.LA(5);

                        if ( (LA3_168=='a') ) {
                            int LA3_217 = input.LA(6);

                            if ( (LA3_217=='t') ) {
                                int LA3_256 = input.LA(7);

                                if ( (LA3_256=='e') ) {
                                    int LA3_286 = input.LA(8);

                                    if ( (LA3_286=='#'||LA3_286=='-'||(LA3_286>='/' && LA3_286<=':')||(LA3_286>='A' && LA3_286<='Z')||LA3_286=='_'||(LA3_286>='a' && LA3_286<='z')) ) {
                                        alt3=67;
                                    }
                                    else {
                                        alt3=18;}
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                        }
                        break;
                    default:
                        alt3=67;}

                }
                else {
                    alt3=67;}
            }
            else {
                alt3=67;}
            }
            break;
        case 'e':
            {
            switch ( input.LA(2) ) {
            case 'n':
                {
                int LA3_67 = input.LA(3);

                if ( (LA3_67=='d') ) {
                    switch ( input.LA(4) ) {
                    case 'e':
                        {
                        int LA3_169 = input.LA(5);

                        if ( (LA3_169=='v') ) {
                            int LA3_218 = input.LA(6);

                            if ( (LA3_218=='n') ) {
                                int LA3_257 = input.LA(7);

                                if ( (LA3_257=='u') ) {
                                    int LA3_287 = input.LA(8);

                                    if ( (LA3_287=='m') ) {
                                        int LA3_308 = input.LA(9);

                                        if ( (LA3_308=='#'||LA3_308=='-'||(LA3_308>='/' && LA3_308<=':')||(LA3_308>='A' && LA3_308<='Z')||LA3_308=='_'||(LA3_308>='a' && LA3_308<='z')) ) {
                                            alt3=67;
                                        }
                                        else {
                                            alt3=27;}
                                    }
                                    else {
                                        alt3=67;}
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                        }
                        break;
                    case 't':
                        {
                        int LA3_170 = input.LA(5);

                        if ( (LA3_170=='i') ) {
                            int LA3_219 = input.LA(6);

                            if ( (LA3_219=='m') ) {
                                int LA3_258 = input.LA(7);

                                if ( (LA3_258=='e') ) {
                                    int LA3_288 = input.LA(8);

                                    if ( (LA3_288=='#'||LA3_288=='-'||(LA3_288>='/' && LA3_288<=':')||(LA3_288>='A' && LA3_288<='Z')||LA3_288=='_'||(LA3_288>='a' && LA3_288<='z')) ) {
                                        alt3=67;
                                    }
                                    else {
                                        alt3=20;}
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                        }
                        break;
                    default:
                        alt3=67;}

                }
                else {
                    alt3=67;}
                }
                break;
            case 'v':
                {
                int LA3_68 = input.LA(3);

                if ( (LA3_68=='n') ) {
                    int LA3_120 = input.LA(4);

                    if ( (LA3_120=='u') ) {
                        int LA3_171 = input.LA(5);

                        if ( (LA3_171=='m') ) {
                            int LA3_220 = input.LA(6);

                            if ( (LA3_220=='#'||LA3_220=='-'||(LA3_220>='/' && LA3_220<=':')||(LA3_220>='A' && LA3_220<='Z')||LA3_220=='_'||(LA3_220>='a' && LA3_220<='z')) ) {
                                alt3=67;
                            }
                            else {
                                alt3=38;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'r':
                {
                int LA3_69 = input.LA(3);

                if ( (LA3_69=='a') ) {
                    int LA3_121 = input.LA(4);

                    if ( (LA3_121=='#'||LA3_121=='-'||(LA3_121>='/' && LA3_121<=':')||(LA3_121>='A' && LA3_121<='Z')||LA3_121=='_'||(LA3_121>='a' && LA3_121<='z')) ) {
                        alt3=67;
                    }
                    else {
                        alt3=39;}
                }
                else {
                    alt3=67;}
                }
                break;
            default:
                alt3=67;}

            }
            break;
        case 'n':
            {
            switch ( input.LA(2) ) {
            case 'u':
                {
                int LA3_70 = input.LA(3);

                if ( (LA3_70=='m') ) {
                    switch ( input.LA(4) ) {
                    case 'l':
                        {
                        int LA3_173 = input.LA(5);

                        if ( (LA3_173=='s') ) {
                            int LA3_221 = input.LA(6);

                            if ( (LA3_221=='s') ) {
                                int LA3_260 = input.LA(7);

                                if ( (LA3_260=='#'||LA3_260=='-'||(LA3_260>='/' && LA3_260<=':')||(LA3_260>='A' && LA3_260<='Z')||LA3_260=='_'||(LA3_260>='a' && LA3_260<='z')) ) {
                                    alt3=67;
                                }
                                else {
                                    alt3=29;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                        }
                        break;
                    case 'r':
                        {
                        int LA3_174 = input.LA(5);

                        if ( (LA3_174=='u') ) {
                            int LA3_222 = input.LA(6);

                            if ( (LA3_222=='n') ) {
                                int LA3_261 = input.LA(7);

                                if ( (LA3_261=='s') ) {
                                    int LA3_290 = input.LA(8);

                                    if ( (LA3_290=='(') ) {
                                        alt3=41;
                                    }
                                    else {
                                        alt3=67;}
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                        }
                        break;
                    case 'e':
                        {
                        int LA3_175 = input.LA(5);

                        if ( (LA3_175=='v') ) {
                            int LA3_223 = input.LA(6);

                            if ( (LA3_223=='e') ) {
                                int LA3_262 = input.LA(7);

                                if ( (LA3_262=='n') ) {
                                    int LA3_291 = input.LA(8);

                                    if ( (LA3_291=='t') ) {
                                        int LA3_311 = input.LA(9);

                                        if ( (LA3_311=='s') ) {
                                            int LA3_324 = input.LA(10);

                                            if ( (LA3_324=='#'||LA3_324=='-'||(LA3_324>='/' && LA3_324<=':')||(LA3_324>='A' && LA3_324<='Z')||LA3_324=='_'||(LA3_324>='a' && LA3_324<='z')) ) {
                                                alt3=67;
                                            }
                                            else {
                                                alt3=28;}
                                        }
                                        else {
                                            alt3=67;}
                                    }
                                    else {
                                        alt3=67;}
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                        }
                        break;
                    case 'f':
                        {
                        int LA3_176 = input.LA(5);

                        if ( (LA3_176=='i') ) {
                            int LA3_224 = input.LA(6);

                            if ( (LA3_224=='l') ) {
                                int LA3_263 = input.LA(7);

                                if ( (LA3_263=='e') ) {
                                    int LA3_292 = input.LA(8);

                                    if ( (LA3_292=='s') ) {
                                        int LA3_312 = input.LA(9);

                                        if ( (LA3_312=='(') ) {
                                            alt3=42;
                                        }
                                        else {
                                            alt3=67;}
                                    }
                                    else {
                                        alt3=67;}
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                        }
                        break;
                    case 'b':
                        {
                        int LA3_177 = input.LA(5);

                        if ( (LA3_177=='e') ) {
                            int LA3_225 = input.LA(6);

                            if ( (LA3_225=='r') ) {
                                int LA3_264 = input.LA(7);

                                if ( (LA3_264=='#'||LA3_264=='-'||(LA3_264>='/' && LA3_264<=':')||(LA3_264>='A' && LA3_264<='Z')||LA3_264=='_'||(LA3_264>='a' && LA3_264<='z')) ) {
                                    alt3=67;
                                }
                                else {
                                    alt3=25;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                        }
                        break;
                    default:
                        alt3=67;}

                }
                else {
                    alt3=67;}
                }
                break;
            case 'a':
                {
                int LA3_71 = input.LA(3);

                if ( (LA3_71=='m') ) {
                    int LA3_123 = input.LA(4);

                    if ( (LA3_123=='e') ) {
                        int LA3_178 = input.LA(5);

                        if ( (LA3_178=='#'||LA3_178=='-'||(LA3_178>='/' && LA3_178<=':')||(LA3_178>='A' && LA3_178<='Z')||LA3_178=='_'||(LA3_178>='a' && LA3_178<='z')) ) {
                            alt3=67;
                        }
                        else {
                            alt3=23;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'o':
                {
                int LA3_72 = input.LA(3);

                if ( (LA3_72=='t') ) {
                    int LA3_124 = input.LA(4);

                    if ( (LA3_124=='#'||LA3_124=='-'||(LA3_124>='/' && LA3_124<=':')||(LA3_124>='A' && LA3_124<='Z')||LA3_124=='_'||(LA3_124>='a' && LA3_124<='z')) ) {
                        alt3=67;
                    }
                    else {
                        alt3=63;}
                }
                else {
                    alt3=67;}
                }
                break;
            default:
                alt3=67;}

            }
            break;
        case 'v':
            {
            int LA3_18 = input.LA(2);

            if ( (LA3_18=='e') ) {
                int LA3_73 = input.LA(3);

                if ( (LA3_73=='r') ) {
                    int LA3_125 = input.LA(4);

                    if ( (LA3_125=='s') ) {
                        int LA3_180 = input.LA(5);

                        if ( (LA3_180=='i') ) {
                            int LA3_227 = input.LA(6);

                            if ( (LA3_227=='o') ) {
                                int LA3_265 = input.LA(7);

                                if ( (LA3_265=='n') ) {
                                    int LA3_294 = input.LA(8);

                                    if ( (LA3_294=='#'||LA3_294=='-'||(LA3_294>='/' && LA3_294<=':')||(LA3_294>='A' && LA3_294<='Z')||LA3_294=='_'||(LA3_294>='a' && LA3_294<='z')) ) {
                                        alt3=67;
                                    }
                                    else {
                                        alt3=24;}
                                }
                                else {
                                    alt3=67;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
            }
            else {
                alt3=67;}
            }
            break;
        case 't':
            {
            switch ( input.LA(2) ) {
            case 'i':
                {
                int LA3_74 = input.LA(3);

                if ( (LA3_74=='e') ) {
                    int LA3_126 = input.LA(4);

                    if ( (LA3_126=='r') ) {
                        int LA3_181 = input.LA(5);

                        if ( (LA3_181=='#'||LA3_181=='-'||(LA3_181>='/' && LA3_181<=':')||(LA3_181>='A' && LA3_181<='Z')||LA3_181=='_'||(LA3_181>='a' && LA3_181<='z')) ) {
                            alt3=67;
                        }
                        else {
                            alt3=36;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'a':
                {
                int LA3_75 = input.LA(3);

                if ( (LA3_75=='g') ) {
                    int LA3_127 = input.LA(4);

                    if ( (LA3_127=='#'||LA3_127=='-'||(LA3_127>='/' && LA3_127<=':')||(LA3_127>='A' && LA3_127<='Z')||LA3_127=='_'||(LA3_127>='a' && LA3_127<='z')) ) {
                        alt3=67;
                    }
                    else {
                        alt3=40;}
                }
                else {
                    alt3=67;}
                }
                break;
            case 'y':
                {
                int LA3_76 = input.LA(3);

                if ( (LA3_76=='p') ) {
                    int LA3_128 = input.LA(4);

                    if ( (LA3_128=='e') ) {
                        int LA3_183 = input.LA(5);

                        if ( (LA3_183=='#'||LA3_183=='-'||(LA3_183>='/' && LA3_183<=':')||(LA3_183>='A' && LA3_183<='Z')||LA3_183=='_'||(LA3_183>='a' && LA3_183<='z')) ) {
                            alt3=67;
                        }
                        else {
                            alt3=33;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
                }
                break;
            default:
                alt3=67;}

            }
            break;
        case 'S':
            {
            int LA3_20 = input.LA(2);

            if ( (LA3_20=='E') ) {
                int LA3_77 = input.LA(3);

                if ( (LA3_77=='L') ) {
                    int LA3_129 = input.LA(4);

                    if ( (LA3_129=='E') ) {
                        int LA3_184 = input.LA(5);

                        if ( (LA3_184=='C') ) {
                            int LA3_230 = input.LA(6);

                            if ( (LA3_230=='T') ) {
                                int LA3_266 = input.LA(7);

                                if ( (LA3_266=='#'||LA3_266=='-'||(LA3_266>='/' && LA3_266<=':')||(LA3_266>='A' && LA3_266<='Z')||LA3_266=='_'||(LA3_266>='a' && LA3_266<='z')) ) {
                                    alt3=67;
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=67;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
            }
            else {
                alt3=67;}
            }
            break;
        case 'F':
            {
            int LA3_21 = input.LA(2);

            if ( (LA3_21=='I') ) {
                int LA3_78 = input.LA(3);

                if ( (LA3_78=='N') ) {
                    int LA3_130 = input.LA(4);

                    if ( (LA3_130=='D') ) {
                        int LA3_185 = input.LA(5);

                        if ( (LA3_185=='#'||LA3_185=='-'||(LA3_185>='/' && LA3_185<=':')||(LA3_185>='A' && LA3_185<='Z')||LA3_185=='_'||(LA3_185>='a' && LA3_185<='z')) ) {
                            alt3=67;
                        }
                        else {
                            alt3=52;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
            }
            else {
                alt3=67;}
            }
            break;
        case 'A':
            {
            int LA3_22 = input.LA(2);

            if ( (LA3_22=='N') ) {
                int LA3_79 = input.LA(3);

                if ( (LA3_79=='D') ) {
                    int LA3_131 = input.LA(4);

                    if ( (LA3_131=='#'||LA3_131=='-'||(LA3_131>='/' && LA3_131<=':')||(LA3_131>='A' && LA3_131<='Z')||LA3_131=='_'||(LA3_131>='a' && LA3_131<='z')) ) {
                        alt3=67;
                    }
                    else {
                        alt3=54;}
                }
                else {
                    alt3=67;}
            }
            else {
                alt3=67;}
            }
            break;
        case 'o':
            {
            int LA3_23 = input.LA(2);

            if ( (LA3_23=='r') ) {
                switch ( input.LA(3) ) {
                case 'd':
                    {
                    int LA3_132 = input.LA(4);

                    if ( (LA3_132=='e') ) {
                        int LA3_187 = input.LA(5);

                        if ( (LA3_187=='r') ) {
                            int LA3_232 = input.LA(6);

                            if ( (LA3_232=='#'||LA3_232=='-'||(LA3_232>='/' && LA3_232<=':')||(LA3_232>='A' && LA3_232<='Z')||LA3_232=='_'||(LA3_232>='a' && LA3_232<='z')) ) {
                                alt3=67;
                            }
                            else {
                                alt3=55;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
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
                    alt3=67;
                    }
                    break;
                default:
                    alt3=59;}

            }
            else {
                alt3=67;}
            }
            break;
        case 'O':
            {
            int LA3_24 = input.LA(2);

            if ( (LA3_24=='R') ) {
                switch ( input.LA(3) ) {
                case 'D':
                    {
                    int LA3_134 = input.LA(4);

                    if ( (LA3_134=='E') ) {
                        int LA3_188 = input.LA(5);

                        if ( (LA3_188=='R') ) {
                            int LA3_233 = input.LA(6);

                            if ( (LA3_233=='#'||LA3_233=='-'||(LA3_233>='/' && LA3_233<=':')||(LA3_233>='A' && LA3_233<='Z')||LA3_233=='_'||(LA3_233>='a' && LA3_233<='z')) ) {
                                alt3=67;
                            }
                            else {
                                alt3=56;}
                        }
                        else {
                            alt3=67;}
                    }
                    else {
                        alt3=67;}
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
                    alt3=67;
                    }
                    break;
                default:
                    alt3=60;}

            }
            else {
                alt3=67;}
            }
            break;
        case 'B':
            {
            int LA3_25 = input.LA(2);

            if ( (LA3_25=='Y') ) {
                int LA3_82 = input.LA(3);

                if ( (LA3_82=='#'||LA3_82=='-'||(LA3_82>='/' && LA3_82<=':')||(LA3_82>='A' && LA3_82<='Z')||LA3_82=='_'||(LA3_82>='a' && LA3_82<='z')) ) {
                    alt3=67;
                }
                else {
                    alt3=58;}
            }
            else {
                alt3=67;}
            }
            break;
        case 'I':
            {
            int LA3_26 = input.LA(2);

            if ( (LA3_26=='N') ) {
                int LA3_83 = input.LA(3);

                if ( (LA3_83=='#'||LA3_83=='-'||(LA3_83>='/' && LA3_83<=':')||(LA3_83>='A' && LA3_83<='Z')||LA3_83=='_'||(LA3_83>='a' && LA3_83<='z')) ) {
                    alt3=67;
                }
                else {
                    alt3=62;}
            }
            else {
                alt3=67;}
            }
            break;
        case 'N':
            {
            int LA3_27 = input.LA(2);

            if ( (LA3_27=='O') ) {
                int LA3_84 = input.LA(3);

                if ( (LA3_84=='T') ) {
                    int LA3_138 = input.LA(4);

                    if ( (LA3_138=='#'||LA3_138=='-'||(LA3_138>='/' && LA3_138<=':')||(LA3_138>='A' && LA3_138<='Z')||LA3_138=='_'||(LA3_138>='a' && LA3_138<='z')) ) {
                        alt3=67;
                    }
                    else {
                        alt3=64;}
                }
                else {
                    alt3=67;}
            }
            else {
                alt3=67;}
            }
            break;
        case 'L':
            {
            int LA3_28 = input.LA(2);

            if ( (LA3_28=='I') ) {
                int LA3_85 = input.LA(3);

                if ( (LA3_85=='K') ) {
                    int LA3_139 = input.LA(4);

                    if ( (LA3_139=='E') ) {
                        int LA3_190 = input.LA(5);

                        if ( (LA3_190=='#'||LA3_190=='-'||(LA3_190>='/' && LA3_190<=':')||(LA3_190>='A' && LA3_190<='Z')||LA3_190=='_'||(LA3_190>='a' && LA3_190<='z')) ) {
                            alt3=67;
                        }
                        else {
                            alt3=66;}
                    }
                    else {
                        alt3=67;}
                }
                else {
                    alt3=67;}
            }
            else {
                alt3=67;}
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
        case 'C':
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
            alt3=67;
            }
            break;
        case ',':
            {
            alt3=68;
            }
            break;
        case ' ':
            {
            int LA3_31 = input.LA(2);

            if ( ((LA3_31>='\t' && LA3_31<='\n')||(LA3_31>='\f' && LA3_31<='\r')||LA3_31==' ') ) {
                alt3=77;
            }
            else {
                alt3=69;}
            }
            break;
        case '.':
            {
            alt3=70;
            }
            break;
        case '>':
            {
            alt3=71;
            }
            break;
        case '<':
            {
            alt3=72;
            }
            break;
        case '=':
            {
            alt3=73;
            }
            break;
        case '&':
            {
            alt3=74;
            }
            break;
        case '%':
        case '*':
            {
            alt3=75;
            }
            break;
        case '\n':
            {
            int LA3_38 = input.LA(2);

            if ( ((LA3_38>='\t' && LA3_38<='\n')||(LA3_38>='\f' && LA3_38<='\r')||LA3_38==' ') ) {
                alt3=77;
            }
            else {
                alt3=76;}
            }
            break;
        case '\t':
        case '\f':
        case '\r':
            {
            alt3=77;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | T63 | T64 | T65 | T66 | T67 | T68 | T69 | T70 | T71 | T72 | T73 | T74 | T75 | T76 | T77 | T78 | T79 | T80 | VALUE | COMMA | SPACE | DOT | GT | LT | EQ | AMP | STAR | NL | WS );", 3, 0, input);

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
                // Sql.g:1:274: VALUE
                {
                mVALUE(); 

                }
                break;
            case 68 :
                // Sql.g:1:280: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 69 :
                // Sql.g:1:286: SPACE
                {
                mSPACE(); 

                }
                break;
            case 70 :
                // Sql.g:1:292: DOT
                {
                mDOT(); 

                }
                break;
            case 71 :
                // Sql.g:1:296: GT
                {
                mGT(); 

                }
                break;
            case 72 :
                // Sql.g:1:299: LT
                {
                mLT(); 

                }
                break;
            case 73 :
                // Sql.g:1:302: EQ
                {
                mEQ(); 

                }
                break;
            case 74 :
                // Sql.g:1:305: AMP
                {
                mAMP(); 

                }
                break;
            case 75 :
                // Sql.g:1:309: STAR
                {
                mSTAR(); 

                }
                break;
            case 76 :
                // Sql.g:1:314: NL
                {
                mNL(); 

                }
                break;
            case 77 :
                // Sql.g:1:317: WS
                {
                mWS(); 

                }
                break;

        }

    }


 

}