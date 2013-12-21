
package com.github.mensajeria.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SServer implements SIServer {

    private ServerSocket socket;
    private int port;
    private Thread thread;
    private Runnable runnable;
    private LinkedList<SOperacion> qopers = new LinkedList<>();


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
                    new Thread(new SClient(getSocket().accept()){

                        @Override
                        void process_input(Object obj){
                            // procesar la entrada 
                            String str = (String)str;
                            Scanner s = new Scanner();
                            Parser p = new Parser(s);

                            p.parse();

                            // agregar a la cola de operaciones del servidor
                            getOperaciones().addAll(p.operaciones);
                        }
                        @Override
                        void process_output(Object obj){
                            // procesar la la salida 
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
