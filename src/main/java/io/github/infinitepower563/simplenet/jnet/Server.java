package io.github.infinitepower563.simplenet.jnet;

import io.github.infinitepower563.simplenet.exception.HttpException;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private boolean active = false;

    private Scanner in;
    private DataOutputStream out;

    public Server(int port) throws HttpException {
        init(port);

    }
    public void init(int port) throws HttpException {
        if (active) return;
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket server = ss.accept();

            in = new Scanner(server.getInputStream());
            out = new DataOutputStream(server.getOutputStream());

            active = true;
        } catch (Exception e) {
            throw new HttpException("Error while opening server.", e);
        }
    }
    public void stop() {
        active = false;
    }
    public void run() {
        while (active) {
            if (in.hasNext()) {
                try {
                    Packet received = new Packet(in.nextLine());
                    System.out.println(received);
                    out.writeUTF("HTTP/1.1 200 OK\r\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
