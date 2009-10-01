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

stmt	: select selectList (where constraintList)? (order by orderList)? 
	( EOF | 'xyzzy' );
//stmt	: select selectList;


orderList	:orderList1
		| orderList1 
		oing = ordering {orderingkw=$oing.text;};
orderList1	:okw=	simpleKw 		{okws.add($okw.text);}
 		(
		COMMA
		okw=	simpleKw  		{okws.add($okw.text);}
 		)*
		; 

ordering 	: asc|desc;	 
selectList	:kw=	slkeyword 		{kws.add($kw.text);}
 		(
		COMMA
	 	kw=	slkeyword  		{kws.add($kw.text);}
 		)*;		 
 		
simpleKw	: entity
		| entity DOT attr;
slkeyword	: simpleKw
		| funct LB simpleKw RB;
	
/*constraintList1	: constraint1 ( 
	rel=	logicalOp 		{ constraints.add($rel.text);}
		constraint1)*;*/
lopen		: (LB)*;
ropen		: (RB)*;



constraintList	:constraint1
		(
		rel=    logicalOp  { constraints.add($rel.text);}
		constraint1)*;
constraint1	:
		kl=   lopen {Constraint c1=new Constraint();c1.setBracket($kl.text);constraints.add(c1);}
		constraint
		kr=     ropen {Constraint c=new Constraint();c.setBracket($kr.text); constraints.add(c);};
//lopenrel	: (LB)*;
//ropenrel	: (RB)*;
/*
constraint1     : kl=   lopen   {Constraint c1=new Constraint();c1.setBracket($kl.text);constraints.add(c1);}
                constraint
		(
		rel=    logicalOp               { constraints.add($rel.text);}
		constraint
		)*
                kr=     ropen   {Constraint c=new Constraint();c.setBracket($kr.text); constraints.add(c);};
//		| constraint;
*/
constraint	:
	kw=	simpleKw 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
	op=	compOpt 	{c.setOp($op.text);}   
	val=	genValue 	{c.setValue($val.text); constraints.add(c); 	}               
		|
	kw=	simpleKw 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
	op1=	in 	 	{c.setOp($op1.text);}  
		LB
	val1=	inValue 		{c.setValue($val1.text); constraints.add(c);}
		RB               
		| 
	kw=	simpleKw 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
	op2=	genLike 		{c.setOp($op2.text);} 
	val2=	genValue 		{c.setValue($val2.text); constraints.add(c);}
		|
 	kw=	simpleKw 		{Constraint c= new Constraint(); c.setKey($kw.text);} 
	op3=	between		{c.setOp($op3.text);} 
	val3=	betValue 		{c.setValue($val3.text); constraints.add(c);};                  


compOpt		:EQ
		|LT
		|GT
		|NOT EQ
		|EQ GT
		|EQ LT
		|LT EQ
		|GT EQ
		|LT GT;
genValue	: (VALUE | keyword) VALUE* (DOT (VALUE | keyword))* (compOpt (VALUE | keyword))* ;
//genValue	:dotValue
//		|dotValue compOpt dotValue (AMP dotValue compOpt dotValue)*;
betValue	:genValue and genValue;
inValue		:genValue ( COMMA genValue )*;
logicalOp	:and|or;

entity		:'ads' | 'config' | 'dataset' | 'release' | 'tier' | 'site' | 'block' | 'file' | 'primds' | 'procds' | 'run' | 'lumi' | 'dq' | 'ilumi' | 'phygrp' | 'group'| 'pset'| 'algo' | 'datatype' | 'mcdesc' | 'trigdesc' | 'branch';
attr		:'createdate' | 'moddate' | 'starttime' | 'endtime' | 'createby' | 'modby' | 'name' | 'dataset' | 'version' | 'number' | 'startevnum' | 'endevnum' | 'numevents' | 'numfiles' | 'numlss' | 'totlumi' | 'store' | 'size' | 'release' | 'count' | 'status' | 'type' | 'id' | 'parent' | 'child' | 'tier' | 'def' | 'evnum' | 'era' | 'tag' | 'xsection' | 'hash' | 'content' | 'family'| 'exe' | 'annotation' | 'checksum' ;
funct		:'count' | 'sum' | 'max' | 'min' | 'avg' | 'COUNT' | 'SUM' | 'MAX' | 'MIN' | 'AVG';
//select		:'select' | 'SELECT' | 'find' | 'FIND';
select		:'find' | 'FIND';
and		:'and' | 'AND';
order		:'order' | 'ORDER';
by		:'by' | 'BY';
or		:'or' | 'OR';
in		:'in' | 'IN';
not		:'not' | 'NOT';
like		:'like' | 'LIKE';
notLike		: not like;
genLike		: like | notLike;
asc		:'asc' | 'ASC';
desc		:'desc' | 'DESC';
between		:'between' | 'BETWEEN';
where		:'where' | 'WHERE';
//VALUE		:('a' .. 'z' | '0' .. '9' | 'A' .. 'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'/'|'-'|'_'|':'|'#'|'*'|'%' | '&' | '>' | '<' | '=' | '!')* ;
keyword		: entity | attr | funct | select | and | order | by | or | in | not | like | asc | desc | between | where;
VALUE		:('a' .. 'z' | '0' .. '9' | 'A' .. 'Z' | '/'|'-'|'_'|':'|'#'|'*'|'%' | '&') ('a'..'z'|'A'..'Z'|'0'..'9'|'/'|'-'|'_'|':'|'#'|'*'|'%' | '&')* ;
LB		: '(';
RB		: ')';
COMMA		:',';
DOT		:'.';
GT		:'>';
LT		:'<';
EQ		:'=';
NOT		:'!';
//AMP		:'&';
WS 		: ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;

