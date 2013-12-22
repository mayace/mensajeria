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
        this.operation = OPERACION.ID;
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
        final OPERACION oper = getOperation();
//        System.err.println(oper);

        switch (oper) {
            case RESTA:
                execResta(pila, simTable, errs);
                break;
            case SUMA:
                execSuma(pila, simTable, errs);
                break;
            case MULTIPLICACION:
                execMultiplicacion(pila, simTable, errs);
                break;
            case DIVISION:
                execDivision(pila,simTable,errs);
                break;
            case LTHAN:
                execMenorQue(pila,simTable,errs);
                break;
            case BTHAN:
                break;
            case NEQUAL:
                break;
            case DEQUAL:
                break;
            case LETHAN:
                break;
            case BETHAN:
                break;
            case OR:
                break;
            case AND:
                break;
            case STMT:
                break;
            case IF:
                break;
            case WHILE:
                break;
            case FOR:
                break;
            case ASIGNACION:
                exec_asignacion(pila, simTable, errs);
                break;
            case ESPERAR:
                break;
            case ENVIAR:
                break;
            case PRINTLN:
                exec_println(pila, simTable, errs);
                break;
            case ID:
                if (isLeaf()) {
                    execLeaf(pila, simTable, errs);
                }
                break;
            default:
                throw new AssertionError(getOperation().name());
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
                                //setVal(data[map.get(name).getPos()]);
                                Sim simbolo = map.get(name);
                                Attr b = new Attr();
                                Object valSimbolo = data[simbolo.getPos()];
                                        
                                b.set("val", valSimbolo);
                                b.set("type", simbolo.getType());
                                b.set("info", a.get("info"));
                                
                                setVal(b);
                                
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
                //setVal(v);
                
                Attr b = new Attr();
                b.set("type", a.get("type"));
                b.set("val", a.get("val"));
                b.set("info", a.get("info"));
                setVal(b);
            }
        }

        
    }

    private void execResta(Object pila, Object simTable, Object errs) {
        LinkedList errores = (LinkedList)errs;
        Nodo l = getLeft();
        Nodo r = getRight();
        
        Attr lAtrib = (Attr)l.getVal();
        Attr rAtrib = (Attr)r.getVal();
        
        String lTipo = lAtrib.getString("type");
        String rTipo = rAtrib.getString("type");
        
        
        
        if((lTipo.equals("int"))&&(rTipo.equals("int")))
        {
            Integer lValor = lAtrib.getInteger("val");
            Integer rValor = rAtrib.getInteger("val");
        
            Attr attrResult = new Attr();
            attrResult.set("type", "int");
            attrResult.set("val", (lValor-rValor));
            setVal(attrResult);
        }
        else
        {
            Err nuevoError = new Err("error: los tipos no son enteros", null, Err.TIPO.SEMANTICO);
            errores.add(nuevoError);  
        }
    }

    private void execSuma(Object pila, Object simTable, Object errs) {
        LinkedList errores = (LinkedList)errs;
        Nodo l = getLeft();
        Nodo r = getRight();
        
        Attr lAtrib = (Attr)l.getVal();
        Attr rAtrib = (Attr)r.getVal();
        
        String lTipo = lAtrib.getString("type");
        String rTipo = rAtrib.getString("type");
        
        if((lTipo.equals("string"))||(rTipo.equals("string")))
        {
            String lvalor = lAtrib.getString("val");
            String rValor = rAtrib.getString("val");
            String res = lvalor+rValor;
            //setVal(res);
            
            
            Attr attrResult = new Attr();
            attrResult.set("type", "string");
            attrResult.set("val", (res));
            setVal(attrResult);
        }
        
        else if((lTipo.equals("int"))&&(rTipo.equals("int")))
        {
            Integer lValor = lAtrib.getInteger("val");
            Integer rValor = rAtrib.getInteger("val");
            //setVal(lValor-rValor);
            int valOperacion = lValor+rValor;
            
            Attr attrResult = new Attr();
            attrResult.set("type", "int");
            attrResult.set("val", (valOperacion));
            setVal(attrResult);
        }
        else
        {
            Err nuevoError = new Err("error de tipos, no coinciden", null, Err.TIPO.SEMANTICO);
            errores.add(nuevoError);  
        }
    }

    private void execMultiplicacion(Object pila, Object simTable, Object errs) {
        LinkedList errores = (LinkedList)errs;
        Nodo l = getLeft();
        Nodo r = getRight();
        
        Attr lAtrib = (Attr)l.getVal();
        Attr rAtrib = (Attr)r.getVal();
        
        String lTipo = lAtrib.getString("type");
        String rTipo = rAtrib.getString("type");
        
        
        
        if((lTipo.equals("int"))&&(rTipo.equals("int")))
        {
            Integer lValor = lAtrib.getInteger("val");
            Integer rValor = rAtrib.getInteger("val");
            int valOperacion = lValor*rValor;
            
            Attr attrResult = new Attr();
            attrResult.set("type", "int");
            attrResult.set("val", (valOperacion));
            setVal(attrResult);
        }
        else
        {
            Err nuevoError = new Err("error: los tipos no son enteros", null, Err.TIPO.SEMANTICO);
            errores.add(nuevoError);  
        }
        
    }

    private void execDivision(Object pila, Object simTable, Object errs) {
        LinkedList errores =(LinkedList)errs;
        Nodo l = getLeft();
        Nodo r = getRight();
        
        Attr lAtrib=(Attr)l.getVal();
        Attr rAtrib=(Attr)r.getVal();
        
        String lTipo = lAtrib.getString("type");
        String rTipo = rAtrib.getString("type");
        
        
        
        if((lTipo.equals("int"))&&(rTipo.equals("int")))
        {
            Integer lValor = lAtrib.getInteger("val");
            Integer rValor = rAtrib.getInteger("val");
            int valDivision = lValor/rValor;
            
            Attr attrResult = new Attr();
            attrResult.set("type", "int");
            attrResult.set("val", (valDivision));
            setVal(attrResult);
        }
        else
        {
            Err nuevoError = new Err("error: los tipos no son enteros", null, Err.TIPO.SEMANTICO);
            errores.add(nuevoError);  
        }
    }

    private void exec_asignacion(Object pila, Object simTable, Object errs) {
        Object expr = getLeft().getVal();
        Object objr = getRight().getVal();

        Object[] data = new Object[100];
        HashMap<String, Sim> tabla = new HashMap<>();
        LinkedList<Err> errores = new LinkedList<>();
        ArrayList<Attr> ids;

        if (pila instanceof Object[]) {
            data = (Object[]) pila;
        }
        if (simTable instanceof HashMap) {
            tabla = (HashMap) simTable;
        }
        if (errs instanceof LinkedList) {
            errores = (LinkedList<Err>) errs;
        }

        if (objr instanceof ArrayList) {
            ids = (ArrayList) objr;

            for (Attr attr : ids) {
                final String idname = attr.getString("val");
                System.err.println(idname + "=" + expr);
                if (tabla.containsKey(idname)) {
                    Sim sim = tabla.get(idname);
                    data[sim.getPos()] = expr;
                } else {
                    errores.add(new Err("No exista la variable", attr.getSymbol("info"), Err.TIPO.SEMANTICO));
                }
            }
        }

    }

    private void exec_println(Object pila, Object simTable, Object errs) {
        System.out.println(getLeft());
    }

    private void execMenorQue(Object pila, Object simTable, Object errs) {
        LinkedList errores =(LinkedList)errs;
        Nodo l = getLeft();
        Nodo r = getRight();
        
        Attr lAtrib=(Attr)l.getVal();
        Attr rAtrib=(Attr)r.getVal();
        
        String lTipo = lAtrib.getString("type");
        String rTipo = rAtrib.getString("type");
        
        
        
        if((lTipo.equals("boolean"))&&(rTipo.equals("boolean")))
        {
            
            Integer lValor = lAtrib.getInteger("val");
            Integer rValor = rAtrib.getInteger("val");
            int valDivision = lValor/rValor;
            
            Attr attrResult = new Attr();
            attrResult.set("type", "int");
            attrResult.set("val", (valDivision));
            setVal(attrResult);
        }
        else
        {
            Err nuevoError = new Err("error: los tipos no son enteros", null, Err.TIPO.SEMANTICO);
            errores.add(nuevoError);  
        }
        
    }

//<editor-fold defaultstate="collapsed" desc="CONSTANTES">
    public static enum OPERACION {

        RESTA, SUMA, MULTIPLICACION, DIVISION, LTHAN, BTHAN, NEQUAL, DEQUAL, LETHAN, BETHAN, OR, AND,
        STMT, IF, WHILE, FOR, ASIGNACION, ESPERAR, ENVIAR, PRINTLN, ID
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
