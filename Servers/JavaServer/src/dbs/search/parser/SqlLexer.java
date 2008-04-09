// $ANTLR 3.0.1 Sql.g 2008-04-09 15:38:09

package dbs.search.parser;
import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SqlLexer extends Lexer {
    public static final int LT=9;
    public static final int STAR=12;
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
    public static final int T21=21;
    public static final int T20=20;
    public static final int T62=62;
    public static final int COMMA=6;
    public static final int T38=38;
    public static final int T37=37;
    public static final int NL=5;
    public static final int EQ=8;
    public static final int T39=39;
    public static final int DOT=7;
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
    public static final int VALUE=11;
    public static final int T43=43;
    public static final int Tokens=63;
    public static final int T42=42;
    public static final int T41=41;
    public static final int T40=40;
    public static final int T47=47;
    public static final int T46=46;
    public static final int T45=45;
    public static final int T44=44;
    public static final int WS=13;
    public static final int T50=50;
    public static final int T59=59;
    public static final int GT=10;
    public static final int T14=14;
    public static final int T52=52;
    public static final int T15=15;
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

    // $ANTLR start T14
    public final void mT14() throws RecognitionException {
        try {
            int _type = T14;
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
    // $ANTLR end T14

    // $ANTLR start T15
    public final void mT15() throws RecognitionException {
        try {
            int _type = T15;
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
    // $ANTLR end T15

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
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
    // $ANTLR end T16

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
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
    // $ANTLR end T17

    // $ANTLR start T18
    public final void mT18() throws RecognitionException {
        try {
            int _type = T18;
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
    // $ANTLR end T18

    // $ANTLR start T19
    public final void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // Sql.g:8:5: ( 'path' )
            // Sql.g:8:7: 'path'
            {
            match("path"); 


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
    // $ANTLR end T20

    // $ANTLR start T21
    public final void mT21() throws RecognitionException {
        try {
            int _type = T21;
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
    // $ANTLR end T21

    // $ANTLR start T22
    public final void mT22() throws RecognitionException {
        try {
            int _type = T22;
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
    // $ANTLR end T22

    // $ANTLR start T23
    public final void mT23() throws RecognitionException {
        try {
            int _type = T23;
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
    // $ANTLR end T23

    // $ANTLR start T24
    public final void mT24() throws RecognitionException {
        try {
            int _type = T24;
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
    // $ANTLR end T24

    // $ANTLR start T25
    public final void mT25() throws RecognitionException {
        try {
            int _type = T25;
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
    // $ANTLR end T25

    // $ANTLR start T26
    public final void mT26() throws RecognitionException {
        try {
            int _type = T26;
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
    // $ANTLR end T26

    // $ANTLR start T27
    public final void mT27() throws RecognitionException {
        try {
            int _type = T27;
            // Sql.g:16:5: ( 'ls' )
            // Sql.g:16:7: 'ls'
            {
            match("ls"); 


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
            // Sql.g:17:5: ( 'createdate' )
            // Sql.g:17:7: 'createdate'
            {
            match("createdate"); 


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
            // Sql.g:18:5: ( 'moddate' )
            // Sql.g:18:7: 'moddate'
            {
            match("moddate"); 


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
            // Sql.g:19:5: ( 'starttime' )
            // Sql.g:19:7: 'starttime'
            {
            match("starttime"); 


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
            // Sql.g:20:5: ( 'endtime' )
            // Sql.g:20:7: 'endtime'
            {
            match("endtime"); 


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
            // Sql.g:21:5: ( 'createby' )
            // Sql.g:21:7: 'createby'
            {
            match("createby"); 


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
            // Sql.g:22:5: ( 'modby' )
            // Sql.g:22:7: 'modby'
            {
            match("modby"); 


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
            // Sql.g:23:5: ( 'name' )
            // Sql.g:23:7: 'name'
            {
            match("name"); 


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
            // Sql.g:24:5: ( 'version' )
            // Sql.g:24:7: 'version'
            {
            match("version"); 


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
            // Sql.g:25:5: ( 'number' )
            // Sql.g:25:7: 'number'
            {
            match("number"); 


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
            // Sql.g:26:5: ( 'startevnum' )
            // Sql.g:26:7: 'startevnum'
            {
            match("startevnum"); 


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
            // Sql.g:27:5: ( 'endevnum' )
            // Sql.g:27:7: 'endevnum'
            {
            match("endevnum"); 


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
            // Sql.g:28:5: ( 'numevents' )
            // Sql.g:28:7: 'numevents'
            {
            match("numevents"); 


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
            // Sql.g:29:5: ( 'numlss' )
            // Sql.g:29:7: 'numlss'
            {
            match("numlss"); 


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
            // Sql.g:30:5: ( 'size' )
            // Sql.g:30:7: 'size'
            {
            match("size"); 


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
            // Sql.g:31:5: ( 'count' )
            // Sql.g:31:7: 'count'
            {
            match("count"); 


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
            // Sql.g:32:5: ( 'numruns()' )
            // Sql.g:32:7: 'numruns()'
            {
            match("numruns()"); 


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
            // Sql.g:33:5: ( 'numfiles()' )
            // Sql.g:33:7: 'numfiles()'
            {
            match("numfiles()"); 


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
            // Sql.g:34:5: ( 'dataquality()' )
            // Sql.g:34:7: 'dataquality()'
            {
            match("dataquality()"); 


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
            // Sql.g:35:5: ( 'latest()' )
            // Sql.g:35:7: 'latest()'
            {
            match("latest()"); 


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
            // Sql.g:36:5: ( 'parentrelease()' )
            // Sql.g:36:7: 'parentrelease()'
            {
            match("parentrelease()"); 


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
            // Sql.g:37:5: ( 'childrelease()' )
            // Sql.g:37:7: 'childrelease()'
            {
            match("childrelease()"); 


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
            // Sql.g:38:5: ( 'intluminosity()' )
            // Sql.g:38:7: 'intluminosity()'
            {
            match("intluminosity()"); 


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
            // Sql.g:39:5: ( 'findevents()' )
            // Sql.g:39:7: 'findevents()'
            {
            match("findevents()"); 


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
            // Sql.g:40:5: ( 'select' )
            // Sql.g:40:7: 'select'
            {
            match("select"); 


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
            // Sql.g:41:5: ( 'SELECT' )
            // Sql.g:41:7: 'SELECT'
            {
            match("SELECT"); 


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
            // Sql.g:42:5: ( 'and' )
            // Sql.g:42:7: 'and'
            {
            match("and"); 


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
            // Sql.g:43:5: ( 'AND' )
            // Sql.g:43:7: 'AND'
            {
            match("AND"); 


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
            // Sql.g:44:5: ( 'or' )
            // Sql.g:44:7: 'or'
            {
            match("or"); 


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
            // Sql.g:45:5: ( 'OR' )
            // Sql.g:45:7: 'OR'
            {
            match("OR"); 


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
            // Sql.g:46:5: ( 'in' )
            // Sql.g:46:7: 'in'
            {
            match("in"); 


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
            // Sql.g:47:5: ( 'IN' )
            // Sql.g:47:7: 'IN'
            {
            match("IN"); 


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
            // Sql.g:48:5: ( 'not' )
            // Sql.g:48:7: 'not'
            {
            match("not"); 


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
            // Sql.g:49:5: ( 'NOT' )
            // Sql.g:49:7: 'NOT'
            {
            match("NOT"); 


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
            // Sql.g:50:5: ( 'like' )
            // Sql.g:50:7: 'like'
            {
            match("like"); 


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
            // Sql.g:51:5: ( 'LIKE' )
            // Sql.g:51:7: 'LIKE'
            {
            match("LIKE"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T62

    // $ANTLR start VALUE
    public final void mVALUE() throws RecognitionException {
        try {
            int _type = VALUE;
            // Sql.g:59:7: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' )+ )
            // Sql.g:59:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' )+
            {
            // Sql.g:59:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='-'||(LA1_0>='/' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Sql.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1)>='/' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
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
            // Sql.g:60:7: ( ( ',' ) )
            // Sql.g:60:8: ( ',' )
            {
            // Sql.g:60:8: ( ',' )
            // Sql.g:60:9: ','
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
            // Sql.g:61:7: ( ( ' ' ) )
            // Sql.g:61:8: ( ' ' )
            {
            // Sql.g:61:8: ( ' ' )
            // Sql.g:61:9: ' '
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
            // Sql.g:62:5: ( ( '.' ) )
            // Sql.g:62:6: ( '.' )
            {
            // Sql.g:62:6: ( '.' )
            // Sql.g:62:7: '.'
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
            // Sql.g:63:4: ( ( '>' ) )
            // Sql.g:63:5: ( '>' )
            {
            // Sql.g:63:5: ( '>' )
            // Sql.g:63:6: '>'
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
            // Sql.g:64:4: ( ( '<' ) )
            // Sql.g:64:5: ( '<' )
            {
            // Sql.g:64:5: ( '<' )
            // Sql.g:64:6: '<'
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
            // Sql.g:65:4: ( ( '=' ) )
            // Sql.g:65:5: ( '=' )
            {
            // Sql.g:65:5: ( '=' )
            // Sql.g:65:6: '='
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

    // $ANTLR start STAR
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            // Sql.g:66:6: ( ( '*' | '%' ) )
            // Sql.g:66:7: ( '*' | '%' )
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
            // Sql.g:67:4: ( ( '\\n' ) )
            // Sql.g:67:5: ( '\\n' )
            {
            // Sql.g:67:5: ( '\\n' )
            // Sql.g:67:6: '\\n'
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
            // Sql.g:68:5: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // Sql.g:68:7: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // Sql.g:68:7: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
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
        // Sql.g:1:8: ( T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | VALUE | COMMA | SPACE | DOT | GT | LT | EQ | STAR | NL | WS )
        int alt3=59;
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
                int LA3_36 = input.LA(3);

                if ( (LA3_36=='E') ) {
                    int LA3_72 = input.LA(4);

                    if ( (LA3_72=='R') ) {
                        int LA3_111 = input.LA(5);

                        if ( (LA3_111=='E') ) {
                            int LA3_151 = input.LA(6);

                            if ( (LA3_151=='-'||(LA3_151>='/' && LA3_151<='9')||(LA3_151>='A' && LA3_151<='Z')||LA3_151=='_'||(LA3_151>='a' && LA3_151<='z')) ) {
                                alt3=50;
                            }
                            else {
                                alt3=3;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
            }
            else {
                alt3=50;}
            }
            break;
        case 'w':
            {
            int LA3_4 = input.LA(2);

            if ( (LA3_4=='h') ) {
                int LA3_37 = input.LA(3);

                if ( (LA3_37=='e') ) {
                    int LA3_73 = input.LA(4);

                    if ( (LA3_73=='r') ) {
                        int LA3_112 = input.LA(5);

                        if ( (LA3_112=='e') ) {
                            int LA3_152 = input.LA(6);

                            if ( (LA3_152=='-'||(LA3_152>='/' && LA3_152<='9')||(LA3_152>='A' && LA3_152<='Z')||LA3_152=='_'||(LA3_152>='a' && LA3_152<='z')) ) {
                                alt3=50;
                            }
                            else {
                                alt3=4;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
            }
            else {
                alt3=50;}
            }
            break;
        case 'a':
            {
            switch ( input.LA(2) ) {
            case 'n':
                {
                int LA3_38 = input.LA(3);

                if ( (LA3_38=='d') ) {
                    int LA3_74 = input.LA(4);

                    if ( (LA3_74=='-'||(LA3_74>='/' && LA3_74<='9')||(LA3_74>='A' && LA3_74<='Z')||LA3_74=='_'||(LA3_74>='a' && LA3_74<='z')) ) {
                        alt3=50;
                    }
                    else {
                        alt3=40;}
                }
                else {
                    alt3=50;}
                }
                break;
            case 'd':
                {
                int LA3_39 = input.LA(3);

                if ( (LA3_39=='s') ) {
                    int LA3_75 = input.LA(4);

                    if ( (LA3_75=='-'||(LA3_75>='/' && LA3_75<='9')||(LA3_75>='A' && LA3_75<='Z')||LA3_75=='_'||(LA3_75>='a' && LA3_75<='z')) ) {
                        alt3=50;
                    }
                    else {
                        alt3=5;}
                }
                else {
                    alt3=50;}
                }
                break;
            default:
                alt3=50;}

            }
            break;
        case 'p':
            {
            switch ( input.LA(2) ) {
            case 'a':
                {
                switch ( input.LA(3) ) {
                case 't':
                    {
                    int LA3_76 = input.LA(4);

                    if ( (LA3_76=='h') ) {
                        int LA3_115 = input.LA(5);

                        if ( (LA3_115=='-'||(LA3_115>='/' && LA3_115<='9')||(LA3_115>='A' && LA3_115<='Z')||LA3_115=='_'||(LA3_115>='a' && LA3_115<='z')) ) {
                            alt3=50;
                        }
                        else {
                            alt3=6;}
                    }
                    else {
                        alt3=50;}
                    }
                    break;
                case 'r':
                    {
                    int LA3_77 = input.LA(4);

                    if ( (LA3_77=='e') ) {
                        int LA3_116 = input.LA(5);

                        if ( (LA3_116=='n') ) {
                            int LA3_154 = input.LA(6);

                            if ( (LA3_154=='t') ) {
                                int LA3_187 = input.LA(7);

                                if ( (LA3_187=='r') ) {
                                    int LA3_213 = input.LA(8);

                                    if ( (LA3_213=='e') ) {
                                        int LA3_237 = input.LA(9);

                                        if ( (LA3_237=='l') ) {
                                            int LA3_254 = input.LA(10);

                                            if ( (LA3_254=='e') ) {
                                                int LA3_266 = input.LA(11);

                                                if ( (LA3_266=='a') ) {
                                                    int LA3_275 = input.LA(12);

                                                    if ( (LA3_275=='s') ) {
                                                        int LA3_282 = input.LA(13);

                                                        if ( (LA3_282=='e') ) {
                                                            int LA3_286 = input.LA(14);

                                                            if ( (LA3_286=='(') ) {
                                                                alt3=34;
                                                            }
                                                            else {
                                                                alt3=50;}
                                                        }
                                                        else {
                                                            alt3=50;}
                                                    }
                                                    else {
                                                        alt3=50;}
                                                }
                                                else {
                                                    alt3=50;}
                                            }
                                            else {
                                                alt3=50;}
                                        }
                                        else {
                                            alt3=50;}
                                    }
                                    else {
                                        alt3=50;}
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                    }
                    break;
                default:
                    alt3=50;}

                }
                break;
            case 'r':
                {
                switch ( input.LA(3) ) {
                case 'i':
                    {
                    int LA3_78 = input.LA(4);

                    if ( (LA3_78=='m') ) {
                        int LA3_117 = input.LA(5);

                        if ( (LA3_117=='d') ) {
                            int LA3_155 = input.LA(6);

                            if ( (LA3_155=='s') ) {
                                int LA3_188 = input.LA(7);

                                if ( (LA3_188=='-'||(LA3_188>='/' && LA3_188<='9')||(LA3_188>='A' && LA3_188<='Z')||LA3_188=='_'||(LA3_188>='a' && LA3_188<='z')) ) {
                                    alt3=50;
                                }
                                else {
                                    alt3=11;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                    }
                    break;
                case 'o':
                    {
                    int LA3_79 = input.LA(4);

                    if ( (LA3_79=='c') ) {
                        int LA3_118 = input.LA(5);

                        if ( (LA3_118=='d') ) {
                            int LA3_156 = input.LA(6);

                            if ( (LA3_156=='s') ) {
                                int LA3_189 = input.LA(7);

                                if ( (LA3_189=='-'||(LA3_189>='/' && LA3_189<='9')||(LA3_189>='A' && LA3_189<='Z')||LA3_189=='_'||(LA3_189>='a' && LA3_189<='z')) ) {
                                    alt3=50;
                                }
                                else {
                                    alt3=12;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                    }
                    break;
                default:
                    alt3=50;}

                }
                break;
            default:
                alt3=50;}

            }
            break;
        case 'r':
            {
            switch ( input.LA(2) ) {
            case 'e':
                {
                int LA3_42 = input.LA(3);

                if ( (LA3_42=='l') ) {
                    int LA3_80 = input.LA(4);

                    if ( (LA3_80=='e') ) {
                        int LA3_119 = input.LA(5);

                        if ( (LA3_119=='a') ) {
                            int LA3_157 = input.LA(6);

                            if ( (LA3_157=='s') ) {
                                int LA3_190 = input.LA(7);

                                if ( (LA3_190=='e') ) {
                                    int LA3_216 = input.LA(8);

                                    if ( (LA3_216=='-'||(LA3_216>='/' && LA3_216<='9')||(LA3_216>='A' && LA3_216<='Z')||LA3_216=='_'||(LA3_216>='a' && LA3_216<='z')) ) {
                                        alt3=50;
                                    }
                                    else {
                                        alt3=7;}
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
                }
                break;
            case 'u':
                {
                int LA3_43 = input.LA(3);

                if ( (LA3_43=='n') ) {
                    int LA3_81 = input.LA(4);

                    if ( (LA3_81=='-'||(LA3_81>='/' && LA3_81<='9')||(LA3_81>='A' && LA3_81<='Z')||LA3_81=='_'||(LA3_81>='a' && LA3_81<='z')) ) {
                        alt3=50;
                    }
                    else {
                        alt3=13;}
                }
                else {
                    alt3=50;}
                }
                break;
            default:
                alt3=50;}

            }
            break;
        case 's':
            {
            switch ( input.LA(2) ) {
            case 'e':
                {
                int LA3_44 = input.LA(3);

                if ( (LA3_44=='l') ) {
                    int LA3_82 = input.LA(4);

                    if ( (LA3_82=='e') ) {
                        int LA3_121 = input.LA(5);

                        if ( (LA3_121=='c') ) {
                            int LA3_158 = input.LA(6);

                            if ( (LA3_158=='t') ) {
                                int LA3_191 = input.LA(7);

                                if ( (LA3_191=='-'||(LA3_191>='/' && LA3_191<='9')||(LA3_191>='A' && LA3_191<='Z')||LA3_191=='_'||(LA3_191>='a' && LA3_191<='z')) ) {
                                    alt3=50;
                                }
                                else {
                                    alt3=38;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
                }
                break;
            case 'i':
                {
                switch ( input.LA(3) ) {
                case 't':
                    {
                    int LA3_83 = input.LA(4);

                    if ( (LA3_83=='e') ) {
                        int LA3_122 = input.LA(5);

                        if ( (LA3_122=='-'||(LA3_122>='/' && LA3_122<='9')||(LA3_122>='A' && LA3_122<='Z')||LA3_122=='_'||(LA3_122>='a' && LA3_122<='z')) ) {
                            alt3=50;
                        }
                        else {
                            alt3=8;}
                    }
                    else {
                        alt3=50;}
                    }
                    break;
                case 'z':
                    {
                    int LA3_84 = input.LA(4);

                    if ( (LA3_84=='e') ) {
                        int LA3_123 = input.LA(5);

                        if ( (LA3_123=='-'||(LA3_123>='/' && LA3_123<='9')||(LA3_123>='A' && LA3_123<='Z')||LA3_123=='_'||(LA3_123>='a' && LA3_123<='z')) ) {
                            alt3=50;
                        }
                        else {
                            alt3=28;}
                    }
                    else {
                        alt3=50;}
                    }
                    break;
                default:
                    alt3=50;}

                }
                break;
            case 't':
                {
                int LA3_46 = input.LA(3);

                if ( (LA3_46=='a') ) {
                    int LA3_85 = input.LA(4);

                    if ( (LA3_85=='r') ) {
                        int LA3_124 = input.LA(5);

                        if ( (LA3_124=='t') ) {
                            switch ( input.LA(6) ) {
                            case 't':
                                {
                                int LA3_192 = input.LA(7);

                                if ( (LA3_192=='i') ) {
                                    int LA3_218 = input.LA(8);

                                    if ( (LA3_218=='m') ) {
                                        int LA3_239 = input.LA(9);

                                        if ( (LA3_239=='e') ) {
                                            int LA3_255 = input.LA(10);

                                            if ( (LA3_255=='-'||(LA3_255>='/' && LA3_255<='9')||(LA3_255>='A' && LA3_255<='Z')||LA3_255=='_'||(LA3_255>='a' && LA3_255<='z')) ) {
                                                alt3=50;
                                            }
                                            else {
                                                alt3=17;}
                                        }
                                        else {
                                            alt3=50;}
                                    }
                                    else {
                                        alt3=50;}
                                }
                                else {
                                    alt3=50;}
                                }
                                break;
                            case 'e':
                                {
                                int LA3_193 = input.LA(7);

                                if ( (LA3_193=='v') ) {
                                    int LA3_219 = input.LA(8);

                                    if ( (LA3_219=='n') ) {
                                        int LA3_240 = input.LA(9);

                                        if ( (LA3_240=='u') ) {
                                            int LA3_256 = input.LA(10);

                                            if ( (LA3_256=='m') ) {
                                                int LA3_268 = input.LA(11);

                                                if ( (LA3_268=='-'||(LA3_268>='/' && LA3_268<='9')||(LA3_268>='A' && LA3_268<='Z')||LA3_268=='_'||(LA3_268>='a' && LA3_268<='z')) ) {
                                                    alt3=50;
                                                }
                                                else {
                                                    alt3=24;}
                                            }
                                            else {
                                                alt3=50;}
                                        }
                                        else {
                                            alt3=50;}
                                    }
                                    else {
                                        alt3=50;}
                                }
                                else {
                                    alt3=50;}
                                }
                                break;
                            default:
                                alt3=50;}

                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
                }
                break;
            default:
                alt3=50;}

            }
            break;
        case 'b':
            {
            int LA3_9 = input.LA(2);

            if ( (LA3_9=='l') ) {
                int LA3_47 = input.LA(3);

                if ( (LA3_47=='o') ) {
                    int LA3_86 = input.LA(4);

                    if ( (LA3_86=='c') ) {
                        int LA3_125 = input.LA(5);

                        if ( (LA3_125=='k') ) {
                            int LA3_162 = input.LA(6);

                            if ( (LA3_162=='-'||(LA3_162>='/' && LA3_162<='9')||(LA3_162>='A' && LA3_162<='Z')||LA3_162=='_'||(LA3_162>='a' && LA3_162<='z')) ) {
                                alt3=50;
                            }
                            else {
                                alt3=9;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
            }
            else {
                alt3=50;}
            }
            break;
        case 'f':
            {
            int LA3_10 = input.LA(2);

            if ( (LA3_10=='i') ) {
                switch ( input.LA(3) ) {
                case 'l':
                    {
                    int LA3_87 = input.LA(4);

                    if ( (LA3_87=='e') ) {
                        int LA3_126 = input.LA(5);

                        if ( (LA3_126=='-'||(LA3_126>='/' && LA3_126<='9')||(LA3_126>='A' && LA3_126<='Z')||LA3_126=='_'||(LA3_126>='a' && LA3_126<='z')) ) {
                            alt3=50;
                        }
                        else {
                            alt3=10;}
                    }
                    else {
                        alt3=50;}
                    }
                    break;
                case 'n':
                    {
                    int LA3_88 = input.LA(4);

                    if ( (LA3_88=='d') ) {
                        int LA3_127 = input.LA(5);

                        if ( (LA3_127=='e') ) {
                            int LA3_164 = input.LA(6);

                            if ( (LA3_164=='v') ) {
                                int LA3_195 = input.LA(7);

                                if ( (LA3_195=='e') ) {
                                    int LA3_220 = input.LA(8);

                                    if ( (LA3_220=='n') ) {
                                        int LA3_241 = input.LA(9);

                                        if ( (LA3_241=='t') ) {
                                            int LA3_257 = input.LA(10);

                                            if ( (LA3_257=='s') ) {
                                                int LA3_269 = input.LA(11);

                                                if ( (LA3_269=='(') ) {
                                                    alt3=37;
                                                }
                                                else {
                                                    alt3=50;}
                                            }
                                            else {
                                                alt3=50;}
                                        }
                                        else {
                                            alt3=50;}
                                    }
                                    else {
                                        alt3=50;}
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                    }
                    break;
                default:
                    alt3=50;}

            }
            else {
                alt3=50;}
            }
            break;
        case 'l':
            {
            switch ( input.LA(2) ) {
            case 'a':
                {
                int LA3_49 = input.LA(3);

                if ( (LA3_49=='t') ) {
                    int LA3_89 = input.LA(4);

                    if ( (LA3_89=='e') ) {
                        int LA3_128 = input.LA(5);

                        if ( (LA3_128=='s') ) {
                            int LA3_165 = input.LA(6);

                            if ( (LA3_165=='t') ) {
                                int LA3_196 = input.LA(7);

                                if ( (LA3_196=='(') ) {
                                    alt3=33;
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
                }
                break;
            case 'i':
                {
                int LA3_50 = input.LA(3);

                if ( (LA3_50=='k') ) {
                    int LA3_90 = input.LA(4);

                    if ( (LA3_90=='e') ) {
                        int LA3_129 = input.LA(5);

                        if ( (LA3_129=='-'||(LA3_129>='/' && LA3_129<='9')||(LA3_129>='A' && LA3_129<='Z')||LA3_129=='_'||(LA3_129>='a' && LA3_129<='z')) ) {
                            alt3=50;
                        }
                        else {
                            alt3=48;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
                }
                break;
            case 's':
                {
                int LA3_51 = input.LA(3);

                if ( (LA3_51=='-'||(LA3_51>='/' && LA3_51<='9')||(LA3_51>='A' && LA3_51<='Z')||LA3_51=='_'||(LA3_51>='a' && LA3_51<='z')) ) {
                    alt3=50;
                }
                else {
                    alt3=14;}
                }
                break;
            default:
                alt3=50;}

            }
            break;
        case 'c':
            {
            switch ( input.LA(2) ) {
            case 'h':
                {
                int LA3_52 = input.LA(3);

                if ( (LA3_52=='i') ) {
                    int LA3_92 = input.LA(4);

                    if ( (LA3_92=='l') ) {
                        int LA3_130 = input.LA(5);

                        if ( (LA3_130=='d') ) {
                            int LA3_167 = input.LA(6);

                            if ( (LA3_167=='r') ) {
                                int LA3_197 = input.LA(7);

                                if ( (LA3_197=='e') ) {
                                    int LA3_222 = input.LA(8);

                                    if ( (LA3_222=='l') ) {
                                        int LA3_242 = input.LA(9);

                                        if ( (LA3_242=='e') ) {
                                            int LA3_258 = input.LA(10);

                                            if ( (LA3_258=='a') ) {
                                                int LA3_270 = input.LA(11);

                                                if ( (LA3_270=='s') ) {
                                                    int LA3_278 = input.LA(12);

                                                    if ( (LA3_278=='e') ) {
                                                        int LA3_283 = input.LA(13);

                                                        if ( (LA3_283=='(') ) {
                                                            alt3=35;
                                                        }
                                                        else {
                                                            alt3=50;}
                                                    }
                                                    else {
                                                        alt3=50;}
                                                }
                                                else {
                                                    alt3=50;}
                                            }
                                            else {
                                                alt3=50;}
                                        }
                                        else {
                                            alt3=50;}
                                    }
                                    else {
                                        alt3=50;}
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
                }
                break;
            case 'r':
                {
                int LA3_53 = input.LA(3);

                if ( (LA3_53=='e') ) {
                    int LA3_93 = input.LA(4);

                    if ( (LA3_93=='a') ) {
                        int LA3_131 = input.LA(5);

                        if ( (LA3_131=='t') ) {
                            int LA3_168 = input.LA(6);

                            if ( (LA3_168=='e') ) {
                                switch ( input.LA(7) ) {
                                case 'b':
                                    {
                                    int LA3_223 = input.LA(8);

                                    if ( (LA3_223=='y') ) {
                                        int LA3_243 = input.LA(9);

                                        if ( (LA3_243=='-'||(LA3_243>='/' && LA3_243<='9')||(LA3_243>='A' && LA3_243<='Z')||LA3_243=='_'||(LA3_243>='a' && LA3_243<='z')) ) {
                                            alt3=50;
                                        }
                                        else {
                                            alt3=19;}
                                    }
                                    else {
                                        alt3=50;}
                                    }
                                    break;
                                case 'd':
                                    {
                                    int LA3_224 = input.LA(8);

                                    if ( (LA3_224=='a') ) {
                                        int LA3_244 = input.LA(9);

                                        if ( (LA3_244=='t') ) {
                                            int LA3_260 = input.LA(10);

                                            if ( (LA3_260=='e') ) {
                                                int LA3_271 = input.LA(11);

                                                if ( (LA3_271=='-'||(LA3_271>='/' && LA3_271<='9')||(LA3_271>='A' && LA3_271<='Z')||LA3_271=='_'||(LA3_271>='a' && LA3_271<='z')) ) {
                                                    alt3=50;
                                                }
                                                else {
                                                    alt3=15;}
                                            }
                                            else {
                                                alt3=50;}
                                        }
                                        else {
                                            alt3=50;}
                                    }
                                    else {
                                        alt3=50;}
                                    }
                                    break;
                                default:
                                    alt3=50;}

                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
                }
                break;
            case 'o':
                {
                int LA3_54 = input.LA(3);

                if ( (LA3_54=='u') ) {
                    int LA3_94 = input.LA(4);

                    if ( (LA3_94=='n') ) {
                        int LA3_132 = input.LA(5);

                        if ( (LA3_132=='t') ) {
                            int LA3_169 = input.LA(6);

                            if ( (LA3_169=='-'||(LA3_169>='/' && LA3_169<='9')||(LA3_169>='A' && LA3_169<='Z')||LA3_169=='_'||(LA3_169>='a' && LA3_169<='z')) ) {
                                alt3=50;
                            }
                            else {
                                alt3=29;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
                }
                break;
            default:
                alt3=50;}

            }
            break;
        case 'm':
            {
            int LA3_13 = input.LA(2);

            if ( (LA3_13=='o') ) {
                int LA3_55 = input.LA(3);

                if ( (LA3_55=='d') ) {
                    switch ( input.LA(4) ) {
                    case 'd':
                        {
                        int LA3_133 = input.LA(5);

                        if ( (LA3_133=='a') ) {
                            int LA3_170 = input.LA(6);

                            if ( (LA3_170=='t') ) {
                                int LA3_200 = input.LA(7);

                                if ( (LA3_200=='e') ) {
                                    int LA3_225 = input.LA(8);

                                    if ( (LA3_225=='-'||(LA3_225>='/' && LA3_225<='9')||(LA3_225>='A' && LA3_225<='Z')||LA3_225=='_'||(LA3_225>='a' && LA3_225<='z')) ) {
                                        alt3=50;
                                    }
                                    else {
                                        alt3=16;}
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                        }
                        break;
                    case 'b':
                        {
                        int LA3_134 = input.LA(5);

                        if ( (LA3_134=='y') ) {
                            int LA3_171 = input.LA(6);

                            if ( (LA3_171=='-'||(LA3_171>='/' && LA3_171<='9')||(LA3_171>='A' && LA3_171<='Z')||LA3_171=='_'||(LA3_171>='a' && LA3_171<='z')) ) {
                                alt3=50;
                            }
                            else {
                                alt3=20;}
                        }
                        else {
                            alt3=50;}
                        }
                        break;
                    default:
                        alt3=50;}

                }
                else {
                    alt3=50;}
            }
            else {
                alt3=50;}
            }
            break;
        case 'e':
            {
            int LA3_14 = input.LA(2);

            if ( (LA3_14=='n') ) {
                int LA3_56 = input.LA(3);

                if ( (LA3_56=='d') ) {
                    switch ( input.LA(4) ) {
                    case 't':
                        {
                        int LA3_135 = input.LA(5);

                        if ( (LA3_135=='i') ) {
                            int LA3_172 = input.LA(6);

                            if ( (LA3_172=='m') ) {
                                int LA3_202 = input.LA(7);

                                if ( (LA3_202=='e') ) {
                                    int LA3_226 = input.LA(8);

                                    if ( (LA3_226=='-'||(LA3_226>='/' && LA3_226<='9')||(LA3_226>='A' && LA3_226<='Z')||LA3_226=='_'||(LA3_226>='a' && LA3_226<='z')) ) {
                                        alt3=50;
                                    }
                                    else {
                                        alt3=18;}
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                        }
                        break;
                    case 'e':
                        {
                        int LA3_136 = input.LA(5);

                        if ( (LA3_136=='v') ) {
                            int LA3_173 = input.LA(6);

                            if ( (LA3_173=='n') ) {
                                int LA3_203 = input.LA(7);

                                if ( (LA3_203=='u') ) {
                                    int LA3_227 = input.LA(8);

                                    if ( (LA3_227=='m') ) {
                                        int LA3_247 = input.LA(9);

                                        if ( (LA3_247=='-'||(LA3_247>='/' && LA3_247<='9')||(LA3_247>='A' && LA3_247<='Z')||LA3_247=='_'||(LA3_247>='a' && LA3_247<='z')) ) {
                                            alt3=50;
                                        }
                                        else {
                                            alt3=25;}
                                    }
                                    else {
                                        alt3=50;}
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                        }
                        break;
                    default:
                        alt3=50;}

                }
                else {
                    alt3=50;}
            }
            else {
                alt3=50;}
            }
            break;
        case 'n':
            {
            switch ( input.LA(2) ) {
            case 'o':
                {
                int LA3_57 = input.LA(3);

                if ( (LA3_57=='t') ) {
                    int LA3_97 = input.LA(4);

                    if ( (LA3_97=='-'||(LA3_97>='/' && LA3_97<='9')||(LA3_97>='A' && LA3_97<='Z')||LA3_97=='_'||(LA3_97>='a' && LA3_97<='z')) ) {
                        alt3=50;
                    }
                    else {
                        alt3=46;}
                }
                else {
                    alt3=50;}
                }
                break;
            case 'a':
                {
                int LA3_58 = input.LA(3);

                if ( (LA3_58=='m') ) {
                    int LA3_98 = input.LA(4);

                    if ( (LA3_98=='e') ) {
                        int LA3_138 = input.LA(5);

                        if ( (LA3_138=='-'||(LA3_138>='/' && LA3_138<='9')||(LA3_138>='A' && LA3_138<='Z')||LA3_138=='_'||(LA3_138>='a' && LA3_138<='z')) ) {
                            alt3=50;
                        }
                        else {
                            alt3=21;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
                }
                break;
            case 'u':
                {
                int LA3_59 = input.LA(3);

                if ( (LA3_59=='m') ) {
                    switch ( input.LA(4) ) {
                    case 'l':
                        {
                        int LA3_139 = input.LA(5);

                        if ( (LA3_139=='s') ) {
                            int LA3_175 = input.LA(6);

                            if ( (LA3_175=='s') ) {
                                int LA3_204 = input.LA(7);

                                if ( (LA3_204=='-'||(LA3_204>='/' && LA3_204<='9')||(LA3_204>='A' && LA3_204<='Z')||LA3_204=='_'||(LA3_204>='a' && LA3_204<='z')) ) {
                                    alt3=50;
                                }
                                else {
                                    alt3=27;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                        }
                        break;
                    case 'r':
                        {
                        int LA3_140 = input.LA(5);

                        if ( (LA3_140=='u') ) {
                            int LA3_176 = input.LA(6);

                            if ( (LA3_176=='n') ) {
                                int LA3_205 = input.LA(7);

                                if ( (LA3_205=='s') ) {
                                    int LA3_229 = input.LA(8);

                                    if ( (LA3_229=='(') ) {
                                        alt3=30;
                                    }
                                    else {
                                        alt3=50;}
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                        }
                        break;
                    case 'f':
                        {
                        int LA3_141 = input.LA(5);

                        if ( (LA3_141=='i') ) {
                            int LA3_177 = input.LA(6);

                            if ( (LA3_177=='l') ) {
                                int LA3_206 = input.LA(7);

                                if ( (LA3_206=='e') ) {
                                    int LA3_230 = input.LA(8);

                                    if ( (LA3_230=='s') ) {
                                        int LA3_249 = input.LA(9);

                                        if ( (LA3_249=='(') ) {
                                            alt3=31;
                                        }
                                        else {
                                            alt3=50;}
                                    }
                                    else {
                                        alt3=50;}
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                        }
                        break;
                    case 'e':
                        {
                        int LA3_142 = input.LA(5);

                        if ( (LA3_142=='v') ) {
                            int LA3_178 = input.LA(6);

                            if ( (LA3_178=='e') ) {
                                int LA3_207 = input.LA(7);

                                if ( (LA3_207=='n') ) {
                                    int LA3_231 = input.LA(8);

                                    if ( (LA3_231=='t') ) {
                                        int LA3_250 = input.LA(9);

                                        if ( (LA3_250=='s') ) {
                                            int LA3_263 = input.LA(10);

                                            if ( (LA3_263=='-'||(LA3_263>='/' && LA3_263<='9')||(LA3_263>='A' && LA3_263<='Z')||LA3_263=='_'||(LA3_263>='a' && LA3_263<='z')) ) {
                                                alt3=50;
                                            }
                                            else {
                                                alt3=26;}
                                        }
                                        else {
                                            alt3=50;}
                                    }
                                    else {
                                        alt3=50;}
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                        }
                        break;
                    case 'b':
                        {
                        int LA3_143 = input.LA(5);

                        if ( (LA3_143=='e') ) {
                            int LA3_179 = input.LA(6);

                            if ( (LA3_179=='r') ) {
                                int LA3_208 = input.LA(7);

                                if ( (LA3_208=='-'||(LA3_208>='/' && LA3_208<='9')||(LA3_208>='A' && LA3_208<='Z')||LA3_208=='_'||(LA3_208>='a' && LA3_208<='z')) ) {
                                    alt3=50;
                                }
                                else {
                                    alt3=23;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                        }
                        break;
                    default:
                        alt3=50;}

                }
                else {
                    alt3=50;}
                }
                break;
            default:
                alt3=50;}

            }
            break;
        case 'v':
            {
            int LA3_16 = input.LA(2);

            if ( (LA3_16=='e') ) {
                int LA3_60 = input.LA(3);

                if ( (LA3_60=='r') ) {
                    int LA3_100 = input.LA(4);

                    if ( (LA3_100=='s') ) {
                        int LA3_144 = input.LA(5);

                        if ( (LA3_144=='i') ) {
                            int LA3_180 = input.LA(6);

                            if ( (LA3_180=='o') ) {
                                int LA3_209 = input.LA(7);

                                if ( (LA3_209=='n') ) {
                                    int LA3_233 = input.LA(8);

                                    if ( (LA3_233=='-'||(LA3_233>='/' && LA3_233<='9')||(LA3_233>='A' && LA3_233<='Z')||LA3_233=='_'||(LA3_233>='a' && LA3_233<='z')) ) {
                                        alt3=50;
                                    }
                                    else {
                                        alt3=22;}
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
            }
            else {
                alt3=50;}
            }
            break;
        case 'd':
            {
            int LA3_17 = input.LA(2);

            if ( (LA3_17=='a') ) {
                int LA3_61 = input.LA(3);

                if ( (LA3_61=='t') ) {
                    int LA3_101 = input.LA(4);

                    if ( (LA3_101=='a') ) {
                        int LA3_145 = input.LA(5);

                        if ( (LA3_145=='q') ) {
                            int LA3_181 = input.LA(6);

                            if ( (LA3_181=='u') ) {
                                int LA3_210 = input.LA(7);

                                if ( (LA3_210=='a') ) {
                                    int LA3_234 = input.LA(8);

                                    if ( (LA3_234=='l') ) {
                                        int LA3_252 = input.LA(9);

                                        if ( (LA3_252=='i') ) {
                                            int LA3_264 = input.LA(10);

                                            if ( (LA3_264=='t') ) {
                                                int LA3_273 = input.LA(11);

                                                if ( (LA3_273=='y') ) {
                                                    int LA3_280 = input.LA(12);

                                                    if ( (LA3_280=='(') ) {
                                                        alt3=32;
                                                    }
                                                    else {
                                                        alt3=50;}
                                                }
                                                else {
                                                    alt3=50;}
                                            }
                                            else {
                                                alt3=50;}
                                        }
                                        else {
                                            alt3=50;}
                                    }
                                    else {
                                        alt3=50;}
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
            }
            else {
                alt3=50;}
            }
            break;
        case 'i':
            {
            int LA3_18 = input.LA(2);

            if ( (LA3_18=='n') ) {
                switch ( input.LA(3) ) {
                case 't':
                    {
                    int LA3_102 = input.LA(4);

                    if ( (LA3_102=='l') ) {
                        int LA3_146 = input.LA(5);

                        if ( (LA3_146=='u') ) {
                            int LA3_182 = input.LA(6);

                            if ( (LA3_182=='m') ) {
                                int LA3_211 = input.LA(7);

                                if ( (LA3_211=='i') ) {
                                    int LA3_235 = input.LA(8);

                                    if ( (LA3_235=='n') ) {
                                        int LA3_253 = input.LA(9);

                                        if ( (LA3_253=='o') ) {
                                            int LA3_265 = input.LA(10);

                                            if ( (LA3_265=='s') ) {
                                                int LA3_274 = input.LA(11);

                                                if ( (LA3_274=='i') ) {
                                                    int LA3_281 = input.LA(12);

                                                    if ( (LA3_281=='t') ) {
                                                        int LA3_285 = input.LA(13);

                                                        if ( (LA3_285=='y') ) {
                                                            int LA3_288 = input.LA(14);

                                                            if ( (LA3_288=='(') ) {
                                                                alt3=36;
                                                            }
                                                            else {
                                                                alt3=50;}
                                                        }
                                                        else {
                                                            alt3=50;}
                                                    }
                                                    else {
                                                        alt3=50;}
                                                }
                                                else {
                                                    alt3=50;}
                                            }
                                            else {
                                                alt3=50;}
                                        }
                                        else {
                                            alt3=50;}
                                    }
                                    else {
                                        alt3=50;}
                                }
                                else {
                                    alt3=50;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                    }
                    break;
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
                    alt3=50;
                    }
                    break;
                default:
                    alt3=44;}

            }
            else {
                alt3=50;}
            }
            break;
        case 'S':
            {
            int LA3_19 = input.LA(2);

            if ( (LA3_19=='E') ) {
                int LA3_63 = input.LA(3);

                if ( (LA3_63=='L') ) {
                    int LA3_104 = input.LA(4);

                    if ( (LA3_104=='E') ) {
                        int LA3_147 = input.LA(5);

                        if ( (LA3_147=='C') ) {
                            int LA3_183 = input.LA(6);

                            if ( (LA3_183=='T') ) {
                                int LA3_212 = input.LA(7);

                                if ( (LA3_212=='-'||(LA3_212>='/' && LA3_212<='9')||(LA3_212>='A' && LA3_212<='Z')||LA3_212=='_'||(LA3_212>='a' && LA3_212<='z')) ) {
                                    alt3=50;
                                }
                                else {
                                    alt3=39;}
                            }
                            else {
                                alt3=50;}
                        }
                        else {
                            alt3=50;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
            }
            else {
                alt3=50;}
            }
            break;
        case 'A':
            {
            int LA3_20 = input.LA(2);

            if ( (LA3_20=='N') ) {
                int LA3_64 = input.LA(3);

                if ( (LA3_64=='D') ) {
                    int LA3_105 = input.LA(4);

                    if ( (LA3_105=='-'||(LA3_105>='/' && LA3_105<='9')||(LA3_105>='A' && LA3_105<='Z')||LA3_105=='_'||(LA3_105>='a' && LA3_105<='z')) ) {
                        alt3=50;
                    }
                    else {
                        alt3=41;}
                }
                else {
                    alt3=50;}
            }
            else {
                alt3=50;}
            }
            break;
        case 'o':
            {
            int LA3_21 = input.LA(2);

            if ( (LA3_21=='r') ) {
                int LA3_65 = input.LA(3);

                if ( (LA3_65=='-'||(LA3_65>='/' && LA3_65<='9')||(LA3_65>='A' && LA3_65<='Z')||LA3_65=='_'||(LA3_65>='a' && LA3_65<='z')) ) {
                    alt3=50;
                }
                else {
                    alt3=42;}
            }
            else {
                alt3=50;}
            }
            break;
        case 'O':
            {
            int LA3_22 = input.LA(2);

            if ( (LA3_22=='R') ) {
                int LA3_66 = input.LA(3);

                if ( (LA3_66=='-'||(LA3_66>='/' && LA3_66<='9')||(LA3_66>='A' && LA3_66<='Z')||LA3_66=='_'||(LA3_66>='a' && LA3_66<='z')) ) {
                    alt3=50;
                }
                else {
                    alt3=43;}
            }
            else {
                alt3=50;}
            }
            break;
        case 'I':
            {
            int LA3_23 = input.LA(2);

            if ( (LA3_23=='N') ) {
                int LA3_67 = input.LA(3);

                if ( (LA3_67=='-'||(LA3_67>='/' && LA3_67<='9')||(LA3_67>='A' && LA3_67<='Z')||LA3_67=='_'||(LA3_67>='a' && LA3_67<='z')) ) {
                    alt3=50;
                }
                else {
                    alt3=45;}
            }
            else {
                alt3=50;}
            }
            break;
        case 'N':
            {
            int LA3_24 = input.LA(2);

            if ( (LA3_24=='O') ) {
                int LA3_68 = input.LA(3);

                if ( (LA3_68=='T') ) {
                    int LA3_109 = input.LA(4);

                    if ( (LA3_109=='-'||(LA3_109>='/' && LA3_109<='9')||(LA3_109>='A' && LA3_109<='Z')||LA3_109=='_'||(LA3_109>='a' && LA3_109<='z')) ) {
                        alt3=50;
                    }
                    else {
                        alt3=47;}
                }
                else {
                    alt3=50;}
            }
            else {
                alt3=50;}
            }
            break;
        case 'L':
            {
            int LA3_25 = input.LA(2);

            if ( (LA3_25=='I') ) {
                int LA3_69 = input.LA(3);

                if ( (LA3_69=='K') ) {
                    int LA3_110 = input.LA(4);

                    if ( (LA3_110=='E') ) {
                        int LA3_150 = input.LA(5);

                        if ( (LA3_150=='-'||(LA3_150>='/' && LA3_150<='9')||(LA3_150>='A' && LA3_150<='Z')||LA3_150=='_'||(LA3_150>='a' && LA3_150<='z')) ) {
                            alt3=50;
                        }
                        else {
                            alt3=49;}
                    }
                    else {
                        alt3=50;}
                }
                else {
                    alt3=50;}
            }
            else {
                alt3=50;}
            }
            break;
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
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
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
        case 't':
        case 'u':
        case 'x':
        case 'y':
        case 'z':
            {
            alt3=50;
            }
            break;
        case ',':
            {
            alt3=51;
            }
            break;
        case ' ':
            {
            int LA3_28 = input.LA(2);

            if ( ((LA3_28>='\t' && LA3_28<='\n')||(LA3_28>='\f' && LA3_28<='\r')||LA3_28==' ') ) {
                alt3=59;
            }
            else {
                alt3=52;}
            }
            break;
        case '.':
            {
            alt3=53;
            }
            break;
        case '>':
            {
            alt3=54;
            }
            break;
        case '<':
            {
            alt3=55;
            }
            break;
        case '=':
            {
            alt3=56;
            }
            break;
        case '%':
        case '*':
            {
            alt3=57;
            }
            break;
        case '\n':
            {
            int LA3_34 = input.LA(2);

            if ( ((LA3_34>='\t' && LA3_34<='\n')||(LA3_34>='\f' && LA3_34<='\r')||LA3_34==' ') ) {
                alt3=59;
            }
            else {
                alt3=58;}
            }
            break;
        case '\t':
        case '\f':
        case '\r':
            {
            alt3=59;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | VALUE | COMMA | SPACE | DOT | GT | LT | EQ | STAR | NL | WS );", 3, 0, input);

            throw nvae;
        }

        switch (alt3) {
            case 1 :
                // Sql.g:1:10: T14
                {
                mT14(); 

                }
                break;
            case 2 :
                // Sql.g:1:14: T15
                {
                mT15(); 

                }
                break;
            case 3 :
                // Sql.g:1:18: T16
                {
                mT16(); 

                }
                break;
            case 4 :
                // Sql.g:1:22: T17
                {
                mT17(); 

                }
                break;
            case 5 :
                // Sql.g:1:26: T18
                {
                mT18(); 

                }
                break;
            case 6 :
                // Sql.g:1:30: T19
                {
                mT19(); 

                }
                break;
            case 7 :
                // Sql.g:1:34: T20
                {
                mT20(); 

                }
                break;
            case 8 :
                // Sql.g:1:38: T21
                {
                mT21(); 

                }
                break;
            case 9 :
                // Sql.g:1:42: T22
                {
                mT22(); 

                }
                break;
            case 10 :
                // Sql.g:1:46: T23
                {
                mT23(); 

                }
                break;
            case 11 :
                // Sql.g:1:50: T24
                {
                mT24(); 

                }
                break;
            case 12 :
                // Sql.g:1:54: T25
                {
                mT25(); 

                }
                break;
            case 13 :
                // Sql.g:1:58: T26
                {
                mT26(); 

                }
                break;
            case 14 :
                // Sql.g:1:62: T27
                {
                mT27(); 

                }
                break;
            case 15 :
                // Sql.g:1:66: T28
                {
                mT28(); 

                }
                break;
            case 16 :
                // Sql.g:1:70: T29
                {
                mT29(); 

                }
                break;
            case 17 :
                // Sql.g:1:74: T30
                {
                mT30(); 

                }
                break;
            case 18 :
                // Sql.g:1:78: T31
                {
                mT31(); 

                }
                break;
            case 19 :
                // Sql.g:1:82: T32
                {
                mT32(); 

                }
                break;
            case 20 :
                // Sql.g:1:86: T33
                {
                mT33(); 

                }
                break;
            case 21 :
                // Sql.g:1:90: T34
                {
                mT34(); 

                }
                break;
            case 22 :
                // Sql.g:1:94: T35
                {
                mT35(); 

                }
                break;
            case 23 :
                // Sql.g:1:98: T36
                {
                mT36(); 

                }
                break;
            case 24 :
                // Sql.g:1:102: T37
                {
                mT37(); 

                }
                break;
            case 25 :
                // Sql.g:1:106: T38
                {
                mT38(); 

                }
                break;
            case 26 :
                // Sql.g:1:110: T39
                {
                mT39(); 

                }
                break;
            case 27 :
                // Sql.g:1:114: T40
                {
                mT40(); 

                }
                break;
            case 28 :
                // Sql.g:1:118: T41
                {
                mT41(); 

                }
                break;
            case 29 :
                // Sql.g:1:122: T42
                {
                mT42(); 

                }
                break;
            case 30 :
                // Sql.g:1:126: T43
                {
                mT43(); 

                }
                break;
            case 31 :
                // Sql.g:1:130: T44
                {
                mT44(); 

                }
                break;
            case 32 :
                // Sql.g:1:134: T45
                {
                mT45(); 

                }
                break;
            case 33 :
                // Sql.g:1:138: T46
                {
                mT46(); 

                }
                break;
            case 34 :
                // Sql.g:1:142: T47
                {
                mT47(); 

                }
                break;
            case 35 :
                // Sql.g:1:146: T48
                {
                mT48(); 

                }
                break;
            case 36 :
                // Sql.g:1:150: T49
                {
                mT49(); 

                }
                break;
            case 37 :
                // Sql.g:1:154: T50
                {
                mT50(); 

                }
                break;
            case 38 :
                // Sql.g:1:158: T51
                {
                mT51(); 

                }
                break;
            case 39 :
                // Sql.g:1:162: T52
                {
                mT52(); 

                }
                break;
            case 40 :
                // Sql.g:1:166: T53
                {
                mT53(); 

                }
                break;
            case 41 :
                // Sql.g:1:170: T54
                {
                mT54(); 

                }
                break;
            case 42 :
                // Sql.g:1:174: T55
                {
                mT55(); 

                }
                break;
            case 43 :
                // Sql.g:1:178: T56
                {
                mT56(); 

                }
                break;
            case 44 :
                // Sql.g:1:182: T57
                {
                mT57(); 

                }
                break;
            case 45 :
                // Sql.g:1:186: T58
                {
                mT58(); 

                }
                break;
            case 46 :
                // Sql.g:1:190: T59
                {
                mT59(); 

                }
                break;
            case 47 :
                // Sql.g:1:194: T60
                {
                mT60(); 

                }
                break;
            case 48 :
                // Sql.g:1:198: T61
                {
                mT61(); 

                }
                break;
            case 49 :
                // Sql.g:1:202: T62
                {
                mT62(); 

                }
                break;
            case 50 :
                // Sql.g:1:206: VALUE
                {
                mVALUE(); 

                }
                break;
            case 51 :
                // Sql.g:1:212: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 52 :
                // Sql.g:1:218: SPACE
                {
                mSPACE(); 

                }
                break;
            case 53 :
                // Sql.g:1:224: DOT
                {
                mDOT(); 

                }
                break;
            case 54 :
                // Sql.g:1:228: GT
                {
                mGT(); 

                }
                break;
            case 55 :
                // Sql.g:1:231: LT
                {
                mLT(); 

                }
                break;
            case 56 :
                // Sql.g:1:234: EQ
                {
                mEQ(); 

                }
                break;
            case 57 :
                // Sql.g:1:237: STAR
                {
                mSTAR(); 

                }
                break;
            case 58 :
                // Sql.g:1:242: NL
                {
                mNL(); 

                }
                break;
            case 59 :
                // Sql.g:1:245: WS
                {
                mWS(); 

                }
                break;

        }

    }


 

}
