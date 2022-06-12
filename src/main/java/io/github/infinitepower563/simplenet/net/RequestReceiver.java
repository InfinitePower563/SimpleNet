package io.github.infinitepower563.simplenet.net;

import io.github.infinitepower563.simplenet.exception.HttpException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * A class which serves as a serverside for your HTTP requests.
 *
 * By default, the server discards all requests.
 *
 * @author InfinitePower563
 * @since 1.1.1
 */
public abstract class RequestReceiver {
    private boolean listening;
    private static Socket s;
    private static Scanner inStreamScanner;
    private static PrintWriter outStreamPrinter;
    public RequestReceiver(String url, int port) throws HttpException{
        ServerSocket ss;
        try {
            ss = new ServerSocket(port);
            s = ss.accept();
            InputStream inStream = s.getInputStream();
            OutputStream outStream = s.getOutputStream();
            inStreamScanner = new Scanner(inStream);
            outStreamPrinter = new PrintWriter(outStream);
            run();
        } catch (Exception e) {
            throw new HttpException("An error occurred while handling an HTTP request.", e);
        }
    }
    public void run() throws HttpException {
        try {
            try {
                listening = true;
                handle();
            } finally {
                s.close();
            }
        } catch (IOException e) {
           throw new HttpException("An error occurred while handling an HTTP request.", e);
        }
    }
    public void handle() throws HttpException {
        while(listening) {
            if (inStreamScanner.hasNext()) {
                StringBuilder request = new StringBuilder();
                String line = inStreamScanner.nextLine();
                while (!line.equals("==sn-close-str==")) {
                    request.append(line);
                    request.append("\n");
                    line = inStreamScanner.nextLine();
                }
                handleServerRequest(request.toString());
            }
        }
    }
    public void handleServerRequest(String request) throws HttpException {
        try {
            String t = "HTTP/1.1 200 OK\r\n";
            outStreamPrinter.write(t);
        } catch (Exception e) {
            throw new HttpException("An error occurred while handling an HTTP request.", e);
        }
    }
}
