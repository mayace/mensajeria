package com.github.proem.compiler;

/* The following code was generated by JFlex 1.4.3 on 9/12/13 10:59 AM */



import java_cup.runtime.Symbol;
import java.util.LinkedList;
import java.lang.StringBuilder;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 9/12/13 10:59 AM from the specification file
 * <tt>D:/dev/NetBeansProjects/ProEM/src/com/github/proem/compiler/src/scanner.jflex</tt>
 */
public class Scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRING = 2;
  public static final int YYINITIAL = 0;
  public static final int COMMENT = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\7\1\3\1\1\2\0\1\2\16\7\4\0\1\3\1\43\1\54"+
    "\1\0\1\6\3\0\1\46\1\47\1\21\1\50\1\32\1\5\1\33"+
    "\1\20\12\4\1\30\1\34\1\45\1\31\1\44\2\0\1\37\2\6"+
    "\1\41\11\6\1\40\1\35\2\6\1\36\1\6\1\42\6\6\1\0"+
    "\1\55\2\0\1\6\1\0\1\15\1\26\1\22\1\23\1\13\1\14"+
    "\2\6\1\27\2\6\1\16\1\51\1\24\1\25\1\52\1\6\1\11"+
    "\1\17\1\10\1\12\1\53\4\6\4\0\41\7\2\0\4\6\4\0"+
    "\1\6\2\0\1\7\7\0\1\6\4\0\1\6\5\0\27\6\1\0"+
    "\37\6\1\0\u01ca\6\4\0\14\6\16\0\5\6\7\0\1\6\1\0"+
    "\1\6\21\0\160\7\5\6\1\0\2\6\2\0\4\6\10\0\1\6"+
    "\1\0\3\6\1\0\1\6\1\0\24\6\1\0\123\6\1\0\213\6"+
    "\1\0\5\7\2\0\236\6\11\0\46\6\2\0\1\6\7\0\47\6"+
    "\11\0\55\7\1\0\1\7\1\0\2\7\1\0\2\7\1\0\1\7"+
    "\10\0\33\6\5\0\3\6\15\0\4\7\7\0\1\6\4\0\13\7"+
    "\5\0\53\6\37\7\4\0\2\6\1\7\143\6\1\0\1\6\10\7"+
    "\1\0\6\7\2\6\2\7\1\0\4\7\2\6\12\7\3\6\2\0"+
    "\1\6\17\0\1\7\1\6\1\7\36\6\33\7\2\0\131\6\13\7"+
    "\1\6\16\0\12\7\41\6\11\7\2\6\4\0\1\6\5\0\26\6"+
    "\4\7\1\6\11\7\1\6\3\7\1\6\5\7\22\0\31\6\3\7"+
    "\244\0\4\7\66\6\3\7\1\6\22\7\1\6\7\7\12\6\2\7"+
    "\2\0\12\7\1\0\7\6\1\0\7\6\1\0\3\7\1\0\10\6"+
    "\2\0\2\6\2\0\26\6\1\0\7\6\1\0\1\6\3\0\4\6"+
    "\2\0\1\7\1\6\7\7\2\0\2\7\2\0\3\7\1\6\10\0"+
    "\1\7\4\0\2\6\1\0\3\6\2\7\2\0\12\7\4\6\7\0"+
    "\1\6\5\0\3\7\1\0\6\6\4\0\2\6\2\0\26\6\1\0"+
    "\7\6\1\0\2\6\1\0\2\6\1\0\2\6\2\0\1\7\1\0"+
    "\5\7\4\0\2\7\2\0\3\7\3\0\1\7\7\0\4\6\1\0"+
    "\1\6\7\0\14\7\3\6\1\7\13\0\3\7\1\0\11\6\1\0"+
    "\3\6\1\0\26\6\1\0\7\6\1\0\2\6\1\0\5\6\2\0"+
    "\1\7\1\6\10\7\1\0\3\7\1\0\3\7\2\0\1\6\17\0"+
    "\2\6\2\7\2\0\12\7\1\0\1\6\17\0\3\7\1\0\10\6"+
    "\2\0\2\6\2\0\26\6\1\0\7\6\1\0\2\6\1\0\5\6"+
    "\2\0\1\7\1\6\7\7\2\0\2\7\2\0\3\7\10\0\2\7"+
    "\4\0\2\6\1\0\3\6\2\7\2\0\12\7\1\0\1\6\20\0"+
    "\1\7\1\6\1\0\6\6\3\0\3\6\1\0\4\6\3\0\2\6"+
    "\1\0\1\6\1\0\2\6\3\0\2\6\3\0\3\6\3\0\14\6"+
    "\4\0\5\7\3\0\3\7\1\0\4\7\2\0\1\6\6\0\1\7"+
    "\16\0\12\7\11\0\1\6\7\0\3\7\1\0\10\6\1\0\3\6"+
    "\1\0\27\6\1\0\12\6\1\0\5\6\3\0\1\6\7\7\1\0"+
    "\3\7\1\0\4\7\7\0\2\7\1\0\2\6\6\0\2\6\2\7"+
    "\2\0\12\7\22\0\2\7\1\0\10\6\1\0\3\6\1\0\27\6"+
    "\1\0\12\6\1\0\5\6\2\0\1\7\1\6\7\7\1\0\3\7"+
    "\1\0\4\7\7\0\2\7\7\0\1\6\1\0\2\6\2\7\2\0"+
    "\12\7\1\0\2\6\17\0\2\7\1\0\10\6\1\0\3\6\1\0"+
    "\51\6\2\0\1\6\7\7\1\0\3\7\1\0\4\7\1\6\10\0"+
    "\1\7\10\0\2\6\2\7\2\0\12\7\12\0\6\6\2\0\2\7"+
    "\1\0\22\6\3\0\30\6\1\0\11\6\1\0\1\6\2\0\7\6"+
    "\3\0\1\7\4\0\6\7\1\0\1\7\1\0\10\7\22\0\2\7"+
    "\15\0\60\6\1\7\2\6\7\7\4\0\10\6\10\7\1\0\12\7"+
    "\47\0\2\6\1\0\1\6\2\0\2\6\1\0\1\6\2\0\1\6"+
    "\6\0\4\6\1\0\7\6\1\0\3\6\1\0\1\6\1\0\1\6"+
    "\2\0\2\6\1\0\4\6\1\7\2\6\6\7\1\0\2\7\1\6"+
    "\2\0\5\6\1\0\1\6\1\0\6\7\2\0\12\7\2\0\2\6"+
    "\42\0\1\6\27\0\2\7\6\0\12\7\13\0\1\7\1\0\1\7"+
    "\1\0\1\7\4\0\2\7\10\6\1\0\44\6\4\0\24\7\1\0"+
    "\2\7\5\6\13\7\1\0\44\7\11\0\1\7\71\0\53\6\24\7"+
    "\1\6\12\7\6\0\6\6\4\7\4\6\3\7\1\6\3\7\2\6"+
    "\7\7\3\6\4\7\15\6\14\7\1\6\17\7\2\0\46\6\12\0"+
    "\53\6\1\0\1\6\3\0\u0149\6\1\0\4\6\2\0\7\6\1\0"+
    "\1\6\1\0\4\6\2\0\51\6\1\0\4\6\2\0\41\6\1\0"+
    "\4\6\2\0\7\6\1\0\1\6\1\0\4\6\2\0\17\6\1\0"+
    "\71\6\1\0\4\6\2\0\103\6\2\0\3\7\40\0\20\6\20\0"+
    "\125\6\14\0\u026c\6\2\0\21\6\1\0\32\6\5\0\113\6\3\0"+
    "\3\6\17\0\15\6\1\0\4\6\3\7\13\0\22\6\3\7\13\0"+
    "\22\6\2\7\14\0\15\6\1\0\3\6\1\0\2\7\14\0\64\6"+
    "\40\7\3\0\1\6\3\0\2\6\1\7\2\0\12\7\41\0\3\7"+
    "\2\0\12\7\6\0\130\6\10\0\51\6\1\7\1\6\5\0\106\6"+
    "\12\0\35\6\3\0\14\7\4\0\14\7\12\0\12\7\36\6\2\0"+
    "\5\6\13\0\54\6\4\0\21\7\7\6\2\7\6\0\12\7\46\0"+
    "\27\6\5\7\4\0\65\6\12\7\1\0\35\7\2\0\13\7\6\0"+
    "\12\7\15\0\1\6\130\0\5\7\57\6\21\7\7\6\4\0\12\7"+
    "\21\0\11\7\14\0\3\7\36\6\12\7\3\0\2\6\12\7\6\0"+
    "\46\6\16\7\14\0\44\6\24\7\10\0\12\7\3\0\3\6\12\7"+
    "\44\6\122\0\3\7\1\0\25\7\4\6\1\7\4\6\1\7\15\0"+
    "\300\6\47\7\25\0\4\7\u0116\6\2\0\6\6\2\0\46\6\2\0"+
    "\6\6\2\0\10\6\1\0\1\6\1\0\1\6\1\0\1\6\1\0"+
    "\37\6\2\0\65\6\1\0\7\6\1\0\1\6\3\0\3\6\1\0"+
    "\7\6\3\0\4\6\2\0\6\6\4\0\15\6\5\0\3\6\1\0"+
    "\7\6\16\0\5\7\32\0\5\7\20\0\2\6\23\0\1\6\13\0"+
    "\5\7\5\0\6\7\1\0\1\6\15\0\1\6\20\0\15\6\3\0"+
    "\32\6\26\0\15\7\4\0\1\7\3\0\14\7\21\0\1\6\4\0"+
    "\1\6\2\0\12\6\1\0\1\6\3\0\5\6\6\0\1\6\1\0"+
    "\1\6\1\0\1\6\1\0\4\6\1\0\13\6\2\0\4\6\5\0"+
    "\5\6\4\0\1\6\21\0\51\6\u0a77\0\57\6\1\0\57\6\1\0"+
    "\205\6\6\0\4\6\3\7\16\0\46\6\12\0\66\6\11\0\1\6"+
    "\17\0\1\7\27\6\11\0\7\6\1\0\7\6\1\0\7\6\1\0"+
    "\7\6\1\0\7\6\1\0\7\6\1\0\7\6\1\0\7\6\1\0"+
    "\40\7\57\0\1\6\u01d5\0\3\6\31\0\11\6\6\7\1\0\5\6"+
    "\2\0\5\6\4\0\126\6\2\0\2\7\2\0\3\6\1\0\132\6"+
    "\1\0\4\6\5\0\51\6\3\0\136\6\21\0\33\6\65\0\20\6"+
    "\u0200\0\u19b6\6\112\0\u51cc\6\64\0\u048d\6\103\0\56\6\2\0\u010d\6"+
    "\3\0\20\6\12\7\2\6\24\0\57\6\1\7\14\0\2\7\1\0"+
    "\31\6\10\0\120\6\2\7\45\0\11\6\2\0\147\6\2\0\4\6"+
    "\1\0\2\6\16\0\12\6\120\0\10\6\1\7\3\6\1\7\4\6"+
    "\1\7\27\6\5\7\20\0\1\6\7\0\64\6\14\0\2\7\62\6"+
    "\21\7\13\0\12\7\6\0\22\7\6\6\3\0\1\6\4\0\12\7"+
    "\34\6\10\7\2\0\27\6\15\7\14\0\35\6\3\0\4\7\57\6"+
    "\16\7\16\0\1\6\12\7\46\0\51\6\16\7\11\0\3\6\1\7"+
    "\10\6\2\7\2\0\12\7\6\0\27\6\3\0\1\6\1\7\4\0"+
    "\60\6\1\7\1\6\3\7\2\6\2\7\5\6\2\7\1\6\1\7"+
    "\1\6\30\0\3\6\43\0\6\6\2\0\6\6\2\0\6\6\11\0"+
    "\7\6\1\0\7\6\221\0\43\6\10\7\1\0\2\7\2\0\12\7"+
    "\6\0\u2ba4\6\14\0\27\6\4\0\61\6\u2104\0\u012e\6\2\0\76\6"+
    "\2\0\152\6\46\0\7\6\14\0\5\6\5\0\1\6\1\7\12\6"+
    "\1\0\15\6\1\0\5\6\1\0\1\6\1\0\2\6\1\0\2\6"+
    "\1\0\154\6\41\0\u016b\6\22\0\100\6\2\0\66\6\50\0\15\6"+
    "\3\0\20\7\20\0\7\7\14\0\2\6\30\0\3\6\31\0\1\6"+
    "\6\0\5\6\1\0\207\6\2\0\1\7\4\0\1\6\13\0\12\7"+
    "\7\0\32\6\4\0\1\6\1\0\32\6\13\0\131\6\3\0\6\6"+
    "\2\0\6\6\2\0\6\6\2\0\3\6\3\0\2\6\3\0\2\6"+
    "\22\0\3\7\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\2\2\1\3\1\4\6\5\1\6\1\7"+
    "\2\5\2\1\1\10\1\11\1\12\3\5\1\1\1\13"+
    "\1\14\1\15\1\16\1\17\2\5\1\20\1\21\1\22"+
    "\1\23\1\2\1\5\1\24\1\25\3\5\1\26\1\0"+
    "\1\27\3\5\1\30\1\31\1\32\2\5\1\33\1\34"+
    "\1\35\2\5\1\36\1\37\1\40\1\41\1\42\12\5"+
    "\1\43\1\44\2\5\1\45\4\5\1\46\1\5\1\47"+
    "\1\50\1\5\1\51\3\5\1\52\2\5\1\53\1\54"+
    "\1\55\1\56\2\5\1\57";

  private static int [] zzUnpackAction() {
    int [] result = new int[104];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\56\0\134\0\212\0\212\0\270\0\346\0\346"+
    "\0\u0114\0\u0142\0\u0170\0\u019e\0\u01cc\0\u01fa\0\u0228\0\212"+
    "\0\u0256\0\u0284\0\u02b2\0\u02e0\0\212\0\212\0\212\0\u030e"+
    "\0\u033c\0\u036a\0\u0398\0\u03c6\0\u03f4\0\212\0\212\0\212"+
    "\0\u0422\0\u0450\0\212\0\u047e\0\212\0\u04ac\0\u04da\0\u0508"+
    "\0\u0114\0\u0536\0\u0564\0\u0592\0\u05c0\0\u05ee\0\u061c\0\212"+
    "\0\u064a\0\u0678\0\u06a6\0\212\0\212\0\u0114\0\u06d4\0\u0702"+
    "\0\212\0\212\0\212\0\u0730\0\u075e\0\212\0\212\0\212"+
    "\0\212\0\212\0\u078c\0\u07ba\0\u07e8\0\u0816\0\u0844\0\u0872"+
    "\0\u08a0\0\u08ce\0\u08fc\0\u092a\0\u0114\0\u0114\0\u0958\0\u0986"+
    "\0\u0114\0\u09b4\0\u09e2\0\u0a10\0\u0a3e\0\u0114\0\u0a6c\0\u0114"+
    "\0\u0114\0\u0a9a\0\u0114\0\u0ac8\0\u0af6\0\u0b24\0\u0114\0\u0b52"+
    "\0\u0b80\0\u0114\0\u0114\0\u0114\0\u0114\0\u0bae\0\u0bdc\0\u0114";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[104];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\1\5\1\6\1\5\1\7\1\10\1\11\1\4"+
    "\1\12\2\11\1\13\1\14\1\11\1\15\1\16\1\17"+
    "\1\20\1\21\3\11\1\22\1\11\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\1\11\1\31\1\32\2\11\1\33"+
    "\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\11"+
    "\1\43\1\4\1\44\2\4\51\44\1\45\1\46\21\5"+
    "\1\47\34\5\57\0\1\5\60\0\1\7\55\0\1\11"+
    "\1\0\12\11\2\0\6\11\5\0\6\11\6\0\3\11"+
    "\6\0\1\11\1\0\3\11\1\50\6\11\2\0\3\11"+
    "\1\51\2\11\5\0\6\11\6\0\3\11\6\0\1\11"+
    "\1\0\11\11\1\52\2\0\2\11\1\53\3\11\5\0"+
    "\6\11\6\0\3\11\6\0\1\11\1\0\7\11\1\54"+
    "\2\11\2\0\6\11\5\0\6\11\6\0\3\11\6\0"+
    "\1\11\1\0\12\11\2\0\5\11\1\55\5\0\6\11"+
    "\6\0\3\11\6\0\1\11\1\0\12\11\2\0\5\11"+
    "\1\56\5\0\6\11\6\0\3\11\22\0\1\57\1\60"+
    "\40\0\1\11\1\0\7\11\1\61\2\11\2\0\3\11"+
    "\1\62\2\11\5\0\6\11\6\0\3\11\6\0\1\11"+
    "\1\0\12\11\2\0\3\11\1\63\2\11\5\0\6\11"+
    "\6\0\3\11\33\0\1\64\55\0\1\65\30\0\1\11"+
    "\1\0\12\11\2\0\6\11\5\0\1\11\1\66\4\11"+
    "\6\0\3\11\6\0\1\11\1\0\12\11\2\0\6\11"+
    "\5\0\3\11\1\67\2\11\6\0\3\11\6\0\1\11"+
    "\1\0\12\11\2\0\6\11\5\0\1\70\5\11\6\0"+
    "\3\11\33\0\1\71\55\0\1\72\55\0\1\73\30\0"+
    "\1\11\1\0\12\11\2\0\5\11\1\74\5\0\6\11"+
    "\6\0\3\11\6\0\1\11\1\0\7\11\1\75\2\11"+
    "\2\0\6\11\5\0\6\11\6\0\3\11\2\0\1\44"+
    "\2\0\51\44\12\0\1\76\1\77\12\0\1\100\27\0"+
    "\1\101\21\0\1\102\41\0\1\11\1\0\4\11\1\103"+
    "\5\11\2\0\6\11\5\0\6\11\6\0\3\11\6\0"+
    "\1\11\1\0\12\11\2\0\6\11\5\0\6\11\6\0"+
    "\1\11\1\104\1\11\6\0\1\11\1\0\2\11\1\105"+
    "\7\11\2\0\6\11\5\0\6\11\6\0\2\11\1\106"+
    "\6\0\1\11\1\0\10\11\1\107\1\11\2\0\6\11"+
    "\5\0\6\11\6\0\3\11\6\0\1\11\1\0\11\11"+
    "\1\110\2\0\6\11\5\0\6\11\6\0\3\11\6\0"+
    "\1\11\1\0\12\11\2\0\2\11\1\111\3\11\5\0"+
    "\6\11\6\0\3\11\2\0\1\57\1\5\1\6\16\57"+
    "\1\0\34\57\4\0\1\11\1\0\12\11\2\0\1\11"+
    "\1\112\4\11\5\0\6\11\6\0\3\11\6\0\1\11"+
    "\1\0\10\11\1\113\1\11\2\0\6\11\5\0\6\11"+
    "\6\0\3\11\6\0\1\11\1\0\12\11\2\0\3\11"+
    "\1\114\2\11\5\0\6\11\6\0\3\11\6\0\1\11"+
    "\1\0\12\11\2\0\6\11\5\0\4\11\1\115\1\11"+
    "\6\0\3\11\6\0\1\11\1\0\12\11\2\0\6\11"+
    "\5\0\5\11\1\116\6\0\3\11\6\0\1\11\1\0"+
    "\5\11\1\117\4\11\2\0\6\11\5\0\6\11\6\0"+
    "\3\11\6\0\1\11\1\0\3\11\1\120\6\11\2\0"+
    "\6\11\5\0\6\11\6\0\3\11\6\0\1\11\1\0"+
    "\5\11\1\121\4\11\2\0\6\11\5\0\6\11\6\0"+
    "\3\11\6\0\1\11\1\0\5\11\1\122\4\11\2\0"+
    "\6\11\5\0\6\11\6\0\3\11\6\0\1\11\1\0"+
    "\5\11\1\123\4\11\2\0\6\11\5\0\6\11\6\0"+
    "\3\11\6\0\1\11\1\0\12\11\2\0\5\11\1\124"+
    "\5\0\6\11\6\0\3\11\6\0\1\11\1\0\11\11"+
    "\1\103\2\0\6\11\5\0\6\11\6\0\3\11\6\0"+
    "\1\11\1\0\2\11\1\125\7\11\2\0\6\11\5\0"+
    "\6\11\6\0\3\11\6\0\1\11\1\0\12\11\2\0"+
    "\3\11\1\126\2\11\5\0\6\11\6\0\3\11\6\0"+
    "\1\11\1\0\5\11\1\127\4\11\2\0\6\11\5\0"+
    "\6\11\6\0\3\11\6\0\1\11\1\0\7\11\1\130"+
    "\2\11\2\0\6\11\5\0\6\11\6\0\3\11\6\0"+
    "\1\11\1\0\10\11\1\131\1\11\2\0\6\11\5\0"+
    "\6\11\6\0\3\11\6\0\1\11\1\0\12\11\2\0"+
    "\2\11\1\132\3\11\5\0\6\11\6\0\3\11\6\0"+
    "\1\11\1\0\7\11\1\133\2\11\2\0\6\11\5\0"+
    "\6\11\6\0\3\11\6\0\1\11\1\0\3\11\1\134"+
    "\6\11\2\0\6\11\5\0\6\11\6\0\3\11\6\0"+
    "\1\11\1\0\3\11\1\135\6\11\2\0\6\11\5\0"+
    "\6\11\6\0\3\11\6\0\1\11\1\0\7\11\1\136"+
    "\2\11\2\0\6\11\5\0\6\11\6\0\3\11\6\0"+
    "\1\11\1\0\7\11\1\137\2\11\2\0\6\11\5\0"+
    "\6\11\6\0\3\11\6\0\1\11\1\0\12\11\2\0"+
    "\2\11\1\140\3\11\5\0\6\11\6\0\3\11\6\0"+
    "\1\11\1\0\2\11\1\141\7\11\2\0\6\11\5\0"+
    "\6\11\6\0\3\11\6\0\1\11\1\0\7\11\1\142"+
    "\2\11\2\0\6\11\5\0\6\11\6\0\3\11\6\0"+
    "\1\11\1\0\12\11\2\0\3\11\1\143\2\11\5\0"+
    "\6\11\6\0\3\11\6\0\1\11\1\0\3\11\1\144"+
    "\6\11\2\0\6\11\5\0\6\11\6\0\3\11\6\0"+
    "\1\11\1\0\7\11\1\145\2\11\2\0\6\11\5\0"+
    "\6\11\6\0\3\11\6\0\1\11\1\0\3\11\1\146"+
    "\6\11\2\0\6\11\5\0\6\11\6\0\3\11\6\0"+
    "\1\11\1\0\7\11\1\147\2\11\2\0\6\11\5\0"+
    "\6\11\6\0\3\11\6\0\1\11\1\0\11\11\1\150"+
    "\2\0\6\11\5\0\6\11\6\0\3\11\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3082];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\2\11\12\1\1\11\4\1\3\11\6\1\3\11"+
    "\2\1\1\11\1\1\1\11\11\1\1\0\1\11\3\1"+
    "\2\11\3\1\3\11\2\1\5\11\46\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[104];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    /** Errores **/
    public LinkedList<compiler.lib.Error> errores=new LinkedList<>();
    private void error(String message) {
            
        Symbol sym=new Symbol(Sym.error, yyline, yycolumn, yytext());
        compiler.lib.Error e=new compiler.lib.Error(message,sym,compiler.lib.Error.Type.LEXIC);
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


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Scanner(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2206) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 41: 
          { return symbol(Sym.FOR);
          }
        case 48: break;
        case 45: 
          { return symbol(Sym.SEND);
          }
        case 49: break;
        case 40: 
          { return symbol(Sym.KW_BOOLEAN);
          }
        case 50: break;
        case 9: 
          { return symbol(Sym.PUNTO);
          }
        case 51: break;
        case 22: 
          { return symbol(Sym.IF);
          }
        case 52: break;
        case 26: 
          { return symbol(Sym.OR);
          }
        case 53: break;
        case 30: 
          { string.append('\t');
          }
        case 54: break;
        case 16: 
          { string.setLength(0); yybegin(STRING);
          }
        case 55: break;
        case 44: 
          { return symbol(Sym.KW_INT);
          }
        case 56: break;
        case 10: 
          { return symbol(Sym.PCOMA);
          }
        case 57: break;
        case 29: 
          { return symbol(Sym.LETHAN);
          }
        case 58: break;
        case 14: 
          { return symbol(Sym.P2);
          }
        case 59: break;
        case 7: 
          { return symbol(Sym.MULTI);
          }
        case 60: break;
        case 5: 
          { return symbol(Sym.ID);
          }
        case 61: break;
        case 43: 
          { return symbol(Sym.WAIT);
          }
        case 62: break;
        case 1: 
          { error("Illegal character.");
          }
        case 63: break;
        case 19: 
          { string.append('\\');
          }
        case 64: break;
        case 31: 
          { string.append('\r');
          }
        case 65: break;
        case 34: 
          { yybegin(YYINITIAL);
          }
        case 66: break;
        case 37: 
          { return symbol(Sym.BOOLEAN);
          }
        case 67: break;
        case 13: 
          { return symbol(Sym.P1);
          }
        case 68: break;
        case 28: 
          { return symbol(Sym.BETHAN);
          }
        case 69: break;
        case 23: 
          { yybegin(COMMENT);
          }
        case 70: break;
        case 11: 
          { return symbol(Sym.BTHAN);
          }
        case 71: break;
        case 42: 
          { return symbol(Sym.KW_LIST);
          }
        case 72: break;
        case 12: 
          { return symbol(Sym.LTHAN);
          }
        case 73: break;
        case 33: 
          { string.append('\"');
          }
        case 74: break;
        case 27: 
          { return symbol(Sym.NEQUAL);
          }
        case 75: break;
        case 20: 
          { return symbol(Sym.TO);
          }
        case 76: break;
        case 21: 
          { return symbol(Sym.IS);
          }
        case 77: break;
        case 47: 
          { return symbol(Sym.WHILE);
          }
        case 78: break;
        case 46: 
          { return symbol(Sym.KW_STRING);
          }
        case 79: break;
        case 24: 
          { return symbol(Sym.EQUAL);
          }
        case 80: break;
        case 36: 
          { return symbol(Sym.NOT);
          }
        case 81: break;
        case 35: 
          { return symbol(Sym.AND);
          }
        case 82: break;
        case 6: 
          { return symbol(Sym.DIV);
          }
        case 83: break;
        case 18: 
          { yybegin(YYINITIAL);
                    return symbol(Sym.STRING,string.toString());
          }
        case 84: break;
        case 32: 
          { string.append('\n');
          }
        case 85: break;
        case 25: 
          { return symbol(Sym.DEQUAL);
          }
        case 86: break;
        case 15: 
          { return symbol(Sym.PLUS);
          }
        case 87: break;
        case 4: 
          { return symbol(Sym.MINUS);
          }
        case 88: break;
        case 38: 
          { return symbol(Sym.ELSE);
          }
        case 89: break;
        case 3: 
          { return num_symbol();
          }
        case 90: break;
        case 39: 
          { return symbol(Sym.KW_QUEUE);
          }
        case 91: break;
        case 17: 
          { string.append( yytext() );
          }
        case 92: break;
        case 2: 
          { 
          }
        case 93: break;
        case 8: 
          { return symbol(Sym.COMA);
          }
        case 94: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {
                return symbol(Sym.EOF);
              }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
