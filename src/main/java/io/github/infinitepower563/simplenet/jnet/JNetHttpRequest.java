package io.github.infinitepower563.simplenet.jnet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public record JNetHttpRequest(String target, int port) {

    public String sendRequest(Packet p) throws IOException {
        Socket socket = new Socket(target, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream());

        out.writeUTF(p.fromData());

        out.flush();
        System.out.println("Sent packet: " + p.fromData());

        String response = in.nextLine();
        in.close();
        out.close();
        socket.close();
        return response;
    }
}
