package com.github.mensajeria.compiler.servidor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashSet;

public class SOperacion {

    TIPO tipo;
    Object valor;

    public static enum TIPO {

        LOGIN, CUENTA, MENSAJE, BUSQUEDA, SOLICITUD_AMISTAD, SOLICITUD_RESPUESTA, LISTA_CONTACTOS
    }

    public SOperacion(Object valor) {
        this.valor = valor;
    }

    public String exec(HashSet<String> conectados) throws IOException {
        Object obj = getValor();
        if (obj instanceof Cuenta) {
            Cuenta val = (Cuenta) obj;
            return exec_cuenta(val, conectados);
        } else if (obj instanceof Login) {
            Login val = (Login) obj;
            return exec_login(val, conectados);
        } else if (obj instanceof Mensaje) {
            Mensaje val = (Mensaje) obj;
            exec_mensaje(val);
        } else if (obj instanceof Busqueda) {
            Busqueda val = (Busqueda) obj;
            exec_busqueda(val);
        } else if (obj instanceof Solicitud_amistad) {
            Solicitud_amistad val = (Solicitud_amistad) obj;
            exec_solicitud_amistad(val);
        } else if (obj instanceof Solicitud_respuesta) {
            Solicitud_respuesta val = (Solicitud_respuesta) obj;
            exec_solicitud_respuesta(val);
        } else if (obj instanceof Lista_contactos) {
            Lista_contactos val = (Lista_contactos) obj;
            exec_lista_contactos(val);
        } else {
            System.err.println("Operación no soportada...");
        }
        return null;
    }

    String exec_login(Login val, HashSet<String> conectados) {
        String ret = null;
        if (conectados.contains(val.cuenta)) {
            ret = "Ya se encuentra logeado...";
        } else {
            conectados.add(val.cuenta);
            ret = "Operacion(Login) realizada con exito...";
        }
        return ret;
    }

    String exec_cuenta(Cuenta val, HashSet<String> conectados) throws IOException {
        String ret = null;

        final Path path = Paths.get(SFile.FILE_CUENTAS);
        if(!Files.exists(path)){
            Files.createFile(path);
        }

        Files.write(path, val.toString().getBytes(), StandardOpenOption.APPEND);
        ret = "Cuenta creada...";
        return ret;
    }

    void exec_mensaje(Mensaje val) {

    }

    void exec_busqueda(Busqueda val) {

    }

    void exec_solicitud_amistad(Solicitud_amistad val) {

    }

    void exec_solicitud_respuesta(Solicitud_respuesta val) {

    }

    void exec_lista_contactos(Lista_contactos val) {

    }

    public TIPO getTipo() {
        return this.tipo;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public Object getValor() {
        return this.valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public static class Login {

        String cuenta;
        String password;

        public Login(String cuenta, String password) {
            this.cuenta = cuenta;
            this.password = password;
        }
    }

    public static class Cuenta {

        String name;
        String password;

        public Cuenta(String name, String password) {
            this.name = name;
            this.password = password;
        }

        @Override
        public String toString() {
            String ret = null;

            ret = String.format("<cuenta>(usuario=%s;password=%s;)", getName(), getPassword());

            return ret;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
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

    public static class Solicitud_amistad {

        String emisor;
        HashSet<String> receptores;

        public Solicitud_amistad(String emisor, String... receptores) {
            this.emisor = emisor;
            this.receptores = new HashSet<>(Arrays.asList(receptores));
        }

    }

    public static class Solicitud_respuesta {

        String emisor;
        String receptor;
        String respuesta;

        public Solicitud_respuesta(String emisor, String receptor, String respuesta) {
            this.emisor = emisor;
            this.receptor = receptor;
            this.respuesta = respuesta;
        }
    }

    public static class Lista_contactos {

        String cuenta;

        public Lista_contactos(String cuenta) {
            this.cuenta = cuenta;
        }
    }

    public static class Busqueda {
    }

}
