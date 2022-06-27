package io.github.infinitepower563.simplenet.example;

import io.github.infinitepower563.simplenet.jnet.JNetHttpRequest;
import io.github.infinitepower563.simplenet.jnet.Packet;
import io.github.infinitepower563.simplenet.jnet.nios.Buffer;
import io.github.infinitepower563.simplenet.jnet.nios.SocketPipeline;

import java.util.Arrays;

public class JNetClient {
    public static void main(String[] args) throws Exception {
        JNetHttpRequest req = new JNetHttpRequest("localhost", 8000);
        System.out.println(req.sendRequest(new Packet(new String[] {"Content-Type: text/plain"}, "Hello world!")));

        SocketPipeline pipeline = new SocketPipeline("localhost",8000);
        Buffer buf = new Buffer(pipeline);
        Packet p  = new Packet(new String[] {"Content-Type: text/plain"}, "Hello world!");

        buf.write(p);
        String[] result = buf.flush();

        System.out.println(Arrays.toString(result));
    }
}
