package com.github.mensajeria.servidor;

import com.github.mensajeria.compiler.servidor.Parser;
import com.github.mensajeria.compiler.servidor.SOperacion;
import com.github.mensajeria.compiler.servidor.Scanner;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SServer implements SIServer {

    private ServerSocket socket;
    private int port;
    private Thread thread;
    private Runnable runnable;
    private final HashSet<String> conectados=new HashSet<>();

    public SServer(int port) {
        setSocket(null);
        setThread(null);
        this.port = port;

        // watcher
        // new Thread(new Watcher(qopers) {
        //     @Override
        //     void process(Object o) {
        //         //ejecutar aqui las operaciones
        //     }
        // }).start();
    }

    @Override
    public void start() throws IOException {
        setSocket(new ServerSocket(getPort()));
        setRunnable(new Runnable4ever() {
            @Override
            public void run4ever() {
                try {
                    new Thread(new SClient(getSocket().accept()) {

                        @Override
                        void process_input(Object obj) {
                            System.out.println(obj);
                            // procesar la entrada 
                            Scanner s = new Scanner(new StringReader(obj.toString()));
                            Parser p = new Parser(s);
                            try {
                                p.parse();
                            } catch (Exception ex) {
                                write(ex.toString());
                                Logger.getLogger(SServer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           
                            
                            // agregar a la cola de operaciones del servidor
                            for (SOperacion oper : p.operaciones) {
                                try {
                                    write(oper.exec(conectados));
                                } catch (IOException ex) {
                                    Logger.getLogger(SServer.class.getName()).log(Level.SEVERE, null, ex);
                                    write(ex.toString());
                                }
                            }
                            
                            //write(obj);
                        }

                        @Override
                        void process_output(Object obj) {
                            // procesar la la salida 
                            try {
                                BufferedOutputStream o = new BufferedOutputStream(getSocket().getOutputStream());
                                o.write(obj.toString().getBytes(),0,obj.toString().getBytes().length);
                                o.flush();
                            } catch (IOException ex) {
                                Logger.getLogger(SServer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
                        }
                    }).start();
                } catch (IOException ex) {
                    Logger.getLogger(SServer.class.getName()).log(Level.SEVERE, null, ex);
                    this.terminate();
                }
            }
        });
        setThread(new Thread(getRunnable()));
        getThread().start();
    }

    @Override
    public void restart() throws IOException, InterruptedException {
        stop();
        start();
    }

    @Override
    public void stop() throws InterruptedException {
        final Runnable run = getRunnable();
        if (run instanceof Runnable4ever) {
            ((Runnable4ever) run).terminate();
        }

        getThread().join();

        setSocket(null);
        setThread(null);
        setRunnable(null);
    }

    //<editor-fold defaultstate="collapsed" desc="getter and setter">
    public ServerSocket getSocket() {
        return socket;
    }

    public int getPort() {
        return port;
    }

    private Thread getThread() {
        return thread;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private void setSocket(ServerSocket socket) {
        this.socket = socket;
    }

    private void setThread(Thread thread) {
        this.thread = thread;
    }

    private void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public Runnable getRunnable() {
        return runnable;
    }
    //</editor-fold>

}

//<editor-fold defaultstate="collapsed" desc="SIServer interface">
interface SIServer {

    public void start() throws IOException;

    public void restart() throws IOException, InterruptedException;

    public void stop() throws InterruptedException;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Runnable4ever abstract class">
abstract class Runnable4ever implements Runnable {

    private volatile boolean running = true;

    public void terminate() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            run4ever();
        }
    }

    abstract public void run4ever();
}
//</editor-fold>
