package com.github.mensajeria.compiler.servidor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class SFile {

    Path file;
    TIPO tipo;

    Object data = null;

    public SFile(Path file, TIPO tipo) {
        this.file = file;
        this.tipo = tipo;

        switch (tipo) {
            case CUENTA:
                this.data = new HashMap<>();
                break;
            case MENSAJE:
                this.data = new ArrayList<>();
                break;
            case CONTACTOS:
                this.data = new HashMap<>();
                break;
            default:
                throw new AssertionError(tipo.name());
        }
    }

    public Object get(Object key) throws Exception {
        Object val = null;
        read();

        return val;
    }

    private void read() throws FileNotFoundException, Exception {
        Path f = getFile();
        Scanner_file s = new Scanner_file(new FileReader(f.toFile()));
        Parser_file p = new Parser_file(s);

        p.parse();

        switch (getTipo()) {
            case CUENTA:
                break;
            case MENSAJE:
                break;
            case CONTACTOS:
                break;
            default:
                throw new AssertionError(getTipo().name());
        }
    }

    public static Object getKey(TIPO tipo, String cuenta) {
        String key = tipo + "_" + cuenta;
        return key;
    }

//<editor-fold defaultstate="collapsed" desc="getter and setter">

    public Path getFile() {
        return file;
    }

    public TIPO getTipo() {
        return tipo;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="TIPOS">
    public static enum TIPO {

        CUENTA, MENSAJE, CONTACTOS
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="STRUCTURAS">
    public static class Cuenta {

        String name;
        String password;

        public Cuenta(String name, String password) {
            this.name = name;
            this.password = password;
        }

    }

    public static class Mensaje {

        String mensaje;
        String emisor;
        HashSet<String> receptores;

        public Mensaje(String mensaje, String emisor, String... receptor) {
            this.mensaje = mensaje;
            this.emisor = emisor;
            this.receptores = new HashSet<>();

            for (String str : receptor) {
                boolean added = this.receptores.add(str);
            }
        }

    }

    public static class Contactos {

        String cuenta;
        HashSet<String> contactos;

        public Contactos(String cuenta, String... contactos) {
            this.cuenta = cuenta;
            this.contactos = new HashSet<>(Arrays.asList(contactos));
        }

    }
//</editor-fold>
}
