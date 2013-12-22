package com.github.mensajeria.compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * @param tabla_simbolos
     * @param errores
     */
    public void exec(Object pila, Object tabla_simbolos, Object errores) {
        // pre 
        exec_nodo(getLeft(), pila, tabla_simbolos, errores);
        exec_nodo(getRight(), pila, tabla_simbolos, errores);
        final OPERACION oper = getOperation();
        //
        switch (oper) {
            case RESTA:
                exec_resta(pila, tabla_simbolos, errores);
                break;
            case SUMA:
                execSuma(pila, tabla_simbolos, errores);
                break;
            case MULTIPLICACION:
                execMultiplicacion(pila, tabla_simbolos, errores);
                break;
            case DIVISION:
                execDivision(pila, tabla_simbolos, errores);
                break;
            case LTHAN:
                execMenorQue(pila, tabla_simbolos, errores);
                break;
            case BTHAN:
                execMayorQue(pila, tabla_simbolos, errores);
                break;
            case NEQUAL:
                execNoIgual(pila, tabla_simbolos, errores);//falta
                break;
            case DEQUAL:
                execIgual(pila, tabla_simbolos, errores);//falta
                break;
            case LETHAN:
                execMenorIgualQue(pila, tabla_simbolos, errores);
                break;
            case BETHAN:
                execMayorIgualQue(pila, tabla_simbolos, errores);
                break;
            case OR:
                exec_or(pila, tabla_simbolos, errores);
                break;
            case AND:
                exec_and(pila, tabla_simbolos, errores);
                break;
            case STMT://Nada aqui....
                break;
            case IF:
                exec_if(pila, tabla_simbolos, errores);
                break;
            case WHILE:
                break;
            case FOR:
                break;
            case ASIGNACION:
                exec_asignacion(pila, tabla_simbolos, errores);
                break;
            case ESPERAR:
                exec_esperar(pila, tabla_simbolos, errores);
                break;
            case ENVIAR:
                exec_enviar(pila, tabla_simbolos, errores);
                break;
            case PRINTLN:
                exec_println(pila, tabla_simbolos, errores);
                break;
            case ID:
                if (isLeaf()) {
                    exec_leaf(pila, tabla_simbolos, errores);
                }
                break;
            case DECLARACION:
                exec_declaracion(pila, tabla_simbolos, errores);
                break;
            case FUNCION:
                exec_funcion(pila, tabla_simbolos, errores);
                break;
            default:
                throw new AssertionError(getOperation().name());
        }
        // post
    }

    private void exec_nodo(Nodo nodo, Object pila, Object simTable, Object errs) {
        if (nodo != null) {
            nodo.exec(pila, simTable, errs);
        }
    }

    private void exec_leaf(Object pila, Object simTable, Object errs) {

        ArrayList data = (ArrayList) pila;
        HashMap<String, Sim> table = (HashMap<String, Sim>) simTable;
        LinkedList<Err> errors = (LinkedList<Err>) errs;

        Attr a = (Attr) getRef(); // atributo que trae
        Attr b = new Attr(); // atributo nuevo a enviar

        if (isId()) {
            String var_name = a.getString("val");
            if (table.containsKey(var_name)) {
                Sim var_simbolo = table.get(var_name);
                Object var_val = data.get(var_simbolo.getPos());

                b.set("val", var_val);
                b.set("tipo", var_simbolo.getType());
                b.set("info", a.get("info"));

            } else {
                errors.add(new Err("Variable no declarada: " + var_name, a.get("info"), Err.TIPO.SEMANTICO));
            }

        } else {
            b.set("tipo", a.get("tipo"));
            b.set("val", a.get("val"));
            b.set("info", a.get("info"));
        }
        setVal(b);
    }

    private void exec_resta(Object pila, Object simTable, Object errs) {
        Object info = (getRef() == null ? null : ((Attr) getRef()).get("info"));
        LinkedList errores = (LinkedList) errs;
        Nodo l = getLeft();
        Nodo r = getRight();

        Attr lAtrib = (Attr) l.getVal();
        Attr rAtrib = (Attr) r.getVal();

        String lTipo = lAtrib.getString("tipo");
        String rTipo = rAtrib.getString("tipo");

        if ((lTipo.equals("int")) && (rTipo.equals("int"))) {
            Integer lValor = lAtrib.getInteger("val");
            Integer rValor = rAtrib.getInteger("val");

            Attr attrResult = new Attr();
            attrResult.set("tipo", "int");
            attrResult.set("val", (lValor - rValor));
            setVal(attrResult);
        } else {
            Err nuevoError = new Err("error: los tipos no son enteros", info, Err.TIPO.SEMANTICO);
            errores.add(nuevoError);
        }
    }

    private void execSuma(Object pila, Object simTable, Object errs) {
        Object info = (getRef() == null ? null : ((Attr) getRef()).get("info"));
        LinkedList errores = (LinkedList) errs;
        Nodo l = getLeft();
        Nodo r = getRight();

        Attr lAtrib = (Attr) l.getVal();
        Attr rAtrib = (Attr) r.getVal();

        String lTipo = lAtrib.getString("tipo");
        String rTipo = rAtrib.getString("tipo");

        if ((lTipo.equals("string")) || (rTipo.equals("string"))) {
            String lvalor = lAtrib.getString("val");
            String rValor = rAtrib.getString("val");
            String res = lvalor + rValor;
            //setVal(res);

            Attr attrResult = new Attr();
            attrResult.set("tipo", "string");
            attrResult.set("val", (res));
            setVal(attrResult);
        } else if ((lTipo.equals("int")) && (rTipo.equals("int"))) {
            Integer lValor = lAtrib.getInteger("val");
            Integer rValor = rAtrib.getInteger("val");
            //setVal(lValor-rValor);
            int valOperacion = lValor + rValor;

            Attr attrResult = new Attr();
            attrResult.set("tipo", "int");
            attrResult.set("val", (valOperacion));
            setVal(attrResult);
        } else {
            Err nuevoError = new Err("error de tipos, no coinciden", info, Err.TIPO.SEMANTICO);
            errores.add(nuevoError);
        }
    }

    private void execMultiplicacion(Object pila, Object simTable, Object errs) {
        Object info = (getRef() == null ? null : ((Attr) getRef()).get("info"));
        LinkedList errores = (LinkedList) errs;
        Nodo l = getLeft();
        Nodo r = getRight();

        Attr lAtrib = (Attr) l.getVal();
        Attr rAtrib = (Attr) r.getVal();

        String lTipo = lAtrib.getString("tipo");
        String rTipo = rAtrib.getString("tipo");

        if ((lTipo.equals("int")) && (rTipo.equals("int"))) {
            Integer lValor = lAtrib.getInteger("val");
            Integer rValor = rAtrib.getInteger("val");
            int valOperacion = lValor * rValor;

            Attr attrResult = new Attr();
            attrResult.set("tipo", "int");
            attrResult.set("val", (valOperacion));
            setVal(attrResult);
        } else {
            Err nuevoError = new Err("error: los tipos no son enteros", info, Err.TIPO.SEMANTICO);
            errores.add(nuevoError);
        }

    }

    private void execDivision(Object pila, Object simTable, Object errs) {
        Object info = (getRef() == null ? null : ((Attr) getRef()).get("info"));
        LinkedList errores = (LinkedList) errs;
        Nodo l = getLeft();
        Nodo r = getRight();

        Attr lAtrib = (Attr) l.getVal();
        Attr rAtrib = (Attr) r.getVal();

        String lTipo = lAtrib.getString("tipo");
        String rTipo = rAtrib.getString("tipo");

        if ((lTipo.equals("int")) && (rTipo.equals("int"))) {
            Integer lValor = lAtrib.getInteger("val");
            Integer rValor = rAtrib.getInteger("val");
            int valDivision = lValor / rValor;

            Attr attrResult = new Attr();
            attrResult.set("tipo", "int");
            attrResult.set("val", (valDivision));
            setVal(attrResult);
        } else {
            Err nuevoError = new Err("error: los tipos no son enteros", info, Err.TIPO.SEMANTICO);
            errores.add(nuevoError);
        }
    }

    private void exec_asignacion(Object pila, Object simTable, Object errs) {
        ArrayList data = data = (ArrayList) pila;
        HashMap<String, Sim> table = (HashMap) simTable;
        LinkedList<Err> errors = (LinkedList<Err>) errs;

        Nodo l = getLeft();
        Nodo r = getRight();
        Object lval = l.getVal();
        Object rval = r.getVal();

        Attr lattr = (Attr) lval;
        Attr rattr = (Attr) rval;

        ArrayList<Attr> vars = lattr.getList("val");
        String expr_tipo = rattr.getString("tipo");
        Object expr_val = rattr.get("val");

        for (Attr var : vars) {
            String var_name = var.getString("val");
            Object var_info = var.get("info");

            if (table.containsKey(var_name)) {
                Sim var_sim = table.get(var_name);
                String var_tipo = var_sim.getType();

                if (var_tipo.equals(expr_tipo)) {
                    data.set(var_sim.getPos(), expr_val);
                } else {
                    errors.add(new Err("Incompatible tipos en asignacion: " + var_tipo + "=" + expr_tipo, var_info, Err.TIPO.SEMANTICO));
                }
            } else {
                errors.add(new Err("La variable no existe: " + var_name, var_info, Err.TIPO.SEMANTICO));
            }
        }

    }

    private void exec_println(Object pila, Object simTable, Object errs) {
        final Attr attr = (Attr) getLeft().getVal();
        final Object valor = attr.get("val");
        System.out.println(valor);
    }

    private void execMenorQue(Object pila, Object simTable, Object errores) {
        Object info = (getRef() == null ? null : ((Attr) getRef()).get("info"));
        LinkedList errors = (LinkedList) errores;

        Nodo l = getLeft();
        Nodo r = getRight();

        Attr lAtrib = (Attr) l.getVal();
        Attr rAtrib = (Attr) r.getVal();

        String lTipo = lAtrib.getString("tipo");
        String rTipo = rAtrib.getString("tipo");

        if ((lTipo.equals("int")) && (rTipo.equals("int"))) {

            Integer lValor = lAtrib.getInteger("val");
            Integer rValor = rAtrib.getInteger("val");
            boolean valorResultado = lValor < rValor;

            Attr attrResult = new Attr();
            attrResult.set("tipo", "boolean");
            attrResult.set("val", (valorResultado));
            setVal(attrResult);
        } else {
            Err nuevoError = new Err("Tipos incompatibles: " + lTipo + "<" + rTipo, info, Err.TIPO.SEMANTICO);
            errors.add(nuevoError);
        }

    }

    private void exec_declaracion(Object pila, Object simTable, Object errs) {
        ArrayList data = (ArrayList) pila;
        HashMap<String, Sim> table = (HashMap<String, Sim>) simTable;
        LinkedList<Err> errors = (LinkedList<Err>) errs;

        Nodo l = getLeft();
        Nodo r = getRight();
        Object lval = l.getVal();
        Object rval = r.getVal();

        Attr lattr = (Attr) lval;
        Attr rattr = (Attr) rval;

        String tipo = lattr.getString("val");
        ArrayList<Attr> vars = rattr.getList("val");

        for (Attr var : vars) {
            String var_name = var.getString("val");
            Object var_info = var.get("info");
            if (table.containsKey(var_name)) {
                errors.add(new Err("Ya existe una variable con el nombre: " + var_name, var_info, Err.TIPO.SEMANTICO));
            } else {
                table.put(var_name, new Sim(var_name, tipo, data.size()));
                //reservar espacio
                switch (tipo) {
                    case "queue":
                        data.add(new LinkedList<String>());
                        break;
                    case "list":
                        data.add(new ArrayList<String>());
                        break;
                    default:
                        data.add(null);
                }
            }
        }

    }

    private void execMayorQue(Object pila, Object tabla_simbolos, Object errores) {
        Object info = (getRef() == null ? null : ((Attr) getRef()).get("info"));
        LinkedList errors = (LinkedList) errores;

        Nodo l = getLeft();
        Nodo r = getRight();

        Attr lAtrib = (Attr) l.getVal();
        Attr rAtrib = (Attr) r.getVal();

        String lTipo = lAtrib.getString("tipo");
        String rTipo = rAtrib.getString("tipo");

        if ((lTipo.equals("int")) && (rTipo.equals("int"))) {

            Integer lValor = lAtrib.getInteger("val");
            Integer rValor = rAtrib.getInteger("val");
            boolean valorResultado = lValor > rValor;

            Attr attrResult = new Attr();
            attrResult.set("tipo", "boolean");
            attrResult.set("val", (valorResultado));
            setVal(attrResult);
        } else {
            Err nuevoError = new Err("Tipos incompatibles: " + lTipo + ">" + rTipo, info, Err.TIPO.SEMANTICO);
            errors.add(nuevoError);
        }
    }

    private void execNoIgual(Object pila, Object tabla_simbolos, Object errores) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void execIgual(Object pila, Object tabla_simbolos, Object errores) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void execMenorIgualQue(Object pila, Object tabla_simbolos, Object errores) {
        LinkedList errors = (LinkedList) errores;
        Object info = (getRef() == null ? null : ((Attr) getRef()).get("info"));

        Nodo l = getLeft();
        Nodo r = getRight();

        Attr lAtrib = (Attr) l.getVal();
        Attr rAtrib = (Attr) r.getVal();

        String lTipo = lAtrib.getString("tipo");
        String rTipo = rAtrib.getString("tipo");

        if ((lTipo.equals("int")) && (rTipo.equals("int"))) {

            Integer lValor = lAtrib.getInteger("val");
            Integer rValor = rAtrib.getInteger("val");
            boolean valorResultado = lValor <= rValor;

            Attr attrResult = new Attr();
            attrResult.set("tipo", "boolean");
            attrResult.set("val", (valorResultado));
            setVal(attrResult);
        } else {
            Err nuevoError = new Err("Tipos incompatibles: " + lTipo + ">" + rTipo, info, Err.TIPO.SEMANTICO);
            errors.add(nuevoError);
        }
    }

    private void execMayorIgualQue(Object pila, Object tabla_simbolos, Object errores) {
        Object info = (getRef() == null ? null : ((Attr) getRef()).get("info"));
        LinkedList errors = (LinkedList) errores;
        Nodo l = getLeft();
        Nodo r = getRight();

        Attr lAtrib = (Attr) l.getVal();
        Attr rAtrib = (Attr) r.getVal();

        String lTipo = lAtrib.getString("tipo");
        String rTipo = rAtrib.getString("tipo");

        if ((lTipo.equals("int")) && (rTipo.equals("int"))) {

            Integer lValor = lAtrib.getInteger("val");
            Integer rValor = rAtrib.getInteger("val");
            boolean valorResultado = lValor >= rValor;

            Attr attrResult = new Attr();
            attrResult.set("tipo", "boolean");
            attrResult.set("val", (valorResultado));
            setVal(attrResult);
        } else {
            Err nuevoError = new Err("Tipos incompatibles: " + lTipo + ">" + rTipo, info, Err.TIPO.SEMANTICO);
            errors.add(nuevoError);
        }
    }

    private void exec_or(Object pila, Object tabla_simbolos, Object errores) {
        Object info = (getRef() == null ? null : ((Attr) getRef()).get("info"));
        LinkedList errors = (LinkedList) errores;
        Nodo l = getLeft();
        Nodo r = getRight();

        Attr lAtrib = (Attr) l.getVal();
        Attr rAtrib = (Attr) r.getVal();

        String lTipo = lAtrib.getString("tipo");
        String rTipo = rAtrib.getString("tipo");

        if ((lTipo.equals("boolean")) && (rTipo.equals("boolean"))) {

            Boolean lValor = lAtrib.getBoolean("val");
            Boolean rValor = rAtrib.getBoolean("val");
            boolean valorResultado = lValor || rValor;

            Attr attrResult = new Attr();
            attrResult.set("tipo", "boolean");
            attrResult.set("val", (valorResultado));
            setVal(attrResult);
        } else {
            Err nuevoError = new Err("Tipos incompatibles: " + lTipo + "or" + rTipo, info, Err.TIPO.SEMANTICO);
            errors.add(nuevoError);
        }
    }

    private void exec_and(Object pila, Object tabla_simbolos, Object errores) {
        Object info = (getRef() == null ? null : ((Attr) getRef()).get("info"));
        LinkedList errors = (LinkedList) errores;
        Nodo l = getLeft();
        Nodo r = getRight();

        Attr lAtrib = (Attr) l.getVal();
        Attr rAtrib = (Attr) r.getVal();

        String lTipo = lAtrib.getString("tipo");
        String rTipo = rAtrib.getString("tipo");

        if ((lTipo.equals("boolean")) && (rTipo.equals("boolean"))) {

            Boolean lValor = lAtrib.getBoolean("val");
            Boolean rValor = rAtrib.getBoolean("val");
            boolean valorResultado = lValor && rValor;

            Attr attrResult = new Attr();
            attrResult.set("tipo", "boolean");
            attrResult.set("val", (valorResultado));
            setVal(attrResult);
        } else {
            Err nuevoError = new Err("Tipos incompatibles: " + lTipo + "or" + rTipo, info, Err.TIPO.SEMANTICO);
            errors.add(nuevoError);
        }
    }

    private void exec_esperar(Object pila, Object tabla_simbolos, Object errores) {
        LinkedList<Err> errors = (LinkedList<Err>) errores;
        Object info = (getRef() == null ? null : ((Attr) getRef()).get("info"));

        Attr expr_attr = (Attr) getLeft().getVal();
        String expr_tipo = expr_attr.getString("tipo");

        if (expr_tipo.equals("int")) {
            Integer expr_val = expr_attr.getInteger("val");
            try {
                Thread.sleep((long) (expr_val * 1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                errors.add(new Err(ex.getMessage(), null, Err.TIPO.SEMANTICO));
            }
        } else {
            errors.add(new Err("Se esperaba un numero entero.", info, Err.TIPO.SEMANTICO));
        }
    }

    private void exec_enviar(Object pila, Object tabla_simbolos, Object errores) {
        Object info = (getRef() == null ? null : ((Attr) getRef()).get("info"));
        System.out.println("Procesar aqui el comando enviar...");
    }

    private void exec_funcion(Object pila, Object tabla_simbolos, Object errores) {
        ArrayList data = (ArrayList) pila;
        HashMap<String, Sim> table = (HashMap<String, Sim>) tabla_simbolos;
        LinkedList<Err> errors = (LinkedList<Err>) errores;

        Attr l_val = (Attr) getLeft().getVal();
        Attr base_attr = l_val.getAttr("val");

        Attr id_attr = base_attr.getAttr("id");
        String id_name = id_attr.getString("val");
        Object id_info = id_attr.get("info");

        Attr r_val = new Attr();

        if (table.containsKey(id_name)) {
            Sim id_sim = table.get(id_name);
            final String id_tipo = id_sim.getType();
            int id_pos = id_sim.getPos();
            Attr funcion_attr = base_attr.getAttr("funcion");
            String funcion_name = funcion_attr.getString("val");
            Object funcion_info = funcion_attr.get("info");
            ArrayList<Attr> funcion_params = base_attr.getList("params");

            switch (id_tipo) {
                case "queue":
                    Queue cola = (Queue) data.get(id_pos);
                    switch (funcion_name) {
                        // INGRESAR
                        case "ingresar":
                            if (funcion_params.size() == 1) {
                                Attr param_attr = funcion_params.get(0);
                                final Nodo param_nodo = param_attr.getNodo("nodo");

                                param_nodo.exec(pila, tabla_simbolos, errores);
                                String param_tipo = ((Attr) param_nodo.getVal()).getString("tipo");
                                if (param_tipo.equals("string")) {
                                    String param_val = ((Attr) param_nodo.getVal()).getString("val");
                                    cola.add(param_val);
                                } else {
                                    errors.add(new Err("Se esperaba una cadena para funcion: " + funcion_name, funcion_info, Err.TIPO.SEMANTICO));
                                }
                            } else {
                                errors.add(new Err("Se esperaba solo un parametro para funcion: " + funcion_name, funcion_info, Err.TIPO.SEMANTICO));
                            }
                            break;
                        // OBTENER
                        case "obtener":
                            if (funcion_params.isEmpty()) {
                                r_val.set("tipo", "string");
                                r_val.set("val", cola.poll());
                            } else {
                                errors.add(new Err("No se esperaba parametro para funcion: " + funcion_name, funcion_info, Err.TIPO.SEMANTICO));
                            }
                            break;
                        // VACIA
                        case "vacia":
                            if (funcion_params.isEmpty()) {
                                r_val.set("tipo", "boolean");
                                r_val.set("val", cola.isEmpty());
                            } else {
                                errors.add(new Err("No se esperaba parametro para funcion: " + funcion_name, funcion_info, Err.TIPO.SEMANTICO));
                            }
                            break;
                        default:
                            errors.add(new Err("Operacion no soportada para una cola", funcion_info, Err.TIPO.SEMANTICO));
                    }
                    break;
                case "list":
                    ArrayList lista = (ArrayList) data.get(id_pos);
                    switch (funcion_name) {
                        // INGRESAR
                        case "ingresar":
                            if (funcion_params.size() == 1) {
                                Attr param_attr = funcion_params.get(0);
                                final Nodo param_nodo = param_attr.getNodo("nodo");

                                param_nodo.exec(pila, tabla_simbolos, errores);
                                String param_tipo = ((Attr) param_nodo.getVal()).getString("tipo");
                                if (param_tipo.equals("string")) {
                                    String param_val = ((Attr) param_nodo.getVal()).getString("val");
                                    lista.add(param_val);
                                } else {
                                    errors.add(new Err("Se esperaba una cadena para funcion: " + funcion_name, funcion_info, Err.TIPO.SEMANTICO));
                                }
                            } else {
                                errors.add(new Err("Se esperaba solo un parametro para funcion: " + funcion_name, funcion_info, Err.TIPO.SEMANTICO));
                            }
                            break;
                        // OBTENER
                        case "obtener":
                            if (funcion_params.size() == 1) {
                                Attr param_attr = funcion_params.get(0);
                                final Nodo param_nodo = param_attr.getNodo("nodo");

                                param_nodo.exec(pila, tabla_simbolos, errores);
                                String param_tipo = ((Attr) param_nodo.getVal()).getString("tipo");
                                if (param_tipo.equals("int")) {
                                    Integer param_val = ((Attr) param_nodo.getVal()).getInteger("val");
                                    if (param_val < funcion_params.size()) {
                                        r_val.set("tipo", "string");
                                        r_val.set("val", lista.get(param_val));
                                    } else {
                                        errors.add(new Err("Indice fuera de rango: " + param_val + "<" + funcion_params.size(), funcion_info, Err.TIPO.SEMANTICO));
                                    }
                                } else {
                                    errors.add(new Err("Se esperaba un entero para funcion: " + funcion_name, funcion_info, Err.TIPO.SEMANTICO));
                                }
                            } else {
                                errors.add(new Err("Se esperaba solo un parametro para funcion: " + funcion_name, funcion_info, Err.TIPO.SEMANTICO));
                            }
                            break;
                        // VACIA
                        case "vacia":
                            if (funcion_params.isEmpty()) {
                                r_val.set("tipo", "boolean");
                                r_val.set("val", lista.isEmpty());
                            } else {
                                errors.add(new Err("No se esperaba  parametro para funcion: " + funcion_name, funcion_info, Err.TIPO.SEMANTICO));
                            }
                            break;
                        case "tamaño":
                            if (funcion_params.isEmpty()) {
                                r_val.set("tipo", "int");
                                r_val.set("val", lista.size());
                            } else {
                                errors.add(new Err("No se esperaba  parametro para funcion: " + funcion_name, funcion_info, Err.TIPO.SEMANTICO));
                            }
                            break;
                        default:
                            errors.add(new Err("Operacion no soportada para una lista", funcion_info, Err.TIPO.SEMANTICO));
                    }
                    break;
                default:
                    errors.add(new Err("Variable no es una cola o arreglo: " + id_name, id_info, Err.TIPO.SEMANTICO));
            }
        } else {
            errors.add(new Err("No existe la variable: " + id_name, id_info, Err.TIPO.SEMANTICO));
        }
        setVal(r_val);
    }

    private void exec_if(Object pila, Object tabla_simbolos, Object errores) 
    {
        LinkedList errs = (LinkedList) errores;
        Nodo l = getLeft();
        Nodo r = getRight();
        
        Attr lAttr = (Attr)l.getVal();
        Attr rAttr = (Attr)r.getVal();
        
        Attr cuerpo = (Attr) rAttr.getAttr("val");
        Nodo ifNodo = cuerpo.getNodo("if");
        Nodo elseNodo = cuerpo.getNodo("else");
        
        //Nodo condicion = lAttr.getNodo("val");
        Nodo condicion = lAttr.getNodo("val");
        Nodo ifElse = rAttr.getNodo("val");
       
        condicion.exec(pila, tabla_simbolos, errores);
        
        Attr ResCondicion = (Attr) condicion.getVal();
        Boolean buleano = ResCondicion.getBoolean("val");
        
        
        
        
        if(buleano)
        {
            ifNodo.exec(pila, tabla_simbolos, errores);
        }
        else
        {
            elseNodo.exec(pila, tabla_simbolos, errores);
        }
        
        
        
    }

//<editor-fold defaultstate="collapsed" desc="CONSTANTES">
    public static enum OPERACION {

        RESTA, SUMA, MULTIPLICACION, DIVISION, LTHAN, BTHAN, NEQUAL, DEQUAL, LETHAN, BETHAN, OR, AND,
        STMT, IF, WHILE, FOR, ASIGNACION, ESPERAR, ENVIAR, PRINTLN, ID, DECLARACION,
        FUNCION
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
