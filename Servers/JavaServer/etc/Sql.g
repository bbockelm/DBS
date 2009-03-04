grammar Sql;

@header {

import java.util.ArrayList;

}

@members {
ArrayList kws = new ArrayList();
ArrayList okws = new ArrayList();
ArrayList constraints = new ArrayList();
String orderingkw = "";
}
@rulecatch {
catch (RecognitionException e) {
	throw e;
}
}

stmt	: select spaces selectList spaces where spaces constraintList spaces 
	| select spaces selectList spaces
	| select spaces selectList spaces order spaces by spaces orderList
	| select spaces selectList spaces where spaces constraintList spaces order spaces by spaces orderList;


spaces	: (SPACE)*;

orderList	:okw=	keyword 		{okws.add($okw.text);}
 		(
		spaces
		COMMA
		spaces
 	okw=	keyword  		{okws.add($okw.text);}
 		)*
		spaces
	oing = 	ordering {orderingkw=$oing.text;}
		; 

ordering 	: (asc|desc)?;	 
selectList	:kw=	keyword 		{kws.add($kw.text);}
 		(
		spaces
		COMMA
		spaces
 	kw=	keyword  		{kws.add($kw.text);}
 		)*;		 
 		
keyword	: entity 
	| entity DOT attr
	| entity DOT funct
	| count spaces '(' spaces entity spaces ')'
	| sum spaces '(' spaces entity DOT attr spaces ')';
	
constraintList	: constraint1 ( spaces 
	rel=	logicalOp 		{ constraints.add($rel.text);}
		spaces constraint1)*;

constraint1     : kl=   ('(')*   {Constraint c1=new Constraint();c1.setBracket($kl.text);constraints.add(c1);}
                constraint 
                kr=    (')')*  {Constraint c=new Constraint();c.setBracket($kr.text); constraints.add(c);};

constraint	: kw=	keyword 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
		spaces
	 op=	compOpt 	{c.setOp($op.text);}   
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
	val2=	dotValue 		{c.setValue($val2.text); constraints.add(c);}
		|
 	kw=	keyword 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
		spaces 
	op3=	between		{c.setOp($op3.text);} 
		spaces 
	val3=	betValue 		{c.setValue($val3.text); constraints.add(c);};                  
                 

where	:('WHERE' | 'where');
dotValue        : VALUE 
		| 'in'
		| VALUE DOT 'in'
		| VALUE DOT VALUE
		| VALUE DOT VALUE DOT 'in'
		| VALUE DOT VALUE DOT VALUE
		| VALUE DOT VALUE DOT VALUE DOT 'in'
		| VALUE DOT VALUE DOT VALUE DOT VALUE
		| VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
		| VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
		| VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
		| VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
		| VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
		| VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
		| VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
		| VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE
		| VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
		| VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE 
		| VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
		| VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE 
		| VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT VALUE DOT 'in'
		| VALUE SPACE VALUE
		| VALUE SPACE VALUE SPACE VALUE;
//dateValue	: (DIGIT);

//cfgValue        : VALUE DOT VALUE SPACE LT SPACE VALUE;
valueList	:dotValue ( spaces COMMA spaces dotValue )*;
//cfgList		:dotValue ( spaces AMP spaces dotValue )*;
compOpt		:(EQ)
		|(LT)
		|(GT)
		|(NOT)(EQ)
		|(EQ)(GT)
		|(EQ)(LT)
		|(LT)(EQ)
		|(GT)(EQ)
		|(LT)(GT);

genValue	:dotValue
		|dotValue compOpt dotValue (AMP dotValue compOpt dotValue)*;
betValue	:dotValue spaces and spaces dotValue;
//cfgValue	: genValue (spaces AMP spaces genValue)*;

//likeValue 	:(dotValue| STAR)+;
//likeValue 	:(dotValue)+;
//likes		:(not)? spaces like;
logicalOp	:(and|or);
entity		: ('ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group'| 'pset'| 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch');
attr		:('createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family'| 'exe' | 'annotation' | 'checksum' );
funct		:('numruns()' | 'numfiles()' | 'dataquality()' | 'latest()' | 'parentrelease()' | 'childrelease()' | 'intluminosity()' | 'findevents()' );
select		:('select' | 'SELECT' | 'find' | 'FIND');
and		:('and' | 'AND');
order		:('order' | 'ORDER');
by		:('by' | 'BY');
or		:('or' | 'OR');
in		:('in' | 'IN');
not		:('not' | 'NOT');
like		:('like' | 'LIKE' | 'not' spaces 'like' | 'NOT' spaces 'LIKE');
//like		:('like' | 'LIKE');
count		:('count' | 'COUNT');
sum		:('sum' | 'SUM');
asc		:('asc' | 'ASC');
desc		:('desc' | 'DESC');
between		:('between' | 'BETWEEN');
lb		: ('(');
rb		: (')');
//likeLeft	:('LikeLeft');
//likeRight	:('LikeRight');
//likeCfg		:('<like>');
//pset		:('pset');
VALUE		:('a'..'z'|'A'..'Z'|'0'..'9'|'/'|'-'|'_'|':'|'#'|'*'|'%' )+ ;
//DVALUE		:('a'..'z'|'A'..'Z'|'0'..'9'|'/'|'-'|'_'|':'|'#'|'.' )+ ;
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
NOT		:('!');
AMP		:('&');
//STAR		:('*'|'%');
NL		:('\n');
WS 		: ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;

