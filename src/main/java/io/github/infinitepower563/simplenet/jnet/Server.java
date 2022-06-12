package io.github.infinitepower563.simplenet.jnet;

import io.github.infinitepower563.simplenet.exception.HttpException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private final int port;
    public Server(int port) {
        this.port = port;
    }
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket connection = ss.accept();
            System.out.println("Client connected.");
            Scanner in = new Scanner(connection.getInputStream());
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());

            Packet p = new Packet(in.nextLine());
            System.out.print("Packet received");
            System.out.println(p.fromData());
            out.write("Received. 200 OK\n".getBytes());
            out.flush();

            in.close();
            out.close();
            connection.close();
            ss.close();

            run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
