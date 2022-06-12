package io.github.infinitepower563.simplenet.jnet;

import io.github.infinitepower563.simplenet.exception.HttpException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Scanner in;
    private final DataOutputStream out;

    public Client(String url, int port) throws HttpException {
        try {
            Socket client = new Socket(url, port);
            in = new Scanner(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
        } catch(Exception e){
            throw new HttpException("Error while initiating client.", e);
        }
    }

    public String send(Packet p) throws IOException {
        out.writeUTF(p.fromData());
        out.flush();
        return in.nextLine();
    }
}
