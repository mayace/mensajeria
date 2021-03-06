package com.github.mensajeria.compiler;

import java_cup.runtime.Symbol;

public class Err {

    public static String ERROR_CONVERT = "No se puede convertir.";
    public static String ERROR_STMT = "Se esperaba una sentencia.";

    private String msg;
    private Symbol sym;
    private TIPO TIPO;
    public static final String FORMAT = "[Error] in line %d column %d on value '%s'. Message: %s";
    public static final String ERROR_ID = "Se esperaba un identificador...";
    public static final String ERROR_INT = "Se esperaba un entero...";
    public static final String ERROR_BOOLEAN = "Se esperaba un boolean verdadero 'verdadero' o 'falso'...";
    public static final String ERROR_NULO = "Valor nulo.";
    public static final String ERROR_VAR_NO_EXISTE = "Variable no existe.";

    public TIPO getType() {
        return TIPO;
    }

    public void setType(TIPO type) {
        this.TIPO = type;
    }

    public enum TIPO {

        LEXICO, SINTACTICO, SEMANTICO
    }

    public Err(String msg, Object sym, TIPO type) {
        this.msg = msg;
        this.TIPO = type;

        if (sym instanceof Symbol) {
            this.sym = (Symbol) sym;
        }
    }

    public Symbol getSym() {
        return sym;
    }

    public void setSym(Symbol sym) {
        this.sym = sym;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        Symbol s = getSym();
        int line = -1;
        int column = -1;
        String value = "null";

        if (s != null) {
            line = s.left + 1;
            column = s.right + 1;
            value = s.value.toString();
        }

        return String.format(FORMAT, line, column, value, getMsg());

    }

    public void println() {

        String t = "Unknow";
        if (getType() != null) {
            t = getType().name();
        }

        System.err.println(t + " Error:");
        System.err.println(toString());
    }
}
