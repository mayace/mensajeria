
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Sun Dec 15 11:01:13 CST 2013
//----------------------------------------------------

package com.github.mensajeria.compiler.servidor;

import com.github.mensajeria.compiler.Attr;
import com.github.mensajeria.compiler.Err;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.lang.Thread.State;
import java.awt.Dimension;
import java.awt.Point;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Sun Dec 15 11:01:13 CST 2013
  */
public class Parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public Parser() {super();}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\050\000\002\015\003\000\002\002\004\000\002\015" +
    "\002\000\002\002\003\000\002\003\004\000\002\003\003" +
    "\000\002\004\003\000\002\005\010\000\002\005\006\000" +
    "\002\005\005\000\002\007\003\000\002\007\002\000\002" +
    "\010\004\000\002\010\003\000\002\011\003\000\002\011" +
    "\003\000\002\006\003\000\002\012\003\000\002\013\004" +
    "\000\002\013\003\000\002\014\005\000\002\016\005\000" +
    "\002\016\003\000\002\017\005\000\002\017\003\000\002" +
    "\020\005\000\002\020\005\000\002\020\005\000\002\020" +
    "\005\000\002\021\003\000\002\021\006\000\002\022\005" +
    "\000\002\023\003\000\002\023\002\000\002\024\004\000" +
    "\002\024\003\000\002\025\003\000\002\025\003\000\002" +
    "\025\003\000\002\025\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\024\000\006\002\uffff\004\006\001\002\000\004\002" +
    "\001\001\002\000\004\002\026\001\002\000\004\006\013" +
    "\001\002\000\006\002\ufffc\004\ufffc\001\002\000\006\002" +
    "\ufffb\004\ufffb\001\002\000\006\002\ufffe\004\006\001\002" +
    "\000\006\002\ufffd\004\ufffd\001\002\000\004\005\014\001" +
    "\002\000\016\002\ufff8\004\ufff8\006\015\007\016\010\ufff8" +
    "\027\ufff8\001\002\000\012\002\ufff9\004\ufff9\010\ufff9\027" +
    "\ufff9\001\002\000\010\004\006\010\ufff6\027\017\001\002" +
    "\000\010\004\ufff2\010\ufff2\027\ufff2\001\002\000\010\004" +
    "\006\010\ufff7\027\017\001\002\000\004\010\024\001\002" +
    "\000\010\004\ufff3\010\ufff3\027\ufff3\001\002\000\010\004" +
    "\ufff4\010\ufff4\027\ufff4\001\002\000\012\002\ufffa\004\ufffa" +
    "\010\ufffa\027\ufffa\001\002\000\010\004\ufff5\010\ufff5\027" +
    "\ufff5\001\002\000\004\002\000\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\024\000\014\002\003\003\010\004\006\005\007\015" +
    "\004\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\006\004" +
    "\011\005\007\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\012\005\021\007" +
    "\020\010\017\011\022\001\001\000\002\001\001\000\006" +
    "\005\021\011\024\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


 
        
        
        public LinkedList<Err> errores=new LinkedList<>();
        
        
        public void report_error(String message, Object info){
                Err e=new Err(message,null,null);
                if(info instanceof java_cup.runtime.Symbol){
                        e.setSym((java_cup.runtime.Symbol)info);
                }
                errores.add(e);
        }
        public void report_fatal_error(String message,Object info){
                report_error(message,info);
                errores.getLast().println();
        }

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$Parser$actions {


  private final Parser parser;

  /** Constructor */
  CUP$Parser$actions(Parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Parser$result;

      /* select the action based on the action number */
      switch (CUP$Parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 39: // stmt_string ::= ANY 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt_string",19, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 38: // stmt_string ::= CLOSEQ 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt_string",19, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 37: // stmt_string ::= PERCENT 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt_string",19, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 36: // stmt_string ::= NUMERAL 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt_string",19, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35: // list_stmt_string ::= stmt_string 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("list_stmt_string",18, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34: // list_stmt_string ::= list_stmt_string stmt_string 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("list_stmt_string",18, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33: // body_string ::= 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("body_string",17, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32: // body_string ::= list_stmt_string 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("body_string",17, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // string ::= QUOTE body_string QUOTE 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("string",16, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // val ::= CONTIENE LP string RP 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("val",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // val ::= string 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("val",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // expr2 ::= MENSAJE EQUAL val 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr2",14, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // expr2 ::= RECEPTOR EQUAL val 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr2",14, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // expr2 ::= REMITENTE EQUAL val 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr2",14, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // expr2 ::= FECHA EQUAL val 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr2",14, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // expr1 ::= expr2 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr1",13, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // expr1 ::= expr1 AND expr2 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr1",13, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // expr ::= expr1 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",12, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // expr ::= expr OR expr1 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",12, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // stmt_busqueda ::= BUSCAR expr PCOMA 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt_busqueda",10, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // list_stmt_busqueda ::= stmt_busqueda 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("list_stmt_busqueda",9, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // list_stmt_busqueda ::= list_stmt_busqueda stmt_busqueda 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("list_stmt_busqueda",9, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // body_busqueda ::= list_stmt_busqueda 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("body_busqueda",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // busqueda ::= body_busqueda 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("busqueda",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // stmt_struct ::= ANY 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt_struct",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // stmt_struct ::= struct 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt_struct",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // list_stmt_struct ::= stmt_struct 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("list_stmt_struct",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // list_stmt_struct ::= list_stmt_struct stmt_struct 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("list_stmt_struct",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // body_struct ::= 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("body_struct",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // body_struct ::= list_stmt_struct 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("body_struct",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // struct ::= LP ID RP 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("struct",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // struct ::= LP ID RP ID 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("struct",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // struct ::= LP ID RP LL1 body_struct LL2 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("struct",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // stmt ::= struct 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // stmt_list ::= stmt 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt_list",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // stmt_list ::= stmt_list stmt 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt_list",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // input ::= stmt_list 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("input",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // begin ::= 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("begin",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= begin EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Attr start_val = (Attr)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		RESULT = start_val;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Parser$parser.done_parsing();
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // begin ::= input 
            {
              Attr RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("begin",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

