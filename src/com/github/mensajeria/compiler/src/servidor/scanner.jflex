package com.github.mensajeria.compiler.servidor;

import com.github.mensajeria.compiler.Err;
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import java.lang.StringBuilder;

%%

%public
%class Scanner
%cup
%line
%column
%state STRING COMMENT

%{
    /** Errores **/
    public LinkedList<Err> errores=new LinkedList<>();
    private void error(String message) {
            
        Symbol sym=new Symbol(Sym.error, yyline, yycolumn, yytext());
        Err e=new Err(message,sym,Err.TIPO.LEXICO);
        errores.add(e);
    }
    /** String **/
    StringBuilder string=new StringBuilder();

    /** Symbol **/
    
    private Symbol symbol(int type) {
            return new Symbol(type, yyline, yycolumn,yytext());
    }
    private Symbol symbol(int type, Object value) {
            return new Symbol(type, yyline, yycolumn, value);
    }
%}

NEWLINE         =        \n|\r|\r\n
SPACE           =        [ \t]|{NEWLINE}
DIGIT           =        [0-9]
INT             =        "-"?{DIGIT}+
ID              =        [:jletter:] [:jletterdigit:]*
BOOLEAN         =        "true"|"false"

%%



<YYINITIAL>
{
    {SPACE}             {}

    "buscar"           {return symbol(Sym.BUSCAR);}
    "remitente"        {return symbol(Sym.REMITENTE);}
    "receptor"         {return symbol(Sym.RECEPTOR);}
    "contiene"         {return symbol(Sym.CONTIENE);}
    "mensaje"          {return symbol(Sym.MENSAJE);}
    "%"                {return symbol(Sym.PERCENT);}
    "?"                {return symbol(Sym.CLOSEQ);}
    "#"                {return symbol(Sym.NUMERAL);}

    "(login)"           {return symbol(Sym.ST_LOGIN);}
    "(cuenta)"          {return symbol(Sym.ST_CUENTA);}
    "(usuario)"         {return symbol(Sym.ST_USUARIO);}
    "(password)"        {return symbol(Sym.ST_PASSWORD);}
    
    "or"                {return symbol(Sym.OR);}
    "and"               {return symbol(Sym.AND);}

    "{"                 {return symbol(Sym.LL1);}
    "}"                 {return symbol(Sym.LL2);}

    "="                 {return symbol(Sym.EQUAL);}
    ";"                 {return symbol(Sym.PCOMA);}

    
    {ID}                {return symbol(Sym.ID);}
}


.|\n        {error("Illegal character.");}


<<EOF>>     {return symbol(Sym.EOF);}
