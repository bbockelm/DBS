grammar Sql;

@header {

import java.util.ArrayList;

}

@members {
ArrayList kws = new ArrayList();
ArrayList constraints = new ArrayList();
}

stmt	: select SPACE  selectList   SPACE where  SPACE constraintList  NL;
		 
selectList	:kw=	keyword 		{kws.add($kw.text);}
 		(COMMA 
 	kw=	keyword  		{kws.add($kw.text);}
 		)*;		 
 		
keyword	: entity 
	| entity DOT attr
	| entity DOT funct;
	
constraintList	: constraint ( SPACE 
	rel=	logicalOp 		{if(rel== null) System.out.println("REL is NULL"); constraints.add($rel.text);}
		SPACE constraint)*;

constraint	: kw=	keyword 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
	 op=	(EQ | LT | GT) 	{c.setOp($op.text);}   
	 val=	VALUE 		{c.setValue($val.text); constraints.add(c); 	}               
		| 
	kw=	keyword 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
		SPACE 
	op1=	in 	 	{c.setOp($op1.text);}  
		SPACE '(' 
	val1=	valueList 		{c.setValue($val1.text); constraints.add(c);}
		')'               
		| 
	kw=	keyword 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
		SPACE 
	op2=	like 		{c.setOp($op2.text);} 
		SPACE 
	val2=	likeValue 		{c.setValue($val2.text); constraints.add(c);};                  

where	:('WHERE' | 'where');
valueList	:VALUE (COMMA VALUE)*;
likeValue 	:(VALUE| STAR)+;
logicalOp	:(and|or);
entity	: ('ads' | 'path' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'ls');
attr	:('createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'path' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size'  );                                                    
funct	:('numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' );
select	:('select' | 'SELECT');
and	:('and' | 'AND');
or	:('or' | 'OR');
in	:('in' | 'IN');
not	:('not' | 'NOT');
like	:('like' | 'LIKE');
VALUE	:('a'..'z'|'A'..'Z'|'0'..'9')+ ;
COMMA	:(',');
SPACE	:(' ')  ;
DOT	:('.');
GT	:('>');
LT	:('<');
EQ	:('=');
STAR	:('*'|'%');
NL	:('\n');
WS 	: ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;

