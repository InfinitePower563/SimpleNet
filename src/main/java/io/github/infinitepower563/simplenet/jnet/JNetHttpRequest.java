package io.github.infinitepower563.simplenet.jnet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class JNetHttpRequest {
    private final String target, data;
    private final String[] headers;
    private final int port;

    public JNetHttpRequest(String target, String data, String[] headers, int port) {
        this.target = target;
        this.data = data;
        this.headers = headers;
        this.port = port;
    }

    public String sendRequest() throws IOException {
        Packet p = new Packet(headers, data);

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
