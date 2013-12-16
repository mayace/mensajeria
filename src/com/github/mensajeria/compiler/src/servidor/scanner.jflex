package com.github.mensajeria.compiler.servidor;

import com.github.mensajeria.compiler.lib.CompilerError;
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
    public LinkedList<CompilerError> errores=new LinkedList<>();
    private void error(String message) {
            
        Symbol sym=new Symbol(Sym.error, yyline, yycolumn, yytext());
        CompilerError e=new CompilerError(message,sym,CompilerError.Type.LEXIC);
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
ANY             =        [^\n\r\t ]

SIMPLE_COMMENT  =        "//"[^\*\n\r]*{NEWLINE}


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

    
    "OR"                {return symbol(Sym.OR);}
    "AND"               {return symbol(Sym.AND);}


    
    "("                 {return symbol(Sym.LP);}
    ")"                 {return symbol(Sym.RP);}
    "{"                 {return symbol(Sym.LL1);}
    "}"                 {return symbol(Sym.LL2);}

    "="                 {return symbol(Sym.EQUAL);}
    ";"                 {return symbol(Sym.PCOMA);}
    \"                  {return symbol(Sym.QUOTE);}

    
    {ID}                {return symbol(Sym.ID);}
    {ANY}               {return symbol(Sym.ANY);}
        
}



.|\n        {error("Illegal character.");}


<<EOF>>     {return symbol(Sym.EOF);}