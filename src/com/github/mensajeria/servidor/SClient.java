
package com.github.mensajeria.servidor;

import com.github.mensajeria.compiler.servidor.Parser;
import com.github.mensajeria.compiler.servidor.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class SClient implements Runnable {

    Socket socket;
    Queue<Object> qin;
    Queue<Object> qout;

    PrintWriter out = null;

    public SClient(Socket socket) {
        System.err.println("created sclient...");
        this.socket = socket;
        this.qin = new LinkedList<>();
        this.qout = new LinkedList<>();

        // watchers
        new Thread(new Watcher(qin) {

            @Override
            void process(Object o) {
                // if (o instanceof String) {
                //     String text = (String) o;
                //     // parser aqui...
                //     Scanner s = new Scanner(new StringReader(text));
                //     Parser p = new Parser(s);
                //     try {
                //         p.parse();
                //         //escribo respuesta (lo pongo en la cola de out)
                //     } catch (Exception ex) {
                //         Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
                //     }
                // }
                process_input(o);
            }
        }).start();

        new Thread(new Watcher(qout) {

            @Override
            void process(Object o) {
                // PrintWriter out = getOut();
                // if (out == null) {
                //     return;
                // }

                // if (o instanceof String) {
                //     String text = (String) o;
                //     out.write(text);
                // }
                process_output(o);
            }
        }).start();
    }

    @Override
    public void run() {
        try {
            // inicializar el out e in... solo aqui
            this.out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                read(line);
            }
            
            
            //socket.close();
//            System.out.println("cerrado");
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    abstract void process_input(Object obj);
    abstract void process_output(Object obj);

    //<editor-fold defaultstate="collapsed" desc="getter methods">
    public Queue<Object> getQin() {
        return qin;
    }

    public Queue<Object> getQout() {
        return qout;
    }

    public PrintWriter getOut() {
        return out;
    }

    public Socket getSocket() {
        return socket;
    }

    //</editor-fold>
    /**
     * Agrega una entrada a la cola de entrada, para ser procesada...
     *
     * @param input
     */
    public void read(Object input) {
//        System.out.println(Thread.currentThread().getName() + "says: " + input);

        final Queue<Object> qi = getQin();
        synchronized (qi) {
            qi.offer(input);
            qi.notify();
        }
    }

    /**
     * Agrega una salida a la cola de salida, para ser procesada...
     *
     * @param output
     */
    public void write(Object output) {
        final Queue<Object> qo = getQout();
        synchronized (qo) {
            qo.offer(output);
            qo.notify();
        }
    }

}

//<editor-fold defaultstate="collapsed" desc="Watcher Abstract Class">
abstract class Watcher implements Runnable {

    Object obj;

    public Watcher(Object lock) {
        this.obj = lock;
    }

    @Override
    public void run() {
        Object o = getObj();
        synchronized (o) {
            if (o instanceof Queue) {
                Queue q = (Queue) o;
                for (;;) {
                    Object polled = q.poll();
                    if (polled != null) {
                        process(polled);
                    } else {
                        try {
                            o.wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Watcher.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }

    public Object getObj() {
        return obj;
    }

    abstract void process(Object o);
}
//</editor-fold>
