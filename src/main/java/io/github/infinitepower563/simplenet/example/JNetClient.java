package io.github.infinitepower563.simplenet.example;

import io.github.infinitepower563.simplenet.jnet.Client;
import io.github.infinitepower563.simplenet.jnet.Packet;

public class JNetClient {
    public static void main(String[] args) throws Exception {
        Client client = new Client("localhost",8000);

        Packet p = new Packet(null, "Hello world!");

        client.send(p);
    }
}
