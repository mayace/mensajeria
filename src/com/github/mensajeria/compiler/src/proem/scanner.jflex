package com.github.mensajeria.compiler.proem;

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
    private Symbol num_symbol(){
        int type=Sym.NERROR;
        
        try{
                Long num=new Long(yytext());
                if(num>=-16599999&&num<=16599999){
                        type=Sym.INT;
                } else if (num>=-165999990000L&&num<=165999990000L){
                        // type=Sym.LONG;
                }
        }catch (java.lang.NumberFormatException exc){
            error("Número demasiado grande.");
        }
        return new Symbol(type, yyline, yycolumn,yytext());
    }
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
    {SIMPLE_COMMENT}    {}
    "/*"                {yybegin(COMMENT);} 

    "cadena"            {return symbol(Sym.KW_STRING);}
    "entero"            {return symbol(Sym.KW_INT);}
    "bool"              {return symbol(Sym.KW_BOOLEAN);}
    "cola"              {return symbol(Sym.KW_QUEUE);}
    "lista"             {return symbol(Sym.KW_LIST);}

    "es"                {return symbol(Sym.IS);}
    ":="                {return symbol(Sym.EQUAL);}
    ","                 {return symbol(Sym.COMA);}
    "."                 {return symbol(Sym.PUNTO);}
    ";"                 {return symbol(Sym.PCOMA);}
    
    "or"                {return symbol(Sym.OR);}
    "and"               {return symbol(Sym.AND);}
    "not"               {return symbol(Sym.NOT);}
    
    "=="                {return symbol(Sym.DEQUAL);}
    "!="                {return symbol(Sym.NEQUAL);}
    ">"                 {return symbol(Sym.BTHAN);}
    "<"                 {return symbol(Sym.LTHAN);}
    ">="                {return symbol(Sym.BETHAN);}
    "<="                {return symbol(Sym.LETHAN);}
    
    "("                 {return symbol(Sym.LP);}
    ")"                 {return symbol(Sym.RP);}
    "-"                 {return symbol(Sym.MINUS);}
    "+"                 {return symbol(Sym.PLUS);}
    "*"                 {return symbol(Sym.MULTI);}
    "/"                 {return symbol(Sym.DIV);}

    "mientras"          {return symbol(Sym.WHILE);}
    "para"              {return symbol(Sym.FOR);}
    "to"                {return symbol(Sym.TO);}
    "espera"            {return symbol(Sym.WAIT);}
    "si"                {return symbol(Sym.IF);}
    "sino"              {return symbol(Sym.ELSE);}
    
    "enviar"            {return symbol(Sym.SEND);}
    "pirntln"           {return symbol(Sym.PRINTLN);}

    

    \"                  {string.setLength(0); yybegin(STRING);}
    {INT}               {return num_symbol();}
    {BOOLEAN}           {return symbol(Sym.BOOLEAN);}
    {ID}                {return symbol(Sym.ID);}
    
        
}

<STRING>{
    \"          {
                    yybegin(YYINITIAL);
                    return symbol(Sym.STRING,string.toString());
                }
  [^\n\r\"\\]+  { string.append( yytext() ); }
  \\t           { string.append('\t'); }
  \\n           { string.append('\n'); }
  \\r           { string.append('\r'); }
  \\\"          { string.append('\"'); }
  \\            { string.append('\\'); }
}

<COMMENT>{
    "*/"    {yybegin(YYINITIAL);}
    .|\n    {}
}


.|\n        {error("Illegal character.");}


<<EOF>>     {return symbol(Sym.EOF);}