package io.github.infinitepower563.simplenet.example;

import io.github.infinitepower563.simplenet.exception.HttpException;
import io.github.infinitepower563.simplenet.jnet.Server;

public class JNetServer {
    public static void main(String[] args) throws HttpException {
        Server discardServer = new Server(8000);
        System.out.println("Server instated");
        discardServer.run();
    }
}
