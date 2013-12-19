package com.github.mensajeria.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Nodo {

    private OPERACION operation = null;
    private Nodo left = null;
    private Nodo right = null;
    private Object val = null;
    private Object ref = null;
    ;
    private boolean leaf = false;
    private boolean id = false;

    /**
     * string Crea como nodo
     *
     * @param operation
     * @param left
     * @param right
     */
    public Nodo(OPERACION operation, Nodo left, Nodo right) {
        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    /**
     * Crea como hoja
     *
     * @param ref
     * @param id
     */
    public Nodo(Object ref, boolean id) {
        this.leaf = true;
        this.ref = ref;
        this.id = id;
    }

    /**
     * Realiza la operacion indicada
     *
     * @param pila
     * @param simTable
     * @param errs
     */
    public void exec(Object pila, Object simTable, Object errs) {
        // pre 
        execNode(getLeft(), pila, simTable, errs);
        execNode(getRight(), pila, simTable, errs);

        switch (getOperation()) {
            case RESTA:
                execResta();
                break;
            case SUMA:
                execSuma();
                break;
            case MULTIPLICACION:
                execMultiplicacion();
                break;
            case DIVISION:
                execDivision();
                break;
            default:
                if (isLeaf()) {
                    execLeaf(pila, simTable, errs);
                } else {
                    throw new AssertionError(getOperation().name());
                }
        }
        // post
    }

    private void execNode(Nodo nodo, Object pila, Object simTable, Object errs) {
        if (nodo != null) {
            nodo.exec(pila, simTable, errs);
        }
    }

    private void execLeaf(Object pila, Object simTable, Object errs) {
        Object v = null;
        Object objr = getRef();
        Object objp = pila;
        Object objt = simTable;
        Object obje = errs;
        if (isId()) {
            // pila
            if (objp instanceof Object[]) {
                Object[] data = (Object[]) objp;
                // simtable
                if (objt instanceof HashMap) {
                    HashMap<String, Sim> map = (HashMap) objt;
                    // errs
                    if (obje instanceof LinkedList) {
                        LinkedList<Err> errors = (LinkedList) obje;
                        // ref
                        if (objr instanceof Attr) {
                            Attr a = (Attr) objr;

                            String name = a.getString("val");

                            if (map.containsKey(name)) {
                                setVal(data[map.get(name).getPos()]);
                            } else {
                                errors.add(new Err(name, a.getSymbol("info"), Err.TIPO.SEMANTICO));
                            }
                        }
                    }

                }

            }

        } else {
            if (objr instanceof Attr) {
                Attr a = (Attr) objr;
                v = a.get("val");
            }
        }

        setVal(v);
    }

    private void execResta() {
        Nodo l = getLeft();
        Nodo r = getRight();

        setVal((Integer) l.getVal() - (Integer) r.getVal());
    }

    private void execSuma() {
        Nodo l = getLeft();
        Nodo r = getRight();

        setVal((Integer) l.getVal() + (Integer) r.getVal());
    }

    private void execMultiplicacion() {
        Nodo l = getLeft();
        Nodo r = getRight();

        setVal((Integer) l.getVal() * (Integer) r.getVal());
    }

    private void execDivision() {
        Nodo l = getLeft();
        Nodo r = getRight();

        setVal((Integer) l.getVal() / (Integer) r.getVal());
    }

//<editor-fold defaultstate="collapsed" desc="CONSTANTES">
    public static enum OPERACION {

        RESTA, SUMA, MULTIPLICACION, DIVISION, LTHAN, BTHAN, NEQUAL, DEQUAL, LETHAN, BETHAN, OR, AND,
        STMT,IF,WHILE,FOR,ASIGNACION,ESPERAR,ENVIAR,PRINTLN
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getter and setter">
    public Object getRef() {
        return ref;
    }

    public void setRef(Object ref) {
        this.ref = ref;
    }

    public OPERACION getOperation() {
        return operation;
    }

    public void setOperation(OPERACION operation) {
        this.operation = operation;
    }

    public Nodo getLeft() {
        return left;
    }

    public void setLeft(Nodo left) {
        this.left = left;
    }

    public Nodo getRight() {
        return right;
    }

    public void setRight(Nodo right) {
        this.right = right;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }
    //</editor-fold>
}
