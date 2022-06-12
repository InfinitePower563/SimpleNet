package io.github.infinitepower563.simplenet.example;

import io.github.infinitepower563.simplenet.jnet.JNetHttpRequest;

public class JNetClient {
    public static void main(String[] args) throws Exception {
        JNetHttpRequest req = new JNetHttpRequest("localhost", "Hello", new String[] {"Content-Type: text/plain"}, 8000);
        System.out.println(req.sendRequest());
    }
}
