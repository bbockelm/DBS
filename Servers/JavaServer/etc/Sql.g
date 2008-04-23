grammar Sql;

@header {

import java.util.ArrayList;

}

@members {
ArrayList kws = new ArrayList();
ArrayList constraints = new ArrayList();
}
@rulecatch {
catch (RecognitionException e) {
	throw e;
}
}

stmt	: select spaces selectList spaces where spaces constraintList  
	| select spaces selectList;

spaces	: (SPACE)*;
		 
selectList	:kw=	keyword 		{kws.add($kw.text);}
 		(COMMA
		spaces
 	kw=	keyword  		{kws.add($kw.text);}
 		)*;		 
 		
keyword	: entity 
	| entity DOT attr
	| entity DOT funct;
	
constraintList	: constraint ( spaces 
	rel=	logicalOp 		{if(rel== null) System.out.println("REL is NULL"); constraints.add($rel.text);}
		spaces constraint)*;

constraint	: kw=	keyword 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
		spaces
	 op=	(EQ | LT | GT) 	{c.setOp($op.text);}   
		spaces
	 val=	genValue	{c.setValue($val.text); constraints.add(c); 	}               
		| 
	kw=	keyword 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
		spaces 
	op1=	in 	 	{c.setOp($op1.text);}  
		spaces '(' 
	val1=	valueList 		{c.setValue($val1.text); constraints.add(c);}
		')'               
		| 
	kw=	keyword 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
		spaces 
	op2=	like 		{c.setOp($op2.text);} 
		spaces 
	val2=	likeValue 		{c.setValue($val2.text); constraints.add(c);};                  

where	:('WHERE' | 'where');
valueList	:VALUE (COMMA VALUE)*;
genValue	:VALUE|
		VALUE EQ VALUE (AMP VALUE EQ VALUE)*;
likeValue 	:(VALUE| STAR)+;
logicalOp	:(and|or);
entity	: ('ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'ls' | 'dq');
attr	:('createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent');
funct	:('numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' );
select	:('select' | 'SELECT' | 'find' | 'FIND');
and	:('and' | 'AND');
or	:('or' | 'OR');
in	:('in' | 'IN');
not	:('not' | 'NOT');
like	:('like' | 'LIKE');
VALUE	:('a'..'z'|'A'..'Z'|'0'..'9'|'/'|'-'|'_')+ ;
COMMA	:(',');
SPACE	:(' ')  ;
DOT	:('.');
GT	:('>');
LT	:('<');
EQ	:('=');
AMP	:('&');
STAR	:('*'|'%');
NL	:('\n');
WS 	: ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;

