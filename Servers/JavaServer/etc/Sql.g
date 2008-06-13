grammar Sql;

@header {

import java.util.ArrayList;

}

@members {
ArrayList kws = new ArrayList();
ArrayList okws = new ArrayList();
ArrayList constraints = new ArrayList();
}
@rulecatch {
catch (RecognitionException e) {
	throw e;
}
}

stmt	: select spaces selectList spaces where spaces constraintList  
	| select spaces selectList
	| select spaces selectList spaces order spaces by spaces
okw=	keyword 	{okws.add($okw.text);}
	spaces
	| select spaces selectList spaces where spaces constraintList spaces order spaces by spaces
okw=	keyword		{okws.add($okw.text);}
	spaces;


spaces	: (SPACE)*;
		 
selectList	:kw=	keyword 		{kws.add($kw.text);}
 		(
		spaces
		COMMA
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
	 val=	genValue 	{c.setValue($val.text); constraints.add(c); 	}               
		| 
	kw=	keyword 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
		spaces 
	op1=	in 	 	{c.setOp($op1.text);}  
		spaces '('
		spaces
	val1=	valueList 		{c.setValue($val1.text); constraints.add(c);}
		spaces
		')'               
		| 
	kw=	keyword 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
		spaces 
	op2=	like 		{c.setOp($op2.text);} 
		spaces 
	val2=	likeValue 		{c.setValue($val2.text); constraints.add(c);};                  

where	:('WHERE' | 'where');
dotValue        : VALUE 
		| VALUE DOT VALUE
		| VALUE SPACE VALUE
		| VALUE SPACE VALUE SPACE VALUE;
//dateValue	: (DIGIT);

valueList	:dotValue ( spaces COMMA spaces dotValue )*;
compOpt		:(EQ)
		|(LT)
		|(GT)
		|(EQ)(GT)
		|(EQ)(LT)
		|(LT)(EQ)
		|(GT)(EQ);
genValue	:dotValue
		|dotValue compOpt dotValue (AMP dotValue compOpt dotValue)*;
likeValue 	:(dotValue| STAR)+;
logicalOp	:(and|or);
entity		: ('ads' | 'dataset' | 'release' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi');
attr		:('createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numlss' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' );
funct		:('numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' );
select		:('select' | 'SELECT' | 'find' | 'FIND');
and		:('and' | 'AND');
order		:('order' | 'ORDER');
by		:('by' | 'BY');
or		:('or' | 'OR');
in		:('in' | 'IN');
not		:('not' | 'NOT');
like		:('like' | 'LIKE');
VALUE		:('a'..'z'|'A'..'Z'|'0'..'9'|'/'|'-'|'_'|':')+ ;
//DIGIT		:('0'..'9');
//DASH		:('-');
//COLON		:(':');
//CHAR		:('a'..'z'|'A'..'Z');
COMMA		:(',');
SPACE		:(' ');
DOT		:('.');
//QUOTE		:('"');
GT		:('>');
LT		:('<');
EQ		:('=');
AMP		:('&');
STAR		:('*'|'%');
NL		:('\n');
WS 		: ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;

