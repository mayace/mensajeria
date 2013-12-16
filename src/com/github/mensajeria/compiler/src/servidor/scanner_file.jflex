
package com.github.mensajeria.compiler.servidor;

import com.github.mensajeria.compiler.lib.CompilerError;
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import java.lang.StringBuilder;

%%

%public
%class Scanner_file
%cup
%line
%column
%state STRING COMMENT

%{
    /** Errores **/
    public LinkedList<CompilerError> errores=new LinkedList<>();
    private void error(String message) {
            
        Symbol sym=new Symbol(Sym_file.error, yyline, yycolumn, yytext());
        CompilerError e=new CompilerError(message,sym,CompilerError.Type.LEXIC);
        errores.add(e);
    }
    /** String **/
    StringBuilder string=new StringBuilder();

    /** Symbol **/
    private Symbol num_symbol(){
        int type=Sym_file.NERROR;
        
        try{
                Long num=new Long(yytext());
                if(num>=-16599999&&num<=16599999){
                        type=Sym_file.INT;
                } else if (num>=-165999990000L&&num<=165999990000L){
                        // type=Sym_file.LONG;
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




    "<contactos>"       {return symbol(Sym_file.CONTACTOS);}
    "<cuenta>"          {return symbol(Sym_file.CUENTA);}
    "<usuario>"         {return symbol(Sym_file.USUARIO);}
    "<emisor>"          {return symbol(Sym_file.EMISOR);}
    "<mensaje>"         {return symbol(Sym_file.MENSAJE);}
    "<receptor>"        {return symbol(Sym_file.RECEPTOR);}
    "<password>"        {return symbol(Sym_file.PASSWORD);}
    "<fecha>"           {return symbol(Sym_file.FECHA);}
    "<contenido>"       {return symbol(Sym_file.CONTENIDO);}
    



    
    "("                 {return symbol(Sym_file.LP);}
    ")"                 {return symbol(Sym_file.RP);}

    "="                 {return symbol(Sym_file.EQUAL);}
    ";"                 {return symbol(Sym_file.PCOMA);}
    ":"                 {return symbol(Sym_file.DPUNTO);}
    "/"                 {return symbol(Sym_file.DIAGONAL);}

    
    {INT}               {return symbol(Sym_file.INT);}
    {ID}                {return symbol(Sym_file.ID);}
    {ANY}               {return symbol(Sym_file.ANY);}
        
}



.|\n        {error("Illegal character.");}


<<EOF>>     {return symbol(Sym_file.EOF);}