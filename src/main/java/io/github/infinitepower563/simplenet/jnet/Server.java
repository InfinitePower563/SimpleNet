package io.github.infinitepower563.simplenet.jnet;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public record Server(int port) {

    public void run() {
        while (true) {
            try(ServerSocket ss = new ServerSocket(port);Socket connection = ss.accept()) {
                System.out.println("Client connected.");
                Scanner in = new Scanner(connection.getInputStream());
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());

                Packet p = new Packet(in.nextLine());
                System.out.print("Packet received");
                out.writeBytes("HTTP/1.1 200 OK\r\n");
                out.writeBytes("Received.\n");
                out.flush();

                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
