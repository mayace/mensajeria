package com.github.mensajeria.compiler;

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

    private Object pila = null;
    private Object simTable = null;
    private Object errs = null;

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
        setPila(pila);
        setSimTable(simTable);
        setErrs(errs);
        execNode(getLeft());
        execNode(getRight());

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
                    execLeaf();
                } else {
                    throw new AssertionError(getOperation().name());
                }
        }
        // post
    }

    private void execNode(Nodo nodo) {
        if (nodo != null) {
            nodo.exec(getPila(), getSimTable(), getErrs());
        }
    }

    private void execLeaf() {
        Object v = null;
        Object objr = getRef();
        Object objp = getPila();
        Object objt = getSimTable();
        Object obje = getErrs();
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

        RESTA, SUMA, MULTIPLICACION, DIVISION, LTHAN, BTHAN, NEQUAL, DEQUAL, LETHAN, BETHAN, OR, AND
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getter and setter">
    public void setErrs(Object errs) {
        this.errs = errs;
    }

    public Object getErrs() {
        return errs;
    }

    public Object getSimTable() {
        return simTable;
    }

    public void setSimTable(Object simTable) {
        this.simTable = simTable;
    }

    public Object getPila() {
        return pila;
    }

    public void setPila(Object pila) {
        this.pila = pila;
    }

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
